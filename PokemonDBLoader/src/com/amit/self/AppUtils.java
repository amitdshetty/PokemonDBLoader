/**
 * 
 */
package com.amit.self;

import java.util.Base64;
import java.util.Base64.Decoder;

/**
 * @author amitshetty Class that contains methods used by the entire application
 *         or which perform technical functions not related to the functionality
 */
public class AppUtils {

	/**
	 * Method return a decoded Base64 encoded string passed as input
	 * 
	 * @param encodedPasswordString
	 * @return
	 */
	protected static String decodePassword(String encodedPasswordString) {
		String decodedPasswordString = null;
		try {
			if (encodedPasswordString == null) {
				return null;
			}
			Decoder decoder = Base64.getDecoder();
			byte[] bytes = decoder.decode(encodedPasswordString);
			decodedPasswordString = new String(bytes).trim();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decodedPasswordString;
	}
}
