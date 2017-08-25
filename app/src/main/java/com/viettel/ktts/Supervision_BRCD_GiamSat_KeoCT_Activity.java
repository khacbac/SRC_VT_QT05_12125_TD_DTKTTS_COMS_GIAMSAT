package com.viettel.ktts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
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
import com.viettel.database.Cause_Brcd_Kct_Controller;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BRCD_GiamSat_Kct_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Design_Controller;
import com.viettel.database.Supervision_BRCD_Kct_Controller;
import com.viettel.database.Supervision_BRCD_Kct_design_Controller;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Line_Bg_TankEntity;
import com.viettel.database.entity.Cause_Line_BG_TankEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BRCD_GiamSat_Kct_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Design_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kct_Entity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Brcd_Kct_Field;
import com.viettel.database.field.Cause_Brcd_Kct_Field;
import com.viettel.database.field.Supervision_BRCD_GiamSat_Kct_Field;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.Supervision_BRCD_GS_KCT_Adapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin be
 * 
 * @author datht1
 * 
 */
public class Supervision_BRCD_GiamSat_KeoCT_Activity extends HomeBaseActivity {
	/* controll */
	private View spvBRCD_GS_KEOCTView;
	private ListView lv_constr_brcd_giamsat_kct_list;

	// private Bundle bundleData;

	private ScrollView scroll_brcd_gs_kct;
	private TextView tv_constr_brcd_giamsat_kct_station_code_value,
			tv_constr_brcd_gs_kct_name, tv_constr_brcd_giamsat_kct_stationcode,
			tv_constr_brcd_giamsat_kct_dropdown;
	private Button bt_giamsat_kct_back, btn_constr_brcd_giamsat_kct_addnew,
			rl_supervision_brcd_giamsat_kct_save;
	private int iField = 0;
	private int iDesignInfo = Constants.BRCD_BACKGROUND_INFO.KEO_CAP_TRUC;
	private List<DropdownItemEntity> listDesignInfo = null;

	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;

	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_BRCD_GiamSat_Kct_Entity brcd_gskct_entity = new Supervision_BRCD_GiamSat_Kct_Entity();
	private Supervision_BRCD_Kct_Entity brcd_kct_entity = new Supervision_BRCD_Kct_Entity();
	private Supervision_BRCD_GiamSat_Kct_Controller brcd_gskct_Controller = new Supervision_BRCD_GiamSat_Kct_Controller(
			this);
	private List<Supervision_Line_BG_TankGSEntity> listSupervisionGS;
	private Supervision_BRCD_Kct_Controller supervisionKCTController = new Supervision_BRCD_Kct_Controller(
			this);

	private Supervision_BRCD_Kct_design_Controller brcd_kct_design_Controller = new Supervision_BRCD_Kct_design_Controller(
			this);
	private Cause_Brcd_Kct_Controller causeBrcdKctController = new Cause_Brcd_Kct_Controller(
			this);
	private Supv_Constr_Attach_FileController supvConstrAttachFileController = new Supv_Constr_Attach_FileController(

	this);

	private Supervision_BRCD_Kcn_Design_Entity brcd_Entity = new Supervision_BRCD_Kcn_Design_Entity();

	private Supervision_BRCD_Kcn_Design_Controller brcd_Controller = new Supervision_BRCD_Kcn_Design_Controller(
			this);
	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankUnqualifyItem;
	private List<Supervision_LBG_UnqualifyItemEntity> listTankAcceptanceItem;
	/* Danh sach keo cap truc cua bang rong co dinh */
	private List<Supervision_BRCD_GiamSat_Kct_Entity> listSupervisionGSKCT;

	/* Item be cap dang sua dung popup */
	private Supervision_BRCD_GiamSat_Kct_Entity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
	private Supervision_BRCD_GS_KCT_Adapter supervisionGsKcnAdapter;
	private boolean outOfKey = false;
	private float positionTouch = 0f;
	String messageError = "";
	String messageError_Poin = "";
	String messageError_image = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		setContentView(R.layout.supervision_brcd_giamsat_kct_activity);
		setTitle(getString(R.string.line_background_header_title_brcd_mt));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvBRCD_GS_KEOCTView = addView(R.layout.supervision_brcd_giamsat_kct_activity, R.id.rl_supervision_line_bg_tank);
		this.tv_constr_brcd_gs_kct_name = (TextView) spvBRCD_GS_KEOCTView
				.findViewById(R.id.tv_constr_brcd_gs_kct_name);
		this.scroll_brcd_gs_kct = (ScrollView) spvBRCD_GS_KEOCTView
				.findViewById(R.id.scroll_brcd_gs_kct);
		this.lv_constr_brcd_giamsat_kct_list = (ListView) spvBRCD_GS_KEOCTView
				.findViewById(R.id.lv_constr_brcd_giamsat_kct_list);

