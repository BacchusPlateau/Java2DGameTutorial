package main;
import javax.swing.JFrame;

//https://www.youtube.com/watch?v=om59cwR7psI 

//last up: https://youtu.be/ugzxCcpoSdE?si=9ZZR9LeA6SGIVfot&t=503 

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure Tutorial");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();  //forces resolution

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}