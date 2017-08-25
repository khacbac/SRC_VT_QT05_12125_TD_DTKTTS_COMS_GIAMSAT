package com.viettel.database.entity;

public class Cause_Bts_Cat_AssessEntity {

	private long cause_BTS_CAT_ASSESS_ID;
	private Cat_Cause_Constr_UnQualifyEntity cat_Cause_Constr_UnQualifyEntity;
	private Supervision_Bts_Cat_AssessEntity supervision_Bts_Cat_AssessEntity;

	public Cause_Bts_Cat_AssessEntity() {
		cat_Cause_Constr_UnQualifyEntity = new Cat_Cause_Constr_UnQualifyEntity();
		supervision_Bts_Cat_AssessEntity = new Supervision_Bts_Cat_AssessEntity();
	}

	public long getCause_BTS_CAT_ASSESS_ID() {
		return cause_BTS_CAT_ASSESS_ID;
	}

	public void setCause_BTS_CAT_ASSESS_ID(long cause_BTS_CAT_ASSESS_ID) {
		this.cause_BTS_CAT_ASSESS_ID = cause_BTS_CAT_ASSESS_ID;
	}

	public Cat_Cause_Constr_UnQualifyEntity getCat_Cause_Constr_UnQualifyEntity() {
		return cat_Cause_Constr_UnQualifyEntity;
	}

	public void setCat_Cause_Constr_UnQualifyEntity(
			Cat_Cause_Constr_UnQualifyEntity cat_Cause_Constr_UnQualifyEntity) {
		this.cat_Cause_Constr_UnQualifyEntity = cat_Cause_Constr_UnQualifyEntity;
	}

	public Supervision_Bts_Cat_AssessEntity getSupervision_Bts_Cat_AssessEntity() {
		return supervision_Bts_Cat_AssessEntity;
	}

	public void setSupervision_Bts_Cat_AssessEntity(
			Supervision_Bts_Cat_AssessEntity supervision_Bts_Cat_AssessEntity) {
		this.supervision_Bts_Cat_AssessEntity = supervision_Bts_Cat_AssessEntity;
	}

}
