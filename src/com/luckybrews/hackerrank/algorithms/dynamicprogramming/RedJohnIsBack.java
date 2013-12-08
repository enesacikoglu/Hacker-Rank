package com.luckybrews.hackerrank.algorithms.dynamicprogramming;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.Test;

/**
 * Red John has committed another murder. But this time, he doesn’t leave a red smiley behind. 
 * What he leaves behind is a puzzle for Patrick Jane to solve. He also texts Teresa Lisbon 
 * that if Patrick is successful, he will turn himself in. The puzzle begins as follows.
 * There is a wall of size CxN in the victim’s house where C is the 1st composite number. 
 * The victim also has an infinite supply of bricks of size Cx1 and 1xC in her house. 
 * There is a hidden safe which can only be opened by a particular configuration of bricks on the wall. 
 * In every configuration, the wall has to be completely covered using the bricks. 
 * There is a phone number written on a note in the safe which is of utmost importance in the murder case. 
 * Gale Bertram wants to know the total number of ways in which the bricks can be arranged on the wall 
 * so that a new configuration arises every time. He calls it M. Since Red John is back after a long time, 
 * he has also gained a masters degree in Mathematics from a reputed university. 
 * So, he wants Patrick to calculate the number of prime numbers (say P) up to M (i.e. <= M). 
 * If Patrick calculates P, Teresa should call Red John on the phone number from the safe 
 * and he will surrender if Patrick tells him the correct answer. 
 * Otherwise, Teresa will get another murder call after a week. 
 * 
 * @author fiskio@luckybrews.com
 *
 */

public class RedJohnIsBack {
	
	// SOLUTION
	static int arrangeBlocks(int c, int rem) {
		if (rem < 0)  { return 0; }
		if (rem == 0) { return 1; }
		return putHorizontal(c, rem) + putVertical(c, rem);
	}
	
	private static int putVertical(int c, int rem) {
		return arrangeBlocks(c, rem - 1);
	}

	private static int putHorizontal(int c, int rem) {
		return arrangeBlocks(c, rem - c);
	}
	
	private static boolean isPrime(int n) {
		if (n < 2) { return false; }
		if (n == 2){ return true;}
		if (n%2 == 0) { return false; }
		final int sqrt = (int)Math.round(Math.sqrt(n));
		for (int i=3; i<=sqrt; i+=2) {
			if (n%i == 0) {
				return false;
			}
		}
		return true;
	}
	// false = prime
	// true  = composite
	private static boolean[] primeSieve(int max) {
		boolean ret[] = new boolean[max+1];
		ret[0] = true;
		ret[1] = true;
		int sqrt = (int) Math.round(Math.sqrt(max+1));
		for (int i=2; i<=sqrt; i++) {
			if (!ret[i]) {
				for (int mul = 2*i; mul < max+1; mul+=i) {
					ret[mul] = true;
				}
			}
		}
		return ret;
	}
	
	public int countElements(boolean[] sieved) {
		int count = 0;
		for (int i=0; i<sieved.length; i++) {
			// System.out.println(i + " " + sieved[i]);
			if (!sieved[i]) { 
				count++;
			}
		}
		return count;
	}
	
	@Test
	public void test_RedJackIsBack() {
		int C = 4;
		int N = 1;
		int arrangements = arrangeBlocks(C, N);
		int sievedPrimes = countElements(primeSieve(arrangements));
		assertEquals(0, sievedPrimes);
		
		C = 4;
		N = 7;
		arrangements = arrangeBlocks(C, N);
		sievedPrimes = countElements(primeSieve(arrangements));
		assertEquals(3, sievedPrimes);
		
		C = 4;
		N = 7;
		arrangements = arrangeBlocks(C, N);
		sievedPrimes = countElements(primeSieve(arrangements));
		assertEquals(3, sievedPrimes);
	}
	
	@Test
	public void test_primeSieve() {
		int max = 10;
		int primes = countElements(primeSieve(max));
		assertEquals(4, primes);
		
		max = 7919;
		primes = countElements(primeSieve(max));
		assertEquals(1000, primes);
	}
	
	@Test
	public void test_SieveMaxInteger() {
		int MAX = Integer.MAX_VALUE-1;
		//primeSieve(MAX);
	}

	@Test
	public void test_ArrangeBlocks() {
	
		final int C = 4;
		assertEquals(1, arrangeBlocks(C, 1));
		assertEquals(1, arrangeBlocks(C, 2));
		assertEquals(1, arrangeBlocks(C, 3));
		assertEquals(2, arrangeBlocks(C, 4));
		assertEquals(3, arrangeBlocks(C, 5));
		assertEquals(4, arrangeBlocks(C, 6));
		assertEquals(5, arrangeBlocks(C, 7));
	}

	@Test
	public void test_isPrime() {
		// primes
		assertTrue(isPrime(2));
		assertTrue(isPrime(5));
		assertTrue(isPrime(17));
		assertTrue(isPrime(89));
		assertTrue(isPrime(7919));
		assertTrue(isPrime(1999));
		
		// composite
		assertFalse(isPrime(-1));
		assertFalse(isPrime(0));
		assertFalse(isPrime(1));
		assertFalse(isPrime(4));
		assertFalse(isPrime(49));
		assertFalse(isPrime(121));
	}
	
	@Test
	public void test_First1000Primes() throws FileNotFoundException {
		// read real first 1000 primes
		Scanner scanner = new Scanner(new File("assets/first_1000_primes.txt"));
		int[] first1000 = new int[1000];
		int count = 0;;
		while (scanner.hasNextInt()) {
			int next = scanner.nextInt();
			//System.out.println(next);
			first1000[count++] = next; 
		}
		assertEquals(1000, count);
		
		// calculate first 1000 primes
		boolean[] calc1000 = primeSieve(7919);
		int[] intCalc100 = new int[1000];
		int idx = 0;
		for (int i=0; i<calc1000.length; i++) {
			if (!calc1000[i]) {
				intCalc100[idx++] = i;
				//System.out.println(i);
			}
		}
		assertArrayEquals(first1000, intCalc100);
		scanner.close();
	}
}
