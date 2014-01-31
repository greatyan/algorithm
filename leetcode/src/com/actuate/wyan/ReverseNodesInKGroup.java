
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class ReverseNodesInKGroup
{

	@Test
	public void test( )
	{
		Assert.assertEquals(
				"2,1,4,3,5",
				ListNode.toString( new Solution( ).reverseKGroup(
						ListNode.toList( "1,2,3,4,5" ), 2 ) ) );
		Assert.assertEquals(
				"3,2,1,4,5",
				ListNode.toString( new Solution( ).reverseKGroup(
						ListNode.toList( "1,2,3,4,5" ), 3 ) ) );
	}

	public class Solution
	{

		public ListNode reverseKGroup( ListNode head, int k )
		{
			int length = getLength( head );
			if ( length < k )
			{
				return head;
			}
			ListNode[] result = reverse( head, k );
			ListNode newHead = result[0];
			ListNode tail = result[1];
			int repeatCount = length / k - 1;
			for ( int i = 0; i < repeatCount; i++ )
			{
				result = reverse( tail.next, k );
				tail.next = result[0];
				tail = result[1];
			}
			return newHead;
		}

		ListNode[] reverse( ListNode head, int k )
		{
			ListNode tail = head;
			ListNode newHead = head;
			ListNode node = head.next;
			for ( int i = 1; i < k; i++ )
			{
				ListNode next = node.next;

				if ( newHead == null )
				{
					newHead = node;
					tail = newHead;
				}
				else
				{
					node.next = newHead;
					newHead = node;
					tail.next = next;
				}
				node = next;
			}
			return new ListNode[]{newHead, tail};
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
	}

}
