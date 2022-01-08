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

public class AccountManage implements ActionListener{
	private JFrame frame;
	private JPanel panel;
	JTextField inputName;
	JTextField inputGender;
	JTextField inputBirthday;
	JTextField inputEmail;
	JTextField inputClassName;
	JTextField inputAccount;
	JTextField inputPassword;
	private JButton backToSystemManager;
	private JButton change;
	private JButton getData;
	private JButton deleteData;
	SystemManager systemaager;
	private JTextField inputStudentNumber;
	String inStudentNumber;
	
	AccountManage(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("帳號管理");		//sets title of frame 
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
        backToSystemManager.setBounds(10,270, 80, 23);
        panel.add(backToSystemManager);
        backToSystemManager.addActionListener(this);
        
        getData = new JButton("取得帳號資料");
        getData.setBounds(80,270, 110, 23);
        frame.getContentPane().add(getData);
        getData.addActionListener(this);
        
        change = new JButton("改變帳號資料");
        change.setBounds(180,270, 110, 23);
        frame.getContentPane().add(change);
        change.addActionListener(this);
        
        deleteData = new JButton("刪除帳號");
        deleteData.setBounds(280,270, 85, 23);
        frame.getContentPane().add(deleteData);
        deleteData.addActionListener(this);
        
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
		if(e.getSource() == change) {
			inStudentNumber = inputStudentNumber.getText();
			int check = 0;
			if(inStudentNumber.equals("") || inStudentNumber.equals(" ") || inStudentNumber.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "學號欄位為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			if(inStudentNumber.length() != 9 && check == 0) {
				check = 1;
				JOptionPane.showMessageDialog(null, "學號格式錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inName = inputName.getText();
			if(inName.equals("") || inName.equals(" ") || inName.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "名字不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inGender = inputGender.getText();
			if(inGender.equals("") || inGender.equals(" ") || inGender.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "性別不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inBirthday = inputBirthday.getText();
			if(inBirthday.equals("") || inBirthday.equals(" ") || inBirthday.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "生日不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inEmail = inputEmail.getText();
			if(inEmail.equals("") || inEmail.equals(" ") || inEmail.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "email不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inClassName = inputClassName.getText();
			if(inClassName.equals("") || inClassName.equals(" ") || inClassName.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "班級不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inAccount = inputAccount.getText();
			if(inAccount.equals("") || inAccount.equals(" ") || inAccount.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "帳號不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			if(inAccount.charAt(0) != 'a' && inAccount.charAt(0) != 'b' && inAccount.charAt(0) != 'c') {
				check = 1;
				JOptionPane.showMessageDialog(null, "帳號格式錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inPassword = inputPassword.getText();
			if(inPassword.equals("") || inPassword.equals(" ") || inPassword.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "密碼不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
		            
		            String sql0="SET SQL_SAFE_UPDATES = 0;";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.execute(sql0);
		            
		            String sqlInputAccount="update `userdata` set `account`='"+ inAccount+ "'" + "where studentnumber='" + inStudentNumber +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputAccount); 
		            
		            //要執行的SQL語句
		            String sqlInputName="update `user` set `name`='"+ inName+ "'" + "where account='" + inAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputName);
		            
		            String sqlInputGender="update `user` set `gender`='"+ inGender+ "'" + "where account='" + inAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputGender);
		            
		            String sqlInputBirthday="update `user` set `birthday`='"+ inBirthday+ "'" + "where account='" + inAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputBirthday);
		            
		            String sqlInputEmail="update `user` set `email`='"+ inEmail+ "'" + "where account='" + inAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputEmail);
		            
		            String sqlInputClassName="update `user` set `classname`='"+ inClassName+ "'" + "where account='" + inAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputClassName);
		            
		            String sqlInputPassword="update `user` set `password`='"+ inPassword+ "'" + "where account='" + inAccount +"'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputPassword);
		            
		            String sql1="SET SQL_SAFE_UPDATES = 1;";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.execute(sql1);
		            
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
		            	JOptionPane.showMessageDialog(null, "查無此學號", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();
		            }catch (Exception ex) {
		            // TODO: handle exception
		            	JOptionPane.showMessageDialog(null, "輸入不符合格式", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();
		            }
				}
	    }
		if(e.getSource() == getData) {
			inStudentNumber = inputStudentNumber.getText();
			int check = 0;
			if(inStudentNumber.equals("") || inStudentNumber.equals(" ") || inStudentNumber.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "學號欄位為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			if(inStudentNumber.length() != 9 && check == 0) {
				check = 1;
				JOptionPane.showMessageDialog(null, "學號格式錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
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
	        if(check == 0) {
	        	//遍歷查詢結果集
		        try {
		            //載入驅動程式
		            Class.forName(driver);
		            //1.getConnection()方法，連線MySQL資料庫！！
		            con = DriverManager.getConnection(url,user,LoginPassword);
		            //2.建立statement類物件，用來執行SQL語句！！
		            Statement statement = con.createStatement();
		            //要執行的SQL語句
		            
		            String sqlInputAccount="select `account` from userdata where studentnumber='"+ inStudentNumber+ "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsaccount= statement.executeQuery(sqlInputAccount);
		            if(rsaccount.next()) {
		            	 String rsAccount = rsaccount.getString(1);
		            	 inputAccount.setText(rsAccount);
		            }
		            
		            String inAccount = inputAccount.getText();
		            
		            String sqlInputName="select `name` from user where account='"+ inAccount + "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsname= statement.executeQuery(sqlInputName);
		            if(rsname.next()) {
		            	 String rsName = rsname.getString(1);
		                 inputName.setText(rsName);
		            }
		            
		            String sqlInputGender="select `gender` from user where account='"+ inAccount+ "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsgender= statement.executeQuery(sqlInputGender);
		            if(rsgender.next()) {
		            	 String rsGender = rsgender.getString(1);
		            	 inputGender.setText(rsGender);
		            }
		            
		            String sqlInputBirthday="select `birthday` from user where account='"+ inAccount+ "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsbirthday= statement.executeQuery(sqlInputBirthday);
		            if(rsbirthday.next()) {
		            	 String rsBirthday = rsbirthday.getString(1);
		            	 inputBirthday.setText(rsBirthday);
		            }
		            
		            String sqlInputEmail="select `email` from user where account='"+ inAccount+ "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsemail= statement.executeQuery(sqlInputEmail);
		            if(rsemail.next()) {
		            	 String rsEmail = rsemail.getString(1);
		            	 inputEmail.setText(rsEmail);
		            }
		            
		            String sqlInputClassName="select `classname` from user where account='"+ inAccount+ "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsclassname= statement.executeQuery(sqlInputClassName);
		            if(rsclassname.next()) {
		            	 String rsClassName = rsclassname.getString(1);
		            	 inputClassName.setText(rsClassName);
		            }
		            
		            String sqlInputPassword="select `password` from user where account='"+ inAccount+ "'";
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
		            }catch (SQLIntegrityConstraintViolationException ex) {
		            	JOptionPane.showMessageDialog(null, "查無此學號", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
		            	ex.printStackTrace();
		            } catch(SQLException ex) {
		            //資料庫連線失敗異常處理
		            	JOptionPane.showMessageDialog(null, "查無此學號", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();  
		            }catch (Exception ex) {
		            // TODO: handle exception
		            	JOptionPane.showMessageDialog(null, "輸入格式有誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();
		        }
	        }
	        
		}
	        if(e.getSource() == deleteData) {
	        	inStudentNumber = inputStudentNumber.getText();
	        	int check = 0;
				if(inStudentNumber.equals("") || inStudentNumber.equals(" ") || inStudentNumber.equals(null)) {
					check = 1;
					JOptionPane.showMessageDialog(null, "學號欄位為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
				}
				if(inStudentNumber.length() != 9) {
					check = 1;
					JOptionPane.showMessageDialog(null, "學號格式錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
				}
				
				String inAccount = inputAccount.getText();
				if(inAccount.equals("") || inAccount.equals(" ") || inAccount.equals(null)) {
					check = 1;
					JOptionPane.showMessageDialog(null, "請先取得帳號資訊", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
				}
				if(inAccount.charAt(0) != 'a') {
					check = 1;
					JOptionPane.showMessageDialog(null, "帳號格式錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
			            String sql0="SET SQL_SAFE_UPDATES = 0;";
			            //3.ResultSet類，用來存放獲取的結果集！！
			            statement.execute(sql0);
			            
			            int delete = 0;
			            
			            String sqlInputAccount="select `account` from userdata where studentnumber='"+ inStudentNumber+ "'";
			            //3.ResultSet類，用來存放獲取的結果集！！
			            ResultSet rsaccount= statement.executeQuery(sqlInputAccount);
			            if(rsaccount.next()) {
			            	 String rsAccount = rsaccount.getString(1);
			            	 if(inAccount.equals(rsAccount)) {
			            		 delete = 1;
			            	 }
			            	 else
			            		 JOptionPane.showMessageDialog(null, "學號與帳號不符", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			            }
			            
			            if(delete == 1) {
			            	String sqlDeleteData="delete from `userdata` where account='" + inAccount + "';";
				            statement.executeUpdate(sqlDeleteData);
			            	String sqlDelete="delete from `user` where account='" + inAccount + "';";
				            statement.executeUpdate(sqlDelete);
				            JOptionPane.showMessageDialog(null, "刪除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
				            inputStudentNumber.setText("");
				            inputName.setText("");
				            inputGender.setText("");
				            inputBirthday.setText("");
				            inputEmail.setText("");
				            inputClassName.setText("");
				            inputAccount.setText("");
				            inputPassword.setText("");
			            }
			            
			            String sql1="SET SQL_SAFE_UPDATES = 1;";
			            //3.ResultSet類，用來存放獲取的結果集！！
			            statement.execute(sql1);

			        } catch(ClassNotFoundException ex) {   
			            //資料庫驅動類異常處理
			            System.out.println("Sorry,can`t find the Driver!");   
			            ex.printStackTrace();   
			            }catch (SQLIntegrityConstraintViolationException ex) {
			            	ex.printStackTrace();
			            } catch(SQLException ex) {
			            //資料庫連線失敗異常處理
			            	JOptionPane.showMessageDialog(null, "查無此學號", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
			            	ex.printStackTrace();
			            }catch (Exception ex) {
			            // TODO: handle exception
			            ex.printStackTrace();
			            }
		        }
	       }
	}
}

