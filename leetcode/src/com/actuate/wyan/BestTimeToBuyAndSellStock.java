
package com.actuate.wyan;

public class BestTimeToBuyAndSellStock
{

	public static void main( String[] args )
	{
		System.out.println( new Solution().maxProfit( new int[] {1,2} ));
	}

	static public class Solution
	{

		public int maxProfit( int[] prices )
		{
			if ( prices == null || prices.length <= 1 )
			{
				return 0;
			}
			int profit = 0;
			int lowest = prices[0];
			for ( int i = 1; i < prices.length; i++ )
			{
				if ( lowest < prices[i] )
				{
					lowest = prices[i];
				}
				else
				{
					int v = prices[i] - lowest;
					if ( v > profit )
					{
						profit = v;
					}
				}
			}
			return profit;
		}
	}

}
