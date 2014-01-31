
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.HashMap;

public class WildcardMatching
{

	public static void main( String[] args )
	{
		Solution s = new Solution( );
//		String value = "aaabababaaabaababbbaaaabbbbbbabbbbabbbabbaabbababab";
//		String pattern = "*ab***ba**b*b*aaab*b";
		
//		String value = "aaabbbbaaaabbabbbbaabbabaaababaababaaaaaaabaaababbaababbaababbbaaaaabaabbabbaabaababbaabababbbbbbbbabbaabbaaabaababaabaababababababbbbbbabbabbaabbaabaaaaaababaabbbaaabaaabbbbbbbbbaabaabaaabaaabbabbabb";
//		String pattern = "****a*b*b**b*bbb*b**bba***b**b*b*b**ba***b*b*a*b*b*****a**aaa*baaa*ba*****a****ba*a****a**b*******a*a";
		
//		String value = "aaabababaaabaababbbaaaabbbbbbabbbbabbbabbaabbababab";
//		String pattern = "*ab***ba**b*b*aaab*b";
		
//		String value = "aab";
//		String pattern = "*a*";

		String value = "aa";
		String pattern = "?";
		
		boolean matched = s.isMatch( value, pattern );
		System.out.println( value + " " + pattern + ":" + matched );
		System.out.println( s.count);
		System.out.println( s.results.size( ));
	}

	static public class Solution
	{
		
		public boolean isMatch( String s, String p )
		{
			MatchSegment[] np = simplifyPattern( p );
			boolean result = isMatch( s.toCharArray( ), 0, s.length( ), np, 0, np.length );
			return result;
		}
		
		boolean enableCache = false;
		long count = 0;
		protected HashMap<String, Boolean> results = new HashMap<String, Boolean>( );
		
		static class MatchSegment
		{

			static enum TYPE {
				WILD, WORD
			}
			TYPE type;
			String word;
			int length;
			boolean wild;

			public String toString( )
			{
				if ( type == TYPE.WILD )
				{
					return "." + length + ( wild ? '+' : "" );
				}
				return word;
			}
		}

		void addSegment( ArrayList<MatchSegment> segments, String word )
		{
			if ( word.equals( "*" ) )
			{
				if ( segments.size( ) > 0 )
				{
					MatchSegment prevSeg = segments.get( segments.size( ) - 1 );
					if ( prevSeg.type == MatchSegment.TYPE.WILD )
					{
						prevSeg.wild = true;
						return;
					}
				}

				MatchSegment seg = new MatchSegment( );
				seg.type = MatchSegment.TYPE.WILD;
				seg.word = "*";
				seg.length = 0;
				seg.wild = true;
				segments.add( seg );
				return;
			}
			if ( word.equals( "?" ) )
			{
				int size = segments.size( );
				if ( size > 0 )
				{
					MatchSegment prevSeg = segments.get( size - 1 );
					if ( prevSeg.type == MatchSegment.TYPE.WILD )
					{
						prevSeg.length ++;
						return;
					}
				}
				MatchSegment seg = new MatchSegment( );
				seg.type = MatchSegment.TYPE.WILD;
				seg.word = "*";
				seg.length = 1;
				seg.wild = false;
				segments.add( seg );
				return;
			}

			MatchSegment seg = new MatchSegment( );
			seg.type = MatchSegment.TYPE.WORD;
			seg.word = word;
			seg.length = word.length( );
			segments.add( seg );
			return;
		}

		public MatchSegment[] simplifyPattern( String pattern )
		{
			ArrayList<MatchSegment> segments = new ArrayList<MatchSegment>( );
			int index = 0;
			while ( index < pattern.length( ) )
			{
				char ch = pattern.charAt( index );
				if ( ch == '*' )
				{
					addSegment( segments, "*" );
					index++;
				}
				else if ( ch == '?' )
				{
					addSegment( segments, "?" );
					index++;
				}
				else
				{
					int segEnd = index + 1;
					while ( segEnd < pattern.length( ) )
					{
						char segChar = pattern.charAt( segEnd );
						if ( segChar != '?' && segChar != '*' )
						{
							segEnd++;
							continue;
						}
						break;
					}
					String word = pattern.substring( index, segEnd );
					addSegment( segments, word );
					index = segEnd;
				}
			}
			if (segments.size( ) > 3) {
				enableCache = true;
			}
			else {
				enableCache = false;
			}
			return segments.toArray( new MatchSegment[segments.size( )] );
		}

		int getSplitIndex( MatchSegment[] pattern, int pattern_start,
				int pattern_end )
		{
			
			// find the longest word pattern
			
			int split_index = 0;
			int max_length = 0;
			for ( int i = pattern_start; i < pattern_end; i++ )
			{
				if ( pattern[i].type == MatchSegment.TYPE.WORD )
				{
					if ( max_length < pattern[i].length )
					{
						split_index = i;
						max_length = pattern[i].length;
					}
				}
			}
			return split_index;
		}

		boolean startWith( char[] ch, int start, int end, String word )
		{
			if ( end - start < word.length( ) )
			{
				return false;
			}
			for ( int i = 0; i < word.length( ); i++ )
			{
				if ( ch[start + i] != word.charAt( i ) )
				{
					return false;
				}
			}
			return true;
		}
		
		public boolean isMatch( char[] input, int input_start, int input_end,
				MatchSegment[] pattern, int pattern_start, int pattern_end )
		{
			if ( enableCache )
			{
				String key = input_start + "_" + input_end + "_"
						+ pattern_start + "_" + pattern_end;
				Boolean result = results.get( key );
				if ( result != null )
				{
					return result.booleanValue( );
				}
				boolean isMatch = doIsMatch( input, input_start, input_end,
						pattern, pattern_start, pattern_end );
				results.put( key, isMatch );
				return isMatch;

			}
			else
			{
				return doIsMatch( input, input_start, input_end,
						pattern, pattern_start, pattern_end );
			}
		}

		public boolean doIsMatch( char[] input, int input_start, int input_end,
				MatchSegment[] pattern, int pattern_start, int pattern_end )
		{
			count++;
			// find the longest constant segment

			int pattern_size = pattern_end - pattern_start;
			int input_size = input_end - input_start;
			if ( pattern_size == 0 )
			{
				if ( input_size == 0 )
				{
					return true;
				}
				return false;
			}
			if ( pattern_size > 1) 
			{
				int split_index = getSplitIndex( pattern, pattern_start,
						pattern_end );
					MatchSegment seg = pattern[split_index];
					for ( int i = input_start; i < input_end; i++ )
					{
						if ( startWith( input, i, input_end, seg.word ) )
						{
							boolean leftMatch = isMatch( input, input_start, i,
									pattern, pattern_start, split_index );
							if ( leftMatch )
							{
								boolean rightMatch = isMatch( input,
										i + seg.word.length( ), input_end, pattern,
										split_index + 1, pattern_end );
								if ( rightMatch )
								{
									return true;
								}
							}
						}
					}
					return false;
			}
			
			MatchSegment seg = pattern[pattern_start];
			if ( seg.type == MatchSegment.TYPE.WILD )
			{
				if ( seg.wild )
				{
					return input_size >= seg.length;
				}
				return input_size == seg.length;
			}
			if ( input_size == seg.word.length( ) )
			{
				return startWith( input, input_start, input_end, seg.word );
			}
			return false;
		}
	}
}
