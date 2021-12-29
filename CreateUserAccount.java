package software;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateUserAccount implements ActionListener{
	private JFrame frame;
	private JPanel panel;
	JTextField inputName;
	JTextField inputGender;
	JTextField inputBirthday;
	JTextField inputEmail;
	JTextField inputAccount;
	JTextField inputPassword;
	private JButton create;
	User user;
	private JTextField inputStudentNumber;

	CreateUserAccount(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("個人帳號");		//sets title of frame 
		frame.setBounds(100, 50, 360, 350);  //設定窗體座標以及打下
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //設定窗體可關閉
		frame.setResizable(false);  //設定窗體大小不可以改變
		frame.setVisible(true);    //設定窗體可見
		panel = new JPanel();
		frame.setContentPane(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);	 
        
        JLabel name = new JLabel("姓名:");
        name.setBounds(80,36, 54, 28);
        panel.add(name);
        
        inputName = new JTextField();
        inputName.setBounds(139,37, 161, 25);
        panel.add(inputName);
        
        JLabel gender = new JLabel("性別:");
        gender.setBounds(80,76, 54, 28);
        panel.add(gender);
        
        inputGender = new JTextField();
        inputGender.setBounds(139,77, 161, 25);
        panel.add(inputGender);
        
        JLabel birthday = new JLabel("生日:");
        birthday.setBounds(80,116, 54, 28);
        panel.add(birthday);
        
        inputBirthday = new JTextField();
        inputBirthday.setBounds(139,118, 161, 25);
        panel.add(inputBirthday);
        
        JLabel email = new JLabel("email:");
        email.setBounds(80,156, 54, 28);
        panel.add(email);
        
        inputEmail = new JTextField();
        inputEmail.setBounds(139,158, 161, 25);
        panel.add(inputEmail);
        
        JLabel account = new JLabel("帳號:");
        account.setBounds(80,196, 54, 28);
        panel.add(account);
        
        inputAccount = new JTextField();
        inputAccount.setBounds(139,198, 161, 25);
        panel.add(inputAccount);
        
        JLabel password = new JLabel("密碼:");
        password.setBounds(80,236, 54, 28);
        panel.add(password);
        
        inputPassword = new JTextField();
        inputPassword.setBounds(139,238, 161, 25);
        panel.add(inputPassword);
        
        create = new JButton("建立");
        create.setBounds(90,270, 80, 23);
        panel.add(create);
        create.addActionListener(this);
        
        
        JLabel studentNumber = new JLabel("學號:");
        studentNumber.setBounds(80, 8, 61, 16);
        panel.add(studentNumber);
        
        inputStudentNumber = new JTextField();
        inputStudentNumber.setBounds(139, 3, 161, 26);
        panel.add(inputStudentNumber);
        inputStudentNumber.setColumns(10);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == create) {
			String inAccount = inputAccount.getText();
			int check = 0;
			if(inAccount.charAt(0) != 'a') {
				check = 1;
				JOptionPane.showMessageDialog(null, "帳號開頭必須是a", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			 //宣告Connection物件
	        Connection con;
	        //驅動程式名
	        String driver = "com.mysql.cj.jdbc.Driver";
	        //URL指向要訪問的資料庫名
	        String url = "jdbc:mysql://localhost:3306/software?sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false";
	        //MySQL配置時的使用者名稱
	        String user = "root";
	        //MySQL配置時的密碼
	        String LoginPassword = "jackywoo";
	        //遍歷查詢結果集
	        if(check == 0) {
	        	try {
		            //載入驅動程式
		            Class.forName(driver);
		            //1.getConnection()方法，連線MySQL資料庫！！
		            con = DriverManager.getConnection(url,user,LoginPassword);
		            //2.建立statement類物件，用來執行SQL語句！！
		            Statement statement = con.createStatement();
		            String inStudentNumber = inputStudentNumber.getText();
		            String inName = inputName.getText();
		            String inGender = inputGender.getText();
		            String inBirthday = inputBirthday.getText();
		            String inEmail = inputEmail.getText();
		            String inPassword = inputPassword.getText();
		            //要執行的SQL語句
		            String createAccount="insert into `user` values('" + inStudentNumber + "','" + inName + "','" + inGender + "','" + inBirthday + "','" + inEmail + "','" + inAccount + "','" + inPassword + "','0');";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(createAccount);
		            
		        } catch(ClassNotFoundException ex) {   
		            //資料庫驅動類異常處理
		            System.out.println("Sorry,can`t find the Driver!");   
		            ex.printStackTrace();   
		            }catch (SQLIntegrityConstraintViolationException ex) {
		            	JOptionPane.showMessageDialog(null, "此帳號已被使用", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();
		            } catch(SQLException ex) {
		            //資料庫連線失敗異常處理
		            ex.printStackTrace();
		            }catch (Exception ex) {
		            // TODO: handle exception
		            ex.printStackTrace();
		            }
				}
	      }
		
	}

}
