
package com.actuate.wyan;

public class ReorderList
{

	public static void main( String[] args )
	{
		new ReorderList( ).test( );
	}

	public void test( )
	{
		ListNode head = createList( new int[]{1,2,3,4} );
		printList( head );
		new Solution( ).reorderList( head );
		printList( head );
	}

	ListNode createList( int[] values )
	{
		if ( values.length == 0 )
		{
			return null;
		}
		ListNode head = new ListNode( values[0] );
		ListNode tail = head;
		for ( int i = 1; i < values.length; i++ )
		{
			ListNode node = new ListNode( values[i] );
			node.val = values[i];
			tail.next = node;
			tail = node;
		}
		return head;
	}

	void printList( ListNode head )
	{
		ListNode node = head;
		while ( node != null )
		{
			System.out.print( node.val );
			System.out.print( "," );
			node = node.next;
		}
		System.out.println( );
	}

	class ListNode
	{

		int val;
		ListNode next;

		ListNode( int x )
		{
			val = x;
			next = null;
		}
	}

	public class Solution
	{

		public void reorderList( ListNode head )
		{
			int length = getLength(head);
			if ( length <= 2) 
			{
				return;
			}
			int splitLength = (length + 1)/ 2;
			ListNode list1 = head;
			ListNode tail1 = getNode( head, splitLength);
			ListNode list2 = tail1.next;
			tail1.next = null;
			list2 = reverse( list2 );
			merge( list1, list2 );
		}

		int getLength( ListNode head )
		{
			int length = 0;
			while ( head != null )
			{
				length++;
				head = head.next;

			}
			return length;
		}
		
		ListNode getNode( ListNode head, int index )
		{
			ListNode node = head;
			for ( int i = 1; i < index; i++ )
			{
				node = node.next;
			}
			return node;
		}

		ListNode reverse( ListNode head )
		{
			if ( head == null || head.next == null )
			{
				return head;
			}
			ListNode next = head.next;
			ListNode newHead = head;
			newHead.next = null;
			while ( next != null )
			{
				ListNode node = next.next;
				next.next = newHead;
				newHead = next;
				next = node;
			}
			return newHead;
		}

		ListNode merge( ListNode node1, ListNode node2 )
		{
			ListNode head = node1;
			ListNode tail = node1;
			node1 = node1.next;
			while ( node1 != null && node2 != null )
			{
				tail.next = node2;
				node2 = node2.next;
				tail = tail.next;
				tail.next = node1;
				node1 = node1.next;
				tail = tail.next;
				tail.next = null;
			}
			if ( node1 != null )
			{
				tail.next = node1;
			}
			if ( node2 != null )
			{
				tail.next = node2;
			}
			return head;
		}

		public ListNode reorder( ListNode head )
		{
			if ( head == null || head.next == null || head.next.next == null )
			{
				return null;
			}

			ListNode prev = head;
			ListNode tail = head.next;
			while ( tail.next != null )
			{
				prev = tail;
				tail = tail.next;
			}
			// next is the tail.
			// prev is the one before tail
			ListNode newHead = head.next;
			ListNode newTail = prev;
			head.next = tail;
			tail.next = newHead;
			newTail.next = null;
			return newHead;
		}
	}
}
