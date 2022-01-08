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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChangeExam implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JTextField inputExam;
	JTextField inputAnswer;
	JTextField inputLevel;
	JComboBox subject;
	JButton changeExam;
	JButton getExam;
	JButton deleteExam;
	JButton backToExamManage;
	ExamManage exammanage;
	String inExam;
	
	ChangeExam(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("題目管理");		//sets title of frame 
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
		
		JLabel exam = new JLabel("題目:");
		exam.setBounds(80,36, 54, 28);
        panel.add(exam);
        
        inputExam = new JTextField();
        inputExam.setBounds(139,37, 161, 25);
        panel.add(inputExam);
        
        JLabel answer = new JLabel("答案:");
        answer.setBounds(80,76, 54, 28);
        panel.add(answer);
        
        inputAnswer = new JTextField();
        inputAnswer.setBounds(139,77, 161, 25);
        panel.add(inputAnswer);
        
        JLabel level = new JLabel("難度:");
        level.setBounds(80,116, 54, 28);
        panel.add(level);
        
        inputLevel = new JTextField();
        inputLevel.setBounds(139,118, 161, 25);
        panel.add(inputLevel);
        
        backToExamManage = new JButton("上一頁");
        backToExamManage.setBounds(80,167, 80, 23);
        panel.add(backToExamManage);
        backToExamManage.addActionListener(this);
        
        getExam = new JButton("取得題目");
        getExam.setBounds(185,167, 80, 23);
        frame.getContentPane().add(getExam);
        getExam.addActionListener(this);
        
        changeExam = new JButton("修改題目");
        changeExam.setBounds(80,230, 80, 23);
        panel.add(changeExam);
        changeExam.addActionListener(this);
        
        deleteExam = new JButton("刪除題目");
        deleteExam.setBounds(185,230, 80, 23);
        frame.getContentPane().add(deleteExam);
        deleteExam.addActionListener(this);
        
        
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToExamManage) {
			frame.dispose();
			exammanage = new ExamManage();
		}
		if(e.getSource() == changeExam) {
			inExam = inputExam.getText();
			int check = 0;
			if(inExam.equals("") || inExam.equals(" ") || inExam.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "題目不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inAnswer = inputAnswer.getText();
			if(inAnswer.equals("") || inAnswer.equals(" ") || inAnswer.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "答案不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inLevel = inputLevel.getText();
			if(inLevel.equals("") || inLevel.equals(" ") || inLevel.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "難度不可以為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
		            
		            //要執行的SQL語句
		            String sqlInputAnswer="update `" + ExamManage.Subject + "` set `answer`='"+ inAnswer + "' where `question` = '"+ inExam +"';";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputAnswer);
		            
		            String sqlInputLevel="update `" + ExamManage.Subject + "` set `level`='"+ inLevel + "' where `question` = '"+ inExam +"';";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(sqlInputLevel);
		            
		            String sql1="SET SQL_SAFE_UPDATES = 1;";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.execute(sql1);
		            
		            JOptionPane.showMessageDialog(null, "修改成功", "成功", JOptionPane.INFORMATION_MESSAGE); 
		 
		        } catch(ClassNotFoundException ex) {   
		            //資料庫驅動類異常處理
		            System.out.println("Sorry,can`t find the Driver!");   
		            ex.printStackTrace();   
		            } catch(SQLException ex) {
		            //資料庫連線失敗異常處理
		            	JOptionPane.showMessageDialog(null, "查無此題目", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();
		            }catch (Exception ex) {
		            // TODO: handle exception
		            	JOptionPane.showMessageDialog(null, "輸入不符合格式", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();
		            }
				}
	    }
		if(e.getSource() == getExam) {
			inExam = inputExam.getText();
			int check = 0;
			if(inExam.equals("") || inExam.equals(" ") || inExam.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "題目欄位為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
		            
		            String sqlInputAnswer="select `answer` from `"+ ExamManage.Subject +"` where question='"+ inExam+ "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsaccount= statement.executeQuery(sqlInputAnswer);
		            if(rsaccount.next()) {
		            	 String rsAccount = rsaccount.getString(1);
		            	 inputAnswer.setText(rsAccount);
		            }
		            
		            String sqlInputLevel="select `level` from `"+ ExamManage.Subject +"` where question='"+ inExam+ "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsname= statement.executeQuery(sqlInputLevel);
		            if(rsname.next()) {
		            	 String rsName = rsname.getString(1);
		                 inputLevel.setText(rsName);
		            }
		 
		        } catch(ClassNotFoundException ex) {   
		            //資料庫驅動類異常處理
		            System.out.println("Sorry,can`t find the Driver!");   
		            ex.printStackTrace();   
		            } catch(SQLException ex) {
		            //資料庫連線失敗異常處理
		            	JOptionPane.showMessageDialog(null, "查無此題目", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();  
		            }catch (Exception ex) {
		            // TODO: handle exception
		            	JOptionPane.showMessageDialog(null, "輸入格式有誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();
		        }
	        }
	        
		}
		 if(e.getSource() == deleteExam) {
	        	inExam = inputExam.getText();
	        	int check = 0;
				if(inExam.equals("") || inExam.equals(" ") || inExam.equals(null)) {
					check = 1;
					JOptionPane.showMessageDialog(null, "題目欄位為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
			            
		            	String sqlDelete="delete from `"+ ExamManage.Subject +"` where question='" + inExam + "';";
			            statement.executeUpdate(sqlDelete);
			            JOptionPane.showMessageDialog(null, "刪除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
			            inputExam.setText(" ");
			            inputAnswer.setText(" ");
			            inputLevel.setText(" ");
			            
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
			            	JOptionPane.showMessageDialog(null, "查無此題目", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
			            	ex.printStackTrace();
			            }catch (Exception ex) {
			            // TODO: handle exception
			            ex.printStackTrace();
			            }
		        }
		 }
	}

}
