
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class WordLadder
{

	public static void main( String[] args )
	{
		String start = "hit";
		String end = "cok";
		String[] words = new String[]{"hot", "dot", "dog", "lot", "log"};

		HashSet<String> dict = toHashSet( words );
		int length = new Solution( ).ladderLength( start, end, dict );
		System.out.println( length );
	}

	static HashSet<String> toHashSet( String[] words )
	{
		HashSet<String> dict = new HashSet<String>( words.length );
		for ( String word : words )
		{
			dict.add( word );
		}
		return dict;
	}

	static public class Solution
	{

		public int ladderLength( String start, String end, HashSet<String> dict )
		{
			if ( start.equals( end ) )
			{
				return 0;
			}

			dict.add( start );
			dict.add( end );

			LinkedList<Node> searchNodes = new LinkedList<Node>( );
			HashSet<String> usedWords = new HashSet<String>( );

			Node root = new Node( );
			root.length = 1;
			root.word = start;
			searchNodes.add( root );
			usedWords.add( start );

			while ( !searchNodes.isEmpty( ) )
			{
				Node node = searchNodes.poll( );
				ArrayList<String> nextWords = getNextWords( node.word, dict );
				for ( String nextWord : nextWords )
				{
					if ( nextWord.equals( end ) )
					{
						return node.length + 1;
					}
					if ( !usedWords.contains( nextWord ) )
					{
						usedWords.add( nextWord );
						Node nextNode = new Node( );
						nextNode.length = node.length + 1;
						nextNode.word = nextWord;
						searchNodes.add( nextNode );
					}
				}
			}
			return 0;
		}

		class Node
		{

			int length;
			String word;
		}

		HashMap<String, ArrayList<String>> cachedNextWords = new HashMap<String, ArrayList<String>>( );

		ArrayList<String> getNextWords( String word, HashSet<String> dict )
		{
			ArrayList<String> words = cachedNextWords.get( word );
			if ( words != null )
			{
				return words;
			}
			cachedNextWords.put( word, words );
			words = new ArrayList<String>( );
			for ( int i = 0; i < word.length( ); i++ )
			{
				char[] chars = word.toCharArray( );
				for ( char ch = 'a'; ch <= 'z'; ch++ )
				{
					chars[i] = ch;
					String newWord = new String( chars );
					if ( dict.contains( newWord ) )
					{
						if ( !newWord.equals( word ) )
						{
							words.add( newWord );
						}
					}
				}
			}

			return words;
		}
	}
}
