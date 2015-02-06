/**
 * AccountData.java		1.0  15/02/05
 * 
 * Copyright (C) {2015}  {Tupik, Jered}
 * 
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *  
 *  @author Jered Tupik
 *  @version 1.0
 */

package com.hightide.mailsender.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testing Class for the AccountData object
 * 
 * @version 1.0
 * @author Jered Tupik
 *
 */
public class AccountDataTest {
	
	protected AccountData TestAccount;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception{
		TestAccount = new AccountData();
	}

	@After
	public void tearDown() throws Exception{
		TestAccount = null;
	}

	@Test
	public void testAccountDataDefault() {
		String defaultUserName = "";
		String defaultPassword = "";
		
		try{
			defaultUserName = System.getProperty("user.name");
		}catch(Exception E){
			defaultUserName = "localhost";
		}
		
		TestAccount = new AccountData();
		assertEquals(defaultUserName, TestAccount.getUserName());
		assertEquals(defaultPassword, TestAccount.getPassword());
	}

	@Test
	public void testAccountDataCustomUserName() {
		String customUserName = "herp-derp";
		TestAccount = new AccountData(customUserName);
		assertEquals(customUserName, TestAccount.getUserName());
		assertEquals("", TestAccount.getPassword());
	}

	@Test
	public void testAccountDataCustomValues() {
		String customUserName = "testname";
		String customPassword = "password";
		TestAccount = new AccountData(customUserName, customPassword);
		assertEquals(customUserName, TestAccount.getUserName());
		assertEquals(customPassword, TestAccount.getPassword());
	}

	@Test
	public void testGetUserName(){
		TestAccount = new AccountData("blah");
		assertEquals("blah", TestAccount.getUserName());
	}

	@Test
	public void testSetUserName(){
		String newUserName = "halb";
		TestAccount.setUserName(newUserName);
		assertEquals(TestAccount.getUserName(), newUserName);
	}

	@Test
	public void testGetPassword() {
		assertEquals("", TestAccount.getPassword());
	}

	@Test
	public void testSetPassword(){
		String newPassword = "pass";
		TestAccount.setPassword(newPassword);
		assertEquals(newPassword, TestAccount.getPassword());
	}

}
