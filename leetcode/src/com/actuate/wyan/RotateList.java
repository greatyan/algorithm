
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class RotateList
{

	@Test
	public void test( )
	{
		Assert.assertEquals(
				ListNode.toString( new Solution( ).rotateRight(
						ListNode.toList( "1,2,3,4,5" ), 3 ) ), "3,4,5,1,2" );
		Assert.assertEquals(
				ListNode.toString( new Solution( ).rotateRight(
						ListNode.toList( "1,2,3" ), 1 ) ), "3,1,2" );
	}

	public class Solution
	{

		public ListNode rotateRight( ListNode head, int n )
		{

			if ( head == null )
			{
				return null;
			}

			if ( n == 0 )
			{
				return head;
			}

			ListNode node = head;
			int size = 0;
			ListNode tail = head;
			while ( node != null )
			{
				size++;
				tail = node;
				node = node.next;
			}

			if ( size == 1 )
			{
				return head;
			}

			n = n % size;
			if ( n == 0 )
			{
				return head;
			}

			node = head;
			for ( int i = 0; i < size - n - 1; i++ )
			{
				node = node.next;
			}

			ListNode newHead = node.next;
			node.next = null;
			tail.next = head;
			return newHead;
		}
	}
}
