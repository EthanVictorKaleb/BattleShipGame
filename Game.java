 import java.awt.*;

public class Game 
{
	//the board with the ships on it
	private int[][] board = new int[10][10];
	//the other player's board
	private int[][] otherboard = new int[10][10];
	
	//change board array values
	public void set_board(int x, int y, int value)
	{
		board[x][y] = value;
	}//end set_board
	
	//change the otherboard array values
	public void set_otherboard(int x, int y, int value)
	{
		otherboard[x][y] = value;
	}//end set_otherboard
	
	//get board array values
	public int get_board(int x, int y)
	{
		return board[x][y];
	}//end get_board
	
	//get otherboard array values
	public int get_otherboard(int x, int y)
	{
		return otherboard[x][y];
	}
	
	//place the ships
	public Point setUpP_AC()
	{
		int startx = (int)(Math.random()*9);
		int starty = (int)(Math.random()*5);
		
		board[startx][starty] = 1;
		board[startx][starty + 1] = 1;
		board[startx][starty + 2] = 1;
		board[startx][starty + 3] = 1;
		board[startx][starty + 4] = 1;
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 52+(startx + 1)*55;
		Out.y = 73+(starty + 1)*50;
		//return the coordinates
		return Out;
		
	}//end setUpACCarrier
	
	public Point setUpP_BS()
	{
		//top of ship grid coords
		int startx;
		int starty;
		
		//while loop to retry if ship is placed over another ship
		while(true)
		{
			//randomly set top of ship coords
			startx = (int)(Math.random()*9);
			starty = (int)(Math.random()*6);
			//if ship will not overlap do not loop again
			if(board[startx][starty] == 0&&board[startx][starty+1] == 0&&board[startx][starty+2] == 0&&board[startx][starty+3] == 0)
			{
				board[startx][starty] = 1;
				board[startx + 1][starty] = 1;
				board[startx + 2][starty] = 1;
				board[startx + 3][starty] = 1;
				break;
			}
			
		}//end while
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 52+(startx + 1)*55;
		Out.y = 73+(starty + 1)*50;
		//return the coordinates
		return Out;
		
	}//end setUpBattleship
	
	public Point setUpP_D()
	{
		int startx;
		int starty;
		
		//while to redo if ships overlap
		while(true)
		{
			startx = (int)(Math.random()*9);
			starty = (int)(Math.random()*6);
			
			//if ships dont overlap
			if(board[startx][starty] == 0&&board[startx][starty+1] == 0&&board[startx][starty+2] == 0)
			{
				//update board
				board[startx][starty] = 1;
				board[startx][starty + 1] = 1;
				board[startx][starty + 2] = 1;
				break;
			}
			
		}//end while
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 68+(startx + 1)*55;
		Out.y = 73+(starty + 1)*50;
		//return the coordinates
		return Out;
		
	}//end setUpDestroyer
	
	public Point setUpS()
	{
		//init
		int startx;
		int starty;
		
		while(true)
		{
			//top ship coords
			startx = (int)(Math.random()*9);
			starty = (int)(Math.random()*6);
			//if ship doesnt overlap
			if(board[startx][starty] == 0&&board[startx][starty+1] == 0&&board[startx][starty+2] == 0)
			{
				board[startx][starty] = 1;
				board[startx + 1][starty] = 1;
				board[startx + 2][starty] = 1;
				break;
			}
			
		}//end while
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 52+(startx + 1)*55;
		Out.y = 73+(starty + 1)*50;
		//return the coordinates
		return Out;
		
		
	}//end setUpSub
	
	public Point setUpPP()
	{
		int startx;
		int starty;
		
		while(true)
		{
			startx = (int)(Math.random()*9);
			starty = (int)(Math.random()*7);
			
			if(board[startx][starty] == 0&&board[startx][starty+1] == 0)
			{
				board[startx][starty] = 1;
				board[startx][starty + 1] = 1;
				break;
			}
			
		}
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 45+(startx + 1)*55;
		Out.y = 73+(starty + 1)*50;
		//return the coordinates
		return Out;
		
	}
	
	//when they fire on your board
	public void hit(int x, int y)
	{
		board[x][y] = board[x][y] + 2;
	}//end hit()
	
	//when your fire on their board
	public void fire(int x, int y, boolean isHit)
	{
		if(isHit)
		{
			otherboard[x][y] += 2;
		}
		else
		{
			otherboard[x][y] += 1;
		}
		
	}//end fire()
	
	//turns the letters from the board into numbers
	//returns a string that can be sent through OSock
	public String translate(String x, String y)
	{
		String A = "1";
		String B = "1";
		String C;
		if(x == "A" || x == "a") 
		{
			A = "0";
		}
		if(x == "B" || x == "b") 
		{
			A = "1";
		}
		if(x == "C" || x == "c")
		{
			A = "2";
		}
		if(x == "D" || x == "d")
		{
			A = "3";
		}
		if(x == "E" || x == "e") 
		{
			A = "4";
		}
		if(x == "F" || x == "f")
		{
			A = "5";
		}
		if(x == "G" || x == "g")
		{
			A = "6";
		}
		if(x == "H" || x == "h")
		{
			A = "7";
		}
		if(x == "I" || x == "i")
		{
			A = "8";
		}
		if(x == "J" || x == "j")
		{
			A = "9";
		}
		if(y == "1")
		{
			B = "0";
		}
		if(y == "2")
		{
			B = "1";
		}
		if(y == "3")
		{
			B = "2";
		}
		if(y == "4")
		{
			B = "3";
		}
		if(y == "5")
		{
			B = "4";
		}
		if(y == "6")
		{
			B = "5";
		}
		if(y == "7")
		{
			B = "6";
		}
		if(y == "8")
		{
			B = "7";
		}
		if(y == "9")
		{
			B = "8";
		}
		if(y == "10")
		{
			B = "9";
		}
		
		C = A + " " + B;
		return C;		
	}//end translate
	
}//end Game
