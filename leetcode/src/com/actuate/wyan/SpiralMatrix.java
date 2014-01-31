
package com.actuate.wyan;

import java.util.ArrayList;

public class SpiralMatrix
{

	public static void main( String[] args )
	{
		int[][] matrix = toMatrix( new int[]{1, 2, 3,4,5,6}, 3, 2 );
		ArrayList<Integer> values = new Solution( ).spiralOrder( matrix );
		for ( int v : values )
		{
			System.out.print( v + "," );;
		}
		System.out.println( );
	}

	static int[][] toMatrix( int[] values, int rows, int columns )
	{
		int[][] matrix = new int[rows][];
		for ( int i = 0; i < rows; i++ )
		{
			matrix[i] = new int[columns];
		}
		int index = 0;
		for ( int i = 0; i < rows; i++ )
		{
			for ( int j = 0; j < columns; j++ )
			{
				matrix[i][j] = values[index];
				index++;
			}
		}
		return matrix;
	}

	static public class Solution
	{

		public ArrayList<Integer> spiralOrder( int[][] matrix )
		{
			ArrayList<Integer> values = new ArrayList<Integer>( );

			if ( matrix == null || matrix.length == 0 || matrix[0].length == 0 )
			{
				return values;
			}

			int startx = 0;
			int starty = 0;
			int endx = matrix[0].length - 1;
			int endy = matrix.length - 1;

			boolean horz = true;
			boolean reverse = false;

			while ( startx <= endx && starty <= endy )
			{
				if ( horz )
				{
					if ( !reverse )
					{
						for ( int x = startx; x <= endx; x++ )
						{
							values.add( matrix[starty][x] );
						}
						starty++;
						horz = false;
						reverse = false;
					}
					else
					{
						for ( int x = endx; x >= startx; x-- )
						{
							values.add( matrix[endy][x] );
						}
						endy--;
						horz = false;
						reverse = true;
					}
				}
				else
				{
					if ( !reverse )
					{
						for ( int y = starty; y <= endy; y++ )
						{
							values.add( matrix[y][endx] );
						}
						endx--;
						horz = true;
						reverse = true;
					}
					else
					{
						for ( int y = endy; y >= starty; y-- )
						{
							values.add( matrix[y][startx] );
						}
						startx++;
						horz = true;
						reverse = false;
					}
				}
			}
			return values;
		}
	}
}
