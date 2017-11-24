package com.viettel.view.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Edit_Text_Popup;
import com.viettel.actionbar.Image_ViewGalleryPopup;
import com.viettel.actionbar.NavDrawerItem;
import com.viettel.actionbar.NavDrawerListAdapter;
import com.viettel.camera.kutcamera.MainActivity;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.CheckInUtil;
import com.viettel.common.DateUtil;
import com.viettel.common.ErrorConstants;
import com.viettel.common.FileManager;
import com.viettel.common.GlobalInfo;
import com.viettel.common.LogManager;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.Constants.CONTEXT_MENU;
import com.viettel.constants.IntentConstants;
import com.viettel.controller.Home_Controller;
import com.viettel.database.EmployeeController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_LocateController;
import com.viettel.database.Supervision_ProgressController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.EmployeeEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_Measure_ConstrEntity;
import com.viettel.database.entity.Supervision_ProgressEntity;
import com.viettel.database.field.Supervision_ProgressField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.dialog.MessagerDialog;
import com.viettel.ktts.GoogleMapActivity;
import com.viettel.ktts.LoginActivity;
import com.viettel.ktts.MakePlanActivity;
import com.viettel.ktts.R;
import com.viettel.ktts.SupervisionBtsActivity;
import com.viettel.ktts.Supervision_BRCD_Sub_Activity;
import com.viettel.ktts.Supervision_BRCD_Thongtintk_Activity;
import com.viettel.ktts.Supervision_Line_BG_DesignActivity;
import com.viettel.ktts.Supervision_Line_HG_DesignActivity;
import com.viettel.service.CheckInService;
import com.viettel.service.GpsServices;
import com.viettel.sync.SyncModel;
import com.viettel.sync.SyncTask;
import com.viettel.utils.Mylog;
import com.viettel.utils.PreferenceUtil;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.IeSave;
import com.viettel.view.listener.IeValidate;
import com.viettel.view.listener.MyItemSelected;
import com.viettel.view.listener.OnEventControlListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public abstract class BaseActivity extends AppCompatActivity
        implements OnClickListener, OnEventControlListener,
        DialogInterface.OnCancelListener, MyItemSelected {
    private final String TAG = "BaseActivity";
    public boolean isFinished = false;
    public Uri imgUri = null;
    public static final String VT_ACTION = "com.viettel.ktts.BROADCAST";
    // co nhan broadcast hay khong
    @SuppressWarnings("unused")
    private boolean broadcast;
    // dialog hien thi khi request
    public static ProgressDialog progressDlg;
    private EditText myText;
    private ClipboardManager myClipboard;
    private ClipData myClip;

    // hungkm
    public DrawerLayout mDrawerLayout;
    public LinearLayout mLnDrawer;
    public LinearLayout mRoot;
    public ListView mDrawerList;
    public TextView mTvUserName;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    public FrameLayout mFrameLayout;
    private LayoutInflater inflater;
    private MyItemSelected itemSelected;
    // action bar menu
    protected Menu mMenu;
    public MenuItem mItemCheckIn, mItemCollapse, mSave;
    protected boolean isCheckIn;

    private Button btnActionTouch;

    private boolean isPopupShowing = false;

    // service dem thoi gian, sau 4 tieng thi checkout va stop
    protected Intent serviceIntent;
    // datatbase luu thoi diem check in
    public Supervision_LocateController supv_locate_controller = null;

    //mock up
    public double reqLat = -1.0f;
    public double reqLong = -1.0f;

    protected Image_ViewGalleryPopup imgViewPopup;

    Toolbar toolbar;
    AppBarLayout appbar;

    // Interface save.
    private IeSave.IeCapNhatNhatKyInteractor ieCapNhatNhatKyInteractor;
    private IeSave.IeCapNhatTienDoInteractor ieCapNhatTienDoInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        LogManager.getInstance().saveLogcatToFile(getApplicationContext());
        serviceIntent = new Intent(this, CheckInService.class);
        createMenuBar(savedInstanceState);
        myClipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        GlobalInfo.getInstance().setAppContext(getApplicationContext());
        supv_locate_controller = new Supervision_LocateController(this);
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        try {
            IntentFilter filter = new IntentFilter(VT_ACTION);
            registerReceiver(receiver, filter);
            this.broadcast = true;
        } catch (Exception e) {
        }
        setMyItemSelected(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showCheckinButton();
    }

    private void showCheckinButton() {
        isCheckIn = GlobalInfo.getInstance().isCheckIn();
        NotificationManager mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNM.cancel(Constants.NOTIFICATION_MANAGER);
        if (mItemCheckIn != null) {
//			visiableCheckInItem(isCheckIn);
            if (isCheckIn) {
                changeMenuCheckIn(R.drawable.icon_checkout, getString(R.string.check_out));
            } else {
                changeMenuCheckIn(R.drawable.icon_checkin, getString(R.string.check_in));
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        NotificationManager mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNM.cancel(Constants.NOTIFICATION_MANAGER);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        try {
            unregisterReceiver(this.receiver);
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        // switch (v.getId()) {
        // default:
        // break;
        // }
    }

    @Override
    public void onBackPressed() {
        // TODO xem xet su kien

    }

    @Override
    public void onCancel(DialogInterface dialog) {
        if (dialog == progressDlg) {
            // TODO khong cho dong bo tiep, hoac cance dong bo
        }
    }

    @Override
    public void onEvent(int eventType, View control, Object data) {
        switch (eventType) {
            case OnEventControlListener.EVENT_OK_LOGOUT:
                sentBroadcast(ActionEventConstant.ACTIVITY_FINISH, new Bundle());
                Intent it = new Intent(this, LoginActivity.class);
                startActivity(it);
                break;
            default:
                break;
        }
    }

    public void startService() {
        startService(serviceIntent);
    }

    public void stopService() {
        stopService(serviceIntent);
    }

    public void handleModelViewEvent(ModelEvent modelEvent) {
        ActionEvent e = modelEvent.getActionEvent();
        switch (e.action) {
            case ActionEventConstant.LOG_OUT:
                sentBroadcast(ActionEventConstant.ACTIVITY_FINISH, new Bundle());
                break;
        }
        closeProgressDialog();
    }

    @SuppressLint("ShowToast")
    public void handleErrorModelViewEvent(ModelEvent modelEvent) {
        switch (modelEvent.getModelCode()) {
            case ErrorConstants.ERROR_COMMON:
                SyncModel.bRunning = false;
                // closeProgressDialog();
                SyncTask.handErrors(StringUtil.getString(R.string.error_common));
                Toast.makeText(this, StringUtil.getString(R.string.error_common),
                        Toast.LENGTH_SHORT).show();
                break;
            case ErrorConstants.ERROR_NO_CONNECTION:
                SyncModel.bRunning = false;
                // closeProgressDialog();
                SyncTask.handErrors(modelEvent.getModelMessage());
                if (!StringUtil.isNullOrEmpty(modelEvent.getModelMessage())) {
                    showDialog(modelEvent.getModelMessage());
                }
                // Toast.makeText(this, modelEvent.getModelMessage(),
                // Toast.LENGTH_SHORT).show();
                break;
            case ErrorConstants.ERR_SYNC_CONNECTION:
                SyncModel.bRunning = false;
                // closeProgressDialog();
                SyncTask.handErrors(StringUtil.getString(R.string.text_sync_error));
                Toast.makeText(this, StringUtil.getString(R.string.text_sync_error),
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                // closeProgressDialog();
                // SyncTask.handErrors(modelEvent.getModelMessage());
                if (!StringUtil.isNullOrEmpty(modelEvent.getModelMessage())) {
                    SyncTask.handErrors(modelEvent.getModelMessage());
                    showDialog(modelEvent.getModelMessage());
                }
                break;
        }
    }

    /**
     * Ham kiem tra dang nhap offline
     *
     * @param sUserName
     * @param sPassWord
     * @return
     */
    public EmployeeEntity getLoginOffline(String sUserName, String sPassWord) {

		/* Kiem tra o db neu co login */
        EmployeeController loginController = new EmployeeController(this);
        EmployeeEntity loginAcount = loginController.getLoginAcount(sUserName, sPassWord);
        return loginAcount;
    }

    protected void gotoLogOut() {
        this.showConfirmDialog(StringUtil.getString(R.string.text_messager_header),
                StringUtil.getString(R.string.text_warning_logout),
                OnEventControlListener.EVENT_OK_LOGOUT);
    }

    @Override
    public void finish() {
        try {
            unregisterReceiver(receiver);
        } catch (Exception e) {
            // TODO: handle exception
        }

        isFinished = true;
        super.finish();
    }

    // xu li copy paste
    @SuppressWarnings("unused")
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case CONTEXT_MENU.COPY:
                String textCopy = myText.getText().toString();
                myClip = ClipData.newPlainText("text", textCopy);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getApplicationContext(), R.string.text_temp_memory_copy,
                        Toast.LENGTH_SHORT).show();
                return true;
            case CONTEXT_MENU.PASTE:
                ClipData clipPaste = myClipboard.getPrimaryClip();
                ClipData.Item itemClip = clipPaste.getItemAt(0);
                String textPaste = itemClip.getText().toString();
                myText.setText(textPaste);
                // Toast.makeText(getApplicationContext(), "Text Pasted",
                // Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        // user has long pressed your TextView
        menu.add(0, CONTEXT_MENU.COPY, 0, R.string.text_copy);
        menu.add(0, CONTEXT_MENU.PASTE, 1, R.string.text_paste);

        // cast the received View to TextView so that you can get its text
        myText = (EditText) v;

    }

    public boolean checkToDisBeforeEqualFromDisLast(long toDisBefore, long fromDisLast) {
        if (toDisBefore != fromDisLast) {
            return true;
        }
        return false;
    }

    // broadcast receiver, nhan broadcast
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int action = intent.getExtras().getInt(Constants.RECEIVE_ACTION);
            int hasCode = intent.getExtras().getInt(Constants.RECEIVE_HASHCODE);
            if (hasCode != BaseActivity.this.hashCode()) {
                receiveBroadcast(action, intent.getExtras());
            }
        }
    };

    public void sentBroadcast(int action, Bundle bundle) {
        Intent intent = new Intent(VT_ACTION);
        bundle.putInt(Constants.RECEIVE_ACTION, action);
        bundle.putInt(Constants.RECEIVE_HASHCODE, intent.getClass().hashCode());
        intent.putExtras(bundle);
        sendBroadcast(intent, "com.viettel.permission.BROADCAST");
    }

    public void receiveBroadcast(int action, Bundle bundle) {
        switch (action) {
            case ActionEventConstant.ACTIVITY_FINISH:
                this.finish();
                break;
            case ActionEventConstant.ACTIVITY_CHECKOUT:
                isCheckIn = GlobalInfo.getInstance().isCheckIn();
                Log.d("CheckinService", "isCheckIn after checkout : " + isCheckIn);
                createOptionMenu();
                Toast.makeText(getApplicationContext(), getString(R.string.checkout_notify), Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    /**
     * Ham finish Activity tra ve du lieu cho activity cha
     */
    @Override
    public void finishActivity(int requestCode) {
        isFinished = true;
        super.finishActivity(requestCode);
    }

    /**
     * Ham lay username dang nhap thanh cong lan dau
     *
     * @return
     */
    public String getLoginNameSetting() {
        String sUserName = "";
        try {
            PreferenceUtil preUtil = new PreferenceUtil(this);
            sUserName = preUtil.getStringPreference(Constants.LONGIN_NAME_SETTING);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return sUserName;
    }

    /**
     * Ham lay username dang nhap thanh cong lan dau
     *
     * @return
     */
    public String getClientIdSetting() {
        String sClientId = "";
        try {
            PreferenceUtil preUtil = new PreferenceUtil(this);
            sClientId = preUtil.getStringPreference(Constants.CLIENTID_SETTING);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return sClientId;
    }

    /**
     * Ham set LoginName de dang nhap
     *
     * @param sUserName
     * @return
     */
    public boolean setLoginNameSetting(String sUserName) {
        try {
            PreferenceUtil preUtil = new PreferenceUtil(this);
            preUtil.setStringPreference(Constants.LONGIN_NAME_SETTING, sUserName);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return false;
    }

    public void showProgressDialog(String content) {
        showProgressDialog(content, false);
    }

    public static void closeProgressDialog() {
        if (progressDlg != null) {
            try {
                progressDlg.dismiss();
                progressDlg = null;
            } catch (Exception e) {
                Mylog.i("Exception", e.toString());
            }
        }
    }

    /**
     * show progress dialog
     *
     * @param content
     * @param cancleable
     * @author: AnhND
     * @return: void
     * @throws:
     */
    public void showProgressDialog(String content, boolean cancleable) {
        if (progressDlg != null && progressDlg.isShowing()) {
            closeProgressDialog();
            Log.d(TAG, "showProgressDialog: close");
        }
        progressDlg = ProgressDialog.show(this, "", content, true, true);
        progressDlg.setCancelable(cancleable);
        progressDlg.setCanceledOnTouchOutside(false);
        progressDlg.setOnCancelListener(this);
    }

    public void showDialog(final String mes) {
        MessagerDialog messagerDialog = new MessagerDialog(this, mes);
        messagerDialog.setCancelable(false);
        messagerDialog.show();
    }

    public void showDialog(final String mes, int iEvent) {
        MessagerDialog messagerDialog = new MessagerDialog(this, mes, iEvent);
        messagerDialog.setCancelable(false);
        messagerDialog.show();
    }

    public void showConfirmDialog(String sTitle, String sMessager, int eEvent) {
        ConfirmDialog confirmDialog = new ConfirmDialog(this, sTitle, sMessager, eEvent);
        confirmDialog.setCancelable(false);
        confirmDialog.show();
    }

    /**
     * ham thong bao su kien thanh cong va tro ve acitivi khac
     *
     * @param ct
     * @param mess
     * @param btn_strView
     */
    public void showAlertDialog(Context ct, String mess, String btn_strView) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ct);
        // set dialog message
        alertDialogBuilder.setMessage(mess + "!").setCancelable(false).setPositiveButton(btn_strView,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        actionBeforAccept();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    // hien thi thong bao yeu cau check in khi muon luu du lieu
    public void showAlertDialogCheckInRequire(Context ct, String mess, String btn_strView) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ct);
        // set dialog message
        alertDialogBuilder.setMessage(mess + "!").setCancelable(false).setPositiveButton(btn_strView,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    // dialog hien thi khi muon checkout
    public void showAlertDialogCheckOut(final Context ct, String mess, String btn_strView) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ct);
        // set dialog message
        alertDialogBuilder.setMessage(mess + "!").setCancelable(true).setPositiveButton(btn_strView,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (CheckInUtil.updateDataAfterCheckOut(ct, supv_locate_controller,
                                Constants.SUPV_LOCATE_CO_STATUS.TU_CHECKOUT,
                                new Date(System.currentTimeMillis()))) {
                            isCheckIn = false;
                            GlobalInfo.getInstance().setCheckIn(isCheckIn, getApplicationContext());
                            if (getLocalClassName().contains(SupervisionBtsActivity.class.getSimpleName())
                                    || getLocalClassName().contains(
                                            Supervision_BRCD_Sub_Activity.class.getSimpleName())
                                    || getLocalClassName().contains(
                                            Supervision_BRCD_Thongtintk_Activity.class.getSimpleName())
                                    || getLocalClassName().contains(
                                            Supervision_Line_BG_DesignActivity.class.getSimpleName())
                                    || getLocalClassName().contains(
                                            Supervision_Line_HG_DesignActivity.class.getSimpleName())) {
                                changeMenuCheckIn(R.drawable.icon_checkin, getString(R.string.check_in));
                            } else {
                                visiableCheckInItem(false);
                            }
                            stopService();
                            Toast.makeText(ct, getString(R.string.checkout_notify), Toast.LENGTH_LONG).show();
                        }

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showAskOptionDialog(String sTitle, String sMessage) {
        AlertDialog dialogAskOption = new AlertDialog.Builder(this)
                .setTitle(sTitle).setMessage(sMessage)
                .setIcon(R.drawable.ic_launcher)

                .setPositiveButton(StringUtil.getString(R.string.text_delete_button), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        actionBeforAccept();
                        dialog.dismiss();
                    }

                })

                .setNegativeButton(StringUtil.getString(R.string.text_close_button), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                }).create();
        dialogAskOption.show();
    }

    public void showAskOptionDialog(Context mContext, String sTitle,
                                    String sMessage, String btnPositiveName) {
        AlertDialog dialogAskOption
                = new AlertDialog.Builder(this).setTitle(sTitle).setMessage(sMessage)
                .setIcon(R.drawable.ic_launcher)
                .setPositiveButton(btnPositiveName, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        actionBeforAccept();
                        dialog.dismiss();
                    }

                })
                .setNegativeButton(StringUtil.getString(R.string.line_bg_mx_kl_huy), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        actionNegative();
                        dialog.dismiss();

                    }
                }).create();
        dialogAskOption.show();
    }

    /* chuyen tiep sau khi thong bao */
    public void actionBeforAccept() {

    }

    public void actionNegative() {

    }

    private final int REQUEST_CAMERA = 10101;
    private final int REQUEST_READ_EXTERNAL_STORAGE = 10201;

    /**
     * Ham chup anh dung chung
     */
    public void takePhoto(Constr_Construction_EmployeeEntity constr_ConstructionItem) {

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(BaseActivity.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA);
            return;
        }

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(BaseActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    REQUEST_READ_EXTERNAL_STORAGE);
            return;
        }

        // Intent intent = new
        // Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//        Intent intent = new Intent(Constants.IMG_TAKE_INTENT);
        Intent intent = new Intent(this, MainActivity.class);

        // Constr_Construction_EmployeeEntity constr_ConstructionEmployeeItem = new
        // Constr_Construction_EmployeeEntity();
        // constr_ConstructionEmployeeItem.setConstrCode("cuongdk3");
        // Bundle bundleMonitorData = new Bundle();
        // bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
        // constr_ConstructionEmployeeItem);

        // intent.putExtras(bundleMonitorData);
        intent.putExtra("constrCode", constr_ConstructionItem.getConstrCode());
        intent.putExtra("supervisorName", GlobalInfo.getInstance().getFullName()); // ten
        // nguoi
        // giam
        // sat
        intent.putExtra("employeeCode", GlobalInfo.getInstance().getEmployeeCode()); // ma
        // nhan
        // vien

        String sFileName = FileManager.getFileName();
        File photo = new File(FileManager.getSdcardFilePath(sFileName));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imgUri = Uri.fromFile(photo);
        startActivityForResult(intent, Constants.TAKE_PICTURE_RESULT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA: {
                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    initData();
//                }
                return;
            }
            case REQUEST_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    selectPhoto();
                }
                return;
            }

        }
    }

    public void selectPhoto() {
//        Intent imgPickerIntent = new Intent();
//        imgPickerIntent.setType("image/*");
//        imgPickerIntent.setAction(Intent.ACTION_GET_CONTENT);
//        imgPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//        startActivityForResult(Intent.createChooser(imgPickerIntent, "Select Picture"),
//        Constants.SELECT_PICTURE_RESULT);
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(
                        BaseActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
            },
                    REQUEST_READ_EXTERNAL_STORAGE);
            return;
        }
        MultiImageSelector.create()
                .showCamera(false)
                .multi() // multi mode, default mode;
                .count(5)
                .start(this, Constants.SELECT_PICTURE_RESULT);

