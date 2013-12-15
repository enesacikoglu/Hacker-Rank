package com.luckybrews.hackerrank.algorithms.sorting;

import java.util.Arrays;
import java.util.Scanner;

/**
 * You are given two integer arrays, A and B, each containing N integers. 
 * The size of the array is <= 1000. 
 * You are free to permute the order of the elements in the arrays.
 * Now for the real question - is there an arrangement of the arrays such that Ai+Bi>=K 
 * for all i where Ai denotes the ith element in the array A.
 * 
 * @author fiskio@luckybrews.com
 * 
 * 15/12/2013
 *
 * https://www.hackerrank.com/challenges/two-arrays
 */

public class TwoArrays {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();
		for (int t=0; t<T; t++) {
			// read test case
			int N = s.nextInt();
			int K = s.nextInt();
			int[] arrA = new int[N];
			int[] arrB = new int[N];
			for (int i=0; i<N; i++) {
				arrA[i] = s.nextInt();
			}
			for (int i=0; i<N; i++) {
				arrB[i] = s.nextInt();
			}
			// solve
			String str = solve(arrA, arrB, K) ?  "YES" : "NO";
			System.out.println(str);
 		}

	}

	private static boolean solve(int[] arrA, int[] arrB, int K) {
		int n = arrA.length - 1;
		Arrays.sort(arrA);
		Arrays.sort(arrB);
		for (int i=0; i<arrA.length; i++) {
			int j = n - i;
			int sum = arrA[i] + arrB[j];
			System.err.println(sum);
			if (sum < K) { 
				return false;
			}
		}
		return true;
	}

}
