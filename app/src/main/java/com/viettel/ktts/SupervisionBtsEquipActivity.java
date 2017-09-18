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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.viettel.database.Product_CompanyController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_Cat_WorkController;
import com.viettel.database.Supervision_Bts_EquipController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Cat_Supervision_Constr_WorkEntity;
import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Product_CompanyEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_Bts_EquipEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Bts_Cat_WorkField;
import com.viettel.database.field.Cause_Bts_Cat_WorkField;
import com.viettel.database.field.Supervision_Bts_Cat_WorkField;
import com.viettel.database.field.Supervision_Bts_EquipField;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.control.Bts_CompanyAdapter;
import com.viettel.view.control.Bts_EquipAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

public class SupervisionBtsEquipActivity extends SupervisionBtsBaseActivity {
	public static final String TAG = SupervisionBtsEquipActivity.class.getSimpleName();
	private View spvBts_EquipView;
	/**
	 * textview
	 */
	private TextView supervision_bts_tv_matram;
	private TextView supervision_bts_tv_mact;

	/**
	 * edittext
	 */
	private EditText supervision_bts_equip_et_diengiai_sotu_2g;
	private EditText supervision_bts_equip_et_diengiai_sotu_3g;

	private TextView supervision_bts_equip_tl_thongtinthietbi_row1_sotu_2g;
	private TextView supervision_bts_equip_tl_thongtinthietbi_row2_sotu_3g;
	//

	// combobox loai thong tin
	private TextView supervision_bts_equip_tv_thietke;

	// combobox danh gia chat luong cot
	private Spinner supervision_bts_equip_sp_hangsx2g;
	private Spinner supervision_bts_equip_sp_hangsx3g;
	private Spinner supervision_bts_equip_sp_trans_hangsx;

	// combobox chon loai tu 2g
	private TextView supervision_bts_equip_tv_2g_type;
	private TextView rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_2g;

	// combobox chon loai tu 3g
	private TextView supervision_bts_equip_tv_3g_type;
	private TextView rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_3g;

	// combobox chon loai thiet bi
	private TextView supervision_bts_equip_tv_equip_type;

	// combobox chon tan so
	private TextView supervision_bts_equip_tv_equip_tanso;

	// combobox chon tan so
	private TextView supervision_bts_equip_tv_equip_kichthuoctrong;

	private ListView listviewTiepdat;
	private ListView listviewNgoaitroi;
	private ListView listviewTrongnha;

	// button save
	private Button save;

	private int isInfomation;
	private int isType2g = -1;
	private int isType3g = -1;
	private int isEquipType = -1;
	private int isTanso = -1;
	private float isKtTrong = -1;

	private Menu_DropdownPopup dropdownPopupMenuInfomation;
	private Menu_DropdownPopup dropdownPopupMenu2gType;
	private Menu_DropdownPopup dropdownPopupMenu3gType;
	private Menu_DropdownPopup dropdownPopupMenuEquipType;
	private Menu_DropdownPopup dropdownPopupMenuTanso;
	private Menu_DropdownPopup dropdownPopupMenuKtTrong;

//	private Image_ViewGalleryPopup imgViewPopup;
	private Contruction_UnqualifyPopup contructionUnqualifyPopup;
	private Edit_Text_Popup editTextPopup;

	private List<DropdownItemEntity> listInfomation = null;
	private List<DropdownItemEntity> list2gType = null;
	private List<DropdownItemEntity> list3gType = null;
	private List<DropdownItemEntity> listEquipType = null;
	private List<DropdownItemEntity> listTanso = null;
	private List<DropdownItemEntity> listKtTrong = null;

	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Cause_Bts_Cat_WorkEntity curEditItem = null;
	private List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyCatWorkItem;

	private Constr_Construction_EmployeeEntity constr_ConstructionItem;

	private List<Cause_Bts_Cat_WorkEntity> listDataTiepdat;
	private List<Cause_Bts_Cat_WorkEntity> listDataNgoaitroi;
	private List<Cause_Bts_Cat_WorkEntity> listDataTrongnha;

	private List<Cat_Supervision_Constr_WorkEntity> listConstrWorkTiepdat;
	private List<Cat_Supervision_Constr_WorkEntity> listConstrWorkNgoaitroi;
	private List<Cat_Supervision_Constr_WorkEntity> listConstrWorkTrongnha;

	private Supervision_BtsEntity btsEntity;
	private Bts_EquipAdapter bts_EquipTiepdatAdapter;
	private Bts_EquipAdapter bts_EquipNgoaitroiAdapter;
	private Bts_EquipAdapter bts_EquipTrongnhaAdapter;

	private Supv_Constr_Attach_FileController supvConstrAttachFileController;

	private Supervision_Bts_EquipEntity btsEquipEntity;
	private List<Product_CompanyEntity> listProductCompany23G;
	private List<Product_CompanyEntity> listProductCompanyTrans;
	private int tabHeight = 40;
	private long bts_2g_company_id;
	private long bts_3g_company_id;
	private long trans_company_id;
	private String sChoice;
	private TabHost tabs;
	private ConfirmDialog confirmSave;
	private Supervision_Bts_EquipController btsEquipController;
	private boolean outOfKey = false;

	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private List<Supervision_LBG_UnqualifyItemEntity> listEquipAcceptanceItem;

	private TextView supervision_bts_equip_td_complete_date;
	private TextView supervision_bts_equip_tn_complete_date;
	private TextView supervision_bts_equip_nt_complete_date;

	private Button rl_supervision_bts_equip_complete;

	// private int tabWidth = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.supervision_bts_equip_activity);
		setTitle(getString(R.string.supervision_bts_title));
		initComponent();
		// setHeader();
		isInfomation = getIntent().getExtras().getInt(
				IntentConstants.INTENT_DESIGNINFO);

		// JSONArray jsonScDataTest = SqlliteSyncModel
		// .getDataJsonSyncTest(Cat_Supervision_Constr_WorkField.TABLE_NAME,
		// Cat_Supervision_Constr_WorkController.allColumn, null, 0,
		// 1000);

		initData();
		//
		constr_ConstructionItem = getConstr_Construction_Employee();
		setData();
		check4G();

