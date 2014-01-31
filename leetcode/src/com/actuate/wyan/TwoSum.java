
package com.actuate.wyan;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class TwoSum
{

	@Test
	public void test( )
	{
		new Solution( ).twoSum( new int[]{230, 863, 916, 585, 981, 404, 316,
				785, 88, 12, 70, 435, 384, 778, 887, 755, 740, 337, 86, 92,
				325, 422, 815, 650, 920, 125, 277, 336, 221, 847, 168, 23, 677,
				61, 400, 136, 874, 363, 394, 199, 863, 997, 794, 587, 124, 321,
				212, 957, 764, 173, 314, 422, 927, 783, 930, 282, 306, 506, 44,
				926, 691, 568, 68, 730, 933, 737, 531, 180, 414, 751, 28, 546,
				60, 371, 493, 370, 527, 387, 43, 541, 13, 457, 328, 227, 652,
				365, 430, 803, 59, 858, 538, 427, 583, 368, 375, 173, 809, 896,
				370, 789}, 542 );
	}

	public class Solution
	{

		public int[] twoSum( int[] numbers, int target )
		{
			return search( numbers, target );
		}

		public int[] searchON2( final int[] numbers, final int target )
		{
			for ( int i = 0; i < numbers.length; i++ )
			{
				for ( int j = i + 1; j < numbers.length; j++ )
				{
					if ( numbers[i] + numbers[j] == target )
					{
						return new int[]{i + 1, j + 1};
					}
				}
			}
			return null;
		}

		public int[] search( final int[] numbers, final int target )
		{
			Integer[] indexes = new Integer[numbers.length];
			for ( int i = 0; i < indexes.length; i++ )
			{
				indexes[i] = i;
			}

			Arrays.sort( indexes, new Comparator<Integer>( ) {

				public int compare( Integer v1, Integer v2 )
				{
					return numbers[v1] - numbers[v2];
				}
			} );

			Comparator<Integer> searchComparator = new Comparator<Integer>( ) {

				public int compare( Integer v1, Integer v2 )
				{
					return numbers[v1] - v2;
				}
			};

			for ( int i = 0; i < numbers.length; i++ )
			{
				int remain = target - numbers[indexes[i]];
				int index = Arrays.binarySearch( indexes, remain,
						searchComparator );
				if ( index > 0 )
				{
					int idx1 = indexes[i];
					int idx2 = indexes[index];
					if ( idx1 != idx2 )
					{
						return new int[]{Math.min( idx1, idx2 ) + 1,
								Math.max( idx1, idx2 ) + 1};
					}
				}
			}
			return null;
		}
	}
}
