/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.viettellib.network.http;

/**
 *
 * @author VietHQ6
 * @since Jun 11, 2010
 * Copyright KunKun.Vn �2010 - All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL.
 */
public interface HTTPListenner {

    /**
     * this function use to client listen the response from server
     * @param Message from server
     */
    public void onReceiveData(HTTPMessage mes);

    /**
     * This function use to notify to highter class the error if it appear
     * @param error
     */
    public abstract void onReceiveError(HTTPResponse response);
}

