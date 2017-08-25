package com.viettel.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.ListView;

import com.viettel.database.Cat_Cause_Constr_UnQualifyController;
import com.viettel.database.entity.Cat_Cause_Constr_UnQualifyEntity;
import com.viettel.ktts.R;
import com.viettel.view.control.Cat_CauseUnqualifyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListCatCauseUnqualifyDialog extends Dialog {

	private ListView lv_listCatCauseUnqualify;
	private String sSubType = "PILLAR";
	private int iType = 1;

	public ListCatCauseUnqualifyDialog(final Context context) {
		super(context);
		setContentView(R.layout.list_cat_cause_unqualify_dialog);
		
		
		lv_listCatCauseUnqualify = (ListView) findViewById(R.id.lv_cat_cause_unqualify_dialog);
		
		List<Cat_Cause_Constr_UnQualifyEntity> listCauseUnqualify = new ArrayList<Cat_Cause_Constr_UnQualifyEntity>();
		Cat_Cause_Constr_UnQualifyController causeUnqualify = new Cat_Cause_Constr_UnQualifyController(context);
		listCauseUnqualify = causeUnqualify.getAllUnQualifyByName(sSubType, iType);
		
		Cat_CauseUnqualifyAdapter unqualifyAdapter = new Cat_CauseUnqualifyAdapter(
				context, listCauseUnqualify);
		
		lv_listCatCauseUnqualify.setAdapter(unqualifyAdapter);
	}

}
