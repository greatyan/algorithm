
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MergeIntervals
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

		public ArrayList<Interval> merge( ArrayList<Interval> intervals )
		{

			if ( intervals == null || intervals.size( ) <= 1 )
			{
				return intervals;
			}
			Collections.sort( intervals, new Comparator<Interval>( ) {

				public int compare( Interval v1, Interval v2 )
				{
					return v1.start - v2.start;
				}
			} );
			ArrayList<Interval> results = new ArrayList<Interval>( );
			Interval iv1 = intervals.get( 0 );
			for ( int i = 1; i < intervals.size( ); i++ )
			{
				Interval iv2 = intervals.get( i );
				if ( iv1.end >= iv2.start )
				{
					// merge
					iv1.start = Math.min( iv1.start, iv2.start );
					iv1.end = Math.max( iv1.end, iv2.end );
				}
				else
				{
					results.add( iv1 );
					iv1 = iv2;
				}
			}
			results.add( iv1 );
			return results;
		}
	}
}
