
package com.actuate.wyan;

public class WordSearch
{

	public static void main( String[] args )
	{
		char[][] board = createBoard( new String[]{"bb", "ab", "ba"} );
		String word = "a";
		System.out.println( new Solution( ).exist( board, word ) );
	}

	static char[][] createBoard( String[] tokens )
	{
		char[][] board = new char[tokens.length][];
		for ( int i = 0; i < board.length; i++ )
		{
			board[i] = tokens[i].toCharArray( );
		}
		return board;
	}

	static public class Solution
	{

		public boolean exist( char[][] board, String word )
		{

			boolean[][] used = new boolean[board.length][];
			for ( int y = 0; y < board.length; y++ )
			{
				used[y] = new boolean[board[y].length];
			}
			char[] chars = word.toCharArray( );
			for ( int y = 0; y < board.length; y++ )
			{
				for ( int x = 0; x < board[y].length; x++ )
				{
					if ( board[y][x] == chars[0] )
					{
						used[y][x] = true;
						if ( exist( board, used, x, y, chars, 1 ) )
						{
							return true;
						}
						used[y][x] = false;
					}
				}
			}
			return false;

		}

		private boolean exist( char[][] board, boolean[][] used, int x, int y,
				char[] chars, int start )
		{
			if ( start == chars.length )
			{
				return true;
			}
			// test x-1, y
			if ( x - 1 >= 0 )
			{
				if ( board[y][x - 1] == chars[start] && used[y][x - 1] == false )
				{
					used[y][x - 1] = true;
					if ( exist( board, used, x - 1, y, chars, start + 1 ) )
					{
						return true;
					}
					used[y][x - 1] = false;
				}
			}
			// test x+1, y
			if ( x + 1 < board[y].length )
			{
				if ( board[y][x + 1] == chars[start] && used[y][x + 1] == false )
				{
					used[y][x + 1] = true;
					if ( exist( board, used, x + 1, y, chars, start + 1 ) )
					{
						return true;
					}
					used[y][x + 1] = false;
				}
			}
			// test x, y-1
			if ( y - 1 >= 0 )
			{
				if ( board[y - 1][x] == chars[start] && used[y - 1][x] == false )
				{
					used[y - 1][x] = true;
					if ( exist( board, used, x, y - 1, chars, start + 1 ) )
					{
						return true;
					}
					used[y - 1][x] = false;
				}
			}
			// test x, y+1
			if ( y + 1 < board.length )
			{
				if ( board[y + 1][x] == chars[start] && used[y + 1][x] == false )
				{
					used[y + 1][x] = true;
					if ( exist( board, used, x, y + 1, chars, start + 1 ) )
					{
						return true;
					}
					used[y + 1][x] = false;
				}
			}
			return false;
		}
	}
}