		this.tv_constr_brcd_giamsat_kct_station_code_value = (TextView) spvBRCD_GS_KEOCTView
				.findViewById(R.id.tv_constr_brcd_giamsat_kct_station_code_value);

		this.tv_constr_brcd_giamsat_kct_stationcode = (TextView) spvBRCD_GS_KEOCTView
				.findViewById(R.id.tv_constr_brcd_giamsat_kct_stationcode);
		this.tv_constr_brcd_giamsat_kct_dropdown = (TextView) spvBRCD_GS_KEOCTView
				.findViewById(R.id.tv_constr_brcd_giamsat_kct_dropdown);
		this.tv_constr_brcd_giamsat_kct_dropdown.setOnClickListener(this);
		this.bt_giamsat_kct_back = (Button) spvBRCD_GS_KEOCTView
				.findViewById(R.id.bt_giamsat_kct_back);
		this.bt_giamsat_kct_back.setOnClickListener(this);
		this.btn_constr_brcd_giamsat_kct_addnew = (Button) spvBRCD_GS_KEOCTView
				.findViewById(R.id.btn_constr_brcd_giamsat_kct_addnew);
		this.btn_constr_brcd_giamsat_kct_addnew.setOnClickListener(this);

		this.rl_supervision_brcd_giamsat_kct_save = (Button) spvBRCD_GS_KEOCTView
				.findViewById(R.id.rl_supervision_brcd_giamsat_kct_save);
		this.rl_supervision_brcd_giamsat_kct_save.setOnClickListener(this);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		positionTouch = event.getY();

