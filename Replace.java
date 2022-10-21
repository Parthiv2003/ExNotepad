package ExNotepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Replace extends JFrame implements ActionListener
{
	Container cn;
	Font f;
	JLabel lb1,lb2;
	JTextField jtf1,jtf2;
	JButton b1,b2;
	JTextArea jta;
	
	public Replace(String string, JTextArea jta) {
		// TODO Auto-generated constructor stub
		
		super(string);
		this.jta = jta;
		
		cn = getContentPane();
		cn.setLayout(null);
		
		f = new Font("Arial",Font.BOLD,20);
		
		lb1 = new JLabel("Find word :- ",JLabel.CENTER);
		lb1.setFont(f);
		lb1.setBounds(0,0,200,100);
		add(lb1);
		
		lb2 = new JLabel("Replace word :- ",JLabel.CENTER);
		lb2.setFont(f);
		lb2.setBounds(0,100,200,100);
		add(lb2);
		
		f = new Font("Arial",Font.BOLD,20);
		
		jtf1 = new JTextField();
		add(jtf1);
		jtf1.setBounds(230,20,340,60);
		jtf1.setFont(f);
		
		jtf2 = new JTextField();
		add(jtf2);
		jtf2.setBounds(230,120,340,60);
		jtf2.setFont(f);
		
		f = new Font("Arial",Font.BOLD,20);

		b1 = new JButton("Replace");
		add(b1);
		b1.setFont(f);
		b1.setBounds(100,200,150,50);
		
		b2 = new JButton("Cancel");
		add(b2);
		b2.setFont(f);
		b2.setBounds(350,200,150,50);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s = e.getActionCommand();
		
		if(s.equals("Replace"))
		{
			String find = jtf1.getText().toUpperCase(); // User Input Word to find
			int ln = find.length();                   
			String fta = jta.getText().toUpperCase(); // TextArea Content

			try
		    {
				int index = 0;
		        while(index>=0) 
		        {
		            index = fta.indexOf(find,index);
		            if (index > 0) 
		            {
		            	jta.setSelectionStart(index);
		            	jta.setSelectionEnd(index + ln);
		            	jta.replaceSelection(jtf2.getText());
		                index++;
		            }
		        }
		    }
			catch(Exception e1)
			{
			}
		}
		
		if(s.equals("Cancel"))
		{
			setVisible(false);
		}
	}
}
