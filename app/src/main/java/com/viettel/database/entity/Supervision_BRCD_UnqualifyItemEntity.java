package com.viettel.database.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supervision_BRCD_UnqualifyItemEntity implements Serializable {

	private static final long serialVersionUID = 7810155547032154441L;
	private long cat_Cause_Constr_Unqualify_Id = -1;
	private long cat_Cause_Constr_Acceptance_Id = -1;
	private long cause_Line_Bg_Id = -1;
	private String title = "";
	private boolean isUnqulify = false;
	private Supv_Constr_Attach_FileEntity attachFile = null;
	private Supv_Constr_Attach_FileEntity newAttachFile = null;
	/* Danh dau xoa nguyen nhan khong dat nay */
	private boolean isDelete = false;
	/* Dung danh dau la xoa anh goc cua nguyen nhan khong dat nay */
	private boolean isDeleteImage = false;
	private int sync_Status;
	private int isActive;
	private long processId;
	private long supervisionConstrId;
	
	//phan sua doi, 1 nguyen nhan loi co nhieu anh
	private List<Supv_Constr_Attach_FileEntity> lstAttachFile = null;
	private List<Supv_Constr_Attach_FileEntity> lstNewAttachFile = null;

	public long getCat_Cause_Constr_Acceptance_Id() {
		return cat_Cause_Constr_Acceptance_Id;
	}

	public void setCat_Cause_Constr_Acceptance_Id(
			long cat_Cause_Constr_Acceptance_Id) {
		this.cat_Cause_Constr_Acceptance_Id = cat_Cause_Constr_Acceptance_Id;
	}

	public long getSupervisionConstrId() {
		return supervisionConstrId;
	}

	public void setSupervisionConstrId(long supervisionConstrId) {
		this.supervisionConstrId = supervisionConstrId;
	}

	public Supervision_BRCD_UnqualifyItemEntity() {
		attachFile = new Supv_Constr_Attach_FileEntity();
		newAttachFile = new Supv_Constr_Attach_FileEntity();
		lstAttachFile = new ArrayList<Supv_Constr_Attach_FileEntity>();
		lstNewAttachFile = new ArrayList<Supv_Constr_Attach_FileEntity>();
	}

	public List<Supv_Constr_Attach_FileEntity> getLstAttachFile() {
		return lstAttachFile;
	}

	public void setLstAttachFile(List<Supv_Constr_Attach_FileEntity> lstAttachFile) {
		this.lstAttachFile = lstAttachFile;
	}

	public List<Supv_Constr_Attach_FileEntity> getLstNewAttachFile() {
		return lstNewAttachFile;
	}

	public void setLstNewAttachFile(
			List<Supv_Constr_Attach_FileEntity> lstNewAttachFile) {
		this.lstNewAttachFile = lstNewAttachFile;
	}

	public long getCat_Cause_Constr_Unqualify_Id() {
		return cat_Cause_Constr_Unqualify_Id;
	}

	public void setCat_Cause_Constr_Unqualify_Id(
			long cat_Cause_Constr_Unqualify_Id) {
		this.cat_Cause_Constr_Unqualify_Id = cat_Cause_Constr_Unqualify_Id;
	}

	public long getCause_Line_Bg_Id() {
		return cause_Line_Bg_Id;
	}

	public void setCause_Line_Bg_Id(long cause_Line_Bg_Tank_Id) {
		this.cause_Line_Bg_Id = cause_Line_Bg_Tank_Id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isUnqulify() {
		return isUnqulify;
	}

	public void setUnqulify(boolean isUnqulify) {
		this.isUnqulify = isUnqulify;
	}

	public Supv_Constr_Attach_FileEntity getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(Supv_Constr_Attach_FileEntity attachFile) {
		this.attachFile = attachFile;
	}

	public Supv_Constr_Attach_FileEntity getNewAttachFile() {
		return newAttachFile;
	}

	public void setNewAttachFile(Supv_Constr_Attach_FileEntity newAttachFile) {
		this.newAttachFile = newAttachFile;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isDeleteImage() {
		return isDeleteImage;
	}

	public void setDeleteImage(boolean isDeleteImage) {
		this.isDeleteImage = isDeleteImage;
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

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