//		startActivityForResult(imgPickerIntent, Constants.SELECT_PICTURE_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == Constants.SELECT_PICTURE_RESULT) {

            List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (this.imgViewPopup != null) {
                for (String s : path) {
                    Log.e(TAG, "onActivityResult: " + s);
                    ImageEntity addImgView = new ImageEntity();
                    addImgView.setId(1);
                    addImgView.setUrl(s);
                    this.imgViewPopup.setImageData(addImgView);
                }
            }


//            Uri selectedImage = data.getData();
//
//            String picturePath = StringUtil.getPath(this, selectedImage);
//            Log.e(TAG, "onActivityResult: " + data );
//            Log.e(TAG, "onActivityResult: " + data.getData() );
//
//            ImageEntity addImgView = new ImageEntity();
//            addImgView.setId(1);
//            addImgView.setUrl(picturePath);
//            if (this.imgViewPopup != null)
//                this.imgViewPopup.setImageData(addImgView);
        }
    }

    public void getLocation(Double dLong, Double dLat, String sTitle, String sValue) {
        Intent intent = new Intent(this, GoogleMapActivity.class);
        intent.putExtra(IntentConstants.INTENT_LONG, dLong);
        intent.putExtra(IntentConstants.INTENT_LAT, dLat);
        intent.putExtra(IntentConstants.INTENT_MAP_TITLE, sTitle);
        intent.putExtra(IntentConstants.INTENT_MAP_VALUE, sValue);
//		intent.setComponent(new ComponentName(
//      "com.viettel.ktts", "com.viettel.ktts.GoogleMapActivity"));
        startActivityForResult(intent, Constants.GET_LOCATION_RESULT);

    }

    /**
     * @param measureItem : ten hang muc do dc chon
     */
    public void getMeasureDetail(Supervision_Measure_ConstrEntity measureItem) {
        Intent intent = new Intent(this, GoogleMapActivity.class);
        Bundle bundleData = new Bundle();
        if (measureItem.getListMeasureDetail() != null
                && measureItem.getListMeasureDetail().size() > 0) {
            bundleData.putSerializable(IntentConstants.INTENT_LST_MEASURE_DETAIL,
                    (Serializable) measureItem.getListMeasureDetail());
            bundleData.putString(IntentConstants.INTENT_MEASURE_INFO, measureItem.getName());

            bundleData.putSerializable(IntentConstants.INTENT_LST_DELETE_MEASURE_DETAIL,
                    (Serializable) measureItem.getListDelMeasureDetail());
        }

        intent.putExtras(bundleData);
//		intent.setComponent(
//		new ComponentName("com.viettel.ktts", "com.viettel.ktts.Measurement_Constr_DetailActivity"));
        startActivityForResult(intent, Constants.GET_MEASURE_INFO);
        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    /* Ham kiem tra co mo Gps */
    public boolean checkOpenGps() {
        final LocationManager gpsManager
                = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (gpsManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Ham kiem tra 3G va wifi co bat khong
     *
     * @return
     */
    public boolean check3GWifi() {
        boolean bResult = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        // For 3G check
        boolean is3g = manager.getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        // For WiFi Check
        boolean isWifi = manager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        if (is3g || isWifi) {
            bResult = true;
        }
        return bResult;
    }

    /**
     * Ham lay dia chi mac cua may android va tablet
     *
     * @return Dia chi mac cua may
     */
    public String getMacAddress() {
        @SuppressLint("WifiManagerLeak") WifiManager wifiManager
                = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        return macAddress;
    }

    /**
     * Ham thong bao bat wifi 3g de dong bo du lieu
     */
    public void show3GWifiOffline() {
        Toast.makeText(this, StringUtil.getString(R.string.text_3g_wifi_offline), Toast.LENGTH_SHORT).show();
    }

    // TODO chinh sua ham thanh chuan
    public void setTextNumberFormat(final EditText pEditText) {
        pEditText.addTextChangedListener(new TextWatcher() {
            private final Pattern sPattern = Pattern.compile("^([1-9][0-9]{0,2})?(\\.[0-9]?)?$");

            private CharSequence mText;

            private boolean isValid(CharSequence s) {
                return sPattern.matcher(s).matches();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("onTextChanged", String.valueOf(s));

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("beforeTextChanged", String.valueOf(s));
                mText = isValid(s) ? s : "";
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("afterTextChanged", String.valueOf(s));
                if (!isValid(s)) {
                    pEditText.setText(mText);
                }
                mText = null;

            }
        });
    }

    public void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception; kiem tra nguyen nhan loi
        }
    }

    public Supervision_ProgressEntity getSupervisionProgress(
            Constr_Construction_EmployeeEntity constr_ConstructionItem,
            int iType, String sSubType) {
        Supervision_ProgressController supvProgressController
                = new Supervision_ProgressController(this);
        Supervision_ProgressEntity supvProgress = supvProgressController.getSupvProgress(
                constr_ConstructionItem.getSupervision_Constr_Id(), iType, sSubType);

        return supvProgress;
    }

    // hien ngay hoan thanh
    public void showCompleteDate(Constr_Construction_EmployeeEntity constr_ConstructionItem,
                                 int iType, String sSubType,
                                 TextView tvComplete, Button btnComplete) {
        if (constr_ConstructionItem.getCatProgressWorkId() <= 2) {
            btnComplete.setVisibility(View.GONE);
        } else {
            Supervision_ProgressController supvProgressController
                    = new Supervision_ProgressController(this);
            Supervision_ProgressEntity supvProgress = supvProgressController.getSupvProgress(
                    constr_ConstructionItem.getSupervision_Constr_Id(), iType, sSubType);

            if (supvProgress.getSupervisionProgressId() > 0) {
                tvComplete.setText(StringUtil.getString(R.string.supervision_date_complete)
                        + DateUtil.convertDateToString(supvProgress.getCompleteDate()));
                tvComplete.setVisibility(View.VISIBLE);
                btnComplete.setVisibility(View.GONE);
            } else {
                tvComplete.setVisibility(View.GONE);
                btnComplete.setVisibility(View.VISIBLE);
            }
        }

    }

    /* save ngay hoan thanh */
    public void saveCompleteDay(Constr_Construction_EmployeeEntity constr_ConstructionItem,
                                int iType, String sSubType,
                                boolean outOfKey) {
        try {
            long idUser = GlobalInfo.getInstance().getUserId();

            Supervision_ProgressController supvProgressController
                    = new Supervision_ProgressController(this);

            // if(supvProgress.getSupervisionProgressId() <= 0 ) {
            long idSupvProgress = Ktts_KeyController.getInstance().getKttsNextKey(
                    Supervision_ProgressField.TABLE_NAME);

            if (idSupvProgress == 0) {
                outOfKey = true;
                return;
            } else
                outOfKey = false;

            Supervision_ProgressEntity supvProgressEntity = new Supervision_ProgressEntity();
            supvProgressEntity.setSupervisionProgressId(idSupvProgress);
            supvProgressEntity.setSupervisionConstrId(
                    constr_ConstructionItem.getSupervision_Constr_Id());
            supvProgressEntity.setType(iType);
            supvProgressEntity.setSubType(sSubType);
            supvProgressEntity.setCompleteDate(Calendar.getInstance().getTime());
            supvProgressEntity.setProcessId(0);
            supvProgressEntity.setEmployeeId(idUser);
            supvProgressEntity.setSync_Status(Constants.SYNC_STATUS.ADD);

            supvProgressController.addItem(supvProgressEntity);

            // Supervision_ConstrController constr_Controller = new
            // Supervision_ConstrController(
            // this);
            // constr_Controller.updateSyncStatus(constr_ConstructionEmployeeItem
            // .getSupervision_Constr_Id());
            // }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    // public String formatNumber(String formatString){
    // DecimalFormatSymbols otherSymbols = new
    // DecimalFormatSymbols(Locale.getDefault());
    // otherSymbols.setDecimalSeparator(',');
    // otherSymbols.setGroupingSeparator('.');
    // DecimalFormat df = new DecimalFormat(formatString, otherSymbols);
    // }

    public void requestSync(Context context) {
        if (this.check3GWifi()) {
            /* Kiem tra xem co dang dong bo khong */
            Bundle bundle = new Bundle();
            ActionEvent e = new ActionEvent();
            e.action = ActionEventConstant.REQEST_SYNC;
            e.viewData = bundle;
            e.isBlockRequest = true;
            e.sender = this;
            Home_Controller.getInstance().handleViewEvent(e, context);
        } else {
            this.show3GWifiOffline();
        }
    }

    // hungkm
    public void createMenuBar(Bundle savedInstanceState) {
        // enabling action bar app icon and behaving it as toggle button
        inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        mTitle = mDrawerTitle = getString(R.string.app_name);

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mLnDrawer = (LinearLayout) findViewById(R.id.ln_slide_menu);
        mRoot = (LinearLayout) findViewById(R.id.root_view);
        mTvUserName = (TextView) findViewById(R.id.tv_user);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        mFrameLayout = (FrameLayout) findViewById(R.id.frame_container);
        btnActionTouch = (Button) findViewById(R.id.btnActionTouch);

        if (GlobalInfo.getInstance().getFullName() != null) {
            mTvUserName.setText(GlobalInfo.getInstance().getFullName());
        }

        navDrawerItems = new ArrayList<NavDrawerItem>();

        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], R.drawable.ic_home));
        // Find People
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], R.drawable.icon_up));
//   <item>@drawable/icon_up</item>
//        <item>@drawable/icon_plan</item>
//        <item>@drawable/icon_syn</item>
//        <item>@drawable/icon_info</item>
//        <item>@drawable/icon_dashboard</item>
//        <item>@drawable/icon_logout</item>
        // Communities, Will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], R.drawable.icon_plan));
        // Photos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], R.drawable.icon_syn));
        // Pages
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], R.drawable.icon_info));
        // What's hot, We will add a counter here
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], R.drawable.icon_dashboard));
        // dartboard
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], R.drawable.icon_logout));

        // Recycle the typed array
        navMenuIcons.recycle();

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name ) {
            public void onDrawerClosed(View view) {
                // getActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                // getActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(0);
        }
        // enabling action bar app icon and behaving it as toggle button
        initToolbar();
        btnActionTouch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard();
            }
        });
