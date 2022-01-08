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

public class CreatePaper implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JTextField inputGroup;
	JTextField inputTime;
	JTextField inputExamNumber;
	JTextField inputExamLevel;
	JTextField inputFinishedNumber;
	private JButton backToExamPaperManage;
	private JButton add;
	ExamPaperManage exampapermanage;
	
	CreatePaper(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("增加試卷");		//sets title of frame 
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
		
        JLabel group = new JLabel("考試名稱:");
        group.setBounds(80,32, 60, 28);
        panel.add(group);
        
        inputGroup = new JTextField();
        inputGroup.setBounds(139,33, 161, 25);
        panel.add(inputGroup);
        
        JLabel time = new JLabel("時間:");
        time.setBounds(80,62, 60, 28);
        panel.add(time);
        
        inputTime = new JTextField();
        inputTime.setBounds(139,63, 161, 25);
        panel.add(inputTime);
        
        JLabel examNumber = new JLabel("題目數量:");
        examNumber.setBounds(80,92, 60, 28);
        panel.add(examNumber);
        
        inputExamNumber= new JTextField();
        inputExamNumber.setBounds(139,93, 161, 25);
        panel.add(inputExamNumber);
        
        JLabel ExamLevel = new JLabel("題目難易:");
        ExamLevel.setBounds(80,122, 60, 28);
        panel.add(ExamLevel);
        
        inputExamLevel = new JTextField();
        inputExamLevel.setBounds(139,123, 161, 25);
        panel.add(inputExamLevel);
        
        JLabel finishedNumber = new JLabel("完成人數:");
        finishedNumber.setBounds(80,152, 60, 28);
        panel.add(finishedNumber);
        
        inputFinishedNumber = new JTextField();
        inputFinishedNumber.setBounds(139,153, 161, 25);
        panel.add(inputFinishedNumber);
        
        backToExamPaperManage = new JButton("取消");
        backToExamPaperManage.setBounds(80,203, 80, 23);
        panel.add(backToExamPaperManage);
        backToExamPaperManage.addActionListener(this);
        
        add = new JButton("新增");
        add.setBounds(190,203, 80, 23);
        frame.getContentPane().add(add);
        add.addActionListener(this);
        
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToExamPaperManage) {
			frame.dispose();
		}
		if(e.getSource() == add) {
			String inGroup = inputGroup.getText();
        	int check = 0;
			if(inGroup.equals("") || inGroup.equals(" ") || inGroup.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "考試名稱不可為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inTime = inputTime.getText();
			if(inTime.equals("") || inTime.equals(" ") || inTime.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "時間不可為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inExamNumber = inputExamNumber.getText();
			if(inExamNumber.equals("") || inExamNumber.equals(" ") || inExamNumber.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "題目數量不可為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			if(inExamNumber.charAt(0) == '-') {
				check = 1;
				JOptionPane.showMessageDialog(null, "題目數量不可為負", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inExamLevel = inputExamLevel.getText();
			if(inExamLevel.equals("") || inExamLevel.equals(" ") || inExamLevel.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "題目難易不可為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inFinishedNumber = inputFinishedNumber.getText();
			if(inFinishedNumber.equals("") || inFinishedNumber.equals(" ") || inFinishedNumber.equals(null)) {
				check = 1;
				JOptionPane.showMessageDialog(null, "完成人數不可為空", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
		            
	            	String sqlAdd="insert into `"+ ExamPaperManage.Subject +"` values('" + inGroup + "','" + inTime + "','" + inExamNumber + "','" + inExamLevel + "','" + inFinishedNumber +"');";
		            statement.executeUpdate(sqlAdd);
		            JOptionPane.showMessageDialog(null, "新增成功", "成功", JOptionPane.INFORMATION_MESSAGE);
		            inputGroup.setText(" ");
		            inputTime.setText(" ");
		            inputExamNumber.setText(" ");
		            inputExamLevel.setText(" ");
		            inputFinishedNumber.setText(" ");
		            
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
		            	JOptionPane.showMessageDialog(null, "新增失敗", "失敗", JOptionPane.ERROR_MESSAGE);
		            	ex.printStackTrace();
		            }catch (Exception ex) {
		            // TODO: handle exception
		            ex.printStackTrace();
		            }
		}
	}
	}
}
