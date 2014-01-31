
package com.actuate.wyan;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class FlattenBinaryTreeToLinkedList
{

	@Test
	public void test( )
	{
		TreeNode root = TreeNode.toTree( "1,2,6,3,4" );
		new Solution( ).flatten( root );
		Assert.assertEquals( "1,#,2,#,3,#,4,#,6,#,#", TreeNode.toString( root ) );

		root = TreeNode.toTree( "1,2" );
		new Solution( ).flatten( root );
		Assert.assertEquals( "1,#,2,#,#", TreeNode.toString( root ) );

	}

	public class Solution
	{

		public void flatten( TreeNode root )
		{
			if ( root == null )
			{
				return;
			}

			LinkedList<TreeNode> nodes = new LinkedList<TreeNode>( );

			TreeNode node = root;
			do
			{
				if ( node.right != null )
				{
					nodes.push( node.right );
					node.right = null;
				}
				if ( node.left == null )
				{
					if ( nodes.isEmpty( ) )
					{
						// break
						return;
					}
					TreeNode next = nodes.pop( );
					node.right = next;
					node = next;
				}
				else
				{
					TreeNode nextNode = node.left;
					node.right = nextNode;
					node.left = null;
					node = nextNode;
				}
			} while ( true );

		}
	}
}
