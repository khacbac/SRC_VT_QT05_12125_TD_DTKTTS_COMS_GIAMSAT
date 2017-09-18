package com.viettel.actionbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.viettel.constants.Constants;
import com.viettel.ktts.R;
import com.viettel.utils.StringUtil;
import com.viettel.view.base.BaseActivity;
import com.viettel.view.base.SupervisionBtsBaseActivity;
import com.viettel.view.listener.OnEventControlListener;

import org.greenrobot.eventbus.EventBus;

public class Edit_Text_Popup implements OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private Activity popupActivity; // activity hien thi popup
	private PopupWindow popup;
	private LinearLayout layout;
	private ViewGroup viewGroup;
	private LayoutInflater layoutInflater;
	private final EditText edt_edit_text_popup_value;
	private Button btn_edit_text_popup_choice;
	private int iWidth = Constants.UNQUALIFY_WIDTH;
	private String sValue;
    private LinearLayout layoutRoot;
	private OnEventControlListener onEvent;
	private int statusBarHeight;
	private ClipboardManager myClipboard;
	private ClipData myClip;
	private String[] arrayChoise = { StringUtil.getString(R.string.text_copy),
			StringUtil.getString(R.string.text_paste) };

	@SuppressLint("RtlHardcoded")
    @SuppressWarnings("static-access")
	public Edit_Text_Popup(Activity insertActivity, ViewGroup viewGroup,
			String sData, int iInputType, boolean bMultiLine, int iMaxLength) {
		this.popupActivity = insertActivity;
		 myClipboard = (ClipboardManager)
		 this.popupActivity.getSystemService(popupActivity.CLIPBOARD_SERVICE);
		this.viewGroup = viewGroup;
		this.sValue = ""+sData;
        onEvent = (OnEventControlListener) insertActivity;
		// Inflate the popup_layout.xml
		layoutInflater = (LayoutInflater) popupActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layout = (LinearLayout) layoutInflater.inflate(
				R.layout.edit_text_popup_layout, this.viewGroup, false);
		this.edt_edit_text_popup_value = (EditText) layout
				.findViewById(R.id.edt_edit_text_popup_value);

		// this.popupActivity.registerForContextMenu(edt_edit_text_popup_value);
		this.edt_edit_text_popup_value
				.setOnLongClickListener(new OnLongClickListener() {

					@Override
					public boolean onLongClick(View v) {
						
						// TODO Auto-generated method stub
						AlertDialog.Builder builder = new AlertDialog.Builder(
								popupActivity);
						builder.setItems(arrayChoise,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
							
										// copy
										if (which == 0) {
											String textCopy = edt_edit_text_popup_value
													.getText().toString();
											myClip = ClipData.newPlainText(
													"text", textCopy);
											myClipboard.setPrimaryClip(myClip);
											Toast.makeText(
													popupActivity
															.getApplicationContext(),
													R.string.text_temp_memory_copy,
													Toast.LENGTH_SHORT).show();
										} else {
											// paste
											ClipData clipPaste = myClipboard
													.getPrimaryClip();
											ClipData.Item itemClip = clipPaste
													.getItemAt(0);
											String textPaste = itemClip
													.getText().toString();
											edt_edit_text_popup_value
													.setText(textPaste);
										}
									}
								});
						// Build the Dialog
						builder.show();
						return false;
					}
				});

		ViewGroup.LayoutParams lpEdittext = edt_edit_text_popup_value.getLayoutParams();
		switch (iInputType) {
		case InputType.TYPE_NUMBER_FLAG_DECIMAL:
			this.edt_edit_text_popup_value.setSingleLine(true);
			this.edt_edit_text_popup_value
					.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL
							|InputType.TYPE_NUMBER_FLAG_SIGNED);
			break;
		case InputType.TYPE_CLASS_NUMBER:
			this.edt_edit_text_popup_value.setSingleLine(true);
			this.edt_edit_text_popup_value
					.setInputType(InputType.TYPE_CLASS_NUMBER);
			break;
		case InputType.TYPE_TEXT_FLAG_MULTI_LINE:
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
			this.edt_edit_text_popup_value
					.setInputType(InputType.TYPE_CLASS_TEXT
							| InputType.TYPE_TEXT_FLAG_MULTI_LINE);
			break;
		case InputType.TYPE_CLASS_TEXT:
			this.edt_edit_text_popup_value.setSingleLine(true);
			break;
		default:
			break;
		}
		this.edt_edit_text_popup_value.setText(this.sValue);
		/* Set max length */
		InputFilter[] fArray = new InputFilter[1];
		fArray[0] = new InputFilter.LengthFilter(iMaxLength);
		this.edt_edit_text_popup_value.setFilters(fArray);

		this.btn_edit_text_popup_choice = (Button) layout
				.findViewById(R.id.btn_edit_text_popup_choice);
		this.btn_edit_text_popup_choice.setOnClickListener(this);
		/* Khai bao popup window */
		popup = new PopupWindow(layout, this.iWidth,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		popupActivity.getWindowManager().getDefaultDisplay()
				.getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;

		Rect rectgle = new Rect();
		Window window = popupActivity.getWindow();
		window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
		statusBarHeight = rectgle.top;

		popup.setHeight(height - statusBarHeight);

        EventBus.getDefault().post(popup);
    }

	public Activity getPopupActivity() {
		return popupActivity;
	}

	public EditText getEdtControll() {
		return this.edt_edit_text_popup_value;
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

		// Rect rectgle= new Rect();
		// Window window= popupActivity.getWindow();
		// window.getDecorView().getWindowVisibleDisplayFrame(rectgle);
		// int StatusBarHeight= rectgle.top;
		// Some offset to align the popup a bit to the right, and a bit down,
		// relative to button's position.
		int OFFSET_X = 0;
		int OFFSET_Y = statusBarHeight;

		// Clear the default translucent background
		popup.setBackgroundDrawable(new BitmapDrawable());
		// Displaying the popup at the specified location, + offsets.
		popup.showAtLocation(layout, Gravity.TOP, OFFSET_X, OFFSET_Y);
        this.edt_edit_text_popup_value.requestFocus();
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
			onEvent.onEvent(OnEventControlListener.EVENT_SET_TEXT, null,
					sCurValue);
			break;
		default:
			break;
		}
	}

	// @Override
	// public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	// // TODO Auto-generated method stub
	// switch (item.getItemId()) {
	// // case CONTEXT_MENU.COPY:
	// // String textCopy = edt_edit_text_popup_value.getText().toString();
	// // myClip = ClipData.newPlainText("text", textCopy);
	// // myClipboard.setPrimaryClip(myClip);
	// // Toast.makeText(getApplicationContext(),
	// // R.string.text_temp_memory_copy, Toast.LENGTH_SHORT).show();
	// // return true;
	// // case CONTEXT_MENU.PASTE:
	// // ClipData clipPaste = myClipboard.getPrimaryClip();
	// // ClipData.Item itemClip = clipPaste.getItemAt(0);
	// // String textPaste = itemClip.getText().toString();
	// // myText.setText(textPaste);
	// //// Toast.makeText(getApplicationContext(), "Text Pasted",
	// //// Toast.LENGTH_SHORT).show();
	// // return true;
	// // default:
	// // return super.onContextItemSelected(item);
	// }
	// return false;
	// }
	//
	// @Override
	// public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	// // TODO Auto-generated method stub
	// menu.add(0, CONTEXT_MENU.COPY, 0, R.string.text_copy);
	// menu.add(0, CONTEXT_MENU.PASTE, 1, R.string.text_paste);
	//
	// return false;
	// }
	//
	// @Override
	// public void onDestroyActionMode(ActionMode mode) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	// // TODO Auto-generated method stub
	// return false;
	// }
}
