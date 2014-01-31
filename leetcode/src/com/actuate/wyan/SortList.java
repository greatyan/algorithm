
package com.actuate.wyan;

public class SortList
{

	static class ListNode
	{

		int val;
		ListNode next;

		ListNode( int x )
		{
			val = x;
			next = null;
		}
	}

	static ListNode createList( int[] values )
	{
		if ( values == null || values.length == 0 )
		{
			return null;
		}
		ListNode head = new ListNode( values[0] );
		ListNode tail = head;
		for ( int i = 1; i < values.length; i++ )
		{
			ListNode node = new ListNode( values[i] );
			tail.next = node;
			tail = node;
		}
		return head;
	}

	public static void main( String[] args )
	{
		ListNode list = createList( new int[]{3} );
		printList( list );
		ListNode result = new Solution( ).sortList( list );
		printList( result );
	}

	static void printList( ListNode head )
	{
		System.out.print( "list:" );
		while ( head != null )
		{
			System.out.print( head.val + "," );
			head = head.next;
		}
		System.out.println( );
	}

	static public class Solution
	{

		public ListNode sortList( ListNode head )
		{
			if ( head == null )
			{
				return null;
			}
			int length = getLength( head );
			ListNode[] results = sortAndSplit( head, length );
			return results[0];
		}

		public ListNode[] sortAndSplit( ListNode head, int length )
		{
			if ( length == 1 )
			{
				ListNode remain = head.next;
				head.next = null;
				return new ListNode[]{head, remain};
			}
			else if ( length == 2 )
			{
				ListNode node1 = head;
				ListNode node2 = head.next;
				ListNode remain = node2.next;
				if ( node1.val > node2.val )
				{
					node2.next = node1;
					node1.next = null;
					return new ListNode[]{node2, remain};
				}
				else
				{
					node2.next = null;
					return new ListNode[]{node1, remain};
				}
			}

			int length1 = length / 2;
			int length2 = length - length1;
			ListNode[] results = sortAndSplit( head, length1 );
			ListNode sortedList1 = results[0];
			ListNode remainList = results[1];
			if ( remainList == null )
			{
				return results;
			}
			results = sortAndSplit( remainList, length2 );
			ListNode sortedList2 = results[0];
			remainList = results[1];
			ListNode newList = merge( sortedList1, sortedList2 );
			return new ListNode[]{newList, remainList};
		}

		ListNode merge( ListNode list1, ListNode list2 )
		{
			if ( list1 == null )
			{
				return list2;
			}
			if ( list2 == null )
			{
				return list1;
			}

			ListNode head = new ListNode( -1 );
			ListNode tail = head;
			while ( list1 != null && list2 != null )
			{
				if ( list1.val > list2.val )
				{
					tail.next = list2;
					list2 = list2.next;
				}
				else
				{
					tail.next = list1;
					list1 = list1.next;
				}
				tail = tail.next;
			}
			if ( list1 != null )
			{
				tail.next = list1;
			}
			if ( list2 != null )
			{
				tail.next = list2;
			}
			return head.next;
		}

		int getLength( ListNode node )
		{
			int length = 0;
			while ( node != null )
			{
				length++;
				node = node.next;
			}
			return length;
		}
	}

}
