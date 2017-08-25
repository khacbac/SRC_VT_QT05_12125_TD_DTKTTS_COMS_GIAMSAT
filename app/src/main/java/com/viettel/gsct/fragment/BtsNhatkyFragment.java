package com.viettel.gsct.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.common.GlobalInfo;
import com.viettel.constants.Constants;
import com.viettel.database.Cat_Constr_TeamController;
import com.viettel.database.Constr_Team_ProgressController;
import com.viettel.database.Constr_Work_LogsController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Supervision_CNVController;
import com.viettel.database.entity.Cat_Constr_TeamEntity;
import com.viettel.database.entity.Constr_ObStructionEntity;
import com.viettel.database.entity.Constr_ObStruction_TypeEntity;
import com.viettel.database.entity.Constr_Team_ProgressEntity;
import com.viettel.database.entity.Constr_Work_LogsEntity;
import com.viettel.database.field.Constr_ObStructionField;
import com.viettel.database.field.Constr_Team_ProgressField;
import com.viettel.database.field.Constr_Work_LogsField;
import com.viettel.gsct.GSCTUtils;
import com.viettel.gsct.View.TeamView;
import com.viettel.ktts.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by admin2 on 05/04/2017.
 */

public class BtsNhatkyFragment extends BaseFragment {
    private static final String TAG = "BtsNhatkyFragment";

    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.sw_thi_cong)
    SwitchCompat swThiCong;
    @BindView(R.id.sp_nguyen_nhan)
    AppCompatSpinner spNguyenNhan;
    @BindView(R.id.tr_nguyen_nhan)
    LinearLayout trNguyenNhan;
    @BindView(R.id.sp_loai_vuong)
    AppCompatSpinner spLoaiVuong;
    @BindView(R.id.tr_loai_vuong)
    LinearLayout trLoaiVuong;
    @BindView(R.id.sp_thoitiet)
    Spinner spThoitiet;
    @BindView(R.id.et_thay_doi)
    AppCompatEditText etThayDoi;
    @BindView(R.id.et_y_kien_giam_sat)
    AppCompatEditText etYKienGiamSat;
    @BindView(R.id.et_y_kien_thi_cong)
    AppCompatEditText etYKienThiCong;
    Unbinder unbinder1;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.title3)
    TextView title3;
    @BindView(R.id.root_layout)
    LinearLayout rootLayout;

    private Unbinder unbinder;

    public static final int TYPE_TUYEN_DEFAULT = 0;
    public static final int TYPE_TUYEN_NGAM = 2;
    public static final int TYPE_TUYEN_TREO = 3;

    private HashSet<View> sThicong = new HashSet<>();
    private HashSet<View> sKhongThiCong = new HashSet<>();

    private Constr_Work_LogsEntity constrWorkLogsEntity;
    Constr_ObStructionEntity obstructionEntity = null;
    private int type = TYPE_TUYEN_DEFAULT;

    private Cat_Constr_TeamController cat_constr_teamController;

    private ConcurrentHashMap<Long, TeamView> hashTeamView = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, Constr_Team_ProgressEntity> hashTeams = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, Constr_ObStruction_TypeEntity> hashObStructionsById = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, Constr_ObStruction_TypeEntity> hashObStructionsByPosition = new ConcurrentHashMap<>();

    private Set<String> filterCodes = new HashSet<>();

    public static BaseFragment newInstance() {
        BtsNhatkyFragment fragment = new BtsNhatkyFragment();
        return fragment;
    }

    public static BaseFragment newInstance(int type) {
        BtsNhatkyFragment fragment = new BtsNhatkyFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_bts_thi_cong, container, false);
        unbinder = ButterKnife.bind(this, layout);
        sKhongThiCong.clear();
        sKhongThiCong.add(trNguyenNhan);
        sKhongThiCong.add(trLoaiVuong);

        sThicong.clear();
