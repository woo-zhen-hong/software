package software;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChooseParctice implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JComboBox subject;
	JComboBox paper;
	JButton backToUser;
	JButton confirm;
	String[] x;
	User user;
	Practice practice;
	public static String Subjects;
	public static String type;
	
	ChooseParctice(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("挑選練習試卷");		//sets title of frame 
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
		
		String[] subjecta = ExamManage.subjects;
		subject = new JComboBox(subjecta);
		subject.setBounds(115,54, 153, 23);
		panel.add(subject);
		subject.addActionListener(this);
		Subjects = (String) subject.getSelectedItem();
		
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
        int y=0;
        int num = 0;
        //遍歷查詢結果集
        try {
            //載入驅動程式
            Class.forName(driver);
            //1.getConnection()方法，連線MySQL資料庫！！
            con = DriverManager.getConnection(url,user,sqlpassword);
            //2.建立statement類物件，用來執行SQL語句！！
            Statement statement = con.createStatement();
            //要執行的SQL語句
            String SqlSelectGroupNumber="select `group` from `" + Subjects +"試卷`;";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsSubjectNumber = statement.executeQuery(SqlSelectGroupNumber);
            while(rsSubjectNumber.next()) {
            	 num++;
            }
            x = new String[num];
            String SqlSelectGroup="select `group` from `" + Subjects +"試卷`;";
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
        
		paper = new JComboBox(x);
		paper.setBounds(115,134, 153, 23);
		panel.add(paper);
		paper.addActionListener(this);
		type = (String) paper.getSelectedItem();
		
		backToUser = new JButton("上一頁");
		backToUser.setBounds(75,210, 80, 23);
        panel.add(backToUser);
        backToUser.addActionListener(this);
        
        confirm = new JButton("確定");
        confirm.setBounds(236,210, 80, 23);
        panel.add(confirm);
        confirm.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToUser) {
			frame.dispose();
			user = new User();
		}
		if(e.getSource() == confirm) {
			if(Subjects.equals("") || Subjects.equals(" ") || Subjects.equals("null")) {
				JOptionPane.showMessageDialog(null, "請選擇有效的試卷", "警告", JOptionPane.ERROR_MESSAGE);
			}
			else {
				frame.dispose();
				practice = new Practice();
				practice.mt.start();
			}
		}
	}
	
}
