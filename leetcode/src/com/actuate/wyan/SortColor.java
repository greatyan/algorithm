
package com.actuate.wyan;

public class SortColor
{

	public class Solution
	{

		public void sortColors( int[] A )
		{
			
			int[][] bucks = new int[][]{new int[A.length], new int[A.length],
					new int[A.length]};
			int[] buckIndexes = new int[3];
			for ( int color : A )
			{
				bucks[color][buckIndexes[color]] = color;
				buckIndexes[color]++;
			}

			int index = 0;
			for ( int i = 0; i < 3; i++ )
			{
				for ( int j = 0; j < buckIndexes[i]; j++ )
				{
					A[index] = bucks[i][j];
					index++;
				}
			}
		}
	}
}
