package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class ReverseWordsInAString {

	@Test
	public void test() {
		Assert.assertEquals("", new Solution().reverseWords("       "));
		Assert.assertEquals("blue is sky the", new Solution().reverseWords(" the sky   is blue      "));
		Assert.assertEquals("a", new Solution().reverseWords("a"));
	}

	public class Solution {
		public String reverseWords(String s) {

			if (s == null || s.length() == 0) {
				return "";
			}
			StringBuilder sb = new StringBuilder();
			int index = s.length() - 1;
			while (index >= 0) {
				// skip white space
				int space = skipWhiteSpace(s, index);
				int length = skipChars(s, index - space);
				if (length == 0) {
					break;
				}
				// add the string to the string buffer
				int start = index - space - length + 1;
				sb.append(s.substring(start, start + length));
				sb.append(' ');
				index -= space;
				index -= length;
			}
			// remove last white space
			if (sb.length() > 0) {
				sb.setLength(sb.length() - 1);
			}
			return sb.toString();
		}

		int skipWhiteSpace(String s, int offset) {
			int index = offset;
			while (index >= 0) {
				if (s.charAt(index) == ' ') {
					index--;
					continue;
				}
				break;
			}
			return offset - index;
		}

		int skipChars(String s, int offset) {
			int index = offset;
			while (index >= 0) {
				if (s.charAt(index) != ' ') {
					index--;
					continue;
				}
				break;
			}
			return offset - index;
		}
	}
}
