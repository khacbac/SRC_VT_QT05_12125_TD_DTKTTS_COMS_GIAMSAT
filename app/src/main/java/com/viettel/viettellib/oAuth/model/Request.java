package com.viettel.viettellib.oAuth.model;

import com.viettel.viettellib.oAuth.exceptions.OAuthException;
import com.viettel.viettellib.oAuth.utils.URLUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Represents an HTTP Request object
 * 
 * @author Pablo Fernandez
 */
class Request {
	private static final String CONTENT_LENGTH = "Content-Length";

	private String url;
	private Verb verb;
	private Map<String, String> querystringParams;
	private Map<String, String> bodyParams;
	private Map<String, String> headers;
	private String payload = null;
	private HttpURLConnection connection;
	private String charset;
	private byte[] bytePayload = null;
	private boolean connectionKeepAlive = false;
	private Long connectTimeout = null;
	private Long readTimeout = null;

	/**
	 * Creates a new Http Request
	 * 
	 * @param verb
	 *            Http Verb (GET, POST, etc)
	 * @param url
	 *            url with optional querystring parameters.
	 */
	public Request(Verb verb, String url) {
		this.verb = verb;
		this.url = url;
		this.querystringParams = new HashMap<String, String>();
		this.bodyParams = new HashMap<String, String>();
		this.headers = new HashMap<String, String>();
	}

	/**
	 * Execute the request and return a {@link Response}
	 * 
	 * @return Http Response
	 * @throws RuntimeException
	 *             if the connection cannot be created.
	 */
	public Response send() {
		try {
			createConnection();
			return doSend();
		} catch (IOException ioe) {
			throw new OAuthException("Problems while creating connection", ioe);
		}
	}

	public void createConnection() throws IOException {
		String effectiveUrl = URLUtils.appendParametersToQueryString(url,
				querystringParams);
		if (connection == null) {
			System.setProperty("http.keepAlive", connectionKeepAlive ? "true"
					: "false");
			URL url = new URL(effectiveUrl);
			if (url.getProtocol().toLowerCase().equals("https")) {

				// HostnameVerifier hostnameVerifier =
				// org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
				trustAllHosts();

				HttpsURLConnection urlHttpsConnection = (HttpsURLConnection) url
						.openConnection();

				// //Seteo la verificacion para que NO verifique nada!!
				urlHttpsConnection.setHostnameVerifier(DO_NOT_VERIFY);

				// Set verifier
				// HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

				// Asigno a la otra variable para usar simpre la mism
				connection = urlHttpsConnection;

			} else {

				// connection = (HttpURLConnection) url.openConnection();
				connection = (HttpURLConnection) new URL(effectiveUrl)
						.openConnection();
//				//cuongdk3 add
//				connection.connect();
//				//end cuongdk3
				System.out.print("PASS");

			}

		}
	}

