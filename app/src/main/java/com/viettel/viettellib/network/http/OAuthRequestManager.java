/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.viettellib.network.http;

import com.viettel.viettellib.oAuth.builder.ServiceBuilder;
import com.viettel.viettellib.oAuth.builder.api.VT_Api;
import com.viettel.viettellib.oAuth.exceptions.OAuthException;
import com.viettel.viettellib.oAuth.model.OAuthRequest;
import com.viettel.viettellib.oAuth.model.Token;
import com.viettel.viettellib.oAuth.model.Verb;
import com.viettel.viettellib.oAuth.model.Verifier;
import com.viettel.viettellib.oAuth.oauth.OAuthService;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 *  OAuth request
 *  @author: HieuNH
 *  @version: 1.1
 *  @since: 1.0
 */
public class OAuthRequestManager{	
	public static final int TOKEN_INVALID = 401;
	public static final int TIMED_OUT = 500;
	public static final int SERVICE_UNAVAILABLE = 503;
	public static final int GATE_WAY_TIME_OUT = 504;
	private final String OAUTH_KEY = "kunkun";
	private final String OAUTH_SECRET = "3bf47226c60e20ce280eadc22c9c7f5de0cbc6c2";
	static OAuthRequestManager instance;
	OAuthService service;
	public Token accessToken = null;
	
	public OAuthRequestManager(){
		initOAuth();
	}
	
	public static OAuthRequestManager getInstance(){			
		if(instance == null || instance.accessToken == null){			
			instance = new OAuthRequestManager();		
		}		
		return instance;
	}
	
	public boolean reInit(){
		try{
			instance = new OAuthRequestManager();
			if(instance == null){
				return false;
			}
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}		
		return true;
	}
	
	private void initOAuth() {		
		service = new ServiceBuilder().provider(VT_Api.class)
				.apiKey(OAUTH_KEY).apiSecret(OAUTH_SECRET).build();
		Token requestToken = service.getRequestToken();
		String url = service.getAuthorizationUrl(requestToken);
		String requestTokenVerifier = null;
		HttpClient httpClient = new HttpClient();
		PostMethod authorizeMethod = new PostMethod(url);
		try {
			httpClient.executeMethod(authorizeMethod);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (authorizeMethod.getResponseHeader("Location") != null){
			String redirectURL = authorizeMethod.getResponseHeader("Location").getValue();
			if (redirectURL != null
					&& redirectURL.indexOf("oauth_verifier") > -1) {
				requestTokenVerifier = redirectURL.substring(redirectURL
						.indexOf("oauth_verifier") + 15);
			}
		}
		Verifier v = new Verifier(requestTokenVerifier);
		accessToken = service.getAccessToken(requestToken, v);
		
    }
	
	public OAuthRequest signRequest(String requestUrl, String method){
		if(accessToken == null){
			return null;
		}else{
			OAuthRequest request;
			if(HTTPRequest.POST.equals(method)){
				request = new OAuthRequest(Verb.POST, requestUrl);
			}else{
				request = new OAuthRequest(Verb.GET, requestUrl);
			}		
			service.signRequest(accessToken, request);
			return request;
		}
	}	
}