		closeProgressDialog();
		//
		// setData();
		// new loadMore().execute();
	}

	public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
		return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
				.getSerializable(IntentConstants.INTENT_DATA);
	}

	public void check4G() {
		if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
			supervision_bts_equip_tl_thongtinthietbi_row1_sotu_2g
					.setText(getString(R.string.supervision_bts_equip_tl_thongtinthietbi_row1_sotu_2g));
			supervision_bts_equip_tl_thongtinthietbi_row2_sotu_3g
					.setText(getString(R.string.supervision_bts_equip_tl_thongtinthietbi_row2_sotu_3g));
			rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_2g
					.setText(getString(R.string.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_2g));
			rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_3g
					.setText(getString(R.string.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_3g));
		} else {
			supervision_bts_equip_tl_thongtinthietbi_row1_sotu_2g
					.setText(getString(R.string.supervision_bts_equip_tl_thongtinthietbi_row1_sotu_2g)
							+ "(*)");
			supervision_bts_equip_tl_thongtinthietbi_row2_sotu_3g
					.setText(getString(R.string.supervision_bts_equip_tl_thongtinthietbi_row2_sotu_3g)
							+ "(*)");
			rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_2g
					.setText(getString(R.string.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_2g)
							+ "(*)");
			rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_3g
					.setText(getString(R.string.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_3g)
							+ "(*)");

		}
	}

	public void initComponent() {
		spvBts_EquipView = addView(R.layout.supervision_bts_equip_activity,
				R.id.rl_spv_bts_equip);
		// textview
		supervision_bts_tv_matram = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tv_matram);
		supervision_bts_tv_mact = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tv_mact);

		supervision_bts_equip_tv_thietke = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tv_thietke);
		supervision_bts_equip_tv_thietke.setOnClickListener(this);

		String thietbi = StringUtil
				.getString(R.string.trl_supervision_bts_equip_thongtin_thietbi);
		tabs = (TabHost) spvBts_EquipView
				.findViewById(R.id.supervision_bts_equip_tabhost);
		tabs.setup();
		// tab thong tin thiet bi
		TabHost.TabSpec specInfomationEquip = tabs.newTabSpec(thietbi);
		specInfomationEquip
				.setContent(R.id.supervision_bts_equip_tl_thongtinthietbi);
		specInfomationEquip.setIndicator(makeTabIndicator(thietbi));

		// tab giam sat phan tiep dat
		String supervisionTiepdat = StringUtil
				.getString(R.string.trl_supervision_bts_equip_giamsat_tiepdat);
		TabHost.TabSpec specSupervisionTiepdat = tabs
				.newTabSpec(supervisionTiepdat);
		specSupervisionTiepdat.setContent(R.id.supervision_bts_equip_tab2);
		specSupervisionTiepdat
				.setIndicator(makeTabIndicator(supervisionTiepdat));

		// tab giam sat phan ngoai troi
		String supervisionNgoaitroi = StringUtil
				.getString(R.string.trl_supervision_bts_equip_giamsat_trongnha);
		TabHost.TabSpec specSupervisionNgoaitroi = tabs
				.newTabSpec(supervisionNgoaitroi);
		specSupervisionNgoaitroi.setContent(R.id.supervision_bts_equip_tab3);
		specSupervisionNgoaitroi
				.setIndicator(makeTabIndicator(supervisionNgoaitroi));

		// tab giam sat phan trong nha
		String supervisionTrongnha = StringUtil
				.getString(R.string.trl_supervision_bts_equip_giamsat_ngoaitroi);
		TabHost.TabSpec specSupervisionTrongnha = tabs
				.newTabSpec(supervisionTrongnha);
		specSupervisionTrongnha.setContent(R.id.supervision_bts_equip_tab4);
		specSupervisionTrongnha
				.setIndicator(makeTabIndicator(supervisionTrongnha));

		tabs.addTab(specInfomationEquip);
		tabs.addTab(specSupervisionTiepdat);
		tabs.addTab(specSupervisionNgoaitroi);
		tabs.addTab(specSupervisionTrongnha);

		// hang san xuat thong tin thiet bi Bts 2g
		supervision_bts_equip_sp_hangsx2g = (Spinner) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row1_tv_chonhangsx2g);

		supervision_bts_equip_sp_hangsx3g = (Spinner) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row1_tv_chonhangsx3g);

		// hang san xuat thong tin thiet bi truyen dan
		supervision_bts_equip_sp_trans_hangsx = (Spinner) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_truyendan_row1_tv_chonhangsx);

		// combobox loai tu 2g

		rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_2g = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_2g);
		supervision_bts_equip_tv_2g_type = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_chonloaitu2G);
		supervision_bts_equip_tv_2g_type.setOnClickListener(this);

		// combobox loai tu 3g
		rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_3g = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_loaitu_3g);
		supervision_bts_equip_tv_3g_type = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_chonloaitu3G);
		supervision_bts_equip_tv_3g_type.setOnClickListener(this);

		// combobox loai thiet bi
		supervision_bts_equip_tv_equip_type = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_truyendan_row1_tv_chonloaithietbi);
		supervision_bts_equip_tv_equip_type.setOnClickListener(this);

		// combobox chon tan so
		supervision_bts_equip_tv_equip_tanso = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_truyendan_row2_tv_chontanso);
		supervision_bts_equip_tv_equip_tanso.setOnClickListener(this);

		// combobox chon kich thuoc trong
		supervision_bts_equip_tv_equip_kichthuoctrong = (TextView) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_truyendan_row2_tv_chonkichthuoctrong);
		supervision_bts_equip_tv_equip_kichthuoctrong.setOnClickListener(this);

		// edit text
		supervision_bts_equip_tl_thongtinthietbi_row1_sotu_2g = (TextView) spvBts_EquipView
				.findViewById(R.id.supervision_bts_equip_tl_thongtinthietbi_row1_sotu_2g);
		supervision_bts_equip_tl_thongtinthietbi_row2_sotu_3g = (TextView) spvBts_EquipView
				.findViewById(R.id.supervision_bts_equip_tl_thongtinthietbi_row2_sotu_3g);
		supervision_bts_equip_et_diengiai_sotu_2g = (EditText) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row1_et_sotu_2g);
		supervision_bts_equip_et_diengiai_sotu_3g = (EditText) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row2_et_sotu_3g);
		//

		// button save
		save = (Button) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_bt_save);
		save.setOnClickListener(this);

		rl_supervision_bts_equip_complete = (Button) spvBts_EquipView
				.findViewById(R.id.rl_supervision_bts_equip_complete);
		rl_supervision_bts_equip_complete.setOnClickListener(this);

		supervision_bts_equip_td_complete_date = (TextView) spvBts_EquipView
				.findViewById(R.id.supervision_bts_equip_td_complete_date);
		supervision_bts_equip_tn_complete_date = (TextView) spvBts_EquipView
				.findViewById(R.id.supervision_bts_equip_tn_complete_date);
		supervision_bts_equip_nt_complete_date = (TextView) spvBts_EquipView
				.findViewById(R.id.supervision_bts_equip_nt_complete_date);

		listviewTiepdat = (ListView) spvBts_EquipView
				.findViewById(R.id.supervision_bts_equip_thicong_tiepdat);

		listviewNgoaitroi = (ListView) spvBts_EquipView
				.findViewById(R.id.supervision_bts_equip_thicong_ngoaitroi);

		listviewTrongnha = (ListView) spvBts_EquipView
				.findViewById(R.id.supervision_bts_equip_thicong_trongnha);
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
		listInfomation = GloablUtils.getListBTSInfo("");
		sChoice = getResources().getString(R.string.text_choice1);
		supervision_bts_equip_tv_thietke.setText(GloablUtils
				.getStringBTSInfo(isInfomation));

		list2gType = GloablUtils.getListBts2gType(sChoice);
		supervision_bts_equip_tv_2g_type.setText(sChoice);

		list3gType = GloablUtils.getListBts3gType(sChoice);
		supervision_bts_equip_tv_3g_type.setText(sChoice);

		// loai thiet bi
		listEquipType = GloablUtils.getListBtsEquipType(sChoice);
		supervision_bts_equip_tv_equip_type.setText(sChoice);

		// tan so
		listTanso = GloablUtils.getListBtsTanSo(sChoice);
		supervision_bts_equip_tv_equip_tanso.setText(sChoice);

		// kich thuoc trong
		listKtTrong = GloablUtils.getListBtsKtTrong(sChoice);
		supervision_bts_equip_tv_equip_kichthuoctrong.setText(sChoice);

	}

	public void setData() {

		Product_CompanyController companyEntity = new Product_CompanyController(
				this);
		// lay ra danh sach nha san xuat 2g, 3g
		listProductCompany23G = companyEntity.getListProductCompanyEntity(1);

		// lay ra danh sach nha san xuat thiet bi truyen dan
		listProductCompanyTrans = companyEntity.getListProductCompanyEntity(2);

		supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
				this);

		final Bts_CompanyAdapter companyAdapter23G = new Bts_CompanyAdapter(
				getApplicationContext(), android.R.layout.simple_spinner_item,
				listProductCompany23G);

		final Bts_CompanyAdapter companyAdapterTrans = new Bts_CompanyAdapter(
				getApplicationContext(), android.R.layout.simple_spinner_item,
				listProductCompanyTrans);

		// set nha san xuat vao spiner
		supervision_bts_equip_sp_hangsx2g.setAdapter(companyAdapter23G);

		supervision_bts_equip_sp_hangsx3g.setAdapter(companyAdapter23G);

		supervision_bts_equip_sp_trans_hangsx.setAdapter(companyAdapterTrans);

		supervision_bts_equip_sp_hangsx2g
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View v, int position, long id) {

						CheckedTextView itemSelect = (CheckedTextView) v
								.findViewById(R.id.product_company_ctv);

						Product_CompanyEntity productItem = (Product_CompanyEntity) supervision_bts_equip_sp_hangsx2g
								.getSelectedItem();

						bts_2g_company_id = productItem.getCompanyId();
						itemSelect.setText(productItem.getName());
						itemSelect.setCheckMarkDrawable(null);

					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {

					}

				});

		supervision_bts_equip_sp_hangsx3g
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View v, int position, long id) {

						CheckedTextView itemSelect = (CheckedTextView) v
								.findViewById(R.id.product_company_ctv);

						Product_CompanyEntity productItem = (Product_CompanyEntity) supervision_bts_equip_sp_hangsx3g
								.getSelectedItem();

						bts_3g_company_id = productItem.getCompanyId();
						itemSelect.setText(productItem.getName());
						itemSelect.setCheckMarkDrawable(null);

					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {

					}

				});

		supervision_bts_equip_sp_trans_hangsx
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View v, int position, long id) {

						CheckedTextView itemSelect = (CheckedTextView) v
								.findViewById(R.id.product_company_ctv);

						Product_CompanyEntity productItem = (Product_CompanyEntity) supervision_bts_equip_sp_trans_hangsx
								.getSelectedItem();
						trans_company_id = productItem.getCompanyId();

						itemSelect.setText(productItem.getName());
						itemSelect.setCheckMarkDrawable(null);

					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {

					}

				});

		supervision_bts_tv_matram.setText(constr_ConstructionItem
				.getStationCode());
		supervision_bts_tv_mact.setText(String.valueOf(constr_ConstructionItem
				.getConstrCode()));

		Supervision_BtsController bts_Controller = new Supervision_BtsController(
				this);

		btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
				.getSupervision_Constr_Id());

		btsEquipController = new Supervision_Bts_EquipController(this);
		btsEquipEntity = btsEquipController
				.getSupervision_Bts_EquipEntity(btsEntity
						.getSupervision_Bts_Id());

		if (btsEquipEntity != null) {
			if (btsEquipEntity.get_2g_TOTAL() == -1l) {
				supervision_bts_equip_et_diengiai_sotu_2g.setText("");
			} else {
				supervision_bts_equip_et_diengiai_sotu_2g.setText(StringUtil
						.formatNumber(btsEquipEntity.get_2g_TOTAL()));
			}

			if (btsEquipEntity.get_3g_TOTAL() == -1l) {
				supervision_bts_equip_et_diengiai_sotu_3g.setText("");
			} else {
				supervision_bts_equip_et_diengiai_sotu_3g.setText(StringUtil
						.formatNumber(btsEquipEntity.get_3g_TOTAL()));
			}

			for (int i = 0; i < listProductCompany23G.size(); i++) {
				if (listProductCompany23G.get(i).getCompanyId() == btsEquipEntity
						.getBts_2g_Company_Id()) {
					supervision_bts_equip_sp_hangsx2g.setSelection(i);
					break;
				}
			}
			for (int i = 0; i < listProductCompany23G.size(); i++) {
				if (listProductCompany23G.get(i).getCompanyId() == btsEquipEntity
						.getBts_3g_Company_Id()) {
					supervision_bts_equip_sp_hangsx3g.setSelection(i);
					break;
				}
			}
			isType2g = btsEquipEntity.get_2g_TYPE();
			supervision_bts_equip_tv_2g_type.setText(GloablUtils
					.getStringBts2gType(btsEquipEntity.get_2g_TYPE()));

			isType3g = btsEquipEntity.get_3g_TYPE();
			supervision_bts_equip_tv_3g_type.setText(GloablUtils
					.getStringBts3gType(btsEquipEntity.get_3g_TYPE()));

			isEquipType = btsEquipEntity.getTrans_Type();
			supervision_bts_equip_tv_equip_type.setText(GloablUtils
					.getStringBtsEquipType(isEquipType));

			for (int i = 0; i < listProductCompanyTrans.size(); i++) {
				if (listProductCompanyTrans.get(i).getCompanyId() == btsEquipEntity
						.getTrans_Company_Id()) {
					supervision_bts_equip_sp_trans_hangsx.setSelection(i);
					break;
				}
			}

			isTanso = btsEquipEntity.getTrans_freq();

			if (isTanso < 0) {
				supervision_bts_equip_tv_equip_tanso.setText(sChoice);
			} else
				supervision_bts_equip_tv_equip_tanso.setText(GloablUtils
						.getStringBtsTanSo(isTanso));

			isKtTrong = btsEquipEntity.getTrans_viba_dimension();

			if (isKtTrong <= 0) {
				supervision_bts_equip_tv_equip_kichthuoctrong.setText(sChoice);
			} else
				supervision_bts_equip_tv_equip_kichthuoctrong
						.setText(GloablUtils.getStringBtsKtTrong(isKtTrong));
		}

		listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
				this).getAllUnQualifyItemByName(
				Constants.ACCEPTANCE_TYPE.BTS_THIET_BI,
				Constants.UNQUALIFY_TYPE.BTS_TYPE);

		setListTiepdatData();
		setListNgoaitroiData();
		setListTrongnhaData();

		refreshView();

		showCompleteDate(constr_ConstructionItem,
				Constants.PROGRESS_TYPE.BTS_TYPE,
				Constants.PROGRESS_TYPE.LAP_DAT_THIET_BI,
				supervision_bts_equip_td_complete_date,
				rl_supervision_bts_equip_complete);

		showCompleteDate(constr_ConstructionItem,
				Constants.PROGRESS_TYPE.BTS_TYPE,
				Constants.PROGRESS_TYPE.LAP_DAT_THIET_BI,
				supervision_bts_equip_tn_complete_date,
				rl_supervision_bts_equip_complete);

		showCompleteDate(constr_ConstructionItem,
				Constants.PROGRESS_TYPE.BTS_TYPE,
				Constants.PROGRESS_TYPE.LAP_DAT_THIET_BI,
				supervision_bts_equip_nt_complete_date,
				rl_supervision_bts_equip_complete);

		/* Set endable va disable voi cong trinh da hoan thanh */
		if (constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			// this.rl_supervision_bts_equip_complete.setVisibility(View.GONE);
			this.save.setVisibility(View.GONE);
		}
	}

	public void refreshView() {
		// btsEquipController = new Supervision_Bts_EquipController(this);
		btsEquipEntity = btsEquipController
				.getSupervision_Bts_EquipEntity(btsEntity
						.getSupervision_Bts_Id());

		if (btsEquipEntity != null) {
			if (btsEquipEntity.get_2g_TOTAL() == -1l) {
				supervision_bts_equip_et_diengiai_sotu_2g.setText("");
			} else {
				supervision_bts_equip_et_diengiai_sotu_2g.setText(StringUtil
						.formatNumber(btsEquipEntity.get_2g_TOTAL()));
			}

			if (btsEquipEntity.get_3g_TOTAL() == -1l) {
				supervision_bts_equip_et_diengiai_sotu_3g.setText("");
			} else {
				supervision_bts_equip_et_diengiai_sotu_3g.setText(StringUtil
						.formatNumber(btsEquipEntity.get_3g_TOTAL()));
			}
		}

		// phan tiep dat
		listDataTiepdat = new ArrayList<Cause_Bts_Cat_WorkEntity>();

		getDataTiepdatFromDatabase();

		
//		 == ban đầu chưa sửa
//		if (listDataTiepdat.size() != 0) {
//			// truong hop da co du lieu
//			for (Cause_Bts_Cat_WorkEntity curItem : this.listDataTiepdat) {
//				curItem.setNew(false);
//
//				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = curItem
//						.getListCauseUniQualify();
//
//				/* Gan anh nghiem thu */
//				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curItem
//						.getListAcceptance()) {
//					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
//							.getLstAttachFile(
//									Acceptance_Bts_Cat_WorkField.TABLE_NAME,
//									curUnqualifyItem.getCause_Line_Bg_Id());
//					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
//						curUnqualifyItem.getLstAttachFile().add(itemFile);
//					}
//				}
//
//				/* Gan anh nguyen nhan loi */
//				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
//
//					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
//							.getLstAttachFile(
//									Cause_Bts_Cat_WorkField.TABLE_NAME,
//									curUnqualifyItem.getCause_Line_Bg_Id());
//					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
//						curUnqualifyItem.getLstAttachFile().add(itemFile);
//					}
//				}
//				curItem.setListCauseUniQualify(listUnqualifyItem);
//			}
//		} else
//			for (int i = 0; i < listConstrWorkTiepdat.size(); i++) {
//				Cause_Bts_Cat_WorkEntity catWorkEntity = new Cause_Bts_Cat_WorkEntity();
//				catWorkEntity.setStt(i + 1);
//				catWorkEntity.setTenHangMuc(listConstrWorkTiepdat.get(i)
//						.getWork_Name());
//				catWorkEntity.setWorkCode(listConstrWorkTiepdat.get(i)
//						.getWork_Code());
//				catWorkEntity.getBts_Cat_WorkEntity()
//						.setCat_Supervision_Constr_Work_Id(
//								listConstrWorkTiepdat.get(i)
//										.getCat_Supervision_Constr_Work_Id());
//				catWorkEntity.getBts_Cat_WorkEntity().setSupervision_Bts_Id(
//						btsEntity.getSupervision_Bts_Id());
//				catWorkEntity.setNew(true);
//				catWorkEntity.setEdited(false);
//				listDataTiepdat.add(catWorkEntity);
//			}
			//======= sửa mới
		// danh sách các nguyên nhân/ lý do của hạng mục tiếp đất đã được lưu từ trước
		for (int i = 0; i < listDataTiepdat.size(); i++) {
			// nếu hạn mục đã được đánh giá
			if (listDataTiepdat.get(i).getBts_Cat_WorkEntity().getStatus()>=0) {
				
				listDataTiepdat.get(i).setNew(false);

				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = listDataTiepdat.get(i)
						.getListCauseUniQualify();

				/* Gan anh nghiem thu */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listDataTiepdat.get(i)
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
				listDataTiepdat.get(i).setListCauseUniQualify(listUnqualifyItem);
			
			}else{
				// hạng mục chưa được đánh giá
				listDataTiepdat.get(i).setStt(i + 1);
				listDataTiepdat.get(i).setTenHangMuc(listConstrWorkTiepdat.get(i)
						.getWork_Name());
				listDataTiepdat.get(i).setWorkCode(listConstrWorkTiepdat.get(i)
						.getWork_Code());
				listDataTiepdat.get(i).getBts_Cat_WorkEntity()
						.setCat_Supervision_Constr_Work_Id(
								listConstrWorkTiepdat.get(i)
										.getCat_Supervision_Constr_Work_Id());
				listDataTiepdat.get(i).getBts_Cat_WorkEntity().setSupervision_Bts_Id(
						btsEntity.getSupervision_Bts_Id());
				listDataTiepdat.get(i).setNew(true);
				listDataTiepdat.get(i).setEdited(false);
			}
		}
		
		bts_EquipTiepdatAdapter = new Bts_EquipAdapter(this, listDataTiepdat);

		// Phan ngoai troi
		listDataNgoaitroi = new ArrayList<Cause_Bts_Cat_WorkEntity>();

		getDataNgoaitroiFromDatabase();
		// ==== chưa sửa 
//		if (listDataNgoaitroi.size() != 0) {
//			// truong hop da co du lieu
//			for (Cause_Bts_Cat_WorkEntity curItem : this.listDataNgoaitroi) {
//				curItem.setNew(false);
//
//				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = curItem
//						.getListCauseUniQualify();
//
//				/* Gan anh nghiem thu */
//				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : curItem
//						.getListAcceptance()) {
//					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
//							.getLstAttachFile(
//									Acceptance_Bts_Cat_WorkField.TABLE_NAME,
//									curUnqualifyItem.getCause_Line_Bg_Id());
//					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
//						curUnqualifyItem.getLstAttachFile().add(itemFile);
//					}
//
//				}
//
//				/* Gan anh nguyen nhan loi */
//				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
//
//					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
//							.getLstAttachFile(
//									Cause_Bts_Cat_WorkField.TABLE_NAME,
//									curUnqualifyItem.getCause_Line_Bg_Id());
//					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
//						curUnqualifyItem.getLstAttachFile().add(itemFile);
//					}
//				}
//				curItem.setListCauseUniQualify(listUnqualifyItem);
//			}
//		} else
//			for (int i = 0; i < listConstrWorkNgoaitroi.size(); i++) {
//				Cause_Bts_Cat_WorkEntity catWorkEntity = new Cause_Bts_Cat_WorkEntity();
//				catWorkEntity.setStt(i + 1);
//				catWorkEntity.setTenHangMuc(listConstrWorkNgoaitroi.get(i)
//						.getWork_Name());
//				catWorkEntity.setWorkCode(listConstrWorkNgoaitroi.get(i)
//						.getWork_Code());
//				catWorkEntity.getBts_Cat_WorkEntity()
//						.setCat_Supervision_Constr_Work_Id(
//								listConstrWorkNgoaitroi.get(i)
//										.getCat_Supervision_Constr_Work_Id());
//				catWorkEntity.getBts_Cat_WorkEntity().setSupervision_Bts_Id(
//						btsEntity.getSupervision_Bts_Id());
//				catWorkEntity.setNew(true);
//				catWorkEntity.setEdited(false);
//				listDataNgoaitroi.add(catWorkEntity);
//			}
		//====
		
		// luannt26 
		// danh sách các nguyên nhân/ lý do của hạng mục tiếp đất đã được lưu từ trước
		for (int i = 0; i < listDataNgoaitroi.size(); i++) {
			// nếu hạng mục đã được đánh giá
			if (listDataNgoaitroi.get(i).getBts_Cat_WorkEntity().getStatus()>=0) {

				listDataNgoaitroi.get(i).setNew(false);

				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = listDataNgoaitroi.get(i)
						.getListCauseUniQualify();

				/* Gan anh nghiem thu */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listDataNgoaitroi.get(i)
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
				listDataNgoaitroi.get(i).setListCauseUniQualify(listUnqualifyItem);
			
			}else{
				// hạng mục chưa được đánh giá
				listDataNgoaitroi.get(i).setStt(i + 1);
				listDataNgoaitroi.get(i).setTenHangMuc(listConstrWorkNgoaitroi.get(i)
						.getWork_Name());
				listDataNgoaitroi.get(i).setWorkCode(listConstrWorkNgoaitroi.get(i)
						.getWork_Code());
				listDataNgoaitroi.get(i).getBts_Cat_WorkEntity()
						.setCat_Supervision_Constr_Work_Id(
								listConstrWorkNgoaitroi.get(i)
										.getCat_Supervision_Constr_Work_Id());
				listDataNgoaitroi.get(i).getBts_Cat_WorkEntity().setSupervision_Bts_Id(
						btsEntity.getSupervision_Bts_Id());
				listDataNgoaitroi.get(i).setNew(true);
				listDataNgoaitroi.get(i).setEdited(false);
			}
		}

		bts_EquipNgoaitroiAdapter = new Bts_EquipAdapter(this,
				listDataNgoaitroi);

		// Phan trong nha
		listDataTrongnha = new ArrayList<Cause_Bts_Cat_WorkEntity>();
		getDataTrongnhaFromDatabase();

		if (listDataTrongnha.size() != 0) {

			// truong hop da co du lieu
			// for (Cause_Bts_Cat_WorkEntity curItem : this.listDataTrongnha) {
			for (int i = 0; i < listDataTrongnha.size(); i++) {
				Cause_Bts_Cat_WorkEntity curItem = listDataTrongnha.get(i);
				if (!curItem.getBts_Cat_WorkEntity().getFail_Desc().equals("")
						|| curItem.getBts_Cat_WorkEntity().getStatus() >= 0) {
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
				} else {
					curItem.setStt(i + 1);
					curItem.setTenHangMuc(listConstrWorkTrongnha.get(i)
							.getWork_Name());
					curItem.setWorkCode(listConstrWorkTrongnha.get(i)
							.getWork_Code());
					curItem.getBts_Cat_WorkEntity()
							.setCat_Supervision_Constr_Work_Id(
									listConstrWorkTrongnha
											.get(i)
											.getCat_Supervision_Constr_Work_Id());
					curItem.getBts_Cat_WorkEntity().setSupervision_Bts_Id(
							btsEntity.getSupervision_Bts_Id());
					curItem.setNew(true);
					curItem.setEdited(false);
				}
			}

		}

		bts_EquipTrongnhaAdapter = new Bts_EquipAdapter(this, listDataTrongnha);

		listviewTiepdat.setAdapter(bts_EquipTiepdatAdapter);
		listviewNgoaitroi.setAdapter(bts_EquipNgoaitroiAdapter);
		listviewTrongnha.setAdapter(bts_EquipTrongnhaAdapter);
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
			// đã chọn nguyên nhân rồi nhưng chưa chọn trang thái đạt hay không
			//---
			if (countNnkdCheck > 0
					&& itemCheck.getBts_Cat_WorkEntity().getStatus() < 0) {
				sReslut = StringUtil
						.getString(R.string.bts_viba_nn_access_tempty);
				sReslut += itemCheck.getTenHangMuc();
			} else if ((itemCheck.getBts_Cat_WorkEntity().getStatus() >= 0)) {
				// chon không đạt mà chưa chọn nguyên nhân
				if (itemCheck.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT
						&& countNnkdCheck < 1) {
					sReslut = StringUtil
							.getString(R.string.bts_viba_nn_khongdat_tempty);
					sReslut += itemCheck.getTenHangMuc();

				}
				// chưa chọn trạng thái đạt hay không và chưa nhập điễn giải
			} 
			// --- yêu cầu phải chọn hết các hạng mục mới cho lưu
//			else if (itemCheck.getBts_Cat_WorkEntity().getStatus() < 0
//					&& itemCheck.getBts_Cat_WorkEntity().getFail_Desc()
//							.equals("")) {
//				sReslut = StringUtil.getString(R.string.bts_viba_danhgia_empty);
//				sReslut += itemCheck.getTenHangMuc();
////				sReslut += ". ";
////				sReslut += StringUtil
////						.getString(R.string.bts_viba_nn_diengiai_tempty);
//			}
			//-----
			
			// kiểm tra nếu chọn không đạt mà nguyên nhân phải chụp ảnh
			if (itemCheck.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {
				for (int i = 0; i < itemCheck.getListCauseUniQualify().size(); i++) {
					// nếu lý do đang được chọn
					if (itemCheck.getListCauseUniQualify().get(i).isUnqulify()
							&& !itemCheck.getListCauseUniQualify().get(i)
									.isDelete()) {
						// nếu IsSerious = 1
						if (itemCheck.getListCauseUniQualify().get(i)
								.getIsSerious() == 1
								&& (itemCheck.getListCauseUniQualify().get(i)
										.getLstAttachFile().size() == 0 && itemCheck
										.getListCauseUniQualify().get(i)
										.getLstNewAttachFile().size() == 0)) {
							sReslut = StringUtil
									.getString(R.string.constr_take_unqualify_not_enough)
									+ itemCheck.getListCauseUniQualify().get(i)
											.getTitle();
							sReslut += ". "
									+ StringUtil
											.getString(R.string.item_measure);

							sReslut += ": " + itemCheck.getTenHangMuc();
							break;
						}

					}
				}
			}

			// =========== kiểm tra các mục chọn đạt
			int countAcceptCheck = 0;

			// kiểm tra danh sách nghiện thu nếu đạt
			for (int i = 0; i < itemCheck.getListAcceptance().size(); i++) {
				if ((itemCheck.getListAcceptance().get(i).getLstNewAttachFile()
						.size() > 0 || itemCheck.getListAcceptance().get(i)
						.getLstAttachFile().size() > 0)
						&& (i == 0)) {
					countAcceptCheck++;
				}
			}
			// kiểm tra phải chụp ảnh với các mục có IS_Mandatory =1 
			if (itemCheck.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT) {
				if(itemCheck.getListAcceptance().size() > 0){
					for (int i = 0; i < itemCheck.getListAcceptance().size(); i++) {
						if (itemCheck.getListAcceptance().get(i).getIs_Mandory()==1&&(itemCheck.getListAcceptance().get(i).getLstAttachFile().size()==0&&itemCheck.getListAcceptance().get(i).getLstNewAttachFile().size()==0)) {
							sReslut = StringUtil
									.getString(R.string.constr_take_acceptance_not_enough)
									+": "+ itemCheck.getListAcceptance().get(i)
											.getTitle();
							sReslut += ". "
									+ StringUtil
											.getString(R.string.item_measure);

							sReslut += ": " + itemCheck.getTenHangMuc();
							break;
						}
					}
				}else{
					sReslut = StringUtil
							.getString(R.string.constr_take_acceptance_not_enough);
					sReslut += ". "
							+ StringUtil
									.getString(R.string.item_measure);

					sReslut += ": " + itemCheck.getTenHangMuc();
				}
				
			}
			//---
			//--- yêu câu phải chụp hết ảnh mới cho lưu
//			if (itemCheck.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT
//					&& countAcceptCheck < 1) {
//				sReslut = StringUtil
//						.getString(R.string.constr_take_acceptance_not_enough);
//			}
			//---
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	public void clearFocus() {
		supervision_bts_equip_et_diengiai_sotu_2g
				.setFocusableInTouchMode(false);
		supervision_bts_equip_et_diengiai_sotu_2g.clearFocus();

		supervision_bts_equip_et_diengiai_sotu_3g
				.setFocusableInTouchMode(false);
		supervision_bts_equip_et_diengiai_sotu_3g.clearFocus();

		supervision_bts_equip_et_diengiai_sotu_2g.setFocusableInTouchMode(true);
		supervision_bts_equip_et_diengiai_sotu_3g.setFocusableInTouchMode(true);
	}

	public String checkValidateEquip() {
		String sReslut = "";
		try {
			if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_4G) {
				if (StringUtil
						.isNullOrEmpty(supervision_bts_equip_et_diengiai_sotu_2g
								.getText().toString())) {

					sReslut = StringUtil.getString(R.string.bts_equip_2g_null);
					tabs.setCurrentTab(0);
					supervision_bts_equip_et_diengiai_sotu_2g.setError(Html
							.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					supervision_bts_equip_et_diengiai_sotu_2g.requestFocus();
					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();
				} else if (StringUtil
						.isNullOrEmpty(supervision_bts_equip_et_diengiai_sotu_3g
								.getText().toString())) {
					sReslut = StringUtil.getString(R.string.bts_equip_3g_null);

					tabs.setCurrentTab(0);
					supervision_bts_equip_et_diengiai_sotu_3g.setError(Html
							.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					supervision_bts_equip_et_diengiai_sotu_3g.requestFocus();
					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();
				} else if (isType2g < 0) {

					sReslut = StringUtil
							.getString(R.string.bts_equip_2gtype_null);
					tabs.setCurrentTab(0);
					supervision_bts_equip_tv_2g_type.setError(Html
							.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();

					clearFocus();

				} else if (isType3g < 0) {

					sReslut = StringUtil
							.getString(R.string.bts_equip_3gtype_null);
					tabs.setCurrentTab(0);
					supervision_bts_equip_tv_3g_type.setError(Html
							.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					Toast.makeText(getApplicationContext(), sReslut,
							Toast.LENGTH_LONG).show();

					clearFocus();

				}
			}
			
			if (isEquipType < 0) {
				tabs.setCurrentTab(0);
				sReslut = StringUtil
						.getString(R.string.bts_equip_equip_type_null);

				supervision_bts_equip_tv_equip_type
						.setError(Html
								.fromHtml("<font color='green'>"
										+ getString(R.string.field_is_null)
										+ "</font>"));
				Toast.makeText(getApplicationContext(), sReslut,
						Toast.LENGTH_LONG).show();

				clearFocus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	public void getDataTiepdatFromDatabase() {
		Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
				this);
		for (int i = 0; i < listConstrWorkTiepdat.size(); i++) {
			Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

			String workcode = listConstrWorkTiepdat.get(i).getWork_Code();

			String workcodess = Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK;
			Log.d("workcodess", workcodess);
			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK
							.toString())) {
				item = causeCatWorkController
						.getCause_Bts_Cat_WorkEntity(
								Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK,
								btsEntity.getSupervision_Bts_Id());

			}
			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER
							.toString())) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER,
						btsEntity.getSupervision_Bts_Id());
			}

			item.setWorkCode(workcode);
			item.setStt(i + 1);
			item.setTenHangMuc(listConstrWorkTiepdat.get(i).getWork_Name());
//			// nếu hạng mục đã được đánh giá	
//			if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
//					|| item.getBts_Cat_WorkEntity().getStatus() >= 0) {
				listDataTiepdat.add(item);
//			}
		}
	}

	public void getDataNgoaitroiFromDatabase() {
		Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
				this);
		for (int i = 0; i < listConstrWorkNgoaitroi.size(); i++) {
			Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

			String workcode = listConstrWorkNgoaitroi.get(i).getWork_Code();

			if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_THANG_CAP)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_THANG_CAP,
						btsEntity.getSupervision_Bts_Id());
			}
			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_HT_DEN_DIEUHOA_THONGGIO)) {
				item = causeCatWorkController
						.getCause_Bts_Cat_WorkEntity(
								Constants.BTS_CONSTR_WORK.WORK_CODE_HT_DEN_DIEUHOA_THONGGIO,
								btsEntity.getSupervision_Bts_Id());
			}
			if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_DIEN_AC)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_TU_DIEN_AC,
						btsEntity.getSupervision_Bts_Id());
			}

			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY,
						btsEntity.getSupervision_Bts_Id());
			}

			if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS,
						btsEntity.getSupervision_Bts_Id());
			}

			if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF,
						btsEntity.getSupervision_Bts_Id());
			}

			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP)) {
				item = causeCatWorkController
						.getCause_Bts_Cat_WorkEntity(
								Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP,
								btsEntity.getSupervision_Bts_Id());
			}

			item.setWorkCode(workcode);
			item.setStt(i + 1);
			item.setTenHangMuc(listConstrWorkNgoaitroi.get(i).getWork_Name());
			// nếu hạng mục đã được đánh giá
