package com.viettel.view.control;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.viettel.actionbar.TextView_Popup;

public class VNMTextViewPopup extends TextView {	
	private TextView_Popup textViewPopup = null;	
	public VNMTextViewPopup(Context context) {
		super(context);
		this.textViewPopup = new TextView_Popup(context, this,
				InputType.TYPE_CLASS_NUMBER, false);
	}

	public VNMTextViewPopup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.textViewPopup = new TextView_Popup(context, this,
				InputType.TYPE_CLASS_NUMBER, false);

	}

	public VNMTextViewPopup(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.textViewPopup = new TextView_Popup(context, this,
				InputType.TYPE_CLASS_NUMBER, false);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean onTouch = false;
		VNMTextViewPopup et = VNMTextViewPopup.this;
		this.textViewPopup.setText(et.getText().toString());
		this.textViewPopup.showAtCenter();
		onTouch = super.onTouchEvent(event);
		return onTouch;
	}
}
