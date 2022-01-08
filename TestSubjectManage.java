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

public class TestSubjectManage implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JTextField inputSubject;
	JButton create;
	JButton delete;
	JButton backToSystemManager;
	SystemManager systemmanager;
	
	TestSubjectManage(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("測驗科目管理");		//sets title of frame 
		frame.setBounds(100, 50, 360, 350);  //設定窗體座標以及打下
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
		
		JLabel createSubject = new JLabel("輸入要建立的科目:");
		createSubject.setBounds(100,39, 142, 28);
        panel.add(createSubject);
        
        inputSubject = new JTextField();
        inputSubject.setBounds(81,117, 161, 25);
        panel.add(inputSubject);
        
        backToSystemManager = new JButton("上一頁");
		backToSystemManager.setBounds(18,198, 80, 23);
        panel.add(backToSystemManager);
        backToSystemManager.addActionListener(this);
        
        create = new JButton("建立");
        create.setBounds(126,198, 80, 23);
        panel.add(create);
        create.addActionListener(this);
        
        delete = new JButton("刪除");
        delete.setBounds(237,198, 80, 23);
        panel.add(delete);
        delete.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToSystemManager) {
			frame.dispose();
			systemmanager = new SystemManager();
		}
		if(e.getSource() == create) {
			String subject = inputSubject.getText();
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
	        	try {
		            //載入驅動程式
		            Class.forName(driver);
		            //1.getConnection()方法，連線MySQL資料庫！！
		            con = DriverManager.getConnection(url,user,LoginPassword);
		            //2.建立statement類物件，用來執行SQL語句！！
		            Statement statement = con.createStatement();
		            //要執行的SQL語句
		            String createGroup="create table `" + subject + "群組`(`classname` varchar(8),\n" + 
		            		"    `studentnumber` varchar(10),\n" + 
		            		"    `group` varchar(7),\n" + 
		            		"    `grade` varchar(3),\n" + 
		            		"    foreign key (`studentnumber`) references `userdata`(`studentnumber`) on delete cascade\n" + 
		            		");";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(createGroup);
		            
		            String createPaper = "create table `" + subject +"試卷`(\n" + 
		            		"	`group` varchar(7) primary key,\n" + 
		            		"    `time` varchar(8),\n" + 
		            		"    `examnumber` varchar(20) ,\n" + 
		            		"    `examlevel` varchar(7),\n" + 
		            		"    `finishednumber` varchar(7)\n" + 
		            		");";
		            statement.executeUpdate(createPaper);
		            
		            String createSubject = "create table `"+ subject +"`(\n" + 
		            		"	`question` varchar(50) primary key,\n" + 
		            		"    `answer` varchar(30),\n" + 
		            		"    `level` varchar(7),\n" + 
		            		"    `group` varchar(10)\n" + 
		            		");";
		            statement.executeUpdate(createSubject);
		            
		            JOptionPane.showMessageDialog(null, "建立成功", "成功", JOptionPane.INFORMATION_MESSAGE);
		            inputSubject.setText("");
		        } catch(ClassNotFoundException ex) {   
		            //資料庫驅動類異常處理
		            System.out.println("Sorry,can`t find the Driver!");   
		            ex.printStackTrace();   
		            }catch (SQLIntegrityConstraintViolationException ex) {
		            	ex.printStackTrace();
		            } catch(SQLException ex) {
		            //資料庫連線失敗異常處理
		            ex.printStackTrace();
		            }catch (Exception ex) {
		            // TODO: handle exception
		            	ex.printStackTrace();
		            }
		}
		if(e.getSource() == delete) {
			String subject = inputSubject.getText();
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
	        	try {
		            //載入驅動程式
		            Class.forName(driver);
		            //1.getConnection()方法，連線MySQL資料庫！！
		            con = DriverManager.getConnection(url,user,LoginPassword);
		            //2.建立statement類物件，用來執行SQL語句！！
		            Statement statement = con.createStatement();
		            //要執行的SQL語句
		            String deleteGroup="drop table `" + subject +"`";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(deleteGroup);
		            
		            String deletePaper = "drop table `" + subject +"試卷`";
		            statement.executeUpdate(deletePaper);
		            
		            String deleteSubject =  "drop table `" + subject +"群組`";
		            statement.executeUpdate(deleteSubject);
		            
		            JOptionPane.showMessageDialog(null, "刪除成功", "成功", JOptionPane.INFORMATION_MESSAGE);
		            inputSubject.setText("");
		        } catch(ClassNotFoundException ex) {   
		            //資料庫驅動類異常處理
		            System.out.println("Sorry,can`t find the Driver!");   
		            ex.printStackTrace();   
		            }catch (SQLIntegrityConstraintViolationException ex) {
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
