/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.viettellib.network.http;

import java.io.IOException;

/**
 *  request multipart (interface)
 *  @author: AnhND
 *  @version: 1.0
 *  @since: Jun 29, 2011
 */
public class HTTPMultiPartRequest extends HTTPRequest{
	MultiPartInputStream multiPartStream;
	public void setMultipartStream(MultiPartInputStream stream) {
		this.multiPartStream = stream;
	}
	
	/* (non-Javadoc)
	 * @see com.viettel.kunkun.kunkunlibrary.network.http.HTTPRequest#getNextPart(com.viettel.kunkun.kunkunlibrary.network.http.DataSupplier.Data)
	 */
	@Override
	public void getNextPart(Data data) {
		// TODO Auto-generated method stub
		try {
//			if (data.buffer == null) {
//				data.buffer = new byte[1024];
//			}
//			data.length = multiPartStream.read(data.buffer, 0, data.buffer.length);
			data.buffer = new byte[multiPartStream.data.length];
			multiPartStream.readFull(data.buffer);
			data.isFinish = (data.length != -1);
			if (data.isFinish) {
				multiPartStream.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
