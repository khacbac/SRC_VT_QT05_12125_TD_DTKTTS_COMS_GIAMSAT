package com.viettel.ktts;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.actionbar.Header;
import com.viettel.actionbar.Menu_DropdownPopup;
import com.viettel.common.ActionEvent;
import com.viettel.common.ActionEventConstant;
import com.viettel.common.GlobalInfo;
import com.viettel.common.ModelEvent;
import com.viettel.constants.Constants;
import com.viettel.constants.IntentConstants;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_CNVController;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.Constr_ObStructionEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

import java.util.List;

/**
 * @author hungtn
 * 
 */
public class SupervisionBts_CNV_List_Activity extends
		SupervisionBtsBaseActivity {
	private View spvBTS_CatWorkView;
	private Constr_Construction_EmployeeEntity constr_ConstructionItem;
	private Supervision_BtsEntity btsEntity;

	/* Hien thi popup */
	private Menu_DropdownPopup dropdownPopupMenuInfomation;

	private List<DropdownItemEntity> listInfomation = null;

	private int isInfomation = 1;

	/**
	 * combobox
	 */
	// thong tin thiet ke
	private RelativeLayout supervision_bts_cnv_cb_thietke;
	private TextView supervision_bts_cnv_thietke;

	/**
	 * text view
	 */
	// ma tram
	private TextView supervision_bts_tv_matram;
	// ma cong trinh
	private TextView supervision_bts_tv_mact;

	/**
	 * button
	 */
	private ImageButton supervision_bts_cnv_add;

	// listview

	private ListView lvCnv;
	private ItemSupervision_CNV_List listAdapter;
	private List<Constr_ObStructionEntity> listData;
	//KienPV add new 10/09/2016
	private boolean clickItem = false;
	private Constr_ObStructionEntity itemDelete;
	//---

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.supervision_bts_cat_work_activity);
		//setTitle(getString(R.string.supervision_bts_title));
		
		initComponent();
		// setHeader();

		isInfomation = getIntent().getExtras().getInt(
				IntentConstants.INTENT_DESIGNINFO);
		constr_ConstructionItem = getConstr_Construction_Employee();
		initData();

		setData();
		closeProgressDialog();

	}
	public void setData() {

		supervision_bts_tv_matram.setText(constr_ConstructionItem
				.getStationCode());
		supervision_bts_tv_mact.setText(String.valueOf(constr_ConstructionItem
				.getConstrCode()));

		Supervision_BtsController bts_Controller = new Supervision_BtsController(
				this);
		btsEntity = bts_Controller.getSupervisionBts(constr_ConstructionItem
				.getSupervision_Constr_Id());

	}

	public void refreshForm() {
		// KienPV add new 9/9/2016
		// comment code
		// String sChoice = getResources().getString(R.string.text_choice1);

		// ---
	}

	public void initData() {

		String sChoice = "";
		// KienPV add new 9/9/2016
		// sChoice = getResources().getString(R.string.text_choice1);
		switch (constr_ConstructionItem.getSupvConstrType()) {
		case Constants.CONSTR_TYPE.BTS:
			setTitle(getString(R.string.supervision_bts_title));
			listInfomation = GloablUtils.getListBTSInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringBTSInfo(isInfomation));
			break;
		case Constants.CONSTR_TYPE.LINE_BACKGROUND:
			setTitle(getString(R.string.line_background_header_title));
			listInfomation = GloablUtils.getListLineBackgroundInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringLineBackgroundInfo(isInfomation));
			break;
		case Constants.CONSTR_TYPE.LINE:
			setTitle(getString(R.string.line_background_header_title_brcd_mt));
			break;
		case Constants.CONSTR_TYPE.LINE_HANG:
			setTitle(getString(R.string.line_hang_header_title));
			listInfomation = GloablUtils.getListLineHangInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringLineHangInfo(isInfomation));
			break;
		case Constants.CONSTR_TYPE.SH:
			setTitle(getString(R.string.line_background_header_title_sub_mt));
			listInfomation = GloablUtils.getListSubInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringSubInfo(isInfomation));
			break;
		case Constants.CONSTR_TYPE.BRCD:
			setTitle(getString(R.string.line_background_header_title_brcd_mt));
			listInfomation = GloablUtils.getListbrcdBackgroundInfo(sChoice);
			supervision_bts_cnv_thietke.setText(GloablUtils
					.getStringBRCDBackgroundInfo(isInfomation));
			break;
		default:
			break;
		}
		//--
		// listInfomation = GloablUtils.getListBTSInfo(sChoice);
		//
		// sChoice = getResources().getString(R.string.text_choice1);
		// supervision_bts_cnv_thietke.setText(GloablUtils
		// .getStringBTSInfo(isInfomation));

		// --------
		// Lay du lieu tu trong db
		Supervision_CNVController cnvController = new Supervision_CNVController(
				this);
		Bundle bundleMonitorData = new Bundle();
		bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
				constr_ConstructionItem);
		bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
				isInfomation);
		listData = cnvController.getAllCNV(String.valueOf(constr_ConstructionItem.getConstructId()));
		listAdapter = new ItemSupervision_CNV_List(this, listData);
		listAdapter.setBundleMonitorData(bundleMonitorData);
		//KienPV add new 10/09/2016
		listAdapter.setOnItemClick(new ItemSupervision_CNV_List.onItemClick() {
			
			@Override
			public void onDelete(Constr_ObStructionEntity item) {
				clickItem = true;
				itemDelete = item;
				ConfirmDialog confirmSave = new ConfirmDialog(SupervisionBts_CNV_List_Activity.this, StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
			}
		});
		//---
		lvCnv.setAdapter(listAdapter);
	}

	public void initComponent() {
		spvBTS_CatWorkView = addView(R.layout.supervision_cnv_list_activity,
				R.id.rlspv_bt_catwork);

		// combobox
		setSupervision_bts_cnv_cb_thietke((RelativeLayout) spvBTS_CatWorkView
				.findViewById(R.id.rl_supervision_bts_cnv_search_thietke));
		supervision_bts_cnv_thietke = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.tv_cnv_dropdown);
		supervision_bts_cnv_thietke.setOnClickListener(this);

		// textview
		supervision_bts_tv_matram = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.tv_cnv_station_code_value);
		supervision_bts_tv_mact = (TextView) spvBTS_CatWorkView
				.findViewById(R.id.tv_cnv_value);

		// button
		supervision_bts_cnv_add = (ImageButton) spvBTS_CatWorkView
				.findViewById(R.id.btn_cnv_add);
		lvCnv = (ListView) spvBTS_CatWorkView.findViewById(R.id.lv_cnv_list);

		supervision_bts_cnv_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// isInfomation = dropdownItem.getId();

				Bundle bundleMonitorData = new Bundle();
				bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
						constr_ConstructionItem);
				bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
						isInfomation);

				Intent intent = new Intent(
						SupervisionBts_CNV_List_Activity.this,
						SupervisionBts_CNV_New_Activity.class);
				intent.putExtras(bundleMonitorData);
				startActivity(intent);
			}
		});

	}

	public void clearFocus() {
		// supervision_bts_catwork_thicongphongmay_diengiai
		// .setFocusableInTouchMode(false);
		// supervision_bts_catwork_thicongphongmay_diengiai.clearFocus();
		//
		// supervision_bts_catwork_thicongnhamayno_diengiai
		// .setFocusableInTouchMode(false);
		// supervision_bts_catwork_thicongnhamayno_diengiai.clearFocus();
		//
		// supervision_bts_catwork_thicongkeoday_diengiai
		// .setFocusableInTouchMode(false);
		// supervision_bts_catwork_thicongkeoday_diengiai.clearFocus();
		//
		// supervision_bts_catwork_thicongphongmay_thietke
		// .setFocusableInTouchMode(false);
		// supervision_bts_catwork_thicongphongmay_thietke.clearFocus();
		//
		// supervision_bts_catwork_thicongphongmay_thucte
		// .setFocusableInTouchMode(false);
		// supervision_bts_catwork_thicongphongmay_thucte.clearFocus();
		//
		// supervision_bts_catwork_thicongnhamayno_thietke
		// .setFocusableInTouchMode(false);
		// supervision_bts_catwork_thicongnhamayno_thietke.clearFocus();
		//
		// supervision_bts_catwork_thicongnhamayno_thucte
		// .setFocusableInTouchMode(false);
		// supervision_bts_catwork_thicongnhamayno_thucte.clearFocus();
		//
		// supervision_bts_catwork_thicongphongmay_diengiai
		// .setFocusableInTouchMode(true);
		// supervision_bts_catwork_thicongnhamayno_diengiai
		// .setFocusableInTouchMode(true);
		// supervision_bts_catwork_thicongkeoday_diengiai
		// .setFocusableInTouchMode(true);
		// supervision_bts_catwork_thicongphongmay_thietke
		// .setFocusableInTouchMode(true);
		// supervision_bts_catwork_thicongphongmay_thucte
		// .setFocusableInTouchMode(true);
	}

	// private TextView makeTabIndicator(String text) {
	// TextView tabView = new TextView(this);
	// LayoutParams lp3 = new LayoutParams(LayoutParams.WRAP_CONTENT,
	// tabHeight, 1);
	// lp3.setMargins(1, 0, 1, 0);
	// tabView.setLayoutParams(lp3);
	// // tabView.setWidth(tabWidth);
	// tabView.setText(text);
	// tabView.setTextColor(getResources().getColor(R.color.black_color));
	// tabView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
	// tabView.setBackgroundDrawable(getResources().getDrawable(
	// R.drawable.tab_indicator));
	// tabView.setPadding(13, 0, 13, 0);
	// return tabView;
	// }

	public Constr_Construction_EmployeeEntity getConstr_Construction_Employee() {
		return (Constr_Construction_EmployeeEntity) getIntent().getExtras()
				.getSerializable(IntentConstants.INTENT_DATA);
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
			// confirmSave = new ConfirmDialog(this,
			// StringUtil.getString(R.string.text_confirm_save),
			// OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			// confirmSave.show();
			break;

		// click combobox thong tin
		case R.id.tv_cnv_dropdown:

			this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
					this.listInfomation);

			dropdownPopupMenuInfomation.show(v);
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

				Bundle bundleMonitorData = new Bundle();
				bundleMonitorData.putSerializable(IntentConstants.INTENT_DATA,
						constr_ConstructionItem);
				bundleMonitorData.putInt(IntentConstants.INTENT_DESIGNINFO,
						isInfomation);

				this.supervision_bts_cnv_thietke.setText(dropdownItem
						.getTitle());
				this.dropdownPopupMenuInfomation.dismiss();

				this.showProgressDialog(StringUtil.getString(R.string.text_loading));
				// KienPV add new  9/9/2016
				gotoSupvConstrType(constr_ConstructionItem.getSupvConstrType(),isInfomation,bundleMonitorData);
				//--
				
				// KienPV add new 9/9/2016 comment code 
