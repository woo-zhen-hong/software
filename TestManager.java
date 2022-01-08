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
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestManager implements ActionListener{

	private JFrame frame;
	JButton examManage;
	JButton examPaperManage;
	JButton inquireExam;
	ExamManage exammanage;
	ExamPaperManage exampapermanage;
	Grade grade;
	
	TestManager(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("評量管理者系統");		//sets title of frame 
		frame.setSize(300,300);	//sets size of frame
        frame.setLayout(new GridBagLayout());	//sets frame type
        frame.getContentPane().setBackground(new Color(0x00EC00));	//change color of background
        frame.setVisible(true);
        
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
        
        examManage = new JButton("題庫與題目管理");
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 2;
        c1.gridy = 0;
        c1.gridwidth = 3;
        c1.gridheight = 1;
        c1.weightx = 0;
        c1.weighty = 0;
        c1.fill = GridBagConstraints.BOTH;
        c1.anchor = GridBagConstraints.CENTER;
        frame.add(examManage, c1);
        examManage.addActionListener(this);
        
        examPaperManage = new JButton("測驗試卷管理");
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 2;
        c4.gridy = 1;
        c4.gridwidth = 3;
        c4.gridheight = 1;
        c4.weightx = 0;
        c4.weighty = 0;
        c4.fill = GridBagConstraints.BOTH;
        c4.anchor = GridBagConstraints.CENTER;
        frame.add(examPaperManage, c4);
        examPaperManage.addActionListener(this);
        
        inquireExam = new JButton("測驗成績查詢");
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 2;
        c2.gridy = 2;
        c2.gridwidth = 3;
        c2.gridheight = 1;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.CENTER;
        frame.add(inquireExam, c2);
        inquireExam.addActionListener(this);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == examManage) {
			frame.dispose();
			exammanage = new ExamManage();
		}
		if(e.getSource() == examPaperManage) {
			frame.dispose();
			exampapermanage = new ExamPaperManage();
		}
		if(e.getSource() == inquireExam) {
			frame.dispose();
			grade = new Grade();
		}
	}
}
