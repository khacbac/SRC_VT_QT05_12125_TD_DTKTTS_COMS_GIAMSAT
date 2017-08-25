/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.viettellib.network.rsa;

import com.viettel.common.GlobalInfo;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

/**
 *  Cac util su dung encode/decode rsa
 *  @author: DoanDM
 *  @version: 1.3
 *  @since: 1.2
 */
public class RSAUtil {
	
	Cipher cipher;
	PublicKey pubKey;
	PrivateKey priKey;
	private static RSAUtil instance;
	public static RSAUtil getInstance(){
		if(instance==null){
			instance = new RSAUtil(); 
		}
		return instance;
	}
	/**
	 * 
	 */
	RSAUtil() {
		// TODO Auto-generated constructor stub
		init();
	}
	/**
	 * 
	*  init util
	*  @author: DoanDM
	*  @return: void
	*  @throws:
	 */
	private void init(){
		
	}
	/**
	 * 
	*  Doc public key tu file trong assert
	*  @author: DoanDM
	*  @param keyFileName
	*  @return
	*  @throws IOException
	*  @return: PublicKey
	*  @throws:
	 */
	PublicKey readPubKeyFromFile(String keyFileName) throws IOException {
		PublicKey publicKey = null;
		try {
			InputStream in = GlobalInfo.getInstance().getAppContext().getAssets().open(keyFileName);
			PEMReader rd = new PEMReader(in);
		    byte[] encodedPublicKey = rd.getDerBytes();
		    in.close();
		    KeyFactory fact = KeyFactory.getInstance("RSA");
		    X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
		    		encodedPublicKey);
		    publicKey = fact.generatePublic(publicKeySpec);
		} catch (Exception e) {
			throw new RuntimeException("Spurious serialisation error", e);
		}
		return publicKey;
	}
	/**
	 * 
	*  Doc private key tu file trong assert
	*  @author: DoanDM
	*  @param keyFileName
	*  @return
	*  @throws IOException
	*  @return: PrivateKey
	*  @throws:
	 */
	PrivateKey readPriKeyFromFile(String keyFileName) throws IOException {
		PrivateKey privateKey = null;
		try {
			InputStream in = GlobalInfo.getInstance().getAppContext().getAssets().open(keyFileName);
			PEMReader rd = new PEMReader(in);
		    byte[] encodedPrivateKey = rd.getDerBytes();
		    in.close();
		    KeyFactory fact = KeyFactory.getInstance("RSA");
//			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
//					encodedPrivateKey);
		    KeySpec privateKeySpec = new PKCS1EncodedKeySpec(
					encodedPrivateKey).getKeySpec();
					privateKey = fact.generatePrivate(privateKeySpec);
		} catch (Exception e) {
			throw new RuntimeException("Spurious serialisation error", e);
		} 
		return privateKey;
	}
	/**
	 * 
	*  Check public key
	*  @author: DoanDM
	*  @return: void
	*  @throws:
	 */
	private void checkPubKey(){
		if(pubKey==null){
			try {
				pubKey = readPubKeyFromFile("public.key");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	*  Check private key
	*  @author: DoanDM
	*  @return: void
	*  @throws:
	 */
	private void checkPriKey(){
		if(priKey==null){
			try {
				priKey  = readPriKeyFromFile("private.key");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	*  Encrypt RSA
	*  @author: DoanDM
	*  @param str
	*  @return
	*  @throws Exception
	*  @return: String
	*  @throws:
	 */
	public String rsaEncrypt(String str) throws Exception{
		checkPubKey();
		byte[] data = str.getBytes("UTF8");
		cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		return blockCipher(data,Cipher.ENCRYPT_MODE);
		
	}
	/**
	 * 
	*  Decrypt RSA
	*  @author: DoanDM
	*  @param encrypted
	*  @return
	*  @throws Exception
	*  @return: String
	*  @throws:
	 */
	public String rsaDecrypt(String encrypted) throws Exception{
		checkPriKey();
		cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		byte[] bts =(encrypted.getBytes("UTF8"));
		return blockCipher(bts,Cipher.DECRYPT_MODE);
	}
	/**
	 * 
	*  Doc cac blog ma hoa/ giai ma
	*  @author: DoanDM
	*  @param bytes
	*  @param mode
	*  @return
	*  @throws IllegalBlockSizeException
	*  @throws BadPaddingException
	*  @return: String
	*  @throws:
	 */
	private String blockCipher(byte[] bytes, int mode) throws IllegalBlockSizeException, BadPaddingException{
		// string initialize 2 buffers.
		// scrambled will hold intermediate results
		byte[] scrambled = new byte[0];

		// toReturn will hold the total result
//		byte[] toReturn = new byte[0];
		String strReturn="";
		// if we encrypt we use 100 byte long blocks. Decryption requires 172 byte long blocks (because of RSA encrypt 64)
		int length = (mode == Cipher.ENCRYPT_MODE)? cipher.getBlockSize() : 172;

		// another buffer. this one will hold the bytes that have to be modified in this step
		byte[] buffer = new byte[length];

		for (int i=0; i< bytes.length; i++){

			// if we filled our buffer array we have our block ready for de- or encryption
			if ((i > 0) && (i % length == 0)){
				if(mode != Cipher.ENCRYPT_MODE){
					buffer = Base64.decodeBase64(buffer);
				}
				//execute the operation
				scrambled = cipher.doFinal(buffer);
				// add the result to our total result.
//				toReturn = append(toReturn, mode == Cipher.ENCRYPT_MODE?Base64.encodeBase64(scrambled):scrambled);
				strReturn+= new String(mode == Cipher.ENCRYPT_MODE?Base64.encodeBase64(scrambled):scrambled);
				// here we calculate the length of the next buffer required
				int newlength = length;

				// if newlength would be longer than remaining bytes in the bytes array we shorten it.
				if (i + length > bytes.length) {
					 newlength = bytes.length - i;
				}
				// clean the buffer array
				buffer = new byte[newlength];
			}
			// copy byte into our buffer.
			buffer[i%length] = bytes[i];
		}

		// this step is needed if we had a trailing buffer. should only happen when encrypting.
		// example: we encrypt 110 bytes. 100 bytes per run means we "forgot" the last 10 bytes. they are in the buffer array
		if(mode != Cipher.ENCRYPT_MODE){
			buffer = Base64.decodeBase64(buffer);
		}
		scrambled = cipher.doFinal(buffer);

		// final step before we can return the modified data.
//		toReturn = append(toReturn,mode == Cipher.ENCRYPT_MODE?Base64.encodeBase64(scrambled):scrambled);
		strReturn+= new String(mode == Cipher.ENCRYPT_MODE?Base64.encodeBase64(scrambled):scrambled);
		strReturn = strReturn.replace("\r", "");
		strReturn = strReturn.replace("\n", "");
		return strReturn;
	}
	
	
}
