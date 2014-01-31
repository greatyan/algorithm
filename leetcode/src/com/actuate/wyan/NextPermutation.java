
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class NextPermutation
{

	@Test
	public void test( )
	{
		Assert.assertArrayEquals( new int[]{1, 3, 2},
				new Solution( ).next( new int[]{1, 2, 3} ) );
		Assert.assertArrayEquals( new int[]{1, 2, 3},
				new Solution( ).next( new int[]{3, 2, 1} ) );
		Assert.assertArrayEquals( new int[]{1, 5, 1},
				new Solution( ).next( new int[]{1, 1, 5} ) );
		Assert.assertArrayEquals( new int[]{2, 1, 3},
				new Solution( ).next( new int[]{1, 3, 2} ) );
	}

	public class Solution
	{

		public int[] next( int[] num )
		{
			nextPermutation( num );
			return num;
		}

		public void nextPermutation( int[] num )
		{
			for ( int i = 2; i <= num.length; i++ )
			{
				if ( next( num, num.length - i ) )
				{
					return;
				}
			}
			// reverse the num
			reverse( num, 0);
		}

		public boolean next( int[] num, int offset )
		{
			for ( int i = num.length - 1; i > offset; i-- )
			{
				if ( num[offset] < num[i] )
				{
					// change and reset
					int v = num[offset];
					num[offset] = num[i];
					num[i] = v;
					reverse( num, offset + 1 );
					return true;
				}
			}
			return false;
		}

		protected void reverse( int[] num, int offset )
		{
			int size = ( num.length - offset ) / 2;
			// reverse the num
			for ( int i = 0; i < size; i++ )
			{
				int index1 = offset + i;
				int index2 = num.length - 1 - i;
				int v = num[index1];
				num[index1] = num[index2];
				num[index2] = v;
			}

		}
	}
}
