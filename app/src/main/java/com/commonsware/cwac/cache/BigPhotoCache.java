/**
 * Copyright 2011 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.commonsware.cwac.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.commonsware.cwac.task.AsyncTaskEx;
import com.commonsware.cwac.thumbnail.ThumbnailBus;
import com.commonsware.cwac.thumbnail.ThumbnailMessage;
import com.viettel.utils.Mylog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

/**
 * Mo ta muc dich cua lop (interface)
 * 
 * @author: AnhND
 * @version: 1.0
 * @param <M>
 * @since: Jul 5, 2011
 */
public class BigPhotoCache extends
		SimpleWebImageCache<ThumbnailBus, ThumbnailMessage> {
	// so luong thuc thi download cung luc
	static final int NUM_DOWNLOADER = 2;
	// doi tuong de download hnh
	AsyncTaskEx[] arrDownloader = new AsyncTaskEx[NUM_DOWNLOADER];
	// index hien hanh
	int curDownloadIdx = -1;
	int widthDisplay;
	int heightDisplay;


	/**
	 * @param cacheRoot
	 * @param policy
	 * @param maxSize
	 * @param bus
	 */
	public BigPhotoCache(File cacheRoot,
			com.commonsware.cwac.cache.CacheBase.DiskCachePolicy policy,
			int maxSize, ThumbnailBus bus) {
		super(cacheRoot, policy, maxSize, bus);
		setReleaseBitmap(true);
		// TODO Auto-generated constructor stub

	}
	/**
	 * goi cac asyntask de download hinh anh , tai mot thoi diem chi co mot so luong 
	 * NUM_DOWNLOADER co the download
	 * 
	 * @author: PhucNT4
	 * @param raw
	 * @return: void
	 * @throws:
	 */
	public void notify(String key, ThumbnailMessage message) throws Exception {

		int status = getStatus(key);

		curDownloadIdx++;
		curDownloadIdx %= NUM_DOWNLOADER;
		if (arrDownloader[curDownloadIdx] != null) {
			// arrDownloader[curDownloadIdx].forceStop = true;
			arrDownloader[curDownloadIdx].cancel(true);
		}
		if (status == CACHE_NONE) {
			Mylog.i("BigPhotoCache", "CACHE_NONE");
			PhotoDownloader downloader = new PhotoDownloader();
			downloader.maxDimension = message.maxDimension;
			arrDownloader[curDownloadIdx] = downloader;
			downloader.execute(message, key, buildCachedImagePath(key));
		} else if (status == CACHE_DISK) {
			Mylog.i("BigPhotoCache", "CACHE_DISK");
			LoadBigImageTask downloader = new LoadBigImageTask();
			downloader.maxDimension = message.maxDimension;
			arrDownloader[curDownloadIdx] = downloader;
			downloader.execute(message, key, buildCachedImagePath(key));
		} else {
			Mylog.i("BigPhotoCache", "CACHE_MEM");
			bus.send(message);
		}
	}

	class PhotoDownloader extends AsyncTaskEx<Object, Void, Void> {
		public int maxDimension = 0;
		public boolean forceStop = false;

		@Override
		protected Void doInBackground(Object... params) {
			String url = params[1].toString();
			final File cache = (File) params[2];

			URLConnection connection = null;
			InputStream stream = null;
			Bitmap bmp = null;
			ThumbnailMessage message = new ThumbnailMessage(url);
			try {
				System.setProperty("http.keepAlive", "false");
				connection = new URL(url).openConnection();
				
				String contentEncoding = connection.getContentEncoding();
				if(contentEncoding != null && contentEncoding.equals("gzip")){
					stream = new GZIPInputStream(connection.getInputStream());
				}else{
					stream = connection.getInputStream();
				}
				contentEncoding = null;
				
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				int read;
				byte[] b = new byte[4096];

				while ((read = stream.read(b)) != -1) {
					out.write(b, 0, read);
				}

				if (isCancelled()) {
					message = (ThumbnailMessage) params[0];
					message.status = ThumbnailMessage.STATUS_CANCEL;
					bus.send(message);
				} else {
					out.flush();
					out.close();
					byte[] raw = out.toByteArray();

//					BitmapFactory.Options resample = new BitmapFactory.Options();
//					resample.inJustDecodeBounds = false;
//					resample.inSampleSize = computeSampleSize(raw,
//							widthDisplay, heightDisplay);
					BitmapFactory.Options bounds = new BitmapFactory.Options();
					bounds.inJustDecodeBounds = true;

					BitmapFactory.decodeByteArray(raw, 0, raw.length, bounds);

					if (bounds.outWidth == -1 || bounds.outHeight == -1) {
						throw new Exception("invalid image file");
					}
					Mylog.e("BigPhotoCache", "maxDimension  " +maxDimension);
					maxDimension = Math.min(maxDimension,
							Math.max(bounds.outWidth, bounds.outHeight));
					int sampleSize = Math
							.max(bounds.outWidth, bounds.outHeight)
							/ maxDimension;// luon >= 1
					BitmapFactory.Options resample = new BitmapFactory.Options();
					resample.inSampleSize = sampleSize;
					Mylog.e("BigPhotoCache", "bmpFactoryOptions.inSampleSize "
							+ resample.inSampleSize +"   "+ bounds.outWidth +"   " +bounds.outHeight);
					bmp = BitmapFactory.decodeByteArray(raw, 0, raw.length,
							resample);

					put(url, new BitmapDrawable(bmp));
					message = (ThumbnailMessage) params[0];
					if (message != null) {
						message.status = ThumbnailMessage.STATUS_SUCCEED;
						bus.send(message);
					}
					if (cache != null ) {
						FileOutputStream file = new FileOutputStream(cache);
						file.write(raw);
//						bmp.compress(Bitmap.CompressFormat.JPEG, 100,
//						 file);
						file.flush();
						file.close();
						checkCleanCache(cache);
					}

				}
			} catch (FileNotFoundException e) {
				boolean isCacheExist = true;
				try {
					FileOutputStream file = new FileOutputStream(cache);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					isCacheExist = false;
				}
				if (isCacheExist){
					message = (ThumbnailMessage) params[0];
					message.status = ThumbnailMessage.STATUS_NOT_FOUND;
					if (message != null) {
						bus.send(message);
	
					}
				}
				Mylog.e("BigPhotoCache", "FileNotFoundException", e);

			} catch (OutOfMemoryError e) {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException io) {
						// TODO Auto-generated catch block
					}
				}
				recycleAllBitmaps(false);
				System.gc();
				System.runFinalization();
				System.gc();
				 try {
				 Thread.sleep(1000);
				 } catch (InterruptedException e1) {
				 // TODO Auto-generated catch block
				 e1.printStackTrace();
				 }


			} catch (Exception e) {
				message = (ThumbnailMessage) params[0];
				message.status = ThumbnailMessage.STATUS_ERR;
				if (message != null) {
					bus.send(message);
				}
				if (bmp != null) {
					bmp.recycle();
					bmp = null;
				}
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException io) {
						// TODO Auto-generated catch block
					}
				}
				System.gc();
				Mylog.e("BigPhotoCache", "Socket connection time out", e);

			} catch (Throwable t) {
				message = (ThumbnailMessage) params[0];
				message.status = ThumbnailMessage.STATUS_ERR;
				if (message != null) {
					bus.send(message);
				}
				if (bmp != null) {
					bmp.recycle();
					bmp = null;
				}
				Mylog.e("BigPhotoCache", "Exception downloading image", t);
			} finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
				}
				// if (bmp != null) {
				// bmp.recycle();
				// bmp = null;
				// }

				System.gc();
			}
			return (null);
		}
	}

	public class LoadBigImageTask extends AsyncTaskEx<Object, Void, Void> {
		public int maxDimension = 0;
		@Override
		protected Void doInBackground(Object... params) {
			String url = params[1].toString();
			File cache = (File) params[2];
			ThumbnailMessage message;
			Bitmap bmp = null;
			try {
//				BitmapFactory.Options bounds = new BitmapFactory.Options();
//				bounds.inJustDecodeBounds = true;
//
//				bmp = BitmapFactory.decodeFile(cache.getAbsolutePath(),
//						bounds);
//
//				if (bounds.outWidth == -1 || bounds.outHeight == -1) {
//					throw new Exception("invalid image file");
//				}
//				KunKunLog.e("BigPhotoCache", "maxDimension  " +maxDimension);
//				maxDimension = Math.min(maxDimension,
//						Math.max(bounds.outWidth, bounds.outHeight));
//				int sampleSize = Math
//						.max(bounds.outWidth, bounds.outHeight)
//						/ maxDimension;// luon >= 1
//				BitmapFactory.Options resample = new BitmapFactory.Options();
//				resample.inSampleSize = sampleSize;
//				KunKunLog.e("BigPhotoCache", "bmpFactoryOptions.inSampleSize "
//						+ resample.inSampleSize +"   "+ bounds.outWidth +"   " +bounds.outHeight);
				if (isCancelled()) {
					message = (ThumbnailMessage) params[0];
					message.status = ThumbnailMessage.STATUS_CANCEL;
					bus.send(message);
				}
				bmp = BitmapFactory.decodeFile(cache.getAbsolutePath());
				if (isCancelled()) {
					message = (ThumbnailMessage) params[0];
					message.status = ThumbnailMessage.STATUS_CANCEL;
					bus.send(message);
				}
				put(url, new BitmapDrawable(bmp));

				message = (ThumbnailMessage) params[0];

				if (params[0] != null) {
					message.status = ThumbnailMessage.STATUS_SUCCEED;
					bus.send(message);
				}
			} catch (Throwable t) {
				// KunKunLog.e(TAG, "Exception downloading image", t);
				message = (ThumbnailMessage) params[0];
				message.status = ThumbnailMessage.STATUS_ERR;
				if (message != null) {
					bus.send(message);
				}
				if (bmp != null) {
					bmp.recycle();
					bmp = null;
				}
			}

			return (null);
		}
	}

	/**
	 * 
	 * cancel download image
	 * 
	 * @author: AnhND
	 * @return: void
	 * @throws:
	 */
	public void stopDownload() {
		if (arrDownloader != null) {
			for (int i = 0; i < arrDownloader.length; i++) {
				if (arrDownloader[i] != null) {
					arrDownloader[i].cancel(true);
					arrDownloader[i] = null;
				}
			}
		}
	}

	/**
	 * ghi du lieu xuong file
	 * 
	 * @author: PhucNT4
	 * @param raw
	 * @return: void
	 * @throws:
	 */
	public void writeToDisk(final File cache, final byte[] raw) {
		// TODO Auto-generated method stub

		new Runnable() {
			public void run() {
				if (cache != null) {
					FileOutputStream file;
					try {
						file = new FileOutputStream(cache);
						file.write(raw);
						file.flush();
						file.close();
						checkCleanCache(cache);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		};
	}

	/**
	 * Mo ta chuc nang cua ham
	 * 
	 * @author: TruongHN
	 * @param dw
	 * @param dh
	 * @return: void
	 * @throws:
	 */
	public void setWidthHeight(int dw, int dh) {
		// TODO Auto-generated method stub
		Mylog.e("BigPhotoCache", "widthDisplay " + dw + " heightDisplay "
				+ dh);
		widthDisplay = (int) dw / 2;
		heightDisplay = (int) dh / 2;
	}
}
