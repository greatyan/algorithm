
package com.actuate.wyan;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

public class LongestConsecutiveSequence
{

	@Test
	public void test( )
	{
		Assert.assertEquals(
				4,
				new Solution( ).longestConsecutive( new int[]{100, 4, 200, 1,
						3, 2} ) );
		Assert.assertEquals( 0,
				new Solution( ).longestConsecutive( new int[]{} ) );
		Assert.assertEquals( 1,
				new Solution( ).longestConsecutive( new int[]{1} ) );

		Assert.assertEquals( 3,
				new Solution( ).longestConsecutive( new int[]{1, 2, 0, 1} ) );
	}

	public class Solution
	{

		public int longestConsecutive( int[] num )
		{

			if ( num == null )
			{
				return 0;
			}
			int maxLength = 0;
			HashSet<Integer> tested = new HashSet<Integer>( );
			HashMap<Integer, Range> maps = new HashMap<Integer, Range>( );
			for ( int i = 0; i < num.length; i++ )
			{
				if ( !tested.contains( num[i] ) )
				{
					tested.add( num[i] );
					int length = addNumber( maps, num[i] );
					if ( length > maxLength )
					{
						maxLength = length;
					}
				}
			}
			return maxLength;
		}

		class Range
		{

			int start;
			int end;
		}

		protected int addNumber( HashMap<Integer, Range> maps, int num )
		{
			// search num - 1
			Range preRange = maps.get( num - 1 );
			if ( preRange != null )
			{
				assert ( preRange.end == num - 1 );
				Range nxtRange = maps.get( num + 1 );
				if ( nxtRange != null )
				{
					assert ( nxtRange.start == num + 1 );
					// remove the cache...
					if ( preRange.start != preRange.end )
					{
						maps.remove( preRange.end );
					}
					maps.remove( nxtRange.start );
					maps.remove( nxtRange.end );
					preRange.end = nxtRange.end;
					maps.put( preRange.end, preRange );
					return preRange.end - preRange.start + 1;
				}
				else
				{
					if ( preRange.start != preRange.end )
					{
						maps.remove( preRange.end );
					}
					preRange.end = num;
					maps.put( preRange.end, preRange );
					return preRange.end - preRange.start + 1;
				}
			}
			Range nxtRange = maps.get( num + 1 );
			if ( nxtRange != null )
			{
				assert ( nxtRange.start == num + 1 );
				if ( nxtRange.start != nxtRange.end )
				{
					maps.remove( nxtRange.start );
				}
				nxtRange.start = num;
				maps.put( num, nxtRange );
				return nxtRange.end - nxtRange.start + 1;
			}
			Range range = new Range( );
			range.start = num;
			range.end = num;
			maps.put( num, range );
			return 1;
		}
	}
}
