package software;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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

public class UserAccount  implements ActionListener{
	private JFrame frame;
	private JPanel panel;
	JTextField inputName;
	JTextField inputGender;
	JTextField inputBirthday;
	JTextField inputEmail;
	JTextField inputClassName;
	JTextField inputAccount;
	JTextField inputPassword;
	private JButton backToUser;
	private JButton change;
	User user;
	private JTextField inputStudentNumber;

	UserAccount(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("個人帳號");		//sets title of frame 
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
        
        JLabel className = new JLabel("系級:");
        className.setBounds(80,152, 54, 28);
        panel.add(className);
        
        inputClassName = new JTextField();
        inputClassName.setBounds(139,153, 161, 25);
        inputClassName.setEditable(false);
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
        
        backToUser = new JButton("上一頁");
        backToUser.setBounds(90,270, 80, 23);
        panel.add(backToUser);
        backToUser.addActionListener(this);
        
        change = new JButton("改變");
        change.setBounds(200,270, 80, 23);
        frame.getContentPane().add(change);
        change.addActionListener(this);
        
        JLabel studentNumber = new JLabel("學號:");
        studentNumber.setBounds(80, 8, 61, 16);
        panel.add(studentNumber);
        
        inputStudentNumber = new JTextField();
        inputStudentNumber.setBounds(139, 3, 161, 26);
        panel.add(inputStudentNumber);
        inputStudentNumber.setColumns(10);
        inputStudentNumber.setEditable(false);

      //宣告Connection物件
        Connection con;
        //驅動程式名
        String driver = "com.mysql.cj.jdbc.Driver";
        //URL指向要訪問的資料庫名
        String url = "jdbc:mysql://localhost:3306/software";
        //MySQL配置時的使用者名稱
        String user = "root";
        //MySQL配置時的密碼
        String LoginPassword = "jackywoo";
        //遍歷查詢結果集
        try {
            //載入驅動程式
            Class.forName(driver);
            //1.getConnection()方法，連線MySQL資料庫！！
            con = DriverManager.getConnection(url,user,LoginPassword);
            //2.建立statement類物件，用來執行SQL語句！！
            Statement statement = con.createStatement();
            //要執行的SQL語句
            
            String sqlInputStudentNumber="select `studentnumber` from userdata where account='"+ SignIn.userAccount+ "'";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsstudentnumber= statement.executeQuery(sqlInputStudentNumber);
            if(rsstudentnumber.next()) {
            	 String rsStudentnumber = rsstudentnumber.getString(1);
            	 inputStudentNumber.setText(rsStudentnumber);
            }
            
            String sqlInputName="select `name` from user where account='"+ SignIn.userAccount+ "'";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsname= statement.executeQuery(sqlInputName);
            if(rsname.next()) {
            	 String rsName = rsname.getString(1);
                 inputName.setText(rsName);
            }
            
            String sqlInputGender="select `gender` from user where account='"+ SignIn.userAccount+ "'";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsgender= statement.executeQuery(sqlInputGender);
            if(rsgender.next()) {
            	 String rsGender = rsgender.getString(1);
            	 inputGender.setText(rsGender);
            }
            
            String sqlInputBirthday="select `birthday` from user where account='"+ SignIn.userAccount+ "'";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsbirthday= statement.executeQuery(sqlInputBirthday);
            if(rsbirthday.next()) {
            	 String rsBirthday = rsbirthday.getString(1);
            	 inputBirthday.setText(rsBirthday);
            }
            
            String sqlInputEmail="select `email` from user where account='"+ SignIn.userAccount+ "'";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsemail= statement.executeQuery(sqlInputEmail);
            if(rsemail.next()) {
            	 String rsEmail = rsemail.getString(1);
            	 inputEmail.setText(rsEmail);
            }
            
            String sqlInputClassName="select `classname` from user where account='"+ SignIn.userAccount+ "'";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsclassname= statement.executeQuery(sqlInputClassName);
            if(rsclassname.next()) {
            	 String rsClassname = rsclassname.getString(1);
            	 inputClassName.setText(rsClassname);
            }
            
            String sqlInputAccount="select `account` from user where account='"+ SignIn.userAccount+ "'";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsaccount= statement.executeQuery(sqlInputAccount);
            if(rsaccount.next()) {
            	 String rsAccount = rsaccount.getString(1);
            	 inputAccount.setText(rsAccount);
            }
            
            String sqlInputPassword="select `password` from user where account='"+ SignIn.userAccount+ "'";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rspassword= statement.executeQuery(sqlInputPassword);
            if(rspassword.next()) {
            	 String rsPassword = rspassword.getString(1);
            	 inputPassword.setText(rsPassword);
            }
 
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
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToUser) {
			frame.dispose();
			user = new User();
		}
		if(e.getSource() == change) {
			String inAccount = inputAccount.getText();
			int check = 0;
			if(inAccount.charAt(0) != 'a') {
				check = 1;
				JOptionPane.showMessageDialog(null, "帳號開頭必須是a", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			if(inAccount.equals("") || inAccount.equals(null) || inAccount.equals(" ")) {
				check = 1;
				JOptionPane.showMessageDialog(null, "帳號不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
		            
		            String sqlInputAccount="update `user` set `account`='"+ inAccount+ "'" + "where account='" + SignIn.userAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputAccount);
		            
		            //要執行的SQL語句
		            String sqlInputName="update `user` set `name`='"+ inName+ "'" + "where account='" + SignIn.userAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputName);
		            
		            String sqlInputGender="update `user` set `gender`='"+ inGender+ "'" + "where account='" + SignIn.userAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputGender);
		            
		            String sqlInputBirthday="update `user` set `birthday`='"+ inBirthday+ "'" + "where account='" + SignIn.userAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputBirthday);
		            
		            String sqlInputEmail="update `user` set `email`='"+ inEmail+ "'" + "where account='" + SignIn.userAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputEmail);
		            
		            String sqlInputPassword="update `user` set `password`='"+ inPassword+ "'" + "where account='" + SignIn.userAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputPassword);
		            
		            String inStudentNumber = inputStudentNumber.getText();
		            String sqlStudentNumber="update `userdata` set `account`='"+ inAccount+ "'" + "where studentnumber='" + inStudentNumber +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlStudentNumber);
		            
		            JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.INFORMATION_MESSAGE); 
		 
		        } catch(ClassNotFoundException ex) {   
		            //資料庫驅動類異常處理
		            System.out.println("Sorry,can`t find the Driver!");   
		            ex.printStackTrace();   
		            }catch (SQLIntegrityConstraintViolationException ex) {
		            	JOptionPane.showMessageDialog(null, "此帳號密碼已被使用", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
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