//        btnActionTouch.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                dispatchTouchEvent(event);
//                return true;
//            }
//        });
    }

    protected void initToolbar() {
        toolbar.setTitle(mTitle);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle(mTitle);
    }

    public View addView(int resource, int rootID) {
        View view = inflater.inflate(resource, (ViewGroup) findViewById(rootID), false);
        mFrameLayout.removeAllViews();
        mFrameLayout.addView(view);
        ButterKnife.bind(this);
        return view;
    }

    private class SlideMenuClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // display view for selected nav drawer item
            displayView(position);
            // setMyItemSelected(itemSelected);
            itemSelected.onItemSelected(position);

            // switch (position) {
            // case 0:
            // Intent intent = new Intent(getApplicationContext(),
            // HomeActivity.class);
            // startActivity(intent);
            // finish();
            // break;
            //
            // case 1:
            // Intent intent1 = new Intent(getApplicationContext(),
            // SecondActivity.class);
            // startActivity(intent1);
            // finish();
            // break;
            //
            // default:
            // break;
            // }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu_actions, menu);
        mMenu = menu;

        // menu checkIn
        mItemCheckIn = mMenu.findItem(R.id.action_checkin);
        mSave = mMenu.findItem(R.id.action_save);
        // menu collapse
        mItemCollapse = mMenu.findItem(R.id.action_collapse);
        createOptionMenu();
        showCheckinButton();
        return super.onCreateOptionsMenu(menu);
    }

    public void createOptionMenu() {
//		if (isCheckIn) {
//			changeMenuCheckIn(R.drawable.icon_checkout, getString(R.string.check_out));
//		} else {
//			changeMenuCheckIn(R.drawable.icon_checkin, getString(R.string.check_in));
//		}

//        Log.e(TAG, "createOptionMenu: " + isCheckIn + " " + getLocalClassName().toString() + " "
//      + SupervisionBtsActivity.class.getSimpleName().toString());
        if (isCheckIn) {
            visiableCheckInItem(true);
            changeMenuCheckIn(R.drawable.icon_checkout, getString(R.string.check_out));
        } else if (getLocalClassName().contains(SupervisionBtsActivity.class.getSimpleName())
                || getLocalClassName().contains(
                        Supervision_Line_HG_DesignActivity.class.getSimpleName())
                || getLocalClassName().contains(
                        Supervision_BRCD_Thongtintk_Activity.class.getSimpleName())
                || getLocalClassName().contains(
                        Supervision_Line_BG_DesignActivity.class.getSimpleName())
                || getLocalClassName().contains(
                        Supervision_BRCD_Sub_Activity.class.getSimpleName())) {
            // visiableCollapseItem();
//            Log.e(TAG, "createOptionMenu: 1");
            visiableCheckInItem(true);
            changeMenuCheckIn(R.drawable.icon_checkin, getString(R.string.check_in));
        } else if (getLocalClassName().contains(MakePlanActivity.class.getSimpleName())) {
//            Log.e(TAG, "createOptionMenu: 2");
            visiableCheckInItem(false);
            visiableCollapseItem(true);
            changeItemConllapse(R.drawable.icon_syn, getString(R.string.text_close_button));
            changeMenuCheckIn(R.drawable.icon_checkin, getString(R.string.check_in));
        } else {
//            Log.e(TAG, "createOptionMenu: 3");
            visiableCheckInItem(false);
            changeMenuCheckIn(R.drawable.icon_checkin, getString(R.string.check_in));
        }
//		if (getLocalClassName().toString().equals(MakePlanActivity.class.getSimpleName().toString())) {
//			// visiableCollapseItem();
//			visiableCheckInItem(false);
//			visiableCollapseItem(true);
//			changeItemConllapse(R.drawable.icon_syn, getString(R.string.text_close_button));
//			changeMenuCheckIn(R.drawable.icon_checkin, getString(R.string.check_in));
//		}
    }

    public void visiableCollapseItem(boolean visibility) {
        mItemCollapse.setVisible(visibility);
    }

    public void visiableCheckInItem(boolean visibility) {
        mItemCheckIn.setVisible(visibility);
    }

    public void changeItemConllapse(int res, String title) {
        mItemCollapse.setIcon(null);
        mItemCollapse.setTitle(" " + title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_checkin:
                if (isCheckIn == false) {
                    actionCheckInButton();
                } else {
                    showAlertDialogCheckOut(this, getString(R.string.checkout_confirm),
                            getString(R.string.text_ok));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void actionCheckInButton() {

    }

    public PopupWindow popupWindow;
    public EditText edtMakePlan;

    public void showPopupPlan(View view) {
        int popupWidth = getResources().getDisplayMetrics().widthPixels / 2;
        int popupHeight = getResources().getDisplayMetrics().heightPixels;
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup_make_new_plan, null);
        popupWindow = new PopupWindow(popupView, popupWidth, popupHeight);
        popupWindow.setFocusable(true);
        popupEvent(popupView, popupWindow);
        popupWindow.update();
        popupWindow.showAsDropDown(view);

//        DialogUtils.showCheckin(this, null, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                doSaveCheckin();
//            }
//        });
    }

    public void popupEvent(View popupView, final PopupWindow popupWindow) {
        edtMakePlan = (EditText) popupView.findViewById(R.id.edt_popup_plan);
        Button btnSavePlan = (Button) popupView.findViewById(R.id.btn_popup_save_plan);
        Button btnCancel = (Button) popupView.findViewById(R.id.btn_popup_cancel);
        btnSavePlan.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    public void changeMenuCheckIn(int icon, String title) {
        mItemCheckIn.setIcon(icon);
        mItemCheckIn.setTitle(title);
        mItemCheckIn.setTitleCondensed(" " + title);
//        invalidateOptionsMenu();
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    public void displayView(int position) {
        mDrawerList.setItemChecked(position, true);
        mDrawerList.setSelection(position);
        setTitle(navMenuTitles[position]);
        mDrawerLayout.closeDrawer(mLnDrawer);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mTitle);
        }
        if (toolbar != null) {
            toolbar.setTitle(mTitle);
        }
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        if (mDrawerToggle != null)
            mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        if (mDrawerToggle != null)
            mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void setMyItemSelected(MyItemSelected itemSelected) {
        this.itemSelected = itemSelected;
    }

    public void changeIconSearch(int position, int icon, String title) {
        adapter.changeIcon(position, icon, title);
    }

    // Bat dau lang nghe su kien save tu cap nhat nhat ky.
    public void listenerSaveFromNhatKy(IeSave.IeCapNhatNhatKyInteractor interactor) {
        interactor.saveNhatKy();
    }
    // Bat dau lang nghe su kien save tu cap nhat nhat ky.
    public void listenerSaveFromTienDo(IeSave.IeCapNhatTienDoInteractor interactor) {
        interactor.saveTienDo();
    }
    // Check validate tu nhat ky.
    public boolean listenerValidateFromNhatKy(IeValidate.IecheckValidateNhatKy validateNhatKy, boolean isThiCong) {
        return validateNhatKy.checkValidateNhatKy(isThiCong);
    }
    // Check validate tu nhat ky.
    public boolean listenerValidateFromTienDo(IeValidate.IecheckValidateTienDo validateTienDo) {
        return validateTienDo.checkValidateTienDo();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        boolean ret = super.dispatchTouchEvent(ev);

        if (view instanceof EditText) {
            View w = getCurrentFocus();
            int scrcoords[] = new int[2];
            if (w != null) {
                w.getLocationOnScreen(scrcoords);
                float x = ev.getRawX() + w.getLeft() - scrcoords[0];
                float y = ev.getRawY() + w.getTop() - scrcoords[1];
                if (ev.getAction() == MotionEvent.ACTION_UP && (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w.getBottom())) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                }
            }
        }

        return ret;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDismissPopupWindow(PopupWindow popupWindow) {
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnActionTouch.performClick();
//                        btnActionTouch.requestFocus();
//                        btnActionTouch.requestFocusFromTouch();
                    }
                },50);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
