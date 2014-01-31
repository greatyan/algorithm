
package com.actuate.wyan.sort;

public class LinkedMergeSort
{

	class Node
	{

		int value;
		Node next;
	}

	void sort( int[] values )
	{
		Node list = createList( values );
		sort( list );
	}

	Node createList( int[] values )
	{
		Node head = new Node( );
		head.value = values[0];
		Node node = head;
		for ( int i = 1; i < values.length; i++ )
		{
			Node next = new Node( );
			next.value = values[i];
			node.next = next;
			node = next;
		}
		node.next = null;
		return head;

	}

	void sort( Node list )
	{

	}

	class List
	{

		Node head;
	}

	Node merge_sort( Node node, Node result )
	{
		Node next = node.next;
		if ( next == null) 
		{
			result.next = node;
			return null;
		}
		Node remain = next.next;
		
		if ( node.value <= next.value) {
			result.next = node;
			next.next = null;
		}
		else {
			result.next = next;
			next.next = node;
			node.next = null;
		}
		return remain;
	
		Node list2 = merge_sort( list );
		Node nextList = merge_sort( list2 );
		list.head = merge_list( list.head, list2.head);
		return nextList;
	}

	Node merge_list( Node list1, Node list2 )
	{
		Node node1 = list1;
		Node node2 = list2;
		Node head = null;
		if ( node1.value < node2.value )
		{
			head = node1;
			node1 = node1.next;
		}
		else
		{
			head = node2;
			node2 = node2.next;
		}

		Node node = head;
		while ( node1 != null && node2 != null )
		{
			if ( node1.value > node2.value )
			{
				node.next = node1;
				node1 = node1.next;
			}
			else
			{
				node.next = node2;
				node2 = node2.next;
			}
			node = node.next;
		}

		if ( node1 != null )
		{
			node.next = node1;
		}

		if ( node2 != null )
		{
			node = node2;
		}
		return head;
	}

}
