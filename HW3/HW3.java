
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HW3 extends JFrame {

    private JComboBox<String> typeBox;
    private JComboBox<String> fromBox;
    private JComboBox<String> toBox;

    private JTextField inputField;
    private JTextField resultField;

    // 三種換算類型的單位
    private String[][] units = {
        {"公尺", "公分", "英吋", "英尺"},
        {"公斤", "公克", "磅", "盎司"},
        {"攝氏", "華氏", "克氏"}
    };

    public HW3() {

        // ===== 視窗設定 =====
        setTitle("單位換算器");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // ===== NORTH：選擇換算類型 =====
        typeBox = new JComboBox<>(
            new String[]{"長度", "重量", "溫度"}
        );

        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("換算類型："));
        northPanel.add(typeBox);

        add(northPanel, BorderLayout.NORTH);

        // ===== CENTER：輸入與單位選擇 =====
        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));

        inputField = new JTextField(10);
        resultField = new JTextField(10);
        resultField.setEditable(false);

        fromBox = new JComboBox<>(units[0]);
        toBox = new JComboBox<>(units[0]);

        JPanel row1 = new JPanel();
        row1.add(new JLabel("來源："));
        row1.add(inputField);
        row1.add(fromBox);

        JPanel row2 = new JPanel();
        row2.add(new JLabel("結果："));
        row2.add(resultField);
        row2.add(toBox);

        centerPanel.add(row1);
        centerPanel.add(row2);

        add(centerPanel, BorderLayout.CENTER);

        // ===== SOUTH：換算按鈕 =====
        JButton convertButton = new JButton("換算");

        JPanel southPanel = new JPanel();
        southPanel.add(convertButton);

        add(southPanel, BorderLayout.SOUTH);

        // ===== 切換換算類型 =====
        typeBox.addActionListener(e -> {
            int index = typeBox.getSelectedIndex();

            fromBox.setModel(new DefaultComboBoxModel<>(units[index]));
            toBox.setModel(new DefaultComboBoxModel<>(units[index]));
        });

        // ===== 按下換算按鈕 =====
        convertButton.addActionListener(e -> convert());

        setVisible(true);
    }

    // ===== 換算功能 =====
    private void convert() {

        try {
            double value = Double.parseDouble(inputField.getText());

            int type = typeBox.getSelectedIndex();
            int from = fromBox.getSelectedIndex();
            int to = toBox.getSelectedIndex();

            double result = 0;

            if (type == 0) {
                result = convertLength(value, from, to);
            }
            else if (type == 1) {
                result = convertWeight(value, from, to);
            }
            else {
                result = convertTemperature(value, from, to);
            }

            resultField.setText(String.format("%.4f", result));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "請輸入正確的數字！",
                "輸入錯誤",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // ===== 長度換算：統一轉成公尺 =====
    private double convertLength(double value, int from, int to) {

        double[] rate = {1, 0.01, 0.0254, 0.3048};

        return value * rate[from] / rate[to];
    }

    // ===== 重量換算：統一轉成公斤 =====
    private double convertWeight(double value, int from, int to) {

        double[] rate = {1, 0.001, 0.45359237, 0.028349523125};

        return value * rate[from] / rate[to];
    }

    // ===== 溫度換算 =====
    private double convertTemperature(double value, int from, int to) {

        double celsius;

        // 先轉換成攝氏
        if (from == 0) {
            celsius = value;
        }
        else if (from == 1) {
            celsius = (value - 32) * 5 / 9;
        }
        else {
            celsius = value - 273.15;
        }

        // 再從攝氏轉換成目標單位
        if (to == 0) {
            return celsius;
        }
        else if (to == 1) {
            return celsius * 9 / 5 + 32;
        }
        else {
            return celsius + 273.15;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HW3());
    }
}
