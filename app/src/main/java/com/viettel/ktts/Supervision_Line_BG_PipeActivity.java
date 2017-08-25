package com.viettel.ktts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Contruction_AcceptancePopup;
import com.viettel.actionbar.Contruction_UnqualifyPopup;
import com.viettel.actionbar.Edit_Text_Popup;
import com.viettel.actionbar.Header;
import com.viettel.actionbar.Image_ViewGalleryPopup;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Cat_Cause_Constr_AcceptanceController;
import com.viettel.database.Cat_Cause_Constr_UnQualifyController;
import com.viettel.database.Cause_Line_BG_PipeController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_BG_PipeController;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Line_Bg_PipeEntity;
import com.viettel.database.entity.Cause_Line_BG_PipeEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_PipeEntity;
import com.viettel.database.entity.Supervision_Line_BG_PipeGSEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Line_Bg_PipeField;
import com.viettel.database.field.Cause_Line_BG_PipeField;
import com.viettel.database.field.Supervision_Line_BG_PipeField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.LineBaseActivity;
import com.viettel.view.control.Supervision_Line_BG_PipeAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin thiet ke
 *
 * @author datht1
 */
public class Supervision_Line_BG_PipeActivity extends LineBaseActivity {
    /* controll */
    private View spvLineBG_PipeView;
    private ListView lv_line_bg_pipe_list;
    private TextView tv_line_bg_pipe_dropdown;
    private TextView tv_line_bg_pipe_info_line_station_code_value;
    private TextView tv_line_bg_pipe_info_line_value;
    private Button btn_line_bg_pipe_save;
    private Button btn_line_bg_pipe_add;
    private RelativeLayout rl_supervision_line_bg_pipe;

    /* Bien set Type Text */
    private int iField = 0;
    private Bundle bundleData;
    /* bien dropdown */
    private int iDesignInfo = 0;
    private List<DropdownItemEntity> listDesignInfo = null;
    private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
    private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
    private Edit_Text_Popup editTextPopup;
    //	private Image_ViewGalleryPopup imgViewPopup;
    /* Bien co so du lieu */
	/* Cong trinh giam sat */
    private Constr_Construction_EmployeeEntity itemConstrData;
    /* Tuyen ngam giam sat */
    private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
    /* Danh sach ong giam sat */
    private List<Supervision_Line_BG_PipeGSEntity> listSupervisionPipeGS;
    private List<Supervision_Line_BG_TankGSEntity> listSupervisionGS;
    private List<Supervision_Line_BG_PipeGSEntity> listSupervisionPipeDeleteGS;
    private Supervision_Line_BG_PipeController supervisionLBGPipeController;
    private Cause_Line_BG_PipeController causeLineBGPPipeController;
    private Supv_Constr_Attach_FileController supvConstrAttachFileController;
    private Supervision_Line_BG_TankController supervisionLBGController;
    /* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
    private List<Supervision_LBG_UnqualifyItemEntity> listPipeUnqualifyItem;
    /* Item ong cap dang sua dung popup */
    private Supervision_Line_BG_PipeGSEntity curEditItem = null;
    private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
    private Supervision_Line_BG_PipeAdapter supervisionPipeAdapter;
    private boolean outOfKey = false;
    private float positionTouch = 0f;

    private List<Supervision_LBG_UnqualifyItemEntity> listPipeAcceptanceItem;
    private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
    private Contruction_AcceptancePopup contruoctionAcceptancePopup;

    private TextView supervision_bg_pipe_complete_date;
    private Button rl_supervision_bg_pipe_bt_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_bg_pipe_activity);
        setTitle(getString(R.string.line_background_header_title));
		/* set controll */
        this.initView();
