import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUp extends java.applet.Applet implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	//close button
	private Button Close;
	private Font fnt12B = new Font("Arial", Font.BOLD, 12);
	//message that it will print
	private String message;
	
	public void paint(Graphics p)
	{
		p.drawString("AAAHH", 75, 90);
	}//end paint
	
	public void init(String notify)
	{
		message = notify;
		
		setSize(400, 150);
		setLayout(null);
		setBackground(Color.gray);
		
		Close = new Button("Close");
		Close.setSize(100, 25); //size of button
		Close.setLocation(100,600); //location of button
		Close.setFont(fnt12B); //font of button
		Close.setBackground(Color.darkGray); //color of button
		Close.setVisible(true);
		Close.addActionListener(this);
		add(Close);
		
		
	}//end init
	
	public void actionPerformed(ActionEvent t)
	{
		if(t.getSource() == Close)
		{
			destroy();
		}
	}
}//end pop
