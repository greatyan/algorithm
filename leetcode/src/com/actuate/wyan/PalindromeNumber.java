
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class PalindromeNumber
{

	@Test
	public void test( )
	{
		Assert.assertEquals( true, new Solution( ).isPalindrome( 0 ) );
		Assert.assertEquals( true, new Solution( ).isPalindrome( 1 ) );
		Assert.assertEquals( true, new Solution( ).isPalindrome( 11 ) );
		Assert.assertEquals( true, new Solution( ).isPalindrome( 101 ) );
		Assert.assertEquals( false, new Solution( ).isPalindrome( 102 ) );
		Assert.assertEquals( true, new Solution( ).isPalindrome( 121 ) );
		Assert.assertEquals( true, new Solution( ).isPalindrome( 121121 ) );
		Assert.assertEquals( true, new Solution( ).isPalindrome( 12121 ) );
	}

	public class Solution
	{

		public boolean isPalindrome( int x )
		{
			if ( x < 0 )
			{
				return false;
			}
			int value = x;
			int scale = 1;
			while ( value > 0 )
			{
				value = value / 10;
				if ( value > 0 )
				{
					scale = scale * 10;
				}
			}

			while ( x > 0 )
			{
				int lastDigital = x % 10;
				int firstDigital = x / scale;
				if ( lastDigital != firstDigital )
				{
					return false;
				}
				x = x % scale / 10;
				scale = scale / 100;
			}
			return true;
		}
	}
}
