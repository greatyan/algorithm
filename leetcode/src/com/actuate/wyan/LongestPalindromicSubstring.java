
package com.actuate.wyan;

public class LongestPalindromicSubstring
{

	public static void main( String[] args )
	{

		new LongestPalindromicSubstring( ).test( );

	}

	public void test( )
	{
		System.out.println( new Solution( ).longestPalindrome( "baab" ) );
	}

	public class Solution
	{

		public String longestPalindrome( String s )
		{
			if ( s == null || s.length( ) == 0 )
			{
				return "";
			}
			char[] chars = s.toCharArray( );
			int start = 0;
			int maxLength = 0;
			for ( int i = 0; i < chars.length; i++ )
			{
				int s1 = palindrome( chars, i, i );
				if ( s1 > 0 )
				{
					int length = s1 * 2 - 1;
					if ( length > maxLength )
					{
						start = i - s1 + 1;
						maxLength = length;
					}
				}
				int s2 = palindrome( chars, i, i + 1 );
				if ( s2 > 0 )
				{
					int len = s2 * 2;
					if ( len > maxLength )
					{
						start = i - s2 + 1;
						maxLength = len;
					}
				}
			}
			return s.substring( start, start + maxLength );
		}

		int palindrome( char[] chars, int leftIndex, int rightIndex )
		{
			int maxsize = Math.min( leftIndex + 1, chars.length - rightIndex );
			if ( maxsize == 0 )
			{
				return 0;
			}
			int size = 0;
			do
			{
				if ( chars[leftIndex - size] == chars[rightIndex + size] )
				{
					size++;
					continue;
				}
				break;
			} while ( size < maxsize );
			return size;
		}
	}
}
