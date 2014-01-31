
package com.actuate.wyan.other;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class Solution2
{

	@Test
	public void testMod( )
	{
		Assert.assertEquals( 0, new Value( 1, 1 ).mod( 1 ) );
		Assert.assertEquals( 0, new Value( 1, 1 ).mod( 2 ) );
		Assert.assertEquals( 0, new Value( 1, 1 ).mod( 3 ) );
		Assert.assertEquals( 2, new Value( 1, 1 ).mod( 4 ) );
		Assert.assertEquals( 1, new Value( 1, 1 ).mod( 5 ) );
		Assert.assertEquals( 0, new Value( 1, 1 ).mod( 6 ) );
		Assert.assertEquals( 6, new Value( 1, 1 ).mod( 7 ) );
		Assert.assertEquals( 2, new Value( 2, 1 ).mod( 5 ) );
		Assert.assertEquals( 3, new Value( 1, 2 ).mod( 5 ) );
		// Assert.assertEquals( 0, new Value( Integer.MAX_VALUE,
		// Integer.MAX_VALUE ).mod( 6 ) );
	}

	@Test
	public void testCalN( )
	{
		ArrayList<Value> golden = create10K( );
		for ( int i = 0; i < 1000; i++ )
		{
			Assert.assertEquals( golden.get( i ), getK( i ) );
		}
		Assert.assertEquals( new Value( 46886, 22475 ),
				getK( Integer.MAX_VALUE ) );
		Assert.assertEquals( new Value( 4373, 70860 ),
				getK( 2 * (long) Integer.MAX_VALUE ) );
	}

	int calculate( long k, int n )
	{
		Value v = getK( k );
		return v.mod( n );
	}

	Value getK( long k )
	{
		double upperValue = LOG3;
		double lowerValue = 0;
		long lowerCount = 0;
		long upperCount = 0;
		int index = 0;
		do
		{
			lowerCount = upperCount;
			upperCount += Math.floor( upperValue );
			lowerValue = upperValue;
			upperValue += LOG3;
			index++;
		} while ( upperCount <= k );

		ArrayList<Value> values = new ArrayList<Value>( );
		double log3 = LOG3;
		for ( int j = 1; j <= index; j++ )
		{
			// create index
			int i = (int) ( lowerValue - log3 );
			double v = i + log3;
			while ( v < upperValue )
			{
				if ( v > lowerValue )
				{
					values.add( new Value( i, j ) );
				}
				v += 1;
				i++;
			}
			log3 += LOG3;
		}
		Collections.sort( values );
		return values.get( (int) ( k - lowerCount ) );
	}

	static int mode( int base, int exp, int value )
	{
		int upExp = log( base, value );
		if ( upExp == 0 )
		{
			return value;
		}
		int upValue = pow( base, upExp );
		int upMod = upValue % value;
		int mod = 1;
		while ( exp > upExp )
		{
			mod = ( mod * upMod ) % value;
			exp -= upExp;
		}
		int lowValue = pow( base, exp );
		mod = ( mod * lowValue ) % value;
		return mod;
	}

	static int log( int base, int value )
	{
		int log = 0;
		while ( value > 0 )
		{
			value = value / base;
			log++;
		}
		return log;
	}

	static int pow( int base, int exp )
	{
		int v = 1;
		for ( int i = 0; i < exp; i++ )
		{
			v *= base;
		}
		return v;
	}

	static final double LOG3 = Math.log( 3 ) / Math.log( 2 );

	static double logValue( int i, int j )
	{
		return i + j * LOG3;
	}

	static class Value implements Comparable<Value>
	{

		int i;
		int j;
		double log;

		public Value( int i, int j )
		{
			this.i = i;
			this.j = j;
			this.log = logValue( i, j );
		}

		public Value( int i, int j, double logValue )
		{
			this.i = i;
			this.j = j;
			this.log = i + logValue;
		}

		@Override
		public boolean equals( Object o2 )
		{
			if ( o2 instanceof Value )
			{
				Value v2 = (Value) o2;
				return i == v2.i && j == v2.j;
			}
			return false;
		}

		public String toString( )
		{
			return i + "," + j;
		}

		@Override
		public int compareTo( Value v2 )
		{
			return Double.compare( log, v2.log );
		}

		public int mod( int v )
		{
			int v1 = mode( 2, i, v );
			int v2 = mode( 3, j, v );
			return (int) ( ( (long) v1 * (long) v2 ) % v );
		}
	}

	static ArrayList<Value> create10K( )
	{
		ArrayList<Value> values = new ArrayList<Value>( );
		for ( int i = 1; i < 100; i++ )
		{
			for ( int j = 1; j < 100; j++ )
			{
				values.add( new Value( i, j ) );
			}
		}
		Collections.sort( values );
		return values;
	}

	static class ValueProvider
	{

		ArrayList<ValueSeries> valueSeries = new ArrayList<ValueSeries>( );

		class ValueIterator implements Iterator<Value>
		{

			int upperIndex;
			int lowIndex;
			long  index;
			ArrayList<Entry> entries = new ArrayList<Entry>( );

			class Entry
			{

				public Entry( Iterator<Value> iterator )
				{
					this.iterator = iterator;
					this.value = iterator.next( );
				}
				Value value;
				Iterator<Value> iterator;
			}

			int i;

			ValueIterator( int i )
			{
				this.i = i;
			}

			@Override
			public boolean hasNext( )
			{
				return true;
			}

			@Override
			public Value next( )
			{
				// introduce next provider
				initValueSeries( index );
				// get the smallest value
				int index = getSmallestValue( entries, 0, entries.size( ) );
				Entry entry = entries.get( index );
				Value value = entry.value;
				entry.value = entry.iterator.next( );
				index++;
				return value;
			}
			
			protected void initValueSeries(long index)
			{
				if ( index >= upperLimit) {
					
				}
				double upperValue = LOG3;
				double lowerValue = 0;
				long lowerCount = 0;
				long upperCount = 0;
				do
				{
					lowerCount = upperCount;
					upperCount += Math.floor( upperValue );
					lowerValue = upperValue;
					upperValue += LOG3;
				} while ( upperCount <= index );
			}

			int getSmallestValue( ArrayList<Entry> entries, int offset, int size )
			{
				if ( size == 1 )
				{
					return offset;
				}
				if ( size == 2 )
				{
					return getSmallerValue( entries, offset, offset + 1 );
				}
				int leftSize = size / 2;
				int offset1 = getSmallestValue( entries, offset, leftSize );
				int offset2 = getSmallestValue( entries, offset + leftSize,
						size - leftSize );
				return getSmallerValue( entries, offset1, offset2 );
			}

			int getSmallerValue( ArrayList<Entry> entries, int offset1,
					int offset2 )
			{
				Entry entry1 = entries.get( offset1 );
				Entry entry2 = entries.get( offset2 );
				int result = entry1.value.compareTo( entry2.value );
				if ( result <= 0 )
				{
					return offset1;
				}
				return offset2;
			}

			@Override
			public void remove( )
			{
				throw new UnsupportedOperationException( );
			}
		}
	}

	static class ValueSeries
	{

		ValueSeries( int j )
		{
			this.j = j;
			this.logValue = LOG3 * j;
		}
		int j;
		double logValue;

		Iterator<Value> iterator( int i )
		{
			return new ValueIterator( i );
		}

		private class ValueIterator implements Iterator<Value>
		{

			int i;

			ValueIterator( int i )
			{
				this.i = i;
			}

			@Override
			public boolean hasNext( )
			{
				return true;
			}

			@Override
			public Value next( )
			{
				Value value = new Value( i, j, logValue );
				i++;
				return value;
			}

			@Override
			public void remove( )
			{
				throw new UnsupportedOperationException( );
			}
		}
	}

}
