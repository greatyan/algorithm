
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class ValidPalindrome
{

	@Test
	public void test( )
	{
		Assert.assertEquals( false, new Solution( ).isPalindrome( "ab" ) );

	}

	public class Solution
	{

		public boolean isPalindrome( String s )
		{
			if ( s.length( ) == 0 )
			{
				return true;
			}
			char[] chars = new char[s.length( )];
			int index = 0;
			for ( int i = 0; i < s.length( ); i++ )
			{
				char ch = s.charAt( i );
				if ( Character.isLetterOrDigit( ch ) )
				{
					chars[index] = ch;
					index++;
				}
			}
			if ( index <= 1 )
			{
				return true;
			}
			return isPalindrome( chars, index );

		}

		boolean isPalindrome( char[] ch, int size )
		{
			int length = size / 2;
			int left = length - 1;
			int right = left + 1;
			if ( size % 2 != 0 )
			{
				right += 1;
			}
			for ( int i = 0; i < length; i++ )
			{
				if ( Character.toLowerCase( ch[left] ) != Character
						.toLowerCase( ch[right] ) )
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
