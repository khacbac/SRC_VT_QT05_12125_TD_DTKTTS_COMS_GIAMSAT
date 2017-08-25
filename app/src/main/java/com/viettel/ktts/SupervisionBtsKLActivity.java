package com.viettel.ktts;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.viettel.database.Cat_Supervision_Constr_WorkController;
import com.viettel.database.Cat_Supv_Constr_MeasureController;
import com.viettel.database.Cause_Bts_Cat_WorkController;
import com.viettel.database.Cause_Bts_Pillar_AntenController;
import com.viettel.database.Cause_Bts_Power_PoleController;
import com.viettel.database.Supervision_BtsController;
import com.viettel.database.Supervision_Bts_MeasureController;
import com.viettel.database.Supervision_ConstrController;
import com.viettel.database.entity.Cat_Supervision_Constr_WorkEntity;
import com.viettel.database.entity.Cat_Supv_Constr_MeasureEntity;
import com.viettel.database.entity.Cause_Bts_Cat_WorkEntity;
import com.viettel.database.entity.Cause_Bts_Pillar_AntenEntity;
import com.viettel.database.entity.Cause_Bts_Power_PoleEntity;
import com.viettel.database.entity.Constr_Construction_EmployeeEntity;
import com.viettel.database.entity.DropdownItemEntity;
import com.viettel.database.entity.Supervision_BtsEntity;
import com.viettel.database.entity.Supervision_Bts_MeasureEntity;
import com.viettel.database.entity.Supervision_ConstrEntity;
import com.viettel.dialog.ConclusionDialog;
import com.viettel.sync.SyncTask;
import com.viettel.utils.GloablUtils;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

import java.util.ArrayList;
import java.util.List;

public class SupervisionBtsKLActivity extends SupervisionBtsBaseActivity {
	private View spvBts_KLView;
	/**
	 * textview
	 */
	private TextView supervision_bts_tv_matram;
	private TextView supervision_bts_tv_mact;

	/**
	 * combobox
	 */
	// combobox loai thong tin
	private RelativeLayout supervision_bts_kl_cb_thietke;
	private TextView supervision_bts_kl_tv_thietke;
	private int isInfomation = 1;
	/* Hien thi popup */
	private Menu_DropdownPopup dropdownPopupMenuInfomation;
	private List<DropdownItemEntity> listInfomation = null;
	private Constr_Construction_EmployeeEntity constr_ConstructionItem;
	private Supervision_BtsEntity btsEntity;

	private Button save;
	private CheckBox rb_dat;
	private CheckBox rb_kdat;
	// private RadioButton rb_hoanthanh;
	// private RadioButton rb_khoanthanh;

	// danh sach thi cong cot
	private List<Cause_Bts_Pillar_AntenEntity> listPillar;
	// thuc the danh gia lap dung cot
	private Cause_Bts_Cat_WorkEntity catWorkEntityLdc = null;
	// thuc the danh gia thi cong tiep dia
	private Cause_Bts_Cat_WorkEntity catWorkEntityTctd = null;
	// thuc the danh gia thi cong phong may
	private Cause_Bts_Cat_WorkEntity causeCatWorkEntityPm;
	// thuc the danh gia thi cong nha may no
	private Cause_Bts_Cat_WorkEntity causeCatWorkEntityNmn;
	// thuc the danh gia thi cong keo day dien nha may no
	private Cause_Bts_Cat_WorkEntity causeCatWorkEntityKdd_Nmn;
	// thuc the danh gia thi cong keo day dien
	private Cause_Bts_Cat_WorkEntity causeCatWorkEntityKdd = null;
	// thuc the danh gia lap dat thiet bi
	private List<Cause_Bts_Cat_WorkEntity> listCauseCatWorkEquipEntity = null;
	// thuc the danh gia lap dat viba dau thap
	private List<Cause_Bts_Cat_WorkEntity> listCauseCatWorkLowVibaEntity = null;
	// thuc the danh gia lap dat viba dau cao
	private List<Cause_Bts_Cat_WorkEntity> listCauseCatWorkHeighVibaEntity = null;
	// thuc the hang muc thi cong han noi tuong ung voi do kiem chat luong tuyen
	// anten feeder 2g
	private List<Cat_Supv_Constr_MeasureEntity> feeder2G;
	// anten feeder 3g
		private List<Cat_Supv_Constr_MeasureEntity> feeder3G;
	// thuc the hang muc thi cong han noi tuong ung voi do kiem viba
	private List<Cat_Supv_Constr_MeasureEntity> viba;
	// //thuc the danh gia voi tung hang muc
	// private Supervision_Bts_MeasureEntity supvBtsMeasureEntity;

