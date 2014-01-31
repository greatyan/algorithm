
package com.actuate.wyan;

public class GetPermutationSequence
{

	static int getMax( int n )
	{
		int v = 1;
		for ( int i = 1; i < n; i++ )
		{
			v = v * i;
		}
		return v;
	}

	public static void main( String[] args )
	{
		/*int n = 9;
		int max = getMax( n + 1 );
		for ( int i = 1; i <= max; i++ )
		{
			String value = new Solution( ).getPermutation( n, i );
			System.out.println( value );
		}*/
		String value = new Solution( ).getPermutation( 1, 1 );
		System.out.println( value );
	}

	static public class Solution
	{

		public String getPermutation( int n, int k )
		{
			if ( n <= 0 || n > 9 )
			{
				return "";
			}
			int[] maxValues = new int[n + 1];
			maxValues[0] = 1;
			for ( int i = 1; i <= n; i++ )
			{
				maxValues[i] = maxValues[i - 1] * i;
			}
			if ( k < 0 || k > maxValues[n] )
			{
				return "";
			}

			char[] chars = new char[]{'1', '2', '3', '4', '5', '6', '7', '8',
					'9'};
			char[] buffer = new char[9];
			boolean[] used = new boolean[9];
			int value = k - 1;
			for ( int i = 1; i < n; i++ )
			{
				int maxValue = maxValues[n - i];
				int id = value / maxValue;
				value = value % maxValue;
				buffer[i - 1] = getChar( id, chars, used );
			}
			buffer[n - 1] = getChar( 0, chars, used );
			return new String( buffer );
		}

		char getChar( int id, char[] chars, boolean[] used )
		{
			int count = 0;
			int index = 0;
			while ( index < chars.length )
			{
				if ( used[index] == false )
				{
					if ( count == id )
					{
						used[index] = true;
						return chars[index];
					}
					count++;
				}
				index++;
			}
			return '-';
		}
	}
}
