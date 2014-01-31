
package com.actuate.wyan;

import org.junit.Test;

public class Sqrt
{

	@Test
	public void test( )
	{
		for ( int i = 1; i <= 100; i++ )
		{
			System.out.println( i + ":" + new Solution( ).sqrt( i ) );

		}
	}

	public class Solution
	{

		public int sqrt( int x )
		{
			// get the first two bits
			int mask = 0xC0000000;
			int bits = 30;
			int result = 0;
			int remain = ( x & mask ) >> bits;
			int y = 0;
			while ( bits >= 0 )
			{
				if ( remain > 0 && remain >= y )
				{
					result = ( result << 1 ) + 1;
					remain -= y;
				}
				else
				{
					result = ( result << 1 );
				}
				bits -= 2;
				mask >>>= 2;
				if ( bits >= 0 )
				{
					remain = ( remain << 2 ) + ( ( x & mask ) >> bits );
					y = ( result << 2 ) + 1;
				}
			}
			return result;
		}
	}
}
