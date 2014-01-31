
package com.actuate.wyan;

public class Candy
{

	public static void main( String[] args )
	{
		int[] ratings = new int[]{4, 2, 3, 4, 1};
		int number = new Solution( ).candy( ratings );
		System.out.println( number );
	}

	static public class Solution
	{

		public int candy( int[] ratings )
		{
			if ( ratings == null || ratings.length == 0 )
			{
				return 0;
			}
			int decreaseCount = 0;
			int count = 1;
			int total = 1;
			for ( int i = 1; i < ratings.length; i++ )
			{
				if ( ratings[i] < ratings[i - 1] )
				{
					decreaseCount++;
					count--;
				}
				else if ( ratings[i] == ratings[i - 1] )
				{
					if ( decreaseCount != 0 )
					{
						total += adjust( decreaseCount, count );
					}
					decreaseCount = 0;
					count = 1;
				}
				else
				/* > */{
					if ( decreaseCount != 0 )
					{
						total += adjust( decreaseCount, count );
						count = 1;
					}
					decreaseCount = 0;
					count++;
				}
				total += count;
			}
			total += adjust( decreaseCount, count );
			return total;
		}

		int adjust( int decreaseCount, int count )
		{
			if ( count > 0 )
			{
				return -decreaseCount * ( count - 1 );
			}
			return ( decreaseCount + 1 ) * ( 1 - count );
		}
	}
}
