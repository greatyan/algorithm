
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class ZigZagConversion
{

	@Test
	public void test( )
	{
		Assert.assertEquals( "PAYPALISHIRING",
				new Solution( ).convert( "PAYPALISHIRING", 1 ) );
		Assert.assertEquals( "PYAIHRNAPLSIIG",
				new Solution( ).convert( "PAYPALISHIRING", 2 ) );
		Assert.assertEquals( "PAHNAPLSIIGYIR",
				new Solution( ).convert( "PAYPALISHIRING", 3 ) );
	}

	public class Solution
	{

		public String convert( String s, int nRows )
		{

			if ( nRows == 1 )
			{
				return s;
			}
			StringBuilder sb = new StringBuilder( );
			addFirstRow( sb, s, nRows );

			for ( int i = 1; i < nRows - 1; i++ )
			{
				addRow( sb, s, i, nRows );
			}
			addLastRow( sb, s, nRows );
			return sb.toString( );
		}

		protected void addFirstRow( StringBuilder sb, String s, int nRows )
		{
			int skip = 2 * nRows - 2;
			int index = 0;
			while ( index < s.length( ) )
			{
				sb.append( s.charAt( index ) );
				index += skip;
			}
		}

		protected void addLastRow( StringBuilder sb, String s, int nRows )
		{
			int skip = 2 * nRows - 2;
			int index = nRows - 1;
			while ( index < s.length( ) )
			{
				sb.append( s.charAt( index ) );
				index += skip;
			}
		}

		protected void addRow( StringBuilder sb, String s, int row, int nRows )
		{
			int skip = 2 * nRows - 2;
			int index = row;
			int next = ( nRows - 1 - row ) * 2;
			while ( index < s.length( ) )
			{
				// if ( index + row < s.length( ) )
				{
					sb.append( s.charAt( index ) );
				}
				if ( index + next < s.length( ) )
				{
					sb.append( s.charAt( index + next ) );
				}
				index += skip;
			}
		}
	}
}
