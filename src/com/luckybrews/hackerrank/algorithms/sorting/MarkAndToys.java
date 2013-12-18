package com.luckybrews.hackerrank.algorithms.sorting;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Mark and Jane are very happy after having their first kid. Their son is very fond of toys. 
 * Therefore, Mark wants to buy some toys for his son. But he has a limited amount of money. 
 * But he wants to buy as many unique toys as he can. So, he is in a dilemma and is wondering 
 * how he can maximize the number of toys he can buy. He has N items in front of him, 
 * tagged with their prices and he has only K rupees. 
 * 
 * Now, you being Mark’s best friend have the task to help him buy as many toys for his son as possible.
 * 
 * @author fiskio@luckybrews.com
 * 
 * 14/12/2013
 * 
 * https://www.hackerrank.com/challenges/mark-and-toys 
 */
public class MarkAndToys {

	public static void main(String[] args) {
		
		// read input
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int K = s.nextInt(); // money
		int[] prices = new int[N];
		for (int i=0; i<N; i++) {
			prices[i] = s.nextInt();
		}
		// choose items
		Arrays.sort(prices);
		int items = 0;
		for (int i=0; i<N; i++) {
			K -= prices[i];
			if (K < 0) {
				break;
			}
			items++;
		}
		System.out.println(items);
	}

}
