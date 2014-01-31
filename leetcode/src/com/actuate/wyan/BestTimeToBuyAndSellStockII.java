
package com.actuate.wyan;

public class BestTimeToBuyAndSellStockII
{

	public static void main( String[] args )
	{
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
			for ( int i = 1; i < prices.length; i++ )
			{
				if ( prices[i] > prices[i-1])
				{
					profit += prices[i] - prices[i-1];
				}
			}
			return profit;
		}

	}

}