//		setHeader();
        this.setData();
    }

    private void initView() {
        spvLineBG_PipeView = addView(R.layout.supervision_line_bg_pipe_activity, R.id.rl_supervision_line_bg_pipe);
        this.lv_line_bg_pipe_list = (ListView) spvLineBG_PipeView
                .findViewById(R.id.lv_line_bg_pipe_list);
        this.tv_line_bg_pipe_dropdown = (TextView) spvLineBG_PipeView
                .findViewById(R.id.tv_line_bg_pipe_dropdown);
        this.tv_line_bg_pipe_dropdown.setOnClickListener(this);

        this.tv_line_bg_pipe_info_line_value = (TextView) spvLineBG_PipeView
                .findViewById(R.id.tv_line_bg_pipe_info_line_value);

        this.tv_line_bg_pipe_info_line_station_code_value = (TextView) spvLineBG_PipeView
                .findViewById(R.id.tv_line_bg_pipe_info_line_station_code_value);
        this.btn_line_bg_pipe_save = (Button) spvLineBG_PipeView
                .findViewById(R.id.btn_line_bg_pipe_save);
        this.btn_line_bg_pipe_save.setOnClickListener(this);
        this.btn_line_bg_pipe_add = (Button) spvLineBG_PipeView
                .findViewById(R.id.btn_line_bg_pipe_add);
        this.btn_line_bg_pipe_add.setOnClickListener(this);

        this.supervision_bg_pipe_complete_date = (TextView) spvLineBG_PipeView
                .findViewById(R.id.supervision_bg_pipe_complete_date);
        this.rl_supervision_bg_pipe_bt_complete = (Button) spvLineBG_PipeView
                .findViewById(R.id.rl_supervision_bg_pipe_bt_complete);
        this.rl_supervision_bg_pipe_bt_complete.setOnClickListener(this);

        this.rl_supervision_line_bg_pipe = (RelativeLayout) spvLineBG_PipeView.findViewById(R.id.rl_supervision_line_bg_pipe);

        this.rl_supervision_line_bg_pipe.getViewTreeObserver()
                .addOnGlobalLayoutListener(
                        new ViewTreeObserver.OnGlobalLayoutListener() {

                            @Override
                            public void onGlobalLayout() {
                                Rect r = new Rect();
                                rl_supervision_line_bg_pipe
                                        .getWindowVisibleDisplayFrame(r);

                                int screenHeight = rl_supervision_line_bg_pipe
                                        .getRootView().getHeight();
                                int heightDifference = screenHeight
                                        - (r.bottom - r.top);
                                int resourceId = getResources()
                                        .getIdentifier("status_bar_height",
                                                "dimen", "android");
                                if (resourceId > 0) {
                                    heightDifference -= getResources()
                                            .getDimensionPixelSize(resourceId);
                                }

                                if (heightDifference > 0) {
                                    if (positionTouch >= heightDifference) {
                                        rl_supervision_line_bg_pipe
                                                .setScrollY(0);
                                    } else {
                                        if ((positionTouch + heightDifference) > screenHeight) {
                                            rl_supervision_line_bg_pipe.setScrollY(heightDifference
                                                    - (screenHeight - (Math
                                                    .round(positionTouch) + heightDifference)));
                                        } else
                                            rl_supervision_line_bg_pipe
                                                    .setScrollY(heightDifference - 25);
                                    }
                                } else {
                                    rl_supervision_line_bg_pipe.setScrollY(0);
                                }

                            }
                        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        positionTouch = event.getY();

        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_supervision_bg_pipe_bt_complete:
                ConfirmDialog confirmSave = new ConfirmDialog(this,
                        StringUtil.getString(R.string.text_confirm_save),
                        OnEventControlListener.EVENT_COMPLETE_PROGRESS);
                confirmSave.show();
                break;
            case R.id.btn_line_bg_pipe_add:
                Supervision_Line_BG_PipeGSEntity addItem = new Supervision_Line_BG_PipeGSEntity();
                this.listSupervisionPipeGS.add(addItem);
                this.supervisionPipeAdapter.notifyDataSetChanged();
                break;
            case R.id.tv_line_bg_pipe_dropdown:
                this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
                        this.listDesignInfo);
                dropdownPopupMenuDesignInfo.show(v);
                break;
            case R.id.btn_line_bg_pipe_save:
                String messageError = "";
                for (Supervision_Line_BG_PipeGSEntity curItemData : listSupervisionPipeGS) {
                    messageError = this.checkVailid(curItemData);
                    if (!StringUtil.isNullOrEmpty(messageError)) {
                        break;
                    }
                }
                if (!StringUtil.isNullOrEmpty(messageError)) {
                    this.showDialog(messageError);
                } else {
                    // messageError = checkVailidPipeItem();
                    // if (!StringUtil.isNullOrEmpty(messageError)) {
                    // this.showDialog(messageError);
                    // } else {
                    confirmSave = new ConfirmDialog(this,
                            StringUtil.getString(R.string.text_confirm_save));
                    confirmSave.show();
                    // }
                }
                break;
            default:
                // this.hideKeyboard(v);
                break;
        }
    }

    private void setHeader() {
        final Header myActionBar = (Header) spvLineBG_PipeView.findViewById(R.id.actionbar);
        myActionBar.setTitle(GlobalInfo.getInstance().getFullName());
        // icon back
        myActionBar.setBackAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                gotoHomeActivity(new Bundle());
                finish();
            }

            @Override
            public int getDrawable() {
                return R.drawable.backicon;
            }
        });
        // buttom home
        myActionBar.addAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                gotoHomeActivity(new Bundle());
                finish();
            }

            @Override
            public int getDrawable() {
                return R.drawable.home;
            }
        });
		/* Dong bo du lieu */
        myActionBar.addAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                requestSync(Supervision_Line_BG_PipeActivity.this);
            }

            @Override
            public int getDrawable() {
                return R.drawable.icon_rotate;
            }
        });
        // buttom loguot
        myActionBar.addAction(new Header.Action() {
            @Override
            public void performAction(View view) {
                gotoLogOut();
            }

            @Override
            public int getDrawable() {
                return R.drawable.logout;
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onEvent(int eventType, View control, Object data) {
        switch (eventType) {
            case OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK:
                DropdownItemEntity dropdownItem = (DropdownItemEntity) data;
                String typeItem = dropdownItem.getType();
                if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_INFO)) {
                    if (this.iDesignInfo != dropdownItem.getId()) {
                        bundleData = new Bundle();
                        bundleData.putSerializable(IntentConstants.INTENT_DATA,
                                itemConstrData);
                        bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
                                supervision_Line_BackgroundData);
                        bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
                                dropdownItem.getId());
                        this.gotoLineBackgroupActivity(bundleData);
                        this.dropdownPopupMenuDesignInfo.dismiss();
                        this.finish();
                    } else {
                        this.dropdownPopupMenuDesignInfo.dismiss();
                    }
                }
                break;
            case OnEventControlListener.EVENT_CHOICE_ACCESS_DAT:
                this.curEditItem = (Supervision_Line_BG_PipeGSEntity) data;
                break;
            case OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT:
                this.curEditItem = (Supervision_Line_BG_PipeGSEntity) data;
                break;
		/* Chon nguyen nhan khong dat */
            case OnEventControlListener.EVENT_SUPERVISION_PIPE_EDIT:
                this.curEditItem = (Supervision_Line_BG_PipeGSEntity) data;
                this.iField = this.curEditItem.getIdField();
                switch (iField) {
                    case Constants.BG_PIPE_EDIT.FROM_TANK:
                        String sFromTankTextValue = this.curEditItem.getFromTank();
                        editTextPopup = new Edit_Text_Popup(this, null,
                                sFromTankTextValue, InputType.TYPE_CLASS_TEXT, false,
                                10);
                        editTextPopup.showAtCenter();
                        break;
                    case Constants.BG_PIPE_EDIT.TO_TANK:
                        String sToTankTextValue = this.curEditItem.getToTank();
                        editTextPopup = new Edit_Text_Popup(this, null,
                                sToTankTextValue, InputType.TYPE_CLASS_TEXT, false, 10);
                        editTextPopup.showAtCenter();
                        break;

                    case Constants.BG_PIPE_EDIT.UNQUALIFY:
                        // Gan gia tri nguyen nhan loi
                        if (this.curEditItem.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
                            this.setUnqualify();
                            contruoctionUnqualifyPopup = new Contruction_UnqualifyPopup(
                                    this, null, this.listPipeUnqualifyItem);
                            contruoctionUnqualifyPopup.showAtCenter();
                        } else if (this.curEditItem.getStatus() == Constants.TANK_STATUS.DAT) {
                            this.setAcceptance();
                            contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
                                    this, null, this.listPipeAcceptanceItem);
                            contruoctionAcceptancePopup.showAtCenter();

                        } else {
                            Toast.makeText(
                                    this,
                                    StringUtil
                                            .getString(R.string.constr_line_tank_unqualify_choice_message),
                                    Toast.LENGTH_SHORT).show();
                        }

                        break;
                    case Constants.BG_PIPE_EDIT.FAIL_DESC:
                        String sFailDescTextValue = this.curEditItem.getFailDesc();
                        editTextPopup = new Edit_Text_Popup(this, null,
                                sFailDescTextValue,
                                InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
                        editTextPopup.showAtCenter();
                        break;
                    case Constants.BG_PIPE_EDIT.DELETE:
                        this.showAskOptionDialog(StringUtil
                                .getString(R.string.text_delete_title), StringUtil
                                .getString(R.string.line_bg_pipe_delete_message));
                        break;
                    default:
                        break;
                }
                break;
		/* Dong anh nghiem thu */
            case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
                this.saveAcceptance();
                contruoctionAcceptancePopup.hidePopup();
                this.supervisionPipeAdapter.notifyDataSetChanged();
                this.curEditItem.setEdited(true);
                break;
		/* chon nghi nguyen nhan khong dat */
            case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
                this.saveUnqualify();
                contruoctionUnqualifyPopup.hidePopup();
                this.supervisionPipeAdapter.notifyDataSetChanged();
                this.curEditItem.setEdited(true);
                break;
            case OnEventControlListener.EVENT_SET_TEXT:
                String sSetTextValue = (String) data;
                switch (this.iField) {
                    case Constants.BG_PIPE_EDIT.FROM_TANK:
                        this.curEditItem.setFromTank(sSetTextValue);
                        break;
                    case Constants.BG_PIPE_EDIT.TO_TANK:
                        this.curEditItem.setToTank(sSetTextValue);
                        break;
                    // case Constants.BG_PIPE_EDIT.FROM_DISTANCE:
                    // if (StringUtil.isNullOrEmpty(sSetTextValue)) {
                    // this.showDialog(StringUtil
                    // .getString(R.string.text_empty_message));
                    // return;
                    // }
                    // this.curEditItem.setFromDistance(Long.parseLong(sSetTextValue));
                    // break;
                    // case Constants.BG_PIPE_EDIT.TO_DISTANCE:
                    // if (StringUtil.isNullOrEmpty(sSetTextValue)) {
                    // this.showDialog(StringUtil
                    // .getString(R.string.text_empty_message));
                    // return;
                    // }
                    // this.curEditItem.setToDistance(Long.parseLong(sSetTextValue));
                    // break;
                    case Constants.BG_PIPE_EDIT.FAIL_DESC:
                        this.curEditItem.setFailDesc(sSetTextValue);
                        break;
                    default:
                        break;
                }
                this.curEditItem.setEdited(true);
                this.supervisionPipeAdapter.notifyDataSetChanged();
                editTextPopup.hidePopup();
                break;
		/* Xu ly su kien anh */
            case OnEventControlListener.EVENT_UNQUALIFY_CHECK_UCHECK:
                Supervision_LBG_UnqualifyItemEntity unqualifyItem = (Supervision_LBG_UnqualifyItemEntity) data;
                if (unqualifyItem.isUnqulify()) {
                    unqualifyItem.setDelete(false);
                } else {
                    unqualifyItem.setDelete(true);
                }
                this.contruoctionUnqualifyPopup.refreshData();
                break;
            // chup anh nghiem thu
            case OnEventControlListener.EVENT_ACCEPTANCE_TAKE_PHOTO:
                this.curAcceptanceItem = (Supervision_LBG_UnqualifyItemEntity) data;
                List<ImageEntity> listImgView = new ArrayList<ImageEntity>();
			/*
			 * Neu anh moi duoc chup hien thi anh moi chup, khong hien thi anh
			 * san co
			 */

                // gan anh co san
                for (Supv_Constr_Attach_FileEntity itemAttach : curAcceptanceItem
                        .getLstAttachFile()) {
                    if (itemAttach != null
                            && itemAttach.getSupv_Constr_Attach_file_Id() > 0) {
                        ImageEntity addImgView = new ImageEntity();
                        addImgView.setId((int) itemAttach
                                .getSupv_Constr_Attach_file_Id());
                        addImgView.setUrl(GlobalInfo.getInstance().getFilePath()
                                + itemAttach.getFile_Path());
                        listImgView.add(addImgView);
                    }
                }
                // gan anh moi them hoac chup anh moi
                for (Supv_Constr_Attach_FileEntity itemNewAttachFile : curAcceptanceItem
                        .getLstNewAttachFile()) {
                    if (!StringUtil.isNullOrEmpty(itemNewAttachFile.getFile_Path())) {
                        ImageEntity addImgView = new ImageEntity();
                        addImgView.setId(-1);
                        addImgView.setUrl(itemNewAttachFile.getFile_Path());
                        listImgView.add(addImgView);
                    }
                }

                this.imgViewPopup = new Image_ViewGalleryPopup(this, null,
                        listImgView);
                this.imgViewPopup.hideShowButton();
                this.imgViewPopup.showAtCenter();
                break;
            case OnEventControlListener.EVENT_UNQUALIFY_TAKE_PHOTO:
                this.curUnqualifyItem = (Supervision_LBG_UnqualifyItemEntity) data;
                listImgView = new ArrayList<ImageEntity>();
                // Neu anh moi duoc chup hien thi anh moi chup, khong hien thi anh
                // san co
                // if (StringUtil.isNullOrEmpty(this.curUnqualifyItem
                // .getNewAttachFile().getFile_Path())) {
                // if (this.curUnqualifyItem.getAttachFile() != null
                // && this.curUnqualifyItem.getAttachFile()
                // .getSupv_Constr_Attach_file_Id() > 0
                // && !this.curUnqualifyItem.isDeleteImage()) {
                // ImageEntity addImgView = new ImageEntity();
                // addImgView.setId(1);
                // addImgView.setUrl(GlobalInfo.getInstance().getFilePath()
                // + this.curUnqualifyItem.getAttachFile()
                // .getFile_Path());
                // listImgView.add(addImgView);
                // }
                // } else {
                // ImageEntity addImgView = new ImageEntity();
                // addImgView.setId(1);
                // addImgView.setUrl(this.curUnqualifyItem.getNewAttachFile()
                // .getFile_Path());
                // listImgView.add(addImgView);
                // }
                // gan anh co san
                for (Supv_Constr_Attach_FileEntity itemAttach : curUnqualifyItem
                        .getLstAttachFile()) {
                    if (itemAttach != null
                            && itemAttach.getSupv_Constr_Attach_file_Id() > 0) {
                        ImageEntity addImgView = new ImageEntity();
                        addImgView.setId((int) itemAttach
                                .getSupv_Constr_Attach_file_Id());
                        addImgView.setUrl(GlobalInfo.getInstance().getFilePath()
                                + itemAttach.getFile_Path());
                        listImgView.add(addImgView);
                    }
                }
                // gan anh moi them hoac chup anh moi
                for (Supv_Constr_Attach_FileEntity itemNewAttachFile : curUnqualifyItem
                        .getLstNewAttachFile()) {
                    if (!StringUtil.isNullOrEmpty(itemNewAttachFile.getFile_Path())) {
                        ImageEntity addImgView = new ImageEntity();
                        addImgView.setId(-1);
                        addImgView.setUrl(itemNewAttachFile.getFile_Path());
                        listImgView.add(addImgView);
                    }
                }
                this.imgViewPopup = new Image_ViewGalleryPopup(this, null,
                        listImgView);
                this.imgViewPopup.hideShowButton();
                this.imgViewPopup.showAtCenter();
                break;
            case OnEventControlListener.EVENT_IMG_TAKE_EXIT:
                List<ImageEntity> lstData = (List<ImageEntity>) data;

                if (this.curEditItem.getStatus() == Constants.TANK_STATUS.DAT) {
                    this.curAcceptanceItem.getLstAttachFile().clear();
                    this.curAcceptanceItem.getLstNewAttachFile().clear();
                    for (ImageEntity imageEntity : lstData) {
                        Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
                        curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
                                .getId());
                        curAttachFile.setFile_Path(imageEntity.getUrl());
                        this.curAcceptanceItem.getLstNewAttachFile().add(
                                curAttachFile);
                    }
                    this.contruoctionAcceptancePopup.refreshData();
                } else if (this.curEditItem.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
                    this.curUnqualifyItem.getLstAttachFile().clear();
                    this.curUnqualifyItem.getLstNewAttachFile().clear();
                    for (ImageEntity imageEntity : lstData) {
                        Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
                        curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
                                .getId());
                        curAttachFile.setFile_Path(imageEntity.getUrl());
                        this.curUnqualifyItem.getLstNewAttachFile().add(
                                curAttachFile);
                    }
                    this.contruoctionUnqualifyPopup.refreshData();
                }

                this.imgViewPopup.hidePopup();

                break;
            case OnEventControlListener.EVENT_IMG_TAKE_NEW:
                this.takePhoto(itemConstrData);
                break;
            case OnEventControlListener.EVENT_IMG_TAKE_DELETE:

                this.imgViewPopup.deleteImageData();
                break;
            case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
                this.selectPhoto();
                break;
            case OnEventControlListener.EVENT_CONFIRM_OK:
                new SaveAsyncTask().execute();
                // this.saveDataPipe();
                // this.refreshListView();
                break;
            case OnEventControlListener.EVENT_COMPLETE_PROGRESS:
                saveCompleteDay(itemConstrData,
                        Constants.PROGRESS_TYPE.LINE_BACKGROUND,
                        Constants.PROGRESS_TYPE.BG_PIPE, outOfKey);
                showCompleteDate(itemConstrData,
                        Constants.PROGRESS_TYPE.LINE_BACKGROUND,
                        Constants.PROGRESS_TYPE.BG_PIPE,
                        supervision_bg_pipe_complete_date,
                        rl_supervision_bg_pipe_bt_complete);
                break;
            default:
                super.onEvent(eventType, control, data);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.TAKE_PICTURE_RESULT:
                if (resultCode == Activity.RESULT_OK) {
                    ImageEntity addImgView = new ImageEntity();
                    addImgView.setId(1);
                    addImgView.setUrl(imgUri.getPath());
                    // this.curUnqualifyItem.getNewAttachFile().setFile_Path(
                    // imgUri.getPath());
                    this.imgViewPopup.setImageData(addImgView);
                }
                break;