//			if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
//					|| item.getBts_Cat_WorkEntity().getStatus() >= 0) {
				listDataNgoaitroi.add(item);
//			}

		}
	}

	public void getDataTrongnhaFromDatabase() {
		Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
				this);
		for (int i = 0; i < listConstrWorkTrongnha.size(); i++) {
			Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

			String workcode = listConstrWorkTrongnha.get(i).getWork_Code();

			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_DEN_BAO_KHONG)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_DEN_BAO_KHONG,
						btsEntity.getSupervision_Bts_Id());
			}

			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER)) {
				item = causeCatWorkController
						.getCause_Bts_Cat_WorkEntity(
								Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER,
								btsEntity.getSupervision_Bts_Id());
			}

			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TAM_BIT_CAP)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_TAM_BIT_CAP,
						btsEntity.getSupervision_Bts_Id());
			}

			if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP,
						btsEntity.getSupervision_Bts_Id());
			}

			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU)) {
				item = causeCatWorkController
						.getCause_Bts_Cat_WorkEntity(
								Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU,
								btsEntity.getSupervision_Bts_Id());
			}

			item.setWorkCode(workcode);
			item.setStt(i + 1);
			item.setTenHangMuc(listConstrWorkTrongnha.get(i).getWork_Name());
//			 if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
//			 || item.getBts_Cat_WorkEntity().getStatus() >= 0) {
			listDataTrongnha.add(item);
