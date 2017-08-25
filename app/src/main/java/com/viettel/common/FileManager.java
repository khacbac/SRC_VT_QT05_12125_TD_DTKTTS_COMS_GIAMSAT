package com.viettel.common;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.util.Base64;
import android.util.Log;

import com.viettel.constants.Constants;
import com.viettel.utils.StringUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {
	
	public static String compressImage(String srcImg, String destImg) {

        String filePath = srcImg;  //getRealPathFromURI(imageUri);
        Bitmap scaledBitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

//      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
//      you try the use the bitmap here, you will get null.
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);

        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;

//      max Height and width values of the compressed image is taken as 816x612

        float maxHeight = 816.0f;//816.0f;
        float maxWidth = 1224.0f;//612.0f;
        float imgRatio = actualWidth / actualHeight;
        float maxRatio = maxWidth / maxHeight;

//      width and height values are set maintaining the aspect ratio of the image

        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;

            }
        }

//      setting inSampleSize value allows to load a scaled down version of the original image

        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);

//      inJustDecodeBounds set to false to load the actual bitmap
        options.inJustDecodeBounds = false;

//      this options allow android to claim the bitmap memory if it runs low on memory
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];

        try {
//          load the bitmap from its path
            bmp = BitmapFactory.decodeFile(filePath, options);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();

        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }

        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

//      check the rotation of the image and display it properly
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);

            int orientation = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0,
                    scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix,
                    true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream out = null;
        String filename = destImg; //getFilename();
        try {
            out = new FileOutputStream(filename);

//          write the compressed bitmap at the destination specified by filename.
            scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return filename;
    }
	
