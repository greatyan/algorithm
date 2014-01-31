
package com.actuate.wyan;

import java.util.ArrayList;

public class GenerateParentheses
{

	public static void main( String[] args )
	{
		new GenerateParentheses( ).test( );
	}

	public void test( )
	{
		ArrayList<String> results = new Solution( ).generateParenthesis( 3 );
		for ( String v : results )
		{
			System.out.println( v );
		}
	}

	public class Solution
	{

		int level;
		int remainOpens;
		int remainCloses;
		char[] chars;
		ArrayList<String> results;

		public ArrayList<String> generateParenthesis( int n )
		{
			level = 0;
			remainOpens = n;
			remainCloses = n;
			chars = new char[n * 2];
			results = new ArrayList<String>( );
			generate( 0 );
			return results;
		}

		private void generate( int index )
		{
			if ( index == chars.length )
			{
				results.add( new String( chars ) );
				return;
			}

			// try open
			if ( remainOpens > 0 )
			{
				chars[index] = '(';
				level++;
				remainOpens--;
				generate( index + 1 );
				level--;
				remainOpens++;
			}

			// try close
			if ( level > 0 && remainCloses > 0 )
			{
				chars[index] = ')';
				level--;
				remainCloses--;
				generate( index + 1 );
				level++;
				remainCloses++;
			}
		}
	}
}
