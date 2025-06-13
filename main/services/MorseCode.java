package main.services;
import java.util.*;
import java.lang.*;

public class MorseCode{
	
		private static final Map<String,Character> morseToEnglish = new HashMap<>();
		private static final Map<Character,String> englishToMorse = new HashMap<>();
		
		static{
			String[] english = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.,?!'()&:;=+-_$@".split("");
			String[] code = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
                "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
                "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", "-----", ".-.-.-", "--..--",
                "..--..", "-.-.--", ".-..-.", ".----.", "-.--.", "-.--.-", ".-...", "---...", "-.-.-.", "-...-",
                ".-.-.", "-....-", "..--.-", "...-..-", ".--.-."};
				
			for(int i = 0; i<english.length; i++)
			{
				morseToEnglish.put(code[i],english[i].charAt(0));
				englishToMorse.put(english[i].charAt(0),code[i]);
			}
		}
		
		//Encodes text into morse code
		public static String morseEncoder(String text)
		{
			StringBuilder morseText = new StringBuilder();
			for (char c: text.toUpperCase().toCharArray())
			{
				if(englishToMorse.containsKey(c))
				{
					morseText.append(englishToMorse.get(c)).append(" ");
				}
				else { morseText.append(" "); }
			}
			return morseText.toString().trim(); // returns the final morse code
		}
		
		//Decodes morse code into text
		public static String morseDecoder(String morse)
		{
			StringBuilder englishText = new StringBuilder();
			for (String m : morse.split(" "))
			{
				if(morseToEnglish.containsKey(m))
				{
					englishText.append(morseToEnglish.get(m));
				}
				else { englishText.append(" "); }
			}
			return englishText.toString().trim(); // returns the final english text
		}
		
}
			