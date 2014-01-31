
package com.actuate.wyan;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class LetterCombinationsOfAPhoneNumber
{

	@Test
	public void test( )
	{
		Assert.assertEquals( 9, new Solution( ).letterCombinations( "23" )
				.size( ) );
		Assert.assertEquals( 1, new Solution( ).letterCombinations( "" ).size( ) );
	}

	static public class Solution
	{
		static char[][] maps = new char[][]{new char[]{}, new char[]{},
				new char[]{'a', 'b', 'c'}, new char[]{'d', 'e', 'f'},
				new char[]{'g', 'h', 'i'}, new char[]{'j', 'k', 'l'},
				new char[]{'m', 'n', 'o'}, new char[]{'p', 'q', 'r', 's'},
				new char[]{'t', 'u', 'v'}, new char[]{'w', 'x', 'y', 'z'}};

		public ArrayList<String> letterCombinations( String digits )
		{
			ArrayList<String> words = new ArrayList<String>( );
			char[] word = new char[digits.length( )];
			fillWord( word, digits.toCharArray( ), 0, words );
			return words;
		}

		protected void fillWord( char[] word, char[] digits, int index,
				ArrayList<String> words )
		{
			if ( index == digits.length )
			{
				words.add( new String( word ) );
				return;
			}

			int value = digits[index] - '0';
			if ( value >= 1 && value <= 9 )
			{
				char[] chars = maps[value];
				for ( int i = 0; i < chars.length; i++ )
				{
					word[index] = chars[i];
					fillWord( word, digits, index + 1, words );
				}
			}
		}
	}
}
