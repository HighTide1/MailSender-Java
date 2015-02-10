package com.hightide.mailsender.util;

import static org.junit.Assert.*;

import javax.crypto.BadPaddingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SecurityUtilitiesTest {
	
	protected char[] TEST_PASSWORD = ("Password").toCharArray();
	protected byte[] TEST_BYTES = ("This is a Test").getBytes();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEncryptText() {
		byte[] encryptedBytes = SecurityUtilities.encryptText(TEST_PASSWORD, TEST_BYTES);
		assertNotEquals(TEST_BYTES, encryptedBytes);
	}
	
	@Test
	public void testGoodDecryptText(){
		byte[] encryptedBytes = SecurityUtilities.encryptText(TEST_PASSWORD, TEST_BYTES);
		byte[] decryptedBytes = SecurityUtilities.decryptText(TEST_PASSWORD, encryptedBytes);
		assertArrayEquals(TEST_BYTES, decryptedBytes);
	}
	
	@Test
	public void testBadDecryptText(){
		char[] BadPassword = ("TEST").toCharArray();
		byte[] encryptedBytes = SecurityUtilities.encryptText(TEST_PASSWORD, TEST_BYTES);
		byte[] decryptedBytes = SecurityUtilities.decryptText(BadPassword, 
															  encryptedBytes);
		assertNull(decryptedBytes);
	}
}
