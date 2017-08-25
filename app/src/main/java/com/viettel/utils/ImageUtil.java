package com.viettel.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.widget.ImageView;

import com.commonsware.cwac.cache.SimpleWebImageCache;
import com.commonsware.cwac.thumbnail.ThumbnailBus;
import com.commonsware.cwac.thumbnail.ThumbnailMessage;
import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.imaging.jpeg.JpegProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifDirectory;
import com.viettel.common.GlobalInfo;
import com.viettel.constants.Constants;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class ImageUtil {

	/**
	 * get bitmap khi thuc hien chon anh tu thu muc picasa cua gallery
	 * 
	 * @author: BangHN
	 * @param image
	 * @throws IOException
	 * @return: Bitmap
	 * @throws:
	 */
	public static Bitmap getBitmapFromPicasaUri(Uri image) throws IOException {
		Bitmap bitmap = null;
		InputStream is = null;
		try {
			is = GlobalInfo.getInstance().getAppContext().getContentResolver()
					.openInputStream(image);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int read;
			byte[] b = new byte[4096];
			while ((read = is.read(b)) != -1) {
				out.write(b, 0, read);
			}
			byte[] raw = out.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(raw, 0, raw.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return bitmap;
	}

	/**
	 * tao doi tuong Bitmap tu file anh
	 * 
	 * @author: AnhND
	 * @param path
	 * @param width
	 * @param height
	 * @return
	 * @return: Bitmap
	 * @throws Exception
	 * @throws:
	 */
	public static Bitmap readImageFromSDCard(String path, int maxDimension)
			throws Exception {
		int degrees = 0;

		Bitmap retBitmap = null;
		BitmapFactory.Options bounds = new BitmapFactory.Options();
		bounds.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, bounds);
		if (bounds.outWidth == -1 || bounds.outHeight == -1) {
			throw new Exception("invalid image file");
		}
		if (maxDimension == -1) {
			Math.max(bounds.outWidth, bounds.outHeight);
		}
		maxDimension = Math.min(maxDimension,
				Math.max(bounds.outWidth, bounds.outHeight));
		int sampleSize = Math.max(bounds.outWidth, bounds.outHeight)
				/ maxDimension;// luon >= 1
		BitmapFactory.Options resample = new BitmapFactory.Options();
		resample.inSampleSize = sampleSize;
		resample.inPurgeable = true;
		Bitmap bitmap = BitmapFactory.decodeFile(path, resample);
		if (bitmap == null) {
			throw new Exception("decode image failed");
		}
		retBitmap = bitmap;

		degrees = degreesRotateImage(path);
		if (Math.max(bitmap.getWidth(), bitmap.getHeight()) > maxDimension) {
			retBitmap = ImageUtil.resizeImageWithOrignal(bitmap, maxDimension,
					degrees);
			bitmap.recycle();
			bitmap = null;
		}
		return retBitmap;
	}

	public static Bitmap readImageFromSDCard(String path) {
		Bitmap image = null;
		FileInputStream iStream = null;
		try {
			System.gc();
			iStream = new FileInputStream(path);
			BufferedInputStream bis = null;
			bis = new BufferedInputStream(iStream);
			image = BitmapFactory.decodeStream(bis);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (iStream != null) {
				try {
					iStream.close();
				} catch (IOException ex) {
					// TODO: handle exception
				}
			}
		}
		return image;
	}

	public static Bitmap readImageFromRes(Resources rs, int FileID) {
		Bitmap mBitmap = BitmapFactory.decodeResource(rs, FileID);
		return mBitmap;
	}

	public static Bitmap readImageFromURL(String image_URL) {
		Bitmap bmp = null;
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize = 1;
		bmOptions.inPurgeable = true;
		bmp = loadImage(image_URL, bmOptions);
		return bmp;
	}

	/**
	 * 
	 * dung cho cac man hinh can lay mot hinh don
	 * 
	 * @author: PhucNT
	 * @param url
	 * @param activity
	 * @param ImageView
	 * @return
	 * @throws:
	 */
	public static void getImageFromURL(final String image_URL,
			final Activity activity, final ImageView view) {
		ThumbnailBus bus = new ThumbnailBus();
		final SimpleWebImageCache<ThumbnailBus, ThumbnailMessage> cache = new SimpleWebImageCache<ThumbnailBus, ThumbnailMessage>(
				null, null, 101, bus);
		cache.setRetryLoadImage(true);
		ThumbnailBus.Receiver<ThumbnailMessage> onCache = new ThumbnailBus.Receiver<ThumbnailMessage>() {
			public void onReceive(final ThumbnailMessage message) {
				final ThumbnailBus.Receiver<ThumbnailMessage> tmp = this;
				final ImageView image = message.getImageView();
				activity.runOnUiThread(new Runnable() {
					public void run() {
						if (image.getTag() != null
								&& image.getTag().toString()
										.equals(message.getUrl())) {

							BitmapDrawable bd = (BitmapDrawable) cache
									.get(message.getUrl());
							// Bitmap bdRounded = ImageUtil.getCornerBitmap(bd
							// .getBitmap());
							// if (bdRounded != null) {
							// image.setImageBitmap(bdRounded);
							// } else {
							if (bd != null) {
								image.setImageDrawable(bd);
								// }
							}
							bd = null;
						}
						cache.getBus().unregister(tmp);
						cache.getSoftHashMap().clear();
					}
				});
			}
		};
		cache.getBus().register(activity.toString(), onCache);
		ThumbnailMessage msg = cache.getBus()
				.createMessage(activity.toString());
		if (!StringUtil.isNullOrEmpty(image_URL)) {
			view.setTag(image_URL);
		}
		msg.setImageView(view);
		msg.setUrl(view.getTag().toString());
		try {
			cache.notify(msg.getUrl(), msg);
		} catch (Throwable t) {

		}

	}

	/**
	 * 
	 * dung cho nhung man hinh can giu lai cai cache de truy xuat nhanh hon
	 * trong nhung man hinh ma co nhung cai hinh dung chung.Vi du man hinh ca
	 * nhan ca 3 view tab deu su dung chung name card, avarta
	 * 
	 * @author: PhucNT
	 * @param url
	 * @param SimpleWebImageCache
	 *            ,Activity cache
	 * @param ImageView
	 * @return
	 * @throws:
	 */
	public static void getImageFromUrlUseCache(final String image_URL,
			final SimpleWebImageCache<ThumbnailBus, ThumbnailMessage> cache,
			final Activity act, ImageView view) {

		ThumbnailBus.Receiver<ThumbnailMessage> onCache = new ThumbnailBus.Receiver<ThumbnailMessage>() {
			@SuppressWarnings("unused")
			public synchronized void onReceive(final ThumbnailMessage message) {
				final ThumbnailBus.Receiver<ThumbnailMessage> tmp = this;
				final ImageView image = message.getImageView();
				Mylog.e("PhucNT4", "on Receive " + message.getUrl());
				act.runOnUiThread(new Runnable() {
					public void run() {
						if (image.getTag() != null
								&& image.getTag().toString()
										.equals(message.getUrl())) {

							BitmapDrawable bd = (BitmapDrawable) cache
									.get(message.getUrl());
							if (bd != null) {
								image.setImageDrawable(bd);
							}
							bd = null;
						}

					}
				});
			}
		};
		Mylog.e("PhucNT4", "on getBus().register " + image_URL.toString());
		cache.getBus().register(image_URL.toString(), onCache);
		ThumbnailMessage msg = cache.getBus().createMessage(
				image_URL.toString());
		if (!StringUtil.isNullOrEmpty(image_URL)) {
			view.setTag(image_URL);
		}
		msg.setImageView(view);
		// msg.maxDimension = Math.max(view.getWidth(), view.getHeight());
		msg.setUrl(view.getTag().toString());
		try {
			cache.notify(msg.getUrl(), msg);
		} catch (Throwable t) {

		}

	}

	/**
	 * 
	 * load image from url, not cache
	 * 
	 * @author: DoanDM
	 * @param url
	 * @return
	 * @return: Drawable
	 * @throws:
	 */
	public static void LoadImageFromWebOperations(Activity act,
			final String url, final ImageView iv) {
		act.runOnUiThread(new Runnable() {
			public void run() {
				try {
					InputStream is = (InputStream) new URL(url).getContent();
					Drawable d = Drawable.createFromStream(is, "src name");
					iv.setImageDrawable(d);
				} catch (Exception e) {
					System.out.println("Exc=" + e);
				}
			}
		});

	}

	/**
	 * lay hinh anh tu url nhung khong su dung corner hinh anh
	 * 
	 * @author: HaiTC
	 * @param image_URL
	 * @param view
	 * @return: void
	 * @throws:
	 */
	public static void getImageFromURLDoNotUseCorner(final String image_URL,
			final ImageView view) {
		final Handler handle = new Handler(new Callback() {
			public boolean handleMessage(Message msg) {
				try {
					if (view != null && msg.obj != null) {
						view.setImageBitmap((Bitmap) msg.obj);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				return false;
			}
		});
		new Thread(new Runnable() {
			public void run() {
				Bitmap bmp = null;
				BitmapFactory.Options bmOptions = new BitmapFactory.Options();
				bmOptions.inSampleSize = 1;
				bmOptions.inPurgeable = true;
				if (image_URL != null && image_URL != "") {
					bmp = loadImage(image_URL, bmOptions);
					Message msg = handle.obtainMessage(0, bmp);
					msg.sendToTarget();
				}
			}
		}).start();
	}

	public static Bitmap loadImage(String URL, BitmapFactory.Options options) {
		Bitmap bitmap = null;
		InputStream in = null;

		try {
			in = OpenHttpConnection(URL);
			if (in != null) {
				bitmap = BitmapFactory.decodeStream(in, null, options);
				in.close();
			} else
				return null;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	public static InputStream OpenHttpConnection(final String strURL)
			throws IOException {
		InputStream inputStream = null;
		URL url = new URL(strURL);
		int countRetry = 0;
		final int NUM_RETRY = 5;
		boolean isRetry = false;
		do {
			isRetry = false;
			countRetry++;
			try {
				URLConnection conn = url.openConnection();
				HttpURLConnection httpConn = (HttpURLConnection) conn;
				httpConn.setRequestMethod("GET");
				httpConn.connect();
				if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {

					String contentEncoding = conn.getContentEncoding();
					if (contentEncoding != null
							&& contentEncoding.equals("gzip")) {
						inputStream = new GZIPInputStream(
								httpConn.getInputStream());
					} else {
						inputStream = httpConn.getInputStream();
					}
					contentEncoding = null;

					// inputStream = httpConn.getInputStream();//BangHN thay
					// bang code kiem tra gzip o tren
				}
				if (inputStream == null) {
					isRetry = true;
					Thread.sleep(1000);
				}
			} catch (FileNotFoundException e) {
				isRetry = true;
			} catch (IOException e) {
				isRetry = true;
			} catch (Exception e) {
				isRetry = true;
			}
		} while (countRetry <= NUM_RETRY && isRetry);
		return inputStream;
	}

	public static boolean writeFileImageToPhone(Bitmap bm, String filename,
			String fileType) {
		boolean isWrite = false;
		String extStorageDirectory = Environment.getExternalStorageDirectory()
				.toString();

		Mylog.i("ImageUtil", "extStorageDirectory: " + extStorageDirectory);
		OutputStream outStream = null;
		File file = new File(extStorageDirectory, filename + "." + fileType);
		try {
			outStream = new FileOutputStream(file);
			if (fileType.endsWith("PNG")) {
				bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
			} else if (fileType.endsWith("JPEG")) {
				bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			}

			try {
				outStream.flush();
				outStream.close();
				isWrite = true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isWrite;
	}

	/*
	 * method convert bitmap to byte[]
	 */
	public static byte[] convertBitmapToByteArray(Bitmap bm) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] data = baos.toByteArray();
		return data;
	}

	public static Bitmap resizeImage(Bitmap orignal, int new_width,
			int new_height) {
		// load the origial BitMap
		int width = orignal.getWidth();
		int height = orignal.getHeight();

		float scaleWidth = ((float) new_width) / width;
		float scaleHeight = ((float) new_height) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(orignal, 0, 0, width,
				height, matrix, true);
		return resizedBitmap;
	}

	public static Bitmap rotateBitmap(Bitmap orignal, int degrees) {
		int width = orignal.getWidth();
		int height = orignal.getHeight();
		Matrix matrix = new Matrix();
		// matrix.postScale(width, height);
		if (degrees != 0) {
			matrix.postRotate(degrees);
		}
		Bitmap rotateBitmap = Bitmap.createBitmap(orignal, 0, 0, width, height,
				matrix, true);
		orignal.recycle();
		orignal = null;
		return rotateBitmap;
	}

	public static Bitmap resizeImageWithOrignal(Bitmap orignal,
			int maxDimension, int degrees) {
		System.gc();
		int width = orignal.getWidth();
		int height = orignal.getHeight();

		if (width > maxDimension || height > maxDimension) {
			int new_width;
			int new_height;
			float ratio = (float) width / height;
			if (ratio > 1.0f) {
				new_width = maxDimension;
				new_height = (int) ((float) new_width / ratio);
			} else {
				new_height = maxDimension;
				new_width = (int) ((float) new_height * ratio);
			}
			float scaleWidth = ((float) new_width) / width;
			float scaleHeight = ((float) new_height) / height;
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			if (degrees != 0) {
				matrix.postRotate(degrees);
			}
			Bitmap resizedBitmap = Bitmap.createBitmap(orignal, 0, 0, width,
					height, matrix, true);
			return resizedBitmap;
		}
		return orignal;
	}

	public static Bitmap resizeImageWithOrignal(Bitmap orignal, int maxDimension) {
		// load the origial BitMap
		int width = orignal.getWidth();
		int height = orignal.getHeight();

		if (width > maxDimension || height > maxDimension) {
			int new_width;
			int new_height;
			float ratio = (float) width / height;
			if (ratio > 1.0f) {
				new_width = maxDimension;
				new_height = (int) ((float) new_width / ratio);
			} else {
				new_height = maxDimension;
				new_width = (int) ((float) new_height * ratio);
			}
			float scaleWidth = ((float) new_width) / width;
			float scaleHeight = ((float) new_height) / height;
			Matrix matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			Bitmap resizedBitmap = Bitmap.createBitmap(orignal, 0, 0, width,
					height, matrix, true);
			return resizedBitmap;
		}
		return orignal;
	}

	public static Bitmap getCornerBitmap(Bitmap bitmap) {

		if (bitmap != null) {
			Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
					bitmap.getHeight(), Config.ARGB_8888);
			Canvas canvas = new Canvas(output);

			final int color = 0xff424242;
			final Paint paint = new Paint();
			final Rect rect1 = new Rect(0, 0, bitmap.getWidth(),
					bitmap.getHeight());
			// final Rect rect1 = new Rect(2, 2, bitmap.getWidth() + 2, bitmap
			// .getHeight() + 2);
			final Rect rect = new Rect(0, 0, bitmap.getWidth(),
					bitmap.getHeight());
			// final Rect rect2 = new Rect(0, 0, bitmap.getWidth() + 4, bitmap
			// .getHeight() + 4);
			// final RectF rectF = new RectF(rect2);
			// final float roundPx = 0;

			paint.setAntiAlias(true);
			canvas.drawARGB(0, 0, 0, 0);
			paint.setColor(color);
			// paint.setStrokeWidth(1);
			canvas.drawColor(Color.WHITE);
			// paint.setStyle(Paint.Style.STROKE);
			// canvas.drawRect(rectF, paint);

			paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
			canvas.drawBitmap(bitmap, rect, rect1, paint);

			return output;
		} else {
			return bitmap;
		}
	}

	public static int getColor(int id) {
		return GlobalInfo.getInstance().getAppContext().getResources()
				.getColor(id);
	}

	public static byte[] getPostImageByteArray(String f) {
		try {
			System.gc();
			BitmapFactory.Options bounds = new BitmapFactory.Options();
			bounds.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(f, bounds);
			if (bounds.outWidth == -1) {
				return null;
			}
			int width = bounds.outWidth;
			int height = bounds.outHeight;
			boolean withinBounds = width <= Constants.MAX_IMAGE_WIDTH_HEIGHT
					&& height <= Constants.MAX_IMAGE_WIDTH_HEIGHT;
			if (withinBounds) {
				Bitmap bitmap = BitmapFactory.decodeFile(f);
				if (bitmap == null) {
					return null;
				}
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
				bitmap.recycle();
				bitmap = null;
				byte[] buff = baos.toByteArray();
				return buff;
			} else {
				int sampleSize = width > height ? width
						/ Constants.MAX_IMAGE_WIDTH_HEIGHT : height
						/ Constants.MAX_IMAGE_WIDTH_HEIGHT;
				BitmapFactory.Options resample = new BitmapFactory.Options();
				resample.inSampleSize = sampleSize;
				resample.inPurgeable = true;
				Bitmap bitmap = BitmapFactory.decodeFile(f, resample);
				if (bitmap == null) {
					return null;
				}
				int degrees = degreesRotateImage(f);
				Bitmap thumbnail = ImageUtil.resizeImageWithOrignal(bitmap,
						Constants.MAX_IMAGE_WIDTH_HEIGHT, degrees);
				bitmap.recycle();
				bitmap = null;
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm
				thumbnail.recycle();
				thumbnail = null;
				byte[] buff = baos.toByteArray();
				baos = null;
				bounds = null;
				return buff;
			}
		} catch (OutOfMemoryError error) {
			return null;
		} finally {
			System.gc();
		}
	}

	/**
	 * kiem tra anh can rotate bao nhieu do de quay ve anh ban dau
	 * 
	 * @author: BangHN
	 * @param filePath
	 *            - duong dan anh
	 * @throws Throwable
	 * @return: int
	 * @throws:
	 */
	public static int degreesRotateImage(String filePath) {
		int degrees = 0;
		Metadata metadata;
		try {
			metadata = JpegMetadataReader.readMetadata(new File(filePath));
			Directory exifDirectory = metadata
					.getDirectory(ExifDirectory.class);
			if (exifDirectory.containsTag(ExifDirectory.TAG_ORIENTATION)) {
				int rotated = exifDirectory
						.getInt(ExifDirectory.TAG_ORIENTATION);

				if (rotated == 6) {
					degrees = 90;
				} else if (rotated == 3) {
					degrees = 180;
				} else if (rotated == 8) {
					degrees = -90;
				}
			}
		} catch (JpegProcessingException e) {
			e.printStackTrace();
		} catch (MetadataException e) {
			e.printStackTrace();
		}

		return degrees;
	}

	/*
	 * BangHN Tao anh thumbnail hien thi tren ugc
	 */
	public static Bitmap createBitmapWithScaleWH(Bitmap input, int maxW,
			int maxH) {
		int srcWidth = input.getWidth();
		int srcHeight = input.getHeight();

		int newWidth = srcWidth;
		int newHeight = srcHeight;
		if (srcWidth > maxW || srcHeight > maxH) {
			float ratio = (float) srcWidth / srcHeight;
			if (ratio > 1.0f) {
				newWidth = maxW;
				newHeight = (int) ((float) newWidth / ratio);
			} else {
				newHeight = maxW;
				newWidth = (int) ((float) newHeight * ratio);
			}
		}
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(input, newWidth,
				newHeight, false);
		return scaledBitmap;
	}

	/**
	 * Decode anh bitmap tu picasa --> mang byte
	 * 
	 * @author: BangHN
	 * @param uriImage
	 * @return
	 * @throws Throwable
	 * @return: byte[]
	 * @throws:
	 */
	public static byte[] getByteArrayBitmapFromPicasa(Uri uriImage)
			throws Throwable {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] retBuffer = null;
		Bitmap bitmap = ImageUtil.getBitmapFromPicasaUri(uriImage);
		if (bitmap != null) {
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90,
					byteArrayOutputStream);
			bitmap.recycle();
			bitmap = null;
			retBuffer = byteArrayOutputStream.toByteArray();
			byteArrayOutputStream = null;
		}
		System.gc();
		return retBuffer;
	}

	/**
	 * 
	 * doc data hinh ho tro scale neu anh lon
	 * 
	 * @author: HaiTC
	 * @param fileName
	 * @param maxWidth
	 * @param maxHeight
	 * @return
	 * @throws Throwable
	 * @return: byte[]
	 * @throws:
	 */
	public static byte[] getByteArrayOfImage(String fileName, int maxWidth,
			int maxHeight) throws Throwable {
		int index = -1;
		index = fileName.indexOf(":/");
		if (index != -1) {
			fileName = fileName.substring(index + 1);
		}

		byte[] retBuffer = null;
		BitmapFactory.Options bounds = new BitmapFactory.Options();
		bounds.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(fileName, bounds);
		if (bounds.outWidth == -1 || bounds.outHeight == -1) {
			return null;
		}

		int width = bounds.outWidth;
		int height = bounds.outHeight;

		if (width < height) {// kiem tra anh duoc quay
			int tmp = width;
			width = height;
			height = tmp;
		}

		boolean withinBounds = (width <= maxWidth) && (height <= maxHeight);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		final int BUFFER_SIZE = 4096;
		byte[] buffer = new byte[BUFFER_SIZE];
		// neu anh trong pham vi cho phep thi decode luon
		if (withinBounds) {
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(fileName);
				int length = fileInputStream.read(buffer);
				while (length != -1) {
					byteArrayOutputStream.write(buffer, 0, length);
					length = fileInputStream.read(buffer);
				}
				// Gan ket qua tra ve
				retBuffer = byteArrayOutputStream.toByteArray();
			} catch (OutOfMemoryError e) {
				System.gc();
				throw e;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Loi gui file: "+fileName);
				throw e;
			} finally {
				if (fileInputStream != null) {
					fileInputStream.close();
					fileInputStream = null;
				}
			}
		} else {
			// nguoc lai resize anh
			double r1 = (double) width / (double) height;
			double r2 = (double) maxWidth / (double) maxHeight;
			int sampleSize = 1;
			if (r1 >= r2) {
				sampleSize = width / maxWidth;
			} else {
				sampleSize = height / maxHeight;
			}

			BitmapFactory.Options resample = new BitmapFactory.Options();
//			resample.inSampleSize = sampleSize;
			resample.inSampleSize = sampleSize;
			resample.inPurgeable = true;
			//new
//			resample.inInputShareable = true;
//			resample.inDither = false;
//			resample.inJustDecodeBounds = true;
			Bitmap bitmap = null;
//			System.gc();
			try {
				bitmap = BitmapFactory.decodeFile(fileName, resample);
			} catch (OutOfMemoryError e) {
				bitmap.recycle();
				System.gc();
				throw e;
			}

			if (bitmap != null) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
						byteArrayOutputStream);
				bitmap.recycle();
				bitmap = null;
				retBuffer = byteArrayOutputStream.toByteArray();
				byteArrayOutputStream = null;
			}
			
//			bitmap.recycle();
		}
		
		System.gc();
		return retBuffer;
	}

	/**
	 * return array byte from a bitmap input
	 * 
	 * @author: BangHN
	 * @param imgPath
	 *            : duong dan anh -> get thuoc tinh anh
	 * @param bmInput
	 *            : anh bitmap truyen vao
	 * @param maxWidth
	 *            : chieu rong toi da anh mong muon
	 * @param maxHeight
	 *            : chieu cao anh toi da mong muon
	 * @return: byte array
	 */
	public static byte[] getByteArrayOfBitmap(Bitmap bmInput, int maxWidth,
			int maxHeight) throws Throwable {
		byte[] retBuffer = null;

		int bmW = bmInput.getWidth();
		int bmH = bmInput.getHeight();

		if (bmW <= 0 || bmH <= 0) {
			return null;
		}

		int width = bmW;
		int height = bmH;

		if (width < height) {// kiem tra anh duoc quay
			int tmp = width;
			width = height;
			height = tmp;
		}

		boolean withinBounds = (width <= maxWidth) && (height <= maxHeight);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		if (withinBounds) {
			try {
				bmInput.compress(Bitmap.CompressFormat.JPEG, 90,
						byteArrayOutputStream);
				// Gan ket qua tra ve
				retBuffer = byteArrayOutputStream.toByteArray();
			} catch (OutOfMemoryError e) {
				System.gc();
				throw e;
			} catch (Exception e) {
				throw e;
			} finally {
				bmInput = null;
			}
		} else {
			int newWidth = width;
			int newHeight = height;
			if (width > maxWidth || height > maxWidth) {
				float ratio = (float) width / height;
				if (ratio > 1.0f) {
					newWidth = maxWidth;
					newHeight = (int) ((float) newWidth / ratio);
				} else {
					newHeight = maxWidth;
					newWidth = (int) ((float) newHeight * ratio);
				}
			}

			Bitmap bitmap = Bitmap.createScaledBitmap(bmInput, newWidth,
					newHeight, false);
			bmInput.recycle();
			bmInput = null;

			if (bitmap != null) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 90,
						byteArrayOutputStream);
				bitmap.recycle();
				bitmap = null;
				retBuffer = byteArrayOutputStream.toByteArray();
				byteArrayOutputStream = null;
			}
		}
		System.gc();
		return retBuffer;
	}
}
