/**
 * Program to solve all possible solutions to a Maths puzzle
 * a + 13 * b / c + d + 12 * e - f - 11 + g * h / i - 10 == 66
 * a..i E Z : 0 < a..i < 10
 * 
 * @author Alan Cowap
 * @date 20150522 (22-May-2015)
 * @version 1.0
 * @link http://www.alancowap.com/2015/05/23/maths-puzzles-are-fun/
 * 
 */
package com.alancowap.maths.snakepuzzle;

import java.util.Arrays;

public class SnakePuzzle {

	private static final double ANSWER = 66.0D;
	private static int numChecks=0;
	private static int numMatches=0;
	
	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		new SnakePuzzle().execute();
		time -= System.currentTimeMillis();
		System.out.println("\n\nCalculations took " + -time + " milliseconds" );
		System.out.println("Permutations checked " + SnakePuzzle.numChecks);
		System.out.println("Winning matches " + SnakePuzzle.numMatches);
	}

	
	private void execute(){
		byte[] seedNums = {1,2,3,4,5,6,7,8,9};
		this.checkEquation(seedNums); //check initial Equation
		this.checkArray(seedNums, 0, 0, seedNums.length);
	}
	
	/**
	 *  Given a formula, swap pieces from start to end
	 * @param formula
	 * @param idx index of array to swap
	 * @param start inclusive
	 * @param end exclusive
	 */
	private void checkArray(byte[] formula, int idx, int start, int end){
		//make copy of original array
		byte[] arr = Arrays.copyOf(formula, formula.length);
		
		//iterate through checkArray swapping each element in turn
		for(int i=start; i < end; ++i){
			if(i != idx){
				// swap elements
				this.swap(arr, i, idx);

				// check modified equation
				this.checkEquation(arr);
			}
			// iterate through remaining elements
			this.checkArray(arr, idx+1, start+1, arr.length);			
		}
		
	}
	
	/**
	 * Swap two elements in an array
	 * @param arr array containing elements to swap
	 * @param id first element to swap
	 * @param id2 second element to swap
	 */
	private void swap(byte[] arr, int id, int id2){
		byte tmp = arr[id];
		arr[id] = arr[id2];
		arr[id2]=tmp;
	}
	
	
	/**
	 * Check an equation to calculate if it is correct
	 * 
	 * @param array 
	 */
	private strictfp void checkEquation(byte[] array){
		++SnakePuzzle.numChecks;
		
		//Make an array of double, we want precise calculations
		double[] arr = new double[9];
		for(int i=0; i<arr.length; ++i){
			arr[i] = array[i];
		}
		
		double result = arr[0] + ((13 * arr[1]) / arr[2]) + arr[3] + (12 * arr[4]) - arr[5] - 11 + ((arr[6] * arr[7]) / arr[8]) - 10; //66
		if (result == ANSWER){
			++SnakePuzzle.numMatches;
			System.out.print("\nWinner: ");
			for(double i : arr){
				System.out.print((int) i + " ");				
			}
			System.out.print("\t: " + result + " : " + SnakePuzzle.numMatches);
		}
//		else{ //print the failures
//			for(double i : arr){
//				System.out.print((int)i + " ");				
//			}
//			System.out.print(" "+ result + "\n");
//		}
	}
	
}
