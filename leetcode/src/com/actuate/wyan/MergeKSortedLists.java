
package com.actuate.wyan;

import java.util.ArrayList;

public class MergeKSortedLists
{

	public class Solution
	{

		public ListNode mergeKLists( ArrayList<ListNode> lists )
		{
			ListNode head = null;
			ListNode tail = null;
			// remove empty list from lists
			for ( int i = lists.size( ) - 1; i >= 0; i-- )
			{
				if ( lists.get( i ) == null )
				{
					lists.remove( i );
				}
			}
			while ( !lists.isEmpty( ) )
			{
				int index = getSmallest( lists, 0, lists.size( ) );
				ListNode node = lists.get( index );
				ListNode next = node.next;
				if ( next != null )
				{
					lists.set( index, next );
				}
				else
				{
					lists.remove( index );
				}
				if ( tail == null )
				{
					head = node;
				}
				else
				{
					tail.next = node;
				}
				node.next = null;
				tail = node;
			}
			return head;
		}
	}

	int getSmallest( ArrayList<ListNode> lists, int offset, int size )
	{
		if ( size == 1 )
		{
			return offset;
		}
		if ( size == 2 )
		{
			return getSmaller( lists, offset, offset + 1 );
		}
		int leftSize = size / 2;
		int leftIndex = getSmallest( lists, offset, leftSize );
		int rightIndex = getSmallest( lists, offset + leftSize, size - leftSize );
		return getSmaller( lists, leftIndex, rightIndex );
	}

	int getSmaller( ArrayList<ListNode> lists, int off1, int off2 )
	{
		ListNode node1 = lists.get( off1 );
		ListNode node2 = lists.get( off2 );
		if ( node1.val <= node2.val )
		{
			return off1;
		}
		return off2;
	}

}
