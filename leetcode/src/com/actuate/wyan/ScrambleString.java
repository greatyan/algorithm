
package com.actuate.wyan;
public class ScrambleString
{

	public static void main( String[] args )
	{
		new ScrambleString( ).test( );
	}

	public void test( )
	{
		System.out.println( new Solution( ).isScramble( "great", "rgeat" ) );
		System.out.println( new Solution( ).isScramble( "great", "rgtae" ) );
		System.out.println( new Solution( ).isScramble( "ccabcbabcbabbbbcbb",
				"bbbbabccccbbbabcba" ) );
		System.out.println( new Solution( ).isScramble( "abbbcbaaccacaacc",
				"acaaaccabcabcbcb" ) );
	}

	public class Solution
	{

		public boolean isScramble( String s1, String s2 )
		{
			if ( s1 == s2 )
			{
				return true;
			}
			if ( s1 == null || s2 == null )
			{
				return false;
			}

			if ( s1.length( ) != s2.length( ) )
			{
				return false;
			}
			return isScrabmleEqual( s1.toCharArray( ), 0, s2.toCharArray( ), 0,
					s1.length( ) );

		}

		boolean isScrabmleEqual( char[] s1, int off1, char[] s2, int off2,
				int len )
		{
			if ( len == 0 )
			{
				return true;
			}
			if ( len == 1 )
			{
				return s1[off1] == s2[off2];
			}
			if ( len == 2 )
			{
				if ( s1[off1] == s2[off2] && s1[off1 + 1] == s2[off2 + 1] )
				{
					return true;
				}
				if ( s1[off1] == s2[off2 + 1] && s1[off1 + 1] == s2[off2] )
				{
					return true;
				}
				return false;
			}

			// test if those two strings has same character
			if ( !hasSameCharacters( s1, off1, s2, off2, len ) )
			{
				return false;
			}

			for ( int size = len - 1; size >= 1; size-- )
			{
				if ( isScrabmleEqual( s1, off1, s2, off2, size )
						&& isScrabmleEqual( s1, off1 + size, s2, off2 + size,
								len - size ) )
				{
					return true;
				}
				if ( isScrabmleEqual( s1, off1, s2, off2 + len - size, size )
						&& isScrabmleEqual( s1, off1 + size, s2, off2, len
								- size ) )
				{
					return true;
				}
			}
			return false;
		}

		boolean hasSameCharacters( char[] s1, int off1, char[] s2, int off2,
				int len )
		{
			int v1 = 0;
			for ( int i = 0; i < len; i++ )
			{
				v1 += s1[off1 + i];
				v1 -= s2[off2 + i];
			}
			if ( v1 == 0 )
			{
				return true;
			}
			return false;
		}
	}
}
