package ExNotepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableColumnModel;

class ResizeTable extends JFrame implements ActionListener,ItemListener
{
	Container cn;
	JLabel jl;
	String str[] = {"5","10","20","30","40","50","60","70","80","90","100","150","200","250","300","350","400","450","500","550","600","650","700","750","800","850","900","950","1000","Other"};
	JComboBox jcb;
	JTextField jtf;
	JButton bt1,bt2,bt3;
	JPanel p1;
	int m=5;

	public ResizeTable(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		cn = getContentPane();
		cn.setLayout(null);
		
		jl = new JLabel("Enter Width of Selected Column:- ");
		cn.add(jl);
		jl.setBounds(100,20,300,50);
		
		jcb = new JComboBox(str);
		cn.add(jcb);
		jcb.setBounds(320, 38, 70, 20);
		jcb.addItemListener(this);
		
		jtf = new JTextField();
		jtf.setSize(100,30);
		jtf.setLocation(100,100);
		jtf.setVisible(false);
		cn.add(jtf);
		
		bt3 = new JButton("OK");
		bt3.setBounds(200,100,80,30);
		bt3.setVisible(false);
		cn.add(bt3);
		
		bt1 = new JButton("OK");
		bt2 = new JButton("Cancel");
		cn.add(bt1);
		cn.add(bt2);
		bt1.setBounds(150, 220, 100, 30);
		bt2.setBounds(280, 220, 100, 30);

		bt1.addActionListener(this);
		bt2.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Object obj = e.getSource();
		
		if(obj == bt1)
		{
			setVisible(false);
		}
		else if(obj == bt2)
		{
			TableColumnModel tcm = TableSize.jt.getColumnModel();
			for(int i=0;i<TableSize.jt.getColumnCount();i++)
				tcm.getColumn(i).setPreferredWidth(100);
			setVisible(false);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == jcb)
		{
			TableColumnModel tcm = TableSize.jt.getColumnModel();
			int c[] = TableSize.jt.getSelectedColumns();
			if(jcb.getSelectedItem() == "Other")
			{
				jtf.setVisible(true);
				bt3.setVisible(true);
				bt3.addActionListener(new ActionListener()
						{
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								m = Integer.parseInt((String)jtf.getText());
								for(int i=0;i<c.length;i++)
								{
									tcm.getColumn(c[i]).setPreferredWidth(m);
									jtf.setVisible(false);
									bt3.setVisible(false);
								}
							}
						});
			}
			else
			   m = Integer.parseInt((String)jcb.getSelectedItem());
			
			for(int i=0;i<c.length;i++)
			{
				tcm.getColumn(c[i]).setPreferredWidth(m);
			}
		}
	}

}

