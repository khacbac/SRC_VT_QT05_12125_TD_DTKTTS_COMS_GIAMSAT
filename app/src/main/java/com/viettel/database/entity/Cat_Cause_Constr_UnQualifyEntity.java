package com.viettel.database.entity;

import com.viettel.constants.Constants;
import com.viettel.database.field.Cat_Cause_Constr_UnQualifyField;
import com.viettel.utils.StringUtil;
import com.viettel.viettellib.json.me.JSONArray;
import com.viettel.viettellib.json.me.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cat_Cause_Constr_UnQualifyEntity implements Serializable {
	private static final long serialVersionUID = -5209992464221129409L;
	private long cat_Cause_Constr_Unquality_Id;
	private String name;
	private int type;
	private String category;
	private String sub_Type;
	private int is_Active;
	private long processId;
	private int isSerious;

	public int getIsSerious() {
		return isSerious;
	}

	public void setIsSerious(int isSerious) {
		this.isSerious = isSerious;
	}

	public long getCat_Cause_Constr_Unquality_Id() {
		return cat_Cause_Constr_Unquality_Id;
	}

	public void setCat_Cause_Constr_Unquality_Id(
			long cat_Cause_Constr_Unquality_Id) {
		this.cat_Cause_Constr_Unquality_Id = cat_Cause_Constr_Unquality_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSub_Type() {
		return sub_Type;
	}

	public void setSub_Type(String sub_Type) {
		this.sub_Type = sub_Type;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * Ham chuyen chuoi json ve object
	 * 
	 * @param entry
	 * @return
	 */
	public static List<Cat_Cause_Constr_UnQualifyEntity> parseJson(
			JSONObject entry) {
		List<Cat_Cause_Constr_UnQualifyEntity> listResult = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();
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
				Cat_Cause_Constr_UnQualifyEntity addItem = new Cat_Cause_Constr_UnQualifyEntity();
				addItem.setCat_Cause_Constr_Unquality_Id(jsonItem
						.getInt(Cat_Cause_Constr_UnQualifyField.COLUMN_CAT_CAUSE_CONSTR_UNQUALIFY_ID));

				if (jsonItem.getString(
						Cat_Cause_Constr_UnQualifyField.COLUMN_NAME).equals(
						"null")) {
					addItem.setName(StringUtil.EMPTY_STRING);
				} else {
					addItem.setName(jsonItem
							.getString(Cat_Cause_Constr_UnQualifyField.COLUMN_NAME));
				}

				if (jsonItem.getString(
						Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE).equals(
						"null")) {
					addItem.setSub_Type(StringUtil.EMPTY_STRING);
				} else {
					addItem.setSub_Type(jsonItem
							.getString(Cat_Cause_Constr_UnQualifyField.COLUMN_TYPE));
				}

				if (jsonItem.getString(
						Cat_Cause_Constr_UnQualifyField.COLUMN_SUB_TYPE)
						.equals("null")) {
					addItem.setSub_Type(StringUtil.EMPTY_STRING);
				} else {
					addItem.setSub_Type(jsonItem
							.getString(Cat_Cause_Constr_UnQualifyField.COLUMN_SUB_TYPE));
				}
				addItem.setProcessId(jsonItem
						.getInt(Cat_Cause_Constr_UnQualifyField.COLUMN_PROCESS_ID));

				listResult.add(addItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}

	public static List<Cat_Cause_Constr_UnQualifyEntity> parseJsonTest(
			JSONObject entry) {
		List<Cat_Cause_Constr_UnQualifyEntity> listResult = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();
		Cat_Cause_Constr_UnQualifyEntity add1 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add2 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add3 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add4 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add5 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add6 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add7 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add8 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add9 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add10 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add11 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add12 = new Cat_Cause_Constr_UnQualifyEntity();
		
		Cat_Cause_Constr_UnQualifyEntity add13 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add14 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add15 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add16 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add17 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add18 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add19 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add20 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add21 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add22 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add23 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add24 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add25 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add26 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add27 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add28 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add29 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add30 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add31 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add32 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add33 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add34 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add35 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add36 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add37 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add38 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add39 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add40 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add41 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add42 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add43 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add44 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add45 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add46 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add47 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add48 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add49 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add50 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add51 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add52 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add53 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add54 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add55 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add56 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add57 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add58 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add59 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add60 = new Cat_Cause_Constr_UnQualifyEntity();

		Cat_Cause_Constr_UnQualifyEntity add61 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add62 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add63 = new Cat_Cause_Constr_UnQualifyEntity();
		
		Cat_Cause_Constr_UnQualifyEntity add64 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add65 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add66 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add67 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add68 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add69 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add70 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add71 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add72 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add73 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add74 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add75 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add76 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add77 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add78 = new Cat_Cause_Constr_UnQualifyEntity();
		Cat_Cause_Constr_UnQualifyEntity add79 = new Cat_Cause_Constr_UnQualifyEntity();
		
		
		
		add1.setCat_Cause_Constr_Unquality_Id(1);
		add1.setProcessId(1);
		add1.setIs_Active(1);
		add1.setName("Không đảm bảo độ sâu");
		add1.setType(2);
		add1.setSub_Type("TANK");
		
		add2.setCat_Cause_Constr_Unquality_Id(2);
		add2.setProcessId(2);
		add2.setIs_Active(1);
		add2.setName("Không đảm bảo chiều dài");
		add2.setType(2);
		add2.setSub_Type("TANK");

		add3.setCat_Cause_Constr_Unquality_Id(3);
		add3.setProcessId(3);
		add3.setIs_Active(1);
		add3.setName("Không đảm bảo thiết kế");
		add3.setType(2);
		add3.setSub_Type("TANK");

		add4.setCat_Cause_Constr_Unquality_Id(4);
		add4.setProcessId(4);
		add4.setIs_Active(1);
		add4.setName("Không đảm bảo chất lượng");
		add4.setType(2);
		add4.setSub_Type("TANK");
		/* pipe */
		add5.setCat_Cause_Constr_Unquality_Id(5);
		add5.setProcessId(5);
		add5.setIs_Active(1);
		add5.setName("Không đảm bảo thiết kế");
		add5.setType(2);
		add5.setSub_Type("PIPE");

		add6.setCat_Cause_Constr_Unquality_Id(6);
		add6.setProcessId(6);
		add6.setIs_Active(1);
		add6.setName("Không đảm bảo chất lượng");
		add6.setType(2);
		add6.setSub_Type("PIPE");
		/* cable */
		add7.setCat_Cause_Constr_Unquality_Id(7);
		add7.setProcessId(7);
		add7.setIs_Active(1);
		add7.setName("Không đảm bảo thiết kế");
		add7.setType(2);
		add7.setSub_Type("CABLE");

		add8.setCat_Cause_Constr_Unquality_Id(8);
		add8.setProcessId(8);
		add8.setIs_Active(1);
		add8.setName("Không đảm bảo chất lượng");
		add8.setType(2);
		add8.setSub_Type("CABLE");

		/* MX */
		add9.setCat_Cause_Constr_Unquality_Id(9);
		add9.setProcessId(9);
		add9.setIs_Active(1);
		add9.setName("Không đảm bảo thiết kế");
		add9.setType(2);
		add9.setSub_Type("MX");

		add10.setCat_Cause_Constr_Unquality_Id(10);
		add10.setProcessId(10);
		add10.setIs_Active(1);
		add10.setName("Không đảm bảo chất lượng");
		add10.setType(2);
		add10.setSub_Type("MX");
		/* Pillar */
		add11.setCat_Cause_Constr_Unquality_Id(11);
		add11.setProcessId(11);
		add11.setIs_Active(1);
		add11.setName("Không đảm bảo thiết kế");
		add11.setType(Constants.UNQUALIFY_TYPE.LINE_HANG);
		add11.setSub_Type(Constants.UNQUALIFY_TYPE.HG_PILLAR);

		add12.setCat_Cause_Constr_Unquality_Id(12);
		add12.setProcessId(12);
		add12.setIs_Active(1);
		add12.setName("Không đảm bảo chất lượng");
		add12.setType(Constants.UNQUALIFY_TYPE.LINE_HANG);
		add12.setSub_Type(Constants.UNQUALIFY_TYPE.HG_PILLAR);
		
		add13.setCat_Cause_Constr_Unquality_Id(13);
		add13.setProcessId(13);
		add13.setIs_Active(1);
		add13.setName("Hoàn trả mặt bằng không đạt");
		add13.setType(1);
		add13.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_PILLAR);
		
		add14.setCat_Cause_Constr_Unquality_Id(14);
		add14.setProcessId(14);
		add14.setIs_Active(1);
		add14.setName("Nguyên nhân khác");
		add14.setType(1);
		add14.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_PILLAR);
		
		add15.setCat_Cause_Constr_Unquality_Id(15);
		add15.setProcessId(15);
		add15.setIs_Active(1);
		add15.setName("cot trong moi khong dat 1");
		add15.setType(1);
		add15.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_COT_TRONG_MOI);
		
		add16.setCat_Cause_Constr_Unquality_Id(16);
		add16.setProcessId(16);
		add16.setIs_Active(1);
		add16.setName("cot trong moi khong dat 2");
		add16.setType(1);
		add16.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_COT_TRONG_MOI);
	
		add17.setCat_Cause_Constr_Unquality_Id(17);
		add17.setProcessId(17);
		add17.setIs_Active(1);
		add17.setName("cot trong moi khong dat 3");
		add17.setType(1);
		add17.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_COT_TRONG_MOI);
	
		add18.setCat_Cause_Constr_Unquality_Id(18);
		add18.setProcessId(18);
		add18.setIs_Active(1);
		add18.setName("keo day dien khong dat 1");
		add18.setType(1);
		add18.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN);
		
		add19.setCat_Cause_Constr_Unquality_Id(19);
		add19.setProcessId(19);
		add19.setIs_Active(1);
		add19.setName("keo day dien khong dat 2");
		add19.setType(1);
		add19.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN);
	
		add20.setCat_Cause_Constr_Unquality_Id(20);
		add20.setProcessId(20);
		add20.setIs_Active(1);
		add20.setName("keo day dien khong dat 3");
		add20.setType(1);
		add20.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN);

		add21.setCat_Cause_Constr_Unquality_Id(21);
		add21.setProcessId(21);
		add21.setIs_Active(1);
		add21.setName("thi cong ngoai troi khong dat 1");
		add21.setType(1);
		add21.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_ANTENNA);

		add22.setCat_Cause_Constr_Unquality_Id(22);
		add22.setProcessId(22);
		add22.setIs_Active(1);
		add22.setName("thi cong ngoai troi khong dat 2");
		add22.setType(1);
		add22.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_ANTENNA);

		add23.setCat_Cause_Constr_Unquality_Id(23);
		add23.setProcessId(23);
		add23.setIs_Active(1);
		add23.setName("thi cong ngoai troi khong dat 3");
		add23.setType(1);
		add23.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_ANTENNA);

		add24.setCat_Cause_Constr_Unquality_Id(24);
		add24.setProcessId(24);
		add24.setIs_Active(1);
		add24.setName("cap trung tan khong dat 1");
		add24.setType(1);
		add24.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_TRUNG_TAN);

		add25.setCat_Cause_Constr_Unquality_Id(25);
		add25.setProcessId(25);
		add25.setIs_Active(1);
		add25.setName("cap trung tan khong dat 2");
		add25.setType(1);
		add25.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_TRUNG_TAN);

		add26.setCat_Cause_Constr_Unquality_Id(26);
		add26.setProcessId(26);
		add26.setIs_Active(1);
		add26.setName("cap trung tan khong dat 3");
		add26.setType(1);
		add26.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_TRUNG_TAN);

		add27.setCat_Cause_Constr_Unquality_Id(27);
		add27.setProcessId(27);
		add27.setIs_Active(1);
		add27.setName("tinh trang phan cung khong dat 1");
		add27.setType(1);
		add27.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TINH_TRANG_PHAN_CUNG);

		add28.setCat_Cause_Constr_Unquality_Id(28);
		add28.setProcessId(28);
		add28.setIs_Active(1);
		add28.setName("tinh trang phan cung khong dat 2");
		add28.setType(1);
		add28.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TINH_TRANG_PHAN_CUNG);


		add29.setCat_Cause_Constr_Unquality_Id(29);
		add29.setProcessId(29);
		add29.setIs_Active(1);
		add29.setName("tinh trang phan cung khong dat 3");
		add29.setType(1);
		add29.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TINH_TRANG_PHAN_CUNG);


		add30.setCat_Cause_Constr_Unquality_Id(30);
		add30.setProcessId(30);
		add30.setIs_Active(1);
		add30.setName("cap nguon dc khong dat 1");
		add30.setType(1);
		add30.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_NGUON_DC);

		add31.setCat_Cause_Constr_Unquality_Id(31);
		add31.setProcessId(31);
		add31.setIs_Active(1);
		add31.setName("cap nguon dc khong dat 2");
		add31.setType(1);
		add31.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_NGUON_DC);

		add32.setCat_Cause_Constr_Unquality_Id(32);
		add32.setProcessId(32);
		add32.setIs_Active(1);
		add32.setName("cap nguon dc khong dat 3");
		add32.setType(1);
		add32.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_NGUON_DC);

		add33.setCat_Cause_Constr_Unquality_Id(33);
		add33.setProcessId(33);
		add33.setIs_Active(1);
		add33.setName("cap bang tan goc khong dat 1");
		add33.setType(1);
		add33.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_BANG_TAN_GOC);

		add34.setCat_Cause_Constr_Unquality_Id(34);
		add34.setProcessId(34);
		add34.setIs_Active(1);
		add34.setName("cap bang tan goc khong dat 2");
		add34.setType(1);
		add34.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_BANG_TAN_GOC);

		add35.setCat_Cause_Constr_Unquality_Id(35);
		add35.setProcessId(35);
		add35.setIs_Active(1);
		add35.setName("cap bang tan goc khong dat 3");
		add35.setType(1);
		add35.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_BANG_TAN_GOC);

		add36.setCat_Cause_Constr_Unquality_Id(36);
		add36.setProcessId(36);
		add36.setIs_Active(1);
		add36.setName("tiep dat thoat set thang cap khong dat 1");
		add36.setType(1);
		add36.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK);

		add37.setCat_Cause_Constr_Unquality_Id(37);
		add37.setProcessId(37);
		add37.setIs_Active(1);
		add37.setName("tiep dat thoat set thang cap khong dat 2");
		add37.setType(1);
		add37.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TIEP_DAT_THOAT_SET_THANG_CAP_RACK);

		add38.setCat_Cause_Constr_Unquality_Id(38);
		add38.setProcessId(38);
		add38.setIs_Active(1);
		add38.setName("tiep dat cho feeder khong dat 1");
		add38.setType(1);
		add38.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TIEP_DAT_FEEDER);

		add39.setCat_Cause_Constr_Unquality_Id(39);
		add39.setProcessId(39);
		add39.setIs_Active(1);
		add39.setName("tiep dat cho feeder khong dat 2");
		add39.setType(1);
		add39.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TIEP_DAT_FEEDER);

		add40.setCat_Cause_Constr_Unquality_Id(40);
		add40.setProcessId(40);
		add40.setIs_Active(1);
		add40.setName("thang cap khong dat 1");
		add40.setType(1);
		add40.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_THANG_CAP);

		add41.setCat_Cause_Constr_Unquality_Id(41);
		add41.setProcessId(41);
		add41.setIs_Active(1);
		add41.setName("thang cap khong dat 2");
		add41.setType(1);
		add41.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_THANG_CAP);

		add42.setCat_Cause_Constr_Unquality_Id(42);
		add42.setProcessId(42);
		add42.setIs_Active(1);
		add42.setName("he thong den dieu hoa thong gio khong dat 1");
		add42.setType(1);
		add42.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_HT_DEN_DIEUHOA_THONGGIO);

		add43.setCat_Cause_Constr_Unquality_Id(43);
		add43.setProcessId(43);
		add43.setIs_Active(1);
		add43.setName("he thong den dieu hoa thong gio khong dat 2");
		add43.setType(1);
		add43.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_HT_DEN_DIEUHOA_THONGGIO);

		add44.setCat_Cause_Constr_Unquality_Id(44);
		add44.setProcessId(44);
		add44.setIs_Active(1);
		add44.setName("tu dien ac khong dat 1");
		add44.setType(1);
		add44.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TU_DIEN_AC);

		add45.setCat_Cause_Constr_Unquality_Id(45);
		add45.setProcessId(45);
		add45.setIs_Active(1);
		add45.setName("tu dien ac khong dat 2");
		add45.setType(1);
		add45.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TU_DIEN_AC);

		add46.setCat_Cause_Constr_Unquality_Id(46);
		add46.setProcessId(46);
		add46.setIs_Active(1);
		add46.setName("tu nguon dc acquy khong dat 1");
		add46.setType(1);
		add46.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TU_NGUON_DC_ACQUY);

		add47.setCat_Cause_Constr_Unquality_Id(47);
		add47.setProcessId(47);
		add47.setIs_Active(1);
		add47.setName("tu nguon dc acquy khong dat 2");
		add47.setType(1);
		add47.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TU_NGUON_DC_ACQUY);

		add48.setCat_Cause_Constr_Unquality_Id(48);
		add48.setProcessId(48);
		add48.setIs_Active(1);
		add48.setName("tu bts khong dat 1");
		add48.setType(1);
		add48.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TU_BTS);

		add49.setCat_Cause_Constr_Unquality_Id(49);
		add49.setProcessId(49);
		add49.setIs_Active(1);
		add49.setName("tu bts khong dat 2");
		add49.setType(1);
		add49.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TU_BTS);

		add50.setCat_Cause_Constr_Unquality_Id(50);
		add50.setProcessId(50);
		add50.setIs_Active(1);
		add50.setName("rack df khong dat 1");
		add50.setType(1);
		add50.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_RACK_DF);

		add51.setCat_Cause_Constr_Unquality_Id(51);
		add51.setProcessId(51);
		add51.setIs_Active(1);
		add51.setName("rack df khong dat 2");
		add51.setType(1);
		add51.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_RACK_DF);

		add52.setCat_Cause_Constr_Unquality_Id(52);
		add52.setProcessId(52);
		add52.setIs_Active(1);
		add52.setName("cap nguon thang cap khong dat 1");
		add52.setType(1);
		add52.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_NGUON_THANG_CAP);

		add53.setCat_Cause_Constr_Unquality_Id(53);
		add53.setProcessId(53);
		add53.setIs_Active(1);
		add53.setName("cap nguon thang cap khong dat 2");
		add53.setType(1);
		add53.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_NGUON_THANG_CAP);

		add54.setCat_Cause_Constr_Unquality_Id(54);
		add54.setProcessId(54);
		add54.setIs_Active(1);
		add54.setName("den beo khong khong dat 1");
		add54.setType(1);
		add54.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_DEN_BAO_KHONG);

		add55.setCat_Cause_Constr_Unquality_Id(55);
		add55.setProcessId(55);
		add55.setIs_Active(1);
		add55.setName("den beo khong khong dat 2");
		add55.setType(1);
		add55.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_DEN_BAO_KHONG);

		add56.setCat_Cause_Constr_Unquality_Id(56);
		add56.setProcessId(56);
		add56.setIs_Active(1);
		add56.setName("anten feeder jumper khong dat 1");
		add56.setType(1);
		add56.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_ANTEN_FEEDER_JUMPER);

		add57.setCat_Cause_Constr_Unquality_Id(57);
		add57.setProcessId(57);
		add57.setIs_Active(1);
		add57.setName("anten feeder jumper khong dat 2");
		add57.setType(1);
		add57.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_ANTEN_FEEDER_JUMPER);

		add58.setCat_Cause_Constr_Unquality_Id(58);
		add58.setProcessId(58);
		add58.setIs_Active(1);
		add58.setName("tam bit cap khong dat 1");
		add58.setType(1);
		add58.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TAM_BIT_CAP);

		add59.setCat_Cause_Constr_Unquality_Id(59);
		add59.setProcessId(59);
		add59.setIs_Active(1);
		add59.setName("tam bit cap khong dat 2");
		add59.setType(1);
		add59.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TAM_BIT_CAP);

		add60.setCat_Cause_Constr_Unquality_Id(60);
		add60.setProcessId(60);
		add60.setIs_Active(1);
		add60.setName("rru khong dat 1");
		add60.setType(1);
		add60.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_RRU_OLP);
		
		add61.setCat_Cause_Constr_Unquality_Id(61);
		add61.setProcessId(61);
		add61.setIs_Active(1);
		add61.setName("rru khong dat 2");
		add61.setType(1);
		add61.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_RRU_OLP);

		add62.setCat_Cause_Constr_Unquality_Id(62);
		add62.setProcessId(62);
		add62.setIs_Active(1);
		add62.setName("cap quang nguon rru khong dat 1");
		add62.setType(1);
		add62.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_QUANG_NGUON_RRU);

		add63.setCat_Cause_Constr_Unquality_Id(63);
		add63.setProcessId(63);
		add63.setIs_Active(1);
		add63.setName("cap quang nguon rru khong dat 2");
		add63.setType(1);
		add63.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_CAP_QUANG_NGUON_RRU);
		
		add64.setCat_Cause_Constr_Unquality_Id(64);
		add64.setProcessId(64);
		add64.setIs_Active(1);
		add64.setName("Độ sâu rãnh tiếp địa không đúng thiết kế");
		add64.setType(1);
		add64.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TDBTS);		
		
		add65.setCat_Cause_Constr_Unquality_Id(65);
		add65.setProcessId(65);
		add65.setIs_Active(1);
		add65.setName("Kích thước, chủng loại vật tư không đúng thiết kế");
		add65.setType(1);
		add65.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TDBTS);		
	
		add66.setCat_Cause_Constr_Unquality_Id(66);
		add66.setProcessId(66);
		add66.setIs_Active(1);
		add66.setName("Liên kết giữa dây dẫn và các cọc không đúng thiết kế");
		add66.setType(1);
		add66.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_TDBTS);
		
		add67.setCat_Cause_Constr_Unquality_Id(67);
		add67.setProcessId(67);
		add67.setIs_Active(1);
		add67.setName("Mặt bích không đúng thiết kế");
		add67.setType(1);
		add67.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_LDCAT);	
		
		add68.setCat_Cause_Constr_Unquality_Id(68);
		add68.setProcessId(68);
		add68.setIs_Active(1);
		add68.setName("Bulong móng không đúng thiết kế");
		add68.setType(1);
		add68.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_LDCAT);	
		
		add69.setCat_Cause_Constr_Unquality_Id(69);
		add69.setProcessId(69);
		add69.setIs_Active(1);
		add69.setName("Kích thước, cao độ trụ không đúng thiết kế");
		add69.setType(1);
		add69.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_LDCAT);	
		
		add70.setCat_Cause_Constr_Unquality_Id(70);
		add70.setProcessId(70);
		add70.setIs_Active(1);
		add70.setName("Ván khuôn không đạt");
		add70.setType(1);
		add70.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_LDCAT);
		
		add71.setCat_Cause_Constr_Unquality_Id(71);
		add71.setProcessId(71);
		add71.setIs_Active(1);
		add71.setName("Vị trí phong may so với BTS không đúng thiết kế");
		add71.setType(1);
		add71.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_PHONG_MAY);	
		
		add72.setCat_Cause_Constr_Unquality_Id(72);
		add72.setProcessId(72);
		add72.setIs_Active(1);
		add72.setName("Vị trí phong may so với BTS không đúng thiết kế");
		add72.setType(1);
		add72.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_PHONG_MAY);
		
		add73.setCat_Cause_Constr_Unquality_Id(73);
		add73.setProcessId(73);
		add73.setIs_Active(1);
		add73.setName("Vị trí keo day dien so với BTS không đúng thiết kế");
		add73.setType(1);
		add73.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN_PHONG_MAY_NO);	
		
		add74.setCat_Cause_Constr_Unquality_Id(74);
		add74.setProcessId(74);
		add74.setIs_Active(1);
		add74.setName("Vị trí keo day dien so với BTS không đúng thiết kế");
		add74.setType(1);
		add74.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_KEO_DAY_DIEN_PHONG_MAY_NO);	
		
		add75.setCat_Cause_Constr_Unquality_Id(75);
		add75.setProcessId(75);
		add75.setIs_Active(1);
		add75.setName("Vị trí nhà máy nổ so với BTS không đúng thiết kế");
		add75.setType(1);
		add75.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_NHA_MAY_NO);
		
		add76.setCat_Cause_Constr_Unquality_Id(76);
		add76.setProcessId(76);
		add76.setIs_Active(1);
		add76.setName("Vị trí nhà máy nổ so với BTS không đúng thiết kế");
		add76.setType(1);
		add76.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_NHA_MAY_NO);			
		
		add77.setCat_Cause_Constr_Unquality_Id(77);
		add77.setProcessId(77);
		add77.setIs_Active(1);
		add77.setName("Mác bê tông không đúng thiết kế");
		add77.setType(1);
		add77.setSub_Type(Constants.UNQUALIFY_TYPE.SUB_TYPE_PILLAR);
		
		add78.setCat_Cause_Constr_Unquality_Id(78);
		add78.setProcessId(7);
		add78.setIs_Active(1);
		add78.setName("Không đảm bảo thiết kế");
		add78.setType(3);
		add78.setSub_Type("CABLE");

		add79.setCat_Cause_Constr_Unquality_Id(79);
		add79.setProcessId(8);
		add79.setIs_Active(1);
		add79.setName("Không đảm bảo chất lượng");
		add79.setType(3);
		add79.setSub_Type("CABLE");
		
		listResult.add(add1);
		listResult.add(add2);
		listResult.add(add3);
		listResult.add(add4);
		listResult.add(add5);
		listResult.add(add6);
		listResult.add(add7);
		listResult.add(add8);
		listResult.add(add9);
		listResult.add(add10);
		listResult.add(add11);
		listResult.add(add12);
		
		listResult.add(add13);
		listResult.add(add14);
		listResult.add(add15);
		listResult.add(add16);
		listResult.add(add17);
		listResult.add(add18);
		listResult.add(add19);
		listResult.add(add20);
		listResult.add(add21);
		listResult.add(add22);
		listResult.add(add23);
		listResult.add(add24);
		listResult.add(add25);
		listResult.add(add26);
		listResult.add(add27);
		listResult.add(add28);
		listResult.add(add29);
		listResult.add(add30);
		listResult.add(add31);
		listResult.add(add32);
		listResult.add(add33);
		listResult.add(add34);
		listResult.add(add35);
		listResult.add(add36);
		listResult.add(add37);
		listResult.add(add38);
		listResult.add(add39);
		listResult.add(add40);
		listResult.add(add41);
		listResult.add(add42);
		listResult.add(add43);
		listResult.add(add44);
		listResult.add(add45);
		listResult.add(add46);
		listResult.add(add47);
		listResult.add(add48);
		listResult.add(add49);
		listResult.add(add50);
		listResult.add(add51);
		listResult.add(add52);
		listResult.add(add53);
		listResult.add(add54);
		listResult.add(add55);
		listResult.add(add56);
		listResult.add(add57);
		listResult.add(add58);
		listResult.add(add59);
		listResult.add(add60);
		listResult.add(add61);
		listResult.add(add62);
		listResult.add(add63);
		listResult.add(add64);
		listResult.add(add65);
		listResult.add(add66);
		listResult.add(add67);
		listResult.add(add68);
		listResult.add(add69);
		listResult.add(add70);
		listResult.add(add71);
		listResult.add(add72);
		listResult.add(add73);
		listResult.add(add74);
		listResult.add(add75);
		listResult.add(add76);
		listResult.add(add77);
		listResult.add(add78);
		listResult.add(add79);
		return listResult;
	}

}
