package com.luckybrews.hackerrank.algorithms.search;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

/**
 * Given N integers [N<=10^5], count the total pairs of integers that have a difference of K [K>0 and K< 1e9]. 
 * Each of the N integers will be greater than 0 and at least K away from 2^31-1 
 * 
 * @author fiskio@luckybrews.com
 * 
 * https://www.hackerrank.com/challenges/pairs
 */

public class Pairs {
	
	// SOLUTION
	static int pairs(int[] a,int k) {
		Arrays.sort(a);
		int count = 0;
		for (int i=0; i<a.length-1; i++) {
			for (int j=i+1; j<a.length; j++) {
				final int diff = a[j]-a[i];
				if (diff < k) { continue; }
				if (diff > k) { break; }
				count++;
			}
		}
		return count;
	}

	@Test
	public void test_01() {
		
		final int K = 2;
		final int[] ARR = new int[] {1, 5, 3, 4, 2};
		
		final int EXPECTED = 3;
		final int RESULT = pairs(ARR, K);
		
		assertEquals(EXPECTED, RESULT);
	}
	
	@Test
	public void test_02() {
		
		final int K = 1;
		final int[] ARR = new int[] {363374326, 364147530, 61825163, 1073065718, 1281246024, 1399469912, 428047635, 491595254, 879792181, 1069262793};

		final int EXPECTED = 0;
		final int RESULT = pairs(ARR, K);
		
		assertEquals(EXPECTED, RESULT);
	}

}
