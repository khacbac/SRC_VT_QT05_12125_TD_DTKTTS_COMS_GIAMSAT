package com.viettel.ktts;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.view.listener.OnEventControlListener;

public class ItemContruction_Acceptance extends LinearLayout implements
		OnClickListener {
	private TextView tv_contruction_acceptance_item;
	private TextView tv_contruction_acceptance_category;
	private TextView tv_contruction_acceptance_is_mandory;
	private TextView tv_contruction_acceptance_item_comment;
	private ImageView iv_contruction_acceptance_item,iv_contruction_acceptance_status_approve;
	private OnEventControlListener onEvent;
	private Supervision_LBG_UnqualifyItemEntity acceptanceItem;
	@SuppressWarnings("unused")
	private Context curContext = null;

	public ItemContruction_Acceptance(Context context) {
		super(context);
		this.curContext = context;
	}

	public ItemContruction_Acceptance(Context context, View rowRoot) {
		super(context);
		this.curContext = context;
		this.tv_contruction_acceptance_item = (TextView) rowRoot
				.findViewById(R.id.tv_contruction_acceptance_item);
		this.tv_contruction_acceptance_item_comment = (TextView) rowRoot
				.findViewById(R.id.tv_contruction_acceptance_item_comment);
		tv_contruction_acceptance_item_comment.setVisibility(GONE);
		this.tv_contruction_acceptance_category = (TextView) rowRoot
				.findViewById(R.id.tv_contruction_acceptance_category);
		this.tv_contruction_acceptance_is_mandory = (TextView) rowRoot
				.findViewById(R.id.tv_contruction_acceptance_is_mandory);
		this.iv_contruction_acceptance_item = (ImageView) rowRoot
				.findViewById(R.id.iv_contruction_acceptance_item);
		
		this.iv_contruction_acceptance_status_approve = (ImageView) rowRoot
				.findViewById(R.id.iv_contruction_acceptance_status_approve);
		iv_contruction_acceptance_status_approve.setVisibility(GONE);
		
		this.iv_contruction_acceptance_item.setOnClickListener(this);
		this.iv_contruction_acceptance_status_approve.setOnClickListener(this);
		

		onEvent = (OnEventControlListener) context;
	}

	public void setData(Supervision_LBG_UnqualifyItemEntity curUniqualifyItem) {
		acceptanceItem = curUniqualifyItem;

		this.tv_contruction_acceptance_item.setText(this.acceptanceItem
				.getTitle());
		if (!this.acceptanceItem.getCategory().equals("")) {
			this.tv_contruction_acceptance_category.setText(this.acceptanceItem
					.getCategory());
		}

		if (this.acceptanceItem.getIs_Mandory() == 1) {
			tv_contruction_acceptance_is_mandory.setVisibility(View.VISIBLE);
		} else {
			tv_contruction_acceptance_is_mandory.setVisibility(View.GONE);
		}

		if (acceptanceItem.getLstNewAttachFile() != null
				&& acceptanceItem.getLstNewAttachFile().size() > 0) {
			iv_contruction_acceptance_item.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_image_exit));
			
			
			// test
//			acceptanceItem.getLstNewAttachFile().get(0).setStatusApprove(2);
//			acceptanceItem.getLstNewAttachFile().get(0).setResonDeny(" Đây là lý do thêm cho nguyên nhân không đạt nghiệm thu");
			// -----
			
			// icon trang thai phe duyệt
			// hiển thi icon phe duyet
			// STATUS_APPROVE (kiểu number, 1: phê duyệt,2: từ chối,0 hoặc null:
			// Chưa phê duyệt) thì mới hiển thị (v,x) tương ứng
			for(Supv_Constr_Attach_FileEntity supv : acceptanceItem.getLstNewAttachFile()){
				if (supv.getStatusApprove() == 1) {
					iv_contruction_acceptance_status_approve.setVisibility(VISIBLE);
					iv_contruction_acceptance_status_approve.setImageDrawable(getResources()
							.getDrawable(R.drawable.ic_approval));

				} else if (supv.getStatusApprove() == 2) {
					iv_contruction_acceptance_status_approve.setVisibility(VISIBLE);
					iv_contruction_acceptance_status_approve.setImageDrawable(getResources()
							.getDrawable(R.drawable.ic_reject));
					break;
				} else {
					iv_contruction_acceptance_status_approve.setVisibility(GONE);
				}
			}
		// ----- danh sach anh đã chọn	
		} else if (acceptanceItem.getLstAttachFile() != null
				&& acceptanceItem.getLstAttachFile().size() > 0) {
			iv_contruction_acceptance_item.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_image_exit));
			
			// test
