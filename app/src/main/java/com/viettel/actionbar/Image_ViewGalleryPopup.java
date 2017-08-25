package com.viettel.actionbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.viettel.constants.Constants;
import com.viettel.database.entity.ImageEntity;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;
import com.viettel.view.control.Image_ViewAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.List;

public class Image_ViewGalleryPopup implements OnClickListener {
	private Activity popupActivity; // activity hien thi popup
	private PopupWindow popup;
	private LinearLayout layout;
	private ImageView iv_img_take;
	private ImageView iv_img_attack;
	private ImageView iv_img_delete;
	private ImageView iv_img_exit;
	private ViewGroup viewGroup;
	private LayoutInflater layoutInflater;
	private List<ImageEntity> listData;
	private GridView lv_img_gallery_list;
	private Image_ViewAdapter imgViewAdapter;
	private int iWidth = Constants.UNQUALIFY_WIDTH;
	private OnEventControlListener onEvent;

	public Image_ViewGalleryPopup(Activity insertActivity, ViewGroup viewGroup,
			List<ImageEntity> curListData) {
		this.listData = curListData;
		this.popupActivity = insertActivity;
		this.viewGroup = viewGroup;
		onEvent = (OnEventControlListener) insertActivity;
		// Inflate the popup_layout.xml
		layoutInflater = (LayoutInflater) popupActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = (LinearLayout) layoutInflater.inflate(
				R.layout.img_gallery_view_layout, this.viewGroup, false);

		this.iv_img_take = (ImageView) layout.findViewById(R.id.iv_img_take);
		this.iv_img_take.setOnClickListener(this);

		this.iv_img_attack = (ImageView) layout
				.findViewById(R.id.iv_img_attack);
		this.iv_img_attack.setOnClickListener(this);

		this.iv_img_delete = (ImageView) layout
				.findViewById(R.id.iv_img_delete);
		this.iv_img_delete.setOnClickListener(this);

		this.iv_img_exit = (ImageView) layout.findViewById(R.id.iv_img_exit);
		this.iv_img_exit.setOnClickListener(this);

		this.lv_img_gallery_list = (GridView) layout
				.findViewById(R.id.lv_img_gallery_list);
		imgViewAdapter = new Image_ViewAdapter(popupActivity, listData);
		this.lv_img_gallery_list.setAdapter(imgViewAdapter);
		/* Khai bao popup window */
		popup = new PopupWindow(layout, this.iWidth,
				ViewGroup.LayoutParams.MATCH_PARENT, true);
	}

	public Image_ViewGalleryPopup(Activity insertActivity, ViewGroup viewGroup,
			List<ImageEntity> curListData, int curIWidth) {
		this.listData = curListData;
		this.popupActivity = insertActivity;
		this.viewGroup = viewGroup;
		this.iWidth = curIWidth;
		// Inflate the popup_layout.xml
		layoutInflater = (LayoutInflater) popupActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = (LinearLayout) layoutInflater.inflate(
				R.layout.img_gallery_view_layout, this.viewGroup, false);
		this.lv_img_gallery_list = (GridView) layout
				.findViewById(R.id.lv_img_gallery_list);
		imgViewAdapter = new Image_ViewAdapter(popupActivity, curListData);
		this.lv_img_gallery_list.setAdapter(imgViewAdapter);
		/* Khai bao popup window */
		popup = new PopupWindow(layout, this.iWidth,
				ViewGroup.LayoutParams.MATCH_PARENT, true);
	}

	public Activity getPopupActivity() {
		return popupActivity;
	}

	public void setPopupActivity(Activity popupActivity) {
		this.popupActivity = popupActivity;
	}

	public void showPopup(Point p) {
		popup.setFocusable(true);

		// Some offset to align the popup a bit to the right, and a bit down,
		// relative to button's position.
		int OFFSET_X = 0;
		int OFFSET_Y = Constants.DROPDOWN_HEIGHT;

		// Clear the default translucent background
		popup.setBackgroundDrawable(new BitmapDrawable());

		// Displaying the popup at the specified location, + offsets.
		popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x + OFFSET_X, p.y
				+ OFFSET_Y);
	}

	public void showAtCenter() {
		popup.setFocusable(true);

		// Some offset to align the popup a bit to the right, and a bit down,
		// relative to button's position.
		int OFFSET_X = 0;
		int OFFSET_Y = 0;

		// Clear the default translucent background
		popup.setBackgroundDrawable(new BitmapDrawable());

		// Displaying the popup at the specified location, + offsets.
		popup.showAtLocation(layout, Gravity.TOP, OFFSET_X, OFFSET_Y);
	}

	public void hidePopup() {
		this.popup.dismiss();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_img_take:
			
			onEvent.onEvent(OnEventControlListener.EVENT_IMG_TAKE_NEW, null,
					null);
			break;
		case R.id.iv_img_attack:
			
			onEvent.onEvent(OnEventControlListener.EVENT_IMG_TAKE_ATTACK, null,
					null);
			
			break;
		case R.id.iv_img_delete:
			int checkItemDel = 0;  //bien de check xem co anh nao dc chon de xoa khong?
			boolean checkApprove = false;
			for (ImageEntity itemDel : listData) {
				if(itemDel.isDelete()){
					checkItemDel++;
					if(itemDel.getStatusApprove() == 1){
						checkApprove = true;
					}
					break;
				}
			}
			if(checkItemDel == 0){
				Toast.makeText(
						this.popupActivity,
						StringUtil
								.getString(R.string.image_unqualify_check_image_null),
						Toast.LENGTH_SHORT).show();
			}else if(checkApprove){
				Toast.makeText(
						this.popupActivity,
						StringUtil
								.getString(R.string.image_check_image_approve),
						Toast.LENGTH_SHORT).show();
			} else {
				onEvent.onEvent(OnEventControlListener.EVENT_IMG_TAKE_DELETE, null,
						null);
			}
			
			break;
		case R.id.iv_img_exit:
			if(listData.size() > Constants.MAX_IMAGE_CAPTURE){
				Toast.makeText(
						this.popupActivity,
						StringUtil
								.getString(R.string.out_of_max_image_capture),
						Toast.LENGTH_SHORT).show();
			} else {
				onEvent.onEvent(OnEventControlListener.EVENT_IMG_TAKE_EXIT, null,
						listData);
			}
			
			break;
		default:
			break;
		}
	}

	public void hideShowButton() {
		if (listData.size() > 0) {
			this.iv_img_delete.setVisibility(View.VISIBLE);
		} else {
			this.iv_img_delete.setVisibility(View.GONE);
		}
	}

	public void setImageData(ImageEntity curItem) {

		this.listData.add(curItem);
		this.imgViewAdapter.notifyDataSetChanged();
		this.iv_img_delete.setVisibility(View.VISIBLE);
	}

	public void deleteImageData() {

		for (int i = 0; i < listData.size(); i++) {
			if(listData.get(i).isDelete()){
				listData.remove(i);
				i--;
			}
		}
		this.imgViewAdapter.notifyDataSetChanged();
	}
}
