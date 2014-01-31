
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class CountAndSay
{

	@Test
	public void test( )
	{
		Assert.assertEquals( "1", new Solution( ).countAndSay( 1 ) );
		Assert.assertEquals( "11", new Solution( ).countAndSay( 2 ) );
		Assert.assertEquals( "21", new Solution( ).countAndSay( 3 ) );
		Assert.assertEquals( "1211", new Solution( ).countAndSay( 4 ) );
		Assert.assertEquals( "111221", new Solution( ).countAndSay( 5 ) );
	}

	public class Solution
	{

		public String countAndSay( int n )
		{

			if ( n == 1 )
			{
				return "1";
			}

			String v = countAndSay( n - 1 );
			StringBuilder sb = new StringBuilder( );
			char pc = v.charAt( 0 );
			int count = 1;
			for ( int i = 1; i < v.length( ); i++ )
			{
				if ( v.charAt( i ) == pc )
				{
					count++;
				}
				else
				{
					sb.append( Integer.toString( count ) );
					sb.append( pc );
					pc = v.charAt( i );
					count = 1;
				}
			}
			sb.append( Integer.toString( count ) );
			sb.append( pc );
			return sb.toString( );

		}
	}
}
