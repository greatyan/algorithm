
package com.actuate.wyan;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class SearchA2DMatrix
{

	@Test
	public void test( )
	{
		new Solution( ).searchMatrix(
				new int[][]{new int[]{-8}, new int[]{-6}}, -3 );
	}

	public class Solution
	{

		public boolean searchMatrix( int[][] matrix, int target )
		{
			// search the row which contains the target

			int index = Arrays.binarySearch( matrix, new int[]{target},
					new Comparator<int[]>( ) {

						public int compare( int[] a, int[] b )
						{
							return a[0] - b[0];
						}
					} );
			if ( index >= 0 )
			{
				return true;
			}

			index = -( index + 1 ) - 1;
			if ( index < 0 )
			{
				return false;
			}
			index = Arrays.binarySearch( matrix[index], target );
			return index >= 0;
		}
	}
}
