package ExNotepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GetDimension implements ActionListener
{
	TableSize ts;
	int r=5,c=5;
	
	public GetDimension(TableSize ts)
	{
		this.ts = ts;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		r = ts.getRow();
		c = ts.getColumn();
	}
	
	public int getRow()
	{
		return r;
	}
	
	public int getColumn()
	{
		return c;
	}
}
