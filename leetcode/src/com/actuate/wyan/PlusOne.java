
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class PlusOne
{

	@Test
	public void test( )
	{
		Assert.assertArrayEquals( new int[]{1},
				new Solution( ).plusOne( new int[]{} ) );
		Assert.assertArrayEquals( new int[]{1, 0, 0, 0},
				new Solution( ).plusOne( new int[]{9, 9, 9} ) );
		Assert.assertArrayEquals( new int[]{1},
				new Solution( ).plusOne( new int[]{0} ) );
		Assert.assertArrayEquals( new int[]{9,0,0,0},
				new Solution( ).plusOne( new int[]{8,9,9,9} ) );
				
	}

	public class Solution
	{

		public int[] plusOne( int[] digits )
		{
			if ( digits.length == 0 )
			{
				return new int[]{1};
			}
			int[] result = new int[digits.length + 1];
			int size = 0;
			int index = digits.length - 1;
			int v = digits[index] + 1;
			int overflow = 0;
			if ( v >= 10 )
			{
				overflow = 1;
				v -= 10;
			}
			result[size] = v;
			size++;
			for ( int i = index - 1; i >= 0; i-- )
			{
				v = digits[i] + overflow;
				if ( v >= 10 )
				{
					v = v - 10;
					overflow = 1;
				}
				else {
					overflow = 0;
				}
				result[size] = v;
				size++;
			}
			if ( overflow == 1 )
			{
				result[size] = 1;
				size++;
			}

			int[] r = new int[size];
			for ( int i = size - 1; i >= 0; i-- )
			{
				r[size - 1 - i] = result[i];
			}
			return r;
		}
	}

}
