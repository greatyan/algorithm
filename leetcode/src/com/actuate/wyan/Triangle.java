
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.HashMap;

public class Triangle
{

	public class Solution
	{

		public int minimumTotal( ArrayList<ArrayList<Integer>> triangle )
		{
			if ( triangle.size( ) == 0 )
			{
				return 0;
			}
			return minimumTotal( triangle, 0, 0 );
		}

		HashMap<String, Integer> caches = new HashMap<String, Integer>( );

		int minimumTotal( ArrayList<ArrayList<Integer>> triangle, int level,
				int index )
		{
			if ( level == triangle.size( ) - 1 )
			{
				return triangle.get( level ).get( index );
			}
			String key = level + "_" + index;
			if ( caches.containsKey( key ) )
			{
				return caches.get( key );
			}
			int v1 = triangle.get( level ).get( index )
					+ minimumTotal( triangle, level + 1, index );
			int v2 = triangle.get( level ).get( index )
					+ minimumTotal( triangle, level + 1, index + 1 );
			int v = Math.min( v1, v2 );
			caches.put( key, v );
			return v;
		}

	}
}
