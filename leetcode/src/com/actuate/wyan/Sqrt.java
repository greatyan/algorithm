package com.actuate.wyan;

import org.junit.Test;

public class Sqrt {

	@Test
	public void test() {
		for (int i = 0; i <= 100; i++) {
			System.out.println(i + ":" + new Solution().sqrt(i));

		}

		System.out.println(new Solution().sqrt(2147395599));
	}

	public class Solution {
		public int sqrt(int x) {
			if (x == 0 || x == 1) {
				return x;
			}
			int min = 0;
			int max = x;
			int delta = Integer.MAX_VALUE;
			while (min < max) {
				int mid = min + (max - min) / 2;
				long v = (long)mid * mid;
				if (v == x) {
					return mid;
				} else if (v > x) {
					max = mid;
				} else if (v < x) {
					min = mid;
				}
				if (max - min == 1) {
					// must be one of it
					return min;
				}
			}
			return max;
		}

		public int sqrt_bit(int x) {
			// get the first two bits
			int mask = 0xC0000000;
			int bits = 30;
			int result = 0;
			int remain = (x & mask) >> bits;
			int y = 0;
			while (bits >= 0) {
				if (remain > 0 && remain >= y) {
					result = (result << 1) + 1;
					remain -= y;
				} else {
					result = (result << 1);
				}
				bits -= 2;
				mask >>>= 2;
				if (bits >= 0) {
					remain = (remain << 2) + ((x & mask) >> bits);
					y = (result << 2) + 1;
				}
			}
			return result;
		}
	}
}
