
package com.actuate.wyan;

public class LongestValidParentheses
{

	public static void main( String[] args )
	{
		new LongestValidParentheses( ).test( );
	}

	public void test( )
	{
		String ss[] = new String[]{"", null, "(", "((", "()", "()()", "(())()(()(("};

		for ( String s : ss )
		{
			int length = new Solution( ).longestValidParentheses( s );
			System.out.println( length + ":" + s);
		}
	}

	public class Solution
	{

		public int longestValidParentheses( String s )
		{
			if ( s == null || s.length( ) <= 1 )
			{
				return 0;
			}
			int[] starts = new int[s.length( ) + 1];
			int maxLength = 0;
			int v = 0;
			reset( starts );
			for ( int i = 0; i < s.length( ); i++ )
			{
				char ch = s.charAt( i );
				if ( ch == '(' )
				{
					// increase level
					if ( starts[v] == -1 )
					{
						starts[v] = i;
					}
					v++;
					starts[v] = -1;
				}
				else
				{
					// decrease level
					v--;
					if ( v >= 0 )
					{
						maxLength = Math.max( maxLength, i - starts[v] + 1 );
					}
					if ( v < 0 )
					{
						v = 0;
						reset( starts );
					}
				}
			}
			return maxLength;
		}

		protected void reset( int[] length )
		{
			length[0] = -1;
//			for ( int i = 0; i < length.length; i++ )
//			{
//				length[i] = -1;
//			}
		}
	}
}
