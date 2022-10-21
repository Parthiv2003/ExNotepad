package ExNotepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

class TableSize extends JComponent
{
	static JTable jt = null;
	SelectSize ss;
	static boolean f = false,flag = false,f1 = false;

	public TableSize()
	{
		setLayout(new BorderLayout());
		
		CreateTable ct = new CreateTable();
		ss = new SelectSize(ct);
		
		add(ss,BorderLayout.CENTER);
	}
	
	public static void showDialog(MyNotepad mnp, String string) throws Exception {
		// TODO Auto-generated method stub
		TableSize ts = new TableSize();
		GetDimension gd = new GetDimension(ts);

		JDialog jd = createDialog(mnp,string,true,ts,gd,null);
		jd.addWindowListener(new TableDialog.Closer());
		jd.addComponentListener(new TableDialog.DisposeOnClose());
		jd.setVisible(true);
			
		int r = gd.getRow();
		int c = gd.getColumn();
		
	}
	
	public int getRow()
	{
		return ss.getRow();
	}
	
	public int getColumn()
	{
		return ss.getColumn();
	}
	
	private static JDialog createDialog(MyNotepad mnp, String string, boolean b, TableSize ts, ActionListener ok,ActionListener cancel) throws Exception {
		// TODO Auto-generated method stub
		return new TableDialog(mnp,string,b,ts,ok,cancel);
	}

	class CreateTable implements ChangeListener,ActionListener
	{
		int r,c,lc;
		DefaultTableModel dtm;
		JPopupMenu jpm;
		JMenuItem ar,ac,dr,dc,del,cc,fs,rs;

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			r = TableSize.this.getRow();
			c = TableSize.this.getColumn();
			
			if(f == false)
			{
				f = true;
			}
			else
			{
				jt.setVisible(false);
				MyNotepad.jta.remove(jt);
			}
			
			dtm = new DefaultTableModel(r,c);
			jt = new JTable(dtm);
			jt.setRowHeight(MyNotepad.jta.getFontMetrics(MyNotepad.jta.getFont()).getHeight());
			MyNotepad.jta.add(jt);
			
			jpm = new JPopupMenu();					
			ar = new JMenuItem("Add Row");
			ac = new JMenuItem("Add Column");
			dr = new JMenuItem("Delete Row");
			dc = new JMenuItem("Delete Column");
			del = new JMenuItem("Delete");
			cc = new JMenuItem("Color Combination");
			fs = new JMenuItem("Font Size");
			rs = new JMenuItem("Resize");
							
			jpm.add(ar);
			jpm.add(ac);
			jpm.addSeparator();
			jpm.add(dr);
			jpm.add(dc);
			jpm.add(del);
			jpm.addSeparator();
			jpm.add(cc);
			jpm.add(fs);
			jpm.add(rs);
			jpm.add(rs);
			
			ar.addActionListener(this);
			ac.addActionListener(this);
			dr.addActionListener(this);
			dc.addActionListener(this);
			del.addActionListener(this);
			cc.addActionListener(this);
			fs.addActionListener(this);
			rs.addActionListener(this);
			
			jt.setColumnSelectionAllowed(true);
			jt.setComponentPopupMenu(jpm);
			lc = MyNotepad.jta.getLineCount();
			jt.setBounds(0,(MyNotepad.jta.getLineCount()-1)*(jt.getRowHeight()),c*250,r*(jt.getRowHeight()));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JMenuItem jmi = (JMenuItem)e.getSource();
			
			TableSize ts = new TableSize();
			if(jmi == ar)
			{
				dtm.addRow(new String[0]);
				r++;
				jt.setBounds(0, (lc-1)*(jt.getRowHeight()),c*250,r*(jt.getRowHeight()));
			}
			else if(jmi == ac)
			{
				dtm.addColumn(new String[0]);
				c++;
				jt.setBounds(0, (lc-1)*(jt.getRowHeight()),c*250,r*(jt.getRowHeight()));
			}
			else if(jmi == dr)
			{
				int j=0;
				int arr[] = jt.getSelectedRows();
				for(int i=0;i<arr.length;i++)
				{	
					dtm.removeRow(arr[i]-j);
					j++;
					r--;
					jt.setBounds(0, (lc-1)*(jt.getRowHeight()),c*250,r*(jt.getRowHeight()));
				}
			}
			else if(jmi == dc)
			{
				int j=0;
				int arr[] = jt.getSelectedColumns();
				for(int i=0;i<arr.length;i++)
				{	
					jt.removeColumn(jt.getColumnModel().getColumn(arr[i]-j));
					j++;
					c--;
					jt.setBounds(0, (lc-1)*(jt.getRowHeight()),c*250,r*(jt.getRowHeight()));
				}
			}
			else if(jmi == del)
			{
				jt.setVisible(false);
				MyNotepad.jta.remove(jt);
			}
			else if(jmi == cc)
			{
				MyNotepad.f3 = true;
				try 
				{
					TextColor.showDialogBox(ts,MyNotepad.jta,MyNotepad.lines,jt,"Select Column Color");
				}
				catch (Exception e1)
				{
				}
			}
			else if(jmi == fs)
			{
				try 
				{
					flag = true;
					FontChooser fc = new FontChooser();
					Font fn1 = fc.showDialog(ts, "Select Font");
					jt.setFont(fn1);
					flag = false;
				}
				catch(Exception e1)
				{		
					
				}
			}
			else if(jmi == rs)
			{
				ResizeTable rt = new ResizeTable("Resize Table");
				rt.setSize(500,300);
				rt.setLocationRelativeTo(ts);
				rt.setVisible(true);
				rt.setResizable(false);
			}
		}
	}
          
	static class SelectSize extends JPanel
	{
		JLabel l1,l2;
		Font f;
		JPanel p1,p2;

		Spin sp1 = new Spin();
		Spin sp2 = new Spin();
		
		public SelectSize(ChangeListener cl)
		{
			setLayout(new BorderLayout());
			
			f = new Font("Arial",Font.PLAIN,20);
			
			l1 = new JLabel("Number of Rows:- ");
			l1.setFont(f);
			p1 = new JPanel();
			p1.setLayout(new FlowLayout(FlowLayout.CENTER));
			p1.add(l1);
			if(cl != null)
			{
				sp1.addChangeListener(cl);
			}
			p1.add(sp1);
			add(p1,BorderLayout.NORTH);
			
			l2 = new JLabel("Number of Columns:- ");
			l2.setFont(f);
			p2 = new JPanel();
			p2.setLayout(new FlowLayout(FlowLayout.CENTER));
			p2.add(l2);
			if(cl != null)
			{
				sp2.addChangeListener(cl);
			}
			p2.add(sp2);
			add(p2,BorderLayout.CENTER);
		}
		
		public int getRow()
		{
			return sp1.getRow();
		}
		
		public int getColumn()
		{
			return sp2.getColumn();
		}		
	}
	
	static class Spin extends JSpinner
	{
		public Spin()
		{
			super(new SpinnerNumberModel(5,1,50,1));
		}
		
		public int getRow() {
			// TODO Auto-generated method stub
			return (int) getValue();
		}

		public int getColumn() {
			// TODO Auto-generated method stub
			return (int) getValue();
		}
	}

}

