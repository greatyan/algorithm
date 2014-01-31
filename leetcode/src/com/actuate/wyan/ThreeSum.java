
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.HashSet;

public class ThreeSum
{

	public static void main( String[] args )
	{
		int[] input = new int[]{-1, 0, 1, 2, -1, -4};
		ArrayList<ArrayList<Integer>> results = new Solution( )
				.threeSum( input );
		for ( ArrayList<Integer> result : results )
		{
			System.out.print( "[" );
			for ( Integer v : result )
			{
				System.out.print( v );
				System.out.print( "," );
			}
			System.out.println( "]" );
		}
	}

	static public class Solution
	{

		public ArrayList<ArrayList<Integer>> threeSum( int[] num )
		{
			HashSet<Result> results = new HashSet<Result>( );
			for ( int i1 = 0; i1 < num.length; i1++ )
			{
				int a = num[i1];
				for ( int i2 = i1 + 1; i2 < num.length; i2++ )
				{
					int b = num[i2];
					for ( int i3 = i2 + 1; i3 < num.length; i3++ )
					{
						int c = num[i3];
						if ( a + b + c == 0 )
						{
							results.add( new Result( a, b, c ) );
						}
					}
				}
			}
			ArrayList<ArrayList<Integer>> arrays = new ArrayList<ArrayList<Integer>>(
					results.size( ) );
			for ( Result result : results )
			{
				arrays.add( result.toArray( ) );
			}
			return arrays;
		}

		class Result
		{

			int v1;
			int v2;
			int v3;

			public int hashCode( )
			{
				return v1 * 13 + v2 * 17 + v3 * 19;
			}

			public boolean equals( Object v )
			{
				if ( v instanceof Result )
				{
					Result r = (Result) v;
					return v1 == r.v1 && v2 == r.v2 && v3 == r.v3;
				}
				return false;
			}

			public ArrayList<Integer> toArray( )
			{
				ArrayList<Integer> array = new ArrayList<Integer>( 3 );
				array.add( v1 );
				array.add( v2 );
				array.add( v3 );
				return array;
			}

			Result( int a, int b, int c )
			{
				if ( a < b )
				{
					if ( b < c )
					{
						// a,b,c
						v1 = a;
						v2 = b;
						v3 = c;
					}
					else if ( c < a )
					{
						// c, a, b
						v1 = c;
						v2 = a;
						v3 = b;
					}
					else
					{
						// a, c, b
						v1 = a;
						v2 = c;
						v3 = b;
					}
				}
				else
				{
					if ( c < b )
					{
						// c,b,a
						v1 = c;
						v2 = b;
						v3 = a;
					}
					else if ( c > a )
					{
						// b, a, c
						v1 = b;
						v2 = a;
						v3 = c;
					}
					else
					{
						// b, c, a
						v1 = b;
						v2 = c;
						v3 = a;
					}
				}
			}

		}

	}
}
