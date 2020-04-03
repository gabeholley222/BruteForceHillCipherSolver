import java.io.*;
import java.util.*;

public class Attack3x3Cipher {
	
	static Scanner sc = new Scanner(System.in);
	static int[][]matrix = new int[3][3];
	static int[][]decrypt = new int[3][1];
	
	static char[] ALPHABET = new char[26];
	
	/**
	 * Start program
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Decrypt 3x3 Matrix?(Yes = 1, No = 0) This may take a few minutes.");
		int response = sc.nextInt();
		if(response == 0) {
			exit();
		}else{
			getAllMatrices();
		}
	}
	/**
	 * Set up initial matrix layout
	 * (a	b	c)
	 * (d	e	f)
	 * (g	h	i)	
	 */
	public static void setupMatrix() {
		for(int j=0;j<3;j++) {
			for(int k=0; k<3; k++) {
				System.out.print(matrix[j][k] + " ");
			}
			System.out.print("\n");
		}
	}
	/**
	 * Sequence to get every matrix possibility for 3x3 matrix from 0-25
	 */
	public static void getAllMatrices() {
		for(int i=0; i<2; i++) {
			matrix[2][2] = i;
			setupMatrix();
			for(int j=0; j<2; j++) {
				matrix[2][1] = j;
				setupMatrix();
				for(int k=0; k<2; k++) {
					matrix[2][0] = k;
					setupMatrix();
					for(int l=0; l<2; l++) {
						matrix[1][2] = l;
						setupMatrix();
						for(int m=0; m<2; m++) {
							matrix[1][1] = m;
							setupMatrix();
							for(int n=0; n<2; n++) {
								matrix[1][0] = n;
								setupMatrix();
								for(int o=0; o<2; o++) {
									matrix[0][2] = o;
									setupMatrix();
									for(int p=0; p<2; p++) {
										matrix[0][1] = p;
										setupMatrix();
										for(int q=0; q<2; q++) {
											matrix[0][0] = q;
											setupMatrix();
											invertMatrix();
											checkResult();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	/**
	 * Checks to see if matrix is invertible. Uses inverted matrix to check against cipherText
	 */
	public static void invertMatrix() {
		int p, q;
		int[][]alphaIndex = matrix;
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
            	if(i == j) {
            		
            	}
            		
            		
            }
		}
	}
	
	/**
	 * Converts a character to a number (0-25)
	 * @param alphaIndex
	 * @return A number 0-25
	 */
	public static int toNumber(char alphaIndex) {
		for (int i = 0; i < ALPHABET.length; i++) {
			if (alphaIndex == ALPHABET[i]) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Checks the result of the (3x3inverse)*(3x1cipherText)%26 and prints out result.
	 * Will be adding a statement to only print strings with suspected cribs.
	 */
	public static void checkResult() {
		
	}	
	
	public static void exit() {
		System.out.println("Ok. Program Stopped.");
	}
}
