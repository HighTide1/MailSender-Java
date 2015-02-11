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

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.nio.file.attribute.*;

import javax.annotation.Nonnull;

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
	 * <p>Writes the given information, {@code FileData} to {@code FileName} in the 
	 * given {@code Directory} if it exists. Otherwise, it will try to create
	 * {@code FileName} in {@code Directory} and write to it.</p>
	 * <p>If this fails, the method will simply quit.</p>
	 * 
	 * @param Directory The current directory to write to.
	 * @param FileName The file to create/append to.
	 * @param FileData The information to write to the file.
	 */
	public static void writeToFile(@Nonnull String Directory, @Nonnull String FileName, 
			                       @Nonnull byte[] FileData){
		Path DirectoryPath = Paths.get(Directory);
		if(!Files.exists(DirectoryPath)){
			try{
				Files.createDirectory(DirectoryPath);
			}catch(IOException IOE){
				System.err.println("Unable to create/edit directory");
				return;
			}
		}
		Path FilePath = Paths.get(Directory, FileName);
		try{
			if(!Files.exists(FilePath)){
				Files.createFile(FilePath);
			}
			Files.write(FilePath, FileData, StandardOpenOption.WRITE, 
					    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.SPARSE);
		}catch(IOException IOE){
			System.err.println("Unable to write to file.");
			return;
		}
	}
	
	/**
	 * <p>Reads {@code FileName}, which is located in {@code Directory}, into a byte array.
	 * If the file does not exist, it will return an empty array.
	 * 
	 * @param Directory The current directory to write to.
	 * @param FileName The file to create/append to.
	 * @return A byte array comprised of the file's data.
	 */
	public static byte[] readFile(@Nonnull String Directory, @Nonnull String FileName){
		byte[] FileData = null;
		Path FilePath = Paths.get(Directory, FileName);
		try{
			FileData = Files.readAllBytes(FilePath);
		}catch(IOException FNFE){
			System.err.println("Unable to read file.");
		}
		return FileData;
	}
}
