
package com.actuate.wyan;

public class BinaryTreeMaximumPathSum
{

	public static void main( String[] args )
	{
		new BinaryTreeMaximumPathSum( ).test( );
	}

	public void test( )
	{
		TreeNode root = new TreeNode( 2 );
		root.left = new TreeNode( 1 );
		root.right = new TreeNode( 3 );
		int length = new Solution( ).maxPathSum( root );
		System.out.println( length );
	}

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

		public int maxPathSum( TreeNode root )
		{
			if ( root == null )
			{
				return 0;
			}
			int[] values = getMaxValues( root );
			if ( values[0] < values[1] )
			{
				return values[1];
			}
			return values[0];
		}

		// return two integer, one is max value, the other is max edge
		public int[] getMaxValues( TreeNode root )
		{
			if ( root.left == null && root.right == null )
			{
				return new int[]{root.val, root.val};
			}

			int[] leftMaxes = null;
			int[] rightMaxes = null;
			if ( root.left != null )
			{
				leftMaxes = getMaxValues( root.left );
			}
			if ( root.right != null )
			{
				rightMaxes = getMaxValues( root.right );
			}

			if ( leftMaxes == null )
			{
				// use rightMaxes only

				int maxEdgeValue = Math
						.max( rightMaxes[1] + root.val, root.val );
				int maxValue = Math.max( rightMaxes[0], maxEdgeValue );
				return new int[]{maxValue, maxEdgeValue};
			}
			if ( rightMaxes == null )
			{
				int maxEdgeValue = Math.max( leftMaxes[1] + root.val, root.val );
				int maxValue = Math.max( leftMaxes[0], maxEdgeValue );
				return new int[]{maxValue, maxEdgeValue};
			}

			// use both leftMaxes and rightMaxes;
			int maxEdgeValue = Math.max( leftMaxes[1] + root.val, rightMaxes[1]
					+ root.val );
			maxEdgeValue = Math.max( maxEdgeValue, root.val );
			int maxValue = Math.max( leftMaxes[0], rightMaxes[0] );
			maxValue = Math.max( maxValue, leftMaxes[1] + root.val
					+ rightMaxes[1] );
			maxValue = Math.max( maxValue, maxEdgeValue );
			return new int[]{maxValue, maxEdgeValue};
		}
	}
}
