package net.suchgenie.koelnerPhonetic;

import java.util.HashMap;
import java.util.Map;

public class KoelnerPhonetik
{
	private Map<Character, Character> simpleMap = new HashMap<Character, Character>();
	
	public KoelnerPhonetik()
	{
		simpleMap.put('a', '0');
		simpleMap.put('ä', '0');
		simpleMap.put('á', '0');
		simpleMap.put('à', '0');
		simpleMap.put('e', '0');
		simpleMap.put('é', '0');
		simpleMap.put('è', '0');
		simpleMap.put('i', '0');
		simpleMap.put('í', '0');
		simpleMap.put('í', '0');
		simpleMap.put('j', '0');
		simpleMap.put('o', '0');
		simpleMap.put('ö', '0');
		simpleMap.put('ó', '0');
		simpleMap.put('ò', '0');
		simpleMap.put('ò', '0');
		simpleMap.put('u', '0');
		simpleMap.put('ü', '0');
		simpleMap.put('ú', '0');
		simpleMap.put('ù', '0');
		simpleMap.put('y', '0');
		simpleMap.put('b', '1');
		simpleMap.put('f', '3');
		simpleMap.put('v', '3');
		simpleMap.put('w', '3');
		simpleMap.put('g', '4');
		simpleMap.put('k', '4');
		simpleMap.put('q', '4');
		simpleMap.put('l', '5');
		simpleMap.put('m', '6');
		simpleMap.put('n', '6');
		simpleMap.put('r', '7');
		simpleMap.put('z', '8');
		simpleMap.put('ß', '8');
	}
	
	public String getPhonem(String term)
	{
		// prepare
		String input = term.toLowerCase().trim();

		// check
		if (input.equals(""))
		{
			return "";
		}

		StringBuffer phonem = new StringBuffer();

		Integer offset = 0;
		
		// init chars
		offset++;
		Character prevChar = null;
		Character currChar = input.charAt(0);
		Character nextChar = offset < input.length() ? input.charAt(1) : null;

		// leading c
		if (currChar == 'c' && nextChar != null)
		{
			phonem.append("ahkloqrux".contains(nextChar.toString()) ? "4" : "8");

			offset++;
			prevChar = currChar;
			currChar = nextChar;
			nextChar = offset < input.length() ? input.charAt(offset) : null;
		}

		// build phonem by checking each char
		while (currChar != null)
		{
			processLetter(phonem, prevChar, currChar, nextChar);
			
			offset++;
			prevChar = currChar;
			currChar = nextChar;
			nextChar = offset < input.length() ? input.charAt(offset) : null;
		}

		return combineDoubleChars(phonem.toString());
	}

	private void processLetter(StringBuffer phonem, Character prevChar, Character currChar, Character nextChar)
	{

		if (simpleMap.containsKey(currChar)) {
			phonem.append(simpleMap.get(currChar));
		}
		else if (currChar == 'p') {
			appendProcessedP(phonem, nextChar);
		}
		else if ("dt".contains(currChar.toString())) {
			appendProcessedDT(phonem, nextChar);
		}
		else if (currChar == 'c') {
			appendProcessedC(phonem, prevChar, nextChar);
		}
		else if (currChar == 'x') {
			appendProcessedX(phonem, prevChar);
		}
		else if (currChar == 's') {
			appendProcessedS(phonem, prevChar);
		}
		else if (currChar != 'h') {
			// special chars and numbers
			// skip "h"
			phonem.append('9');
		}
	}

	private void appendProcessedS(StringBuffer phonem, Character prevChar)
	{
		phonem.append((prevChar == null || prevChar != 'k') ? '8' : "");
	}

	private void appendProcessedX(StringBuffer phonem, Character prevChar)
	{
		phonem.append((prevChar != null && "ckq".contains(prevChar.toString())) ? '8' : '4');
	}

	private void appendProcessedP(StringBuffer phonem, Character nextChar)
	{
		phonem.append(nextChar == 'h' ? '3' : '1');
	}

	private void appendProcessedDT(StringBuffer phonem, Character nextChar)
	{
		phonem.append((nextChar != null && "cszß".contains(nextChar.toString())) ? '8' : '2');
	}

	private void appendProcessedC(StringBuffer phonem, Character prevChar, Character nextChar)
	{
		if (prevChar != null && "szß".contains(prevChar.toString()))
		{
			phonem.append('8');
		}
		else if (nextChar != null && "ahkloqrux".contains(nextChar.toString()))
		{
			phonem.append('4');
		}
		else
		{
			phonem.append('8');
		}
	}

	private String combineDoubleChars(String phonem)
	{
		// filtering
		StringBuffer filteredPhonem = new StringBuffer();
		
		// add first Character
		Character lastChar = phonem.charAt(0);
		Character currentChar;
		filteredPhonem.append(phonem.charAt(0));
		
		for (int i = 1, phonemLength = phonem.length(); i < phonemLength; i++)
		{
			currentChar = phonem.charAt(i);
			if ( ! currentChar.equals(lastChar) && ! currentChar.equals('0'))
			{
				lastChar = phonem.charAt(i);
				filteredPhonem.append(lastChar);
			}
			lastChar = currentChar;
		}
		return filteredPhonem.toString();
	}

}
