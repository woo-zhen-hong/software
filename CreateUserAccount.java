package software;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
	JTextField inputClassName;
	JTextField inputAccount;
	JTextField inputPassword;
	private JButton create;
	private JButton backToSystemManager;
	User user;
	private JTextField inputStudentNumber;
	SystemManager systemaager;

	CreateUserAccount(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("建立使用者帳號");		//sets title of frame 
		frame.setBounds(100, 50, 360, 350);  //設定窗體座標以及打下
		frame.setResizable(false);  //設定窗體大小不可以改變
		frame.setVisible(true);    //設定窗體可見
		panel = new JPanel();
		frame.setContentPane(panel);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.setLayout(null);	 
        
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	//宣告Connection物件
                Connection con;
                //驅動程式名
                String driver = "com.mysql.cj.jdbc.Driver";
                //URL指向要訪問的資料庫名
                String url = "jdbc:mysql://localhost:3306/software";
                //MySQL配置時的使用者名稱
                String user = "root";
                //MySQL配置時的密碼
                String sqlpassword = "jackywoo";
                //遍歷查詢結果集
                try {
                    //載入驅動程式
                    Class.forName(driver);
                    //1.getConnection()方法，連線MySQL資料庫！！
                    con = DriverManager.getConnection(url,user,sqlpassword);
                    //2.建立statement類物件，用來執行SQL語句！！
                    Statement statement = con.createStatement();
                    //要執行的SQL語句
                    String SqlUpadateSignIn="update `user` set `signin`='0' where `account`='" + SignIn.userAccount +"';";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(SqlUpadateSignIn);
                } catch(ClassNotFoundException ex) {   
                    //資料庫驅動類異常處理
                    System.out.println("Sorry,can`t find the Driver!");   
                    ex.printStackTrace();   
                    } catch(SQLException ex) {
                    //資料庫連線失敗異常處理
                    ex.printStackTrace();  
                    }catch (Exception ex) {
                    // TODO: handle exception
                    ex.printStackTrace();
                }
                frame.dispose();
            }
        });
		
		JLabel name = new JLabel("姓名:");
        name.setBounds(80,32, 54, 28);
        panel.add(name);
        
        inputName = new JTextField();
        inputName.setBounds(139,33, 161, 25);
        panel.add(inputName);
        
        JLabel gender = new JLabel("性別:");
        gender.setBounds(80,62, 54, 28);
        panel.add(gender);
        
        inputGender = new JTextField();
        inputGender.setBounds(139,63, 161, 25);
        panel.add(inputGender);
        
        JLabel birthday = new JLabel("生日:");
        birthday.setBounds(80,92, 54, 28);
        panel.add(birthday);
        
        inputBirthday = new JTextField();
        inputBirthday.setBounds(139,93, 161, 25);
        panel.add(inputBirthday);
        
        JLabel email = new JLabel("email:");
        email.setBounds(80,122, 54, 28);
        panel.add(email);
        
        inputEmail = new JTextField();
        inputEmail.setBounds(139,123, 161, 25);
        panel.add(inputEmail);
        
        JLabel className = new JLabel("班級:");
        className.setBounds(80,152, 54, 28);
        panel.add(className);
        
        inputClassName = new JTextField();
        inputClassName.setBounds(139,153, 161, 25);
        panel.add(inputClassName);
        
        JLabel account = new JLabel("帳號:");
        account.setBounds(80,182, 54, 28);
        panel.add(account);
        
        inputAccount = new JTextField();
        inputAccount.setBounds(139,183, 161, 25);
        panel.add(inputAccount);
        
        JLabel password = new JLabel("密碼:");
        password.setBounds(80,212, 54, 28);
        panel.add(password);
        
        inputPassword = new JTextField();
        inputPassword.setBounds(139,213, 161, 25);
        panel.add(inputPassword);
        
        backToSystemManager = new JButton("上一頁");
        backToSystemManager.setBounds(80,270, 80, 23);
        panel.add(backToSystemManager);
        backToSystemManager.addActionListener(this);
        
        create = new JButton("建立");
        create.setBounds(200,270, 80, 23);
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
		if(e.getSource() == backToSystemManager) {
			frame.dispose();
			systemaager = new SystemManager();
		}
		if(e.getSource() == create) {
			String inAccount = inputAccount.getText();
			int check = 0;
			if(inAccount.equals("") || inAccount.equals(null) || inAccount.equals(" ")) {
				check = 1;
				JOptionPane.showMessageDialog(null, "帳號不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			if(inAccount.charAt(0) != 'a' && inAccount.charAt(0) != 'b' && inAccount.charAt(0) != 'c') {
				check = 1;
				JOptionPane.showMessageDialog(null, "帳號格式錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inStudentNumber = inputStudentNumber.getText();
			if(inStudentNumber.equals("") || inStudentNumber.equals(" ") || inStudentNumber.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "學號不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			if(inStudentNumber.length() != 9 && check == 0) {
				check = 1;
				JOptionPane.showMessageDialog(null, "學號格式錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inName = inputName.getText();
			if(inName.equals("") || inName.equals(" ") || inName.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "名字不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inGender = inputGender.getText();
			if(inGender.equals("") || inGender.equals(" ") || inGender.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "性別不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inBirthday = inputBirthday.getText();
			if(inBirthday.equals("") || inBirthday.equals(" ") || inBirthday.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "生日不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inEmail = inputEmail.getText();
			if(inEmail.equals("") || inEmail.equals(" ") || inEmail.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "email不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inClassName = inputClassName.getText();
			if(inClassName.equals("") || inClassName.equals(" ") || inClassName.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "班級不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inPassword = inputPassword.getText();
			if(inPassword.equals("") || inPassword.equals(" ") || inPassword.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "密碼不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
		            //要執行的SQL語句
		            String createAccount="insert into `user` values('" + inName + "','" + inGender + "','" + inBirthday + "','" + inEmail + "','" + inClassName + "','" + inAccount + "','" + inPassword + "','0');";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(createAccount);
		            
		            String createStudentNumber = "insert into `userdata` values('" + inStudentNumber + "','" + inAccount + "');";
		            statement.executeUpdate(createStudentNumber);
		            JOptionPane.showMessageDialog(null, "帳號建立成功", "成功", JOptionPane.INFORMATION_MESSAGE); 
		            inputStudentNumber.setText("");
		            inputName.setText("");
		            inputGender.setText("");
		            inputBirthday.setText("");
		            inputEmail.setText("");
		            inputClassName.setText("");
		            inputAccount.setText("");
		            inputPassword.setText("");
		            
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
		            	JOptionPane.showMessageDialog(null, "輸入不符合格式", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();
		            }
				}
	      }
		
	}

}