//        sThicong.add(title1);
//        sThicong.add(title2);
//        sThicong.add(title3);
//        sThicong.add(etYKienGiamSat);
//        sThicong.add(etYKienThiCong);
//        sThicong.add(etThayDoi);

        Bundle args = getArguments();
        if (args != null)
            type = args.getInt("type", TYPE_TUYEN_DEFAULT);

        initViews();
        initData();
        unbinder1 = ButterKnife.bind(this, layout);
        return layout;
    }

    private void initViews() {
        tvDate.setText(GSCTUtils.getDateNow("dd/MM/yyyy"));
        ArrayAdapter<CharSequence> adapterThoitiet = ArrayAdapter.createFromResource(getContext(),
                R.array.array_bts_thoi_tiet, R.layout.spinner_textview);
        adapterThoitiet.setDropDownViewResource(R.layout.spinner_dropdown_textview);
        spThoitiet.setAdapter(adapterThoitiet);
        spThoitiet.setSelection(4); // Mặc định là bình thường

        ArrayAdapter<CharSequence> adapterNguyennhan = ArrayAdapter.createFromResource(getContext(),
                R.array.array_bts_nguyen_nhan, R.layout.spinner_textview);

        adapterNguyennhan.setDropDownViewResource(R.layout.spinner_dropdown_textview);
        spNguyenNhan.setAdapter(adapterNguyennhan);

        ArrayList<Constr_ObStruction_TypeEntity> obStrucstions = new Supervision_CNVController(getContext()).getItemConstrObStructionTypes();
        int i = 0;
        List<CharSequence> names = new ArrayList<>();
        for (Constr_ObStruction_TypeEntity obstruction : obStrucstions) {
            obstruction.setPosition(i++);
            names.add(obstruction.getName());
            hashObStructionsByPosition.put(obstruction.getPosition(), obstruction);
            hashObStructionsById.put(obstruction.getConstrObStructionTypeId(), obstruction);
        }
        ArrayAdapter<CharSequence> adapterLoaivuong = new ArrayAdapter<CharSequence>(getContext(), R.layout.spinner_textview, names);
        adapterLoaivuong.setDropDownViewResource(R.layout.spinner_dropdown_textview);
        spLoaiVuong.setAdapter(adapterLoaivuong);

        spNguyenNhan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getCount() - 1) {
                    // Loại vướng
                    spLoaiVuong.setEnabled(true);
                    spLoaiVuong.setClickable(true);
                } else {
                    spLoaiVuong.setEnabled(false);
                    spLoaiVuong.setClickable(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swThiCong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateViewByIsWork(swThiCong.isChecked());
            }
        });
    }


    public void initData() {
        super.initData();
        cat_constr_teamController = new Cat_Constr_TeamController(getContext());

        if (type == TYPE_TUYEN_NGAM) {
            filterCodes.add("DDR");
            filterCodes.add("DXB");
            filterCodes.add("DKC");
            filterCodes.add("MAY");
        } else if (type == TYPE_TUYEN_TREO) {
            filterCodes.add("DKC");
            filterCodes.add("DTC");
        }

        ArrayList<Cat_Constr_TeamEntity> arr = cat_constr_teamController.getCates(constr_ConstructionItem.getConstrType());
        int i = 1;
        for (Cat_Constr_TeamEntity entity : arr) {
            if (type != TYPE_TUYEN_DEFAULT && !filterCodes.contains(entity.getCode()))
                continue;

            TeamView view = new TeamView(getContext());
            view.setTitle(entity.getName());
            rootLayout.addView(view, ++i);
            hashTeamView.put(entity.getCat_constr_team_id(), view);
            sThicong.add(view);
        }

        constrWorkLogsEntity = new Constr_Work_LogsController(getContext()).getItem(constr_ConstructionItem.getConstructId(), GSCTUtils.getDateNow());
        if (constrWorkLogsEntity == null) return;

        swThiCong.setChecked(constrWorkLogsEntity == null || constrWorkLogsEntity.getIs_work() == 1);
        updateViewByIsWork(swThiCong.isChecked());


        etThayDoi.setText(constrWorkLogsEntity.getWork_content());
        etYKienThiCong.setText(constrWorkLogsEntity.getConstructor_comments());
        etYKienGiamSat.setText(constrWorkLogsEntity.getMonitor_comments());

        // Id thời tiết đếm từ 1
        if (constrWorkLogsEntity.getCat_Weather_id() <=0) {
            constrWorkLogsEntity.setCat_Weather_id(1);
        }
        spThoitiet.setSelection(constrWorkLogsEntity.getCat_Weather_id() - 1);

        spNguyenNhan.setSelection(constrWorkLogsEntity.getCat_cause_not_work_id());

        Supervision_CNVController vuongConller = new Supervision_CNVController(getContext());
        Constr_Team_ProgressController teamController = new Constr_Team_ProgressController(getContext());

        obstructionEntity = vuongConller.getItemConstrObStruction(constrWorkLogsEntity.getConstr_work_logs_id());
        if (obstructionEntity != null) {
            Constr_ObStruction_TypeEntity obStructionType= hashObStructionsById.get(obstructionEntity.getConstrObStructionTypeId());
            if (obStructionType != null) {
                spLoaiVuong.setSelection(obStructionType.getPosition());
            }
        }

        ArrayList<Constr_Team_ProgressEntity> arrTeamEntity = teamController.getItems(constrWorkLogsEntity.getConstr_work_logs_id());
        if (arrTeamEntity != null && arrTeamEntity.size() > 0) {
            for (Constr_Team_ProgressEntity team : arrTeamEntity) {

                TeamView view = hashTeamView.get(team.getCat_constr_team_id());
                hashTeams.put(team.getCat_constr_team_id(), team);
                if (view != null && team.getNum_of_team() > 0)
                    view.setNumber(team.getNum_of_team() + "");
            }
        }
    }

    public void save() {
        boolean isInsert = false;

        Constr_Work_LogsController controller = new Constr_Work_LogsController(getContext());
        Supervision_CNVController vuongConller = new Supervision_CNVController(getContext());
        Constr_Team_ProgressController teamController = new Constr_Team_ProgressController(getContext());
        constrWorkLogsEntity = new Constr_Work_LogsController(getContext()).getItem(constr_ConstructionItem.getConstructId(), GSCTUtils.getDateNow());

        if (constrWorkLogsEntity == null) {
            constrWorkLogsEntity = new Constr_Work_LogsEntity();
            isInsert = true;
        }

        if (swThiCong.isChecked()) {
            boolean flagValidateTeamNumber = true;
            Enumeration<Long> keys = hashTeamView.keys();
            while (keys.hasMoreElements()) {
                TeamView view = hashTeamView.get(keys.nextElement());
                if (view.getTeamNumber() > 0) {
                    flagValidateTeamNumber = false;
                    break;
                }
            }

            if (flagValidateTeamNumber) {
                showError("Bạn phải nhập số người cho ít nhất một đội!");
                return;
            }
        }

        long idUser = GlobalInfo.getInstance().getUserId();
        if (etThayDoi.getEditableText().toString().length() == 0) {
            showError("Bạn phải nhập thay đổi, bổ sung!");
            return;
        }
        // có thi công
        if (etYKienGiamSat.getEditableText().toString().length() == 0) {
            showError("Bạn phải nhập ý kiến giám sát!");
            return;
        }


        if (etYKienThiCong.getEditableText().toString().length() == 0) {
            showError("Bạn phải nhập ý kiến thi công!");
            return;
        }
        constrWorkLogsEntity.setConstructor_comments(etYKienThiCong.getText().toString());
        constrWorkLogsEntity.setMonitor_comments(etYKienGiamSat.getText().toString());
        constrWorkLogsEntity.setWork_content(etThayDoi.getEditableText().toString());
        constrWorkLogsEntity.setLog_date(GSCTUtils.getDateNow());
        constrWorkLogsEntity.setCat_Weather_id(spThoitiet.getSelectedItemPosition() + 1);
        constrWorkLogsEntity.setConstruct_id(constr_ConstructionItem.getConstructId());
        constrWorkLogsEntity.setEmployeeId(idUser);
        constrWorkLogsEntity.setIs_active(Constants.ISACTIVE.ACTIVE);
        constrWorkLogsEntity.setIs_work(swThiCong.isChecked() ? 1 : 0);
        if (constrWorkLogsEntity.getIs_work() != 1) {
            // không thi công
            constrWorkLogsEntity.setCat_cause_not_work_id(spNguyenNhan.getSelectedItemPosition());
            obstructionEntity = vuongConller.getItemConstrObStruction(constrWorkLogsEntity.getConstr_work_logs_id());
            if (obstructionEntity == null) {
                obstructionEntity = new Constr_ObStructionEntity();
            }
            obstructionEntity.setCreatedDate(Calendar.getInstance().getTime());
            obstructionEntity.setUpdatedBy(String.valueOf(idUser));
            obstructionEntity.setCat_Employee_id(idUser);
//            obstructionEntity.setConstrObStructionTypeId(spLoaiVuong.getSelectedItemPosition());
            Constr_ObStruction_TypeEntity typeObstruction = hashObStructionsByPosition.get(spLoaiVuong.getSelectedItemPosition());
            Log.e(TAG, "save: " + spLoaiVuong.getSelectedItemPosition() + " " + typeObstruction.getConstrObStructionTypeId() + " " + typeObstruction.getName());
            obstructionEntity.setConstrObStructionTypeId(typeObstruction.getConstrObStructionTypeId());
            obstructionEntity.setIsActive(1);
            obstructionEntity.setConstructId(constr_ConstructionItem.getConstructId());
        }

        // Lưu constr_work_log xuong DB
        boolean ret = false;
        if (isInsert) {
            constrWorkLogsEntity.setCreated_date(GSCTUtils.getDateNow());
            long id = Ktts_KeyController.getInstance()
                    .getKttsNextKey(Constr_Work_LogsField.TABLE_NAME);
            constrWorkLogsEntity.setConstr_work_logs_id(id);
            constrWorkLogsEntity.setSyncStatus(Constants.SYNC_STATUS.ADD);
            constrWorkLogsEntity.setCreated_user_id(idUser);
            ret = controller.addItem(constrWorkLogsEntity);
        } else {
            constrWorkLogsEntity.setSyncStatus(Constants.SYNC_STATUS.EDIT);
            ret = controller.updateItem(constrWorkLogsEntity);

        }

        if (constrWorkLogsEntity.getIs_work() == 1) {
            // tinh so leng de dam bao khong bi lay quá số phần tử
            Enumeration<Long> keys = hashTeamView.keys();
            while (keys.hasMoreElements()) {
                long key = keys.nextElement();
                TeamView view = hashTeamView.get(key);
                int num = view.getTeamNumber();
                Constr_Team_ProgressEntity item = hashTeams.get(key);
                if (item == null) {
                    long id = Ktts_KeyController.getInstance().getKttsNextKey(Constr_Team_ProgressField.TABLE_NAME);
                    item = new Constr_Team_ProgressEntity();
                    item.setConstr_team_progress_id(id);
                    item.setConstruct_id(constr_ConstructionItem.getConstructId());
                    item.setCat_constr_team_id(key);
                    item.setCreator_id(idUser);
                    item.setConstr_work_logs_id(constrWorkLogsEntity.getConstr_work_logs_id());
                    item.setNum_of_team(num);
                    item.setSyncStatus(Constants.SYNC_STATUS.ADD);
                    item.setEmployeeId(idUser);
                    teamController.addItem(item);
                } else {
                    item.setConstr_work_logs_id(constrWorkLogsEntity.getConstr_work_logs_id());
                    item.setNum_of_team(num);
                    item.setEmployeeId(idUser);
                    item.setSyncStatus(Constants.SYNC_STATUS.EDIT);
                    teamController.updateItem(item);
                }
            }
        }

        // Luu loai vuong xuong DB
        if (obstructionEntity != null) {
            Log.e(TAG, "save: obstruction constrWorkLogsEntity " + constrWorkLogsEntity.getConstr_work_logs_id());
            obstructionEntity.setConstr_work_logs_id(constrWorkLogsEntity.getConstr_work_logs_id());
            obstructionEntity.setEmployeeId(idUser);
            obstructionEntity.setSyncStatus(obstructionEntity.getProcessId() > 0 ? Constants.SYNC_STATUS.EDIT : Constants.SYNC_STATUS.ADD);
            if (obstructionEntity.getConstrObStructionId() > 0) {
                vuongConller.updateConstrObStruction(obstructionEntity);
            } else {
                long id = Ktts_KeyController.getInstance().getKttsNextKey(Constr_ObStructionField.TABLE_NAME);
                obstructionEntity.setConstrObStructionId(id);
                vuongConller.addItemConstrObStruction(obstructionEntity);
            }
        }

        Toast.makeText(getContext(), ret ? "Cập nhật thành công" : "fail", Toast.LENGTH_SHORT).show();
    }

    private void updateViewByIsWork(boolean isWork) {
        Iterator<View> thicong = sThicong.iterator();
        while (thicong.hasNext()) {
            thicong.next().setVisibility(isWork ? View.VISIBLE : View.GONE);
        }

        Iterator<View> khongthicong = sKhongThiCong.iterator();
        while (khongthicong.hasNext()) {
            khongthicong.next().setVisibility(!isWork ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
