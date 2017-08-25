package com.viettel.database.entity;

import com.viettel.constants.Constants;
import com.viettel.database.field.Cat_Supervision_Constr_WorkField;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Cat_Supervision_Constr_WorkEntity {

	private long cat_Supervision_Constr_Work_Id;
	private String work_Name;
	private String work_Type;
	private String work_Code;
	private int is_Active;
	private long processId;

	public Cat_Supervision_Constr_WorkEntity() {

	}

	public long getCat_Supervision_Constr_Work_Id() {
		return cat_Supervision_Constr_Work_Id;
	}

	public void setCat_Supervision_Constr_Work_Id(
			long cat_Supervision_Constr_Work_Id) {
		this.cat_Supervision_Constr_Work_Id = cat_Supervision_Constr_Work_Id;
	}

	public String getWork_Name() {
		return work_Name;
	}

	public void setWork_Name(String work_Name) {
		this.work_Name = work_Name;
	}

	public String getWork_Type() {
		return work_Type;
	}

	public void setWork_Type(String work_Type) {
		this.work_Type = work_Type;
	}

	public String getWork_Code() {
		return work_Code;
	}

	public void setWork_Code(String work_Code) {
		this.work_Code = work_Code;
	}

	public int getIs_Active() {
		return is_Active;
	}

	public void setIs_Active(int is_Active) {
		this.is_Active = is_Active;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	/**
	 * Ham chuyen chuoi json ve object
	 * 
	 * @param entry
	 * @return
	 */
	public static List<Cat_Supervision_Constr_WorkEntity> parseJson(
			JSONObject entry) {
		List<Cat_Supervision_Constr_WorkEntity> listResult = new ArrayList<Cat_Supervision_Constr_WorkEntity>();
		if (entry == null) {
			return listResult;
		}
		try {
			if (entry.isNull("listResult")) {
				return listResult;
			}
			JSONArray jsonMainArray = entry.getJSONArray("listResult");
			int total = jsonMainArray.length();
			for (int i = 0; i < total; i++) {

				JSONObject jsonItem = jsonMainArray.getJSONObject(i);
				Cat_Supervision_Constr_WorkEntity addItem = new Cat_Supervision_Constr_WorkEntity();
				addItem.setCat_Supervision_Constr_Work_Id(jsonItem
						.getInt(Cat_Supervision_Constr_WorkField.COLUMN_CAT_SUPERVISION_CONSTR_WORK_ID));

				if (jsonItem.getString(
						Cat_Supervision_Constr_WorkField.COLUMN_WORK_NAME)
						.equals("null")) {
					addItem.setWork_Name(StringUtil.EMPTY_STRING);
				} else {
					addItem.setWork_Name(jsonItem
							.getString(Cat_Supervision_Constr_WorkField.COLUMN_WORK_NAME));
				}

				if (jsonItem.getString(
						Cat_Supervision_Constr_WorkField.COLUMN_WORK_TYPE)
						.equals("null")) {
					addItem.setWork_Type(StringUtil.EMPTY_STRING);
				} else {
					addItem.setWork_Type(jsonItem
							.getString(Cat_Supervision_Constr_WorkField.COLUMN_WORK_TYPE));
				}

				if (jsonItem.getString(
						Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE)
						.equals("null")) {
					addItem.setWork_Code(StringUtil.EMPTY_STRING);
				} else {
					addItem.setWork_Code(jsonItem
							.getString(Cat_Supervision_Constr_WorkField.COLUMN_WORK_CODE));
				}
				addItem.setIs_Active(jsonItem
						.getInt(Cat_Supervision_Constr_WorkField.COLUMN_IS_ACTIVE));
				
				addItem.setProcessId(jsonItem
						.getInt(Cat_Supervision_Constr_WorkField.COLUMN_PROCESS_ID));

				listResult.add(addItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	public static List<Cat_Supervision_Constr_WorkEntity> parseJsonTest(
			JSONObject entry) {
		List<Cat_Supervision_Constr_WorkEntity> listResult = new ArrayList<Cat_Supervision_Constr_WorkEntity>();
		Cat_Supervision_Constr_WorkEntity constrWork1 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork2 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork3 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork4 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork5 = new Cat_Supervision_Constr_WorkEntity();

		Cat_Supervision_Constr_WorkEntity constrWork6 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork7 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork8 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork9 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork10 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork11 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork12 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork13 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork14 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork15 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork16 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork17 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork18 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork19 = new Cat_Supervision_Constr_WorkEntity();
		
		Cat_Supervision_Constr_WorkEntity constrWork20 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork21 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork22 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork23 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork24 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork25 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork26 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork27 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork28 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork29 = new Cat_Supervision_Constr_WorkEntity();
		Cat_Supervision_Constr_WorkEntity constrWork30 = new Cat_Supervision_Constr_WorkEntity();
		constrWork1.setCat_Supervision_Constr_Work_Id(1);
		constrWork1.setProcessId(1);
		constrWork1.setIs_Active(1);
		constrWork1.setWork_Name("Antennna");
		constrWork1
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA);
		constrWork1
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW);

		constrWork2.setCat_Supervision_Constr_Work_Id(2);
		constrWork2.setProcessId(2);
		constrWork2.setIs_Active(1);
		constrWork2.setWork_Name("Cáp trung tần");
		constrWork2
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN);
		constrWork2
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW);

		constrWork3.setCat_Supervision_Constr_Work_Id(3);
		constrWork3.setProcessId(3);
		constrWork3.setIs_Active(1);
		constrWork3.setWork_Name("Tình trạng phần cứng");
		constrWork3
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG);
		constrWork3
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW);

		constrWork4.setCat_Supervision_Constr_Work_Id(4);
		constrWork4.setProcessId(4);
		constrWork4.setIs_Active(1);
		constrWork4.setWork_Name("Cáp nguồn DC");
		constrWork4
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC);
		constrWork4
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW);

		constrWork5.setCat_Supervision_Constr_Work_Id(5);
		constrWork5.setProcessId(5);
		constrWork5.setIs_Active(1);
		constrWork5.setWork_Name("Cáp băng tần gốc, cáp ra luồng");
		constrWork5
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC);
		constrWork5
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_LOW);
		
		constrWork26.setCat_Supervision_Constr_Work_Id(26);
		constrWork26.setProcessId(26);
		constrWork26.setIs_Active(1);
		constrWork26.setWork_Name("Antennna");
		constrWork26
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTENNA);
		constrWork26
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH);

		constrWork27.setCat_Supervision_Constr_Work_Id(27);
		constrWork27.setProcessId(27);
		constrWork27.setIs_Active(1);
		constrWork27.setWork_Name("Cáp trung tần");
		constrWork27
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_TRUNG_TAN);
		constrWork27
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH);

		constrWork28.setCat_Supervision_Constr_Work_Id(28);
		constrWork28.setProcessId(28);
		constrWork28.setIs_Active(1);
		constrWork28.setWork_Name("Tình trạng phần cứng");
		constrWork28
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_TINH_TRANG_PHAN_CUNG);
		constrWork28
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH);

		constrWork29.setCat_Supervision_Constr_Work_Id(29);
		constrWork29.setProcessId(29);
		constrWork29.setIs_Active(1);
		constrWork29.setWork_Name("Cáp nguồn DC");
		constrWork29
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_DC);
		constrWork29
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH);

		constrWork30.setCat_Supervision_Constr_Work_Id(30);
		constrWork30.setProcessId(30);
		constrWork30.setIs_Active(1);
		constrWork30.setWork_Name("Cáp băng tần gốc, cáp ra luồng");
		constrWork30
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_BANG_TAN_GOC);
		constrWork30
		.setWork_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_BTS_VIBA_HIGH);

		constrWork6.setCat_Supervision_Constr_Work_Id(6);
		constrWork6.setProcessId(6);
		constrWork6.setIs_Active(1);
		constrWork6
				.setWork_Name("Tiếp đất thoát sét cho thang cáp, thiết bị rack.");
		constrWork6
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK);

		constrWork7.setCat_Supervision_Constr_Work_Id(7);
		constrWork7.setProcessId(7);
		constrWork7.setIs_Active(1);
		constrWork7.setWork_Name("Tiếp đất cho Feeder, RRU");
		constrWork7
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_TIEP_DAT_FEEDER);

		constrWork8.setCat_Supervision_Constr_Work_Id(8);
		constrWork8.setProcessId(8);
		constrWork8.setIs_Active(1);
		constrWork8.setWork_Name("Thang cáp");
		constrWork8.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_THANG_CAP);

		constrWork9.setCat_Supervision_Constr_Work_Id(9);
		constrWork9.setProcessId(9);
		constrWork9.setIs_Active(1);
		constrWork9.setWork_Name("Hệ thống đèn điều hòa, thông gió");
		constrWork9
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_HT_DEN_DIEUHOA_THONGGIO);

		constrWork10.setCat_Supervision_Constr_Work_Id(10);
		constrWork10.setProcessId(10);
		constrWork10.setIs_Active(1);
		constrWork10.setWork_Name("Tủ điện AC");
		constrWork10
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_DIEN_AC);

		constrWork11.setCat_Supervision_Constr_Work_Id(11);
		constrWork11.setProcessId(11);
		constrWork11.setIs_Active(1);
		constrWork11.setWork_Name("Tủ điện DC, ắc quy");
		constrWork11
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_NGUON_DC_ACQUY);

		constrWork12.setCat_Supervision_Constr_Work_Id(12);
		constrWork12.setProcessId(12);
		constrWork12.setIs_Active(1);
		constrWork12.setWork_Name("Tủ thiết bị BTS");
		constrWork12.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_TU_BTS);

		constrWork13.setCat_Supervision_Constr_Work_Id(13);
		constrWork13.setProcessId(13);
		constrWork13.setIs_Active(1);
		constrWork13.setWork_Name("Rack 19”, DF, DDF, và phiến bắn luồng");
		constrWork13.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_RACK_DF);

		constrWork14.setCat_Supervision_Constr_Work_Id(14);
		constrWork14.setProcessId(14);
		constrWork14.setIs_Active(1);
		constrWork14.setWork_Name("Cáp nguồn, cáp tín hiệu trên thang cáp");
		constrWork14
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_NGUON_THANG_CAP);

		constrWork15.setCat_Supervision_Constr_Work_Id(15);
		constrWork15.setProcessId(15);
		constrWork15.setIs_Active(1);
		constrWork15.setWork_Name("Đèn báo không");
		constrWork15
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_DEN_BAO_KHONG);

		constrWork16.setCat_Supervision_Constr_Work_Id(16);
		constrWork16.setProcessId(16);
		constrWork16.setIs_Active(1);
		constrWork16
				.setWork_Name("Anten và gá, Feeder, Jumper, Clamp, connector");
		constrWork16
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_ANTEN_FEEDER_JUMPER);

		constrWork17.setCat_Supervision_Constr_Work_Id(17);
		constrWork17.setProcessId(17);
		constrWork17.setIs_Active(1);
		constrWork17.setWork_Name("Tấm bịt cáp nhập trạm");
		constrWork17
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_TAM_BIT_CAP);

		constrWork18.setCat_Supervision_Constr_Work_Id(18);
		constrWork18.setProcessId(18);
		constrWork18.setIs_Active(1);
		constrWork18.setWork_Name("thiết bị RRU và OLP");
		constrWork18.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_RRU_OLP);

		constrWork19.setCat_Supervision_Constr_Work_Id(19);
		constrWork19.setProcessId(19);
		constrWork19.setIs_Active(1);
		constrWork19.setWork_Name("Cáp quang và cáp nguồn cho RRU");
		constrWork19
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_CAP_QUANG_NGUON_RRU);
		
		constrWork20.setCat_Supervision_Constr_Work_Id(20);
		constrWork20.setProcessId(20);
		constrWork20.setIs_Active(1);
		constrWork20.setWork_Name("dsfd");
		constrWork20
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_LDCAT);
		
		constrWork21.setCat_Supervision_Constr_Work_Id(21);
		constrWork21.setProcessId(21);
		constrWork21.setIs_Active(1);
		constrWork21.setWork_Name("dffd");
		constrWork21
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_TDBTS);
		
		constrWork22.setCat_Supervision_Constr_Work_Id(22);
		constrWork22.setProcessId(22);
		constrWork22.setIs_Active(1);
		constrWork22.setWork_Name("ytuyt");
		constrWork22
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_PHONG_MAY);
		
		constrWork23.setCat_Supervision_Constr_Work_Id(23);
		constrWork23.setProcessId(23);
		constrWork23.setIs_Active(1);
		constrWork23.setWork_Name("rtytry");
		constrWork23
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_NHA_MAY_NO);
		
		constrWork24.setCat_Supervision_Constr_Work_Id(24);
		constrWork24.setProcessId(24);
		constrWork24.setIs_Active(1);
		constrWork24.setWork_Name("vbnbn");
		constrWork24
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_KEO_DAY_DIEN_PHONG_MAY_NO);
		
		constrWork25.setCat_Supervision_Constr_Work_Id(25);
		constrWork25.setProcessId(25);
		constrWork25.setIs_Active(1);
		constrWork25.setWork_Name("rtj");
		constrWork25
				.setWork_Code(Constants.BTS_CONSTR_WORK.WORK_CODE_KEO_DAY_DIEN);
		listResult.add(constrWork1);
		listResult.add(constrWork2);
		listResult.add(constrWork3);
		listResult.add(constrWork4);
		listResult.add(constrWork5);
		listResult.add(constrWork6);
		listResult.add(constrWork7);
		listResult.add(constrWork8);
		listResult.add(constrWork9);
		listResult.add(constrWork10);
		listResult.add(constrWork11);
		listResult.add(constrWork12);
		listResult.add(constrWork13);
		listResult.add(constrWork14);
		listResult.add(constrWork15);
		listResult.add(constrWork16);
		listResult.add(constrWork17);
		listResult.add(constrWork18);
		listResult.add(constrWork19);
		listResult.add(constrWork20);
		listResult.add(constrWork21);
		listResult.add(constrWork22);
		listResult.add(constrWork23);
		listResult.add(constrWork24);
		listResult.add(constrWork25);
		listResult.add(constrWork26);
		listResult.add(constrWork27);
		listResult.add(constrWork28);
		listResult.add(constrWork29);
		listResult.add(constrWork30);
		return listResult;
	}

}
