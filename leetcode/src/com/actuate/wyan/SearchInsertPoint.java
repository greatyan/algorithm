
package com.actuate.wyan;

public class SearchInsertPoint
{

	public static void main( String[] args )
	{
		System.out.println( new Solution( ).searchInsert( new int[]{1,3}, 2 ) );
	}

	static public class Solution
	{

		public int searchInsert( int[] A, int target )
		{
			int start = 0;
			int end = A.length - 1;
			do
			{
				int mid = ( start + end ) / 2;

				if ( A[mid] == target )
				{
					return mid;
				}
				if ( A[mid] < target )
				{
					start = mid + 1;
				}
				if ( target < A[mid] )
				{
					end = mid - 1;
				}
			} while ( start <= end );
			return start;

		}
	}

}
