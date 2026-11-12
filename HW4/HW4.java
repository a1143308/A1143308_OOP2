
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class HW4 extends JFrame {

    private JLabel infoLabel;
    private JLabel statsLabel;
    private DicePanel dicePanel;

    private int count = 0;
    private int sum = 0;

    // 紀錄 1～6 各出現幾次
    private int[] frequency = new int[7];

    private Random random = new Random();

    public HW4() {

        // ===== 視窗設定 =====
        setTitle("骰子模擬器");
        setSize(500, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));


        // ===== 上方資訊 =====
        infoLabel = new JLabel(
                "已擲 0 次，總和 0，平均 0.00",
                JLabel.CENTER
        );

        infoLabel.setFont(
                new Font("Microsoft JhengHei", Font.BOLD, 18)
        );

        add(infoLabel, BorderLayout.NORTH);


        // ===== 中間骰子 =====
        dicePanel = new DicePanel();

        add(dicePanel, BorderLayout.CENTER);


        // ===== 下方按鈕 =====

        JButton rollButton = new JButton("擲骰子");
        JButton tenButton = new JButton("連骰十次");
        JButton resetButton = new JButton("重新開始");

        rollButton.addActionListener(e -> rollDice());

        tenButton.addActionListener(e -> rollTenTimes());

        resetButton.addActionListener(e -> resetGame());


        // 按鈕字體
        Font buttonFont = new Font(
                "Microsoft JhengHei",
                Font.BOLD,
                15
        );

        rollButton.setFont(buttonFont);
        tenButton.setFont(buttonFont);
        resetButton.setFont(buttonFont);


        // 按鈕面板
        JPanel buttonPanel = new JPanel();

        buttonPanel.add(rollButton);
        buttonPanel.add(tenButton);
        buttonPanel.add(resetButton);


        // ===== 點數統計 =====
        statsLabel = new JLabel(
                "1點：0次　2點：0次　3點：0次"
                + "　4點：0次　5點：0次　6點：0次",
                JLabel.CENTER
        );

        statsLabel.setFont(
                new Font("Microsoft JhengHei", Font.PLAIN, 13)
        );


        // 將按鈕與統計放在下方
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));

        bottomPanel.add(buttonPanel);
        bottomPanel.add(statsLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }


    // ===== 單次擲骰子 =====
    private void rollDice() {

        int dice = random.nextInt(6) + 1;

        updateData(dice);
    }


    // ===== 連續擲骰十次 =====
    private void rollTenTimes() {

        for (int i = 0; i < 10; i++) {

            int dice = random.nextInt(6) + 1;

            updateData(dice);
        }
    }


    // ===== 更新統計資料 =====
    private void updateData(int dice) {

        // 更新總次數
        count++;

        // 更新點數總和
        sum += dice;

        // 更新各點數出現次數
        frequency[dice]++;

        // 更新骰子畫面
        dicePanel.setNumber(dice);

        // 計算平均
        double average = (double) sum / count;

        // 更新上方資訊
        infoLabel.setText(
                String.format(
                        "已擲 %d 次，總和 %d，平均 %.2f",
                        count,
                        sum,
                        average
                )
        );

        // 更新各點數統計
        statsLabel.setText(
                "1點：" + frequency[1] + "次　"
                + "2點：" + frequency[2] + "次　"
                + "3點：" + frequency[3] + "次　"
                + "4點：" + frequency[4] + "次　"
                + "5點：" + frequency[5] + "次　"
                + "6點：" + frequency[6] + "次"
        );
    }


    // ===== 重新開始 =====
    private void resetGame() {

        count = 0;
        sum = 0;

        // 將所有點數統計歸零
        for (int i = 1; i <= 6; i++) {
            frequency[i] = 0;
        }

        // 骰子恢復為 1
        dicePanel.setNumber(1);

        // 更新資訊
        infoLabel.setText(
                "已擲 0 次，總和 0，平均 0.00"
        );

        // 更新點數統計
        statsLabel.setText(
                "1點：0次　2點：0次　3點：0次"
                + "　4點：0次　5點：0次　6點：0次"
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


            // ===== 點的位置 =====
            int pipSize = 20;

            int left = x + 35;
            int center = x + size / 2 - pipSize / 2;
            int right = x + size - 55;

            int top = y + 30;
            int middle = y + size / 2 - pipSize / 2;
            int bottom = y + size - 50;


            // ===== 根據點數設定顏色 =====

            if (number == 1) {

                g2.setColor(Color.RED);

            } else if (number == 6) {

                g2.setColor(Color.GREEN);

            } else {

                g2.setColor(Color.BLACK);
            }


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
            new HW4();
        });
    }
}