//		case Constants.SELECT_PICTURE_RESULT:
//			if (resultCode == Activity.RESULT_OK) {
//				Uri selectedImage = data.getData();
////				String[] filePathColumn = { MediaStore.Images.Media.DATA };
////				Cursor cursor = getContentResolver().query(selectedImage,
////						filePathColumn, null, null, null);
////				cursor.moveToFirst();
////				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
////				String picturePath = cursor.getString(columnIndex);
////				cursor.close();
//				String picturePath = StringUtil.getPath(this, selectedImage);
//				ImageEntity addImgView = new ImageEntity();
//				addImgView.setId(1);
//				addImgView.setUrl(picturePath);
//				// this.curUnqualifyItem.getNewAttachFile().setFile_Path(
//				// picturePath);
//				this.imgViewPopup.setImageData(addImgView);
//			}
//			break;

            default:
                break;
        }
    }

    private void setData() {
        try {

            bundleData = this.getIntent().getExtras();
            this.itemConstrData = (Constr_Construction_EmployeeEntity) bundleData
                    .getSerializable(IntentConstants.INTENT_DATA);
            this.supervision_Line_BackgroundData = (Supervision_Line_BackgroundEntity) bundleData
                    .getSerializable(IntentConstants.INTENT_DATA_EX);
            this.iDesignInfo = bundleData.getInt(IntentConstants.INTENT_DESIGNINFO);
            this.listSupervisionPipeDeleteGS = new ArrayList<Supervision_Line_BG_PipeGSEntity>();
            this.tv_line_bg_pipe_dropdown.setText(GloablUtils.getStringLineBackgroundInfo(iDesignInfo));
            supervisionLBGPipeController = new Supervision_Line_BG_PipeController(
                    this);
            causeLineBGPPipeController = new Cause_Line_BG_PipeController(this);
            supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
                    this);

            this.tv_line_bg_pipe_info_line_value.setText(this.itemConstrData
                    .getConstrCode());
            this.tv_line_bg_pipe_info_line_station_code_value
                    .setText(itemConstrData.getStationCode());
			/* Nguyen nhan khong dat du be */
            listPipeUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(
                    this).getAllUnQualifyItemByName(
                    Constants.UNQUALIFY_TYPE.BG_PIPE,
                    Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);

			/* nghiem thu */
            listPipeAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
                    this).getAllUnQualifyItemByName(
                    Constants.ACCEPTANCE_TYPE.BG_PIPE,
                    Constants.UNQUALIFY_TYPE.LINE_BACKGROUND);

			/* Drop down */
            this.listDesignInfo = GloablUtils.getListLineBackgroundInfo("");

            supervisionLBGController = new Supervision_Line_BG_TankController(
                    this);
            // lay danh sach be
            listSupervisionGS = supervisionLBGController
                    .getAllTankGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
                            .getSupervision_Line_Background_Id());

            if (listSupervisionGS.isEmpty()) {
                long iNumberTank = this.supervision_Line_BackgroundData
                        .getTank_New_Total()
                        + this.supervision_Line_BackgroundData
                        .getTank_Old_Total();
                for (int i = 0; i < iNumberTank; i++) {
                    Supervision_Line_BG_TankGSEntity curItem = new Supervision_Line_BG_TankGSEntity();
                    curItem.setIdTank(i + 1);
                    curItem.setTankName(GloablUtils.getTankName(i + 1));
                    curItem.setNew(true);
                    curItem.setEdited(false);
                    listSupervisionGS.add(curItem);
                }

            }

            this.refreshListView();

            showCompleteDate(itemConstrData,
                    Constants.PROGRESS_TYPE.LINE_BACKGROUND,
                    Constants.PROGRESS_TYPE.BG_PIPE,
                    supervision_bg_pipe_complete_date,
                    rl_supervision_bg_pipe_bt_complete);

            if (itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
                    || itemConstrData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
                this.btn_line_bg_pipe_save.setVisibility(View.GONE);
                // this.rl_supervision_bg_pipe_bt_complete.setVisibility(View.GONE);
                this.btn_line_bg_pipe_add.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            // handle exception
        }
    }

    private void refreshListView() {
        listSupervisionPipeGS = supervisionLBGPipeController
                .getAllPipeGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
                        .getSupervision_Line_Background_Id());
		/* Truong hop Load luu du lieu */
        if (listSupervisionPipeGS.size() == 0) {
        } else {
			/* Gan danh sach loi cho danh sach ong */
            for (Supervision_Line_BG_PipeGSEntity itemPipeGS : listSupervisionPipeGS) {
                List<Supervision_LBG_UnqualifyItemEntity> listLBGUnqualify = causeLineBGPPipeController
                        .getAllPipeUnqulifyByPipeId(itemPipeGS.getIdPipe());
                List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = this.causeLineBGPPipeController
                        .getAllTanAcceptByPipeId(itemPipeGS.getIdPipe());
				/* Gan anh nghiem thu */
                for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listAcceptItem) {
                    List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
                            .getLstAttachFile(
                                    Acceptance_Line_Bg_PipeField.TABLE_NAME,
                                    curUnqualifyItem.getCause_Line_Bg_Id());
                    for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                        curUnqualifyItem.getLstAttachFile().add(itemFile);
                    }

                }
                itemPipeGS.setListAcceptance(listAcceptItem);
				/* Gan anh nguyen nhan loi */
                for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listLBGUnqualify) {
                    List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
                            .getLstAttachFile(
                                    Cause_Line_BG_PipeField.TABLE_NAME,
                                    curUnqualifyItem.getCause_Line_Bg_Id());
                    for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                        curUnqualifyItem.getLstAttachFile().add(itemFile);
                    }
                }
                itemPipeGS.setListCauseUniQualify(listLBGUnqualify);
            }
        }
        this.supervisionPipeAdapter = new Supervision_Line_BG_PipeAdapter(this,
                listSupervisionPipeGS);
        this.lv_line_bg_pipe_list.setAdapter(supervisionPipeAdapter);
        this.lv_line_bg_pipe_list.setItemsCanFocus(true);
    }

    private void saveDataPipe() {
		/* Tinh toan gia tri */
        try {
            long idUser = GlobalInfo.getInstance().getUserId();
            for (Supervision_Line_BG_PipeGSEntity curItemAdd : listSupervisionPipeGS) {
                if (curItemAdd.isNew() || curItemAdd.isEdited()) {
                    long idPipe = 0;
                    Supervision_Line_BG_PipeEntity itemAddPipe = new Supervision_Line_BG_PipeEntity();
                    itemAddPipe
                            .setSupervision_Line_Background_Id(this.supervision_Line_BackgroundData
                                    .getSupervision_Line_Background_Id());
                    itemAddPipe.setFromTank(curItemAdd.getFromTank());
                    itemAddPipe.setToTank(curItemAdd.getToTank());
                    itemAddPipe.setFromDistance(curItemAdd.getFromDistance());
                    itemAddPipe.setToDistance(curItemAdd.getToDistance());
                    itemAddPipe.setFail_Desc(curItemAdd.getFailDesc());
                    itemAddPipe.setStatus(curItemAdd.getStatus());
                    if (curItemAdd.isNew()) {
                        itemAddPipe.setSync_Status(Constants.SYNC_STATUS.ADD);
                        itemAddPipe.setIsActive(Constants.ISACTIVE.ACTIVE);
                        itemAddPipe.setProcessId(0);
                        itemAddPipe.setEmployeeId(idUser);

                        idPipe = Ktts_KeyController
                                .getInstance()
                                .getKttsNextKey(
                                        Supervision_Line_BG_PipeField.TABLE_NAME);

                        if (idPipe == 0) {
                            outOfKey = true;
                            return;
                        } else
                            outOfKey = false;

                        itemAddPipe.setSupervision_Line_Bg_Pipe_Id(idPipe);
                        itemAddPipe
                                .setSupervisionConstrId(supervision_Line_BackgroundData
                                        .getSupervisionConstrId());
                        supervisionLBGPipeController.addItem(itemAddPipe);

                        if (itemAddPipe.getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
							/* Tao moi nguyen nhan loi */
                            List<Supervision_LBG_UnqualifyItemEntity> listCauseUnqualify = curItemAdd
                                    .getListCauseUniQualify();
                            for (Supervision_LBG_UnqualifyItemEntity curUnqulifyAdd : listCauseUnqualify) {
                                Cause_Line_BG_PipeEntity causeUnqualifyAdd = new Cause_Line_BG_PipeEntity();
                                causeUnqualifyAdd
                                        .setCat_Cause_Constr_Unqualify_Id(curUnqulifyAdd
                                                .getCat_Cause_Constr_Unqualify_Id());
                                causeUnqualifyAdd
                                        .setSupervision_Line_Bg_Pipe_Id(idPipe);

                                causeUnqualifyAdd
                                        .setSync_Status(Constants.SYNC_STATUS.ADD);
                                causeUnqualifyAdd
                                        .setIsActive(Constants.ISACTIVE.ACTIVE);
                                causeUnqualifyAdd.setProcessId(0);
                                causeUnqualifyAdd.setEmployeeId(idUser);
                                causeUnqualifyAdd
                                        .setSupervisionConstrId(supervision_Line_BackgroundData
                                                .getSupervision_Constr_Id());

                                long iAddCause = Ktts_KeyController
                                        .getInstance()
                                        .getKttsNextKey(
                                                Cause_Line_BG_PipeField.TABLE_NAME);

                                if (iAddCause == 0) {
                                    outOfKey = true;
                                    return;
                                } else
                                    outOfKey = false;
                                causeUnqualifyAdd
                                        .setCause_Line_Bg_Pipe_Id(iAddCause);

                                this.causeLineBGPPipeController
                                        .addItem(causeUnqualifyAdd);
                                // Neu chon moi anh thi ghi file moi vao
                                for (Supv_Constr_Attach_FileEntity itemFile : curUnqulifyAdd
                                        .getLstNewAttachFile()) {
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.itemConstrData,
                                                        itemFile.getFile_Path(),
                                                        iAddCause,
                                                        Cause_Line_BG_PipeField.TABLE_NAME);

                                    }
                                }
                            }
                        } else {
                            // neu dat thi save anh nghiem thu
                            List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = curItemAdd
                                    .getListAcceptance();
                            for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
                                Acceptance_Line_Bg_PipeEntity addCauseItem = new Acceptance_Line_Bg_PipeEntity();
                                addCauseItem
                                        .setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
                                                .getCat_Cause_Constr_Acceptance_Id());
                                addCauseItem
                                        .setSupervision_Line_Bg_Pipe_Id(idPipe);
                                addCauseItem
                                        .setSync_Status(Constants.SYNC_STATUS.ADD);
                                addCauseItem
                                        .setIsActive(Constants.ISACTIVE.ACTIVE);
                                addCauseItem.setProcessId(0);
                                addCauseItem.setEmployeeId(idUser);

                                long iAddCause = Ktts_KeyController
                                        .getInstance()
                                        .getKttsNextKey(
                                                Acceptance_Line_Bg_PipeField.TABLE_NAME);
                                if (iAddCause == 0) {
                                    outOfKey = true;
                                    return;
                                } else
                                    outOfKey = false;

                                addCauseItem
                                        .setAcceptance_Line_Bg_Pipe_Id(iAddCause);
                                this.causeLineBGPPipeController
                                        .addItem(addCauseItem);

                                for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
                                        .getLstNewAttachFile()) {
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.itemConstrData,
                                                        itemFile.getFile_Path(),
                                                        iAddCause,
                                                        Acceptance_Line_Bg_PipeField.TABLE_NAME);

                                    }
                                }
                            }
                        }

                    } else {
                        idPipe = Integer.parseInt(String.valueOf(curItemAdd
                                .getIdPipe()));
                        curItemAdd.setSync_Status(Constants.SYNC_STATUS.ADD);
                        supervisionLBGPipeController
                                .updateAllColumn(curItemAdd);
						/* 2. Cap nhat nguyen nhan loi */
                        List<Supervision_LBG_UnqualifyItemEntity> listAddCause = curItemAdd
                                .getListCauseUniQualify();
                        List<Supervision_LBG_UnqualifyItemEntity> listAddAccept = curItemAdd
                                .getListAcceptance();

                        for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddAccept) {
							/* 1. Chinh sua nghiem thu */
                            if (addItemCause.getCause_Line_Bg_Id() > 0) {

                                // xoa nghiem thu khi chuyen
                                // trang thai tu khong dat sang dat
                                // if (curItemAdd.getStatus() ==
                                // Constants.SUPERVISION_STATUS.CHUADAT) {
                                // this.causeLineBGPPipeController
                                // .deleteAccept(addItemCause);
                                // }

                                if (addItemCause.getLstNewAttachFile().size() > 0
                                        || (addItemCause.getLstNewAttachFile()
                                        .size() == 0 && addItemCause
                                        .getLstAttachFile().size() == 0)) {

                                    List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
                                            .getLstAttachFile(
                                                    Acceptance_Line_Bg_PipeField.TABLE_NAME,
                                                    addItemCause
                                                            .getCause_Line_Bg_Id());
                                    // xoa anh cu di

                                    for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                                        supvConstrAttachFileController
                                                .delete(itemFile);
                                    }
                                }

                                for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                        .getLstNewAttachFile()) {

                                    // them anh moi vao
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.itemConstrData,
                                                        itemFile.getFile_Path(),
                                                        addItemCause
                                                                .getCause_Line_Bg_Id(),
                                                        Acceptance_Line_Bg_PipeField.TABLE_NAME);

                                    }
                                }

                            }
							/* 2. Them moi nghiem thu */
                            else {
                                if (curItemAdd.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
                                    Acceptance_Line_Bg_PipeEntity addCauseItem = new Acceptance_Line_Bg_PipeEntity();
                                    addCauseItem
                                            .setCat_Cause_Constr_Acceptance_Id(addItemCause
                                                    .getCat_Cause_Constr_Acceptance_Id());
                                    addCauseItem
                                            .setSupervision_Line_Bg_Pipe_Id(curItemAdd
                                                    .getIdPipe());
                                    addCauseItem
                                            .setSync_Status(Constants.SYNC_STATUS.ADD);
                                    addCauseItem
                                            .setIsActive(Constants.ISACTIVE.ACTIVE);
                                    addCauseItem.setProcessId(0);
                                    addCauseItem.setEmployeeId(idUser);

                                    long iAddCause = Ktts_KeyController
                                            .getInstance()
                                            .getKttsNextKey(
                                                    Acceptance_Line_Bg_PipeField.TABLE_NAME);

                                    if (iAddCause == 0) {
                                        outOfKey = true;
                                        return;
                                    } else
                                        outOfKey = false;

                                    addCauseItem
                                            .setAcceptance_Line_Bg_Pipe_Id(iAddCause);
                                    this.causeLineBGPPipeController
                                            .addItem(addCauseItem);
                                    // Luu anh nguyen nhan loi neu co
                                    for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                            .getLstNewAttachFile()) {
                                        if (!StringUtil.isNullOrEmpty(itemFile
                                                .getFile_Path())) {
                                            this.supvConstrAttachFileController
                                                    .coppyAndAddAttachFile(
                                                            this.itemConstrData,
                                                            itemFile.getFile_Path(),
                                                            iAddCause,
                                                            Acceptance_Line_Bg_PipeField.TABLE_NAME);

                                        }
                                    }
                                }

                            }
                        }

                        for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddCause) {
							/*
							 * 1. Chinh sua nguyen nhan khong dat( Khong sua lai
							 * cac gia tri dong bo)
							 */
                            if (addItemCause.getCause_Line_Bg_Id() > 0) {
								/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
                                if (addItemCause.isDelete()) {
                                    this.causeLineBGPPipeController
                                            .delete(addItemCause);
                                }
								/* 1.2. Update lai nguyen nhan khong dat */
                                else {
                                    // if (curItemAdd.getStatus() ==
                                    // Constants.SUPERVISION_STATUS.DAT) {
                                    // this.causeLineBGPPipeController
                                    // .delete(addItemCause);
                                    // }

                                    if (addItemCause.getLstNewAttachFile()
                                            .size() > 0
                                            || (addItemCause
                                            .getLstNewAttachFile()
                                            .size() == 0 && addItemCause
                                            .getLstAttachFile().size() == 0)) {
                                        List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
                                                .getLstAttachFile(
                                                        Cause_Line_BG_PipeField.TABLE_NAME,
                                                        addItemCause
                                                                .getCause_Line_Bg_Id());
                                        // xoa anh cu di

                                        for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
                                            supvConstrAttachFileController
                                                    .delete(itemFile);
                                        }
                                    }

                                    for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                            .getLstNewAttachFile()) {

                                        // them anh moi vao
                                        if (!StringUtil.isNullOrEmpty(itemFile
                                                .getFile_Path())) {
                                            this.supvConstrAttachFileController
                                                    .coppyAndAddAttachFile(
                                                            this.itemConstrData,
                                                            itemFile.getFile_Path(),
                                                            addItemCause
                                                                    .getCause_Line_Bg_Id(),
                                                            Cause_Line_BG_PipeField.TABLE_NAME);

                                        }
                                    }
									/* 1.2.1 Thay doi anh */
                                    // if
                                    // (!StringUtil.isNullOrEmpty(addItemCause
                                    // .getNewAttachFile().getFile_Path())) {
                                    // long idAttachFile = addItemCause
                                    // .getAttachFile()
                                    // .getSupv_Constr_Attach_file_Id();
                                    // /*
                                    // * Neu da ton tai file luu trong bang
                                    // * attachment thi chi thay doi ten va
                                    // * duong dan
                                    // */
                                    // if (idAttachFile > 0) {
                                    //
                                    // //cho getFileName co the van
                                    // // trung can xem lai
                                    // String sFileName = FileManager
                                    // .getFileName();
                                    //
                                    // String sFilePath = FileManager
                                    // .getSaveFilePath(
                                    // String.valueOf(this.itemConstrData
                                    // .getConstructId()),
                                    // sFileName);
                                    //
                                    // FileManager.coppyFile(addItemCause
                                    // .getNewAttachFile()
                                    // .getFile_Path(), sFilePath);
                                    //
                                    // supvConstrAttachFileController
                                    // .updatePathNameFile(
                                    // idAttachFile,
                                    // sFilePath,
                                    // sFileName,
                                    // addItemCause
                                    // .getAttachFile()
                                    // .getSync_Status());
                                    // }
                                    // /* Khong ton tai file thi lai them moi */
                                    // else {
                                    // this.supvConstrAttachFileController
                                    // .coppyAndAddAttachFile(
                                    // this.itemConstrData,
                                    // addItemCause
                                    // .getNewAttachFile()
                                    // .getFile_Path(),
                                    // addItemCause
                                    // .getCause_Line_Bg_Id(),
                                    // Cause_Line_BG_PipeField.TABLE_NAME);
                                    // }
                                    // } else {
                                    // /* 1.2.2 Xoa anh */
                                    // if (addItemCause.isDeleteImage()) {
                                    // supvConstrAttachFileController
                                    // .delete(addItemCause
                                    // .getAttachFile());
                                    // }
                                    // }
                                }
                            }
							/* 2. Them moi nguyen nhan khong dat */
                            else {
                                Cause_Line_BG_PipeEntity addCauseItem = new Cause_Line_BG_PipeEntity();
                                addCauseItem
                                        .setCat_Cause_Constr_Unqualify_Id(addItemCause
                                                .getCat_Cause_Constr_Unqualify_Id());
                                addCauseItem
                                        .setSupervision_Line_Bg_Pipe_Id(curItemAdd
                                                .getIdPipe());
                                addCauseItem
                                        .setSync_Status(Constants.SYNC_STATUS.ADD);
                                addCauseItem
                                        .setIsActive(Constants.ISACTIVE.ACTIVE);
                                addCauseItem.setProcessId(0);
                                addCauseItem.setEmployeeId(idUser);
                                addCauseItem
                                        .setSupervisionConstrId(supervision_Line_BackgroundData
                                                .getSupervision_Constr_Id());

                                long iAddCause = Ktts_KeyController
                                        .getInstance()
                                        .getKttsNextKey(
                                                Cause_Line_BG_PipeField.TABLE_NAME);

                                if (iAddCause == 0) {
                                    outOfKey = true;
                                    return;
                                } else
                                    outOfKey = false;

                                addCauseItem
                                        .setCause_Line_Bg_Pipe_Id(iAddCause);
                                this.causeLineBGPPipeController
                                        .addItem(addCauseItem);
                                // Luu anh nguyen nhan loi neu co
                                // if (!StringUtil.isNullOrEmpty(addItemCause
                                // .getNewAttachFile().getFile_Path())) {
                                // this.supvConstrAttachFileController
                                // .coppyAndAddAttachFile(
                                // this.itemConstrData,
                                // addItemCause
                                // .getNewAttachFile()
                                // .getFile_Path(),
                                // iAddCause,
                                // Cause_Line_BG_PipeField.TABLE_NAME);
                                // }
                                for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
                                        .getLstNewAttachFile()) {
                                    if (!StringUtil.isNullOrEmpty(itemFile
                                            .getFile_Path())) {
                                        this.supvConstrAttachFileController
                                                .coppyAndAddAttachFile(
                                                        this.itemConstrData,
                                                        itemFile.getFile_Path(),
                                                        iAddCause,
                                                        Cause_Line_BG_PipeField.TABLE_NAME);

                                    }
                                }
                            }
                        }
                    }
                }
            }
			/* Xoa ong duoc danh dau xoa */
            for (Supervision_Line_BG_PipeGSEntity curItemDelete : listSupervisionPipeDeleteGS) {
                supervisionLBGPipeController.delete(curItemDelete.getIdPipe(),
                        curItemDelete.getSync_Status());
            }
            this.listSupervisionPipeDeleteGS.clear();

            // cap nhat trang thai cong trinh
            Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
                    this);
            constr_Controller.updateSyncStatus(itemConstrData
                    .getSupervision_Constr_Id());

            // TODO: Thiet lap ket luan. DanhDue ExOICTIF
            bundleData = this.getIntent().getExtras();
            boolean bDeny = checkCauseDenyBackgroundLine(bundleData);
            Log.i("Check_Deny", String.valueOf(bDeny));
            if (bDeny) {
                constr_Controller.updateStatus(
                        itemConstrData.getSupervision_Constr_Id(), 0);
            } else {
                constr_Controller.updateStatus(
                        itemConstrData.getSupervision_Constr_Id(), 1);
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
            // Toast.makeText(this,
            // StringUtil.getString(R.string.text_update_error),
            // Toast.LENGTH_SHORT).show();
            // this.showDialog(StringUtil.getString(R.string.text_update_error));
        }
    }

    /* Xoa ong sau khi confirm */
    @Override
    public void actionBeforAccept() {
        super.actionBeforAccept();
        if (this.curEditItem.getIdPipe() > 0) {
            this.listSupervisionPipeDeleteGS.add(curEditItem);
        }
        this.listSupervisionPipeGS.remove(this.curEditItem);
        this.supervisionPipeAdapter.notifyDataSetChanged();
    }

    private String checkVailid(Supervision_Line_BG_PipeGSEntity itemCheck) {
        String sReslut = "";
        try {
            int countNnkdCheck = 0;

            for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
                if (!itemCheck.getListCauseUniQualify().get(i).isDelete()) {
                    countNnkdCheck++;
                    break;
                }
            }
            if (StringUtil.isNullOrEmpty(itemCheck.getFromTank())) {
                sReslut = StringUtil
                        .getString(R.string.line_bg_pipe_unvalid_message);
                return sReslut;
            }
            if (itemCheck.getFromDistance() < 0) {
                sReslut = StringUtil
                        .getString(R.string.line_bg_pipe_unvalid_message);
                return sReslut;
            }
            if (StringUtil.isNullOrEmpty(itemCheck.getToTank())) {
                sReslut = StringUtil
                        .getString(R.string.line_bg_pipe_unvalid_message);
                return sReslut;
            }
            if (itemCheck.getToDistance() < 0) {
                sReslut = StringUtil
                        .getString(R.string.line_bg_pipe_unvalid_message);
                return sReslut;
            }
            if (itemCheck.getFromDistance() > itemCheck.getToDistance()) {
                sReslut = StringUtil
                        .getString(R.string.line_hang_cable_fromdis_less_todis)
                        + " " + (listSupervisionPipeGS.indexOf(itemCheck) + 1);

                return sReslut;
            }
            if (itemCheck.getFromTank().equalsIgnoreCase(itemCheck.getToTank())) {
                sReslut = StringUtil
                        .getString(R.string.line_bg_pipe_fromTank_diffirence_toTank)
                        + " " + (listSupervisionPipeGS.indexOf(itemCheck) + 1);
                return sReslut;
            }
            int countCheckFromTank = 0;
            int countCheckToTank = 0;
            for (Supervision_Line_BG_TankGSEntity itemTank : listSupervisionGS) {
                if (!itemCheck.getFromTank().equalsIgnoreCase(
                        itemTank.getTankName())) {
                    countCheckFromTank++;
                }
            }

            for (Supervision_Line_BG_TankGSEntity itemTank : listSupervisionGS) {
                if (!itemCheck.getToTank().equalsIgnoreCase(
                        itemTank.getTankName())) {
                    countCheckToTank++;
                }
            }

            if (countCheckFromTank == (listSupervisionGS.size())
                    || countCheckToTank == (listSupervisionGS.size())) {
                sReslut = StringUtil
                        .getString(R.string.line_bg_pipe_fromTank_toTank_wrong)
                        + " " + (listSupervisionPipeGS.indexOf(itemCheck) + 1);
                ;
                return sReslut;
            }
            if (itemCheck.getStatus() == Constants.ID_ENTITY_DEFAULT) {
                sReslut = StringUtil
                        .getString(R.string.constr_line_pipe_danhgia_empty);
                return sReslut;
            }
            if (itemCheck.getStatus() == Constants.TANK_STATUS.KHONG_DAT
                    && countNnkdCheck < 1) {
                sReslut = StringUtil
                        .getString(R.string.constr_line_special_nn_khongdat_tempty);
                return sReslut;
            }
            int countAcceptCheck = 0;

            for (int i = 0; i < itemCheck.getListAcceptance().size(); i++) {
                if ((itemCheck.getListAcceptance().get(i).getLstNewAttachFile()
                        .size() > 0 || itemCheck.getListAcceptance().get(i)
                        .getLstAttachFile().size() > 0)
                        && (i == 0)) {
                    countAcceptCheck++;
                }
            }
            if (itemCheck.getStatus() == Constants.TANK_STATUS.DAT
                    && countAcceptCheck < 1) {
                sReslut = StringUtil
                        .getString(R.string.constr_take_acceptance_not_enough);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sReslut;
    }

    // private String checkVailidPipeItem() {
    // String sReslut = "";
    //
    // for (int i = 0, size = this.listSupervisionPipeGS.size(); i < size; i++)
    // {
    // long toDistance = listSupervisionPipeGS.get(i).getToDistance();
    //
    // // kiem tra xem den khoang truoc co bang tu khoang sau hay khong
    // if ((i) < (size - 1)) {
    // long fromDistance = listSupervisionPipeGS.get(i + 1)
    // .getFromDistance();
    // if (checkToDisBeforeEqualFromDisLast(toDistance, fromDistance)) {
    // return (StringUtil
    // .getString(R.string.line_hang_cable_todisbefore)
    // + " "
    // + (i + 1)
    // + " "
    // + StringUtil
    // .getString(R.string.line_hang_cable_not_equal)
    // + " "
    // + StringUtil
    // .getString(R.string.line_hang_cable_fromdislast)
    // + " " + (i + 2));
    // }
    // }
    //
    // // neu la phan tu cuoi cung thi kiem tra den khoang co bang tong
    // // chieu dai tuyen hay khong
    // if (i == (size - 1)
    // && listSupervisionPipeGS.get(i).getToDistance() !=
    // supervision_Line_BackgroundData
    // .getLong_Total()) {
    // return StringUtil
    // .getString(R.string.line_hang_cable_todislast_eual_longtotal);
    // }
    // }
    // return sReslut;
    // }

	/* Ghi nguyen nhan khong dat vao danh sach ong */
	/* 1. Tim nguyen nhan khong dat trong danh sach */

    /* Ghi nghiem thu vao danh sach ong */
    private void saveAcceptance() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListAcceptance();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPipeAcceptanceItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseAcceptFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Acceptance_Id());
            if (curItem == null) {
				/* Them moi */
                Supervision_LBG_UnqualifyItemEntity addItem = new Supervision_LBG_UnqualifyItemEntity();
                addItem.setCat_Cause_Constr_Acceptance_Id(curCauseUniqualify
                        .getCat_Cause_Constr_Acceptance_Id());
                addItem.setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);

                addItem.setLstNewAttachFile(curCauseUniqualify
                        .getLstNewAttachFile());
                addItem.setTitle(curCauseUniqualify.getTitle());
                curListUnqualify.add(addItem);
            }
        }
        Log.i("", "");
    }

    /**
     * Ham set lai nghiem thu cho 1 be trong list danh sach nguyen nhan loi
     */
    private void setAcceptance() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListAcceptance();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPipeAcceptanceItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseAcceptFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Acceptance_Id());
            if (curItem == null) {
                curCauseUniqualify.setUnqulify(false);
                curCauseUniqualify.setDeleteImage(false);
                curCauseUniqualify
                        .setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
            } else {
                curCauseUniqualify.setUnqulify(curItem.isUnqulify());
                curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
                curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
                curCauseUniqualify.setLstNewAttachFile(curItem
                        .getLstNewAttachFile());
                curCauseUniqualify.setDelete(curItem.isDelete());
                curCauseUniqualify.setCause_Line_Bg_Id(curItem
                        .getCause_Line_Bg_Id());
            }
        }
    }

    private void saveUnqualify() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPipeUnqualifyItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());
            if (curItem == null) {
				/* Them moi */
                if (curCauseUniqualify.isUnqulify()) {
                    Supervision_LBG_UnqualifyItemEntity addItem = new Supervision_LBG_UnqualifyItemEntity();
                    addItem.setCat_Cause_Constr_Unqualify_Id(curCauseUniqualify
                            .getCat_Cause_Constr_Unqualify_Id());
                    addItem.setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
                    addItem.setUnqulify(true);
                    // addItem.setNewAttachFile(curCauseUniqualify
                    // .getNewAttachFile());
                    addItem.setLstNewAttachFile(curCauseUniqualify
                            .getLstNewAttachFile());
                    addItem.setTitle(curCauseUniqualify.getTitle());
                    curListUnqualify.add(addItem);
                }
            } else {
                if (curCauseUniqualify.isUnqulify()) {
                    curItem.setUnqulify(true);
                    curItem.setDelete(curCauseUniqualify.isDelete());
                    curItem.setDeleteImage(curCauseUniqualify.isDeleteImage());

                } else {
					/* Danh dau xoa nguyen nhan khong dat */
                    curItem.setDelete(true);
                }
            }
        }
    }

    private void setUnqualify() {
        List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
                .getListCauseUniQualify();
        for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPipeUnqualifyItem) {
            Supervision_LBG_UnqualifyItemEntity curItem = this
                    .getCauseUnqualifyFromList(curListUnqualify,
                            curCauseUniqualify
                                    .getCat_Cause_Constr_Unqualify_Id());
            if (curItem == null) {
                curCauseUniqualify.setUnqulify(false);
                // curCauseUniqualify
                // .setAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify.setDeleteImage(false);
                curCauseUniqualify
                        .setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                curCauseUniqualify
                        .setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
                // curCauseUniqualify
                // .setNewAttachFile(new Supv_Constr_Attach_FileEntity());
                curCauseUniqualify
                        .setCause_Line_Bg_Id(Constants.ID_ENTITY_DEFAULT);
            } else {
                curCauseUniqualify.setUnqulify(curItem.isUnqulify());
                // curCauseUniqualify.setAttachFile(curItem.getAttachFile());
                curCauseUniqualify.setDeleteImage(curItem.isDeleteImage());
                // curCauseUniqualify.setNewAttachFile(curItem.getNewAttachFile());
                curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
                curCauseUniqualify.setLstNewAttachFile(curItem
                        .getLstNewAttachFile());
                curCauseUniqualify.setDelete(curItem.isDelete());
                curCauseUniqualify.setCause_Line_Bg_Id(curItem
                        .getCause_Line_Bg_Id());
            }
        }
    }

    /* Lay nghiem thu tu id */
    public Supervision_LBG_UnqualifyItemEntity getCauseAcceptFromList(
            List<Supervision_LBG_UnqualifyItemEntity> listData,
            long idCauseAccept) {
        for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listData) {
            if (curUnqualifyItem.getCat_Cause_Constr_Acceptance_Id() == idCauseAccept) {
                return curUnqualifyItem;
            }
        }
        return null;
    }

    public Supervision_LBG_UnqualifyItemEntity getCauseUnqualifyFromList(
            List<Supervision_LBG_UnqualifyItemEntity> listData,
            long idCauseUnqualify) {
        for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listData) {
            if (curUnqualifyItem.getCat_Cause_Constr_Unqualify_Id() == idCauseUnqualify) {
                return curUnqualifyItem;
            }
        }
        return null;
    }

