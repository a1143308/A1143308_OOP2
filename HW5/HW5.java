
import javax.swing.*;
import java.awt.*;

public class HW5 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("小房子");

        MyPanel panel = new MyPanel();

        frame.add(panel);
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}

class MyPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. 天空與草地
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, 500, 250);

        g.setColor(Color.GREEN);
        g.fillRect(0, 250, 500, 150);


        // 2. 雲朵
        g.setColor(Color.WHITE);
        g.fillOval(35, 25, 80, 60);
        g.fillOval(5, 45, 80, 55);
        g.fillOval(75, 45, 80, 55);


        // 3. 太陽光芒
        g.setColor(Color.ORANGE);

        for (int i = 0; i < 8; i++) {

            double angle = Math.toRadians(i * 45);

            int x1 = (int)(420 + Math.cos(angle) * 45);
            int y1 = (int)(80 + Math.sin(angle) * 45);

            int x2 = (int)(420 + Math.cos(angle) * 65);
            int y2 = (int)(80 + Math.sin(angle) * 65);

            g.drawLine(x1, y1, x2, y2);
        }

        // 太陽本體
        g.setColor(Color.YELLOW);
        g.fillOval(380, 40, 80, 80);


        // 4. 房屋主體
        g.setColor(Color.ORANGE);
        g.fillRect(150, 160, 200, 150);

        g.setColor(Color.BLACK);
        g.drawRect(150, 160, 200, 150);


        // 5. 屋頂
        g.setColor(Color.RED);
        g.drawLine(100, 160, 400, 160);
        g.drawLine(250, 40, 100, 160);
        g.drawLine(250, 40, 400, 160);


        // 6. 窗戶
        g.setColor(Color.WHITE);
        g.fillRect(170, 180, 50, 50);
        g.fillRect(280, 180, 50, 50);

        g.setColor(Color.BLACK);
        g.drawRect(170, 180, 50, 50);
        g.drawRect(280, 180, 50, 50);

        g.drawLine(195, 180, 195, 230);
        g.drawLine(305, 180, 305, 230);
        g.drawLine(170, 205, 220, 205);
        g.drawLine(280, 205, 330, 205);


        // 7. 門
        g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(220, 240, 60, 70, 10, 10);

        // 門把
        g.setColor(Color.YELLOW);
        g.fillOval(260, 270, 10, 10);


        // 8. 簽名
        g.setColor(Color.BLACK);
        g.drawString("A1143308 林亞蒨", 370, 350);

    }
}
