
package com.actuate.wyan;

import org.junit.Assert;

public class ListNode
{

	int val;
	ListNode next;

	public ListNode( int x )
	{
		val = x;
		next = null;
	}

	public static ListNode toList( String values )
	{
		if ( values.isEmpty( ) )
		{
			return null;
		}
		String[] tokens = values.split( "," );
		ListNode head = new ListNode( Integer.parseInt( tokens[0] ) );
		ListNode tail = head;
		for ( int i = 1; i < tokens.length; i++ )
		{
			tail.next = new ListNode( Integer.parseInt( tokens[i] ) );
			tail = tail.next;
		}
		return head;
	}

	public static String toString( ListNode node )
	{
		if ( node == null )
		{
			return "";
		}
		StringBuilder sb = new StringBuilder( );
		while ( node != null )
		{
			sb.append( node.val );
			sb.append( "," );
			node = node.next;
		}
		sb.setLength( sb.length( ) - 1 );
		return sb.toString( );
	}

	public static void main( String[] args )
	{
		ListNode list = ListNode.toList( "1,2,3,4,5" );
		String output = ListNode.toString( list );
		System.out.println( output);
	}

}
