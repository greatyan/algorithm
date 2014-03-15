package com.actuate.wyan;

import org.junit.Assert;
import org.junit.Test;

public class SearchForARange {

	@Test
	public void testBSearch() {
		int[] values = new int[1024];
		for (int i = 0; i < values.length; i++) {
			values[i] = i;
		}
		for (int i = 0; i < values.length; i++) {
			if (i != new Solution().bsearch(values, i)) {
				Assert.assertFalse("failed at " + i, false);
			}
		}

		Assert.assertEquals(-1, new Solution().bsearch(values, -1));
		Assert.assertEquals(-1, new Solution().bsearch(values, 1024));

	}

	@Test
	public void testBSearch_L() {
		int[] values = new int[1024];
		for (int i = 0; i < values.length; i++) {
			values[i] = i;
		}
		for (int i = 0; i < values.length; i++) {
			if (i - 1 != new Solution().bsearch_l(values, i)) {
				Assert.assertFalse("failed at " + i, false);
			}
		}

		Assert.assertEquals(-1, new Solution().bsearch_l(values, -1));
		Assert.assertEquals(1023, new Solution().bsearch_l(values, 1024));

	}

	@Test
	public void testBSearch_R() {
		int[] values = new int[1024];
		for (int i = 0; i < values.length; i++) {
			values[i] = i;
		}
		for (int i = 0; i < values.length; i++) {
			if (i + 1 != new Solution().bsearch_r(values, i)) {
				Assert.assertFalse("failed at " + i, false);
			}
		}

		Assert.assertEquals(0, new Solution().bsearch_r(values, -1));
		Assert.assertEquals(1024, new Solution().bsearch_r(values, 1024));

	}

	@Test
	public void testRange() {
		Assert.assertArrayEquals(new int[] { 3, 4 },
				new Solution().searchRange(new int[] { 5, 7, 7, 8, 8, 10 }, 8));
	}

	public class Solution {

		public int[] searchRange(int[] A, int target) {
			int left = bsearch_l(A, target);
			int right = bsearch_r(A, target);
			if (right - left <= 1) {
				return new int[] { -1, -1 };
			}
			return new int[] { left + 1, right - 1 };
		}

		int bsearch(int[] values, int target) {
			int min = 0;
			int max = values.length - 1;
			int mid = min + (max - min) / 2;
			while (true) {
				if (values[mid] < target) {
					min = mid + 1;
					if (min > max) {
						return -1;
					}
				} else if (values[mid] > target) {
					max = mid - 1;
					if (min > max) {
						return -1;
					}
				} else if (values[mid] == target) {
					return mid;
				}
				mid = min + (max - min) / 2;
			}
		}

		int bsearch_l(int[] values, int target) {
			int min = 0;
			int max = values.length - 1;
			int mid = min + (max - min) / 2;
			while (true) {
				if (values[mid] < target) {
					min = mid + 1;
					if (min > max) {
						return mid;
					}
				} else if (values[mid] >= target) {
					max = mid - 1;
					if (min > max) {
						return max;
					}
				}
				mid = min + (max - min) / 2;
			}
		}

		int bsearch_r(int[] values, int target) {
			int min = 0;
			int max = values.length - 1;
			int mid = min + (max - min) / 2;
			while (true) {
				if (values[mid] <= target) {
					min = mid + 1;
					if (min > max) {
						return min;
					}
				} else if (values[mid] > target) {
					max = mid - 1;
					if (min > max) {
						return mid;
					}
				}
				mid = min + (max - min) / 2;
			}
		}

	}
}
