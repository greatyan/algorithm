
package com.actuate.wyan;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal
{

	@Test
	public void test( )
	{
		Assert.assertEquals(
				"1,#,2,#,3,#,4,#,#",
				TreeNode.toString( new Solution( ).buildTree( new int[]{1, 2,
						3, 4}, new int[]{1, 2, 3, 4} ) ) );
		Assert.assertEquals(
				"4,3,#,2,#,1,#,#,#",
				TreeNode.toString( new Solution( ).buildTree( new int[]{4, 3,
						2, 1}, new int[]{1, 2, 3, 4} ) ) );
		Assert.assertEquals(
				"1,2,3,4,5,6,7,#,#,#,#,#,#,#,#",
				TreeNode.toString( new Solution( ).buildTree( new int[]{1, 2,
						4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 6, 3, 7} ) ) );
	}

	public class Solution
	{

		public TreeNode buildTree( int[] preorder, int[] inorder )
		{
			if ( preorder == null || preorder.length == 0 )
			{
				return null;
			}
			
			TreeNode root = new TreeNode( preorder[0] );
			LinkedList<TreeNode> nodes = new LinkedList<TreeNode>( );
			nodes.addLast( root );
			int preoff = 1;
			int inoff = 0;

			boolean rightNode = false;
			while ( !nodes.isEmpty( ) )
			{
				TreeNode parent = nodes.getLast( );
				if ( parent.val == inorder[inoff] )
				{
					inoff++;
					nodes.removeLast( );
					rightNode = true;
				}
				else
				{
					if ( preorder[preoff] == inorder[inoff] )
					{
						if ( rightNode) {
							parent.right = new TreeNode( preorder[preoff] );
							nodes.addLast( parent.right );
						}
						else {
							parent.right = new TreeNode( preorder[preoff] );
							nodes.addLast( parent.right );
						}
						preoff++;
						inoff++;
					}
					else
					{
						parent.left = new TreeNode( preorder[preoff] );
						preoff++;
						nodes.addLast( parent.left );
					}
				}
			}
			return root;
		}
	}
}
