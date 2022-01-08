package software;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreateExam implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JTextField inputExam;
	JTextField inputAnswer;
	JTextField inputLevel;
	JComboBox subject;
	JButton createExam;
	JButton backToExamManage;
	ExamManage exammanage;
	
	CreateExam(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("建立題目");		//sets title of frame 
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
        
        createExam = new JButton("增加題目");
        createExam.setBounds(174,167, 80, 23);
        panel.add(createExam);
        createExam.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToExamManage) {
			frame.dispose();
			exammanage = new ExamManage();
		}
		if(e.getSource() == createExam) {
			int check = 0;
			String inExam = inputExam.getText();
			if(inExam.equals("") || inExam.equals(null) || inExam.equals(" ")) {
				check = 1;
				JOptionPane.showMessageDialog(null, "題目不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inAnswer = inputAnswer.getText();
			if(inAnswer.equals("") || inAnswer.equals(null) || inAnswer.equals(" ")) {
				check = 1;
				JOptionPane.showMessageDialog(null, "答案不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
			}
			
			String inLevel = inputLevel.getText();
			if(inLevel.equals("") || inLevel.equals(null) || inLevel.equals(" ")) {
				check = 1;
				JOptionPane.showMessageDialog(null, "難度不可以是空的", "錯誤訊息", JOptionPane.ERROR_MESSAGE);
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
		            String createExam="insert into `" + ExamManage.Subject +"` values('" + inExam + "','" + inAnswer + "','" + inLevel + "','無');";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            statement.executeUpdate(createExam);
		            
		            JOptionPane.showMessageDialog(null, "題目建立成功", "成功", JOptionPane.INFORMATION_MESSAGE); 
		            frame.dispose();
		            exammanage = new ExamManage();
		        } catch(ClassNotFoundException ex) {   
		            //資料庫驅動類異常處理
		            System.out.println("Sorry,can`t find the Driver!");   
		            ex.printStackTrace();   
		            } catch(SQLException ex) {
		            	JOptionPane.showMessageDialog(null, "該題目已存在", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            //資料庫連線失敗異常處理
		            	ex.printStackTrace();
		            }catch (Exception ex) {
		            // TODO: handle exception
		            	JOptionPane.showMessageDialog(null, "輸入不符合格式", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
		            	ex.printStackTrace();
		            }
				}
		}
		
	}

}
