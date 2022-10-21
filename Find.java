package ExNotepad;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.*;

class Find extends JFrame implements ActionListener
{
	JLabel lb;
	JTextField jtf;
	JTextArea jta;
	JButton b1,b2,b3,b4;
	Font f;
	Container cn;
	static int index=0;

	public Find(String string, JTextArea jta) {
		// TODO Auto-generated constructor stub
		
		super(string);
		this.jta = jta;
		
		cn = getContentPane();
		cn.setLayout(null);
		
		f = new Font("Arial",Font.BOLD,30);
		
		lb = new JLabel("Find word :- ",JLabel.CENTER);
		lb.setFont(f);
		lb.setBounds(0,0,300,100);
		add(lb);
		
		jtf = new JTextField();
		add(jtf);
		jtf.setBounds(50,100,200,50);
		jtf.setFont(f);
		
		f = new Font("Arial",Font.BOLD,15);

		b1 = new JButton("Find");
		add(b1);
		b1.setFont(f);
		b1.setBounds(350,10,120,25);
		
		b2 = new JButton("Cancel");
		add(b2);
		b2.setFont(f);
		b2.setBounds(350,130,120,25);
		
		b3 = new JButton("Find Next");
		add(b3);
		b3.setFont(f);
		b3.setBounds(350,50,120,25);
		
		b4 = new JButton("Find Prev");
		add(b4);
		b4.setFont(f);
		b4.setBounds(350,90,120,25);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = e.getActionCommand();
		
		
		if(s.equals("Find"))
		{
			String find = jtf.getText().toUpperCase(); // User Input Word to find
			int ln = find.length();                   
			String fta = jta.getText().toUpperCase(); // TextArea Content
			Highlighter h = jta.getHighlighter();
			h.removeAllHighlights();
			
			try
		    {
		        while(index>=0) 
		        {
		            index = fta.indexOf(find,index);
		            if (index > 0) 
		            {
		               h.addHighlight(index,index + ln, DefaultHighlighter.DefaultPainter);
		               index++;
		            }
		        }
		    }
			catch(Exception e1)
			{
			}
		}
		
		if(s.equals("Find Next"))
		{
			String find = jtf.getText().toUpperCase(); // User Input Word to find
			int ln = find.length();                   
			String fta = jta.getText().toUpperCase(); // TextArea Content
			Highlighter h = jta.getHighlighter();
			h.removeAllHighlights();

			try
		    {
		        index = fta.indexOf(find,index);
		        if (index > 0) 
		        {
		            h.addHighlight(index,index + ln, DefaultHighlighter.DefaultPainter);
		            index++;
		        }
		    }
			catch(Exception e1)
			{
				
			}
		}
		
		if(s.equals("Find Prev"))
		{
			String find = jtf.getText().toUpperCase(); // User Input Word to find
			int ln = find.length();                   
			String fta = jta.getText().toUpperCase(); // TextArea Content
			Highlighter h = jta.getHighlighter();
			h.removeAllHighlights();
		
			int p=0;
				
			int offset = fta.indexOf(find);
			
			while (offset != index && offset != -1)
			{
				p = offset;
			    offset = fta.indexOf(find, offset);
			    offset++;
			}
			
			if(offset == index)
			{
				try
				{
					index = p;
					h.addHighlight(p, p + ln, DefaultHighlighter.DefaultPainter);
				}
				catch(Exception ae)
				{
				}
			}
			else if(offset == -1)
			{
				index = fta.lastIndexOf(find);
				try
				{
					h.addHighlight(index, index+ln, DefaultHighlighter.DefaultPainter);
				} 
				catch (Exception e1)
				{

				}
			}
		}
		
		if(s.equals("Cancel"))
		{
			setVisible(false);
			
		}
	}
	
}
