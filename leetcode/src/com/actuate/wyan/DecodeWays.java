
package com.actuate.wyan;

import java.util.HashMap;

public class DecodeWays
{

	public static void main( String[] args )
	{
		String input = "10";
		int number = new Solution( ).numDecodings( input );
		System.out.println( number );
	}

	static public class Solution
	{

		public int numDecodings( String s )
		{
			if ( s.length( ) == 0 )
			{
				return 0;
			}
			return numDecodings( s.toCharArray( ), 0 );
		}

		HashMap<Integer, Integer> cachedDecordings = new HashMap<Integer, Integer>( );

		int numDecodings( char[] chars, int start )
		{
			Integer value = cachedDecordings.get( start );
			if ( value != null )
			{
				return value.intValue( );
			}
			int number = doNumDecordings( chars, start );
			cachedDecordings.put( start, number );
			return number;
		}

		int doNumDecordings( char[] chars, int start )
		{
			// if it is the last character
			if ( start >= chars.length )
			{
				// should never come here
				return 1;
			}

			// this is the last one
			if ( start == chars.length - 1 )
			{
				char ch = chars[start];
				if ( ch >= '1' && ch <= '9' )
				{
					return 1;
				}
				return 0;
			}

			// remain more than 1 characters
			char ch1 = chars[start];
			char ch2 = chars[start + 1];

			if ( ch1 == '1' )
			{
				if ( ch2 == '0' )
				{
					return numDecodings( chars, start + 2 );
				}
				return numDecodings( chars, start + 1 )
						+ numDecodings( chars, start + 2 );
			}
			if ( ch1 == '2' )
			{
				if ( ch2 == '0' )
				{
					return numDecodings( chars, start + 2 );
				}
				if ( ch2 >= '1' && ch2 <= '6' )
				{
					return numDecodings( chars, start + 1 )
							+ numDecodings( chars, start + 2 );
				}
				return numDecodings( chars, start + 1 );
			}
			if ( ch1 >= '3' && ch1 <= '9' )
			{
				return numDecodings( chars, start + 1 );
			}
			return 0;
		}
	}
}
