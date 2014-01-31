
package com.actuate.wyan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CloneGraph
{

	public static void main( String[] args )
	{
		new CloneGraph( ).test( );
	}

	public void test( )
	{
		UndirectedGraphNode root = new UndirectedGraphNode( 0 );
		root.neighbors.add( root );
		root.neighbors.add( root );

		new Solution( ).cloneGraph( root );
	}

	class UndirectedGraphNode
	{

		int label;
		ArrayList<UndirectedGraphNode> neighbors;

		UndirectedGraphNode( int x )
		{
			label = x;
			neighbors = new ArrayList<UndirectedGraphNode>( );
		}
	};

	public class Solution
	{

		public UndirectedGraphNode cloneGraph( UndirectedGraphNode node )
		{
			if ( node == null )
			{
				return null;
			}
			HashMap<Integer, UndirectedGraphNode> cloneNodes = new HashMap<Integer, UndirectedGraphNode>( );
			HashSet<Integer> visitedNodes = new HashSet<Integer>( );
			visitGraph( node, cloneNodes, visitedNodes );
			return cloneNodes.get( node.label );
		}

		protected void visitGraph( UndirectedGraphNode node,
				HashMap<Integer, UndirectedGraphNode> cloneNodes,
				HashSet<Integer> visitedNodes )
		{
			visitedNodes.add( node.label );
			cloneNode( node, cloneNodes );
			for ( UndirectedGraphNode neighbor : node.neighbors )
			{
				if ( !visitedNodes.contains( neighbor.label ) )
				{
					visitGraph( neighbor, cloneNodes, visitedNodes );
				}
			}
		}

		protected UndirectedGraphNode cloneNode( UndirectedGraphNode node,
				HashMap<Integer, UndirectedGraphNode> cloneNodes )
		{
			UndirectedGraphNode cloneNode = cloneNodeWithoutNeighbors( node, cloneNodes);
			// duplicate the links
			for ( UndirectedGraphNode neighbor : node.neighbors )
			{
				UndirectedGraphNode cloneNeighbor = cloneNodeWithoutNeighbors( neighbor,
						cloneNodes );
				cloneNode.neighbors.add( cloneNeighbor );
			}
			return cloneNode;
		}
		
		protected UndirectedGraphNode cloneNodeWithoutNeighbors( UndirectedGraphNode node,
				HashMap<Integer, UndirectedGraphNode> cloneNodes )
		{
			UndirectedGraphNode cloneNode = cloneNodes.get( node.label );
			if ( cloneNode == null )
			{
				cloneNode = new UndirectedGraphNode( node.label );
				cloneNodes.put( node.label, cloneNode );
			}
			return cloneNode;
		}

	}
}
