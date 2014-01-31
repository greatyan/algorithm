
package com.actuate.wyan;

import java.math.BigInteger;
import java.util.HashSet;

public class MaxPointsonALine
{

	/**
	 * Definition for a point.
	 */
	static class Point
	{

		int x;
		int y;

		Point( )
		{
			x = 0;
			y = 0;
		}

		Point( int a, int b )
		{
			x = a;
			y = b;
		}
	}

	public static void main( String[] args )
	{
//		Point[] points = new Point[]{new Point( -240, -657 ),
//				new Point( -27, -188 ), new Point( -616, -247 ),
//				new Point( -264, -311 ), new Point( -352, -393 ),
//				new Point( -270, -748 ), new Point( 3, 4 ),
//				new Point( -308, -87 ), new Point( 150, 526 ),
//				new Point( 0, -13 ), new Point( -7, -40 ),
//				new Point( -3, -10 ), new Point( -531, -892 ),
//				new Point( -88, -147 ), new Point( 4, -3 ),
//				new Point( -873, -555 ), new Point( -582, -360 ),
//				new Point( -539, -207 ), new Point( -118, -206 ),
//				new Point( 970, 680 ), new Point( -231, -47 ),
//				new Point( 352, 263 ), new Point( 510, 143 ),
//				new Point( 295, 480 ), new Point( -590, -990 ),
//				new Point( -236, -402 ), new Point( 308, 233 ),
//				new Point( -60, -111 ), new Point( 462, 313 ),
//				new Point( -270, -748 ), new Point( -352, -393 ),
//				new Point( -35, -148 ), new Point( -7, -40 ),
//				new Point( 440, 345 ), new Point( 388, 290 ),
//				new Point( 270, 890 ), new Point( 10, -7 ),
//				new Point( 60, 253 ), new Point( -531, -892 ),
//				new Point( 388, 290 ), new Point( -388, -230 ),
//				new Point( 340, 85 ), new Point( 0, -13 ),
//				new Point( 770, 473 ), new Point( 0, 73 ),
//				new Point( 873, 615 ), new Point( -42, -175 ),
//				new Point( -6, -8 ), new Point( 49, 176 ),
//				new Point( 308, 222 ), new Point( 170, 27 ),
//				new Point( -485, -295 ), new Point( 170, 27 ),
//				new Point( 510, 143 ), new Point( -18, -156 ),
//				new Point( -63, -316 ), new Point( -28, -121 ),
//				new Point( 396, 304 ), new Point( 472, 774 ),
//				new Point( -14, -67 ), new Point( -5, 7 ),
//				new Point( -485, -295 ), new Point( 118, 186 ),
//				new Point( -154, -7 ), new Point( -7, -40 ),
//				new Point( -97, -35 ), new Point( 4, -9 ),
//				new Point( -18, -156 ), new Point( 0, -31 ),
//				new Point( -9, -124 ), new Point( -300, -839 ),
//				new Point( -308, -352 ), new Point( -425, -176 ),
//				new Point( -194, -100 ), new Point( 873, 615 ),
//				new Point( 413, 676 ), new Point( -90, -202 ),
//				new Point( 220, 140 ), new Point( 77, 113 ),
//				new Point( -236, -402 ), new Point( -9, -124 ),
//				new Point( 63, 230 ), new Point( -255, -118 ),
//				new Point( 472, 774 ), new Point( -56, -229 ),
//				new Point( 90, 228 ), new Point( 3, -8 ), new Point( 81, 196 ),
//				new Point( 970, 680 ), new Point( 485, 355 ),
//				new Point( -354, -598 ), new Point( -385, -127 ),
//				new Point( -2, 7 ), new Point( 531, 872 ),
//				new Point( -680, -263 ), new Point( -21, -94 ),
//				new Point( -118, -206 ), new Point( 616, 393 ),
//				new Point( 291, 225 ), new Point( -240, -657 ),
//				new Point( -5, -4 ), new Point( 1, -2 ), new Point( 485, 355 ),
//				new Point( 231, 193 ), new Point( -88, -147 ),
//				new Point( -291, -165 ), new Point( -176, -229 ),
//				new Point( 154, 153 ), new Point( -970, -620 ),
//				new Point( -77, 33 ), new Point( -60, -111 ),
//				new Point( 30, 162 ), new Point( -18, -156 ),
//				new Point( 425, 114 ), new Point( -177, -304 ),
//				new Point( -21, -94 ), new Point( -10, 9 ),
//				new Point( -352, -393 ), new Point( 154, 153 ),
//				new Point( -220, -270 ), new Point( 44, -24 ),
//				new Point( -291, -165 ), new Point( 0, -31 ),
//				new Point( 240, 799 ), new Point( -5, -9 ),
//				new Point( -70, -283 ), new Point( -176, -229 ),
//				new Point( 3, 8 ), new Point( -679, -425 ),
//				new Point( -385, -127 ), new Point( 396, 304 ),
//				new Point( -308, -352 ), new Point( -595, -234 ),
//				new Point( 42, 149 ), new Point( -220, -270 ),
//				new Point( 385, 273 ), new Point( -308, -87 ),
//				new Point( -54, -284 ), new Point( 680, 201 ),
//				new Point( -154, -7 ), new Point( -440, -475 ),
//				new Point( -531, -892 ), new Point( -42, -175 ),
//				new Point( 770, 473 ), new Point( 118, 186 ),
//				new Point( -385, -127 ), new Point( 154, 153 ),
//				new Point( 56, 203 ), new Point( -616, -247 )};

		/*
		 * Point[] points = new Point[]{new Point( 0, 0 ), new Point( 1, 1 ),
		 * new Point( 1, -1 )};
		 */

		// Point[] points = new Point[]{new Point( 0, 0 ), new Point( 1, 1 ),
		// new Point( 1, -1 )};

		 Point[] points = new Point[]{new Point( 0, 0 ), new Point( 0, 0 ),
		 new Point( 0, 0 )};
		
		Solution s = new Solution( );
		long start = System.currentTimeMillis( );
		for ( int i = 0; i < 1024; i++ )
		{
			int size = s.maxPoints( points );
			// System.out.println( size );
		}
		long end = System.currentTimeMillis( );
		System.out.println( ( end - start ) );
	}

