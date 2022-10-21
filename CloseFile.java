package ExNotepad;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;

class CloseFile implements ActionListener
{
	JLabel lb;
	JTextArea jta;
	JButton b1,b2,b3;
	Font f;
	JPanel pn;
	Container cn;
	String str;
	static boolean flag = false;
	MyNotepad mnp;
	JDialog jd;
	
	public CloseFile(JDialog jd,String s,JTextArea jta,MyNotepad mnp) {
		// TODO Auto-generated constructor stub
		
		this.str = s;
		this.jta = jta;
		this.jd = jd;
		this.mnp = mnp;
		
		f = new Font("Arial",Font.BOLD,30);
		
		jd.setModal(true);
		lb = new JLabel("Do You Want to save?",JLabel.CENTER);
		lb.setFont(f);
		jd.add(lb,BorderLayout.CENTER);
		
		f = new Font("Arial",Font.BOLD,15);
		pn = new JPanel();
		
		b1 = new JButton("Save");
		pn.add(b1);
		b1.setFont(f);
				
		b2 = new JButton("Don't Save");
		pn.add(b2);
		b2.setFont(f);
		
		b3 = new JButton("Cancel");
		pn.add(b3);
		b3.setFont(f);

		jd.add(pn,BorderLayout.SOUTH);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s = e.getActionCommand();
		
		if(s.equals("Save"))
		{
			jd.setVisible(false);
			mnp.setVisible(true);
			try
			{
				FileDialog fd = new FileDialog(mnp,"SAVE A FILE",FileDialog.SAVE);
				fd.setVisible(true);
					
				mnp.folder = fd.getDirectory();
				mnp.fname = fd.getFile();
					
				FileWriter fw = new FileWriter(mnp.folder + "//" +mnp.fname);
					
				fw.write(mnp.content);
				fw.close();
				
				jd.setTitle(mnp.fname);
			}
			catch(Exception e1)
			{		
			}
			
			if(mnp.s == "New")
			{			
				mnp.setTitle("Your New File");
				jta.setText(null);
				mnp.fname = "Your New File";
			}
			else if(mnp.s == "Open")
			{
				try
				{
					FileDialog fd = new FileDialog(mnp,"Open a File",FileDialog.LOAD);
					fd.setVisible(true);
						
					mnp.folder = fd.getDirectory();
					mnp.fname = fd.getFile();
					mnp.setTitle(mnp.fname);
						
					File f = new File(mnp.folder,mnp.fname);
					FileInputStream fis = new FileInputStream(f);
						
					int n = 0;
					String data = "";
						
					while(n != -1)
					{
						n = fis.read();		
						if(n != -1)
						{
							data += (char)n;
						}
					}
					fis.close();
					jta.setText(data);						
				}
				catch(IOException ioe)
				{
					JDialog dia = new JDialog(mnp,"File Not Found !!!");
					dia.setVisible(true);
					dia.setBounds(700,425,400,100);
					JLabel lab = new JLabel("\"" + mnp.fname + "\"" + " This file not exist." + "\n" + "Please Enter file name again.",JLabel.CENTER);
					dia.add(lab);
				}
			}
			else if(mnp.s == "Exit")
			{
				System.exit(0);
			}
		}

		if(s.equals("Don't Save"))
		{
			jd.setVisible(false);
			mnp.setVisible(true);
			
			if(mnp.s == "New")
			{			
				mnp.setTitle("Your New File");
				jta.setText(null);
				mnp.fname = "Your New File";
			}
			else if(mnp.s == "Open")
			{
				try
				{
					FileDialog fd = new FileDialog(mnp,"Open a File",FileDialog.LOAD);
					fd.setVisible(true);
						
					mnp.folder = fd.getDirectory();
					mnp.fname = fd.getFile();
					mnp.setTitle(mnp.fname);
						
					File f = new File(mnp.folder,mnp.fname);
					FileInputStream fis = new FileInputStream(f);
						
					int n = 0;
					String data = "";
						
					while(n != -1)
					{
						n = fis.read();		
						if(n != -1)
						{
							data += (char)n;
						}
					}
					fis.close();
					jta.setText(data);						
				}
				catch(IOException ioe)
				{
					JDialog dia = new JDialog(mnp,"File Not Found !!!");
					dia.setVisible(true);
					dia.setBounds(700,425,400,100);
					JLabel lab = new JLabel("\"" + mnp.fname + "\"" + " This file not exist." + "\n" + "Please Enter file name again.",JLabel.CENTER);
					dia.add(lab);
				}
			}
			else if(mnp.s == "Exit")
			{
				System.exit(0);
			}
		}

		if(s.equals("Cancel"))
		{
			jd.setVisible(false);
			mnp.setVisible(true);
		}
	}
}
