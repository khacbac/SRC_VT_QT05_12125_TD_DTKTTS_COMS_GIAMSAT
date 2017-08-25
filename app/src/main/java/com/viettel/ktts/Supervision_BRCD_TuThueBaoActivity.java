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
import com.viettel.database.Cause_Brcd_Ttb_Controller;
import com.viettel.database.Cause_Line_BG_TankController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_BRCD_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Controller;
import com.viettel.database.Supervision_BRCD_Kcn_Design_Controller;
import com.viettel.database.Supervision_BRCD_Ttb_Controller;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.Supervision_Line_BG_TankController;
import com.viettel.database.Supv_Constr_Attach_FileController;
import com.viettel.database.entity.Acceptance_Line_Bg_TankEntity;
import com.viettel.database.entity.Cause_Line_BG_TankEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.ImageEntity;
import com.viettel.database.entity.Supervision_BRCD_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Design_Entity;
import com.viettel.database.entity.Supervision_BRCD_Kcn_Entity;
import com.viettel.database.entity.Supervision_BRCD_Ttb_Entity;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supervision_Line_BG_TankGSEntity;
import com.viettel.database.entity.Supervision_Line_BackgroundEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.database.field.Acceptance_Brcd_Box_Field;
import com.viettel.database.field.Cause_Brcd_Box_Field;
import com.viettel.database.field.Cause_Brcd_Drop_Field;
import com.viettel.dialog.ConfirmDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.HomeBaseActivity;
import com.viettel.view.control.Supervision_BRCD_TTB_Adapter;
import com.viettel.view.control.Supervision_Line_BGAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

/**
 * giam sat thong tin be
 * 
 * @author datht1
 * 
 */
public class Supervision_BRCD_TuThueBaoActivity extends HomeBaseActivity {
	/* controll */
	private View spvBRCD_TU_TBView;
	private ScrollView scroll_brcd_ttb;
	private ListView lv_constr_brcd_ttb_list;
	private TextView tv_constr_brcd_ttb_dropdown;
	private TextView tv_constr_brcd_ttb_station_code_value;
	private TextView tv_constr_brcd_ttb_info_line_value;

	private Button rl_supervision_ttb_save, btn_constr_ttb_perfect;

	/* bien dropdown */
	private int iDesignInfo = Constants.BRCD_BACKGROUND_INFO.TU_THUE_BAO;

	private int iField = 0;
	private List<DropdownItemEntity> listDesignInfo = null;
	private Menu_DropdownPopup dropdownPopupMenuDesignInfo;
	private Contruction_UnqualifyPopup contruoctionUnqualifyPopup;
	private Contruction_AcceptancePopup contruoctionAcceptancePopup;
	private Edit_Text_Popup editTextPopup;
//	private Image_ViewGalleryPopup imgViewPopup;
	/* Bien co so du lieu */
	private Constr_Construction_EmployeeEntity itemData;
	private Supervision_Line_BackgroundEntity supervision_Line_BackgroundData;
	private List<Supervision_Line_BG_TankGSEntity> listSupervisionGS;
	private Supervision_Line_BG_TankController supervisionLBGController;
	private Cause_Line_BG_TankController causeLineBGTankController;
	private Cause_Brcd_Ttb_Controller causeBrcdTtbController = new Cause_Brcd_Ttb_Controller(
			this);

	private Supv_Constr_Attach_FileController supvConstrAttachFileController = new Supv_Constr_Attach_FileController(
			this);
	/* Danh sach nguyen nhan khong dat cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankUnqualifyItem;
	/* Danh sach nghiem thu cua be da chuyen doi de hien thi item */
	private List<Supervision_LBG_UnqualifyItemEntity> listTankAcceptanceItem;
	/* Item be cap dang sua dung popup */
	private Supervision_BRCD_Ttb_Entity curEditItem = null;
	private Supervision_LBG_UnqualifyItemEntity curUnqualifyItem = null;
	private Supervision_LBG_UnqualifyItemEntity curAcceptanceItem = null;
	private Supervision_Line_BGAdapter supervisionAdapter;

