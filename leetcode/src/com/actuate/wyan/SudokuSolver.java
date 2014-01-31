
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.HashSet;

public class SudokuSolver
{

	public static void main( String[] args )
	{
		new SudokuSolver( ).test( );
	}

	char[][] initBoard( String[] strs )
	{
		char[][] board = new char[9][];
		for ( int i = 0; i < 9; i++ )
		{
			board[i] = strs[i].toCharArray( );
		}
		return board;
	}

	void printBoard( char[][] board )
	{
		for ( int i = 0; i < 9; i++ )
		{
			for ( int j = 0; j < 9; j++ )
			{
				System.out.print( board[i][j] );
				System.out.print( ' ' );
			}
			System.out.println( );
		}
	}

	public void test( )
	{
		char[][] board = initBoard( new String[]{"53..7....", "6..195...",
				".98....6.", "8...6...3", "4..8.3..1", "7...2...6",
				".6....28.", "...419..5", "....8..79"} );
		printBoard( board );
		new Solution( ).solveSudoku( board );
		printBoard( board );

	}

	public class Solution
	{

		public void solveSudoku( char[][] board )
		{
			initBoard( board );
			doResolve( 0, 0 );
		}

		protected boolean doResolve( int x, int y )
		{
			while ( y < 9 )
			{
				if ( board[y][x] == '.' )
				{
					ArrayList<Character> values = getPossibleValues( x, y );
					if ( values == null || values.isEmpty( ) )
					{
						return false;
					}
					for ( int i = 0; i < values.size( ); i++ )
					{
						setBoard( x, y, values.get( i ) );
						int nextX = x + 1;
						int nextY = y;
						if ( nextX == 9 )
						{
							nextX = 0;
							nextY++;
						}
						if ( nextY == 9 )
						{
							return isValidBoard( );
						}
						if ( doResolve( nextX, nextY ) )
						{
							return true;
						}
						resetBoard( x, y );
					}
					return false;
				}
				x++;
				if ( x == 9 )
				{
					x = 0;
					y++;
				}
			}
			// test grid...
			return isValidBoard( );
		}

		char[][] board;
		HashSet<Character>[] rowRemains = new HashSet[9];
		HashSet<Character>[] columnRemains = new HashSet[9];
		HashSet<Character>[] gridRemains = new HashSet[9];

		void initBoard( char[][] board )
		{
			this.board = board;
			for ( int i = 0; i < 9; i++ )
			{
				rowRemains[i] = new HashSet<Character>( 9 );
				columnRemains[i] = new HashSet<Character>( 9 );
				gridRemains[i] = new HashSet<Character>( 9 );
				for ( int j = 0; j < 9; j++ )
				{
					char ch = (char) ( '1' + j );
					rowRemains[i].add( ch );
					columnRemains[i].add( ch );
					gridRemains[i].add( ch );
				}
			}
			for ( int y = 0; y < 9; y++ )
			{
				for ( int x = 0; x < 9; x++ )
				{
					if ( board[y][x] != '.' )
					{
						rowRemains[y].remove( board[y][x] );
						columnRemains[x].remove( board[y][x] );
						gridRemains[getGridId( x, y )].remove( board[y][x] );
					}
				}
			}
		}

		boolean isValidBoard( )
		{
			return true;
		}

		void setBoard( int x, int y, char ch )
		{
			rowRemains[y].remove( ch );
			columnRemains[x].remove( ch );
			int gridId = getGridId( x, y );
			gridRemains[gridId].remove( ch );
			board[y][x] = ch;
		}

		int getGridId( int x, int y )
		{
			return y / 3 * 3 + x / 3;
		}

		void resetBoard( int x, int y )
		{
			char ch = board[y][x];
			rowRemains[y].add( ch );
			columnRemains[x].add( ch );
			int gridId = getGridId( x, y );
			gridRemains[gridId].add( ch );
			board[y][x] = '.';
		}

		public ArrayList<Character> getPossibleValues( int x, int y )
		{
			HashSet<Character> row = rowRemains[y];
			HashSet<Character> col = columnRemains[x];
			HashSet<Character> grid = gridRemains[getGridId( x, y )];

			if ( row.isEmpty( ) || col.isEmpty( ) || grid.isEmpty( ) )
			{
				return null;
			}

			ArrayList<Character> remains = new ArrayList<Character>( );
			for ( Character ch : row )
			{
				if ( col.contains( ch ) && grid.contains( ch ) )
				{
					remains.add( ch );
				}
			}
			if ( remains.isEmpty( ) )
			{
				return null;
			}
			return remains;
		}
	}
}
