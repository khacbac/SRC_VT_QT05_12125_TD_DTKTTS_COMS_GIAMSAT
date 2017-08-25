/**
 * Copyright 2013 Viettel Telecome. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.viettel.actionbar;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.viettel.ktts.R;

/**
 * 
 * Mo ta muc dich lop
 * 
 * @author: Datnv5
 * @throws:
 */
public class Header extends RelativeLayout implements OnClickListener {
	private LayoutInflater mInflater;
	private RelativeLayout mBarView;
	private ImageView mUserView;
	private ImageButton mUserBack;
	private TextView mTitleView;
	private LinearLayout mActionsView;// add các chức năng
	private ImageView logoviettel;
	public Header(Context context, AttributeSet attrs) {
		super(context, attrs);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		mBarView = (RelativeLayout) mInflater.inflate(R.layout.header, null);		
		
		addView(mBarView);
		mUserView = (ImageView) mBarView.findViewById(R.id.actionbar_user_icon);
		mUserBack = (ImageButton) mBarView.findViewById(R.id.actionbar_back_btn);
		mTitleView = (TextView) mBarView.findViewById(R.id.header_title);
		mActionsView = (LinearLayout) mBarView.findViewById(R.id.header_actions);
		logoviettel= (ImageView) mBarView.findViewById(R.id.logoviettel);
		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.ActionBar);
		CharSequence title = a.getString(R.styleable.ActionBar_title);
		if (title != null) {
			setTitle(title);
		}
		
		logoviettel.setVisibility(View.GONE);
		mUserView.setVisibility(View.GONE);
		a.recycle();
	}
	
	public void setVisibilityLogo() {
		logoviettel.setVisibility(View.VISIBLE);
	}
	public void setBackAction(Action action) {
		mUserBack.setOnClickListener(this);
		mUserBack.setTag(action);
		mUserBack.setImageResource(action.getDrawable());
		mUserBack.setVisibility(View.VISIBLE);
	}

	public void setTitle(CharSequence title) {
		mUserView.setVisibility(View.VISIBLE);
		mTitleView.setText(title);
	}

	public void setTitle(int resid) {
		mTitleView.setText(resid);
	}

	public void setOnTitleClickListener(OnClickListener listener) {
		mTitleView.setOnClickListener(listener);
	}

	public void addAction(Action action, int index) {
		mActionsView.addView(inflateAction(action), index);
	}

	public interface Action {
		public int getDrawable();
		public void performAction(View view);
	}

	private View inflateAction(Action action) {
		View view = mInflater.inflate(R.layout.header_item, mActionsView,false);
		ImageButton imgButtom = (ImageButton) view.findViewById(R.id.actionbar_item);
		imgButtom.setImageResource(action.getDrawable());
		view.setTag(action);
		view.setOnClickListener(this);
		return view;
	}

	public static abstract class AbstractAction implements Action {
		final private int mDrawable;

		public AbstractAction(int drawable) {
			mDrawable = drawable;
		}

		@Override
		public int getDrawable() {
			return mDrawable;
		}
	}

	@Override
	public void onClick(View view) {				
		final Object tag = view.getTag();
		if (tag instanceof Action) {
			final Action action = (Action) tag;
			action.performAction(view);
		}
	}

	/**
	 * 
	 * Mo ta muc dich ham
	 * 
	 * @author: Datnv5
	 * @param
	 * @param
	 * @return:
	 * @throws:
	 */
	public void addAction(Action action) {
		final int index = mActionsView.getChildCount();
		addAction(action, index);
	}

	public static class IntentAction extends AbstractAction {
		private Context mContext;
		private Intent mIntent;

		public IntentAction(Context context, Intent intent, int drawable) {
			super(drawable);
			mContext = context;
			mIntent = intent;
		}

		@Override
		public void performAction(View view) {
			try {
				mContext.startActivity(mIntent);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(
						mContext,
						mContext.getText(R.string.actionbar_activity_not_found),Toast.LENGTH_SHORT).show();
			}
		}
	}
}
