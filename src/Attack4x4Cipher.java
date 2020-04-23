import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
 * Created By Gabriel Holley
 * To attack 4x4 enciphered messages 
 * with possible cribs by brute force.
 * Expected total run time: To be determined
 */
public class Attack4x4Cipher {
	// This is how many characters are in the Cipher Message (Change value of MESSAGE for different length messages)
	final static int MESSAGE = 436;
	
	static StringBuilder contentBuilder = new StringBuilder();
	static Scanner sc = new Scanner(System.in);
	
	static int[][]matrix = new int[4][4];
	static int[][]result = new int[4][4];
	static int[]convert = new int[4];
	
	static int[]ALPHABET = new int[26];
	static int[]textToInt = new int[MESSAGE];
	static int[]intToText = new int[MESSAGE];
	static char[]textResult = new char[MESSAGE];
	
	static String[]textConvert = new String[MESSAGE];
	static String cipherText;
	static String plainText;
	
	/**
	 * Start program
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Decrypt 4x4 Matrix?(Yes = 1, No = 0) This will take many hours.");
		int response = sc.nextInt();
		if(response == 0) {
			exit();
		}else{
			String filePath = "C:/Users/gabeh/Documents/MAT391/CipherText4x4.txt";
			System.out.println(readCipherText(filePath));
			stringToInt(contentBuilder);
			getAllMatrices();
		}
	}
	/**
	 * Set up initial matrix layout
	 * (a	b	c	d)
	 * (e	f	g	h)
	 * (i	j	k	l)
	 * (m	n	o	p)	
	 */
	public static void setupMatrix() {
		//System.out.print("Matrix is: \n");
		for(int j=0;j<4;j++) {
			for(int k=0; k<4; k++) {
				//System.out.print(matrix[j][k] + " ");
			}
			//System.out.print("\n");
		}
	}
	/**
	 * Sequence to get every matrix possibility for 4x4 matrix from 0-25
	 */
	public static void getAllMatrices() {
		for(int a=0; a<26; a++) {
			matrix[3][3] = a;
			for(int b=0; b<26; b++) {
				matrix[3][2] = b;
				for(int c=0; c<26; c++) {
					matrix[3][1] = c;
					for(int d=0; d<26; d++) {
						matrix[3][0] = d;
						for(int e=0; e<26; e++) {
							matrix[2][3] = e;
							for(int f=0; f<26; f++) {
								matrix[2][2] = f;
								for(int g=0; g<26; g++) {
									matrix[2][1] = g;
									for(int h=0; h<26; h++) {
										matrix[2][0] = h;
										for(int i=0; i<26; i++) {
											matrix[1][3] = i;
											for(int j=0; j<26; j++) {
												matrix[1][2] = j;
												for(int k=0; k<26; k++) {
													matrix[1][1] = k;
													for(int l=0; l<26; l++) {
														matrix[1][0] = l;
														for(int m=0; m<26; m++) {
															matrix[0][3] = m;
															for(int n=0; n<26; n++) {
																matrix[0][2] = n;
																for(int o=0; o<26; o++) {
																	matrix[0][1] = o;
																	for(int p=0; p<26; p++) {
																		matrix[0][0] = p;
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
	 * Checks the result of the (4x4inverse)*(4x1cipherText)%26 and prints out result.
	 */
	public static void checkResult(int matrix[][], int textToInt[]) {
		int counter = 0;
		for (int i = -1; i < MESSAGE - 1; i+=0) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					i++;
					result[j][k] = textToInt[i] * matrix[j][k];
				}
				i-=4;
				int sum = ((result[j][0] + result[j][1] + result[j][2] + result[j][3])%26) + 65;
				textResult[counter] = (char)sum;
				textConvert[counter] = String.valueOf(textResult[counter]);
				counter++;
			}
				i+=4;
		}
		StringBuilder sb = new StringBuilder();
		for(int a = 0; a < MESSAGE - 1; a++) {
			sb.append(String.valueOf(textConvert[a]));
		}
		plainText = sb.toString();
		/**
		 * Modify to attack by suspected cribs
		 * I recommend using larger words
		 */
		if(plainText.contains("CENSOR") && plainText.contains("THE") && plainText.contains("CIPHER")) {
			for(int b = 0; b < 4; b++) {
				for(int c = 0; c < 4; c++) {
					System.out.print(matrix[b][c] + " ");
				}
			}
			System.out.println();
			System.out.println(plainText);
		}
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
		System.out.println();
	}
	public static void exit() {
		System.out.println("Ok. Program Stopped.");
	}
}