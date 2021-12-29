package software;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SystemManager implements ActionListener{
	JButton accountManage;
	JButton createUserAccount;
	private JFrame frame;
	
	SystemManager(){
		frame = new JFrame();	//create a frame 
		frame.setTitle("系統管理者系統");		//sets title of frame 
		frame.setSize(300,300);	//sets size of frame
        frame.setLayout(new GridBagLayout());	//sets frame type
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//exit out of application
        frame.getContentPane().setBackground(Color.blue);	//change color of background
        frame.setVisible(true);
        
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
        frame.add(accountManage, c3);
        
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
        
        JButton testGroupManage = new JButton("測驗群組管理");
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
        
        JButton testSubjectManage = new JButton("測驗科目管理");
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
        
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == createUserAccount) {
			frame.dispose();
			CreateUserAccount createuseraccount = new CreateUserAccount();
		}
	}
}
