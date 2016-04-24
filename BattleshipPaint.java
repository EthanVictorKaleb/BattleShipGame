//this gives the game graphics
import java.applet.Applet; 
import java.applet.AudioClip;
import java.awt.*; 
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.net.*;
import java.io.*;
import java.awt.event.*; // allows buttons

public class BattleshipPaint extends java.applet.Applet implements ActionListener, MouseListener, ItemListener
{
	private Font fnt32B = new Font("Arial", Font.BOLD, 32); //font
	private Font fnt12B = new Font("Arial", Font.BOLD, 12);
	private Font fnt20B = new Font("Arial", Font.BOLD, 20);
	
	AudioClip music;
	private ButtonGroup Attack;
	private Button b_attack;
	private TextField tf_cn;
	private TextField tf_cl;
	private TextField tf_ip;
	private ButtonGroup Starting;
	private Button b_start;
	private ButtonGroup Players;
	private Button Player_1;
	private Button Player_2;
	private ButtonGroup Random;
	private Button Randomize;
	private Image hit;
	private Image rp;
	private Image wp;
	private ButtonGroup Connectg;
	private Button Connect;
	private Button p2;
	//the locations of the ships
	private Point P_AC = new Point(52, 73);
	private Point P_BS = new Point(110, 73);
	private Point P_D = new Point(179, 73);
	private Point S = new Point(262, 73);
	private Point PP = new Point(320, 73);
	//these allow us to use the other classes
	private Game Game = new Game();
	private OSocklocal OSocklocal = new OSocklocal();
	//to tell if you are client or server
	private boolean isPlayerOne = true;
	//if they have selected a player
	private boolean selected = true;
	public boolean isHit;
	//for use with the connect functionality
	public InetAddress ip;
	//to tell if it is your turn
	private boolean isTurn = isPlayerOne;
	//array of red pins
	Point[] redpins = new Point[100];
	Point[] whitepins = new Point[100];
	int pin_numw = 0;
	int pin_numr = 0;
	
