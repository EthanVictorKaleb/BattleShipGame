import java.awt.*;

public class Game 
{
	//the board with the ships on it
	private int[][] board = new int[10][10];
	
	//change board array values
	public void set_board(int x, int y, int value)
	{
		board[x][y] = value;
	}//end set_board
	
	//get board array values
	public int get_board(int x, int y)
	{
		return board[x][y];
	}//end get_board
	
	//place the aircraft carrier
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
		Out.x = (startx + 1)*52;
		Out.y = (starty + 1)*73;
		//return the coordinates
		return Out;
		
	}//end setUpACCarrier
	
	public Point setUpP_BS()
	{
		int startx = (int)(Math.random()*6);
		int starty = (int)(Math.random()*9);
		
		if(board[startx][starty] == 0&&board[startx+1][starty] == 0&&board[startx+2][starty] == 0&&board[startx+3][starty] == 0)
		{
			board[startx][starty] = 1;
			board[startx + 1][starty] = 1;
			board[startx + 2][starty] = 1;
			board[startx + 3][starty] = 1;
		}
		
		else
		{
			setUpP_BS();
		}
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = (startx + 1)*52;
		Out.y = (starty + 1)*73;
		//return the coordinates
		return Out;
		
	}//end setUpBattleship
	
	public Point setUpP_D()
	{
		int startx = (int)(Math.random()*9);
		int starty = (int)(Math.random()*6);
		
		if(board[startx][starty] == 0&&board[startx][starty+1] == 0&&board[startx][starty+2] == 0)
		{
			board[startx][starty] = 1;
			board[startx][starty + 1] = 1;
			board[startx][starty + 2] = 1;
		}
		
		else
		{
			setUpP_D();
		}
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = (startx + 1)*52;
		Out.y = (starty + 1)*73;
		//return the coordinates
		return Out;
		
	}//end setUpDestroyer
	
	public Point setUpS()
	{
		int startx = (int)(Math.random()*6);
		int starty = (int)(Math.random()*9);
		
		if(board[startx][starty] == 0&&board[startx+1][starty] == 0&&board[startx+2][starty] == 0)
		{
			board[startx][starty] = 1;
			board[startx + 1][starty] = 1;
			board[startx + 2][starty] = 1;
		}
		
		else
		{
			setUpS();
		}
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = (startx + 1)*52;
		Out.y = (starty + 1)*73;
		//return the coordinates
		return Out;
		
	}//end setUpSub
	
	public Point setUpPP()
	{
		int startx = (int)(Math.random()*9);
		int starty = (int)(Math.random()*7);
		
		if(board[startx][starty] == 0&&board[startx][starty +1] == 0)
		{
			board[startx][starty] = 1;
			board[startx][starty + 1] = 1;
		}
		
		else
		{
			setUpPP();
		}
		
		//calculate where it will be in terms of pixels
		Point Out = new Point();
		Out.x = (startx + 1)*52;
		Out.y = (starty + 1)*73;
		//return the coordinates
		return Out;
		
	}
	
	public void hit(int x, int y)
	{
		board[x][y] = board[x][y] + 2;
	}
}//end Game
