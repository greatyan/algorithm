
package com.actuate.wyan;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class SearchInRotatedSortedArray
{

	@Test
	public void test( )
	{
		Assert.assertEquals( 1, new Solution( ).searchSplit( new int[]{2, 1} ) );
		Assert.assertEquals( 2,
				new Solution( ).searchSplit( new int[]{2, 3, 1} ) );
		Assert.assertEquals( 2,
				new Solution( ).searchSplit( new int[]{3, 4, 1, 2} ) );
		Assert.assertEquals( 0,
				new Solution( ).searchSplit( new int[]{1, 2, 3, 4} ) );
		Assert.assertEquals( 0, new Solution( ).searchSplit( new int[]{1} ) );
		Assert.assertEquals( 0, new Solution( ).searchSplit( new int[]{1, 2} ) );
		Assert.assertEquals( 0, new Solution( ).search( new int[]{1}, 1 ) );
	}

	public class Solution
	{

		public int search( int[] A, int target )
		{
			// search the split point
			int splitIndex = searchSplit( A );
			int index = Arrays.binarySearch( A, 0, splitIndex, target );
			if ( index >= 0 )
			{
				return index;
			}
			index = Arrays.binarySearch( A, splitIndex, A.length, target );
			if ( index >= 0 )
			{
				return index;
			}
			return -1;

		}

		int searchSplit( int[] A )
		{
			if ( A.length == 1 )
			{
				return 0;
			}
			if ( A.length == 2 )
			{
				if ( A[0] < A[1] )
				{
					return 0;
				}
				return 1;
			}
			int start = 0;
			int end = A.length - 1;
			int mid = ( start + end ) / 2;
			if ( A[start] < A[mid] && A[mid] < A[end] )
			{
				return 0;
			}
			do
			{
				if ( start == end - 1 )
				{
					return end;
				}
				if ( A[start] < A[mid] )
				{
					// split at mid to end
					start = mid;
					mid = ( start + end ) / 2;
					continue;
				}
				if ( A[start] > A[mid] )
				{
					// split at start to mid
					end = mid;
					mid = ( start + end ) / 2;
					continue;
				}
				return mid;
			} while ( true );
		}
	}
}
