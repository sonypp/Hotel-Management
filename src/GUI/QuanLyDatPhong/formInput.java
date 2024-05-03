package GUI.QuanLyDatPhong;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class formInput extends JFrame {
    private JLabel lblSoLuong;
    private JTextField txtSoLuong;
    private JButton btnThayDoi;
    private JButton btnDong;
    private int number;
    private int dialogResult = JOptionPane.CANCEL_OPTION;

    public formInput() {
        setTitle("Nhập số lượng muốn thay đổi");
        getContentPane().setLayout(null);

        lblSoLuong = new JLabel("Số lượng:");
        lblSoLuong.setBounds(20, 20, 80, 25);
        getContentPane().add(lblSoLuong);

        txtSoLuong = new JTextField();
        txtSoLuong.setBounds(100, 20, 150, 25);
        txtSoLuong.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '\b') {
                    e.consume(); // Ignore non-numeric characters and backspace
                }
            }
        });
        getContentPane().add(txtSoLuong);

        btnThayDoi = new JButton("Thay đổi");
        btnThayDoi.setBounds(30, 60, 100, 25);
        btnThayDoi.setBackground(new Color(66, 139, 202)); // Blue color
        btnThayDoi.setForeground(Color.WHITE); // Text color
        btnThayDoi.setFocusPainted(false);
        btnThayDoi.setBorderPainted(false);
        btnThayDoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThayDoi.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnThayDoi.setBackground(new Color(33, 97, 140)); // Darker blue on hover
            }

            public void mouseExited(MouseEvent e) {
                btnThayDoi.setBackground(new Color(66, 139, 202)); // Original blue color on exit
            }
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		try {
                    number = Integer.parseInt(txtSoLuong.getText());
                    dialogResult = JOptionPane.YES_OPTION;
                    dispose(); // Close the dialog
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
        	}
        });
        getContentPane().add(btnThayDoi);

        btnDong = new JButton("Đóng");
        btnDong.setBounds(150, 60, 100, 25);
        btnDong.setBackground(Color.LIGHT_GRAY); // Light gray color
        btnDong.setFocusPainted(false);
        btnDong.setBorderPainted(false);
        btnDong.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDong.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnDong.setBackground(Color.GRAY); // Darker gray on hover
            }

            public void mouseExited(MouseEvent e) {
                btnDong.setBackground(Color.LIGHT_GRAY); // Original light gray color on exit
            }
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		dialogResult = JOptionPane.CANCEL_OPTION;
                dispose(); // Close the dialog
        	}
        });
        getContentPane().add(btnDong);

        setSize(300, 130);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setResizable(false); // Disable resize
    }

    public int getNumber() {
        return number;
    }

    public int getDialogResult() {
        return dialogResult;
    }
}
