package ExNotepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class FontChooser extends JComponent
{
	InputPanel input;
	public FontChooser()
	{
		setLayout(new BorderLayout());
		
		SampleText st = new SampleText();
		input = new InputPanel(st);
		
		add(input,BorderLayout.CENTER);
		add(st,BorderLayout.SOUTH);
	}
	
	public static Font showDialog(MyNotepad mnp, String string) throws Exception {
		// TODO Auto-generated method stub
		final FontChooser pane = new FontChooser();
		FontTracker ftc = new FontTracker(pane);
		
		JDialog jd = createDialog(mnp,string,true,pane,ftc,null);
		jd.addWindowListener(new FontChooserDialog.Closer());
		jd.addComponentListener(new FontChooserDialog.DisposeOnClose());
		jd.setVisible(true);
		
		return ftc.getSelectedFont();
	}
	
	public static Font showDialog(TableSize ts, String string) throws Exception {
		// TODO Auto-generated method stub
		final FontChooser pane = new FontChooser();
		FontTracker ftc = new FontTracker(pane);
		
		JDialog jd = createDialog(ts,string,true,pane,ftc,null);
		jd.addWindowListener(new FontChooserDialog.Closer());
		jd.addComponentListener(new FontChooserDialog.DisposeOnClose());
		jd.setVisible(true);
		
		return ftc.getSelectedFont();
	}
	
	public static JDialog createDialog(MyNotepad mnp,String str,boolean b,FontChooser fc,ActionListener ok,ActionListener cancel) throws Exception
	{
		return new FontChooserDialog(mnp,str,b,fc,ok,cancel);
	}
	
	public static JDialog createDialog(TableSize ts,String str,boolean b,FontChooser fc,ActionListener ok,ActionListener cancel) throws Exception
	{
		return new FontChooserDialog(ts,str,b,fc,ok,cancel);
	}
	
	public Font getSelectedFont()
	{
		return input.getSelectedFont();
	}
	
//	Inner class
	
	static class InputPanel extends JPanel
	{
		JLabel lb1,lb2,lb3;
		static JTextField jtf1;
		static JTextField jtf2;
		static JTextField jtf3;
		JScrollPane jsp1,jsp2,jsp3;
		Font f;
		Box b1,b2,b3,mainBox;
		
		private FontName fn = new FontName();
		private FontStyle fs = new FontStyle();
		private FontSize fz = new FontSize();
		
		public InputPanel(ListSelectionListener lst)
		{
			setLayout(new BorderLayout());
			
			f = new Font("Arial",Font.PLAIN,15);
			
			b1 = Box.createVerticalBox();
			b1.add(Box.createHorizontalStrut(10));
			lb1 = new JLabel("Font Name :- ");
			lb1.setFont(f);
			b1.add(lb1);
			jtf1 = new JTextField();
			b1.add(jtf1);
			
			if(lst != null)
			{
				fn.addListSelectionListener(lst);
			}
			
			jsp1 = new JScrollPane(fn);
			b1.add(jsp1);
			b1.add(Box.createVerticalStrut(10));
			
			b2 = Box.createVerticalBox();
			b2.add(Box.createVerticalStrut(10));
			lb2 = new JLabel("Font Style :- ");
			lb2.setFont(f);
			b2.add(lb2);
			jtf2 = new JTextField();
			b2.add(jtf2);
			
			if(lst != null)
			{
				fs.addListSelectionListener(lst);
			}
			
			jsp2 = new JScrollPane(fs);
			b2.add(jsp2);
			b2.add(Box.createVerticalStrut(10));
			
			b3 = Box.createVerticalBox();
			b3.add(Box.createVerticalStrut(10));
			lb3 = new JLabel("Font Size :- ");
			lb3.setFont(f);
			b3.add(lb3);
			jtf3 = new JTextField();
			b3.add(jtf3);
			
			if(lst != null)
			{
				fz.addListSelectionListener(lst);
			}
			
			jsp3 = new JScrollPane(fz);
			b3.add(jsp3);
			b3.add(Box.createVerticalStrut(10));
			
			mainBox = Box.createHorizontalBox();
			mainBox.add(Box.createHorizontalStrut(10));
			mainBox.add(b1);
			mainBox.add(Box.createHorizontalStrut(10));
			mainBox.add(b2);
			mainBox.add(Box.createHorizontalStrut(10));
			mainBox.add(b3);
			mainBox.add(Box.createHorizontalStrut(10));
			add(mainBox,BorderLayout.CENTER);
			
	    	setBorder(BorderFactory.createTitledBorder("Font-Family :-"));
		}
		
		public Font getSelectedFont()
		{
			return new Font(fn.getFontName(),fs.getFontStyle(),fz.getFontsize());
		}
		
	}
	
	class SampleText extends JPanel implements ListSelectionListener
	{
		JLabel lb;	
		JScrollPane jsp;
		Box b;
		Font f;
		
		JTextField jtf = new JTextField("My Name is Parthiv Dholakiya...");
		
		public SampleText()
		{
			setLayout(new FlowLayout());
			f = new Font("Arial",Font.PLAIN,15);
			
			b = Box.createVerticalBox();
			lb = new JLabel("Sample Text:- ");
			lb.setFont(f);
			b.add(lb);
				
			f = new Font("Arial",Font.PLAIN,12);
			
			jtf.setEditable(false);
			jtf.setBackground(Color.WHITE);
			jtf.setForeground(Color.BLACK);
			jtf.setFont(f);
			
			jsp = new JScrollPane(jtf);
			jsp.setPreferredSize(new Dimension(300,80));
			b.add(jsp);
			
			add(b);
		}
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			jtf.setFont(FontChooser.this.getSelectedFont());
		}
		
	}
		
    static class FontName extends JList
	{
		private final static String[] fns = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		static int index = 0;
		FontName()
		{
			super(fns);
			setSelectedIndex(index);
			setVisibleRowCount(7);
		}
		
		String getFontName()
		{
			index = this.getSelectedIndex();
			InputPanel.jtf1.setText((String)getSelectedValue());
			return (String)getSelectedValue();
		}
	}
      
    static class FontStyle extends JList
    {
    	static final String[] fss = {"Regular","Italic","Bold","Bold-Italic"};
    	static int index = 0;
    	
    	FontStyle()
    	{
    		super(fss);
			setSelectedIndex(index);
			setVisibleRowCount(7);
    	}
    	
    	int getFontStyle()
    	{
    		int s =0;
    		InputPanel.jtf2.setText((String)getSelectedValue());
			index = this.getSelectedIndex();
    		String name = (String)getSelectedValue();
    		
    		if(name.equals("Regular"))
    		{
    			s = Font.PLAIN;
    		}
    		else if(name.equals("Italic"))
    		{
    			s = Font.ITALIC;
    		}
    		else if(name.equals("Bold"))
    		{
    			s = Font.BOLD;
    		}
    		else if(name.equals("Bold-Italic"))
    		{
    			s = Font.BOLD + Font.ITALIC;
    		}
    		return s;
    	}
    }
    
     static class FontSize extends JList
     {
    	static final String[] fzs = {"6","7","8","9","10","11","12","14","16","18","20","22","24","28","32","36","40","44","48","52","56","60","64","68","72"};
    	static int index = 6;

    	FontSize()
    	{
    		super(fzs);
    		setSelectedIndex(index);
    		setVisibleRowCount(7);
    	}
    	
    	int getFontsize()
    	{
    		if(TableSize.flag == true)
    		{
    			this.setEnabled(false);
        		index = this.getSelectedIndex();
        		InputPanel.jtf3.setText("" + Integer.parseInt((String)getSelectedValue()));
        		
        		return Integer.parseInt((String)getSelectedValue());
    		}
    		else
    		{
	    		index = this.getSelectedIndex();
	    		InputPanel.jtf3.setText("" + Integer.parseInt((String)getSelectedValue()));
	    		
	    		return Integer.parseInt((String)getSelectedValue());
    		}
    	}
    }
}
