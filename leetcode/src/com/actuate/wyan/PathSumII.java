
package com.actuate.wyan;

import java.util.ArrayList;

import org.junit.Test;

public class PathSumII
{

	@Test
	public void test( )
	{
		new Solution( ).pathSum(
				TreeNode.toTree( "5,4,8,11,#,13,4,7,2,#,#,5,1" ), 22 );
	}

	public class Solution
	{

		public ArrayList<ArrayList<Integer>> pathSum( TreeNode root, int sum )
		{
			if ( root == null )
			{
				return null;
			}
			ArrayList<ArrayList<Integer>> pathes = new ArrayList<ArrayList<Integer>>( );
			ArrayList<Integer> path = new ArrayList<Integer>( );
			visit( root, sum, path, pathes );
			return pathes;

		}

		protected void visit( TreeNode node, int remain,
				ArrayList<Integer> path, ArrayList<ArrayList<Integer>> pathes )
		{
			if ( node.left == null && node.right == null )
			{
				if ( remain == node.val )
				{
					ArrayList<Integer> result = new ArrayList<Integer>(
							pathes.size( ) + 1 );
					result.addAll( path );
					result.add( node.val );
					pathes.add( result );
				}
				return;
			}

			path.add( node.val );
			if ( node.left != null )
			{
				visit( node.left, remain - node.val, path, pathes );
			}
			if ( node.right != null )
			{
				visit( node.right, remain - node.val, path, pathes );
			}
			path.remove( path.size( ) - 1 );
		}
	}
}
