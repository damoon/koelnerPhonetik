package net.suchgenie.koelnerPhonetic;

import static org.junit.Assert.*;

import org.junit.Test;

public class KoelnerPhonetikTest {

	protected KoelnerPhonetik koelnerPhonetik = new KoelnerPhonetik();

	@Test
	public void testEmpty()
	{
		assertEquals("", koelnerPhonetik.getPhonem(""));
	}

	@Test
	public final void testShort()
	{
		assertEquals("0", koelnerPhonetik.getPhonem("a"));
		assertEquals("0", koelnerPhonetik.getPhonem("aa"));
	}

	@Test
	public final void testEasy()
	{
		assertEquals("1", koelnerPhonetik.getPhonem("b"));
		assertEquals("86", koelnerPhonetik.getPhonem("tzone"));
	}

	@Test
	public final void testMatch()
	{
		assertEquals("232", koelnerPhonetik.getPhonem("david"));
		assertEquals("232", koelnerPhonetik.getPhonem("davidd"));
		assertEquals("232", koelnerPhonetik.getPhonem("tawit"));
	}

	@Test
	public final void testX()
	{
		assertEquals("4536", koelnerPhonetik.getPhonem("xylophon"));
		assertEquals("64", koelnerPhonetik.getPhonem("nix"));
		assertEquals("48", koelnerPhonetik.getPhonem("qx"));
	}

	@Test
	public final void testS()
	{
		assertEquals("81", koelnerPhonetik.getPhonem("suppe"));
		assertEquals("674", koelnerPhonetik.getPhonem("murks"));

		assertEquals("78", koelnerPhonetik.getPhonem("reuas"));
		assertEquals("86", koelnerPhonetik.getPhonem("schön"));
	}

	@Test
	public final void testNumber()
	{
		assertEquals("9", koelnerPhonetik.getPhonem("007"));
	}

	@Test
	public final void testC()
	{
		assertEquals("8", koelnerPhonetik.getPhonem("c"));
		assertEquals("8", koelnerPhonetik.getPhonem("cc"));
		assertEquals("8", koelnerPhonetik.getPhonem("ccc"));

		assertEquals("474864", koelnerPhonetik.getPhonem("chorgesang"));
		assertEquals("474647", koelnerPhonetik.getPhonem("kirchenchor"));
		assertEquals("85", koelnerPhonetik.getPhonem("cello"));
		assertEquals("1582485", koelnerPhonetik.getPhonem("plastikcello"));
		assertEquals("68", koelnerPhonetik.getPhonem("nic"));
	}

}
