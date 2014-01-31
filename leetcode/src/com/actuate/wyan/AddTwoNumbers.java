
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class AddTwoNumbers
{

	@Test
	public void test( )
	{
		Assert.assertEquals(
				"",
				ListNode.toString( new Solution( ).addTwoNumbers(
						ListNode.toList( "" ), ListNode.toList( "" ) ) ) );
		Assert.assertEquals(
				"0,0,1",
				ListNode.toString( new Solution( ).addTwoNumbers(
						ListNode.toList( "9,9" ), ListNode.toList( "1" ) ) ) );
		Assert.assertEquals( "7,0,8", ListNode.toString( new Solution( )
				.addTwoNumbers( ListNode.toList( "2,4,3" ),
						ListNode.toList( "5,6,4" ) ) ) );

	}

	public class Solution
	{

		public ListNode addTwoNumbers( ListNode l1, ListNode l2 )
		{
			ListNode head = null;
			ListNode tail = null;
			int overflow = 0;
			ListNode node1 = l1;
			ListNode node2 = l2;
			while ( node1 != null && node2 != null )
			{
				int value = node1.val + node2.val + overflow;
				if ( value >= 10 )
				{
					overflow = 1;
					value -= 10;
				}
				else
				{
					overflow = 0;
				}
				ListNode node = new ListNode( value );
				if ( tail == null )
				{
					head = node;
					tail = node;
				}
				else
				{
					tail.next = node;
					tail = tail.next;
				}
				node1 = node1.next;
				node2 = node2.next;
			}

			ListNode remain = node1;
			if ( node2 != null )
			{
				remain = node2;
			}
			while ( remain != null )
			{
				int value = remain.val + overflow;
				if ( value >= 10 )
				{
					overflow = 1;
					value -= 10;
				}
				else
				{
					overflow = 0;
				}
				ListNode node = new ListNode( value );
				if ( tail == null )
				{
					head = node;
					tail = node;
				}
				else
				{
					tail.next = node;
					tail = tail.next;
				}
				remain = remain.next;
			}
			if ( overflow > 0 )
			{
				ListNode node = new ListNode( overflow );
				if ( tail == null )
				{
					head = node;
					tail = node;
				}
				else
				{
					tail.next = node;
					tail = tail.next;
				}
			}
			return head;
		}
	}
}
