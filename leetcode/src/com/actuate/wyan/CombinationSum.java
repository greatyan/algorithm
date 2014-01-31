
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class CombinationSum
{

	@Test
	public void testIndex( )
	{
		int[] index = new int[]{0};
		StringBuilder sb = new StringBuilder( );
		while ( new Solution( ).increaseIndex( index, 7 ) != -1 )
		{
			sb.append( "[" );
			for ( int i = 0; i < index.length; i++ )
			{
				sb.append( index[i] );
				sb.append( "," );
			}
			sb.setLength( sb.length( ) - 1 );
			sb.append( "]" );
		}
		Assert.assertEquals( "[1][2][3][4][5][6][7]", sb.toString( ) );
	}

	@Test
	public void testCombine( )
	{
		Assert.assertArrayEquals( new String[]{"7", "2,2,3"},
				toString( new Solution( ).combinationSum(
						new int[]{2, 3, 6, 7}, 7 ) ) );

	}

	String[] toString( ArrayList<ArrayList<Integer>> results )
	{
		String[] strs = new String[results.size( )];
		StringBuilder sb = new StringBuilder( );
		for ( int i = 0; i < strs.length; i++ )
		{
			for ( Integer v : results.get( i ) )
			{
				sb.append( v );
				sb.append( "," );
			}
			sb.setLength( sb.length( ) - 1 );
			strs[i] = sb.toString( );
			sb.setLength( 0 );
		}
		return strs;
	}

	public class Solution
	{

		public ArrayList<ArrayList<Integer>> combinationSum( int[] candidates,
				int target )
		{
			ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>( );
			Arrays.sort( candidates );

			int maxDigit = ( target + 1 ) / candidates[0];
			int minDigit = ( target + 1 ) / candidates[candidates.length - 1];

			for ( int i = minDigit; i <= maxDigit; i++ )
			{
				ArrayList<ArrayList<Integer>> res = combinationSum( candidates,
						i, target );
				if ( res != null && !res.isEmpty( ) )
				{
					results.addAll( res );
				}
			}
			return results;
		}

		public ArrayList<ArrayList<Integer>> combinationSum( int[] candidates,
				int length, int target )
		{
			ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>( );

			if ( length == 0 )
			{
				if ( Arrays.binarySearch( candidates, target ) >= 0 )
				{
					ArrayList<Integer> r = new ArrayList<Integer>( );
					r.add( target );
					results.add( r );
					return results;
				}
			}
			int[] index = new int[length];
			int[] sums = new int[length + 1];
			int maxIndex = candidates.length - 1;

			for ( int i = 0; i < length; i++ )
			{
				sums[i + 1] = sums[i] + candidates[0];
			}

			if ( sums[length] == target )
			{
				results.add( createEntry( candidates, index ) );
				return results;
			}

			int level = 0;
			while ( ( level = increaseIndex( index, maxIndex ) ) != -1 )
			{
				for ( int i = level; i < length; i++ )
				{
					sums[i + 1] = sums[i] + candidates[index[i]];
				}
				if ( sums[length] == target )
				{
					results.add( createEntry( candidates, index ) );
				}
			}
			return results;
		}

		protected int increaseIndex( int[] index, int maxValue )
		{
			for ( int i = index.length - 1; i >= 0; i-- )
			{
				if ( index[i] + 1 <= maxValue )
				{
					index[i] = index[i] + 1;
					for ( int j = i + 1; j < index.length; j++ )
					{
						index[j] = index[j - 1];
					}
					return i;
				}
			}
			return -1;
		}

		ArrayList<Integer> createEntry( int[] candicates, int[] index )
		{
			ArrayList<Integer> values = new ArrayList<Integer>( index.length );
			for ( int i = 0; i < index.length; i++ )
			{
				values.add( candicates[index[i]] );
			}
			return values;
		}
	}
}
