import java.awt.*;
import java.awt.event.*;

public class Game 
{
	//the board with the ships on it
	private int[][] board = new int[10][10];
	//the other player's board
	private int[][] otherboard = new int[10][10];
	//the boards must be switched depending on which player you are
	private boolean isPlayerOne;
	
	//set the value of is player one
	public void set_isPlayerOne(boolean set)
	{
		isPlayerOne = set;
	}//end set is player one
	
	//change board array values
	public void set_board(int x, int y, int value)
	{
		if(isPlayerOne)
		{
			board[x][y] = value;
		}
		else if(!isPlayerOne)
		{
			otherboard[x][y] = value;
		}
	}//end set_board
	
	//change the otherboard array values
	public void set_otherboard(int x, int y, int value)
	{
		if(isPlayerOne)
		{
			otherboard[x][y] = value;
		}
		else if(!isPlayerOne)
		{
			board[x][y] = value;
		}
	}//end set_otherboard
	
	//get the value of is player one
	public boolean get_isPlayerOne()
	{
		return isPlayerOne;
	}//end get is player one
	
	//get board array values
	public int get_board(int x, int y)
	{
		if(isPlayerOne)
		{
			return board[x][y];
		}
		else
		{
			return otherboard[x][y];
		}
	}//end get_board
	
	//get otherboard array values
	public int get_otherboard(int x, int y)
	{
		if(isPlayerOne)
		{
			return otherboard[x][y];
		}
		else
		{
			return board[x][y];
		}
	}
	
	//place the ships
	public Point setUpP_AC()
	{
		int startx = (int)(Math.random()*9);
		int starty = (int)(Math.random()*4);
		
		if(isPlayerOne)
		{
			board[startx][starty] = 1;
			board[startx][starty + 1] = 1;
			board[startx][starty + 2] = 1;
			board[startx][starty + 3] = 1;
			board[startx][starty + 4] = 1;
		}
		else if(!isPlayerOne)
		{
			otherboard[startx][starty] = 1;
			otherboard[startx][starty + 1] = 1;
			otherboard[startx][starty + 2] = 1;
			otherboard[startx][starty + 3] = 1;
			otherboard[startx][starty + 4] = 1;
		}
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 52+(startx)*55;
		Out.y = 73+(starty )*50;
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
			starty = (int)(Math.random()*5);
			//if ship will not overlap do not loop again
			if(isPlayerOne)
			{
				if(board[startx][starty] == 0&&board[startx][starty+1] == 0&&board[startx][starty+2] == 0&&board[startx][starty+3] == 0)
				{
					board[startx][starty] = 1;
					board[startx][starty + 1] = 1;
					board[startx][starty + 2] = 1;
					board[startx][starty + 3] = 1;
					break;
				}
			}
			else if(!isPlayerOne)
			{
				if(otherboard[startx][starty] == 0&&otherboard[startx][starty+1] == 0&&otherboard[startx][starty+2] == 0&&otherboard[startx][starty+3] == 0)
				{
					otherboard[startx][starty] = 1;
					otherboard[startx][starty + 1] = 1;
					otherboard[startx][starty + 2] = 1;
					otherboard[startx][starty + 3] = 1;
					break;
				}
			}
		}//end while
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 55+(startx)*55;
		Out.y = 73+(starty)*50;
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
			
