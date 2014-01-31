
package com.actuate.wyan.other;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class Solution3
{

	@Test
	public void test( )
	{
		Assert.assertArrayEquals( new int[]{3, 4, 5, 6, 7},
				merge( new int[]{1, 2, 3}, new int[]{2, 3, 4} ) );
	}

	int[] merge( int[] A, int[] B )
	{
		if ( A == null || A.length == 0 )
		{
			return A;
		}
		if ( B == null || B.length == 0 )
		{
			return A;
		}
		ArrayList<Integer> C = new ArrayList<Integer>( );

		ArrayList<ValueProvider> values = new ArrayList<ValueProvider>(
				A.length );
		for ( int i = 0; i < A.length; i++ )
		{
			ValueProvider vp = new ValueProvider( A[i], B );
			values.add( vp );
		}

		int prev = A[0] + B[0];
		C.add( prev );
		while ( !values.isEmpty( ) )
		{
			int offset = getSmallest( values, 0, values.size( ) );
			ValueProvider vp = values.get( offset );
			int v = vp.getValue( );
			if ( !vp.next( ) )
			{
				values.remove( offset );
			}
			if ( prev != v )
			{
				C.add( v );
				prev = v;
			}
		}
		int[] r = new int[C.size( )];
		for ( int i = 0; i < r.length; i++ )
		{
			r[i] = C.get( i );
		}
		return r;
	}

	protected int getSmallest( ArrayList<ValueProvider> values, int offset,
			int size )
	{
		if ( size == 1 )
		{
			return offset;
		}
		if ( size == 2 )
		{
			return getSmaller( values, offset, offset + 1 );
		}

		int leftSize = size / 2;
		int offset1 = getSmallest( values, offset, leftSize );
		int offset2 = getSmallest( values, offset + leftSize, size - leftSize );
		return getSmaller( values, offset1, offset2 );
	}

	protected int getSmaller( ArrayList<ValueProvider> values, int offset1,
			int offset2 )
	{
		int v1 = values.get( offset1 ).getValue( );
		int v2 = values.get( offset2 ).getValue( );
		if ( v1 <= v2 )
		{
			return offset1;
		}
		return offset2;
	}

	static class ValueProvider
	{

		int v;
		int[] values;
		int index;

		ValueProvider( int v, int[] values )
		{
			this.v = v;
			this.values = values;
			this.index = 0;
		}

		int getValue( )
		{
			return v + values[index];
		}

		boolean hasValue( )
		{
			return index < values.length;
		}

		boolean next( )
		{
			index++;
			return hasValue( );
		}
	}
}