	static public class Solution
	{
		class Line
		{

			int a;
			int b;
			int c;

			Line( Point p1, Point p2 )
			{
				setup( p1, p2 );
			}

			boolean isInLine( Point p )
			{
				return ( a * p.x + b * p.y + c ) == 0;
			}

			void setup( Point p1, Point p2 )
			{
				a = p2.y - p1.y;
				b = p1.x - p2.x;
				c = p2.x * p1.y - p1.x * p2.y;

				int gcd = gcd( a, b, c );
				a = a / gcd;
				b = b / gcd;
				c = c / gcd;

				if ( a < 0 )
				{
					a = -a;
					b = -b;
					c = -c;
				}
			}

			int gcd( int a, int b, int c )
			{
				if ( a == 0 )
				{
					if ( b == 0 )
					{
						if ( c == 0 )
						{
							return 1;
						}
						if ( c > 0 )
						{
							return c;
						}
						return -c;
					}
					else
					{
						if ( c == 0 )
						{
							if ( b > 0 )
							{
								return b;
							}
							return -b;
						}
						BigInteger B = BigInteger.valueOf( b );
						BigInteger C = BigInteger.valueOf( c );
						return B.gcd( C ).intValue( );
					}
				}
				else
				{
					if ( b == 0 )
					{
						if ( c == 0 )
						{
							if ( a > 0 )
							{
								return a;
							}
							return -a;
						}
						BigInteger A = BigInteger.valueOf( a );
						BigInteger C = BigInteger.valueOf( c );
						return A.gcd( C ).intValue( );
					}
					else
					{
						if ( c == 0 )
						{
							BigInteger A = BigInteger.valueOf( a );
							BigInteger B = BigInteger.valueOf( b );
							return A.gcd( B ).intValue( );
						}
						else
						{
							BigInteger A = BigInteger.valueOf( a );
							BigInteger B = BigInteger.valueOf( b );
							BigInteger C = BigInteger.valueOf( c );
							return A.gcd( B ).gcd( C ).intValue( );
						}
					}
				}
			}

			public int hashCode( )
			{
				return a + b + c;
			}

			public boolean equals( Line l )
			{
				return a == l.a && b == l.b && c == l.c;
			}
		}

		HashSet<Line> checkLines = new HashSet<Line>( );
		HashSet<String> checkPoints = new HashSet<String>();
		
		public int maxPoints( Point[] points )
		{
			if ( points == null || points.length == 0 )
			{
				return 0;
			}
			if ( points.length == 1 )
			{
				return 1;
			}
			if ( points.length == 2 )
			{
				return 2;
			}
			int maxCount = 0;
			for ( Point p1 : points )
			{
				for ( Point p2 : points )
				{
					int count = 0;
					if ( p1.x == p2.x && p1.y == p2.y )
					{
						if ( !checkPoints.contains( p1.x + "_" + p1.y ) )
						{
							checkPoints.add( p1.x + "_" + p1.y );
							for ( Point p3 : points )
							{
								if ( p1.x == p3.x && p1.y == p3.y )
								{
									count++;
								}
							}
						}
					}
					else
					{
						Line line = new Line( p1, p2 );
						if ( !checkLines.contains( line ) )
						{
							for ( Point p3 : points )
							{
								if ( line.isInLine( p3 ) )
								{
									count++;
								}
							}
						}
					}
					if ( maxCount < count )
					{
						maxCount = count;
					}
				}
			}
			return maxCount;
		}
	}
}
