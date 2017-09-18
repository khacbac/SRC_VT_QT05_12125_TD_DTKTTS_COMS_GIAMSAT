package com.viettel.ktts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
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
import com.viettel.database.Cat_Supervision_Constr_WorkController;
import com.viettel.database.Cause_Bts_Cat_WorkController;
import com.viettel.database.Cause_Bts_Power_PoleController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_Cat_WorkController;
import com.viettel.database.Supervision_Bts_Power_PoleController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Cause_Bts_Power_PoleEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Bts_Cat_WorkField;
import com.viettel.database.field.Cause_Bts_Cat_WorkField;
import com.viettel.database.field.Cause_Bts_Power_PoleField;
import com.viettel.database.field.Supervision_Bts_Cat_WorkField;
import com.viettel.database.field.Supervision_Bts_Power_PoleField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.control.Bts_PowerPoleAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

public class SupervisionBtsPowerPoleActivity extends SupervisionBtsBaseActivity
		implements OnTabChangeListener {
	private static final String TAG = SupervisionBtsPowerPoleActivity.class.getSimpleName();
	private View spvBts_PowerPollView;
	/**
	 * text view
	 */
	private TextView supervision_bts_tv_matram;
	private TextView supervision_bts_tv_mact;

	/**
	 * edit text
	 */
	private EditText supervision_bts_power_pole_et_socot_moi;
	private EditText supervision_bts_power_pole_et_loaicap;

	/**
	 * combobox
	 */
	// combobox loai thong tin
	private TextView supervision_bts_power_pole_tv_thietke;

	// combobox danh gia chat luong thi cong keo dien
	private TextView supervision_bts_power_pole_tv_status;

	// combobox chon diem dau
	private TextView supervision_bts_power_pole_tv_diemdau;

	// dien giai thi cong keo dien
	private EditText supervision_bts_power_pole_et_diengiai;

	/**
	 * button luu
	 */
	private Button save;

	/**
	 * button de them truong danh gia cot
	 */
	private ImageButton supervision_bts_power_pole_add;

	/**
	 * list view
	 */
	private ListView listview;

	private int isInfomation;
	private int isStatus = -1;
	private int isDiemdau = -1;

	private int tabHeight = 50;
	private int countNnkdCheckKdd;

	private List<DropdownItemEntity> listInfomation = null;
	private List<DropdownItemEntity> listStatus = null;
	private List<DropdownItemEntity> listDiemdau = null;

	private Menu_DropdownPopup dropdownPopupMenuInfomation;
	private Menu_DropdownPopup dropdownPopupMenuStatus;
	private Menu_DropdownPopup dropdownPopupMenuDiemdau;

//	private Image_ViewGalleryPopup imgViewPopup;

	private Contruction_UnqualifyPopup contructionUnqualifyPopup;
	String sChoice;

	// combobox nguyen nhan khong dat thi cong keo dien
	private TextView supervision_bts_power_pole_tv_nnkd;

	private Constr_Construction_EmployeeEntity constr_ConstructionItem;
	private Supervision_BtsEntity btsEntity;

	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Cause_Bts_Power_PoleEntity curEditItem = null;

	private Bts_PowerPoleAdapter bts_PowerPoleAdapter;
	private List<Cause_Bts_Power_PoleEntity> listData;

	private boolean choicePowerPole = false;
	private boolean choiceKd = false;
	private TabHost tabs;
	private boolean outOfKey = false;

	/* Danh sach nguyen nhan khong dat cua cot da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listPowerPoleUnqualifyItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkItem;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;
	private Cause_Bts_Cat_WorkEntity curEditItemKdd = null;
	private Edit_Text_Popup editTextPopup;
	private ConfirmDialog confirmSave;
	private Cause_Bts_Power_PoleController btsPowerPoleController;
	private Cause_Bts_Cat_WorkController causeCatWorkController;

	private List<Supervision_LBG_UnqualifyItemEntity> listKddAcceptanceItem;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;

	private TextView supervision_bts_power_pole_complete_date;
	private Button rl_supervision_bts_power_pole_complete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_bts_power_pole_activity);
		setTitle(getString(R.string.supervision_bts_title));
		initComponent();
//		setHeader();
		isInfomation = getIntent().getExtras().getInt(
				IntentConstants.INTENT_DESIGNINFO);

		initData();

		constr_ConstructionItem = getConstr_Construction_Employee();

		setData();
		closeProgressDialog();
		// new loadMore().execute();
	}

	public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
		return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
				.getSerializable(IntentConstants.INTENT_DATA);
	}

	public void initComponent() {
		spvBts_PowerPollView = addView(R.layout.supervision_bts_power_pole_activity, R.id.rl_spv_bts_powerpole);
		String cottrongmoi_kdat = StringUtil
				.getString(R.string.trl_supervision_bts_power_pole_cottrongmoi_khongdat);
		tabs = (TabHost) spvBts_PowerPollView.findViewById(R.id.supervision_bts_power_pole_tabhost);
		tabs.setup();
		// tab cot trong moi khong dat
		TabHost.TabSpec specCtmkd = tabs.newTabSpec(cottrongmoi_kdat);
		specCtmkd
				.setContent(R.id.supervision_bts_power_pole_tab_cottrongmoi_khongdat);
		specCtmkd.setIndicator(makeTabIndicator(cottrongmoi_kdat));

		// tab thi cong keo day dien
		String thicong_keodaydien = StringUtil
				.getString(R.string.trl_supervision_bts_power_pole_thicongkeoday);
		TabHost.TabSpec specTckdd = tabs.newTabSpec(thicong_keodaydien);
		specTckdd.setContent(R.id.supervision_bts_power_pole_tl_kdd);
		specTckdd.setIndicator(makeTabIndicator(thicong_keodaydien));

		tabs.addTab(specCtmkd);
		tabs.addTab(specTckdd);
		tabs.setOnTabChangedListener(this);

		// textview
		supervision_bts_tv_matram = (TextView) spvBts_PowerPollView.findViewById(R.id.rl_supervision_bts_power_pole_tv_matram);
		supervision_bts_tv_mact = (TextView) spvBts_PowerPollView.findViewById(R.id.rl_supervision_bts_power_pole_tv_mact);

		// combobox
		// supervision_bts_power_pole_cb_thietke = (RelativeLayout)
		// findViewById(R.id.rl_supervision_bts_power_pole_search_thietke);

		supervision_bts_power_pole_tv_thietke = (TextView) spvBts_PowerPollView.findViewById(R.id.rl_supervision_bts_power_pole_tv_thietke);
		supervision_bts_power_pole_tv_thietke.setOnClickListener(this);
		// supervision_bts_power_pole_cb_status = (RelativeLayout)
		// findViewById(R.id.supervision_bts_power_pole_tl_thicongkeoday_row1_chontrangthai);

		supervision_bts_power_pole_tv_status = (TextView) spvBts_PowerPollView.findViewById(R.id.supervision_bts_power_pole_tl_thicongkeoday_row1_tv_chontrangthai);
		supervision_bts_power_pole_tv_status.setOnClickListener(this);
		// supervision_bts_power_pole_cb_diemdau = (RelativeLayout)
		// findViewById(R.id.rl_supervision_bts_power_pole_search_chondiemdau);

		supervision_bts_power_pole_tv_diemdau = (TextView) spvBts_PowerPollView.findViewById(R.id.rl_supervision_bts_power_pole_tv_chondiemdau);
		supervision_bts_power_pole_tv_diemdau.setOnClickListener(this);
		// edit text
		supervision_bts_power_pole_et_diengiai = (EditText) spvBts_PowerPollView.findViewById(R.id.supervision_bts_power_pole_tl_thicongkeoday_row2_et_diengiai);
		registerForContextMenu(supervision_bts_power_pole_et_diengiai);
		supervision_bts_power_pole_et_socot_moi = (EditText) spvBts_PowerPollView.findViewById(R.id.rl_supervision_bts_power_pole_et_socottrong);
		supervision_bts_power_pole_et_loaicap = (EditText) spvBts_PowerPollView.findViewById(R.id.rl_supervision_bts_cat_work_et_loaicap);

		// nguyen nhan khong dat
		supervision_bts_power_pole_tv_nnkd = (TextView) spvBts_PowerPollView.findViewById(R.id.supervision_bts_power_pole_tl_thicongkeoday_row1_tv_chonnnkd);
		supervision_bts_power_pole_tv_nnkd.setOnClickListener(this);

		// button
		supervision_bts_power_pole_add = (ImageButton) spvBts_PowerPollView.findViewById(R.id.supervision_bts_power_pole_bt_add);
		supervision_bts_power_pole_add.setOnClickListener(this);

		save = (Button) spvBts_PowerPollView.findViewById(R.id.rl_supervision_bts_power_pole_save);
		save.setOnClickListener(this);

		supervision_bts_power_pole_complete_date = (TextView) spvBts_PowerPollView.findViewById(R.id.supervision_bts_power_pole_complete_date);

		rl_supervision_bts_power_pole_complete = (Button) spvBts_PowerPollView.findViewById(R.id.rl_supervision_bts_power_pole_complete);
		rl_supervision_bts_power_pole_complete.setOnClickListener(this);

		// listview
		listview = (ListView) spvBts_PowerPollView.findViewById(R.id.supervision_bts_power_pole_thicong_lv);
	}

	private TextView makeTabIndicator(String text) {
		TelephonyManager manager = (TelephonyManager)this
				.getSystemService(Context.TELEPHONY_SERVICE);
		if(manager.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE){
			Log.d(TAG,"Tablet");
		}else{
			Log.d(TAG,"Mobile");
			tabHeight = 120;
		}
		TextView tabView = new TextView(this);
		LayoutParams lp3 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				tabHeight, 1);
		lp3.setMargins(1, 0, 1, 0);
		tabView.setLayoutParams(lp3);
		// tabView.setWidth(tabWidth);
		tabView.setText(text);
		tabView.setTextColor(getResources().getColor(R.color.black_color));
		tabView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		tabView.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tab_indicator));
		tabView.setPadding(13, 0, 13, 0);
		return tabView;
	}

	public Supervision_BtsEntity getSupervisionBtsEntity() {
		Supervision_BtsEntity result = new Supervision_BtsEntity();
		result.setSupervision_Bts_Id(btsEntity.getSupervision_Bts_Id());
		result.setPower_CONNECT_POINT(isDiemdau);
		result.setPower_POLE_NEW_TOTAL(Integer
				.parseInt(supervision_bts_power_pole_et_socot_moi.getText()
						.toString()));
		result.setPower_CABLE_TYPE(supervision_bts_power_pole_et_loaicap
				.getText().toString().trim());

		if (btsEntity.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
			result.setSync_Status(Constants.SYNC_STATUS.EDIT);
		} else {
			result.setSync_Status(btsEntity.getSync_Status());
		}
		return result;
	}

	public void initData() {

		listData = new ArrayList<Cause_Bts_Power_PoleEntity>();

		/* Drop down */
		String sChoice = "";
		listInfomation = GloablUtils.getListBTSInfo(sChoice);
		sChoice = getResources().getString(R.string.text_choice1);
		supervision_bts_power_pole_tv_thietke.setText(GloablUtils
				.getStringBTSInfo(isInfomation));

		listDiemdau = GloablUtils.getListBtsPowerPoleDiemdau(sChoice);
		supervision_bts_power_pole_tv_diemdau.setText(GloablUtils
				.getStringBtsAssessPillar(isDiemdau));
		supervision_bts_power_pole_tv_diemdau.setText(sChoice);

		listStatus = GloablUtils.getListBtsAssessBuildPillar(sChoice);
		supervision_bts_power_pole_tv_status.setText(GloablUtils
				.getStringBtsAssessPillar(isStatus));
		supervision_bts_power_pole_tv_status.setText(sChoice);

		btsPowerPoleController = new Cause_Bts_Power_PoleController(this);
		causeCatWorkController = new Cause_Bts_Cat_WorkController(this);
	}

	public void setData() {

		// JSONArray jsonBtsTest = SqlliteSyncModel.getDataJsonSyncTest(
		// Supervision_BtsField.TABLE_NAME,
		// Supervision_BtsController.allColumn, null, 0,
		// Constants.NUMBER_MAX_ITEM_SYNC);

		sChoice = getResources().getString(R.string.text_choice1);
		supervision_bts_tv_matram.setText(constr_ConstructionItem
				.getStationCode());
		supervision_bts_tv_mact.setText(String.valueOf(constr_ConstructionItem
				.getConstrCode()));
		Supervision_BtsController bts_Controller = new Supervision_BtsController(
				this);
		btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
				.getSupervision_Constr_Id());

		supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
				this);

		Cat_Cause_Constr_UnQualifyController unqualifyController = new Cat_Cause_Constr_UnQualifyController(
				this);

		/**
		 * lay du lieu nguyen nhan loi cot trong moi
		 */
		listPowerPoleUnqualifyItem = unqualifyController
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.SUB_TYPE_COT_TRONG_MOI,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listUnqualifyCatWorkItem = unqualifyController
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listKddAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
				.getAllUnQualifyItemByName(
						Constants.ACCEPTANCE_TYPE.BTS_KEO_DIEN,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		isDiemdau = btsEntity.getPower_CONNECT_POINT();
		if (btsEntity.getPower_CONNECT_POINT() < 1) {

			supervision_bts_power_pole_tv_diemdau.setText(sChoice);
		} else {
			supervision_bts_power_pole_tv_diemdau.setText(GloablUtils
					.getStringBtsPowerPoleDiemdau(isDiemdau));
		}

		// set du lieu vao cac truong danh gia cot trong moi
		if (btsEntity.getPower_POLE_NEW_TOTAL() != -1) {
			supervision_bts_power_pole_et_socot_moi.setText(StringUtil
					.formatNumber((btsEntity.getPower_POLE_NEW_TOTAL())));
		}

		supervision_bts_power_pole_et_loaicap.setText(btsEntity
				.getPower_CABLE_TYPE());

		refreshView(sChoice);

		showCompleteDate(constr_ConstructionItem,
				Constants.PROGRESS_TYPE.BTS_TYPE,
				Constants.PROGRESS_TYPE.KEO_DAY_DIEN,
				supervision_bts_power_pole_complete_date,
				rl_supervision_bts_power_pole_complete);
		
		/* Set endable va disable voi cong trinh da hoan thanh */

		if (constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED
				|| constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_COSITE) {
			this.save.setVisibility(View.GONE);
			this.supervision_bts_power_pole_add.setVisibility(View.GONE);
//			this.rl_supervision_bts_power_pole_complete.setVisibility(View.GONE);
		}
		if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE
				&& btsEntity.getConstructionType() == Constants.CONSTRUCTION_TYPE.NANG_CAP) {
			this.save.setVisibility(View.GONE);
			this.supervision_bts_power_pole_add.setVisibility(View.GONE);
//			this.rl_supervision_bts_power_pole_complete.setVisibility(View.GONE);
		}
	}

	public void refreshView(String sChoice) {

		Supervision_BtsController bts_Controller = new Supervision_BtsController(
				this);
		btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
				.getSupervision_Constr_Id());

		// set du lieu vao cac truong danh gia cot trong moi
		if (btsEntity.getPower_POLE_NEW_TOTAL() != -1) {
			supervision_bts_power_pole_et_socot_moi.setText(StringUtil
					.formatNumber((btsEntity.getPower_POLE_NEW_TOTAL())));
		}

		supervision_bts_power_pole_et_loaicap.setText(btsEntity
				.getPower_CABLE_TYPE());

		// lay thuc the de set du lieu ung voi thi cong keo dien
		curEditItemKdd = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
				Constants.BTS_CONSTR_WORK.WORK_CODE_KEO_DAY_DIEN,
				btsEntity.getSupervision_Bts_Id());

		countNnkdCheckKdd = 0;

		for (int i = 0; i < this.curEditItemKdd.getListCauseUniQualify().size(); i++) {
			if (!curEditItemKdd.getListCauseUniQualify().get(i).isDelete()) {
				countNnkdCheckKdd++;
				break;
			}
		}

		// set du lieu vao thi cong keo dien
		if (curEditItemKdd.getBts_Cat_WorkEntity().getStatus() >= 0) {
			isStatus = curEditItemKdd.getBts_Cat_WorkEntity().getStatus();

			curEditItemKdd.setNew(false);
			if (isStatus < 0) {
				supervision_bts_power_pole_tv_status.setText(sChoice);
			} else
				supervision_bts_power_pole_tv_status.setText(GloablUtils
						.getStringBtsCatWorkStatus(isStatus));

			List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = curEditItemKdd
					.getListCauseUniQualify();

			/* Gan anh nghiem thu */
			for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curEditItemKdd
					.getListAcceptance()) {
				List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
						.getLstAttachFile(
								Acceptance_Bts_Cat_WorkField.TABLE_NAME,
								curUnqualifyItem.getCause_Line_Bg_Id());
				for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
					curUnqualifyItem.getLstAttachFile().add(itemFile);
				}

			}

			/* Gan anh nguyen nhan loi */
			for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {

				List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
						.getLstAttachFile(Cause_Bts_Cat_WorkField.TABLE_NAME,
								curUnqualifyItem.getCause_Line_Bg_Id());
				for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
					curUnqualifyItem.getLstAttachFile().add(itemFile);
				}
			}
			curEditItemKdd.setListCauseUniQualify(listUnqualifyItem);

			int countAcceptCheck = 0;

			for (int i = 0; i < this.curEditItemKdd.getListAcceptance().size(); i++) {
				if (curEditItemKdd.getListAcceptance().get(i)
						.getLstNewAttachFile().size() > 0
						|| curEditItemKdd.getListAcceptance().get(i)
								.getLstAttachFile().size() > 0) {
					countAcceptCheck++;
					break;
				}
			}

			if (isStatus == Constants.TANK_STATUS.DAT) {
				if (countAcceptCheck > 0) {
					supervision_bts_power_pole_tv_nnkd.setText(StringUtil
							.getString(R.string.text_view_dot));
				} else {
					supervision_bts_power_pole_tv_nnkd.setText(StringUtil
							.getString(R.string.text_empty));
				}
			} else {
				if (curEditItemKdd.getListCauseUniQualify().size() > 0) {
					supervision_bts_power_pole_tv_nnkd.setText(StringUtil
							.getString(R.string.text_view_dot));
				} else
					supervision_bts_power_pole_tv_nnkd.setText(StringUtil
							.getString(R.string.text_empty));
			}

			supervision_bts_power_pole_et_diengiai.setText(curEditItemKdd
					.getBts_Cat_WorkEntity().getFail_Desc());
		}

		// power pole

		listData = btsPowerPoleController
				.getListCauseBts_Power_PoleEntity(btsEntity
						.getSupervision_Bts_Id());

		// truong hop da co du lieu cot trong moi khong dat
		for (Cause_Bts_Power_PoleEntity curItem : listData) {
			// curItem.setListCauseUniQualify(convertUnqualifyToItem(curItem
			// .getListConstrUnqualifyEntity()));
			curItem.setNew(false);

			List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = curItem
					.getListCauseUniQualify();

			/* Gan anh nguyen nhan loi */
			for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {

				List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
						.getLstAttachFile(Cause_Bts_Power_PoleField.TABLE_NAME,
								curUnqualifyItem.getCause_Line_Bg_Id());
				for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
					curUnqualifyItem.getLstAttachFile().add(itemFile);
				}
			}
			curItem.setListCauseUniQualify(listUnqualifyItem);
		}

		bts_PowerPoleAdapter = new Bts_PowerPoleAdapter(this, listData);

		listview.setAdapter(bts_PowerPoleAdapter);

	}

	private String checkVailid(Cause_Bts_Power_PoleEntity itemCheck) {
		String sReslut = "";
		try {
			if (StringUtil.isNullOrEmpty(itemCheck.getBts_PowerPoleEntity()
					.getPower_POLE_NAME())) {
				sReslut = StringUtil
						.getString(R.string.constr_pillar_anten_pillar_name_empty);

			} else {
				int countNnkdCheck = 0;

				for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
					if (!itemCheck.getListCauseUniQualify().get(i).isDelete()) {
						countNnkdCheck++;
						break;
					}
				}
				if (countNnkdCheck < 1) {

					sReslut = StringUtil
							.getString(R.string.constr_pillar_anten_nn_khongdat_tempty);
					sReslut += itemCheck.getBts_PowerPoleEntity()
							.getPower_POLE_NAME();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	private String checkVailidKdd(Cause_Bts_Cat_WorkEntity itemCheck) {
		String sReslut = "";
		try {
			if (isStatus < 0) {
				sReslut = StringUtil
						.getString(R.string.bts_constr_power_pole_dgtckd_tempty);
				tabs.setCurrentTab(1);
				supervision_bts_power_pole_tv_status
						.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));

				Toast.makeText(getApplicationContext(), sReslut,
						Toast.LENGTH_LONG).show();

				clearFocus();
			} else if (isStatus == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT
					&& countNnkdCheckKdd < 1) {
				sReslut = StringUtil
						.getString(R.string.constr_cat_work_nn_khongdat_kd_tempty);
				Toast.makeText(getApplicationContext(), sReslut,
						Toast.LENGTH_LONG).show();

				clearFocus();
			} else if (isStatus == Constants.BTS_CAT_WORK_STATUS.DAT) {
				int countAcceptCheck = 0;

				for (int i = 0; i < itemCheck.getListAcceptance().size(); i++) {
					if ((itemCheck.getListAcceptance().get(i)
							.getLstNewAttachFile().size() > 0 || itemCheck
							.getListAcceptance().get(i).getLstAttachFile()
							.size() > 0)
							&& (i == 0 || i == 1)) {
						countAcceptCheck++;
					}
				}

				if (countAcceptCheck < 2) {
					sReslut = StringUtil
							.getString(R.string.constr_take_acceptance_not_enough);
					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();

					clearFocus();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	public void clearFocus() {
		// FocusHelper.releaseFocus(supervision_bts_equip_et_diengiai_sotu_3g);
		// FocusHelper.releaseFocus(supervision_bts_equip_et_diengiai_sotu_2g);
		supervision_bts_power_pole_et_socot_moi.setFocusableInTouchMode(false);
		supervision_bts_power_pole_et_socot_moi.clearFocus();

		supervision_bts_power_pole_et_loaicap.setFocusableInTouchMode(false);
		supervision_bts_power_pole_et_loaicap.clearFocus();

		supervision_bts_power_pole_et_diengiai.setFocusableInTouchMode(false);
		supervision_bts_power_pole_et_diengiai.clearFocus();

		supervision_bts_power_pole_et_socot_moi.setFocusableInTouchMode(true);
		supervision_bts_power_pole_et_loaicap.setFocusableInTouchMode(true);
		supervision_bts_power_pole_et_diengiai.setFocusableInTouchMode(true);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// click hoan thanh
		case R.id.rl_supervision_bts_power_pole_complete:
			if(!GlobalInfo.getInstance().isCheckIn()){
				showAlertDialogCheckInRequire(this, getString(R.string.checkin_require), getString(R.string.text_close));
				break;
			}
			confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save),
					OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			confirmSave.show();
			break;
		// click save
		case R.id.rl_supervision_bts_power_pole_save:
			if(!GlobalInfo.getInstance().isCheckIn()){
				showAlertDialogCheckInRequire(this, getString(R.string.checkin_require), getString(R.string.text_close));
				break;
			}
			String messageError = "";

			// tab cot trong moi khong dat
			if (tabs.getCurrentTab() == 0) {
				for (Cause_Bts_Power_PoleEntity curItemData : listData) {
					messageError = this.checkVailid(curItemData);
					if (!StringUtil.isNullOrEmpty(messageError)) {

						break;
					}
				}
				if (!StringUtil.isNullOrEmpty(messageError)) {
					// tabs.setCurrentTab(0);
					this.showDialog(messageError);
				} else {

					if (isDiemdau < 1) {
						// tabs.setCurrentTab(0);
						supervision_bts_power_pole_tv_diemdau.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));

						Toast.makeText(
								getApplicationContext(),
								StringUtil
										.getString(R.string.bts_power_pole_diemdau_is_null),
								Toast.LENGTH_LONG).show();

						clearFocus();
					} else if (StringUtil
							.isNullOrEmpty(supervision_bts_power_pole_et_socot_moi
									.getText().toString())) {
						// tabs.setCurrentTab(0);
						supervision_bts_power_pole_et_socot_moi.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));

						supervision_bts_power_pole_et_socot_moi.requestFocus();

						Toast.makeText(
								getApplicationContext(),
								StringUtil
										.getString(R.string.bts_power_pole_socot_is_null),
								Toast.LENGTH_LONG).show();

					} else if (StringUtil
							.isNullOrEmpty(supervision_bts_power_pole_et_loaicap
									.getText().toString())) {
						supervision_bts_power_pole_et_loaicap.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));
						supervision_bts_power_pole_et_loaicap.requestFocus();

						Toast.makeText(
								getApplicationContext(),
								StringUtil
										.getString(R.string.bts_power_pole_cap_type_is_null),
								Toast.LENGTH_LONG).show();
					} else {
						confirmSave = new ConfirmDialog(this,
								StringUtil
										.getString(R.string.text_confirm_save));
						confirmSave.show();
					}
				}
			} else {
				if (StringUtil.isNullOrEmpty(this
						.checkVailidKdd(curEditItemKdd))) {
					confirmSave = new ConfirmDialog(this,
							StringUtil.getString(R.string.text_confirm_save));
					confirmSave.show();
				}
			}

			break;

		// click nguyen nhan khong dat muc thi cong keo dien
		case R.id.supervision_bts_power_pole_tl_thicongkeoday_row1_tv_chonnnkd:
			choicePowerPole = false;
			choiceKd = true;
			if (isStatus == Constants.BTS_ASSESS_PILLAR.KHONG_DAT) {
				setUnqualifyKdd();
				// this.setUnqualifyCurTankItem(curlistPillarAntenUnqualifyItem);
				contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
						this, null, this.listUnqualifyCatWorkItem);
				contructionUnqualifyPopup.showAtCenter();
			} else if (isStatus == Constants.TANK_STATUS.DAT) {
				this.setKddAcceptance();
				contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
						this, null, this.listKddAcceptanceItem);

				contruoctionAcceptancePopup.showAtCenter();

			} else {
				Toast.makeText(
						this,
						StringUtil
								.getString(R.string.constr_line_tank_unqualify_choice_message),
						Toast.LENGTH_SHORT).show();
			}
			break;
		// click add
		case R.id.supervision_bts_power_pole_bt_add:
			if(!GlobalInfo.getInstance().isCheckIn()){
				showAlertDialogCheckInRequire(this, getString(R.string.checkin_require), getString(R.string.text_close));
				break;
			}
			Cause_Bts_Power_PoleEntity cause_bts_powerPole = new Cause_Bts_Power_PoleEntity();

			cause_bts_powerPole.getBts_PowerPoleEntity().setSupervision_BTS_ID(
					btsEntity.getSupervision_Bts_Id());
			cause_bts_powerPole.setNew(true);
			cause_bts_powerPole.setEdited(false);

			listData.add(cause_bts_powerPole);
			bts_PowerPoleAdapter.notifyDataSetChanged();

			listview.setSelection(listview.getAdapter().getCount() - 1);
			break;

		// click combobox thong tin
		case R.id.rl_supervision_bts_power_pole_tv_thietke:
			this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
					this.listInfomation);

			dropdownPopupMenuInfomation.show(v);
			break;

		// click combobox diem dau
		case R.id.rl_supervision_bts_power_pole_tv_chondiemdau:
			this.dropdownPopupMenuDiemdau = new Menu_DropdownPopup(this,
					this.listDiemdau);
			dropdownPopupMenuDiemdau.show(v);
			break;

		// click combobox danh gia trang thai thi cong keo dien
		case R.id.supervision_bts_power_pole_tl_thicongkeoday_row1_tv_chontrangthai:
			this.dropdownPopupMenuStatus = new Menu_DropdownPopup(this,
					this.listStatus);
			dropdownPopupMenuStatus.show(v);
			break;
		default:
			break;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onEvent(int eventType, View control, Object data) {
		switch (eventType) {
		case OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK:
			DropdownItemEntity dropdownItem = (DropdownItemEntity) data;
			String typeItem = dropdownItem.getType();
			Drawable ic_combo = getResources().getDrawable(
					R.drawable.icon_combo);
			ic_combo.setBounds(0, 0, ic_combo.getIntrinsicWidth(),
					ic_combo.getIntrinsicHeight());

			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_INFO)) {
				isInfomation = dropdownItem.getId();

				this.supervision_bts_power_pole_tv_thietke.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuInfomation.dismiss();

				this.showProgressDialog(StringUtil
						.getString(R.string.text_loading));

				Bundle bundleMonitorData = new Bundle();
				bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
						constr_ConstructionItem);
				bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
						isInfomation);

				gotoBtsActivity(bundleMonitorData, isInfomation);

				finish();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.TYPE_DIEM_DAU)) {
				isDiemdau = dropdownItem.getId();
				if (this.isDiemdau > 0) {
					supervision_bts_power_pole_tv_diemdau.setError(null,
							ic_combo);
				}
				this.supervision_bts_power_pole_tv_diemdau.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuDiemdau.dismiss();

			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.ASSESS_BUILD_PILLAR)) {
				isStatus = dropdownItem.getId();
				if (this.isStatus >= 0) {
					supervision_bts_power_pole_tv_status.setError(null,
							ic_combo);

					if (isStatus == 0) {
						countNnkdCheckKdd = 0;

						for (int i = 0; i < this.curEditItemKdd
								.getListCauseUniQualify().size(); i++) {
							if (!curEditItemKdd.getListCauseUniQualify().get(i)
									.isDelete()) {
								countNnkdCheckKdd++;
								break;
							}
						}

						if (countNnkdCheckKdd > 0) {
							// if
							// (curEditItemKdd.getListCauseUniQualify().size() >
							// 0) {
							supervision_bts_power_pole_tv_nnkd
									.setText(StringUtil
											.getString(R.string.text_view_dot));
						} else
							supervision_bts_power_pole_tv_nnkd
									.setText(StringUtil
											.getString(R.string.text_empty));
					} else {
						countNnkdCheckKdd = 0;

						for (int i = 0; i < this.curEditItemKdd
								.getListAcceptance().size(); i++) {
							if (this.curEditItemKdd.getListAcceptance().get(i)
									.getLstNewAttachFile().size() > 0
									|| this.curEditItemKdd.getListAcceptance()
											.get(i).getLstAttachFile().size() > 0) {
								countNnkdCheckKdd++;
								break;
							}
						}

						if (countNnkdCheckKdd > 0) {
							supervision_bts_power_pole_tv_nnkd
									.setText(StringUtil
											.getString(R.string.text_view_dot));
						} else
							supervision_bts_power_pole_tv_nnkd
									.setText(StringUtil
											.getString(R.string.text_empty));
					}
				}
				this.supervision_bts_power_pole_tv_status.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuStatus.dismiss();
			}
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			break;

		// chon nguyen nhan khong dat
		case OnEventControlListener.EVENT_SUPERVISION_TANK_UNQUALIFY:
			this.curEditItem = (Cause_Bts_Power_PoleEntity) data;
			choicePowerPole = true;
			choiceKd = false;
			setUnqualify();
			contructionUnqualifyPopup = new Contruction_UnqualifyPopup(this,
					null, this.listPowerPoleUnqualifyItem);
			contructionUnqualifyPopup.showAtCenter();

			break;

		// click dien giai
		case OnEventControlListener.EVENT_SUPERVISION_EDIT:
			this.curEditItem = (Cause_Bts_Power_PoleEntity) data;
			switch (this.curEditItem.getiField()) {
			case Constants.BTS_POWER_POLE_EDIT.FAIL_DESC:
				String sFailDescTextValue = this.curEditItem
						.getBts_PowerPoleEntity().getFail_DESC();
				editTextPopup = new Edit_Text_Popup(this, null,
						sFailDescTextValue,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
				editTextPopup.showAtCenter();
				break;
			case Constants.BTS_POWER_POLE_EDIT.POWER_POLE_NAME:
				String sPowerPoleTextValue = this.curEditItem
						.getBts_PowerPoleEntity().getPower_POLE_NAME();
				editTextPopup = new Edit_Text_Popup(this, null,
						sPowerPoleTextValue,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, false, 200);
				editTextPopup.showAtCenter();
				break;
			}
			break;
		case OnEventControlListener.EVENT_SET_TEXT:
			String sSetTextValue = (String) data;

			if (this.curEditItem.getiField() == Constants.BTS_POWER_POLE_EDIT.FAIL_DESC) {
				this.curEditItem.getBts_PowerPoleEntity().setFail_DESC(
						sSetTextValue);
			}
			if (this.curEditItem.getiField() == Constants.BTS_POWER_POLE_EDIT.POWER_POLE_NAME) {
				this.curEditItem.getBts_PowerPoleEntity().setPower_POLE_NAME(
						sSetTextValue);
			}
			this.curEditItem.setEdited(true);
			this.bts_PowerPoleAdapter.notifyDataSetChanged();
			editTextPopup.hidePopup();
			break;
		// su kien xoa 1 dong tren listview
		case OnEventControlListener.EVENT_DELETE_ROW:
			this.curEditItem = (Cause_Bts_Power_PoleEntity) data;
			this.showAskOptionDialog(StringUtil
					.getString(R.string.text_delete_title), StringUtil
					.getString(R.string.bts_new_pillar_delete_message));

			break;

		case OnEventControlListener.EVENT_CONFIRM_OK:

			SaveAsyncTask task = new SaveAsyncTask();
			task.execute();
			break;
		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			saveKddAcceptance();
			countNnkdCheckKdd = 0;

			for (int i = 0; i < this.curEditItemKdd.getListAcceptance().size(); i++) {
				if (this.curEditItemKdd.getListAcceptance().get(i)
						.getLstNewAttachFile().size() > 0
						|| this.curEditItemKdd.getListAcceptance().get(i)
								.getLstAttachFile().size() > 0) {
					countNnkdCheckKdd++;
					break;
				}
			}

			if (countNnkdCheckKdd > 0) {
				supervision_bts_power_pole_tv_nnkd.setText(StringUtil
						.getString(R.string.text_view_dot));
			} else
				supervision_bts_power_pole_tv_nnkd.setText(StringUtil
						.getString(R.string.text_empty));

			contruoctionAcceptancePopup.hidePopup();
			break;
		/* Dong nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			if (choicePowerPole) {
				this.saveUnqualify();
				contructionUnqualifyPopup.hidePopup();
				this.bts_PowerPoleAdapter.notifyDataSetChanged();
				this.curEditItem.setEdited(true);
			}

			if (choiceKd) {
				this.saveUnqualifyKdd();
				contructionUnqualifyPopup.hidePopup();
				this.curEditItemKdd.setEdited(true);

				countNnkdCheckKdd = 0;

				for (int i = 0; i < this.curEditItemKdd
						.getListCauseUniQualify().size(); i++) {
					if (!curEditItemKdd.getListCauseUniQualify().get(i)
							.isDelete()) {
						countNnkdCheckKdd++;
						break;
					}
				}

				if (countNnkdCheckKdd > 0) {
					// if (curEditItemKdd.getListCauseUniQualify().size() > 0) {
					supervision_bts_power_pole_tv_nnkd.setText(StringUtil
							.getString(R.string.text_view_dot));
				} else
					supervision_bts_power_pole_tv_nnkd.setText(StringUtil
							.getString(R.string.text_empty));
			}

			break;
		/* Xu ly su kien anh */
		case OnEventControlListener.EVENT_UNQUALIFY_CHECK_UCHECK:
			Supervision_LBG_UnqualifyItemEntity unqualifyItem = (Supervision_LBG_UnqualifyItemEntity) data;
			if (unqualifyItem.isUnqulify()) {
				unqualifyItem.setDelete(false);
			} else {
				unqualifyItem.setDelete(true);
			}
			this.contructionUnqualifyPopup.refreshData();
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
					addImgView.setStatusApprove(itemAttach.getStatusApprove());
					addImgView.setReasonDeny(itemAttach.getResonDeny());
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
					addImgView.setStatusApprove(itemNewAttachFile.getStatusApprove());
					addImgView.setReasonDeny(itemNewAttachFile.getResonDeny());
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
					addImgView.setStatusApprove(itemAttach.getStatusApprove());
					addImgView.setReasonDeny(itemAttach.getResonDeny());
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
					addImgView.setStatusApprove(itemNewAttachFile.getStatusApprove());
					addImgView.setReasonDeny(itemNewAttachFile.getResonDeny());
					listImgView.add(addImgView);
				}
			}

			this.imgViewPopup = new Image_ViewGalleryPopup(this, null,
					listImgView);
			this.imgViewPopup.hideShowButton();
			this.imgViewPopup.showAtCenter();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_NEW:
			this.takePhoto(constr_ConstructionItem);
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_EXIT:
			List<ImageEntity> lstData = (List<ImageEntity>) data;

			if (choicePowerPole) {
				this.curUnqualifyItem.getLstAttachFile().clear();
				this.curUnqualifyItem.getLstNewAttachFile().clear();
				for (ImageEntity imageEntity : lstData) {
					Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
					curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
							.getId());
					curAttachFile.setFile_Path(imageEntity.getUrl());
					curAttachFile.setStatusApprove(imageEntity.getStatusApprove());
					curAttachFile.setResonDeny(imageEntity.getReasonDeny());
					this.curUnqualifyItem.getLstNewAttachFile().add(
							curAttachFile);
				}
				this.contructionUnqualifyPopup.refreshData();
			}

			if (choiceKd) {
				if (isStatus == Constants.TANK_STATUS.DAT) {
					this.curAcceptanceItem.getLstAttachFile().clear();
					this.curAcceptanceItem.getLstNewAttachFile().clear();
					for (ImageEntity imageEntity : lstData) {
						Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
						curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
								.getId());
						curAttachFile.setFile_Path(imageEntity.getUrl());
						curAttachFile.setStatusApprove(imageEntity.getStatusApprove());
						curAttachFile.setResonDeny(imageEntity.getReasonDeny());
						this.curAcceptanceItem.getLstNewAttachFile().add(
								curAttachFile);
					}
					this.contruoctionAcceptancePopup.refreshData();
				} else if (isStatus == Constants.TANK_STATUS.KHONG_DAT) {
					this.curUnqualifyItem.getLstAttachFile().clear();
					this.curUnqualifyItem.getLstNewAttachFile().clear();
					for (ImageEntity imageEntity : lstData) {
						Supv_Constr_Attach_FileEntity curAttachFile = new Supv_Constr_Attach_FileEntity();
						curAttachFile.setSupv_Constr_Attach_file_Id(imageEntity
								.getId());
						curAttachFile.setFile_Path(imageEntity.getUrl());
						curAttachFile.setStatusApprove(imageEntity.getStatusApprove());
						curAttachFile.setResonDeny(imageEntity.getReasonDeny());
						this.curUnqualifyItem.getLstNewAttachFile().add(
								curAttachFile);
					}
					this.contructionUnqualifyPopup.refreshData();
				}
			}

			this.imgViewPopup.hidePopup();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_DELETE:

			this.imgViewPopup.deleteImageData();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
			this.selectPhoto();
			break;
		case OnEventControlListener.EVENT_COMPLETE_PROGRESS:
			saveCompleteDay(constr_ConstructionItem,
					Constants.PROGRESS_TYPE.BTS_TYPE,
					Constants.PROGRESS_TYPE.KEO_DAY_DIEN, outOfKey);
			showCompleteDate(constr_ConstructionItem,
					Constants.PROGRESS_TYPE.BTS_TYPE,
					Constants.PROGRESS_TYPE.KEO_DAY_DIEN,
					supervision_bts_power_pole_complete_date,
					rl_supervision_bts_power_pole_complete);
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			confirmSave.dismiss();
			showProgressDialog(StringUtil.getString(R.string.text_progcessing));
		}

		@Override
		protected Void doInBackground(Void... params) {
			saveData();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(SupervisionBtsPowerPoleActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshView(sChoice);

			Toast toast = Toast.makeText(SupervisionBtsPowerPoleActivity.this,
					getResources().getString(R.string.text_update_success),
					Toast.LENGTH_LONG);

			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			closeProgressDialog();
		}

	}

	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();

		if (!curEditItem.isNew()) {
			deletePowerPoleRow(curEditItem);
		}
		listData.remove(curEditItem);
		this.bts_PowerPoleAdapter.notifyDataSetChanged();
	}

	public void deletePowerPoleRow(Cause_Bts_Power_PoleEntity itemRow) {
		Supervision_Bts_Power_PoleController btsPowerPoleController = new Supervision_Bts_Power_PoleController(
				this);
		// Cause_Bts_Power_PoleController causeBtsPowerPoleController = new
		// Cause_Bts_Power_PoleController(
		// this);

		btsPowerPoleController.deleteSupervision_Bts_Power_PoleEntity(itemRow
				.getBts_PowerPoleEntity());
		// causeBtsPowerPoleController.deleteCause_Bts_Power_PoleEntity(itemRow);
	}

	public void saveData() {

		try {
			long idUser = GlobalInfo.getInstance().getUserId();

			if (tabs.getCurrentTab() == 0) {
				Supervision_BtsController bts_Controller = new Supervision_BtsController(
						this);

				bts_Controller
						.updateNumberNewPoleTotal(getSupervisionBtsEntity());

				Supervision_Bts_Power_PoleController btsPowerPoleController = new Supervision_Bts_Power_PoleController(
						this);
				Cause_Bts_Power_PoleController causeBtsPowerPoleController = new Cause_Bts_Power_PoleController(
						this);

				for (Cause_Bts_Power_PoleEntity curItemData : listData) {
					// tao moi (insert tat ca truong du lieu cot vao database)
					if (curItemData.isNew()) {
						long idBtsPowerPoleLast = Ktts_KeyController
								.getInstance()
								.getKttsNextKey(
										Supervision_Bts_Power_PoleField.TABLE_NAME);

						if (idBtsPowerPoleLast == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						curItemData.getBts_PowerPoleEntity().setSync_Status(
								Constants.SYNC_STATUS.ADD);
						curItemData.getBts_PowerPoleEntity().setIsActive(
								Constants.ISACTIVE.ACTIVE);
						curItemData.getBts_PowerPoleEntity().setProcessId(0);
						curItemData.getBts_PowerPoleEntity().setEmployeeId(
								idUser);
						curItemData.getBts_PowerPoleEntity()
								.setSupervisionConstrId(
										btsEntity.getSupervision_ConstrEntity()
												.getSupervision_Constr_Id());

						btsPowerPoleController.insertSupervisionBtsPowerPole(
								curItemData.getBts_PowerPoleEntity(),
								idBtsPowerPoleLast);

						Cause_Bts_Power_PoleEntity causeBtsPowerPoleEntity = new Cause_Bts_Power_PoleEntity();
						causeBtsPowerPoleEntity.getBts_PowerPoleEntity()
								.setSupervision_BTS_POWER_POLE_ID(
										idBtsPowerPoleLast);
						causeBtsPowerPoleEntity
								.setListCauseUniQualify(curItemData
										.getListCauseUniQualify());

						ArrayList<Long> ListIdCause = new ArrayList<Long>();

						for (Supervision_LBG_UnqualifyItemEntity unqualify : causeBtsPowerPoleEntity
								.getListCauseUniQualify()) {
							long lastId = Ktts_KeyController
									.getInstance()
									.getKttsNextKey(
											Cause_Bts_Power_PoleField.TABLE_NAME);

							if (lastId == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;

							unqualify.setSync_Status(Constants.SYNC_STATUS.ADD);
							unqualify.setIsActive(Constants.ISACTIVE.ACTIVE);
							unqualify.setProcessId(0);
							unqualify.setSupervisionConstrId(btsEntity
									.getSupervision_ConstrEntity()
									.getSupervision_Constr_Id());

							causeBtsPowerPoleController
									.insertCauseBts_PowerPoleEntity(unqualify,
											causeBtsPowerPoleEntity
													.getBts_PowerPoleEntity(),
											lastId, idUser);
							ListIdCause.add(lastId);

						}

						int dem = 0;
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curItemData
								.getListCauseUniQualify()) {
							// Luu anh nguyen nhan loi neu co

							for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
									.getLstNewAttachFile()) {
								if (!StringUtil.isNullOrEmpty(itemFile
										.getFile_Path())) {
									this.supvConstrAttachFileController
											.coppyAndAddAttachFile(
													this.constr_ConstructionItem,
													itemFile.getFile_Path(),
													ListIdCause.get(dem),
													Cause_Bts_Power_PoleField.TABLE_NAME);

								}
							}
							dem++;
						}
					} else {
						// update nhung du lieu cot da sua
						if (curItemData.isEdited()) {

							if (curItemData.getBts_PowerPoleEntity()
									.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
								curItemData.getBts_PowerPoleEntity()
										.setSync_Status(
												Constants.SYNC_STATUS.EDIT);
							}

							btsPowerPoleController
									.updateSupervisionBtsPowerPole(curItemData
											.getBts_PowerPoleEntity());

							Cause_Bts_Power_PoleEntity causeBtsPowerPoleEntity = new Cause_Bts_Power_PoleEntity();
							causeBtsPowerPoleEntity
									.getBts_PowerPoleEntity()
									.setSupervision_BTS_POWER_POLE_ID(
											curItemData
													.getBts_PowerPoleEntity()
													.getSupervision_BTS_POWER_POLE_ID());
							causeBtsPowerPoleEntity
									.setListCauseUniQualify(curItemData
											.getListCauseUniQualify());

							for (Supervision_LBG_UnqualifyItemEntity addItemCause : curItemData
									.getListCauseUniQualify()) {
								// xoa het nguyen nhan khong dat cu
								// cauCatWorkController
								// .deleteCause_Bts_Cat_WorkEntity(unqualify);
								/* 1. Chinh sua nguyen nhan khong dat */
								if (addItemCause.getCause_Line_Bg_Id() > 0) {
									/*
									 * 1.1. Xoa nguyen nhan khong dat danh dau
									 * xoa
									 */
									if (addItemCause.isDelete()) {
										causeBtsPowerPoleController
												.deleteCause_Bts_Power_PoleEntity(addItemCause);
									}
									/* 1.2. Update lai nguyen nhan khong dat */
									else {
										List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
												.getLstAttachFile(
														Cause_Bts_Power_PoleField.TABLE_NAME,
														addItemCause
																.getCause_Line_Bg_Id());

										if (addItemCause.getLstNewAttachFile()
												.size() > 0
												|| (addItemCause
														.getLstNewAttachFile()
														.size() == 0 && lstCurAttachFile
														.size() > 0)) {
											// xoa anh cu di

											for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
												supvConstrAttachFileController
														.delete(itemFile);
											}
										}

										for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
												.getLstNewAttachFile()) {

											// them anh moi vao
											if (!StringUtil
													.isNullOrEmpty(itemFile
															.getFile_Path())) {
												this.supvConstrAttachFileController
														.coppyAndAddAttachFile(
																this.constr_ConstructionItem,
																itemFile.getFile_Path(),
																addItemCause
																		.getCause_Line_Bg_Id(),
																Cause_Bts_Power_PoleField.TABLE_NAME);

											}
										}
										/* 1.2.1 Thay doi anh */
										// if (!StringUtil
										// .isNullOrEmpty(addItemCause
										// .getNewAttachFile()
										// .getFile_Path())) {
										// long idAttachFile = addItemCause
										// .getAttachFile()
										// .getSupv_Constr_Attach_file_Id();
										// /*
										// * Neu da ton tai file luu trong
										// * bang attachment thi chi thay doi
										// * ten va duong dan
										// */
										// if (idAttachFile > 0) {
										//
										// String sFileName = FileManager
										// .getFileName();
										//
										// String sFilePath = FileManager
										// .getSaveFilePath(
										// String.valueOf(this.constr_ConstructionEmployeeItem
										// .getConstructId()),
										// sFileName);
										//
										// FileManager
										// .coppyFile(
										// addItemCause
										// .getNewAttachFile()
										// .getFile_Path(),
										// sFilePath);
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
										// /*
										// * Khong ton tai file thi lai them
										// * moi
										// */
										// else {
										// this.supvConstrAttachFileController
										// .coppyAndAddAttachFile(
										// this.constr_ConstructionEmployeeItem,
										// addItemCause
										// .getNewAttachFile()
										// .getFile_Path(),
										// addItemCause
										// .getCause_Line_Bg_Id(),
										// Cause_Bts_Power_PoleField.TABLE_NAME);
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

									ArrayList<Long> ListIdCause = new ArrayList<Long>();

									long lastId = Ktts_KeyController
											.getInstance()
											.getKttsNextKey(
													Cause_Bts_Power_PoleField.TABLE_NAME);

									if (lastId == 0) {
										outOfKey = true;
										return;
									} else
										outOfKey = false;

									Cause_Bts_Power_PoleEntity addCauseItem = new Cause_Bts_Power_PoleEntity();
									addItemCause
											.setSync_Status(Constants.SYNC_STATUS.ADD);
									addItemCause
											.setIsActive(Constants.ISACTIVE.ACTIVE);
									addItemCause.setProcessId(0);
									addItemCause
											.setSupervisionConstrId(btsEntity
													.getSupervision_ConstrEntity()
													.getSupervision_Constr_Id());

									addCauseItem
											.getBts_PowerPoleEntity()
											.setSupervision_BTS_POWER_POLE_ID(
													curItemData
															.getBts_PowerPoleEntity()
															.getSupervision_BTS_POWER_POLE_ID());

									causeBtsPowerPoleController
											.insertCauseBts_PowerPoleEntity(
													addItemCause,
													addCauseItem
															.getBts_PowerPoleEntity(),
													lastId, idUser);
									ListIdCause.add(lastId);

									// Luu anh nguyen nhan loi neu co

									for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
											.getLstNewAttachFile()) {
										if (!StringUtil.isNullOrEmpty(itemFile
												.getFile_Path())) {
											this.supvConstrAttachFileController
													.coppyAndAddAttachFile(
															this.constr_ConstructionItem,
															itemFile.getFile_Path(),
															lastId,
															Cause_Bts_Power_PoleField.TABLE_NAME);

										}
									}
								}
							}

						}
					}
				}
			} else {
				Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
						this);
				Supervision_Bts_Cat_WorkController catWorkController = new Supervision_Bts_Cat_WorkController(
						this);

				Cause_Bts_Cat_WorkController cauCatWorkController = new Cause_Bts_Cat_WorkController(
						this);

				Supervision_Bts_Cat_WorkEntity btsCatWorkKddEntity = getSupervisionBtsCatWorkKddEntity();

				if (curEditItemKdd.getBts_Cat_WorkEntity().getStatus() < 0) {
					/**
					 * xu li insert thi cong keo dien
					 */

					int idConstrWorkKdd = constrWorkController
							.getConstrWorkEntityByWorkCodeReturnId(Constants.BTS_CONSTR_WORK.WORK_CODE_KEO_DAY_DIEN);
					btsCatWorkKddEntity
							.setCat_Supervision_Constr_Work_Id(idConstrWorkKdd);
					btsCatWorkKddEntity.setSupervision_Bts_Id(btsEntity
							.getSupervision_Bts_Id());

					long idBtsCatWorkKdd = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_Bts_Cat_WorkField.TABLE_NAME);

					if (idBtsCatWorkKdd == 0) {
						outOfKey = true;
						return;
					} else
						outOfKey = false;

					btsCatWorkKddEntity
							.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkKdd);

					btsCatWorkKddEntity
							.setSync_Status(Constants.SYNC_STATUS.ADD);
					btsCatWorkKddEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
					btsCatWorkKddEntity.setProcessId(0);
					btsCatWorkKddEntity.setEmployeeId(idUser);
					btsCatWorkKddEntity.setSupervisionConstrId(btsEntity
							.getSupervision_ConstrEntity()
							.getSupervision_Constr_Id());

					catWorkController
							.insertBtsCatWorkEntity(btsCatWorkKddEntity);

					Cause_Bts_Cat_WorkEntity causeCatWorkKddEntity = new Cause_Bts_Cat_WorkEntity();
					causeCatWorkKddEntity.getBts_Cat_WorkEntity()
							.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkKdd);
					// causeCatWorkTdEntity
					// .setListConstrUnqualifyEntity(listUnqualifyTdEntity);
					causeCatWorkKddEntity.setListCauseUniQualify(curEditItemKdd
							.getListCauseUniQualify());

					if (btsCatWorkKddEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {
						// ArrayList<Integer> ListIdCause = cauCatWorkController
						// .insertCause_Bts_Cat_WorkEntity(causeCatWorkKddEntity);

						ArrayList<Long> ListIdCause = new ArrayList<Long>();

						for (Supervision_LBG_UnqualifyItemEntity unqualify : causeCatWorkKddEntity
								.getListCauseUniQualify()) {
							long lastId = Ktts_KeyController.getInstance()
									.getKttsNextKey(
											Cause_Bts_Cat_WorkField.TABLE_NAME);

							if (lastId == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;

							unqualify.setSync_Status(Constants.SYNC_STATUS.ADD);
							unqualify.setIsActive(Constants.ISACTIVE.ACTIVE);
							unqualify.setProcessId(0);
							unqualify.setSupervisionConstrId(btsEntity
									.getSupervision_ConstrEntity()
									.getSupervision_Constr_Id());

							cauCatWorkController
									.insertCause_Bts_Cat_WorkEntity(unqualify,
											causeCatWorkKddEntity
													.getBts_Cat_WorkEntity(),
											lastId, idUser);
							ListIdCause.add(lastId);

						}
						int dem = 0;
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curEditItemKdd
								.getListCauseUniQualify()) {
							// Luu anh nguyen nhan loi neu co
							// if (!StringUtil.isNullOrEmpty(curItemUnqualify
							// .getNewAttachFile().getFile_Path())) {
							// this.supvConstrAttachFileController
							// .coppyAndAddAttachFile(
							// this.constr_ConstructionEmployeeItem,
							// curItemUnqualify
							// .getNewAttachFile()
							// .getFile_Path(),
							// ListIdCause.get(dem),
							// Cause_Bts_Cat_WorkField.TABLE_NAME);
							//
							// }
							for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
									.getLstNewAttachFile()) {
								if (!StringUtil.isNullOrEmpty(itemFile
										.getFile_Path())) {
									this.supvConstrAttachFileController
											.coppyAndAddAttachFile(
													this.constr_ConstructionItem,
													itemFile.getFile_Path(),
													ListIdCause.get(dem),
													Cause_Bts_Cat_WorkField.TABLE_NAME);

								}
							}
							dem++;
						}
					} else {
						// neu dat thi save anh nghiem thu
						List<Supervision_LBG_UnqualifyItemEntity> curListAcceptance = curEditItemKdd
								.getListAcceptance();
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListAcceptance) {
							Acceptance_Bts_Cat_WorkEntity addCauseItem = new Acceptance_Bts_Cat_WorkEntity();
							addCauseItem
									.setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
											.getCat_Cause_Constr_Acceptance_Id());
							addCauseItem
									.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN);
							addCauseItem
									.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkKdd);
							addCauseItem
									.setSync_Status(Constants.SYNC_STATUS.ADD);
							addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
							addCauseItem.setProcessId(0);
							addCauseItem.setEmployeeId(idUser);

							long iAddCause = Ktts_KeyController
									.getInstance()
									.getKttsNextKey(
											Acceptance_Bts_Cat_WorkField.TABLE_NAME);
							if (iAddCause == 0) {
								outOfKey = true;
								return;
							} else
								outOfKey = false;

							addCauseItem
									.setAcceptance_Bts_Cat_Work_Id(iAddCause);
							cauCatWorkController.addItem(addCauseItem);

							for (Supv_Constr_Attach_FileEntity itemFile : curItemUnqualify
									.getLstNewAttachFile()) {
								if (!StringUtil.isNullOrEmpty(itemFile
										.getFile_Path())) {
									this.supvConstrAttachFileController
											.coppyAndAddAttachFile(
													this.constr_ConstructionItem,
													itemFile.getFile_Path(),
													iAddCause,
													Acceptance_Bts_Cat_WorkField.TABLE_NAME);

								}
							}
						}
					}

					refreshView(sChoice);

				} else {
					/**
					 * xu li update thi cong keo dien
					 */
					btsCatWorkKddEntity
							.setSupervision_Bts_Cat_Work_Id(curEditItemKdd
									.getBts_Cat_WorkEntity()
									.getSupervision_Bts_Cat_Work_Id());

					if (curEditItemKdd.getBts_Cat_WorkEntity().getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
						btsCatWorkKddEntity
								.setSync_Status(Constants.SYNC_STATUS.EDIT);
					} else {
						btsCatWorkKddEntity.setSync_Status(curEditItemKdd
								.getBts_Cat_WorkEntity().getSync_Status());
					}
					catWorkController
							.updateBtsCatWorkEntity(btsCatWorkKddEntity);

					Cause_Bts_Cat_WorkEntity causeCatWorkKddEntity = new Cause_Bts_Cat_WorkEntity();
					causeCatWorkKddEntity.getBts_Cat_WorkEntity()
							.setSupervision_Bts_Cat_Work_Id(
									btsCatWorkKddEntity
											.getSupervision_Bts_Cat_Work_Id());

					causeCatWorkKddEntity.setListCauseUniQualify(curEditItemKdd
							.getListCauseUniQualify());

					for (Supervision_LBG_UnqualifyItemEntity addItemCause : curEditItemKdd
							.getListAcceptance()) {
						/* 1. Chinh sua nghiem thu */
						if (addItemCause.getCause_Line_Bg_Id() > 0) {

							// xoa nghiem thu khi chuyen
							// trang thai tu khong dat sang dat
							// if (isStatus ==
							// Constants.SUPERVISION_STATUS.CHUADAT) {
							// catWorkController.deleteAccept(addItemCause);
							// }

							if (addItemCause.getLstNewAttachFile().size() > 0
									|| (addItemCause.getLstNewAttachFile()
											.size() == 0 && addItemCause
											.getLstAttachFile().size() == 0)) {
								List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
										.getLstAttachFile(
												Acceptance_Bts_Cat_WorkField.TABLE_NAME,
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
													this.constr_ConstructionItem,
													itemFile.getFile_Path(),
													addItemCause
															.getCause_Line_Bg_Id(),
													Acceptance_Bts_Cat_WorkField.TABLE_NAME);

								}
							}

						}
						/* 2. Them moi nghiem thu */
						else {
							if (isStatus == Constants.SUPERVISION_STATUS.DAT) {
								Acceptance_Bts_Cat_WorkEntity addCauseItem = new Acceptance_Bts_Cat_WorkEntity();
								addCauseItem
										.setCat_Cause_Constr_Acceptance_Id(addItemCause
												.getCat_Cause_Constr_Acceptance_Id());
								addCauseItem
										.setSupervision_Bts_Cat_Work_Id(curEditItemKdd
												.getBts_Cat_WorkEntity()
												.getSupervision_Bts_Cat_Work_Id());
								addCauseItem
										.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN);
								addCauseItem
										.setSync_Status(Constants.SYNC_STATUS.ADD);
								addCauseItem
										.setIsActive(Constants.ISACTIVE.ACTIVE);
								addCauseItem.setProcessId(0);
								addCauseItem.setEmployeeId(idUser);

								long iAddCause = Ktts_KeyController
										.getInstance()
										.getKttsNextKey(
												Acceptance_Bts_Cat_WorkField.TABLE_NAME);

								if (iAddCause == 0) {
									outOfKey = true;
									return;
								} else
									outOfKey = false;

								addCauseItem
										.setAcceptance_Bts_Cat_Work_Id(iAddCause);

								cauCatWorkController.addItem(addCauseItem);

								// Luu anh nguyen nhan loi neu co
								for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
										.getLstNewAttachFile()) {
									if (!StringUtil.isNullOrEmpty(itemFile
											.getFile_Path())) {
										this.supvConstrAttachFileController
												.coppyAndAddAttachFile(
														this.constr_ConstructionItem,
														itemFile.getFile_Path(),
														iAddCause,
														Acceptance_Bts_Cat_WorkField.TABLE_NAME);

									}
								}
							}

						}
					}

					for (Supervision_LBG_UnqualifyItemEntity addItemCause : curEditItemKdd
							.getListCauseUniQualify()) {
						// xoa het nguyen nhan khong dat cu
						// cauCatWorkController
						// .deleteCause_Bts_Cat_WorkEntity(unqualify);
						/* 1. Chinh sua nguyen nhan khong dat */
						if (addItemCause.getCause_Line_Bg_Id() > 0) {
							/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
							if (addItemCause.isDelete()) {
								cauCatWorkController
										.deleteCause_Bts_Cat_WorkEntity(addItemCause);
							}
							/* 1.2. Update lai nguyen nhan khong dat */
							else {
//								if (btsCatWorkKddEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT) {
//									cauCatWorkController
//											.deleteCause_Bts_Cat_WorkEntity(addItemCause);
//								}

								if (addItemCause.getLstNewAttachFile().size() > 0
										|| (addItemCause.getLstNewAttachFile()
												.size() == 0 && addItemCause
												.getLstAttachFile().size() == 0)) {
									List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
											.getLstAttachFile(
													Cause_Bts_Cat_WorkField.TABLE_NAME,
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
														this.constr_ConstructionItem,
														itemFile.getFile_Path(),
														addItemCause
																.getCause_Line_Bg_Id(),
														Cause_Bts_Cat_WorkField.TABLE_NAME);

									}
								}
								/* 1.2.1 Thay doi anh */
								// if (!StringUtil.isNullOrEmpty(addItemCause
								// .getNewAttachFile().getFile_Path())) {
								// long idAttachFile = addItemCause
								// .getAttachFile()
								// .getSupv_Constr_Attach_file_Id();
								// /*
								// * Neu da ton tai file luu trong bang
								// * attachment thi chi thay doi ten va duong
								// * dan
								// */
								// if (idAttachFile > 0) {
								//
								// String sFileName = FileManager
								// .getFileName();
								//
								// String sFilePath = FileManager
								// .getSaveFilePath(
								// String.valueOf(this.constr_ConstructionEmployeeItem
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
								// sFileName,
								// sFilePath,
								// addItemCause
								// .getAttachFile()
								// .getSync_Status());
								// }
								// /* Khong ton tai file thi lai them moi */
								// else {
								// this.supvConstrAttachFileController
								// .coppyAndAddAttachFile(
								// this.constr_ConstructionEmployeeItem,
								// addItemCause
								// .getNewAttachFile()
								// .getFile_Path(),
								// addItemCause
								// .getCause_Line_Bg_Id(),
								// Cause_Bts_Cat_WorkField.TABLE_NAME);
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
							if (btsCatWorkKddEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

								ArrayList<Long> ListIdCause = new ArrayList<Long>();

								long lastId = Ktts_KeyController
										.getInstance()
										.getKttsNextKey(
												Cause_Bts_Cat_WorkField.TABLE_NAME);

								if (lastId == 0) {
									outOfKey = true;
									return;
								} else
									outOfKey = false;

								Cause_Bts_Cat_WorkEntity addCauseItem = new Cause_Bts_Cat_WorkEntity();
								addItemCause
										.setSync_Status(Constants.SYNC_STATUS.ADD);
								addItemCause
										.setIsActive(Constants.ISACTIVE.ACTIVE);
								addItemCause.setProcessId(0);
								addItemCause.setSupervisionConstrId(btsEntity
										.getSupervision_ConstrEntity()
										.getSupervision_Constr_Id());

								addCauseItem
										.getBts_Cat_WorkEntity()
										.setSupervision_Bts_Cat_Work_Id(
												causeCatWorkKddEntity
														.getBts_Cat_WorkEntity()
														.getSupervision_Bts_Cat_Work_Id());

								cauCatWorkController
										.insertCause_Bts_Cat_WorkEntity(
												addItemCause,
												addCauseItem
														.getBts_Cat_WorkEntity(),
												lastId, idUser);
								ListIdCause.add(lastId);

								// Luu anh nguyen nhan loi neu co
								// if (!StringUtil.isNullOrEmpty(addItemCause
								// .getNewAttachFile().getFile_Path())) {
								// this.supvConstrAttachFileController
								// .coppyAndAddAttachFile(
								// this.constr_ConstructionEmployeeItem,
								// addItemCause
								// .getNewAttachFile()
								// .getFile_Path(),
								// lastId,
								// Cause_Bts_Cat_WorkField.TABLE_NAME);
								//
								// }
								for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
										.getLstNewAttachFile()) {
									if (!StringUtil.isNullOrEmpty(itemFile
											.getFile_Path())) {
										this.supvConstrAttachFileController
												.coppyAndAddAttachFile(
														this.constr_ConstructionItem,
														itemFile.getFile_Path(),
														lastId,
														Cause_Bts_Cat_WorkField.TABLE_NAME);

									}
								}
							}
						}
					}

				}
			}

			// cap nhat trang thai cong trinh
			Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
					this);
			constr_Controller.updateSyncStatus(constr_ConstructionItem
					.getSupervision_Constr_Id());
			
			// TODO: Thiet lap ket luan. DanhDue ExOICTIF
			boolean bDeny = checkCauseDeny(constr_ConstructionItem);
			Log.i("Check_Deny", String.valueOf(bDeny));
			// Toast.makeText(getApplicationContext(), String.valueOf(checkCauseDeny(constr_ConstructionEmployeeItem)), Toast.LENGTH_LONG).show();
			if (bDeny) {
				constr_Controller.updateStatus(
						constr_ConstructionItem.getSupervision_Constr_Id(), 0);
			} else {
				constr_Controller.updateStatus(
						constr_ConstructionItem.getSupervision_Constr_Id(), 1);
			}
			

		} catch (Exception e) {
			Log.e("error", e.toString());
			// Toast toast = Toast.makeText(this, e.toString(),
			// Toast.LENGTH_LONG);
			//
			// toast.setGravity(Gravity.CENTER, 0, 0);
			// toast.show();

		}

	}

	// tao thuc the bts cat work de insert vao co so du lieu ung voi danh sach
	// nguyen nhan loi ung voi thi cong keo dien
	public Supervision_Bts_Cat_WorkEntity getSupervisionBtsCatWorkKddEntity() {
		Supervision_Bts_Cat_WorkEntity result = new Supervision_Bts_Cat_WorkEntity();
		result.setStatus(isStatus);
		result.setFail_Desc(supervision_bts_power_pole_et_diengiai.getText()
				.toString());
		// result.setListConstrUnqualifyEntity(list);
		return result;
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

	/* Ghi nghiem thu vao danh sach ong */
	private void saveKddAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemKdd
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listKddAcceptanceItem) {
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
				addItem.setUnqulify(true);
				addItem.setLstNewAttachFile(curCauseUniqualify
						.getLstNewAttachFile());
				addItem.setTitle(curCauseUniqualify.getTitle());
				curListUnqualify.add(addItem);
			}
		}
	}

	/**
	 * Ham set lai nghiem thu cho 1 be trong list danh sach nguyen nhan loi
	 */
	private void setKddAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemKdd
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listKddAcceptanceItem) {
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

	/* Ghi nguyen nhan khong dat vao danh sach cot */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
	private void saveUnqualifyKdd() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemKdd
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkItem) {
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
					addItem.setCause_Line_Bg_Id(this.curEditItemKdd
							.getCause_Bts_Cat_Work_Id());
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

	private void setUnqualifyKdd() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItemKdd
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkItem) {
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

	/* Ghi nguyen nhan khong dat vao danh sach cot */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
	private void saveUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPowerPoleUnqualifyItem) {
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
					addItem.setCause_Line_Bg_Id(this.curEditItem
							.getCause_Bts_Power_Pole_Id());
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
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPowerPoleUnqualifyItem) {
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

	/* Lay nguyen nhan loi tu id */
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

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBts_PowerPollView.findViewById(R.id.actionbar);
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
				requestSync(SupervisionBtsPowerPoleActivity.this);
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

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		gotoHomeActivity(new Bundle());
		finish();
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

			refreshView(sChoice);

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

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		int i = tabs.getCurrentTab();
		if (i == 0) {
			supervision_bts_power_pole_add.setVisibility(View.VISIBLE);
		} else {
			supervision_bts_power_pole_add.setVisibility(View.GONE);
		}
	}
}
