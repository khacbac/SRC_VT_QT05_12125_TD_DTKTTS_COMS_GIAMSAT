package com.viettel.database.entity;

public class Supervision_Bts_EquipEntity {
	public long supervision_BTS_EQUIP_ID;
	public long bts_2g_Company_Id;
	public long bts_3g_Company_Id;
	public long _2g_TOTAL;
	public long _3g_TOTAL;
	public int _2g_TYPE;
	public int _3g_TYPE;
	public long supervision_BTS_ID;
	public int trans_Type;
	public long trans_Company_Id;
	public int trans_freq;
	public float trans_viba_dimension;
	private long processId;
	private int sync_Status;
	private int isActive;
	private long employeeId;
	private long supervisionConstrId;

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public Supervision_Bts_EquipEntity() {
		trans_freq = -1;
		trans_viba_dimension = -1;
	}

	public int get_2g_TYPE() {
		return _2g_TYPE;
	}

	public void set_2g_TYPE(int _2g_TYPE) {
		this._2g_TYPE = _2g_TYPE;
	}

	public long getBts_2g_Company_Id() {
		return bts_2g_Company_Id;
	}

	public void setBts_2g_Company_Id(long bts_2g_Company_Id) {
		this.bts_2g_Company_Id = bts_2g_Company_Id;
	}

	public long getBts_3g_Company_Id() {
		return bts_3g_Company_Id;
	}

	public void setBts_3g_Company_Id(long bts_3g_Company_Id) {
		this.bts_3g_Company_Id = bts_3g_Company_Id;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public int getSync_Status() {
		return sync_Status;
	}

	public void setSync_Status(int sync_Status) {
		this.sync_Status = sync_Status;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public long getSupervision_BTS_ID() {
		return supervision_BTS_ID;
	}

	public void setSupervision_BTS_ID(long supervision_BTS_ID) {
		this.supervision_BTS_ID = supervision_BTS_ID;
	}

	public long getSupervision_BTS_EQUIP_ID() {
		return supervision_BTS_EQUIP_ID;
	}

	public void setSupervision_BTS_EQUIP_ID(long supervision_BTS_EQUIP_ID) {
		this.supervision_BTS_EQUIP_ID = supervision_BTS_EQUIP_ID;
	}

	public int getTrans_Type() {
		return trans_Type;
	}

	public void setTrans_Type(int trans_Type) {
		this.trans_Type = trans_Type;
	}

	public long getTrans_Company_Id() {
		return trans_Company_Id;
	}

	public void setTrans_Company_Id(long trans_Company_Id) {
		this.trans_Company_Id = trans_Company_Id;
	}

	public int getTrans_freq() {
		return trans_freq;
	}

	public void setTrans_freq(int trans_freq) {
		this.trans_freq = trans_freq;
	}

	public float getTrans_viba_dimension() {
		return trans_viba_dimension;
	}

	public void setTrans_viba_dimension(float trans_viba_dimension) {
		this.trans_viba_dimension = trans_viba_dimension;
	}

	public long get_2g_TOTAL() {
		return _2g_TOTAL;
	}

	public void set_2g_TOTAL(long _2g_TOTAL) {
		this._2g_TOTAL = _2g_TOTAL;
	}

	public long get_3g_TOTAL() {
		return _3g_TOTAL;
	}

	public void set_3g_TOTAL(long _3g_TOTAL) {
		this._3g_TOTAL = _3g_TOTAL;
	}

	public int get_3g_TYPE() {
		return _3g_TYPE;
	}

	public void set_3g_TYPE(int _3g_TYPE) {
		this._3g_TYPE = _3g_TYPE;
	}

}
