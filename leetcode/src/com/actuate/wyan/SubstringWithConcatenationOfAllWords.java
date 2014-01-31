
package com.actuate.wyan;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class SubstringWithConcatenationOfAllWords
{

	@Test
	public void test( )
	{
		assertEquals( new int[]{0, 9}, new Solution( ).findSubstring(
				"barfoothefoobarman", new String[]{"foo", "bar"} ) );
	}

	class CharNode
	{
		char ch;
		ArrayList<CharNode> nodes;
		CharNode( char ch)
		{
			this.ch = ch;
		}
	}
	
	void addWord( CharNode root, String word, int index )
	{
		if ( index == word.length( ) )
		{
			return;
		}

		if ( root.nodes == null )
		{
			root.nodes = new ArrayList<CharNode>( );
		}
		for ( CharNode node : root.nodes )
		{
			if ( node.ch == word.charAt( index ) )
			{
				addWord( node, word, index + 1 );
				return;
			}
		}
		CharNode node = new CharNode( word.charAt( index ) );
		addWord( node, word, index + 1 );
	}
	
	String matches(CharNode root, String word)
	{
		
	}
	
	int matches( CharNode root, String word, int index, int size)
	{
		
	}
	
	
	
	
	public static void assertEquals( int[] values, ArrayList<Integer> results )
	{
		if ( values.length == results.size( ) )
		{
			for ( int i = 0; i < values.length; i++ )
			{
				if ( values[i] != results.get( i ) )
				{
					Assert.fail( );
				}
			}
		}
		else
		{
			Assert.fail( );
		}
	}

	public class Solution
	{
		public ArrayList<Integer> findSubstring( String S, String[] L )
		{
			int off = 0;
			int totalSize = L[0].length( ) * L.length;
			if ( S.length( ) < totalSize) {
				return new ArrayList<Integer>();
			}
			do {
				int index = findSubString( chars, 0, L);
				if ( index == -1) {
					index++
				}
			}
			
		}
		
		public int findSubstring( char[] s, int off, String[] strs)
		{
		}
		
	}
}
