package software;

import java.awt.TextField;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Practice implements ActionListener{

	private JFrame frame;
	private JPanel panel;
	JLabel timer;
	JTextArea exam;
	JTextField answer;
	JButton backToChooseParctice;
	JButton front;
	JButton back;
	JButton handIn;
	int practiceTime;
	int nowPosition;
	int num;
	int right;
	int wrong;
	ChooseParctice chooseparctice;
	String[] Answer;
	ClockDispaly mt; // 倒計時模組
	
	Practice(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("練習");		//sets title of frame 
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
		            System.exit(0);
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
		
		exam = new JTextArea();
        exam.setBounds(6,54, 388, 84);
        exam.setLineWrap(true); //設定自動換行
        exam.setEditable(false);
        frame.getContentPane().add(exam);
        
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
            String subject=ChooseParctice.Subjects+"試卷";
            //要執行的SQL語句
            String SqlSelectTime="select `time` from `" +subject +"` where `group` = '" + ChooseParctice.type + "';";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsTime = statement.executeQuery(SqlSelectTime); 
            if(rsTime.next()) {
            	 String a =rsTime.getString(1);
            	 practiceTime = Integer.parseInt(a);
            }

            String SqlSelectExam="select `question` from `" + ChooseParctice.Subjects +"` where `group` = '" + ChooseParctice.type + "';";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsExam = statement.executeQuery(SqlSelectExam); 
            if(rsExam.next()) {
            	 String b =rsExam.getString("question");
            	 exam.setText(b);
            }
            
            String SqlExamNumber="select `examnumber` from `" + ChooseParctice.Subjects +"試卷` where `group` = '" + ChooseParctice.type + "';";
            //3.ResultSet類，用來存放獲取的結果集！！
            ResultSet rsExamNumber = statement.executeQuery(SqlExamNumber); 
            if(rsExamNumber.next()) {
            	 String c =rsExamNumber.getString(1);
            	 num = Integer.parseInt(c);
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
        
		timer = new JLabel("時間");
		timer.setBounds(102,6, 205, 36);
		panel.add(timer);
		
        answer = new JTextField();
        answer.setBounds(83,150, 205, 48);
        panel.add(answer);
        Answer = new String[num];
        for(int q=0; q<num; q++) {
        	Answer[q] = "";
        }
        
        backToChooseParctice = new JButton("取消");
        backToChooseParctice.setBounds(6,210, 80, 23);
        panel.add(backToChooseParctice);
        backToChooseParctice.addActionListener(this);
        
        front = new JButton("上一題");
        front.setBounds(83,210, 80, 23);
        panel.add(front);
        front.addActionListener(this);
        
        back = new JButton("下一題");
        back.setBounds(175,210, 80, 23);
        panel.add(back);
        back.addActionListener(this);
        
        handIn = new JButton("繳交");
        handIn.setBounds(261,210, 80, 23);
        panel.add(handIn);
        handIn.addActionListener(this);
        if(nowPosition == num) {
        	handIn.setEnabled(true);
        }
        mt = new ClockDispaly(timer, practiceTime); // 呼叫並設定倒計時的時間
        
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backToChooseParctice) {
			frame.dispose();
			chooseparctice = new ChooseParctice();
		}
		if(e.getSource() == front) {
			if(nowPosition == 0) {
				 JOptionPane.showMessageDialog(null, "這是第一題", "小提醒", JOptionPane.INFORMATION_MESSAGE); 
			}
			else {
				String ans = answer.getText();
				Answer[nowPosition] = ans;
				answer.setText("");
				nowPosition--;
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
		            String b ="";
		            String SqlSelectExam="select `question` from `" + ChooseParctice.Subjects +"` where `group` = '" + ChooseParctice.type + "';";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsExam = statement.executeQuery(SqlSelectExam); 
		            for(int i=0; i<=nowPosition; i++) {
		            	if(rsExam.next()) {
			            	 b =rsExam.getString("question");
			            }
		            }
		            exam.setText(b);
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
		        
		        if(!Answer[nowPosition].equals("")) {
		        	answer.setText(Answer[nowPosition]);
		        }
			}
		}
		if(e.getSource() == back) {
			if(nowPosition == num-1) {
				 JOptionPane.showMessageDialog(null, "這是最後一題", "小提醒", JOptionPane.INFORMATION_MESSAGE); 
			}
			else {
				String ans = answer.getText();
				Answer[nowPosition] = ans;
				answer.setText("");
				nowPosition++;
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
		            String b ="";
		            String SqlSelectExam="select `question` from `" + ChooseParctice.Subjects +"` where `group` = '" + ChooseParctice.type + "';";
		            //3.ResultSet類，用來存放獲取的結果集！！
		            ResultSet rsExam = statement.executeQuery(SqlSelectExam); 
		            for(int i=0; i<=nowPosition; i++) {
		            	if(rsExam.next()) {
			            	 b =rsExam.getString("question");
			            }
		            }
		            exam.setText(b);
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
		        
		        if(!Answer[nowPosition].equals("")) {
		        	answer.setText(Answer[nowPosition]);
		        }
			}
			
		}
		if(e.getSource() == handIn) {
			String ans = answer.getText();
			Answer[nowPosition] = ans;
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
	            int ti = 0;
	            String SqlSelectAnswer="select `answer` from `" + ChooseParctice.Subjects +"` where `group` = '" + ChooseParctice.type + "';";
	            //3.ResultSet類，用來存放獲取的結果集！！
	            ResultSet rsAnswer = statement.executeQuery(SqlSelectAnswer); 
	            	while(rsAnswer.next()) {
		            	String b =rsAnswer.getString("answer");
		            	if(Answer[ti].equals(b)) {
		            		right++;
		            	}
		            	else if(!Answer[ti].equals(b)) {
		            		wrong++;
		            	}
		            	ti++;
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
	        int sum = right + wrong;
	        int grade = 100 / sum * right;
	        String Right = Integer.toString(right);
	        String  Sum= Integer.toString(sum);
	        String  Grade= Integer.toString(grade);
	        JOptionPane.showMessageDialog(null, "答對" + Right +"題 共" + Sum +"題 分數： " + Grade, "成績", JOptionPane.INFORMATION_MESSAGE); 
	        frame.dispose();
	        chooseparctice = new ChooseParctice();
		}
	}

	class ClockDispaly extends Thread {// 設定Thread考試倒計時模組

		private JLabel lefttimer;
		 private int practiceTime;
		  public ClockDispaly(JLabel lt, int time) {
			  lefttimer = lt;
			  practiceTime = time * 60;
		  }
	public void run() {
		  NumberFormat numberFormat = NumberFormat.getInstance();// 控制時間的顯示格式
		  numberFormat.setMinimumIntegerDigits(2);// 設定數值的整數部分允許的最小位數
		  int h, m, s;// 定義時分秒
		  while (practiceTime >= 0) {
		   h = practiceTime / 3600;
		   m = practiceTime % 3600 / 60;
		   s = practiceTime % 60;
		   StringBuffer stringBuffer = new StringBuffer("");
		   // 增加到timer標籤
		   stringBuffer.append(
		     "考試剩餘時間為：" + numberFormat.format(h) + ":" + numberFormat.format(m) + ":" + numberFormat.format(s));
		   lefttimer.setText(stringBuffer.toString());
		   try {
		    Thread.sleep(1000);// 延時一秒
		   } catch (Exception e) {
		    // ignore error
		   }
		   practiceTime = practiceTime - 1;
		  }
		  if (practiceTime <= 0) {
			  JOptionPane.showMessageDialog(null, "時間已到，請按繳交", "注意",JOptionPane.ERROR_MESSAGE);
			  front.setEnabled(false);
			  back.setEnabled(false);
		  }  
		  }
		 }
}

