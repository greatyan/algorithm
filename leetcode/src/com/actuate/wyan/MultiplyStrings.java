
package com.actuate.wyan;

public class MultiplyStrings
{

	public static void main( String[] args )
	{
		new MultiplyStrings( ).test( );
	}

	public void test( )
	{
		System.out.println( new Solution( ).multiply( "90", "99" ) );
	}

	public class Solution
	{

		public String multiply( String num1, String num2 )
		{
			if ( num1 == null || num1.length( ) == 0 )
			{
				return "0";
			}
			if ( num2 == null || num2.length( ) == 0 )
			{
				return "0";
			}
			return new MultiplyOperator( ).multiply( normalize(num1), normalize(num2) );
		}

		String normalize(String value)
		{
			if ( value == null || value.length( ) == 0) {
				return "0";
			}
			//remove all leading zeros
			int leadingZeros = 0;
			for ( int i = 0; i < value.length( ); i++) {
				if ( value.charAt( i ) != '0') {
					break;
				}
				leadingZeros++;
			}
			if ( leadingZeros == value.length( )) {
				return "0";
			}
			return value.substring( leadingZeros );
		}

		
		
		class MultiplyOperator
		{

			int overflow;

			private char multiply( char c1, char c2 )
			{
				int v1 = c1 - '0';
				int v2 = c2 - '0';
				int v = v1 * v2 + overflow;
				overflow = v / 10;
				v = v % 10;
				return (char) ( '0' + v );
			}

			private String multiply( String v1, char v2 )
			{
				if ( v2 == '0')
				{
					return "0";
				}
				StringBuffer sb = new StringBuffer( );
				for ( int i = v1.length( ) - 1; i >= 0; i-- )
				{
					char result = multiply( v1.charAt( i ), v2 );
					sb.append( result );
				}
				if ( overflow != 0 )
				{
					sb.append( (char) ( overflow + '0' ) );
					overflow = 0;
				}
				return sb.reverse( ).toString( );
			}

			public String multiply( String v1, String v2 )
			{
				String result = multiply( v1, v2.charAt( v2.length( ) - 1 ) );
				for ( int i = v2.length( ) - 2; i >= 0; i-- )
				{
					String value = multiply( v1, v2.charAt( i ) );
					if ( !value.equals( "0" ) )
					{
						for ( int j = i + 1; j < v2.length( ); j++ )
						{
							value += '0';
						}
					}
					result = new AddOperator( ).add( value, result );
				}
				return result;
			}
		}

		class AddOperator
		{

			int overflow;

			private char add( char c1, char c2 )
			{
				int v1 = c1 - '0';
				int v2 = c2 - '0';
				int v = v1 + v2 + overflow;
				overflow = v / 10;
				v = v % 10;
				return (char) ( '0' + v );
			}

			public String add( String v1, String v2 )
			{
				if ( v1.length( ) < v2.length( ) )
				{
					return add( v2, v1 );
				}

				StringBuffer sb = new StringBuffer( );
				int delta = v1.length( ) - v2.length( );
				for ( int i = v2.length( ) - 1; i >= 0; i-- )
				{
					char v = add( v1.charAt( delta + i ), v2.charAt( i ) );
					sb.append( v );
				}
				for ( int i = delta - 1; i >= 0; i-- )
				{
					char v = add( v1.charAt( i ), '0' );
					sb.append( v );
				}

				if ( overflow != 0 )
				{
					sb.append( (char) ( '0' + overflow ) );
				}
				overflow = 0;
				return sb.reverse( ).toString( );
			}
		}
	}
}
