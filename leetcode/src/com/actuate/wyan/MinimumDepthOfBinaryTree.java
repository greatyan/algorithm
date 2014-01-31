
package com.actuate.wyan;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class MinimumDepthOfBinaryTree
{

	@Test
	public void test( )
	{
		Assert.assertEquals( 2,
				new Solution( ).minDepth( TreeNode.toTree( "1,2" ) ) );
	}

	public class Solution
	{

		public int minDepth( TreeNode root )
		{
			if ( root == null )
			{
				return 0;
			}

			LinkedList<TreeNode> nodes = new LinkedList<TreeNode>( );
			int depth = 1;
			nodes.add( root );
			nodes.add( null );
			while ( !nodes.isEmpty( ) )
			{
				TreeNode node = nodes.removeFirst( );
				if ( node != null )
				{
					if ( node.left == null && node.right == null )
					{
						return depth;
					}
					if ( node.left != null )
					{
						nodes.add( node.left );
					}
					if ( node.right != null )
					{
						nodes.add( node.right );
					}
				}
				else
				{
					depth++;
					if ( nodes.isEmpty( ) )
					{
						return depth;
					}
					nodes.add( null );
				}
			}
			return depth;
		}
	}
}
