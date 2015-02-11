/**
 * SecurityUtilities.java		1.2  15/02/10
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

package com.hightide.mailsender.util;

import javax.annotation.Nonnull;
import javax.swing.JOptionPane;

import java.awt.HeadlessException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Utility class for the Encryption/Decryption of .cfg
 * 
 * @version 1.0
 * @author Jered Tupik
 *
 */
public class SecurityUtilities{
	
	/**
	 * PBEKeySpec used for the generation
	 * of a password-based key
	 */
	private static PBEKeySpec 			PBEKeySpecification;
	
	/** 
	 * PBEParameterSpec used for the generation
	 * a salt/count based specification
	 */
	private static PBEParameterSpec     PBEParameterSpecification;
	
	/**
	 * Secret Key for PBE Encryption/Decryption
	 */
	private static SecretKey			PBEKey;
	
	/**
	 * SecretKeyFactory for the PBE Encryption/Decryption
	 */
	private static SecretKeyFactory		PBESecretKeyGenerator;
	
	/**
	 * PBECipher used for encryption/decryption of 
	 * files
	 */
	private static Cipher 				PBECipher;
	
	/**
	 * Salt used for the PBEParameterSpecification.
	 * NOTE: If someone other than the author is
	 * using this program, CHANGE THESE VALUES.
	 * Otherwise, the security of your files
	 * cannot be guaranteed.
	 */
	private static final byte[] SALT = {
		(byte)0x7F, (byte)0x09, (byte)0xFF, (byte)0xAD,
		(byte)0xA8, (byte)0x00, (byte)0xCF, (byte)0x9A
	};
	
	/**
	 * Count used for the PBEParameterSpecification
	 * NOTE: If someone other than the author is
	 * using this program, CHANGE THESE VALUES.
	 * Otherwise, the security of your files
	 * cannot be guaranteed.
	 */
	private static final int ITER_COUNT = 10;
	
	//public static void setupSecurityUtilities(){
	static{
		try{
			PBESecretKeyGenerator = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
		}catch(NoSuchAlgorithmException NSA){
			System.err.println(NSA);
			System.exit(1);
		}
		
		PBEParameterSpecification = new PBEParameterSpec(SALT, ITER_COUNT);
		
		try{
			PBECipher = Cipher.getInstance("PBEWithMD5AndDES");
		}catch(Exception E){
			System.err.println(E);
			System.exit(1);
		}
	}
	
	/**
	 * Encryptes the text with a user-given password and salt
	 * 
	 * @param Password The password to use for the encryption
	 * @return The Encrypted Text
	 */
	public static byte[] encryptText(@Nonnull char[] Password, @Nonnull byte[] decryptedBytes){
		byte[] 		  UserInput;
		byte[]   	  encryptedBytes = null;

		PBEKeySpecification = new PBEKeySpec(Password);
		
		try{
			PBEKey = PBESecretKeyGenerator.generateSecret(PBEKeySpecification);
		}catch(InvalidKeySpecException IKS){
			System.err.println(IKS);
			System.exit(1);
		}
		
		try {
			PBECipher.init(Cipher.ENCRYPT_MODE, PBEKey, PBEParameterSpecification);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			encryptedBytes = PBECipher.doFinal(decryptedBytes);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return encryptedBytes;
	}
	
	/**
	 * Decrypts the text with a user-given password and encrypted text
	 * 
	 * @param Password The password used for the encryption
	 * @param encryptedBytes The encrypted bytes to decrypt
	 * @return The decrypted text
	 */
	public static byte[] decryptText(@Nonnull char[] Password, @Nonnull byte[] encryptedBytes){
		byte[] 		  UserInput;
		byte[]   	  decryptedBytes = null;
		
		PBEKeySpecification = new PBEKeySpec(Password);
		
		try{
			PBEKey = PBESecretKeyGenerator.generateSecret(PBEKeySpecification);
		}catch(InvalidKeySpecException IKS){
			System.err.println(IKS);
			System.exit(1);
		}
		
		try {
			PBECipher.init(Cipher.DECRYPT_MODE, PBEKey, PBEParameterSpecification);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			decryptedBytes = PBECipher.doFinal(encryptedBytes);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return decryptedBytes;
	}
}
