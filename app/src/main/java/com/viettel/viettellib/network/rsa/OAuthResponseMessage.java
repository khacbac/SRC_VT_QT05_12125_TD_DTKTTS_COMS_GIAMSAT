/**
 * Copyright 2012 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.viettellib.network.rsa;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
/**
 * An HTTP response, encapsulated as an OAuthMessage.
 * 
 * @author John Kristian
 */
public class OAuthResponseMessage extends OAuthMessage
{
    OAuthResponseMessage(HttpResponseMessage http) throws IOException
    {
        super(http.method, http.url.toExternalForm(), null);
        this.http = http;
        getHeaders().addAll(http.headers);
        for (Map.Entry<String, String> header : http.headers) {
            if ("WWW-Authenticate".equalsIgnoreCase(header.getKey())) {
                for (OAuth.Parameter parameter : decodeAuthorization(header.getValue())) {
                    if (!"realm".equalsIgnoreCase(parameter.getKey())) {
                        addParameter(parameter);
                    }
                }
            }
        }
    }

    private final HttpResponseMessage http;

    public HttpResponseMessage getHttpResponse() {
        return http;
    }

    @Override
    public InputStream getBodyAsStream() throws IOException
    {
        return http.getBody();
    }

    @Override
    public String getBodyEncoding()
    {
        return http.getContentCharset();
    }

    @Override
    public void requireParameters(String... names) throws OAuthProblemException, IOException {
        try {
            super.requireParameters(names);
        } catch (OAuthProblemException problem) {
            problem.getParameters().putAll(getDump());
            throw problem;
        }
    }

    /**
     * Encapsulate this message as an exception. Read and close the body of this
     * message.
     */
    public OAuthProblemException toOAuthProblemException() throws IOException {
        OAuthProblemException problem = new OAuthProblemException();
        try {
            getParameters(); // decode the response body
        } catch (IOException ignored) {
        }
        problem.getParameters().putAll(getDump());
        try {
            InputStream b = getBodyAsStream();
            if (b != null) {
                b.close(); // release resources
            }
        } catch (IOException ignored) {
        }
        return problem;
    }

    @Override
    protected void completeParameters() throws IOException
    {
        super.completeParameters();
        String body = readBodyAsString();
        if (body != null) {
            addParameters(OAuth.decodeForm(body.trim()));
        }
    }

    @Override
    protected void dump(Map<String, Object> into) throws IOException
    {
        super.dump(into);
        http.dump(into);
    }

}
