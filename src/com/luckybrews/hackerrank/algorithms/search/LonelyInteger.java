package com.luckybrews.hackerrank.algorithms.search;

import java.util.Arrays;
import java.util.Scanner;
/**
 * There are N integers in an array A. All but one integer occurs in pairs. 
 * Your task is to find out that number that occurs only once. 
 * 
 * @author fiskio@luckybrews.com
 * 
 * 15/12/2013
 *
 * https://www.hackerrank.com/challenges/lonely-integer
 */
public class LonelyInteger {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// read test
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int[] A = new int[N];
		for (int i=0; i<N; i++) {
			A[i] = s.nextInt();
		}
		// solve
		int lonely = solve2(A);
		System.out.println(lonely); 
		
	}
	
	private static int solve2(int[] arr) {
		int[] hits = new int[101];
		for (int i : arr) {
			hits[i]++;
		}
		for (int i=0; i<101; i++) {
			if (hits[i] == 1) {
				return i;
			}
		}
		return -1;
	}

	private static int solveSorting(int[] arr) {
		Arrays.sort(arr);
		for (int i=0; i<arr.length-1; i+=2) {
			if (arr[i] != arr[i+1]) { 
				return arr[i]; 
			}
		}
		return arr[arr.length-1]; 
	}

}
