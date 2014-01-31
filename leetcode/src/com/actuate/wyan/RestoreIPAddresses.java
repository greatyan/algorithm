
package com.actuate.wyan;

import java.util.ArrayList;

public class RestoreIPAddresses
{

	public static void main( String[] args )
	{
		ArrayList<String> results = new Solution( )
				.restoreIpAddresses( "25525511135" );
		for ( String result : results )
		{
			System.out.println( result );
		}
	}

	static public class Solution
	{

		public ArrayList<String> restoreIpAddresses( String s )
		{
			ArrayList<String> results = new ArrayList<String>( );
			if ( s == null || s.length( ) < 4 )
			{
				return results;
			}
			char[] chars = s.toCharArray( );
			guessAddress( results, "", chars, 0, 4 );
			return results;
		}

		public void guessAddress( ArrayList<String> results, String prefix,
				char[] chars, int start, int remain )
		{
			if ( remain == 0 && start < chars.length )
			{
				return;
			}
			if ( remain == 0 && start == chars.length )
			{
				results.add( prefix );
				return;
			}
			// try first char
			if ( isValidIpSegment( chars, start, 1 ) )
			{
				String newPrefix = createIpPrefix( prefix, chars, start, 1 );
				guessAddress( results, newPrefix, chars, start + 1, remain - 1 );
			}
			if ( isValidIpSegment( chars, start, 2 ) )
			{
				String newPrefix = createIpPrefix( prefix, chars, start, 2 );
				guessAddress( results, newPrefix, chars, start + 2, remain - 1 );
			}
			if ( isValidIpSegment( chars, start, 3 ) )
			{
				String newPrefix = createIpPrefix( prefix, chars, start, 3 );
				guessAddress( results, newPrefix, chars, start + 3, remain - 1 );
			}
		}

		String createIpPrefix( String prefix, char[] chars, int offset, int size )
		{
			if ( prefix == null || prefix.length( ) == 0 )
			{
				return new String( chars, offset, size );
			}
			return prefix + "." + new String( chars, offset, size );
		}

		boolean isValidIpSegment( char[] chars, int offset, int size )
		{
			if ( offset + size > chars.length )
			{
				return false;
			}
			if ( chars[offset] == '0' )
			{
				if ( size == 1 )
				{
					return true;
				}
				return false;
			}
			int v = Integer.valueOf( new String( chars, offset, size ) );
			if ( v <= 255 )
			{
				return true;
			}
			return false;
		}
	}
}
