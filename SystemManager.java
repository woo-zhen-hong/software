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
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SystemManager implements ActionListener{
	JButton accountManage;
	JButton createUserAccount;
	JButton testSubjectManage;
	private JFrame frame;
	CreateUserAccount createuseraccount;
	AccountManage accountmanage;
	TestSubjectManage testsubjectmanage;
	JButton testGroupManage;
	TestGroupManage testgroupmanage;
	
	SystemManager(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("系統管理者系統");		//sets title of frame 
		frame.setSize(300,300);	//sets size of frame
        frame.setLayout(new GridBagLayout());	//sets frame type
        frame.getContentPane().setBackground(new Color(0x009100));	//change color of background
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
        
        createUserAccount = new JButton("建立使用者帳號");
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 2;
        c3.gridy = 0;
        c3.gridwidth = 3;
        c3.gridheight = 1;
        c3.weightx = 0;
        c3.weighty = 0;
        c3.fill = GridBagConstraints.BOTH;
        c3.anchor = GridBagConstraints.CENTER;
        frame.add(createUserAccount, c3);
        createUserAccount.addActionListener(this);
        
        accountManage = new JButton("帳號管理");
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 2;
        c1.gridy = 1;
        c1.gridwidth = 3;
        c1.gridheight = 1;
        c1.weightx = 0;
        c1.weighty = 0;
        c1.fill = GridBagConstraints.BOTH;
        c1.anchor = GridBagConstraints.CENTER;
        frame.add(accountManage, c1);
        accountManage.addActionListener(this);
        
        testGroupManage = new JButton("測驗群組管理");
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 2;
        c4.gridy = 2;
        c4.gridwidth = 3;
        c4.gridheight = 1;
        c4.weightx = 0;
        c4.weighty = 0;
        c4.fill = GridBagConstraints.BOTH;
        c4.anchor = GridBagConstraints.CENTER;
        frame.add(testGroupManage, c4);
        testGroupManage.addActionListener(this);
        
        testSubjectManage = new JButton("測驗科目管理");
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 2;
        c2.gridy = 3;
        c2.gridwidth = 3;
        c2.gridheight = 1;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.CENTER;
        frame.add(testSubjectManage, c2);
        testSubjectManage.addActionListener(this);
        
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createUserAccount) {
			frame.dispose();
			createuseraccount = new CreateUserAccount();
		}
		if(e.getSource() == accountManage) {
			frame.dispose();
			JOptionPane.showMessageDialog(null, "請使用學號來進行動作", "小提醒", JOptionPane.INFORMATION_MESSAGE); 
			accountmanage = new AccountManage();
		}
		if(e.getSource() == testGroupManage) {
			frame.dispose();
			testgroupmanage = new TestGroupManage();
		}
		if(e.getSource() == testSubjectManage) {
			frame.dispose();
			testsubjectmanage = new TestSubjectManage();
		}
	}
}
