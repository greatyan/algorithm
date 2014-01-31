
package com.actuate.wyan;

public class GasStation
{

	public class Solution
	{

		public int canCompleteCircuit( int[] gas, int[] cost )
		{
			if ( gas == null|| gas.length == 0) 
			{
				return -1;
			}
			int number = gas.length;
			int index = 0;
			int totalRemain = 0;
			int minRemain = Integer.MAX_VALUE;
			for ( int i = 0; i < number; i++ )
			{
				int remain = gas[i] - cost[i];
				totalRemain += remain;
				if ( minRemain > totalRemain) {
					minRemain = totalRemain;
					index = i;
				}
			}
			if ( totalRemain < 0)
			{
				return -1;
			}
			return (index + 1) % number;
		}

		public int bf( int[] gas, int[] cost )
		{
			int number = gas.length;
			for ( int i = 0; i < number; i++ )
			{
				int v = 0;
				for ( int j = 0; j < number; j++ )
				{
					int index = ( i + j ) % number;
					v += gas[index];
					v -= cost[index];
					if ( v < 0 )
					{
						break;
					}
				}
				if ( v >= 0 )
				{
					return i;
				}
			}
			return -1;
		}
	}
}
