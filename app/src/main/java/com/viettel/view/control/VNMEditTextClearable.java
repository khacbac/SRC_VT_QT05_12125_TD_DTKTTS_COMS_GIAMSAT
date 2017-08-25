package com.viettel.view.control;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AutoCompleteTextView;

import com.viettel.ktts.R;
/**
 * control edittext voi buttun clear text phia sau
 * 
 * @author banghn use: com.viettel.vinamilk.view.control.VNMEditTextClearable
 */
public class VNMEditTextClearable extends AutoCompleteTextView {
	public String defaultValue = "";
	// mac dinh editText tu xu ly handle OnTouch
	// neu khong muon editText tu xu ly thi set = false
	private boolean isHandleDefault = true;
	final Drawable imgX = getResources().getDrawable(
			R.drawable.icon_delete_box); // X image
	// image X visible ?
	private boolean isImageClearVisibile = true;

	public VNMEditTextClearable(Context context) {
		super(context);

		init();
	}

	public VNMEditTextClearable(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

		init();
	}

	public VNMEditTextClearable(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}

	/**
	 * Hien thi button (X) xoa text
	 * 
	 * @author banghn
	 * @param visible
	 */
	public void setImageClearVisibile(boolean visible) {
		isImageClearVisibile = visible;
		if (!isImageClearVisibile) {
			removeClearButton();
		}
	}

	/**
	 * init edittext
	 */
	void init() {
		// set vung bound button
		imgX.setBounds(0, 0, imgX.getIntrinsicWidth(),
				imgX.getIntrinsicHeight());

		// kiem tra viec hien thi button clear
		if (isImageClearVisibile) {
			manageClearButton();
		} else {
			removeClearButton();
		}

		this.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (isImageClearVisibile) {
					VNMEditTextClearable.this.manageClearButton();
				}
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// touch trong vung drawable
		boolean onTouch = false;
		VNMEditTextClearable et = VNMEditTextClearable.this;
		if (et != null
				&& event.getX() > et.getWidth() - et.getPaddingRight()
						- imgX.getIntrinsicWidth() && isImageClearVisibile) {
			et.setText("");
			VNMEditTextClearable.this.removeClearButton();
			onTouch = true;
		} else if (!this.isHandleDefault) {
			onTouch = false;
		} else {
			onTouch = super.onTouchEvent(event);
		}
		return onTouch;

	}

	/**
	 * hien thi button clear text?
	 * 
	 * @author banghn
	 */
	void manageClearButton() {
		if (this.getText().toString().equals(""))
			removeClearButton();
		else
			addClearButton();
	}

	/**
	 * Add button clear text
	 * 
	 * @author banghn
	 */
	void addClearButton() {
		this.setCompoundDrawables(this.getCompoundDrawables()[0],
				this.getCompoundDrawables()[1], imgX,
				this.getCompoundDrawables()[3]);
	}

	/**
	 * remove button clear text
	 * 
	 * @author banghn
	 */
	void removeClearButton() {
		this.setCompoundDrawables(this.getCompoundDrawables()[0],
				this.getCompoundDrawables()[1], null,
				this.getCompoundDrawables()[3]);
	}

	public void setIsHandleDefault(boolean isHandle) {
		this.isHandleDefault = isHandle;
	}

}