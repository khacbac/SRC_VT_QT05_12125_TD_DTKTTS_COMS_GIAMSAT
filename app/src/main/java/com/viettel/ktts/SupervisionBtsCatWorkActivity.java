package com.viettel.ktts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Contruction_AcceptancePopup;
import com.viettel.actionbar.Contruction_UnqualifyPopup;
import com.viettel.actionbar.Header;
import com.viettel.actionbar.Image_ViewGalleryPopup;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.Constants.BTS_HOUSE_TYPE;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Cat_Cause_Constr_AcceptanceController;
import com.viettel.database.Cat_Cause_Constr_UnQualifyController;
import com.viettel.database.Cat_Supervision_Constr_WorkController;
import com.viettel.database.Cause_Bts_Cat_WorkController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_Cat_WorkController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Bts_Cat_WorkField;
import com.viettel.database.field.Cause_Bts_Cat_WorkField;
import com.viettel.database.field.Supervision_Bts_Cat_WorkField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

public class SupervisionBtsCatWorkActivity extends SupervisionBtsBaseActivity
		implements OnTabChangeListener {
	private View spvBTS_CatWorkView;
	private Constr_Construction_EmployeeEntity constr_ConstructionItem;
	private Supervision_BtsEntity btsEntity;

	/* Hien thi popup */
	private Menu_DropdownPopup dropdownPopupMenuInfomation;
	private Menu_DropdownPopup dropdownPopupMenuStatusTcpm;
	private Menu_DropdownPopup dropdownPopupMenuStatusTcnmn;
	private Menu_DropdownPopup dropdownPopupMenuStatusKdd;
	private Menu_DropdownPopup dropdownPopupMenuDidayType;
	private Contruction_UnqualifyPopup contructionUnqualifyPopup;
//	private Image_ViewGalleryPopup imgViewPopup;

	// private int dropdownWidth = 20;
	private List<DropdownItemEntity> listInfomation = null;
	private List<DropdownItemEntity> listStatusTcpm = null;
	private List<DropdownItemEntity> listStatusTcnmn = null;
	private List<DropdownItemEntity> listStatusKdd = null;
	private List<DropdownItemEntity> listDidayType = null;

	private int isInfomation = 1;
	private int isStatusTcpm = -1;
	private int isStatusTcnmn = -1;
	private int isStatusKdd = -1;
	private int isDidayType = 0;

	int countNnkdCheckPm;
	int countNnkdCheckNmn;
	int countNnkdCheckKdd;

	/**
	 * combobox
	 */
	// thong tin thiet ke
	private RelativeLayout supervision_bts_catwork_cb_thietke;
	private TextView supervision_bts_catwork_tv_thietke;

	private RelativeLayout supervision_bts_catwork_thicongphongmay_cb_chontrangthai;
	private TextView supervision_bts_catwork_thicongphongmay_chontrangthai;

	private RelativeLayout supervision_bts_catwork_thicongnhamayno_cb_chontrangthai;
	private TextView supervision_bts_catwork_thicongnhamayno_chontrangthai;

	private RelativeLayout supervision_bts_catwork_thicongkeoday_cb_chontrangthai;
	private TextView supervision_bts_catwork_thicongkeoday_chontrangthai;

	private RelativeLayout supervision_bts_catwork_thicongkeoday_cb_loaididay;
	private TextView supervision_bts_catwork_thicongkeoday_loaididay;

	/**
	 * text view
	 */
	// ma tram
	private TextView supervision_bts_tv_matram;
	// ma cong trinh
	private TextView supervision_bts_tv_mact;

	private TextView supervision_bts_kdd_complete_date;
	private TextView supervision_bts_nmn_complete_date;
	private TextView supervision_bts_pm_complete_date;

	/**
	 * nguyen nhan khong dat
	 */
	// nguyen nhan khong dat thi cong phong may
	private TextView supervision_bts_catwork_thicongphongmay_chonnnkd;
	// nguyen nhan khong dat thi cong nha may no
	private TextView supervision_bts_catwork_thicongnhamayno_chonnnkd;
	// nguyen nhan khong dat thi cong keo dien
	private TextView supervision_bts_catwork_thicongkeoday_chonnnkd;

	/**
	 * edit text
	 */
	private EditText supervision_bts_catwork_thicongphongmay_diengiai;
	private EditText supervision_bts_catwork_thicongnhamayno_diengiai;
	private EditText supervision_bts_catwork_thicongkeoday_diengiai;
	private EditText supervision_bts_catwork_thicongphongmay_thietke;
	private EditText supervision_bts_catwork_thicongphongmay_thucte;
	private EditText supervision_bts_catwork_thicongnhamayno_thietke;
	private EditText supervision_bts_catwork_thicongnhamayno_thucte;
	// private EditText supervision_bts_catwork_thicongkeoday_thietke;
	// private EditText supervision_bts_catwork_thicongkeoday_thucte;

	/**
	 * button
	 */
	private Button supervision_bts_cat_work_save;

	private Button rl_supervision_bts_cat_work_complete;

	private Cause_Bts_Cat_WorkEntity causeCatWorkEntityPm;
	private Cause_Bts_Cat_WorkEntity causeCatWorkEntityNmn;
	private Cause_Bts_Cat_WorkEntity causeCatWorkEntityKdd;

	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;

	private boolean choicePm = false;
	private boolean choiceNmn = false;
	private boolean choiceKdd = false;
	private boolean outOfKey = false;

	private int tabHeight = 40;

	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkPmNewItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkPmLapGhepItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkPmCaiTaoItem;

	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkNmnNewItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkNmnVuotLuItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkNmnTienCheItem;

	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkKddItem;

	private Cat_Supervision_Constr_WorkController constrWorkController;
	private Supervision_Bts_Cat_WorkController catWorkController;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;

	private ScrollView sc_supervision_bts_catwork_pm;
	private ScrollView sc_supervision_bts_catwork_nmn;
	private ScrollView sc_supervision_bts_catwork_kdd;
	private RelativeLayout supervision_bts_catwork_notification_pm;
	private RelativeLayout supervision_bts_catwork_notification_nmn;
	private RelativeLayout supervision_bts_catwork_notification_keodaydien;

	private Cause_Bts_Cat_WorkController cauCatWorkController;
	private TabHost tabs;
	private ConfirmDialog confirmSave;
	private Cause_Bts_Cat_WorkController causeCatWorkController;

	private List<Supervision_LBG_UnqualifyItemEntity> listPmNewAcceptanceItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listPmAssemblyAcceptanceItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listNmnNewAcceptanceItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listNmnAssemblyAcceptanceItem;
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.supervision_bts_cat_work_activity);
		setTitle(getString(R.string.supervision_bts_title));
		initComponent();
		// setHeader();

		isInfomation = getIntent().getExtras().getInt(
				IntentConstants.INTENT_DESIGNINFO);

		initData();

		constr_ConstructionItem = getConstr_Construction_Employee();
		setData();
		closeProgressDialog();

	}

	public void setData() {

		constrWorkController = new Cat_Supervision_Constr_WorkController(this);
		catWorkController = new Supervision_Bts_Cat_WorkController(this);
		cauCatWorkController = new Cause_Bts_Cat_WorkController(this);
		supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
				this);
		causeCatWorkController = new Cause_Bts_Cat_WorkController(this);

		supervision_bts_tv_matram.setText(constr_ConstructionItem
				.getStationCode());
		supervision_bts_tv_mact.setText(String.valueOf(constr_ConstructionItem
				.getConstrCode()));

		Supervision_BtsController bts_Controller = new Supervision_BtsController(
				this);
		btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
				.getSupervision_Constr_Id());

		if (btsEntity.getHouse_TYPE() == BTS_HOUSE_TYPE.CO_SAN) {
			supervision_bts_catwork_notification_pm.setVisibility(View.VISIBLE);
			sc_supervision_bts_catwork_pm.setVisibility(View.GONE);
		}

		if (btsEntity.getHouse_GENERATOR() == Constants.ID_ENTITY_DEFAULT) {
			supervision_bts_catwork_notification_nmn
					.setVisibility(View.VISIBLE);
			sc_supervision_bts_catwork_nmn.setVisibility(View.GONE);

			supervision_bts_catwork_notification_keodaydien
					.setVisibility(View.VISIBLE);
			sc_supervision_bts_catwork_kdd.setVisibility(View.GONE);
		}

		Cat_Cause_Constr_UnQualifyController unqualifyController = new Cat_Cause_Constr_UnQualifyController(
				this);

		Cat_Cause_Constr_AcceptanceController acceptController = new Cat_Cause_Constr_AcceptanceController(
				this);

		/**
		 * lay du lieu nguyen nhan loi cat work
		 */
		listUnqualifyCatWorkPmNewItem = unqualifyController
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.BTS_PHONG_MAY_NEW_UNQUALIFY,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listUnqualifyCatWorkPmLapGhepItem = unqualifyController
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.BTS_PHONG_MAY_LAP_GHEP_UNQUALIFY,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listUnqualifyCatWorkPmCaiTaoItem = unqualifyController
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.BTS_PHONG_MAY_CAI_TAO_UNQUALIFY,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listUnqualifyCatWorkNmnNewItem = unqualifyController
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.BTS_NHA_MAY_NO_NEW_UNQUALIFY,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listUnqualifyCatWorkNmnVuotLuItem = unqualifyController
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.BTS_NHA_MAY_NO_VUOT_LU_UNQUALIFY,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listUnqualifyCatWorkNmnTienCheItem = unqualifyController
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.BTS_NHA_MAY_NO_TIEN_CHE_UNQUALIFY,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listPmNewAcceptanceItem = acceptController.getAllUnQualifyItemByName(
				Constants.ACCEPTANCE_TYPE.BTS_PHONG_MAY_NEW,
				Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listPmAssemblyAcceptanceItem = acceptController
				.getAllUnQualifyItemByName(
						Constants.ACCEPTANCE_TYPE.BTS_PHONG_MAY_ASSEMBLY,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listNmnAssemblyAcceptanceItem = acceptController
				.getAllUnQualifyItemByName(
						Constants.ACCEPTANCE_TYPE.BTS_NHA_MAY_NO_ASSEMBLY,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listNmnNewAcceptanceItem = acceptController.getAllUnQualifyItemByName(
				Constants.ACCEPTANCE_TYPE.BTS_NHA_MAY_NO_NEW,
				Constants.UNQUALIFY_TYPE.BTS_TYPE);

		listUnqualifyCatWorkKddItem = unqualifyController
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN_PHONG_MAY_NO,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		refreshForm();

		showCompleteDate(constr_ConstructionItem,
				Constants.PROGRESS_TYPE.BTS_TYPE,
				Constants.PROGRESS_TYPE.PHONG_MAY,
				supervision_bts_pm_complete_date,
				rl_supervision_bts_cat_work_complete);

		/* Set endable va disable voi cong trinh da hoan thanh */
		if (constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED
				|| constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_COSITE) {
			// this.rl_supervision_bts_cat_work_complete.setVisibility(View.GONE);
			this.supervision_bts_cat_work_save.setVisibility(View.GONE);
		}
		if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE
				&& btsEntity.getConstructionType() == Constants.CONSTRUCTION_TYPE.NANG_CAP) {
			// this.rl_supervision_bts_cat_work_complete.setVisibility(View.GONE);
			this.supervision_bts_cat_work_save.setVisibility(View.GONE);
		}
	}

	public void refreshForm() {
		String sChoice = getResources().getString(R.string.text_choice1);

		// lay thuc the de set du lieu ung voi thi cong phong may
		causeCatWorkEntityPm = causeCatWorkController
				.getCause_Bts_Cat_WorkEntity(
						Constants.UNQUALIFY_TYPE.SUB_TYPE_PHONG_MAY,
						btsEntity.getSupervision_Bts_Id());

		// lay thuc the de set du lieu ung voi thi cong nha may no
		causeCatWorkEntityNmn = causeCatWorkController
				.getCause_Bts_Cat_WorkEntity(
						Constants.UNQUALIFY_TYPE.SUB_TYPE_NHA_MAY_NO,
						btsEntity.getSupervision_Bts_Id());

		// lay thuc the de set du lieu ung voi thi cong keo day dien nha may no
		causeCatWorkEntityKdd = causeCatWorkController
				.getCause_Bts_Cat_WorkEntity(
						Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN_PHONG_MAY_NO,
						btsEntity.getSupervision_Bts_Id());

		// kiem tra nguyen nhan khong dat neu danh gia la khong dat
		countNnkdCheckPm = 0;

		for (int i = 0; i < this.causeCatWorkEntityPm.getListCauseUniQualify()
				.size(); i++) {
			if (!causeCatWorkEntityPm.getListCauseUniQualify().get(i)
					.isDelete()) {
				countNnkdCheckPm++;
				break;
			}
		}

		countNnkdCheckNmn = 0;

		for (int i = 0; i < this.causeCatWorkEntityNmn.getListCauseUniQualify()
				.size(); i++) {
			if (!causeCatWorkEntityNmn.getListCauseUniQualify().get(i)
					.isDelete()) {
				countNnkdCheckNmn++;
				break;
			}
		}

		countNnkdCheckKdd = 0;

		for (int i = 0; i < this.causeCatWorkEntityKdd.getListCauseUniQualify()
				.size(); i++) {
			if (!causeCatWorkEntityKdd.getListCauseUniQualify().get(i)
					.isDelete()) {
				countNnkdCheckKdd++;
				break;
			}
		}

		// set du lieu vao thi cong phong may
		if (causeCatWorkEntityPm.getBts_Cat_WorkEntity().getStatus() >= 0) {
			isStatusTcpm = causeCatWorkEntityPm.getBts_Cat_WorkEntity()
					.getStatus();

			if (isStatusTcpm < 0) {
				supervision_bts_catwork_thicongphongmay_chontrangthai
						.setText(sChoice);
			} else
				supervision_bts_catwork_thicongphongmay_chontrangthai
						.setText(GloablUtils
								.getStringBtsCatWorkStatus(isStatusTcpm));

			supervision_bts_catwork_thicongphongmay_thietke
					.setText(causeCatWorkEntityPm.getBts_Cat_WorkEntity()
							.getDimension_Design());

			supervision_bts_catwork_thicongphongmay_thucte
					.setText(causeCatWorkEntityPm.getBts_Cat_WorkEntity()
							.getDimension_Real());

			List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = causeCatWorkEntityPm
					.getListCauseUniQualify();

			/* Gan anh nghiem thu */
			for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : causeCatWorkEntityPm
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
			causeCatWorkEntityPm.setListCauseUniQualify(listUnqualifyItem);

			int countAcceptCheckPm = 0;

			for (int i = 0; i < this.causeCatWorkEntityPm.getListAcceptance()
					.size(); i++) {
				if (causeCatWorkEntityPm.getListAcceptance().get(i)
						.getLstNewAttachFile().size() > 0
						|| causeCatWorkEntityPm.getListAcceptance().get(i)
								.getLstAttachFile().size() > 0) {
					countAcceptCheckPm++;
					break;
				}
			}

			if (isStatusTcpm == Constants.TANK_STATUS.DAT) {
				if (countAcceptCheckPm > 0) {
					supervision_bts_catwork_thicongphongmay_chonnnkd
							.setText(StringUtil
									.getString(R.string.text_view_dot));
				} else {
					supervision_bts_catwork_thicongphongmay_chonnnkd
							.setText(StringUtil.getString(R.string.text_empty));
				}
			} else {
				if (causeCatWorkEntityPm.getListCauseUniQualify().size() > 0) {
					supervision_bts_catwork_thicongphongmay_chonnnkd
							.setText(StringUtil
									.getString(R.string.text_view_dot));
				} else
					supervision_bts_catwork_thicongphongmay_chonnnkd
							.setText(StringUtil.getString(R.string.text_empty));
			}

			supervision_bts_catwork_thicongphongmay_diengiai
					.setText(causeCatWorkEntityPm.getBts_Cat_WorkEntity()
							.getFail_Desc());
		}

		// set du lieu vao thi cong nha may no
		if (causeCatWorkEntityNmn.getBts_Cat_WorkEntity().getStatus() >= 0) {
			isStatusTcnmn = causeCatWorkEntityNmn.getBts_Cat_WorkEntity()
					.getStatus();

			if (isStatusTcnmn < 0) {
				supervision_bts_catwork_thicongnhamayno_chontrangthai
						.setText(sChoice);
			} else
				supervision_bts_catwork_thicongnhamayno_chontrangthai
						.setText(GloablUtils
								.getStringBtsCatWorkStatus(isStatusTcnmn));

			supervision_bts_catwork_thicongnhamayno_thietke
					.setText(causeCatWorkEntityNmn.getBts_Cat_WorkEntity()
							.getDimension_Design());

			supervision_bts_catwork_thicongnhamayno_thucte
					.setText(causeCatWorkEntityNmn.getBts_Cat_WorkEntity()
							.getDimension_Real());

			List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = causeCatWorkEntityNmn
					.getListCauseUniQualify();

			/* Gan anh nghiem thu */
			for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : causeCatWorkEntityNmn
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
			causeCatWorkEntityNmn.setListCauseUniQualify(listUnqualifyItem);

			int countAcceptCheckNmn = 0;

			for (int i = 0; i < this.causeCatWorkEntityNmn.getListAcceptance()
					.size(); i++) {
				if (causeCatWorkEntityNmn.getListAcceptance().get(i)
						.getLstNewAttachFile().size() > 0
						|| causeCatWorkEntityNmn.getListAcceptance().get(i)
								.getLstAttachFile().size() > 0) {
					countAcceptCheckNmn++;
					break;
				}
			}

			if (isStatusTcnmn == Constants.TANK_STATUS.DAT) {
				if (countAcceptCheckNmn > 0) {
					supervision_bts_catwork_thicongnhamayno_chonnnkd
							.setText(StringUtil
									.getString(R.string.text_view_dot));
				} else {
					supervision_bts_catwork_thicongnhamayno_chonnnkd
							.setText(StringUtil.getString(R.string.text_empty));
				}
			} else {
				if (causeCatWorkEntityNmn.getListCauseUniQualify().size() > 0) {
					supervision_bts_catwork_thicongnhamayno_chonnnkd
							.setText(StringUtil
									.getString(R.string.text_view_dot));
				} else
					supervision_bts_catwork_thicongnhamayno_chonnnkd
							.setText(StringUtil.getString(R.string.text_empty));
			}

			supervision_bts_catwork_thicongnhamayno_diengiai
					.setText(causeCatWorkEntityNmn.getBts_Cat_WorkEntity()
							.getFail_Desc());
		}

		// set du lieu vao thi cong keo day dien nha may no
		if (causeCatWorkEntityKdd.getBts_Cat_WorkEntity().getStatus() >= 0) {
			isStatusKdd = causeCatWorkEntityKdd.getBts_Cat_WorkEntity()
					.getStatus();

			if (isStatusKdd < 0) {
				supervision_bts_catwork_thicongkeoday_chontrangthai
						.setText(sChoice);
			} else
				supervision_bts_catwork_thicongkeoday_chontrangthai
						.setText(GloablUtils
								.getStringBtsCatWorkStatus(isStatusKdd));

			List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = causeCatWorkEntityKdd
					.getListCauseUniQualify();

			/* Gan anh nguyen nhan loi */
			for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {

				List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
						.getLstAttachFile(Cause_Bts_Cat_WorkField.TABLE_NAME,
								curUnqualifyItem.getCause_Line_Bg_Id());
				for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
					curUnqualifyItem.getLstAttachFile().add(itemFile);
				}
			}
			causeCatWorkEntityKdd.setListCauseUniQualify(listUnqualifyItem);
			if (causeCatWorkEntityKdd.getListCauseUniQualify().size() > 0) {
				supervision_bts_catwork_thicongkeoday_chonnnkd
						.setText(StringUtil.getString(R.string.text_view_dot));
			} else
				supervision_bts_catwork_thicongkeoday_chonnnkd
						.setText(StringUtil.getString(R.string.text_empty));

			supervision_bts_catwork_thicongkeoday_diengiai
					.setText(causeCatWorkEntityKdd.getBts_Cat_WorkEntity()
							.getFail_Desc());

			isDidayType = causeCatWorkEntityKdd.getBts_Cat_WorkEntity()
					.getPulling_Wire_Type();
			supervision_bts_catwork_thicongkeoday_loaididay.setText(GloablUtils
					.getStringBtsCatWorkDidayType(isDidayType));
		}
	}

	public void initData() {
		// TODO Auto-generated method stub
		this.causeCatWorkEntityPm = new Cause_Bts_Cat_WorkEntity();
		this.causeCatWorkEntityNmn = new Cause_Bts_Cat_WorkEntity();
		this.causeCatWorkEntityKdd = new Cause_Bts_Cat_WorkEntity();

		String sChoice = "";
		listInfomation = GloablUtils.getListBTSInfo(sChoice);

		sChoice = getResources().getString(R.string.text_choice1);
		supervision_bts_catwork_tv_thietke.setText(GloablUtils
				.getStringBTSInfo(isInfomation));

		listStatusTcpm = GloablUtils.getListBtsCatWorkStatusTcpm(sChoice);
		supervision_bts_catwork_thicongphongmay_chontrangthai
				.setText(GloablUtils.getStringBtsCatWorkStatus(isStatusTcpm));
		supervision_bts_catwork_thicongphongmay_chontrangthai.setText(sChoice);

		listStatusTcnmn = GloablUtils.getListBtsCatWorkStatusTcnmn(sChoice);
		supervision_bts_catwork_thicongnhamayno_chontrangthai
				.setText(GloablUtils.getStringBtsCatWorkStatus(isStatusTcnmn));
		supervision_bts_catwork_thicongnhamayno_chontrangthai.setText(sChoice);

		listStatusKdd = GloablUtils.getListBtsCatWorkStatusTckd(sChoice);
		supervision_bts_catwork_thicongkeoday_chontrangthai.setText(GloablUtils
				.getStringBtsCatWorkStatus(isStatusKdd));
		supervision_bts_catwork_thicongkeoday_chontrangthai.setText(sChoice);

		listDidayType = GloablUtils.getListBtsCatWorDidayType(sChoice);
		supervision_bts_catwork_thicongkeoday_loaididay.setText(GloablUtils
				.getStringBtsCatWorkDidayType(isDidayType));
		supervision_bts_catwork_thicongkeoday_loaididay.setText(sChoice);

	}

	public void initComponent() {
		spvBTS_CatWorkView = addView(
				R.layout.supervision_bts_cat_work_activity,
				R.id.rlspv_bt_catwork);
		String thicong_phongmay = StringUtil
				.getString(R.string.supervision_bts_catwork_tv_thicongphongmay);
		tabs = (TabHost) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_cat_work_tabhost);
		tabs.setup();
		// tab thi cong phong may
		TabHost.TabSpec specTcpm = tabs.newTabSpec(thicong_phongmay);
		specTcpm.setContent(R.id.supervision_bts_catwork_tl_thicongphongmay);
		specTcpm.setIndicator(makeTabIndicator(thicong_phongmay));

		// tab thi cong nha may no
		String thicong_nhamayno = StringUtil
				.getString(R.string.supervision_bts_catwork_tv_thicongnhamayno);
		TabHost.TabSpec specTcnmn = tabs.newTabSpec(thicong_nhamayno);
		specTcnmn.setContent(R.id.supervision_bts_catwork_tl_thicongnhamayno);
		specTcnmn.setIndicator(makeTabIndicator(thicong_nhamayno));

		// tab thi cong keo dien
		String thicong_keodaydien = StringUtil
				.getString(R.string.supervision_bts_catwork_tv_thicongkeoday);
		TabHost.TabSpec specTckdd = tabs.newTabSpec(thicong_keodaydien);
		specTckdd.setContent(R.id.supervision_bts_catwork_tl_thicongkeodaydien);
		specTckdd.setIndicator(makeTabIndicator(thicong_keodaydien));

		tabs.addTab(specTcpm);
		tabs.addTab(specTcnmn);
		tabs.addTab(specTckdd);
		tabs.setOnTabChangedListener(this);

		// combobox
		setSupervision_bts_catwork_cb_thietke((RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_cat_work_search_thietke));
		supervision_bts_catwork_tv_thietke = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_cat_work_tv_thietke);
		supervision_bts_catwork_tv_thietke.setOnClickListener(this);

		setSupervision_bts_catwork_thicongphongmay_cb_chontrangthai((RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongphongmay_row1_chontrangthai));
		supervision_bts_catwork_thicongphongmay_chontrangthai = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongphongmay_row1_tv_chontrangthai);
		supervision_bts_catwork_thicongphongmay_chontrangthai
				.setOnClickListener(this);

		setSupervision_bts_catwork_thicongnhamayno_cb_chontrangthai((RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongnhamayno_row1_chontrangthai));
		supervision_bts_catwork_thicongnhamayno_chontrangthai = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongnhamayno_row1_tv_chontrangthai);
		supervision_bts_catwork_thicongnhamayno_chontrangthai
				.setOnClickListener(this);

		setSupervision_bts_catwork_thicongkeoday_cb_chontrangthai((RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongkeoday_row1_chontrangthai));
		supervision_bts_catwork_thicongkeoday_chontrangthai = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongkeoday_row1_tv_chontrangthai);
		supervision_bts_catwork_thicongkeoday_chontrangthai
				.setOnClickListener(this);

		setSupervision_bts_catwork_thicongkeoday_cb_loaididay((RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongkeoday_row2_rl_anh));
		supervision_bts_catwork_thicongkeoday_loaididay = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongkeoday_row2_loaididay);
		supervision_bts_catwork_thicongkeoday_loaididay
				.setOnClickListener(this);

		// textview
		supervision_bts_tv_matram = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_cat_work_tv_matram);
		supervision_bts_tv_mact = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_cat_work_tv_mact);

		supervision_bts_kdd_complete_date = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_kdd_complete_date);
		supervision_bts_nmn_complete_date = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_nmn_complete_date);
		supervision_bts_pm_complete_date = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_pm_complete_date);

		// nguyen nhan khong dat
		supervision_bts_catwork_thicongphongmay_chonnnkd = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongphongmay_row1_tv_chonnnkd);
		supervision_bts_catwork_thicongnhamayno_chonnnkd = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongnhamayno_row1_tv_chonnnkd);
		supervision_bts_catwork_thicongkeoday_chonnnkd = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongkeoday_row1_tv_chonnnkd);

		supervision_bts_catwork_thicongphongmay_chonnnkd
				.setOnClickListener(this);
		supervision_bts_catwork_thicongnhamayno_chonnnkd
				.setOnClickListener(this);
		supervision_bts_catwork_thicongkeoday_chonnnkd.setOnClickListener(this);
		// edit text
		supervision_bts_catwork_thicongphongmay_diengiai = (EditText) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongphongmay_row2_et_diengiai);
		registerForContextMenu(supervision_bts_catwork_thicongphongmay_diengiai);
		supervision_bts_catwork_thicongnhamayno_diengiai = (EditText) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongnhamayno_row2_et_diengiai);
		registerForContextMenu(supervision_bts_catwork_thicongnhamayno_diengiai);
		supervision_bts_catwork_thicongkeoday_diengiai = (EditText) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_catwork_tl_thicongkeoday_row2_et_diengiai);
		registerForContextMenu(supervision_bts_catwork_thicongkeoday_diengiai);

		supervision_bts_catwork_thicongphongmay_thietke = (EditText) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_catwork_tl_thicongphongmay_size_drc_et_thietke);
		registerForContextMenu(supervision_bts_catwork_thicongphongmay_thietke);
		supervision_bts_catwork_thicongphongmay_thucte = (EditText) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_catwork_tl_thicongphongmay_size_drc_et_thucte);
		registerForContextMenu(supervision_bts_catwork_thicongphongmay_thucte);
		supervision_bts_catwork_thicongnhamayno_thietke = (EditText) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_catwork_tl_thicongnhamayno_size_drc_et_thietke);
		// registerForContextMenu(supervision_bts_catwork_thicongnhamayno_thietke);
		supervision_bts_catwork_thicongnhamayno_thucte = (EditText) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_catwork_tl_thicongnhamayno_size_drc_et_thucte);
		// registerForContextMenu(supervision_bts_catwork_thicongnhamayno_thucte);

		sc_supervision_bts_catwork_pm = (ScrollView) spvBTS_CatWorkView
				.findViewById(R.id.sc_supervision_bts_catwork_pm);
		sc_supervision_bts_catwork_nmn = (ScrollView) spvBTS_CatWorkView
				.findViewById(R.id.sc_supervision_bts_catwork_nmn);
		sc_supervision_bts_catwork_kdd = (ScrollView) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_catwork_tl_thicongkeoday);
		supervision_bts_catwork_notification_pm = (RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_catwork_notification_pm);
		supervision_bts_catwork_notification_nmn = (RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_catwork_notification_nmn);
		supervision_bts_catwork_notification_keodaydien = (RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.supervision_bts_catwork_notification_keodaydien);
		// supervision_bts_catwork_thicongkeoday_thietke = (EditText)
		// findViewById(R.id.supervision_bts_catwork_tl_thicongkeoday_size_drc_et_thietke);
		// supervision_bts_catwork_thicongkeoday_thucte = (EditText)
		// findViewById(R.id.supervision_bts_catwork_tl_thicongkeoday_size_drc_et_thucte);

		// button
		supervision_bts_cat_work_save = (Button) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_cat_work_save);
		supervision_bts_cat_work_save.setOnClickListener(this);

		rl_supervision_bts_cat_work_complete = (Button) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_cat_work_complete);
		rl_supervision_bts_cat_work_complete.setOnClickListener(this);
	}

	public void clearFocus() {
		supervision_bts_catwork_thicongphongmay_diengiai
				.setFocusableInTouchMode(false);
		supervision_bts_catwork_thicongphongmay_diengiai.clearFocus();

		supervision_bts_catwork_thicongnhamayno_diengiai
				.setFocusableInTouchMode(false);
		supervision_bts_catwork_thicongnhamayno_diengiai.clearFocus();

		supervision_bts_catwork_thicongkeoday_diengiai
				.setFocusableInTouchMode(false);
		supervision_bts_catwork_thicongkeoday_diengiai.clearFocus();

		supervision_bts_catwork_thicongphongmay_thietke
				.setFocusableInTouchMode(false);
		supervision_bts_catwork_thicongphongmay_thietke.clearFocus();

		supervision_bts_catwork_thicongphongmay_thucte
				.setFocusableInTouchMode(false);
		supervision_bts_catwork_thicongphongmay_thucte.clearFocus();

		supervision_bts_catwork_thicongnhamayno_thietke
				.setFocusableInTouchMode(false);
		supervision_bts_catwork_thicongnhamayno_thietke.clearFocus();

		supervision_bts_catwork_thicongnhamayno_thucte
				.setFocusableInTouchMode(false);
		supervision_bts_catwork_thicongnhamayno_thucte.clearFocus();

		supervision_bts_catwork_thicongphongmay_diengiai
				.setFocusableInTouchMode(true);
		supervision_bts_catwork_thicongnhamayno_diengiai
				.setFocusableInTouchMode(true);
		supervision_bts_catwork_thicongkeoday_diengiai
				.setFocusableInTouchMode(true);
		supervision_bts_catwork_thicongphongmay_thietke
				.setFocusableInTouchMode(true);
		supervision_bts_catwork_thicongphongmay_thucte
				.setFocusableInTouchMode(true);
	}

	private TextView makeTabIndicator(String text) {
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

	public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
		return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
				.getSerializable(IntentConstants.INTENT_DATA);
	}

	// tao thuc the bts cat work de insert vao co so du lieu ung voi danh sach
	// nguyen nhan loi ung voi thi cong phong may
	public Supervision_Bts_Cat_WorkEntity getSupervisionBtsCatWorkPmEntity() {
		Supervision_Bts_Cat_WorkEntity result = new Supervision_Bts_Cat_WorkEntity();
		result.setStatus(isStatusTcpm);
		result.setFail_Desc(supervision_bts_catwork_thicongphongmay_diengiai
				.getText().toString());

		// result.setListConstrUnqualifyEntity(list);
		return result;
	}

	// tao thuc the bts cat work de insert vao co so du lieu ung voi danh sach
	// nguyen nhan loi ung voi thi cong nha may no
	public Supervision_Bts_Cat_WorkEntity getSupervisionBtsCatWorkNmnEntity() {
		Supervision_Bts_Cat_WorkEntity result = new Supervision_Bts_Cat_WorkEntity();
		result.setStatus(isStatusTcnmn);
		result.setFail_Desc(supervision_bts_catwork_thicongnhamayno_diengiai
				.getText().toString());
		// result.setListConstrUnqualifyEntity(list);
		return result;
	}

	// tao thuc the bts cat work de insert vao co so du lieu ung voi thi cong
	// keo day dien
	public Supervision_Bts_Cat_WorkEntity getSupervisionBtsCatWorkKddEntity() {
		Supervision_Bts_Cat_WorkEntity result = new Supervision_Bts_Cat_WorkEntity();
		result.setStatus(isStatusKdd);
		result.setFail_Desc(supervision_bts_catwork_thicongkeoday_diengiai
				.getText().toString());
		// result.setListConstrUnqualifyEntity(list);
		result.setPulling_Wire_Type(isDidayType);
		return result;
	}

	private String checkVailidPm(Cause_Bts_Cat_WorkEntity itemCheck) {
		String sReslut = "";
		try {
			if (btsEntity.getHouse_TYPE() != Constants.BTS_HOUSE_TYPE.CO_SAN) {
				if (isStatusTcpm < 0) {
					sReslut = StringUtil
							.getString(R.string.constr_cat_work_danhgia_tcpm_empty);
					tabs.setCurrentTab(0);
					supervision_bts_catwork_thicongphongmay_chontrangthai
							.setError(Html.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					supervision_bts_catwork_thicongphongmay_chontrangthai
							.requestFocus();
					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();

					clearFocus();

				} else if (isStatusTcpm == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT
						&& countNnkdCheckPm < 1) {
					tabs.setCurrentTab(0);
					sReslut = StringUtil
							.getString(R.string.constr_cat_work_nn_khongdat_tcpm_tempty);

					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();

					clearFocus();

				} else if (isStatusTcpm == Constants.BTS_CAT_WORK_STATUS.DAT) {
					int countAcceptCheck = 0;

					if (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP
							|| btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL) {
						for (int i = 0; i < itemCheck.getListAcceptance()
								.size(); i++) {
							if ((itemCheck.getListAcceptance().get(i)
									.getLstNewAttachFile().size() > 0 || itemCheck
									.getListAcceptance().get(i)
									.getLstAttachFile().size() > 0)
									&& (i == 0)) {
								countAcceptCheck++;
							}
						}

						if (countAcceptCheck < 1) {
							sReslut = StringUtil
									.getString(R.string.constr_take_acceptance_not_enough);
							Toast.makeText(getApplicationContext(), sReslut,
									Toast.LENGTH_LONG).show();

							clearFocus();
						}
					} else {
						for (int i = 0; i < itemCheck.getListAcceptance()
								.size(); i++) {
							if ((itemCheck.getListAcceptance().get(i)
									.getLstNewAttachFile().size() > 0 || itemCheck
									.getListAcceptance().get(i)
									.getLstAttachFile().size() > 0)
									&& (i == 0)) {
								countAcceptCheck++;
							}
						}

						if (countAcceptCheck < 1) {
							sReslut = StringUtil
									.getString(R.string.constr_take_acceptance_not_enough);
							Toast.makeText(getApplicationContext(), sReslut,
									Toast.LENGTH_LONG).show();

							clearFocus();
						}
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	private String checkVailidTcnmn(Cause_Bts_Cat_WorkEntity itemCheck) {
		String sReslut = "";
		try {
			if (btsEntity.getHouse_GENERATOR() != Constants.ID_ENTITY_DEFAULT) {
				if (isStatusTcnmn < 0) {
					sReslut = StringUtil
							.getString(R.string.constr_cat_work_danhgia_tcnmn_empty);

					tabs.setCurrentTab(1);
					supervision_bts_catwork_thicongnhamayno_chontrangthai
							.setError(Html.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					supervision_bts_catwork_thicongnhamayno_chontrangthai
							.requestFocus();
					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();

					clearFocus();
				} else if (isStatusTcnmn == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT
						&& itemCheck.getListCauseUniQualify().size() < 1) {
					sReslut = StringUtil
							.getString(R.string.constr_cat_work_nn_khongdat_tcnmn_tempty);

					tabs.setCurrentTab(1);

					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();

					clearFocus();
				} else if (isStatusTcnmn == Constants.BTS_CAT_WORK_STATUS.DAT) {
					int countAcceptCheck = 0;

					if (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP
							|| btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL) {
						for (int i = 0; i < itemCheck.getListAcceptance()
								.size(); i++) {
							if ((itemCheck.getListAcceptance().get(i)
									.getLstNewAttachFile().size() > 0 || itemCheck
									.getListAcceptance().get(i)
									.getLstAttachFile().size() > 0)
									&& (i == 0)) {
								countAcceptCheck++;
							}
						}

						if (countAcceptCheck < 1) {
							sReslut = StringUtil
									.getString(R.string.constr_take_acceptance_not_enough);
							Toast.makeText(getApplicationContext(), sReslut,
									Toast.LENGTH_LONG).show();

							clearFocus();
						}
					} else {
						for (int i = 0; i < itemCheck.getListAcceptance()
								.size(); i++) {
							if ((itemCheck.getListAcceptance().get(i)
									.getLstNewAttachFile().size() > 0 || itemCheck
									.getListAcceptance().get(i)
									.getLstAttachFile().size() > 0)
									&& (i == 0)) {
								countAcceptCheck++;
							}
						}

						if (countAcceptCheck < 1) {
							sReslut = StringUtil
									.getString(R.string.constr_take_acceptance_not_enough);
							Toast.makeText(getApplicationContext(), sReslut,
									Toast.LENGTH_LONG).show();

							clearFocus();
						}
					}

					clearFocus();
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
			if (btsEntity.getHouse_GENERATOR() != Constants.ID_ENTITY_DEFAULT) {
				if (isStatusKdd < 0) {
					sReslut = StringUtil
							.getString(R.string.constr_cat_work_danhgia_kdd_empty);
					tabs.setCurrentTab(2);
					supervision_bts_catwork_thicongkeoday_chontrangthai
							.setError(Html.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();

					clearFocus();

				} else if (isDidayType < 1) {
					sReslut = StringUtil
							.getString(R.string.constr_cat_work_loai_diday_kdd_tempty);
					tabs.setCurrentTab(2);
					supervision_bts_catwork_thicongkeoday_loaididay
							.setError(Html.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();

					clearFocus();

				} else if (isStatusKdd == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT
						&& countNnkdCheckKdd < 1) {
					sReslut = StringUtil
							.getString(R.string.constr_cat_work_nn_khongdat_kdd_tempty);
					tabs.setCurrentTab(2);

					// supervision_bts_catwork_thicongkeoday_chonnnkd
					// .setError(Html
					// .fromHtml("<font color='green'>"
					// + getString(R.string.field_is_null)
					// + "</font>"));
					// supervision_bts_catwork_thicongkeoday_chonnnkd.requestFocus();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// click nut hoan thanh
		case R.id.rl_supervision_bts_cat_work_complete:
			if (!GlobalInfo.getInstance().isCheckIn()) {
				showAlertDialogCheckInRequire(this,
						getString(R.string.checkin_require),
						getString(R.string.text_close));
				break;
			}
			confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save),
					OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			confirmSave.show();
			break;
		// click save
		case R.id.rl_supervision_bts_cat_work_save:
			if (!GlobalInfo.getInstance().isCheckIn()) {
				showAlertDialogCheckInRequire(this,
						getString(R.string.checkin_require),
						getString(R.string.text_close));
				break;
			}
			// neu la tab thi cong phong may
			if (tabs.getCurrentTab() == 0) {
				if (StringUtil.isNullOrEmpty(this
						.checkVailidPm(causeCatWorkEntityPm))) {
					confirmSave = new ConfirmDialog(this,
							StringUtil.getString(R.string.text_confirm_save));
					confirmSave.show();
				}
			} else {
				// neu la tab thi cong nha may no
				if (tabs.getCurrentTab() == 1) {
					if (StringUtil.isNullOrEmpty(this
							.checkVailidTcnmn(causeCatWorkEntityNmn))) {
						confirmSave = new ConfirmDialog(this,
								StringUtil
										.getString(R.string.text_confirm_save));
						confirmSave.show();
					}
				} else {
					// neu la tab thi cong keo day dien nha may no
					if (tabs.getCurrentTab() == 2) {
						if (StringUtil.isNullOrEmpty(this
								.checkVailidKdd(causeCatWorkEntityKdd))) {
							confirmSave = new ConfirmDialog(
									this,
									StringUtil
											.getString(R.string.text_confirm_save));
							confirmSave.show();

						}
					}
				}
			}

			break;
		// click combobox thong tin
		case R.id.rl_supervision_bts_cat_work_tv_thietke:

			this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
					this.listInfomation);

			dropdownPopupMenuInfomation.show(v);
			break;
		// click combobox trang thai thi cong phong may
		case R.id.rl_supervision_bts_catwork_tl_thicongphongmay_row1_tv_chontrangthai:

			this.dropdownPopupMenuStatusTcpm = new Menu_DropdownPopup(this,
					this.listStatusTcpm);
			dropdownPopupMenuStatusTcpm.show(v);
			break;
		// click combobox trang thai thi cong nha may no
		case R.id.rl_supervision_bts_catwork_tl_thicongnhamayno_row1_tv_chontrangthai:

			this.dropdownPopupMenuStatusTcnmn = new Menu_DropdownPopup(this,
					this.listStatusTcnmn);
			dropdownPopupMenuStatusTcnmn.show(v);
			break;
		// click combobox trang thai thi cong keo day
		case R.id.rl_supervision_bts_catwork_tl_thicongkeoday_row1_tv_chontrangthai:

			this.dropdownPopupMenuStatusKdd = new Menu_DropdownPopup(this,
					this.listStatusKdd);
			dropdownPopupMenuStatusKdd.show(v);
			break;
		// click combobox chon loai di day
		case R.id.rl_supervision_bts_catwork_tl_thicongkeoday_row2_loaididay:

			this.dropdownPopupMenuDidayType = new Menu_DropdownPopup(this,
					this.listDidayType);
			dropdownPopupMenuDidayType.show(v);
			break;

		// click nguyen nhan khong dat muc thi cong phong may
		case R.id.rl_supervision_bts_catwork_tl_thicongphongmay_row1_tv_chonnnkd:
			choicePm = true;
			choiceNmn = false;
			choiceKdd = false;
			if (isStatusTcpm == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {
				setUnqualifyPm();
				if ((btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.XAY_MOI)
						|| (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.XAY_MOI_VL)) {
					contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
							this, null, this.listUnqualifyCatWorkPmNewItem);
				} else {
					if ((btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP)
							|| (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL)) {
						contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
								this, null,
								this.listUnqualifyCatWorkPmLapGhepItem);
					} else {
						contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
								this, null,
								this.listUnqualifyCatWorkPmCaiTaoItem);
					}
				}

				contructionUnqualifyPopup.showAtCenter();
			} else if (isStatusTcpm == Constants.TANK_STATUS.DAT) {
				this.setPmAcceptance();
				if ((btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP)
						|| (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL)) {
					contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
							this, null, this.listPmAssemblyAcceptanceItem);
				} else {
					contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
							this, null, this.listPmNewAcceptanceItem);
				}

				contruoctionAcceptancePopup.showAtCenter();

			} else {
				Toast.makeText(
						this,
						StringUtil
								.getString(R.string.constr_line_tank_unqualify_choice_message),
						Toast.LENGTH_SHORT).show();
			}
			break;
		// click nguyen nhan khong dat muc thi cong nha may no
		case R.id.rl_supervision_bts_catwork_tl_thicongnhamayno_row1_tv_chonnnkd:
			choicePm = false;
			choiceNmn = true;
			choiceKdd = false;
			if (isStatusTcnmn == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {
				setUnqualifyNmn();
				if ((btsEntity.getHouse_GENERATOR() == Constants.BTS_FACTORY_TYPE.NHA_XAY_MOI)
						|| (btsEntity.getHouse_GENERATOR() == Constants.BTS_FACTORY_TYPE.LIEN_PHONG_MAY)) {
					contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
							this, null, this.listUnqualifyCatWorkNmnNewItem);

				} else {
					if ((btsEntity.getHouse_GENERATOR() == Constants.BTS_FACTORY_TYPE.XAY_MOI_VL)) {
						contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
								this, null,
								this.listUnqualifyCatWorkNmnVuotLuItem);
					} else {
						contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
								this, null,
								this.listUnqualifyCatWorkNmnTienCheItem);
					}
				}
				// this.setUnqualifyCurTankItem(curlistPillarAntenUnqualifyItem);

				contructionUnqualifyPopup.showAtCenter();
			} else if (isStatusTcnmn == Constants.TANK_STATUS.DAT) {
				this.setNmnAcceptance();
				if ((btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP)
						|| (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL)) {
					contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
							this, null, this.listNmnAssemblyAcceptanceItem);
				} else {
					contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
							this, null, this.listNmnNewAcceptanceItem);
				}

				contruoctionAcceptancePopup.showAtCenter();

			} else {
				Toast.makeText(
						this,
						StringUtil
								.getString(R.string.constr_line_tank_unqualify_choice_message),
						Toast.LENGTH_SHORT).show();
			}
			break;
		// click nguyen nhan khong dat muc thi cong keo day dien nha may no
		case R.id.rl_supervision_bts_catwork_tl_thicongkeoday_row1_tv_chonnnkd:
			choicePm = false;
			choiceNmn = false;
			choiceKdd = true;
			if (isStatusKdd == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {
				setUnqualifyKdd();
				// this.setUnqualifyCurTankItem(curlistPillarAntenUnqualifyItem);
				contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
						this, null, this.listUnqualifyCatWorkKddItem);
				contructionUnqualifyPopup.showAtCenter();
			} else {
				Toast.makeText(
						this,
						StringUtil
								.getString(R.string.constr_line_tank_unqualify_choice_message),
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
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

	/* Ghi nghiem thu vao danh sach ong */
	private void savePmAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityPm
				.getListAcceptance();
		if (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP
				|| btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL) {
			for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPmAssemblyAcceptanceItem) {
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
		} else {
			for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPmNewAcceptanceItem) {
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
		}

	}

	/**
	 * Ham set lai nghiem thu cho 1 be trong list danh sach nguyen nhan loi
	 */
	private void setNmnAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityNmn
				.getListAcceptance();
		if (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP
				|| btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL) {
			for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listNmnAssemblyAcceptanceItem) {
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
					curCauseUniqualify.setLstAttachFile(curItem
							.getLstAttachFile());
					curCauseUniqualify.setLstNewAttachFile(curItem
							.getLstNewAttachFile());
					curCauseUniqualify.setDelete(curItem.isDelete());
					curCauseUniqualify.setCause_Line_Bg_Id(curItem
							.getCause_Line_Bg_Id());
				}
			}
		} else {
			for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listNmnNewAcceptanceItem) {
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
					curCauseUniqualify.setLstAttachFile(curItem
							.getLstAttachFile());
					curCauseUniqualify.setLstNewAttachFile(curItem
							.getLstNewAttachFile());
					curCauseUniqualify.setDelete(curItem.isDelete());
					curCauseUniqualify.setCause_Line_Bg_Id(curItem
							.getCause_Line_Bg_Id());
				}
			}
		}

	}

	/* Ghi nghiem thu vao danh sach ong */
	private void saveNmnAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityNmn
				.getListAcceptance();
		if (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP
				|| btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL) {
			for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listNmnAssemblyAcceptanceItem) {
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
		} else {
			for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listNmnNewAcceptanceItem) {
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
		}

	}

	/**
	 * Ham set lai nghiem thu cho 1 be trong list danh sach nguyen nhan loi
	 */
	private void setPmAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityPm
				.getListAcceptance();
		if (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP
				|| btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL) {
			for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPmAssemblyAcceptanceItem) {
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
					curCauseUniqualify.setLstAttachFile(curItem
							.getLstAttachFile());
					curCauseUniqualify.setLstNewAttachFile(curItem
							.getLstNewAttachFile());
					curCauseUniqualify.setDelete(curItem.isDelete());
					curCauseUniqualify.setCause_Line_Bg_Id(curItem
							.getCause_Line_Bg_Id());
				}
			}
		} else {
			for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listPmNewAcceptanceItem) {
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
					curCauseUniqualify.setLstAttachFile(curItem
							.getLstAttachFile());
					curCauseUniqualify.setLstNewAttachFile(curItem
							.getLstNewAttachFile());
					curCauseUniqualify.setDelete(curItem.isDelete());
					curCauseUniqualify.setCause_Line_Bg_Id(curItem
							.getCause_Line_Bg_Id());
				}
			}
		}

	}

	/* Ghi nguyen nhan khong dat vao danh sach cot */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
	private void saveUnqualifyPm() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityPm
				.getListCauseUniQualify();
		if ((btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.XAY_MOI)
				|| (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.XAY_MOI_VL)) {
			saveUnqualifyPmType(curListUnqualify, listUnqualifyCatWorkPmNewItem);
		} else {
			if ((btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP)
					|| (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL)) {
				saveUnqualifyPmType(curListUnqualify,
						listUnqualifyCatWorkPmLapGhepItem);
			} else {
				saveUnqualifyPmType(curListUnqualify,
						listUnqualifyCatWorkPmCaiTaoItem);
			}
		}

	}

	public void saveUnqualifyPmType(
			List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify,
			List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkPmItem) {
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkPmItem) {
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
					addItem.setCause_Line_Bg_Id(this.causeCatWorkEntityPm
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

	private void setUnqualifyPm() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityPm
				.getListCauseUniQualify();
		if ((btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.XAY_MOI)
				|| (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.XAY_MOI_VL)) {
			setUnqualifyPmType(curListUnqualify, listUnqualifyCatWorkPmNewItem);
		} else {
			if ((btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP)
					|| (btsEntity.getHouse_TYPE() == Constants.BTS_HOUSE_TYPE.LAP_GHEP_VL)) {
				setUnqualifyPmType(curListUnqualify,
						listUnqualifyCatWorkPmLapGhepItem);
			} else {
				setUnqualifyPmType(curListUnqualify,
						listUnqualifyCatWorkPmCaiTaoItem);
			}
		}

	}

	public void setUnqualifyPmType(
			List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify,
			List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkPmItem) {
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkPmItem) {
			Supervision_LBG_UnqualifyItemEntity curItem = this
					.getCauseUnqualifyFromList(curListUnqualify,
							curCauseUniqualify
									.getCat_Cause_Constr_Unqualify_Id());
			if (curItem == null) {
				curCauseUniqualify.setUnqulify(false);
				// curCauseUniqualify
				// .setAttachFile(new Supv_Constr_Attach_FileEntity());
				curCauseUniqualify.setDeleteImage(false);
				// curCauseUniqualify
				// .setNewAttachFile(new Supv_Constr_Attach_FileEntity());
				curCauseUniqualify
						.setLstNewAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
				curCauseUniqualify
						.setLstAttachFile(new ArrayList<Supv_Constr_Attach_FileEntity>());
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

	private void saveUnqualifyNmn() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityNmn
				.getListCauseUniQualify();

		if ((btsEntity.getHouse_GENERATOR() == Constants.BTS_FACTORY_TYPE.NHA_XAY_MOI)
				|| (btsEntity.getHouse_GENERATOR() == Constants.BTS_FACTORY_TYPE.LIEN_PHONG_MAY)) {

			saveUnqualifyNmnType(curListUnqualify,
					listUnqualifyCatWorkNmnNewItem);
		} else {
			if ((btsEntity.getHouse_GENERATOR() == Constants.BTS_FACTORY_TYPE.XAY_MOI_VL)) {
				saveUnqualifyNmnType(curListUnqualify,
						listUnqualifyCatWorkNmnVuotLuItem);
			} else {

				saveUnqualifyNmnType(curListUnqualify,
						listUnqualifyCatWorkNmnTienCheItem);
			}
		}

	}

	public void saveUnqualifyNmnType(
			List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify,
			List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkNmnItem) {
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkNmnItem) {
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
					addItem.setCause_Line_Bg_Id(this.causeCatWorkEntityNmn
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

	private void setUnqualifyNmn() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityNmn
				.getListCauseUniQualify();
		if ((btsEntity.getHouse_GENERATOR() == Constants.BTS_FACTORY_TYPE.NHA_XAY_MOI)
				|| (btsEntity.getHouse_GENERATOR() == Constants.BTS_FACTORY_TYPE.LIEN_PHONG_MAY)) {

			setUnqualifyNmnType(curListUnqualify,
					listUnqualifyCatWorkNmnNewItem);
		} else {
			if ((btsEntity.getHouse_GENERATOR() == Constants.BTS_FACTORY_TYPE.XAY_MOI_VL)) {
				setUnqualifyNmnType(curListUnqualify,
						listUnqualifyCatWorkNmnVuotLuItem);
			} else {

				setUnqualifyNmnType(curListUnqualify,
						listUnqualifyCatWorkNmnTienCheItem);
			}
		}
	}

	public void setUnqualifyNmnType(
			List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify,
			List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkNmnItem) {
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkNmnItem) {
			Supervision_LBG_UnqualifyItemEntity curItem = this
					.getCauseUnqualifyFromList(curListUnqualify,
							curCauseUniqualify
									.getCat_Cause_Constr_Unqualify_Id());
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

	private void saveUnqualifyKdd() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityKdd
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkKddItem) {
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
					addItem.setCause_Line_Bg_Id(this.causeCatWorkEntityKdd
							.getCause_Bts_Cat_Work_Id());
					addItem.setUnqulify(true);
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
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.causeCatWorkEntityKdd
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listUnqualifyCatWorkKddItem) {
			Supervision_LBG_UnqualifyItemEntity curItem = this
					.getCauseUnqualifyFromList(curListUnqualify,
							curCauseUniqualify
									.getCat_Cause_Constr_Unqualify_Id());
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

				Bundle bundleMonitorData = new Bundle();
				bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
						constr_ConstructionItem);
				bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
						isInfomation);

				this.supervision_bts_catwork_tv_thietke.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuInfomation.dismiss();

				this.showProgressDialog(StringUtil
						.getString(R.string.text_loading));
				gotoBtsActivity(bundleMonitorData, isInfomation);
//				if (isInfomation == Constants.BTS_INFO.THIET_TKE_INFO) {
//					gotoSupervisionBtsActivity(bundleMonitorData);
//				}
//
//				if (isInfomation == Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO) {
//					gotoSupervisionBtsPillarActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO) {
//					gotoSupervisionBtsCatWorkActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.KEO_DIEN_INFO) {
//					gotoSupervisionBtsPowerPoleActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO) {
//					gotoSupervisionBtsEquipActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.LAP_DAT_VIBA_INFO) {
//
//					gotoSupervisionBtsVibaActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO) {
//
//					gotoSupervisionBtsMeasureActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.MEASURE_CONSTR_INFO) {
//					gotoSupervisionMeasureConstrActivity(bundleMonitorData);
//				}
//
//				// HungTN add new 24/08/2016
//				if (isInfomation == Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG) {
//					gotoSupervisionCNDTCActivity(bundleMonitorData);
//				}
//				if (isInfomation == Constants.BTS_INFO.CAP_NHAT_VUONG) {
//					gotoSupervisionCNVCActivity(bundleMonitorData);
//				}
//				// ---
//
//				if (isInfomation == Constants.BTS_INFO.KET_LUAN_INFO) {
//
//					gotoSupervisionBtsKLActivity(bundleMonitorData);
//				}
				finish();
			}

			if (typeItem.equals(Constants.DROPDOWN_TYPE.STATUS_TCPM)) {
				isStatusTcpm = dropdownItem.getId();
				if (this.isStatusTcpm >= 0) {
					supervision_bts_catwork_thicongphongmay_chontrangthai
							.setError(null, ic_combo);
					if (isStatusTcpm == 1) {
						countNnkdCheckPm = 0;

						for (int i = 0; i < this.causeCatWorkEntityPm
								.getListAcceptance().size(); i++) {
							if (this.causeCatWorkEntityPm.getListAcceptance()
									.get(i).getLstNewAttachFile().size() > 0
									|| this.causeCatWorkEntityPm
											.getListAcceptance().get(i)
											.getLstAttachFile().size() > 0) {
								countNnkdCheckPm++;
								break;
							}
						}

						if (countNnkdCheckPm > 0) {
							supervision_bts_catwork_thicongphongmay_chonnnkd
									.setText(StringUtil
											.getString(R.string.text_view_dot));
						} else
							supervision_bts_catwork_thicongphongmay_chonnnkd
									.setText(StringUtil
											.getString(R.string.text_empty));
					} else {
						countNnkdCheckPm = 0;

						for (int i = 0; i < this.causeCatWorkEntityPm
								.getListCauseUniQualify().size(); i++) {
							if (!causeCatWorkEntityPm.getListCauseUniQualify()
									.get(i).isDelete()) {
								countNnkdCheckPm++;
								break;
							}
						}

						if (countNnkdCheckPm > 0) {
							supervision_bts_catwork_thicongphongmay_chonnnkd
									.setText(StringUtil
											.getString(R.string.text_view_dot));
						} else
							supervision_bts_catwork_thicongphongmay_chonnnkd
									.setText(StringUtil
											.getString(R.string.text_empty));
					}
				}
				this.supervision_bts_catwork_thicongphongmay_chontrangthai
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuStatusTcpm.dismiss();
			}

			if (typeItem.equals(Constants.DROPDOWN_TYPE.STATUS_TCNMN)) {
				isStatusTcnmn = dropdownItem.getId();
				if (this.isStatusTcnmn >= 0) {
					supervision_bts_catwork_thicongnhamayno_chontrangthai
							.setError(null, ic_combo);

					if (isStatusTcnmn == 1) {
						countNnkdCheckNmn = 0;

						for (int i = 0; i < this.causeCatWorkEntityNmn
								.getListAcceptance().size(); i++) {
							if (this.causeCatWorkEntityNmn.getListAcceptance()
									.get(i).getLstNewAttachFile().size() > 0
									|| this.causeCatWorkEntityNmn
											.getListAcceptance().get(i)
											.getLstAttachFile().size() > 0) {
								countNnkdCheckNmn++;
								break;
							}
						}

						if (countNnkdCheckNmn > 0) {
							supervision_bts_catwork_thicongnhamayno_chonnnkd
									.setText(StringUtil
											.getString(R.string.text_view_dot));
						} else
							supervision_bts_catwork_thicongnhamayno_chonnnkd
									.setText(StringUtil
											.getString(R.string.text_empty));
					} else {
						countNnkdCheckNmn = 0;

						for (int i = 0; i < this.causeCatWorkEntityNmn
								.getListCauseUniQualify().size(); i++) {
							if (!causeCatWorkEntityNmn.getListCauseUniQualify()
									.get(i).isDelete()) {
								countNnkdCheckNmn++;
								break;
							}
						}

						if (countNnkdCheckNmn > 0) {
							supervision_bts_catwork_thicongnhamayno_chonnnkd
									.setText(StringUtil
											.getString(R.string.text_view_dot));
						} else
							supervision_bts_catwork_thicongnhamayno_chonnnkd
									.setText(StringUtil
											.getString(R.string.text_empty));
					}
				}
				this.supervision_bts_catwork_thicongnhamayno_chontrangthai
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuStatusTcnmn.dismiss();
			}

			if (typeItem.equals(Constants.DROPDOWN_TYPE.STATUS_TCKD)) {
				isStatusKdd = dropdownItem.getId();
				if (this.isStatusKdd >= 0) {
					supervision_bts_catwork_thicongkeoday_chontrangthai
							.setError(null, ic_combo);
				}
				this.supervision_bts_catwork_thicongkeoday_chontrangthai
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuStatusKdd.dismiss();
			}

			if (typeItem.equals(Constants.DROPDOWN_TYPE.DIDAY_TYPE)) {
				isDidayType = dropdownItem.getId();
				if (this.isDidayType > 0) {
					supervision_bts_catwork_thicongkeoday_loaididay.setError(
							null, ic_combo);
				}
				this.supervision_bts_catwork_thicongkeoday_loaididay
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuDidayType.dismiss();
			}
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			break;

		case OnEventControlListener.EVENT_CONFIRM_OK:

			SaveAsyncTask task = new SaveAsyncTask();
			task.execute();
			break;

		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			if (choicePm) {
				savePmAcceptance();
				countNnkdCheckPm = 0;

				for (int i = 0; i < this.causeCatWorkEntityPm
						.getListAcceptance().size(); i++) {
					if (this.causeCatWorkEntityPm.getListAcceptance().get(i)
							.getLstNewAttachFile().size() > 0
							|| this.causeCatWorkEntityPm.getListAcceptance()
									.get(i).getLstAttachFile().size() > 0) {
						countNnkdCheckPm++;
						break;
					}
				}

				if (countNnkdCheckPm > 0) {
					supervision_bts_catwork_thicongphongmay_chonnnkd
							.setText(StringUtil
									.getString(R.string.text_view_dot));
				} else
					supervision_bts_catwork_thicongphongmay_chonnnkd
							.setText(StringUtil.getString(R.string.text_empty));
			}

			if (choiceNmn) {
				saveNmnAcceptance();
				countNnkdCheckNmn = 0;

				for (int i = 0; i < this.causeCatWorkEntityNmn
						.getListAcceptance().size(); i++) {
					if (this.causeCatWorkEntityNmn.getListAcceptance().get(i)
							.getLstNewAttachFile().size() > 0
							|| this.causeCatWorkEntityNmn.getListAcceptance()
									.get(i).getLstAttachFile().size() > 0) {
						countNnkdCheckNmn++;
						break;
					}
				}

				if (countNnkdCheckNmn > 0) {
					supervision_bts_catwork_thicongnhamayno_chonnnkd
							.setText(StringUtil
									.getString(R.string.text_view_dot));
				} else
					supervision_bts_catwork_thicongnhamayno_chonnnkd
							.setText(StringUtil.getString(R.string.text_empty));
			}

			contruoctionAcceptancePopup.hidePopup();
			break;

		/* Dong nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			if (choicePm) {
				this.saveUnqualifyPm();
				contructionUnqualifyPopup.hidePopup();
				countNnkdCheckPm = 0;

				for (int i = 0; i < this.causeCatWorkEntityPm
						.getListCauseUniQualify().size(); i++) {
					if (!causeCatWorkEntityPm.getListCauseUniQualify().get(i)
							.isDelete()) {
						countNnkdCheckPm++;
						break;
					}
				}

				if (countNnkdCheckPm > 0) {
					supervision_bts_catwork_thicongphongmay_chonnnkd
							.setText(StringUtil
									.getString(R.string.text_view_dot));
				} else
					supervision_bts_catwork_thicongphongmay_chonnnkd
							.setText(StringUtil.getString(R.string.text_empty));
			}

			if (choiceNmn) {
				this.saveUnqualifyNmn();
				contructionUnqualifyPopup.hidePopup();
				countNnkdCheckNmn = 0;

				for (int i = 0; i < this.causeCatWorkEntityNmn
						.getListCauseUniQualify().size(); i++) {
					if (!causeCatWorkEntityNmn.getListCauseUniQualify().get(i)
							.isDelete()) {
						countNnkdCheckNmn++;
						break;
					}
				}

				if (countNnkdCheckNmn > 0) {
					supervision_bts_catwork_thicongnhamayno_chonnnkd
							.setText(StringUtil
									.getString(R.string.text_view_dot));
				} else
					supervision_bts_catwork_thicongnhamayno_chonnnkd
							.setText(StringUtil.getString(R.string.text_empty));
			}

			if (choiceKdd) {
				this.saveUnqualifyKdd();
				contructionUnqualifyPopup.hidePopup();

				countNnkdCheckKdd = 0;

				for (int i = 0; i < this.causeCatWorkEntityKdd
						.getListCauseUniQualify().size(); i++) {
					if (!causeCatWorkEntityKdd.getListCauseUniQualify().get(i)
							.isDelete()) {
						countNnkdCheckKdd++;
						break;
					}
				}

				if (countNnkdCheckKdd > 0) {
					supervision_bts_catwork_thicongkeoday_chonnnkd
							.setText(StringUtil
									.getString(R.string.text_view_dot));
				} else
					supervision_bts_catwork_thicongkeoday_chonnnkd
							.setText(StringUtil.getString(R.string.text_empty));
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
		// chup anh nguyen nhan loi
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
		case OnEventControlListener.EVENT_IMG_TAKE_DELETE:

			this.imgViewPopup.deleteImageData();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_EXIT:
			List<ImageEntity> lstData = (List<ImageEntity>) data;

			if (choicePm) {
				if (isStatusTcpm == Constants.TANK_STATUS.DAT) {
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
				} else if (isStatusTcpm == Constants.TANK_STATUS.KHONG_DAT) {
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

			if (choiceNmn) {
				if (isStatusTcnmn == Constants.TANK_STATUS.DAT) {
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
				} else if (isStatusTcnmn == Constants.TANK_STATUS.KHONG_DAT) {
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

			if (choiceKdd) {
				if (isStatusKdd == Constants.TANK_STATUS.DAT) {
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
				} else if (isStatusKdd == Constants.TANK_STATUS.KHONG_DAT) {
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
		case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
			this.selectPhoto();
			break;
		case OnEventControlListener.EVENT_COMPLETE_PROGRESS:
			// save ngay hoan thanh thi cong phong may
			if (tabs.getCurrentTab() == 0) {
				saveCompleteDay(constr_ConstructionItem,
						Constants.PROGRESS_TYPE.BTS_TYPE,
						Constants.PROGRESS_TYPE.PHONG_MAY, outOfKey);
				showCompleteDate(constr_ConstructionItem,
						Constants.PROGRESS_TYPE.BTS_TYPE,
						Constants.PROGRESS_TYPE.PHONG_MAY,
						supervision_bts_pm_complete_date,
						rl_supervision_bts_cat_work_complete);
			} else {
				// save ngay hoan thanh thi cong nha may no
				if (tabs.getCurrentTab() == 1) {
					saveCompleteDay(constr_ConstructionItem,
							Constants.PROGRESS_TYPE.BTS_TYPE,
							Constants.PROGRESS_TYPE.NHA_MAY_NO, outOfKey);
					showCompleteDate(constr_ConstructionItem,
							Constants.PROGRESS_TYPE.BTS_TYPE,
							Constants.PROGRESS_TYPE.NHA_MAY_NO,
							supervision_bts_nmn_complete_date,
							rl_supervision_bts_cat_work_complete);
				} else {
					// save ngay hoan thanh tih cong keo day dien nha may no
					saveCompleteDay(constr_ConstructionItem,
							Constants.PROGRESS_TYPE.BTS_TYPE,
							Constants.PROGRESS_TYPE.KEO_DAY_DIEN_PHONG_MAY_NO,
							outOfKey);
					showCompleteDate(constr_ConstructionItem,
							Constants.PROGRESS_TYPE.BTS_TYPE,
							Constants.PROGRESS_TYPE.KEO_DAY_DIEN_PHONG_MAY_NO,
							supervision_bts_kdd_complete_date,
							rl_supervision_bts_cat_work_complete);
				}
			}
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
				Toast.makeText(SupervisionBtsCatWorkActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshForm();

			Toast.makeText(SupervisionBtsCatWorkActivity.this,
					getResources().getString(R.string.text_update_success),
					Toast.LENGTH_LONG).show();

			closeProgressDialog();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
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

	// insert update thi cong phong may
	public boolean querryPm() {
		long idUser = GlobalInfo.getInstance().getUserId();
		Supervision_Bts_Cat_WorkEntity btsCatWorkPmEntity = getSupervisionBtsCatWorkPmEntity();

		btsCatWorkPmEntity
				.setDimension_Design(supervision_bts_catwork_thicongphongmay_thietke
						.getText().toString());
		btsCatWorkPmEntity
				.setDimension_Real(supervision_bts_catwork_thicongphongmay_thucte
						.getText().toString());
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = causeCatWorkEntityPm
				.getListCauseUniQualify();

		try {

			if (causeCatWorkEntityPm.getBts_Cat_WorkEntity().getStatus() < 0) {
				/**
				 * xu li insert danh gia phong may
				 */
				// them constr work co work_code tuong ung vao csdl
				int idConstrWorkPm = constrWorkController
						.getConstrWorkEntityByWorkCodeReturnId(Constants.UNQUALIFY_TYPE.SUB_TYPE_PHONG_MAY);

				// set id constr_work vua them vao database vao thuc the
				// btsCatWork
				btsCatWorkPmEntity
						.setCat_Supervision_Constr_Work_Id(idConstrWorkPm);
				btsCatWorkPmEntity.setSupervision_Bts_Id(btsEntity
						.getSupervision_Bts_Id());

				long idBtsCatWorkPm = Ktts_KeyController.getInstance()
						.getKttsNextKey(
								Supervision_Bts_Cat_WorkField.TABLE_NAME);

				if (idBtsCatWorkPm == 0) {

					return true;
				}

				btsCatWorkPmEntity
						.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkPm);
				btsCatWorkPmEntity.setSync_Status(Constants.SYNC_STATUS.ADD);
				btsCatWorkPmEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
				btsCatWorkPmEntity.setProcessId(0);
				btsCatWorkPmEntity.setEmployeeId(idUser);
				btsCatWorkPmEntity.setSupervisionConstrId(btsEntity
						.getSupervision_ConstrEntity()
						.getSupervision_Constr_Id());

				catWorkController.insertBtsCatWorkEntity(btsCatWorkPmEntity);

				Cause_Bts_Cat_WorkEntity causeCatWorkPmEntity = new Cause_Bts_Cat_WorkEntity();
				causeCatWorkPmEntity.getBts_Cat_WorkEntity()
						.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkPm);
				causeCatWorkPmEntity
						.setListCauseUniQualify(causeCatWorkEntityPm
								.getListCauseUniQualify());

				if (btsCatWorkPmEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

					ArrayList<Long> ListIdCause = new ArrayList<Long>();

					for (Supervision_LBG_UnqualifyItemEntity unqualify : causeCatWorkPmEntity
							.getListCauseUniQualify()) {
						long lastId = Ktts_KeyController.getInstance()
								.getKttsNextKey(
										Cause_Bts_Cat_WorkField.TABLE_NAME);

						if (lastId == 0) {

							return true;
						}

						unqualify.setSync_Status(Constants.SYNC_STATUS.ADD);
						unqualify.setIsActive(Constants.ISACTIVE.ACTIVE);
						unqualify.setProcessId(0);
						unqualify.setSupervisionConstrId(btsEntity
								.getSupervision_ConstrEntity()
								.getSupervision_Constr_Id());

						cauCatWorkController.insertCause_Bts_Cat_WorkEntity(
								unqualify,
								causeCatWorkPmEntity.getBts_Cat_WorkEntity(),
								lastId, idUser);
						ListIdCause.add(lastId);
					}

					int dem = 0;
					for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
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
												Cause_Bts_Cat_WorkField.TABLE_NAME);

							}
						}
						dem++;
					}

				} else {
					// neu dat thi save anh nghiem thu
					List<Supervision_LBG_UnqualifyItemEntity> curListAcceptance = causeCatWorkEntityPm
							.getListAcceptance();
					for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListAcceptance) {
						Acceptance_Bts_Cat_WorkEntity addCauseItem = new Acceptance_Bts_Cat_WorkEntity();
						addCauseItem
								.setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
										.getCat_Cause_Constr_Acceptance_Id());
						addCauseItem
								.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_PHONG_MAY);
						addCauseItem
								.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkPm);
						addCauseItem.setSync_Status(Constants.SYNC_STATUS.ADD);
						addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
						addCauseItem.setProcessId(0);
						addCauseItem.setEmployeeId(idUser);

						long iAddCause = Ktts_KeyController
								.getInstance()
								.getKttsNextKey(
										Acceptance_Bts_Cat_WorkField.TABLE_NAME);
						if (iAddCause == 0) {
							outOfKey = true;
							return true;
						} else
							outOfKey = false;

						addCauseItem.setAcceptance_Bts_Cat_Work_Id(iAddCause);
						this.cauCatWorkController.addItem(addCauseItem);

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
			} else {
				/**
				 * xu li update danh gia phong may neu loai nha trong thiet ke
				 * la co san
				 */

				if (btsEntity.getHouse_TYPE() != BTS_HOUSE_TYPE.CO_SAN) {
					btsCatWorkPmEntity
							.setSupervision_Bts_Cat_Work_Id(causeCatWorkEntityPm
									.getBts_Cat_WorkEntity()
									.getSupervision_Bts_Cat_Work_Id());

					if (causeCatWorkEntityPm.getBts_Cat_WorkEntity()
							.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
						btsCatWorkPmEntity
								.setSync_Status(Constants.SYNC_STATUS.EDIT);
					} else {
						btsCatWorkPmEntity.setSync_Status(causeCatWorkEntityPm
								.getBts_Cat_WorkEntity().getSync_Status());
					}

					catWorkController
							.updateBtsCatWorkEntity(btsCatWorkPmEntity);

					Cause_Bts_Cat_WorkEntity causeCatWorkPmEntity = new Cause_Bts_Cat_WorkEntity();
					causeCatWorkPmEntity.getBts_Cat_WorkEntity()
							.setSupervision_Bts_Cat_Work_Id(
									btsCatWorkPmEntity
											.getSupervision_Bts_Cat_Work_Id());
					causeCatWorkPmEntity
							.setListCauseUniQualify(causeCatWorkEntityPm
									.getListCauseUniQualify());

					for (Supervision_LBG_UnqualifyItemEntity addItemCause : causeCatWorkEntityPm
							.getListAcceptance()) {
						/* 1. Chinh sua nghiem thu */
						if (addItemCause.getCause_Line_Bg_Id() > 0) {

							// xoa nghiem thu khi chuyen
							// trang thai tu khong dat sang dat
							// if (isStatusTcpm ==
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
							if (isStatusTcpm == Constants.SUPERVISION_STATUS.DAT) {
								Acceptance_Bts_Cat_WorkEntity addCauseItem = new Acceptance_Bts_Cat_WorkEntity();
								addCauseItem
										.setCat_Cause_Constr_Acceptance_Id(addItemCause
												.getCat_Cause_Constr_Acceptance_Id());
								addCauseItem
										.setSupervision_Bts_Cat_Work_Id(causeCatWorkEntityPm
												.getBts_Cat_WorkEntity()
												.getSupervision_Bts_Cat_Work_Id());
								addCauseItem
										.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_PHONG_MAY);
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
									return true;
								} else
									outOfKey = false;

								addCauseItem
										.setAcceptance_Bts_Cat_Work_Id(iAddCause);

								this.cauCatWorkController.addItem(addCauseItem);

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

					for (Supervision_LBG_UnqualifyItemEntity addItemCause : causeCatWorkEntityPm
							.getListCauseUniQualify()) {
						// xoa het nguyen nhan khong dat cu
						// cauCatWorkController
						// .deleteCause_Bts_Cat_WorkEntity(unqualify);
						/* 1. Chinh sua nguyen nhan khong dat */
						if (addItemCause.getCause_Line_Bg_Id() > 0) {
							/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
							if (addItemCause.isDelete()) {
								this.cauCatWorkController
										.deleteCause_Bts_Cat_WorkEntity(addItemCause);
							}
							/* 1.2. Update lai nguyen nhan khong dat */
							else {
								// if (btsCatWorkPmEntity.getStatus() ==
								// Constants.BTS_CAT_WORK_STATUS.DAT) {
								// this.cauCatWorkController
								// .deleteCause_Bts_Cat_WorkEntity(addItemCause);
								// }

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

							}
						}
						/* 2. Them moi nguyen nhan khong dat */
						else {
							if (btsCatWorkPmEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

								ArrayList<Long> ListIdCause = new ArrayList<Long>();

								long lastId = Ktts_KeyController
										.getInstance()
										.getKttsNextKey(
												Cause_Bts_Cat_WorkField.TABLE_NAME);

								if (lastId == 0) {

									return true;
								}

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
												causeCatWorkEntityPm
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
		} catch (Exception e) {
			Log.e("error", e.toString());
		}

		return false;
	}

	// insert update thi cong nha may no
	public boolean querryNmn() {
		long idUser = GlobalInfo.getInstance().getUserId();
		Supervision_Bts_Cat_WorkEntity btsCatWorkNmnEntity = getSupervisionBtsCatWorkNmnEntity();
		btsCatWorkNmnEntity
				.setDimension_Design(supervision_bts_catwork_thicongnhamayno_thietke
						.getText().toString());
		btsCatWorkNmnEntity
				.setDimension_Real(supervision_bts_catwork_thicongnhamayno_thucte
						.getText().toString());

		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = causeCatWorkEntityNmn
				.getListCauseUniQualify();

		try {
			if (isStatusTcnmn >= 0) {
				if (causeCatWorkEntityNmn.getBts_Cat_WorkEntity().getStatus() < 0) {
					/**
					 * xu li insert danh gia nha may no
					 */

					int idConstrWorkNmn = constrWorkController
							.getConstrWorkEntityByWorkCodeReturnId(Constants.UNQUALIFY_TYPE.SUB_TYPE_NHA_MAY_NO);

					btsCatWorkNmnEntity
							.setCat_Supervision_Constr_Work_Id(idConstrWorkNmn);
					btsCatWorkNmnEntity.setSupervision_Bts_Id(btsEntity
							.getSupervision_Bts_Id());

					long idBtsCatWorkNmn = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_Bts_Cat_WorkField.TABLE_NAME);
					if (idBtsCatWorkNmn == 0) {

						return true;
					}

					btsCatWorkNmnEntity
							.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkNmn);

					btsCatWorkNmnEntity
							.setSync_Status(Constants.SYNC_STATUS.ADD);
					btsCatWorkNmnEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
					btsCatWorkNmnEntity.setProcessId(0);
					btsCatWorkNmnEntity.setEmployeeId(idUser);
					btsCatWorkNmnEntity.setSupervisionConstrId(btsEntity
							.getSupervision_ConstrEntity()
							.getSupervision_Constr_Id());

					catWorkController
							.insertBtsCatWorkEntity(btsCatWorkNmnEntity);

					Cause_Bts_Cat_WorkEntity causeCatWorkNmnEntity = new Cause_Bts_Cat_WorkEntity();
					causeCatWorkNmnEntity.getBts_Cat_WorkEntity()
							.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkNmn);
					causeCatWorkNmnEntity
							.setListCauseUniQualify(curListUnqualify);

					if (btsCatWorkNmnEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

						ArrayList<Long> ListIdCause = new ArrayList<Long>();

						for (Supervision_LBG_UnqualifyItemEntity unqualify : causeCatWorkNmnEntity
								.getListCauseUniQualify()) {
							long lastId = Ktts_KeyController.getInstance()
									.getKttsNextKey(
											Cause_Bts_Cat_WorkField.TABLE_NAME);

							if (lastId == 0) {

								return true;
							}

							unqualify.setSync_Status(Constants.SYNC_STATUS.ADD);
							unqualify.setIsActive(Constants.ISACTIVE.ACTIVE);
							unqualify.setProcessId(0);
							unqualify.setSupervisionConstrId(btsEntity
									.getSupervision_ConstrEntity()
									.getSupervision_Constr_Id());

							cauCatWorkController
									.insertCause_Bts_Cat_WorkEntity(unqualify,
											causeCatWorkNmnEntity
													.getBts_Cat_WorkEntity(),
											lastId, idUser);
							ListIdCause.add(lastId);

						}
						int dem = 0;
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
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
													Cause_Bts_Cat_WorkField.TABLE_NAME);

								}
							}
							dem++;
						}
					} else {
						// neu dat thi save anh nghiem thu
						List<Supervision_LBG_UnqualifyItemEntity> curListAcceptance = causeCatWorkEntityNmn
								.getListAcceptance();
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListAcceptance) {
							Acceptance_Bts_Cat_WorkEntity addCauseItem = new Acceptance_Bts_Cat_WorkEntity();
							addCauseItem
									.setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
											.getCat_Cause_Constr_Acceptance_Id());
							addCauseItem
									.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_NHA_MAY_NO);
							addCauseItem
									.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkNmn);
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
								return true;
							} else
								outOfKey = false;

							addCauseItem
									.setAcceptance_Bts_Cat_Work_Id(iAddCause);
							this.cauCatWorkController.addItem(addCauseItem);

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
				} else {
					/**
					 * xu li update danh gia nha may no
					 */
					btsCatWorkNmnEntity
							.setSupervision_Bts_Cat_Work_Id(causeCatWorkEntityNmn
									.getBts_Cat_WorkEntity()
									.getSupervision_Bts_Cat_Work_Id());

					if (causeCatWorkEntityNmn.getBts_Cat_WorkEntity()
							.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
						btsCatWorkNmnEntity
								.setSync_Status(Constants.SYNC_STATUS.EDIT);
					} else {
						btsCatWorkNmnEntity
								.setSync_Status(causeCatWorkEntityNmn
										.getBts_Cat_WorkEntity()
										.getSync_Status());
					}

					// btsCatWorkNmnEntity.setIsActive(Constants.ISACTIVE.ACTIVE);

					catWorkController
							.updateBtsCatWorkEntity(btsCatWorkNmnEntity);

					Cause_Bts_Cat_WorkEntity causeCatWorkNmnEntity = new Cause_Bts_Cat_WorkEntity();
					causeCatWorkNmnEntity.getBts_Cat_WorkEntity()
							.setSupervision_Bts_Cat_Work_Id(
									btsCatWorkNmnEntity
											.getSupervision_Bts_Cat_Work_Id());
					causeCatWorkNmnEntity
							.setListCauseUniQualify(curListUnqualify);

					for (Supervision_LBG_UnqualifyItemEntity addItemCause : causeCatWorkEntityNmn
							.getListAcceptance()) {
						/* 1. Chinh sua nghiem thu */
						if (addItemCause.getCause_Line_Bg_Id() > 0) {

							// xoa nghiem thu khi chuyen
							// trang thai tu khong dat sang dat
							// if (isStatusTcnmn ==
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
							if (isStatusTcnmn == Constants.SUPERVISION_STATUS.DAT) {
								Acceptance_Bts_Cat_WorkEntity addCauseItem = new Acceptance_Bts_Cat_WorkEntity();
								addCauseItem
										.setCat_Cause_Constr_Acceptance_Id(addItemCause
												.getCat_Cause_Constr_Acceptance_Id());
								addCauseItem
										.setSupervision_Bts_Cat_Work_Id(causeCatWorkEntityNmn
												.getBts_Cat_WorkEntity()
												.getSupervision_Bts_Cat_Work_Id());
								addCauseItem
										.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_NHA_MAY_NO);
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
									return true;
								} else
									outOfKey = false;

								addCauseItem
										.setAcceptance_Bts_Cat_Work_Id(iAddCause);

								this.cauCatWorkController.addItem(addCauseItem);

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

					for (Supervision_LBG_UnqualifyItemEntity addItemCause : causeCatWorkEntityNmn
							.getListCauseUniQualify()) {
						// xoa het nguyen nhan khong dat cu
						// cauCatWorkController
						// .deleteCause_Bts_Cat_WorkEntity(unqualify);
						/* 1. Chinh sua nguyen nhan khong dat */
						if (addItemCause.getCause_Line_Bg_Id() > 0) {
							/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
							if (addItemCause.isDelete()) {
								this.cauCatWorkController
										.deleteCause_Bts_Cat_WorkEntity(addItemCause);
							}
							/* 1.2. Update lai nguyen nhan khong dat */
							else {
								// neu chuyen trang thai tu khong dat sang dat
								// thi
								// xoa nguyen nhan khong dat di
								// if (btsCatWorkNmnEntity.getStatus() ==
								// Constants.BTS_CAT_WORK_STATUS.DAT) {
								// this.cauCatWorkController
								// .deleteCause_Bts_Cat_WorkEntity(addItemCause);
								// }

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
							if (btsCatWorkNmnEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

								ArrayList<Long> ListIdCause = new ArrayList<Long>();

								long lastId = Ktts_KeyController
										.getInstance()
										.getKttsNextKey(
												Cause_Bts_Cat_WorkField.TABLE_NAME);

								if (lastId == 0) {
									return true;
								}

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
												causeCatWorkEntityNmn
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
		} catch (Exception e) {
			Log.e("error", e.toString());
		}

		return false;

	}

	// insert update thi cong keo day dien nha may no
	public boolean querryKdd() {
		long idUser = GlobalInfo.getInstance().getUserId();
		Supervision_Bts_Cat_WorkEntity btsCatWorkKddEntity = getSupervisionBtsCatWorkKddEntity();

		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = causeCatWorkEntityKdd
				.getListCauseUniQualify();

		try {
			if (causeCatWorkEntityKdd.getBts_Cat_WorkEntity().getStatus() < 0) {
				/**
				 * xu li insert danh gia keo day dien nha may no
				 */

				int idConstrWorkKdd = constrWorkController
						.getConstrWorkEntityByWorkCodeReturnId(Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN_PHONG_MAY_NO);
				btsCatWorkKddEntity
						.setCat_Supervision_Constr_Work_Id(idConstrWorkKdd);
				btsCatWorkKddEntity.setSupervision_Bts_Id(btsEntity
						.getSupervision_Bts_Id());
				long idBtsCatWorkKdd = Ktts_KeyController.getInstance()
						.getKttsNextKey(
								Supervision_Bts_Cat_WorkField.TABLE_NAME);
				if (idBtsCatWorkKdd == 0) {
					return true;
				}

				btsCatWorkKddEntity
						.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkKdd);

				btsCatWorkKddEntity.setSync_Status(Constants.SYNC_STATUS.ADD);
				btsCatWorkKddEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
				btsCatWorkKddEntity.setProcessId(0);
				btsCatWorkKddEntity.setEmployeeId(idUser);
				btsCatWorkKddEntity.setSupervisionConstrId(btsEntity
						.getSupervision_ConstrEntity()
						.getSupervision_Constr_Id());

				catWorkController.insertBtsCatWorkEntity(btsCatWorkKddEntity);

				Cause_Bts_Cat_WorkEntity causeCatWorkKddEntity = new Cause_Bts_Cat_WorkEntity();
				causeCatWorkKddEntity.getBts_Cat_WorkEntity()
						.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkKdd);
				causeCatWorkKddEntity.setListCauseUniQualify(curListUnqualify);

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
							return true;
						}

						unqualify.setSync_Status(Constants.SYNC_STATUS.ADD);
						unqualify.setIsActive(Constants.ISACTIVE.ACTIVE);
						unqualify.setProcessId(0);
						unqualify.setSupervisionConstrId(btsEntity
								.getSupervision_ConstrEntity()
								.getSupervision_Constr_Id());

						cauCatWorkController.insertCause_Bts_Cat_WorkEntity(
								unqualify,
								causeCatWorkKddEntity.getBts_Cat_WorkEntity(),
								lastId, idUser);
						ListIdCause.add(lastId);

					}
					int dem = 0;
					for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListUnqualify) {
						// Luu anh nguyen nhan loi neu co
						// if (!StringUtil.isNullOrEmpty(curItemUnqualify
						// .getNewAttachFile().getFile_Path())) {
						// this.supvConstrAttachFileController
						// .coppyAndAddAttachFile(
						// this.constr_ConstructionEmployeeItem,
						// curItemUnqualify.getNewAttachFile()
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
				}
			} else {
				/**
				 * xu li update danh gia thi cong keo dien nha may no
				 */
				btsCatWorkKddEntity
						.setSupervision_Bts_Cat_Work_Id(causeCatWorkEntityKdd
								.getBts_Cat_WorkEntity()
								.getSupervision_Bts_Cat_Work_Id());

				if (causeCatWorkEntityKdd.getBts_Cat_WorkEntity()
						.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
					btsCatWorkKddEntity
							.setSync_Status(Constants.SYNC_STATUS.EDIT);
				} else {
					btsCatWorkKddEntity.setSync_Status(causeCatWorkEntityKdd
							.getBts_Cat_WorkEntity().getSync_Status());
				}

				// btsCatWorkKddEntity.setIsActive(Constants.ISACTIVE.ACTIVE);

				catWorkController.updateBtsCatWorkEntity(btsCatWorkKddEntity);
				Cause_Bts_Cat_WorkEntity causeCatWorkKddEntity = new Cause_Bts_Cat_WorkEntity();
				causeCatWorkKddEntity.getBts_Cat_WorkEntity()
						.setSupervision_Bts_Cat_Work_Id(
								btsCatWorkKddEntity
										.getSupervision_Bts_Cat_Work_Id());
				causeCatWorkKddEntity.setListCauseUniQualify(curListUnqualify);

				for (Supervision_LBG_UnqualifyItemEntity addItemCause : causeCatWorkEntityKdd
						.getListCauseUniQualify()) {
					// xoa het nguyen nhan khong dat cu
					// cauCatWorkController
					// .deleteCause_Bts_Cat_WorkEntity(unqualify);
					/* 1. Chinh sua nguyen nhan khong dat */
					if (addItemCause.getCause_Line_Bg_Id() > 0) {
						/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
						if (addItemCause.isDelete()) {
							this.cauCatWorkController
									.deleteCause_Bts_Cat_WorkEntity(addItemCause);
						}
						/* 1.2. Update lai nguyen nhan khong dat */
						else {
							// neu chuyen trang thai tu khong dat sang dat thi
							// xoa
							// nguyen nhan khong dat di
							if (btsCatWorkKddEntity.getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT) {
								this.cauCatWorkController
										.deleteCause_Bts_Cat_WorkEntity(addItemCause);
							}

							if (addItemCause.getLstNewAttachFile().size() > 0
									|| (addItemCause.getLstNewAttachFile()
											.size() == 0 && addItemCause
											.getLstAttachFile().size() == 0)) {
								// xoa anh cu di
								List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
										.getLstAttachFile(
												Cause_Bts_Cat_WorkField.TABLE_NAME,
												addItemCause
														.getCause_Line_Bg_Id());
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
							// * Neu da ton tai file luu trong bang attachment
							// * thi chi thay doi ten va duong dan
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
							// .getNewAttachFile().getFile_Path(),
							// sFilePath);
							//
							// supvConstrAttachFileController
							// .updatePathNameFile(idAttachFile,
							// sFileName, sFilePath,
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

							long lastId = Ktts_KeyController.getInstance()
									.getKttsNextKey(
											Cause_Bts_Cat_WorkField.TABLE_NAME);
							if (lastId == 0) {
								return true;
							}

							Cause_Bts_Cat_WorkEntity addCauseItem = new Cause_Bts_Cat_WorkEntity();
							addItemCause
									.setSync_Status(Constants.SYNC_STATUS.ADD);
							addItemCause.setIsActive(Constants.ISACTIVE.ACTIVE);
							addItemCause.setProcessId(0);
							addItemCause.setSupervisionConstrId(btsEntity
									.getSupervision_ConstrEntity()
									.getSupervision_Constr_Id());

							addCauseItem
									.getBts_Cat_WorkEntity()
									.setSupervision_Bts_Cat_Work_Id(
											causeCatWorkEntityKdd
													.getBts_Cat_WorkEntity()
													.getSupervision_Bts_Cat_Work_Id());

							cauCatWorkController
									.insertCause_Bts_Cat_WorkEntity(
											addItemCause, addCauseItem
													.getBts_Cat_WorkEntity(),
											lastId, idUser);
							ListIdCause.add(lastId);

							// Luu anh nguyen nhan loi neu co
							// if (!StringUtil.isNullOrEmpty(addItemCause
							// .getNewAttachFile().getFile_Path())) {
							// this.supvConstrAttachFileController
							// .coppyAndAddAttachFile(
							// this.constr_ConstructionEmployeeItem,
							// addItemCause.getNewAttachFile()
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
		} catch (Exception e) {
			Log.e("error", e.toString());
		}

		return false;
	}

	public void saveData() {
		try {
			if (tabs.getCurrentTab() == 0) {
				if (querryPm()) {
					outOfKey = true;
					return;
				} else
					outOfKey = false;
			}
			if (tabs.getCurrentTab() == 1) {
				if (querryNmn()) {
					outOfKey = true;
					return;
				} else
					outOfKey = false;
			}
			if (tabs.getCurrentTab() == 2) {
				if (querryKdd()) {
					outOfKey = true;
					return;
				} else
					outOfKey = false;
			}
			Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
					this);
			constr_Controller.updateSyncStatus(constr_ConstructionItem
					.getSupervision_Constr_Id());

			// TODO: Thiet lap ket luan. DanhDue ExOICTIF
			boolean bDeny = checkCauseDeny(constr_ConstructionItem);
			Log.i("Check_Deny", String.valueOf(bDeny));
			// Toast.makeText(getApplicationContext(),
			// String.valueOf(checkCauseDeny(constr_ConstructionEmployeeItem)),
			// Toast.LENGTH_LONG).show();

			if (bDeny) {
				constr_Controller.updateStatus(
						constr_ConstructionItem.getSupervision_Constr_Id(), 0);
			} else {
				constr_Controller.updateStatus(
						constr_ConstructionItem.getSupervision_Constr_Id(), 1);
			}

		} catch (Exception e) {
			Log.e("error", e.toString());
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBTS_CatWorkView
				.findViewById(R.id.actionbar);
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
				requestSync(SupervisionBtsCatWorkActivity.this);
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

	public RelativeLayout getSupervision_bts_catwork_cb_thietke() {
		return supervision_bts_catwork_cb_thietke;
	}

	public void setSupervision_bts_catwork_cb_thietke(
			RelativeLayout supervision_bts_catwork_cb_thietke) {
		this.supervision_bts_catwork_cb_thietke = supervision_bts_catwork_cb_thietke;
	}

	public RelativeLayout getSupervision_bts_catwork_thicongphongmay_cb_chontrangthai() {
		return supervision_bts_catwork_thicongphongmay_cb_chontrangthai;
	}

	public void setSupervision_bts_catwork_thicongphongmay_cb_chontrangthai(
			RelativeLayout supervision_bts_catwork_thicongphongmay_cb_chontrangthai) {
		this.supervision_bts_catwork_thicongphongmay_cb_chontrangthai = supervision_bts_catwork_thicongphongmay_cb_chontrangthai;
	}

	public RelativeLayout getSupervision_bts_catwork_thicongnhamayno_cb_chontrangthai() {
		return supervision_bts_catwork_thicongnhamayno_cb_chontrangthai;
	}

	public void setSupervision_bts_catwork_thicongnhamayno_cb_chontrangthai(
			RelativeLayout supervision_bts_catwork_thicongnhamayno_cb_chontrangthai) {
		this.supervision_bts_catwork_thicongnhamayno_cb_chontrangthai = supervision_bts_catwork_thicongnhamayno_cb_chontrangthai;
	}

	public RelativeLayout getSupervision_bts_catwork_thicongkeoday_cb_chontrangthai() {
		return supervision_bts_catwork_thicongkeoday_cb_chontrangthai;
	}

	public void setSupervision_bts_catwork_thicongkeoday_cb_chontrangthai(
			RelativeLayout supervision_bts_catwork_thicongkeoday_cb_chontrangthai) {
		this.supervision_bts_catwork_thicongkeoday_cb_chontrangthai = supervision_bts_catwork_thicongkeoday_cb_chontrangthai;
	}

	public RelativeLayout getSupervision_bts_catwork_thicongkeoday_cb_loaididay() {
		return supervision_bts_catwork_thicongkeoday_cb_loaididay;
	}

	public void setSupervision_bts_catwork_thicongkeoday_cb_loaididay(
			RelativeLayout supervision_bts_catwork_thicongkeoday_cb_loaididay) {
		this.supervision_bts_catwork_thicongkeoday_cb_loaididay = supervision_bts_catwork_thicongkeoday_cb_loaididay;
	}

	// private void requestSync() {
	// if (this.check3GWifi()) {
	// showProgressDialog(StringUtil.getString(R.string.text_sync_loading));
	// Bundle bundle = new Bundle();
	// ActionEvent e = new ActionEvent();
	// e.action = ActionEventConstant.REQEST_SYNC;
	// e.viewData = bundle;
	// e.isBlockRequest = true;
	// e.sender = this;
	// Home_Controller.getInstance().handleViewEvent(e);
	// } else {
	// this.show3GWifiOffline();
	// }
	// }

	@Override
	public void handleModelViewEvent(ModelEvent modelEvent) {
		ActionEvent e = modelEvent.getActionEvent();
		switch (e.action) {
		case ActionEventConstant.REQEST_SYNC:

			refreshForm();
			// closeProgressDialog();
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
			showCompleteDate(constr_ConstructionItem,
					Constants.PROGRESS_TYPE.BTS_TYPE,
					Constants.PROGRESS_TYPE.PHONG_MAY,
					supervision_bts_pm_complete_date,
					rl_supervision_bts_cat_work_complete);
		} else {
			if (i == 1) {
				showCompleteDate(constr_ConstructionItem,
						Constants.PROGRESS_TYPE.BTS_TYPE,
						Constants.PROGRESS_TYPE.NHA_MAY_NO,
						supervision_bts_nmn_complete_date,
						rl_supervision_bts_cat_work_complete);
			} else {
				showCompleteDate(constr_ConstructionItem,
						Constants.PROGRESS_TYPE.BTS_TYPE,
						Constants.PROGRESS_TYPE.KEO_DAY_DIEN_PHONG_MAY_NO,
						supervision_bts_kdd_complete_date,
						rl_supervision_bts_cat_work_complete);
			}
		}
	}
}
