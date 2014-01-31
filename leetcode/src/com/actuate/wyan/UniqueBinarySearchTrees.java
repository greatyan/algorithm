
package com.actuate.wyan;

public class UniqueBinarySearchTrees
{

	public static void main( String[] args )
	{
		for ( int i = 0; i < 10; i++ )
		{
			System.out.println( i + ":" + new Solution( ).numTrees( i ) );
		}
	}

	static public class Solution
	{

		public int numTrees( int n )
		{
			int[] caches = new int[n <= 3 ? 4 : n + 1];
			caches[0] = 1;
			caches[1] = 1;
			caches[2] = 2;
			caches[3] = 5;
			return numTrees( n, caches );
		}

		public int numTrees( int n, int[] caches )
		{
			if ( caches[n] != 0 )
			{
				return caches[n];
			}
			int v = 0;
			int j = n / 2;
			for ( int i = 0; i < j; i++ )
			{
				v += numTrees( i, caches ) * numTrees( n - 1 - i, caches );
			}
			v = v * 2;
			if ( n % 2 == 1 )
			{
				v += numTrees( j, caches ) * numTrees( j, caches );
			}
			caches[n] = v;
			return v;
		}
	}

}
