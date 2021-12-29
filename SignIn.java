package software;
import java.awt.*;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SignIn implements ActionListener{
	private JFrame frame;
	private JButton SignIn;
	private JTextField inputAccount;
	public static String userAccount;
	public static String userPassword;
	private JTextField inputPassword;
	User user;
	SystemManager systemManager;
	TestManager testManager;
	int count = 0;
	//宣告Connection物件
    Connection con;
    
	public void run() {
		frame = new JFrame();	//create a frame 
		frame.setTitle("評量系統KYUTES");		//sets title of frame 
		frame.setSize(300,300);	//sets size of frame
        frame.setLayout(new GridBagLayout());	//sets frame type
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//exit out of application
        frame.getContentPane().setBackground(Color.pink);	//change color of background
        
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
                    String SqlUpadateSignIn="update `user` set `signin`='0' where account='" + userAccount +"'";
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
        
        JLabel systemName = new JLabel("評量系統KYUTES");
        GridBagConstraints c0 = new GridBagConstraints();
        c0.gridx = 0;
        c0.gridy = 0;
        c0.gridwidth = 6;
        c0.gridheight = 1;
        c0.weightx = 0;
        c0.weighty = 0;
        c0.fill = GridBagConstraints.NONE;
        c0.anchor = GridBagConstraints.CENTER;
        frame.add(systemName, c0);
        
        JLabel account = new JLabel("帳號");
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy = 1;
        c1.gridwidth = 2;
        c1.gridheight = 1;
        c1.weightx = 0;
        c1.weighty = 0;
        c1.fill = GridBagConstraints.BOTH;
        c1.anchor = GridBagConstraints.WEST;
        frame.add(account, c1);
        
        inputAccount = new JTextField();
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 3;
        c2.gridy = 1;
        c2.gridwidth = 4;
        c2.gridheight = 1;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.WEST;
        frame.add(inputAccount, c2);
        
        JLabel password = new JLabel("密碼");
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy = 2;
        c3.gridwidth = 2;
        c3.gridheight = 1;
        c3.weightx = 0;
        c3.weighty = 0;
        c3.fill = GridBagConstraints.BOTH;
        c3.anchor = GridBagConstraints.WEST;
        frame.add(password, c3);
        
        inputPassword = new JTextField();
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 3;
        c4.gridy = 2;
        c4.gridwidth = 4;
        c4.gridheight = 1;
        c4.weightx = 0;
        c4.weighty = 0;
        c4.fill = GridBagConstraints.BOTH;
        c4.anchor = GridBagConstraints.WEST;
        frame.add(inputPassword, c4);
        
        SignIn = new JButton("登入");
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 3;
        c5.gridy = 3;
        c5.gridwidth = 2;
        c5.gridheight = 1;
        c5.weightx = 0;
        c5.weighty = 0;
        c5.fill = GridBagConstraints.NONE;
        c5.anchor = GridBagConstraints.CENTER;
        frame.add(SignIn, c5);
        SignIn.addActionListener(this);
        
        frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == SignIn) {
			userAccount = inputAccount.getText();
			userPassword = inputPassword.getText();
			int sign = -1;
			//宣告Connection物件
	        Connection con;
	        //驅動程式名
	        String driver = "com.mysql.cj.jdbc.Driver";
	        //URL指向要訪問的資料庫名
	        String url = "jdbc:mysql://localhost:3306/software";
	        //MySQL配置時的使用者名稱
	        String user = "root";
	        //MySQL配置時的密碼
	        String password = "jackywoo";
	        //遍歷查詢結果集
	        try {
	            //載入驅動程式
	            Class.forName(driver);
	            //1.getConnection()方法，連線MySQL資料庫！！
	            con = DriverManager.getConnection(url,user,password);
	            //2.建立statement類物件，用來執行SQL語句！！
	            Statement statement = con.createStatement();
	            //要執行的SQL語句
	           
	            String sqlSignIn="select `signin` from user where account='"+ userAccount+ "'";
	            //3.ResultSet類，用來存放獲取的結果集！！
	            ResultSet rsSignIn= statement.executeQuery(sqlSignIn);
	            if(rsSignIn.next()) {
	            	 String rsSignin = rsSignIn.getString(1);
	            	 sign = Integer.parseInt(rsSignin);
	            }
	            
	            String sql="select `account` from user where account='"+userAccount+"'  and password='"+userPassword+"'";
	            //3.ResultSet類，用來存放獲取的結果集！！
	            ResultSet rs= statement.executeQuery(sql);
	            if(rs.next()) {
	            	if(userAccount.charAt(0) == 'a' && sign == 0) {
	            		String sqlUpadateSignIn="update `user` set `signin`='1' where account='" + userAccount +"'";
			            //3.ResultSet類，用來存放獲取的結果集！！
			            statement.executeUpdate(sqlUpadateSignIn);
						frame.dispose();
						//con.close();
						this.user = new User();
					}
					else if(userAccount.charAt(0) == 'b' && sign == 0) {
						String sqlUpadateSignIn="update `user` set `signin`='1' where account='" + userAccount +"'";
			            //3.ResultSet類，用來存放獲取的結果集！！
			            statement.executeUpdate(sqlUpadateSignIn);
						frame.dispose();
						//con.close();
						systemManager = new SystemManager();
					}
					else if(userAccount.charAt(0) == 'c' && sign == 0) {
						String sqlUpadateSignIn="update `user` set `signin`='1' where account='" + userAccount +"'";
			            //3.ResultSet類，用來存放獲取的結果集！！
			            statement.executeUpdate(sqlUpadateSignIn);
						frame.dispose();
						//con.close();
						testManager = new TestManager();
					}
	            	if(userAccount.charAt(0) == 'a' && sign == 1) {
	            		JOptionPane.showMessageDialog(null, "此帳號已被登入", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
	            		inputAccount.setText(" ");
	            		inputPassword.setText(" ");
	            		count++;
	            	}
	            	else if(userAccount.charAt(0) == 'b' && sign == 1) {
	            		JOptionPane.showMessageDialog(null, "此帳號已被登入", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
	            		inputAccount.setText(" ");
	            		inputPassword.setText(" ");
	            		count++;
	            	}
	            	else if(userAccount.charAt(0) == 'c' && sign == 1) {
	            		JOptionPane.showMessageDialog(null, "此帳號已被登入", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
	            		inputAccount.setText(" ");
	            		inputPassword.setText(" ");
	            		count++;
	            	}
	            }
	            else {
	            	JOptionPane.showMessageDialog(null, "帳號密碼輸入錯誤", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
	            	count++;
	            }		
	            if(count >= 3) {
	            	JOptionPane.showMessageDialog(null, "登入錯誤超過三次，已無法再登入", "錯誤訊息", JOptionPane.ERROR_MESSAGE); 
	    			SignIn.setEnabled(false);
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
		}
	}
}
