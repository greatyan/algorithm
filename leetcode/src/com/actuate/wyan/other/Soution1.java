
package com.actuate.wyan.other;



	int[] find( int[][] matrix )
	{
		ArrayList<int[][]> pathes = new ArrayList<int[][]>();
		
		for ( int x = 0; x < width; x ++) {
			for ( int y = 0; y < height; y++)
			{
				if ( matrix[y][x] == 1) {
					int[] path = createMatrix(matrix, x, y, pathes);
					pathes.add( path);
				}
			}
		}
		
		int 
		for ( int x = 0; x < width; x++) {
			for ( int y = 0; y < hegiht; y++) {
				if ( matrix[y][x] == 0)
				{
					boolean linked = true;
					int length = 0;
					for ( int[][] path : pathes) 
					{
						if ( path[x][y] == 0) 
						{
							matched = false;
							break;
						}
						length += path[x][y]; 
					}
					it
				}
			}
		}
			
	}
	
	int[][] createMatrix(int width, int height)
	{
		int[][] matrix = new int[height][];
		for ( int i = 0; i < height; i++) {
			matrix[i] = new int[width];
		}
		return matrix;
	}


	void visit( int[] matrix, int x, int y, int[][] pathes) 
	{
		boolean visitTop = false;
		boolean visitBelow = false;
		boolean visitLeft = false;
		boolean visitRigth = false;
		if ( x-1 >= 0)
		{
			if ( matrix[y][x-1] == 0) {
				if ( pathes[y][x-1] == 0) 
				{
					visitLeft = true;
					pathes[y][x-1] = pathes[y][x] + 1;
				}
			}
		}
		if ( x +1 < colCount)
		{
			if ( matrix[y][x+1] == 0) {
				if ( pathes[y][x+1] == 0) 
				{
					visitRight = true;
					pathes[y][x+1] = pathes[y][x] + 1;
				}
			}
		}
		if ( y-1 >= 0)
		{
			if ( matrix[y-1][x] == 0) {
				if ( pathes[y-1][x] == 0) 
				{
					visitTop = true;
					pathes[y-1][x] = pathes[y][x] + 1;
				}
			}
		}
		if ( y +1 < colCount)
		{
			if ( matrix[y+1][x] == 0) {
				if ( pathes[y+1][x] == 0) 
				{
					visitBottom = true;
					pathes[y+1][x] = pathes[y][x] + 1;
				}
			}
		}
		
		if ( visitLeft)
		{
			visit(matrix, x-1, y, pathes);
		}
		if ( visitRight)
		{
			visit(matrix, x+1, y, pathes);
		}
		if ( visitTop)
		{
			visit(matrix, x, y - 1, pathes);
		}
		if ( visitBottom)
		{
			visit(matrix, x, y + 1, pathes);
		}
	}
}
