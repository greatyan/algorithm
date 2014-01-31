
package com.actuate.wyan;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class ThreeSumClosest
{

	@Test
	public void test( )
	{
		Assert.assertEquals( 2,
				new Solution( ).threeSumClosest( new int[]{-4, -1, 2, 1}, 1 ) );

		Assert.assertEquals(
				284,
				new Solution( ).threeSumClosest( new int[]{43, 75, -90, 47,
						-49, 72, 17, -31, -68, -22, -21, -30, 65, 88, -75, 23,
						97, -61, 53, 87, -3, 33, 20, 51, -79, 43, 80, -9, 34,
						-89, -7, 93, 43, 55, -94, 29, -32, -49, 25, 72, -6, 35,
						53, 63, 6, -62, -96, -83, -73, 66, -11, 96, -90, -27,
						78, -51, 79, 35, -63, 85, -82, -15, 100, -82, 1, -4,
						-41, -21, 11, 12, 12, 72, -82, -22, 37, 47, -18, 61,
						60, 55, 22, -6, 26, -60, -42, -92, 68, 45, -1, -26, 5,
						-56, -1, 73, 92, -55, -20, -43, -56, -15, 7, 52, 35,
						-90, 63, 41, -55, -58, 46, -84, -92, 17, -66, -23, 96,
						-19, -44, 77, 67, -47, -48, 99, 51, -25, 19, 0, -13,
						-88, -10, -67, 14, 7, 89, -69, -83, 86, -70, -66, -38,
						-50, 66, 0, -67, -91, -65, 83, 42, 70, -6, 52, -21,
						-86, -87, -44, 8, 49, -76, 86, -3, 87, -32, 81, -58,
						37, -55, 19, -26, 66, -89, -70, -69, 37, 0, 19, -65,
						38, 7, 3, 1, -96, 96, -65, -52, 66, 5, -3, -87, -16,
						-96, 57, -74, 91, 46, -79, 0, -69, 55, 49, -96, 80, 83,
						73, 56, 22, 58, -44, -40, -45, 95, 99, -97, -22, -33,
						-92, -51, 62, 20, 70, 90}, 284 ) );
	}

	public class Solution
	{

		public int threeSumClosest( int[] num, int target )
		{
			return bf( num, target );
		}

		public int bf( int[] num, int target )
		{
			int minDelta = Integer.MAX_VALUE;
			int value = 0;
			for ( int i = 0; i < num.length; i++ )
			{
				for ( int j = i + 1; j < num.length; j++ )
				{
					for ( int k = j + 1; k < num.length; k++ )
					{
						int v = num[i] + num[j] + num[k];
						int delta = Math.abs( v - target );
						if ( delta == 0 )
						{
							return v;
						}
						if ( minDelta > delta )
						{
							minDelta = delta;
							value = v;
						}
					}
				}
			}
			return value;
		}

		public int recursive( int[] num, int target )
		{
			caches.clear( );
			cached = 0;
			executed = 0;
			int value = sumClosest( num, 0, 3, target );
			System.out.println( cached + "/" + executed );
			return value;

		}

		HashMap<String, Integer> caches = new HashMap<String, Integer>( );

		int cached = 0;
		int executed = 0;

		public int sumClosest( int[] num, int offset, int count, int target )
		{

			executed++;
			String key = offset + "_" + target;
			if ( caches.containsKey( key ) )
			{
				cached++;
				return caches.get( key );
			}
			int minDelta = Integer.MAX_VALUE;;
			int closestValue = 0;
			if ( count == 1 )
			{

				for ( int i = offset; i < num.length; i++ )
				{
					int delta = Math.abs( num[i] - target );
					if ( minDelta > delta )
					{
						minDelta = delta;
						closestValue = num[i];
					}
				}
				caches.put( key, closestValue );
				return closestValue;
			}

			for ( int i = offset; i < num.length - count + 1; i++ )
			{
				int value = num[i]
						+ sumClosest( num, i + 1, count - 1, target - num[i] );
				int delta = Math.abs( value - target );
				if ( minDelta > delta )
				{
					minDelta = delta;
					closestValue = value;
				}
			}
			caches.put( key, closestValue );
			return closestValue;
		}
	}

}