	private Supervision_BRCD_Entity brcd_Entity = new Supervision_BRCD_Entity();
	private List<Supervision_BRCD_Kcn_Entity> listSupervisionKcn;
	private Supervision_BRCD_Kcn_Entity kcn_Entity = new Supervision_BRCD_Kcn_Entity();
	private Supervision_BRCD_Controller brcd_Controller = new Supervision_BRCD_Controller(
			this);

	private Supervision_BRCD_Kcn_Design_Entity kcn_design_Entity = new Supervision_BRCD_Kcn_Design_Entity();
	private Supervision_BRCD_Kcn_Design_Controller brcd_kcn_design_Controller = new Supervision_BRCD_Kcn_Design_Controller(
			this);
	private Supervision_BRCD_Kcn_Controller brcd_kcn_Controller = new Supervision_BRCD_Kcn_Controller(
			this);

	private Supervision_BRCD_Ttb_Controller supervisionTTBController = new Supervision_BRCD_Ttb_Controller(
			this);
	private List<Supervision_BRCD_Ttb_Entity> listSupervisionTTB;
	private Supervision_BRCD_TTB_Adapter supervisionTtbAdapter;
	private boolean outOfKey = false;
	private float positionTouch = 0f;
	private long supervition_brcd_id;

	private TextView supervision_brcd_ttb_complete_date;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_brcd_tuthuebao_activity);
		setTitle(getString(R.string.line_background_header_title_brcd_mt));
		/* set controll */
		this.initView();
