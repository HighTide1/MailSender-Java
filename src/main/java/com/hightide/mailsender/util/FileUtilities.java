/**
 * FileUtilities.java		1.2  15/02/10
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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;

/**
 * Utility class for various File I/O related
 * operations.
 * 
 * @version 1.0
 * @author Jered Tupik
 *
 */
public class FileUtilities{
	
	/**
	 * String of the Local User Directory for
	 * saving config files
	 */
	protected static String LOCAL_DIRECTORY;
	
	/**
	 * BufferedInputStream for the reading of files
	 */
	protected static BufferedInputStream BufferedInput;
	
	/**
	 * BufferedOutputStream for the reading of files
	 */
	protected static BufferedOutputStream BufferedOutput;
	
	/**
	 * If {@code CREATED_DIRECTORY} is false, then the User
	 * will need to add their password and account info 
	 * for every send. If it is true, then we can determine
	 * if a config file exists and read from there.
	 */
	private static boolean CREATED_DIRECTORY = false;
	
	static{
		try{
			LOCAL_DIRECTORY = System.getProperty("user.home");
		}catch(SecurityException SE){
			LOCAL_DIRECTORY = "/";
		}
		File currDirectory = new File(LOCAL_DIRECTORY);
		try{
			currDirectory.mkdirs();
			CREATED_DIRECTORY = true;
		}catch(SecurityException SE){
			CREATED_DIRECTORY = false;
		}
	}
}