			if(isPlayerOne)
			{
				//if ships dont overlap
				if(board[startx][starty] == 0&&board[startx][starty+1] == 0&&board[startx][starty+2] == 0)
				{
					//update board
					board[startx][starty] = 1;
					board[startx][starty + 1] = 1;
					board[startx][starty + 2] = 1;
					break;
				}
			}//end if is player one
			else if(!isPlayerOne)
			{
				//if ships dont overlap
				if(otherboard[startx][starty] == 0&&otherboard[startx][starty+1] == 0&&otherboard[startx][starty+2] == 0)
				{
					//update otherboard
					otherboard[startx][starty] = 1;
					otherboard[startx][starty + 1] = 1;
					otherboard[startx][starty + 2] = 1;
					break;
				}
			}//end if is player two
		}//end while
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 69+(startx)*55;
		Out.y = 73+(starty)*50;
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
			starty = (int)(Math.random()*7);
			
			if(isPlayerOne)
			{
				//if ship doesnt overlap
				if(board[startx][starty] == 0&&board[startx][starty+1] == 0&&board[startx][starty+2] == 0)
				{
					board[startx][starty] = 1;
					board[startx][starty+1] = 1;
					board[startx][starty+2] = 1;
					break;
				}
			}//end if is player one
			else if(!isPlayerOne)
			{
				//if ship doesnt overlap
				if(otherboard[startx][starty] == 0&&otherboard[startx][starty+1] == 0&&otherboard[startx][starty+2] == 0)
				{
					otherboard[startx][starty] = 1;
					otherboard[startx][starty+1] = 1;
					otherboard[startx][starty+2] = 1;
					break;
				}
			}//end is if player two
			
		}//end while
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 97+(startx*55);
		Out.y = 73+(starty*50);
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
			starty = (int)(Math.random()*8);
			
			if(isPlayerOne)
			{
				if(board[startx][starty] == 0&&board[startx][starty+1] == 0)
				{
					board[startx][starty] = 1;
					board[startx][starty + 1] = 1;
					break;
				}
			}//end if is player one
			else if(!isPlayerOne)
			{
				if(otherboard[startx][starty] == 0&&otherboard[startx][starty+1] == 0)
				{
					otherboard[startx][starty] = 1;
					otherboard[startx][starty + 1] = 1;
					break;
				}
			}//end if is player two
			
		}//end while
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = 110+(startx)*55;
		Out.y = 73+(starty)*50;
		//return the coordinates
		return Out;
		
	}//end set up pp
	
	//when they fire on your board
	public void hit(int x, int y)
	{
		if(isPlayerOne)
		{
			board[x][y] = board[x][y] + 2;
		}
		else if(!isPlayerOne)
		{
			otherboard[x][y] = otherboard[x][y] + 2;
		}
	}//end hit()
	
	//turns the letters from the board into numbers
	//returns a string that can be sent through OSock
	public String translate(String x, String y)
	{
		String C;
		
		if(!((x.charAt(0) >= 'A' && x.charAt(0) <= 'J') && (Integer.parseInt(y) >= 1 && Integer.parseInt(y) <= 10)))
		{
			C = "TRY AGAIN";
		}
		else
		{
			String A = Integer.toString(x.charAt(0) - 'A');
			String B = Integer.toString(Integer.parseInt(y)-1);
			C = A + " " + B;
		}
		return C;		
	}//end translate
	
	public void sleep(long time)
	{
		try
		{
		Thread.sleep(time);
		}
		catch(InterruptedException e){}
		finally {}
	}
	
	//diagnostic tool for printing the game board
	public String printboard()
	{
		String board = "";
		for(int j = 0; j <= 9; j++)
		{
			String iter = "";
			for(int i = 0; i <= 9; i++)
			{
				iter = iter + Integer.toString(get_board(i, j)) + " "; 
			}//end for i
			iter = iter + "\n";
			board = board + iter;
			
		}//end for j
		return board;
	}//end printboard
	
	//return true if you have lost
	public boolean isEliminated()
	{
		boolean haveLost = true;
		if(isPlayerOne)
		{
			for(int i = 0; i <= 9; i++)
			{
				for(int j = 0; j <= 9; j++)
				{
					if(board[i][j] == 1)
					{
						haveLost = false;
					}
				}//end for j
				
			}//end for i
			
		}//end if is player one
		
		else if(!isPlayerOne)
		{
			for(int i = 0; i <= 9; i++)
			{
				for(int j = 0; j <= 9; j++)
				{
					if(otherboard[i][j] == 1)
					{
						haveLost = false;
					}
				}//end for j
				
			}//end for i
			
		}//end if is player two
		
		return haveLost;
	}
	
	public void notify(String message, Graphics x)
	{
		PopUp PopUp = new PopUp();
		PopUp.init(message);
		PopUp.paint(x);
	}
	
}//end Game
