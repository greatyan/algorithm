
package com.actuate.wyan;

public class MinimumPathSum
{

	public static void main( String[] args )
	{
		new MinimumPathSum( ).test( );
	}

	public void test( )
	{/*
		int[][] grid = new int[][]{
				new int[]{7, 1, 3, 5, 8, 9, 9, 2, 1, 9, 0, 8, 3, 1, 6, 6, 9, 5},
				new int[]{9, 5, 9, 4, 0, 4, 8, 8, 9, 5, 7, 3, 6, 6, 6, 9, 1, 6},
				new int[]{8, 2, 9, 1, 3, 1, 9, 7, 2, 5, 3, 1, 2, 4, 8, 2, 8, 8},
				new int[]{6, 7, 9, 8, 4, 8, 3, 0, 4, 0, 9, 6, 6, 0, 0, 5, 1, 4},
				new int[]{7, 1, 3, 1, 8, 8, 3, 1, 2, 1, 5, 0, 2, 1, 9, 1, 1, 4},
				new int[]{9, 5, 4, 3, 5, 6, 1, 3, 6, 4, 9, 7, 0, 8, 0, 3, 9, 9},
				new int[]{1, 4, 2, 5, 8, 7, 7, 0, 0, 7, 1, 2, 1, 2, 7, 7, 7, 4},
				new int[]{3, 9, 7, 9, 5, 8, 9, 5, 6, 9, 8, 8, 0, 1, 4, 2, 8, 2},
				new int[]{1, 5, 2, 2, 2, 5, 6, 3, 9, 3, 1, 7, 9, 6, 8, 6, 8, 3},
				new int[]{5, 7, 8, 3, 8, 8, 3, 9, 9, 8, 1, 9, 2, 5, 4, 7, 7, 7},
				new int[]{2, 3, 2, 4, 8, 5, 1, 7, 2, 9, 5, 2, 4, 2, 9, 2, 8, 7},
				new int[]{0, 1, 6, 1, 1, 0, 0, 6, 5, 4, 3, 4, 3, 7, 9, 6, 1, 9}};
			*/
		int[][] grid = new int[][]{
				new int[]{1,2,3},
				new int[]{4,5,6},
				new int[]{-5,8,9}
		};

		int v = new Solution( ).minPathSum( grid );
		System.out.println( v );
	}

	public class Solution
	{

		public int minPathSum( int[][] grid )
		{
			if ( grid == null || grid.length == 0 )
			{
				return 0;
			}

			minValues = new int[grid.length][];
			for ( int i = 0; i < grid.length; i++ )
			{
				minValues[i] = new int[grid[i].length];
			}
			int v = go( grid, 0, 0 );
			return v;
		}

		int[][] minValues;

		int go( int[][] grid, int x, int y )
		{
			if ( minValues[y][x] != 0 )
			{
				return minValues[y][x];
			}

			if ( x == grid[y].length - 1 )
			{
				int value = 0;
				for ( int i = y; i < grid.length; i++ )
				{
					value += grid[i][x];
				}
				minValues[y][x] = value;
				return value;
			}

			if ( y == grid.length - 1 )
			{
				int value = 0;
				for ( int i = x; i < grid[y].length; i++ )
				{
					value += grid[y][i];
				}
				minValues[y][x] = value;
				return value;
			}

			// try go right
			int value1 = grid[y][x] + go( grid, x + 1, y );
			//try go bottom
			int value2 = grid[y][x] + go( grid, x, y + 1 );
			int minValue = Math.min( value1, value2 );
			minValues[y][x] = minValue;
			return minValue;
		}
	}
}
