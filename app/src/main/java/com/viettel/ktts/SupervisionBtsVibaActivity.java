package com.viettel.ktts;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
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
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_Cat_WorkController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Cat_Supervision_Constr_WorkEntity;
import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
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
import com.viettel.view.control.Bts_VibaAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

public class SupervisionBtsVibaActivity extends SupervisionBtsBaseActivity {

    private static final String TAG = SupervisionBtsVibaActivity.class.getSimpleName();
    private View spvBts_VibaView;
	/**
	 * textview
	 */
	private TextView supervision_bts_tv_matram;
	private TextView supervision_bts_tv_mact;
	private TextView supervision_bts_viba_tv_title;

	/**
	 * combobox
	 */
	// combobox loai thong tin
	private TextView supervision_bts_viba_tv_thietke;

	private LayoutInflater infalInflater;

	private Button save;
	private RelativeLayout btn_daucao;
	private RelativeLayout btn_dauthap;

	private ListView listview_tcnt;
	private ListView listview_tctn;

	private int isInfomation;
	private Menu_DropdownPopup dropdownPopupMenuInfomation;
//	private Image_ViewGalleryPopup imgViewPopup;
	private Contruction_UnqualifyPopup contructionUnqualifyPopup;

	private List<DropdownItemEntity> listInfomation = null;
	private Constr_Construction_EmployeeEntity constr_ConstructionItem;

	private Bts_VibaAdapter bts_VibarTcntAdapter;
	private Bts_VibaAdapter bts_VibarTctnAdapter;

	// danh sach giam sat thi cong ngoai troi
	private List<Cause_Bts_Cat_WorkEntity> listDataTcnt;

	// danh sach giam sat thi cong trong nha
	private List<Cause_Bts_Cat_WorkEntity> listDataTctn;

	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Cause_Bts_Cat_WorkEntity curEditItem = null;

	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkItem;
	private Supervision_BtsEntity btsEntity;
	private List<Cat_Supervision_Constr_WorkEntity> listConstrWorkTcnt;
	private List<Cat_Supervision_Constr_WorkEntity> listConstrWorkTctn;
	private Edit_Text_Popup editTextPopup;
	private Supv_Constr_Attach_FileController supvConstrAttachFileController;
	private int tabHeight = 40;
	private View itemVibaView;
	private LinearLayout viewLayout;
	private TabHost tabs;
	private String worktype;
	private boolean isClick = false;
	private boolean isChooseDauthap = false;
	private boolean isChooseDaucao = false;
	private boolean outOfKey = false;
	private ConfirmDialog confirmSave;

	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private List<Supervision_LBG_UnqualifyItemEntity> listVibaAcceptanceItem;

	private Button rl_supervision_bts_viba_bt_complete;
	private TextView supervision_bts_viba_complete_date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_bts_viba_activity);
		initComponent();
