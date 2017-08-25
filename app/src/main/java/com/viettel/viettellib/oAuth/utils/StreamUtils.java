package com.viettel.viettellib.oAuth.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Utils to deal with Streams.
 * 
 * @author Pablo Fernandez
 */
public class StreamUtils {
	/**
	 * Returns the stream contents as an UTF-8 encoded string
	 * 
	 * @param is
	 *            input stream
	 * @return string contents
	 */
	public static String getStreamContents(InputStream is) {
		Preconditions.checkNotNull(is, "Cannot get String from a null object");
		Reader in = null;
		StringBuilder out = new StringBuilder();
		try {
			final char[] buffer = new char[0x10000];
			in = new InputStreamReader(is, "UTF-8");
			int read;
			do {
				read = in.read(buffer, 0, buffer.length);
				if (read > 0) {
					out.append(buffer, 0, read);
				}
			} while (read >= 0);
//			in.close();
		} catch (IOException ioe) {
			throw new IllegalStateException(
					"Error while reading response body", ioe);
		}finally{
			if (in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return out.toString();
	}
	
}