//			 }

		}
	}

	public void setListTiepdatData() {
			listConstrWorkTiepdat = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

			Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
					this);

			// lay ten constr work tuong ung voi work code tiep dat thoat set
			Cat_Supervision_Constr_WorkEntity constrWorkTiepdat_Thoatset = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK);

			// lay ten constr work tuong ung voi work code tiep dat feeder
			Cat_Supervision_Constr_WorkEntity constrWorkTiepdat_Feeder = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER);

			listConstrWorkTiepdat.add(constrWorkTiepdat_Thoatset);
			listConstrWorkTiepdat.add(constrWorkTiepdat_Feeder);
		
	}

	public void setListNgoaitroiData() {
		
		if (constr_ConstructionItem != null
				&& constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_4G) {
			listConstrWorkNgoaitroi = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

			Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
					this);

			// lay ten constr work tuong ung voi work code thang cap
			Cat_Supervision_Constr_WorkEntity constrWorkThangcap = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_THANG_CAP);

			// lay ten constr work tuong ung voi work code he thong den dieu hoa
			Cat_Supervision_Constr_WorkEntity constrWorkHethongden = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_HT_DEN_DIEUHOA_THONGGIO);

			// lay ten constr work tuong ung voi work code tu dien ac
			Cat_Supervision_Constr_WorkEntity constrWorkTudienAc = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_DIEN_AC);

			// lay ten constr work tuong ung voi work code tu nguon dc acquy
			Cat_Supervision_Constr_WorkEntity constrWorkTunguon = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY);

			// lay ten constr work tuong ung voi work code tu bts
			Cat_Supervision_Constr_WorkEntity constrWorkTuBts = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS);

			// lay ten constr work tuong ung voi work code rack df
			Cat_Supervision_Constr_WorkEntity constrWorkRackDf = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF);

			// lay ten constr work tuong ung voi work code cap nguon thang cap
			Cat_Supervision_Constr_WorkEntity constrWorkCapnguon_Thangcap = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP);

			listConstrWorkNgoaitroi.add(constrWorkThangcap);
			listConstrWorkNgoaitroi.add(constrWorkHethongden);
			listConstrWorkNgoaitroi.add(constrWorkTudienAc);

			listConstrWorkNgoaitroi.add(constrWorkTunguon);
			listConstrWorkNgoaitroi.add(constrWorkTuBts);
			listConstrWorkNgoaitroi.add(constrWorkRackDf);
			listConstrWorkNgoaitroi.add(constrWorkCapnguon_Thangcap);
		}else if (constr_ConstructionItem != null
				&& constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
			listConstrWorkNgoaitroi = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

			Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
					this);


			// lay ten constr work tuong ung voi work code tu nguon dc acquy
			Cat_Supervision_Constr_WorkEntity constrWorkTunguon = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY);

			// lay ten constr work tuong ung voi work code tu bts
			Cat_Supervision_Constr_WorkEntity constrWorkTuBts = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS);

			// lay ten constr work tuong ung voi work code rack df
			Cat_Supervision_Constr_WorkEntity constrWorkRackDf = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP);


			listConstrWorkNgoaitroi.add(constrWorkTunguon);
			listConstrWorkNgoaitroi.add(constrWorkTuBts);
			listConstrWorkNgoaitroi.add(constrWorkRackDf);
		}
		
		
	}

	public void setListTrongnhaData() {
		
		if (constr_ConstructionItem != null
				&& constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_4G) {
			listConstrWorkTrongnha = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

			Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
					this);

			// lay ten constr work tuong ung voi work code den bao khong
			Cat_Supervision_Constr_WorkEntity constrWorkDenbaokhong = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_DEN_BAO_KHONG);

			// lay ten constr work tuong ung voi work code antenfeeder
			Cat_Supervision_Constr_WorkEntity constrWorkAntenFeeder = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER);

			// lay ten constr work tuong ung voi work code tam bit cap
			Cat_Supervision_Constr_WorkEntity constrWorkTambitcap = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TAM_BIT_CAP);

			// lay ten constr work tuong ung voi work code rru olp
			Cat_Supervision_Constr_WorkEntity constrWorkRru = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP);

			// lay ten constr work tuong ung voi work code cap quang nguon rru
			Cat_Supervision_Constr_WorkEntity constrWorkCapquangnguon = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU);

			listConstrWorkTrongnha.add(constrWorkDenbaokhong);

			listConstrWorkTrongnha.add(constrWorkAntenFeeder);
			listConstrWorkTrongnha.add(constrWorkTambitcap);
			listConstrWorkTrongnha.add(constrWorkRru);
			listConstrWorkTrongnha.add(constrWorkCapquangnguon);
		}
		else if (constr_ConstructionItem != null
				&& constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
			listConstrWorkTrongnha = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

			Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
					this);

			// lay ten constr work tuong ung voi work code antenfeeder
			Cat_Supervision_Constr_WorkEntity constrWorkAntenFeeder = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER);

			// lay ten constr work tuong ung voi work code rru olp
			Cat_Supervision_Constr_WorkEntity constrWorkRru = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP);

			// lay ten constr work tuong ung voi work code cap quang nguon rru
			Cat_Supervision_Constr_WorkEntity constrWorkCapquangnguon = constrWorkController
					.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU);

			listConstrWorkTrongnha.add(constrWorkAntenFeeder);
			listConstrWorkTrongnha.add(constrWorkRru);
			listConstrWorkTrongnha.add(constrWorkCapquangnguon);
		}
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// click nut hoan thanh
		case R.id.rl_supervision_bts_equip_complete:
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
		case R.id.rl_supervision_bts_equip_bt_save:
			String messageError = "";
			String messageErrorEquip = "";
			if (!GlobalInfo.getInstance().isCheckIn()) {
				showAlertDialogCheckInRequire(this,
						getString(R.string.checkin_require),
						getString(R.string.text_close));
				break;
			}
			// neu la tab thong tin thiet bi
			if (tabs.getCurrentTab() == 0) {
				messageErrorEquip = checkValidateEquip();
				if (!StringUtil.isNullOrEmpty(messageErrorEquip)) {
					return;
				}

			} else {
				// neu la tab Gs lap dat: Phan tiep dat
				if (tabs.getCurrentTab() == 1) {
					for (Cause_Bts_Cat_WorkEntity curItemData : listDataTiepdat) {
						messageError = this.checkVailid(curItemData);
						if (!StringUtil.isNullOrEmpty(messageError)) {
							break;
						}
					}

					if (!StringUtil.isNullOrEmpty(messageError)) {
						this.showDialog(messageError);
						return;
					}
				} else {
					// neu la tab Gs lap dat: Phan ngoai troi
					if (tabs.getCurrentTab() == 2) {
						for (Cause_Bts_Cat_WorkEntity curItemData : listDataNgoaitroi) {
							messageError = this.checkVailid(curItemData);
							if (!StringUtil.isNullOrEmpty(messageError)) {
								break;
							}
						}

						if (!StringUtil.isNullOrEmpty(messageError)) {
							this.showDialog(messageError);
							return;
						}
					} else {
						// neu la tab Gs lap dat: Phan trong nha
						for (Cause_Bts_Cat_WorkEntity curItemData : listDataTrongnha) {
							messageError = this.checkVailid(curItemData);
							if (!StringUtil.isNullOrEmpty(messageError)) {
								break;
							}
						}

						if (!StringUtil.isNullOrEmpty(messageError)) {
							this.showDialog(messageError);
							return;
						}
					}
				}
			}
			confirmSave = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save));
			confirmSave.show();

			break;
		// click combobox thong tin
		case R.id.rl_supervision_bts_equip_tv_thietke:

			this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
					this.listInfomation);

			dropdownPopupMenuInfomation.show(v);
			break;

		// click combobox chon loai tu 2g
		case R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_chonloaitu2G:
			this.dropdownPopupMenu2gType = new Menu_DropdownPopup(this,
					this.list2gType);
			dropdownPopupMenu2gType.show(v);
			break;

		// click combobox chon loai tu 3g
		case R.id.rl_supervision_bts_equip_tl_thongtinthietbi_row2_tv_chonloaitu3G:

			this.dropdownPopupMenu3gType = new Menu_DropdownPopup(this,
					this.list3gType);
			dropdownPopupMenu3gType.show(v);
			break;

		// click combobox chon loai thiet bi
		case R.id.rl_supervision_bts_equip_tl_thongtinthietbi_truyendan_row1_tv_chonloaithietbi:

			this.dropdownPopupMenuEquipType = new Menu_DropdownPopup(this,
					this.listEquipType);
			dropdownPopupMenuEquipType.show(v);
			break;
		// click combobox chon tan so
		case R.id.rl_supervision_bts_equip_tl_thongtinthietbi_truyendan_row2_tv_chontanso:

			this.dropdownPopupMenuTanso = new Menu_DropdownPopup(this,
					this.listTanso);
			dropdownPopupMenuTanso.show(v);
			break;

		// click combobox chon kich thuoc trong
		case R.id.rl_supervision_bts_equip_tl_thongtinthietbi_truyendan_row2_tv_chonkichthuoctrong:

			this.dropdownPopupMenuKtTrong = new Menu_DropdownPopup(this,
					this.listKtTrong);
			dropdownPopupMenuKtTrong.show(v);
			break;
		}
	}

	public Supervision_Bts_EquipEntity getEquipEntity() {
		Supervision_Bts_EquipEntity result = new Supervision_Bts_EquipEntity();

		if (StringUtil.isNullOrEmpty(supervision_bts_equip_et_diengiai_sotu_2g
				.getText().toString())) {
			result.set_2g_TOTAL(-1);
		} else
			result.set_2g_TOTAL(Long
					.parseLong(supervision_bts_equip_et_diengiai_sotu_2g
							.getText().toString()));

		if (StringUtil.isNullOrEmpty(supervision_bts_equip_et_diengiai_sotu_3g
				.getText().toString())) {
			result.set_3g_TOTAL(-1);
		} else
			result.set_3g_TOTAL(Long
					.parseLong(supervision_bts_equip_et_diengiai_sotu_3g
							.getText().toString()));
		result.setBts_2g_Company_Id(bts_2g_company_id);
		result.setBts_3g_Company_Id(bts_3g_company_id);
		result.setTrans_Company_Id(trans_company_id);
		result.setTrans_Type(isEquipType);
		result.setTrans_freq(isTanso);
		result.setTrans_viba_dimension(isKtTrong);
		result.set_3g_TYPE(isType3g);
		result.set_2g_TYPE(isType2g);
		result.setSupervision_BTS_ID(btsEntity.getSupervision_Bts_Id());
		return result;
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

				this.supervision_bts_equip_tv_thietke.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuInfomation.dismiss();

				Bundle bundleMonitorData = new Bundle();
				bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
						constr_ConstructionItem);
				bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
						isInfomation);

				this.showProgressDialog(StringUtil
						.getString(R.string.text_loading));

				gotoBtsActivity(bundleMonitorData, isInfomation);

				finish();
				// gotoSupervisionBtsPillarActivity(new Bundle());
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.TYPE_2G)) {
				isType2g = dropdownItem.getId();
				if (this.isType2g > 0) {
					supervision_bts_equip_tv_2g_type.setError(null, ic_combo);
				}
				this.supervision_bts_equip_tv_2g_type.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenu2gType.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.TYPE_3G)) {
				isType3g = dropdownItem.getId();
				if (this.isType3g > 0) {
					supervision_bts_equip_tv_3g_type.setError(null, ic_combo);
				}
				this.supervision_bts_equip_tv_3g_type.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenu3gType.dismiss();
			}

			if (typeItem.equals(Constants.DROPDOWN_TYPE.EQUIP_TYPE)) {
				isEquipType = dropdownItem.getId();
				if (this.isEquipType > 0) {
					supervision_bts_equip_tv_equip_type
							.setError(null, ic_combo);
				}

				this.supervision_bts_equip_tv_equip_type.setText(dropdownItem
						.getTitle());

				this.dropdownPopupMenuEquipType.dismiss();
			}

			if (typeItem.equals(Constants.DROPDOWN_TYPE.TAN_SO)) {
				isTanso = dropdownItem.getId();
				if (this.isTanso > 0) {
					supervision_bts_equip_tv_equip_tanso.setError(null,
							ic_combo);
				}

				this.supervision_bts_equip_tv_equip_tanso.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuTanso.dismiss();
			}

			if (typeItem.equals(Constants.DROPDOWN_TYPE.KICH_THUOC_TRONG)) {
				isKtTrong = dropdownItem.getFid();
				if (this.isKtTrong > 0) {
					supervision_bts_equip_tv_equip_kichthuoctrong.setError(
							null, ic_combo);
				}

				this.supervision_bts_equip_tv_equip_kichthuoctrong
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuKtTrong.dismiss();
			}
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			break;

		// chon nguyen nhan khong dat
		case OnEventControlListener.EVENT_SUPERVISION_UNQUALIFY:
			this.curEditItem = (Cause_Bts_Cat_WorkEntity) data;
			if (this.curEditItem.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.KHONG_DAT) {
				String workcode = curEditItem.getWorkCode();
				Cat_Cause_Constr_UnQualifyController unqualifyController = new Cat_Cause_Constr_UnQualifyController(
						this);

				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK
								.toString())) {
					// kiểm tra trạm nếu 4G thì thay
					// SUB_TYPE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK =
					// SUB_TYPE_4G_TIEP_DAT_THOAT_SET_THANG_CAP_RACK
					if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TIEP_DAT_THOAT_SET_THANG_CAP_RACK,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					} else {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER
								.toString())) {
					if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TIEP_DAT_FEEDER,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					} else {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_TIEP_DAT_FEEDER,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_THANG_CAP
								.toString())) {
					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_THANG_CAP,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_HT_DEN_DIEUHOA_THONGGIO
								.toString())) {
					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_HT_DEN_DIEUHOA_THONGGIO,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_DIEN_AC
								.toString())) {
					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_TU_DIEN_AC,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY
								.toString())) {
					if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TU_NGUON_DC_ACQUY,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					} else {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_TU_NGUON_DC_ACQUY,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}

				}
				if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS
						.toString())) {
					if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TU_BTS,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					} else {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_TU_BTS,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}

				}
				if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF
						.toString())) {
					if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_RACK_DF,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					} else {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_RACK_DF,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP
								.toString())) {
					if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_CAP_NGUON_THANG_CAP,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}else{
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_NGUON_THANG_CAP,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}
						

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_DEN_BAO_KHONG
								.toString())) {
					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_DEN_BAO_KHONG,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER
								.toString())) {
					if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_ANTEN_FEEDER_JUMPER,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					} else {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_ANTEN_FEEDER_JUMPER,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TAM_BIT_CAP
								.toString())) {
					listUnqualifyCatWorkItem = unqualifyController
							.getAllUnQualifyItemByName(
									Constants.UNQUALIFY_TYPE.SUB_TYPE_TAM_BIT_CAP,
									Constants.UNQUALIFY_TYPE.BTS_TYPE);

				}
				if (workcode.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP
						.toString())) {
					if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_RRU_OLP,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					} else {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_RRU_OLP,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}

				}
				if (workcode
						.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU
								.toString())) {
					if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_CAP_QUANG_NGUON_RRU,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					} else {
						listUnqualifyCatWorkItem = unqualifyController
								.getAllUnQualifyItemByName(
										Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_QUANG_NGUON_RRU,
										Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}

				}
				//
				setUnqualify();

				contructionUnqualifyPopup = new Contruction_UnqualifyPopup(
						this, null, this.listUnqualifyCatWorkItem);
				contructionUnqualifyPopup.showAtCenter();
			} else if (this.curEditItem.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT) {
//				this.setAcceptance();
				// kiểm tra nếu 4G
				if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
					String workcode = curEditItem.getWorkCode();
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TIEP_DAT_THOAT_SET_THANG_CAP_RACK,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TIEP_DAT_FEEDER,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TU_NGUON_DC_ACQUY,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TU_BTS,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_RACK_DF,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_ANTEN_FEEDER_JUMPER,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_RRU_OLP,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_CAP_QUANG_NGUON_RRU,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
									this).getAllUnQualifyItemByName(
											Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_CAP_NGUON_THANG_CAP,
											Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}else{
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.ACCEPTANCE_TYPE.BTS_THIET_BI,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}
				}else{
					listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
							this).getAllUnQualifyItemByName(
							Constants.ACCEPTANCE_TYPE.BTS_THIET_BI,
							Constants.UNQUALIFY_TYPE.BTS_TYPE);
				}
				
				this.setAcceptance();
				contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
						this, null, this.listEquipAcceptanceItem);
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
			case Constants.BTS_EQUIP_EDIT.FAIL_DESC:
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
			if (this.curEditItem.getiField() == Constants.BTS_EQUIP_EDIT.FAIL_DESC) {

				this.curEditItem.getBts_Cat_WorkEntity().setFail_Desc(
						sSetTextValue);
			}
			this.curEditItem.setEdited(true);
			this.bts_EquipTiepdatAdapter.notifyDataSetChanged();
			this.bts_EquipNgoaitroiAdapter.notifyDataSetChanged();
			this.bts_EquipTrongnhaAdapter.notifyDataSetChanged();

			editTextPopup.hidePopup();
			break;
		// chon danh gia la dat
		case OnEventControlListener.EVENT_CHOICE_ACCESS_DAT:
			this.curEditItem = (Cause_Bts_Cat_WorkEntity) data;
			// nếu chon đạt thi phải kiểm tra xem có cái nào cần chụp ảnh hay không
			if (curEditItem.getBts_Cat_WorkEntity().getStatus() == Constants.BTS_CAT_WORK_STATUS.DAT) {
				// kiểm tra nếu 4G
				if (constr_ConstructionItem.getCatStationTypeId() == Constants.STATION_TYPE.TYPE_4G) {
					String workcode = curEditItem.getWorkCode();
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TIEP_DAT_THOAT_SET_THANG_CAP_RACK,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TIEP_DAT_FEEDER,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TU_NGUON_DC_ACQUY,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_TU_BTS,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_RACK_DF,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_ANTEN_FEEDER_JUMPER,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_RRU_OLP,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else
					if (workcode
							.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU
									.toString())) {
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.UNQUALIFY_TYPE.SUB_TYPE_4G_CAP_QUANG_NGUON_RRU,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
						
					}else{
						listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
								this).getAllUnQualifyItemByName(
								Constants.ACCEPTANCE_TYPE.BTS_THIET_BI,
								Constants.UNQUALIFY_TYPE.BTS_TYPE);
					}
				}else{
					listEquipAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(
							this).getAllUnQualifyItemByName(
							Constants.ACCEPTANCE_TYPE.BTS_THIET_BI,
							Constants.UNQUALIFY_TYPE.BTS_TYPE);
				}
				
				if (curEditItem.getListAcceptance().size()==0) {
					for (int i = 0; i < listEquipAcceptanceItem.size(); i++) {
						if (listEquipAcceptanceItem.get(i).getIs_Mandory()==1) {
							curEditItem.getListAcceptance().add(listEquipAcceptanceItem.get(i));
						}
					}
				}
			}else{
				curEditItem.getListAcceptance().clear();
			}

			this.curEditItem.setEdited(true);
			break;
		// chon danh gia la khong dat
		case OnEventControlListener.EVENT_CHOICE_ACCESS_KDAT:
			this.curEditItem = (Cause_Bts_Cat_WorkEntity) data;

			this.curEditItem.setEdited(true);
			break;
		// click confirm yes dialog chac chan them du lieu vao database
		case OnEventControlListener.EVENT_CONFIRM_OK:
			// this.showProgressDialog(StringUtil.getString(R.string.text_loading));
			// this.saveData();
			// this.closeProgressDialog();
			showProgressDialog(StringUtil.getString(R.string.text_progcessing));
			SaveAsyncTask task = new SaveAsyncTask();
			task.execute();
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
		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			this.saveAcceptance();
			contruoctionAcceptancePopup.hidePopup();
			this.bts_EquipTiepdatAdapter.notifyDataSetChanged();
			this.bts_EquipNgoaitroiAdapter.notifyDataSetChanged();
			this.bts_EquipTrongnhaAdapter.notifyDataSetChanged();
			this.curEditItem.setEdited(true);
			break;
		/* Dong nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contructionUnqualifyPopup.hidePopup();
			this.bts_EquipTiepdatAdapter.notifyDataSetChanged();
			this.bts_EquipNgoaitroiAdapter.notifyDataSetChanged();
			this.bts_EquipTrongnhaAdapter.notifyDataSetChanged();

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
					Constants.PROGRESS_TYPE.LAP_DAT_THIET_BI, outOfKey);
			showCompleteDate(constr_ConstructionItem,
					Constants.PROGRESS_TYPE.BTS_TYPE,
					Constants.PROGRESS_TYPE.LAP_DAT_THIET_BI,
					supervision_bts_equip_td_complete_date,
					rl_supervision_bts_equip_complete);

			showCompleteDate(constr_ConstructionItem,
					Constants.PROGRESS_TYPE.BTS_TYPE,
					Constants.PROGRESS_TYPE.LAP_DAT_THIET_BI,
					supervision_bts_equip_tn_complete_date,
					rl_supervision_bts_equip_complete);

			showCompleteDate(constr_ConstructionItem,
					Constants.PROGRESS_TYPE.BTS_TYPE,
					Constants.PROGRESS_TYPE.LAP_DAT_THIET_BI,
					supervision_bts_equip_nt_complete_date,
					rl_supervision_bts_equip_complete);
			break;
		default:
			super.onEvent(eventType, control, data);
			break;

		}
	}

	/* Ghi nghiem thu vao danh sach ong */
	private void saveAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listEquipAcceptanceItem) {
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
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listEquipAcceptanceItem) {
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

	class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			confirmSave.dismiss();
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
				Toast.makeText(SupervisionBtsEquipActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshView();

			Toast.makeText(SupervisionBtsEquipActivity.this,
					getResources().getString(R.string.text_update_success),
					Toast.LENGTH_LONG).show();

			closeProgressDialog();
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

	public boolean saveDataType(
			Supervision_Bts_Cat_WorkController btsCatWorkController,
			Cause_Bts_Cat_WorkController causeBtsCatWorkController,
			List<Cause_Bts_Cat_WorkEntity> listItem) {

		long idUser = GlobalInfo.getInstance().getUserId();
		try {
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
									.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_THIET_BI);
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

						curItemData.getBts_Cat_WorkEntity().setIsActive(
								Constants.IS_ACTIVE);

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
											.setType(Constants.UNQUALIFY_TYPE.SUB_TYPE_THIET_BI);
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
									// if (curItemData.getBts_Cat_WorkEntity()
									// .getStatus() ==
									// Constants.BTS_CAT_WORK_STATUS.DAT) {
									// causeBtsCatWorkController
									// .deleteCause_Bts_Cat_WorkEntity(addItemCause);
									// }

									if (addItemCause.getLstNewAttachFile()
											.size() > 0
											|| (addItemCause
													.getLstNewAttachFile()
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

	private void saveData() {
		try {
			long idUser = GlobalInfo.getInstance().getUserId();
			Supervision_Bts_Cat_WorkController btsCatWorkController = new Supervision_Bts_Cat_WorkController(
					this);
			Cause_Bts_Cat_WorkController causeBtsCatWorkController = new Cause_Bts_Cat_WorkController(
					this);

			// neu la tab thong tin thiet bi
			if (tabs.getCurrentTab() == 0) {
				Supervision_Bts_EquipController btsEquipController = new Supervision_Bts_EquipController(
						this);
				Supervision_Bts_EquipEntity equipEntity = getEquipEntity();

				if (btsEquipEntity != null) {
					equipEntity.setSync_Status(Constants.SYNC_STATUS.ADD);
					btsEquipController.updateSupervisionBtsEquip(equipEntity);
				} else {
					long idSupervionEquip = Ktts_KeyController.getInstance()
							.getKttsNextKey(
									Supervision_Bts_EquipField.TABLE_NAME);
					if (idSupervionEquip == 0) {
						outOfKey = true;
						return;
					} else
						outOfKey = false;
					equipEntity.setSync_Status(Constants.SYNC_STATUS.ADD);
					equipEntity.setIsActive(Constants.ISACTIVE.ACTIVE);
					equipEntity.setProcessId(0);
					equipEntity.setEmployeeId(idUser);
					equipEntity.setSupervisionConstrId(btsEntity
							.getSupervision_ConstrEntity()
							.getSupervision_Constr_Id());
					equipEntity.setSupervision_BTS_EQUIP_ID(idSupervionEquip);
					btsEquipController.insertSupervisionBtsEquip(equipEntity);
				}
			} else {
				// neu la tab Gs lap dat: Phan tiep dat
				if (tabs.getCurrentTab() == 1) {
					if (saveDataType(btsCatWorkController,
							causeBtsCatWorkController, listDataTiepdat)) {
						outOfKey = true;

						return;
					} else
						outOfKey = false;
				} else {
					// neu la tab Gs lap dat: Phan ngoai troi
					if (tabs.getCurrentTab() == 2) {
						if (saveDataType(btsCatWorkController,
								causeBtsCatWorkController, listDataNgoaitroi)) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;
					} else {
						// neu la tab Gs lap dat: Phan trong nha
						if (saveDataType(btsCatWorkController,
								causeBtsCatWorkController, listDataTrongnha)) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;
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
					addItem.setLstAttachFile(curCauseUniqualify
							.getLstAttachFile());
					addItem.setTitle(curCauseUniqualify.getTitle());
					addItem.setIsSerious(curCauseUniqualify.getIsSerious());
					curListUnqualify.add(addItem);
				}
			} else {
				if (curCauseUniqualify.isUnqulify()) {
					curItem.setUnqulify(true);
					curItem.setDelete(curCauseUniqualify.isDelete());
					curItem.setDeleteImage(curCauseUniqualify.isDeleteImage());
					curItem.setIsSerious(curCauseUniqualify.getIsSerious());
					curItem.setLstNewAttachFile(curCauseUniqualify
							.getLstNewAttachFile());
					curItem.setLstAttachFile(curCauseUniqualify
							.getLstAttachFile());

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
				curCauseUniqualify.setLstAttachFile(curItem.getLstAttachFile());
				curCauseUniqualify.setLstNewAttachFile(curItem
						.getLstNewAttachFile());
				curCauseUniqualify.setDelete(curItem.isDelete());
				curCauseUniqualify.setCause_Line_Bg_Id(curItem
						.getCause_Line_Bg_Id());
			}
		}
	}

	/* Lay nguyen nhan loi tu id */

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

	private void setHeader() {
		final Header myActionBar = (Header) spvBts_EquipView
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
				requestSync(SupervisionBtsEquipActivity.this);
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

			setListTiepdatData();
			setListNgoaitroiData();
			setListTrongnhaData();
			refreshView();

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
}
