
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class ConvertSortedListToBinarySearchTree
{

	@Test
	public void test( )
	{
		Assert.assertEquals( "#", TreeNode.toString( new Solution( )
				.sortedListToBST( ListNode.toList( "" ) ) ) );
		Assert.assertEquals( "1,#,#", TreeNode.toString( new Solution( )
				.sortedListToBST( ListNode.toList( "1" ) ) ) );
		Assert.assertEquals( "2,1,#,#,#", TreeNode.toString( new Solution( )
				.sortedListToBST( ListNode.toList( "1,2" ) ) ) );
		Assert.assertEquals( "2,1,3,#,#,#,#", TreeNode.toString( new Solution( )
				.sortedListToBST( ListNode.toList( "1,2,3" ) ) ) );
		Assert.assertEquals( "3,2,4,1,#,#,#,#,#", TreeNode
				.toString( new Solution( ).sortedListToBST( ListNode
						.toList( "1,2,3,4" ) ) ) );
	}

	public class Solution
	{

		public TreeNode sortedListToBST( ListNode head )
		{

			// first get length
			int length = length( head );
			if ( length == 0 )
			{
				return null;
			}

			Result result = toTree( head, length );
			return result.tree;

		}

		int length( ListNode node )
		{
			int length = 0;
			while ( node != null )
			{
				length++;
				node = node.next;
			}
			return length;
		}

		class Result
		{

			TreeNode tree;
			ListNode next;
		}

		Result toTree( ListNode list, int length )
		{
			if ( length == 1 )
			{
				Result result = new Result( );
				result.tree = new TreeNode( list.val );
				result.next = list.next;
				return result;
			}
			// get the left tree
			int leftLength = length / 2;
			Result leftResult = toTree( list, leftLength );
			ListNode nextList = leftResult.next;
			// get the root
			TreeNode root = new TreeNode( nextList.val );
			root.left = leftResult.tree;
			nextList = nextList.next;
			// get the right tree
			int rightLength = length - leftLength - 1;
			if ( rightLength > 0 )
			{
				Result rightResult = toTree( nextList, rightLength );
				nextList = rightResult.next;
				root.right = rightResult.tree;
			}
			Result result = new Result( );
			result.tree = root;
			result.next = nextList;
			return result;
		}
	}
}
