
package com.actuate.wyan;

import java.util.HashMap;
import java.util.LinkedList;

public class MinimumWindowSubstring
{

	public static void main( String[] args )
	{

		 String S = "ADOBECODEBANC";
		 String T = "ABC";
		//String S = "a";
		//String T = "a";
		String r = new Solution( ).minWindow( S, T );
		System.out.println( r );
	}

	static public class Solution
	{

		public String minWindow( String S, String T )
		{
			if ( S.length( ) == 0 || T.length( ) == 0 )
			{
				return "";
			}
			if ( T.length( ) > S.length( ) )
			{
				return "";
			}
			int start = -1;
			int end = -1;
			int minSize = Integer.MAX_VALUE;
			TestBuffer buffer = new TestBuffer( T );
			// assume t has no duplicate characters
			for ( int i = 0; i < S.length( ); i++ )
			{
				buffer.append( i, S.charAt( i ) );
				int size = buffer.getSize( );
				if ( size != -1 && minSize > size )
				{
					minSize = size;
					start = buffer.getStart( );
					end = buffer.getEnd( );
				}
			}
			if ( start != -1 && end != -1 )
			{
				return S.substring( start, end + 1 );
			}
			return "";
		}

		class CharBuffer
		{

			char ch;
			int length;
			LinkedList<Integer> position;

			CharBuffer( char ch )
			{
				this.ch = ch;
				this.position = new LinkedList<Integer>( );
			}

			public int add( int index )
			{
				if ( position.size( ) == length )
				{
					int result = position.removeFirst( );
					position.add( index );
					return result;
				}
				else
				{
					position.add( index );
				}
				return -1;
			}

			public boolean isFull( )
			{
				return position.size( ) == length;
			}

			public boolean isEmpty( )
			{
				return position.isEmpty( );
			}

			public int getFirst( )
			{
				return position.getFirst( );
			}

			public int getLast( )
			{
				return position.getLast( );
			}
		}

		class TestBuffer
		{

			int start;
			int end;

			HashMap<Character, CharBuffer> buffers;
			int fullCount;

			TestBuffer( String value )
			{
				buffers = new HashMap<Character, CharBuffer>( );
				for ( int i = 0; i < value.length( ); i++ )
				{
					char ch = value.charAt( i );
					CharBuffer charBuffer = buffers.get( ch );
					if ( charBuffer == null )
					{
						charBuffer = new CharBuffer( ch );
						buffers.put( ch, charBuffer );
					}
					charBuffer.length++;
				}
				start = -1;
				end = -1;
			}

			public void append( int index, char ch )
			{
				CharBuffer charBuffer = buffers.get( ch );
				if ( charBuffer == null )
				{
					return;
				}

				if ( start == -1 )
				{
					start = index;
				}
				end = index;
				if ( !charBuffer.isFull( ) )
				{
					charBuffer.add( index );
					if ( charBuffer.isFull( ) )
					{
						fullCount++;
					}
					return;
				}

				// recalculate the
				int prevIndex = charBuffer.add( index );
				if ( prevIndex == start )
				{
					start = index;
					for ( CharBuffer buff : buffers.values( ) )
					{
						if ( !buff.isEmpty( ) )
						{
							int first = buff.getFirst( );
							if ( first < start )
							{
								start = first;
							}
						}
					}
				}
			}

			public int getSize( )
			{
				if ( fullCount == buffers.size( ) )
				{
					return end - start + 1;
				}
				return -1;
			}

			public int getStart( )
			{
				return start;
			}

			public int getEnd( )
			{
				return end;
			}
		}

	}
}