//	public String getFilename() {
//        File file = new File(Environment.getExternalStorageDirectory().getPath(), "MyFolder/Images");
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        String uriSting = (file.getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg");
//        return uriSting;
//
//    }
	
//	private String getRealPathFromURI(String contentURI) {
//        Uri contentUri = Uri.parse(contentURI);
//        Cursor cursor = getContentResolver().query(contentUri, null, null, null, null);
//        if (cursor == null) {
//            return contentUri.getPath();
//        } else {
//            cursor.moveToFirst();
//            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            return cursor.getString(index);
//        }
//    }
	
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }
	
	/**
	 * Ham coppy file anh khong resize
	 * 
	 * @param sFilePathSource
	 * @param sFilePathDestination
	 * @return
	 */
	public static boolean coppyFile(String sFilePathSource,
			String sFilePathDestination) {
		boolean bResult = false;
		try {
			File fileSource = new File(sFilePathSource);
			File fileDestination = new File(GlobalInfo.getInstance()
					.getFilePath() + sFilePathDestination);
//			if (fileDestination.exists()) {
//				fileDestination.deleteOnExit();
//			}
//			fileDestination.createNewFile();
//			InputStream inFile = new FileInputStream(fileSource);
//			OutputStream outFile = new FileOutputStream(fileDestination);
//			// Copy the bits from instream to outstream
//			byte[] buf = new byte[1024];
//			int len;
//			while ((len = inFile.read(buf)) > 0) {
//				outFile.write(buf, 0, len);
//			}
//			inFile.close();
//			outFile.close();
			
			compressImage(sFilePathSource, GlobalInfo.getInstance()
					.getFilePath() + sFilePathDestination);
			
			fileSource.deleteOnExit();
		} catch (Exception e) {
			Log.i("Error coppy file", e.getMessage());
		}
		return bResult;
	}
	
	

	public static String getFileName() {
		String sResult = "";
		try {
			Date now = new Date();
			SimpleDateFormat simpleNow = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			sResult = simpleNow.format(now) + ".png";
		} catch (Exception e) {
			Log.i("Error get file Name", e.getMessage());
		}
		return sResult;
	}

	public static String getFileName(long idCause) {
		String sResult = "";
		try {
			Date now = new Date();
			SimpleDateFormat simpleNow = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			sResult = simpleNow.format(now) + String.valueOf(idCause) + ".png";
		} catch (Exception e) {
			Log.i("Error get file Name", e.getMessage());
		}
		return sResult;
	}

	public static String getSdcardFilePath(String pFileName) {
		String sResult = "";
		try {
			sResult = GlobalInfo.getInstance().getFilePath();
			sResult += Constants.IMAGE_PATH_TEMPLATE;
			/* Tao folder neu khong ton tai */
			File folder = new File(sResult);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			sResult += "/" + pFileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sResult;
	}

	public static String getSaveFilePath(String pConstrId, String pFileName) {
		String sResult = "";
		try {
			sResult += Constants.IMAGE_PATH;
			Date curDate = new Date();
			if (!StringUtil.isNullOrEmpty(pConstrId)) {
				sResult += "/" + (curDate.getYear() + 1900) + "/"
						+ (curDate.getMonth() + 1) + "/" + curDate.getDate()
						+ "/" + pConstrId;
			} else {
				sResult += "/" + (curDate.getYear() + 1900) + "/"
						+ (curDate.getMonth() + 1) + "/" + curDate.getDate();
			}
			/* Tao folder neu khong ton tai */
			File folder = new File(GlobalInfo.getInstance().getFilePath()
					+ sResult);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			sResult += "/" + pFileName;
		} catch (Exception e) {
			Log.i("Error get file Name", e.getMessage());
		}
		return sResult;
	}

	/**
	 * Ham ghi file download tu String Base64
	 * 
	 * @param pFileStr
	 * @param pFilePath
	 * @return
	 */
	public static boolean saveFileDownload(String pFileStr, String pFilePath) {
		boolean bResult = false;
		try {
			String sFolder = StringUtil.getDirectoryPath(pFilePath);
			if (!StringUtil.isNullOrEmpty(sFolder)) {
				File fFolder = new File(sFolder);
				if (!fFolder.exists()) {
					fFolder.mkdirs();
				}
			}
			File fileSave = new File(pFilePath);
			if (fileSave.exists()) {
				fileSave.deleteOnExit();
			}
			fileSave.createNewFile();
			byte[] data = Base64.decode(pFileStr, Base64.DEFAULT);
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(pFilePath);
				// Write data to file
				fos.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fos != null)
					try {
						fos.close();
					} catch (IOException e) {
					}
			}
			bResult = true;
		} catch (Exception e) {
			Log.i("SFDLERR", e.getMessage());
		}
		return bResult;
	}

	public static String convertImageToBase64Str(String pFilePath) {
		String sResult = "";
		try {

			File file = new File(pFilePath);
			long length = file.length();
			BufferedInputStream bis = null;
			try {
				bis = new BufferedInputStream(new FileInputStream(file));
				if (length > Integer.MAX_VALUE) {
					throw new IOException("File must be smaller than 2 GB.");
				}
				byte[] data = new byte[(int) length];
				// Read bytes from file
				bis.read(data);
				// Gzip and encode to base64
				sResult = Base64.encodeToString(data, Base64.DEFAULT);
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (bis != null)
					try {
						bis.close();
					} catch (IOException ex) {
					}
			}
		} catch (Exception e) {
			Log.i("ConvertFileToStringErr", e.getMessage());
		}
		return sResult;
	}

	/**
	 * Ham coppy file anh va resize file coppy
	 * 
	 * @param sFilePathSource
	 * @param sFilePathDestination
	 * @return
	 */
	public static boolean coppyFileResize(String sFilePathSource,
			String sFilePathDestination) {
		try {
			File fileSource = new File(sFilePathSource);
			if (!fileSource.exists()) {
				return false;
			}
			File fileDestination = new File(GlobalInfo.getInstance()
					.getFilePath() + sFilePathDestination);
			if (fileDestination.exists()) {
				fileDestination.deleteOnExit();
			}
			Boolean bCreat = fileDestination.createNewFile();
			// Resize+
			Bitmap bitmap = BitmapFactory.decodeFile(sFilePathSource);
			int newWidth = 740;
			int newHeight = 740;
			Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight,
					Config.ARGB_8888);

			float ratioX = newWidth / (float) bitmap.getWidth();
			float ratioY = newHeight / (float) bitmap.getHeight();
			float middleX = newWidth / 2.0f;
			float middleY = newHeight / 2.0f;

			Matrix scaleMatrix = new Matrix();
			scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

			Canvas canvas = new Canvas(scaledBitmap);
			canvas.setMatrix(scaleMatrix);
			canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2, middleY
					- bitmap.getHeight() / 2, new Paint(
					Paint.FILTER_BITMAP_FLAG));
			try {
				FileOutputStream fos = new FileOutputStream(fileDestination);
				scaledBitmap.compress(CompressFormat.PNG, 70, fos);
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				return false;
			} catch (IOException e) {
				return false;
			} finally {
				System.gc();
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
