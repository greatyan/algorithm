
package com.actuate.wyan;

import java.util.LinkedList;

public class ValidParentheses
{

	public class Solution
	{

		public boolean isValid( String s )
		{

			LinkedList<Character> stacks = new LinkedList<Character>( );
			for  ( int i = 0; i < s.length( ); i++) {
				char ch = s.charAt( i );
				switch ( ch) {
					case '(':
						stacks.push( ')' );
						break;
					case '{':
						stacks.push( '}' );
						break;
					case '[':
						stacks.push( ']' );
						break;
					case '}':
					case ']':
					case ')':
					{
						if ( stacks.isEmpty( )) {
							return false;
						}
						if ( ch != stacks.pop( )) {
							return false;
						}
					}
				}
			}
			return stacks.isEmpty( );
		}
	}
}
