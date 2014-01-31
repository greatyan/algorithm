
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InsertInterval
{

	public class Interval
	{

		int start;
		int end;

		Interval( )
		{
			start = 0;
			end = 0;
		}

		Interval( int s, int e )
		{
			start = s;
			end = e;
		}
	}

	public class Solution
	{

		public ArrayList<Interval> insert( ArrayList<Interval> intervals,
				Interval newInterval )
		{
			if ( intervals == null )
			{
				intervals = new ArrayList<Interval>( );
			}
			int start = Collections.binarySearch( intervals, newInterval,
					new Comparator<Interval>( ) {

						public int compare( Interval v1, Interval v2 )
						{
							return v1.start - v2.start;
						}
					} );
			if ( start < 0 )
			{
				start = -( start + 1 );
			}

			// merge it with previous one
			int prevIndex = start - 1;
			if ( prevIndex >= 0 )
			{
				Interval prevInterval = intervals.get( prevIndex );
				if ( prevInterval.end > newInterval.start )
				{
					if ( prevInterval.end < newInterval.end) 
					{
						prevInterval.end = newInterval.end;
						merge( intervals, prevIndex );
						return intervals;
					}
				}
			}

			intervals.add( start, newInterval );
			merge( intervals, start );
			return intervals;
		}

		void merge( ArrayList<Interval> intervals, int index )
		{
			// merge with next
			Interval interval = intervals.get( index );
			int nextIndex = index + 1;
			while ( nextIndex < intervals.size( ) )
			{
				Interval nextInterval = intervals.get( nextIndex );
				if ( nextInterval.start <= interval.end )
				{
					if ( nextInterval.end >= interval.end )
					{
						interval.end = nextInterval.end;
					}
					intervals.remove( nextIndex );
					continue;
				}
				break;
			}
		}
	}
}