	// always verify the host - dont check for certificate
	final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	};

	/**
	 * Trust every server - dont check for any certificate
	 */
	private static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Response doSend() throws IOException {
		connection.setRequestMethod(this.verb.name());
		if (connectTimeout != null) {
			connection.setConnectTimeout(connectTimeout.intValue());
		}
		if (readTimeout != null) {
			connection.setReadTimeout(readTimeout.intValue());
		}
		addHeaders(connection);
		if (verb.equals(Verb.PUT) || verb.equals(Verb.POST)) {
			addBody(connection, getByteBodyContents());
		}
		return new Response(connection);
	}

	void addHeaders(HttpURLConnection conn) {
		for (String key : headers.keySet())
			conn.setRequestProperty(key, headers.get(key));
	}

	void addBody(HttpURLConnection conn, byte[] content) throws IOException {
		conn.setRequestProperty(CONTENT_LENGTH, String.valueOf(content.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(content);
	}

	/**
	 * Add an HTTP Header to the Request
	 * 
	 * @param key
	 *            the header name
	 * @param value
	 *            the header value
	 */
	public void addHeader(String key, String value) {
		this.headers.put(key, value);
	}

	/**
	 * Add a body Parameter (for POST/ PUT Requests)
	 * 
	 * @param key
	 *            the parameter name
	 * @param value
	 *            the parameter value
	 */
	public void addBodyParameter(String key, String value) {
		this.bodyParams.put(key, value);
	}

	/**
	 * Add a QueryString parameter
	 * 
	 * @param key
	 *            the parameter name
	 * @param value
	 *            the parameter value
	 */
	public void addQuerystringParameter(String key, String value) {
		this.querystringParams.put(key, value);
	}

	/**
	 * Add body payload.
	 * 
	 * This method is used when the HTTP body is not a form-url-encoded string,
	 * but another thing. Like for example XML.
	 * 
	 * Note: The contents are not part of the OAuth signature
	 * 
	 * @param payload
	 *            the body of the request
	 */
	public void addPayload(String payload) {
		this.payload = payload;
	}

	/**
	 * Overloaded version for byte arrays
	 * 
	 * @param payload
	 */
	public void addPayload(byte[] payload) {
		this.bytePayload = payload;
	}

	/**
	 * Get a {@link Map} of the query string parameters.
	 * 
	 * @return a map containing the query string parameters
	 * @throws OAuthException
	 *             if the URL is not valid
	 */
	public Map<String, String> getQueryStringParams() {
		try {
			Map<String, String> params = new HashMap<String, String>();
			String queryString = new URL(url).getQuery();
			params.putAll(URLUtils.queryStringToMap(queryString));
			params.putAll(this.querystringParams);
			return params;
		} catch (MalformedURLException mue) {
			throw new OAuthException("Malformed URL", mue);
		}
	}

	/**
	 * Obtains a {@link Map} of the body parameters.
	 * 
	 * @return a map containing the body parameters.
	 */
	public Map<String, String> getBodyParams() {
		return bodyParams;
	}

	/**
	 * Obtains the URL of the HTTP Request.
	 * 
	 * @return the original URL of the HTTP Request
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Returns the URL without the port and the query string part.
	 * 
	 * @return the OAuth-sanitized URL
	 */
	public String getSanitizedUrl() {
		return url.replaceAll("\\?.*", "").replace("\\:\\d{4}", "");
	}

	/**
	 * Returns the body of the request
	 * 
	 * @return form encoded string
	 * @throws OAuthException
	 *             if the charset chosen is not supported
	 */
	public String getBodyContents() {
		try {
			return new String(getByteBodyContents(), getCharset());
		} catch (UnsupportedEncodingException uee) {
			throw new OAuthException("Unsupported Charset: " + charset, uee);
		}
	}

	byte[] getByteBodyContents() {
		if (bytePayload != null)
			return bytePayload;
		String body = (payload != null) ? payload : URLUtils
				.formURLEncodeMap(bodyParams);
		try {
			return body.getBytes(getCharset());
		} catch (UnsupportedEncodingException uee) {
			throw new OAuthException("Unsupported Charset: " + getCharset(),
					uee);
		}
	}

	/**
	 * Returns the HTTP Verb
	 * 
	 * @return the verb
	 */
	public Verb getVerb() {
		return verb;
	}

	/**
	 * Returns the connection headers as a {@link Map}
	 * 
	 * @return map of headers
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * Returns the connection charset. Defaults to {@link Charset}
	 * defaultCharset if not set
	 * 
	 * @return charset
	 */
	public String getCharset() {
		return charset == null ? Charset.defaultCharset().name() : charset;
	}

	/**
	 * Sets the connect timeout for the underlying {@link HttpURLConnection}
	 * 
	 * @param duration
	 *            duration of the timeout
	 * 
	 * @param unit
	 *            unit of time (milliseconds, seconds, etc)
	 */
	public void setConnectTimeout(int duration, TimeUnit unit) {
		this.connectTimeout = unit.toMillis(duration);
	}

	/**
	 * Sets the read timeout for the underlying {@link HttpURLConnection}
	 * 
	 * @param duration
	 *            duration of the timeout
	 * 
	 * @param unit
	 *            unit of time (milliseconds, seconds, etc)
	 */
	public void setReadTimeout(int duration, TimeUnit unit) {
		this.readTimeout = unit.toMillis(duration);
	}

	/**
	 * Set the charset of the body of the request
	 * 
	 * @param charsetName
	 *            name of the charset of the request
	 */
	public void setCharset(String charsetName) {
		this.charset = charsetName;
	}

	/**
	 * Sets wether the underlying Http Connection is persistent or not.
	 * 
	 * @see http
	 *      ://download.oracle.com/javase/1.5.0/docs/guide/net/http-keepalive
	 *      .html
	 * @param connectionKeepAlive
	 */
	public void setConnectionKeepAlive(boolean connectionKeepAlive) {
		this.connectionKeepAlive = connectionKeepAlive;
	}

	/*
	 * We need this in order to stub the connection object for test cases
	 */
	void setConnection(HttpURLConnection connection) {
		this.connection = connection;
	}

	@Override
	public String toString() {
		return String.format("@Request(%s %s)", getVerb(), getUrl());
	}

	public HttpURLConnection getConnection() {
		return this.connection;
	}
}
