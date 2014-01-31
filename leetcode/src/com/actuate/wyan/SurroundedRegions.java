
package com.actuate.wyan;

import java.util.LinkedList;

public class SurroundedRegions
{

	public static void main( String[] args )
	{
		String[] input = new String[]{"XXXX", "XOOX", "XXOX", "XOXX"};
		Solution s = new Solution( );

		printBoard( input );
		char[][] board = toChars( input );
		s.solve( board );
		printBoard( toStrings( board ) );
	}

	static void printBoard( String[] board )
	{
		for ( String s : board )
		{
			System.out.println( s );
		}
	}

	static char[][] toChars( String[] board )
	{
		char[][] chars = new char[board.length][];
		for ( int i = 0; i < chars.length; i++ )
		{
			chars[i] = board[i].toCharArray( );
		}
		return chars;
	}

	static String[] toStrings( char[][] board )
	{
		String[] strs = new String[board.length];
		for ( int i = 0; i < strs.length; i++ )
		{
			strs[i] = new String( board[i] );
		}
		return strs;
	}

	static public class Solution
	{

		public void solve( char[][] board )
		{
			if ( board == null || board.length == 0 )
			{
				return;
			}

			LinkedList<int[]> visitQueue = new LinkedList<int[]>( );
			// initialize the queue
			for ( int y = 0; y < board.length; y++ )
			{
				if ( board[y][0] == 'O' )
				{
					visitQueue.push( new int[]{0, y} );
				}
				if ( board[y][board[y].length - 1] == 'O' )
				{
					visitQueue.push( new int[]{board[y].length - 1, y} );
				}
			}

			for ( int x = 0; x < board[0].length; x++ )
			{
				if ( board[0][x] == 'O' )
				{
					visitQueue.push( new int[]{x, 0} );
				}
			}
			for ( int x = 0; x < board[board.length - 1].length; x++ )
			{
				if ( board[board.length - 1][x] == 'O' )
				{
					visitQueue.push( new int[]{x, board.length - 1} );
				}
			}

			while ( !visitQueue.isEmpty( ) )
			{
				int[] position = visitQueue.poll( );
				int x = position[0];
				int y = position[1];
				if ( board[y][x] == 'O' )
				{
					board[y][x] = 't';
					if ( y + 1 < board.length )
					{
						visitQueue.push( new int[]{x, y + 1} );
					}
					if ( y - 1 > 0 )
					{
						visitQueue.push( new int[]{x, y - 1} );
					}
					if ( x + 1 < board[y].length )
					{
						visitQueue.push( new int[]{x + 1, y} );
					}
					if ( x - 1 > 0 )
					{
						visitQueue.push( new int[]{x - 1, y} );
					}
				}
			}

			for ( int y = 0; y < board.length; y++ )
			{
				for ( int x = 0; x < board[y].length; x++ )
				{
					char ch = board[y][x];
					switch ( ch )
					{
						case 't' :
							board[y][x] = 'O';
							break;
						case 'O' :
							board[y][x] = 'X';
					}
				}
			}
		}
	}
}
