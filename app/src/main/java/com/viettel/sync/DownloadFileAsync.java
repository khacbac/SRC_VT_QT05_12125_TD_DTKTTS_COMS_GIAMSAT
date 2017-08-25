package com.viettel.sync;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import com.viettel.common.ActionEvent;
import com.viettel.common.GlobalInfo;
import com.viettel.constants.Constants;
import com.viettel.database.SqlliteSyncModel;
import com.viettel.utils.StringUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFileAsync extends AsyncTask<String, String, String> {
	private long idFile;
	private String fileSavePath;
	private ActionEvent e;
	protected long total;

	public DownloadFileAsync(long pIdFile, String pFileUrl,
			String pFileSavePath, ActionEvent pE) {
		this.idFile = pIdFile;
		this.fileSavePath = GlobalInfo.getInstance().getFilePath() + pFileSavePath;
		this.e = pE;
	}

	@Override
	protected void onPreExecute() {
		SyncQueue.getInstance().addFileDownload(this.idFile);
		super.onPreExecute();
	}

	@SuppressLint({ "WorldWriteableFiles", "WorldReadableFiles" })
	@Override
	protected String doInBackground(String... aurl) {
		int count;
		try {
			URL url = new URL(
					"http://ds.serving-sys.com/BurstingRes//Site-16990/Type-0/69b4c658-bdab-4d8e-849f-fe76f1cb4b0f.jpg");
			URLConnection conexion = url.openConnection();
			conexion.connect();
			int lenghtOfFile = conexion.getContentLength();
			if (lenghtOfFile == -1) {
				lenghtOfFile = 10000000;
			}

			InputStream input = new BufferedInputStream(url.openStream());

			Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);
			byte data[] = new byte[1024];
			/* Kiem tra xem file da ton tai thi xoa roi ghi de */
			/* Tao thu muc neu chua ton tai */
			String sFolder = StringUtil.getDirectoryPath(this.fileSavePath);
			if (!StringUtil.isNullOrEmpty(sFolder)) {
				File fFolder = new File(sFolder);
				if (!fFolder.exists()) {
					fFolder.mkdirs();
				}
			}
			File fileSave = new File(this.fileSavePath);
			if (fileSave.exists()) {
				fileSave.deleteOnExit();
			}
			fileSave.createNewFile();
			FileOutputStream fos = new FileOutputStream(fileSave);
			while (!isCancelled() && (input != null)
					&& ((count = input.read(data)) != -1)) {
				total += count;
				publishProgress("" + (int) ((total * 100) / lenghtOfFile));
				fos.write(data, 0, count);
			}

			publishProgress("0");

			fos.flush();
			fos.close();
			input.close();
			return Constants.DOWNLOAD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constants.DOWNLOAD_ERROR;

	}

	protected void onProgressUpdate(String... progress) {
		Log.d("ANDRO_ASYNC", progress[0]);
	}

	@Override
	protected void onPostExecute(String unused) {
		try {
			if (unused.equals(Constants.DOWNLOAD_SUCCESS)) {
				SqlliteSyncModel.updateDownloadFileSuccess(this.idFile);
			} else {
				Log.i(Constants.GSCT_TAG_ERROR, "Loi khi downlod file");
			}
			SyncQueue.getInstance().removeFileDownload(this.idFile);
			if (SyncQueue.getInstance().checkFileDownload()) {
				SyncModel.getInstance().syncGetImage(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
