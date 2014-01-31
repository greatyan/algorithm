
package com.actuate.wyan;

import java.util.LinkedList;

public class TrappingRainWater
{

	public class Solution
	{

		class Bucket
		{

			int left;
			int height;

			Bucket( int left, int height )
			{
				this.left = left;
				this.height = height;
			}
		}

		public int trap( int[] A )
		{
			int volume = 0;
			LinkedList<Bucket> buckets = new LinkedList<Bucket>( );
			for ( int i = 0; i < A.length; i++ )
			{
				if ( buckets.isEmpty( ) )
				{
					buckets.add( new Bucket( i, A[i] ) );
				}
				else
				{
					Bucket bucket = buckets.getLast( );
					if ( A[i] > bucket.height )
					{
						buckets.removeLast();
						while ( !buckets.isEmpty( )) {
							bucket = buckets.getLast( );
							if ( A[i] <= bucket.height )
							{
								break;
							}
						}
					}
					
					if ( buckets.isEmpty( )) {
						volume += bucket.height * ( i - bucket.left); 
					}
					else {
						if ( A[i] < bucket.height) {
							buckets.add(  new Bucket( i, A[i]) );
						}
						else {
							//ignroe.
						}
					}
				}
			}
			
			if ( buckets.isEmpty( )) {
				return area;
			}
			
			
			
			
			
			//remain the last one
			while ( !buckets.isEmpty( ) )
			{
				Bucket bucket = buckets.removeLast( );
			}

			return volume;
		}
	}
}