//		setHeader();
		this.setData();
	}

	private void initView() {
		spvBRCD_TU_TBView = addView(R.layout.supervision_brcd_tuthuebao_activity, R.id.rl_supervision_line_bg_tank);
		this.lv_constr_brcd_ttb_list = (ListView) spvBRCD_TU_TBView.findViewById(R.id.lv_constr_brcd_ttb_list);
		this.scroll_brcd_ttb = (ScrollView) spvBRCD_TU_TBView.findViewById(R.id.scroll_brcd_ttb);

		this.tv_constr_brcd_ttb_dropdown = (TextView) spvBRCD_TU_TBView
				.findViewById(R.id.tv_constr_brcd_ttb_dropdown);
		this.tv_constr_brcd_ttb_dropdown.setOnClickListener(this);

		this.tv_constr_brcd_ttb_station_code_value = (TextView) spvBRCD_TU_TBView
				.findViewById(R.id.tv_constr_brcd_ttb_station_code_value);

		this.tv_constr_brcd_ttb_info_line_value = (TextView) spvBRCD_TU_TBView
				.findViewById(R.id.tv_constr_brcd_ttb_info_line_value);
		this.rl_supervision_ttb_save = (Button) spvBRCD_TU_TBView
				.findViewById(R.id.rl_supervision_ttb_save);
		this.rl_supervision_ttb_save.setOnClickListener(this);
		this.btn_constr_ttb_perfect = (Button) spvBRCD_TU_TBView
				.findViewById(R.id.btn_constr_ttb_perfect);
		this.btn_constr_ttb_perfect.setOnClickListener(this);
		this.supervision_brcd_ttb_complete_date = (TextView) spvBRCD_TU_TBView
				.findViewById(R.id.supervision_brcd_ttb_complete_date);

		// final int heightScreen = displaymetrics.heightPixels;

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		positionTouch = event.getY();

		return super.onTouchEvent(event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_constr_ttb_perfect:
			ConfirmDialog confirmPerfect = new ConfirmDialog(this,
					StringUtil.getString(R.string.text_confirm_save),
					OnEventControlListener.EVENT_COMPLETE_PROGRESS);
			confirmPerfect.show();
			break;
		case R.id.tv_constr_brcd_ttb_dropdown:
			this.dropdownPopupMenuDesignInfo = new Menu_DropdownPopup(this,
					this.listDesignInfo);
			dropdownPopupMenuDesignInfo.show(v);
			break;
		case R.id.rl_supervision_ttb_save:
			String messageError = "";
			String messageError_image = "";

			messageError = this.checkVailid(listSupervisionTTB);
			messageError_image = this.checkVailid_Image(listSupervisionTTB);
			if (!StringUtil.isNullOrEmpty(messageError_image)) {
				this.showDialog(messageError_image);
				// Toast.makeText(getApplicationContext(), messageError,
				// Toast.LENGTH_LONG).show();
			}
//			else if (!StringUtil.isNullOrEmpty(messageError)) {
//				this.showDialog(messageError);
//			}
			else {
				ConfirmDialog confirmSave = new ConfirmDialog(this,
						StringUtil.getString(R.string.text_confirm_save));
				confirmSave.show();
			}
			break;

		default:
			break;
		}
	}

	private String checkVailid_Image(
			List<Supervision_BRCD_Ttb_Entity> listSupervisionTTB) {
		String sReslut = "";
		try {

			if (listSupervisionTTB.size() > 0) {
				for (int i = 0; i < listSupervisionTTB.size(); i++) {
					if (listSupervisionTTB.get(i).getStatus() == 1) {
						int countAcceptCheck = 0;
						if (listSupervisionTTB.get(i).getListAcceptance()
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
										+ " tại "
										+ listSupervisionTTB.get(i)
												.getBox_Name();
								break;
							}

						} else {

							for (int j = 0; j < listTankAcceptanceItem.size(); j++) {
								if ((listSupervisionTTB.get(i)
										.getListAcceptance().get(j)
										.getLstNewAttachFile().size() != 0 || listSupervisionTTB
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
									for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listSupervisionTTB
											.get(i).getListAcceptance()) {
										if (curUnqualifyItem
												.getCat_Cause_Constr_Acceptance_Id() == listTankAcceptanceItem
												.get(j)
												.getCat_Cause_Constr_Acceptance_Id()) {
											List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
													.getLstAttachFile(
															Acceptance_Brcd_Box_Field.TABLE_NAME,
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
										+ " tại "
										+ listSupervisionTTB.get(i)
												.getBox_Name();
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

	private void setHeader() {
		final Header myActionBar = (Header) spvBRCD_TU_TBView.findViewById(R.id.actionbar);
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
				requestSync(Supervision_BRCD_TuThueBaoActivity.this);
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
					bundleData.putSerializable(IntentConstants.INTENT_DATA_EX,
							supervision_Line_BackgroundData);

					bundleData.putLong(IntentConstants.INTENT_BRCD_ID,
							supervition_brcd_id);

					bundleData.putInt(IntentConstants.INTENT_DESIGNINFO,
							dropdownItem.getId());
					this.gotoBrcdBackgroupActivity(bundleData);
					this.dropdownPopupMenuDesignInfo.dismiss();
					this.finish();
				} else {
					this.dropdownPopupMenuDesignInfo.dismiss();
				}
			}
			break;
		/* Chon nguyen nhan khong dat */
		case OnEventControlListener.EVENT_SUPERVISION_BRCD_TN_MAP:
			this.curEditItem = (Supervision_BRCD_Ttb_Entity) data;
			// Bundle data_map = new Bundle();
			// // data.putSerializable(IntentConstants.INTENT_DATA,
			// // constr_ConstructionEmployeeItem);
			// data_map.putDouble(IntentConstants.INTENT_LONG,
			// curEditItem.getLongitude());
			// data_map.putDouble(IntentConstants.INTENT_LAT,
			// curEditItem.getLatitude());
			//
			// Intent intent = new
			// Intent(Supervision_BRCD_TuThueBaoActivity.this,
			// GoogleMapActivity.class);
			// intent.setComponent(new ComponentName("com.viettel.ktts",
			// "com.viettel.ktts.GoogleMapActivity"));
			// // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// intent.putExtras(data_map);
			//
			// startActivityForResult(intent, Constants.LOCATION_RESULT);
			this.getLocation(this.curEditItem.getLongitude(),
					this.curEditItem.getLatitude(),
					StringUtil.getString(R.string.constr_line_name), "");
			break;
		/* Dong anh nghiem thu */
		case OnEventControlListener.EVENT_ACCEPTANCE_CHOICE:
			this.saveAcceptance();
			contruoctionAcceptancePopup.hidePopup();
			break;
		/* Dong nguyen nhan khong dat */
		case OnEventControlListener.EVENT_UNQUALIFY_CHOICE:
			this.saveUnqualify();
			contruoctionUnqualifyPopup.hidePopup();
			break;

		case OnEventControlListener.EVENT_SET_TEXT:
			String sSetTextValue = (String) data;

			this.curEditItem.setDesc(sSetTextValue.toString());

			this.supervisionTtbAdapter.notifyDataSetChanged();
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
		case OnEventControlListener.EVENT_LOCATION:
			this.curEditItem = (Supervision_BRCD_Ttb_Entity) data;
			this.getLocation(this.curEditItem.getLongitude(),
					this.curEditItem.getLatitude(),
					StringUtil.getString(R.string.constr_line_tank_name),
					this.curEditItem.getBox_Name());
			break;
		case OnEventControlListener.EVENT_SUPERVISION_BRCD_KCT_EDIT:
			this.curEditItem = (Supervision_BRCD_Ttb_Entity) data;
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
				this.curEditItem = (Supervision_BRCD_Ttb_Entity) data;
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
											deleteTtb(curEditItem);
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
		case OnEventControlListener.EVENT_COMPLETE_PROGRESS:
			saveCompleteDay(itemData, Constants.PROGRESS_TYPE.BRCD_TYPE,
					Constants.PROGRESS_TYPE.BRCD_TTB, outOfKey);
			showCompleteDate(itemData, Constants.PROGRESS_TYPE.BRCD_TYPE,
					Constants.PROGRESS_TYPE.BRCD_TTB,
					supervision_brcd_ttb_complete_date, btn_constr_ttb_perfect);
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}
	}

	protected void deleteTtb(Supervision_BRCD_Ttb_Entity curEditItem_ttb) {
		new SaveAsyncTask().execute();
		int sotu = 0;
		kcn_design_Entity = brcd_kcn_design_Controller
				.getSupervisionBRCD_Kcn_design_id(curEditItem_ttb
						.getSupervition_branch_design_id());
		switch (curEditItem_ttb.getCab_type()) {
		case 2:
			sotu = kcn_design_Entity.getNum_Box_Two();
			boolean updateTwo = brcd_kcn_design_Controller.updateBoxNumTwo(
					kcn_design_Entity.getSupervition_branch_design_id(),
					(sotu - 1));
			break;
		case 4:
			sotu = kcn_design_Entity.getNum_Box_Four();
			boolean updateFour = brcd_kcn_design_Controller.updateBoxNumFour(
					kcn_design_Entity.getSupervition_branch_design_id(),
					(sotu - 1));
			break;
		case 8:
			sotu = kcn_design_Entity.getNum_Box_Eight();
			boolean updateEight = brcd_kcn_design_Controller.updateBoxNumEight(
					kcn_design_Entity.getSupervition_branch_design_id(),
					(sotu - 1));
			break;
		case 12:
			sotu = kcn_design_Entity.getNum_Box_Twelve();
			boolean updateTwelve = brcd_kcn_design_Controller
					.updateBoxNumTwelve(
							kcn_design_Entity.getSupervition_branch_design_id(),
							(sotu - 1));
			break;

		default:
			break;
		}
		supervisionTTBController.deleteTtbEntity(curEditItem_ttb);
		refreshListView();
		lv_constr_brcd_ttb_list.setSelection(curEditItem_ttb.getPosition());

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
		case Constants.GET_LOCATION_RESULT:
			if (resultCode == Activity.RESULT_OK) {
				this.curEditItem.setLatitude(data.getDoubleExtra(
						IntentConstants.INTENT_LAT,
						Constants.ID_DOUBLE_ENTITY_DEFAULT));
				this.curEditItem.setLongitude(data.getDoubleExtra(
						IntentConstants.INTENT_LONG,
						Constants.ID_DOUBLE_ENTITY_DEFAULT));
				// listSupervisionGS.
			}
			break;
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
		Bundle bun = getIntent().getExtras();
		this.itemData = (Constr_Construction_EmployeeEntity) bun
				.getSerializable(IntentConstants.INTENT_DATA);

		this.tv_constr_brcd_ttb_dropdown.setText(GloablUtils
				.getStringBRCDBackgroundInfo(iDesignInfo));
		this.tv_constr_brcd_ttb_station_code_value.setText(itemData
				.getStationCode());
		this.tv_constr_brcd_ttb_info_line_value.setText(itemData
				.getConstrCode());

		/* Drop down */
		this.listDesignInfo = GloablUtils.getListbrcdBackgroundInfo("");
		listTankUnqualifyItem = new Cat_Cause_Constr_UnQualifyController(this)
				.getAllUnQualifyItemByName(
						Constants.UNQUALIFY_TYPE.TU_THUE_BAO,
						Constants.UNQUALIFY_TYPE.BRCD);

		listTankAcceptanceItem = new Cat_Cause_Constr_AcceptanceController(this)
				.getAllUnQualifyItemByName(
						Constants.ACCEPTANCE_TYPE.TU_THUE_BAO,
						Constants.UNQUALIFY_TYPE.BRCD);
		this.supervition_brcd_id = bun.getLong(IntentConstants.INTENT_BRCD_ID);
		if (itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| itemData.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.rl_supervision_ttb_save.setVisibility(View.GONE);
		}
		this.refreshListView();
	}

	private void refreshListView() {
		brcd_Entity = brcd_Controller.getSupervisionBRCD_Sup(itemData
				.getSupervision_Constr_Id());
		listSupervisionTTB = new ArrayList<Supervision_BRCD_Ttb_Entity>();
		listSupervisionTTB = supervisionTTBController
				.getAllbrcd_tn(supervition_brcd_id);
		this.supervisionTtbAdapter = new Supervision_BRCD_TTB_Adapter(this,
				listSupervisionTTB);
		this.lv_constr_brcd_ttb_list.setAdapter(supervisionTtbAdapter);

		lv_constr_brcd_ttb_list.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				scroll_brcd_ttb.requestDisallowInterceptTouchEvent(true);
				int action = event.getActionMasked();
				switch (action) {
				case MotionEvent.ACTION_UP:
					scroll_brcd_ttb.requestDisallowInterceptTouchEvent(false);
					break;
				}
				return false;
			}
		});
		if (listSupervisionTTB.size() != 0) {
			for (Supervision_BRCD_Ttb_Entity curItemSupervisonGS : listSupervisionTTB) {
				List<Supervision_LBG_UnqualifyItemEntity> listUnqualifyItem = this.causeBrcdTtbController
						.getAllTankUnqulifyByTankId(curItemSupervisonGS
								.getSupervition_branch_box_id());
				List<Supervision_LBG_UnqualifyItemEntity> listAcceptItem = this.causeBrcdTtbController
						.getAllTanAcceptByTankId(curItemSupervisonGS
								.getSupervition_branch_box_id());
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listAcceptItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(
									Acceptance_Brcd_Box_Field.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}

				}
				curItemSupervisonGS.setListAcceptance(listAcceptItem);
				/* Gan anh nguyen nhan loi */
				for (Supervision_LBG_UnqualifyItemEntity curUnqualifyItem : listUnqualifyItem) {
					List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
							.getLstAttachFile(Cause_Brcd_Box_Field.TABLE_NAME,
									curUnqualifyItem.getCause_Line_Bg_Id());
					for (Supv_Constr_Attach_FileEntity itemFile : lstCurAttachFile) {
						curUnqualifyItem.getLstAttachFile().add(itemFile);
					}

				}
				curItemSupervisonGS.setListCauseUniQualify(listUnqualifyItem);
			}
		}

	}

	private void saveDataTank() {
		long idUser = GlobalInfo.getInstance().getUserId();
		for (Supervision_BRCD_Ttb_Entity curItemData : listSupervisionTTB) {
			Supervision_BRCD_Ttb_Entity brcd_kcn = new Supervision_BRCD_Ttb_Entity();
			brcd_kcn.setCab_type(curItemData.getCab_type());
			brcd_kcn.setBox_Name(curItemData.getBox_Name());
			brcd_kcn.setBox_code(curItemData.getBox_code());
			brcd_kcn.setLatitude(curItemData.getLatitude());
			brcd_kcn.setLongitude(curItemData.getLongitude());
			brcd_kcn.setDesc(curItemData.getDesc());
			brcd_kcn.setStatus(curItemData.getStatus());
			brcd_kcn.setSUPERVISION_BRCD_ID(supervition_brcd_id);
			brcd_kcn.setSync_Status(Constants.SYNC_STATUS.ADD);
			brcd_kcn.setIsActive(Constants.ISACTIVE.ACTIVE);
			brcd_kcn.setProcessId(0);
			brcd_kcn.setEmployeeId(idUser);
			brcd_kcn.setSupervition_branch_box_id(curItemData
					.getSupervition_branch_box_id());
			supervisionTTBController.updateSupervisionBRCD_Ttb(
					brcd_kcn.getSupervition_branch_box_id(), brcd_kcn);

			List<Supervision_LBG_UnqualifyItemEntity> listAddCause = curItemData
					.getListCauseUniQualify();
			List<Supervision_LBG_UnqualifyItemEntity> listAddAccept = curItemData
					.getListAcceptance();
			for (Supervision_LBG_UnqualifyItemEntity addItemCause : listAddAccept) {
				/* 1. Chinh sua nghiem thu */
				if (addItemCause.getCause_Line_Bg_Id() > 0) {

					// xoa nghiem thu khi chuyen
					// trang thai tu khong dat sang dat
//					if (curItemData.getStatus() == Constants.SUPERVISION_STATUS.CHUADAT) {
//						this.causeBrcdTtbController.deleteAccept(addItemCause);
//					}

					if (addItemCause.getLstNewAttachFile().size() > 0
							|| (addItemCause.getLstNewAttachFile().size() == 0 && addItemCause
									.getLstAttachFile().size() == 0)) {
						List<Supv_Constr_Attach_FileEntity> lstCurAttachFile = this.supvConstrAttachFileController
								.getLstAttachFile(
										Acceptance_Brcd_Box_Field.TABLE_NAME,
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
											Acceptance_Brcd_Box_Field.TABLE_NAME);

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
								.getSupervition_branch_box_id());

						addCauseItem.setSync_Status(Constants.SYNC_STATUS.ADD);
						addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
						addCauseItem.setProcessId(0);
						addCauseItem.setEmployeeId(idUser);

						long iAddCause = Ktts_KeyController.getInstance()
								.getKttsNextKey(
										Acceptance_Brcd_Box_Field.TABLE_NAME);

						if (iAddCause == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						addCauseItem.setAcceptance_Line_Bg_Tank_Id(iAddCause);
						this.causeBrcdTtbController.addItem(addCauseItem);
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
												Acceptance_Brcd_Box_Field.TABLE_NAME);

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
						this.causeBrcdTtbController.delete(addItemCause);
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
											Cause_Brcd_Box_Field.TABLE_NAME,
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
												Cause_Brcd_Box_Field.TABLE_NAME);

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
								.getSupervition_branch_box_id());
						addCauseItem.setSync_Status(Constants.SYNC_STATUS.ADD);
						addCauseItem.setIsActive(Constants.ISACTIVE.ACTIVE);
						addCauseItem.setProcessId(0);
						addCauseItem.setEmployeeId(idUser);
						addCauseItem.setSupervisionConstrId(itemData
								.getSupervision_Constr_Id());

						long iAddCause = Ktts_KeyController.getInstance()
								.getKttsNextKey(
										Cause_Brcd_Drop_Field.TABLE_NAME);

						if (iAddCause == 0) {
							outOfKey = true;
							return;
						} else
							outOfKey = false;

						addCauseItem.setCause_Line_Bg_Tank_Id(iAddCause);
						this.causeBrcdTtbController.addItem(addCauseItem);
						// Luu anh nguyen nhan loi neu co
						for (Supv_Constr_Attach_FileEntity itemFile : addItemCause
								.getLstNewAttachFile()) {
							if (!StringUtil.isNullOrEmpty(itemFile
									.getFile_Path())) {
								this.supvConstrAttachFileController
										.coppyAndAddAttachFile(this.itemData,
												itemFile.getFile_Path(),
												iAddCause,
												Cause_Brcd_Box_Field.TABLE_NAME);

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
			constr_Controller.updateStatus(
					itemData.getSupervision_Constr_Id(), 0);
		} else {
			constr_Controller.updateStatus(
					itemData.getSupervision_Constr_Id(), 1);
		}
		
	}

	private String checkVailid(List<Supervision_BRCD_Ttb_Entity> itemCheck) {
		String sReslut = "";
		int countNnkdCheck = 0;
		try {
			for (int i = 0; i < itemCheck.size(); i++) {

				if (itemCheck.get(i).getStatus() == 1) {
					if (itemCheck.get(i).getLatitude() == -1
							|| itemCheck.get(i).getLongitude() == -1) {

						sReslut = StringUtil
								.getString(R.string.brcd_chon_toado);
					}

				} else if (itemCheck.get(i).getStatus() == 0) {
					if (itemCheck.get(i).getLatitude() == -1
							|| itemCheck.get(i).getLongitude() == -1) {

						sReslut = StringUtil
								.getString(R.string.brcd_chon_toado);
					}
					for (int j = 0; j < itemCheck.get(i)
							.getListCauseUniQualify().size(); j++) {
						if (!itemCheck.get(i).getListCauseUniQualify().get(j)
								.isDelete()) {
							countNnkdCheck++;
							break;
						}
					}
					if (countNnkdCheck < 1) {
						sReslut = StringUtil
								.getString(R.string.constr_line_tank_nn_khongdat_tempty);
					}
				} else if (itemCheck.get(i).getLatitude() > 0
						|| itemCheck.get(i).getLongitude() > 0) {
					if (itemCheck.get(i).getStatus() == -1) {

						sReslut = StringUtil
								.getString(R.string.brcd_chon_thieu_trangthai);
					}

				} else if (!itemCheck.get(i).getDesc().equals("")) {
					if (itemCheck.get(i).getStatus() == -1) {

						sReslut = StringUtil
								.getString(R.string.brcd_chon_thieu_trangthai);
					}

				} else if (!itemCheck.get(i).getBox_code().equals("")) {
					if (itemCheck.get(i).getStatus() == -1) {

						sReslut = StringUtil
								.getString(R.string.brcd_chon_thieu_trangthai);
					}

				}
				if (!sReslut.equals("")) {
					sReslut = sReslut + " cho tủ "
							+ itemCheck.get(i).getBox_Name();
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sReslut;
	}

	/* Ghi nghiem thu vao danh sach ong */
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
			saveDataTank();

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (outOfKey) {
				Toast.makeText(Supervision_BRCD_TuThueBaoActivity.this,
						StringUtil.getString(R.string.text_out_of_key),
						Toast.LENGTH_SHORT).show();
				closeProgressDialog();
				return;
			}
			refreshListView();
			Toast.makeText(Supervision_BRCD_TuThueBaoActivity.this,
					StringUtil.getString(R.string.text_update_success),
					Toast.LENGTH_SHORT).show();
			closeProgressDialog();
		}

	}

}