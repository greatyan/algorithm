
package com.actuate.wyan.sort;

public class HeapSort
{

	public static void main( String[] args )
	{

		int[] values = new int[]{8, 6, 7, 4, 5, 3, 2, 1};
		new HeapSort( ).sort( values );
		for ( int v : values )
		{
			System.out.print( v );
			System.out.print( "," );
		}
	}

	void sort( int[] values )
	{
		sort( values, 0, values.length );
	}

	int swap_count;
	int compare_count;

	void print( int[] values )
	{
		for ( int v : values )
		{
			System.out.print( v );
			System.out.print( "," );
		}
		System.out.println( );
		System.out.println( "swap:" + swap_count + " comp:" + compare_count );
	}

	void sort( int[] values, int start, int length )
	{
		int end = length - 1;
		while ( end > start )
		{
			heapify( values, start, end );
			int v = values[start];
			values[start] = values[end];
			values[end] = v;
			swap_count += 2;
			end--;
			print( values );
		}
		print( values );

	}

	void heapify( int[] values, int start, int end )
	{
		int root = start;
		int left_start = start * 2 + 1;
		int right_start = start * 2 + 2;
		if ( left_start <= end )
		{
			heapify( values, left_start, end );
			if ( values[root] < values[left_start] )
			{
				int value = values[root];
				values[root] = values[left_start];
				values[left_start] = value;
				swap_count += 2;
				compare_count += 1;
			}
		}
		if ( right_start <= end )
		{
			heapify( values, right_start, end );
			if ( values[root] < values[right_start] )
			{
				int value = values[root];
				values[root] = values[right_start];
				values[right_start] = value;
				swap_count += 2;
				compare_count += 1;
			}
		}
	}
}
