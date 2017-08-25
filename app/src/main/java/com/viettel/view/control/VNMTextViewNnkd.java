package com.viettel.view.control;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.viettel.ktts.R;

public class VNMTextViewNnkd extends TextView {
	final Drawable imgX = getResources().getDrawable(R.drawable.icon_nnkd);

	public VNMTextViewNnkd(Context context) {
		super(context);
		init();
	}

	public VNMTextViewNnkd(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public VNMTextViewNnkd(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	void init() {
		imgX.setBounds(0, 0, imgX.getIntrinsicWidth(),
				imgX.getIntrinsicHeight());
		
		addComboIcon();
	}

	void addComboIcon() {
		this.setCompoundDrawables(this.getCompoundDrawables()[0],
				this.getCompoundDrawables()[1], imgX,
				this.getCompoundDrawables()[3]);
	}
}
