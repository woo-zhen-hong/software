package software;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class User implements ActionListener{
	private JFrame frame;
	JButton userAccount;
	UserAccount useraccount;
	
	User(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("使用者系統");		//sets title of frame 
		frame.setSize(300,300);	//sets size of frame
        frame.setLayout(new GridBagLayout());	//sets frame type
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//exit out of application
        frame.getContentPane().setBackground(Color.gray);	//change color of background
        frame.setVisible(true);
        
        userAccount = new JButton("個人帳號修改");
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 2;
        c1.gridy = 0;
        c1.gridwidth = 3;
        c1.gridheight = 1;
        c1.weightx = 0;
        c1.weighty = 0;
        c1.fill = GridBagConstraints.BOTH;
        c1.anchor = GridBagConstraints.CENTER;
        frame.add(userAccount, c1);
        userAccount.addActionListener(this);
        
        JButton inquireGrade = new JButton("個人成績查詢");
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 2;
        c4.gridy = 1;
        c4.gridwidth = 3;
        c4.gridheight = 1;
        c4.weightx = 0;
        c4.weighty = 0;
        c4.fill = GridBagConstraints.BOTH;
        c4.anchor = GridBagConstraints.CENTER;
        frame.add(inquireGrade, c4);
        
        JButton testGroup = new JButton("測驗群組");
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 2;
        c2.gridy = 2;
        c2.gridwidth = 3;
        c2.gridheight = 1;
        c2.weightx = 0;
        c2.weighty = 0;
        c2.fill = GridBagConstraints.BOTH;
        c2.anchor = GridBagConstraints.CENTER;
        frame.add(testGroup, c2);
        
        JButton testSubject = new JButton("測驗科目");
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 2;
        c3.gridy = 3;
        c3.gridwidth = 3;
        c3.gridheight = 1;
        c3.weightx = 0;
        c3.weighty = 0;
        c3.fill = GridBagConstraints.BOTH;
        c3.anchor = GridBagConstraints.CENTER;
        frame.add(testSubject, c3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == userAccount) {
			frame.dispose();
			useraccount = new UserAccount();
		}
		
	}
}
