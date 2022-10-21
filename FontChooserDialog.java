package ExNotepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class FontChooserDialog extends JDialog implements ActionListener
{
	FontChooser fcs;
	Container cn;
	JPanel pn;
	JButton b1,b2;
	
	public FontChooserDialog(TableSize ts, String str, boolean b, FontChooser fc, ActionListener ok, ActionListener cancel) throws Exception
	{
		super(JOptionPane.getFrameForComponent(ts),str,b);
		this.fcs = fc;
		
		cn = getContentPane();
		cn.setLayout(new BorderLayout());
		cn.add(fcs,BorderLayout.CENTER);
		
		pn = new JPanel();
		pn.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		b1 = new JButton("OK");
		getRootPane().setDefaultButton(b1);
		b1.setActionCommand("OK");
		
		if(b1 != null)
		{
			b1.addActionListener(ok);
		}
		
		b1.addActionListener(this);
		pn.add(b1);
		
		b2 = new JButton("Cancel");
		b2.setActionCommand("Cancel");
		
		if(b2 != null)
		{
			b2.addActionListener(cancel);
		}
		
		b2.addActionListener(this);
		pn.add(b2);
		
		cn.add(pn,BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(ts);
	}
	
	public FontChooserDialog(MyNotepad mnp, String str, boolean b, FontChooser fc, ActionListener ok, ActionListener cancel) throws Exception
	{
		super(JOptionPane.getFrameForComponent(mnp),str,b);
		this.fcs = fc;
		
		cn = getContentPane();
		cn.setLayout(new BorderLayout());
		cn.add(fcs,BorderLayout.CENTER);
		
		pn = new JPanel();
		pn.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		b1 = new JButton("OK");
		getRootPane().setDefaultButton(b1);
		b1.setActionCommand("OK");
		
		if(b1 != null)
		{
			b1.addActionListener(ok);
		}
		
		b1.addActionListener(this);
		pn.add(b1);
		
		b2 = new JButton("Cancel");
		b2.setActionCommand("Cancel");
		
		if(b2 != null)
		{
			b2.addActionListener(cancel);
		}
		
		b2.addActionListener(this);
		pn.add(b2);
		
		cn.add(pn,BorderLayout.SOUTH);
		pack();
		setLocationRelativeTo(mnp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		String str = e.getActionCommand();
			
		if(str.equals("OK"))
			setVisible(false);
			
		if(str.equals("Cancel"))
			setVisible(false);
	}
		
	static class Closer extends WindowAdapter
	{
		public void windowClosing(WindowEvent we)
		{
			Window w = we.getWindow();
			w.setVisible(false);
		}
	}
	
	static class DisposeOnClose extends ComponentAdapter
	{
		public void componentHidden(ComponentEvent ce)
		{
			Window w = (Window) ce.getComponent();
			w.dispose();
		}
	}
	
}
