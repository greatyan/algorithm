
package com.actuate.wyan;

public class EvaluateReversePolishNotation
{

	public class Solution
	{

		public int evalRPN( String[] tokens )
		{
			int[] values = new int[tokens.length];
			int index = 0;
			for ( String tok : tokens )
			{
				if ( "+".equals( tok ) )
				{
					values[index - 2] = values[index - 1] + values[index - 2];
					index--;
				}
				else if ( "-".equals( tok ) )
				{
					values[index - 2] = values[index - 2] - values[index - 1];
					index--;

				}
				else if ( "*".equals( tok ) )
				{
					values[index - 2] = values[index - 2] * values[index - 1];
					index--;
				}
				else if ( "/".equals( tok ) )
				{
					values[index - 2] = values[index - 2] / values[index - 1];
					index--;
				}
				else
				{
					values[index] = Integer.valueOf( tok );
					index++;
				}
			}
			return values[index-1];
		}
	}
}