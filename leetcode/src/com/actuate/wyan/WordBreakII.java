
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class WordBreakII
{

	public static void main( String[] args )
	{
//		String text = "catsanddog";
//		String[] dict = new String[]{"cat", "cats", "and", "sand", "dog"};
		
		String text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		String[] dict = new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"};

		Arrays.sort( dict);
		ArrayList<String> stmts = new Solution( ).wordBreak( text, dict );
		for ( String stmt : stmts )
		{
			System.out.println( stmt );
		}
	}

	static public class Solution
	{

		public ArrayList<String> wordBreak( String s, Set<String> dict )
		{
			String[] words = dict.toArray( new String[dict.size( )] );
			Arrays.sort( words);
			return wordBreak( s, words );
		}

		public ArrayList<String> wordBreak( String s, String[] dict )
		{
			char[] chars = s.toCharArray( );
			return wordBreak( chars, 0, dict );
		}

		protected HashMap<Integer, ArrayList<String>> cachedStmts = new HashMap<Integer, ArrayList<String>>();
		
		public ArrayList<String> wordBreak( char[] input, int offset,
				String[] dict )
		{
			ArrayList<String> stmts = cachedStmts.get( offset );
			if ( stmts == null)
			{
				stmts = doWordBreak( input, offset, dict);
				cachedStmts.put( offset, stmts);
					
			}
			return stmts;
		}
			
		public ArrayList<String> doWordBreak( char[] input, int offset,
				String[] dict )
		{
			ArrayList<String> words = matches( input, offset, dict );
			if ( words.isEmpty( ) )
			{
				return words;
			}
			ArrayList<String> allStmts = new ArrayList<String>( );
			for ( String word : words )
			{
				if ( offset + word.length( ) == input.length) {
					allStmts.add( word );
				}
				else
				{
					ArrayList<String> stmts = wordBreak( input,
							offset + word.length( ), dict );
					for ( int i = 0; i < stmts.size( ); i++ )
					{
						allStmts.add( word + " " + stmts.get( i ) );
					}
				}
			}
			return allStmts;
		}

		ArrayList<String> matches( char[] input, int offset, String[] dict )
		{
			ArrayList<String> words = new ArrayList<String>( );
			int length = input.length - offset;
			if ( length == 0 )
			{
				words.add( "" );
				return words;
			}
			int size = 1;
			int index = 0;
			while ( size <= length )
			{
				String word = new String( input, offset, size );
				index = Arrays.binarySearch( dict, index, dict.length, word );
				if ( index >= 0 )
				{
					// we find one
					words.add( dict[index] );
					size++;
					continue;
				}
				// we don't find the word, it should be insert into the a
				index = -( index + 1 );
				if ( index >= dict.length )
				{
					// no more matching word
					break;
				}
				size++;
				if ( dict[index].startsWith( word )) {
					continue;
				}
				break;
			}
			return words;
		}
	}
}
