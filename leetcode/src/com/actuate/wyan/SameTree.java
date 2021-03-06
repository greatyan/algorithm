
package com.actuate.wyan;

public class SameTree
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

		public boolean isSameTree( TreeNode p, TreeNode q )
		{
			if ( p == q )
			{
				return true;
			}

			if ( p == null || q == null )
			{
				return false;
			}

			if ( p.val == q.val )
			{
				return isSameTree( p.left, q.left )
						&& isSameTree( p.right, q.right );
			}
			return false;
		}
	}

}
