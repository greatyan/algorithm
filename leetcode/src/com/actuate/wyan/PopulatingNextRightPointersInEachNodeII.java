
package com.actuate.wyan;

import java.util.LinkedList;

public class PopulatingNextRightPointersInEachNodeII
{

	public class Solution
	{

		public void connect( TreeLinkNode root )
		{
			if ( root == null )
			{
				return;
			}

			LinkedList<TreeLinkNode> nodes = new LinkedList<TreeLinkNode>( );

			nodes.add( root );
			nodes.add( null );

			while ( true )
			{
				TreeLinkNode node = nodes.removeFirst( );
				if ( node != null )
				{
					if ( node.left != null )
					{
						nodes.add( node.left );
					}
					if ( node.right != null )
					{
						nodes.add( node.right );
					}
					TreeLinkNode next = nodes.getFirst( );
					if ( next != null )
					{
						node.next = next;
					}
				}
				else
				{
					if ( !nodes.isEmpty( ) )
					{
						nodes.add( null );
						continue;
					}
					else
					{
						return;
					}
				}
			}
		}
	}
}
