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
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TestGroupManage implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JComboBox subject;
	JButton backToSystemManager;
	JButton addStudent;
	JButton removeStudent;
	JTextArea exam;
	TestManager testmanager;
	CreateExam createexam;
	ChangeExam changeexam;
	SystemManager systemmanager;
	AddStudent addstudent;
	RemoveStudent removestudent;
	public static String Subject;
	public static String[] subjectGroup = {"數學群組","英文群組"};
	
	TestGroupManage(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("測驗群組管理");		//sets title of frame 
		frame.setBounds(100, 50, 360, 350);  //設定窗體座標以及打下
		frame.setSize(400,400);
		frame.setVisible(true);    //設定窗體可見
		
		panel = new JPanel();
		frame.setContentPane(panel);
		panel.setBounds(10,10,370,50);
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
        
		subject = new JComboBox(subjectGroup);
		subject.setBounds(6,20, 117, 23);
		panel.add(subject);
		subject.addActionListener(this);
		
		backToSystemManager = new JButton("上一頁");
		backToSystemManager.setBounds(135,20, 80, 23);
        panel.add(backToSystemManager);
        backToSystemManager.addActionListener(this);
        
        addStudent = new JButton("增加學生");
        addStudent.setBounds(214,20, 80, 23);
        panel.add(addStudent);
        addStudent.addActionListener(this);
        
        removeStudent = new JButton("移除學生");
        removeStudent.setBounds(294,20, 80, 23);
        panel.add(removeStudent);
        removeStudent.addActionListener(this);
        
        exam = new JTextArea();
        exam.setBounds(6,50, 388, 316);
        exam.setLineWrap(true); //設定自動換行
        exam.setEditable(false);
        frame.getContentPane().add(exam);
        
      
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToSystemManager) {
			frame.dispose();
			systemmanager = new SystemManager();
		}
		if(e.getSource() == subject) {
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
	        Subject = (String) subject.getSelectedItem();
	        //遍歷查詢結果集
	        	try {
		            //載入驅動程式
		            Class.forName(driver);
		            //1.getConnection()方法，連線MySQL資料庫！！
		            con = DriverManager.getConnection(url,user,LoginPassword);
		            //2.建立statement類物件，用來執行SQL語句！！
		            Statement statement = con.createStatement();
		            //要執行的SQL語句
		            String selectSubject="select classname,studentnumber from `" + Subject + "`;";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsSubject = statement.executeQuery(selectSubject);
		            String x = "";
		            
		            while(rsSubject.next()) {
		            	 String a ="系級：" +rsSubject.getString("classname") + "  學號：" 
		            		   + rsSubject.getString("studentnumber") + "\n";
		            	 x += a;
		            }
		            exam.setText(x);
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
		if(e.getSource() == addStudent) {
			addstudent = new AddStudent();
		}
		if(e.getSource() == removeStudent) {
			removestudent = new RemoveStudent();
		}
	}
}
