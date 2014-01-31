
package com.actuate.wyan;

public class RecoverBinarySearchTree
{

	public static void main( String[] args )
	{
		new RecoverBinarySearchTree( ).test( );
	}

	public void test( )
	{
		TreeNode root = TreeNode.toTree( "1,2,3" );
		new Solution( ).recoverTree( root );
		System.out.println( TreeNode.toString( root ) );

		root = TreeNode.toTree( "2,3,1" );
		new Solution( ).recoverTree( root );
		System.out.println( TreeNode.toString( root ) );
	}

	public class Solution
	{

		public void recoverTree( TreeNode root )
		{
			if ( root == null )
			{
				return;
			}
			TreeNode node = visitTree( root, null );
			if ( node != null && wrongNode != null )
			{
				int v = node.val;
				node.val = wrongNode.val;
				wrongNode.val = v;
			}
		}

		TreeNode wrongNode;

		protected TreeNode visitTree( TreeNode node, TreeNode prevNode )
		{
			if ( node.left != null )
			{
				prevNode = visitTree( node.left, prevNode );
				if ( prevNode == null )
				{
					return null;
				}
			}
			if ( wrongNode == null )
			{
				if ( prevNode != null && node.val < prevNode.val )
				{
					// either node is wrong or prevNode is wrong
					wrongNode = prevNode;
				}
			}
			else
			{
				// last change
				if ( wrongNode.val < node.val && wrongNode.val > prevNode.val )
				{
					int v = prevNode.val;
					prevNode.val = wrongNode.val;
					wrongNode.val = v;
					wrongNode = null;
					return null;
				}
			}

			prevNode = node;
			if ( node.right != null )
			{
				prevNode = visitTree( node.right, node );
				if ( prevNode == null )
				{
					return null;
				}
			}
			return prevNode;
		}
	}
}
