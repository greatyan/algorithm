
package com.actuate.wyan;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache
{

	LinkedHashMap<Integer, Integer> caches;
	int capacity;

	public LRUCache( int capacity )
	{
		this.capacity = capacity;
		caches = new LinkedHashMap<Integer, Integer>( capacity, (float) 0.75,
				true ) {

			protected boolean removeEldestEntry(
					Map.Entry<Integer, Integer> eldest )
			{
				if ( LRUCache.this.capacity < size( ) )
				{
					return true;
				}
				return false;
			}
		};
	}

	public int get( int key )
	{
		Integer v = caches.get( key );
		if ( v != null )
		{
			return v.intValue( );
		}
		return -1;
	}

	public void set( int key, int value )
	{
		caches.put( key, value );
	}
}
