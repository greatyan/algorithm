
package com.actuate.wyan;

import java.util.Arrays;


public class MedianOfTwoSortedArrays
{

	public static void main( String[] args )
	{
		int[] a  = new int[]{4, 2, 3, 4, 1};
		int[] b = new int[]{};
		double number = new Solution( ).findMedianSortedArrays(a,b);
		System.out.println( number );
	}

	static public class Solution
	{

		public double findMedianSortedArrays( int A[], int B[] )
		{
			if ( A[0] < B[0] )
			{
				return find( A, 0, A.length, B, 0, B.length );
			}
			return find( B, 0, B.length, A, 0, A.length );
		}
		
		public double find(int[] a, int start_a, int end_a, int[] b, int start_b, int end_b, int pos)
		{
			if ( a[end_a - 1] <= b[start_b])
			{
				int size_a = end_a - start_a;
				int size_b = end_b - start_b;
				int size = size_a + size_b;
				int mid = size / 2;
				if ( size == mid + mid) 
				{
					int v1 = getValue( a, start_a, end_a, b, start_b, end_b, mid-1);
					int v2 = getValue( a, start_a, size_a, b, start_b, size_b, mid); 
					return (v1 + v2 ) /2.0 ;
				}
				else {
					int v = getValue(a, start_a, size_a, b, start_b, size_b, mid);
					return v;
				}
			}
			
			
			int index_a = start_a + size_a / 2;
			int index_b = start_b + size_b / 2;
			int value_a = a[index_a];
			int value_b = b[index_b];
			
			while ( value_a != value_b)
			{
				if ( value_a < value_b) {
					//search value_b in index_a to size
					find(a, index_a, )
					//search value_a in start_b to index_b
				}
				else  {
					//search value_b from index_a to end_a;
					//search value_a from index_b to end_b;
				}
			}
			return (value_a + value_b) / 2;
		}

		public int find( int[] array, int start, int end, int value )
		{
			return Arrays.binarySearch( array, start, end, value );
		}

		int getValue( int[] a, int start_a, int size_a, int[] b, int start_b,
				int size_b, int pos )
		{
			if ( pos < size_a )
			{
				return a[start_a + pos];
			}
			return b[start_b + pos - size_a];
		}
	}
}
