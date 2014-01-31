
package com.actuate.wyan;

import java.util.ArrayList;

public class Permutations
{

	public static void main( String[] args )
	{
		new Permutations( ).test( );

	}

	void printResults( ArrayList<ArrayList<Integer>> results )
	{
		for ( ArrayList<Integer> values : results )
		{
			for ( Integer v : values )
			{
				System.out.print( v );
				System.out.print( "," );
			}
			System.out.println( );
		}
	}

	public void test( )
	{
		int[] num = new int[]{0, 1,2,3};

		ArrayList<ArrayList<Integer>> result = new Solution( ).permute( num );
		printResults( result );
	}

	public class Solution
	{

		public ArrayList<ArrayList<Integer>> permute( int[] num )
		{
			ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>( );

			int[] index = new int[num.length];
			for ( int i = 0; i < num.length; i++ )
			{
				index[i] = i;
			}
			int[] values = new int[num.length];
			permute( results, values, index, num, 0 );
			return results;
		}

		ArrayList<Integer> createArray( int[] values )
		{
			ArrayList<Integer> array = new ArrayList<Integer>( values.length );
			for ( int v : values )
			{
				array.add( v );
			}
			return array;
		}

		public void permute( ArrayList<ArrayList<Integer>> results,
				int[] values, int index[], int[] num, int start )
		{
			int length = num.length - start;
			if ( length == 1 )
			{
				values[start] = num[index[start]];
				results.add( createArray( values ) );
				return;
			}
			for ( int i = 0; i < length; i++ )
			{
				int v = index[start];
				index[start] = index[start + i];
				index[start + i] = v;

				values[start] = num[index[start]];
				permute( results, values, index, num, start + 1 );
				v = index[start];
				index[start] = index[start + i];
				index[start + i] = v;
			}
		}

	}

}
