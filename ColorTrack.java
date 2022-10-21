package ExNotepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ColorTrack implements ActionListener
{
	TextColor tcs;
	Color c1,c2,c3;
	
	public ColorTrack(TextColor tcs)
	{
		this.tcs = tcs;
	}
	
	public Color caretColor() {
		// TODO Auto-generated method stub
		return c3;
	}

	public Color getForegroundColor() {
		// TODO Auto-generated method stub
		return c2;
	}

	public Color getBackgroundColor() {
		// TODO Auto-generated method stub
		return c1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// TODO Auto-generated method stub
		if(MyNotepad.f2 == false)
		{
			c1 = tcs.getBackgroundColor();
			c2 = tcs.getForegroundColor();
			c3 = tcs.getCaretColor();
		}
		else
		{
			c1 = tcs.getBackgroundColor();
			c2 = tcs.getForegroundColor();
		}
	}

}