//				 if (isInfomation == Constants.BTS_INFO.THIET_TKE_INFO) {
//				 gotoSupervisionBtsActivity(bundleMonitorData);
//				 }
//				
//				 if (isInfomation ==
//				 Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO) {
//				 gotoSupervisionBtsPillarActivity(bundleMonitorData);
//				 }
//				 if (isInfomation ==
//				 Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO) {
//				 gotoSupervisionBtsCatWorkActivity(bundleMonitorData);
//				 }
//				 if (isInfomation == Constants.BTS_INFO.KEO_DIEN_INFO) {
//				 gotoSupervisionBtsPowerPoleActivity(bundleMonitorData);
//				 }
//				 if (isInfomation == Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO)
//				 {
//				 gotoSupervisionBtsEquipActivity(bundleMonitorData);
//				 }
//				 if (isInfomation == Constants.BTS_INFO.LAP_DAT_VIBA_INFO) {
//				
//				 gotoSupervisionBtsVibaActivity(bundleMonitorData);
//				 }
//				 if (isInfomation == Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO)
//				 {
//				
//				 gotoSupervisionBtsMeasureActivity(bundleMonitorData);
//				 }
//				 if (isInfomation == Constants.BTS_INFO.MEASURE_CONSTR_INFO) {
//				 gotoSupervisionMeasureConstrActivity(bundleMonitorData);
//				 }
//				
//				 // HungTN add new 24/08/2016
//				 if (isInfomation == Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG)
//				 {
//				 gotoSupervisionCNDTCActivity(bundleMonitorData);
//				 }
//				 // ---
//				 // KienPV add new 08/09/2016
//				 if(isInfomation == Constants.BTS_INFO.CAP_NHAT_VUONG){
//				 closeProgressDialog();
//				 return;
//				 }
//				 //--
//				 if (isInfomation == Constants.BTS_INFO.KET_LUAN_INFO) {
//				
//				 gotoSupervisionBtsKLActivity(bundleMonitorData);
//				 }
				// ---
				//if()finish();
			}

		case OnEventControlListener.EVENT_CONFIRM_OK:
			if(clickItem){
				clickItem = false;
				if(itemDelete != null){
					DeleteAsyncTask task = new DeleteAsyncTask();
					task.execute();
				}
				listAdapter.notifyDataSetChanged();
			}else{
				SaveAsyncTask task = new SaveAsyncTask();
				task.execute();
			}
			break;

		case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
			this.selectPhoto();
			break;
		case OnEventControlListener.EVENT_COMPLETE_PROGRESS:

			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}
    // KienPV add new 09/09/2016
	public void gotoSupvConstrType(int type, int itemId,Bundle bundleMonitorData) {
		switch (type) {
		case Constants.CONSTR_TYPE.BTS:
			gotoBtsActivity(bundleMonitorData, type);
//				switch (itemId) {
//				case Constants.BTS_INFO.THIET_TKE_INFO:
//					gotoSupervisionBtsActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.THI_CONG_TIEP_DIA_INFO:
//					gotoSupervisionBtsPillarActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.THI_CONG_PHONG_MAY_NHA_MAY_NO_INFO:
//					gotoSupervisionBtsCatWorkActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.KEO_DIEN_INFO:
//					gotoSupervisionBtsPowerPoleActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.LAP_DAT_THIET_BI_INFO:
//					gotoSupervisionBtsEquipActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.LAP_DAT_VIBA_INFO:
//					gotoSupervisionBtsVibaActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.THI_CONG_HAN_NOI_INFO:
//					gotoSupervisionBtsMeasureActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.MEASURE_CONSTR_INFO:
//					 gotoSupervisionMeasureConstrActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.CAP_NHAT_DOI_THI_CONG:
//					gotoSupervisionCNDTCActivity(bundleMonitorData);
//					break;
//				case Constants.BTS_INFO.CAP_NHAT_VUONG:
//					 closeProgressDialog();
//					break;
//				case Constants.BTS_INFO.KET_LUAN_INFO:
//					gotoSupervisionBtsKLActivity(bundleMonitorData);
//					break;
//				default:
//					break;
//				}
			break;
		case Constants.CONSTR_TYPE.LINE_BACKGROUND:
			switch (itemId) {
			case Constants.LINE_BACKGROUND_INFO.THIE_TKE_INFO:
				gotoSupervisionBtsActivity(bundleMonitorData);
				break;
			case Constants.LINE_BACKGROUND_INFO.VI_TRI_DAC_BIET_INFO:
			case Constants.LINE_BACKGROUND_INFO.BE_CAP_INFO:
			case Constants.LINE_BACKGROUND_INFO.CONG_RANH_CAP_INFO:
			case Constants.LINE_BACKGROUND_INFO.KEO_CAP_INFO:
			case Constants.LINE_BACKGROUND_INFO.HAN_NOI_DO_KIEM_INFO:
			case Constants.LINE_BACKGROUND_INFO.CAP_NHAT_VUONG:
				break;
			case Constants.LINE_BACKGROUND_INFO.MEASURE_CONSTR_INFO:
				 gotoSupervisionMeasureConstrActivity(bundleMonitorData);
				break;
			case Constants.LINE_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG:
				gotoSupervisionCNDTCActivity(bundleMonitorData);
				break;
			case Constants.LINE_BACKGROUND_INFO.KET_LUAN_INFO:
				gotoSupervisionBtsKLActivity(bundleMonitorData);
				break;			
			default:
				break;
			}
			
			break;
		case Constants.CONSTR_TYPE.LINE_HANG:
				switch (itemId) {
				case Constants.LINE_HANG_INFO.THIE_TKE_INFO:		
					gotoSupervisionBtsActivity(bundleMonitorData);
					break;
				case Constants.LINE_HANG_INFO.GS_COT_INFO:
				case Constants.LINE_HANG_INFO.GS_CAP_INFO:
				case Constants.LINE_HANG_INFO.GS_HNDK_INFO:
				case Constants.LINE_HANG_INFO.CAP_NHAT_VUONG:
					break;
				case Constants.LINE_HANG_INFO.MEASURE_CONSTR_INFO:
					gotoSupervisionMeasureConstrActivity(bundleMonitorData);
					break;
				case Constants.LINE_HANG_INFO.CAP_NHAT_DOI_THI_CONG:
					gotoSupervisionCNDTCActivity(bundleMonitorData);
					break;				
				case Constants.LINE_HANG_INFO.KET_LUAN_INFO:
					gotoSupervisionBtsKLActivity(bundleMonitorData);
					break;
				default:
					break;
				}
			break;
		case Constants.CONSTR_TYPE.BRCD:
			switch (itemId) {
			case Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK:
			case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC:
			case Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC:
			case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_NHANH:
			case Constants.BRCD_BACKGROUND_INFO.TU_NHANH:
			case Constants.BRCD_BACKGROUND_INFO.DROP_GO:
			case Constants.BRCD_BACKGROUND_INFO.TU_THUE_BAO:
				// no method
				break;
			case Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_VUONG:
				break;
			case Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_DOI_THI_CONG:
				gotoSupervisionCNDTCActivity(bundleMonitorData);
				break;
			case Constants.BRCD_BACKGROUND_INFO.KET_LUAN_INFO:
				gotoSupervisionBtsKLActivity(bundleMonitorData);
				break;
			default:
				break;
			}
			break;
		case Constants.CONSTR_TYPE.SH:
			 switch (itemId) {
			case Constants.SUBHEADEND_INFO.GIAM_SAT_LAP_DAT_THIET_BI:
			case Constants.SUBHEADEND_INFO.CAP_NHAT_VUONG:
				break;
			case Constants.SUBHEADEND_INFO.KET_LUAN_INFO:
				gotoSupervisionBtsKLActivity(bundleMonitorData);
				break;
			case Constants.SUBHEADEND_INFO.CAP_NHAT_DOI_THI_CONG:
				gotoSupervisionCNDTCActivity(bundleMonitorData);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
   //---
	class SaveAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// confirmSave.dismiss();
			showProgressDialog(StringUtil.getString(R.string.text_progcessing));
			// KienPV add new 9/9/2016
			switch (constr_ConstructionItem.getSupvConstrType()) {
				case Constants.CONSTR_TYPE.BTS:
					if(isInfomation == Constants.BTS_INFO.CAP_NHAT_VUONG){closeProgressDialog();}
					break;
				case Constants.CONSTR_TYPE.BRCD:
					switch (isInfomation) {
					case Constants.BRCD_BACKGROUND_INFO.THONG_TIN_TK:
					case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC:
					case Constants.BRCD_BACKGROUND_INFO.MANG_SONG_TRUC:
					case Constants.BRCD_BACKGROUND_INFO.KEO_CAP_NHANH:
					case Constants.BRCD_BACKGROUND_INFO.TU_NHANH:
					case Constants.BRCD_BACKGROUND_INFO.DROP_GO:
					case Constants.BRCD_BACKGROUND_INFO.TU_THUE_BAO:
					case Constants.BRCD_BACKGROUND_INFO.CAP_NHAT_VUONG:
						closeProgressDialog();
						break;
					default:
						break;
					}				
					break;
				case Constants.CONSTR_TYPE.SH:
					switch (isInfomation) {
					case Constants.SUBHEADEND_INFO.GIAM_SAT_LAP_DAT_THIET_BI:
					case Constants.SUBHEADEND_INFO.CAP_NHAT_VUONG:
						closeProgressDialog();
						break;
					default:
						break;
					}
					break;
				case Constants.CONSTR_TYPE.LINE_BACKGROUND:
					switch (isInfomation) {
						case Constants.LINE_BACKGROUND_INFO.VI_TRI_DAC_BIET_INFO:
						case Constants.LINE_BACKGROUND_INFO.BE_CAP_INFO:
						case Constants.LINE_BACKGROUND_INFO.CONG_RANH_CAP_INFO:
						case Constants.LINE_BACKGROUND_INFO.KEO_CAP_INFO:
						case Constants.LINE_BACKGROUND_INFO.HAN_NOI_DO_KIEM_INFO:
						case Constants.LINE_BACKGROUND_INFO.CAP_NHAT_VUONG:
							
							closeProgressDialog();
							break;
						default:
							break;
					}
					break;
				case Constants.CONSTR_TYPE.LINE_HANG:
					switch (isInfomation) {
						case Constants.LINE_HANG_INFO.GS_COT_INFO:
						case Constants.LINE_HANG_INFO.GS_CAP_INFO:
						case Constants.LINE_HANG_INFO.GS_HNDK_INFO:
						case Constants.LINE_HANG_INFO.CAP_NHAT_VUONG:
							closeProgressDialog();
							break;
						default:
							break;
					}
					break;
			default:
				break;
			}
			//--
		}

		@Override
		protected Void doInBackground(Void... params) {
			// saveData();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

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
				requestSync(SupervisionBts_CNV_List_Activity.this);
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

	public RelativeLayout getSupervision_bts_cnv_cb_thietke() {
		return supervision_bts_cnv_cb_thietke;
	}

	public void setSupervision_bts_cnv_cb_thietke(
			RelativeLayout supervision_bts_cnv_cb_thietke) {
		this.supervision_bts_cnv_cb_thietke = supervision_bts_cnv_cb_thietke;
	}

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
	//KienPV add new 10/09/2016
private boolean result = false;
	
	class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// confirmSave.dismiss();
			showProgressDialog(StringUtil.getString(R.string.text_progcessing));
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				if (itemDelete != null) {				
					result = new Supervision_CNVController(SupervisionBts_CNV_List_Activity.this).delete(itemDelete);					
				} else {
					result = false;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result1) {
			super.onPostExecute(result1);
			closeProgressDialog();
			if (result) {
				showDialog(StringUtil.getString(R.string.cnv_txt_delete_success));
			} else {
				showDialog(StringUtil.getString(R.string.cnv_txt_delete_error));
			}

		}

	}
	
	//---

}
