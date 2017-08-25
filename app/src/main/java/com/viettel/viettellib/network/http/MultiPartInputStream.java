/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.viettellib.network.http;

import android.util.Log;

import com.commonsware.cwac.cache.MemoryUtils;
import com.viettel.common.LogManager;
import com.viettel.constants.Constants;
import com.viettel.utils.ImageUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * doc du lieu multipart (interface)
 * 
 * @author: AnhND
 * @version: 1.0
 * @since: Jun 29, 2011
 */
public class MultiPartInputStream extends InputStream {
	byte[] data = null;
	int currentPos = 0;

	public MultiPartInputStream(String dataText, String filePath,
			String fileName, String fileField, String fileType)
			throws Throwable {
		try {
			byte[] imageData = ImageUtil.getByteArrayOfImage(filePath,
					Constants.MAX_UPLOAD_IMAGE_WIDTH,
					Constants.MAX_UPLOAD_IMAGE_HEIGHT);
			data = NetworkUtil.getMultiPartByte(dataText, imageData, fileName,
					fileField, fileType);
		} catch (OutOfMemoryError ex) {
			//giai phong vung nho
			MemoryUtils.getInstance().getCacheCleanTask()
					.execute(MemoryUtils.getInstance().cacheRootPath);
			Log.e("file outMemory:", "file outMemory:" + filePath);
			
			LogManager.getInstance().writeFile("file outMemory:" + filePath);
			LogManager.getInstance().writeFile(ex.getMessage() + "/"+ex.toString());
			
			byte[] imageData = ImageUtil.getByteArrayOfImage(filePath,
					Constants.MAX_UPLOAD_IMAGE_WIDTH,
					Constants.MAX_UPLOAD_IMAGE_HEIGHT);
			data = NetworkUtil.getMultiPartByte(dataText, imageData, fileName,
					fileField, fileType);
			throw ex;
		}
	}	
	
	@Override
	public int read(byte[] b) throws IOException {
		// TODO Auto-generated method stub
		return read(b, 0, b.length);
	}	
	
	@Override
	public int read(byte[] buffer, int offset, int length) throws IOException {
		if (this.data == null) {
			return -1;
		}
		int n = Math.min(this.data.length - currentPos, length);
		for (int i = 0; i < n; i++) {
			buffer[offset + i] = data[currentPos + i];
		}
		currentPos += n;
		return (n == 0 ? -1 : n);
	}
	
	public int readFull(byte[] buffer){
		if (this.data == null) {
			return -1;
		}		
		for (int i = 0; i < data.length; i++) {
			buffer[i] = data[i];
		}		
		return buffer.length;
	}
	

	@Override
	public void close() throws IOException {
	}

	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}
}