//		setHeader();
		setTitle(getString(R.string.supervision_bts_title));
		isInfomation = getIntent().getExtras().getInt(
				IntentConstants.INTENT_DESIGNINFO);

		initData();

		constr_ConstructionItem = getConstr_Construction_Employee();

		// new loadMore().execute();
		setData();
		
		closeProgressDialog();

	}

	public void initComponent() {
		spvBts_VibaView = addView(R.layout.supervision_bts_viba_activity, R.id.rl_spv_bts_viba);
		infalInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		viewLayout = (LinearLayout) spvBts_VibaView.findViewById(R.id.rl_supervision_bts_viba_header);

		// textview
		supervision_bts_tv_matram = (TextView) spvBts_VibaView.findViewById(R.id.rl_supervision_bts_viba_tv_matram);
		supervision_bts_tv_mact = (TextView) spvBts_VibaView.findViewById(R.id.rl_supervision_bts_viba_tv_mact);

		// combobox
		// supervision_bts_viba_cb_thietke = (RelativeLayout)
		// findViewById(R.id.rl_supervision_bts_viba_search_thietke);
		supervision_bts_viba_tv_thietke = (TextView) spvBts_VibaView.findViewById(R.id.rl_supervision_bts_viba_tv_thietke);
		supervision_bts_viba_tv_thietke.setOnClickListener(this);
		initView();
		getView();

	}

	public void initView() {
		// button
		btn_dauthap = (RelativeLayout) spvBts_VibaView
				.findViewById(R.id.rl_supervision_bts_viba_bt_dauthap);
		btn_daucao = (RelativeLayout) spvBts_VibaView
				.findViewById(R.id.rl_supervision_bts_viba_bt_daucao);

		supervision_bts_viba_complete_date = (TextView) spvBts_VibaView
				.findViewById(R.id.supervision_bts_viba_complete_date);
		
		rl_supervision_bts_viba_bt_complete = (Button) spvBts_VibaView
				.findViewById(R.id.rl_supervision_bts_viba_bt_complete);
		rl_supervision_bts_viba_bt_complete.setOnClickListener(this);

		btn_dauthap.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getView();
				isClick = true;
				isChooseDauthap = true;
				isChooseDaucao = false;
				viewLayout.removeAllViews();
				worktype = Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW;
				supervision_bts_viba_tv_title.setText(StringUtil
						.getString(R.string.bts_viba_giamsat_dauthap));
				setListTcntData(worktype);
				setListTctnData(worktype);
				refreshView(worktype);
				viewLayout.addView(itemVibaView);

				/* Set endable va disable voi cong trinh da hoan thanh */
				if (constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
						|| constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED
						|| constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_COSITE) {
					save.setVisibility(View.GONE);
				}
				if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE
						&& btsEntity.getConstructionType() == Constants.CONSTRUCTION_TYPE.NANG_CAP) {
					save.setVisibility(View.GONE);
				}
			}
		});
		btn_daucao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getView();
				isClick = true;
				isChooseDauthap = false;
				isChooseDaucao = true;
				viewLayout.removeAllViews();
				worktype = Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH;
				supervision_bts_viba_tv_title.setText(StringUtil
						.getString(R.string.bts_viba_giamsat_daucao));
				setListTcntData(worktype);
				setListTctnData(worktype);
				refreshView(worktype);
				viewLayout.addView(itemVibaView);
				/* Set endable va disable voi cong trinh da hoan thanh */
				if (constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
						|| constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED
						|| constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_COSITE) {
					save.setVisibility(View.GONE);
				}
				if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE
						&& btsEntity.getConstructionType() == Constants.CONSTRUCTION_TYPE.NANG_CAP) {
					save.setVisibility(View.GONE);
				}
			}
		});
	}

	

	public void getView() {
		itemVibaView = infalInflater.inflate(R.layout.item_expand_bts_viba,
				null);

		String thicongngoaitroi = StringUtil
				.getString(R.string.bts_viba_thicong_ngoaitroi);
		tabs = (TabHost) itemVibaView
				.findViewById(R.id.supervision_bts_viba_tabhost);
		tabs.setup();
		TabHost.TabSpec specThicongngoaitroi = tabs
				.newTabSpec(thicongngoaitroi);
		specThicongngoaitroi
				.setContent(R.id.ll_supervision_bts_viba_tl_thongtinthietbi_thicong_ngoaitroi);
		specThicongngoaitroi.setIndicator(makeTabIndicator(thicongngoaitroi));

		String thicongtrongnha = StringUtil
				.getString(R.string.bts_viba_thicong_trongnha);
		TabHost.TabSpec specThicongtrongnha = tabs.newTabSpec(thicongtrongnha);
		specThicongtrongnha
				.setContent(R.id.ll_supervision_bts_viba_tl_thongtinthietbi_thicong_trongnha);
		specThicongtrongnha.setIndicator(makeTabIndicator(thicongtrongnha));

		tabs.addTab(specThicongngoaitroi);
		tabs.addTab(specThicongtrongnha);

		save = (Button) itemVibaView
				.findViewById(R.id.rl_supervision_bts_viba_bt_save);
		save.setOnClickListener(this);

		supervision_bts_viba_tv_title = (TextView) itemVibaView
				.findViewById(R.id.rl_supervision_bts_viba_tv_title);
		// list view cho danh sach thi cong ngoai troi
		listview_tcnt = (ListView) itemVibaView
				.findViewById(R.id.supervision_bts_viba_lv_thicong_ngoaitroi);

		// list view cho danh sach thi cong trong nha
		listview_tctn = (ListView) itemVibaView
				.findViewById(R.id.supervision_bts_viba_lv_thicong_trongnha);
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

	public void initData() {
		/* Drop down */
		String sChoice = "";
		listInfomation = GloablUtils.getListBTSInfo(sChoice);
		sChoice = getResources().getString(R.string.text_choice1);
		supervision_bts_viba_tv_thietke.setText(GloablUtils
				.getStringBTSInfo(isInfomation));

	}

	public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
		return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
				.getSerializable(IntentConstants.INTENT_DATA);
	}

	public void setData() {
		// String sChoice = getResources().getString(R.string.text_choice1);

		supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
				this);

		supervision_bts_tv_matram.setText(constr_ConstructionItem
				.getStationCode());
		supervision_bts_tv_mact.setText(String.valueOf(constr_ConstructionItem
				.getConstrCode()));

		Supervision_BtsController bts_Controller = new Supervision_BtsController(
				this);

		listVibaAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
				.getAllUnQualifyItemByName(Constants.ACCEPTANCE_TYPE.BTS_VIBA,
						Constants.UNQUALIFY_TYPE.BTS_TYPE);

		btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
				.getSupervision_Constr_Id());
		
		showCompleteDate(constr_ConstructionItem,
				Constants.PROGRESS_TYPE.BTS_TYPE,
				Constants.PROGRESS_TYPE.LAP_DAT_VIBA,
				supervision_bts_viba_complete_date,
				rl_supervision_bts_viba_bt_complete);

	}

	public void refreshView(String worktype) {

		// thi cong ngoai troi
		listDataTcnt = new ArrayList<Cause_Bts_Cat_WorkEntity>();

		getDataTcntFromDatabase(worktype);

		if (listDataTcnt.size() != 0) {
			// truong hop da co du lieu
			for (Cause_Bts_Cat_WorkEntity curItem : this.listDataTcnt) {
				curItem.setNew(false);
				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = curItem
						.getListCauseUniQualify();

				/* Gan anh nghiem thu */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curItem
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
							.getLstAttachFile(
									Cause_Bts_Cat_WorkField.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}
				}
				curItem.setListCauseUniQualify(listUnqualifyItem);
			}
		} else
			for (int i = 0; i < listConstrWorkTcnt.size(); i++) {
				Cause_Bts_Cat_WorkEntity catWorkEntity = new Cause_Bts_Cat_WorkEntity();
				catWorkEntity.setStt(i + 1);
				catWorkEntity.setTenHangMuc(listConstrWorkTcnt.get(i)
						.getWork_Name());
				catWorkEntity.setWorkCode(listConstrWorkTcnt.get(i)
						.getWork_Code());
				catWorkEntity.getBts_Cat_WorkEntity()
						.setCat_Supervision_Constr_Work_Id(
								listConstrWorkTcnt.get(i)
										.getCat_Supervision_Constr_Work_Id());
				catWorkEntity.getBts_Cat_WorkEntity().setSupervision_Bts_Id(
						btsEntity.getSupervision_Bts_Id());
				catWorkEntity.setNew(true);
				catWorkEntity.setEdited(false);
				listDataTcnt.add(catWorkEntity);
			}

		bts_VibarTcntAdapter = new Bts_VibaAdapter(this, listDataTcnt);

		listview_tcnt.setAdapter(bts_VibarTcntAdapter);

		// thi cong trong nha
		listDataTctn = new ArrayList<Cause_Bts_Cat_WorkEntity>();

		getDataTctnFromDatabase(worktype);

		if (listDataTctn.size() != 0) {
			// truong hop da co du lieu
			for (Cause_Bts_Cat_WorkEntity curItem : this.listDataTctn) {

				curItem.setNew(false);
				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = curItem
						.getListCauseUniQualify();

				/* Gan anh nghiem thu */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curItem
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
							.getLstAttachFile(
									Cause_Bts_Cat_WorkField.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}
				}
				curItem.setListCauseUniQualify(listUnqualifyItem);
			}
		} else
			for (int i = 0; i < listConstrWorkTctn.size(); i++) {
				Cause_Bts_Cat_WorkEntity catWorkEntity = new Cause_Bts_Cat_WorkEntity();
				catWorkEntity.setStt(i + 1);
				catWorkEntity.setTenHangMuc(listConstrWorkTctn.get(i)
						.getWork_Name());
				catWorkEntity.setWorkCode(listConstrWorkTctn.get(i)
						.getWork_Code());
				catWorkEntity.getBts_Cat_WorkEntity()
						.setCat_Supervision_Constr_Work_Id(
								listConstrWorkTctn.get(i)
										.getCat_Supervision_Constr_Work_Id());
				catWorkEntity.getBts_Cat_WorkEntity().setSupervision_Bts_Id(
						btsEntity.getSupervision_Bts_Id());
				catWorkEntity.setNew(true);
				catWorkEntity.setEdited(false);
				listDataTctn.add(catWorkEntity);
			}

		bts_VibarTctnAdapter = new Bts_VibaAdapter(this, listDataTctn);

		listview_tctn.setAdapter(bts_VibarTctnAdapter);
	}

	public void getDataTcntFromDatabase(String worktype) {
		Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
				this);
		for (int i = 0; i < listConstrWorkTcnt.size(); i++) {
			Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

			String workcode = listConstrWorkTcnt.get(i).getWork_Code();

			if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA, worktype,
						btsEntity.getSupervision_Bts_Id());

			}
			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN,
						worktype, btsEntity.getSupervision_Bts_Id());
			}

			item.setWorkCode(workcode);
			item.setStt(i + 1);
			item.setTenHangMuc(listConstrWorkTcnt.get(i).getWork_Name());

			if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
					|| item.getBts_Cat_WorkEntity().getStatus() >= 0) {
				listDataTcnt.add(item);
			}

		}
	}

	public void getDataTctnFromDatabase(String worktype) {
		Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
				this);
		for (int i = 0; i < listConstrWorkTctn.size(); i++) {
			Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

			String workcode = listConstrWorkTctn.get(i).getWork_Code();

			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG)) {
				item = causeCatWorkController
						.getCause_Bts_Cat_WorkEntity(
								Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG,
								worktype, btsEntity.getSupervision_Bts_Id());
			}
			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC,
						worktype, btsEntity.getSupervision_Bts_Id());
			}
			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC,
						worktype, btsEntity.getSupervision_Bts_Id());
			}

			item.setWorkCode(workcode);
			item.setStt(i + 1);
			item.setTenHangMuc(listConstrWorkTctn.get(i).getWork_Name());

			if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
					|| item.getBts_Cat_WorkEntity().getStatus() >= 0) {
				listDataTctn.add(item);
			}

		}
	}

	public void setListTcntData(String work_type) {
		listConstrWorkTcnt = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

		Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
				this);

		// lay ten constr work tuong ung voi work code antenna
		Cat_Supervision_Constr_WorkEntity constrWorkThicong_ngtroi = constrWorkController
				.getListConstrWorkEntityByWorkCodeAndWorkType(
						Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA, work_type);

		// lay ten constr work tuong ung voi work code cap trung tan
		Cat_Supervision_Constr_WorkEntity constrWorkCap_trungtan = constrWorkController
				.getListConstrWorkEntityByWorkCodeAndWorkType(
						Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN,
						work_type);
		if (constrWorkThicong_ngtroi.getCat_Supervision_Constr_Work_Id() > 0) {
			listConstrWorkTcnt.add(constrWorkThicong_ngtroi);
		}
		if (constrWorkCap_trungtan.getCat_Supervision_Constr_Work_Id() > 0) {
			listConstrWorkTcnt.add(constrWorkCap_trungtan);
		}

	}

	public void setListTctnData(String work_type) {
		listConstrWorkTctn = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

		Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
				this);

		// lay ten constr work tuong ung voi work code tinh trang phan cung
		Cat_Supervision_Constr_WorkEntity constrWorkTinhtrang_phancung = constrWorkController
				.getListConstrWorkEntityByWorkCodeAndWorkType(
						Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG,
						work_type);

		// lay ten constr work tuong ung voi work code cap nguon DC
		Cat_Supervision_Constr_WorkEntity constrWorkCapnguon_Dc = constrWorkController
				.getListConstrWorkEntityByWorkCodeAndWorkType(
						Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC,
						work_type);

		// lay ten constr work tuong ung voi work code cap bang tan goc
		Cat_Supervision_Constr_WorkEntity constrWorkCapbangtan = constrWorkController
				.getListConstrWorkEntityByWorkCodeAndWorkType(
						Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC,
						work_type);

		listConstrWorkTctn.add(constrWorkTinhtrang_phancung);
		listConstrWorkTctn.add(constrWorkCapnguon_Dc);
		listConstrWorkTctn.add(constrWorkCapbangtan);
	}

	private String checkVailid(Cause_Bts_Cat_WorkEntity itemCheck) {
		String sReslut = "";
		try {
			int countNnkdCheck = 0;

			for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
				if (!itemCheck.getListCauseUniQualify().get(i).isDelete()) {
					countNnkdCheck++;
					break;
				}
			}
			if (countNnkdCheck > 0
					&& itemCheck.getBts_Cat_WorkEntity().getStatus() < 0) {
				sReslut = StringUtil
						.getString(R.string.bts_viba_nn_access_tempty);
				sReslut += itemCheck.getTenHangMuc();
			} else if ((itemCheck.getBts_Cat_WorkEntity().getStatus() >= 0)) {

				if (itemCheck.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT
						&& countNnkdCheck < 1) {
					sReslut = StringUtil
							.getString(R.string.bts_viba_nn_khongdat_tempty);
					sReslut += itemCheck.getTenHangMuc();

				}
			} else if (itemCheck.getBts_Cat_WorkEntity().getStatus() < 0
					&& itemCheck.getBts_Cat_WorkEntity().getFail_Desc()
							.equals("")) {
				sReslut = StringUtil.getString(R.string.bts_viba_danhgia_empty);
				sReslut += itemCheck.getTenHangMuc();
				sReslut += ". ";
				sReslut += StringUtil
						.getString(R.string.bts_viba_nn_diengiai_tempty);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.rl_supervision_bts_viba_bt_complete:
			confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save),
					OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			confirmSave.show();
			
			
			
			break;
		// click save
		case R.id.rl_supervision_bts_viba_bt_save:
			String messageError = "";

			if (tabs.getCurrentTab() == 0) {
				for (Cause_Bts_Cat_WorkEntity curItemData : listDataTcnt) {
					messageError = this.checkVailid(curItemData);
					if (!StringUtil.isNullOrEmpty(messageError)) {
						break;
					}
				}

				if (!StringUtil.isNullOrEmpty(messageError)) {
					this.showDialog(messageError);
					return;
				} else {
					confirmSave = new ConfirmDialog(this,
							StringUtil.getString(R.string.text_confirm_save));
					confirmSave.show();
				}
			} else if (tabs.getCurrentTab() == 1) {
				for (Cause_Bts_Cat_WorkEntity curItemData : listDataTctn) {
					messageError = this.checkVailid(curItemData);
					if (!StringUtil.isNullOrEmpty(messageError)) {
						break;
					}
				}
				if (!StringUtil.isNullOrEmpty(messageError)) {
					this.showDialog(messageError);
				} else {
					confirmSave = new ConfirmDialog(this,
							StringUtil.getString(R.string.text_confirm_save));
					confirmSave.show();
				}
			}

			break;
		// click combobox thong tin
		case R.id.rl_supervision_bts_viba_tv_thietke:
			this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
					this.listInfomation);

			dropdownPopupMenuInfomation.show(v);
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

	@SuppressWarnings("unchecked")
	@Override
	public void onEvent(int eventType, View control, Object data) {
		switch (eventType) {
		case OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK:
			DropdownItemEntity dropdownItem = (DropdownItemEntity) data;
			String typeItem = dropdownItem.getType();
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_INFO)) {

				this.supervision_bts_viba_tv_thietke.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuInfomation.dismiss();
				this.showProgressDialog(StringUtil
						.getString(R.string.text_loading));

				isInfomation = dropdownItem.getId();

				Bundle bundleMonitorData = new Bundle();
				bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
						constr_ConstructionItem);
				bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
						isInfomation);

				gotoBtsActivity(bundleMonitorData, isInfomation);

				finish();
			}
			break;
		// chon nguyen nhan khong dat
		case OnEventControlListener.EVENT_SUPERVISION_UNQUALIFY:
			this.curEditItem = (Cause_Bts_Cat_WorkEntity) data;
			if (this.curEditItem.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {
				String workcode = curEditItem.getWorkCode();

				Cat_Cause_Constr_UnQualifyController unqualifyController = new Cat_Cause_Constr_UnQualifyController(
						this);

				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA)) {

					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_ANTENNA,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);
				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN)) {

					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_TRUNG_TAN,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);
				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG)) {
					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_TINH_TRANG_PHAN_CUNG,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);
				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC)) {
					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_NGUON_DC,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);
				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC)) {
					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_BANG_TAN_GOC,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);
				}

				setUnqualify();

				contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
						this, null, this.listUnqualifyCatWorkItem);
				contructionUnqualifyPopup.showAtCenter();
			} else if (this.curEditItem.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT) {
				this.setAcceptance();
				contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
						this, null, this.listVibaAcceptanceItem);
				contruoctionAcceptancePopup.showAtCenter();

			} else {
				Toast.makeText(
						this,
						StringUtil
								.getString(R.string.constr_line_tank_unqualify_choice_message),
						Toast.LENGTH_SHORT).show();
			}
			break;

		// click dien giai
		case OnEventControlListener.EVENT_SUPERVISION_EDIT:

			this.curEditItem = (Cause_Bts_Cat_WorkEntity) data;
			switch (this.curEditItem.getiField()) {
			case Constants.BTS_VIBA_EDIT.FAIL_DESC:
				String sFailDescTextValue = this.curEditItem
						.getBts_Cat_WorkEntity().getFail_Desc();

				editTextPopup = new Edit_Text_Popup(this, null,
						sFailDescTextValue,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
				editTextPopup.showAtCenter();
				break;
			}
			break;

		case OnEventControlListener.EVENT_SET_TEXT:
			String sSetTextValue = (String) data;

			if (this.curEditItem.getiField() == Constants.BTS_VIBA_EDIT.FAIL_DESC) {

				this.curEditItem.getBts_Cat_WorkEntity().setFail_Desc(
						sSetTextValue);

				this.curEditItem.setEdited(true);
				this.bts_VibarTcntAdapter.notifyDataSetChanged();
				this.bts_VibarTctnAdapter.notifyDataSetChanged();
			}

			editTextPopup.hidePopup();

			break;

		// chon danh gia la dat
		case OnEventControlListener.EVENT_CHOICE_ACCESS_DAT:

			this.curEditItem = (Cause_Bts_Cat_WorkEntity) data;
			this.curEditItem.setEdited(true);
			break;
		// chon danh gia la khong dat
		case OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT:
			this.curEditItem = (Cause_Bts_Cat_WorkEntity) data;
			this.curEditItem.setEdited(true);
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
		case OnEventControlListener.EVENT_CONFIRM_OK:
			// this.showProgressDialog(StringUtil.getString(R.string.text_loading));
			// this.saveData();
			// this.closeProgressDialog();
			SaveAsyncTask task = new SaveAsyncTask();
			task.execute();
			break;
		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			this.saveAcceptance();
			contruoctionAcceptancePopup.hidePopup();
			this.bts_VibarTcntAdapter.notifyDataSetChanged();
			this.bts_VibarTctnAdapter.notifyDataSetChanged();
			this.curEditItem.setEdited(true);
			break;
		/* Dong nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:

			this.saveUnqualify();
			contructionUnqualifyPopup.hidePopup();
			this.bts_VibarTcntAdapter.notifyDataSetChanged();
			this.bts_VibarTctnAdapter.notifyDataSetChanged();
			this.curEditItem.setEdited(true);

			break;
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

			if (this.curEditItem.getBts_Cat_WorkEntity().getStatus() == Constants.TANK_STATUS.DAT) {
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
			} else if (this.curEditItem.getBts_Cat_WorkEntity().getStatus() == Constants.TANK_STATUS.KHONG_DAT) {
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
					Constants.PROGRESS_TYPE.LAP_DAT_VIBA, outOfKey);
			showCompleteDate(constr_ConstructionItem,
					Constants.PROGRESS_TYPE.BTS_TYPE,
					Constants.PROGRESS_TYPE.LAP_DAT_VIBA,
					supervision_bts_viba_complete_date,
					rl_supervision_bts_viba_bt_complete);
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
				Toast.makeText(SupervisionBtsVibaActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshView(worktype);

			Toast toast = Toast.makeText(SupervisionBtsVibaActivity.this,
					getResources().getString(R.string.text_update_success),
					Toast.LENGTH_LONG);

			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();

			closeProgressDialog();
		}

	}

	public boolean saveDataType(
			Supervision_Bts_Cat_WorkController btsCatWorkController,
			Cause_Bts_Cat_WorkController causeBtsCatWorkController,
			List<Cause_Bts_Cat_WorkEntity> listItem) {

		try {
			long idUser = GlobalInfo.getInstance().getUserId();
			for (Cause_Bts_Cat_WorkEntity curItemData : listItem) {
				// tao moi (insert tat ca truong du lieu cot vao database)
				if (curItemData.isNew()) {
					long idBtsCatWorkLast = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_Bts_Cat_WorkField.TABLE_NAME);
					if (idBtsCatWorkLast == 0) {
						return true;
					}

					curItemData.getBts_Cat_WorkEntity()
							.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkLast);

					curItemData.getBts_Cat_WorkEntity().setSync_Status(
							Constants.SYNC_STATUS.ADD);
					curItemData.getBts_Cat_WorkEntity().setIsActive(
							Constants.ISACTIVE.ACTIVE);
					curItemData.getBts_Cat_WorkEntity().setProcessId(0);
					curItemData.getBts_Cat_WorkEntity().setEmployeeId(idUser);
					curItemData.getBts_Cat_WorkEntity().setSupervisionConstrId(
							btsEntity.getSupervision_ConstrEntity()
									.getSupervision_Constr_Id());

					btsCatWorkController.insertBtsCatWorkEntity(curItemData
							.getBts_Cat_WorkEntity());
					Cause_Bts_Cat_WorkEntity causeBtsCatWorkEntity = new Cause_Bts_Cat_WorkEntity();
					causeBtsCatWorkEntity.getBts_Cat_WorkEntity()
							.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkLast);
					causeBtsCatWorkEntity.setListCauseUniQualify(curItemData
							.getListCauseUniQualify());

					if (curItemData.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

						ArrayList<Long> ListIdCause = new ArrayList<Long>();

						for (Supervision_LBG_UnqualifyItemEntity unqualify : causeBtsCatWorkEntity
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

							causeBtsCatWorkController
									.insertCause_Bts_Cat_WorkEntity(unqualify,
											causeBtsCatWorkEntity
													.getBts_Cat_WorkEntity(),
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
													Cause_Bts_Cat_WorkField.TABLE_NAME);

								}
							}
							dem++;
						}
					} else {
						// neu dat thi save anh nghiem thu
						List<Supervision_LBG_UnqualifyItemEntity> curListAcceptance = curItemData
								.getListAcceptance();
						for (Supervision_LBG_UnqualifyItemEntity curItemUnqualify : curListAcceptance) {
							Acceptance_Bts_Cat_WorkEntity addCauseItem = new Acceptance_Bts_Cat_WorkEntity();
							addCauseItem
									.setCat_Cause_Constr_Acceptance_Id(curItemUnqualify
											.getCat_Cause_Constr_Acceptance_Id());
							addCauseItem
									.setSupervision_Bts_Cat_Work_Id(idBtsCatWorkLast);
							addCauseItem
									.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_VIBA);
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
							causeBtsCatWorkController.addItem(addCauseItem);

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
					// update nhung du lieu cot da sua
					if (curItemData.isEdited()) {

						if (curItemData.getBts_Cat_WorkEntity()
								.getSync_Status() == Constants.SYNC_STATUS.UPDATED) {
							curItemData.getBts_Cat_WorkEntity().setSync_Status(
									Constants.SYNC_STATUS.EDIT);
						}

						btsCatWorkController.updateBtsCatWorkEntity(curItemData
								.getBts_Cat_WorkEntity());

						// causeBtsCatWorkController
						// .deleteCause_Bts_Cat_WorkEntity(curItemData);

						Cause_Bts_Cat_WorkEntity causeBtsCatWorkEntity = new Cause_Bts_Cat_WorkEntity();
						causeBtsCatWorkEntity
								.getBts_Cat_WorkEntity()
								.setSupervision_Bts_Cat_Work_Id(
										curItemData
												.getBts_Cat_WorkEntity()
												.getSupervision_Bts_Cat_Work_Id());
						causeBtsCatWorkEntity
								.setListCauseUniQualify(curItemData
										.getListCauseUniQualify());

						for (Supervision_LBG_UnqualifyItemEntity addItemCause : curItemData
								.getListAcceptance()) {
							/* 1. Chinh sua nghiem thu */
							if (addItemCause.getCause_Line_Bg_Id() > 0) {

								// xoa nghiem thu khi chuyen
								// trang thai tu khong dat sang dat
								// if (curItemData.getBts_Cat_WorkEntity()
								// .getStatus() ==
								// Constants.SUPERVISION_STATUS.CHUADAT) {
								// btsCatWorkController
								// .deleteAccept(addItemCause);
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
								if (curItemData.getBts_Cat_WorkEntity()
										.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
									Acceptance_Bts_Cat_WorkEntity addCauseItem = new Acceptance_Bts_Cat_WorkEntity();
									addCauseItem
											.setCat_Cause_Constr_Acceptance_Id(addItemCause
													.getCat_Cause_Constr_Acceptance_Id());
									addCauseItem
											.setSupervision_Bts_Cat_Work_Id(curItemData
													.getBts_Cat_WorkEntity()
													.getSupervision_Bts_Cat_Work_Id());
									addCauseItem
											.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_VIBA);
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
									causeBtsCatWorkController
											.addItem(addCauseItem);

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

						for (Supervision_LBG_UnqualifyItemEntity addItemCause : curItemData
								.getListCauseUniQualify()) {
							// xoa het nguyen nhan khong dat cu
							// cauCatWorkController
							// .deleteCause_Bts_Cat_WorkEntity(unqualify);
							/* 1. Chinh sua nguyen nhan khong dat */
							if (addItemCause.getCause_Line_Bg_Id() > 0) {
								/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
								if (addItemCause.isDelete()) {
									causeBtsCatWorkController
											.deleteCause_Bts_Cat_WorkEntity(addItemCause);
								}
								/* 1.2. Update lai nguyen nhan khong dat */
								else {
//									if (curItemData.getBts_Cat_WorkEntity()
//											.getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT) {
//										causeBtsCatWorkController
//												.deleteCause_Bts_Cat_WorkEntity(addItemCause);
//									}

									if (addItemCause.getLstNewAttachFile()
											.size() > 0
											|| (addItemCause
													.getLstNewAttachFile()
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
								if (curItemData.getBts_Cat_WorkEntity()
										.getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {

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
									addItemCause
											.setSupervisionConstrId(btsEntity
													.getSupervision_ConstrEntity()
													.getSupervision_Constr_Id());

									addCauseItem
											.getBts_Cat_WorkEntity()
											.setSupervision_Bts_Cat_Work_Id(
													curItemData
															.getBts_Cat_WorkEntity()
															.getSupervision_Bts_Cat_Work_Id());

									causeBtsCatWorkController
											.insertCause_Bts_Cat_WorkEntity(
													addItemCause,
													addCauseItem
															.getBts_Cat_WorkEntity(),
													lastId, idUser);
									ListIdCause.add(lastId);

									// Luu anh nguyen nhan loi neu co
									// if
									// (!StringUtil.isNullOrEmpty(addItemCause
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
			}
		} catch (Exception e) {
			Log.e("error", e.toString());
		}

		return false;
	}

	/* Ghi nghiem thu vao danh sach ong */
	private void saveAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listVibaAcceptanceItem) {
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
	private void setAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listVibaAcceptanceItem) {
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

	private void saveData() {
		try {
			Supervision_Bts_Cat_WorkController btsCatWorkController = new Supervision_Bts_Cat_WorkController(
					this);
			Cause_Bts_Cat_WorkController causeBtsCatWorkController = new Cause_Bts_Cat_WorkController(
					this);

			if (tabs.getCurrentTab() == 0) {
				if (saveDataType(btsCatWorkController,
						causeBtsCatWorkController, listDataTcnt)) {
					outOfKey = true;
					return;
				} else
					outOfKey = false;
			}
			if (tabs.getCurrentTab() == 1) {
				if (saveDataType(btsCatWorkController,
						causeBtsCatWorkController, listDataTctn)) {
					outOfKey = true;
					return;
				} else
					outOfKey = false;
			}

			// cap nhat trang thai cong trinh
			Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
					this);
			constr_Controller.updateSyncStatus(constr_ConstructionItem
					.getSupervision_Constr_Id());
			
			// TODO: Thiet lap ket luan. DanhDue ExOICTIF
			boolean bDeny = checkCauseDeny(constr_ConstructionItem);
			Log.i("Check_Deny", String.valueOf(bDeny));
//			Toast.makeText(getApplicationContext(), String.valueOf(checkCauseDeny(constr_ConstructionEmployeeItem)), Toast.LENGTH_LONG).show();
			
			if (bDeny) {
				constr_Controller.updateStatus(constr_ConstructionItem.getSupervision_Constr_Id(), 0);
			}
			else {
				constr_Controller.updateStatus(constr_ConstructionItem.getSupervision_Constr_Id(), 1);
			}

		} catch (Exception e) {
			Log.e("error", e.toString());
			// this.showDialog(StringUtil.getString(R.string.text_update_error));
			// Toast toast = Toast.makeText(this,
			// getResources().getString(R.string.text_update_error),
			// Toast.LENGTH_LONG);
			//
			// toast.setGravity(Gravity.CENTER, 0, 0);
			// toast.show();
		}
	}

	/* Ghi nguyen nhan khong dat vao danh sach cot */
	/* 1. Tim nguyen nhan khong dat trong danh sach */
	private void saveUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
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
					addItem.setCause_Line_Bg_Id(this.curEditItem
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

	private void setUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
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
				curCauseUniqualify.setDelete(curItem.isDelete());
				curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
				curCauseUniqualify.setLstNewAttachFile(curItem
						.getLstNewAttachFile());
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

	private void setHeader() {
		final Header myActionBar = (Header) spvBts_VibaView.findViewById(R.id.actionbar);
		myActionBar.setTitle(GlobalInfo.getInstance().getFullName());
		// icon back
		myActionBar.setBackAction(new Header.Action() {
			@Override
			public void performAction(View view) {
				if (isClick) {
					isClick = false;
					viewLayout.removeAllViews();
					// /initView();
					viewLayout.addView(btn_dauthap);
					viewLayout.addView(btn_daucao);
					// getView();
					// viewLayout.addView(itemVibaView);
				} else {
					gotoHomeActivity(new Bundle());
					finish();
				}
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
				requestSync(SupervisionBtsVibaActivity.this);
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
		if (isClick) {
			isClick = false;
			viewLayout.removeAllViews();
			// /initView();
			viewLayout.addView(btn_dauthap);
			viewLayout.addView(btn_daucao);
			// getView();
			// viewLayout.addView(itemVibaView);
		} else {
			gotoHomeActivity(new Bundle());
			finish();
		}
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
			if (isChooseDauthap) {
				setListTcntData(worktype);
				setListTctnData(worktype);
				refreshView(worktype);
			}
			if (isChooseDaucao) {
				setListTcntData(worktype);
				setListTctnData(worktype);
				refreshView(worktype);
			}

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
}
