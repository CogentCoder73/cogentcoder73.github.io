
/**
 * Write a description of CaesarEncryptionInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class CaesarEncryptionInterface {
    public void main(){
        System.out.println("Hello! Welcome to this simple Caesar Cipher Encryption and Decryption program!");
        boolean ans = true;
        while(ans){
            ans = cipherInterface();
        }
        System.out.println("Thank you for using this program!");
    }
    private boolean cipherInterface(){
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\\n");
        String s = "";
        String message = "";
        int key1 = 0;
        int key2 = 0;
        String known = "";
        do {
            System.out.print("Would you like to encrypt or decrypt a message? (Enter either \"encrypt\" or \"decrypt\") ");
            s = scan.next();
        } while (!s.toLowerCase().equals("encrypt") && !s.toLowerCase().equals("decrypt"));
        if(s.toLowerCase().equals("encrypt")){
            System.out.print("What message would you like to encrypt? ");
            message = scan.next();
            do {
                System.out.print("Would you like to encrypt a message with 1 or 2 keys? (Enter either \"1\" or \"2\") ");
                s = scan.next();
            } while (!s.equals("1") && !s.equals("2"));
            if(s.equals("1")){
                System.out.print("What is the key? ");
                while(true) {
                    try{
                        key1 = scan.nextInt() % 26;
                        break;
                    } catch (Exception e){
                        System.out.print("Please enter an integer key: ");
                    }
                }
                System.out.println("The encrypted message is \"" + encrypt(message, key1) + "\"");
            } else {
                System.out.print("What is the first key? ");
                while(true) {
                    try{
                        key1 = scan.nextInt() % 26;
                        break;
                    } catch (Exception e){
                        System.out.print("Please enter an integer key: ");
                    }
                }
                System.out.print("What is the second key? ");
                while(true) {
                    try{
                        key2 = scan.nextInt() % 26;
                        break;
                    } catch (Exception e){
                        System.out.print("Please enter an integer key: ");
                    }
                }
                System.out.println("The encrypted message is \"" + encryptTwoKeys(message, key1, key2) + "\"");
            }
        } else {
            System.out.print("What message would you like to decrypt? ");
            message = scan.next();
            do {
                System.out.print("How many keys was this message encrypted with? (Enter either \"1\", \"2\", or \"Unknown\") ");
                s = scan.next();
            } while (!s.equals("1") && !s.equals("2") && !s.toLowerCase().equals("unknown"));
            if(s.equals("1") || s.equals("2")){
                do {
                    System.out.print("Do you know the key? (Enter either \"Yes\" or \"No\") ");
                    known = scan.next();
                } while (!known.toLowerCase().equals("yes") && !known.toLowerCase().equals("no"));
                if(known.toLowerCase().equals("yes")){
                    if(s.equals("1")){
                        System.out.print("What is the key? ");
                        while(true) {
                            try{
                                key1 = scan.nextInt() % 26;
                                break;
                            } catch (Exception e){
                                System.out.print("Please enter an integer key: ");
                            }
                        }
                        System.out.println("The decrypted message is \"" + decryptGivenKey(message, key1) + "\"");
                    } else {
                        System.out.print("What is the first key? ");
                        while(true) {
                            try{
                                key1 = scan.nextInt() % 26;
                                break;
                            } catch (Exception e){
                                System.out.print("Please enter an integer key: ");
                            }
                        }   
                        System.out.print("What is the second key? ");
                        while(true) {
                            try{
                                key2 = scan.nextInt() % 26;
                                break;
                            } catch (Exception e){
                                System.out.print("Please enter an integer key: ");
                            }
                        }
                        System.out.println("The decrypted message is \"" + decryptTwoKeysGivenKeys(message, key1, key2) + "\"");
                    }
                } else {
                    if(s.equals("1")){
                        System.out.println("The decrypted message is most likely \"" + decrypt(message) + "\"");
                    } else {
                        System.out.println("The decrypted message is most likely \"" + decryptTwoKeys(message) + "\"");
                    }
                }
            } else {
                System.out.println("The decrypted message is most likely either \"" + decrypt(message) + "\" or \"" 
                + decryptTwoKeys(message) + "\"");
            }
        }
        do {
            System.out.print("Would you like to use the Cipher again? (Enter either \"Yes\" or \"No\") ");
            s = scan.next();
        } while (!s.toLowerCase().equals("yes") && !s.toLowerCase().equals("no"));
        if(s.toLowerCase().equals("yes")){
            return true;
        }
        return false;
    }
    private String shift(int key){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26 - key; i++){
            sb.append(Character.toString((char)(65 + i + key)));
        }
        for(int i = 26 - key; i < 26; i++){
            sb.append(Character.toString(Character.toUpperCase((char)(71 + i + key))));
        }
        return sb.toString();
    }
    private char shiftChar(char ch, int key){
        if(Character.isLowerCase(ch)){
            if(Character.isAlphabetic((char) (ch + key))){
                return (char) (ch + key);
            }
            return (char) (ch + key - 26);
        }
        if(Character.isAlphabetic((char) (ch + key)) && Character.isUpperCase((char) (ch + key))){
            return (char) (ch + key);
        }
        return (char) (ch + key - 26);
    }
    private String encrypt(String input, int key){
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++){
            if(!Character.isAlphabetic(input.charAt(i))){
                continue;
            }
            sb.setCharAt(i, shiftChar(input.charAt(i), key));
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }
    private String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder sb = new StringBuilder(input);
        for(int i = 0; i < input.length(); i++){
            if(!Character.isAlphabetic(input.charAt(i))){
                continue;
            }
            if(i % 2 == 0){
                    sb.setCharAt(i, shiftChar(input.charAt(i), key1));
            } else {
                    sb.setCharAt(i, shiftChar(input.charAt(i), key2));
            }
        }
        //System.out.println(sb.toString());
        return sb.toString();
    }
    private void countLetters(String s, int[] letterCount){
        Scanner scan = new Scanner(s);
        while(scan.hasNext()){
            String cur = scan.next();
            for(int i = 0; i < cur.length(); i++){
                if(Character.isLetter(cur.charAt(i))){
                    letterCount[Character.toUpperCase(cur.charAt(i)) - 65]++;
                }
            }
        }
    }
    private int maxIndex(int[] values){
        //int[] letterCount = new int[26];
        //countLetters(s, letterCount);
        int curMaxIndex = 0;
        int curMax = values[0];
        for(int i = 1; i < values.length; i++){
            if(values[i] > curMax){
                curMaxIndex = i;
                curMax = values[i];
            }
        }
        return curMaxIndex;
    }
    private String decrypt(String encrypted){
        int[] letterCount = new int[26];
        for(int key = 0; key < 26; key++){
            String s = encrypt(encrypted, 26 - key);
            countLetters(s, letterCount);
            //System.out.println("i: " + key + ", letterCount = " + arrToStr(letterCount));
            if(maxIndex(letterCount) == 4){
                return s;
            }
            Arrays.fill(letterCount, 0);
        }
        return encrypted;
    }
    private String decryptGivenKey(String encrypted, int key){
        String s = encrypt(encrypted, 26 - key);
        return s;
    }
    private String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder(message);
        int cnt = (start + 1) % 2;
        for(int i = 0; i < (message.length() + start)/2; i++){
            sb.deleteCharAt(cnt);
            cnt++;
        }
        return sb.toString();
    }
    private int getKey(String s){
        int[] letterCount = new int[26];
        //System.out.println("encrypted: " + s);
        for(int key = 0; key < 26; key++){
            countLetters(encrypt(s, 26 - key), letterCount);
            //System.out.println("i: " + key + ", letterCount = " + arrToStr(letterCount));
            if(maxIndex(letterCount) == 4){
                return key;
            }
            Arrays.fill(letterCount, 0);
        }
        return 0;
    }
    private String decryptTwoKeys(String encrypted){
        int key1 = getKey(halfOfString(encrypted, 0));
        int key2 = getKey(halfOfString(encrypted, 1));
        System.out.println("The first key is " + key1);
        System.out.println("The second key is " + key2);
        return encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
    private String decryptTwoKeysGivenKeys(String encrypted, int key1, int key2){
        return encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
    }
}
