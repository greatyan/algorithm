
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BinaryTreeLevelOrderTraversalII
{

	public static void main( String[] args )
	{
		new BinaryTreeLevelOrderTraversalII( ).test( );
	}

	protected void printResult( ArrayList<ArrayList<Integer>> result )
	{
		for ( ArrayList<Integer> values : result )
		{
			for ( Integer v : values )
			{
				System.out.print( v );
				System.out.print( "," );
			}
			System.out.println( );
		}

	}

	public void test( )
	{
		TreeNode root = TreeNode.toTree( "1,2,3" );
		ArrayList<ArrayList<Integer>> results = new Solution( )
				.levelOrderBottom( root );
		printResult( results );

	}

	public class Solution
	{

		public ArrayList<ArrayList<Integer>> levelOrderBottom( TreeNode root )
		{

			LinkedList<TreeNode> nodes = new LinkedList<TreeNode>( );

			ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>( );
			if ( root == null )
			{
				return results;
			}

			ArrayList<Integer> values = new ArrayList<Integer>( );

			nodes.add( root );
			nodes.add( null );

			while ( !nodes.isEmpty( ) )
			{
				TreeNode node = nodes.removeFirst( );
				if ( node == null )
				{
					results.add( values );
					if (! nodes.isEmpty( )) {
						nodes.add( null );
					}
					values = new ArrayList<Integer>( );
				}
				else
				{
					values.add( node.val );
					if ( node.left != null )
					{
						nodes.add( node.left );
					}
					if ( node.right != null )
					{
						nodes.add( node.right );
					}
				}
			}

			Collections.reverse( results );
			return results;
		}

	}
}
