/**
 * AccountData.java		1.0  15/02/04
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

import javax.annotation.Nonnull;

/**
 * Container Class for various email-account related details
 * (i.e. Username, Password, etc)
 * 
 * @version 1.0
 * @author Jered Tupik
 *
 */
public class AccountData{
	
	/* User's Email Username */
	private String UserName;
	
	/* User's Email Password */
	private String Password;
	
	/**
	 * Default Constructor for an AccountData object.
	 * Sets {@code UserName} to the local running user,
	 * and {@code Password} to "".
	 */
	public AccountData(){
		try{
			UserName = System.getProperty("user.name");
		}catch(IllegalArgumentException IAE){
			UserName = "localhost";
		}
		Password = "";
	}
	
	/**
	 * Custom Constructor for an AccountData object.
	 * Sets {@code UserName} to {@code UN}, and 
	 * {@code Password} to "".
	 * 
	 * @param UN The UserName of the email account to use
	 */
	public AccountData(@Nonnull final String UN){
		UserName = UN;
		Password = "";
	}
	
	/**
	 * Custom Constructor for an AccountData object.
	 * Sets {@code UserName} to {@code UN}, and 
	 * {@code Password} to {@code P}.
	 * 
	 * @param UN The UserName of the email account to use
	 * @param P  The Password of the email account to use
	 */
	public AccountData(@Nonnull final String UN, @Nonnull final String P){
		UserName = UN;
		Password = P;
	}
	
	/**
	 * toString() override for the AccountData object
	 */
	public String toString(){
		String Data = "";
		Data += "Email UserName: " + UserName + "\nEmail Password: " + Password;
		return Data;
	}
	
	/**
	 * Returns {@code UserName}
	 * 
	 * @return UserName The UserName field of the AccountData object
	 */
	public String getUserName(){
		return UserName;
	}
	
	/**
	 * Sets {@code UserName} of the
	 * AccountData object to 
	 * {@code UN}
	 * 
	 * @param UN The new {@code UserName}
	 *           of the AccountData object
	 */
	public void setUserName(final String UN){
		UserName = UN;
	}
	
	/**
	 * Returns {@code Password}
	 * 
	 * @return Password The Password field of the AcconutData object
	 */
	public String getPassword(){
		return Password;
	}
	
	/**
	 * Sets {@code Password} of the
	 * AccountData object to 
	 * {@code P}
	 * 
	 * @param P The new {@code Password} 
	 *          of the AccountData object
	 */
	public void setPassword(final String P){
		Password = P;
	}
}
