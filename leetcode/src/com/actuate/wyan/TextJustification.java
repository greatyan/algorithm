
package com.actuate.wyan;

import java.util.ArrayList;

public class TextJustification
{

	public static void main( String[] args )
	{
		Solution s = new Solution( );
		String[] words = new String[]{"This", "is", "an", "example", "of",
				"text", "justification."};
		int L = 16;
		ArrayList<String> lines = s.fullJustify( words, L );
		for ( String line : lines )
		{
			System.out.println( line );
		}
	}

	static public class Solution
	{

		public ArrayList<String> fullJustify( String[] words, int lineWidth )
		{
			ArrayList<String> lines = new ArrayList<String>( );

			int lineStart = 0;
			int lineEnd = 0;
			do
			{
				lineEnd = fillLine( words, lineStart, lineWidth );
				if ( lineEnd == words.length )
				{
					String line = rightJustify( words, lineStart, lineEnd,
							lineWidth );
					lines.add( line );
				}
				else
				{
					String line = justify( words, lineStart, lineEnd, lineWidth );
					lines.add( line );

				}
				lineStart = lineEnd;
			} while ( lineEnd < words.length );

			return lines;

		}

		public int fillLine( String[] words, int start, int lineWidth )
		{
			int wordWidth = words[start].length( );
			int index = start + 1;
			while ( index < words.length )
			{
				int newWidth = wordWidth + 1 + words[index].length( );
				if ( newWidth > lineWidth )
				{
					// break at the width
					return index;
				}
				// test next word
				index++;
				wordWidth = newWidth;
			}
			return index;
		}

		public String rightJustify( String[] words, int start, int end,
				int lineWidth )
		{
			StringBuilder sb = new StringBuilder( );

			int size = 0;
			for ( int i = start; i < end; i++ )
			{
				sb.append( words[i] );
				sb.append( " " );
				size += words[i].length( );
				size++;
			}
			if ( size > lineWidth )
			{
				sb.setLength( lineWidth );
			}
			else
			{
				padding( sb, lineWidth - size );
			}
			return sb.toString( );
		}

		public String justify( String[] words, int start, int end, int lineWidth )
		{
			int wordWidth = 0;
			for ( int i = start; i < end; i++ )
			{
				wordWidth += words[i].length( );
			}

			StringBuilder sb = new StringBuilder( );

			int spacecount = lineWidth - wordWidth;
			int gapCount = end - start - 1;
			if ( gapCount == 0 )
			{
				sb.append( words[start] );
				padding( sb, spacecount );
				return sb.toString( );
			}

			int whitespace = spacecount / gapCount;
			int remainspace = spacecount % gapCount;

			for ( int i = 0; i < remainspace; i++ )
			{
				sb.append( words[start + i] );
				padding( sb, whitespace + 1 );
			}

			for ( int i = remainspace; i < gapCount; i++ )
			{
				sb.append( words[start + i] );
				padding( sb, whitespace );
			}

			sb.append( words[end - 1] );
			return sb.toString( );
		}

		void padding( StringBuilder sb, int size )
		{
			for ( int i = 0; i < size; i++ )
			{
				sb.append( ' ' );
			}
		}
	}
}
