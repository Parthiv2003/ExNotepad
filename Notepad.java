package ExNotepad;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;
import javax.swing.undo.UndoManager;
import javax.swing.event.*;


public class Notepad
{
	public static void main(String args[])
	{
		MyNotepad mn = new MyNotepad("My Notepad");
		mn.setVisible(true);
		mn.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mn.setResizable(true);
	}
}

class MyNotepad extends JFrame implements ActionListener  {

	static boolean f1 = false,f2 = false,f3 = false;
	static String fname,folder;
	Container c;
	int a=0,b;
	JMenuBar jmb;
	JMenu fi,ed,in,fc,he,color;
	JMenuItem ne,op,sa,sas,pr,ex;
	JMenuItem cut,copy,paste,delete,undo,redo,find,replace,selectall,font,textColor,columnColor;
	JMenuItem table,pic,shape,chart;
	static JTextArea jta;
	static JTextArea lines;
	JScrollPane jsp;
	static String s = null;
	static String content;
	
	
	MyNotepad(String string)
	{
		super(string);
		c = getContentPane();
		
		jta = new JTextArea();
		jsp = new JScrollPane();
		
		lines = new JTextArea("1");
		lines.setEditable(false);
		
		jta.getDocument().addDocumentListener(new DocumentListener()
				{
					public String getText()
					{
						int caretPosition = jta.getDocument().getLength();
						Element root = jta.getDocument().getDefaultRootElement();
						String text = "1  " + System.getProperty("line.separator");
						for(int i=2;i<root.getElementIndex(caretPosition)+2;i++)
						{
							text += i + "  " + System.getProperty("line.separator");
						}
						return text;
					}
					
					@Override
					public void insertUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
						lines.setText(getText());
					}

					@Override
					public void removeUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
						lines.setText(getText());
					}

					@Override
					public void changedUpdate(DocumentEvent e) {
						// TODO Auto-generated method stub
						lines.setText(getText());
					}
				});
		jsp.getViewport().add(jta);
		jsp.setRowHeaderView(lines);
		lines.setBackground(Color.GRAY);
		add(jsp);
			
		jmb = new JMenuBar();
		setJMenuBar(jmb);
		
		fi = new JMenu("File");
		ed = new JMenu("Edit");
		in = new JMenu("Insert");
		fc = new JMenu("F&C");
		he = new JMenu("Help");
		
		jmb.add(fi);
		jmb.add(ed);
		jmb.add(in);
		jmb.add(fc);
		jmb.add(he);
		
		ne = new JMenuItem("New");
		op = new JMenuItem("Open");
		sa = new JMenuItem("Save");
		sas = new JMenuItem("Save As");
		pr = new JMenuItem("Print");
		ex = new JMenuItem("Exit");
	
		ne.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK,true));
		op.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK,true));
		sa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK,true));
		sas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.SHIFT_MASK,true));
		pr.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK,true));
		ex.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK,true)); 
		
		fi.add(ne);
		fi.add(op);
		fi.addSeparator();
		fi.add(sa);
		fi.add(sas);
		fi.add(pr);
		fi.addSeparator();
		fi.add(ex);
		
		ne.addActionListener(this);
		op.addActionListener(this);
		sa.addActionListener(this);
		sas.addActionListener(this);
		pr.addActionListener(this);
		ex.addActionListener(this);
		
		
//		Edit section
		
		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		delete = new JMenuItem("Delete");
		undo = new JMenuItem("Undo");
		redo = new JMenuItem("Redo");
		find = new JMenuItem("Find");
		replace = new JMenuItem("Replace");
		selectall = new JMenuItem("Select All");
		
		UndoManager um = new UndoManager();
		
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK,true));
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK,true));
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK,true));
		delete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0,true));
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK,true));
		redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,InputEvent.CTRL_MASK,true)); 
		find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK,true));
		replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK,true));
		selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK,true));

		ed.add(cut);
		ed.add(copy);
		ed.add(paste);
		ed.add(delete);
		ed.addSeparator();
		ed.add(undo);
		ed.add(redo);
		ed.addSeparator();
		ed.add(find);
		ed.add(replace);
		ed.addSeparator();
		ed.add(selectall);
		
		cut.addActionListener(this);
		copy.addActionListener(this);
		paste.addActionListener(this);
		delete.addActionListener(this);
		undo.addActionListener(this);
		redo.addActionListener(this);
		find.addActionListener(this);
		replace.addActionListener(this);
		selectall.addActionListener(this);
		
//		Font-Color Section
		
		font = new JMenuItem("Font");
		color = new JMenu("Color");
	
		font.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK,true));
		color.setMnemonic(KeyEvent.VK_Q);
		fc.add(font);
		fc.add(color);
		
		font.addActionListener(this);
		color.addActionListener(this);
		
		textColor = new JMenuItem("Text-Color");
		columnColor = new JMenuItem("Column-Color");
		
		color.add(textColor);
		color.add(columnColor);
		
		textColor.addActionListener(this);
		columnColor.addActionListener(this);
		
		
