import java.io.*;
import java.util.*;

public class Attack3x3Cipher {
	
	static StringBuilder contentBuilder = new StringBuilder();
	static Scanner sc = new Scanner(System.in);
	static int[][]matrix = new int[3][3];
	static int[][]inverseMatrix = new int[3][3];
	static int[][]a = new int[3][3];
	static int[][]b = new int[3][3];
	//static int[]intArray = new int[contentBuilder.length() - 1];
	static int textToInt;
	
	static int[] ALPHABET = new int[26];
	
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
			//getAllMatrices();
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
				//System.out.print(matrix[j][k] + " ");
			}
			//System.out.print("\n");
		}
	}
	/**
	 * Sequence to get every matrix possibility for 3x3 matrix from 0-25
	 */
	public static void getAllMatrices() {
		for(int i=0; i<8; i++) {
			matrix[2][2] = i;
			setupMatrix();
			for(int j=0; j<8; j++) {
				matrix[2][1] = j;
				setupMatrix();
				for(int k=0; k<8; k++) {
					matrix[2][0] = k;
					setupMatrix();
					for(int l=0; l<8; l++) {
						matrix[1][2] = l;
						setupMatrix();
						for(int m=0; m<8; m++) {
							matrix[1][1] = m;
							setupMatrix();
							for(int n=0; n<8; n++) {
								matrix[1][0] = n;
								setupMatrix();
								for(int o=0; o<8; o++) {
									matrix[0][2] = o;
									setupMatrix();
									for(int p=0; p<8; p++) {
										matrix[0][1] = p;
										setupMatrix();
										for(int q=0; q<8; q++) {
											matrix[0][0] = q;
											setupMatrix();
											invertMatrix(matrix);
											//checkResult();
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
	public static void invertMatrix(int matrix[][]) {
		int p, q;
		int convert[][] = new int[3][3];
		for(int i = 0; i < 3; i++) 
        	for(int j = 0; j < 3; j++) 
        		convert[i][j] = matrix[i][j];
		System.out.println("\n");
        for(int i = 0; i < 3; i++) 
        	for(int j = 0; j < 3; j++) { 
        		System.out.print(convert[i][j] + " ");
        		if(i == j) 
        			inverseMatrix[i][j] = 1; 
                else 
                	inverseMatrix[i][j] = 0; 
        	} 
        for(int k = 0; k < 3; k++) { 
             for(int i = 0; i < 3; i++) { 
                  p = convert[i][k]; 
                  q = convert[k][k]; 
                  for(int j = 0; j < 3; j++) { 
                	  if(i != k) { 
                    	    convert[i][j] = convert[i][j] * q - p * convert[k][j]; 
                            inverseMatrix[i][j] = inverseMatrix[i][j] * q - p * inverseMatrix[k][j]; 
                      } 
                  } 
             } 
        } 
        for(int i = 0; i < 3; i++) {
        	for (int j = 0; j < 3; j++) { 
        		if(convert[i][i] != 0) {
        			inverseMatrix[i][j] = inverseMatrix[i][j] / convert[i][i];
            	}
            } 
        }
        System.out.println("\nInverse Matrix: ");
        for(int i = 0; i < 3; i++) { 
        	for(int j = 0; j < 3; j++) {
        		if(inverseMatrix[i][0] == 0 && inverseMatrix[i][1] == 0 && inverseMatrix[i][2] == 0) {
        		}else {	
        			inverseMatrix[i][j] %= 26;
        			System.out.print(inverseMatrix[i][j]%26 + " ");
        		}
        	}
        System.out.print("\n");
        }
        if(inverseMatrix[0][0] == 0 && inverseMatrix[0][1] == 0 && inverseMatrix[0][2] == 0
           && inverseMatrix[1][0] == 0 && inverseMatrix[1][1] == 0 && inverseMatrix[1][2] == 0
           && inverseMatrix[2][0] == 0 && inverseMatrix[2][1] == 0 && inverseMatrix[2][2] == 0) {
        }
       //  getResult();?
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
	public static void checkResult() {
		
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
	public static int stringToInt(StringBuilder contentBuilder) {
		for(int i = 0; i < contentBuilder.length() - 1; i++) {
			char character = contentBuilder.charAt(i);
			textToInt = (int)character - (int)'A';
			System.out.print(textToInt + " ");
		}
		return 0;
	}
	
	public static void exit() {
		System.out.println("Ok. Program Stopped.");
	}
}
