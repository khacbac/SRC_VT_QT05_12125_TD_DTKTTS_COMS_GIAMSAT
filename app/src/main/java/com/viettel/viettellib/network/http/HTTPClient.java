/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.viettellib.network.http;


/**
 *
 * @author Quocviet
 */
public class HTTPClient {
    //AnhND added
    public static final int ERR_SUCCESS = 0;
    public static final int ERR_UNKNOWN = 1;
    public static final int ERR_NO_CONNECTION = 2;
    public static final int ERR_OUT_OF_MEM = 3;
    public static final int ERR_NOT_FOUND = 4;
    public static final int ERR_NO_ACCECSS_INTERNET = 5;
    public static final int ERR_INVALID_URL = 6;
    public static final int ERR_TIME_OUT = 7;
    public static final int ERR_SYNC_CONNECTION = 8;
    //End AnhND
    
    public static String sessionID = null;
}