	private List<Cat_Supervision_Constr_WorkEntity> listConstrWork;
	private List<Cat_Supervision_Constr_WorkEntity> listConstrWorkLow;
	private List<Cat_Supervision_Constr_WorkEntity> listConstrWorkHeigh;

	private List<Cause_Bts_Power_PoleEntity> listPowerPole;
	private Supervision_ConstrController supervisionConstroller;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.supervision_bts_kl_activity);
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
		spvBts_KLView = addView(R.layout.supervision_bts_kl_activity, R.id.rl_login);
		// textview
		supervision_bts_tv_matram = (TextView) spvBts_KLView.findViewById(R.id.rl_supervision_bts_equip_tv_matram);
		supervision_bts_tv_mact = (TextView) spvBts_KLView.findViewById(R.id.rl_supervision_bts_equip_tv_mact);

		// combobox
		setSupervision_bts_kl_cb_thietke((RelativeLayout) spvBts_KLView.findViewById(R.id.rl_supervision_bts_equip_search_thietke));

		supervision_bts_kl_tv_thietke = (TextView) spvBts_KLView.findViewById(R.id.rl_supervision_bts_equip_tv_thietke);
		supervision_bts_kl_tv_thietke.setOnClickListener(this);

		// radiobutton
		rb_dat = (CheckBox) spvBts_KLView.findViewById(R.id.rd_bts_kl_tt_dat);
		rb_kdat = (CheckBox) spvBts_KLView.findViewById(R.id.rd_bts_kl_tt_khongdat);

		rb_dat.setOnClickListener(this);
		rb_kdat.setOnClickListener(this);
		// rb_hoanthanh = (RadioButton)
		// findViewById(R.id.rd_bts_kl_td_hoanthanh);
		// rb_khoanthanh = (RadioButton)
		// findViewById(R.id.rd_bts_kl_td_chuahoanthanh);

		save = (Button) spvBts_KLView.findViewById(R.id.btn_bts_kl_save);
		save.setOnClickListener(this);

	}

	public void initData() {
		/* Drop down */
		String sChoice = "";
		listInfomation = GloablUtils.getListBTSInfo(sChoice);
		sChoice = getResources().getString(R.string.text_choice1);
		supervision_bts_kl_tv_thietke.setText(GloablUtils
				.getStringBTSInfo(isInfomation));

		supervisionConstroller = new Supervision_ConstrController(this);
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

		Cause_Bts_Pillar_AntenController btsPillarAntenController = new Cause_Bts_Pillar_AntenController(
				this);
		Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
				this);
		Cause_Bts_Power_PoleController btsPowerPoleController = new Cause_Bts_Power_PoleController(
				this);

		listPowerPole = btsPowerPoleController
				.getListCauseBts_Power_PoleEntity(btsEntity
						.getSupervision_Bts_Id());

		// lay danh sach danh gia thi cong cot
		listPillar = btsPillarAntenController
				.getListCauseBts_PillarAntenEntity(btsEntity
						.getSupervision_Bts_Id());
		// lay danh gia lap dung cot
		catWorkEntityLdc = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
				Constants.BTS_CONSTR_WORK.WORK_CODE_LDCAT,
				btsEntity.getSupervision_Bts_Id());

		// lay danh gia lap dung cot anten va thang cap ngoai troi

		// lay danh gia thi cong tiep dia
		catWorkEntityTctd = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
				Constants.BTS_CONSTR_WORK.WORK_CODE_TDBTS,
				btsEntity.getSupervision_Bts_Id());

		// lay danh gia thi cong phong may
		causeCatWorkEntityPm = causeCatWorkController
				.getCause_Bts_Cat_WorkEntity(
						Constants.UNQUALIFY_TYPE.SUB_TYPE_PHONG_MAY,
						btsEntity.getSupervision_Bts_Id());

		// lay danh gia thi cong nha may no
		causeCatWorkEntityNmn = causeCatWorkController
				.getCause_Bts_Cat_WorkEntity(
						Constants.UNQUALIFY_TYPE.SUB_TYPE_NHA_MAY_NO,
						btsEntity.getSupervision_Bts_Id());

		// lay danh gia thic ogn keo day dien nha may no
		causeCatWorkEntityKdd_Nmn = causeCatWorkController
				.getCause_Bts_Cat_WorkEntity(
						Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN_PHONG_MAY_NO,
						btsEntity.getSupervision_Bts_Id());

		// lay danh gia thi cong keo day dien
		causeCatWorkEntityKdd = causeCatWorkController
				.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_KEO_DAY_DIEN,
						btsEntity.getSupervision_Bts_Id());

		setListDataForEquip();

		// lay danh sach danh gia lap dat thiet bi
		listCauseCatWorkEquipEntity = new ArrayList<Cause_Bts_Cat_WorkEntity>();

		getDataFromDatabaseEquip();

		setListDataLowViba();

		// lay danh sach danh gia lap dat viba dau thap
		listCauseCatWorkLowVibaEntity = new ArrayList<Cause_Bts_Cat_WorkEntity>();

		getDataFromDatabaseVibaLow();

		setListDataHeightViba();

		// lay danh sach danh gia lap dat viba dau cao
		listCauseCatWorkHeighVibaEntity = new ArrayList<Cause_Bts_Cat_WorkEntity>();

		getDataFromDatabaseVibaHeigh();

		// lay du lieu thi cong han noi
		prepareListData();

		// lay du lieu phan ket luan
		Supervision_ConstrEntity constrItem = supervisionConstroller
				.getItem(constr_ConstructionItem.getSupervision_Constr_Id());

		if (constrItem.getSupervision_Progress() == Constants.SUPERVISION_PROGRESS.COMPLETED) {
			if (constrItem.getSupervision_Status() == Constants.SUPERVISION_STATUS.DAT) {
				rb_dat.setChecked(true);
			} else if (constrItem.getSupervision_Status() == Constants.SUPERVISION_STATUS.CHUADAT) {
				rb_kdat.setChecked(true);
			}
		}

		// if (constrItem.getSupervision_Progress() ==
		// Constants.SUPERVISION_PROGRESS.COMPLETED) {
		// rb_hoanthanh.setChecked(true);
		// } else {
		// rb_khoanthanh.setChecked(true);
		// }

		/* Set endable va disable voi cong trinh da hoan thanh */
		if (constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.COMPLETED
				|| constr_ConstructionItem.getConstrProgressId() == Constants.SUPERVISION_PROGRESS.UNCOMPLETED) {
			this.save.setVisibility(View.GONE);
		}

	}

	public void setListDataLowViba() {
		listConstrWorkLow = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

		Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
				this);

		// lay ten constr work tuong ung voi work code thi cong ngoai troi
		Cat_Supervision_Constr_WorkEntity constrWorkThicong_ngtroi = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA);

		// lay ten constr work tuong ung voi work code cap trung tan
		Cat_Supervision_Constr_WorkEntity constrWorkCap_trungtan = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN);

		// lay ten constr work tuong ung voi work code tinh trang phan cung
		Cat_Supervision_Constr_WorkEntity constrWorkTinhtrang_phancung = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG);

		// lay ten constr work tuong ung voi work code cap nguon DC
		Cat_Supervision_Constr_WorkEntity constrWorkCapnguon_Dc = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC);

		// lay ten constr work tuong ung voi work code cap bang tan goc
		Cat_Supervision_Constr_WorkEntity constrWorkCapbangtan = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC);

		listConstrWorkLow.add(constrWorkThicong_ngtroi);
		listConstrWorkLow.add(constrWorkCap_trungtan);
		listConstrWorkLow.add(constrWorkTinhtrang_phancung);
		listConstrWorkLow.add(constrWorkCapnguon_Dc);
		listConstrWorkLow.add(constrWorkCapbangtan);
	}

	public void setListDataHeightViba() {
		listConstrWorkHeigh = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

		Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
				this);

		// lay ten constr work tuong ung voi work code thi cong ngoai troi
		Cat_Supervision_Constr_WorkEntity constrWorkThicong_ngtroi = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA);

		// lay ten constr work tuong ung voi work code cap trung tan
		Cat_Supervision_Constr_WorkEntity constrWorkCap_trungtan = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN);

		// lay ten constr work tuong ung voi work code tinh trang phan cung
		Cat_Supervision_Constr_WorkEntity constrWorkTinhtrang_phancung = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG);

		// lay ten constr work tuong ung voi work code cap nguon DC
		Cat_Supervision_Constr_WorkEntity constrWorkCapnguon_Dc = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC);

		// lay ten constr work tuong ung voi work code cap bang tan goc
		Cat_Supervision_Constr_WorkEntity constrWorkCapbangtan = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC);

		listConstrWorkHeigh.add(constrWorkThicong_ngtroi);
		listConstrWorkHeigh.add(constrWorkCap_trungtan);
		listConstrWorkHeigh.add(constrWorkTinhtrang_phancung);
		listConstrWorkHeigh.add(constrWorkCapnguon_Dc);
		listConstrWorkHeigh.add(constrWorkCapbangtan);
	}

	public void getDataFromDatabaseVibaLow() {
		Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
				this);
		String worktype = Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW;
		for (int i = 0; i < listConstrWorkLow.size(); i++) {
			Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

			String workcode = listConstrWorkLow.get(i).getWork_Code();

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

			// item.setListCauseUniQualify(convertUnqualifyToItem(item
			// .getListConstrUnqualifyEntity()));
			// item.setWorkCode(workcode);
			// item.setStt(i + 1);
			// item.setTenHangMuc(listConstrWork.get(i).getWork_Name());

			if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
					|| item.getBts_Cat_WorkEntity().getStatus() >= 0) {
				listCauseCatWorkLowVibaEntity.add(item);
			}

		}
	}

	public void getDataFromDatabaseVibaHeigh() {
		Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
				this);
		String worktype = Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH;

		for (int i = 0; i < listConstrWorkHeigh.size(); i++) {
			Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

			String workcode = listConstrWorkHeigh.get(i).getWork_Code();

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

			// item.setListCauseUniQualify(convertUnqualifyToItem(item
			// .getListConstrUnqualifyEntity()));
			// item.setWorkCode(workcode);
			// item.setStt(i + 1);
			// item.setTenHangMuc(listConstrWork.get(i).getWork_Name());

			if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
					|| item.getBts_Cat_WorkEntity().getStatus() >= 0) {
				listCauseCatWorkHeighVibaEntity.add(item);
			}

		}
	}

	public void setListDataForEquip() {
		listConstrWork = new ArrayList<Cat_Supervision_Constr_WorkEntity>();

		Cat_Supervision_Constr_WorkController constrWorkController = new Cat_Supervision_Constr_WorkController(
				this);

		// lay ten constr work tuong ung voi work code tiep dat thoat set
		Cat_Supervision_Constr_WorkEntity constrWorkTiepdat_Thoatset = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK);

		// lay ten constr work tuong ung voi work code tiep dat feeder
		Cat_Supervision_Constr_WorkEntity constrWorkTiepdat_Feeder = constrWorkController
				.getListConstrWorkEntityByWorkCode(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER);

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

		listConstrWork.add(constrWorkTiepdat_Thoatset);
		listConstrWork.add(constrWorkTiepdat_Feeder);
		listConstrWork.add(constrWorkThangcap);
		listConstrWork.add(constrWorkHethongden);
		listConstrWork.add(constrWorkTudienAc);

		listConstrWork.add(constrWorkTunguon);
		listConstrWork.add(constrWorkTuBts);
		listConstrWork.add(constrWorkRackDf);
		listConstrWork.add(constrWorkCapnguon_Thangcap);
		listConstrWork.add(constrWorkDenbaokhong);

		listConstrWork.add(constrWorkAntenFeeder);
		listConstrWork.add(constrWorkTambitcap);
		listConstrWork.add(constrWorkRru);
		listConstrWork.add(constrWorkCapquangnguon);
	}

	public void getDataFromDatabaseEquip() {
		Cause_Bts_Cat_WorkController causeCatWorkController = new Cause_Bts_Cat_WorkController(
				this);
		for (int i = 0; i < listConstrWork.size(); i++) {
			Cause_Bts_Cat_WorkEntity item = new Cause_Bts_Cat_WorkEntity();

			String workcode = listConstrWork.get(i).getWork_Code();

			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK)) {
				item = causeCatWorkController
						.getCause_Bts_Cat_WorkEntity(
								Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK,
								btsEntity.getSupervision_Bts_Id());

			}
			if (workcode
					.equals(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER)) {
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER,
						btsEntity.getSupervision_Bts_Id());
			}
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
				item = causeCatWorkController.getCause_Bts_Cat_WorkEntity(
						Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP,
						btsEntity.getSupervision_Bts_Id());
			}

			// item.setListCauseUniQualify(convertUnqualifyToItem(item
			// .getListConstrUnqualifyEntity()));
			// item.setWorkCode(workcode);
			// item.setStt(i + 1);
			// item.setTenHangMuc(listConstrWork.get(i).getWork_Name());

			if (!item.getBts_Cat_WorkEntity().getFail_Desc().equals("")
					|| item.getBts_Cat_WorkEntity().getStatus() >= 0) {
				listCauseCatWorkEquipEntity.add(item);
			}

		}
	}

	private String checkVailid() {
		String sResult = "";
		if (!rb_dat.isChecked() && !rb_kdat.isChecked()) {
			sResult = StringUtil
					.getString(R.string.supervision_bts_kl_danhgia_chooice_status_null);
			return sResult;
		}

		if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE
				&& btsEntity.getConstructionType() == Constants.CONSTRUCTION_TYPE.XAY_MOI) {
			if (btsEntity.getPillar_STATUS_QUALITY() < 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_danhgia_danhgia_clc_null);
				return sResult;
			} else {
				if (btsEntity.getPillar_ANTEN() != Constants.BTS_POS_PILLAR_ANTEN.CO_SAN) {
					if (listPillar.size() == 0) {
						sResult = StringUtil
								.getString(R.string.supervision_bts_kl_danhgia_thicongcot_null);
						return sResult;
					}
				}
				if (btsEntity.getPillar_STATUS_QUALITY() > 0
						&& catWorkEntityLdc.getBts_Cat_WorkEntity().getStatus() < 0) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_danhgia_lapdungcot_null);
					return sResult;
				}
			}

			if (catWorkEntityTctd.getBts_Cat_WorkEntity().getStatus() < 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_danhgia_thicongtiepdia_null);
				return sResult;
			}
			if (btsEntity.getHouse_TYPE() != Constants.BTS_HOUSE_TYPE.CO_SAN
					&& causeCatWorkEntityPm.getBts_Cat_WorkEntity().getStatus() < 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_danhgia_thicongphongmay_null);
				return sResult;
			}
			if (btsEntity.getHouse_GENERATOR() != Constants.ID_ENTITY_DEFAULT
					&& causeCatWorkEntityNmn.getBts_Cat_WorkEntity()
							.getStatus() < 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_danhgia_thicongnhamayno_null);
				return sResult;
			}
			if (btsEntity.getHouse_GENERATOR() != Constants.ID_ENTITY_DEFAULT
					&& causeCatWorkEntityKdd_Nmn.getBts_Cat_WorkEntity()
							.getStatus() < 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_danhgia_keodaydien_nmn_null);
				return sResult;
			}
			if (causeCatWorkEntityKdd.getBts_Cat_WorkEntity().getStatus() < 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_danhgia_thicongkeodaydien_null);
				return sResult;
			}
			//
			// String worktype = Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW;
			// setListTcntData(worktype);
			// setListTctnData(worktype);
			// listDataTcntLow = getDataTcntFromDatabase(worktype);
			// listDataTctnLow = getDataTctnFromDatabase(worktype);
			//
			// worktype = Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH;
			// setListTcntData(worktype);
			// setListTctnData(worktype);
			// listDataTcntHeight = getDataTcntFromDatabase(worktype);
			// listDataTctnHeight = getDataTctnFromDatabase(worktype);

			Supervision_Bts_MeasureController measureController = new Supervision_Bts_MeasureController(
					this);

			for (int i = 0; i < feeder2G.size(); i++) {
				Supervision_Bts_MeasureEntity supvBtsMeasureEntity = measureController
						.getBtsMeasureEntity(btsEntity.getSupervision_Bts_Id(),
								feeder2G.get(i).getCat_Supv_Constr_Measure_Id());
				if (supvBtsMeasureEntity == null) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_hangmuc)
							+ " "
							+ feeder2G.get(i).getMeasure_Name()
							+ " "
							+ StringUtil
									.getString(R.string.supervision_bts_kl_danhgia_hannoi_null);
					return sResult;
				}
			}
			
			for (int i = 0; i < feeder3G.size(); i++) {
				Supervision_Bts_MeasureEntity supvBtsMeasureEntity = measureController
						.getBtsMeasureEntity(btsEntity.getSupervision_Bts_Id(),
								feeder3G.get(i).getCat_Supv_Constr_Measure_Id());
				if (supvBtsMeasureEntity == null) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_hangmuc)
							+ " "
							+ feeder3G.get(i).getMeasure_Name()
							+ " "
							+ StringUtil
									.getString(R.string.supervision_bts_kl_danhgia_hannoi_null);
					return sResult;
				}
			}

			if (listCauseCatWorkLowVibaEntity.size() > 0
					|| listCauseCatWorkHeighVibaEntity.size() > 0) {
				for (int i = 0; i < viba.size(); i++) {
					Supervision_Bts_MeasureEntity supvBtsMeasureEntity = measureController
							.getBtsMeasureEntity(btsEntity
									.getSupervision_Bts_Id(), viba.get(i)
									.getCat_Supv_Constr_Measure_Id());
					if (supvBtsMeasureEntity == null) {
						sResult = StringUtil
								.getString(R.string.supervision_bts_kl_hangmuc)
								+ " "
								+ viba.get(i).getMeasure_Name()
								+ " "
								+ StringUtil
										.getString(R.string.supervision_bts_kl_danhgia_hannoi_null);
						return sResult;
					}
				}
			}
		}

		return sResult;
	}

	// lay du lieu thi cong han noi
	private void prepareListData() {
		Cat_Supv_Constr_MeasureController constrMeasureController = new Cat_Supv_Constr_MeasureController(
				this);

		// 2g
		Cat_Supv_Constr_MeasureEntity constrMeasureAll2G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ALL_2G);

		Cat_Supv_Constr_MeasureEntity constrMeasureJumper2G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_2G);

		Cat_Supv_Constr_MeasureEntity constrMeasureFeeder2G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_FEEDER_2G);

		Cat_Supv_Constr_MeasureEntity constrMeasureJumperFeeder2G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_FEEDER_2G);

		Cat_Supv_Constr_MeasureEntity constrMeasureConnecter2G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_CONNECTER_2G);

		Cat_Supv_Constr_MeasureEntity constrMeasureAnten2G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ANTEN_2G);

		Cat_Supv_Constr_MeasureEntity constrMeasureResoantAnten2G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_RESOANT_ANTEN_2G);

		// 3g
		Cat_Supv_Constr_MeasureEntity constrMeasureAll3G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ALL_3G);

		Cat_Supv_Constr_MeasureEntity constrMeasureJumper3G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_3G);

		Cat_Supv_Constr_MeasureEntity constrMeasureFeeder3G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_FEEDER_3G);

		Cat_Supv_Constr_MeasureEntity constrMeasureJumperFeeder3G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_JUMPER_FEEDER_3G);

		Cat_Supv_Constr_MeasureEntity constrMeasureConnecter3G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_CONNECTER_3G);

		Cat_Supv_Constr_MeasureEntity constrMeasureAnten3G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_ANTEN_3G);

		Cat_Supv_Constr_MeasureEntity constrMeasureResoantAnten3G = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_VSWR_RESOANT_ANTEN_3G);

		// them du lieu vao tung group title
		feeder2G = new ArrayList<Cat_Supv_Constr_MeasureEntity>();
		feeder2G.add(constrMeasureAll2G);
		feeder2G.add(constrMeasureJumper2G);
		feeder2G.add(constrMeasureFeeder2G);
		feeder2G.add(constrMeasureJumperFeeder2G);
		feeder2G.add(constrMeasureConnecter2G);
		feeder2G.add(constrMeasureAnten2G);
		feeder2G.add(constrMeasureResoantAnten2G);
		
		feeder3G = new ArrayList<Cat_Supv_Constr_MeasureEntity>();
		feeder3G.add(constrMeasureAll3G);
		feeder3G.add(constrMeasureJumper3G);
		feeder3G.add(constrMeasureFeeder3G);
		feeder3G.add(constrMeasureJumperFeeder3G);
		feeder3G.add(constrMeasureConnecter3G);
		feeder3G.add(constrMeasureAnten3G);
		feeder3G.add(constrMeasureResoantAnten3G);

		viba = new ArrayList<Cat_Supv_Constr_MeasureEntity>();

		Cat_Supv_Constr_MeasureEntity constrMeasureCapacityViba = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_CAPACITY_VIBA);

		Cat_Supv_Constr_MeasureEntity constrMeasureObtainViba = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_OBTAIN_VIBA);

		Cat_Supv_Constr_MeasureEntity constrMeasureBerViba = constrMeasureController
				.getConstrMeasureEntityByWorkCode(Constants.BTS_CONSTR_MEASURE.WORK_CODE_BER_VIBA);

		viba.add(constrMeasureCapacityViba);
		viba.add(constrMeasureObtainViba);
		viba.add(constrMeasureBerViba);

	}

	public String checkKhongDat() {
		String sResult = "";
		if (constr_ConstructionItem.getCatStationTypeId() != Constants.STATION_TYPE.TYPE_COSITE
				&& btsEntity.getConstructionType() == Constants.CONSTRUCTION_TYPE.XAY_MOI) {

			if (btsEntity.getPillar_STATUS_QUALITY() == Constants.BTS_ASSESS_PILLAR.DAT) {
				for (Cause_Bts_Pillar_AntenEntity i : listPillar) {
					if (i.getSupervision_Bts_Pillar_AntenEntity().getStatus() == 0) {
						sResult = StringUtil
								.getString(R.string.supervision_bts_kl_pillar_khongdat);
						return sResult;
					}
				}
				if (catWorkEntityLdc.getBts_Cat_WorkEntity().getStatus() == 0) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_lapdungcot_khongdat);
					return sResult;
				}
			} else {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_danhgia_chatluong_cot_khongdat);
				return sResult;
			}

			if (catWorkEntityTctd.getBts_Cat_WorkEntity().getStatus() == 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_tiepdia_khongdat);
				return sResult;
			}

			if (causeCatWorkEntityPm.getBts_Cat_WorkEntity().getStatus() == 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_phongmay_khongdat);
				return sResult;
			}

			if (causeCatWorkEntityNmn.getBts_Cat_WorkEntity().getStatus() == 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_nhamayno_khongdat);
				return sResult;
			}

			if (causeCatWorkEntityKdd_Nmn.getBts_Cat_WorkEntity().getStatus() == 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_keodaydien_nmn_khongdat);
				return sResult;
			}

			if (causeCatWorkEntityKdd.getBts_Cat_WorkEntity().getStatus() == 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_keodaydien_khongdat);
				return sResult;
			}

			if (listPowerPole.size() > 0) {
				sResult = StringUtil
						.getString(R.string.supervision_bts_kl_cottrongmoi_khongdat);
				return sResult;
			}

			for (Cause_Bts_Cat_WorkEntity i : listCauseCatWorkEquipEntity) {
				if (i.getBts_Cat_WorkEntity().getStatus() == 0) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_lapdatthietbi_khongdat);
					return sResult;
				}
			}

			for (Cause_Bts_Cat_WorkEntity i : listCauseCatWorkLowVibaEntity) {
				if (i.getBts_Cat_WorkEntity().getStatus() == 0) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_lapdatviba_low_khongdat);
					return sResult;
				}
			}

			for (Cause_Bts_Cat_WorkEntity i : listCauseCatWorkHeighVibaEntity) {
				if (i.getBts_Cat_WorkEntity().getStatus() == 0) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_lapdatviba_heigh_khongdat);
					return sResult;
				}
			}

			Supervision_Bts_MeasureController measureController = new Supervision_Bts_MeasureController(
					this);

			for (int i = 0; i < feeder2G.size(); i++) {
				Supervision_Bts_MeasureEntity supvBtsMeasureEntity = measureController
						.getBtsMeasureEntity(btsEntity.getSupervision_Bts_Id(),
								feeder2G.get(i).getCat_Supv_Constr_Measure_Id());
				if (supvBtsMeasureEntity != null
						&& supvBtsMeasureEntity.getMeasure_Status() == 0) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_hangmuc)
							+ " "
							+ feeder2G.get(i).getMeasure_Name()
							+ " "
							+ StringUtil
									.getString(R.string.supervision_bts_kl_danhgia_hannoi_kdat);
					return sResult;
				}
			}
			
			for (int i = 0; i < feeder3G.size(); i++) {
				Supervision_Bts_MeasureEntity supvBtsMeasureEntity = measureController
						.getBtsMeasureEntity(btsEntity.getSupervision_Bts_Id(),
								feeder3G.get(i).getCat_Supv_Constr_Measure_Id());
				if (supvBtsMeasureEntity != null
						&& supvBtsMeasureEntity.getMeasure_Status() == 0) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_hangmuc)
							+ " "
							+ feeder3G.get(i).getMeasure_Name()
							+ " "
							+ StringUtil
									.getString(R.string.supervision_bts_kl_danhgia_hannoi_kdat);
					return sResult;
				}
			}

			for (int i = 0; i < viba.size(); i++) {
				Supervision_Bts_MeasureEntity supvBtsMeasureEntity = measureController
						.getBtsMeasureEntity(btsEntity.getSupervision_Bts_Id(),
								viba.get(i).getCat_Supv_Constr_Measure_Id());
				if (supvBtsMeasureEntity != null
						&& supvBtsMeasureEntity.getMeasure_Status() == 0) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_hangmuc)
							+ " "
							+ viba.get(i).getMeasure_Name()
							+ " "
							+ StringUtil
									.getString(R.string.supervision_bts_kl_danhgia_hannoi_kdat);
					return sResult;
				}
			}
		} else {
			for (Cause_Bts_Cat_WorkEntity i : listCauseCatWorkEquipEntity) {
				if (i.getBts_Cat_WorkEntity().getStatus() == 0) {
					sResult = StringUtil
							.getString(R.string.supervision_bts_kl_lapdatthietbi_khongdat);
					return sResult;
				}
			}
		}

		return sResult;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// click chon dat
		case R.id.rd_bts_kl_tt_dat:
			if (rb_dat.isChecked()) {
				rb_kdat.setChecked(false);
			}
			break;
		// click chon khong dat
		case R.id.rd_bts_kl_tt_khongdat:
			if (rb_kdat.isChecked()) {
				rb_dat.setChecked(false);
			}
			break;
			
		// TODO: Cập nhật trạng thái cho toàn bộ công trình
		// click save
		case R.id.btn_bts_kl_save:
			if(!GlobalInfo.getInstance().isCheckIn()){
				showAlertDialogCheckInRequire(this, getString(R.string.checkin_require), getString(R.string.text_close));
				break;
			}
			String sMessage = this.checkVailid();
			if (!StringUtil.isNullOrEmpty(sMessage)) {
				this.showDialog(sMessage);
			} else {
				String sMessageKhongDat = checkKhongDat();

				if (this.rb_dat.isChecked()
						&& !StringUtil.isNullOrEmpty(sMessageKhongDat)) {
					this.showDialog(sMessageKhongDat);
					return;
				}
				String sProgress = StringUtil
						.getString(R.string.constr_sonstruction_progress_complete);
				if (StringUtil.isNullOrEmpty(sMessageKhongDat)) {
					String sStatus = "";
					if (this.rb_dat.isChecked()) {
						sStatus = rb_dat.getText().toString();
					} else {
						sStatus = rb_kdat.getText().toString();

						this.showDialog(StringUtil
								.getString(R.string.supervision_bts_kl_access_dat));
						return;
					}

					// if (this.rb_hoanthanh.isChecked()) {
					// sProgress = rb_hoanthanh.getText().toString();
					// } else {
					// sProgress = rb_khoanthanh.getText().toString();
					// }
					ConclusionDialog conDialog = new ConclusionDialog(this,
							sStatus, sProgress);
					conDialog.show();
				} else {
					String sStatus = "";
					if (this.rb_kdat.isChecked()) {
						sStatus = rb_kdat.getText().toString();
					}
					// String sProgress = "";
					// if (this.rb_hoanthanh.isChecked()) {
					// sProgress = rb_hoanthanh.getText().toString();
					// } else {
					// sProgress = rb_khoanthanh.getText().toString();
					// }
					ConclusionDialog conDialog = new ConclusionDialog(this,
							sStatus, sProgress);
					conDialog.show();

				}

			}
			break;
		case R.id.rl_supervision_bts_equip_tv_thietke:
			this.dropdownPopupMenuInfomation = new Menu_DropdownPopup(this,
					this.listInfomation);

			dropdownPopupMenuInfomation.show(v);
			break;
		}
	}

	// TODO: Phương thức lưu trạng thái công trình BTS
	private void saveDataKL() {
		try {
			/* update SUPERVISION_CONSTR */
			Supervision_ConstrController superConstrController = new Supervision_ConstrController(
					this);
			int iStatus = -1;
			if (this.rb_dat.isChecked()) {
				iStatus = Constants.SUPERVISION_STATUS.DAT;
			} else if(this.rb_kdat.isChecked()){
				iStatus = Constants.SUPERVISION_STATUS.CHUADAT;
			}else {
				iStatus = -1;
			}
			int iProgress = -1;
			// if (this.rb_hoanthanh.isChecked()) {
			iProgress = Constants.SUPERVISION_PROGRESS.COMPLETED;
			// } else {
			// iProgress = Constants.SUPERVISION_PROGRESS.UNCOMPLETED;
			// }
			String sNoteCode = superConstrController.getNoteCode(GlobalInfo
					.getInstance().getGroupCode());

			superConstrController.updateStatusProgegressCode(
					this.constr_ConstructionItem.getConstructId(), iStatus,
					iProgress, sNoteCode);

		} catch (Exception e) {
			Log.e("error", e.toString());
		}
		// this.showDialog(StringUtil.getString(R.string.text_update_success));
		Toast toast = Toast.makeText(this,
				getResources().getString(R.string.text_update_success),
				Toast.LENGTH_LONG);

		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
		this.gotoHomeActivity(new Bundle());
	}

	@Override
	public void onEvent(int eventType, View control, Object data) {
		switch (eventType) {

		case OnEventControlListener.EVENT_DROPDOWN_ITEM_CLICK:
			DropdownItemEntity dropdownItem = (DropdownItemEntity) data;
			String typeItem = dropdownItem.getType();
			if (typeItem.equals(Constants.DROPDOWN_TYPE.DESIGN_INFO)) {
				isInfomation = dropdownItem.getId();

				this.supervision_bts_kl_tv_thietke.setText(dropdownItem
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

			}

			break;
		case OnEventControlListener.EVENT_SUPERVISION_BG_OK:
			this.saveDataKL();
			break;
		default:
			super.onEvent(eventType, control, data);
			break;
		}

	}

	private void setHeader() {
		final Header myActionBar = (Header) spvBts_KLView.findViewById(R.id.actionbar);
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
				requestSync(SupervisionBtsKLActivity.this);
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
		gotoHomeActivity(new Bundle());
		finish();
	}

	public RelativeLayout getSupervision_bts_kl_cb_thietke() {
		return supervision_bts_kl_cb_thietke;
	}

	public void setSupervision_bts_kl_cb_thietke(
			RelativeLayout supervision_bts_kl_cb_thietke) {
		this.supervision_bts_kl_cb_thietke = supervision_bts_kl_cb_thietke;
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
