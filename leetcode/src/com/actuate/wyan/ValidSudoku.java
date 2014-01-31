
package com.actuate.wyan;

public class ValidSudoku
{

	public class Solution
	{

		public boolean isValidSudoku( char[][] board )
		{

			// valid row
			for ( int i = 0; i < 9; i++ )
			{
				if ( validRow( board, i ) == false )
				{
					return false;
				}
			}

			// valid column
			for ( int i = 0; i < 9; i++ )
			{
				if ( validColumn( board, i ) == false )
				{
					return false;
				}
			}
			// valid area

			for ( int i = 0; i < 3; i++ )
			{
				for ( int j = 0; j < 3; j++ )
				{
					if ( validArea( board, i * 3, j * 3 ) == false )
					{
						return false;
					}
				}
			}
			return true;
		}

		public boolean validRow( char[][] board, int row )
		{
			boolean[] used = new boolean[9];

			for ( int i = 0; i < 9; i++ )
			{
				char v = board[row][i];
				int index = v - '0';
				if ( index >= 0 && index < 9 )
				{
					if ( used[index] == true )
					{
						return false;
					}
					used[index] = true;
				}
				else
				{
					return false;
				}
			}
			return true;
		}

		public boolean validColumn( char[][] board, int col )
		{
			boolean[] used = new boolean[9];

			for ( int i = 0; i < 9; i++ )
			{
				char v = board[i][col];
				int index = v - '0';
				if ( index >= 0 && index < 9 )
				{
					if ( used[index] == true )
					{
						return false;
					}
					used[index] = true;
				}
				else
				{
					return false;
				}
			}
			return true;
		}

		public boolean validArea( char[][] board, int row, int col )
		{
			boolean[] used = new boolean[9];

			for ( int i = 0; i < 3; i++ )
			{
				for ( int j = 0; j < 3; j++ )
				{
					char v = board[row + i][col + j];
					int index = v - '0';
					if ( index >= 0 && index < 9 )
					{
						if ( used[index] == true )
						{
							return false;
						}
						used[index] = true;
					}
					else
					{
						return false;
					}
				}
			}
			return true;
		}

	}
}
