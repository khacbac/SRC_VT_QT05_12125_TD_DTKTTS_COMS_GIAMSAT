package com.viettel.test;

import android.content.Context;

import com.viettel.database.Cat_Supervision_Constr_WorkController;
import com.viettel.database.Cat_Supv_Constr_MeasureController;
import com.viettel.database.EmployeeController;
import com.viettel.database.Ktts_KeyController;
import com.viettel.database.Product_CompanyController;
import com.viettel.database.entity.Cat_Supervision_Constr_WorkEntity;
import com.viettel.database.entity.EmployeeEntity;
import com.viettel.database.entity.Product_CompanyEntity;
import com.viettel.viettellib.json.me.JSONObject;

public class CreateDataTest {
	private Context mContext;

	public CreateDataTest(Context pContext) {
		this.mContext = pContext;
	}

	public void createData() {
		// Tao Key
		 Ktts_KeyController.getInstance().addDefaultTableKey("May001");
		// Tao du lieu
		 EmployeeController loginControll;
		 EmployeeEntity loginAcountAdd;
		 loginControll = new EmployeeController(this.mContext);
		/* Tao bang nguoi dung */
		 loginAcountAdd = new EmployeeEntity();
		 loginAcountAdd.setId(1);
		 loginAcountAdd.setUserName("admin");
		 loginAcountAdd.setPassWord("123456");
		 loginAcountAdd.setFullName("Hoang Tien Dat");
		 loginControll.addLogin(loginAcountAdd);
		JSONObject entry = new JSONObject();

		// Cat_Cause_Constr_UnQualifyController catCauseController = new
		// Cat_Cause_Constr_UnQualifyController(
		// mContext);
		// catCauseController.updateGetData(Cat_Cause_Constr_UnQualifyEntity
		// .parseJsonTest(entry));

		Product_CompanyController productController = new Product_CompanyController(
				mContext);
		productController.updateGetData(Product_CompanyEntity
				.parseJsonTest(entry));

		Cat_Supervision_Constr_WorkController cat_supvController = new Cat_Supervision_Constr_WorkController(
				mContext);
		cat_supvController.updateGetData(Cat_Supervision_Constr_WorkEntity
				.parseJsonTest(entry));
		Cat_Supv_Constr_MeasureController catsupvMesual = new Cat_Supv_Constr_MeasureController(
				mContext);
//		catsupvMesual.updateGetData(Cat_Supv_Constr_MeasureEntity
//				.parseJsonTest(entry));

		// Supervision_Constr_REQController supContrReqController = new
		// Supervision_Constr_REQController(
		// mContext);
		// supContrReqController.updateGetData(Supervision_Constr_REQEntity
		// .parseJsonTest(entry));
		//
		// Supervision_ConstrController supvConstrController = new
		// Supervision_ConstrController(
		// mContext);
		// supvConstrController.updateGetData(Supervision_ConstrEntity
		// .parseJsonTest(entry));
		//
		// Constr_ConstructionController constrController = new
		// Constr_ConstructionController(
		// this.mContext);
		// constrController.updateGetData(Constr_ConstructionEntity
		// .parseJsonTest(entry));

	}
}
