package com.luckybrews.hackerrank.algorithms.discretemath;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Scanner;

import org.junit.Test;

/**
 * @author fiskio@luckybrews.com
 *
 * 11/12/2013
 * 
 * On the eve of Diwali, Hari is decorating his house with a serial light bulb set. The serial light bulb set has N bulbs placed sequentially on a string which is programmed to change patterns every second. If atleast one bulb in the set is on at any given instant of time, how many different patterns of light can the serial light bulb set produce?
 * Note: Lighting two bulbs *-* is different from **-
 * 
 * https://www.hackerrank.com/challenges/diwali-lights
 * 
 * http://en.wikipedia.org/wiki/Modular_exponentiation
 */

public class DiwaliLights {

	final static int MODULO = 100_000;
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		final int T = s.nextInt();
		for (int i=0; i<T; i++) {
			int bulbs = s.nextInt();
			System.out.println(countPatterns(bulbs) % MODULO);
		}

	}

	private static int naiveCountPatterns(int bulbs) {
		return (1 << bulbs) -1;
	}
	
	private static int countPatterns(int bulbs) {
		return iterativeModularPow(2, bulbs, MODULO) - 1;
	}
	
	private static int iterativeModularPow(int base, int exp, int modulo) {
		int ret = 1;
		for (int i=0; i<exp; i++) {
			ret = (ret * base) % MODULO;
		}
		return ret;
	}
	
	private static int bigInt_ModularPow(long base, int exp, int modulo) {
		BigInteger ret = new BigInteger(""+base);
		return (int) Math.pow(2, base) % MODULO;
	}
	
	@Test
	public void test() {
		assertEquals(1, countPatterns(1));
		assertEquals(3, countPatterns(2));
		assertEquals(7, countPatterns(3));
	}
	
	@Test
	public void testTime() {
		
		final int ITER = 10_000_000;
		
		long now = System.currentTimeMillis();
		for (int i=0; i<ITER; i++) {
			bigInt_ModularPow(2, i, MODULO);
		}
		long time1 = System.currentTimeMillis() - now;
		System.out.println(time1);
		
		now = System.currentTimeMillis();
		for (int i=0; i<ITER; i++) {
			iterativeModularPow(2, i, MODULO);
		}
		long time2 = System.currentTimeMillis() - now;
		System.out.println(time2);
	}
}
