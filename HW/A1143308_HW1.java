import javax.swing.*;
import java.awt.*;

public class A1143308_HW1 extends JFrame {

    public A1143308_HW1() {

        setTitle("登入");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("帳號：");
        JTextField t1 = new JTextField();

        JLabel l2 = new JLabel("密碼：");
        JPasswordField t2 = new JPasswordField();

        JButton btn = new JButton("登入");

        // 設定元件位置
        l1.setBounds(30, 30, 60, 30);
        t1.setBounds(90, 30, 150, 30);

        l2.setBounds(30, 70, 60, 30);
        t2.setBounds(90, 70, 150, 30);

        btn.setBounds(100, 110, 100, 30);

        // 加入視窗
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(btn);

        // 按下登入按鈕
        btn.addActionListener(e -> {

            String account = t1.getText();
            String password = new String(t2.getPassword());

            if (account.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "登入成功");
            } else {
                JOptionPane.showMessageDialog(this, "帳號或密碼錯誤");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new A1143308_HW1();
    }
}