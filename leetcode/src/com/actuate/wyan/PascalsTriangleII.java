
package com.actuate.wyan;

import java.util.ArrayList;

import org.junit.Test;

public class PascalsTriangleII
{

	@Test
	public void test( )
	{
		new Solution( ).getRow( 0 );
		new Solution( ).getRow( 1 );
		new Solution( ).getRow( 2 );
	}

	public class Solution
	{

		public ArrayList<Integer> getRow( int rowIndex )
		{
			ArrayList<Integer> v = new ArrayList<Integer>( rowIndex + 1 );
			for ( int i = 0; i <= rowIndex; i++ )
			{
				for ( int j = i-1; j > 0; j-- )
				{
					v.set( j, v.get( j ) + v.get( j - 1 ) );
				}
				v.add( 1 );
			}
			return v;
		}
	}
}