//			acceptanceItem.getLstAttachFile().get(0).setStatusApprove(1);
//			acceptanceItem.getLstAttachFile().get(0).setResonDeny(" Đây là lý do thêm cho nguyên nhân không đạt nghiệm thu");
			// -----
			
			
			// icon trang thai phe duyệt
			// hiển thi icon phe duyet
			// STATUS_APPROVE (kiểu number, 1: phê duyệt,2: từ chối,0 hoặc null:
			// Chưa phê duyệt) thì mới hiển thị (v,x) tương ứng
			for(Supv_Constr_Attach_FileEntity supv : acceptanceItem.getLstAttachFile()){
				if (supv.getStatusApprove() == 1) {
					iv_contruction_acceptance_status_approve.setVisibility(VISIBLE);
					iv_contruction_acceptance_status_approve.setImageDrawable(getResources()
							.getDrawable(R.drawable.ic_approval));

				} else if (supv.getStatusApprove() == 2) {
					iv_contruction_acceptance_status_approve.setVisibility(VISIBLE);
					iv_contruction_acceptance_status_approve.setImageDrawable(getResources()
							.getDrawable(R.drawable.ic_reject));
					break;
				} else {
					iv_contruction_acceptance_status_approve.setVisibility(GONE);
				}
			}	

		} else {
			iv_contruction_acceptance_item.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_img_take));
			iv_contruction_acceptance_status_approve.setVisibility(GONE);
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_contruction_acceptance_item:
			if (acceptanceItem != null) {
				onEvent.onEvent(
						OnEventControlListener.EVENT_ACCEPTANCE_TAKE_PHOTO,
						null, acceptanceItem);

			}
			break;
		case R.id.iv_contruction_acceptance_status_approve:
			// kiểm tra trang thai cua tuong ảnh trong tường list attach 
						if (acceptanceItem.getLstNewAttachFile().size()>0) {
							// anh của nguyên nhân mới được chọn từ trước
							for(Supv_Constr_Attach_FileEntity supv : acceptanceItem.getLstNewAttachFile()){
								if (supv.getStatusApprove() == 2) {
									// hiển thị dong comment
										if (tv_contruction_acceptance_item_comment.getVisibility()==View.VISIBLE) {
											tv_contruction_acceptance_item_comment.setVisibility(View.GONE);
										}else{
											tv_contruction_acceptance_item_comment.setText(supv.getResonDeny());
											tv_contruction_acceptance_item_comment.setVisibility(View.VISIBLE);
										
									}
										break;
								}
							}
							// anh của nguyên nhân chọn từ trước
						}else if (acceptanceItem.getLstAttachFile().size()>0) {
							for(Supv_Constr_Attach_FileEntity supv : acceptanceItem.getLstAttachFile()){
								if (supv.getStatusApprove() == 2) {
									// hiển thị dong comment
										if (tv_contruction_acceptance_item_comment.getVisibility()==View.VISIBLE) {
											tv_contruction_acceptance_item_comment.setVisibility(View.GONE);
										}else{
											tv_contruction_acceptance_item_comment.setText(supv.getResonDeny());
											tv_contruction_acceptance_item_comment.setVisibility(View.VISIBLE);
										
									}
										break;
								}
							}
						}
			break;
		default:
			break;
		}

	}
}
