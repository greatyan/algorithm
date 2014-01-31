
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class RemoveNthNodeFromEndOfList
{

	@Test
	public void test( )
	{

		Assert.assertEquals(
				"1",
				ListNode.toString( new Solution( ).removeNthFromEnd(
						ListNode.toList( "1,2" ), 1 ) ) );

		Assert.assertEquals(
				"",
				ListNode.toString( new Solution( ).removeNthFromEnd(
						ListNode.toList( "1" ), 1 ) ) );
	}

	public class Solution
	{

		public ListNode removeNthFromEnd( ListNode head, int n )
		{
			ListNode[] nodes = new ListNode[n + 1];
			int index = 0;
			ListNode node = head;
			while ( node != null )
			{
				nodes[index] = node;
				node = node.next;
				index = ( index + 1 ) % ( n + 1 );
			}

			int removeIndex = ( index + 1 ) % ( n + 1 );
			// remove the head
			if ( head == nodes[removeIndex] )
			{
				return head.next;
			}

			//exceed the list length
			if ( nodes[index] == null )
			{
				return head;
			}
			
			if ( nodes[index].next != null )
			{
				nodes[index].next = nodes[index].next.next;
			}
			return head;

		}
	}
}
