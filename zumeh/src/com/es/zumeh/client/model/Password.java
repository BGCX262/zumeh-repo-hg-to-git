/*
 *   Password.java
 *
 *   Copyright 2011 Heitor Meira - htrmeira AT gmail.com
 *   All rights reserved.
 *
 *   This file is part of Jaleco.
 *
 *   Jaleco is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Jaleco is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with Jaleco.  If not, see <http://www.gnu.org/licenses/>.
 *
 *   Contributors:
 *      Heitor Meira - htrmeira AT gmail.com
 */

package com.es.zumeh.client.model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Class to represent a generic user password.
 * 
 * @author htrmeira@gmail.com
 * 
 */
public class Password {

	private String password;

	/**
	 * Constructor of a new password, password should be set with setPassword.
	 */
	public Password() {
		password = new String();
	}

	/**
	 * Constructor of an existent password. We can not store password in plain
	 * text, so we store the MD5 hash, and retrieve him putting hash in this
	 * constructor.
	 * 
	 * @param encryptedPassword The MD5 hash of the password to represent.
	 * @throws Exception 
	 */
	public Password(String encryptedPassword) throws Exception {
		if (!checkText(encryptedPassword)) {
			throw new Exception(
					"Password can not be null or empty.");
		}

		this.password = encryptedPassword;
	}

	/**
	 * Set the password to the password in the {@param}, it will be hashed with
	 * MD5 and stored. Note that password can not be null or empty and it will
	 * note be stored in plain text.
	 * 
	 * @param password
	 *            The new password to represent, this password should not be a
	 *            hash.
	 * @throws Exception 
	 */
	public void setPassword(String password) throws Exception {
		if (!checkText(password)) {
			throw new Exception( 
					"Password can not be null or empty.");
		}

		try {
			this.password = encrypt(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Find a better way to treat this exception.
			e.printStackTrace();
		}
	}

	/**
	 * Check if the passwords matches, it will hash with MD5 the password in the
	 * {@param} and compare with hashed password stored.
	 * 
	 * @param password
	 *            The password to check with the password stored, this password
	 *            should be in plain text.
	 * @return true if they match, false otherwise.
	 */
	public boolean checkPasswords(String password) {
		if (!checkText(password)) {
			return false;
		}

		String passwordHash = null;

		try {
			passwordHash = encrypt(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO I think we should find a better way to treat this exception.
			e.printStackTrace();
		}

		return passwordHash.toLowerCase().equals(this.password);
	}

	/**
	 * Encrypt a password with MD5, will get an algorithm instance from
	 * {@link MessageDigest}
	 * 
	 * @param text
	 *            The text to encrypt, in this case the password.
	 * @return A MD5 hash of the text given.
	 * 
	 * @throws NoSuchAlgorithmException
	 *             If the given algorithm does not exists.
	 */
	private String encrypt(String text) throws NoSuchAlgorithmException {
		final int RADIX = 16;
		final String ALGORITHM = "MD5";

		MessageDigest md = MessageDigest.getInstance(ALGORITHM);
		BigInteger hash = new BigInteger(1, md.digest(text.getBytes()));
		String encriptedText = hash.toString(RADIX);

		if (encriptedText.length() % 2 != 0) {
			encriptedText = "0" + encriptedText;
		}
		return encriptedText;
	}

	/**
	 * Check if a string is null or empty.
	 * @param text
	 * @return
	 */
	private boolean checkText(String text) {
		if (text == null || text.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public String getPassword() {
		return password;
	}
	
}