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

public class AddStudent implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JTextField inputClassName;
	JTextField inputStudentNumber;
	private JButton backToTestGroupManage;
	private JButton add;
	
	AddStudent(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("增加學生");		//sets title of frame 
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
		
        JLabel className = new JLabel("班級:");
        className.setBounds(80,32, 54, 28);
        panel.add(className);
        
        inputClassName = new JTextField();
        inputClassName.setBounds(139,33, 161, 25);
        panel.add(inputClassName);
        
        JLabel studentNumber = new JLabel("學號:");
        studentNumber.setBounds(80,62, 54, 28);
        panel.add(studentNumber);
        
        inputStudentNumber = new JTextField();
        inputStudentNumber.setBounds(139,63, 161, 25);
        panel.add(inputStudentNumber);
        
        backToTestGroupManage = new JButton("取消");
        backToTestGroupManage.setBounds(80,118, 80, 23);
        panel.add(backToTestGroupManage);
        backToTestGroupManage.addActionListener(this);
        
        add = new JButton("新增");
        add.setBounds(185,118, 80, 23);
        frame.getContentPane().add(add);
        add.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToTestGroupManage) {
			frame.dispose();
		}
		if(e.getSource() == add) {
			String inClassName = inputClassName.getText();
        	int check = 0;
			if(inClassName.equals("") || inClassName.equals(" ") || inClassName.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "班級欄位為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inStudentNumber = inputStudentNumber.getText();
			if(inStudentNumber.equals("") || inStudentNumber.equals(" ") || inStudentNumber.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "請先取得學號資訊", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			if(inStudentNumber.length() > 9) {
				check = 1;
				JOptionPane.showMessageDialog(null, "學號格式錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
		            String rsAccount = "";
		            
		            String sqlInputAccount="select `account` from userdata where studentnumber='"+ inStudentNumber+ "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsaccount= statement.executeQuery(sqlInputAccount);
		            if(rsaccount.next()) {
		            	 rsAccount = rsaccount.getString(1);
		            }
		            
		            String sqlInputClassName="select `classname` from user where account='"+ rsAccount+ "'";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsclassname= statement.executeQuery(sqlInputClassName);
		            if(rsclassname.next()) {
		            	 String rsClassName = rsclassname.getString(1);
		            	 if(rsClassName.equals(inClassName)) {
		            		 delete = 1;
		            	 }
		            	 else
		            		 JOptionPane.showMessageDialog(null, "學號與班級不符", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
		            }
		            
		            if(delete == 1) {
		            	String sqlAdd="insert into `"+ TestGroupManage.Subject +"` values('" + inClassName + "','" + inStudentNumber +"','無','無');";
			            statement.executeUpdate(sqlAdd);
			            JOptionPane.showMessageDialog(null, "新增成功", "成功", JOptionPane.INFORMATION_MESSAGE);
			            inputStudentNumber.setText("");
			            inputClassName.setText("");
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
