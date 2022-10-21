package ExNotepad;

import java.awt.*;
import java.awt.event.*;

class FontTracker implements ActionListener
{
	FontChooser fcr;
	Font f;
	
	public FontTracker(FontChooser fcr)
	{
		this.fcr = fcr;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		f = fcr.getSelectedFont();
	}
	
	public Font getSelectedFont()
	{
		return f;
	}
}
