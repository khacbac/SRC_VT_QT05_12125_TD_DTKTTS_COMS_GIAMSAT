package com.viettel.sync;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.ktts.R;
import com.viettel.viettellib.network.http.HTTPRequest;
import com.viettel.view.base.BaseActivity;

public class SyncTask extends AsyncTask<Void, Bundle, Void> {
	private static final String TAG = "SyncTask";
	public static final String KEY_SYNC_DATA_TYPE = "sync_data_type";
	public static final String KEY_PERCENT = "percent";
	private ActionEvent actionEvent;
	private static Dialog dialog;
	private static ProgressBar progressBar;
	private static TextView tvSyncTitle, tvSyncDataType;
	private Button butHide;
	private ImageView imgSync;
	private static Handler mHandler;
	private static Context context;
	private static Builder mBuilder;
	private static NotificationManager mNotificationManager;
	public static final int SYNC_NOTIFICATION_ID = 0;
	private static int percentFinish = 0;

	public SyncTask(ActionEvent e, Context context) {
		this.actionEvent = e;
		this.context = context;
		mHandler = new Handler(new Handler.Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub

				Bundle bundle = msg.getData();

				publishProgress(bundle);

				return true;
			}
		});

		mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(context);

		mBuilder.setContentTitle(
				context.getResources().getString(R.string.txt_hethong_gsct))
				.setContentText(
						context.getResources().getString(R.string.txt_syncing))
				.setSmallIcon(R.drawable.ic_ktts);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();

		dialog = new Dialog(context);
		dialog.setCancelable(false);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.sync_progress_bar_layout);
		dialog.getWindow().setBackgroundDrawable(
				new ColorDrawable(android.graphics.Color.TRANSPARENT));

		progressBar = (ProgressBar) dialog.findViewById(R.id.pbSync);
		tvSyncTitle = (TextView) dialog.findViewById(R.id.tvSyncTitle);
		tvSyncDataType = (TextView) dialog.findViewById(R.id.tvSyncDataType);
		butHide = (Button) dialog.findViewById(R.id.butHide);
//		imgSync = (ImageView) dialog.findViewById(R.id.imgSync);
//		AnimUtils.rotate(context, imgSync);

		// Displays the progress bar for the first time.
		mBuilder.setProgress(100, 0, false);
		mNotificationManager.notify(SYNC_NOTIFICATION_ID, mBuilder.build());

		dialog.show();

	}

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		HTTPRequest request = null;
		BaseActivity baseActivity = (BaseActivity) actionEvent.sender;
		switch (actionEvent.action) {
		/* lay du lieu lich hop */
		case ActionEventConstant.REQEST_LOGIN:
			SyncModel.bStop = false;
			request = SyncModel.getInstance().requestLoginHTTP(actionEvent);
			break;
		case ActionEventConstant.REQEST_SYNC:

			SyncQueue.getInstance().resetData(mHandler);
			SyncModel.getInstance().syncKttsKey(actionEvent, mHandler);

			break;
		default:
			break;
		}

		if (request != null && baseActivity != null) {
			actionEvent.request = request;
		}

		return null;
	}

	@Override
	protected void onProgressUpdate(Bundle... values) {
		// TODO Auto-generated method stub
		// super.onProgressUpdate(values);

		Double percent = values[0].getDouble(KEY_PERCENT);
		percentFinish = percent.intValue();
		progressBar.setProgress(percent.intValue());
		tvSyncTitle.setText(context.getResources().getString(
				R.string.txt_syncing)
				+ " ("
				+ String.valueOf((int) (values[0].getDouble(KEY_PERCENT)))
				+ " %" + ")");
		tvSyncDataType.setText(context.getResources().getString(
				R.string.txt_sync)
				+ values[0].getString(KEY_SYNC_DATA_TYPE));

		// Update Notification
		mBuilder.setProgress(100, (int) (values[0].getDouble(KEY_PERCENT)),
				false);

		mNotificationManager.notify(SYNC_NOTIFICATION_ID, mBuilder.build());
		// super.onProgressUpdate(values);

	}

	public static void closeDialog() {
		Log.e(TAG, "closeDialog: " + percentFinish );
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (percentFinish == 100) {

						mBuilder.setProgress(0, 0, false);
						//
						mBuilder.setContentText(
								context.getResources().getString(R.string.txt_sync_success))
								.setSound(
										Uri.parse("android.resource://"
												+ context.getPackageName() + "/"
												+ R.raw.sync_success_sond));
						mNotificationManager.notify(SYNC_NOTIFICATION_ID, mBuilder.build());
						
						dialog.dismiss();
						break;
					}
				}
			}
		});

		thread.start();

	}
	
	public static void handErrors(String error)
	{
		mBuilder.setProgress(0, 0, false);
		mBuilder.setContentText(error);
		mNotificationManager.notify(SYNC_NOTIFICATION_ID, mBuilder.build());
		dialog.dismiss();
	}

}