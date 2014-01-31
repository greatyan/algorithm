
package com.actuate.wyan;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class PalindromePartitioning
{

	void assertEquals( ArrayList<ArrayList<String>> results, String[] values )
	{
		Assert.assertEquals( results.size( ), values.length );
		for ( int i = 0; i < values.length; i++ )
		{
			StringBuilder sb = new StringBuilder( );
			ArrayList<String> result = results.get( i );
			for ( String s : result )
			{
				sb.append( s );
				sb.append( "," );
			}
			if ( sb.length( ) > 0 )
			{
				sb.setLength( sb.length( ) - 1 );
			}
			Assert.assertEquals( sb.toString( ), values[i] );
		}
	}

	@Test
	public void test( )
	{
		assertEquals( new Solution( ).partition( "aab" ), new String[]{"a,a,b",
				"aa,b"} );
	}

	public class Solution
	{

		public ArrayList<ArrayList<String>> partition( String s )
		{
			if ( s == null || s.length( ) == 0 )
			{
				return null;
			}
			ArrayList<String> prefix = new ArrayList<String>( );
			ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>( );
			partition( s.toCharArray( ), 0, prefix, results );
			return results;
		}

		void partition( char[] s, int offset, ArrayList<String> prefix,
				ArrayList<ArrayList<String>> results )
		{
			int length = s.length - offset;
			for ( int i = 1; i <= length; i++ )
			{
				if ( isPalindrome( s, offset, i ) )
				{
					if ( offset + i == s.length )
					{
						ArrayList<String> result = new ArrayList<String>( );
						result.addAll( prefix );
						result.add( new String( s, offset, i ) );
						results.add( result );
					}
					else
					{
						prefix.add( new String( s, offset, i ) );
						partition( s, offset + i, prefix, results );
						prefix.remove( prefix.size( ) - 1 );
					}
				}
			}
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
