
package com.actuate.wyan;


public class RegularExpressionMatching
{

	public static void main( String[] args )
	{
		new RegularExpressionMatching( ).test( );
	}

	public void test( )
	{

		isMatch( "", "" );
		isMatch( "", "*" );
		isMatch( "", "?" );
		isMatch( "aa", "a" );
		isMatch( "aa", "aa" );
		isMatch( "aaa", "aa" );
		isMatch( "aa", "a*" );
		isMatch( "aa", ".*" );
		isMatch( "ab", ".*" );
		isMatch( "aab", "c*a*b" );
	}

	public void isMatch( String s, String p )
	{
		System.out.print( s + "\t" + p + ":" );
		System.out.println( new Solution( ).isMatch( s, p ) );
	}

	public class Solution
	{

		public boolean isMatch( String s, String p )
		{
			if ( s.length( ) == 0 && p.length( ) == 0 )
			{
				return true;
			}
			return isMatch( s.toCharArray( ), 0, p.toCharArray( ), 0 );
		}

		public boolean isMatch( char[] s, int start1, char[] p, int start2 )
		{
			//both are end
			if ( start1 == s.length && start2 == p.length )
			{
				return true;
			}
			//pattern ends while string doesn't
			if ( start2 >= p.length )
			{
				return false;
			}
			for ( ; start2 < p.length; start2++ )
			{
				char pc = p[start2];
				switch ( pc )
				{
					case '*' :
						// error, have no preceding characters, skip it
						continue;
					case '.' :
						//test if it is a .*
						if ( start2 + 1 < p.length && p[start2 + 1] == '*' )
						{
							start2++;
							// match 0 to arbitrary
							for ( int i = start1; i <= s.length; i++ )
							{
								if ( isMatch( s, i, p, start2 + 1 ) )
								{
									return true;
								}
							}
							return false;
						}
						else
						{
							// match any character
							if ( start1 >= s.length )
							{
								return false;
							}
							return isMatch( s, start1 + 1, p, start2 + 1 );
						}
					default :
						//test if it is a *
						if ( start2 + 1 < p.length && p[start2 + 1] == '*' )
						{
							start2++;
							// match 0
							if ( isMatch( s, start1, p, start2 + 1 ) )
							{
								return true;
							}
							//match at least 1
							for ( int i = start1; i < s.length; i++ )
							{
								if ( s[i] == pc )
								{
									if ( isMatch( s, i+1, p, start2 + 1 ) )
									{
										return true;
									}
									continue;
								}
								break;
							}
							return false;
						}
						else
						{
							if ( start1 >= s.length )
							{
								return false;
							}
							if ( s[start1] != pc )
							{
								return false;
							}
							start1++;
						}
				}
			}
			if ( start1 == s.length )
			{
				return true;
			}
			return false;
		}
	}
}
