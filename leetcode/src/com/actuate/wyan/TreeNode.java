
package com.actuate.wyan;

import java.util.LinkedList;

public class TreeNode
{

	int val;
	TreeNode left;
	TreeNode right;

	TreeNode( int x )
	{
		val = x;
	}

	public static TreeNode toTree( String str )
	{
		String[] tokens = str.split( "," );
		if ( tokens.length == 0 )
		{
			return null;
		}
		if ( tokens[0].equals( "#" ) )
		{
			return null;
		}
		TreeNode root = new TreeNode( Integer.parseInt( tokens[0] ) );
		if ( tokens.length <= 1 )
		{
			return root;
		}
		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>( );
		nodes.add( root );

		int index = 1;
		do
		{
			TreeNode node = nodes.removeFirst( );
			if ( "#".equals( tokens[index] ) )
			{
				node.left = null;
			}
			else
			{
				node.left = new TreeNode( Integer.parseInt( tokens[index] ) );
				nodes.add( node.left );
			}
			index++;
			if ( index >= tokens.length )
			{
				break;
			}
			if ( "#".equals( tokens[index] ) )
			{
				node.right = null;
			}
			else
			{
				node.right = new TreeNode( Integer.parseInt( tokens[index] ) );
				nodes.add( node.right );
			}
			index++;
			if ( index >= tokens.length )
			{
				break;
			}
		} while ( !nodes.isEmpty( ) );
		return root;

	}

	static public String toString( TreeNode root )
	{
		if ( root == null )
		{
			return "#";
		}
		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>( );
		nodes.add( root );
		StringBuffer sb = new StringBuffer( );
		do
		{
			TreeNode node = nodes.removeFirst( );
			if ( node != null )
			{
				sb.append( node.val );
				nodes.add( node.left );
				nodes.add( node.right );
			}
			else
			{
				sb.append( "#" );
			}
			sb.append( "," );
		} while ( !nodes.isEmpty( ) );
		sb.setLength( sb.length( ) - 1 );
		return sb.toString( );
	}

	public static void main( String[] args )
	{
		TreeNode tree = toTree( "1,#,2,3" );
		System.out.println( toString( tree ) );
	}
}
