package com.viettel.database.entity;

import java.io.Serializable;

public class Supervision_BRCD_Entity implements Serializable {
	private static final long serialVersionUID = 1050294185975395843L;
	private long SUPERVISION_CONSTR_ID;
	private long SUPERVISION_BRCD_ID;
	private int DESCS;
	private int BRANCH_CABLE_TYPE;
	private int BRANCH_NUM_CABLE;
	private int BRANCH_NUM_CHEST;
	private int TRUNK_PILLAR_NEW_TOTAL;
	private int TRUNK_PILLAR_OLD_TOTAL;
	private int BRANCH_PILLAR_NEW_TOTAL;
	private int BRANCH_PILLAR_OLD_TOTAL;
	private int TRUNK_NUM_CABLE_HT;
	private int TRUNK_NUM_CABLE_BT;
	private int TRUNK_NUM_CABLE_CS;

	private int SYNC_STATUS;
	private int IS_ACTIVE;
	private int PROCESS_ID;
	


	public int getDESCS() {
		return DESCS;
	}

	public void setDESCS(int dESCS) {
		DESCS = dESCS;
	}

	public long getSUPERVISION_BRCD_ID() {
		return SUPERVISION_BRCD_ID;
	}

	public void setSUPERVISION_BRCD_ID(long sUPERVISION_BRCD_ID) {
		SUPERVISION_BRCD_ID = sUPERVISION_BRCD_ID;
	}


	public int getSYNC_STATUS() {
		return SYNC_STATUS;
	}

	public void setSYNC_STATUS(int sYNC_STATUS) {
		SYNC_STATUS = sYNC_STATUS;
	}

	public int getIS_ACTIVE() {
		return IS_ACTIVE;
	}

	public void setIS_ACTIVE(int iS_ACTIVE) {
		IS_ACTIVE = iS_ACTIVE;
	}

	public int getPROCESS_ID() {
		return PROCESS_ID;
	}

	public void setPROCESS_ID(int pROCESS_ID) {
		PROCESS_ID = pROCESS_ID;
	}

	public long getEMPLOYEE_ID() {
		return EMPLOYEE_ID;
	}

	public void setEMPLOYEE_ID(long eMPLOYEE_ID) {
		EMPLOYEE_ID = eMPLOYEE_ID;
	}

	private long EMPLOYEE_ID;

	public long getSUPERVISION_CONSTR_ID() {
		return SUPERVISION_CONSTR_ID;
	}

	public void setSUPERVISION_CONSTR_ID(long sUPERVISION_CONSTR_ID) {
		SUPERVISION_CONSTR_ID = sUPERVISION_CONSTR_ID;
	}


	public int getTRUNK_NUM_CABLE_HT() {
		return TRUNK_NUM_CABLE_HT;
	}

	public void setTRUNK_NUM_CABLE_HT(int tRUNK_NUM_CABLE_HT) {
		TRUNK_NUM_CABLE_HT = tRUNK_NUM_CABLE_HT;
	}

	public int getTRUNK_NUM_CABLE_BT() {
		return TRUNK_NUM_CABLE_BT;
	}

	public void setTRUNK_NUM_CABLE_BT(int tRUNK_NUM_CABLE_BT) {
		TRUNK_NUM_CABLE_BT = tRUNK_NUM_CABLE_BT;
	}

	public int getTRUNK_NUM_CABLE_CS() {
		return TRUNK_NUM_CABLE_CS;
	}

	public void setTRUNK_NUM_CABLE_CS(int tRUNK_NUM_CABLE_CS) {
		TRUNK_NUM_CABLE_CS = tRUNK_NUM_CABLE_CS;
	}

	public int getBRANCH_CABLE_TYPE() {
		return BRANCH_CABLE_TYPE;
	}

	public void setBRANCH_CABLE_TYPE(int bRANCH_CABLE_TYPE) {
		BRANCH_CABLE_TYPE = bRANCH_CABLE_TYPE;
	}

	public int getBRANCH_NUM_CABLE() {
		return BRANCH_NUM_CABLE;
	}

	public void setBRANCH_NUM_CABLE(int bRANCH_NUM_CABLE) {
		BRANCH_NUM_CABLE = bRANCH_NUM_CABLE;
	}

	public int getBRANCH_NUM_CHEST() {
		return BRANCH_NUM_CHEST;
	}

	public void setBRANCH_NUM_CHEST(int bRANCH_NUM_CHEST) {
		BRANCH_NUM_CHEST = bRANCH_NUM_CHEST;
	}

	public int getTRUNK_PILLAR_NEW_TOTAL() {
		return TRUNK_PILLAR_NEW_TOTAL;
	}

	public void setTRUNK_PILLAR_NEW_TOTAL(int tRUNK_PILLAR_NEW_TOTAL) {
		TRUNK_PILLAR_NEW_TOTAL = tRUNK_PILLAR_NEW_TOTAL;
	}

	public int getTRUNK_PILLAR_OLD_TOTAL() {
		return TRUNK_PILLAR_OLD_TOTAL;
	}

	public void setTRUNK_PILLAR_OLD_TOTAL(int tRUNK_PILLAR_OLD_TOTAL) {
		TRUNK_PILLAR_OLD_TOTAL = tRUNK_PILLAR_OLD_TOTAL;
	}

	public int getBRANCH_PILLAR_NEW_TOTAL() {
		return BRANCH_PILLAR_NEW_TOTAL;
	}

	public void setBRANCH_PILLAR_NEW_TOTAL(int bRANCH_PILLAR_NEW_TOTAL) {
		BRANCH_PILLAR_NEW_TOTAL = bRANCH_PILLAR_NEW_TOTAL;
	}

	public int getBRANCH_PILLAR_OLD_TOTAL() {
		return BRANCH_PILLAR_OLD_TOTAL;
	}

	public void setBRANCH_PILLAR_OLD_TOTAL(int bRANCH_PILLAR_OLD_TOTAL) {
		BRANCH_PILLAR_OLD_TOTAL = bRANCH_PILLAR_OLD_TOTAL;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
