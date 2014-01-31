
package com.actuate.wyan;

import java.util.HashMap;

public class PalindromePartitioningII
{

	public static void main( String[] args )
	{
		new PalindromePartitioningII( ).test( );
	}

	public void test( )
	{
		// String value =
		// "apjesgpsxoeiokmqmfgvjslcjukbqxpsobyhjpbgdfruqdkeiszrlmtwgfxyfostpqczidfljwfbbrflkgdvtytbgqalguewnhvvmcgxboycffopmtmhtfizxkmeftcucxpobxmelmjtuzigsxnncxpaibgpuijwhankxbplpyejxmrrjgeoevqozwdtgospohznkoyzocjlracchjqnggbfeebmuvbicbvmpuleywrpzwsihivnrwtxcukwplgtobhgxukwrdlszfaiqxwjvrgxnsveedxseeyeykarqnjrtlaliyudpacctzizcftjlunlgnfwcqqxcqikocqffsjyurzwysfjmswvhbrmshjuzsgpwyubtfbnwajuvrfhlccvfwhxfqthkcwhatktymgxostjlztwdxritygbrbibdgkezvzajizxasjnrcjwzdfvdnwwqeyumkamhzoqhnqjfzwzbixclcxqrtniznemxeahfozp";
		String value = "aa";

		System.out.println( new Solution( ).minCut( value ) );
	}

	public class Solution
	{

		public int minCut( String s )
		{
			if ( s == null || s.length( ) == 0 )
			{
				return 0;
			}
			char[] chars = s.toCharArray( );

			initCaches( );
			return minCut( chars, 0, chars.length - 1 );
		}

		HashMap<String, Integer> cachedValues;

		void initCaches( )
		{
			cachedValues = new HashMap<String, Integer>( );
		}

		int getCachedValeu( int start, int end )
		{
			String key = start + "_" + end;
			if ( cachedValues.containsKey( key ) )
			{
				return cachedValues.get( key );
			}
			return 0;
		}

		void setCachedValue( int start, int end, int value )
		{
			String key = start + "_" + end;
			cachedValues.put( key, value );
		}

		int minCut( char[] chars, int start, int end )
		{
			if ( end < start )
			{
				return 0;
			}
			if ( end == start )
			{
				return 1;
			}

			int v = getCachedValeu( start, end );
			if ( v != 0 )
			{
				return v;
			}

			int length = ( end - start + 1 ) / 2;
			int middle = ( end + start ) / 2;

			int minValue = cutAt( chars, start, end, middle );
			for ( int i = 1; i <= length; i++ )
			{

				if ( middle + i <= end )
				{
					int value = cutAt( chars, start, end, middle + i );
					if ( minValue > value )
					{
						minValue = value;
					}
				}
				if ( middle - i >= 0 )
				{
					int value = cutAt( chars, start, end, middle - i );
					if ( minValue > value )
					{
						minValue = value;
					}
				}
			}
			setCachedValue( start, end, minValue );
			return minValue;
		}

		protected int cutAt( char[] chars, int start, int end, int index )
		{
			int minValue = Integer.MAX_VALUE;
			int size = getPalindrome( chars, start, end, index - 1, index );
			int cut1 = minCut( chars, start, index - size - 1 )
					+ minCut( chars, index + size, end );
			if ( size > 0 )
			{
				cut1 += 1;
			}
			if ( minValue > cut1 )
			{
				minValue = cut1;
			}

			// may be zero
			size = getPalindrome( chars, start, end, index - 1, index + 1 );
			int cut2 = minCut( chars, start, index - size - 1 )
					+ minCut( chars, index + size + 1, end ) + 1;
			if ( minValue > cut2 )
			{
				minValue = cut2;
			}
			return minValue;
		}

		int getPalindrome( char[] chars, int start, int end, int left, int right )
		{
			if ( left < start || right > end )
			{
				return 0;
			}
			int length = 0;
			while ( left >= start && right <= end )
			{
				if ( chars[left] == chars[right] )
				{
					length++;
					left--;
					right++;
				}
				else
				{
					break;
				}
			}
			return length;
		}
	}
}
