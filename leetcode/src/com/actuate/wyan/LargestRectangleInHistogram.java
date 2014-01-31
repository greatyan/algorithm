
package com.actuate.wyan;

public class LargestRectangleInHistogram
{

	public static void main( String[] args )
	{
		int[] values = new int[60000];
		for ( int i = 0; i < values.length; i++ )
		{
			values[i] = 1;
		}
		test( values );
		test( new int[]{2, 1, 2} );
		test( new int[]{2,1,5,6,2,3} );
		test( new int[] {4,2,0,3,2,4,3,4});
	}

	static public void test( int[] values )
	{
		int v = new Solution( ).largestRectangleArea( values );
		System.out.println( v );
	}

	static public class Solution
	{

		public int largestRectangleArea( int[] height )
		{

			if ( height == null || height.length == 0 )
			{
				return 0;
			}

			int max = 0;
			int[][] buffer = new int[height.length + 1][];
			buffer[0] = new int[2];
			buffer[0][0] = 0;
			int index = 0;
			for ( int i = 0; i < height.length; i++ )
			{
				int removeCount = 0;
				while ( buffer[index][0] > height[i] )
				{
					int value = buffer[index][0] * (buffer[index][1] + removeCount);
					if ( max < value )
					{
						max = value;
					}
					removeCount += buffer[index][1];
					index--;
				}
				if ( buffer[index][0] != height[i] )
				{
					index++;
					if ( buffer[index] == null )
					{
						buffer[index] = new int[2];
					}
					buffer[index][0] = height[i];
					buffer[index][1] = 0;
				}
				buffer[index][1] += removeCount;
				buffer[index][1]++;
			}

			while ( index >= 0 )
			{
				int value = buffer[index][0] * buffer[index][1];
				if ( max < value )
				{
					max = value;
				}
				index--;
				if ( index >= 0 )
				{
					buffer[index][1] += buffer[index + 1][1];
				}
			}

			return max;
		}
	}
}
