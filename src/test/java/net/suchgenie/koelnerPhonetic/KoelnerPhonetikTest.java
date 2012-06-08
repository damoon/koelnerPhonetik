package net.suchgenie.koelnerPhonetic;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class KoelnerPhonetikTest
{

	protected KoelnerPhonetik koelnerPhonetik;

	@Before
	public void before() throws IOException
	{
		koelnerPhonetik = new KoelnerPhonetik();
	}
	
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
		assertEquals("48", koelnerPhonetik.getPhonem("x"));
		assertEquals("48536", koelnerPhonetik.getPhonem("xylophon"));
		assertEquals("648", koelnerPhonetik.getPhonem("nix"));
		assertEquals("48", koelnerPhonetik.getPhonem("qx"));
		assertEquals("748", koelnerPhonetik.getPhonem("rx"));
	}

	@Test
	public final void testS()
	{
		assertEquals("81", koelnerPhonetik.getPhonem("suppe"));
		assertEquals("6748", koelnerPhonetik.getPhonem("murks"));

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

	public final void testDifferentWords()
	{
		assertEquals("3412", koelnerPhonetik.getPhonem("Wikipedia"));
		assertEquals("17863", koelnerPhonetik.getPhonem("Breschnew"));
		assertEquals("65752682", koelnerPhonetik.getPhonem("Müller-Lüdenscheidt"));

		assertEquals("68", koelnerPhonetik.getPhonem("Hans"));
		assertEquals("3768", koelnerPhonetik.getPhonem("Franz"));
		assertEquals("8452", koelnerPhonetik.getPhonem("Schokolade"));
		assertEquals("726137", koelnerPhonetik.getPhonem("Raddampfer"));

		assertEquals("678657", koelnerPhonetik.getPhonem("Moritz Müller"));
		assertEquals("678657", koelnerPhonetik.getPhonem("Moriz Müler"));
		assertEquals("5767", koelnerPhonetik.getPhonem("Laura Mayer"));
		assertEquals("5767", koelnerPhonetik.getPhonem("Laura Meier"));

		assertEquals("516", koelnerPhonetik.getPhonem("Leben"));
		assertEquals("516", koelnerPhonetik.getPhonem("Lübyien"));
		assertEquals("516", koelnerPhonetik.getPhonem("Lybien"));

		assertEquals("4783", koelnerPhonetik.getPhonem("CRESZEW"));

		assertEquals("127", koelnerPhonetik.getPhonem("peter"));
		assertEquals("376", koelnerPhonetik.getPhonem("pharma"));
		assertEquals("174845214", koelnerPhonetik.getPhonem("bergisch-gladbach"));
		assertEquals("664645214", koelnerPhonetik.getPhonem("münchengladbach"));
		assertEquals("28", koelnerPhonetik.getPhonem("deutsch"));

		assertEquals("28", koelnerPhonetik.getPhonem("deutz"));
		assertEquals("06174", koelnerPhonetik.getPhonem("hamburg"));
		assertEquals("0637", koelnerPhonetik.getPhonem("hannover"));
		assertEquals("478256", koelnerPhonetik.getPhonem("christstollen"));
		assertEquals("48621", koelnerPhonetik.getPhonem("Xanthippe"));

		assertEquals("8478", koelnerPhonetik.getPhonem("Zacharias"));
		assertEquals("0581", koelnerPhonetik.getPhonem("Holzbau"));
		assertEquals("68", koelnerPhonetik.getPhonem("matsch"));
		assertEquals("68", koelnerPhonetik.getPhonem("matz"));
		assertEquals("071862", koelnerPhonetik.getPhonem("Arbeitsamt"));

		assertEquals("01772", koelnerPhonetik.getPhonem("Eberhard"));
		assertEquals("01772", koelnerPhonetik.getPhonem("Eberhardt"));
		assertEquals("021", koelnerPhonetik.getPhonem("heithabu"));

	}

}
