
package com.actuate.wyan;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class InterleavingString
{

	public static void main( String[] args )
	{
		new InterleavingString( ).test( );
	}

	@Test
	public void test( )
	{
		Assert.assertEquals( true,
				new Solution( ).isInterleave( "aa", "ab", "aaba" ) );

		Assert.assertEquals( true,
				new Solution( ).isInterleave( "aabcc", "dbbca", "aadbcbbcac" ) );
	}

	public class Solution
	{

		HashMap<String, Boolean> cache;

		public boolean isInterleave( String s1, String s2, String s3 )
		{
			if ( s1 == null && s2 == null && s3 == null )
			{
				return true;
			}
			if ( s1 == null || s2 == null || s3 == null )
			{
				return false;
			}
			if ( s1.length( ) + s2.length( ) != s3.length( ) )
			{
				return false;
			}
			cache = new HashMap<String, Boolean>( );
			return isInterleave( s1.toCharArray( ), 0, s2.toCharArray( ), 0,
					s3.toCharArray( ), 0 );
		}

		public boolean isInterleave( char[] s1, int off1, char[] s2, int off2,
				char[] s3, int off3 )
		{
			if ( off1 >= s1.length )
			{
				return equals( s2, off2, s3, off3 );
			}
			if ( off2 >= s2.length )
			{
				return equals( s1, off1, s3, off3 );
			}
			String key = off1 + "_" + off2;
			if ( cache.containsKey( key ) )
			{
				return cache.get( key );
			}
			if ( s3[off3] == s1[off1] )
			{
				if ( isInterleave( s1, off1 + 1, s2, off2, s3, off3 + 1 ) )
				{
					cache.put( key, true );
					return true;
				}
			}
			if ( s3[off3] == s2[off2] )
			{
				if ( isInterleave( s1, off1 , s2, off2+1, s3, off3 + 1 ) )
				{
					cache.put( key, true );
					return true;
				}
			}

			cache.put( key, false );
			return false;
		}

		public boolean equals( char[] s1, int off1, char[] s2, int off2 )
		{

			if ( s1.length - off1 == s2.length - off2 )
			{
				int size = s1.length - off1;
				for ( int i = 0; i < size; i++ )
				{
					if ( s1[off1 + i] != s2[off2 + i] )
					{
						return false;
					}
				}
				return true;
			}
			return false;
		}
	}
}
