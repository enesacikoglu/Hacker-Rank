package com.luckybrews.hackerrank.algorithms.graphtheory;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;
/**
 * The country of Byteland contains N cities and N - 1 bidirectional roads between them 
 * such that there is a path between any two cities. The cities are numbered (0,…,N - 1). 
 * The people were very unhappy about the time it took to commute, especially salesmen 
 * who had to go about every city selling goods. So it was decided that new roads would 
 * be built between any two “somewhat near” cities. Any two cities in Bytleland that can 
 * be reached by traveling on exactly two old roads are known as “somewhat near” each other.
 * 
 * Now a salesman situated in city 0, just like any other typical salesman, has to visit 
 * all cities exactly once and return back to city 0 in the end. In how many ways can he 
 * do this?
 * 
 * @author fiskio@luckybrews.com
 * 
 * 14/12/2013
 * 
 * https://www.hackerrank.com/challenges/bytelandian-tours
 * 
 * NOTE: https://gist.github.com/yuvipanda/1285165
 */

public class ByteLandianTours {
	
	final static int DISTANCE = 2;
	final static int ORIGIN = 0;
	final static int MODULO = 1000000007;
	
	Map<Integer, Set<Integer>> oldRoads = new HashMap<Integer, Set<Integer>>();
	Map<Integer, Set<Integer>> newRoads = new HashMap<Integer, Set<Integer>>();
	int distanceArr[];

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();
		for (int i=0; i<T; i++) {
			int N = s.nextInt();
			ByteLandianTours foo = new ByteLandianTours();
			for (int j=0; j<N-1; j++) {
				int from = s.nextInt();
				int to = s.nextInt();
				foo.addOldRoad(from, to);
			}
			Map<Integer, Set<Integer>> roads = foo.connectNearCities();
			System.out.println(foo.countPath(roads));
		}
	} 
	
	public int countPath(Map<Integer, Set<Integer>> roads) {
		// System.out.println(this);
		
		calculateMinDistance(); 
		
		return countPath(ORIGIN, ORIGIN, new LinkedHashSet<Integer>(), roads);
	}
	
	private void calculateMinDistance() {
		calculateMinDistance(oldRoads, 0);
	}
	
	private void calculateMinDistance(Map<Integer, Set<Integer>> roads, int origin) {
		Set<Integer> visited = new HashSet<>();
		List<Integer> distances = new LinkedList<Integer>();
		distanceArr = new int[roads.keySet().size()];
		// add origin
		distances.add(origin);
		distanceArr[origin] = 0;
		visited.add(origin);
		// explore
		while (!distances.isEmpty()) {
			Integer city = distances.remove(0);
			System.err.println("** City: " + city);
			
			Set<Integer> neighbours = roads.get(city);
			for (Integer i : neighbours) {
				if (!visited.contains(i)) {
					distances.add(i);
					distanceArr[i] = distanceArr[city]+1;
					visited.add(i);
					System.err.println("Distance " + origin + " -> " + i + " = " + distanceArr[i]);
				}
			}
		}
	}
	
	public int countPath(Integer origin, Integer current, Set<Integer> visited, 
			Map<Integer, Set<Integer>> roads) {

		// visit current
		visited.add(current);
		
		// no road back
		int missingCities = roads.keySet().size() - visited.size();
		if (missingCities < distanceArr[current] - 1) {
			return 0;
		}
		
		
		//System.out.println(origin + " " + current + " " + visited); 
		
	
		// all visited
		if (roads.keySet().size() == visited.size()) {
			if (roads.get(current).contains(ORIGIN)) {
				return 1;
			} else {
				return 0;
			}
		}
		// keep visiting
		int ret = 0;
		Set<Integer> neighbours = roads.get(current);
		for (Integer city : neighbours) {
			if (!visited.contains(city)) {
				
				ret += countPath(origin, city, copySet(visited), roads);
			}
		}
		return ret % MODULO;
	}
	
	public void addOldRoad(Integer from, Integer to) {
		// add origin if absent
		if (!oldRoads.containsKey(from)) {
			oldRoads.put(from, new HashSet<Integer>());
		}
		// new road
		oldRoads.get(from).add(to);
		// add origin if absent
		if (!oldRoads.containsKey(to)) {
			oldRoads.put(to, new HashSet<Integer>());
		}
		// new road
		oldRoads.get(to).add(from);
	}
	
	public void addNewRoad(Integer from, Integer to) {
		// add origin if absent
		if (!newRoads.containsKey(from)) {
			newRoads.put(from, new HashSet<Integer>());
		}
		// new road
		newRoads.get(from).add(to);
		// add origin if absent
		if (!newRoads.containsKey(to)) {
			newRoads.put(to, new HashSet<Integer>());
		}
		// new road
		newRoads.get(to).add(from);
	}
	
	private void mergeRoads() {
		for (Integer from : newRoads.keySet()) {
			Set<Integer> oldNeighbours = oldRoads.get(from);
			Set<Integer> newNeighbours = newRoads.get(from);
			for (Integer i : newNeighbours) {
				oldNeighbours.add(i);
			}
		}
	}
	
	private void walk(Integer origin, Set<Integer> visited, Integer current, int remSteps) {
		// found? connect!
		if (remSteps == 0) {
			addNewRoad(origin, current);
			return;
		}
		// keep walking
		Set<Integer> neighbours = oldRoads.get(current);
		for (Integer city : neighbours) {
			if (!visited.contains(city)) {
				visited.add(city);
				
				walk(origin, copySet(visited), city, remSteps-1);
			}
		}
	}
	
	private Set<Integer> copySet(Set<Integer> visited) {
		Set<Integer> ret = new HashSet<>();
		for (Integer i : visited) {
			ret.add(i);
		}
		return ret;
	}

	private Map<Integer, Set<Integer>> connectNearCities() {
		// make new roads
		for (Integer city : oldRoads.keySet()) {
			Set<Integer> visited = new HashSet<>();
			visited.add(city);
			walk(city, visited, city, DISTANCE);
		}
		// merge paths
		mergeRoads();
		
		return oldRoads;
	}
	
	public String toString() { return "OLD " + oldRoads.toString(); }
	
	@Test
	public void test_walk() {
		
		ByteLandianTours foo = new ByteLandianTours();
		foo.addOldRoad(0, 1);
		foo.addOldRoad(1, 2);
		foo.addOldRoad(2, 3);
		Map<Integer, Set<Integer>> roads = foo.connectNearCities();
		//System.out.println(roads);
		System.out.println(foo.countPath(roads));
		
		//foo.calculateMinDistance();
	}

}
