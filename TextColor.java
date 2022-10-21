package ExNotepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

class TextColor extends JComponent
{
	SelectColor sc;
	SelectLineColor slc;
	
	public TextColor() {
		// TODO Auto-generated constructor stub
		
		setLayout(new BorderLayout());
		
		if((MyNotepad.f2 == false && MyNotepad.f3 == false) || (MyNotepad.f2 == false && MyNotepad.f3 == true))
		{	
			SampleColor sm = new SampleColor();
			sc = new SelectColor(sm);
			add(sc,BorderLayout.CENTER);
		}
		else if(MyNotepad.f2 == true && MyNotepad.f3 == false)
		{
			SampleColor sm = new SampleColor();
			slc = new SelectLineColor(sm);
			add(slc,BorderLayout.CENTER);
	  	}
	}
	      
	public static void showDialogBox(TableSize createTable, JTextArea jta, JTextArea lines, JTable jt,String string) throws Exception {
		// TODO Auto-generated method stub
		TextColor tc = new TextColor();
		ColorTrack ct = new ColorTrack(tc);
		
		JDialog jd = createDialog(createTable,string,true,tc,ct,null);
		jd.addWindowListener(new TextColorDialog.Closer());
		jd.addComponentListener(new TextColorDialog.DisposeOnClose());
		jd.setVisible(true);
		
		if(MyNotepad.f3 = true && MyNotepad.f2 == false)
		{
			jt.setBackground(ct.getBackgroundColor());
			jt.setForeground(ct.getForegroundColor());
			jt.setGridColor(ct.caretColor());
		}
	}

	public static void showDialogBox(MyNotepad mnp, JTextArea jta, JTextArea lines,JTable jt, String string) throws Exception {
		// TODO Auto-generated method stub
		
		TextColor tc = new TextColor();
		ColorTrack ct = new ColorTrack(tc);
		
		JDialog jd = createDialog(mnp,string,true,tc,ct,null);
		jd.addWindowListener(new TextColorDialog.Closer());
		jd.addComponentListener(new TextColorDialog.DisposeOnClose());
		jd.setVisible(true);

		if(MyNotepad.f2 == false && MyNotepad.f3 == false)
		{
			jta.setBackground(ct.getBackgroundColor());
			jta.setForeground(ct.getForegroundColor());
			jta.setCaretColor(ct.caretColor());
		}
		else if(MyNotepad.f2 = true && MyNotepad.f3 == false)
		{
			lines.setBackground(ct.getBackgroundColor());
			lines.setForeground(ct.getForegroundColor());
		}
		
	}

	private static JDialog createDialog(TableSize createTable, String string, boolean b, TextColor tc,ActionListener ok,ActionListener cancel) throws Exception {
		// TODO Auto-generated method stub
		return new TextColorDialog(createTable,string,b,tc,ok,cancel);
	}
	
	private static JDialog createDialog(MyNotepad mnp, String string, boolean b, TextColor tc, ActionListener ok,ActionListener cancel) throws Exception {
		// TODO Auto-generated method stub
		return new TextColorDialog(mnp,string,b,tc,ok,cancel);
	}

	public Color getBackgroundColor() {
		// TODO Auto-generated method stub
		if(MyNotepad.f2 == false && MyNotepad.f3 == false)
			return sc.getBackgroundColor();
		else if(MyNotepad.f2 == true && MyNotepad.f3 == false)
			return slc.getBackgroundColor();
		else if(MyNotepad.f3 == true && MyNotepad.f2 == false)
			return sc.getBackgroundColor();
		return null;
	}

	public Color getForegroundColor() {
		// TODO Auto-generated method stub
		if(MyNotepad.f2 == false && MyNotepad.f3 == false)
			return sc.getForegroundColor();
		else if(MyNotepad.f3 == false && MyNotepad.f2 == true)
			return slc.getForegroundColor();
		else if(MyNotepad.f3 == true && MyNotepad.f2 == false)
			return sc.getForegroundColor();
		return null;
	}
	
	public Color getCaretColor() {
		// TODO Auto-generated method stub
		return sc.getCaretColor();
	}
	
