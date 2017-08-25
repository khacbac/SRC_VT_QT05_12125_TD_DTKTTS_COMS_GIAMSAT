package com.viettel.actionbar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.viettel.constants.Constants;
import com.viettel.database.entity.Supervision_LBG_UnqualifyItemEntity;
import com.viettel.ktts.R;
import com.viettel.view.control.ContructionUnqualifyAdapter;
import com.viettel.view.listener.OnEventControlListener;

import java.util.List;

public class Contruction_UnqualifyPopup implements OnClickListener {
	private Activity popupActivity; // activity hien thi popup
	private PopupWindow popup;
	private LinearLayout layout;
	private ViewGroup viewGroup;
	private LayoutInflater layoutInflater;
	private List<Supervision_LBG_UnqualifyItemEntity> listData;
	private ListView lv_contruction_unqualify;
	private Button btn_contruction_unqualify_choice;
	private ContructionUnqualifyAdapter unqualifyAdapter;
	private int iWidth = Constants.UNQUALIFY_WIDTH;
	private OnEventControlListener onEvent;
	//
	 

	public Contruction_UnqualifyPopup(Activity insertActivity,
			ViewGroup viewGroup, List<Supervision_LBG_UnqualifyItemEntity> curListData) {
		this.listData = curListData;
		this.popupActivity = insertActivity;
		this.viewGroup = viewGroup;
		onEvent = (OnEventControlListener) insertActivity;
		// Inflate the popup_layout.xml
		layoutInflater = (LayoutInflater) popupActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = (LinearLayout) layoutInflater.inflate(
				R.layout.contruction_unqualify_layout, this.viewGroup, false);
		this.btn_contruction_unqualify_choice = (Button) layout
				.findViewById(R.id.btn_contruction_unqualify_choice);
		this.btn_contruction_unqualify_choice.setOnClickListener(this);
		this.lv_contruction_unqualify = (ListView) layout
				.findViewById(R.id.lv_contruction_unqualify);
		unqualifyAdapter = new ContructionUnqualifyAdapter(popupActivity,
				listData);
		this.lv_contruction_unqualify.setAdapter(unqualifyAdapter);
		/* Khai bao popup window */
		DisplayMetrics metrics = popupActivity.getResources().getDisplayMetrics();
	     int width;
	     int height;
	     width = metrics.widthPixels * 80 / 100;
	     height = metrics.heightPixels * 90 / 100;
//		popup = new PopupWindow(layout, this.iWidth,
//				ViewGroup.LayoutParams.MATCH_PARENT, true);
	     popup = new PopupWindow(layout, width,
	    		 ViewGroup.LayoutParams.MATCH_PARENT, true);
	     
	}

	public Contruction_UnqualifyPopup(Activity insertActivity,
			ViewGroup viewGroup, List<Supervision_LBG_UnqualifyItemEntity> curListData,
			int curIWidth) {
		this.listData = curListData;
		this.popupActivity = insertActivity;
		this.viewGroup = viewGroup;
		this.iWidth = curIWidth;
		// Inflate the popup_layout.xml
		layoutInflater = (LayoutInflater) popupActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = (LinearLayout) layoutInflater.inflate(
				R.layout.contruction_unqualify_layout, this.viewGroup, false);
		this.lv_contruction_unqualify = (ListView) layout
				.findViewById(R.id.lv_contruction_unqualify);
		unqualifyAdapter = new ContructionUnqualifyAdapter(popupActivity,
				curListData);
		this.lv_contruction_unqualify.setAdapter(unqualifyAdapter);
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

	public void refreshData() {
		
		this.unqualifyAdapter.notifyDataSetChanged();
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
		popup.showAtLocation(layout, Gravity.CENTER_HORIZONTAL, OFFSET_X,
				OFFSET_Y);
	}

	public void hidePopup() {
		this.popup.dismiss();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_contruction_unqualify_choice:
			onEvent.onEvent(OnEventControlListener.EVENT_UNQUALIFY_CHOICE, null,
					listData);
			break;
		default:
			break;
		}
	}
}
