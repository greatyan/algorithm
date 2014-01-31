
package com.actuate.wyan.sort;

public class MergeSort
{

	public static void main( String[] args )
	{

		int[] values = new int[]{8, 6, 7, 4, 5, 3, 2, 1};
		new MergeSort( ).sort( values );
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

	void sort( int[] values )
	{
		int[] results = new int[values.length];
		merge_sort( values, 0, values.length, results );
		print( values );
	}

	void merge_sort( int[] values, int start, int end, int[] results )
	{
		// sort left part and right part
		int size = end - start;
		if ( size == 1 )
		{
			results[start] = values[start];
			swap_count++;
			return;
		}
		else if ( size == 2 )
		{
			if ( values[start] > values[start + 1] )
			{
				int v = values[start];
				values[start] = values[start + 1];
				values[start + 1] = v;
				swap_count++;
			}
			compare_count++;
			return;
		}

		size = size / 2;
		int left_start = start;
		int left_end = start + size;
		int right_start = left_start + size;
		int right_end = end;

		merge_sort( values, left_start, left_end, results );
		merge_sort( values, right_start, right_end, results );

		int left_index = left_start;
		int right_index = right_start;
		int merge_index = start;
		while ( left_index < left_end && right_index < right_end )
		{
			if ( values[left_index] < values[right_index] )
			{
				results[merge_index] = values[left_index];
				swap_count++;
				left_index++;
			}
			else
			{
				results[merge_index] = values[right_index];
				swap_count++;
				right_index++;
			}
			merge_index++;
		}
		while ( left_index < left_end )
		{
			results[merge_index] = values[left_index];
			swap_count++;
			left_index++;
			merge_index++;
		}
		while ( right_index < right_end )
		{
			results[merge_index] = values[right_index];
			swap_count++;
			merge_index++;
			right_index++;
		}
		for ( int i = start; i < end; i++ )
		{
			values[i] = results[i];
			swap_count++;
		}
	}

}
