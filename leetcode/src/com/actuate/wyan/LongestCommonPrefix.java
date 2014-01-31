
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class LongestCommonPrefix
{

	@Test
	public void test( )
	{
		Assert.assertEquals( "",
				new Solution( ).longestCommonPrefix( new String[]{"", "abc"} ) );
		Assert.assertEquals( "a",
				new Solution( ).longestCommonPrefix( new String[]{"a", "abc"} ) );
	}

	public class Solution
	{

		public String longestCommonPrefix( String[] strs )
		{
			if ( strs.length == 0 )
			{
				return "";
			}

			int index = 0;
			while ( true )
			{
				if ( index >= strs[0].length( ) )
				{
					return strs[0];
				}
				char ch = strs[0].charAt( index );
				for ( int i = 1; i < strs.length; i++ )
				{
					if ( index >= strs[i].length( ) )
					{
						return strs[i];
					}
					if ( strs[i].charAt( index ) != ch )
					{
						return strs[i].substring( 0, index );
					}
				}
				index++;
			}
		}
	}
}
