
package com.actuate.wyan;

import java.util.HashSet;
import java.util.Set;

public class WordBreak
{

	public static void main( String[] args )
	{
		new WordBreak( ).test( );
	}

	public void test( )
	{
		HashSet<String> dict = new HashSet<String>( );
		dict.add( "a" );
		System.out.println( new Solution( ).wordBreak( "a", dict ) );
	}

	public class Solution
	{

		public boolean wordBreak( String s, Set<String> dict )
		{

			Boolean[] cache = new Boolean[s.length( )];
			return wordBreak( cache, s, 0, dict );
		}

		boolean wordBreak( Boolean[] cache, String s, int offset,
				Set<String> dict )
		{
			if ( cache[offset] != null )
			{
				return cache[offset];
			}
			int maxLength = s.length( ) - offset;
			for ( int i = 1; i <= maxLength; i++ )
			{
				String word = s.substring( offset, offset + i );
				if ( dict.contains( word ) )
				{
					if ( i == maxLength )
					{
						// last one
						cache[offset] = true;
						return true;
					}
					// test remain
					if ( wordBreak( cache, s, offset + i, dict ) )
					{
						cache[offset] = true;
						return true;
					}
				}
			}
			cache[offset] = false;
			return false;
		}
	}
}
