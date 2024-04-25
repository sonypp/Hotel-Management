package GUI.NhanVien;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import BUS.NhanVienBUS;
import DTO.NhanVienDTO;
import com.toedter.calendar.JDateChooser;

public class PanelStaffInfo extends JPanel {
	private static String[] optionChucVu = {"Quản lý","Lễ tân","Kế Toán","Bếp"};
	private static final long serialVersionUID = 1L;
	private JTextField txtMaNV;
	private ButtonGroup btnGT;
	private JRadioButton btnGT_Nu;
	private JRadioButton btnGT_Nam;
	private JComboBox<String> cbBoxChucVu;
	private JSpinner spinnerLuong1Ngay;
	private JSpinner spinnerSoNgayPhep;
	private JButton btnSubmit;
	private JTextField txtTenNV;
	private JTextField txtEmail;
	private JDateChooser dateNgaySinh;
	private JDateChooser dateNgayVaoLam;
	private JLabel errNgaySinh;
	private JLabel errNgayVaoLam;
	private JLabel errEmail;
	private JLabel errTenNV;
	private NhanVienDTO nhanvien = null;
	private int gioiTinh;
	private int index;
	private boolean isUpdate; //true: Update, false: Add
	public ActionListener closeListener;
	/**
	 * Create the panel.
	 */
	public PanelStaffInfo(int count,ActionListener closeListener) {
		isUpdate = false;
		index = count + 1;
		this.closeListener = closeListener;

		setBounds(new Rectangle(0, 0, 700, 600));
		setBackground(new Color(245, 245, 245));
		setLayout(null);
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(new Color(255, 255, 255));
		panelTitle.setBounds(24, 25, 651, 50);
		add(panelTitle);
		panelTitle.setLayout(null);
		
		JLabel lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setBounds(0, 0, 651, 50);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panelTitle.add(lblTitle);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBackground(new Color(255, 255, 255));
		panelInfo.setBounds(24, 87, 651, 494);
		add(panelInfo);
		panelInfo.setLayout(null);
		
		JPanel panelTop = new JPanel();
		panelTop.setBounds(27, 6, 596, 77);
		panelTop.setBackground(new Color(255, 255, 255));
		panelInfo.add(panelTop);
		panelTop.setLayout(new GridLayout(0, 1, 5, 10));

		JLabel lblMaNV = new JLabel("Mã Nhân Viên");
		lblMaNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelTop.add(lblMaNV);
		
		txtMaNV = new JTextField();
		txtMaNV.setFocusable(true);
		txtMaNV.setEditable(false);
		panelTop.add(txtMaNV);
		txtMaNV.setColumns(10);

		JPanel panelCenter = new JPanel();
		panelCenter.setBounds(27, 312, 596, 170);
		panelCenter.setBackground(new Color(255, 255, 255));
		panelInfo.add(panelCenter);
		panelCenter.setLayout(new GridLayout(4, 2, 35, 5));
		
		JLabel lblGioiTinh = new JLabel("Giới Tính");
		lblGioiTinh.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelCenter.add(lblGioiTinh);
		
		JLabel lblChucVu = new JLabel("Chức Vụ");
		lblChucVu.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelCenter.add(lblChucVu);
		
		JPanel panelBtnGr = new JPanel();
		panelBtnGr.setBackground(new Color(255, 255, 255));
		panelCenter.add(panelBtnGr);
		panelBtnGr.setLayout(new GridLayout(0, 2, 0, 0));

		btnGT = new ButtonGroup();

		btnGT_Nam = new JRadioButton("Nam");
		panelBtnGr.add(btnGT_Nam);
		btnGT.add(btnGT_Nam);
		btnGT_Nam.setSelected(true);

		btnGT_Nu = new JRadioButton("Nữ");
		panelBtnGr.add(btnGT_Nu);
		btnGT.add(btnGT_Nu);

		btnGT_Nam.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseGioiTinh(e);
				if (!isUpdate) updateID();
			}
		});
		btnGT_Nu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chooseGioiTinh(e);
				if (!isUpdate) updateID();
			}
		});


		cbBoxChucVu = new JComboBox<>(optionChucVu);
		panelCenter.add(cbBoxChucVu);

		JLabel lblLuong1Ngay = new JLabel("Lương 1 Ngày");
		lblLuong1Ngay.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelCenter.add(lblLuong1Ngay);
		
		JLabel lblSoNgayPhep = new JLabel("Số ngày nghỉ phép");
		lblSoNgayPhep.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelCenter.add(lblSoNgayPhep);

		SpinnerNumberModel spinnerModelLuong1Ngay = new SpinnerNumberModel(0,0,5000000,1000);
		spinnerLuong1Ngay = new JSpinner(spinnerModelLuong1Ngay);
		panelCenter.add(spinnerLuong1Ngay);
		spinnerLuong1Ngay.setFocusable(false);

		SpinnerNumberModel spinnerModelSoNgayPhep = new SpinnerNumberModel(0, 0, 30, 1);
		spinnerSoNgayPhep = new JSpinner(spinnerModelSoNgayPhep);
		panelCenter.add(spinnerSoNgayPhep);
		spinnerSoNgayPhep.setFocusable(false);
		
		JPanel panelCenterHasRegex = new JPanel();
		panelCenterHasRegex.setBackground(Color.WHITE);
		panelCenterHasRegex.setBounds(27, 95, 596, 218);
		panelInfo.add(panelCenterHasRegex);
		panelCenterHasRegex.setLayout(new GridLayout(0, 2, 35, 2));
		
		JLabel lblTenNV = new JLabel("Tên Nhân Viên");
		lblTenNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelCenterHasRegex.add(lblTenNV);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelCenterHasRegex.add(lblEmail);
		
		txtTenNV = new JTextField();
		txtTenNV.setColumns(10);
		panelCenterHasRegex.add(txtTenNV);
		//Kiểm tra dữ liệu nhập vào
		txtTenNV.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validInput();
            }

            private void validInput() {
                String text = txtTenNV.getText().trim();
                if (text.isEmpty()) {
                    errTenNV.setText("* Không được để trống");
                } else if (text.matches(".*\\d.*")) {
                    errTenNV.setText("* Không được nhập số");
                } else {
                    errTenNV.setText(""); // Xóa thông báo lỗi nếu dữ liệu hợp lệ,dữ liệu đúng
                }
            }
        });
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		panelCenterHasRegex.add(txtEmail);
		txtEmail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkEmail();
            }

            private void checkEmail() {
				String text = txtEmail.getText().trim();
				if (text.isEmpty()) {
					errEmail.setText("* Không được để trống");
				} else if (!isValidEmail(text)) {
					errEmail.setText("* Email không hợp lệ");
				} else {
					errEmail.setText(""); // Xóa thông báo lỗi nếu dữ liệu hợp lệ
				}
            }
            private boolean isValidEmail(String email) {
                // Kiểm tra định dạng email
                return email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
            }
        });
		
		errTenNV = new JLabel("* Không được để trống");
		errTenNV.setForeground(new Color(220, 20, 60));
		errTenNV.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelCenterHasRegex.add(errTenNV);
		
		errEmail = new JLabel("* Không được để trống");
		errEmail.setForeground(new Color(220, 20, 60));
		errEmail.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelCenterHasRegex.add(errEmail);
		
		JLabel lblNgaySinh_1 = new JLabel("Ngày Sinh");
		lblNgaySinh_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelCenterHasRegex.add(lblNgaySinh_1);
		
		JLabel lblNgayVaoLam_1 = new JLabel("Ngày Vào Làm");
		lblNgayVaoLam_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelCenterHasRegex.add(lblNgayVaoLam_1);

		dateNgaySinh = new JDateChooser();
		dateNgaySinh.setDateFormatString("dd-MM-yyyy");
		panelCenterHasRegex.add(dateNgaySinh);
        dateNgaySinh.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                checkIsOver18();
				checkNgayVaoLam();
            }

            private void checkIsOver18() {
                LocalDate selectedDate = dateNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
                if (selectedDate != null) {
                    LocalDate today = LocalDate.now();
                    long timeDiff = today.getYear() - selectedDate.getYear();
                    if (timeDiff < 18) {
                        errNgaySinh.setText("* Chưa đủ 18 tuổi");
                    } else {
                        errNgaySinh.setText(""); // Xóa thông báo lỗi nếu tuổi hợp lệ
                    }
                }
            }
			private void checkNgayVaoLam() {
				if (dateNgaySinh.getDate() != null){
					dateNgayVaoLam.setEnabled(true);
					errNgayVaoLam.setText("");
				}
			}
        });
		
		dateNgayVaoLam = new JDateChooser();
		dateNgayVaoLam.setDateFormatString("dd-MM-yyyy");
		dateNgayVaoLam.setDate(new Date());
		panelCenterHasRegex.add(dateNgayVaoLam);
		dateNgayVaoLam.setEnabled(false);
		dateNgayVaoLam.getDateEditor().addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                checkIsOver18FromBirth();
				if (!isUpdate) updateID();
            }

            private void checkIsOver18FromBirth() {
					LocalDate birthDate = dateNgaySinh.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
					LocalDate selectedDate = dateNgayVaoLam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();;
					if (selectedDate != null) {
						long timeDiff = selectedDate.getYear() - birthDate.getYear();
						if (timeDiff < 18) {
							errNgayVaoLam.setText("* Chưa đủ 18 tuổi so với ngày sinh");
						} else {
							errNgayVaoLam.setText(""); // Xóa thông báo lỗi nếu tuổi hợp lệ, dữ liệu đúng
						}
					}
            }
        });

		errNgaySinh = new JLabel("* Vui lòng chọn ngày sinh");
		errNgaySinh.setForeground(new Color(220, 20, 60));
		errNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panelCenterHasRegex.add(errNgaySinh);

		errNgayVaoLam = new JLabel("");
		errNgayVaoLam.setFont(new Font("Tahoma", Font.PLAIN, 10));
		errNgayVaoLam.setForeground(new Color(220, 20, 60));
		panelCenterHasRegex.add(errNgayVaoLam);

		JPanel panelDown = new JPanel();
		panelDown.setBounds(24, 582, 651, 70);
		add(panelDown);
		panelDown.setBackground(new Color(255, 255, 255));
		panelDown.setLayout(null);
		
		btnSubmit = new JButton("Xác Nhận");
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmit.setForeground(new Color(255, 255, 255));
		btnSubmit.setBackground(new Color(119, 136, 153));
		btnSubmit.setBounds(262, 13, 127, 44);
		panelDown.add(btnSubmit);
		//Sau khi setInfo rồi mới đóng frame;
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateInfoNV(e);
				if(getNhanvien() != null){
					closeListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "close"));
				}
			}
		});

		updateID();
	}
	public void setInfoNV(Object[] data){
		setInfoNV((String)data[0],(String)data[1],(int)data[2],(int)data[6],(int)data[5],(Date)data[3],(Date)data[4],(String)data[8],(int)data[7]);
	}
	//0,1,2,6,5,3,4,8,7
	public void setInfoNV(String maNV, String tenNV, int gioiTinh, int soNgayPhep, int chucVu, Date ngaySinh, Date ngayVaoLam, String emailNV, int luong1Ngay){
		isUpdate = true;
		txtMaNV.setText(maNV);
		index = Integer.parseInt(maNV.substring(maNV.length()-4));
		txtTenNV.setText(tenNV);
		txtEmail.setText(emailNV);
		spinnerSoNgayPhep.setValue(soNgayPhep);
		spinnerLuong1Ngay.setValue(luong1Ngay);
		dateNgaySinh.setDate(ngaySinh);
		cbBoxChucVu.setSelectedIndex(chucVu);
		switch (gioiTinh){
			case 0: {
				btnGT_Nam.setSelected(true);
				break;
			}
			case 1: {
				btnGT_Nu.setSelected(true);
				break;
			}
		}
		dateNgayVaoLam.setDate(ngayVaoLam);
	}
	public void updateInfoNV(ActionEvent e){
		String mess;
		if (errTenNV.getText().trim().isEmpty() && errEmail.getText().trim().isEmpty() && errNgaySinh.getText().trim().isEmpty() && errNgayVaoLam.getText().trim().isEmpty()){
			if (isUpdate) {
				nhanvien = new NhanVienDTO(txtMaNV.getText().trim(),txtTenNV.getText().trim(),gioiTinh,(int)spinnerSoNgayPhep.getValue(),cbBoxChucVu.getSelectedIndex(),
						dateNgaySinh.getDate(),dateNgayVaoLam.getDate(),txtEmail.getText().trim(),(int)spinnerLuong1Ngay.getValue(),0);
			}
			else{
				mess = "Thêm nhân viên thành công";
				nhanvien = new NhanVienDTO(txtMaNV.getText().trim(),txtTenNV.getText().trim(),gioiTinh,(int)spinnerSoNgayPhep.getValue(),cbBoxChucVu.getSelectedIndex(),
							dateNgaySinh.getDate(),dateNgayVaoLam.getDate(),txtEmail.getText().trim(),(int)spinnerLuong1Ngay.getValue(),0);
				JOptionPane.showMessageDialog(null,mess,"Thông Báo",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null,"Vui lòng thực hiện đầy đủ các yêu cầu","Cảnh Báo",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void chooseGioiTinh(ActionEvent e){
		JRadioButton selectedRadioButton = (JRadioButton) e.getSource();
		if (selectedRadioButton == btnGT_Nam) {
			gioiTinh = 0;
		} else if (selectedRadioButton == btnGT_Nu) {
			gioiTinh = 1;
		}
	}

	public String createID() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String str_dateNgayVaoLam = dateFormat.format(dateNgayVaoLam.getDate());

		return String.format("NV%1d%s%04d",gioiTinh,str_dateNgayVaoLam,index);
	}

	public void updateID() {
		txtMaNV.setText(createID());
	}

	public NhanVienDTO getNhanvien() {
		return nhanvien;
	}

}
