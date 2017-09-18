package com.viettel.ktts;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Header;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.Constants.SPECIAL_LOCATION_TYPE;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Constr_ConstructionController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Special_LocationController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_BG_FiberController;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.Supervision_Line_BackgroundController;
import com.viettel.database.entity.Constr_ConstructionEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Special_LocationEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.database.entity.Supervision_Line_BG_FiberEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supervision_Locate_Entity;
import com.viettel.database.field.Special_LocationField;
import com.viettel.database.field.Supervision_Line_BackgroundField;
import com.viettel.database.field.Supervision_Locate_Field;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.service.GpsServices;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.Point_To_BridgeAdapter;
import com.viettel.view.control.Point_To_PondAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * giam sat thong tin thiet ke
 * 
 * @author datht1
 * 
 */
public class Supervision_Line_BG_DesignActivity extends HomeBaseActivity {
    private final String TAG = this.getClass().getSimpleName();
    private View spvLineBG_DesignView;
	private TabHost tabHost;
	private int tabHeight = 40;
	private int tabWidth = 400;
	private RelativeLayout rl_supervision_line_bg_design;
	/* controll */
	private TextView tv_constr_line_background_dropdown;
	private TextView tv_constr_line_background_info_line_value;
	private TextView tv_constr_line_background_info_line_station_code_value;
	private TextView tv_constr_line_background_design_type_dropdown;
	private TextView tv_constr_line_background_review_dropdown;
	private EditText edt_constr_line_background_startcode_value;
	private EditText edt_constr_line_background_endcode_value;
	private EditText edt_constr_line_background_inter_value;
	private EditText edt_constr_line_background_long_value;
	private Button btn_constr_line_background_save;
	/* control be */
	private EditText edt_line_background_design_tank_size;
	private EditText edt_line_background_design_tank_dan1;
	private EditText edt_line_background_design_tank_dan2;
	private EditText edt_line_background_design_tank_dan3;
	private EditText edt_line_background_design_tank_size_old;
	/* control diem qua cau cong */
	private Button btn_constr_line_background_point_to_bridge_save;
	private ListView ll_constr_line_background_tab_point_to_bridge_list;
	/* control diem qua ao ho */
	private Button btn_constr_line_background_point_to_pond_save;
	private ListView ll_constr_line_background_tab_point_to_pond_list;
	/* control cable */
	private TextView tv_constr_line_background_ctt_dropdown;
	private EditText edt_constr_line_background_cdctt;
	private EditText edt_constr_line_background_tsmx;
	private EditText edt_constr_line_background_deployday_value;
	private TextView tv_constr_line_background_lc;
	private EditText edt_constr_line_background_lo;
	private EditText edt_constr_line_background_so;
	/* bien dropdown */
	private int iDesignInfo = 0;
	private int iDesignType = Constants.SEARCH_VALUE_DEFAULT;
	private int iDesignAssess = Constants.SEARCH_VALUE_DEFAULT;
	private int iBuryType = Constants.SEARCH_VALUE_DEFAULT;
	private int iCableType = Constants.SEARCH_VALUE_DEFAULT;
	private List<DropdownItemEntity> listDesignInfo = null;
	private List<DropdownItemEntity> listDesignType = null;
	private List<DropdownItemEntity> listDesignAssess = null;
	private List<DropdownItemEntity> listBuryType = null;
	private List<DropdownItemEntity> listCableType = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Menu_DropdownPopup dropdownPopupMenuDesignType;
	private Menu_DropdownPopup dropdownPopupMenuDesignAssess;
	private Menu_DropdownPopup dropdownPopupMenuBuryType;
	private Menu_DropdownPopup dropdownPopupMenuCableType;
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_ConstrEntity supervision_ConstrData;
	private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
	private Supervision_ConstrController supervision_ConstrController;
	private Supervision_Line_BackgroundController supervision_Line_BackgroundController;
	private List<Supervision_Line_BG_FiberEntity> listLineBGFiber;
	private Supervision_Line_BG_FiberController supervisionLineFiberController;
	private List<Supervision_Line_BG_TankGSEntity> listSupervisionGS;
	private Supervision_Line_BG_TankController supervisionLBGController;

	private Special_LocationEntity curItemDel;
	private List<Special_LocationEntity> listPointBridge;
	private List<Special_LocationEntity> listPointPond;
	private Special_LocationController specialLocationController;
	private Point_To_BridgeAdapter pointBridgeAdapter;
	private Point_To_PondAdapter pointPondAdapter;
	@SuppressWarnings("unused")
	private boolean tankNumChange = false;
	private boolean saveOrDel = false;
	private long iTotalNewTank;
	private long iTotalTank;
	@SuppressWarnings("unused")
	private boolean outOfKey = false;
	// private float positionTouch = 0f;
	private boolean isBridge;
	private boolean clickView = true;
	
	//hunkgm - get lat long form constr_contruction table
	private Constr_ConstructionController constr_ConstructionController;
	private Constr_ConstructionEntity constr_ConstructionData;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_line_background_activity);
		setTitle(getString(R.string.line_background_header_title));
		/* set controll */
		this.initView();
