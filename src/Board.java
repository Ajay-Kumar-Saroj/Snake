import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
public class Board extends JPanel implements ActionListener{
    private Image apple;
    private Image dot;
    private Image head;
    private int dots;
    private final int All_Dots = 900;
    private final int Dot_Size = 10;

private boolean  flag = true;
private boolean left = false;
private  boolean right = true;
private boolean up = false;
private boolean down = false;


private Timer timer;

    private final int RANDOM_Position = 29;
    private int apple_x;
    private int apple_y;
    private final int x[] = new int [All_Dots];
   private final int y[] = new int [All_Dots];
    //private Timer timer;
    Board(){
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setPreferredSize(new Dimension(300,300));
        setFocusable(true);
      //  requestFocusInWindow();
        loadimages();
        initGame();
    }
    public void loadimages(){
        ImageIcon i1 = new ImageIcon((ClassLoader.getSystemResource("apple.png")));
        apple = i1.getImage();
        ImageIcon i2 = new ImageIcon((ClassLoader.getSystemResource("dot.png")));
        dot = i2.getImage();
        ImageIcon i3 = new ImageIcon((ClassLoader.getSystemResource("head.png")));
        head = i3.getImage();
    }

    public void initGame(){
        dots = 3;
        for(int i=0; i<dots; i++)
        {
        y[i]=50;
        x[i]= 50 - i*Dot_Size;
        }
        locateApple();
         timer = new Timer(140,this);
        timer.start();
    }
    public void locateApple() {
        int r = (int) (Math.random()*RANDOM_Position);
        apple_x = r*Dot_Size;
         r = (int) (Math.random()*RANDOM_Position);
        apple_y = r*Dot_Size;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);// to call the parent component
        draw(g);
    } //  to show the images on frame there is a method pC
    public void draw(Graphics g){

        if(flag){
            g.drawImage(apple, apple_x, apple_y, this);

            for (int i = 0; i < dots; i++) {
                if (i == 0) {
                    g.drawImage(head, x[i], y[i], this);
                } else {
                    g.drawImage(dot, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }
        else {
            gameOver(g);
        }

    }
//    public void gameOver(Graphics g){
//        String msg ="Gamer Over";
//        Font font= new Font("SAN SERIF".font.Bold, size 14);
//        FontMetrics metrics = getFontMetrics(font);
//        g.drawString((300-metrices.stringWidth(msg)/2),300/2);
//    }

    public void gameOver(Graphics g) {

        String msg = "Game Over";

        Font font = new Font("SAN_SERIF", Font.BOLD, 14);
        FontMetrics metrics = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);

        // Center the text horizontally
        int x = (300 - metrics.stringWidth(msg)) / 2;

        // Center vertically
        int y = 300 / 2;

        g.drawString(msg, x, y);
    }
    public void move(){
        for (int i=dots; i>0; i--)
        {
            x[i] = x[i-1];  // KKK
            y[i] = y[i-1];
        }
        if(left)
        {
            x[0]= x[0]-Dot_Size;
        }
        if(right)
        {
            x[0]= x[0]+Dot_Size;
        }
        if(up)
        {
            y[0]= y[0]-Dot_Size;
        }
        if(down)
        {
            y[0]= y[0]+Dot_Size;
        }
        //x[0]+= Dot_Size;
        //y[0]+= Dot_Size;
    }
//    public void checkCollision() {
//        for (int i = dots; i > 0; i--) {
//            if ((i > 4) && (x[0] == x[i]) && (y[0] == y[i])) {
//                flag = false;
//            }
//        }
//        if (y[0] >= 300) {
//            flag = false;
//        }
//       else if (x[0] >= 300) {
//            flag = false;
//        }
//       else if (y[0] < 300) {
//            flag = false;
//        }
//       else if (x[0] < 300) {
//            flag = false;
//        }
//        else if(!flag) {
//            timer.stop();
//        }
//
//    }
public void checkCollision() {

    // self collision
    for (int i = dots; i > 4; i--) {
        if (x[0] == x[i] && y[0] == y[i]) {
            flag = false;
        }
    }

    // wall collision
    if (x[0] < 0 || x[0] >= 300 || y[0] < 0 || y[0] >= 300) {
        flag = false;
    }

    if (!flag) {
        timer.stop();
    }
}


    public void checkApple(){
        if((x[0]==apple_x) && y[0]==apple_y)
        {
            dots++;
            locateApple();
        }
    }
    public void actionPerformed(ActionEvent ae){
        if(flag) {
            checkApple();
            checkCollision();
            move();
        }

repaint();

    }

    public class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e){
            int key =e.getKeyCode();
            if (key== KeyEvent.VK_LEFT && (!right)){
                left=true;
                up= false;
                down=false;
                //right=false
            }
            if (key== KeyEvent.VK_RIGHT && (!left)){
                right=true;
                up= false;
                down=false;
                //right=false
            }
            if (key== KeyEvent.VK_UP && (!down)){
                up=true;
                right= false;
                left=false;
                //right=false
            }
            if (key== KeyEvent.VK_DOWN && (!up)){
                down=true;
                right= false;
                left=false;
                //right=false
            }
        }
    }
}
