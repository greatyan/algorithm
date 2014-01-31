
package com.actuate.wyan;

import java.util.ArrayList;

public class GrayCode
{

	public static void main( String[] args )
	{
		new GrayCode( ).test( );
	}

	void printResult( ArrayList<Integer> results )
	{
		for ( int v : results )
		{
			System.out.println( Integer.toBinaryString( v ) + " --> " + v );
		}
	}

	public void test( )
	{
		ArrayList<Integer> results = new Solution( ).grayCode( 8 );
		printResult( results );

	}

	public class Solution
	{

		public ArrayList<Integer> grayCode( int n )
		{
			ArrayList<Integer> results = new ArrayList<Integer>( );
			if ( n == 0 )
			{
				results.add(0);
				return results;
			}

			results.add( 0 );
			results.add( 1 );

			int v = 1;
			for ( int i = 1; i < n; i++ )
			{
				v = v << 1;
				int length = results.size( );
				for ( int j = length - 1; j >= 0; j-- )
				{
					int value = results.get( j ) | v;
					results.add( value );
				}
			}
			return results;
		}
	}
}
