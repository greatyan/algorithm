
package com.actuate.wyan;

public class ReverseInteger
{

	public static void main( String[] args )
	{
		System.out.println( new Solution( ).reverse( 0 ) );
		System.out.println( new Solution( ).reverse( 123 ) );
		System.out.println( new Solution( ).reverse( -123 ) );
		System.out.println( new Solution( ).reverse( -100 ) );
		System.out.println( new Solution( ).reverse( 100 ) );
	}

	static public class Solution
	{

		public int reverse( int x )
		{
			int result = 0;
			int remain = x;
			if ( remain < 0 )
			{
				remain = -remain;
			}
			while ( remain > 0 )
			{
				int v = remain % 10;
				remain = remain / 10;
				result = result * 10 + v;
			}
			if ( x < 0 )
			{
				return -result;
			}
			return result;
		}
	}

}