		return super.onTouchEvent(event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.tv_constr_brcd_giamsat_kct_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.rl_supervision_brcd_giamsat_kct_save:

			messageError_Poin = this.checkVailid_Poin(listSupervisionGSKCT);
			messageError = this.checkVailid(listSupervisionGSKCT);
			messageError_image = this.checkVailid_Image(listSupervisionGSKCT);
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);

			} else if (!StringUtil.isNullOrEmpty(messageError_Poin)) {
				this.showDialog(messageError_Poin);
			} else if (!StringUtil.isNullOrEmpty(messageError_image)) {
				this.showDialog(messageError_image);
			} else {
				ConfirmDialog confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
			}
			break;
		case R.id.btn_constr_brcd_giamsat_kct_addnew:

			messageError_Poin = this.checkVailid_Poin(listSupervisionGSKCT);
			messageError = this.checkVailid(listSupervisionGSKCT);
			if (!StringUtil.isNullOrEmpty(messageError)) {
				this.showDialog(messageError);
			} else if (!StringUtil.isNullOrEmpty(messageError_Poin)) {
				this.showDialog(messageError_Poin);
			} else {
				new SaveAsyncTask().execute();
				Supervision_BRCD_GiamSat_Kct_Entity brcd_giamsat_kct_Entity = new Supervision_BRCD_GiamSat_Kct_Entity();
				long idUser = GlobalInfo.getInstance().getUserId();
				long idSupervion = Ktts_KeyController.getInstance()
						.getKttsNextKey(
								Supervision_BRCD_GiamSat_Kct_Field.TABLE_NAME);
				if (idSupervion == 0) {
					Toast.makeText(
							Supervision_BRCD_GiamSat_KeoCT_Activity.this,
							StringUtil.getString(R.string.text_out_of_key),
							Toast.LENGTH_SHORT).show();

				}
				brcd_giamsat_kct_Entity.setSupervisionConstrId(itemData
						.getSupervision_Constr_Id());
				brcd_giamsat_kct_Entity.setEmployeeId(idUser);
				brcd_giamsat_kct_Entity.setIsActive(Constants.ISACTIVE.ACTIVE);
				brcd_giamsat_kct_Entity
						.setSync_Status(Constants.SYNC_STATUS.ADD);
				brcd_giamsat_kct_Entity
						.setSupervition_trunk_cable_id(brcd_kct_entity
								.getSUPERVISION_TRUNK_CABLE_ID());
				brcd_giamsat_kct_Entity
						.setSupervision_trunk_cable_fiber_id(idSupervion);
				brcd_gskct_Controller.addItem(brcd_giamsat_kct_Entity);
				refreshListView();
			}
			break;
		case R.id.bt_giamsat_kct_back:
			Intent it = new Intent(
					Supervision_BRCD_GiamSat_KeoCT_Activity.this,
					Supervision_BRCD_KeoCTActivity.class);
			Bundle bundleData = new Bundle();

			bundleData.putSerializable(IntentConstants.INTENT_DATA, itemData);
			bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
					brcd_kct_entity.getSupervition_brcd_id());
			it.putExtras(bundleData);
			this.startActivity(it);
			break;
		default:
			break;
		}
	}

	private String checkVailid_Image(
			List<Supervision_BRCD_GiamSat_Kct_Entity> listSupervisionGSKCN) {
		String sReslut = "";
		try {

			if (listSupervisionGSKCN.size() > 0) {
				for (int i = 0; i < listSupervisionGSKCN.size(); i++) {
					if (listSupervisionGSKCN.get(i).getStatus() == 1) {
						int countAcceptCheck = 0;
						if (listSupervisionGSKCN.get(i).getListAcceptance()
								.size() == 0) {

							for (int j = 0; j < listTankAcceptanceItem.size(); j++) {
								if (listTankAcceptanceItem.get(j)
										.getIs_Mandory() == 1) {
									countAcceptCheck++;
									break;
								}
							}
							if (countAcceptCheck > 0) {
								sReslut = StringUtil
										.getString(R.string.constr_take_acceptance_not_enough)
										+ " tại đoạn" + (i + 1);
								break;
							}

						} else {

							for (int j = 0; j < listTankAcceptanceItem.size(); j++) {
								if ((listSupervisionGSKCN.get(i)
										.getListAcceptance().get(j)
										.getLstNewAttachFile().size() != 0 || listSupervisionGSKCN
										.get(i).getListAcceptance().get(j)
										.getLstAttachFile().size() != 0)

										&& listTankAcceptanceItem.get(j)
												.getIs_Mandory() == 1) {
									countAcceptCheck++;
								}
							}
							for (int j = 0; j < listTankAcceptanceItem.size(); j++) {

								if (listTankAcceptanceItem.get(j)
										.getIs_Mandory() == 1) {
									for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listSupervisionGSKCN
											.get(i).getListAcceptance()) {
										if (curUnqualifyItem
												.getCat_Cause_Constr_Acceptance_Id() == listTankAcceptanceItem
												.get(j)
												.getCat_Cause_Constr_Acceptance_Id()) {
											List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
													.getLstAttachFile(
															Acceptance_Brcd_Kct_Field.TABLE_NAME,
															curUnqualifyItem
																	.getCause_Line_Bg_Id());
											if (lstCurAttachFile.size() > 0) {
												countAcceptCheck++;
												break;
											}

										}

									}

								}

							}
							if (countAcceptCheck == 0) {
								sReslut = StringUtil
										.getString(R.string.constr_take_acceptance_not_enough)
										+ " tại đoạn" + (i + 1);
								break;
							}
						}

					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return sReslut;
	}

	private String checkVailid(
			List<Supervision_BRCD_GiamSat_Kct_Entity> listSupervisiongiamdropgo) {
		String sReslut = "";
		try {
			for (int i = 0; i < listSupervisiongiamdropgo.size(); i++) {
				if (listSupervisiongiamdropgo.get(i).getEnd_section() < listSupervisiongiamdropgo
						.get(i).getStart_section()) {
					sReslut = "Tại đoạn "
							+ (i + 1)
							+ StringUtil
									.getString(R.string.brcd_diemcuoinhohondau);
				} else if (listSupervisiongiamdropgo.get(i).getEnd_section() > brcd_kct_entity
						.getLength()) {

					sReslut = "Chiều dài đoạn là "
							+ brcd_kct_entity.getLength()
							+ ", bạn đã vượt quá "
							+ (listSupervisiongiamdropgo.get(i)
									.getEnd_section() - brcd_kct_entity
									.getLength()) + "m tại đoạn " + (i + 1);

				} else if (i > 0
						&& listSupervisiongiamdropgo.get(i).getStart_section() != -1
						&& listSupervisiongiamdropgo.get(i).getEnd_section() != -1) {

					if (listSupervisiongiamdropgo.get(i).getStart_section() <= listSupervisiongiamdropgo
							.get(i - 1).getEnd_section()) {

						sReslut = "Tại đoạn "
								+ (i + 1)
								+ " có "
								+ StringUtil
										.getString(R.string.brcd_diemdaunhohoncuoi)
								+ " đoạn " + i;
					} else if (listSupervisiongiamdropgo.get(i - 1)
							.getStart_section() == -1
							&& listSupervisiongiamdropgo.get(i - 1)
									.getEnd_section() == -1) {
						sReslut = StringUtil
								.getString(R.string.brcd_nhapthieudulieu_tainhanh)
								+ i;
					}

				}

				if (!sReslut.equals("")) {
					break;
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sReslut;
	}

	private String checkVailid_Poin(
			List<Supervision_BRCD_GiamSat_Kct_Entity> listSupervisiongiamsatdropgo) {
		String sReslut = "";
		try {

			for (int i = 0; i < listSupervisiongiamsatdropgo.size(); i++) {
				int countNnkdCheck = 0;
				for (int j = 0; j < listSupervisiongiamsatdropgo.get(i)
						.getListCauseUniQualify().size(); j++) {
					if (!listSupervisiongiamsatdropgo.get(i)
							.getListCauseUniQualify().get(j).isDelete()) {
						countNnkdCheck++;
					}
					if (countNnkdCheck > 0) {
						break;
					}
				}
				if (listSupervisiongiamsatdropgo.get(i).getStatus() == 1) {
					if (listSupervisiongiamsatdropgo.get(i).getStart_section() == -1) {
						sReslut = StringUtil.getString(R.string.null_text)
								+ " đoạn đầu cho đoạn " + (i + 1);
					} else if (listSupervisiongiamsatdropgo.get(i)
							.getStart_section() == -1) {
						sReslut = StringUtil.getString(R.string.null_text)
								+ " đoạn cuối cho đoạn " + (i + 1);
					}
				} else if (listSupervisiongiamsatdropgo.get(i).getStatus() == 0) {
					if (countNnkdCheck < 1) {
						sReslut = StringUtil
								.getString(R.string.constr_line_tank_nn_khongdat_tempty)
								+ " cho đoạn" + (i + 1);
					}
				} else if (listSupervisiongiamsatdropgo.get(i)
						.getStart_section() > 0) {
					if (listSupervisiongiamsatdropgo.get(i).getStatus() == -1) {
						sReslut = StringUtil
								.getString(R.string.line_background_design_assess_is_null)
								+ " cho đoạn " + (i + 1);

					}
				} else if (listSupervisiongiamsatdropgo.get(i).getEnd_section() > 0) {
					if (listSupervisiongiamsatdropgo.get(i).getStatus() == -1) {
						sReslut = StringUtil
								.getString(R.string.line_background_design_assess_is_null)
								+ " cho đoạn " + (i + 1);

					}
				}
				if (!sReslut.equals("")) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBRCD_GS_KEOCTView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_GiamSat_KeoCT_Activity.this);
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
					Bundle bundleData = new Bundle();
					bundleData.putSerializable(IntentConstants.INTENT_DATA,
							itemData);
					bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
							brcd_kct_entity.getSupervition_brcd_id());

					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoBrcdBackgroupActivity(bundleData);
					this.dropdownPopupMenuDesignInfo.dismiss();
					this.finish();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}

			}
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_SO_TRUC)) {
				iDesignInfo = dropdownItem.getId();
				if (this.iDesignInfo > 0) {
					this.tv_constr_brcd_giamsat_kct_dropdown
							.setText(dropdownItem.getTitle());
					this.dropdownPopupMenuDesignInfo.dismiss();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			break;
		case Constants.BG_PIPE_EDIT.FAIL_DESC:
			String sFailDescTextValue = this.curEditItem.getDesc();
			editTextPopup = new Edit_Text_Popup(this, null, sFailDescTextValue,
					InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
			editTextPopup.showAtCenter();
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
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contruoctionUnqualifyPopup.hidePopup();
			break;
		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			this.saveAcceptance();
			contruoctionAcceptancePopup.hidePopup();
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

		// chup anh nguyen nhan loi
		case OnEventControlListener.EVENT_UNQUALIFY_TAKE_PHOTO:
			this.curUnqualifyItem = (Supervision_LBG_UnqualifyItemEntity) data;
			listImgView = new ArrayList<ImageEntity>();
			/*
			 * Neu anh moi duoc chup hien thi anh moi chup, khong hien thi anh
			 * san co
			 */

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

		case OnEventControlListener.EVENT_SUPERVISION_BRCD_TN_EDIT:
			this.curEditItem = (Supervision_BRCD_GiamSat_Kct_Entity) data;
			this.iField = this.curEditItem.getiField();
			switch (iField) {

			case Constants.BG_PIPE_EDIT.FAIL_DESC:
				String sFailDescText = this.curEditItem.getDesc();
				editTextPopup = new Edit_Text_Popup(this, null, sFailDescText,
						InputType.TYPE_TEXT_FLAG_MULTI_LINE, true, 200);
				editTextPopup.showAtCenter();
				break;
			case Constants.BG_PIPE_EDIT.UNQUALIFY:
				if (this.curEditItem.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
					// Gan gia tri nguyen nhan loi
					this.setUnqualify();
					contruoctionUnqualifyPopup = new Contruction_UnqualifyPopup(
							this, null, this.listTankUnqualifyItem);
					contruoctionUnqualifyPopup.showAtCenter();
				} else if (this.curEditItem.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
					this.setAcceptance();
					contruoctionAcceptancePopup = new Contruction_AcceptancePopup(
							this, null, this.listTankAcceptanceItem);
					contruoctionAcceptancePopup.showAtCenter();

				} else {
					Toast.makeText(
							this,
							StringUtil
									.getString(R.string.line_background_accept_choice),
							Toast.LENGTH_SHORT).show();
				}
				break;
			case Constants.BG_PIPE_EDIT.DELETE:
				if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
						|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
					this.showDialog("Công trình này đã hoàn thành nên không thể xóa");
				} else {
					AlertDialog dialogAskOption = new AlertDialog.Builder(this)
							.setTitle(R.string.text_delete_title)
							.setMessage(R.string.line_bg_pipe_delete_message)
							.setIcon(R.drawable.ic_launcher)

							.setPositiveButton(
									StringUtil
											.getString(R.string.text_delete_button),
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int whichButton) {
											deleteKct(curEditItem);
											dialog.dismiss();
										}

									})

							.setNegativeButton(
									StringUtil
											.getString(R.string.text_close_button),
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog,
												int which) {
											dialog.dismiss();

										}
									}).create();
					dialogAskOption.show();
				}
				break;
			default:
				break;
			}
			break;
		case OnEventControlListener.EVENT_SET_TEXT:
			String sSetTextValue = (String) data;

			this.curEditItem.setDesc(sSetTextValue.toString());

			editTextPopup.hidePopup();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_EXIT:
			List<ImageEntity> lstData = (List<ImageEntity>) data;

			this.imgViewPopup.hidePopup();
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

			// this.supervisionAdapter.notifyDataSetChanged();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_NEW:
			this.takePhoto(itemData);
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_DELETE:

			this.imgViewPopup.deleteImageData();
			break;
		case OnEventControlListener.EVENT_IMG_TAKE_ATTACK:
			this.selectPhoto();
			break;
		case OnEventControlListener.EVENT_CONFIRM_OK:
			new SaveAsyncTask().execute();
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	protected void deleteKct(
			Supervision_BRCD_GiamSat_Kct_Entity curEditItem_Gs_Kcn) {
		// new SaveAsyncTask().execute();
		brcd_gskct_Controller.deleteGSKctEntity(curEditItem_Gs_Kcn);
		refreshListView();

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
//
//				this.imgViewPopup.setImageData(addImgView);
//			}
//			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	private void setData() {
		Bundle bundleData = getIntent().getExtras();
		this.itemData = (Constr_Construction_EmployeeEntity) bundleData
				.getSerializable(IntentConstants.INTENT_DATA);
		this.brcd_kct_entity = (Supervision_BRCD_Kct_Entity) bundleData
				.getSerializable(IntentConstants.INTENT_BRCD_TRUC);

		this.tv_constr_brcd_giamsat_kct_dropdown.setText(GloablUtils
				.getStringBRCDBackgroundInfo(iDesignInfo));
		this.tv_constr_brcd_giamsat_kct_station_code_value.setText(itemData
				.getStationCode());
		this.tv_constr_brcd_giamsat_kct_stationcode.setText(itemData
				.getConstrCode());
		this.tv_constr_brcd_gs_kct_name
				.setText(brcd_kct_entity.getCable_code());

		listTankUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(this)
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.KEO_CAP_TRUC,
						Constants.UNQUALIFY_TYPE.BRCD);

		listTankAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
				.getAllUnQualifyItemByName(
						Constants.ACCEPTANCE_TYPE.KEO_CAP_TRUC,
						Constants.UNQUALIFY_TYPE.BRCD);

		this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.rl_supervision_brcd_giamsat_kct_save.setVisibility(View.GONE);

			this.btn_constr_brcd_giamsat_kct_addnew.setVisibility(View.GONE);
		}
		this.refreshListView();
	}

	private void refreshListView() {
		// lay danh sach keo cap truc

		listSupervisionGSKCT = new ArrayList<Supervision_BRCD_GiamSat_Kct_Entity>();

		listSupervisionGSKCT = brcd_gskct_Controller
				.getAllbrcd_Kct(brcd_kct_entity.getSUPERVISION_TRUNK_CABLE_ID());

		this.supervisionGsKcnAdapter = new Supervision_BRCD_GS_KCT_Adapter(
				this, listSupervisionGSKCT);
		this.lv_constr_brcd_giamsat_kct_list
				.setAdapter(supervisionGsKcnAdapter);
		lv_constr_brcd_giamsat_kct_list
				.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						scroll_brcd_gs_kct
								.requestDisallowInterceptTouchEvent(true);
						int action = event.getActionMasked();
						switch (action) {
						case MotionEvent.ACTION_UP:
							scroll_brcd_gs_kct
									.requestDisallowInterceptTouchEvent(false);
							break;
						}
						return false;
					}
				});
		if (listSupervisionGSKCT.size() != 0) {

			for (Supervision_BRCD_GiamSat_Kct_Entity curItemSupervisonGS : listSupervisionGSKCT) {
				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = this.causeBrcdKctController
						.getAllTankUnqulifyByTankId(curItemSupervisonGS
								.getSupervision_trunk_cable_fiber_id());
				List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = this.causeBrcdKctController
						.getAllTanAcceptByTankId(curItemSupervisonGS
								.getSupervision_trunk_cable_fiber_id());
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listAcceptItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(
									Acceptance_Brcd_Kct_Field.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}

				}
				curItemSupervisonGS.setListAcceptance(listAcceptItem);
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(Cause_Brcd_Kct_Field.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}

				}
				curItemSupervisonGS.setListCauseUniQualify(listUnqualifyItem);
			}

		}

	}

	// TODO DanhDue ExOICTIF
	private void saveDataTank() {
		// brcd_kct_Controller.trunCate_kct();
		long idUser = GlobalInfo.getInstance().getUserId();

		for (Supervision_BRCD_GiamSat_Kct_Entity curItemData : listSupervisionGSKCT) {
			Supervision_BRCD_GiamSat_Kct_Entity brcd_kct = new Supervision_BRCD_GiamSat_Kct_Entity();

			brcd_kct.setDesc(curItemData.getDesc());
			brcd_kct.setStatus(curItemData.getStatus());
			brcd_kct.setStart_section(curItemData.getStart_section());
			brcd_kct.setEnd_section(curItemData.getEnd_section());
			brcd_kct.setSupervition_trunk_cable_id(brcd_kct_entity
					.getSUPERVISION_TRUNK_CABLE_ID());
			brcd_kct.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kct.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kct.setProcessId(0);
			brcd_kct.setEmployeeId(idUser);

			brcd_kct.setSupervision_trunk_cable_fiber_id(curItemData
					.getSupervision_trunk_cable_fiber_id());
			brcd_gskct_Controller.updateSupervisionBRCD_Kct(
					brcd_kct.getSupervision_trunk_cable_fiber_id(), brcd_kct);

			List<Supervision_LBG_UnqualifyItemEntity> listAddCause = curItemData
					.getListCauseUniQualify();
			List<Supervision_LBG_UnqualifyItemEntity> listAddAccept = curItemData
					.getListAcceptance();
			for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddAccept) {
				/* 1. Chinh sua nghiem thu */
				if (addItemCause.getCause_Line_Bg_Id() > 0) {

					// xoa nghiem thu khi chuyen
					// trang thai tu khong dat sang dat
					// if (curItemData.getStatus() ==
					// Constants.SUPERVISION_STATUS.CHUADAT) {
					// this.causeBrcdKctController.deleteAccept(addItemCause);
					// }

					if (addItemCause.getLstNewAttachFile().size() > 0
							|| (addItemCause.getLstNewAttachFile().size() == 0 && addItemCause
									.getLstAttachFile().size() == 0)) {
						List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
								.getLstAttachFile(
										Acceptance_Brcd_Kct_Field.TABLE_NAME,
										addItemCause.getCause_Line_Bg_Id());

						// xoa anh cu di
						for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
							supvConstrAttachFileController.delete(itemFile);
						}
					}

					for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
							.getLstNewAttachFile()) {

						// them anh moi vao
						if (!StringUtil.isNullOrEmpty(itemFile.getFile_Path())) {
							this.supvConstrAttachFileController
									.coppyAndAddAttachFile(
											this.itemData,
											itemFile.getFile_Path(),
											addItemCause.getCause_Line_Bg_Id(),
											Acceptance_Brcd_Kct_Field.TABLE_NAME);

						}
					}

				}
				/* 2. Them moi nghiem thu */
				else {
					if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.DAT) {
						Acceptance_Line_Bg_TankEntity addCauseItem = new Acceptance_Line_Bg_TankEntity();
						addCauseItem
								.setCat_Cause_Constr_Acceptance_Id(addItemCause
										.getCat_Cause_Constr_Acceptance_Id());
						addCauseItem.setSupervision_Line_Bg_Tank_Id(curItemData
								.getSupervision_trunk_cable_fiber_id());

						addCauseItem.setSync_Status(Constants.SYNC_STATUS.ADD);
						addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
						addCauseItem.setProcessId(0);
						addCauseItem.setEmployeeId(idUser);

						long iAddCause = Ktts_KeyController.getInstance()
								.getKttsNextKey(
										Acceptance_Brcd_Kct_Field.TABLE_NAME);

						if (iAddCause == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						addCauseItem.setAcceptance_Line_Bg_Tank_Id(iAddCause);
						this.causeBrcdKctController.addItem(addCauseItem);
						// Luu anh nguyen nhan loi neu co
						for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
								.getLstNewAttachFile()) {
							if (!StringUtil.isNullOrEmpty(itemFile
									.getFile_Path())) {
								this.supvConstrAttachFileController
										.coppyAndAddAttachFile(
												this.itemData,
												itemFile.getFile_Path(),
												iAddCause,
												Acceptance_Brcd_Kct_Field.TABLE_NAME);

							}
						}
					}

				}
			}

			for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddCause) {
				/* 1. Chinh sua nguyen nhan khong dat */
				if (addItemCause.getCause_Line_Bg_Id() > 0) {
					/* 1.1. Xoa nguyen nhan khong dat danh dau xoa */
					if (addItemCause.isDelete()) {
						this.causeBrcdKctController.delete(addItemCause);
					}
					/* 1.2. Update lai nguyen nhan khong dat */
					else {
						// xoa nguyen nhan khong dat khi chuyen
						// trang thai tu khong dat sang dat
						// if (curItemData.getStatus() ==
						// Constants.SUPERVISION_STATUS.DAT) {
						// this.causeLineBGTankController
						// .delete(addItemCause);
						// }

						if (addItemCause.getLstNewAttachFile().size() > 0
								|| (addItemCause.getLstNewAttachFile().size() == 0 && addItemCause
										.getLstAttachFile().size() == 0)) {
							// danh sach anh da co
							List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
									.getLstAttachFile(
											Cause_Brcd_Kct_Field.TABLE_NAME,
											addItemCause.getCause_Line_Bg_Id());

							// xoa anh cu di
							for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
								supvConstrAttachFileController.delete(itemFile);
							}
						}

						for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
								.getLstNewAttachFile()) {

							// them anh moi vao
							if (!StringUtil.isNullOrEmpty(itemFile
									.getFile_Path())) {
								this.supvConstrAttachFileController
										.coppyAndAddAttachFile(this.itemData,
												itemFile.getFile_Path(),
												addItemCause
														.getCause_Line_Bg_Id(),
												Cause_Brcd_Kct_Field.TABLE_NAME);

							}
						}
					}
				}
				/* 2. Them moi nguyen nhan khong dat */
				else {
					if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
						Cause_Line_BG_TankEntity addCauseItem = new Cause_Line_BG_TankEntity();
						addCauseItem
								.setCat_Cause_Constr_Unqualify_Id(addItemCause
										.getCat_Cause_Constr_Unqualify_Id());
						addCauseItem.setSupervision_Line_Bg_Tank_Id(curItemData
								.getSupervision_trunk_cable_fiber_id());
						addCauseItem.setSync_Status(Constants.SYNC_STATUS.ADD);
						addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
						addCauseItem.setProcessId(0);
						addCauseItem.setEmployeeId(idUser);
						addCauseItem.setSupervisionConstrId(itemData
								.getSupervision_Constr_Id());

						long iAddCause = Ktts_KeyController
								.getInstance()
								.getKttsNextKey(Cause_Brcd_Kct_Field.TABLE_NAME);

						if (iAddCause == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						addCauseItem.setCause_Line_Bg_Tank_Id(iAddCause);
						this.causeBrcdKctController.addItem(addCauseItem);
						// Luu anh nguyen nhan loi neu co
						for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
								.getLstNewAttachFile()) {
							if (!StringUtil.isNullOrEmpty(itemFile
									.getFile_Path())) {
								this.supvConstrAttachFileController
										.coppyAndAddAttachFile(this.itemData,
												itemFile.getFile_Path(),
												iAddCause,
												Cause_Brcd_Kct_Field.TABLE_NAME);

							}
						}
					}
				}
			}

		}

		// TODO: Thiet lap ket luan. DanhDue ExOICTIF
		Supervision_ConstrController constr_Controller = new Supervision_ConstrController(
				this);
		Bundle bundleData = this.getIntent().getExtras();
		boolean bDeny = checkBRCDCauseDeny(bundleData);
		Log.i("Check_Deny", String.valueOf(bDeny));
		if (bDeny) {
			constr_Controller.updateStatus(itemData.getSupervision_Constr_Id(),
					0);
		} else {
			constr_Controller.updateStatus(itemData.getSupervision_Constr_Id(),
					1);
		}

	}

	/* Ghi nghiem thu vao danh sach ong */

	/**
	 * Ham set lai nghiem thu cho 1 be trong list danh sach nguyen nhan loi
	 */

	private void setAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTankAcceptanceItem) {
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

	private void saveAcceptance() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListAcceptance();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTankAcceptanceItem) {
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

	/* Ghi nguyen nhan khong dat vao danh sach ong */
	private void saveUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTankUnqualifyItem) {
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

	/**
	 * Ham set lai nguyen nhan loi cho 1 be trong list danh sach nguyen nhan loi
	 */
	private void setUnqualify() {
		List<Supervision_LBG_UnqualifyItemEntity> curListUnqualify = this.curEditItem
				.getListCauseUniQualify();
		for (Supervision_LBG_UnqualifyItemEntity curCauseUniqualify : listTankUnqualifyItem) {
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
			SyncTask.closeDialog();
			refreshListView();
			// closeProgressDialog();
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
			saveDataTank();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(Supervision_BRCD_GiamSat_KeoCT_Activity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}

			refreshListView();

			Toast.makeText(Supervision_BRCD_GiamSat_KeoCT_Activity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}