	public void paint (Graphics g)
	{
		
		//grids
		g.setFont(fnt32B);
		g.setColor(Color.white);
		
		Image background;
		background = getImage(getCodeBase(), "game.png");//game background
		g.drawImage(background,0,0,1400,800, this);
		
		Image water;
		water = getImage(getCodeBase(), "water.gif");//gif
		//g.drawImage(water, 130, 65, 550, 500, this); //size and location of image
		//g.drawImage(water, 130 + 620, 65, 550, 500, this); //size and location of image
		//grid1
		//vertical lines in grid1
		g.fillRect(130, 10, 3, 550);
		g.fillRect(185, 10, 3, 550);
		g.fillRect(240, 10, 3, 550);
		g.fillRect(295, 10, 3, 550);
		g.fillRect(350, 10, 3, 550);
		g.fillRect(405, 10, 3, 550);
		g.fillRect(460, 10, 3, 550);
		g.fillRect(515, 10, 3, 550);
		g.fillRect(570, 10, 3, 550);
		g.fillRect(625, 10, 3, 550);
		//sides of grid1
		g.fillRect(680, 10, 5, 550);
		g.fillRect(75, 10, 610, 5);
		g.fillRect(75, 10, 5, 550);
		g.fillRect(75, 560, 610, 5);
		//horizantal lines in grid1
		g.fillRect(75, 60, 610, 3);
		g.fillRect(75, 110, 610, 3);
		g.fillRect(75, 160, 610, 3);
		g.fillRect(75, 210, 610, 3);
		g.fillRect(75, 260, 610, 3);
		g.fillRect(75, 310, 610, 3);
		g.fillRect(75, 360, 610, 3);
		g.fillRect(75, 410, 610, 3);
		g.fillRect(75, 460, 610, 3);
		g.fillRect(75, 510, 610, 3);
		//thicker lines horizantal
		g.fillRect(75, 59, 610, 5);
		g.fillRect(75, 109, 55, 5);
		g.fillRect(75, 159, 55, 5);
		g.fillRect(75, 209, 55, 5);
		g.fillRect(75, 259, 55, 5);
		g.fillRect(75, 309, 55, 5);
		g.fillRect(75, 359, 55, 5);
		g.fillRect(75, 409, 55, 5);
		g.fillRect(75, 459, 55, 5);
		g.fillRect(75, 509, 55, 5);
		//thicker lines vertical
		g.fillRect(129, 10, 5, 550);
		g.fillRect(184, 10, 5, 54);
		g.fillRect(239, 10, 5, 54);
		g.fillRect(294, 10, 5, 54);
		g.fillRect(349, 10, 5, 54);
		g.fillRect(404, 10, 5, 54);
		g.fillRect(459, 10, 5, 54);
		g.fillRect(514, 10, 5, 54);
		g.fillRect(569, 10, 5, 54);
		g.fillRect(624, 10, 5, 54);
		//numbers grid 1
		g.drawString("1", 95, 100);
		g.drawString("2", 95, 150);
		g.drawString("3", 95, 200);
		g.drawString("4", 95, 250);
		g.drawString("5", 95, 300);
		g.drawString("6", 95, 350);
		g.drawString("7", 95, 400);
		g.drawString("8", 95, 450);
		g.drawString("9", 95, 500);
		g.drawString("10", 85, 550);
		//alphabet grid 1
		g.drawString("A", 147, 48);
		g.drawString("B", 203, 48);
		g.drawString("C", 258, 48);
		g.drawString("D", 313, 48);
		g.drawString("E", 370, 48);
		g.drawString("F", 425, 48);
		g.drawString("G", 476, 48);
		g.drawString("H", 533, 48);
		g.drawString("I", 595, 48);
		g.drawString("J", 645, 48);
	
		//grid2
		//vertical lines in grid2
		g.fillRect(130 + 620, 10, 3, 550);
		g.fillRect(185 + 620, 10, 3, 550);
		g.fillRect(240 + 620, 10, 3, 550);
		g.fillRect(295 + 620, 10, 3, 550);
		g.fillRect(350 + 620, 10, 3, 550);
		g.fillRect(405 + 620, 10, 3, 550);
		g.fillRect(460 + 620, 10, 3, 550);
		g.fillRect(515 + 620, 10, 3, 550);
		g.fillRect(570 + 620, 10, 3, 550);
		g.fillRect(625 + 620, 10, 3, 550);
		//sides of grid2
		g.fillRect(680 + 620, 10, 5, 550);
		g.fillRect(75 + 620, 10, 610, 5);
		g.fillRect(75 + 620, 10, 5, 550);
		g.fillRect(75 + 620, 560, 610, 5);
		
		//horizantal lines in grid2
		g.fillRect(75 + 620, 60, 610, 3);
		g.fillRect(75 + 620, 110, 610, 3);
		g.fillRect(75 + 620, 160, 610, 3);
		g.fillRect(75 + 620, 210, 610, 3);
		g.fillRect(75 + 620, 260, 610, 3);
		g.fillRect(75 + 620, 310, 610, 3);
		g.fillRect(75 + 620, 360, 610, 3);
		g.fillRect(75 + 620, 410, 610, 3);
		g.fillRect(75 + 620, 460, 610, 3);
		g.fillRect(75 + 620, 510, 610, 3);
		
		//thicker lines horizantal
		g.fillRect(75 + 620, 59, 610, 5);
		g.fillRect(75 + 620, 109, 55, 5);
		g.fillRect(75 + 620, 159, 55, 5);
		g.fillRect(75 + 620, 209, 55, 5);
		g.fillRect(75 + 620, 259, 55, 5);
		g.fillRect(75 + 620, 309, 55, 5);
		g.fillRect(75 + 620, 359, 55, 5);
		g.fillRect(75 + 620, 409, 55, 5);
		g.fillRect(75 + 620, 459, 55, 5);
		g.fillRect(75 + 620, 509, 55, 5);
		
		//thicker lines vertical
		g.fillRect(129 + 620, 10, 5, 550);
		g.fillRect(184 + 620, 10, 5, 54);
		g.fillRect(239 + 620, 10, 5, 54);
		g.fillRect(294 + 620, 10, 5, 54);
		g.fillRect(349 + 620, 10, 5, 54);
		g.fillRect(404 + 620, 10, 5, 54);
		g.fillRect(459 + 620, 10, 5, 54);
		g.fillRect(514 + 620, 10, 5, 54);
		g.fillRect(569 + 620, 10, 5, 54);
		g.fillRect(624 + 620, 10, 5, 54);
		//numbers grid 2
		g.drawString("1", 95 + 620, 100);
		g.drawString("2", 95 + 620, 150);
		g.drawString("3", 95 + 620, 200);
		g.drawString("4", 95 + 620, 250);
		g.drawString("5", 95 + 620, 300);
		g.drawString("6", 95 + 620, 350);
		g.drawString("7", 95 + 620, 400);
		g.drawString("8", 95 + 620, 450);
		g.drawString("9", 95 + 620, 500);
		g.drawString("10", 85 + 620, 550);
		
		//alphabet grid 2
		g.drawString("A", 147 + 620, 48);
		g.drawString("B", 203 + 620, 48);
		g.drawString("C", 258 + 620, 48);
		g.drawString("D", 313 + 620, 48);
		g.drawString("E", 370 + 620, 48);
		g.drawString("F", 425 + 620, 48);
		g.drawString("G", 476 + 620, 48);
		g.drawString("H", 533 + 620, 48);
		g.drawString("I", 595 + 620, 48);
		g.drawString("J", 645 + 620, 48);
		
		//fill ins
		g.setColor(Color.cyan);
		g.fillRect(80, 15, 50, 45);
		g.fillRect(700, 15, 50, 45);
		
		//words next to textfields
		g.setColor(Color.black);
		g.setFont(fnt20B);
		g.drawString("Number Coordinates:", 490, 665);
		g.drawString("Letter Coordinates:", 510, 615);
		
		//pieces
		//the group logo
		Image Grouplogo;
		Grouplogo = getImage(getCodeBase(), "Company logo.png");
		g.drawImage(Grouplogo, 900, 500 ,600 , 300, this);
		Image Aircraft;
		Aircraft = getImage(getCodeBase(), "AirCraft.png");
		g.drawImage(Aircraft, P_AC.x, P_AC.y, 240, 250, this);
		Image Battleship;
		Battleship = getImage(getCodeBase(), "BattleShip.png");
		g.drawImage(Battleship, P_BS.x, P_BS.y, 230, 230, this);
		Image Destroyer;
		Destroyer = getImage(getCodeBase(), "Destroyer.png");
		g.drawImage(Destroyer, P_D.x, P_D.y, 210, 210, this);
		Image Submarine;
		Submarine = getImage(getCodeBase(), "Submarine.png");
		g.drawImage(Submarine, S.x, S.y, 130, 130, this);
		Image Patrol;
		Patrol = getImage(getCodeBase(), "Patrol.png");
		g.drawImage(Patrol, PP.x, PP.y, 130, 130, this);
		Image Redpin;
		rp = getImage(getCodeBase(), "RedPin.png");
		Image Whitepin;
		wp = getImage(getCodeBase(), "WhitePin.png");
		//draw red pins
		for(int i = 0; i < pin_numr; i++)
		{
			g.drawImage(rp, redpins[i].x, redpins[i].y, 100, 100, this);
		}//end for, redpins
		//draw white pins
		for(int i = 0; i < pin_numw; i++)
		{
			g.drawImage(wp, whitepins[i].x, whitepins[i].y, 100, 100, this);
		}
		
		Image Hit;
		hit = getImage(getCodeBase(), "HIT.png");
		if(isHit)
		{
			g.drawImage(hit, 230, 50, 900, 600, this);
		}
		
	}//ends paint method

