import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;

public class A1143308_HW2 extends JFrame {

    private JLabel infoLabel;
    private DicePanel dicePanel;

    private int count = 0;
    private int sum = 0;

    private Random random = new Random();

    public A1143308_HW2() {

        // ===== 視窗設定 =====
        setTitle("骰子模擬器");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 視窗置中

        setLayout(new BorderLayout());

        // ===== 上方資訊 =====
        infoLabel = new JLabel(
                "已擲 0 次，總和 0，平均 0.00",
                JLabel.CENTER
        );

        infoLabel.setFont(
                new Font("Microsoft JhengHei", Font.PLAIN, 16)
        );

        add(infoLabel, BorderLayout.NORTH);


        // ===== 中間骰子 =====
        dicePanel = new DicePanel();

        add(dicePanel, BorderLayout.CENTER);


        // ===== 下方按鈕 =====
        JButton rollButton = new JButton("擲骰子");

        rollButton.setFont(
                new Font("Microsoft JhengHei", Font.BOLD, 18)
        );

        rollButton.setPreferredSize(new Dimension(120, 45));

        // 按下按鈕
        rollButton.addActionListener(e -> rollDice());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rollButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


    // ===== 擲骰子 =====
    private void rollDice() {

        // 產生 1～6
        int dice = random.nextInt(6) + 1;

        // 更新資料
        count++;
        sum += dice;

        // 更新骰子
        dicePanel.setNumber(dice);

        // 計算平均
        double average = (double) sum / count;

        // 更新上方文字
        infoLabel.setText(
                String.format(
                        "已擲 %d 次，總和 %d，平均 %.2f",
                        count,
                        sum,
                        average
                )
        );
    }


    // =====================================================
    // 自訂骰子面板
    // =====================================================

    class DicePanel extends JPanel {

        private int number = 1;

        public DicePanel() {
            setBackground(Color.WHITE);
        }

        public void setNumber(int number) {
            this.number = number;
            repaint();
        }


        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            // 開啟反鋸齒
            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            // ===== 骰子大小 =====
            int size = 130;

            int x = (getWidth() - size) / 2;
            int y = (getHeight() - size) / 2;

            // ===== 骰子陰影 =====
            g2.setColor(new Color(180, 180, 180));

            g2.fillRoundRect(
                    x + 6,
                    y + 8,
                    size,
                    size,
                    30,
                    30
            );


            // ===== 骰子本體 =====
            g2.setColor(Color.WHITE);

            g2.fillRoundRect(
                    x,
                    y,
                    size,
                    size,
                    30,
                    30
            );


            // ===== 骰子邊框 =====
            g2.setColor(new Color(150, 150, 150));

            g2.setStroke(new BasicStroke(2));

            g2.drawRoundRect(
                    x,
                    y,
                    size,
                    size,
                    30,
                    30
            );


            // ===== 點的設定 =====
            int pipSize = 20;

            int left = x + 35;
            int center = x + size / 2 - pipSize / 2;
            int right = x + size - 55;

            int top = y + 30;
            int middle = y + size / 2 - pipSize / 2;
            int bottom = y + size - 50;


            // 黑色骰子點
            g2.setColor(Color.BLACK);


            // ===== 根據點數畫點 =====

            switch (number) {

                case 1:
                    drawPip(g2, center, middle, pipSize);
                    break;

                case 2:
                    drawPip(g2, left, top, pipSize);
                    drawPip(g2, right, bottom, pipSize);
                    break;

                case 3:
                    drawPip(g2, left, top, pipSize);
                    drawPip(g2, center, middle, pipSize);
                    drawPip(g2, right, bottom, pipSize);
                    break;

                case 4:
                    drawPip(g2, left, top, pipSize);
                    drawPip(g2, right, top, pipSize);
                    drawPip(g2, left, bottom, pipSize);
                    drawPip(g2, right, bottom, pipSize);
                    break;

                case 5:
                    drawPip(g2, left, top, pipSize);
                    drawPip(g2, right, top, pipSize);
                    drawPip(g2, center, middle, pipSize);
                    drawPip(g2, left, bottom, pipSize);
                    drawPip(g2, right, bottom, pipSize);
                    break;

                case 6:
                    drawPip(g2, left, top, pipSize);
                    drawPip(g2, right, top, pipSize);

                    drawPip(g2, left, middle, pipSize);
                    drawPip(g2, right, middle, pipSize);

                    drawPip(g2, left, bottom, pipSize);
                    drawPip(g2, right, bottom, pipSize);
                    break;
            }

            g2.dispose();
        }


        // ===== 畫骰子上的一個點 =====
        private void drawPip(
                Graphics2D g2,
                int x,
                int y,
                int size
        ) {

            g2.fillOval(x, y, size, size);
        }
    }


    // ===== 主程式 =====
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new A1143308_HW2();
        });
    }
}