	class SampleColor implements ListSelectionListener
	{
		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			
			if(MyNotepad.f2 == false && MyNotepad.f3 == false)
			{
				MyNotepad.jta.setBackground(TextColor.this.getBackgroundColor());
				MyNotepad.jta.setForeground(TextColor.this.getForegroundColor());
				MyNotepad.jta.setCaretColor(TextColor.this.getCaretColor());
			}
			else if(MyNotepad.f3 == false && MyNotepad.f2 == true)
			{
				MyNotepad.lines.setBackground(TextColor.this.getBackgroundColor());
				MyNotepad.lines.setForeground(TextColor.this.getForegroundColor());
			}
			else if(MyNotepad.f3 == true && MyNotepad.f2 == false)
			{
				TableSize.jt.setBackground(TextColor.this.getBackgroundColor());
				TableSize.jt.setForeground(TextColor.this.getForegroundColor());
				TableSize.jt.setGridColor(TextColor.this.getCaretColor());
			}
		}
	}
	
	static class SelectLineColor extends JPanel implements ActionListener
	{
		JLabel lb1,lb2;
		Color c1,c2;
		static JTextField jtf1,jtf2;
		JScrollPane jsp1,jsp2;
		Font f;
		JPanel pn1,pn2;
		JButton bt1,bt2;
		Box b1,b2,mainBox1;
		Container cn;
		static boolean f1 = false,f2 = false;
		
		Background bg = new Background();
		Foreground fg = new Foreground();
		
		public SelectLineColor(ListSelectionListener sm)
		{
			setLayout(new BorderLayout());
			
			f = new Font("Arial",Font.PLAIN,15);
			
			b1 = Box.createVerticalBox();
			b1.add(Box.createHorizontalStrut(10));
			
			pn1 = new JPanel();
			pn1.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			lb1 = new JLabel("Select Background Color:- ");
			lb1.setFont(f);
			pn1.add(lb1);
			bt1 = new JButton("More Colours...");
			bt1.addActionListener(this);
			pn1.add(bt1);
			
			b1.add(pn1);
			
			jtf1 = new JTextField();
			b1.add(jtf1);
			
			if(sm != null)
			{
				bg.addListSelectionListener(sm);
			}
			
			jsp1 = new JScrollPane(bg);
			b1.add(jsp1);
			b1.add(Box.createVerticalStrut(10));
			
			b2 = Box.createVerticalBox();
			b2.add(Box.createHorizontalStrut(10));			
			pn2 = new JPanel();
			pn2.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			lb2 = new JLabel("Select Foreground Color:- ");
			lb2.setFont(f);
			pn2.add(lb2);
			bt2 = new JButton("More Colours...");
			bt2.addActionListener(this);
			pn2.add(bt2);
			
			b2.add(pn2);

			jtf2 = new JTextField();
			b2.add(jtf2);
			
			if(sm != null)
			{
				fg.addListSelectionListener(sm);
			}
			
			jsp2 = new JScrollPane(fg);
			b2.add(jsp2);
			b2.add(Box.createVerticalStrut(10));
			
			mainBox1 = Box.createHorizontalBox();
			mainBox1.add(Box.createHorizontalStrut(10));
			mainBox1.add(b1);
			mainBox1.add(Box.createHorizontalStrut(10));
			mainBox1.add(b2);
			mainBox1.add(Box.createHorizontalStrut(10));
			add(mainBox1,BorderLayout.NORTH);
		
		}
		
		public Color getBackgroundColor() {
			// TODO Auto-generated method stub
			if(f1 == true)
			{
				f1 = false;
				return c1;
			}
			else 
				return bg.getBackgroundColor();
		}

		public Color getForegroundColor() {
			// TODO Auto-generated method stub
			if(f2 == true)
			{
				f2 = false;
				return c2;
			}
			else
				return fg.getForegroundColor();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Object str = e.getSource();
			
			if(str == bt1)
			{
				c1 = JColorChooser.showDialog(this, "Select Background Color", getBackgroundColor());
				f1 = true;
				bg.setSelectedIndex(-1);
			}
			if(str == bt2)
			{
				c2 = JColorChooser.showDialog(this, "Select Foreground Color", getForegroundColor());
				f2 = true;
				fg.setSelectedIndex(-1);
			}
		}
	}
	
	static class SelectColor extends JPanel implements ActionListener
	{
		JLabel lb1,lb2,lb3;
		Color c1,c2,c3;
		static JTextField jtf1,jtf2,jtf3;
		JScrollPane jsp1,jsp2,jsp3;
		Font f;
		JPanel pn1,pn2,pn3;
		JButton bt1,bt2,bt3;
		Box b1,b2,b3,mainBox1,mainBox2;
		Container cn;
		static boolean f1 = false,f2 = false,f3 = false;
		
		Background bg = new Background();
		Foreground fg = new Foreground();
		Caret cr = new Caret();
		
		public SelectColor(ListSelectionListener sm)
		{
			setLayout(new BorderLayout());
			
			f = new Font("Arial",Font.PLAIN,15);
			
			b1 = Box.createVerticalBox();
			b1.add(Box.createHorizontalStrut(10));
			
			pn1 = new JPanel();
			pn1.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			lb1 = new JLabel("Select Background Color:- ");
			lb1.setFont(f);
			pn1.add(lb1);
			bt1 = new JButton("More Colours...");
			bt1.addActionListener(this);
			pn1.add(bt1);
			
			b1.add(pn1);
			
			jtf1 = new JTextField();
			b1.add(jtf1);
			
			
			if(sm != null)
			{
				bg.addListSelectionListener(sm);
			}
			
			jsp1 = new JScrollPane(bg);
			b1.add(jsp1);
			b1.add(Box.createVerticalStrut(10));
			
			b2 = Box.createVerticalBox();
			b2.add(Box.createHorizontalStrut(10));
			
			pn2 = new JPanel();
			pn2.setLayout(new FlowLayout(FlowLayout.CENTER));
			
			lb2 = new JLabel("Select Foreground Color:- ");
			lb2.setFont(f);
			pn2.add(lb2);
			bt2 = new JButton("More Colours...");
			bt2.addActionListener(this);
			pn2.add(bt2);
			
			b2.add(pn2);

			jtf2 = new JTextField();
			b2.add(jtf2);
			
			if(sm != null)
			{
				fg.addListSelectionListener(sm);
			}
			
			jsp2 = new JScrollPane(fg);
			b2.add(jsp2);
			b2.add(Box.createVerticalStrut(10));
			
			b3 = Box.createVerticalBox();
			b3.add(Box.createHorizontalStrut(10));
			
			pn3 = new JPanel();
			pn3.setLayout(new FlowLayout(FlowLayout.CENTER));

			if(MyNotepad.f3 == false)
				lb3 = new JLabel("Select Caret Color:- ");
			else
				lb3 = new JLabel("Select Grid Color:- ");
			
			lb3.setFont(f);
			pn3.add(lb3);
			bt3 = new JButton("More Colours...");
			bt3.addActionListener(this);
			pn3.add(bt3);
			
			b3.add(pn3);
			
			jtf3 = new JTextField();
			b3.add(jtf3);
			
			if(sm != null)
			{
				cr.addListSelectionListener(sm);
			}
			
			jsp3 = new JScrollPane(cr);
			b3.add(jsp3);
			b3.add(Box.createVerticalStrut(10));
			
			mainBox1 = Box.createHorizontalBox();
			mainBox1.add(Box.createHorizontalStrut(10));
			mainBox1.add(b1);
			mainBox1.add(Box.createHorizontalStrut(10));
			mainBox1.add(b2);
			mainBox1.add(Box.createHorizontalStrut(10));
			add(mainBox1,BorderLayout.NORTH);
			
			mainBox2 = Box.createHorizontalBox();
			mainBox2.add(Box.createHorizontalStrut(150));
			mainBox2.add(b3);
			mainBox2.add(Box.createHorizontalStrut(150));
			add(mainBox2,BorderLayout.SOUTH);
		}

		public Color getBackgroundColor() {
			// TODO Auto-generated method stub
			if(f1 == true)
			{
				f1 = false;
				return c1;
			}
			else 
				return bg.getBackgroundColor();
		}

		public Color getForegroundColor() {
			// TODO Auto-generated method stub
			if(f2 == true)
			{
				f2 = false;
				return c2;
			}
			else
				return fg.getForegroundColor();
		}

		public Color getCaretColor() {
			// TODO Auto-generated method stub
			if(f3 == true)
			{
				f3 = false;
				return c3;
			}
			else
				return cr.getCaretColor();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			Object str = e.getSource();
			
			if(str == bt1)
			{
				c1 = JColorChooser.showDialog(this, "Select Background Color", getBackgroundColor());
				f1 = true;
				bg.setSelectedIndex(-1);
			}
			if(str == bt2)
			{
				c2 = JColorChooser.showDialog(this, "Select Foreground Color", getForegroundColor());
				f2 = true;
				fg.setSelectedIndex(-1);
			}
			if(str == bt3)
			{
				if(MyNotepad.f3 == false)
				{
					c3 = JColorChooser.showDialog(this, "Select Caret Color", getCaretColor());
					f3 = true;
					cr.setSelectedIndex(-1);
				}
				else
				{
					c3 = JColorChooser.showDialog(this, "Select Grid Color", getCaretColor());
					f3 = true;
					cr.setSelectedIndex(-1);
				}
			}
		}
	}
	
	static class Background extends JList
	{
		private final static String[] bc = {"black","BLACK","blue","BLUE","cyan","CYAN","DARK_GRAY","darkGray","gray","GRAY","green","GREEN","LIGHT_GRAY","lightGray","magenta","MAGENTA","orange","ORANGE","pink","PINK","red","RED","white","WHITE","yellow","YELLOW"};
		static int index = 22;
		
		Background()
		{
			super(bc);
			if(SelectColor.f1 == true)
				setSelectedIndex(-1);
			else
				setSelectedIndex(index);
			setVisibleRowCount(7);
		}
		
		Color getBackgroundColor()
		{
			Color c = null;
			if(MyNotepad.f2 == false)
				SelectColor.jtf1.setText((String)getSelectedValue());
			else
				SelectLineColor.jtf1.setText((String)getSelectedValue());
			index = this.getSelectedIndex();
			String name = (String)getSelectedValue();
			
			if(name.equals("black"))
				c = Color.black;
			else if(name.equals("BLACK"))
				c = Color.BLACK;
			else if(name.equals("blue"))
				c = Color.blue;
			else if(name.equals("BLUE"))
				c = Color.BLUE;
			else if(name.equals("cyan"))
				c = Color.cyan;
			else if(name.equals("CYAN"))
				c = Color.CYAN;
			else if(name.equals("DARK_GRAY"))
				c = Color.DARK_GRAY;
			else if(name.equals("darkGray"))
				c = Color.darkGray;
			else if(name.equals("gray"))
				c = Color.gray;
			else if(name.equals("GRAY"))
				c = Color.GRAY;
			else if(name.equals("green"))
				c = Color.green;
			else if(name.equals("GREEN"))
				c = Color.GREEN;
			else if(name.equals("LIGHT_GRAY"))
				c = Color.LIGHT_GRAY;
			else if(name.equals("lightGray"))
				c = Color.lightGray;
			else if(name.equals("magenta"))
				c = Color.magenta;
			else if(name.equals("MAGENTA"))
				c = Color.MAGENTA;
			else if(name.equals("orange"))
				c = Color.orange;
			else if(name.equals("ORANGE"))
				c = Color.ORANGE;
			else if(name.equals("pink"))
				c = Color.pink;
			else if(name.equals("PINK"))
				c = Color.PINK;
			else if(name.equals("red"))
				c = Color.red;
			else if(name.equals("RED"))
				c = Color.RED;
			else if(name.equals("white"))
				c = Color.white;
			else if(name.equals("WHITE"))
				c = Color.WHITE;
			else if(name.equals("yellow"))
				c = Color.yellow;
			else if(name.equals("YELLOW"))
				c = Color.YELLOW;
			
			return c;
		}
	}
    
    static class Foreground extends JList
    {
    	static final String[] fc = {"black","BLACK","blue","BLUE","cyan","CYAN","DARK_GRAY","darkGray","gray","GRAY","green","GREEN","LIGHT_GRAY","lightGray","magenta","MAGENTA","orange","ORANGE","pink","PINK","red","RED","white","WHITE","yellow","YELLOW"};
    	static int index = 0;
    	
    	Foreground()
    	{
    		super(fc);
    		if(SelectColor.f2 == true)
    			setSelectedIndex(-1);
    		else
    			setSelectedIndex(index);
			setVisibleRowCount(7);
    	}
    	
    	Color getForegroundColor()
    	{
    		Color c = null;
			if(MyNotepad.f2 == false)
				SelectColor.jtf2.setText((String)getSelectedValue());
			else
				SelectLineColor.jtf2.setText((String)getSelectedValue());
			index = this.getSelectedIndex();
			String name = (String)getSelectedValue();
    		
    		if(name.equals("black"))
				c = Color.black;
			else if(name.equals("BLACK"))
				c = Color.BLACK;
			else if(name.equals("blue"))
				c = Color.blue;
			else if(name.equals("BLUE"))
				c = Color.BLUE;
			else if(name.equals("cyan"))
				c = Color.cyan;
			else if(name.equals("CYAN"))
				c = Color.CYAN;
			else if(name.equals("DARK_GRAY"))
				c = Color.DARK_GRAY;
			else if(name.equals("darkGray"))
				c = Color.darkGray;
			else if(name.equals("gray"))
				c = Color.gray;
			else if(name.equals("GRAY"))
				c = Color.GRAY;
			else if(name.equals("green"))
				c = Color.green;
			else if(name.equals("GREEN"))
				c = Color.GREEN;
			else if(name.equals("LIGHT_GRAY"))
				c = Color.LIGHT_GRAY;
			else if(name.equals("lightGray"))
				c = Color.lightGray;
			else if(name.equals("magenta"))
				c = Color.magenta;
			else if(name.equals("MAGENTA"))
				c = Color.MAGENTA;
			else if(name.equals("orange"))
				c = Color.orange;
			else if(name.equals("ORANGE"))
				c = Color.ORANGE;
			else if(name.equals("pink"))
				c = Color.pink;
			else if(name.equals("PINK"))
				c = Color.PINK;
			else if(name.equals("red"))
				c = Color.red;
			else if(name.equals("RED"))
				c = Color.RED;
			else if(name.equals("white"))
				c = Color.white;
			else if(name.equals("WHITE"))
				c = Color.WHITE;
			else if(name.equals("yellow"))
				c = Color.yellow;
			else if(name.equals("YELLOW"))
				c = Color.YELLOW;
			
			return c;
    	}
    }
    
    static class Caret extends JList
     {
    	static final String[] cc = {"black","BLACK","blue","BLUE","cyan","CYAN","DARK_GRAY","darkGray","gray","GRAY","green","GREEN","LIGHT_GRAY","lightGray","magenta","MAGENTA","orange","ORANGE","pink","PINK","red","RED","white","WHITE","yellow","YELLOW"};
    	static int index = 0;

    	Caret()
    	{
    		super(cc);
    		if(SelectColor.f3 == true)
    			setSelectedIndex(-1);
    		else
    			setSelectedIndex(index);
    		setVisibleRowCount(7);
    	}
    	
    	Color getCaretColor()
    	{
    		Color c = null;
    		SelectColor.jtf3.setText((String)getSelectedValue());
			index = this.getSelectedIndex();
    		String name = (String)getSelectedValue();
    		
    		if(name.equals("black"))
				c = Color.black;
			else if(name.equals("BLACK"))
				c = Color.BLACK;
			else if(name.equals("blue"))
				c = Color.blue;
			else if(name.equals("BLUE"))
				c = Color.BLUE;
			else if(name.equals("cyan"))
				c = Color.cyan;
			else if(name.equals("CYAN"))
				c = Color.CYAN;
			else if(name.equals("DARK_GRAY"))
				c = Color.DARK_GRAY;
			else if(name.equals("darkGray"))
				c = Color.darkGray;
			else if(name.equals("gray"))
				c = Color.gray;
			else if(name.equals("GRAY"))
				c = Color.GRAY;
			else if(name.equals("green"))
				c = Color.green;
			else if(name.equals("GREEN"))
				c = Color.GREEN;
			else if(name.equals("LIGHT_GRAY"))
				c = Color.LIGHT_GRAY;
			else if(name.equals("lightGray"))
				c = Color.lightGray;
			else if(name.equals("magenta"))
				c = Color.magenta;
			else if(name.equals("MAGENTA"))
				c = Color.MAGENTA;
			else if(name.equals("orange"))
				c = Color.orange;
			else if(name.equals("ORANGE"))
				c = Color.ORANGE;
			else if(name.equals("pink"))
				c = Color.pink;
			else if(name.equals("PINK"))
				c = Color.PINK;
			else if(name.equals("red"))
				c = Color.red;
			else if(name.equals("RED"))
				c = Color.RED;
			else if(name.equals("white"))
				c = Color.white;
			else if(name.equals("WHITE"))
				c = Color.WHITE;
			else if(name.equals("yellow"))
				c = Color.yellow;
			else if(name.equals("YELLOW"))
				c = Color.YELLOW;
			
			return c;
    	}
    }
 
}