//	private void requestSync() {
//		if (this.check3GWifi()) {
//			showProgressDialog(StringUtil.getString(R.string.text_sync_loading));
//			Bundle bundle = new Bundle();
//			ActionEvent e = new ActionEvent();
//			e.action = ActionEventConstant.REQEST_SYNC;
//			e.viewData = bundle;
//			e.isBlockRequest = true;
//			e.sender = this;
//			Home_Controller.getInstance().handleViewEvent(e);
//		} else {
//			this.show3GWifiOffline();
//		}
//	}

    @Override
    public void handleModelViewEvent(ModelEvent modelEvent) {
        ActionEvent e = modelEvent.getActionEvent();
        switch (e.action) {
            case ActionEventConstant.REQEST_SYNC:
                refreshListView();
//			closeProgressDialog();
                SyncTask.closeDialog();
                Toast.makeText(this,
                        StringUtil.getString(R.string.text_sync_success),
                        Toast.LENGTH_LONG).show();
                break;
            default:
                super.handleModelViewEvent(modelEvent);
                break;
        }

    }

    class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // confirmSave.dismiss();
            showProgressDialog(StringUtil.getString(R.string.text_progcessing));
        }

        @Override
        protected Void doInBackground(Void... params) {
            saveDataPipe();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (outOfKey) {
                Toast.makeText(Supervision_Line_BG_PipeActivity.this,
                        StringUtil.getString(R.string.text_out_of_key),
                        Toast.LENGTH_SHORT).show();
                closeProgressDialog();
                return;
            }

            refreshListView();

            Toast.makeText(Supervision_Line_BG_PipeActivity.this,
                    StringUtil.getString(R.string.text_update_success),
                    Toast.LENGTH_SHORT).show();
            closeProgressDialog();
        }

    }
}