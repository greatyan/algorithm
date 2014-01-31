package com.actuate.wyan.other;

import org.junit.Assert;
import org.junit.Test;

public class FindMissingNumber {

	@Test
	public void testAdd() {
		Assert.assertEquals("1011", add("999", 12));
	}

	@Test
	public void getNumber() {
		Assert.assertEquals(3, getNumbersToN0("78910", 1));
		Assert.assertEquals(2, getNumbersToN0("89", 1));
		Assert.assertEquals(-1, getNumbersToN0("78", 1));
		Assert.assertEquals(-1, getNumbersN0("123", 0, 1));
		Assert.assertEquals(10, getNumbersN0("012345678910", 0, 1));
	}

	@Test
	public void getEndNumberSize() {
		Assert.assertArrayEquals(new int[] { 2, 2 }, getEndNumberSize("911", 1));
		Assert.assertArrayEquals(new int[] { 2, 1 }, getEndNumberSize("810", 1));
		Assert.assertEquals(null, getEndNumberSize("8911", 2));
	}

	@Test
	public void test() {
		Assert.assertEquals("3", findMissingNumber("124567"));
		Assert.assertEquals("126", findMissingNumber("123124125127"));
		Assert.assertEquals("12346", findMissingNumber("1234512347"));
		Assert.assertEquals(
				"100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000",
				findMissingNumber("99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001"));
	}

	String findMissingNumber(String values) {
		int maxLength = values.length() / 2;
		for (int size = 1; size <= maxLength; size++) {
			int[] result = getEndNumberSize(values, size);
			if (result != null) {
				return findMissingNumber(values, size, result[0], result[1]);
			}
		}
		return null;
	}

	boolean matches(String v, int startSize, int endSize, int count) {
		String start = v.substring(0, startSize);
		String end = v.substring(v.length() - endSize, v.length());
		String result = add(start, count - 1);
		if (result.equals(end)) {
			return true;
		}
		return false;
	}

	/**
	 * calculate the size of last number and missing number.
	 * 
	 * 
	 * @param v
	 *            string
	 * @param startSize
	 *            size of start number
	 * @return int[2], int[0] is size of last number, int[1] is the size of
	 *         missing number
	 */
	int[] getEndNumberSize(String v, int startSize) {

		int numbers = getNumbersToN0(v, startSize);
		if (numbers == -1) {
			numbers = v.length() / startSize + 1;
			if (matches(v, startSize, startSize, numbers)) {
				int delta = numbers * startSize - v.length();
				return new int[] { startSize, delta };
			}
			return null;
		}
		int totalNumbers = numbers;
		int offset = numbers * startSize;
		int endSize = startSize + 1;
		numbers = getNumbersN0(v, offset, endSize);
		while (numbers != -1) {
			offset += numbers * endSize;
			totalNumbers += numbers;
			endSize = +1;
			numbers = getNumbersN0(v, offset, endSize);
		}
		numbers = (v.length() - offset) / endSize;
		// add a missing number
		numbers += 1;
		totalNumbers += numbers;
		// we should have following numbers
		offset += numbers * endSize;
		// delta is the missing number size
		int delta = offset - v.length();
		if (matches(v, startSize, endSize, totalNumbers)) {

			return new int[] { endSize, delta };
		}
		return null;
	}

	String findMissingNumber(String v, int startSize, int endSize, int delta) {
		if (delta == startSize) {
			if (delta == endSize) {
				// the whole string is define as same size
				return findMissingNumberWithSize(v, 0, v.length(), startSize);
			} else {
				// find it in the startSize
				int endOffset = getNumbersToN0(v, startSize);
				assert endOffset > 0;
				return findMissingNumberWithSize(v, 0, endOffset, startSize);
			}
		}

		int startOffset = 0;
		int endOffset = 0;
		for (int i = startSize + 1; i < delta; i++) {
			int numbers = getNumbersN0(v, startOffset, i);
			assert numbers > 0;
			startOffset += numbers * i;
		}

		if (delta == endSize) {
			endOffset = v.length();
		} else {
			int numbers = getNumbersN0(v, startOffset, delta);
			assert numbers > 0;
			endOffset = startOffset + numbers * delta;
		}
		return findMissingNumberWithSize(v, startOffset, endOffset, delta);
	}

	String findMissingNumberWithSize(String v, int startOffset, int endOffset,
			int size) {
		String baseValue = v.substring(startOffset, size);
		int start = 0;
		int end = (endOffset - startOffset) / size - 1;
		while (start < end) {
			int mid = (start + end) / 2;
			String value = add(baseValue, mid);
			if (value.equals(v.substring(startOffset + mid * size, size))) {
				start = mid;
			} else {
				end = mid;
			}
		}
		// start is the one missing
		return add(baseValue, start);
	}

	/*
	 * test if the string contains 2 can hold numbers with size digit from
	 * n2[offset].
	 * 
	 * return -1 if all the values are saved in n2. return offset which hold the
	 * values.
	 */
	int getNumbersToN0(String n2, int size) {

		if (size == 1) {
			int remain = 9 - (n2.charAt(0) - '0') + 1;
			if (n2.length() > remain) {
				return remain;
			}
			return -1;
		}

		int valueSize = 0;
		int overflow = 0;
		int scale = 1;
		for (int i = size - 1; i >= 0; i--) {
			int v = '0' - n2.charAt(i) - overflow;
			if (v < 0) {
				v += 10;
				overflow = 1;
			} else {
				overflow = 0;
			}
			valueSize = valueSize + v * scale * size;
			if (valueSize > n2.length()) {
				return -1;
			}
			scale *= 10;
		}
		return valueSize;
	}

	/**
	 * test if the string can hold number from 1000 to 9999.
	 * 
	 * return -1 if it can't hold the numbers. return size till next.
	 * 
	 * @param length
	 * @param numbers
	 * @return
	 */
	int getNumbersN0(String n2, int offset, int size) {
		if (size == 1) {
			int v = n2.charAt(offset) - '0';
			int remain = 10 - v;
			if (offset + remain >= n2.length()) {
				return -1;
			}
			return remain;
		}
		int valueSize = 0;
		int maxValue = 10;
		for (int i = 2; i <= size; i++) {
			valueSize = 9 * maxValue * i;
			if (valueSize > n2.length() - offset) {
				return -1;
			}
			maxValue *= 10;
		}
		return valueSize;
	}

	String add(String v, int value) {
		StringBuilder sb = new StringBuilder();

		int overflow = 0;
		for (int i = v.length() - 1; i >= 0; i--) {
			int remain = value % 10;
			value = value / 10;
			char result = (char) (v.charAt(i) + overflow + remain);
			if (result > '9') {
				result = (char) ('0' + result - '9' - 1);
				overflow = 1;
			} else {
				overflow = 0;
			}
			sb.append(result);
		}
		if (overflow > 0) {
			sb.append('1');
		}
		return sb.reverse().toString();
	}

}
