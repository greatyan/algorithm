
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class LongestSubstringWithoutRepeatingCharacters
{

	@Test
	public void test( )
	{
		Assert.assertEquals( 0, new Solution( ).lengthOfLongestSubstring( "" ) );
		Assert.assertEquals( 1,
				new Solution( ).lengthOfLongestSubstring( "b" ) );
		Assert.assertEquals( 1,
				new Solution( ).lengthOfLongestSubstring( "bbb" ) );
		Assert.assertEquals( 3,
				new Solution( ).lengthOfLongestSubstring( "abcabcbb" ) );

	}

	public class Solution
	{

		public int lengthOfLongestSubstring( String s )
		{
			if ( s == null || s.length( ) == 0 )
			{
				return 0;
			}
			return lengthOfLongestSubstring( s.toCharArray( ), 0 );
		}

		int lengthOfLongestSubstring( char[] chars, int off )
		{
			int length = 1;
			for ( int i = chars.length - 1; i >= off; i-- )
			{
				length = Math.max( length, length( chars, i ) );
			}
			return length;
		}

		int length( char[] chars, int off )
		{
			for ( int i = off + 1; i < chars.length; i++ )
			{
				for ( int j = off; j < i; j++ )
				{
					if ( chars[i] == chars[j] )
					{
						return i - off;
					}
				}
			}
			return chars.length - off;
		}
	}
}
