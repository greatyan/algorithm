
package com.actuate.wyan;

import java.util.ArrayList;

import org.junit.Test;

public class Combinations
{

	@Test
	public void test( )
	{
		new Solution( ).combine( 2, 1 );
	}

	public class Solution
	{

		public ArrayList<ArrayList<Integer>> combine( int n, int k )
		{
			if ( k > n )
			{
				return null;
			}
			ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>( );
			if ( k == n )
			{
				ArrayList<Integer> a = new ArrayList<Integer>( n );
				for ( int i = 1; i <= n; i++ )
				{
					a.add( i );
				}
				results.add( a );
				return results;
			}

			int[] values = new int[k];
			create( 1, n, 0, values, results );
			return results;
		}

		void create( int start, int end, int index, int[] values,
				ArrayList<ArrayList<Integer>> results )
		{
			// not enough digitals
			if ( ( values.length - index ) > ( end - start + 1 ) )
			{
				return;
			}
			if ( index == values.length )
			{
				// add the values into
				ArrayList<Integer> a = new ArrayList<Integer>( values.length );
				for ( int v : values )
				{
					a.add( v );
				}
				results.add( a );
				return;
			}

			for ( int i = start; i <= end; i++ )
			{
				values[index] = i;
				create( i + 1, end, index + 1, values, results );
			}
		}

	}
}
