package com.mclr.mini.riesgosmovil.database.procedures;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.mclr.mini.riesgosmovil.database.DatabaseHandler;

import java.util.Calendar;

public class DatabaseProcedures {
	private DatabaseHandler db;
	private Context context;

	public DatabaseProcedures(Context contexto) {
		this.context = contexto;
		db = new DatabaseHandler(context);
	}

	public String getDescriptionFromRecordID(String RecordID){
		String query;
		String sDesc = "";

		query = "SELECT Description" +
				" FROM TBL_CatalogsContent" +
				" WHERE (_id = '" + RecordID + "')";

		Cursor cursor = db.getReadableDatabase().rawQuery(query, null);

		if(cursor != null) {
		    cursor.moveToFirst();
		}
		sDesc = cursor.getString(cursor.getColumnIndex("Description"));

		return sDesc;
	}

	public Cursor getPropertyChildren(String propertyID, String PropertyTypeID) {
		String query;

		query = "SELECT P.* ,F.FeatureID, CN._id As CatalogID, CN.CatalogName As CatalogName, CC._id As RecordID, CC.Description As Description, F.Value As Value" +
				" FROM TBL_PropertyFeatures F," + 
				"  (SELECT P._id As PropertyID, P.Name, P.PropertyUse As PropertyUse" + 
				"    FROM TBL_Properties P" +
				"    WHERE (PropertyParentID = '" + propertyID + "')" +
				"    AND (Propertytype = '" + PropertyTypeID + "')) P," +
				"  TBL_CatalogsNames CN," +
				"  TBL_CatalogsContent CC" +
				" WHERE (F._id = P.PropertyID)" +
				"  AND (F.FeatureID = CC._id)" +
				"  AND (CC.CatalogID = CN._id)" +
				" ORDER BY PropertyID";

		Cursor cursor = db.getReadableDatabase().rawQuery(query, null);

		if(cursor != null) {
		    cursor.moveToFirst();
		}
		return cursor;
	}

	public Cursor getPropertyLocation(String id, String postalCodeID) {
		String query;
		
		if (postalCodeID.equals("'00000000-0000-0000-0000-000000000000'")) {
			query = "Select P.*, S.StateID, StateCode, StateName, '''' As City,T.Value As CityHall,'''' As TownShip,''00000'' As PostalCode" +
					" From TBL_Properties P, TBL_CatalogsContent C1, TBL_PropertyFeatures F, TBL_States S, TBL_PropertyFeatures T" +
					" Where (P.PropertyID = F.PropertyID)" +
					" And (P.PropertyID = T.PropertyID)" +
					" And (F.FeatureID = C1.RecordID)" +
					" And (C1.RecordID = S.StateID)" +
					" And (T.FeatureID = (Select RecordID From TBL_CatalogsContent Where Description = ''TownName''))" +
					" And (P._id = '" + id + "')";
		} else {
			query = "Select P.*, PC.StateID, StateCode, StateName, PC.City, PC.CityHall, PC.Township, PC.PostalCode" +
					" From TBL_Properties P, TBL_PostalCodes PC, TBL_States S" +
					" Where (P.PostalCodeID = PC._id)" +
					" And (PC.StateID = S._id)" +
					" And (P._id = '" + id + "')";
		}
		Cursor cursor = db.getReadableDatabase().rawQuery(query, null);

		if(cursor != null) {
		    cursor.moveToFirst();
		}
		return cursor;
	}

	public Cursor getPropertyFeatures(String id) {
		String query = "Select F._id, CN._id AS CatalogID, CN.CatalogName, CC._id AS RecordID, CC.Description, F.Value" +
				" From TBL_PropertyFeatures F, TBL_CatalogsNames CN, TBL_CatalogsContent CC" +
				" Where (F.FeatureID = CC._id)" +
				" And (CC.CatalogID = CN._id)" +
				" And (F._id = '" + id + "')";

		Cursor cursor = db.getReadableDatabase().rawQuery(query , null);

		if(cursor != null) {
		    cursor.moveToFirst();
		}
		return cursor;
	}

	public Cursor getPropertyDamagesRisks(String id){
		String query = "Select DataType, ConceptID" +
				" From TBL_FormerDamageAndRisk" +
				" Where (_id = '" + id + "')";

		Cursor cursor = db.getReadableDatabase().rawQuery(query , null);

		if(cursor != null) {
		    cursor.moveToFirst();
		}
		return cursor;
	}

	public Cursor getPropertySiniestrality(String id){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("Select Year, Amount, Description");
		queryBuilder.append(" From TBL_AnnualAmount A");
		queryBuilder.append(" Where (A._id = '");
		queryBuilder.append(id);
		queryBuilder.append("')");
		queryBuilder.append(" And (A.year >= ");
		queryBuilder.append(year-10);
		queryBuilder.append(")");
		queryBuilder.append(" Order By A.Year Desc");
		String query = queryBuilder.toString();

		Cursor cursor = db.getReadableDatabase().rawQuery(query , null);

		if(cursor != null) {
		    cursor.moveToFirst();
		}
		return cursor;
	}

	public String getWeatherStationInfo(String id){
		String query;
		String sDesc = "";

		query = "Select W._id, StationName, StateName, Latitude, Longitude" +
				" From TBL_WeatherStations W, TBL_States S" +
				" WHERE (W._id = '" + id + "')" +
				" AND (W.StateID = S._id)" +
				" Order by StationName Asc";

		try
		{
			Cursor cursor = db.getReadableDatabase().rawQuery(query, null);
	
			if(cursor != null) {
			    cursor.moveToFirst();
			}
			sDesc = cursor.getString(cursor.getColumnIndex("StationName"));
		}
		catch (Exception e){
			Log.d("Error", "El mensaje de error es: " + e.getMessage());
		}

		return sDesc;
	}

	public Cursor getUser(String id) {
		String query = "Select * From TBL_Users" +
				" Where (_id = '" + id + "')";

		Cursor cursor = db.getReadableDatabase().rawQuery(query , null);

		try
		{
			if(cursor != null) {
			    cursor.moveToFirst();
			}
		}
		catch (Exception e){
			Log.d("Error", "El mensaje de error es: " + e.getMessage());
		}
		return cursor;
	}

	public Cursor getResult(){
		String query = "Select * From TBL_Users" +
				" Where (_id = 'XXX')";

		Cursor cursor = db.getReadableDatabase().rawQuery(query , null);

		try
		{
			if(cursor != null) {
			    cursor.moveToFirst();
			}
		}
		catch (Exception e){
			Log.d("Error", "El mensaje de error es: " + e.getMessage());
		}
		return cursor;
	}
}
