import java.io.*;
import java.util.*;

public class Attack3x3Cipher {
	// This is how many characters are in the Cipher Message (Change value of MESSAGE for different length messages)
	final static int MESSAGE = 432;
	
	static StringBuilder contentBuilder = new StringBuilder();
	static Scanner sc = new Scanner(System.in);
	
	static int[][]matrix = new int[3][3];
	static int[][]result = new int[3][3];
	static int[]convert = new int[3];
	
	static int[]ALPHABET = new int[26];
	static int[]textToInt = new int[MESSAGE];
	static int[]intToText = new int[MESSAGE];
	static char[]textResult = new char[MESSAGE];
	
	static String cipherText;
	static String textConvert;
	
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
			String filePath = "C:/Users/gabeh/Documents/MAT391/CipherText3x3.txt";
			System.out.println(readCipherText(filePath));
			stringToInt(contentBuilder);
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
		//System.out.print("Matrix is: \n");
		for(int j=0;j<3;j++) {
			for(int k=0; k<3; k++) {
				//System.out.print(matrix[j][k] + " ");
			}
			//System.out.print("\n");
		}
	}
	/**
	 * Sequence to get every matrix possibility for 3x3 matrix from 0-25
	 */
	public static void getAllMatrices() {
		for(int i=0; i<2; i++) {
			matrix[2][2] = i;
			for(int j=0; j<2; j++) {
				matrix[2][1] = j;
				for(int k=0; k<2; k++) {
					matrix[2][0] = k;
					for(int l=0; l<2; l++) {
						matrix[1][2] = l;
						for(int m=0; m<2; m++) {
							matrix[1][1] = m;
							for(int n=0; n<2; n++) {
								matrix[1][0] = n;
								for(int o=0; o<2; o++) {
									matrix[0][2] = o;
									for(int p=0; p<2; p++) {
										matrix[0][1] = p;
										for(int q=0; q<2; q++) {
											matrix[0][0] = q;
											setupMatrix();
											checkResult(matrix, textToInt);
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
	 * Makes letters A-Z in the format of 0-25
	 * @param alphaIndex
	 * @return A number 0-25
	 */
	public static int toNumber() {
		int alphaIndex = 0;
		for (int i = 0; i < ALPHABET.length; i++) {
			alphaIndex = i;
			System.out.println(alphaIndex);
		}
		return alphaIndex;
	}
	/**
	 * Checks the result of the (3x3inverse)*(3x1cipherText)%26 and prints out result.
	 * Will be adding a statement to only print strings with suspected cribs.
	 */
	public static void checkResult(int matrix[][], int textToInt[]) {
		for (int i = 0; i < MESSAGE - 1; i+=0) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					result[j][k] = textToInt[i] * matrix[j][k];
					//System.out.print(result[j][k] + " ");
					i++;
				}
				i-=3;
				int sum = ((result[j][0] + result[j][1] + result[j][2])%26) + 65;
				//System.out.println(sum + " ");
				textResult[i] = (char)sum;
				//textConvert = String.copyValueOf(textResult);
				//System.out.print(textConvert);
			}
			i+=3;
		}
		/*for(int a = 0; a < MESSAGE - 1; a++) {
			if((textResult[a] == 'C' && textResult[a+1] == 'C' && textResult[a+1] == 'C')||(textResult[a] == 'T' && textResult[a+1] == 'H' && textResult[a+2] == 'E')) {
				System.out.print(textResult);
			}
		}*/
	}
	/**
	 * Read file and convert contents to string.
	 * Will have to modify file path to work on individual computers.
	 * @param filePath
	 * @return
	 */
	public static String readCipherText(String filePath) 
	{
	    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) 
	    {
	        while ((cipherText = br.readLine()) != null) 
	        {
	            contentBuilder.append(cipherText).append("\n");
	        }
	    } 
	    catch (IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return contentBuilder.toString();
	}
	/**
	 * Convert cipherText file into meaningful integers corresponding to A-Z = 0-25
	 * @param contentBuilder
	 * @return
	 */
	public static void stringToInt(StringBuilder contentBuilder) {
		for(int i = 0; i < contentBuilder.length() - 1; i++) {
			char character = contentBuilder.charAt(i);
			textToInt[i] = (int)character - (int)'A';
			System.out.print(textToInt[i] + " ");
		}
	}
	public static void exit() {
		System.out.println("Ok. Program Stopped.");
	}
}
