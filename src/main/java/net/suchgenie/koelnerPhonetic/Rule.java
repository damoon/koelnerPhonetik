package net.suchgenie.koelnerPhonetic;

import java.util.HashSet;
import java.util.Set;

class Rule
{
	private Boolean hasToBeFirst = false;

	private Boolean anyCharacterBefore = false;

	private Boolean anyCharacterAfter = false;

	private Set<Character> before = new HashSet<Character>();

	private Set<Character> after = new HashSet<Character>();

	private Set<Character> from = new HashSet<Character>();

	private String to;

	protected Rule(String line)
	{
		String[] parts = line.split(" -> ");

		to = parts[1].equals("_") ? "" : parts[1];

		String[] fromParts = parts[0].split(" ");

		extractBefore(fromParts[0]);

		extractFrom(fromParts[1]);

		extractAfter(fromParts[2]);
	}

	protected boolean isMatching(Character previousChar, Character currentChar, Character nextChar)
	{
		return matchesCurrentChar(currentChar) && matchesPreviousChar(previousChar) && matchesNextChar(nextChar);
	}

	private boolean matchesNextChar(Character nextChar)
	{
		return anyCharacterAfter || nextChar != null && after.contains(nextChar);
	}

	private boolean matchesCurrentChar(Character currentChar)
	{
		return from.contains(currentChar);
	}

	private boolean matchesPreviousChar(Character previousChar)
	{
		if (previousChar == null && hasToBeFirst)
		{
			return true;
		}
		return anyCharacterBefore || previousChar != null && before.contains(previousChar);
	}

	protected String getTo()
	{
		return to;
	}

	private void extractAfter(String afterChars)
	{
		if (afterChars.equals("*"))
		{
			anyCharacterAfter = true;
		}
		else
		{
			for (String character : afterChars.split(","))
			{
				after.add(character.charAt(0));
			}
		}
	}

	private void extractBefore(String beforeChars)
	{
		if (beforeChars.equals("*"))
		{
			anyCharacterBefore = true;
		}
		else if (beforeChars.equals("^"))
		{
			hasToBeFirst = true;
		}
		else
		{
			for (String character : beforeChars.split(","))
			{
				before.add(character.charAt(0));
			}
		}
	}

	private void extractFrom(String fromChars)
	{
		if (fromChars.equals(","))
		{
			from.add(',');
		}
		else
		{
			for (String character : fromChars.split(","))
			{
				from.add(character.charAt(0));
			}
		}
	}
}