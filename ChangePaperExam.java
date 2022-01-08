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
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ChangePaperExam implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JComboBox subject;
	JButton backToExamPaperManage;
	JButton createPaperExam;
	JButton removePaperExam;
	JTextArea exam;
	String[] x;
	ExamPaperManage exampapermanage;
	CreatePaperExam createpaperexam;
	RemovePaperExam removepaperexam;
	public static String Subject;
	
	ChangePaperExam(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("修改試卷中的題目");		//sets title of frame 
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
        x = new String[ExamPaperManage.paperNumber];
        int y=0;
        //遍歷查詢結果集
        try {
            //載入驅動程式
            Class.forName(driver);
            //1.getConnection()方法，連線MySQL資料庫！！
            con = DriverManager.getConnection(url,user,sqlpassword);
            //2.建立statement類物件，用來執行SQL語句！！
            Statement statement = con.createStatement();
            //要執行的SQL語句
            String SqlSelectGroup="select `group` from `" + ExamPaperManage.Subject +"`;";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsSubject = statement.executeQuery(SqlSelectGroup);
            
            
            while(rsSubject.next()) {
            	 String a =rsSubject.getString("group");
            	 x[y] = a;
            	 y++;
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
        
		subject = new JComboBox(x);
		subject.setBounds(29,20, 113, 23);
		panel.add(subject);
		subject.addActionListener(this);
		
		backToExamPaperManage = new JButton("完成");
		backToExamPaperManage.setBounds(299,20, 80, 23);
        panel.add(backToExamPaperManage);
        backToExamPaperManage.addActionListener(this);
        
        createPaperExam = new JButton("增加題目");
        createPaperExam.setBounds(142,20, 80, 23);
        panel.add(createPaperExam);
        createPaperExam.addActionListener(this);
        
        removePaperExam = new JButton("移除題目");
        removePaperExam.setBounds(218,20, 80, 23);
        panel.add(removePaperExam);
        removePaperExam.addActionListener(this);
        
        exam = new JTextArea();
        exam.setBounds(6,50, 388, 316);
        exam.setLineWrap(true); //設定自動換行
        frame.getContentPane().add(exam);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToExamPaperManage) {
			frame.dispose();
			exampapermanage = new ExamPaperManage();
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
		            String selectSubject="select `question`,`answer`,`level`,`group` from `" + ExamPaperManage.Subject.charAt(0) + ExamPaperManage.Subject.charAt(1) + "`;";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsSubject = statement.executeQuery(selectSubject);
		            String x = "";
		            
		            while(rsSubject.next()) {
		            	 String a ="題目："  + rsSubject.getString("question")
		            	 		 + "  答案："  + rsSubject.getString("answer") 
		            	 	   	 + "  難度："  + rsSubject.getString("level")
		            	 	   	 + "  考試名稱："  + rsSubject.getString("group")+ "\n";
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
		if(e.getSource() == createPaperExam) {
			createpaperexam = new CreatePaperExam();
		}
		if(e.getSource() == removePaperExam) {
			removepaperexam = new RemovePaperExam();
		}
	}

}
