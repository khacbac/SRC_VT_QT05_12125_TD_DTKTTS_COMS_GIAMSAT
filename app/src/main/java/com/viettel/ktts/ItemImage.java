package com.viettel.ktts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.viettel.database.entity.ImageEntity;

import java.io.File;

public class ItemImage extends LinearLayout implements OnClickListener {
	private ImageView iv_unqualify,iv_status_approve;
	private ImageEntity imageItem;
	//sua doi 1 nguyen nhan loi, nhieu anh, them checkbox de xoa anh
	private CheckBox cb_iv_unqualify;
	
	private int finalHeight = 192;
	private int finalWidth = 192;

	public ItemImage(Context context) {
		super(context);
	}

	public ItemImage(Context context, View rowRoot) {
		super(context);
		this.iv_unqualify = (ImageView) rowRoot.findViewById(R.id.iv_unqualify);
		this.iv_status_approve = (ImageView) rowRoot.findViewById(R.id.iv_status_approve);
		this.iv_status_approve.setVisibility(GONE);
		this.cb_iv_unqualify = (CheckBox) rowRoot.findViewById(R.id.cb_iv_unqualify);
		this.cb_iv_unqualify.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if(isChecked){
					imageItem.setDelete(true);
				} else {
					imageItem.setDelete(false);
				}
			}
		});
	}

	public void setData(ImageEntity curimageItemEntity) {
		try {
			// check hiển thị icon phê duyệt
			if (curimageItemEntity.getStatusApprove()==1) {
				iv_status_approve.setImageDrawable(getResources()
						.getDrawable(R.drawable.ic_approval));
				iv_status_approve.setVisibility(VISIBLE);
			}else if (curimageItemEntity.getStatusApprove()==2) {
				iv_status_approve.setImageDrawable(getResources()
						.getDrawable(R.drawable.ic_reject));
				iv_status_approve.setVisibility(VISIBLE);
			}else {
				iv_status_approve.setVisibility(GONE);
			}
			
			
			imageItem = curimageItemEntity;
			File imgFile = new File(this.imageItem.getUrl());
			Log.e("ItemImage", "setData: " + this.imageItem.getUrl() + " " + imgFile.exists());
			if (imgFile.exists()) {
				this.setPic(this.imageItem.getUrl());
			}
			if(!curimageItemEntity.isDelete()){
				cb_iv_unqualify.setChecked(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_unqualify:
			break;
		default:
			break;
		}
	}

	/**
	 * Ham Resize imge tranh out of memory
	 * 
	 * @param pImagePath
	 *            : duong dan anh
	 */
	private void setPic(String pImagePath) {
		// Get the dimensions of the View
		int targetW = finalWidth;
		int targetH = finalHeight;

		// Get the dimensions of the bitmap
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pImagePath, bmOptions);
		int photoW = bmOptions.outWidth;
		int photoH = bmOptions.outHeight;

		// Determine how much to scale down the image
		int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

		// Decode the image file into a Bitmap sized to fill the View
		bmOptions.inJustDecodeBounds = false;
		bmOptions.inSampleSize = scaleFactor;
		bmOptions.inPurgeable = true;

		Bitmap bitmap = BitmapFactory.decodeFile(pImagePath, bmOptions);
		iv_unqualify.setImageBitmap(bitmap);
	}
}
