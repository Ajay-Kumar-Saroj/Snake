
import javax.swing.*;

public class SnakeGame extends JFrame{
    public SnakeGame (){
        super("Snake Game");
        add(new Board());
        pack();
        //setSize(300, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);


    }
    public static void main(String[] args) {
new SnakeGame().setVisible(true);
    }
}

//import javax.swing.*;
//
//public class SnakeGame extends JFrame {
//
//    public SnakeGame() {
//        setVisible(true);
//        setSize(400, 400);
//    }
//
//    public static void main(String[] args) {
//        new SnakeGame();
//    }
//}