//		setHeader();
		initTabHost();
	}

	private void initView() {
		spvLineBG_DesignView = addView(
				R.layout.supervision_line_background_activity, R.id.rl_supervision_line_bg_design);
		this.tv_constr_line_background_dropdown = (TextView) spvLineBG_DesignView
				.findViewById(R.id.tv_constr_line_background_dropdown);
		this.tv_constr_line_background_dropdown.setOnClickListener(this);

		this.tv_constr_line_background_info_line_value = (TextView) spvLineBG_DesignView
				.findViewById(R.id.tv_constr_line_background_info_line_value);

		this.tv_constr_line_background_info_line_station_code_value = (TextView) spvLineBG_DesignView
				.findViewById(R.id.tv_constr_line_background_info_line_station_code_value);

		this.tv_constr_line_background_design_type_dropdown = (TextView) spvLineBG_DesignView
				.findViewById(R.id.tv_constr_line_background_design_type_dropdown);
		this.tv_constr_line_background_design_type_dropdown
				.setText(getResources().getString(R.string.text_choice1));
		this.tv_constr_line_background_design_type_dropdown
				.setOnClickListener(this);

		this.tv_constr_line_background_review_dropdown = (TextView) spvLineBG_DesignView
				.findViewById(R.id.tv_constr_line_background_review_dropdown);
		this.tv_constr_line_background_review_dropdown.setText(getResources()
				.getString(R.string.text_choice1));
		this.tv_constr_line_background_review_dropdown.setOnClickListener(this);

		rl_supervision_line_bg_design = (RelativeLayout) spvLineBG_DesignView
				.findViewById(R.id.rl_supervision_line_bg_design);
//		rl_supervision_line_bg_design.getViewTreeObserver()
//				.addOnGlobalLayoutListener(
//						new ViewTreeObserver.OnGlobalLayoutListener() {
//
//							@Override
//							public void onGlobalLayout() {
//								Rect r = new Rect();
//								rl_supervision_line_bg_design
//										.getWindowVisibleDisplayFrame(r);
//
//								int screenHeight = rl_supervision_line_bg_design
//										.getRootView().getHeight();
//								int heightDifference = screenHeight
//										- (r.bottom - r.top);
//								int resourceId = getResources()
//										.getIdentifier("status_bar_height",
//												"dimen", "android");
//								if (resourceId > 0) {
//									heightDifference -= getResources()
//											.getDimensionPixelSize(resourceId);
//								}
//
//								if (heightDifference > 0) {
//									if (clickView
//											|| edt_constr_line_background_inter_value
//													.isFocused()
//											|| edt_constr_line_background_long_value
//													.isFocused()) {
//										rl_supervision_line_bg_design
//												.setScrollY(0);
//									} else {
//										rl_supervision_line_bg_design
//												.setScrollY(heightDifference - 25);
//									}
//
//								} else {
//									rl_supervision_line_bg_design.setScrollY(0);
//								}
//							}
//						});

		this.edt_constr_line_background_startcode_value = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_constr_line_background_startcode_value);

		this.edt_constr_line_background_endcode_value = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_constr_line_background_endcode_value);

		this.edt_constr_line_background_inter_value = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_constr_line_background_inter_value);
		registerForContextMenu(edt_constr_line_background_inter_value);
		edt_constr_line_background_inter_value.setOnClickListener(this);

		this.edt_constr_line_background_deployday_value = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_constr_line_background_deployday_value);

		this.edt_constr_line_background_long_value = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_constr_line_background_long_value);
		edt_constr_line_background_long_value.setOnClickListener(this);

		this.btn_constr_line_background_save = (Button) spvLineBG_DesignView
				.findViewById(R.id.btn_constr_line_background_save);
		this.btn_constr_line_background_save.setOnClickListener(this);
		/* cotrol be */
		this.edt_line_background_design_tank_size = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_line_background_design_tank_size);

		this.edt_line_background_design_tank_dan1 = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_line_background_design_tank_dan1);

		this.edt_line_background_design_tank_dan2 = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_line_background_design_tank_dan2);

		this.edt_line_background_design_tank_dan3 = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_line_background_design_tank_dan3);

		this.edt_line_background_design_tank_size_old = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_line_background_design_tank_size_old);

		/* control cable */
		this.tv_constr_line_background_ctt_dropdown = (TextView) spvLineBG_DesignView
				.findViewById(R.id.tv_constr_line_background_ctt_dropdown);
		this.tv_constr_line_background_ctt_dropdown.setText(getResources()
				.getString(R.string.text_choice1));
		this.tv_constr_line_background_ctt_dropdown.setOnClickListener(this);

		this.edt_constr_line_background_cdctt = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_constr_line_background_cdctt);

		this.edt_constr_line_background_tsmx = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_constr_line_background_tsmx);

		this.tv_constr_line_background_lc = (TextView) spvLineBG_DesignView
				.findViewById(R.id.tv_constr_line_background_lc_dropdown);

		this.tv_constr_line_background_lc.setText(getResources().getString(
				R.string.text_choice1));
		this.tv_constr_line_background_lc.setOnClickListener(this);

		this.edt_constr_line_background_lo = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_constr_line_background_lo);

		this.edt_constr_line_background_so = (EditText) spvLineBG_DesignView
				.findViewById(R.id.edt_constr_line_background_so);

		/* diem qua cau cong */
		btn_constr_line_background_point_to_bridge_save = (Button) spvLineBG_DesignView
                .findViewById(R.id.btn_constr_line_background_point_to_bridge_add);
		btn_constr_line_background_point_to_bridge_save
				.setOnClickListener(this);
		ll_constr_line_background_tab_point_to_bridge_list = (ListView) spvLineBG_DesignView
                .findViewById(R.id.lv_constr_line_background_tab_point_to_bridge_list);

		/* diem qua cau cong */
		btn_constr_line_background_point_to_pond_save = (Button) spvLineBG_DesignView
                .findViewById(R.id.btn_constr_line_background_point_to_pond_add);
		btn_constr_line_background_point_to_pond_save.setOnClickListener(this);
		ll_constr_line_background_tab_point_to_pond_list = (ListView) spvLineBG_DesignView
                .findViewById(R.id.ll_constr_line_background_tab_point_to_pond_list);

		listPointBridge = new ArrayList<Special_LocationEntity>();
		listPointPond = new ArrayList<Special_LocationEntity>();

		specialLocationController = new Special_LocationController(this);

	}

	public void clearFocus() {
		edt_constr_line_background_inter_value.setFocusableInTouchMode(false);
		edt_constr_line_background_inter_value.clearFocus();

		edt_constr_line_background_long_value.setFocusableInTouchMode(false);
		edt_constr_line_background_long_value.clearFocus();

		edt_line_background_design_tank_size.setFocusableInTouchMode(false);
		edt_line_background_design_tank_size.clearFocus();

		edt_line_background_design_tank_dan1.setFocusableInTouchMode(false);
		edt_line_background_design_tank_dan1.clearFocus();

		edt_line_background_design_tank_dan2.setFocusableInTouchMode(false);
		edt_line_background_design_tank_dan2.clearFocus();

		edt_line_background_design_tank_dan3.setFocusableInTouchMode(false);
		edt_line_background_design_tank_dan3.clearFocus();

		edt_line_background_design_tank_size_old.setFocusableInTouchMode(false);
		edt_line_background_design_tank_size_old.clearFocus();

		edt_constr_line_background_cdctt.setFocusableInTouchMode(false);
		edt_constr_line_background_cdctt.clearFocus();

		edt_constr_line_background_tsmx.setFocusableInTouchMode(false);
		edt_constr_line_background_tsmx.clearFocus();

		edt_constr_line_background_lo.setFocusableInTouchMode(false);
		edt_constr_line_background_lo.clearFocus();

		edt_constr_line_background_so.setFocusableInTouchMode(false);
		edt_constr_line_background_so.clearFocus();
		edt_constr_line_background_deployday_value.setFocusableInTouchMode(false);
		edt_constr_line_background_deployday_value.clearFocus();

		edt_constr_line_background_inter_value.setFocusableInTouchMode(true);
		edt_constr_line_background_long_value.setFocusableInTouchMode(true);
		edt_line_background_design_tank_size.setFocusableInTouchMode(true);
		edt_line_background_design_tank_dan1.setFocusableInTouchMode(true);
		edt_line_background_design_tank_dan2.setFocusableInTouchMode(true);
		edt_line_background_design_tank_dan3.setFocusableInTouchMode(true);
		edt_line_background_design_tank_size_old.setFocusableInTouchMode(true);
		edt_constr_line_background_cdctt.setFocusableInTouchMode(true);
		edt_constr_line_background_tsmx.setFocusableInTouchMode(true);
		edt_constr_line_background_lo.setFocusableInTouchMode(true);
		edt_constr_line_background_so.setFocusableInTouchMode(true);
		edt_constr_line_background_deployday_value.setFocusableInTouchMode(true);
	}

	private void initTabHost() {
		tabHost = (TabHost) spvLineBG_DesignView.findViewById(R.id.tabHost);
		tabHost.setup();
		String sTank = getString(R.string.line_background_design_tank);
		TabSpec specTank = tabHost.newTabSpec(sTank);
		specTank.setContent(R.id.ll_constr_line_background_tab_tank);
		specTank.setIndicator(makeTabIndicator(sTank));

		String sCable = getString(R.string.line_background_design_cable);
		TabSpec specCable = tabHost.newTabSpec(sCable);
		specCable.setContent(R.id.ll_constr_line_background_tab_cable);
		specCable.setIndicator(makeTabIndicator(sCable));

		String sBridge = getString(R.string.line_background_design_point_to_bridge);
		TabSpec specBridge = tabHost.newTabSpec(sBridge);
		specBridge
				.setContent(R.id.ll_constr_line_background_tab_point_to_bridge);
		specBridge.setIndicator(makeTabIndicator(sBridge));

		String sPond = getString(R.string.line_background_design_point_to_pond);
		TabSpec specPond = tabHost.newTabSpec(sPond);
		specPond.setContent(R.id.ll_constr_line_background_tab_point_to_pond);
		specPond.setIndicator(makeTabIndicator(sPond));

		tabHost.addTab(specTank);
		tabHost.addTab(specCable);
		tabHost.addTab(specBridge);
		tabHost.addTab(specPond);

		if (tabHost.getCurrentTab() == 0) {
			btn_constr_line_background_point_to_bridge_save
					.setVisibility(View.GONE);
			btn_constr_line_background_point_to_pond_save
					.setVisibility(View.GONE);
		}

		tabHost.setOnTabChangedListener(new OnTabChangeListener() {

			@Override
			public void onTabChanged(String tabId) {
				int i = tabHost.getCurrentTab();

				if (i == 2) {
					clickView = false;
					btn_constr_line_background_point_to_bridge_save
							.setVisibility(View.VISIBLE);
					btn_constr_line_background_point_to_pond_save
							.setVisibility(View.GONE);
				} else if (i == 3) {
					clickView = false;
					btn_constr_line_background_point_to_bridge_save
							.setVisibility(View.GONE);
					btn_constr_line_background_point_to_pond_save
							.setVisibility(View.VISIBLE);
				} else {
					btn_constr_line_background_point_to_bridge_save
							.setVisibility(View.GONE);
					btn_constr_line_background_point_to_pond_save
							.setVisibility(View.GONE);
				}
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		setData();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		gotoHomeActivity(new Bundle());
		finish();
	}
	
	@Override
	public void actionCheckInButton() {
		super.actionCheckInButton();
		// isCheckIn = !isCheckIn;
		// GlobalInfo.getInstance().setCheckIn(isCheckIn);
		// kiểm tra điều kiện check in
		boolean canCheckIn = false;
		String alertMessage = "";
//		reqLat = Double.parseDouble(constr_ConstructionData.getLatitude());
//		reqLong = Double.parseDouble(constr_ConstructionData.getLongtitude());
		if(constr_ConstructionData.getLatitude()!=null
                && constr_ConstructionData.getLongtitude()!=null){
			reqLat = (constr_ConstructionData.getLatitude().equalsIgnoreCase(""))
                    ? -1.0:Double.parseDouble(constr_ConstructionData.getLatitude());
			reqLong = (constr_ConstructionData.getLongtitude().equalsIgnoreCase(""))
                    ?-1.0:Double.parseDouble(constr_ConstructionData.getLongtitude());
		}else {
			reqLat = -1;
			reqLong = -1;
		}
		Log.d("ktts_checkin", "req latlng: "+reqLat+", "+reqLong);
		/*if(GlobalInfo.getInstance().getCheckInTBId(this)<0){
				canCheckIn = true;
		}else if ((reqLat < 0) || (reqLong < 0) ) {
				canCheckIn = false;
				alertMessage = getString(R.string.checkin_gps_require);
		}else if (!CheckInUtil.checkDistanceBetween2Point(reqLat, reqLong)) {
				canCheckIn = false;
				alertMessage = getString(R.string.checkin_distance_require);
		}else {
			canCheckIn = true;
		}*/
		
		
		
//		else {
//			long startTimeLater = supv_locate_controller.getSupvLocateItem(
//          GlobalInfo.getInstance().getCheckInTBId(this)).getCheckinDate().getTime();
//			long diffTime  = System.currentTimeMillis() - startTimeLater;
//			if(diffTime < CheckInService.distance_require_time){
//				canCheckIn = false;
//				alertMessage = getString(R.string.checkin_time_require);
//			} else {
//				canCheckIn = true;
//			}
//		}
		
//		Log.d("hungkm", "can checkin : "+canCheckIn);
//		if(!canCheckIn){
//			showAlertDialogCheckInRequire(this, alertMessage, getString(R.string.text_close));
//		}else{
//			showPopupPlan((View) findViewById(R.id.action_checkin));
//		}
		
		showPopupPlan((View) findViewById(R.id.action_checkin));
	}
	
	public boolean addnewSupvLocatetoDB(){
		long supv_locate_id = Ktts_KeyController.getInstance()
                .getKttsNextKey(Supervision_Locate_Field.TABLE_NAME);
		if(supv_locate_id==0){
			showAlertDialogCheckInRequire(Supervision_Line_BG_DesignActivity.this,
					getString(R.string.text_out_of_key),
					getString(R.string.text_close_button));
			return false;
		}
		GlobalInfo.getInstance().setCheckInTBId(this, supv_locate_id);
		//update new data
		Supervision_Locate_Entity supvEntity = new Supervision_Locate_Entity();
		supvEntity.setIsActive(1);
		//set new id
		supvEntity.setLocateId(supv_locate_id);
		supvEntity.setPlan(edtMakePlan.getText().toString());
		supvEntity.setCheckinDate(new Date(System.currentTimeMillis()));
		supvEntity.setCheckinLatitude(String.valueOf(GpsServices.latLocation));
		supvEntity.setCheckinLongtitude(String.valueOf(GpsServices.longLocation));
		supvEntity.setSuppervisionId(itemData.getSupervision_Constr_Id());
		supvEntity.setEmployeeId(GlobalInfo.getInstance().getUserId());
//		supvEntity.setProvinceId(constr_ConstructionData.getProvinceCode());
		supvEntity.setProcessId(0);
		supvEntity.setSync_Status(0);
		return supv_locate_controller.insertSupervisionLocateEntity(supvEntity);
	}

	private void refreshListView() {
		// danh sach diem qua cau cong
		listPointBridge = specialLocationController.getListBridge(
				this.supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id(),
				Constants.SPECIAL_LOCATION_TYPE.POINT_TO_BRIDGE);

		this.pointBridgeAdapter = new Point_To_BridgeAdapter(this,
				listPointBridge);
		this.ll_constr_line_background_tab_point_to_bridge_list
				.setAdapter(pointBridgeAdapter);

		// danh sach diem qua ao ho
		listPointPond = specialLocationController.getListBridge(
				this.supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id(),
				Constants.SPECIAL_LOCATION_TYPE.POINT_TO_POND);

		this.pointPondAdapter = new Point_To_PondAdapter(this, listPointPond);
		this.ll_constr_line_background_tab_point_to_pond_list
				.setAdapter(pointPondAdapter);

	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if(!GlobalInfo.getInstance().isCheckIn()
                && v.getId()!=R.id.btn_popup_cancel && v.getId()!=R.id.btn_popup_save_plan){
			showAlertDialogCheckInRequire(
			        this, getString(R.string.checkin_require), getString(R.string.text_close));
			return;
		}
		switch (v.getId()) {
		case R.id.edt_constr_line_background_inter_value:
			clickView = true;
			break;
		case R.id.edt_constr_line_background_long_value:
			clickView = true;
			break;
		case R.id.tv_constr_line_background_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.tv_constr_line_background_design_type_dropdown:
			this.dropdownPopupMenuDesignType = new Menu_DropdownPopup(this,
					this.listDesignType);
			dropdownPopupMenuDesignType.show(v);
			break;
		case R.id.tv_constr_line_background_review_dropdown:
			this.dropdownPopupMenuDesignAssess = new Menu_DropdownPopup(this,
					this.listDesignAssess);
			dropdownPopupMenuDesignAssess.show(v);
			break;
		case R.id.tv_constr_line_background_ctt_dropdown:
			this.dropdownPopupMenuBuryType = new Menu_DropdownPopup(this,
					this.listBuryType);
			dropdownPopupMenuBuryType.show(v);

			break;
		case R.id.tv_constr_line_background_lc_dropdown:
			this.dropdownPopupMenuCableType = new Menu_DropdownPopup(this,
					this.listCableType);
			dropdownPopupMenuCableType.show(v);
			break;
		// click button them moi diem qua cau cong
		case R.id.btn_constr_line_background_point_to_bridge_add:
			Special_LocationEntity addItemBridge = new Special_LocationEntity();
			this.listPointBridge.add(addItemBridge);
			this.pointBridgeAdapter.notifyDataSetChanged();
			break;
		// click button them moi diem qua ao ho
		case R.id.btn_constr_line_background_point_to_pond_add:
			Special_LocationEntity addItemPond = new Special_LocationEntity();
			addItemPond.setBridgeName(GloablUtils.getPondName(listPointPond
					.size() + 1));
			this.listPointPond.add(addItemPond);
			this.pointPondAdapter.notifyDataSetChanged();
			break;
		case R.id.btn_constr_line_background_save:
			/* hien thi confirm ghi thong tin */
			boolean bValid = this.checkValid();
			if (bValid) {
				ConfirmDialog confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
			}
			break;
			// click chon save tren popup check in
		case R.id.btn_popup_save_plan:
			if(edtMakePlan.getText().toString().trim().length()<=0){
				showAlertDialogCheckInRequire(
				        this, getString(R.string.input_plan_require), getString(R.string.text_close));
				break;
			} else if (addnewSupvLocatetoDB()) {
				isCheckIn = !isCheckIn;
				GlobalInfo.getInstance().setCheckIn(isCheckIn);
				changeMenuCheckIn(R.drawable.icon_checkout, getString(R.string.check_out));
				startService();
				popupWindow.dismiss();
			}
			break;
			// dong popup check in
		case R.id.btn_popup_cancel:
			popupWindow.dismiss();
			break;
		default:
			break;
		}
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvLineBG_DesignView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_Line_BG_DesignActivity.this);
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
				this.iDesignInfo = dropdownItem.getId();
				this.dropdownPopupMenuDesignInfo.dismiss();
				// TODO KienPV test
//				this.supervision_Line_BackgroundData.setSupervision_Line_Background_Id(1);
//				this.supervision_ConstrData.setDeployExpectedDay(1); 
				//
				if (this.supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id() > 0) {
					if(this.supervision_ConstrData.getDeployExpectedDay() == 0){
						this.showDialog(StringUtil
								.getString(R.string.supervision_deploy_day_not_fill));
						return;
					}
					Bundle bundleData = new Bundle();
					bundleData.putSerializable(IntentConstants.INTENT_DATA,
							itemData);
					bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
							supervision_Line_BackgroundData);
					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							this.iDesignInfo);
					this.gotoLineBackgroupActivity(bundleData);
					this.finish();
				} else {
					this.showDialog(StringUtil
							.getString(R.string.line_background_design_noinfo_message));
				}
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_TYPE)) {
				this.iDesignType = dropdownItem.getId();
				if (this.iDesignType > 0) {

					tv_constr_line_background_design_type_dropdown.setError(
							null, ic_combo);
				}
				this.tv_constr_line_background_design_type_dropdown
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuDesignType.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_ASSESS)) {
				this.iDesignAssess = dropdownItem.getId();
				if (this.iDesignAssess > 0) {
					tv_constr_line_background_review_dropdown.setError(null,
							ic_combo);
				}
				this.tv_constr_line_background_review_dropdown
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuDesignAssess.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.BURY_TYPE)) {
				this.iBuryType = dropdownItem.getId();

				if (this.iBuryType > 0) {
					tv_constr_line_background_ctt_dropdown.setError(null,
							ic_combo);
				}
				this.tv_constr_line_background_ctt_dropdown
						.setText(dropdownItem.getTitle());
				this.dropdownPopupMenuBuryType.dismiss();
			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.CABLE_TYPE)) {
				this.iCableType = dropdownItem.getId();
				if (this.iCableType > 0) {
					tv_constr_line_background_lc.setError(null, ic_combo);
				}
				this.tv_constr_line_background_lc.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuCableType.dismiss();
			}
			getWindow().setSoftInputMode(
					WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			break;
		case OnEventControlListener.EVENT_DELETE_ROW_BRIDGE:
			curItemDel = (Special_LocationEntity) data;
			saveOrDel = false;
			isBridge = true;
			this.showAskOptionDialog(StringUtil
					.getString(R.string.text_delete_title), StringUtil
					.getString(R.string.bts_special_location_delete_message));

			break;
		case OnEventControlListener.EVENT_DELETE_ROW_POND:
			curItemDel = (Special_LocationEntity) data;
			saveOrDel = false;
			isBridge = false;
			this.showAskOptionDialog(StringUtil
					.getString(R.string.text_delete_title), StringUtil
					.getString(R.string.bts_special_location_delete_message));

			break;
		case OnEventControlListener.EVENT_CONFIRM_OK:
			new SaveAsyncTask().execute();
			// this.saveDataDesign();
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	@Override
	public void actionBeforAccept() {
		super.actionBeforAccept();
		if (saveOrDel) {

			tankNumChange = false;

			if (tabHost.getCurrentTab() == 0) {
				for (int i = ((int) iTotalTank); i < listSupervisionGS.size(); i++) {
					deleteTankRow(listSupervisionGS.get(i));
				}
				supervision_Line_BackgroundController
						.updateTank(supervision_Line_BackgroundData);
			} else {
				for (int i = (iCableType); i < listLineBGFiber.size(); i++) {
					deleteFiberRow(listLineBGFiber.get(i));
				}
				supervision_Line_BackgroundController
						.updateCable(supervision_Line_BackgroundData);
			}

			Toast.makeText(
					this,
					getResources().getString(R.string.update_database_complete),
					Toast.LENGTH_LONG).show();

			this.setData();
		} else {
			if (isBridge) {
				if (!curItemDel.isNew()) {
					deleteItemSpecialPosition(curItemDel);
				}
				listPointBridge.remove(curItemDel);
				this.pointBridgeAdapter.notifyDataSetChanged();
			} else {
				if (!curItemDel.isNew()) {
					deleteItemSpecialPosition(curItemDel);
				}
				listPointPond.remove(curItemDel);
				this.pointPondAdapter.notifyDataSetChanged();
			}
		}

	}

	// ham xoa 1 item special position
	public void deleteItemSpecialPosition(Special_LocationEntity curItemDel) {
		try {
			specialLocationController.deleteItem(curItemDel);
		} catch (Exception e) {
			Toast.makeText(this,
					StringUtil.getString(R.string.text_update_error),
					Toast.LENGTH_SHORT).show();
		}

	}

	// xoa be
	public void deleteTankRow(Supervision_Line_BG_TankGSEntity itemDelete) {

		supervisionLBGController.deleteTankEntity(itemDelete);
	}

	// xoa soi
	public void deleteFiberRow(Supervision_Line_BG_FiberEntity itemDelete) {
		Supervision_Line_BG_FiberController bgFiberController
                = new Supervision_Line_BG_FiberController(this);

		bgFiberController.deleteBgFiberEntity(itemDelete);
	}

	private void setData() {
		try {

			Bundle bundleData = this.getIntent().getExtras();
			this.itemData = (Constr_Construction_EmployeeEntity) bundleData
					.getSerializable(IntentConstants.INTENT_DATA);
			this.supervision_ConstrController = new Supervision_ConstrController(
					this);
			this.supervision_Line_BackgroundController = new Supervision_Line_BackgroundController(
					this);
			this.supervision_ConstrData = this.supervision_ConstrController
					.getItem(this.itemData.getSupervision_Constr_Id());
			this.supervision_Line_BackgroundData = supervision_Line_BackgroundController
					.getItemBySupervisionConstrId(this.supervision_ConstrData
							.getSupervision_Constr_Id());
			/* set data control cho ung dung */
			if (this.supervision_ConstrData.getDesign_Type() > 0) {
				this.iDesignType = supervision_ConstrData.getDesign_Type();
				this.tv_constr_line_background_design_type_dropdown
						.setText(GloablUtils.getStringDesignType(iDesignType));
			}
			this.edt_constr_line_background_inter_value
					.setText(supervision_ConstrData.getDesign_Description());
			if (this.supervision_ConstrData.getDesign_Assess() > 0) {
				this.iDesignAssess = supervision_ConstrData.getDesign_Assess();
				this.tv_constr_line_background_review_dropdown
						.setText(GloablUtils
								.getStringDesignAssess(iDesignAssess));
			}
			this.edt_constr_line_background_startcode_value.setText(itemData
					.getStartPointCode());
			this.edt_constr_line_background_endcode_value.setText(itemData
					.getEndPointCode());
			if (this.supervision_Line_BackgroundData
					.getSupervision_Line_Background_Id() > 0) {

				this.edt_constr_line_background_long_value.setText(StringUtil
						.formatNumber(supervision_Line_BackgroundData
								.getLong_Total()));

                if (supervision_Line_BackgroundData
                        .getTank_New_Total() != Constants.ID_ENTITY_DEFAULT) {
                    this.edt_line_background_design_tank_size.setText(String
                            .valueOf(supervision_Line_BackgroundData
                                    .getTank_New_Total()));
                }

				if (supervision_Line_BackgroundData.getTank_New_One_Part() >= 0) {
					this.edt_line_background_design_tank_dan1.setText(String
							.valueOf(supervision_Line_BackgroundData
									.getTank_New_One_Part()));
				}

				if (supervision_Line_BackgroundData.getTank_New_Two_Part() >= 0) {
					this.edt_line_background_design_tank_dan2.setText(String
							.valueOf(supervision_Line_BackgroundData
									.getTank_New_Two_Part()));
				}

				if (supervision_Line_BackgroundData.getTank_New_Three_Part() >= 0) {
					this.edt_line_background_design_tank_dan3.setText(String
							.valueOf(supervision_Line_BackgroundData
									.getTank_New_Three_Part()));
				}

				if (supervision_Line_BackgroundData.getTank_Old_Total() >= 0) {
					this.edt_line_background_design_tank_size_old
							.setText(String
									.valueOf(supervision_Line_BackgroundData
											.getTank_Old_Total()));
				}
				
				if (supervision_ConstrData.getDeployExpectedDay() > 0) {
					edt_constr_line_background_deployday_value
							.setText(String.valueOf(supervision_ConstrData
									.getDeployExpectedDay()));
				}

				this.iBuryType = supervision_Line_BackgroundData.getBury_Type();

				this.tv_constr_line_background_ctt_dropdown.setText(GloablUtils
						.getStringBuryType(iBuryType));

				if (supervision_Line_BackgroundData.getBury_Long() != -1) {
					this.edt_constr_line_background_cdctt.setText(StringUtil
							.formatNumber(supervision_Line_BackgroundData
									.getBury_Long()));
				}

				if (supervision_Line_BackgroundData.getMx_Total() != -1) {
					this.edt_constr_line_background_tsmx.setText(String
							.valueOf(supervision_Line_BackgroundData
									.getMx_Total()));
				}

				this.iCableType = supervision_Line_BackgroundData
						.getCable_type();

				if (iCableType == -1) {
					this.tv_constr_line_background_lc.setText(StringUtil
							.getString(R.string.text_choice1));
				} else
					this.tv_constr_line_background_lc.setText(String
							.valueOf(iCableType));

				this.edt_constr_line_background_lo
						.setText(supervision_Line_BackgroundData.getPipe_Type());

				if (supervision_Line_BackgroundData.getPipe_Number() >= 0) {
					this.edt_constr_line_background_so.setText(String
							.valueOf(supervision_Line_BackgroundData
									.getPipe_Number()));
				}

			}

			this.tv_constr_line_background_info_line_value
					.setText(this.itemData.getConstrCode());

			this.tv_constr_line_background_info_line_station_code_value
					.setText(this.itemData.getStationCode());
			/* Drop down */
			String sChoice = getResources().getString(R.string.text_choice1);
			this.listDesignInfo = GloablUtils.getListLineBackgroundInfo("");
			this.listDesignType = GloablUtils.getListLineDesignType(sChoice);
			this.listDesignAssess = GloablUtils
					.getListLineDesignAssess(sChoice);

			this.listBuryType = GloablUtils.getListLineBuryType(sChoice);

			this.listCableType = GloablUtils.getListLineCableType(sChoice);

			this.refreshListView();

			/* Set endable va disable voi cong trinh da hoan thanh */
			if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
					|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
				this.btn_constr_line_background_save.setVisibility(View.GONE);
			}
			
			//hungkm
			constr_ConstructionController = new Constr_ConstructionController(this);
			constr_ConstructionData = constr_ConstructionController
                    .getItem(supervision_ConstrData.getConstruct_Id());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ham check vailid du lieu truoc khi ghi
	 * 
	 * @return
	 */
	private boolean checkValid() {
		boolean bResult = true;
		String messege = "";
		String sError = "";
		if (this.iDesignType == Constants.SEARCH_VALUE_DEFAULT) {
			sError = StringUtil
					.getString(R.string.line_background_design_type_is_null);

			tv_constr_line_background_design_type_dropdown.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			Toast.makeText(getApplicationContext(), sError, Toast.LENGTH_LONG)
					.show();

			clearFocus();
			return false;
		}
		if (this.iDesignAssess == Constants.SEARCH_VALUE_DEFAULT) {
			sError = StringUtil
					.getString(R.string.line_background_design_assess_is_null);

			tv_constr_line_background_review_dropdown.setError(Html
					.fromHtml("<font color='green'>"
							+ getString(R.string.field_is_null) + "</font>"));
			tv_constr_line_background_review_dropdown.requestFocus();

			Toast.makeText(getApplicationContext(), sError, Toast.LENGTH_LONG)
					.show();

			clearFocus();
			return false;
		}
		
		String deployValue = edt_constr_line_background_deployday_value.getText()
		.toString().trim();
		
		if (StringUtil.isNullOrEmpty(deployValue) || deployValue.equals("0")) {
			sError = StringUtil.getString(R.string.field_is_null);
			if (deployValue.equals("0")) {
				sError = StringUtil.getString(R.string.field_is_equal_zero);
			}
			edt_constr_line_background_deployday_value.setError(Html
					.fromHtml("<font color='green'>" + sError + "</font>"));
			edt_constr_line_background_deployday_value.requestFocus();
			return false;
		}
		
		String sBGLongTotal = edt_constr_line_background_long_value.getText()
				.toString().trim();
		if (StringUtil.isNullOrEmpty(sBGLongTotal) || sBGLongTotal.equals("0")) {
			sError = StringUtil.getString(R.string.field_is_null);
			if (sBGLongTotal.equals("0")) {
				sError = StringUtil.getString(R.string.field_is_equal_zero);
			}
			edt_constr_line_background_long_value.setError(Html
					.fromHtml("<font color='green'>" + sError + "</font>"));
			edt_constr_line_background_long_value.requestFocus();
			return false;
		}

		// tab be
		if (tabHost.getCurrentTab() == 0) {
			String sNewTankSize = edt_line_background_design_tank_size
					.getText().toString().trim();
			if (StringUtil.isNullOrEmpty(sNewTankSize)) {
				// || sNewTankSize.equals("0")) {
				sError = StringUtil.getString(R.string.field_is_null);

				tabHost.setCurrentTab(0);
				edt_line_background_design_tank_size.setError(Html
						.fromHtml("<font color='green'>" + sError + "</font>"));
				edt_line_background_design_tank_size.requestFocus();
				return false;
			}

			int tankSize = Integer
					.parseInt(edt_line_background_design_tank_size.getText()
							.toString());

			int iTankNewOnePart = 0, iTankNewTwoPart = 0, iTankNewThreePart = 0;

			if (!StringUtil.isNullOrEmpty(edt_line_background_design_tank_dan1
					.getText().toString())) {
				iTankNewOnePart = Integer
						.parseInt(edt_line_background_design_tank_dan1
								.getText().toString());
			}

			if (!StringUtil.isNullOrEmpty(edt_line_background_design_tank_dan2
					.getText().toString())) {
				iTankNewTwoPart = Integer
						.parseInt(edt_line_background_design_tank_dan2
								.getText().toString());
			}

			if (!StringUtil.isNullOrEmpty(edt_line_background_design_tank_dan3
					.getText().toString())) {
				iTankNewThreePart = Integer
						.parseInt(edt_line_background_design_tank_dan3
								.getText().toString());
			}

			if (tankSize != (iTankNewOnePart + iTankNewTwoPart + iTankNewThreePart)) {
				sError = StringUtil
						.getString(R.string.line_background_design_tank_size_message);
				Toast.makeText(getApplicationContext(), sError,
						Toast.LENGTH_LONG).show();
				bResult = false;
			}

		} else {
			// tab cap
			if (tabHost.getCurrentTab() == 1) {
				if (this.iBuryType == Constants.SEARCH_VALUE_DEFAULT) {
					sError = StringUtil
							.getString(R.string.tv_constr_line_background_ctt_dropdown_is_null);
					tabHost.setCurrentTab(1);
					tv_constr_line_background_ctt_dropdown.setError(Html
							.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					tv_constr_line_background_ctt_dropdown.requestFocus();

					Toast.makeText(getApplicationContext(), sError,
							Toast.LENGTH_LONG).show();
					clearFocus();
					return false;
				}
				String sBgCDTTT = edt_constr_line_background_cdctt.getText()
						.toString().trim();
				if (StringUtil.isNullOrEmpty(sBgCDTTT) || sBgCDTTT.equals("0")) {
					sError = StringUtil.getString(R.string.field_is_null);
					if (sBgCDTTT.equals("0")) {
						sError = StringUtil
								.getString(R.string.field_is_equal_zero);
					}
					tabHost.setCurrentTab(1);
					edt_constr_line_background_cdctt.setError(Html
							.fromHtml("<font color='green'>" + sError
									+ "</font>"));
					edt_constr_line_background_cdctt.requestFocus();
					return false;
				}
				if (StringUtil.isNullOrEmpty(edt_constr_line_background_tsmx
						.getText().toString())) {
					sError = StringUtil
							.getString(R.string.text_unvalid_message);
					tabHost.setCurrentTab(1);
					edt_constr_line_background_tsmx.setError(Html
							.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					edt_constr_line_background_tsmx.requestFocus();
					return false;
				}
				if (iCableType < 0) {
					sError = StringUtil
							.getString(R.string.tv_constr_line_background_lc_is_null);
					tabHost.setCurrentTab(1);
					tv_constr_line_background_lc.setError(Html
							.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					tv_constr_line_background_lc.requestFocus();

					Toast.makeText(getApplicationContext(), sError,
							Toast.LENGTH_LONG).show();

					clearFocus();
					return false;
				}
				if (StringUtil.isNullOrEmpty(edt_constr_line_background_lo
						.getText().toString())) {
					sError = StringUtil
							.getString(R.string.text_unvalid_message);
					tabHost.setCurrentTab(1);
					edt_constr_line_background_lo.setError(Html
							.fromHtml("<font color='green'>"
									+ getString(R.string.field_is_null)
									+ "</font>"));
					edt_constr_line_background_lo.requestFocus();
					return false;
				}
				/* Kiem tra thong tin chieu dai */
				Double dTotalLong = Double
						.valueOf(edt_constr_line_background_long_value
								.getText().toString());
				Double dBuryTank = Double
						.valueOf(edt_constr_line_background_cdctt.getText()
								.toString());
				if (dTotalLong < dBuryTank) {
					sError = StringUtil
							.getString(R.string.line_background_design_long_message);
					Toast.makeText(getApplicationContext(), sError,
							Toast.LENGTH_LONG).show();
					bResult = false;
				}
			} else {
				// tab diem qua cau cong
				if (tabHost.getCurrentTab() == 2) {
					for (Special_LocationEntity itemBridge : listPointBridge) {
						messege = checkValidationSpecialLocation(itemBridge);
						if (!StringUtil.isNullOrEmpty(messege)) {
							Toast.makeText(getApplicationContext(), messege,
									Toast.LENGTH_LONG).show();
							tabHost.setCurrentTab(2);
							return false;
						}
					}
				} else {
					// tab diem qua ao ho
					for (Special_LocationEntity itemPond : listPointPond) {
						messege = checkValidationSpecialLocation(itemPond);
						if (!StringUtil.isNullOrEmpty(messege)) {
							Toast.makeText(getApplicationContext(), messege,
									Toast.LENGTH_LONG).show();
							tabHost.setCurrentTab(3);
							return false;
						}
					}

				}
			}
		}

		return bResult;
	}

	public String checkValidationSpecialLocation(
			Special_LocationEntity itemCheck) {
		String result = "";

		if (StringUtil.isNullOrEmpty(itemCheck.getFromStage())) {
			result = StringUtil
					.getString(R.string.line_background_design_from_stage_is_empty);
		} else if (StringUtil.isNullOrEmpty(itemCheck.getToStage())) {
			result = StringUtil
					.getString(R.string.line_background_design_to_stage_is_empty);
		} else if (StringUtil.isNullOrEmpty(itemCheck.getSupplieType())) {
			result = StringUtil
					.getString(R.string.line_background_design_supplie_type_is_empty);
		} else if (itemCheck.getSpecialLocationType()
                == Constants.SPECIAL_LOCATION_TYPE.POINT_TO_BRIDGE
				&& StringUtil.isNullOrEmpty(itemCheck.getBridgeName())) {

			result = StringUtil
					.getString(R.string.line_background_design_bridge_name_is_empty);
		}

		return result;
	}

	private void saveDataDesign() {
		try {
			saveOrDel = true;
			this.supervisionLineFiberController = new Supervision_Line_BG_FiberController(
					this);
			supervisionLBGController = new Supervision_Line_BG_TankController(
					this);

			this.listLineBGFiber = this.supervisionLineFiberController
					.getAllFiberBySupervistionLineBackground(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());

			listSupervisionGS = supervisionLBGController
					.getAllTankGSBySupervistionLineBackground(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());

			long idUser = GlobalInfo.getInstance().getUserId();
			@SuppressWarnings("unused")
			boolean bSaveStatus = false;
			double iLongTotal = Constants.ID_ENTITY_DEFAULT;
			iTotalNewTank = Constants.ID_ENTITY_DEFAULT;
			iTotalTank = Constants.ID_ENTITY_DEFAULT;
			long iTankNewOnePart = Constants.ID_ENTITY_DEFAULT;
			long iTankNewTwoPart = Constants.ID_ENTITY_DEFAULT;
			long iTankNewThreePart = Constants.ID_ENTITY_DEFAULT;
			long iTankOldTotal = Constants.ID_ENTITY_DEFAULT;
			double iBuryLong = Constants.ID_ENTITY_DEFAULT;
			long iMXTotal = Constants.ID_ENTITY_DEFAULT;
			iCableType = Constants.ID_ENTITY_DEFAULT;
			long iPipeNumber = Constants.ID_ENTITY_DEFAULT;
			String sDesignDescription = edt_constr_line_background_inter_value
					.getText().toString();
			supervision_ConstrData.setDesign_Description(sDesignDescription);
			supervision_ConstrData.setDesign_Type(iDesignType);
			supervision_ConstrData.setDesign_Assess(iDesignAssess);
			supervision_ConstrData.setConstruct_Id(this.itemData
					.getConstructId());
			supervision_ConstrData.setDeployExpectedDay(Integer
					.parseInt(edt_constr_line_background_deployday_value.getText()
							.toString()));
			supervision_ConstrData
					.setSupervision_Progress(Constants.SUPERVISION_PROGRESS.DOING);

			// Sua thong tin thiet ke
			bSaveStatus = this.supervision_ConstrController
					.updateDesign(supervision_ConstrData);

			if (this.supervision_Line_BackgroundData == null) {
				this.supervision_Line_BackgroundData = new Supervision_Line_BackgroundEntity();
			}

			String sLongTotal = edt_constr_line_background_long_value.getText()
					.toString();

			iLongTotal = StringUtil.convertToDoubleRound2(sLongTotal);
			this.supervision_Line_BackgroundData.setLong_Total(iLongTotal);

			// save tab be
			if (tabHost.getCurrentTab() == 0) {

				String sTotalTank = edt_line_background_design_tank_size
						.getText().toString();

				String sTankNewOnePart = edt_line_background_design_tank_dan1
						.getText().toString();
				if (!StringUtil.isNullOrEmpty(sTankNewOnePart)) {
					iTankNewOnePart = Long.valueOf(sTankNewOnePart);
				}

				String sTankNewTwoPart = edt_line_background_design_tank_dan2
						.getText().toString();

				if (!StringUtil.isNullOrEmpty(sTankNewTwoPart)) {
					iTankNewTwoPart = Long.valueOf(sTankNewTwoPart);
				}

				String sTankNewThreePart = edt_line_background_design_tank_dan3
						.getText().toString();

				if (!StringUtil.isNullOrEmpty(sTankNewThreePart)) {
					iTankNewThreePart = Long.valueOf(sTankNewThreePart);
				}

				String sTankOldTotal = edt_line_background_design_tank_size_old
						.getText().toString();
				if (!StringUtil.isNullOrEmpty(sTankOldTotal)) {
					iTankOldTotal = Long.valueOf(sTankOldTotal);
				}

				iTotalNewTank = Long.valueOf(sTotalTank);
				iTotalTank = Long.valueOf(sTotalTank)
						+ Long.valueOf(iTankOldTotal);

				this.supervision_Line_BackgroundData
						.setTank_New_Total(iTotalNewTank);
				this.supervision_Line_BackgroundData
						.setTank_New_One_Part(iTankNewOnePart);
				this.supervision_Line_BackgroundData
						.setTank_New_Two_Part(iTankNewTwoPart);
				this.supervision_Line_BackgroundData
						.setTank_New_Three_Part(iTankNewThreePart);
				this.supervision_Line_BackgroundData
						.setTank_Old_Total(iTankOldTotal);

				if (this.supervision_Line_BackgroundData
						.getSupervision_Line_Background_Id() > 0) {
					this.supervision_Line_BackgroundData
							.setSync_Status(Constants.SYNC_STATUS.ADD);

					if ((iTotalTank < listSupervisionGS.size() && listSupervisionGS
							.size() > 0)) {
						this.showAskOptionDialog(
								StringUtil
										.getString(R.string.text_delete_title),
								StringUtil
										.getString(R.string.text_confirm_tank_num));

						return;
					} else
						bSaveStatus = supervision_Line_BackgroundController
								.updateTank(supervision_Line_BackgroundData);
				} else {
					// Tao moi ban ghi giam sat tuyen ngam
					this.supervision_Line_BackgroundData
							.setSupervision_Constr_Id(this.supervision_ConstrData
									.getSupervision_Constr_Id());
					long idSupervion = Ktts_KeyController
							.getInstance()
							.getKttsNextKey(
									Supervision_Line_BackgroundField.TABLE_NAME);
					if (idSupervion == 0) {
						Toast.makeText(this,
								StringUtil.getString(R.string.text_out_of_key),
								Toast.LENGTH_SHORT).show();
						return;
					}

					this.supervision_Line_BackgroundData
							.setSupervision_Line_Background_Id(idSupervion);

					this.supervision_Line_BackgroundData
							.setSync_Status(Constants.SYNC_STATUS.ADD);

					this.supervision_Line_BackgroundData
							.setIsActive(Constants.ISACTIVE.ACTIVE);

					this.supervision_Line_BackgroundData.setProcessId(0);
					this.supervision_Line_BackgroundData.setEmployeeId(idUser);

					bSaveStatus = this.supervision_Line_BackgroundController
							.addItemTank(supervision_Line_BackgroundData);

				}
			} else {
				// save tab cap
				if (tabHost.getCurrentTab() == 1) {
					String sBuryLong = edt_constr_line_background_cdctt
							.getText().toString();
					iBuryLong = StringUtil.convertToDoubleRound2(sBuryLong);

					String sMXTotal = edt_constr_line_background_tsmx.getText()
							.toString();
					iMXTotal = Long.valueOf(sMXTotal);

					String sPipeType = edt_constr_line_background_lo.getText()
							.toString();

					String sCableType = tv_constr_line_background_lc.getText()
							.toString();
					iCableType = StringUtil.convertToInt(sCableType);

					String sPipeNumber = edt_constr_line_background_so
							.getText().toString();

					if (!StringUtil.isNullOrEmpty(sPipeNumber)) {
						iPipeNumber = Long.valueOf(sPipeNumber);
					}

					this.supervision_Line_BackgroundData
							.setBury_Type(iBuryType);
					this.supervision_Line_BackgroundData
							.setBury_Long(iBuryLong);
					this.supervision_Line_BackgroundData
							.setCable_type(iCableType);
					this.supervision_Line_BackgroundData
							.setPipe_Type(sPipeType);
					this.supervision_Line_BackgroundData
							.setPipe_Number(iPipeNumber);
					this.supervision_Line_BackgroundData.setMx_Total(iMXTotal);
					if (this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id() > 0) {
						this.supervision_Line_BackgroundData
								.setSync_Status(Constants.SYNC_STATUS.ADD);

						if ((iCableType < listLineBGFiber.size() && listLineBGFiber
								.size() > 0)) {
							this.showAskOptionDialog(
									StringUtil
											.getString(R.string.text_delete_title),
									StringUtil
											.getString(R.string.text_confirm_fiber_num));

							return;
						} else
							bSaveStatus = supervision_Line_BackgroundController
									.updateCable(supervision_Line_BackgroundData);
					} else {
						// Tao moi ban ghi giam sat tuyen ngam
						this.supervision_Line_BackgroundData
								.setSupervision_Constr_Id(this.supervision_ConstrData
										.getSupervision_Constr_Id());
						long idSupervion = Ktts_KeyController
								.getInstance()
								.getKttsNextKey(
										Supervision_Line_BackgroundField.TABLE_NAME);
						if (idSupervion == 0) {
							Toast.makeText(
									this,
									StringUtil
											.getString(R.string.text_out_of_key),
									Toast.LENGTH_SHORT).show();
							return;
						}

						this.supervision_Line_BackgroundData
								.setSupervision_Line_Background_Id(idSupervion);

						this.supervision_Line_BackgroundData
								.setSync_Status(Constants.SYNC_STATUS.ADD);

						this.supervision_Line_BackgroundData
								.setIsActive(Constants.ISACTIVE.ACTIVE);

						this.supervision_Line_BackgroundData.setProcessId(0);
						this.supervision_Line_BackgroundData
								.setEmployeeId(idUser);

						bSaveStatus = this.supervision_Line_BackgroundController
								.addItemCable(supervision_Line_BackgroundData);

					}
				} else {
					if (this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id() > 0) {
						bSaveStatus = supervision_Line_BackgroundController
								.updateTank(supervision_Line_BackgroundData);
					} else {
						this.supervision_Line_BackgroundData
								.setTank_New_Total(Constants.ID_ENTITY_DEFAULT);
						this.supervision_Line_BackgroundData
								.setTank_New_One_Part(Constants.ID_ENTITY_DEFAULT);
						this.supervision_Line_BackgroundData
								.setTank_New_Two_Part(Constants.ID_ENTITY_DEFAULT);
						this.supervision_Line_BackgroundData
								.setTank_New_Three_Part(Constants.ID_ENTITY_DEFAULT);
						this.supervision_Line_BackgroundData
								.setTank_Old_Total(Constants.ID_ENTITY_DEFAULT);

						this.supervision_Line_BackgroundData
								.setSupervision_Constr_Id(this.supervision_ConstrData
										.getSupervision_Constr_Id());
						long idSupervion = Ktts_KeyController
								.getInstance()
								.getKttsNextKey(
										Supervision_Line_BackgroundField.TABLE_NAME);
						if (idSupervion == 0) {
							Toast.makeText(
									this,
									StringUtil
											.getString(R.string.text_out_of_key),
									Toast.LENGTH_SHORT).show();
							return;
						}

						this.supervision_Line_BackgroundData
								.setSupervision_Line_Background_Id(idSupervion);

						this.supervision_Line_BackgroundData
								.setSync_Status(Constants.SYNC_STATUS.ADD);

						this.supervision_Line_BackgroundData
								.setIsActive(Constants.ISACTIVE.ACTIVE);

						this.supervision_Line_BackgroundData.setProcessId(0);
						this.supervision_Line_BackgroundData
								.setEmployeeId(idUser);

						bSaveStatus = this.supervision_Line_BackgroundController
								.addItemTank(supervision_Line_BackgroundData);
					}
					// save tab diem qua cau cong
					if (tabHost.getCurrentTab() == 2) {
						savePointToBridge();
					} else {
						// save tab diem qua ao ho
						if (tabHost.getCurrentTab() == 3) {
							savePointToPond();
						}
					}
				}
			}

			this.setData();
			Toast.makeText(
					this,
					StringUtil
							.getString(R.string.line_background_save_susses_message),
					Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
		}
	}

	// save tab diem qua cau cong
	public void savePointToBridge() {
		try {
			long idUser = GlobalInfo.getInstance().getUserId();
			for (Special_LocationEntity itemToBridge : listPointBridge) {

				// neu them moi du lieu: insert
				if (itemToBridge.isNew()) {
					Special_LocationEntity itemAdd = new Special_LocationEntity();
					itemAdd.setBridgeName(itemToBridge.getBridgeName());
					itemAdd.setFromStage(itemToBridge.getFromStage());
					itemAdd.setToStage(itemToBridge.getToStage());
					itemAdd.setSupplieType(itemToBridge.getSupplieType());

					itemAdd.setSupervisionLineBackgroundId(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());
					itemAdd.setSync_Status(Constants.SYNC_STATUS.ADD);
					itemAdd.setIsActive(Constants.ISACTIVE.ACTIVE);
					itemAdd.setProcessId(0);
					itemAdd.setEmployeeId(idUser);
					long idAddBridge = Ktts_KeyController.getInstance()
							.getKttsNextKey(Special_LocationField.TABLE_NAME);

					if (idAddBridge == 0) {
						outOfKey = true;
						return;
					} else
						outOfKey = false;
					itemAdd.setSpecialLocationId(idAddBridge);
					itemAdd.setSpecialLocationType(SPECIAL_LOCATION_TYPE.POINT_TO_BRIDGE);

					specialLocationController.insertItemBridge(itemAdd);
				} else {

					// neu sua du lieu: cap nhat lai
					if (itemToBridge.isEdited()) {
						itemToBridge.setSync_Status(Constants.SYNC_STATUS.ADD);
						this.specialLocationController
								.updateAllColumn(itemToBridge);
					}
				}
			}
		} catch (Exception e) {
			Log.e("error", e.toString());
			// Toast.makeText(this,
			// StringUtil.getString(R.string.text_update_error),
			// Toast.LENGTH_SHORT).show();
			// this.showDialog(StringUtil.getString(R.string.text_update_error));
		}

	}

	// save tab diem qua ao ho
	public void savePointToPond() {
		try {
			long idUser = GlobalInfo.getInstance().getUserId();
			for (Special_LocationEntity itemToPond : listPointPond) {
				// neu them moi du lieu: insert
				if (itemToPond.isNew()) {
					Special_LocationEntity itemAdd = new Special_LocationEntity();
					itemAdd.setBridgeName(itemToPond.getBridgeName());
					itemAdd.setFromStage(itemToPond.getFromStage());
					itemAdd.setToStage(itemToPond.getToStage());
					itemAdd.setSupplieType(itemToPond.getSupplieType());

					itemAdd.setSupervisionLineBackgroundId(this.supervision_Line_BackgroundData
							.getSupervision_Line_Background_Id());
					itemAdd.setSync_Status(Constants.SYNC_STATUS.ADD);
					itemAdd.setIsActive(Constants.ISACTIVE.ACTIVE);
					itemAdd.setProcessId(0);
					itemAdd.setEmployeeId(idUser);
					long idAddBridge = Ktts_KeyController.getInstance()
							.getKttsNextKey(Special_LocationField.TABLE_NAME);

					if (idAddBridge == 0) {
						outOfKey = true;
						return;
					} else
						outOfKey = false;
					itemAdd.setSpecialLocationId(idAddBridge);
					itemAdd.setSpecialLocationType(SPECIAL_LOCATION_TYPE.POINT_TO_POND);

					specialLocationController.insertItemBridge(itemAdd);
				} else {

					// neu sua du lieu: cap nhat lai
					if (itemToPond.isEdited()) {
						itemToPond.setSync_Status(Constants.SYNC_STATUS.ADD);
						this.specialLocationController
								.updateAllColumn(itemToPond);
					}
				}
			}
		} catch (Exception e) {
			Log.e("error", e.toString());
		}
	}

	/**
	 * Ham tao header cua tab
	 * 
	 * @param text
	 * @return
	 */
	private TextView makeTabIndicator(String text) {
        TelephonyManager manager = (TelephonyManager)this.getSystemService(Context.TELEPHONY_SERVICE);
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
		tabView.setWidth(tabWidth);
		tabView.setText(text);
		tabView.setTextColor(getResources().getColor(R.color.white_color));
		tabView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
		tabView.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tab_indicator));
		tabView.setPadding(13, 0, 13, 10);
		return tabView;
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
			this.supervision_Line_BackgroundData = supervision_Line_BackgroundController
					.getItemBySupervisionConstrId(this.supervision_ConstrData
							.getSupervision_Constr_Id());
			refreshListView();
			
			SyncTask.closeDialog();
//			closeProgressDialog();
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
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					saveDataDesign();

				}
			});

			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			closeProgressDialog();
		}

	}

}