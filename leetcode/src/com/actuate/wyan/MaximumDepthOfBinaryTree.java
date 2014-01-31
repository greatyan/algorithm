
package com.actuate.wyan;

public class MaximumDepthOfBinaryTree
{

	public class TreeNode
	{

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode( int x )
		{
			val = x;
		}
	}

	public class Solution
	{

		public int maxDepth( TreeNode root )
		{
			if ( root == null )
			{
				return 0;
			}
			int depth1 = maxDepth( root.left );
			int depth2 = maxDepth( root.right );
			if ( depth1 > depth2 )
			{
				return depth1 + 1;
			}
			return depth2 + 1;
		}
	}

}
