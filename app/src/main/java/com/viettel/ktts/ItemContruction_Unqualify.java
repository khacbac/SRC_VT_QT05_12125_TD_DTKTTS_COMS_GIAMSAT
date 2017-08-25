package com.viettel.ktts;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.database.entity.Supv_Constr_Attach_FileEntity;
import com.viettel.utils.StringUtil;
import com.viettel.view.listener.OnEventControlListener;

public class ItemContruction_Unqualify extends LinearLayout implements
		OnClickListener {
	private TextView tv_contruction_unqualify_item;
	private TextView tv_contruction_unqualify_category;
	private CheckBox chk_contruction_unqualify_item;
	private ImageView iv_contruction_unqualify_item;
	private ImageView img_contruction_unqualify_item;
	//
	private ImageView img_contruction_unqualify_item_status_approval;// icon Phê duyệt/từ chối 
	private TextView tv_contruction_unqualify_item_comment;// nguyên nhân từ chối
	
	
	private OnEventControlListener onEvent;
	private Supervision_LBG_UnqualifyItemEntity unqualifyItem;
	private Context curContext = null;

	public ItemContruction_Unqualify(Context context) {
		super(context);
		this.curContext = context;
	}

	public ItemContruction_Unqualify(Context context, View rowRoot) {
		super(context);
		this.curContext = context;
		this.tv_contruction_unqualify_item = (TextView) rowRoot
				.findViewById(R.id.tv_contruction_unqualify_item);
//		 comment ly do tu chối
		this.tv_contruction_unqualify_item_comment = (TextView) rowRoot
				.findViewById(R.id.tv_contruction_unqualify_item_comment);
		
		this.tv_contruction_unqualify_category = (TextView) rowRoot
				.findViewById(R.id.tv_contruction_unqualify_category);
		this.chk_contruction_unqualify_item = (CheckBox) rowRoot
				.findViewById(R.id.chk_contruction_unqualify_item);
		this.iv_contruction_unqualify_item = (ImageView) rowRoot
				.findViewById(R.id.iv_contruction_unqualify_item);
		this.img_contruction_unqualify_item = (ImageView) rowRoot
				.findViewById(R.id.ic_warning);
		// icon trạng thái thông tin phê duyệt (đồng ý/từ chối)
		this.img_contruction_unqualify_item_status_approval = (ImageView) rowRoot
				.findViewById(R.id.img_contruction_unqualify_item_status_approval);
		
		this.iv_contruction_unqualify_item.setOnClickListener(this);
		this.img_contruction_unqualify_item_status_approval.setOnClickListener(this);
		img_contruction_unqualify_item_status_approval.setVisibility(GONE);

		onEvent = (OnEventControlListener) context;
	}

	public void setData(Supervision_LBG_UnqualifyItemEntity curUniqualifyItem) {
		unqualifyItem = curUniqualifyItem;
		this.tv_contruction_unqualify_item.setText(this.unqualifyItem
				.getTitle());
		//duhv -- warning
		if (this.unqualifyItem.getIsSerious()==1) {
			this.img_contruction_unqualify_item.setVisibility(View.VISIBLE);
			this.tv_contruction_unqualify_item.setTextColor(Color.RED);
		}else{
			this.img_contruction_unqualify_item.setVisibility(View.GONE);
			this.tv_contruction_unqualify_item.setTextColor(Color.BLACK);
		}
		
		if (unqualifyItem.isUnqulify() && !unqualifyItem.isDelete()) {
			chk_contruction_unqualify_item.setChecked(true);
			
		} else {
			chk_contruction_unqualify_item.setChecked(false);
			
			if (tv_contruction_unqualify_item_comment.getVisibility()==View.VISIBLE) {
				tv_contruction_unqualify_item_comment.setVisibility(View.GONE);
			}
		}
		try {
			if (!this.unqualifyItem.getCategory().equals("")) {
				this.tv_contruction_unqualify_category.setText(this.unqualifyItem
						.getCategory());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		// if (unqualifyItem.getNewAttachFile() != null
		// && !StringUtil.isNullOrEmpty(unqualifyItem.getNewAttachFile()
		// .getFile_Path()) && !unqualifyItem.isDelete()) {
		
		// kiểm tra danh sách ảnh khi mới chọn
		if (unqualifyItem.getLstNewAttachFile() != null
				&& unqualifyItem.getLstNewAttachFile().size() > 0
				&& !unqualifyItem.isDelete()) {
			
//			unqualifyItem.getLstNewAttachFile().get(0).setStatusApprove(1);
//			unqualifyItem.getLstNewAttachFile().get(0).setResonDeny("sdfsdfsd sdfsdsd f");
			
			iv_contruction_unqualify_item.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_image_exit));
			// hiển thi icon phe duyet
			// STATUS_APPROVE (kiểu number, 1: phê duyệt,2: từ chối,0 hoặc null: Chưa phê duyệt) thì mới hiển thị (v,x) tương ứng
			for(Supv_Constr_Attach_FileEntity supv : unqualifyItem.getLstNewAttachFile()){
				if (supv.getStatusApprove() == 1) {
					img_contruction_unqualify_item_status_approval.setVisibility(VISIBLE);
					img_contruction_unqualify_item_status_approval.setImageDrawable(getResources().getDrawable(
									R.drawable.ic_approval));
				} else if (supv.getStatusApprove() == 2) {
					img_contruction_unqualify_item_status_approval.setVisibility(VISIBLE);
					img_contruction_unqualify_item_status_approval.setImageDrawable(getResources().getDrawable(
									R.drawable.ic_reject));
					break;
				} else {
					img_contruction_unqualify_item_status_approval.setVisibility(GONE);
				}
			}
			
		// danh sách ảnh khi chọn từ trước hoặc từ database	
		} else if (unqualifyItem.getLstAttachFile() != null
				&& unqualifyItem.getLstAttachFile().size() > 0
				&& !unqualifyItem.isDelete()) {
			iv_contruction_unqualify_item.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_image_exit));
			// test
//			unqualifyItem.getLstAttachFile().get(0).setStatusApprove(2);
//			unqualifyItem.getLstAttachFile().get(0).setResonDeny(" Đây là lý do thêm cho nguyên nhân không đạt nghiệm thu");
			// -----
			
			// hiển thi icon phe duyet
			// STATUS_APPROVE (kiểu number, 1: phê duyệt,2: từ chối,0 hoặc null:
			// Chưa phê duyệt) thì mới hiển thị (v,x) tương ứng
			for(Supv_Constr_Attach_FileEntity supv : unqualifyItem.getLstAttachFile()){
				if (supv.getStatusApprove() == 1) {
					img_contruction_unqualify_item_status_approval.setVisibility(VISIBLE);
					img_contruction_unqualify_item_status_approval.setImageDrawable(getResources().getDrawable(
									R.drawable.ic_approval));
				} else if (supv.getStatusApprove() == 2) {
					img_contruction_unqualify_item_status_approval.setVisibility(VISIBLE);
					img_contruction_unqualify_item_status_approval.setImageDrawable(getResources().getDrawable(
									R.drawable.ic_reject));
					break;
				} else {
					img_contruction_unqualify_item_status_approval.setVisibility(GONE);
				}
			}
			
		} else {
			iv_contruction_unqualify_item.setImageDrawable(getResources()
					.getDrawable(R.drawable.icon_img_take));
			// không hiển thị icon phê duyêt khi chưa chụp ảnh
			img_contruction_unqualify_item_status_approval.setVisibility(GONE);
		}

		this.chk_contruction_unqualify_item
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (unqualifyItem != null) {
							unqualifyItem.setUnqulify(isChecked);
							onEvent.onEvent(
									OnEventControlListener.EVENT_UNQUALIFY_CHECK_UCHECK,
									null, unqualifyItem);
						}
					}
				});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_contruction_unqualify_item:
			if (unqualifyItem != null) {
				if (unqualifyItem.isUnqulify()) {
					onEvent.onEvent(
							OnEventControlListener.EVENT_UNQUALIFY_TAKE_PHOTO,
							null, unqualifyItem);
				} else {
					Toast.makeText(
							this.curContext,
							StringUtil
									.getString(R.string.constr_line_tank_unqualify_uncheck_message),
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.img_contruction_unqualify_item_status_approval:
			// kiểm tra trang thai cua tuong ảnh trong tường list attach 
			if (unqualifyItem.getLstNewAttachFile().size()>0) {
				// anh của nguyên nhân mới được chọn từ trước
				for(Supv_Constr_Attach_FileEntity supv : unqualifyItem.getLstNewAttachFile()){
					if (supv.getStatusApprove() == 2) {
						// hiển thị dong comment
							if (tv_contruction_unqualify_item_comment.getVisibility()==View.VISIBLE) {
								tv_contruction_unqualify_item_comment.setVisibility(View.GONE);
							}else{
								tv_contruction_unqualify_item_comment.setText(supv.getResonDeny());
								tv_contruction_unqualify_item_comment.setVisibility(View.VISIBLE);
							
						}
							break;
					}
				}
				// anh của nguyên nhân chọn từ trước
			}else if (unqualifyItem.getLstAttachFile().size()>0) {
				for(Supv_Constr_Attach_FileEntity supv : unqualifyItem.getLstAttachFile()){
					if (supv.getStatusApprove() == 2) {
						// hiển thị dong comment
							if (tv_contruction_unqualify_item_comment.getVisibility()==View.VISIBLE) {
								tv_contruction_unqualify_item_comment.setVisibility(View.GONE);
							}else{
								tv_contruction_unqualify_item_comment.setText(supv.getResonDeny());
								tv_contruction_unqualify_item_comment.setVisibility(View.VISIBLE);
							
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
