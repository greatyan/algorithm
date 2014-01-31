
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class PalindromePartitionII
{

	@Test
	public void test( )
	{
		Assert.assertEquals( 1, new Solution( ).minCut( "aaaa1" ) );
		Assert.assertEquals( 1, new Solution( ).minCut( "aab" ) );
		Assert.assertEquals(
				1,
				new Solution( )
						.minCut( "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" ) );
		Assert.assertEquals( 0, new Solution( ).minCut( "a" ) );
	}

	public class Solution
	{

		public int minCut( String s )
		{
			if ( s == null || s.length( ) == 0 )
			{
				return 0;
			}

			int[] minCuts = new int[s.length( )];
			return minCut( s.toCharArray( ), 0, Integer.MAX_VALUE, minCuts ) - 1;
		}

		public int minCut( char[] s, int offset, int remainCut, int[] minCuts )
		{
			if ( minCuts[offset] != 0 )
			{
				return minCuts[offset];
			}
			if ( remainCut <= 0 )
			{
				// don't need cut as it has exceed the minCut
				return Integer.MAX_VALUE;
			}
			int length = s.length - offset;
			if ( remainCut == 1 )
			{
				if ( isPalindrome( s, offset, length ) )
				{
					return 1;
				}
				return Integer.MAX_VALUE;
			}
			int minCut = Integer.MAX_VALUE;
			for ( int i = length; i >= 1; i-- )
			{
				if ( isPalindrome( s, offset, i ) )
				{
					if ( offset + i == s.length )
					{
						minCuts[offset] = 1;
						return 1;
					}
					int cuts = minCut( s, offset + i, remainCut - 1, minCuts );
					if ( cuts != Integer.MAX_VALUE )
					{
						cuts = cuts + 1;
						if ( minCut > cuts )
						{
							minCut = cuts;
							remainCut = minCut;
						}
					}
				}
			}
			if ( minCut != Integer.MAX_VALUE )
			{
				minCuts[offset] = minCut;
			}
			return minCut;
		}

		boolean isPalindrome( char[] s, int offset, int size )
		{
			if ( size == 1 )
			{
				return true;
			}

			int length = size / 2;
			int left = offset + length - 1;
			int right = left + 1;
			if ( size % 2 != 0 )
			{
				right++;
			}
			for ( int i = 0; i < length; i++ )
			{
				if ( s[left] != s[right] )
				{
					return false;
				}
				left--;
				right++;
			}
			return true;
		}
	}
}
