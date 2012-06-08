package net.suchgenie.koelnerPhonetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * KoelnerPhonetik is used to create phonems from words
 */
public class KoelnerPhonetik
{
	private List<Rule> rules = new LinkedList<Rule>();

	/**
	 * initializes the rules to transcript the strings
	 * @throws IOException
	 */
	public KoelnerPhonetik() throws IOException
	{
		createRules(KoelnerPhonetik.class.getResourceAsStream("/koelnerPhoneticRules.txt"));
	}

	private void createRules(InputStream resourceAsStream) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(resourceAsStream));
		String line = reader.readLine();
		while (line != null)
		{
			if (line.length() > 0)
			{
				rules.add(new Rule(line));
			}
			line = reader.readLine();
		}
	}

	/**
	 * creates the phonem to the given word
	 * @param term
	 * @return the transcripted phonem 
	 */
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

		iterateOverCharacters(input, phonem);

		return combineDoubleChars(phonem).toString();
	}

	private void iterateOverCharacters(String input, StringBuffer phonem)
	{
		Integer offset = 1;
		Character prevChar = null;
		Character currChar = input.charAt(0);
		Character nextChar = offset < input.length() ? input.charAt(1) : null;

		while (currChar != null)
		{
			processLetter(phonem, prevChar, currChar, nextChar);

			offset++;
			prevChar = currChar;
			currChar = nextChar;
			nextChar = offset < input.length() ? input.charAt(offset) : null;
		}
	}

	private void processLetter(StringBuffer phonem, Character prevChar, Character currChar, Character nextChar)
	{
		for (Rule rule : rules)
		{
			if (rule.isMatching(prevChar, currChar, nextChar))
			{
				phonem.append(rule.getTo());
				return;
			}
		}
	}

	private StringBuffer combineDoubleChars(StringBuffer phonem)
	{
		// filtering
		StringBuffer filteredPhonem = new StringBuffer();

		// add first Character
		// keep 0 as first character
		Character previousChar = phonem.charAt(0);
		filteredPhonem.append(phonem.charAt(0));

		for (int i = 1, phonemLength = phonem.length(); i < phonemLength; i++)
		{
			Character currentChar = phonem.charAt(i);
			if (!currentChar.equals(previousChar) && !currentChar.equals('0'))
			{
				previousChar = phonem.charAt(i);
				filteredPhonem.append(previousChar);
			}
			previousChar = currentChar;
		}
		return filteredPhonem;
	}

}
