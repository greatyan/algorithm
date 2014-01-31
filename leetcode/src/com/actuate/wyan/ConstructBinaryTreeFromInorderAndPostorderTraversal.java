
package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal
{

	@Test
	public void test( )
	{
		Assert.assertEquals(
				"1,2,3,4,5,6,7,#,#,#,#,#,#,#,#",
				TreeNode.toString( new Solution( ).buildTree( new int[]{4, 2,
						5, 1, 6, 3, 7}, new int[]{4, 5, 2, 6, 7, 3, 1} ) ) );
	}

	public class Solution
	{

		public TreeNode buildTree( int[] inorder, int[] postorder )
		{
			if ( inorder == null || postorder == null )
			{
				return null;
			}
			return buildTree( inorder, 0, postorder, 0, inorder.length );
		}

		public TreeNode buildTree( int[] inorder, int off1, int[] postorder,
				int off2, int length )
		{
			if ( length == 0 )
			{
				return null;
			}
			if ( length == 1 )
			{
				return new TreeNode( inorder[off1] );
			}
			int rootIndex = find( inorder, off1, length, postorder[off2
					+ length - 1] );
			TreeNode root = new TreeNode( inorder[rootIndex] );
			int leftLength = rootIndex - off1;
			root.left = buildTree( inorder, off1, postorder, off2, leftLength );
			int rightLength = length - leftLength - 1;
			if ( rightLength > 0 )
			{
				root.right = buildTree( inorder, rootIndex + 1,
						postorder, off2 + leftLength, rightLength );
			}
			return root;
		}

		int find( int[] values, int off, int len, int val )
		{
			int end = off + len;
			for ( int i = off; i < end; i++ )
			{
				if ( values[i] == val )
				{
					return i;
				}
			}
			return -1;
		}
	}
}
