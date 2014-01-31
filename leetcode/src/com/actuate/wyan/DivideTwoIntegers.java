
package com.actuate.wyan;

public class DivideTwoIntegers
{

	public static void main( String[] args )
	{
		int dividend = -2147483648;
		int divisor = 2;
		int number = new Solution( ).divide( dividend, divisor );
		System.out.println( number );
	}

	static public class Solution
	{

		public int divide( int dividend, int divisor )
		{
			//return dividend / divisor;
			return (int) divide( (long) dividend, (long) divisor );
		}

		public long divide( long dividend, long divisor )
		{
			boolean negative = false;
			if ( dividend == 0 )
			{
				return 0;
			}
			if ( divisor == 0 )
			{
				return 0;
			}

			if ( dividend < 0 )
			{
				dividend = -dividend;
				if ( divisor < 0 )
				{
					divisor = -divisor;
				}
				else
				{
					negative = true;
				}
			}

			if ( divisor < 0 )
			{
				divisor = -divisor;
				negative = true;
			}

			if ( divisor == 1 )
			{
				return negative ? -dividend : dividend;
			}

			if ( dividend < divisor )
			{
				return 0;
			}

			long[] results = new long[32];
			long[] values = new long[32];

			values[0] = divisor;
			results[0] = 1;
			long remain = dividend;
			int index = 0;
			int result = 0;
			while ( remain >= values[index] )
			{
				remain -= values[index];
				result += results[index];
				if ( remain > values[index] )
				{
					values[index + 1] = values[index] + values[index];
					results[index + 1] = results[index] + results[index];
					index++;
				}
			}

			// remain is less than values[index], start from index - 1;
			while ( index >= 0 )
			{
				if ( remain >= values[index] )
				{
					remain -= values[index];
					result += results[index];
				}
				index--;
			}
			return negative ? -result : result;
		}
	}
}