	public void init()
	{
		setSize(1400, 900);
		setLayout(null);
		setBackground(Color.gray);
	    
		//start button
		Starting = new ButtonGroup();
		b_start = new Button("Start"); //creates button
		b_start.setSize(100, 50); //size of button
		b_start.setLocation(100, 700); //location of button
		b_start.setFont(fnt32B); //font of button
		b_start.setBackground(Color.red); //color of button
		b_start.addActionListener(this);
		b_start.setVisible(false);
		add(b_start);
		
		//attack button
		Attack = new ButtonGroup();
		b_attack = new Button("Fire!"); //creates button
		b_attack.setSize(100,20); //size of button
		b_attack.setLocation(700,700); //location of button
		b_attack.setFont(fnt12B); //font of button
		b_attack.setBackground(Color.blue); //color of button
		b_attack.addActionListener(this);
		b_attack.setVisible(false);
		add(b_attack);
		/*
		JButton next1 = new JButton(new ImageIcon("Firenorm.png"));
		next1.setLocation(500, 500);
		next1.setSize(100,100);
		next1.setVisible(true);
		add(next1);
		*/
		//radio buttons
		Players = new ButtonGroup();
		Player_1 = new Button("Player 1");
		Player_1.setSize(100, 25); //size of checkbox
		Player_1.setLocation(100,600); //location of checkbox
		Player_1.setFont(fnt12B); //font of checkbox
		Player_1.setBackground(Color.yellow); //color of checkbox
		Player_1.setVisible(true);
		Player_1.addActionListener(this);
		add(Player_1);
		
		Player_2 = new Button("Player 2");
		Player_2.setSize(100, 25); //size of checkbox
		Player_2.setLocation(100,650); //location of checkbox
		Player_2.setFont(fnt12B); //font of checkbox
		Player_2.setBackground(Color.red); //color of checkbox
		Player_2.setVisible(true);
		Player_2.addActionListener(this);
		add(Player_2);
		
		//textfields
		tf_cl = new TextField("");
		tf_cl.setSize(50,20);//size of textfield
		tf_cl.setLocation(700,600);//location of textfield
		tf_cl.setFont(fnt12B);//font of textfield
		tf_cl.setBackground(Color.yellow); //color of textfield
		tf_cl.setVisible(true);
		add(tf_cl);
		
		tf_cn = new TextField("");
		tf_cn.setSize(50,20);//size of textfield
		tf_cn.setLocation(700,650);//location of textfield
		tf_cn.setFont(fnt12B);//font of textfield
		tf_cn.setBackground(Color.yellow); //color of textfield
		tf_cn.setVisible(true);
		add(tf_cn);
		
		tf_ip = new TextField("");
		tf_ip.setSize(200,25);//size of textfield
		tf_ip.setLocation(215,650);//location of textfield
		tf_ip.setFont(fnt12B);//font of textfield
		tf_ip.setBackground(Color.yellow); //color of textfield
		tf_ip.setVisible(false);
		add(tf_ip);
		
		//connect button
		Connectg = new ButtonGroup();
		Connect = new Button("Connect"); //creates button
		Connect.setSize(170, 50); //size of button
		Connect.setLocation(230, 700); //location of button
		Connect.setFont(fnt32B); //font of button
		Connect.setBackground(Color.red); //collor of button
		Connect.addActionListener(this);
		Connect.setVisible(true);
		add(Connect);
		/*
		//player 2 button
		p2 = new Button(""); //creates button
		p2.setSize(100, 25); //size of button
		p2.setLocation(100, 650); //location of button
		p2.setFont(fnt32B); //font of button
		p2.setBackground(Color.red); //collor of button
		p2.addActionListener(this);
		p2.setVisible(true);
		add(p2);
		*/
		//image button
		music = getAudioClip(getCodeBase(), "Centuries8bit.wav");
		music.loop();
	}//ends init method
	
