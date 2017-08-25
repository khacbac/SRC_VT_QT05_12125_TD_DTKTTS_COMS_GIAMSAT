package com.viettel.actionbar;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.viettel.constants.Constants;
import com.viettel.ktts.R;
import com.viettel.view.control.VNMTextViewPopup;

public class TextView_Popup implements OnClickListener {
	private PopupWindow popup;
	private LinearLayout layout;
	private ViewGroup viewGroup;
	private LayoutInflater layoutInflater;
	private final EditText edt_edit_text_popup_value;
	private Button btn_edit_text_popup_choice;
	private int iWidth = Constants.UNQUALIFY_WIDTH;	
	private VNMTextViewPopup textView;

	public TextView_Popup(Context context, VNMTextViewPopup curtextView,
			int iInputType, boolean bMultiLine) {		
		this.textView = curtextView;
		// Inflate the popup_layout.xml
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = (LinearLayout) layoutInflater.inflate(
				R.layout.edit_text_popup_layout, this.viewGroup, false);

		this.edt_edit_text_popup_value = (EditText) layout
				.findViewById(R.id.edt_edit_text_popup_value);
		ViewGroup.LayoutParams lpEdittext = edt_edit_text_popup_value
				.getLayoutParams();
		switch (iInputType) {
		case InputType.TYPE_CLASS_NUMBER:
			this.edt_edit_text_popup_value.setSingleLine(true);
			break;
		case InputType.TYPE_CLASS_TEXT:
			if (bMultiLine) {
				this.edt_edit_text_popup_value.setSingleLine(false);
				this.edt_edit_text_popup_value.setLines(14);
				this.edt_edit_text_popup_value.setMinLines(13);
				this.edt_edit_text_popup_value.setMaxLines(15);
				this.edt_edit_text_popup_value
						.setLines(Constants.NUMBER_LINE_DEFAULT);
				lpEdittext.height = Constants.NUMBER_LINE_HEIGHT_DEFAULT;
			}
			this.edt_edit_text_popup_value.setGravity(Gravity.LEFT
					| Gravity.TOP);
			this.edt_edit_text_popup_value.setLayoutParams(lpEdittext);
			break;
		default:
			break;
		}
		this.edt_edit_text_popup_value.setInputType(iInputType);
		this.btn_edit_text_popup_choice = (Button) layout
				.findViewById(R.id.btn_edit_text_popup_choice);
		this.btn_edit_text_popup_choice.setOnClickListener(this);

		/* Khai bao popup window */
		popup = new PopupWindow(layout, this.iWidth,
				ViewGroup.LayoutParams.MATCH_PARENT, true);		
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
		this.edt_edit_text_popup_value.setFocusable(true);
		// Displaying the popup at the specified location, + offsets.
		popup.showAtLocation(layout, Gravity.CENTER_HORIZONTAL, OFFSET_X,
				OFFSET_Y);
	}

	public void setText(String sValue) {
		this.edt_edit_text_popup_value.setText(sValue);
	}

	public void hidePopup() {
		this.popup.dismiss();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_edit_text_popup_choice:
			String sCurValue = this.edt_edit_text_popup_value.getText()
					.toString();
			textView.setText(sCurValue);
			this.hidePopup();
			break;
		default:
			break;
		}
	}
}