//		Insert Section
		
		table = new JMenuItem("Table");
		pic = new JMenuItem("Picture");
		shape = new JMenuItem("Shape");
		chart = new JMenuItem("Chart");
	
		table.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.ALT_MASK,true));
		pic.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.ALT_MASK,true));
		shape.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.ALT_MASK,true));
		chart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.ALT_MASK,true));

		in.add(table);
		in.add(pic);
		in.add(shape);
		in.add(chart);
		
		table.addActionListener(this);
		pic.addActionListener(this);
		shape.addActionListener(this);
		chart.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		s = ae.getActionCommand();
		
		if(s.equals("New"))
		{
			JDialog jd = new JDialog(JOptionPane.getFrameForComponent(this),"WARNING");
			jd.setVisible(true);
			jd.setBounds(700, 425, 600, 200);
			content = jta.getText();
			CloseFile cf = new CloseFile(jd,"New",jta,this);	
		}
		
		if(s.equals("Open"))
		{
			JDialog jd = new JDialog(this,"WARNING");
			jd.setVisible(true);
			jd.setLocation(700,425);
			jd.setSize(600,200);
			content = jta.getText();
			CloseFile cf = new CloseFile(jd,"",jta,this);
		}
		
		if(s.equals("Save"))
		{
			if(f1 == false || fname == "Your New File")
			{
				try
				{
					FileDialog fd = new FileDialog(this,"SAVE A FILE",FileDialog.SAVE);
					fd.setVisible(true);
					
					folder = fd.getDirectory();
					fname = fd.getFile();
					
					String data = jta.getText();
					FileWriter fw = new FileWriter(folder + "//" +fname);
					
					fw.write(data);
					fw.close();
					
					this.setTitle(fname);
					f1 = true;
				}
				catch(Exception e)
				{
				}
			}
			else
			{
				try
				{
					
					String data = jta.getText();
					
					FileWriter fw = new FileWriter(folder + "//" +fname);
					
					fw.write(data);
					fw.close();
					
					this.setTitle(fname);
				}
				catch(Exception e)
				{
				}
			}
		}
		
		if(s.equals("Save As"))
		{
			try
			{
				FileDialog fd = new FileDialog(this,"SAVE AS FILE",FileDialog.SAVE);
				fd.setVisible(true);
				
				folder = fd.getDirectory();
				fname = fd.getFile();
			
				String data = jta.getText();
				FileWriter fw = new FileWriter(folder + "//" +fname);
				
				fw.write(data);
				fw.close();
				this.setTitle(fname);
			}
			catch(Exception e)
			{
			}
		}
		
		if(s.equals("Print"))
		{
			
		}
		
		if(s.equals("Exit"))
		{			
			JDialog jd = new JDialog(this,"WARNING");
			jd.setVisible(true);
			jd.setLocation(700,425);
			jd.setSize(600,200);
			content = jta.getText();
			CloseFile cf = new CloseFile(jd,"",jta,this);
		}
		
		if(s.equals("Cut"))
		{
			jta.cut();
		}
		
		if(s.equals("Copy"))
		{
			jta.copy();
		}
		
		if(s.equals("Paste"))
		{
			jta.paste();
		}
		
		if(s.equals("Copy"))
		{
			jta.copy();
		}
		
		if(s.equals("Delete"))
		{
			jta.replaceSelection("");
		}
		
		if(s.equals("Undo"))
		{
		
		}
		
		if(s.equals("Redo"))
		{
			
		}
		
		if(s.equals("Find"))
		{
			Find fd = new Find("Find",jta);
			fd.setVisible(true);
			fd.setLocation(700,425);
			fd.setSize(500,200);
			fd.setResizable(false);
		}
		
		if(s.equals("Replace"))
		{
			Replace rc = new Replace("Replace",jta);
			rc.setVisible(true);
			rc.setLocation(700,425);
			rc.setSize(600,300);
			rc.setResizable(false);
		}
		
		if(s.equals("Select All"))
		{
			jta.selectAll();
		}
		
		if(s.equals("Font"))
		{
			try 
			{
				FontChooser fc = new FontChooser();
				Font fn1 = fc.showDialog(this, "Select Font");
				jta.setFont(fn1);
				lines.setFont(fn1);
			}
			catch(Exception e)
			{
			}
		}
		
		if(s.equals("Text-Color"))
		{
			try
			{
				f2 = false;
				f3 = false;
				TextColor.showDialogBox(this,jta,lines,TableSize.jt,"Select Text Color");
			}
			catch(Exception e)
			{
			}
		}
		
		if(s.equals("Column-Color"))
		{
			try
			{
				f2 = true;
				f3 = false;
				TextColor.showDialogBox(this,jta,lines,TableSize.jt,"Select Column Color");
			}
			catch(Exception e)
			{
			}
		}
		
		if(s.equals("Table"))
		{
			try 
			{
				TableSize.showDialog(this,"Select table Size");
			}
			catch (Exception e)
			{
			}
		}
	}
}