	//auto generated methods because of the class BattleshipPaint's extensions
	public void itemStateChanged(ItemEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	public void mouseClicked(MouseEvent e) 
	{

	}
	
	public void mousePressed(MouseEvent e) 
	{
	}
	
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		//if they press player one
		if(e.getSource() == Player_1)
			{
				//they are player one
				isPlayerOne = true;
				//player one goes first
				isTurn = true;
				Player_2.setVisible(false);
				Player_1.setVisible(false);
				repaint();
			}
		
		//if they press player two
		if(e.getSource() == Player_2)
			{
				//take the ip from the player
				//tf_ip.setVisible(true); //took this out since we're doing it locally
				//they are player two
				//player one goes first
				Player_1.setVisible(false);
				Player_2.setVisible(false);
				repaint();
				isTurn = false;
				isPlayerOne = false;
				
			}
		
		if(e.getSource() == b_start)
			{
				P_AC = Game.setUpP_AC();
				P_BS = Game.setUpP_BS();
				P_D = Game.setUpP_D();
				S = Game.setUpS();
				PP = Game.setUpPP();
				b_start.setVisible(false);
				b_attack.setVisible(isTurn);
				repaint();
				if(!isPlayerOne)
				{
					otherTurn();
				}
			}
		
		if(e.getSource() == Connect)
			{
				//tell OSock what player they are
				OSocklocal.set_isPlayerOne(isPlayerOne);
				
				//if they are player one
				if(isPlayerOne)
				{
					OSocklocal.set_ip(InetAddress.getLoopbackAddress());
					OSocklocal.connect();
					b_start.setVisible(true);
					repaint();
				}
				
				//if they are player two
				if(!isPlayerOne)
				{
					try
					{
						ip = InetAddress.getByName(tf_ip.getText());
						OSocklocal.set_ip(InetAddress.getLoopbackAddress());
						OSocklocal.connect();
					}
					catch(UnknownHostException h)
					{
						System.out.println("You need to pass it an ip not a hostname");
					}
					finally
					{
					b_start.setVisible(true);
					P_AC = Game.setUpP_AC();
					P_BS = Game.setUpP_BS();
					P_D = Game.setUpP_D();
					S = Game.setUpS();
					PP = Game.setUpPP();
					b_attack.setVisible(isTurn);
					repaint();
					repaint();
					}
				}//end if is not player one
				
			}//end if e == Connect
		
		if(e.getSource() == b_attack)
			{
			//send the coordinates to the other player 
			OSocklocal.writeSock(Game.translate(tf_cl.getText(), tf_cn.getText()));
			//end turn
			isTurn = false;
			b_attack.setVisible(false);
			otherTurn();
			}
	}//end actionPerformed
	
	public void otherTurn()
	{
		String attackdata;
		//wait on other player to send data
		while((attackdata = OSocklocal.readSock()) == null)
		{
			try
			{
				Thread.sleep(100);
				repaint();
			} 
			catch (InterruptedException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			repaint();
		}//end loop waiting on other player to send data
		
		//turn string into an array of two numbers(coordinates of attack)
		String[] move = attackdata.split("[ ]+");
		//put each number into an integer for the board and one for the pixel
		int xhit = Integer.parseInt(move[0]);
		int xhitcoord = 52 + (58*(xhit+1));
		int yhit = Integer.parseInt(move[1]);
		int yhitcoord = 73 + (58*(yhit+1));
		//update board array with attack
		Game.hit(xhit, yhit);
		//update array of white or red pins
		if(Game.get_board(xhit, yhit) == 2)
		{
			whitepins[pin_numw] = new Point(xhitcoord, yhitcoord);
			pin_numw++;
		}
		else if(Game.get_board(xhit, yhit) == 3)
		{
			redpins[pin_numr] = new Point(xhitcoord, yhitcoord);
			pin_numr++;
		}
		b_attack.setVisible(true);
	}//end otherturn	
}//end class
