
package com.actuate.wyan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidNumber
{

	public static void main( String[] args )
	{
		String[] values = new String[]{"abc", " 123 ", " 12.3", "1E32", " .3 ", "1.0e-32"};
		Solution s = new Solution( );
		for ( String v : values )
		{
			boolean r = s.isNumber( v );
			System.out.println( v + ":" + r );
		}
	}

	static public class Solution
	{

		Pattern[] patterns = new Pattern[]{
				Pattern.compile( "(\\s)*(\\+|-)?\\d+(\\.\\d+)?((E|e)(\\+|-)?\\d+)?(\\s)*" ),
				Pattern.compile( "(\\s)*(\\+|-)?(\\.\\d+)((E|e)(\\+|-)?\\d+)?(\\s)*" )};

		public boolean isNumber( String s )
		{
			for ( Pattern pattern : patterns )
			{
				Matcher m = pattern.matcher( s );
				if ( m.matches( ) )
				{
					return true;
				}
				return m.matches( );
			}
			return false;
		}
	}
}
