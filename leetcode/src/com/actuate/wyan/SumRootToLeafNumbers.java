
package com.actuate.wyan;

public class SumRootToLeafNumbers
{
	
	public class Solution
	{

		public int sumNumbers( TreeNode root )
		{
			if ( root == null )
			{
				return 0;
			}
			total = 0;
			visit( root, 0 );
			return total;
		}

		int total;

		void visit( TreeNode root, int value )
		{
			if ( root.left == null && root.right == null )
			{
				total += value * 10 + root.val;
				return;
			}
			if ( root.left != null )
			{
				visit( root.left, value * 10 + root.val );
			}
			if ( root.right != null )
			{
				visit( root.right, value * 10 + root.val );
			}
		}
	}
}
