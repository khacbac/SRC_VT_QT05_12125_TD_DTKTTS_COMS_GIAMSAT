/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.viettellib.network.http;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.viettel.common.LogManager;
import com.viettel.constants.Constants;
import com.viettel.sync.SyncModel;
import com.viettel.utils.Mylog;
import com.viettel.viettellib.network.http.DataSupplier.Data;
import com.viettel.viettellib.oAuth.exceptions.OAuthException;
import com.viettel.viettellib.oAuth.model.OAuthRequest;
import com.viettel.viettellib.oAuth.model.Response;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

/**
 * DataSuplier interface
 *
 * @author: AnhND
 * @version: 1.0
 * @since: 1.0
 */
interface DataSupplier {
    public class Data {
        byte[] buffer;
        boolean isFinish;
        int length;
    }

    void getNextPart(Data data);

    void releaseData();

    int overallDataSize();

    void reset();
}

/**
 * request http
 *
 * @author: AnhND
 * @version: 1.0
 * @since: 1.0
 */
public class HttpAsyncTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "HttpAsyncTask";
    public static int CONNECT_TIMEOUT = 3 * 60 * 1000;
    private static int TIME_OUT = 3 * 60 * 1000;
    public static int TIME_OUT_PAYMENT = 8 * 60 * 1000;

    private HTTPRequest request;
    private HTTPResponse response;
    private boolean isSuccess;
    private static final String LOG_TAG = "HttpAsyncTask";
    private int readTimeout = TIME_OUT;
    private int connectTimeout = CONNECT_TIMEOUT;

    public HttpAsyncTask(HTTPRequest re) {
        this.request = re;
        this.readTimeout = TIME_OUT;
    }

    public HttpAsyncTask(HTTPRequest re, int timeout) {
        this.request = re;
        this.readTimeout = timeout;
    }

    @Override
    protected Void doInBackground(Void... params) {
        if (SyncModel.bStop) {
            Mylog.i(LOG_TAG, "Request stop!!!!!!!!!");
            LogManager.getInstance().writeFile(LOG_TAG + ": " + "Request stop!!!!!!!!!");
            return null;
        }
        if (request == null || request.isAlive() == false) {
            if (request == null) {
                Mylog.i(LOG_TAG, "Request null");
                LogManager.getInstance().writeFile(LOG_TAG + ": " + "Request null");
            } else {
                LogManager.getInstance().writeFile(LOG_TAG + ": " + "Request NOT alive");
            }
            return null;
        }

        int countRetry = 0;
        final int NUM_RETRY = 1;
        boolean isRetry = false;
        do {
            isRetry = false;
            countRetry++;
            HttpURLConnection connection = null;
            isSuccess = true;
            // bug sometime response code = -1
            disableConnectionReuseIfNecessary();
            try {
                response = new HTTPResponse(request);
                /* HieuNH add OAuthRequest */
                OAuthRequest oAuthRequest = OAuthRequestManager.getInstance().signRequest(request.getUrl(), request.getMethod());
                // OAuthRequest oAuthRequest = new OAuthRequest(Verb.POST,
                // request.getUrl());
                if (oAuthRequest != null) {
                    oAuthRequest.createConnection();
                    connection = oAuthRequest.getConnection();

                    oAuthRequest.setConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
                    oAuthRequest.setReadTimeout(readTimeout, TimeUnit.MILLISECONDS);
                    if (request.getContentType() != null) {
                        connection.setRequestProperty("Content-type", request.contentType);
                    }
                    if (HTTPRequest.POST.equals(request.getMethod())) {
                        Data data = new Data();
                        request.getNextPart(data);
                        oAuthRequest.addPayload(data.buffer);
                    }
                    if (HTTPClient.sessionID != null) {
                        oAuthRequest.addHeader("Cookie", HTTPClient.sessionID);
                        connection.setRequestProperty("Cookie", HTTPClient.sessionID);
                    }

                    connection.addRequestProperty("Cache-Control", "no-cache");
                    Response response1 = oAuthRequest.doSend();

                    // String res = response1.getBody();
                    response.setDataText(response1.getBody());
                    response.setCode(response1.getCode());
                    if (response.getCode() == OAuthRequestManager.TOKEN_INVALID
                            || response.getCode() == OAuthRequestManager.TIMED_OUT
                            || response.getCode() == OAuthRequestManager.SERVICE_UNAVAILABLE
                            || response.getCode() == OAuthRequestManager.GATE_WAY_TIME_OUT) {
                        if (response.getCode() == OAuthRequestManager.GATE_WAY_TIME_OUT
                                || response.getCode() == OAuthRequestManager.SERVICE_UNAVAILABLE) {
                            isSuccess = false;
                            response.setError(HTTPClient.ERR_TIME_OUT, Constants.MESSAGE_ERROR_NO_CONNECT_SERVER, "");
                        } else {
                            response.setCode(OAuthRequestManager.TIMED_OUT);
                        }
                        OAuthRequestManager.getInstance().accessToken = null;
                    }
                    if (response.getDataText() == null && response.getDataBinary() == null && request.isAlive()) {
                        isSuccess = false;
                        isRetry = true;
                        StringBuffer strBuffer = new StringBuffer();
                        strBuffer.append("ResponseCode: " + connection.getResponseCode());
                        strBuffer.append("/ResponseMsg: " + connection.getResponseMessage());
                        response.setError(HTTPClient.ERR_UNKNOWN, strBuffer.toString());
                    }
                } else {
                    /* Van request khi dang bi time out */
                    response.setCode(OAuthRequestManager.TIMED_OUT);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                isSuccess = false;
                response.setError(HTTPClient.ERR_INVALID_URL, Constants.MESSAGE_ERROR_NO_CONNECT_SERVER, e.getMessage() + "/" + e.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                isSuccess = false;
                response.setError(HTTPClient.ERR_NOT_FOUND, Constants.MESSAGE_ERROR_NO_CONNECT_SERVER, e.getMessage() + "/" + e.toString());
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
                isSuccess = false;
                response.setError(HTTPClient.ERR_TIME_OUT, Constants.MESSAGE_ERROR_NO_CONNECT_SERVER, e.getMessage() + "/" + e.toString());
            } catch (IOException e) {
                e.printStackTrace();
                isSuccess = false;
                isRetry = true;
                response.setError(HTTPClient.ERR_UNKNOWN, Constants.MESSAGE_ERROR_NO_CONNECTION, e.getMessage() + "/" + e.toString());
            } catch (OAuthException e) {
                e.printStackTrace();
                isSuccess = false;
                if (e.getCause() instanceof SocketTimeoutException
                        || e.getCause() instanceof SocketException) {
                    isRetry = false;
                    response.setError(HTTPClient.ERR_UNKNOWN, Constants.MESSAGE_ERROR_NO_CONNECT_SERVER, e.getMessage() + "/" + e.toString());
                } else {
                    isRetry = true;
                    response.setError(HTTPClient.ERR_UNKNOWN, Constants.MESSAGE_ERROR_NO_CONNECTION, e.getMessage() + "/" + e.toString());
                }

            } catch (Throwable e) {
                e.printStackTrace();
                isSuccess = false;
                isRetry = true;
                response.setError(HTTPClient.ERR_UNKNOWN, Constants.MESSAGE_ERROR_NO_CONNECTION, e.getMessage() + "/" + e.toString());
            } finally {
                if (connection != null) {
//                    Mylog.i(LOG_TAG, "disconnect");
                    connection.disconnect();
                }
            }
        } while (countRetry <= NUM_RETRY && isRetry);

        HTTPListenner listenner = null;
        if (response != null) {
            listenner = response.getObserver();
        }
        if (listenner != null) {
            if (isSuccess) {
                listenner.onReceiveData(response);
            } else {
                listenner.onReceiveError(response);
            }
        }
        return null;
    }

    /**
     * check connection reuse
     *
     * @author: BangHN
     * @return: void
     * @throws:
     */
    private void disableConnectionReuseIfNecessary() {
        // HTTP connection reuse which was buggy pre-froyo
        if (Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO) {
            System.setProperty("http.keepAlive", "false");
        }
        System.setProperty("networkaddress.cache.ttl", "0");
        System.setProperty("networkaddress.cache.negative.ttl", "0");
    }

    /**
     * send data
     *
     * @param outputStream
     * @param dataSupplier
     * @throws IOException
     * @author: AnhND
     * @return: void
     * @throws:
     */
    void writeData(OutputStream outputStream, DataSupplier dataSupplier)
            throws IOException {
        Data data = new Data();
        while (true) {
            dataSupplier.getNextPart(data);
            if (data.buffer != null && data.length > 0) {
                outputStream.write(data.buffer, 0, data.length);
                outputStream.flush();
                dataSupplier.releaseData();
            }
            if (data.isFinish) {
                break;
            }
        }
    }
}
