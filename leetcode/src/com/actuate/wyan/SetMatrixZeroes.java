
package com.actuate.wyan;

public class SetMatrixZeroes
{

	public class Solution
	{

		public void setZeroes( int[][] matrix )
		{
			if ( matrix == null || matrix.length == 0 || matrix[0].length == 0 )
			{
				return;
			}

			int rowCount = matrix.length;
			int colCount = matrix[0].length;
			boolean[] rowSetted = new boolean[rowCount];
			boolean[] colSetted = new boolean[colCount];

			for ( int y = 0; y < rowCount; y++ )
			{
				for ( int x = 0; x < colCount; x++ )
				{
					if ( matrix[y][x] == 0 )
					{
						rowSetted[y] = true;
						colSetted[x] = true;
					}
				}
			}

			for ( int y = 0; y < rowCount; y++ )
			{
				if ( rowSetted[y] )
				{
					setRow( matrix, y );
				}
			}
			for ( int x = 0; x < colCount; x++ )
			{
				if ( colSetted[x] )
				{
					setCol( matrix, x );
				}
			}
		}

		void setRow( int[][] matrix, int y )
		{
			for ( int x = 0; x < matrix[y].length; x++ )
			{
				matrix[y][x] = 0;
			}
		}

		void setCol( int[][] matrix, int x )
		{
			for ( int y = 0; y < matrix.length; y++ )
			{
				matrix[y][x] = 0;
			}
		}

	}
}
