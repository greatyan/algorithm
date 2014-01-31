
package com.actuate.wyan;

public class UniquePaths
{

	public static void main( String[] args )
	{
		new UniquePaths( ).test( );
	}

	public void test( )
	{
		int v = new Solution( ).uniquePaths(4, 6);
		System.out.println( v );
	}

	public class Solution
	{

		public int uniquePaths( int m, int n )
		{
			if ( m == 0 || n == 0 )
			{
				return 0;
			}

			this.rows = n;
			this.columns = m;
			this.values = new int[rows * columns];
			return path( m - 1, n - 1 );
		}

		int rows;
		int columns;
		int[] values;

		int path( int x, int y )
		{
			if ( x == 0 || y == 0 )
				return 1;
			int id = y * columns + x;
			if ( values[id] == 0 )
			{
				values[id] = path( x - 1, y ) + path( x, y - 1 );
			}
			return values[id];
		}
	}
}
