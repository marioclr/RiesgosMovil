package com.mclr.mini.riesgosmovil.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import com.mclr.mini.riesgosmovil.modelos.CatalogItem;
import com.mclr.mini.riesgosmovil.modelos.Property;
import com.mclr.mini.riesgosmovil.utilitarios.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mini on 05/06/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    //Ruta por defecto de las bases de datos en el sistema Android.
    private static String RUTA_BASE_DATOS = "/data/data/net.proyectosbeta.pruebabasedatos/databases/";
    // Nombre de la Base de Datos.
    private static String NOMBRE_BASE_DATOS = "prueba.db";
    // Version de la Base de Datos.
    private static final int VERSION_BASE_DATOS = 1;
    private SQLiteDatabase base_datos;
    private Context contexto;
    private boolean bInserta = false;

    private String SQL_CREATE_TABLE_TBL_AnnualAmount = "CREATE TABLE [TBL_AnnualAmount](" +
            "[_id] TEXT NOT NULL," +
            "[Year] [int] NOT NULL," +
            "[Amount] [float] NOT NULL," +
            "[Description] TEXT NULL" +
            ")";

    private String SQL_CREATE_TABLE_TBL_CatalogsContent = "CREATE TABLE [TBL_CatalogsContent](" +
            "[_id] TEXT PRIMARY KEY NOT NULL," +
            "[CatalogID] TEXT NULL," +
            "[ShortName] TEXT NULL," +
            "[Description] TEXT NOT NULL," +
            "[Lock] [int] NULL" +
            ")";

    private String SQL_CREATE_TABLE_TBL_CatalogsNames = "CREATE TABLE [TBL_CatalogsNames](" +
            "[_id] TEXT PRIMARY KEY NOT NULL," +
            "[CatalogName] TEXT NOT NULL," +
            "[MenuCategoryID] [int] NOT NULL," +
            "[Visible] [int] NOT NULL" +
            ")";

    private String SQL_CREATE_TABLE_TBL_FormerDamageAndRisk = "CREATE TABLE TBL_FormerDamageAndRisk(" +
            "[_id] TEXT NULL," +
            "[DataType] TEXT NOT NULL," +
            "[ConceptID] TEXT NULL" +
            ")";

    private String SQL_CREATE_TABLE_TBL_PostalCodes = "CREATE TABLE TBL_PostalCodes(" +
            "[_id] TEXT PRIMARY KEY NOT NULL," +
            "[StateID] TEXT NULL," +
            "[PostalCode] TEXT NULL," +
            "[Township] TEXT NULL," +
            "[TownshipKind] TEXT NULL," +
            "[CityHall] TEXT NULL," +
            "[City] TEXT NULL" +
            ")";

    private String SQL_CREATE_TABLE_TBL_Properties = "CREATE TABLE [TBL_Properties](" +
            "[_id] TEXT PRIMARY KEY," +
            "[PropertyType] TEXT ," +
            "[PropertyNumber] TEXT," +
            "[PropertyUse] TEXT," +
            "[Name] TEXT," +
            "[Address] TEXT," +
            "[Lock] int ," +
            "[Assigned] int ," +
            "[Finding] int ," +
            "[Status] int ," +
            "[RegisterDate] TEXT," +
            "[RegisterUser] TEXT," +
            "[Deleted] TEXT ," +
            "[Justification] TEXT ," +
            "[PropertyParentID] TEXT ," +
            "[PostalCodeID] TEXT ," +
            "[BuildingDate] TEXT ," +
            "[Levels] int" +
            ")";

    private String SQL_CREATE_TABLE_TBL_PropertyFeatures = "CREATE TABLE [TBL_PropertyFeatures] (" +
            "[_id] TEXT NULL," +
            "[FeatureID] TEXT NULL," +
            "[Value] TEXT NOT NULL" +
            ")";

    private String SQL_CREATE_TABLE_TBL_States = "CREATE TABLE [TBL_States] (" +
            "[_id] TEXT PRIMARY KEY NOT NULL," +
            "[StateName] TEXT NOT NULL," +
            "[DELETED] [int] NULL," +
            "[LOCKED] [int] NULL," +
            "[StateCode] TEXT NULL" +
            ")";

    private String SQL_CREATE_TABLE_TBL_Users = "CREATE TABLE [TBL_Users] (" +
            "[_id] TEXT PRIMARY KEY NOT NULL," +
            "[QrCode] TEXT NOT NULL," +
            "[UserName] TEXT NULL," +
            "[Password] TEXT NULL," +
            "[Active] int NULL," +
            "[Lock] int NULL," +
            "[Deleted] int NULL," +
            "[UserID] int NULL," +
            "[inUse] int NULL" +
            ")";

    private String SQL_CREATE_TABLE_TBL_WeatherStations = "CREATE TABLE [TBL_WeatherStations](" +
            "[_id] TEXT PRIMARY KEY NOT NULL," +
            "[StateID] TEXT NOT NULL," +
            "[StationName] TEXT NULL," +
            "[Latitude] TEXT NULL," +
            "[Longitude] TEXT NULL," +
            "[Altitude] TEXT NULL," +
            "[StartDate] TEXT NULL," +
            "[StationType] int NULL" +
            ")";

    public DatabaseHandler(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_Users);
            insertInto_TBL_Users(db);
        } catch(Exception e) {
            Log.d("Error en TBL_Users", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_AnnualAmount);
            if (bInserta) insertInto_TBL_AnnualAmount(db);
        } catch(Exception e) {
            Log.d("Error TBL_CataloContent", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_CatalogsContent);
            //insertInto_TBL_CatalogsContent(db);
            insertInto_TBL_CatalogsContent_VG(db);
        } catch(Exception e) {
            Log.d("Error TBL_CataloContent", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_CatalogsNames);
            //insertInto_TBL_CatalogsNames(db);
            insertInto_TBL_CatalogsNames_VG(db);
        } catch(Exception e) {
            Log.d("Error TBL_CatalogsNames", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_FormerDamageAndRisk);
            if (bInserta) insertInto_TBL_FormerDamageAndRisk(db);
        } catch(Exception e) {
            Log.d("Error TBL_CatalogsNames", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_PostalCodes);
            ioInsertaPostalCodes(db);
        } catch(Exception e) {
            Log.d("Error TBL_CatalogsNames", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_Properties);
            if (bInserta) insertInto_TBL_Properties(db);
        } catch(Exception e) {
            Log.d("Error en TBL_Properties", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_PropertyFeatures);
            if (bInserta) insertInto_TBL_PropertyFeatures(db);
        } catch(Exception e) {
            Log.d("Error TBL_CatalogsNames", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_States);
            insertInto_TBL_States(db);
        } catch(Exception e) {
            Log.d("Error TBL_CatalogsNames", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            db.execSQL(SQL_CREATE_TABLE_TBL_WeatherStations);
            insertInto_TBL_WeatherStations(db);
        } catch(Exception e) {
            Log.d("Error TBL_WeatherStatio", "El mensaje de error es: " + e.getMessage());
        }
        try
        {
            if (!bInserta) insertInto_TBLS_Aqu(db);
            if (!bInserta) insertInto_TBLS_Aqu1(db);

            if (!bInserta) insertInto_TBLS_Dam(db);
            if (!bInserta) insertInto_TBLS_Dam1(db);

            if (!bInserta) insertInto_TBLS_Edu(db);
            if (!bInserta) insertInto_TBLS_Edu1(db);

            if (!bInserta) insertInto_TBLS_Hea(db);
            if (!bInserta) insertInto_TBLS_Hea1(db);

            if (!bInserta) insertInto_TBLS_His(db);
            if (!bInserta) insertInto_TBLS_His1(db);

            if (!bInserta) insertInto_TBLS_Hyd(db);
            if (!bInserta) insertInto_TBLS_Hyd1(db);

            if (!bInserta) insertInto_TBLS_Urb(db);
            if (!bInserta) insertInto_TBLS_Urb1(db);

            if (!bInserta) insertInto_TBLS_Was(db);
            if (!bInserta) insertInto_TBLS_Was1(db);

            if (!bInserta) insertInto_TBLS_Way(db);
            if (!bInserta) insertInto_TBLS_Way1(db);

            if (!bInserta) insertInto_TBLS_Way1_Tunnel(db);
            if (!bInserta) insertInto_TBLS_Way_Tunnel(db);

            if (!bInserta) insertInto_TBLS_Way1_Puente(db);
            if (!bInserta) insertInto_TBLS_Way_Puente(db);

        } catch(Exception e) {
            Log.d("Error insInto_TBLS_Edu", "El mensaje de error es: " + e.getMessage());
        }
    }

    public void insertInto_TBL_AnnualAmount(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2013','1000000','evento hist 003')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2012','150000','evento edu 001')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2011','85000000','evento hist 002')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2010','999999','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2009','67600','evento 001')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D2193E0A-EFAE-4D27-BC02-DEE69452615D','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D2193E0A-EFAE-4D27-BC02-DEE69452615D','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D2193E0A-EFAE-4D27-BC02-DEE69452615D','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2013','300000','evento 3')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2012','180000','evento 1')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2009','78900','Evento 2')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('22A68057-B377-4825-B598-0E73FC90764D','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('3172F08D-6498-42E3-9F38-A3226C9132EC','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('4F5D6170-333A-4123-BEB3-FB62ED3B3F83','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2013','2.3e+006','evento 3')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2011','2.38e+007','Evento urbano 1')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C5E85927-D23A-4702-8442-F1691092157E','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('0FCBD422-F54C-491B-9DCF-2EFBB876E0B0','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('FA5D7646-EEBE-45C1-A9E5-42DC58C55D03','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2012','1e+006','evento 001')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('86F258B3-E168-4553-A551-EF9E1FF14F8A','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('04BB250B-3A9F-4D24-B334-E6B2EB8F537A','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2013','1e+006','evento 001 cáminos')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('179EF5CB-65B9-4422-8ACD-8A119E58A7A2','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2013','873000','evento 001')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2009','1.6e+007','evento 002 hidráulica')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('30846D73-E1FA-42D3-B8D5-7F19C586F7B0','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2013','2.5e+007','evento 001 hidráulica')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2009','1.6e+006','evento 002 hidráulica')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','2004','890000','evento 003 hidráulica')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2010','2.1e+007','evento clínica 001')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('23BE4066-EAFA-4D30-9AB5-E7D94F506081','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B7C6E3B1-995F-49AE-A20B-A7BD6363D9A8','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A624F52D-09C8-4B06-B555-4F0835BE5263','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2012','1.2e+007','Evento 001 BC')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2012','980000','evento 001 histórico')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2007','1e+007','evento 002 histórico')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','2004','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2013','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2012','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2011','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2010','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2009','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2008','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2007','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2006','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2005','0','')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C28F2DC7-8641-40BD-A6A3-24E8FF95A99D','2004','0','')");
    }

    public void insertInto_TBL_CatalogsContent_VG(SQLiteDatabase db){
        db.execSQL("insert into tbl_catalogsContent values('00000000-0000-0000-0000-000000000000','00000000-0000-0000-0000-000000000000','','Ninguno',0)");
        db.execSQL("insert into tbl_catalogsContent values('5C34B042-7F2C-4AED-B764-011F60BC1110','EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','','Muy accidentada. centro de la ciudad',1)");
        db.execSQL("insert into tbl_catalogsContent values('B4E5D36A-1D9B-443E-8D1D-013167C66E0E','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Depósitos o Tanques de retención',0)");
        db.execSQL("insert into tbl_catalogsContent values('3788CF80-8F9C-406F-BFE2-013857CC6AD6','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Longitud',0)");
        db.execSQL("insert into tbl_catalogsContent values('E4886740-2AA3-4DF7-8AD5-016E0A02E110','D2261320-26BD-4560-AD96-0135366285A3','AGU','Aguascalientes ',0)");
        db.execSQL("insert into tbl_catalogsContent values('40F910EA-B105-4A72-93BD-01EDAAA47939','EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','','Promontorio, colinas, montañas',0)");
        db.execSQL("insert into tbl_catalogsContent values('DF7C6571-FADE-4290-A3E5-01FA967E23EA','07C3B108-6581-4DA7-85BC-FF6263924E99','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('12F2C6E5-6026-48D3-B45C-024A396BB119','68A049E3-4B55-4F48-AE03-A6CB920028B8','','Laguna',0)");
        db.execSQL("insert into tbl_catalogsContent values('0D0F0F70-88D5-42EB-BFC7-02A067C5B4FD','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Puente principal',0)");
        db.execSQL("insert into tbl_catalogsContent values('FCBEBA65-5197-436B-9333-0303737A101B','15641A0F-51F0-4969-AD95-90EF456AFE13','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('15B81355-B8D8-4AA5-8141-030F824F9AA3','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','dsf','Dosificadores',0)");
        db.execSQL("insert into tbl_catalogsContent values('C72F6A69-6C38-4FAB-AE0D-038BDF7F9321','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Desfogues',0)");
        db.execSQL("insert into tbl_catalogsContent values('7567E762-5B28-4833-A61A-0418AB8B7CAE','2043ACDA-56F7-40CE-A853-233F470FCB76','','Agua potable, electricidad y drenaje',0)");
        db.execSQL("insert into tbl_catalogsContent values('C0CAC8CC-5B88-420C-94CF-045FB4266973','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Estación de bombeo',0)");
        db.execSQL("insert into tbl_catalogsContent values('B1C48FCD-BC79-47C3-951F-04689E2C63D3','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Ancho de vía',0)");
        db.execSQL("insert into tbl_catalogsContent values('E92609D1-815A-48D3-B5B8-049D3DE8E713','DE4815D5-E7B5-4C48-A694-180F351E1B41','E','Metálica a base de marcos tipo ´butler´ y largueros ´monten´',0)");
        db.execSQL("insert into tbl_catalogsContent values('E4D1D20F-AE2F-4DD3-9F66-04ADB9549B6F','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Enajenación en licitación por invitación',0)");
        db.execSQL("insert into tbl_catalogsContent values('F1C41B62-4F08-4050-A6BD-04EACC827E15','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Malla ciclónica',0)");
        db.execSQL("insert into tbl_catalogsContent values('CF7AEF27-292A-44CE-8628-052647F28468','12634A22-8918-4A1C-B2DC-BE4C827735CA','','Metal',0)");
        db.execSQL("insert into tbl_catalogsContent values('EEE9CBEB-A9F0-422D-9D4C-06A5152A58C1','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','L','Multipanel',0)");
        db.execSQL("insert into tbl_catalogsContent values('8B592765-8BAB-4388-A1B4-06C0DBD9ECB1','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','F','Cristal de 6mm. polarizado',0)");
        db.execSQL("insert into tbl_catalogsContent values('0B758DA7-0998-469D-A7E0-06F674E9FEB6','B374ABA0-9C03-4916-ADE8-0BA48161CE05','','Terracería',0)");
        db.execSQL("insert into tbl_catalogsContent values('8EC64169-4982-4D59-AB22-078E1D868C9F','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Talleres',0)");
        db.execSQL("insert into tbl_catalogsContent values('30F63AA1-7E28-4991-9050-07A2462C5F85','75B17471-ED30-467C-BA2A-8F8D1D988282','','Percipitación máxima mensual',0)");
        db.execSQL("insert into tbl_catalogsContent values('48A062CF-1DEF-41A8-B3C4-07C9F7965C08','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','Puente en ménsula',0)");
        db.execSQL("insert into tbl_catalogsContent values('83B8352C-8CB0-4ACE-BDE0-083B4B38471F','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','B','Aplanadas, acabado rustico, pintura vinílica',0)");
        db.execSQL("insert into tbl_catalogsContent values('F80BC205-AC04-4821-85E3-09306045B435','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Rural',0)");
        db.execSQL("insert into tbl_catalogsContent values('3F5770ED-26FF-40B0-85CF-0956BF285147','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Aliviadero',0)");
        db.execSQL("insert into tbl_catalogsContent values('62EE6E39-FAA0-43E7-A167-0976F2B7443B','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Geometría regular',0)");
        db.execSQL("insert into tbl_catalogsContent values('FA3307DE-ECE8-455B-B436-098DE5CCBACA','8EB882ED-6597-4111-ACD1-0DFF899B2BD8','','Aspersores',0)");
        db.execSQL("insert into tbl_catalogsContent values('BDD1C727-2C66-4E1E-82FC-09C38A554164','B504418E-8F8D-4086-8E59-CD615707E5FB','','Obras provisionales durante la construcción',0)");
        db.execSQL("insert into tbl_catalogsContent values('947076EC-48E0-4C26-9F88-0A49DE1970E3','7EB43883-335D-4184-86F3-D899EB2F6784','','Hormigón',0)");
        db.execSQL("insert into tbl_catalogsContent values('C9C512BE-E033-4C87-BE67-0A89E9B33D20','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Dispositivos de seguridad',0)");
        db.execSQL("insert into tbl_catalogsContent values('1086726A-37AE-43F1-AD77-0AABF2A3AE8D','E8E42A49-5874-4AE2-9070-8C15EE163201','','Otros',0)");
        db.execSQL("insert into tbl_catalogsContent values('8087E024-AC00-4B31-A678-0AD0E73061F3','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Casa de máquinas',0)");
        db.execSQL("insert into tbl_catalogsContent values('E9257515-B9F0-4FDC-858F-0B0FE2A8B14D','F751B0D0-97F8-402C-9065-077B5136B6D6','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('1CB48385-7879-4E03-B0AE-0B52F8DC7F1F','DB407EA2-A1BB-4254-87C7-6AF894A75AF4','','Distribución',0)");
        db.execSQL("insert into tbl_catalogsContent values('BE2FD0F8-68CD-4586-9055-0B66C33A1D2C','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Capacidad del cauce',0)");
        db.execSQL("insert into tbl_catalogsContent values('71823EDB-3947-4024-BE13-0B8352A2B713','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Enseres',0)");
        db.execSQL("insert into tbl_catalogsContent values('F3AF4BE3-1C0F-4DAE-AC10-0B92D827624D','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Nombre Común',0)");
        db.execSQL("insert into tbl_catalogsContent values('E3E9B5D8-66AF-49A5-8576-0B9F97A118B2','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Instalación sanitaria (saneamiento)',0)");
        db.execSQL("insert into tbl_catalogsContent values('51C8A233-0790-4DBE-9540-0BB3AA511FF3','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Instalaciones electromecánicas',0)");
        db.execSQL("insert into tbl_catalogsContent values('82CD4EBE-D257-400E-A607-0BD5D3A41CF4','EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','','Campo abierto plano',1)");
        db.execSQL("insert into tbl_catalogsContent values('07755B24-046D-44C6-BF1E-0BFBC03581CB','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Refuerzos',0)");
        db.execSQL("insert into tbl_catalogsContent values('A1A8691D-6BBE-47D1-A749-0D6C43FBBB6A','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Bombas',0)");
        db.execSQL("insert into tbl_catalogsContent values('70B12BA9-DB20-4665-8DA5-0DAA2D0D2AA9','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Diques',0)");
        db.execSQL("insert into tbl_catalogsContent values('997387A7-DE6C-4E3B-975D-0E5E63545843','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Embalse',0)");
        db.execSQL("insert into tbl_catalogsContent values('0D6FB893-959C-49FC-A0AF-0E874F41BDEB','B1137091-9300-4989-BE0F-4D38B9C543C6','','Campos deportivos',0)");
        db.execSQL("insert into tbl_catalogsContent values('664B3CC3-031D-4CB6-8C66-0E9AF34465F0','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','NumPasos subterraneos',0)");
        db.execSQL("insert into tbl_catalogsContent values('05CCD55D-B4C8-4903-BA01-0E9E6AB32DBF','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Canales',0)");
        db.execSQL("insert into tbl_catalogsContent values('6E2B3EB1-E70D-43D3-83C4-0F70DDC11BB6','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Primaria',0)");
        db.execSQL("insert into tbl_catalogsContent values('24DDF93E-A50A-4296-B248-0F9910098A33','D2261320-26BD-4560-AD96-0135366285A3','BCN','Baja California ',0)");
        db.execSQL("insert into tbl_catalogsContent values('699DEBA9-416D-4BAD-81C3-104823FD6801','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Enajenación en licitación pública',0)");
        db.execSQL("insert into tbl_catalogsContent values('947273D5-0A4E-45C7-86AD-10F7848A195B','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','','Tanques apoyados en el suelo',0)");
        db.execSQL("insert into tbl_catalogsContent values('3002ECE2-0C0B-49FE-BFB6-110B47BE2B91','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Antenas parabolicas',0)");
        db.execSQL("insert into tbl_catalogsContent values('224BD51D-2007-4452-A26F-112EAC580DC5','F751B0D0-97F8-402C-9065-077B5136B6D6','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('4261EE86-3A7A-43A6-BE02-116A12C78499','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Otros medios de almacenamiento de información.',0)");
        db.execSQL("insert into tbl_catalogsContent values('53454B12-5024-459D-9A02-118AA074CDF4','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Obras de drenaje',0)");
        db.execSQL("insert into tbl_catalogsContent values('008F0F4F-37F7-472D-BF61-11EFE2B077E4','3585818D-7660-4569-A24F-F5F0999D4705','D','Pilotes de concreto y losa de cimentación de concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('41C333F3-1A40-471D-B997-121D0B472E37','15641A0F-51F0-4969-AD95-90EF456AFE13','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('8C3802A8-8BE2-4DEF-BA88-126E5FAB954C','E8E42A49-5874-4AE2-9070-8C15EE163201','','Material ligero',0)");
        db.execSQL("insert into tbl_catalogsContent values('DAB02A9A-8A91-463F-BA49-129A0E6E1389','D2261320-26BD-4560-AD96-0135366285A3','BCS','Baja California Sur ',0)");
        db.execSQL("insert into tbl_catalogsContent values('B03ADF5D-634F-4B09-8C91-12B1E9D8302D','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Aspersores',0)");
        db.execSQL("insert into tbl_catalogsContent values('18B65E0C-7C41-480A-8D19-130624114EF7','D2261320-26BD-4560-AD96-0135366285A3','CAM','Campeche ',0)");
        db.execSQL("insert into tbl_catalogsContent values('0BC41175-42AF-4305-845A-1463D6E48290','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Balsas',0)");
        db.execSQL("insert into tbl_catalogsContent values('046A83CE-043B-41AD-A7D5-148BDD788E1D','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red de conducción de agua',0)");
        db.execSQL("insert into tbl_catalogsContent values('50201AC6-2234-484E-8D1D-1512E394EF5E','DE4815D5-E7B5-4C48-A694-180F351E1B41','G','De madera en columnas, armaduras y largueros',0)");
        db.execSQL("insert into tbl_catalogsContent values('09636565-3915-4200-98BD-15B80C86DCA1','F751B0D0-97F8-402C-9065-077B5136B6D6','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('9CE95B9F-CAAA-49BE-9019-16C624CF2053','443523EE-F532-4577-956B-64E55A7B89BB','','Almeja',0)");
        db.execSQL("insert into tbl_catalogsContent values('132637B9-C82D-4193-ADF8-16E5C5D7CD3D','B504418E-8F8D-4086-8E59-CD615707E5FB','','Vertedero o aliviadero',0)");
        db.execSQL("insert into tbl_catalogsContent values('171E7A87-8DD4-44F4-9091-17552ACB1389','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Equipos de cómputo',0)");
        db.execSQL("insert into tbl_catalogsContent values('045DE8F3-FB18-46F0-B783-1771D662E85C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Descripción de edificio',0)");
        db.execSQL("insert into tbl_catalogsContent values('EF110FF3-70FA-4A9F-8699-17D762F38C33','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','P','Libre',0)");
        db.execSQL("insert into tbl_catalogsContent values('2EBA0130-7CAF-47AD-BD24-19DEF9B073EC','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','vgr2','Desc V2',0)");
        db.execSQL("insert into tbl_catalogsContent values('E56C9FA2-3F1A-4B35-B5B4-19FC38CAD43C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Num Tuneles',0)");
        db.execSQL("insert into tbl_catalogsContent values('5593FE07-598E-46E9-B97A-1A5E10A3FD43','EA766519-648F-41FB-B429-A21F94758AE8','','Otros',0)");
        db.execSQL("insert into tbl_catalogsContent values('ECB19424-C020-4381-B7F7-1A892CAD84F0','D2261320-26BD-4560-AD96-0135366285A3','COA','Chiapas ',0)");
        db.execSQL("insert into tbl_catalogsContent values('5657B098-8A82-4AA4-AD57-1A9CFD508154','1ACD8B10-3A71-4481-ABEA-70E91B70B53A','','Suelos altamente orgánicos (referidos como ´turba´).',0)");
        db.execSQL("insert into tbl_catalogsContent values('C2CA920B-6C45-41D2-9FB9-1B9A44B46411','D2261320-26BD-4560-AD96-0135366285A3','COL','Chihuahua ',0)");
        db.execSQL("insert into tbl_catalogsContent values('A477075C-A544-44B1-B050-1BC0CD6D6F41','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Canal de aproximación',0)");
        db.execSQL("insert into tbl_catalogsContent values('1905CF7B-C8D3-4F79-B58F-1BEBE7597B4D','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Cunetas, Rígolas y Caces',0)");
        db.execSQL("insert into tbl_catalogsContent values('4FFF7B5D-90C2-4B92-848D-1BEF72520E1E','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Puente',0)");
        db.execSQL("insert into tbl_catalogsContent values('FFF576F7-3CD3-4355-ADBF-1CD7C2B3B3E0','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Enseres',0)");
        db.execSQL("insert into tbl_catalogsContent values('1137AE2A-8473-4BF1-98D0-1D1500A0212C','4F217F72-98B8-445F-AF8C-396B2B06671B','F','En cercas a base se malla tipo ciclon',0)");
        db.execSQL("insert into tbl_catalogsContent values('3329062C-56FE-4732-9B5F-1D8440D721D9','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Desvio temporal',0)");
        db.execSQL("insert into tbl_catalogsContent values('53D5F71D-0077-40A7-8B02-1DB537F3D01D','B5A4C0E7-FF66-4988-A957-F8FC519E2895','','Compuerta',0)");
        db.execSQL("insert into tbl_catalogsContent values('B8357F96-9512-44D2-87D8-1E30377CE17D','81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','','Elementos sin trabar',1)");
        db.execSQL("insert into tbl_catalogsContent values('B134137B-182D-4E36-A126-1F307C170386','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('E07B24E8-33AF-4C60-8326-1F7B3B419FB6','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Malla ciclónica',0)");
        db.execSQL("insert into tbl_catalogsContent values('620241C8-FDAB-4984-8D67-202C5B60D39E','241FA096-9264-4150-AF9E-C9694D5E66C2','','Primarias',0)");
        db.execSQL("insert into tbl_catalogsContent values('55809A44-27C5-41FE-BEF9-20404DBE0475','7EB43883-335D-4184-86F3-D899EB2F6784','','Otro(s)',0)");
        db.execSQL("insert into tbl_catalogsContent values('8D92370B-0B0F-4EA4-AC14-20416707EF6F','0E28A695-8644-4E13-92D1-F32EAEEEA29A','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('8EBEB4B7-4BC9-491A-B387-20B31F4E6672','2043ACDA-56F7-40CE-A853-233F470FCB76','','Cerca perimetral',0)");
        db.execSQL("insert into tbl_catalogsContent values('39DFF07F-C2DD-4F2B-AED2-20C330A1BBFD','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones en arena y grava',0)");
        db.execSQL("insert into tbl_catalogsContent values('B0D85803-E7BA-44F0-BDC5-211458B63132','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Cámaras de succión',0)");
        db.execSQL("insert into tbl_catalogsContent values('89F0CF88-CA27-4D9A-9734-2198E0DAD25D','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Monto presupuestado destinado al mantenimiento correctivo',0)");
        db.execSQL("insert into tbl_catalogsContent values('089F3A9C-558F-427D-ADA0-21DE453E47BF','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Instalaciones electromecánicas',0)");
        db.execSQL("insert into tbl_catalogsContent values('8B541FEF-B43B-48C0-99A5-2297F6121470','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Comentarios',0)");
        db.execSQL("insert into tbl_catalogsContent values('77CAFAA0-4AC3-4F38-9A09-22B00BBFE2E1','F3244557-C16D-472E-BC31-CA403BE81DA0','','Techo en forma de sierra',0)");
        db.execSQL("insert into tbl_catalogsContent values('CD8C4F92-3C0D-4B00-BACA-22BFD8FD1B47','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','B','Medio doble translucido',1)");
        db.execSQL("insert into tbl_catalogsContent values('BA97FAB2-D490-45B3-A92D-2300541BC757','D2261320-26BD-4560-AD96-0135366285A3','CHP','Coahuila',0)");
        db.execSQL("insert into tbl_catalogsContent values('A556770F-1EA6-4CF2-B876-2302B7DC543E','68A049E3-4B55-4F48-AE03-A6CB920028B8','','Mar',0)");
        db.execSQL("insert into tbl_catalogsContent values('4425812C-B492-4149-B284-23301C5B8013','539D1830-90A8-484C-B183-F79A448CEC78','','A.4. abanico',0)");
        db.execSQL("insert into tbl_catalogsContent values('3B1E376C-2C95-4127-A650-234462BD3BCA','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros medianos no rigidizada',0)");
        db.execSQL("insert into tbl_catalogsContent values('43B146CF-B958-4CF2-8038-2369ACFB5D5C','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Donado a beneficencia pública o privada',0)");
        db.execSQL("insert into tbl_catalogsContent values('BA1A74D2-F64C-4F5D-92A4-2463A43B096F','2043ACDA-56F7-40CE-A853-233F470FCB76','','Franja de amortiguamiento (mínimo 10 metros)',0)");
        db.execSQL("insert into tbl_catalogsContent values('1FA322BB-016C-4147-A3A3-2470B9128BDA','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','J','Cristal opaco',0)");
        db.execSQL("insert into tbl_catalogsContent values('95B91081-AF09-4D5E-9541-2483D9AFC90D','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Muebles',0)");
        db.execSQL("insert into tbl_catalogsContent values('8402AAF5-ABC1-4A64-A6F9-258FA08A161D','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('802467BB-C972-4B83-ABDA-261E05B696B0','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Placas',0)");
        db.execSQL("insert into tbl_catalogsContent values('E69F8708-B7C3-41A3-B792-2674213103A1','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','','Control de azolves',0)");
        db.execSQL("insert into tbl_catalogsContent values('04C4A44E-B1C9-4FAD-AC58-26B97004BCD1','2043ACDA-56F7-40CE-A853-233F470FCB76','','Vestidores y servicios sanitarios',0)");
        db.execSQL("insert into tbl_catalogsContent values('21CAACCE-0DAF-4132-A283-26E51FBFDCAF','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Altura',0)");
        db.execSQL("insert into tbl_catalogsContent values('CC6738FD-3B22-4975-8796-26EB03B460D0','7EB43883-335D-4184-86F3-D899EB2F6784','','Enrocamiento',0)");
        db.execSQL("insert into tbl_catalogsContent values('76C18DB1-DA2C-4B31-BC08-26F42D964407','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Casa de máquinas',0)");
        db.execSQL("insert into tbl_catalogsContent values('277476A8-F5EF-480B-9EA1-2730FF6698BB','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor promedio estimado de reconstrucción por km',0)");
        db.execSQL("insert into tbl_catalogsContent values('1BBF168C-6340-4360-92A5-2765715A3F5B','0D4FEBCD-D28A-4293-9065-A5A906BF6E69','','De 5 a 10m',0)");
        db.execSQL("insert into tbl_catalogsContent values('B7F23120-DFCF-47A4-A9A1-27888D5FA3EB','4F1CFD2A-E24E-4AD5-B2C6-FC20367D0FFC','','Corriente estacional',0)");
        db.execSQL("insert into tbl_catalogsContent values('BC7003E5-34F6-40A9-965A-27A4B4AB1B51','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Túnel',0)");
        db.execSQL("insert into tbl_catalogsContent values('C847ACC9-DE59-4E73-B1DA-27ABCE9CBB0A','1E1DA417-5EC6-4A03-A13E-08EB93D15E77','','Con tubería a presión',0)");
        db.execSQL("insert into tbl_catalogsContent values('ED76BA38-EAA1-4DED-9C65-27EC963AD10F','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Tragantes',0)");
        db.execSQL("insert into tbl_catalogsContent values('25839726-9C31-43DE-8653-28588F1BD5AE','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','K','Cristal reflectasol',0)");
        db.execSQL("insert into tbl_catalogsContent values('2794E46F-4FC7-4291-9A98-292AD27DEF16','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Colectores',0)");
        db.execSQL("insert into tbl_catalogsContent values('DD195E35-6AAB-4579-BF76-295CBCB0C2B5','54DA8427-56C1-4EC7-A258-FBA21E9303D2','','Otro',0)");
        db.execSQL("insert into tbl_catalogsContent values('5181022D-9B65-4002-8BB3-2A1C944CAF6F','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Consultorios médicos',0)");
        db.execSQL("insert into tbl_catalogsContent values('C0939295-E05F-43EE-A96B-2A94755A20ED','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Gasto máximo registrado',0)");
        db.execSQL("insert into tbl_catalogsContent values('E74C8ECF-3DFE-4CFA-A837-2B01DC233FB9','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Vertederos',0)");
        db.execSQL("insert into tbl_catalogsContent values('8509787F-61BC-4049-A9D7-2BC38ECEF602','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Camino',0)");
        db.execSQL("insert into tbl_catalogsContent values('93FF0681-8D45-470A-8BBC-2C46250CB550','D2261320-26BD-4560-AD96-0135366285A3','CHH','Colima ',0)");
        db.execSQL("insert into tbl_catalogsContent values('FD7DEEA1-39CB-43B7-9147-2C9459029608','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('3A6F52AB-B1C2-43AB-98AC-2CD6914830B0','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Instalaciones especiales',0)");
        db.execSQL("insert into tbl_catalogsContent values('579F377A-54FD-444F-960B-2D06E3410775','3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','','Roca volcanica',0)");
        db.execSQL("insert into tbl_catalogsContent values('97D1E7C0-38BE-4BBD-80D2-2D211DFD6216','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('E925EC90-A5C3-4D25-9CB0-2D375A34320B','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','C','Aplanadas, acabado rustico, pintura esmalte',0)");
        db.execSQL("insert into tbl_catalogsContent values('B4CEFB51-267A-4DB4-AB05-2D37E792AAF3','3585818D-7660-4569-A24F-F5F0999D4705','F','Pilas de concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('3E81DF69-5B7D-47A2-803B-2EDC1F3C7E6F','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red electromecánica',0)");
        db.execSQL("insert into tbl_catalogsContent values('C3F1E804-234B-42B2-8DF8-2F2E23BCD272','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Abandonado',0)");
        db.execSQL("insert into tbl_catalogsContent values('AD12C70A-C355-42A1-B229-2FB8B57302B6','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Error de captura',0)");
        db.execSQL("insert into tbl_catalogsContent values('2EAF0764-13E0-47E1-9A2F-2FCEDCAEBE10','E43CE3ED-6F48-4668-ABF3-222A8533005B','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('0C3BD0E9-FA09-48F9-BFE6-2FF1173C53C0','3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','','Relleno artificial',0)");
        db.execSQL("insert into tbl_catalogsContent values('97375940-E1E4-45DE-A269-301EAED38029','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','G','Block de barro hueco',0)");
        db.execSQL("insert into tbl_catalogsContent values('81E07212-E7FE-43EB-858A-30B29EF3356A','5F7C9D4C-B206-4791-B14B-328E40FA25E2','','Riego',0)");
        db.execSQL("insert into tbl_catalogsContent values('51C59D8A-9902-491F-A7A7-31733DC4A039','B504418E-8F8D-4086-8E59-CD615707E5FB','','Enservaderas',0)");
        db.execSQL("insert into tbl_catalogsContent values('9592CF5A-8854-43FE-BBD9-32DED763D407','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Controles de nivel del agua en el canal',0)");
        db.execSQL("insert into tbl_catalogsContent values('80C32CBD-155F-45D3-9994-32EF4B2BE2DC','6DAC2E76-3A32-401C-BBEB-1CC3CDA7E428','','Canal de riego o de drenaje con caminos rurales --> alcantarilla o puente.',0)");
        db.execSQL("insert into tbl_catalogsContent values('DC09E0A9-1040-4080-BB58-33061D1C1699','D2261320-26BD-4560-AD96-0135366285A3','DUR','Durango ',0)");
        db.execSQL("insert into tbl_catalogsContent values('8521DE27-2162-4D74-964E-337FF2B06890','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Reservorios',0)");
        db.execSQL("insert into tbl_catalogsContent values('947DD835-305C-40F1-A93F-34B294F743E1','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Extensión',0)");
        db.execSQL("insert into tbl_catalogsContent values('97635CAF-308A-43FF-BB56-34B54B168927','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Rehabilitación de estanques',0)");
        db.execSQL("insert into tbl_catalogsContent values('0FBE1C9F-9B2C-45FE-B024-35F86EB7AC5B','B504418E-8F8D-4086-8E59-CD615707E5FB','','Descarga de fondo',0)");
        db.execSQL("insert into tbl_catalogsContent values('392C312A-E42B-42EE-ACB3-363DB08924CE','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Terreno',0)");
        db.execSQL("insert into tbl_catalogsContent values('DAE30B59-3C4A-431F-BED1-3664491E9929','76A8D090-EB15-4600-ADB5-9F68043E46A5','','Vía rapida',0)");
        db.execSQL("insert into tbl_catalogsContent values('3F5BF841-D8A0-490F-8040-36802F452433','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Frontera sur',0)");
        db.execSQL("insert into tbl_catalogsContent values('56BA60CA-CD09-40B0-BDC0-36C6C02DBC4D','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Subestación y ductos eléctricos',0)");
        db.execSQL("insert into tbl_catalogsContent values('BEF0166B-EE4B-442E-B23F-37109D13DC63','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Rehabilitación de estanques',0)");
        db.execSQL("insert into tbl_catalogsContent values('65844E11-AE6B-4ADF-B9F8-378E185105F8','443523EE-F532-4577-956B-64E55A7B89BB','','Otros (especificar)',0)");
        db.execSQL("insert into tbl_catalogsContent values('F9A21F90-E696-47D0-9FB0-37AA39B7F24B','967615A4-74A4-40C3-B276-4EFCBDE63A48','FED','Gobierno Municipal',0)");
        db.execSQL("insert into tbl_catalogsContent values('7D7A9721-2B8F-4DF5-8C09-37DC1833C6E8','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Equipos electrónicos auxiliares',0)");
        db.execSQL("insert into tbl_catalogsContent values('0902CD92-3142-44CD-9E19-3825ABD1526A','C52AF614-2865-4708-B489-1BE720793BE6','','F.     presa de contrafuertes de cabeza redonda',0)");
        db.execSQL("insert into tbl_catalogsContent values('B6583E84-A321-401B-98B6-38419935EA61','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','A','Sencillo translucido',0)");
        db.execSQL("insert into tbl_catalogsContent values('3F7D1163-E3C0-4C20-9B45-38BCDFD99F26','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Región CNA',0)");
        db.execSQL("insert into tbl_catalogsContent values('F59B4C4F-620D-472F-9FD9-38D23F1C25B9','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','','Áreas de espera a la entrada y salida de la esclusa',0)");
        db.execSQL("insert into tbl_catalogsContent values('830E087E-07FA-48FD-A96D-38FD71642A48','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen al NAMINO',0)");
        db.execSQL("insert into tbl_catalogsContent values('0A555B6E-9E34-47A5-B0D1-3928226C93DA','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Edificio',0)");
        db.execSQL("insert into tbl_catalogsContent values('B1E28593-F69A-426A-8ECF-393E42FEC30D','539D1830-90A8-484C-B183-F79A448CEC78','','A.2. descarga directa',0)");
        db.execSQL("insert into tbl_catalogsContent values('4E90F1ED-9FEF-4973-8604-39425C6C4049','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Malla ciclónica',0)");
        db.execSQL("insert into tbl_catalogsContent values('ACAD85F1-A100-488A-84F3-3974A5DBB773','443523EE-F532-4577-956B-64E55A7B89BB','','Dorado',1)");
        db.execSQL("insert into tbl_catalogsContent values('63BB6F73-DB9B-4E5D-98CD-39C9E553B22F','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Cárcamos',0)");
        db.execSQL("insert into tbl_catalogsContent values('069942C1-2FCC-4ABA-956C-3A2EE69C6C68','D2261320-26BD-4560-AD96-0135366285A3','GUA','Guanajuato ',0)");
        db.execSQL("insert into tbl_catalogsContent values('83A622BA-EF89-4777-B347-3A47334FAA45','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Cuerpo de presa',0)");
        db.execSQL("insert into tbl_catalogsContent values('614CA928-A142-4F1C-A8A4-3B5B86C237A4','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros pequeños rigidizada',0)");
        db.execSQL("insert into tbl_catalogsContent values('371782BB-BDB2-471C-9F08-3B91EC256234','12634A22-8918-4A1C-B2DC-BE4C827735CA','','Material ligero',1)");
        db.execSQL("insert into tbl_catalogsContent values('45D10F58-B28B-4214-AA93-3BAE71D61D47','6CB19E01-D41B-4052-9C10-BC5360DD22FE','roc','Roca',0)");
        db.execSQL("insert into tbl_catalogsContent values('11D62DA7-CE9B-4AFD-BE40-3CAE6CB4D89B','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','G','Cristal de 9mm. polarizado',0)");
        db.execSQL("insert into tbl_catalogsContent values('BF8930EC-F48B-44A5-8173-3CCCC8071A7C','20F41507-5058-466C-AD1E-1819B8A6207F','','Elevación',0)");
        db.execSQL("insert into tbl_catalogsContent values('18D18212-90A6-4DB3-A770-3CD014CD1227','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Dispositivos de desinfección',0)");
        db.execSQL("insert into tbl_catalogsContent values('8AF49775-D99F-4AFF-91D5-3CF7CE53BF26','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('D0A962B1-C77A-4777-A409-3D7B2095DFCE','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Inventarios libros',0)");
        db.execSQL("insert into tbl_catalogsContent values('EE05D8D9-D260-4EFE-A76C-3D7FA49B6594','EA50619F-3012-4841-8939-BB83634FCF9C','','Intermedia',0)");
        db.execSQL("insert into tbl_catalogsContent values('7E209574-F231-4AC9-83AF-3DB3B9B6CCC7','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Compuerta de entrada',0)");
        db.execSQL("insert into tbl_catalogsContent values('6287ECE5-8BEA-493C-A860-3DB770460C1B','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red de conducción de agua (obras de conducción y distribución)',0)");
        db.execSQL("insert into tbl_catalogsContent values('819A106D-73B3-48C1-95EA-3DBB96A693DB','94E45704-8045-4CF8-B747-763D600789C4','','Capacidad de 50 a 100 toneladas por día',0)");
        db.execSQL("insert into tbl_catalogsContent values('7486C853-EF38-4E04-91EF-3DD336D6C151','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Carretera',0)");
        db.execSQL("insert into tbl_catalogsContent values('C19EC910-1500-47AC-921F-3DF631DBB667','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Cauces de alivio',0)");
        db.execSQL("insert into tbl_catalogsContent values('DA311670-94FF-4692-98A2-3DFB9802408E','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Fecha de refuerzo',0)");
        db.execSQL("insert into tbl_catalogsContent values('6750A007-0F9A-47C2-B744-3E2B8BB44DBE','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen útil',0)");
        db.execSQL("insert into tbl_catalogsContent values('DF2A44B6-93E4-484C-B3A2-3E731EBB0366','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red sanitaria',0)");
        db.execSQL("insert into tbl_catalogsContent values('120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','D2261320-26BD-4560-AD96-0135366285A3','GRO','Guerrero ',0)");
        db.execSQL("insert into tbl_catalogsContent values('713C2CCC-B552-4E81-B0A5-3EE5D055A0BC','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Presa',0)");
        db.execSQL("insert into tbl_catalogsContent values('ED6EC14E-47FE-436A-B619-3F5C73CBF41F','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','','Poca',0)");
        db.execSQL("insert into tbl_catalogsContent values('71BE3081-6D8F-46D1-AD6D-3FF54A30AAA3','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Península de yucatán',0)");
        db.execSQL("insert into tbl_catalogsContent values('F745F6CE-4A71-4426-8943-3FFCC219D38D','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Taludes aguas abajo',0)");
        db.execSQL("insert into tbl_catalogsContent values('427D821A-1386-4C68-8DB6-4046DDC7A239','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Instrumental médico',0)");
        db.execSQL("insert into tbl_catalogsContent values('65C08A09-51C8-4863-A43F-40AC7CDAB503','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Taludes aguas arriba',0)");
        db.execSQL("insert into tbl_catalogsContent values('26D9F7E2-53EE-4E61-B3E4-40B6C3CCF2C6','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('EDE30602-EC27-4D8C-B40E-4152C636E456','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('E264210A-287A-47CC-B40F-416324BC02ED','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Rehabilitación de estanques',0)");
        db.execSQL("insert into tbl_catalogsContent values('99B256E2-2A6A-4177-93D1-41649F379220','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red de alimentación (obras de conducción y distribución)',0)");
        db.execSQL("insert into tbl_catalogsContent values('D80E79B8-4248-44D1-A085-41F5DD46700B','0D4FEBCD-D28A-4293-9065-A5A906BF6E69','','< 5m',0)");
        db.execSQL("insert into tbl_catalogsContent values('7DB6DDC4-2BAD-4374-9902-4238C74FEBAE','A232C4C1-5524-4302-9329-F69D050F9698','','Escuela',0)");
        db.execSQL("insert into tbl_catalogsContent values('0702B2DD-4F96-48ED-B0CC-4242F6D45814','443523EE-F532-4577-956B-64E55A7B89BB','','Trucha',1)");
        db.execSQL("insert into tbl_catalogsContent values('568AEF5E-5B44-4F74-9440-4313D984592F','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Cuencas centrales del norte',0)");
        db.execSQL("insert into tbl_catalogsContent values('037702C4-72AB-49D8-B124-433CE827A512','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Carta INEGI',0)");
        db.execSQL("insert into tbl_catalogsContent values('BE7DCBE2-5B33-4ADF-9521-434B378E72AC','D2261320-26BD-4560-AD96-0135366285A3','HID','Hidalgo ',0)");
        db.execSQL("insert into tbl_catalogsContent values('181E092C-025C-42DE-AF91-440262209F60','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Equipos electrónicos auxiliares',0)");
        db.execSQL("insert into tbl_catalogsContent values('C084285A-B1D3-4190-8D7A-44996075C029','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor maquinara y equipo',0)");
        db.execSQL("insert into tbl_catalogsContent values('AE3B3625-732A-427A-AE98-45535B98BD19','0E28A695-8644-4E13-92D1-F32EAEEEA29A','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('6697439F-53AB-4068-B4CC-4571B628EFDC','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','L','Libre',0)");
        db.execSQL("insert into tbl_catalogsContent values('F59F337D-84D6-4837-8AF8-4597A8DC03F7','DE4815D5-E7B5-4C48-A694-180F351E1B41','F','Metálica a base de marcos con perfiles ´monten´',0)");
        db.execSQL("insert into tbl_catalogsContent values('6E48CA3E-D61E-4E9B-B9A5-45A7B178364C','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Nivel de corriente',0)");
        db.execSQL("insert into tbl_catalogsContent values('F67769ED-C9E3-4250-8407-4704D50B13E9','7EB43883-335D-4184-86F3-D899EB2F6784','','Mampostería',0)");
        db.execSQL("insert into tbl_catalogsContent values('59C22422-BA3B-483C-8B11-474770CA64E8','15641A0F-51F0-4969-AD95-90EF456AFE13','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('CF31FB59-9007-4D32-9752-4801A1AA2876','464A3F38-B8B5-4828-871B-D717885AE5CB','','Bocatomas',0)");
        db.execSQL("insert into tbl_catalogsContent values('D26C6275-8F48-4F07-8CCF-488B90570ACE','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','De arco en concreto',0)");
        db.execSQL("insert into tbl_catalogsContent values('226E9B9F-4023-4576-B060-48A52BE444D7','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen máximo de escurrimiento anual',0)");
        db.execSQL("insert into tbl_catalogsContent values('524CEF90-B61D-4064-99D0-4A6EC89C97B5','C52AF614-2865-4708-B489-1BE720793BE6','','C.     presa de gravedad',0)");
        db.execSQL("insert into tbl_catalogsContent values('EB6ADFB7-016C-4E80-8EDF-4AB87DCBD9EA','B56CE9B9-A793-4C6E-995D-EB7D6490F71E','','Mucha',0)");
        db.execSQL("insert into tbl_catalogsContent values('12D752E8-4F17-40D6-83FA-4B1243B598A7','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','','Nula',0)");
        db.execSQL("insert into tbl_catalogsContent values('2E850C3C-1B15-460C-B334-4B2C0605C0E7','B4C32095-C18C-4448-BB13-7725B88EF351','','Otro',0)");
        db.execSQL("insert into tbl_catalogsContent values('8445FB89-1B78-41EA-A767-4B3AE36B45EC','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','N','Celosias de barro',0)");
        db.execSQL("insert into tbl_catalogsContent values('40B4B773-BA6E-48DC-910B-4B3F89A25372','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Golfo norte',0)");
        db.execSQL("insert into tbl_catalogsContent values('9E78DD24-2DC0-4991-AF15-4C18A66AD3F1','E9D156CD-19DE-43F1-8B17-0F33227A90B2','','Rodantes',0)");
        db.execSQL("insert into tbl_catalogsContent values('98B72092-0088-4AC3-AABC-4C18B811B315','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red sanitaria (saneamiento)',0)");
        db.execSQL("insert into tbl_catalogsContent values('0513315E-23F1-4A01-8A29-4C3EE96B8735','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Puentes',0)");
        db.execSQL("insert into tbl_catalogsContent values('24ED8566-FB18-46E9-98A6-4C7CA3FFFA57','0E28A695-8644-4E13-92D1-F32EAEEEA29A','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('3479F77A-1A10-4BE2-BFA3-4CEF9CFD7E42','0FD32189-1390-4CB3-9047-92F3EF391E1C','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('37E088BE-8C24-4F43-BACC-4E3C9A3C918E','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Tiene Domos',0)");
        db.execSQL("insert into tbl_catalogsContent values('7EA618BC-FF9D-4955-A16A-4E49DFE0ADD8','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','','Canales de llenado y vaciado',0)");
        db.execSQL("insert into tbl_catalogsContent values('BFDBDC7F-10A0-4A33-AC7D-4E624FAA9AFB','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Motores',0)");
        db.execSQL("insert into tbl_catalogsContent values('18434229-1950-431B-BA13-4EA38F5D96BD','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Ancho de la corona',0)");
        db.execSQL("insert into tbl_catalogsContent values('EE4282A4-33DB-426B-AFA7-4EB32074384A','D2261320-26BD-4560-AD96-0135366285A3','JAL','Jalisco ',0)");
        db.execSQL("insert into tbl_catalogsContent values('AE012E0E-C281-4843-941D-4F7685C897F3','37CE79B4-15EB-4BF3-892F-897223CFD12E','','Ancho de base',0)");
        db.execSQL("insert into tbl_catalogsContent values('895A8374-18B0-496D-B0A9-4F876204536B','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','F','Libre',1)");
        db.execSQL("insert into tbl_catalogsContent values('C76D4B02-BB8E-4479-A2E0-50473E4B0775','D2261320-26BD-4560-AD96-0135366285A3','MEX','México ',0)");
        db.execSQL("insert into tbl_catalogsContent values('7B9079A0-38E8-4449-BB91-51030E32ECFA','BA697C53-E720-4D8C-BB2F-A405ED51F43D','','Albañales',0)");
        db.execSQL("insert into tbl_catalogsContent values('165AFB7D-1A1F-4AF9-AF91-510542431638','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros pequeños no rigidizada',0)");
        db.execSQL("insert into tbl_catalogsContent values('352D21EA-DEA0-4693-86DD-516F1D266D7C','5F7C9D4C-B206-4791-B14B-328E40FA25E2','','Otros',0)");
        db.execSQL("insert into tbl_catalogsContent values('A6601A4A-4C7D-4682-B701-518E9729CC2C','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Floculadores',0)");
        db.execSQL("insert into tbl_catalogsContent values('576ECD79-210B-44CE-9797-51AC63884E6A','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Cds',0)");
        db.execSQL("insert into tbl_catalogsContent values('58283B22-7A10-4CDA-9956-521D2C9667A3','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Equipos de diagnósitoco y laboratorio',0)");
        db.execSQL("insert into tbl_catalogsContent values('D9C05E00-C1C3-4C22-94C7-522EBD9EB08A','241FA096-9264-4150-AF9E-C9694D5E66C2','','Secundarias',0)");
        db.execSQL("insert into tbl_catalogsContent values('620B74DC-755B-439C-9DC7-5278BF8F1960','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','B','Concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('5026E187-04A6-4F0F-A268-52991956BCDA','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Gasto máximo de diseño',0)");
        db.execSQL("insert into tbl_catalogsContent values('44AD696B-CAE1-4251-A975-52EE8ACF6399','D2261320-26BD-4560-AD96-0135366285A3','MIC','Michoacán',0)");
        db.execSQL("insert into tbl_catalogsContent values('8644C4CB-262A-489C-9B13-52F7ACA11BF2','4F217F72-98B8-445F-AF8C-396B2B06671B','E','En rejas a base de hierro forjado',0)");
        db.execSQL("insert into tbl_catalogsContent values('8056AED7-CAC8-4E9B-A552-53506F2E5885','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Otros',0)");
        db.execSQL("insert into tbl_catalogsContent values('8A64A888-9E59-412A-A370-53E8C3ECE420','443523EE-F532-4577-956B-64E55A7B89BB','','Ostra',0)");
        db.execSQL("insert into tbl_catalogsContent values('6193134F-02D4-462D-8B9E-53FDC287C8B8','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','D','Block silico-calcareo',0)");
        db.execSQL("insert into tbl_catalogsContent values('7EF7351D-E116-4068-9611-544DC9C9421E','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','J','Costaneros de lamina galvanizada sobre perfiles metálicos',0)");
        db.execSQL("insert into tbl_catalogsContent values('280EB7FD-72B4-4C87-AAED-547DD5B70EBF','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Líneas de impulsión',0)");
        db.execSQL("insert into tbl_catalogsContent values('AADC54DE-B167-45C0-86FD-54880CCDF70D','37CE79B4-15EB-4BF3-892F-897223CFD12E','','Nivel de inundación',0)");
        db.execSQL("insert into tbl_catalogsContent values('22E02AEA-D38A-49C0-8C8D-54CAD7B719B8','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','C','Cristal de 6mm. natural',0)");
        db.execSQL("insert into tbl_catalogsContent values('F28B4182-39C5-40D0-96FE-554D61C654B5','EF7170F5-F452-47A9-8E1E-B6ED39DE7E90','','Puertas',0)");
        db.execSQL("insert into tbl_catalogsContent values('FBDEF03B-6739-496E-A485-55A30586AC87','539D1830-90A8-484C-B183-F79A448CEC78','','B.4. abanico',0)");
        db.execSQL("insert into tbl_catalogsContent values('B2784C7E-85B5-4164-A146-55DF62FA22F0','241FA096-9264-4150-AF9E-C9694D5E66C2','','Terciarias',0)");
        db.execSQL("insert into tbl_catalogsContent values('F092724D-DA1E-44AA-AAA0-55E562E05221','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Alcantarillas',0)");
        db.execSQL("insert into tbl_catalogsContent values('0B59181F-C16B-473B-92B6-5600D7ED3172','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Taludes aguas arriba',0)");
        db.execSQL("insert into tbl_catalogsContent values('5A200EE8-0947-46F7-A51F-562BEF0ABBB4','75B17471-ED30-467C-BA2A-8F8D1D988282','','WBAltitude',0)");
        db.execSQL("insert into tbl_catalogsContent values('6D431485-6C7F-417A-80C8-5678B9AF5ACF','D2261320-26BD-4560-AD96-0135366285A3','MOR','Morelos ',0)");
        db.execSQL("insert into tbl_catalogsContent values('0F0F02A3-FF7A-4302-8594-567E6E55219A','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Tipo de corriente',0)");
        db.execSQL("insert into tbl_catalogsContent values('62E62F2E-20C9-4955-8F08-56C2335AC131','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Cuenca',0)");
        db.execSQL("insert into tbl_catalogsContent values('79383E89-BC48-4454-9655-56F1F251B066','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Bombas',0)");
        db.execSQL("insert into tbl_catalogsContent values('44A78916-1C19-476E-BF88-57F2D0B19B68','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Salud',0)");
        db.execSQL("insert into tbl_catalogsContent values('EF6C223C-008C-4CEE-B97B-580E827F92FF','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Desfogues',0)");
        db.execSQL("insert into tbl_catalogsContent values('AF9E59FF-127F-4D0E-BC0A-5822B1B48DFC','089475EF-D242-4F7B-9C0C-CBFE98BB480D','','Chicos <.25 m2',0)");
        db.execSQL("insert into tbl_catalogsContent values('268AA290-FF1E-4B88-807F-58236E03C3B3','B504418E-8F8D-4086-8E59-CD615707E5FB','','Bocatomas para los diversos usos del embalse',0)");
        db.execSQL("insert into tbl_catalogsContent values('919A4798-DA01-4BC9-9396-583AC4598968','75B17471-ED30-467C-BA2A-8F8D1D988282','Y','Latitude',0)");
        db.execSQL("insert into tbl_catalogsContent values('26C4E75F-821A-446F-B3F6-58A32B5B884A','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red electromecánica (obras de conducción y distribución)',0)");
        db.execSQL("insert into tbl_catalogsContent values('4C729D1F-0CFB-4483-93E4-59D563B1DE13','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('1558CE49-AE55-4505-9D9D-59D70FAA0653','94E45704-8045-4CF8-B747-763D600789C4','','Capacidad de 10 a 50 toneladas por día',0)");
        db.execSQL("insert into tbl_catalogsContent values('5FC905BE-52E9-4360-A403-59EAC5DD1750','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen para control de avenidas',0)");
        db.execSQL("insert into tbl_catalogsContent values('B54C0944-AFF7-41B3-ADD0-5A5A7C32155B','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Otros',0)");
        db.execSQL("insert into tbl_catalogsContent values('5DA13A22-2A14-4642-B57D-5AA0BAA97048','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Siniestrado',0)");
        db.execSQL("insert into tbl_catalogsContent values('FF4AC697-B9D0-48DA-95D2-5B4A3E80892B','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen de conservación',0)");
        db.execSQL("insert into tbl_catalogsContent values('F0AAC454-6FE2-453F-8CE1-5B4B8DA82E93','81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','','Contrafuertes',0)");
        db.execSQL("insert into tbl_catalogsContent values('63BAFD4F-6234-4A6F-9FAF-5B959069353D','D2261320-26BD-4560-AD96-0135366285A3','NAY','Nayarit ',0)");
        db.execSQL("insert into tbl_catalogsContent values('C0F7CDCB-1D0A-40F0-955D-5C347B81F550','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Bienes históricos y arqueologicos',0)");
        db.execSQL("insert into tbl_catalogsContent values('51C2B7D8-9AC4-4CF4-AC5E-5D2D0EEAD57F','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('D6AD4FE9-55C1-4C54-A0D1-5D311E3D8D93','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('3D1555E2-058D-4409-96DB-5D4816E9CB06','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Hidraúlica',0)");
        db.execSQL("insert into tbl_catalogsContent values('76882720-5AF6-427E-8FBF-5D5E64A9EE41','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','De armadura en cantilever',0)");
        db.execSQL("insert into tbl_catalogsContent values('109567AB-77FE-4C6C-9ACE-5DD4FF28586A','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Transformadores de energía',0)");
        db.execSQL("insert into tbl_catalogsContent values('FFE859EF-E837-44FE-98C0-5DEDA615A8D2','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Gasto de agua',0)");
        db.execSQL("insert into tbl_catalogsContent values('56BCFEBE-1C66-4F21-993F-5E7B411C0252','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen de azolver',0)");
        db.execSQL("insert into tbl_catalogsContent values('B546FF4C-8DC1-47B0-B648-5F53B0998BEF','1B44E444-42A5-463B-AC94-0FA2DAFCB2D8','','Pesada (concreto)',0)");
        db.execSQL("insert into tbl_catalogsContent values('62671D0F-784E-4996-9D77-60CBC7447A06','539D1830-90A8-484C-B183-F79A448CEC78','','B.2. descarga directa',0)");
        db.execSQL("insert into tbl_catalogsContent values('EDBCF3A6-A979-445A-8AAC-6100088678E5','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Canales de riego con todos sus componentes',0)");
        db.execSQL("insert into tbl_catalogsContent values('08A9201C-F6CC-44F0-B136-61370BC2EFDB','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Disposición de residuos',0)");
        db.execSQL("insert into tbl_catalogsContent values('6291860C-380D-40C8-ADC5-61F1EFDCC1DD','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen medio de escurrimiento anual',0)");
        db.execSQL("insert into tbl_catalogsContent values('8A8EA3AC-5D57-425E-A121-628A1EFBE552','464A3F38-B8B5-4828-871B-D717885AE5CB','','Galerías filtrantes',0)");
        db.execSQL("insert into tbl_catalogsContent values('AB2A8871-7430-4499-9D56-62BBEABACA32','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red de conducción de agua (obras de conducción y distribución)',0)");
        db.execSQL("insert into tbl_catalogsContent values('B475F358-782B-49DF-BCBE-62F4646FDB1C','E398849C-BDF2-4ABB-AFB0-0E4E8C8EF326','','Unitaria',0)");
        db.execSQL("insert into tbl_catalogsContent values('34069595-7CA2-4341-B1D1-632507BD06ED','75B17471-ED30-467C-BA2A-8F8D1D988282','','Percipitación máxima anual',0)");
        db.execSQL("insert into tbl_catalogsContent values('8CACBFC2-60BA-4542-B5BD-63B2A9E1FCFF','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Severidad de hundimientos',0)");
        db.execSQL("insert into tbl_catalogsContent values('EB31C186-1719-4CE7-B66C-640CFCC5E593','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Corriente',0)");
        db.execSQL("insert into tbl_catalogsContent values('C49271C9-0B7E-441B-9F98-651194F4D863','76A8D090-EB15-4600-ADB5-9F68043E46A5','','Calle',0)");
        db.execSQL("insert into tbl_catalogsContent values('2A228004-8DD4-4D2F-ABDE-6524266D61E3','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Clínicas y hospitales',0)");
        db.execSQL("insert into tbl_catalogsContent values('06E3CBD9-A517-4E51-B019-65621E025CEC','B374ABA0-9C03-4916-ADE8-0BA48161CE05','','Carretera',1)");
        db.execSQL("insert into tbl_catalogsContent values('60752DDF-C488-4757-9A6B-65F2903B06DA','0E28A695-8644-4E13-92D1-F32EAEEEA29A','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('97C51E3D-3FAB-46D1-9D64-65FDAD271BD5','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Pacífico sur',0)");
        db.execSQL("insert into tbl_catalogsContent values('3210C802-6BED-4DE0-B6F5-66064949EA22','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor de reposición a nuevo',0)");
        db.execSQL("insert into tbl_catalogsContent values('B682AA18-C836-4BA9-9ECA-6658D6D8D124','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Talleres',0)");
        db.execSQL("insert into tbl_catalogsContent values('8580B592-08F3-4BA0-BC63-66965C335E5A','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones relativamente secas',0)");
        db.execSQL("insert into tbl_catalogsContent values('478BFA53-F1FC-4843-8E9E-66C52A882F63','6CB19E01-D41B-4052-9C10-BC5360DD22FE','arn','Arenoso',1)");
        db.execSQL("insert into tbl_catalogsContent values('9CEE1E14-0AAE-4D4F-989C-67B7A0BCAF3E','0D4FEBCD-D28A-4293-9065-A5A906BF6E69','','> a 15 m',0)");
        db.execSQL("insert into tbl_catalogsContent values('5C0B7B63-E2DE-48C6-9086-680BAF833781','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','E','Cristal de 12mm. natural',0)");
        db.execSQL("insert into tbl_catalogsContent values('0C1403CF-5E97-4EFA-AB4A-687276B83A54','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','H','Cristal de 12mm. polarizado',0)");
        db.execSQL("insert into tbl_catalogsContent values('C9F23800-29DA-4231-BA72-68B7CE145A7A','C52AF614-2865-4708-B489-1BE720793BE6','','H.     presa de diseño combinado',1)");
        db.execSQL("insert into tbl_catalogsContent values('C331B67C-920F-42DB-A2EA-6925B36F66D8','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Aliviadero',0)");
        db.execSQL("insert into tbl_catalogsContent values('388AE204-9EFB-4729-B6F5-6A575E1EFE7B','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Diques',0)");
        db.execSQL("insert into tbl_catalogsContent values('F12DA261-C9EC-4EB7-AEA1-6AA468DD2FFB','E43CE3ED-6F48-4668-ABF3-222A8533005B','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('D6726C92-D2E1-4C38-B553-6AC5868E22B2','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Escuela',0)");
        db.execSQL("insert into tbl_catalogsContent values('6236ECC2-F92B-416D-807D-6B22A16A8FC3','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Almacenes',0)");
        db.execSQL("insert into tbl_catalogsContent values('EDD00D9C-4FAD-4847-BE66-6B35252F704B','75B17471-ED30-467C-BA2A-8F8D1D988282','','WBLatitude',0)");
        db.execSQL("insert into tbl_catalogsContent values('A6B4D19F-3C39-4E07-91C6-6BAE4896AD0C','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Línea de impulsión',0)");
        db.execSQL("insert into tbl_catalogsContent values('27FC32A7-DC28-455C-AD54-6BB56DA86E1F','D2261320-26BD-4560-AD96-0135366285A3','NLE','Nuevo León ',0)");
        db.execSQL("insert into tbl_catalogsContent values('94C3672E-3315-4D5D-92F8-6C49E5517F83','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('6F5701EB-8DCA-4A2D-B18A-6C4C959FEB0F','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Volumen del cuerpo',0)");
        db.execSQL("insert into tbl_catalogsContent values('20A5A886-B52B-456F-8199-6C9F3D63C092','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Obras de toma y salida',0)");
        db.execSQL("insert into tbl_catalogsContent values('CA88B7FE-4BC1-4CAF-B14A-6CD9620FCAC9','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','No ubicable',0)");
        db.execSQL("insert into tbl_catalogsContent values('F071577C-AD31-42C8-B208-6D304B2F1C1C','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Geometría irregular',0)");
        db.execSQL("insert into tbl_catalogsContent values('CABF2D63-ADEF-4226-BF66-6D7AD8B47DF1','5F7C9D4C-B206-4791-B14B-328E40FA25E2','','Abrevadero',0)");
        db.execSQL("insert into tbl_catalogsContent values('A545FC72-EDE8-4883-BCB1-6F09C5CD371A','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Laboratorios',0)");
        db.execSQL("insert into tbl_catalogsContent values('AFB03373-3BCF-4E95-82CB-6FA76501C9A6','22A44093-7E34-46D9-92DF-57FE3A335E13','','Saneamiento',0)");
        db.execSQL("insert into tbl_catalogsContent values('27E91B61-2BC4-484A-8EF5-6FAF6D1A6E29','07C3B108-6581-4DA7-85BC-FF6263924E99','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('9638A1E6-9D30-4885-AC60-705CEB53BE82','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Urbana',0)");
        db.execSQL("insert into tbl_catalogsContent values('D4BABE2A-F30A-4307-8430-70A122FACE70','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Constructor',0)");
        db.execSQL("insert into tbl_catalogsContent values('BFD91756-633C-4CD3-B0C9-70D7D076B835','76A8D090-EB15-4600-ADB5-9F68043E46A5','','Tunel',0)");
        db.execSQL("insert into tbl_catalogsContent values('6D0F75F6-4E18-4D16-97B5-70F8B6E7388F','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Estancias infantiles',0)");
        db.execSQL("insert into tbl_catalogsContent values('4AA6D428-CE01-4F88-B779-71017EAE5196','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Diseñador',0)");
        db.execSQL("insert into tbl_catalogsContent values('F5ABED99-B8A4-47C7-A4F8-714B7FA4AAA9','EA50619F-3012-4841-8939-BB83634FCF9C','','Flexible',0)");
        db.execSQL("insert into tbl_catalogsContent values('84CFF176-BB1E-4B18-9053-718888F9DB4A','75B17471-ED30-467C-BA2A-8F8D1D988282','','WBLongitude',0)");
        db.execSQL("insert into tbl_catalogsContent values('22299D7C-D581-4D3B-A7B3-71BF5DC6368F','75B17471-ED30-467C-BA2A-8F8D1D988282','A','accuracy',0)");
        db.execSQL("insert into tbl_catalogsContent values('30D129AA-E074-4BEE-89FA-71F29CBFC040','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','Puente Viga',0)");
        db.execSQL("insert into tbl_catalogsContent values('8BA91214-6248-4571-A25D-7207F10CB286','E398849C-BDF2-4ABB-AFB0-0E4E8C8EF326','','Separativa',0)");
        db.execSQL("insert into tbl_catalogsContent values('0F81038B-9DE6-4045-97D5-72136F321B82','234FB73E-843A-4976-BF1F-48318D352CDA','','Si, ligeros',0)");
        db.execSQL("insert into tbl_catalogsContent values('3A94A057-9BAF-4633-9AD4-72F60065C46A','D2261320-26BD-4560-AD96-0135366285A3','OAX','Oaxaca ',0)");
        db.execSQL("insert into tbl_catalogsContent values('C9DE5964-9E57-4A7B-BA9F-73A7B4418331','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Otros',0)");
        db.execSQL("insert into tbl_catalogsContent values('77A062EB-832E-4972-BEB0-741862DC9FED','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','','Reservas de agua para el llenado de la esclusa',0)");
        db.execSQL("insert into tbl_catalogsContent values('F4F5D823-2945-401C-ABF7-74189AFB50CC','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Golfo centro',0)");
        db.execSQL("insert into tbl_catalogsContent values('4332572D-8FD6-4C25-934C-74A3165B3C26','5F7C9D4C-B206-4791-B14B-328E40FA25E2','','Agua potable',0)");
        db.execSQL("insert into tbl_catalogsContent values('66B1B7A6-DF11-489A-8DD7-74C000D78A26','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Muebles',0)");
        db.execSQL("insert into tbl_catalogsContent values('3B6A4AE4-8E8B-4600-A881-752C394BFC2C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Estaciones depuradoras',0)");
        db.execSQL("insert into tbl_catalogsContent values('4094E5D7-EF96-4A3D-A160-755C6634AC24','12634A22-8918-4A1C-B2DC-BE4C827735CA','','Hormigon',0)");
        db.execSQL("insert into tbl_catalogsContent values('9F2AF287-A9DD-4DFF-998C-7583032FDEBB','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','vgr3','Desc V3',0)");
        db.execSQL("insert into tbl_catalogsContent values('42020774-B8EB-4994-9A5A-75A49FBD7932','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','E','Tabique barro rojo cocido',0)");
        db.execSQL("insert into tbl_catalogsContent values('A31FDF5A-12D2-481E-AABB-7631A2EF2AD0','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Otro',0)");
        db.execSQL("insert into tbl_catalogsContent values('682C8723-BFCE-4F17-A5DE-7664EF196F9B','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Represas',0)");
        db.execSQL("insert into tbl_catalogsContent values('371D7062-79C8-4080-A0BA-7673FD691D16','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n6','prueba 6',1)");
        db.execSQL("insert into tbl_catalogsContent values('5D4F7C0B-2DA7-4253-83B4-76804F2938A6','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Noroeste',0)");
        db.execSQL("insert into tbl_catalogsContent values('DDEB7463-2B76-4B49-9630-76B8EF104F56','754E0F98-3A4A-4D1B-9B84-135B860A7791','','Alta',0)");
        db.execSQL("insert into tbl_catalogsContent values('47D8EDA0-D54F-4ADA-AC95-7701B7A7DE75','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Área',0)");
        db.execSQL("insert into tbl_catalogsContent values('1FA917DF-061A-49DF-A8FA-77340906B686','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','tst','Registro prueba',0)");
        db.execSQL("insert into tbl_catalogsContent values('C06F5911-6A89-41EA-8FD2-7762DEECD539','DE4815D5-E7B5-4C48-A694-180F351E1B41','B','Columnas, trabes, castillos, cadenas y cerramientos de concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('C05F46A4-77F8-40BB-99E8-776A975589B9','84987039-D458-480B-8B34-0C7AEE808206','','Terraceria',0)");
        db.execSQL("insert into tbl_catalogsContent values('9EDF9087-14E2-4C10-9107-777D5CD9EDC0','4F217F72-98B8-445F-AF8C-396B2B06671B','G','En rejas a base de tubulares',1)");
        db.execSQL("insert into tbl_catalogsContent values('6E26183E-6799-4839-B293-77E9B3C863AA','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Educación',0)");
        db.execSQL("insert into tbl_catalogsContent values('E58E0C92-3AEB-4378-B962-786BD484459C','D2261320-26BD-4560-AD96-0135366285A3','PUE','Puebla ',0)");
        db.execSQL("insert into tbl_catalogsContent values('74A5FF1A-2F4C-44B4-AB61-7880F462CA92','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Mnto destinado anualmente a mantenimiento ',0)");
        db.execSQL("insert into tbl_catalogsContent values('6D8A20A4-11C9-4FA9-9421-78E5499B4EE3','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Inventarios libros',0)");
        db.execSQL("insert into tbl_catalogsContent values('504BAE09-9F93-46A5-83CA-790CB72996F2','E9D156CD-19DE-43F1-8B17-0F33227A90B2','','Otras',0)");
        db.execSQL("insert into tbl_catalogsContent values('042D61F5-D44B-490D-9EFD-79199F4779E7','BA697C53-E720-4D8C-BB2F-A405ED51F43D','','Entronques',0)");
        db.execSQL("insert into tbl_catalogsContent values('F82BA6B5-AE2C-49FE-8EBE-792ABC71626B','1ACD8B10-3A71-4481-ABEA-70E91B70B53A','','Suelos de grano grueso (e.g. arenas y gravas)',0)");
        db.execSQL("insert into tbl_catalogsContent values('39774039-AFC9-4DDA-A9C8-7933E4545103','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Descargas de fondos',0)");
        db.execSQL("insert into tbl_catalogsContent values('A390BB9B-B1FB-4185-AF3C-7957F0C60CA4','3585818D-7660-4569-A24F-F5F0999D4705','A','Mampostería de piedra y refuerzos de concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('96448ECA-C1E0-4601-A886-79BA979E0CEF','4F217F72-98B8-445F-AF8C-396B2B06671B','B','En puertas y ventanas perfiles metalicos tubulares',0)");
        db.execSQL("insert into tbl_catalogsContent values('64CC0DFB-A94F-492A-A3A1-79F9121DA059','20F41507-5058-466C-AD1E-1819B8A6207F','','Sección',0)");
        db.execSQL("insert into tbl_catalogsContent values('542972F2-20A9-42C3-BB3A-7A0A03BBA1E7','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('3ABE7B4F-010A-4DE2-9A97-7A76AB156418','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Esclusas',0)");
        db.execSQL("insert into tbl_catalogsContent values('5652F097-CA3C-4349-8283-7A88309AFE93','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Afluente',0)");
        db.execSQL("insert into tbl_catalogsContent values('92030EA0-C76C-461A-883B-7ABF1B3D2621','CA38F79C-B87A-4B9B-BC1D-2002B2658C9B','','Mucha',0)");
        db.execSQL("insert into tbl_catalogsContent values('9847C866-496F-4F09-B2FA-7ACA1364B85A','443523EE-F532-4577-956B-64E55A7B89BB','','Lubina',0)");
        db.execSQL("insert into tbl_catalogsContent values('E137FF48-9965-47BF-A998-7B60895F5A2D','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones en roca',0)");
        db.execSQL("insert into tbl_catalogsContent values('483BCBBF-1447-4961-9741-7B9CCE4C4E5F','6CB19E01-D41B-4052-9C10-BC5360DD22FE','arc','Arcilla',0)");
        db.execSQL("insert into tbl_catalogsContent values('13B98A31-21D5-4AC6-9049-7BB57FDE3CCF','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','','Compuertas',0)");
        db.execSQL("insert into tbl_catalogsContent values('F0AC53C2-01A8-4528-9C23-7BE4CF1FAB0B','EA50619F-3012-4841-8939-BB83634FCF9C','','Rígida',0)");
        db.execSQL("insert into tbl_catalogsContent values('48D79D10-AD36-41C6-A28E-7BE7B1145BF3','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n5','prueba 5',1)");
        db.execSQL("insert into tbl_catalogsContent values('48E751A1-3FA8-44E3-B59B-7D673C1E7192','B1137091-9300-4989-BE0F-4D38B9C543C6','','Biblioteca',0)");
        db.execSQL("insert into tbl_catalogsContent values('5FFA3D32-0C98-4947-9EE2-7D8F52BB6EA5','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Otro',0)");
        db.execSQL("insert into tbl_catalogsContent values('4FCEE5B2-75BE-4E65-A3AD-7E08006B7FE4','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','F','Mampostería de piedra',0)");
        db.execSQL("insert into tbl_catalogsContent values('A67C9F30-6D1D-48C5-8CE4-7E216AE47889','DE4815D5-E7B5-4C48-A694-180F351E1B41','C','Metálica con columnas, trabes, armaduras y largueros a base de diversos elementos metálicos',0)");
        db.execSQL("insert into tbl_catalogsContent values('E88B42EE-81B7-4986-8A9F-7E8CBFE720EC','1758A063-FF75-4AAB-9D43-6FDD89946A54','','Bitubo',0)");
        db.execSQL("insert into tbl_catalogsContent values('934D6B71-5B72-44E4-AD81-7EEB289F5A59','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros grandes no rigidizada',0)");
        db.execSQL("insert into tbl_catalogsContent values('FB1A6D04-3FF6-4275-A70D-7EF41C015102','4F217F72-98B8-445F-AF8C-396B2B06671B','H','Libre',0)");
        db.execSQL("insert into tbl_catalogsContent values('2EE66436-7EB6-463D-A64F-7EFA95791E72','84987039-D458-480B-8B34-0C7AEE808206','','Asfalto',0)");
        db.execSQL("insert into tbl_catalogsContent values('D519A22A-F479-45B8-BCE7-7F42D3F74CB4','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Num Hundimientos',0)");
        db.execSQL("insert into tbl_catalogsContent values('55846B69-506C-41FB-B7A1-7F68F3FF0A54','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','No habitable',0)");
        db.execSQL("insert into tbl_catalogsContent values('71CB55AF-9DD9-448E-97C7-8137E0D511C1','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Loseta',0)");
        db.execSQL("insert into tbl_catalogsContent values('A319CE2C-0EFE-49C9-BF9A-814922C7CC12','75B17471-ED30-467C-BA2A-8F8D1D988282','','Percipitación máxima diaria',0)");
        db.execSQL("insert into tbl_catalogsContent values('3B2233F0-B84A-4543-8B11-81D23FC1CDFE','81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','','Bóveda',1)");
        db.execSQL("insert into tbl_catalogsContent values('98CFD274-71A3-4EDA-B5B1-8202A13D44E4','8831E933-E4BB-47DF-A0FA-139EC6495C73','','Al centro de un dique de tierra',0)");
        db.execSQL("insert into tbl_catalogsContent values('2E084FD5-681C-4A14-915B-8227CED05C7A','3585818D-7660-4569-A24F-F5F0999D4705','H','Libre',0)");
        db.execSQL("insert into tbl_catalogsContent values('12AC30E2-B313-4519-9F4D-82A12F161EE3','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','','Tanques elevados',0)");
        db.execSQL("insert into tbl_catalogsContent values('4076FD65-E25F-4CAC-8764-82D3011D97B5','22A44093-7E34-46D9-92DF-57FE3A335E13','','Alcantarillado',0)");
        db.execSQL("insert into tbl_catalogsContent values('C1022DE8-58B6-49A3-86E3-82D94BB2FF26','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Infraestructura hidraúlica',0)");
        db.execSQL("insert into tbl_catalogsContent values('4F213B14-A341-40B8-B15F-8301538E9F3A','E9D156CD-19DE-43F1-8B17-0F33227A90B2','','Deslizantes',0)");
        db.execSQL("insert into tbl_catalogsContent values('5B588BDF-405F-408D-9076-8306BC116A21','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Maquinaria y equipo',0)");
        db.execSQL("insert into tbl_catalogsContent values('9FC3A281-AFE2-4FFC-984E-83111D1AC5CC','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Demolido',0)");
        db.execSQL("insert into tbl_catalogsContent values('197409AB-1F50-4BC0-8993-8342133900F6','07C3B108-6581-4DA7-85BC-FF6263924E99','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('78046592-0251-4528-B6DB-834882CCB4A0','6CB19E01-D41B-4052-9C10-BC5360DD22FE','trn','Transición',1)");
        db.execSQL("insert into tbl_catalogsContent values('2F1DB29D-3273-43EC-88A5-83C306609F39','EA766519-648F-41FB-B429-A21F94758AE8','','Tubería a presión',0)");
        db.execSQL("insert into tbl_catalogsContent values('3E113983-2942-435A-B471-83D67866C650','E8E42A49-5874-4AE2-9070-8C15EE163201','','Madera',0)");
        db.execSQL("insert into tbl_catalogsContent values('1CAB8099-D748-4215-B70B-84031036B944','2043ACDA-56F7-40CE-A853-233F470FCB76','','Caseta de vigilancia y control de acceso',0)");
        db.execSQL("insert into tbl_catalogsContent values('42E4A3AD-6EAF-400D-91AD-845B218336CF','8EB882ED-6597-4111-ACD1-0DFF899B2BD8','','Cisterna',0)");
        db.execSQL("insert into tbl_catalogsContent values('492010A2-1935-4B0D-924D-847D19B67110','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Otros medios de almacenamiento de información.',0)");
        db.execSQL("insert into tbl_catalogsContent values('593BAEAB-940C-48DD-96AF-852A4C5F8454','BA697C53-E720-4D8C-BB2F-A405ED51F43D','','Arquetas de arranque',0)");
        db.execSQL("insert into tbl_catalogsContent values('897BC614-403F-47EB-8EF2-854351F431BB','F4DBA965-B5D9-4A89-93A2-C55850E82FFB','','Adyacente a la cortina',0)");
        db.execSQL("insert into tbl_catalogsContent values('30502FC5-7CE7-49FD-ABED-85D0C2586280','3585818D-7660-4569-A24F-F5F0999D4705','G','Zapatas corridas, dados y contratrabes de concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('35ECE2CE-EA3A-4F4C-8584-866A9BA5F3EC','20F41507-5058-466C-AD1E-1819B8A6207F','','Altura',0)");
        db.execSQL("insert into tbl_catalogsContent values('B9FFADBA-530F-4EED-8811-867B7B7DD4C2','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Hundimiento',0)");
        db.execSQL("insert into tbl_catalogsContent values('A344B1B3-BA1A-4D2C-95DF-870996C2F502','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','D','Cristal de 9mm. natural',0)");
        db.execSQL("insert into tbl_catalogsContent values('813F6030-A058-4505-906C-87A40E6D4EC2','C52AF614-2865-4708-B489-1BE720793BE6','','D.    presa de pantalla plana',0)");
        db.execSQL("insert into tbl_catalogsContent values('E80F4100-A293-420B-B8E0-87CCFE8B30A6','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red electromecánica (saneamiento)',0)");
        db.execSQL("insert into tbl_catalogsContent values('666FA81A-D0B0-4C10-939D-88449A0E5481','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Estaciones de bombeo',0)");
        db.execSQL("insert into tbl_catalogsContent values('7ABA1EDB-1327-4676-968D-88B5BA5FE63E','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','O','Celosias de concreto',0)");
        db.execSQL("insert into tbl_catalogsContent values('3BB97903-A462-48F4-BE9F-894CC5F25C4E','782F77AC-0455-45F8-8B8F-F29C9DC058B9','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('6811BAF0-2EF0-407E-946F-895C4BB258C9','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Presencia de golpeteo con otras estructuras',0)");
        db.execSQL("insert into tbl_catalogsContent values('23CB6695-F543-4542-8247-897ED624B95E','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Dispositivo para amortiguar el golpe de ariete',0)");
        db.execSQL("insert into tbl_catalogsContent values('FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Inundación',0)");
        db.execSQL("insert into tbl_catalogsContent values('12414F86-999E-4071-91C5-8A19430A3A46','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Dispositivos para la mediación del caudal',0)");
        db.execSQL("insert into tbl_catalogsContent values('BFE031DC-88BF-422B-94EE-8A26A4729F45','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Cuerpo de agua cruza',0)");
        db.execSQL("insert into tbl_catalogsContent values('5A95A512-B8BB-446E-BF24-8AB01208F4BA','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','VGR1','Desc V1',0)");
        db.execSQL("insert into tbl_catalogsContent values('B4D8440A-39B9-4A44-BB27-8BC129EA7FDA','B4C32095-C18C-4448-BB13-7725B88EF351','','Poste de luz o teléfono',0)");
        db.execSQL("insert into tbl_catalogsContent values('8BA335C7-6BBF-46A1-8DB5-8BDCDB001FD1','2043ACDA-56F7-40CE-A853-233F470FCB76','','Servicio médico y seguridad personal',0)");
        db.execSQL("insert into tbl_catalogsContent values('0CF4F5E6-33D7-4A16-8F38-8BEB69FF8C13','8EB882ED-6597-4111-ACD1-0DFF899B2BD8','','Hidrantes',0)");
        db.execSQL("insert into tbl_catalogsContent values('DC92108D-C042-4FC6-8CE0-8C507B30E320','539D1830-90A8-484C-B183-F79A448CEC78','','A.1. económico o lavadero',0)");
        db.execSQL("insert into tbl_catalogsContent values('32A24C2D-4257-40DF-BD30-8CF9CF471279','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Tuberías',0)");
        db.execSQL("insert into tbl_catalogsContent values('2808E19A-76D4-41CF-ADA4-8D05D329A878','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Libramiento',0)");
        db.execSQL("insert into tbl_catalogsContent values('9A17845A-8B67-4F96-8893-8DC73E843807','6CB19E01-D41B-4052-9C10-BC5360DD22FE','vrt','Vertisoles',1)");
        db.execSQL("insert into tbl_catalogsContent values('0C76AD41-D3BE-481C-A82A-8E947F5134B0','B4C32095-C18C-4448-BB13-7725B88EF351','','Arbol',0)");
        db.execSQL("insert into tbl_catalogsContent values('4FE15384-6C32-42F7-9EF4-8EAFCCD5843A','DE4815D5-E7B5-4C48-A694-180F351E1B41','D','Mixta, con columnas de concreto armado y armaduras y largueros de perfiles metálicos',0)");
        db.execSQL("insert into tbl_catalogsContent values('115B140C-2596-4F69-95F7-8EFAC742051F','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Presas',0)");
        db.execSQL("insert into tbl_catalogsContent values('1AF15855-447D-482A-BFB9-8F3BA5EA48B4','464A3F38-B8B5-4828-871B-D717885AE5CB','','Pozos',0)");
        db.execSQL("insert into tbl_catalogsContent values('1D853564-86AE-4C34-8786-8F591AC07D74','539D1830-90A8-484C-B183-F79A448CEC78','','B. de cresta de caída recta',0)");
        db.execSQL("insert into tbl_catalogsContent values('AD0D2809-30C8-4E53-9595-8F7894062CC0','75B17471-ED30-467C-BA2A-8F8D1D988282','S','TownName',0)");
        db.execSQL("insert into tbl_catalogsContent values('D79634AC-B15F-426A-83C7-8FB83D290FC0','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','cst','Cisternas',0)");
        db.execSQL("insert into tbl_catalogsContent values('432E2E88-5084-498C-9B7A-8FE7DB1F1593','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('C42FE244-8F70-4EF0-959C-9034C14E4A93','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Cancelería',0)");
        db.execSQL("insert into tbl_catalogsContent values('E22398FA-66BC-45F0-B4C8-905D4BDA2E30','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','D','Aparentes con pintura vinílica',1)");
        db.execSQL("insert into tbl_catalogsContent values('8F684117-6E6E-4C78-83AF-9147C0AFFE55','B4C32095-C18C-4448-BB13-7725B88EF351','','Anuncios espectaculares',0)");
        db.execSQL("insert into tbl_catalogsContent values('245341C6-5DBE-4227-BF11-9172906C1E24','D2261320-26BD-4560-AD96-0135366285A3','QUE','Querétaro ',0)");
        db.execSQL("insert into tbl_catalogsContent values('E32672F5-5B56-43E7-AE23-9256F054C5DD','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Taludes aguas abajo',0)");
        db.execSQL("insert into tbl_catalogsContent values('28C54E78-15B8-48F4-B1F7-9261A75F2325','B374ABA0-9C03-4916-ADE8-0BA48161CE05','','Brecha',0)");
        db.execSQL("insert into tbl_catalogsContent values('04BFB10E-8005-475B-9564-92B9B90E2C87','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red de alimentación (obras de conducción y distribución)',0)");
        db.execSQL("insert into tbl_catalogsContent values('BC589497-44DC-48BD-8FBF-92E30B3EC8AD','2043ACDA-56F7-40CE-A853-233F470FCB76','','Caminos interiores',0)");
        db.execSQL("insert into tbl_catalogsContent values('3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','D2261320-26BD-4560-AD96-0135366285A3','ROO','Quintana Roo ',0)");
        db.execSQL("insert into tbl_catalogsContent values('E072EA3D-C4A0-4741-9962-93DB68B4B86F','E8E42A49-5874-4AE2-9070-8C15EE163201','','Concreto',0)");
        db.execSQL("insert into tbl_catalogsContent values('E8995CE2-2E6E-43FB-8CD6-93FF0C81C1C0','6CB19E01-D41B-4052-9C10-BC5360DD22FE','fls','Fluvisoles',1)");
        db.execSQL("insert into tbl_catalogsContent values('494C0A72-F88B-4E66-AEC5-94A63D228153','D2261320-26BD-4560-AD96-0135366285A3','SLP','San Luis Potosí ',0)");
        db.execSQL("insert into tbl_catalogsContent values('975D1E82-3F39-419D-8116-94A8EACA4A95','089475EF-D242-4F7B-9C0C-CBFE98BB480D','','Grandes >1 m2',0)");
        db.execSQL("insert into tbl_catalogsContent values('43BE923C-BA5F-4FC7-B266-94E3F735520A','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','',' aguas del valle de méxico',0)");
        db.execSQL("insert into tbl_catalogsContent values('AD89F22C-AA79-4C94-A263-956CF9CEA51F','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Cancelería',0)");
        db.execSQL("insert into tbl_catalogsContent values('92EE36EE-A07B-4FA6-BE80-956EFEF8A735','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Rio bravo',0)");
        db.execSQL("insert into tbl_catalogsContent values('B4514A07-DFD7-4F90-8FD2-95BF1C2E052B','B5A4C0E7-FF66-4988-A957-F8FC519E2895','','Mariposa',0)");
        db.execSQL("insert into tbl_catalogsContent values('9C3B386F-1490-4A02-AD17-963E38CBC077','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Balsa del agua',0)");
        db.execSQL("insert into tbl_catalogsContent values('4B274684-843A-4CEF-97B1-971D874C27B4','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Clínica',0)");
        db.execSQL("insert into tbl_catalogsContent values('5C03CF75-7DF8-407B-81CC-973D4E8A4968','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Periodo de retorno',0)");
        db.execSQL("insert into tbl_catalogsContent values('C31B9366-FBC1-4B61-9279-9745B50F43C4','6CB19E01-D41B-4052-9C10-BC5360DD22FE','lts','Litosoles',1)");
        db.execSQL("insert into tbl_catalogsContent values('BC82E604-7D0C-412C-A91D-97552482BC86','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Subestación y ductos eléctricos',0)");
        db.execSQL("insert into tbl_catalogsContent values('DE9C700C-0B99-4683-8E02-977A142120C8','D2261320-26BD-4560-AD96-0135366285A3','SIN','Sinaloa ',0)");
        db.execSQL("insert into tbl_catalogsContent values('808920BA-B0C3-4B8F-ABEC-9883688BAD36','D2261320-26BD-4560-AD96-0135366285A3','SON','Sonora ',0)");
        db.execSQL("insert into tbl_catalogsContent values('791CFA6C-B694-4B5E-9351-989673F62B34','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Ancho',0)");
        db.execSQL("insert into tbl_catalogsContent values('50F4054C-30DA-40E9-87E1-98BED8C9E9AD','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Cuerpo de agua más cercano',0)");
        db.execSQL("insert into tbl_catalogsContent values('96F4E9E5-E5E4-44E1-A75D-9913175F052E','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Techumbres',0)");
        db.execSQL("insert into tbl_catalogsContent values('C8E5BBC2-8142-4A50-9520-996F0942A383','D2261320-26BD-4560-AD96-0135366285A3','TAB','Tabasco ',0)");
        db.execSQL("insert into tbl_catalogsContent values('78C74191-5BBF-47E2-9738-999C6BD2E39A','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Filtros',0)");
        db.execSQL("insert into tbl_catalogsContent values('425DB9F7-93CD-4310-9C31-99D7B6FDCD5D','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Superalmacenamiento',0)");
        db.execSQL("insert into tbl_catalogsContent values('D9C3F321-A3CE-4784-8E63-9AD53B3EF6F5','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Desarenador',0)");
        db.execSQL("insert into tbl_catalogsContent values('DC8C3EAF-8E0E-4598-A2D1-9B36E277F6C4','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Obras de toma y salida',0)");
        db.execSQL("insert into tbl_catalogsContent values('F01DF7E9-C2FF-4436-B93A-9C324BDC60A6','A232C4C1-5524-4302-9329-F69D050F9698','','Edificio',0)");
        db.execSQL("insert into tbl_catalogsContent values('4F44BDD2-009B-4244-BFC4-9C6A37BFF81F','F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','','Hidroeléctricas.',0)");
        db.execSQL("insert into tbl_catalogsContent values('B7A3C1E3-7301-4F26-B09C-9C70EE751B6A','DE4815D5-E7B5-4C48-A694-180F351E1B41','H','Libre',0)");
        db.execSQL("insert into tbl_catalogsContent values('EE347E08-8DA4-41F4-8614-9CEC336C9D46','E9D156CD-19DE-43F1-8B17-0F33227A90B2','','Radiales',0)");
        db.execSQL("insert into tbl_catalogsContent values('A5F06F88-B3A8-4D55-AB0F-9CEDA99A68D4','967615A4-74A4-40C3-B276-4EFCBDE63A48','FED','Gobierno Federal',0)");
        db.execSQL("insert into tbl_catalogsContent values('0A736DEA-8966-4F93-9353-9DA16F9B4174','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Península de baja california',0)");
        db.execSQL("insert into tbl_catalogsContent values('3F4EF8B6-0B95-46A5-8DBA-9E4736B8049E','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Red de caminos',0)");
        db.execSQL("insert into tbl_catalogsContent values('6D7E8620-C6E1-4184-B426-9E4BD0916AAA','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red sanitaria (saneamiento)',0)");
        db.execSQL("insert into tbl_catalogsContent values('F6DD4CC8-80B0-4995-B60E-9E55ED8A640B','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','E','Aparentes con pintura de esmalte',0)");
        db.execSQL("insert into tbl_catalogsContent values('3F7FB352-D3DA-4AA3-8FCB-9E8B151ECF4B','7EB43883-335D-4184-86F3-D899EB2F6784','','Concreto',0)");
        db.execSQL("insert into tbl_catalogsContent values('9B674B1C-3E69-4249-AEC0-9EDD05C2628E','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','A','Aparentes',0)");
        db.execSQL("insert into tbl_catalogsContent values('39301E28-AFF7-452E-8B0B-9F38489AB220','68A049E3-4B55-4F48-AE03-A6CB920028B8','','Lago',0)");
        db.execSQL("insert into tbl_catalogsContent values('731C321F-2EE8-43E2-8F2D-9F9B63443BA1','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Galerías',0)");
        db.execSQL("insert into tbl_catalogsContent values('B5C155EB-5B5B-4509-AD46-A0F797CA327F','EF7170F5-F452-47A9-8E1E-B6ED39DE7E90','','Ventanas',0)");
        db.execSQL("insert into tbl_catalogsContent values('4CAC869B-3F61-450E-8D35-A15B22BA2E19','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Reja para la retención de material',0)");
        db.execSQL("insert into tbl_catalogsContent values('CBC48695-91B6-4959-83A1-A1C015784FFC','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Equipos de cómputo',0)");
        db.execSQL("insert into tbl_catalogsContent values('CE4D2C2C-B3A9-475D-B0D7-A265027AB968','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Interceptores',0)");
        db.execSQL("insert into tbl_catalogsContent values('3502DA0C-56D9-4DE3-9AA8-A3DBE42CF63A','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('E6F46501-32F1-469A-A5D1-A456BCB6C9BD','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Presencia de muros cortos entre columnas de carga',0)");
        db.execSQL("insert into tbl_catalogsContent values('BDF64B6B-482B-4011-9A68-A4E647632281','15641A0F-51F0-4969-AD95-90EF456AFE13','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('DDE06835-B4C8-4883-B484-A4F83A42370C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Instalación sanitaria',0)");
        db.execSQL("insert into tbl_catalogsContent values('5EE87CE7-9EED-4B5C-B0C8-A54096C91B2B','754E0F98-3A4A-4D1B-9B84-135B860A7791','','Baja',0)");
        db.execSQL("insert into tbl_catalogsContent values('805D46D8-7187-451D-B92E-A5555E019A6F','F3244557-C16D-472E-BC31-CA403BE81DA0','','Techo inclinado',0)");
        db.execSQL("insert into tbl_catalogsContent values('500E06C8-B4FB-412C-965D-A58FED08C760','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Monto presupuestado destinado al mantenimiento preventivo rutinario',0)");
        db.execSQL("insert into tbl_catalogsContent values('1E5D6FE7-1516-47F5-BC97-A5BA4D31C163','754E0F98-3A4A-4D1B-9B84-135B860A7791','','Media',0)");
        db.execSQL("insert into tbl_catalogsContent values('B001F51B-0787-4A2B-9BF5-A60FF751DAF7','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Instalación sanitaria (saneamiento)',0)");
        db.execSQL("insert into tbl_catalogsContent values('3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Hracán ',0)");
        db.execSQL("insert into tbl_catalogsContent values('8B419E06-D4E4-4092-8C90-A7548B3E58DB','0FD32189-1390-4CB3-9047-92F3EF391E1C','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('5FBEF8C0-9E66-4814-90B5-A7C020071F0B','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','H','Block de barro hueco vitrificado',0)");
        db.execSQL("insert into tbl_catalogsContent values('0E3DAA86-89DF-40FC-BC4D-A85CE7C2621E','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Nivel de Aguas Mínimo de Operación',0)");
        db.execSQL("insert into tbl_catalogsContent values('8033F45A-6EB0-499A-8AA0-A870EAD872D7','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('83288640-F669-435C-9EBA-A88C5AA2F02C','1E1DA417-5EC6-4A03-A13E-08EB93D15E77','','Tunel y compuertas',0)");
        db.execSQL("insert into tbl_catalogsContent values('7EF67AFD-3906-4180-B438-A897DE81829D','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros medianos rigidizada',0)");
        db.execSQL("insert into tbl_catalogsContent values('1E829FEB-B88F-4FC9-B3F9-A92CD6273D75','EA766519-648F-41FB-B429-A21F94758AE8','','Canales',0)");
        db.execSQL("insert into tbl_catalogsContent values('1664D9AD-45CE-4799-A578-AA896ECE572C','443523EE-F532-4577-956B-64E55A7B89BB','','Mejillón',1)");
        db.execSQL("insert into tbl_catalogsContent values('C4D145E3-6699-436F-B8AD-AAABE34DD678','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Ninguno',0)");
        db.execSQL("insert into tbl_catalogsContent values('0E39AF62-2CBD-4879-9105-AAEF8C455442','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red electromecánica (saneamiento)',0)");
        db.execSQL("insert into tbl_catalogsContent values('CC956656-46F5-4ED4-A044-AB05F0E6F1F9','2043ACDA-56F7-40CE-A853-233F470FCB76','','Oficinas',0)");
        db.execSQL("insert into tbl_catalogsContent values('92CFA66C-695A-4220-B584-AB0DC55EA9B9','DB407EA2-A1BB-4254-87C7-6AF894A75AF4','','Depósitos',0)");
        db.execSQL("insert into tbl_catalogsContent values('675D314E-5322-4499-853A-AB8BBADB6200','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Caminos, puentes y túneles',0)");
        db.execSQL("insert into tbl_catalogsContent values('8F05D1C7-2144-48D7-B31E-AC0E46D897B7','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen al NAME',0)");
        db.execSQL("insert into tbl_catalogsContent values('8EABC000-3397-4875-93A4-AC535B367ED1','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('0D989CDA-F74E-4DB3-967D-AC674C2C550D','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Instrumental médico',0)");
        db.execSQL("insert into tbl_catalogsContent values('A00A3B20-3F2E-4DB1-9D64-AD251FB3C5FD','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Bordos',0)");
        db.execSQL("insert into tbl_catalogsContent values('4842F83B-FDAD-4764-B290-AD9A1CF723B7','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Canales de drenaje',0)");
        db.execSQL("insert into tbl_catalogsContent values('BD400EA1-BE2E-4DD6-88B2-ADBF7334E5C3','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Elevación de la corona',0)");
        db.execSQL("insert into tbl_catalogsContent values('CAE59183-13D5-45CF-9825-AE2695E1731A','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Monto presupuestado destinado al mantenimiento preventivo rutinario',0)");
        db.execSQL("insert into tbl_catalogsContent values('39CFCBBB-B016-4685-BC19-AE7E0BB52624','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor promedio de reposición de puentes de menor envergadura',0)");
        db.execSQL("insert into tbl_catalogsContent values('8736B811-A686-493C-B39F-AEBF678C11E2','94E45704-8045-4CF8-B747-763D600789C4','','Capacidad mayor a 100 toneladas por día',0)");
        db.execSQL("insert into tbl_catalogsContent values('56E685AE-B2AE-44F6-A5BD-AF56A0CBBC2F','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor promedio estimado de reconstrucción por km',0)");
        db.execSQL("insert into tbl_catalogsContent values('D144CAAB-10BB-4EC9-A88E-B00C25E0096F','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Vertederos',0)");
        db.execSQL("insert into tbl_catalogsContent values('0A725F1C-F530-4B18-A2BE-B05BB70F9E18','DE4815D5-E7B5-4C48-A694-180F351E1B41','A','A base de muros de carga y refuerzos de concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('C3BE71BB-B881-4A2F-A57E-B0F1DF479E64','A232C4C1-5524-4302-9329-F69D050F9698','','Clínica',0)");
        db.execSQL("insert into tbl_catalogsContent values('30C47124-4A97-4408-A9D0-B101E77006B1','1E1DA417-5EC6-4A03-A13E-08EB93D15E77','','Tunel y válvulas',0)");
        db.execSQL("insert into tbl_catalogsContent values('BC42295A-EB87-4F76-A713-B107362494AD','443523EE-F532-4577-956B-64E55A7B89BB','','Carpa',0)");
        db.execSQL("insert into tbl_catalogsContent values('205EF8B1-11E3-4674-81BB-B108B08806E1','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n3','prueba 3',1)");
        db.execSQL("insert into tbl_catalogsContent values('E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','D2261320-26BD-4560-AD96-0135366285A3','TAM','Tamaulipas ',0)");
        db.execSQL("insert into tbl_catalogsContent values('DA90B6B8-4A96-406D-8D62-B2C0FD3EF7EE','6CB19E01-D41B-4052-9C10-BC5360DD22FE','lvs','Luvisoles',0)");
        db.execSQL("insert into tbl_catalogsContent values('2D09E941-9416-4A8C-9AA9-B2C8581FBCAF','D2261320-26BD-4560-AD96-0135366285A3','TLA','Tlaxcala ',0)");
        db.execSQL("insert into tbl_catalogsContent values('43DBE0B2-C811-471F-8F5E-B3896F6F0EFE','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Otras características',0)");
        db.execSQL("insert into tbl_catalogsContent values('E4B5FDC4-0F64-41F5-9928-B468F23C9C9C','F751B0D0-97F8-402C-9065-077B5136B6D6','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('19CA51CA-EEAE-41E2-8066-B4935E35B1C4','443523EE-F532-4577-956B-64E55A7B89BB','','Camarón',0)");
        db.execSQL("insert into tbl_catalogsContent values('4CCC8F7D-9119-4C1A-BFFB-B4AD80808A79','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Paneles',0)");
        db.execSQL("insert into tbl_catalogsContent values('E82265D7-CAEB-4C37-8EE0-B5889F3A685F','F751B0D0-97F8-402C-9065-077B5136B6D6','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('F4606983-47BB-49C5-96F7-B58AF90DB8F2','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Detalle Otros',0)");
        db.execSQL("insert into tbl_catalogsContent values('00CE65C2-5197-4B19-B0E3-B5A05B8F96F4','234FB73E-843A-4976-BF1F-48318D352CDA','','Si, severos',0)");
        db.execSQL("insert into tbl_catalogsContent values('4D8486AB-1F7B-4400-8DDD-B6068E2471AE','22A44093-7E34-46D9-92DF-57FE3A335E13','','Estaciones depuradoras',0)");
        db.execSQL("insert into tbl_catalogsContent values('4DF63070-33F3-4074-AB72-B75010E268AC','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Derrumbe',0)");
        db.execSQL("insert into tbl_catalogsContent values('5760E0C7-7612-49DA-86CE-B852E04477BD','75B17471-ED30-467C-BA2A-8F8D1D988282','','Distancia',0)");
        db.execSQL("insert into tbl_catalogsContent values('4B00463B-CD5F-4591-9BE4-B87ECDFA5135','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Nivel de Aguas Máximas Extraordinarias',0)");
        db.execSQL("insert into tbl_catalogsContent values('44790418-99C3-4B66-B456-B91F622E9E48','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Ancho de la base',0)");
        db.execSQL("insert into tbl_catalogsContent values('D0EDA2D6-E19D-4444-AB5E-B94EE5C85309','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Decantadores o sedimentadores',0)");
        db.execSQL("insert into tbl_catalogsContent values('D4C94600-E425-4712-BA34-BA2C5C21A905','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros grandes rigidizada',0)");
        db.execSQL("insert into tbl_catalogsContent values('BFEADDF6-7B6F-4D1A-9980-BAAE1339E42C','E43CE3ED-6F48-4668-ABF3-222A8533005B','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('645C5B0C-8427-4750-A76A-BACF8B44B6CC','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Región Hidrológica',0)");
        db.execSQL("insert into tbl_catalogsContent values('AD5FE7F0-2A07-4F24-82ED-BB164668E432','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Cuerpo de presa',0)");
        db.execSQL("insert into tbl_catalogsContent values('D43BD6F5-7F8D-488E-A73E-BB19EE57C76E','443523EE-F532-4577-956B-64E55A7B89BB','','Salmón',0)");
        db.execSQL("insert into tbl_catalogsContent values('73BD7584-2C2D-40C4-A7A1-BCC1DC65C6A8','DB407EA2-A1BB-4254-87C7-6AF894A75AF4','','Embalses',0)");
        db.execSQL("insert into tbl_catalogsContent values('450511D0-7DC6-4157-8420-BCEA1F8E412A','6CB19E01-D41B-4052-9C10-BC5360DD22FE','Gls','Gleysoloes',0)");
        db.execSQL("insert into tbl_catalogsContent values('812CE4EF-783F-4E2B-9218-BD3D3E30D3EA','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor unitario estimado de reposición',0)");
        db.execSQL("insert into tbl_catalogsContent values('5C626B8E-B782-4582-9A44-BD9332C3150A','E43CE3ED-6F48-4668-ABF3-222A8533005B','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('D5AAE3FE-5779-4D9C-8856-BDB9201A463E','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor obra civil',0)");
        db.execSQL("insert into tbl_catalogsContent values('67BA88F5-D327-460C-A96D-BDDC0665A3C6','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Enajenación por asignación directa',0)");
        db.execSQL("insert into tbl_catalogsContent values('DCEE544D-049F-48F3-B205-BE2460E5F476','F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','','Abastecimiento.',0)");
        db.execSQL("insert into tbl_catalogsContent values('A278335A-5C15-4087-8D5F-BF9DED3C7ACB','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Total kilómetros',0)");
        db.execSQL("insert into tbl_catalogsContent values('EC27C80D-D935-4231-BDEE-BFA35B62C4E6','B1137091-9300-4989-BE0F-4D38B9C543C6','','Escuelas',0)");
        db.execSQL("insert into tbl_catalogsContent values('F5F784DD-2BC6-4522-ADA9-BFD708880277','782F77AC-0455-45F8-8B8F-F29C9DC058B9','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('11F98018-15F8-482D-AC33-C000B7B716BF','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('AF0D4D6B-C9C8-48B4-9D32-C00BFA13336D','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Secundaria',0)");
        db.execSQL("insert into tbl_catalogsContent values('D50DAED1-18C1-49B3-BD2D-C0105E3A58E2','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red de alimentación',0)");
        db.execSQL("insert into tbl_catalogsContent values('3AC58692-7E6B-4F77-99C9-C0F402F3BF36','5E0E74BD-7EAE-4257-B19B-89692156AC08','','Medianos > 7 y < 12 mts',0)");
        db.execSQL("insert into tbl_catalogsContent values('F7BD1F33-BEEE-429F-8549-C17D2458E51E','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Altura sobre el calce',0)");
        db.execSQL("insert into tbl_catalogsContent values('0228C44B-185A-45E4-BE8D-C1D984F67670','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Longitud',0)");
        db.execSQL("insert into tbl_catalogsContent values('46FF5ECD-3C14-47DF-953C-C1F52CA65D77','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Depósitos de combustible',0)");
        db.execSQL("insert into tbl_catalogsContent values('79C547A6-9529-418B-B0BD-C214A8C33B14','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('C8E0BE4A-1918-47CE-A381-C25CD0C16B83','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','A','Adobe',0)");
        db.execSQL("insert into tbl_catalogsContent values('1E171CDE-E651-4F48-BA34-C2AB9F5E7AD8','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor de reposición a nuevo',0)");
        db.execSQL("insert into tbl_catalogsContent values('75DF1869-DE9A-4670-8DF6-C2D6563A87A4','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','I','Costaneros de asbesto sobre perfiles metalicos',0)");
        db.execSQL("insert into tbl_catalogsContent values('66F2BFB7-D569-4ED0-8AEB-C30DE3D3E7CA','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Monto presupuestado destinado al mantenimiento correctivo',0)");
        db.execSQL("insert into tbl_catalogsContent values('08839679-93F8-43A0-B0C8-C3AA75147EB6','D2261320-26BD-4560-AD96-0135366285A3','VER','Veracruz',0)");
        db.execSQL("insert into tbl_catalogsContent values('84B02DB6-3D76-47F4-9D85-C41DC13FC94D','54DA8427-56C1-4EC7-A258-FBA21E9303D2','','En suelo y roca',0)");
        db.execSQL("insert into tbl_catalogsContent values('893CA3F9-A621-40B7-92BF-C43A368B1694','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('D3F0A270-9FF5-499B-9F55-C46ACE58A5C9','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Altura del parapeto',0)");
        db.execSQL("insert into tbl_catalogsContent values('65225D21-2C9F-4D9A-8A8B-C5604C12D5CA','E8E42A49-5874-4AE2-9070-8C15EE163201','','Mamposteria',0)");
        db.execSQL("insert into tbl_catalogsContent values('0189AC40-DB42-4466-818B-C66DA492C068','F3244557-C16D-472E-BC31-CA403BE81DA0','','Techo curvo',0)");
        db.execSQL("insert into tbl_catalogsContent values('A9A8D618-EDA0-47DB-9F19-C6D67A5E36D4','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n4','Prueba 4',1)");
        db.execSQL("insert into tbl_catalogsContent values('E40EB25A-3F3E-41AF-9118-C79FA36D1991','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Cimbras',0)");
        db.execSQL("insert into tbl_catalogsContent values('2CBD0817-D3E2-49F7-B2F3-C82C2CCDDD50','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','','Mucha',0)");
        db.execSQL("insert into tbl_catalogsContent values('F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','75B17471-ED30-467C-BA2A-8F8D1D988282','X','Longitude',0)");
        db.execSQL("insert into tbl_catalogsContent values('DA7A7E78-AE66-42DD-B587-C8781061C046','C52AF614-2865-4708-B489-1BE720793BE6','','G.    presa de arco',1)");
        db.execSQL("insert into tbl_catalogsContent values('C4B66D66-D0BC-48EF-B3AB-C8BFAEAAF28F','3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','','Residual',0)");
        db.execSQL("insert into tbl_catalogsContent values('B350B8B1-4B3C-49CD-8609-C922E9918A1D','B56CE9B9-A793-4C6E-995D-EB7D6490F71E','','Nula',0)");
        db.execSQL("insert into tbl_catalogsContent values('38750791-01FB-413F-BB6A-C9AB7CEC36C5','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Geometría regular',0)");
        db.execSQL("insert into tbl_catalogsContent values('79516D64-A265-4CE6-9348-CA10D9652EE6','4F217F72-98B8-445F-AF8C-396B2B06671B','D','En puertas y ventanas perfiles de aluminio anodizado en color',0)");
        db.execSQL("insert into tbl_catalogsContent values('BC688C66-B0FE-4FF7-A006-CA1EF89D2321','76A8D090-EB15-4600-ADB5-9F68043E46A5','','Puente',0)");
        db.execSQL("insert into tbl_catalogsContent values('8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','D2261320-26BD-4560-AD96-0135366285A3','YUC','Yucatán',0)");
        db.execSQL("insert into tbl_catalogsContent values('E8A3186E-D0F1-4C18-9BCE-CA4E4870F1C7','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Num Puentes',0)");
        db.execSQL("insert into tbl_catalogsContent values('A38D04BF-44E1-4EBD-9FC0-CA7E809862DF','1B44E444-42A5-463B-AC94-0FA2DAFCB2D8','','Ligera (lamina)',0)");
        db.execSQL("insert into tbl_catalogsContent values('BAE65756-D9B9-4373-B505-CB0E1632E377','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Equipos de diagnósitoco y laboratorio',0)");
        db.execSQL("insert into tbl_catalogsContent values('12852E10-512B-4C15-9B65-CB45B77E4D7B','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Desvio temporal',0)");
        db.execSQL("insert into tbl_catalogsContent values('2CA2B76D-138B-493D-80C9-CB510071E89B','5E0E74BD-7EAE-4257-B19B-89692156AC08','','Cortos < 7 mts',0)");
        db.execSQL("insert into tbl_catalogsContent values('8A95F972-8730-4C42-9210-CB78E5FF3836','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','ExistenObjetos en azotea',0)");
        db.execSQL("insert into tbl_catalogsContent values('DAE92CF4-36BB-4186-946F-CC2C67FC1E95','0D4FEBCD-D28A-4293-9065-A5A906BF6E69','','De 10 a 15 m',0)");
        db.execSQL("insert into tbl_catalogsContent values('E80C6CC0-C386-4655-80A0-CC2F1D4D3AF0','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Cancelería',0)");
        db.execSQL("insert into tbl_catalogsContent values('57E87AC8-BACE-431B-8409-CC8E3DA7F368','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Bocatomas de derivación',0)");
        db.execSQL("insert into tbl_catalogsContent values('2A6946F8-6103-4793-90B5-CD00B11F5226','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','K','Costaneros de lamina pintro sobre perfiles metálicos',0)");
        db.execSQL("insert into tbl_catalogsContent values('ACD6980D-0C78-4EFE-B8F8-CD41415DD286','1E1DA417-5EC6-4A03-A13E-08EB93D15E77','','Torre-galería',0)");
        db.execSQL("insert into tbl_catalogsContent values('5DE56387-077D-48F4-8638-CD4B4C7CF639','6DAC2E76-3A32-401C-BBEB-1CC3CDA7E428','','Canal de riego con dren --> puente canal',0)");
        db.execSQL("insert into tbl_catalogsContent values('F5758A40-90CC-4012-93B7-CD828FF961C9','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Depresiones',0)");
        db.execSQL("insert into tbl_catalogsContent values('FFB983D3-BD84-45CA-B23A-CDCAB4FB6FC4','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Tipo de túnel',0)");
        db.execSQL("insert into tbl_catalogsContent values('C260A1F1-21CC-4654-9422-CE265565928E','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('E8776B49-033B-45AF-BDF8-CE3DA9D76470','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Tuneles',0)");
        db.execSQL("insert into tbl_catalogsContent values('030908EE-7446-4290-93F1-CE775B99CE2E','539D1830-90A8-484C-B183-F79A448CEC78','','B.3. canal lateral',0)");
        db.execSQL("insert into tbl_catalogsContent values('5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Elevación de desplante de la planta baja',0)");
        db.execSQL("insert into tbl_catalogsContent values('FCDE3AC9-F416-4EB0-B9F4-CFE0B51C6500','7EB43883-335D-4184-86F3-D899EB2F6784','','Homogenea de tierra',0)");
        db.execSQL("insert into tbl_catalogsContent values('E93721DC-206C-4918-9638-CFEDFA047031','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Oficinas administrativas de sistemas de salud municipal y estatal',0)");
        db.execSQL("insert into tbl_catalogsContent values('86267EF7-9149-4E12-97D8-D0013249417B','F4DBA965-B5D9-4A89-93A2-C55850E82FFB','','Distante a la cortina (indicar distancia)',0)");
        db.execSQL("insert into tbl_catalogsContent values('23806607-AF02-4E82-B28D-D011783F0EB3','12634A22-8918-4A1C-B2DC-BE4C827735CA','','Madera',0)");
        db.execSQL("insert into tbl_catalogsContent values('08D5E6FA-D3BE-4E4A-8B0F-D022FCD1BC3C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red electromecánica',0)");
        db.execSQL("insert into tbl_catalogsContent values('8F29339F-3AC7-446E-9142-D0637466054A','F4DBA965-B5D9-4A89-93A2-C55850E82FFB','','Subterránea',0)");
        db.execSQL("insert into tbl_catalogsContent values('85B9E0DC-7F12-49B1-849A-D09F2404AF7C','07C3B108-6581-4DA7-85BC-FF6263924E99','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('729063FB-EA8D-4346-A5DF-D0BC1242ECB4','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Techumbres',0)");
        db.execSQL("insert into tbl_catalogsContent values('7296391B-93F8-45D0-986B-D1819E43CF0D','0FD32189-1390-4CB3-9047-92F3EF391E1C','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('1507EDE4-43A0-446B-B301-D2101DD49DD2','0E28A695-8644-4E13-92D1-F32EAEEEA29A','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('45DB1D54-05AC-4920-8313-D27183F52AAF','F2C11D82-03E7-4771-9231-31283AD1D519','','De menor y mator altura',0)");
        db.execSQL("insert into tbl_catalogsContent values('39298EC3-EA9B-4D99-931A-D2A58DCFFD86','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Brecha',0)");
        db.execSQL("insert into tbl_catalogsContent values('D9D28390-F7A7-4794-8909-D39281E89C03','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Pintura',0)");
        db.execSQL("insert into tbl_catalogsContent values('65191D5A-49AF-41EA-8A03-D3999F9A7FD7','F2C11D82-03E7-4771-9231-31283AD1D519','','De igual altura',0)");
        db.execSQL("insert into tbl_catalogsContent values('0E815275-F782-4EB0-A254-D3CC7E16A6BE','B504418E-8F8D-4086-8E59-CD615707E5FB','','Escalera de peces',0)");
        db.execSQL("insert into tbl_catalogsContent values('B59B7EFB-42BB-4640-B301-D3CF1DDCC3F5','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Túneles o ductos de conducción',0)");
        db.execSQL("insert into tbl_catalogsContent values('3AA6E5A2-A138-4BD6-9132-D4793FACE572','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Detalle de irregularidad',0)");
        db.execSQL("insert into tbl_catalogsContent values('7EBF93C4-F1BB-481C-B82F-D48A91794D12','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('92E3D299-AC40-4B52-867E-D537E1E77B57','F2C11D82-03E7-4771-9231-31283AD1D519','','De menor altura',0)");
        db.execSQL("insert into tbl_catalogsContent values('7FF87226-25E6-47DB-8BE8-D58081F851FD','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen al NAMO',0)");
        db.execSQL("insert into tbl_catalogsContent values('733E4658-52B3-4E64-B526-D5C9EA0ECA9B','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Bocatoma',0)");
        db.execSQL("insert into tbl_catalogsContent values('CB3F18B5-6FB1-46FC-807E-D5E29DFFDDF0','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','Colgante',0)");
        db.execSQL("insert into tbl_catalogsContent values('FAB78DE3-AB97-4BB7-91B7-D5E4BF7FA7B1','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Cortina',0)");
        db.execSQL("insert into tbl_catalogsContent values('204266D3-3109-432D-AE4F-D679CC4EE903','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('2C1D1D7D-1407-42AB-A22C-D73159051F4A','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Acuícola',0)");
        db.execSQL("insert into tbl_catalogsContent values('97D7A91E-3544-4C1E-B3B0-D785EE8FF350','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor unitario estimado de reposición',0)");
        db.execSQL("insert into tbl_catalogsContent values('9F81001A-AD8E-4CCB-8F7D-D7C2035F2CBC','7EB43883-335D-4184-86F3-D899EB2F6784','','Bloques labrados',0)");
        db.execSQL("insert into tbl_catalogsContent values('8EA8FFD6-5722-4823-B9CB-D90AA6DEC587','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Altura máxima',0)");
        db.execSQL("insert into tbl_catalogsContent values('5318380B-145B-442E-8F30-DA2868331399','4F1CFD2A-E24E-4AD5-B2C6-FC20367D0FFC','','Río',0)");
        db.execSQL("insert into tbl_catalogsContent values('1B034109-50B9-44D6-B6EA-DB0AB08496F2','3585818D-7660-4569-A24F-F5F0999D4705','E','Muertos de concreto simple',0)");
        db.execSQL("insert into tbl_catalogsContent values('2281FC61-C336-4C63-82FE-DB11E8ED752F','8EB882ED-6597-4111-ACD1-0DFF899B2BD8','','Extinguidores',0)");
        db.execSQL("insert into tbl_catalogsContent values('A48C6EA3-C71E-4CB4-A161-DC3C8A7DA95D','C52AF614-2865-4708-B489-1BE720793BE6','','B.    escollera',1)");
        db.execSQL("insert into tbl_catalogsContent values('DCE35523-84B1-41D0-B8BA-DC558F562C9E','F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','','Riegos',0)");
        db.execSQL("insert into tbl_catalogsContent values('C08E5B7F-0114-41FD-9A90-DCADE741C8E8','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Geometría irregular',0)");
        db.execSQL("insert into tbl_catalogsContent values('2891F3E2-2970-4EBB-B00C-DD4B379BF6EF','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('24431C2E-E8D0-4EFD-812C-DD7C02D37392','54DA8427-56C1-4EC7-A258-FBA21E9303D2','','En suelo',0)");
        db.execSQL("insert into tbl_catalogsContent values('03FCF704-BCFF-460B-B8B0-DD7C4537376F','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('2E8CE8FC-DEBD-40BA-9F5A-DD834CCBCEF0','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Techumbres',0)");
        db.execSQL("insert into tbl_catalogsContent values('F8254142-FC4C-48AF-B4EC-DD8D0DAD7D15','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones saturadas',0)");
        db.execSQL("insert into tbl_catalogsContent values('417E779A-78C5-45A6-A238-DDCBD482FEA9','07C3B108-6581-4DA7-85BC-FF6263924E99','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('29751529-FCEF-4FBF-B399-DE0225703908','5E0E74BD-7EAE-4257-B19B-89692156AC08','','Largos > 12 mts',0)");
        db.execSQL("insert into tbl_catalogsContent values('78C3C3F1-575E-4927-8D49-DE686946E3D6','3585818D-7660-4569-A24F-F5F0999D4705','C','Losa de cimentación de concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('1FAB1802-29ED-4B65-A1A9-DE98F62CAFAA','4F217F72-98B8-445F-AF8C-396B2B06671B','A','En puertas y ventanas perfiles metalicos estructurales',0)");
        db.execSQL("insert into tbl_catalogsContent values('F4B048C4-0A44-4E54-9E01-DE9E2C2F1DC9','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor promedio de reposición de puentes de menor envergadura',0)");
        db.execSQL("insert into tbl_catalogsContent values('C71BB043-E793-4689-BBC8-DFB58291C010','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Dispositivos móviles de riego por aspersión',0)");
        db.execSQL("insert into tbl_catalogsContent values('A26A5A87-7149-435C-B7B7-E008663D3C9A','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Madera',0)");
        db.execSQL("insert into tbl_catalogsContent values('81BE09A2-7E94-4C0B-8611-E03F1CA38794','C52AF614-2865-4708-B489-1BE720793BE6','','E.     presa de bóvedas múltiples',0)");
        db.execSQL("insert into tbl_catalogsContent values('F7F9E1A6-8CC0-4391-9B50-E089DABECB34','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','E','Ruinosa',0)");
        db.execSQL("insert into tbl_catalogsContent values('DCF27175-EE31-45C5-B91E-E0BF7C10AEEA','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Pintura',0)");
        db.execSQL("insert into tbl_catalogsContent values('79BFB7AF-1B31-4088-BF61-E0DAEF07FB83','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Red de abastecimiento de energía eléctrica',0)");
        db.execSQL("insert into tbl_catalogsContent values('6419CE4B-D535-4BCE-AF9F-E0DBB3AC3F3B','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Número de cuerpos',0)");
        db.execSQL("insert into tbl_catalogsContent values('87EB66D2-EA84-4358-AFD4-E14962854AB7','E43CE3ED-6F48-4668-ABF3-222A8533005B','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('8C3626DA-562E-4835-98CF-E1A937767C5A','A232C4C1-5524-4302-9329-F69D050F9698','','Bodega',0)");
        db.execSQL("insert into tbl_catalogsContent values('B442A62B-DFFD-4254-AE88-E1CF8ADBC2A5','539D1830-90A8-484C-B183-F79A448CEC78','','A. cimacio tipo creager',0)");
        db.execSQL("insert into tbl_catalogsContent values('57552A4E-9AB7-4AB7-AA2F-E21712653957','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Otros',0)");
        db.execSQL("insert into tbl_catalogsContent values('04F58B3F-745A-49CD-AA8F-E28840386E00','CA38F79C-B87A-4B9B-BC1D-2002B2658C9B','','Poca',0)");
        db.execSQL("insert into tbl_catalogsContent values('D0EEDDA4-5B01-4E64-A244-E3BD489C5EE8','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red electromecánica (obras de conducción y distribución)',0)");
        db.execSQL("insert into tbl_catalogsContent values('2D9DE949-A018-4E35-B2EE-E425AEE8417C','8831E933-E4BB-47DF-A0FA-139EC6495C73','','Vertedor de pozo',0)");
        db.execSQL("insert into tbl_catalogsContent values('58D09D38-3A21-4512-AD31-E442718788D0','443523EE-F532-4577-956B-64E55A7B89BB','','Robalo',0)");
        db.execSQL("insert into tbl_catalogsContent values('DF9D370B-55C4-4CC5-89BA-E4E315F5665A','0FD32189-1390-4CB3-9047-92F3EF391E1C','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('A102999C-E84E-4A90-B910-E52A499BBDD6','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones en limo-arcilla',0)");
        db.execSQL("insert into tbl_catalogsContent values('AE30C03C-005C-4F7C-85EF-E5439B932982','4F1CFD2A-E24E-4AD5-B2C6-FC20367D0FFC','','Manantial',0)");
        db.execSQL("insert into tbl_catalogsContent values('4FBB6137-9322-4C6E-8AAB-E5471DF59F20','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','NumPasos superiores',0)");
        db.execSQL("insert into tbl_catalogsContent values('549D9B9D-CEC5-43F4-AB00-E5FE4B8B4EFC','4F217F72-98B8-445F-AF8C-396B2B06671B','C','En puertas y ventanas perfiles de aluminio blanco',1)");
        db.execSQL("insert into tbl_catalogsContent values('E3A00057-7CAA-4DC4-8C1F-E687F10AE76B','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Pozos',0)");
        db.execSQL("insert into tbl_catalogsContent values('D99AB80F-6E10-488E-8F1E-E6B09BBFC9C9','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Instrucciones especiales de oepración',0)");
        db.execSQL("insert into tbl_catalogsContent values('3771EE8D-1F6F-428F-BA91-E6F4B12BB8A0','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Cds',0)");
        db.execSQL("insert into tbl_catalogsContent values('379A69FE-806F-43A7-A4C2-E72DBEA27B20','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Almacenes',0)");
        db.execSQL("insert into tbl_catalogsContent values('C37B98C5-CCC6-4B8D-858F-E7D8E1513CC6','B56CE9B9-A793-4C6E-995D-EB7D6490F71E','','Poca',0)");
        db.execSQL("insert into tbl_catalogsContent values('226D3BEC-B08D-473C-8C24-E80FE3A0B771','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Sismo',0)");
        db.execSQL("insert into tbl_catalogsContent values('1378BDB0-474D-4CA5-B894-E89C2FB01A1B','782F77AC-0455-45F8-8B8F-F29C9DC058B9','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('D066282F-5BB9-485D-AEB7-E89D3568DCAD','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','I','Cristal de 6mm. biselado',0)");
        db.execSQL("insert into tbl_catalogsContent values('4421CCC8-0AC6-4D35-A69B-E8C4815B626E','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Obras de toma',0)");
        db.execSQL("insert into tbl_catalogsContent values('DE39610E-E351-4AB4-9420-E92A70FDE789','1ACD8B10-3A71-4481-ABEA-70E91B70B53A','','Suelos de grano fino (e.g. limos y arcillas)',0)");
        db.execSQL("insert into tbl_catalogsContent values('5BB50BB8-CEE1-49D8-83A6-E9D8E2EAA35B','E8E42A49-5874-4AE2-9070-8C15EE163201','','Plastico',0)");
        db.execSQL("insert into tbl_catalogsContent values('6D01807F-124C-481D-AF36-E9ED617DB05D','0FD32189-1390-4CB3-9047-92F3EF391E1C','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('DE7386AB-91E7-415E-B58B-EAE09CCCDCEF','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Deslave',0)");
        db.execSQL("insert into tbl_catalogsContent values('D94C5681-51CE-4467-A4C4-EB791072742A','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('B0C6DD01-FF6A-49D8-92D6-EBC6FB6EF0CA','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen de avenida máxima registrada',0)");
        db.execSQL("insert into tbl_catalogsContent values('EA0C637F-F7E2-4F47-BB03-EC719BA7475D','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Nivel de Aguas Máximas Ordinarias',0)");
        db.execSQL("insert into tbl_catalogsContent values('98DA7E9F-8377-46A0-92F7-EC7535A5667F','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','Atirantado',0)");
        db.execSQL("insert into tbl_catalogsContent values('4083D9B1-C947-4971-9584-EC8B371295CE','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor obra civil',0)");
        db.execSQL("insert into tbl_catalogsContent values('5D96A958-63F4-497C-9C5B-EC973DEBE3C6','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','','Almacenamiento',0)");
        db.execSQL("insert into tbl_catalogsContent values('4FACD655-FCB4-4734-9987-ECF17A367D21','81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','','Gravedad',0)");
        db.execSQL("insert into tbl_catalogsContent values('76F3AC4C-0A22-45BF-9456-ED0D317FA369','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('1524E843-BEBE-4D0E-92E5-ED1AC5CA0EFF','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','','Hipocloradores',0)");
        db.execSQL("insert into tbl_catalogsContent values('7FD12A6E-A23B-4E13-9618-ED5BAB4807DD','3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','','Sedimentario',0)");
        db.execSQL("insert into tbl_catalogsContent values('017C56AF-4B38-43D7-96E9-ED64BB58786C','F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','','Uso mixto.',0)");
        db.execSQL("insert into tbl_catalogsContent values('F47629FB-4D92-40AC-9861-ED989610FDAC','3585818D-7660-4569-A24F-F5F0999D4705','B','Zapatas aisladas, dados, contratrabes de concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('EF279DAE-7F58-425B-B517-EDAC96F5B338','94E45704-8045-4CF8-B747-763D600789C4','','Capacidad de menor de 10 toneladas por día',0)");
        db.execSQL("insert into tbl_catalogsContent values('F83A9BA0-6D0C-4C1B-AAE7-EDB04056ABF3','D2261320-26BD-4560-AD96-0135366285A3','ZAC','Zacatecas ',0)");
        db.execSQL("insert into tbl_catalogsContent values('1C5D6DBF-3D7A-4109-B209-EE743EEC9ACA','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','De arco en acero',0)");
        db.execSQL("insert into tbl_catalogsContent values('C28872F2-BAEB-41AE-85C7-EE7918A618CD','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Reja para el desbaste y la retención de finos',0)");
        db.execSQL("insert into tbl_catalogsContent values('04CBA926-49D4-4ACD-A1CE-EE8962505356','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Pintura',0)");
        db.execSQL("insert into tbl_catalogsContent values('FCBB3903-A20C-498C-B7F8-EE978F6FF769','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Organismo responsable',0)");
        db.execSQL("insert into tbl_catalogsContent values('1BA1A59F-CB38-41D1-83C1-EEB4D105EF1E','2043ACDA-56F7-40CE-A853-233F470FCB76','','Báscula',0)");
        db.execSQL("insert into tbl_catalogsContent values('2FF330C5-F6ED-443E-9310-EF398E763FCF','539D1830-90A8-484C-B183-F79A448CEC78','','B.1. económico o lavadero',0)");
        db.execSQL("insert into tbl_catalogsContent values('CE2343ED-DD2F-4390-BFC5-EFD56A08F6E7','68A049E3-4B55-4F48-AE03-A6CB920028B8','','Río',0)");
        db.execSQL("insert into tbl_catalogsContent values('F891E369-8570-42AE-883C-F105D840F9C9','B504418E-8F8D-4086-8E59-CD615707E5FB','','Túnel de derivación',0)");
        db.execSQL("insert into tbl_catalogsContent values('C4665A95-5979-4C2F-A83B-F196A05068EE','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Anuncios espectaculares',0)");
        db.execSQL("insert into tbl_catalogsContent values('132AB919-C75C-4878-8F05-F1EF8A436263','54DA8427-56C1-4EC7-A258-FBA21E9303D2','','En roca',0)");
        db.execSQL("insert into tbl_catalogsContent values('524C7CFF-DFCD-4976-901A-F272F7616254','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Equipo de telecomunicaciones',0)");
        db.execSQL("insert into tbl_catalogsContent values('C30E3F8D-CBD3-42B0-966B-F2DF8BC04C3B','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('07B1CC81-92D5-4F55-8A56-F2F5E0F7B791','B5A4C0E7-FF66-4988-A957-F8FC519E2895','','Otro tipo',0)");
        db.execSQL("insert into tbl_catalogsContent values('40F69BB2-AAC5-4197-9808-F3EF069E1053','8831E933-E4BB-47DF-A0FA-139EC6495C73','','Cortado en la roca adyacente a la cortina',0)");
        db.execSQL("insert into tbl_catalogsContent values('DBE30BC0-C58B-41CB-B343-F40D18AC6355','EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','','Arbolados, lomerios, barrio residencial',0)");
        db.execSQL("insert into tbl_catalogsContent values('4089A68B-73B1-4C3A-96D2-F4D671F7E16D','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','','Control de afluentes',0)");
        db.execSQL("insert into tbl_catalogsContent values('E0825274-57D0-43C4-9209-F52C7F2D7287','1758A063-FF75-4AAB-9D43-6FDD89946A54','','Monotubo',0)");
        db.execSQL("insert into tbl_catalogsContent values('23A11090-4CD0-4256-A285-F56441CD5E75','443523EE-F532-4577-956B-64E55A7B89BB','','Corvina',0)");
        db.execSQL("insert into tbl_catalogsContent values('E960A7BF-A572-4D63-9405-F5920E785210','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen de avenida Diseño',0)");
        db.execSQL("insert into tbl_catalogsContent values('A00BC745-DA49-4D7F-9068-F60AADF9B397','089475EF-D242-4F7B-9C0C-CBFE98BB480D','','Medianos >.25 y <1 m2',0)");
        db.execSQL("insert into tbl_catalogsContent values('632E5F23-427B-4AC3-85B9-F612B4064EBD','2043ACDA-56F7-40CE-A853-233F470FCB76','','Caminos de acceso',0)");
        db.execSQL("insert into tbl_catalogsContent values('115D7CEF-07C0-4427-BCFB-F62A351D9A3B','75B17471-ED30-467C-BA2A-8F8D1D988282','H','Altitude',0)");
        db.execSQL("insert into tbl_catalogsContent values('2B813A7C-8D89-4E08-8D86-F63AE2442A60','C52AF614-2865-4708-B489-1BE720793BE6','','A.     terraplén',0)");
        db.execSQL("insert into tbl_catalogsContent values('95566830-EFF4-4048-9175-F6ACF22C3ABA','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor maquinara y equipo',0)");
        db.execSQL("insert into tbl_catalogsContent values('0F94528A-4CB2-4378-A2CB-F6B491BD488B','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','M','Spancret o similar',0)");
        db.execSQL("insert into tbl_catalogsContent values('0F3D9596-69B3-4572-8605-F7474FF558D3','75B17471-ED30-467C-BA2A-8F8D1D988282','S','StateID',0)");
        db.execSQL("insert into tbl_catalogsContent values('3D57AD96-3FCC-4D91-9540-F79F46C8D33F','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Domos',0)");
        db.execSQL("insert into tbl_catalogsContent values('63DFF92C-7420-4AAC-85BC-F7A358FCDA79','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','A','Nueva',0)");
        db.execSQL("insert into tbl_catalogsContent values('B0BDE85B-5FE6-42EB-85D8-F82F8815D5A9','782F77AC-0455-45F8-8B8F-F29C9DC058B9','C','Buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('87029EB0-680B-4057-8ED2-F886E74C5947','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Pozos de inspección',0)");
        db.execSQL("insert into tbl_catalogsContent values('4B88AB8E-70BD-4E91-9EE4-F8988F919DE4','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Vertederos',0)");
        db.execSQL("insert into tbl_catalogsContent values('88EC99E7-FB7C-44E5-A91A-F8E4CC3EF71B','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Mnto destinado anualmente a mantenimiento ',0)");
        db.execSQL("insert into tbl_catalogsContent values('DAEA5CE9-FFC2-4F92-A94F-F92CB92EF07E','782F77AC-0455-45F8-8B8F-F29C9DC058B9','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('0D2A6CDF-C437-4910-881E-F996D6EB7676','84987039-D458-480B-8B34-0C7AEE808206','','Concreto armado',0)");
        db.execSQL("insert into tbl_catalogsContent values('BD6B4922-321D-4A57-9ED2-FA9A74B41CE1','D2261320-26BD-4560-AD96-0135366285A3','DIF','Distrito Federal ',0)");
        db.execSQL("insert into tbl_catalogsContent values('9E4EFA91-A546-4DA4-B760-FAA5C74D1401','E8E42A49-5874-4AE2-9070-8C15EE163201','','Vidrio',0)");
        db.execSQL("insert into tbl_catalogsContent values('E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Edificio',0)");
        db.execSQL("insert into tbl_catalogsContent values('84D2BAA4-800E-480A-BEC7-FAF59FDFBCC7','443523EE-F532-4577-956B-64E55A7B89BB','','Langosta',0)");
        db.execSQL("insert into tbl_catalogsContent values('8B6B976C-13DD-462F-9359-FAFE07940FC5','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Túneles o ductos de conducción',0)");
        db.execSQL("insert into tbl_catalogsContent values('AC855C6E-6189-41CB-BDB2-FB22A6B5DD85','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','D','Mala',0)");
        db.execSQL("insert into tbl_catalogsContent values('268F8011-963B-4FE6-B8AD-FB7E17770FB0','7EB43883-335D-4184-86F3-D899EB2F6784','','Enrocamiento con hormigón',0)");
        db.execSQL("insert into tbl_catalogsContent values('E6C1B3A9-0FE0-44B9-99FF-FB87A00F4AD3','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','C','Block hueco (cemento-arena)',0)");
        db.execSQL("insert into tbl_catalogsContent values('DDC52FD1-790E-4686-AD8C-FBC23E1F780A','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','','Derivadoras',0)");
        db.execSQL("insert into tbl_catalogsContent values('F19AC24F-F1D8-4349-BCF1-FCF3C79D1047','DB407EA2-A1BB-4254-87C7-6AF894A75AF4','','Tratamiento',0)");
        db.execSQL("insert into tbl_catalogsContent values('6CDF9CEA-0317-43FE-9644-FD774CC779D3','15641A0F-51F0-4969-AD95-90EF456AFE13','B','Muy buena',0)");
        db.execSQL("insert into tbl_catalogsContent values('58482DFF-E530-4C58-B04C-FD819B5DE8D0','B504418E-8F8D-4086-8E59-CD615707E5FB','','Cuencas de disipación',0)");
        db.execSQL("insert into tbl_catalogsContent values('9703AFF7-BFCD-4246-A074-FE317F846787','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Edificio',0)");
        db.execSQL("insert into tbl_catalogsContent values('E50940EC-4701-4573-89A2-FE8DF7FE5FAF','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Obras de toma',0)");
        db.execSQL("insert into tbl_catalogsContent values('6C27DCF0-6A78-44FC-B8FB-FED854952651','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Área de almacenamiento de insumos y de la producción',0)");
        db.execSQL("insert into tbl_catalogsContent values('8418AEAF-CEA8-49A9-B90F-FF18DE8A3199','539D1830-90A8-484C-B183-F79A448CEC78','','A.3. canal lateral',0)");
        db.execSQL("insert into tbl_catalogsContent values('C67C16CB-C192-4171-9085-FF85DCB3FF12','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Lerma santiago pacífico',0)");
        db.execSQL("insert into tbl_catalogsContent values('42E3FD97-66C9-48FE-BE4B-FFF08F878CD6','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n1','Prueba 2',0)");
    }

    public void insertInto_TBL_CatalogsContent(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('00000000-0000-0000-0000-000000000000','00000000-0000-0000-0000-000000000000','','Ninguno', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5C34B042-7F2C-4AED-B764-011F60BC1110','EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','','Muy accidentada. centro de la ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B4E5D36A-1D9B-443E-8D1D-013167C66E0E','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Depósitos o Tanques de retenci', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E4886740-2AA3-4DF7-8AD5-016E0A02E110','D2261320-26BD-4560-AD96-0135366285A3','AGU','Aguascalientes ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('40F910EA-B105-4A72-93BD-01EDAAA47939','EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','','Promontorio, colinas, montañas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DF7C6571-FADE-4290-A3E5-01FA967E23EA','07C3B108-6581-4DA7-85BC-FF6263924E99','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('12F2C6E5-6026-48D3-B45C-024A396BB119','68A049E3-4B55-4F48-AE03-A6CB920028B8','','Laguna', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0D0F0F70-88D5-42EB-BFC7-02A067C5B4FD','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Puente principal', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FCBEBA65-5197-436B-9333-0303737A101B','15641A0F-51F0-4969-AD95-90EF456AFE13','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('15B81355-B8D8-4AA5-8141-030F824F9AA3','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','dsf','Dosificadores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C72F6A69-6C38-4FAB-AE0D-038BDF7F9321','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Desfogues', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7567E762-5B28-4833-A61A-0418AB8B7CAE','2043ACDA-56F7-40CE-A853-233F470FCB76','','Agua potable, electricidad y d', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C0CAC8CC-5B88-420C-94CF-045FB4266973','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Estación de bombeo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B1C48FCD-BC79-47C3-951F-04689E2C63D3','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Ancho de vía', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E92609D1-815A-48D3-B5B8-049D3DE8E713','DE4815D5-E7B5-4C48-A694-180F351E1B41','E','Metálica a base de marcos tipo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E4D1D20F-AE2F-4DD3-9F66-04ADB9549B6F','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Enajenación en licitación por ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F1C41B62-4F08-4050-A6BD-04EACC827E15','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Malla ciclónica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CF7AEF27-292A-44CE-8628-052647F28468','12634A22-8918-4A1C-B2DC-BE4C827735CA','','Metal', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EEE9CBEB-A9F0-422D-9D4C-06A5152A58C1','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','L','Multipanel', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8B592765-8BAB-4388-A1B4-06C0DBD9ECB1','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','F','Cristal de 6mm. polarizado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0B758DA7-0998-469D-A7E0-06F674E9FEB6','B374ABA0-9C03-4916-ADE8-0BA48161CE05','','Terracería', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8EC64169-4982-4D59-AB22-078E1D868C9F','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Talleres', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('30F63AA1-7E28-4991-9050-07A2462C5F85','75B17471-ED30-467C-BA2A-8F8D1D988282','','Percipitación máxima mensual', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('48A062CF-1DEF-41A8-B3C4-07C9F7965C08','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','Puente en ménsula', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('83B8352C-8CB0-4ACE-BDE0-083B4B38471F','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','B','Aplanadas, acabado rustico, pi', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F80BC205-AC04-4821-85E3-09306045B435','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Rural', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3F5770ED-26FF-40B0-85CF-0956BF285147','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Aliviadero', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('62EE6E39-FAA0-43E7-A167-0976F2B7443B','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Geometría regular', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BDD1C727-2C66-4E1E-82FC-09C38A554164','B504418E-8F8D-4086-8E59-CD615707E5FB','','Obras provisionales durante la', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('947076EC-48E0-4C26-9F88-0A49DE1970E3','7EB43883-335D-4184-86F3-D899EB2F6784','','Hormigón', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C9C512BE-E033-4C87-BE67-0A89E9B33D20','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Dispositivos de seguridad', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1086726A-37AE-43F1-AD77-0AABF2A3AE8D','E8E42A49-5874-4AE2-9070-8C15EE163201','','Otros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8087E024-AC00-4B31-A678-0AD0E73061F3','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Casa de máquinas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E9257515-B9F0-4FDC-858F-0B0FE2A8B14D','F751B0D0-97F8-402C-9065-077B5136B6D6','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1CB48385-7879-4E03-B0AE-0B52F8DC7F1F','DB407EA2-A1BB-4254-87C7-6AF894A75AF4','','Distribución', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BE2FD0F8-68CD-4586-9055-0B66C33A1D2C','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Capacidad del cauce', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('71823EDB-3947-4024-BE13-0B8352A2B713','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Enseres', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F3AF4BE3-1C0F-4DAE-AC10-0B92D827624D','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Nombre Común', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E3E9B5D8-66AF-49A5-8576-0B9F97A118B2','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Instalación sanitaria (saneami', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('51C8A233-0790-4DBE-9540-0BB3AA511FF3','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Instalaciones electromecánicas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('82CD4EBE-D257-400E-A607-0BD5D3A41CF4','EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','','Campo abierto plano', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A1A8691D-6BBE-47D1-A749-0D6C43FBBB6A','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Bombas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('70B12BA9-DB20-4665-8DA5-0DAA2D0D2AA9','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Diques', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('997387A7-DE6C-4E3B-975D-0E5E63545843','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Embalse', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0D6FB893-959C-49FC-A0AF-0E874F41BDEB','B1137091-9300-4989-BE0F-4D38B9C543C6','','Campos deportivos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('664B3CC3-031D-4CB6-8C66-0E9AF34465F0','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','NumPasos subterraneos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('05CCD55D-B4C8-4903-BA01-0E9E6AB32DBF','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Canales', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6E2B3EB1-E70D-43D3-83C4-0F70DDC11BB6','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Primaria', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('24DDF93E-A50A-4296-B248-0F9910098A33','D2261320-26BD-4560-AD96-0135366285A3','BCN','Baja California ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('699DEBA9-416D-4BAD-81C3-104823FD6801','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Enajenación en licitación públ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('947273D5-0A4E-45C7-86AD-10F7848A195B','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','','Tanques apoyados en el suelo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3002ECE2-0C0B-49FE-BFB6-110B47BE2B91','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Antenas parabolicas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('224BD51D-2007-4452-A26F-112EAC580DC5','F751B0D0-97F8-402C-9065-077B5136B6D6','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4261EE86-3A7A-43A6-BE02-116A12C78499','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Otros medios de almacenamiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('53454B12-5024-459D-9A02-118AA074CDF4','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Obras de drenaje', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('008F0F4F-37F7-472D-BF61-11EFE2B077E4','3585818D-7660-4569-A24F-F5F0999D4705','D','Pilotes de concreto y losa de ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('41C333F3-1A40-471D-B997-121D0B472E37','15641A0F-51F0-4969-AD95-90EF456AFE13','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8C3802A8-8BE2-4DEF-BA88-126E5FAB954C','E8E42A49-5874-4AE2-9070-8C15EE163201','','Material ligero', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DAB02A9A-8A91-463F-BA49-129A0E6E1389','D2261320-26BD-4560-AD96-0135366285A3','BCS','Baja California Sur ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B03ADF5D-634F-4B09-8C91-12B1E9D8302D','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Aspersores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('18B65E0C-7C41-480A-8D19-130624114EF7','D2261320-26BD-4560-AD96-0135366285A3','CAM','Campeche ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0BC41175-42AF-4305-845A-1463D6E48290','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Balsas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('046A83CE-043B-41AD-A7D5-148BDD788E1D','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red de conducción de agua', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('50201AC6-2234-484E-8D1D-1512E394EF5E','DE4815D5-E7B5-4C48-A694-180F351E1B41','G','De madera en columnas, armadur', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('09636565-3915-4200-98BD-15B80C86DCA1','F751B0D0-97F8-402C-9065-077B5136B6D6','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9CE95B9F-CAAA-49BE-9019-16C624CF2053','443523EE-F532-4577-956B-64E55A7B89BB','','Almeja', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('132637B9-C82D-4193-ADF8-16E5C5D7CD3D','B504418E-8F8D-4086-8E59-CD615707E5FB','','Vertedero o aliviadero', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('171E7A87-8DD4-44F4-9091-17552ACB1389','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Equipos de cómputo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('045DE8F3-FB18-46F0-B783-1771D662E85C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Descripción de edificio', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EF110FF3-70FA-4A9F-8699-17D762F38C33','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','P','Libre', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2EBA0130-7CAF-47AD-BD24-19DEF9B073EC','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','vgr2','Desc V2', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E56C9FA2-3F1A-4B35-B5B4-19FC38CAD43C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Num Tuneles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5593FE07-598E-46E9-B97A-1A5E10A3FD43','EA766519-648F-41FB-B429-A21F94758AE8','','Otros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('ECB19424-C020-4381-B7F7-1A892CAD84F0','D2261320-26BD-4560-AD96-0135366285A3','COA','Chiapas ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5657B098-8A82-4AA4-AD57-1A9CFD508154','1ACD8B10-3A71-4481-ABEA-70E91B70B53A','','Suelos altamente orgánicos (re', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C2CA920B-6C45-41D2-9FB9-1B9A44B46411','D2261320-26BD-4560-AD96-0135366285A3','COL','Chihuahua ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A477075C-A544-44B1-B050-1BC0CD6D6F41','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Canal de aproximación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1905CF7B-C8D3-4F79-B58F-1BEBE7597B4D','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Cunetas, Rígolas y Caces', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4FFF7B5D-90C2-4B92-848D-1BEF72520E1E','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Puente', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FFF576F7-3CD3-4355-ADBF-1CD7C2B3B3E0','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Enseres', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1137AE2A-8473-4BF1-98D0-1D1500A0212C','4F217F72-98B8-445F-AF8C-396B2B06671B','F','En cercas a base se malla tipo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3329062C-56FE-4732-9B5F-1D8440D721D9','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Desvio temporal', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('53D5F71D-0077-40A7-8B02-1DB537F3D01D','B5A4C0E7-FF66-4988-A957-F8FC519E2895','','Compuerta', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B8357F96-9512-44D2-87D8-1E30377CE17D','81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','','Elementos sin trabar', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B134137B-182D-4E36-A126-1F307C170386','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E07B24E8-33AF-4C60-8326-1F7B3B419FB6','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Malla ciclónica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('620241C8-FDAB-4984-8D67-202C5B60D39E','241FA096-9264-4150-AF9E-C9694D5E66C2','','Primarias', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('55809A44-27C5-41FE-BEF9-20404DBE0475','7EB43883-335D-4184-86F3-D899EB2F6784','','Otro(s)', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8D92370B-0B0F-4EA4-AC14-20416707EF6F','0E28A695-8644-4E13-92D1-F32EAEEEA29A','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8EBEB4B7-4BC9-491A-B387-20B31F4E6672','2043ACDA-56F7-40CE-A853-233F470FCB76','','Cerca perimetral', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('39DFF07F-C2DD-4F2B-AED2-20C330A1BBFD','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones en arena y grava', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B0D85803-E7BA-44F0-BDC5-211458B63132','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Cámaras de succión', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('89F0CF88-CA27-4D9A-9734-2198E0DAD25D','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Monto presupuestado destinado ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('089F3A9C-558F-427D-ADA0-21DE453E47BF','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Instalaciones electromecánicas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8B541FEF-B43B-48C0-99A5-2297F6121470','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Comentarios', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('77CAFAA0-4AC3-4F38-9A09-22B00BBFE2E1','F3244557-C16D-472E-BC31-CA403BE81DA0','','Techo en forma de sierra', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CD8C4F92-3C0D-4B00-BACA-22BFD8FD1B47','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','B','Medio doble translucido', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BA97FAB2-D490-45B3-A92D-2300541BC757','D2261320-26BD-4560-AD96-0135366285A3','CHP','Coahuila', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A556770F-1EA6-4CF2-B876-2302B7DC543E','68A049E3-4B55-4F48-AE03-A6CB920028B8','','Mar', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4425812C-B492-4149-B284-23301C5B8013','539D1830-90A8-484C-B183-F79A448CEC78','','A.4. abanico', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3B1E376C-2C95-4127-A650-234462BD3BCA','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros medianos no rigidiz', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('43B146CF-B958-4CF2-8038-2369ACFB5D5C','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Donado a beneficencia pública ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BA1A74D2-F64C-4F5D-92A4-2463A43B096F','2043ACDA-56F7-40CE-A853-233F470FCB76','','Franja de amortiguamiento (mín', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1FA322BB-016C-4147-A3A3-2470B9128BDA','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','J','Cristal opaco', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('95B91081-AF09-4D5E-9541-2483D9AFC90D','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Muebles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8402AAF5-ABC1-4A64-A6F9-258FA08A161D','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('802467BB-C972-4B83-ABDA-261E05B696B0','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Placas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E69F8708-B7C3-41A3-B792-2674213103A1','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','','Control de azolves', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('04C4A44E-B1C9-4FAD-AC58-26B97004BCD1','2043ACDA-56F7-40CE-A853-233F470FCB76','','Vestidores y servicios sanitar', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CC6738FD-3B22-4975-8796-26EB03B460D0','7EB43883-335D-4184-86F3-D899EB2F6784','','Enrocamiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('76C18DB1-DA2C-4B31-BC08-26F42D964407','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Casa de máquinas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('277476A8-F5EF-480B-9EA1-2730FF6698BB','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor promedio estimado de rec', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1BBF168C-6340-4360-92A5-2765715A3F5B','0D4FEBCD-D28A-4293-9065-A5A906BF6E69','','De 5 a 10m', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B7F23120-DFCF-47A4-A9A1-27888D5FA3EB','4F1CFD2A-E24E-4AD5-B2C6-FC20367D0FFC','','Corriente estacional', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BC7003E5-34F6-40A9-965A-27A4B4AB1B51','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Túnel', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C847ACC9-DE59-4E73-B1DA-27ABCE9CBB0A','1E1DA417-5EC6-4A03-A13E-08EB93D15E77','','Con tubería a presión', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('ED76BA38-EAA1-4DED-9C65-27EC963AD10F','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Tragantes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('25839726-9C31-43DE-8653-28588F1BD5AE','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','K','Cristal reflectasol', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2794E46F-4FC7-4291-9A98-292AD27DEF16','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Colectores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DD195E35-6AAB-4579-BF76-295CBCB0C2B5','54DA8427-56C1-4EC7-A258-FBA21E9303D2','','Otro', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5181022D-9B65-4002-8BB3-2A1C944CAF6F','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Consultorios médicos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C0939295-E05F-43EE-A96B-2A94755A20ED','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Gasto máximo registrado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E74C8ECF-3DFE-4CFA-A837-2B01DC233FB9','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Vertederos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8509787F-61BC-4049-A9D7-2BC38ECEF602','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Camino', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('93FF0681-8D45-470A-8BBC-2C46250CB550','D2261320-26BD-4560-AD96-0135366285A3','CHH','Colima ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FD7DEEA1-39CB-43B7-9147-2C9459029608','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3A6F52AB-B1C2-43AB-98AC-2CD6914830B0','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Instalaciones especiales', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('579F377A-54FD-444F-960B-2D06E3410775','3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','','Roca volcanica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('97D1E7C0-38BE-4BBD-80D2-2D211DFD6216','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E925EC90-A5C3-4D25-9CB0-2D375A34320B','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','C','Aplanadas, acabado rustico, pi', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B4CEFB51-267A-4DB4-AB05-2D37E792AAF3','3585818D-7660-4569-A24F-F5F0999D4705','F','Pilas de concreto armado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3E81DF69-5B7D-47A2-803B-2EDC1F3C7E6F','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red electromecánica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C3F1E804-234B-42B2-8DF8-2F2E23BCD272','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Abandonado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AD12C70A-C355-42A1-B229-2FB8B57302B6','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Error de captura', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2EAF0764-13E0-47E1-9A2F-2FCEDCAEBE10','E43CE3ED-6F48-4668-ABF3-222A8533005B','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0C3BD0E9-FA09-48F9-BFE6-2FF1173C53C0','3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','','Relleno artificial', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('97375940-E1E4-45DE-A269-301EAED38029','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','G','Block de barro hueco', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('81E07212-E7FE-43EB-858A-30B29EF3356A','5F7C9D4C-B206-4791-B14B-328E40FA25E2','','Riego', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('51C59D8A-9902-491F-A7A7-31733DC4A039','B504418E-8F8D-4086-8E59-CD615707E5FB','','Enservaderas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9592CF5A-8854-43FE-BBD9-32DED763D407','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Controles de nivel del agua en', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('80C32CBD-155F-45D3-9994-32EF4B2BE2DC','6DAC2E76-3A32-401C-BBEB-1CC3CDA7E428','','Canal de riego o de drenaje co', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DC09E0A9-1040-4080-BB58-33061D1C1699','D2261320-26BD-4560-AD96-0135366285A3','DUR','Durango ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8521DE27-2162-4D74-964E-337FF2B06890','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Reservorios', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('947DD835-305C-40F1-A93F-34B294F743E1','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Extensión', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('97635CAF-308A-43FF-BB56-34B54B168927','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Rehabilitación de estanques', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0FBE1C9F-9B2C-45FE-B024-35F86EB7AC5B','B504418E-8F8D-4086-8E59-CD615707E5FB','','Descarga de fondo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('392C312A-E42B-42EE-ACB3-363DB08924CE','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Terreno', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DAE30B59-3C4A-431F-BED1-3664491E9929','76A8D090-EB15-4600-ADB5-9F68043E46A5','','Vía rapida', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3F5BF841-D8A0-490F-8040-36802F452433','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Frontera sur', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('56BA60CA-CD09-40B0-BDC0-36C6C02DBC4D','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Subestación y ductos eléctrico', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BEF0166B-EE4B-442E-B23F-37109D13DC63','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Rehabilitación de estanques', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('65844E11-AE6B-4ADF-B9F8-378E185105F8','443523EE-F532-4577-956B-64E55A7B89BB','','Otros (especificar)', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F9A21F90-E696-47D0-9FB0-37AA39B7F24B','967615A4-74A4-40C3-B276-4EFCBDE63A48','FED','Gobierno Municipal', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7D7A9721-2B8F-4DF5-8C09-37DC1833C6E8','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Equipos electrónicos auxiliare', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0902CD92-3142-44CD-9E19-3825ABD1526A','C52AF614-2865-4708-B489-1BE720793BE6','','F.     presa de contrafuertes ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B6583E84-A321-401B-98B6-38419935EA61','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','A','Sencillo translucido', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3F7D1163-E3C0-4C20-9B45-38BCDFD99F26','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Región CNA', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F59B4C4F-620D-472F-9FD9-38D23F1C25B9','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','','Áreas de espera a la entrada y', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('830E087E-07FA-48FD-A96D-38FD71642A48','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen al NAMINO', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0A555B6E-9E34-47A5-B0D1-3928226C93DA','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Edificio', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B1E28593-F69A-426A-8ECF-393E42FEC30D','539D1830-90A8-484C-B183-F79A448CEC78','','A.2. descarga directa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4E90F1ED-9FEF-4973-8604-39425C6C4049','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Malla ciclónica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('ACAD85F1-A100-488A-84F3-3974A5DBB773','443523EE-F532-4577-956B-64E55A7B89BB','','Dorado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('63BB6F73-DB9B-4E5D-98CD-39C9E553B22F','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Cárcamos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('069942C1-2FCC-4ABA-956C-3A2EE69C6C68','D2261320-26BD-4560-AD96-0135366285A3','GUA','Guanajuato ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('83A622BA-EF89-4777-B347-3A47334FAA45','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Cuerpo de presa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('614CA928-A142-4F1C-A8A4-3B5B86C237A4','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros pequeños rigidizada', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('371782BB-BDB2-471C-9F08-3B91EC256234','12634A22-8918-4A1C-B2DC-BE4C827735CA','','Material ligero', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('45D10F58-B28B-4214-AA93-3BAE71D61D47','6CB19E01-D41B-4052-9C10-BC5360DD22FE','roc','Roca', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('11D62DA7-CE9B-4AFD-BE40-3CAE6CB4D89B','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','G','Cristal de 9mm. polarizado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BF8930EC-F48B-44A5-8173-3CCCC8071A7C','20F41507-5058-466C-AD1E-1819B8A6207F','','Elevación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('18D18212-90A6-4DB3-A770-3CD014CD1227','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Dispositivos de desinfección', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8AF49775-D99F-4AFF-91D5-3CF7CE53BF26','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D0A962B1-C77A-4777-A409-3D7B2095DFCE','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Inventarios libros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EE05D8D9-D260-4EFE-A76C-3D7FA49B6594','EA50619F-3012-4841-8939-BB83634FCF9C','','Intermedia', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7E209574-F231-4AC9-83AF-3DB3B9B6CCC7','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Compuerta de entrada', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6287ECE5-8BEA-493C-A860-3DB770460C1B','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red de conducción de agua (obr', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('819A106D-73B3-48C1-95EA-3DBB96A693DB','94E45704-8045-4CF8-B747-763D600789C4','','Capacidad de 50 a 100 tonelada', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7486C853-EF38-4E04-91EF-3DD336D6C151','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Carretera', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C19EC910-1500-47AC-921F-3DF631DBB667','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Cauces de alivio', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6750A007-0F9A-47C2-B744-3E2B8BB44DBE','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen útil', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DF2A44B6-93E4-484C-B3A2-3E731EBB0366','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red sanitaria', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','D2261320-26BD-4560-AD96-0135366285A3','GRO','Guerrero ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('713C2CCC-B552-4E81-B0A5-3EE5D055A0BC','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Presa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('ED6EC14E-47FE-436A-B619-3F5C73CBF41F','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','','Poca', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('71BE3081-6D8F-46D1-AD6D-3FF54A30AAA3','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Península de yucatán', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F745F6CE-4A71-4426-8943-3FFCC219D38D','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Taludes aguas abajo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('427D821A-1386-4C68-8DB6-4046DDC7A239','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Instrumental médico', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('65C08A09-51C8-4863-A43F-40AC7CDAB503','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Taludes aguas arriba', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('26D9F7E2-53EE-4E61-B3E4-40B6C3CCF2C6','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EDE30602-EC27-4D8C-B40E-4152C636E456','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E264210A-287A-47CC-B40F-416324BC02ED','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Rehabilitación de estanques', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('99B256E2-2A6A-4177-93D1-41649F379220','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red de alimentación (obras de ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D80E79B8-4248-44D1-A085-41F5DD46700B','0D4FEBCD-D28A-4293-9065-A5A906BF6E69','','< 5m', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7DB6DDC4-2BAD-4374-9902-4238C74FEBAE','A232C4C1-5524-4302-9329-F69D050F9698','','Escuela', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0702B2DD-4F96-48ED-B0CC-4242F6D45814','443523EE-F532-4577-956B-64E55A7B89BB','','Trucha', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('568AEF5E-5B44-4F74-9440-4313D984592F','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Cuencas centrales del norte', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BE7DCBE2-5B33-4ADF-9521-434B378E72AC','D2261320-26BD-4560-AD96-0135366285A3','HID','Hidalgo ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('181E092C-025C-42DE-AF91-440262209F60','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Equipos electrónicos auxiliare', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C084285A-B1D3-4190-8D7A-44996075C029','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor maquinara y equipo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AE3B3625-732A-427A-AE98-45535B98BD19','0E28A695-8644-4E13-92D1-F32EAEEEA29A','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6697439F-53AB-4068-B4CC-4571B628EFDC','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','L','Libre', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F59F337D-84D6-4837-8AF8-4597A8DC03F7','DE4815D5-E7B5-4C48-A694-180F351E1B41','F','Metálica a base de marcos con ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F67769ED-C9E3-4250-8407-4704D50B13E9','7EB43883-335D-4184-86F3-D899EB2F6784','','Mampostería', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('59C22422-BA3B-483C-8B11-474770CA64E8','15641A0F-51F0-4969-AD95-90EF456AFE13','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CF31FB59-9007-4D32-9752-4801A1AA2876','464A3F38-B8B5-4828-871B-D717885AE5CB','','Bocatomas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D26C6275-8F48-4F07-8CCF-488B90570ACE','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','De arco en concreto', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('226E9B9F-4023-4576-B060-48A52BE444D7','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen máximo de escurrimient', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('524CEF90-B61D-4064-99D0-4A6EC89C97B5','C52AF614-2865-4708-B489-1BE720793BE6','','C.     presa de gravedad', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EB6ADFB7-016C-4E80-8EDF-4AB87DCBD9EA','B56CE9B9-A793-4C6E-995D-EB7D6490F71E','','Mucha', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('12D752E8-4F17-40D6-83FA-4B1243B598A7','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','','Nula', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2E850C3C-1B15-460C-B334-4B2C0605C0E7','B4C32095-C18C-4448-BB13-7725B88EF351','','Otro', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8445FB89-1B78-41EA-A767-4B3AE36B45EC','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','N','Celosias de barro', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('40B4B773-BA6E-48DC-910B-4B3F89A25372','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Golfo norte', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9E78DD24-2DC0-4991-AF15-4C18A66AD3F1','E9D156CD-19DE-43F1-8B17-0F33227A90B2','','Rodantes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('98B72092-0088-4AC3-AABC-4C18B811B315','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red sanitaria (saneamiento)', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0513315E-23F1-4A01-8A29-4C3EE96B8735','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Puentes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('24ED8566-FB18-46E9-98A6-4C7CA3FFFA57','0E28A695-8644-4E13-92D1-F32EAEEEA29A','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3479F77A-1A10-4BE2-BFA3-4CEF9CFD7E42','0FD32189-1390-4CB3-9047-92F3EF391E1C','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('37E088BE-8C24-4F43-BACC-4E3C9A3C918E','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Tiene Domos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7EA618BC-FF9D-4955-A16A-4E49DFE0ADD8','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','','Canales de llenado y vaciado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BFDBDC7F-10A0-4A33-AC7D-4E624FAA9AFB','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Motores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EE4282A4-33DB-426B-AFA7-4EB32074384A','D2261320-26BD-4560-AD96-0135366285A3','JAL','Jalisco ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AE012E0E-C281-4843-941D-4F7685C897F3','37CE79B4-15EB-4BF3-892F-897223CFD12E','','Ancho de base', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('895A8374-18B0-496D-B0A9-4F876204536B','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','F','Libre', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C76D4B02-BB8E-4479-A2E0-50473E4B0775','D2261320-26BD-4560-AD96-0135366285A3','MEX','México ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7B9079A0-38E8-4449-BB91-51030E32ECFA','BA697C53-E720-4D8C-BB2F-A405ED51F43D','','Albañales', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('165AFB7D-1A1F-4AF9-AF91-510542431638','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros pequeños no rigidiz', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('352D21EA-DEA0-4693-86DD-516F1D266D7C','5F7C9D4C-B206-4791-B14B-328E40FA25E2','','Otros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A6601A4A-4C7D-4682-B701-518E9729CC2C','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Floculadores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('576ECD79-210B-44CE-9797-51AC63884E6A','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Cds', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('58283B22-7A10-4CDA-9956-521D2C9667A3','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Equipos de diagnósitoco y labo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D9C05E00-C1C3-4C22-94C7-522EBD9EB08A','241FA096-9264-4150-AF9E-C9694D5E66C2','','Secundarias', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('620B74DC-755B-439C-9DC7-5278BF8F1960','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','B','Concreto armado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5026E187-04A6-4F0F-A268-52991956BCDA','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Gasto máximo de diseño', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('44AD696B-CAE1-4251-A975-52EE8ACF6399','D2261320-26BD-4560-AD96-0135366285A3','MIC','Michoacán', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8644C4CB-262A-489C-9B13-52F7ACA11BF2','4F217F72-98B8-445F-AF8C-396B2B06671B','E','En rejas a base de hierro forj', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8056AED7-CAC8-4E9B-A552-53506F2E5885','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Otros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8A64A888-9E59-412A-A370-53E8C3ECE420','443523EE-F532-4577-956B-64E55A7B89BB','','Ostra', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6193134F-02D4-462D-8B9E-53FDC287C8B8','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','D','Block silico-calcareo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7EF7351D-E116-4068-9611-544DC9C9421E','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','J','Costaneros de lamina galvaniza', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('280EB7FD-72B4-4C87-AAED-547DD5B70EBF','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Líneas de impulsión', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AADC54DE-B167-45C0-86FD-54880CCDF70D','37CE79B4-15EB-4BF3-892F-897223CFD12E','','Nivel de inundación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('22E02AEA-D38A-49C0-8C8D-54CAD7B719B8','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','C','Cristal de 6mm. natural', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F28B4182-39C5-40D0-96FE-554D61C654B5','EF7170F5-F452-47A9-8E1E-B6ED39DE7E90','','Puertas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FBDEF03B-6739-496E-A485-55A30586AC87','539D1830-90A8-484C-B183-F79A448CEC78','','B.4. abanico', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B2784C7E-85B5-4164-A146-55DF62FA22F0','241FA096-9264-4150-AF9E-C9694D5E66C2','','Terciarias', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F092724D-DA1E-44AA-AAA0-55E562E05221','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Alcantarillas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5A200EE8-0947-46F7-A51F-562BEF0ABBB4','75B17471-ED30-467C-BA2A-8F8D1D988282','','WBAltitude', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6D431485-6C7F-417A-80C8-5678B9AF5ACF','D2261320-26BD-4560-AD96-0135366285A3','MOR','Morelos ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0F0F02A3-FF7A-4302-8594-567E6E55219A','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Tipo de corriente', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('62E62F2E-20C9-4955-8F08-56C2335AC131','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Cuenca', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('79383E89-BC48-4454-9655-56F1F251B066','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Bombas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('44A78916-1C19-476E-BF88-57F2D0B19B68','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Salud', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EF6C223C-008C-4CEE-B97B-580E827F92FF','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Desfogues', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AF9E59FF-127F-4D0E-BC0A-5822B1B48DFC','089475EF-D242-4F7B-9C0C-CBFE98BB480D','','Chicos <.25 m2', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('268AA290-FF1E-4B88-807F-58236E03C3B3','B504418E-8F8D-4086-8E59-CD615707E5FB','','Bocatomas para los diversos us', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('919A4798-DA01-4BC9-9396-583AC4598968','75B17471-ED30-467C-BA2A-8F8D1D988282','Y','Latitude', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('26C4E75F-821A-446F-B3F6-58A32B5B884A','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red electromecánica (obras de ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4C729D1F-0CFB-4483-93E4-59D563B1DE13','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1558CE49-AE55-4505-9D9D-59D70FAA0653','94E45704-8045-4CF8-B747-763D600789C4','','Capacidad de 10 a 50 toneladas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5FC905BE-52E9-4360-A403-59EAC5DD1750','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen para control de avenid', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B54C0944-AFF7-41B3-ADD0-5A5A7C32155B','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Otros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5DA13A22-2A14-4642-B57D-5AA0BAA97048','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Siniestrado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FF4AC697-B9D0-48DA-95D2-5B4A3E80892B','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen de conservación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F0AAC454-6FE2-453F-8CE1-5B4B8DA82E93','81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','','Contrafuertes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('63BAFD4F-6234-4A6F-9FAF-5B959069353D','D2261320-26BD-4560-AD96-0135366285A3','NAY','Nayarit ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C0F7CDCB-1D0A-40F0-955D-5C347B81F550','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Bienes históricos y arqueologi', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('51C2B7D8-9AC4-4CF4-AC5E-5D2D0EEAD57F','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D6AD4FE9-55C1-4C54-A0D1-5D311E3D8D93','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3D1555E2-058D-4409-96DB-5D4816E9CB06','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Hidraúlica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('76882720-5AF6-427E-8FBF-5D5E64A9EE41','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','De armadura en cantilever', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('109567AB-77FE-4C6C-9ACE-5DD4FF28586A','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Transformadores de energía', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('56BCFEBE-1C66-4F21-993F-5E7B411C0252','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen de azolver', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B546FF4C-8DC1-47B0-B648-5F53B0998BEF','1B44E444-42A5-463B-AC94-0FA2DAFCB2D8','','Pesada (concreto)', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('62671D0F-784E-4996-9D77-60CBC7447A06','539D1830-90A8-484C-B183-F79A448CEC78','','B.2. descarga directa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EDBCF3A6-A979-445A-8AAC-6100088678E5','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Canales de riego con todos sus', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('08A9201C-F6CC-44F0-B136-61370BC2EFDB','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Disposición de residuos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6291860C-380D-40C8-ADC5-61F1EFDCC1DD','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen medio de escurrimiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8A8EA3AC-5D57-425E-A121-628A1EFBE552','464A3F38-B8B5-4828-871B-D717885AE5CB','','Galerías filtrantes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AB2A8871-7430-4499-9D56-62BBEABACA32','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red de conducción de agua (obr', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B475F358-782B-49DF-BCBE-62F4646FDB1C','E398849C-BDF2-4ABB-AFB0-0E4E8C8EF326','','Unitaria', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('34069595-7CA2-4341-B1D1-632507BD06ED','75B17471-ED30-467C-BA2A-8F8D1D988282','','Percipitación máxima anual', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EB31C186-1719-4CE7-B66C-640CFCC5E593','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Corriente', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C49271C9-0B7E-441B-9F98-651194F4D863','76A8D090-EB15-4600-ADB5-9F68043E46A5','','Calle', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2A228004-8DD4-4D2F-ABDE-6524266D61E3','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Clínicas y hospitales', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('06E3CBD9-A517-4E51-B019-65621E025CEC','B374ABA0-9C03-4916-ADE8-0BA48161CE05','','Carretera', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('60752DDF-C488-4757-9A6B-65F2903B06DA','0E28A695-8644-4E13-92D1-F32EAEEEA29A','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('97C51E3D-3FAB-46D1-9D64-65FDAD271BD5','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Pacífico sur', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3210C802-6BED-4DE0-B6F5-66064949EA22','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor de reposición a nuevo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B682AA18-C836-4BA9-9ECA-6658D6D8D124','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Talleres', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8580B592-08F3-4BA0-BC63-66965C335E5A','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones relativamente se', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('478BFA53-F1FC-4843-8E9E-66C52A882F63','6CB19E01-D41B-4052-9C10-BC5360DD22FE','arn','Arenoso', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9CEE1E14-0AAE-4D4F-989C-67B7A0BCAF3E','0D4FEBCD-D28A-4293-9065-A5A906BF6E69','','> a 15 m', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5C0B7B63-E2DE-48C6-9086-680BAF833781','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','E','Cristal de 12mm. natural', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0C1403CF-5E97-4EFA-AB4A-687276B83A54','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','H','Cristal de 12mm. polarizado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C9F23800-29DA-4231-BA72-68B7CE145A7A','C52AF614-2865-4708-B489-1BE720793BE6','','H.     presa de diseño combina', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C331B67C-920F-42DB-A2EA-6925B36F66D8','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Aliviadero', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('388AE204-9EFB-4729-B6F5-6A575E1EFE7B','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Diques', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F12DA261-C9EC-4EB7-AEA1-6AA468DD2FFB','E43CE3ED-6F48-4668-ABF3-222A8533005B','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D6726C92-D2E1-4C38-B553-6AC5868E22B2','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Escuela', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6236ECC2-F92B-416D-807D-6B22A16A8FC3','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Almacenes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EDD00D9C-4FAD-4847-BE66-6B35252F704B','75B17471-ED30-467C-BA2A-8F8D1D988282','','WBLatitude', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A6B4D19F-3C39-4E07-91C6-6BAE4896AD0C','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Línea de impulsión', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('27FC32A7-DC28-455C-AD54-6BB56DA86E1F','D2261320-26BD-4560-AD96-0135366285A3','NLE','Nuevo León ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('94C3672E-3315-4D5D-92F8-6C49E5517F83','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6F5701EB-8DCA-4A2D-B18A-6C4C959FEB0F','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Volumen del cuerpo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('20A5A886-B52B-456F-8199-6C9F3D63C092','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Obras de toma y salida', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CA88B7FE-4BC1-4CAF-B14A-6CD9620FCAC9','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','No ubicable', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F071577C-AD31-42C8-B208-6D304B2F1C1C','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Geometría irregular', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CABF2D63-ADEF-4226-BF66-6D7AD8B47DF1','5F7C9D4C-B206-4791-B14B-328E40FA25E2','','Abrevadero', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A545FC72-EDE8-4883-BCB1-6F09C5CD371A','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Laboratorios', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AFB03373-3BCF-4E95-82CB-6FA76501C9A6','22A44093-7E34-46D9-92DF-57FE3A335E13','','Saneamiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('27E91B61-2BC4-484A-8EF5-6FAF6D1A6E29','07C3B108-6581-4DA7-85BC-FF6263924E99','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9638A1E6-9D30-4885-AC60-705CEB53BE82','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Urbana', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D4BABE2A-F30A-4307-8430-70A122FACE70','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Constructor', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BFD91756-633C-4CD3-B0C9-70D7D076B835','76A8D090-EB15-4600-ADB5-9F68043E46A5','','Tunel', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6D0F75F6-4E18-4D16-97B5-70F8B6E7388F','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Estancias infantiles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4AA6D428-CE01-4F88-B779-71017EAE5196','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Diseñador', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F5ABED99-B8A4-47C7-A4F8-714B7FA4AAA9','EA50619F-3012-4841-8939-BB83634FCF9C','','Flexible', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('84CFF176-BB1E-4B18-9053-718888F9DB4A','75B17471-ED30-467C-BA2A-8F8D1D988282','','WBLongitude', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('22299D7C-D581-4D3B-A7B3-71BF5DC6368F','75B17471-ED30-467C-BA2A-8F8D1D988282','A','accuracy', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('30D129AA-E074-4BEE-89FA-71F29CBFC040','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','Puente Viga', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8BA91214-6248-4571-A25D-7207F10CB286','E398849C-BDF2-4ABB-AFB0-0E4E8C8EF326','','Separativa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0F81038B-9DE6-4045-97D5-72136F321B82','234FB73E-843A-4976-BF1F-48318D352CDA','','Si, ligeros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3A94A057-9BAF-4633-9AD4-72F60065C46A','D2261320-26BD-4560-AD96-0135366285A3','OAX','Oaxaca ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C9DE5964-9E57-4A7B-BA9F-73A7B4418331','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Otros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('77A062EB-832E-4972-BEB0-741862DC9FED','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','','Reservas de agua para el llena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F4F5D823-2945-401C-ABF7-74189AFB50CC','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Golfo centro', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4332572D-8FD6-4C25-934C-74A3165B3C26','5F7C9D4C-B206-4791-B14B-328E40FA25E2','','Agua potable', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('66B1B7A6-DF11-489A-8DD7-74C000D78A26','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Muebles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3B6A4AE4-8E8B-4600-A881-752C394BFC2C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Estaciones depuradoras', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4094E5D7-EF96-4A3D-A160-755C6634AC24','12634A22-8918-4A1C-B2DC-BE4C827735CA','','Hormigon', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9F2AF287-A9DD-4DFF-998C-7583032FDEBB','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','vgr3','Desc V3', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('42020774-B8EB-4994-9A5A-75A49FBD7932','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','E','Tabique barro rojo cocido', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A31FDF5A-12D2-481E-AABB-7631A2EF2AD0','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Otro', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('682C8723-BFCE-4F17-A5DE-7664EF196F9B','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Represas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('371D7062-79C8-4080-A0BA-7673FD691D16','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n6','prueba 6', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5D4F7C0B-2DA7-4253-83B4-76804F2938A6','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Noroeste', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DDEB7463-2B76-4B49-9630-76B8EF104F56','754E0F98-3A4A-4D1B-9B84-135B860A7791','','Alta', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('47D8EDA0-D54F-4ADA-AC95-7701B7A7DE75','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Área', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1FA917DF-061A-49DF-A8FA-77340906B686','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','tst','Registro prueba', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C06F5911-6A89-41EA-8FD2-7762DEECD539','DE4815D5-E7B5-4C48-A694-180F351E1B41','B','Columnas, trabes, castillos, c', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C05F46A4-77F8-40BB-99E8-776A975589B9','84987039-D458-480B-8B34-0C7AEE808206','','Terraceria', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9EDF9087-14E2-4C10-9107-777D5CD9EDC0','4F217F72-98B8-445F-AF8C-396B2B06671B','G','En rejas a base de tubulares', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6E26183E-6799-4839-B293-77E9B3C863AA','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Educación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E58E0C92-3AEB-4378-B962-786BD484459C','D2261320-26BD-4560-AD96-0135366285A3','PUE','Puebla ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('74A5FF1A-2F4C-44B4-AB61-7880F462CA92','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Mnto destinado anualmente a ma', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6D8A20A4-11C9-4FA9-9421-78E5499B4EE3','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Inventarios libros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('504BAE09-9F93-46A5-83CA-790CB72996F2','E9D156CD-19DE-43F1-8B17-0F33227A90B2','','Otras', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('042D61F5-D44B-490D-9EFD-79199F4779E7','BA697C53-E720-4D8C-BB2F-A405ED51F43D','','Entronques', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F82BA6B5-AE2C-49FE-8EBE-792ABC71626B','1ACD8B10-3A71-4481-ABEA-70E91B70B53A','','Suelos de grano grueso (e.g. a', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('39774039-AFC9-4DDA-A9C8-7933E4545103','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Descargas de fondos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A390BB9B-B1FB-4185-AF3C-7957F0C60CA4','3585818D-7660-4569-A24F-F5F0999D4705','A','Mampostería de piedra y refuer', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('96448ECA-C1E0-4601-A886-79BA979E0CEF','4F217F72-98B8-445F-AF8C-396B2B06671B','B','En puertas y ventanas perfiles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('64CC0DFB-A94F-492A-A3A1-79F9121DA059','20F41507-5058-466C-AD1E-1819B8A6207F','','Sección', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('542972F2-20A9-42C3-BB3A-7A0A03BBA1E7','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3ABE7B4F-010A-4DE2-9A97-7A76AB156418','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Esclusas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5652F097-CA3C-4349-8283-7A88309AFE93','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Afluente', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('92030EA0-C76C-461A-883B-7ABF1B3D2621','CA38F79C-B87A-4B9B-BC1D-2002B2658C9B','','Mucha', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9847C866-496F-4F09-B2FA-7ACA1364B85A','443523EE-F532-4577-956B-64E55A7B89BB','','Lubina', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E137FF48-9965-47BF-A998-7B60895F5A2D','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones en roca', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('483BCBBF-1447-4961-9741-7B9CCE4C4E5F','6CB19E01-D41B-4052-9C10-BC5360DD22FE','arc','Arcilla', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('13B98A31-21D5-4AC6-9049-7BB57FDE3CCF','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','','Compuertas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F0AC53C2-01A8-4528-9C23-7BE4CF1FAB0B','EA50619F-3012-4841-8939-BB83634FCF9C','','Rígida', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('48D79D10-AD36-41C6-A28E-7BE7B1145BF3','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n5','prueba 5', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('48E751A1-3FA8-44E3-B59B-7D673C1E7192','B1137091-9300-4989-BE0F-4D38B9C543C6','','Biblioteca', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5FFA3D32-0C98-4947-9EE2-7D8F52BB6EA5','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Otro', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4FCEE5B2-75BE-4E65-A3AD-7E08006B7FE4','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','F','Mampostería de piedra', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A67C9F30-6D1D-48C5-8CE4-7E216AE47889','DE4815D5-E7B5-4C48-A694-180F351E1B41','C','Metálica con columnas, trabes,', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E88B42EE-81B7-4986-8A9F-7E8CBFE720EC','1758A063-FF75-4AAB-9D43-6FDD89946A54','','Bitubo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('934D6B71-5B72-44E4-AD81-7EEB289F5A59','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros grandes no rigidiza', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FB1A6D04-3FF6-4275-A70D-7EF41C015102','4F217F72-98B8-445F-AF8C-396B2B06671B','H','Libre', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2EE66436-7EB6-463D-A64F-7EFA95791E72','84987039-D458-480B-8B34-0C7AEE808206','','Asfalto', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D519A22A-F479-45B8-BCE7-7F42D3F74CB4','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Num Hundimientos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('55846B69-506C-41FB-B7A1-7F68F3FF0A54','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','No habitable', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('71CB55AF-9DD9-448E-97C7-8137E0D511C1','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Loseta', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A319CE2C-0EFE-49C9-BF9A-814922C7CC12','75B17471-ED30-467C-BA2A-8F8D1D988282','','Percipitación máxima diaria', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3B2233F0-B84A-4543-8B11-81D23FC1CDFE','81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','','Bóveda', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('98CFD274-71A3-4EDA-B5B1-8202A13D44E4','8831E933-E4BB-47DF-A0FA-139EC6495C73','','Al centro de un dique de tierr', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2E084FD5-681C-4A14-915B-8227CED05C7A','3585818D-7660-4569-A24F-F5F0999D4705','H','Libre', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('12AC30E2-B313-4519-9F4D-82A12F161EE3','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','','Tanques elevados', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4076FD65-E25F-4CAC-8764-82D3011D97B5','22A44093-7E34-46D9-92DF-57FE3A335E13','','Alcantarillado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C1022DE8-58B6-49A3-86E3-82D94BB2FF26','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Infraestructura hidraúlica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4F213B14-A341-40B8-B15F-8301538E9F3A','E9D156CD-19DE-43F1-8B17-0F33227A90B2','','Deslizantes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5B588BDF-405F-408D-9076-8306BC116A21','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Maquinaria y equipo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9FC3A281-AFE2-4FFC-984E-83111D1AC5CC','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Demolido', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('197409AB-1F50-4BC0-8993-8342133900F6','07C3B108-6581-4DA7-85BC-FF6263924E99','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('78046592-0251-4528-B6DB-834882CCB4A0','6CB19E01-D41B-4052-9C10-BC5360DD22FE','trn','Transición', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2F1DB29D-3273-43EC-88A5-83C306609F39','EA766519-648F-41FB-B429-A21F94758AE8','','Tubería a presión', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3E113983-2942-435A-B471-83D67866C650','E8E42A49-5874-4AE2-9070-8C15EE163201','','Madera', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1CAB8099-D748-4215-B70B-84031036B944','2043ACDA-56F7-40CE-A853-233F470FCB76','','Caseta de vigilancia y control', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('492010A2-1935-4B0D-924D-847D19B67110','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Otros medios de almacenamiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('593BAEAB-940C-48DD-96AF-852A4C5F8454','BA697C53-E720-4D8C-BB2F-A405ED51F43D','','Arquetas de arranque', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('897BC614-403F-47EB-8EF2-854351F431BB','F4DBA965-B5D9-4A89-93A2-C55850E82FFB','','Adyacente a la cortina', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('30502FC5-7CE7-49FD-ABED-85D0C2586280','3585818D-7660-4569-A24F-F5F0999D4705','G','Zapatas corridas, dados y cont', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('35ECE2CE-EA3A-4F4C-8584-866A9BA5F3EC','20F41507-5058-466C-AD1E-1819B8A6207F','','Altura', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B9FFADBA-530F-4EED-8811-867B7B7DD4C2','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Hundimiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A344B1B3-BA1A-4D2C-95DF-870996C2F502','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','D','Cristal de 9mm. natural', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('813F6030-A058-4505-906C-87A40E6D4EC2','C52AF614-2865-4708-B489-1BE720793BE6','','D.    presa de pantalla plana', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E80F4100-A293-420B-B8E0-87CCFE8B30A6','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Red electromecánica (saneamien', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('666FA81A-D0B0-4C10-939D-88449A0E5481','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Estaciones de bombeo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7ABA1EDB-1327-4676-968D-88B5BA5FE63E','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','O','Celosias de concreto', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3BB97903-A462-48F4-BE9F-894CC5F25C4E','782F77AC-0455-45F8-8B8F-F29C9DC058B9','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6811BAF0-2EF0-407E-946F-895C4BB258C9','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Presencia de golpeteo con otra', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('23CB6695-F543-4542-8247-897ED624B95E','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Dispositivo para amortiguar el', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Inundación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('12414F86-999E-4071-91C5-8A19430A3A46','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Dispositivos para la mediación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BFE031DC-88BF-422B-94EE-8A26A4729F45','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Cuerpo de agua cruza', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5A95A512-B8BB-446E-BF24-8AB01208F4BA','0248C639-8F72-45D5-BF9F-1028C1B2CCE4','VGR1','Desc V1', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B4D8440A-39B9-4A44-BB27-8BC129EA7FDA','B4C32095-C18C-4448-BB13-7725B88EF351','','Poste de luz o teléfono', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8BA335C7-6BBF-46A1-8DB5-8BDCDB001FD1','2043ACDA-56F7-40CE-A853-233F470FCB76','','Servicio médico y seguridad pe', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DC92108D-C042-4FC6-8CE0-8C507B30E320','539D1830-90A8-484C-B183-F79A448CEC78','','A.1. económico o lavadero', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('32A24C2D-4257-40DF-BD30-8CF9CF471279','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Tuberías', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2808E19A-76D4-41CF-ADA4-8D05D329A878','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Libramiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9A17845A-8B67-4F96-8893-8DC73E843807','6CB19E01-D41B-4052-9C10-BC5360DD22FE','vrt','Vertisoles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0C76AD41-D3BE-481C-A82A-8E947F5134B0','B4C32095-C18C-4448-BB13-7725B88EF351','','Arbol', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4FE15384-6C32-42F7-9EF4-8EAFCCD5843A','DE4815D5-E7B5-4C48-A694-180F351E1B41','D','Mixta, con columnas de concret', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('115B140C-2596-4F69-95F7-8EFAC742051F','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Presas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1AF15855-447D-482A-BFB9-8F3BA5EA48B4','464A3F38-B8B5-4828-871B-D717885AE5CB','','Pozos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1D853564-86AE-4C34-8786-8F591AC07D74','539D1830-90A8-484C-B183-F79A448CEC78','','B. de cresta de caída recta', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AD0D2809-30C8-4E53-9595-8F7894062CC0','75B17471-ED30-467C-BA2A-8F8D1D988282','S','TownName', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D79634AC-B15F-426A-83C7-8FB83D290FC0','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','cst','Cisternas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('432E2E88-5084-498C-9B7A-8FE7DB1F1593','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C42FE244-8F70-4EF0-959C-9034C14E4A93','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Cancelería', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E22398FA-66BC-45F0-B4C8-905D4BDA2E30','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','D','Aparentes con pintura vinílica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8F684117-6E6E-4C78-83AF-9147C0AFFE55','B4C32095-C18C-4448-BB13-7725B88EF351','','Anuncios espectaculares', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('245341C6-5DBE-4227-BF11-9172906C1E24','D2261320-26BD-4560-AD96-0135366285A3','QUE','Querétaro ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('28C54E78-15B8-48F4-B1F7-9261A75F2325','B374ABA0-9C03-4916-ADE8-0BA48161CE05','','Brecha', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('04BFB10E-8005-475B-9564-92B9B90E2C87','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red de alimentación (obras de ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BC589497-44DC-48BD-8FBF-92E30B3EC8AD','2043ACDA-56F7-40CE-A853-233F470FCB76','','Caminos interiores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','D2261320-26BD-4560-AD96-0135366285A3','ROO','Quintana Roo ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E072EA3D-C4A0-4741-9962-93DB68B4B86F','E8E42A49-5874-4AE2-9070-8C15EE163201','','Concreto', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E8995CE2-2E6E-43FB-8CD6-93FF0C81C1C0','6CB19E01-D41B-4052-9C10-BC5360DD22FE','fls','Fluvisoles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('494C0A72-F88B-4E66-AEC5-94A63D228153','D2261320-26BD-4560-AD96-0135366285A3','SLP','San Luis Potosí ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('975D1E82-3F39-419D-8116-94A8EACA4A95','089475EF-D242-4F7B-9C0C-CBFE98BB480D','','Grandes >1 m2', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('43BE923C-BA5F-4FC7-B266-94E3F735520A','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','',' aguas del valle de méxico', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AD89F22C-AA79-4C94-A263-956CF9CEA51F','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Cancelería', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('92EE36EE-A07B-4FA6-BE80-956EFEF8A735','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Rio bravo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B4514A07-DFD7-4F90-8FD2-95BF1C2E052B','B5A4C0E7-FF66-4988-A957-F8FC519E2895','','Mariposa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9C3B386F-1490-4A02-AD17-963E38CBC077','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Balsa del agua', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4B274684-843A-4CEF-97B1-971D874C27B4','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Clínica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5C03CF75-7DF8-407B-81CC-973D4E8A4968','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Periodo de retorno', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C31B9366-FBC1-4B61-9279-9745B50F43C4','6CB19E01-D41B-4052-9C10-BC5360DD22FE','lts','Litosoles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BC82E604-7D0C-412C-A91D-97552482BC86','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Subestación y ductos eléctrico', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DE9C700C-0B99-4683-8E02-977A142120C8','D2261320-26BD-4560-AD96-0135366285A3','SIN','Sinaloa ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('808920BA-B0C3-4B8F-ABEC-9883688BAD36','D2261320-26BD-4560-AD96-0135366285A3','SON','Sonora ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('791CFA6C-B694-4B5E-9351-989673F62B34','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Ancho', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('50F4054C-30DA-40E9-87E1-98BED8C9E9AD','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Cuerpo de agua más cercano', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('96F4E9E5-E5E4-44E1-A75D-9913175F052E','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Techumbres', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C8E5BBC2-8142-4A50-9520-996F0942A383','D2261320-26BD-4560-AD96-0135366285A3','TAB','Tabasco ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('78C74191-5BBF-47E2-9738-999C6BD2E39A','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Filtros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('425DB9F7-93CD-4310-9C31-99D7B6FDCD5D','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Superalmacenamiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D9C3F321-A3CE-4784-8E63-9AD53B3EF6F5','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Desarenador', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DC8C3EAF-8E0E-4598-A2D1-9B36E277F6C4','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Obras de toma y salida', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F01DF7E9-C2FF-4436-B93A-9C324BDC60A6','A232C4C1-5524-4302-9329-F69D050F9698','','Edificio', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4F44BDD2-009B-4244-BFC4-9C6A37BFF81F','F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','','Hidroeléctricas.', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B7A3C1E3-7301-4F26-B09C-9C70EE751B6A','DE4815D5-E7B5-4C48-A694-180F351E1B41','H','Libre', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EE347E08-8DA4-41F4-8614-9CEC336C9D46','E9D156CD-19DE-43F1-8B17-0F33227A90B2','','Radiales', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A5F06F88-B3A8-4D55-AB0F-9CEDA99A68D4','967615A4-74A4-40C3-B276-4EFCBDE63A48','FED','Gobierno Federal', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0A736DEA-8966-4F93-9353-9DA16F9B4174','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Península de baja california', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3F4EF8B6-0B95-46A5-8DBA-9E4736B8049E','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Red de caminos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6D7E8620-C6E1-4184-B426-9E4BD0916AAA','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red sanitaria (saneamiento)', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F6DD4CC8-80B0-4995-B60E-9E55ED8A640B','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','E','Aparentes con pintura de esmal', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3F7FB352-D3DA-4AA3-8FCB-9E8B151ECF4B','7EB43883-335D-4184-86F3-D899EB2F6784','','Concreto', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9B674B1C-3E69-4249-AEC0-9EDD05C2628E','AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','A','Aparentes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('39301E28-AFF7-452E-8B0B-9F38489AB220','68A049E3-4B55-4F48-AE03-A6CB920028B8','','Lago', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('731C321F-2EE8-43E2-8F2D-9F9B63443BA1','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Galerías', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B5C155EB-5B5B-4509-AD46-A0F797CA327F','EF7170F5-F452-47A9-8E1E-B6ED39DE7E90','','Ventanas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4CAC869B-3F61-450E-8D35-A15B22BA2E19','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Reja para la retención de mate', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CBC48695-91B6-4959-83A1-A1C015784FFC','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Equipos de cómputo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CE4D2C2C-B3A9-475D-B0D7-A265027AB968','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Interceptores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3502DA0C-56D9-4DE3-9AA8-A3DBE42CF63A','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E6F46501-32F1-469A-A5D1-A456BCB6C9BD','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Presencia de muros cortos entr', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BDF64B6B-482B-4011-9A68-A4E647632281','15641A0F-51F0-4969-AD95-90EF456AFE13','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DDE06835-B4C8-4883-B484-A4F83A42370C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Instalación sanitaria', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5EE87CE7-9EED-4B5C-B0C8-A54096C91B2B','754E0F98-3A4A-4D1B-9B84-135B860A7791','','Baja', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('805D46D8-7187-451D-B92E-A5555E019A6F','F3244557-C16D-472E-BC31-CA403BE81DA0','','Techo inclinado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('500E06C8-B4FB-412C-965D-A58FED08C760','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Monto presupuestado destinado ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1E5D6FE7-1516-47F5-BC97-A5BA4D31C163','754E0F98-3A4A-4D1B-9B84-135B860A7791','','Media', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B001F51B-0787-4A2B-9BF5-A60FF751DAF7','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Instalación sanitaria (saneami', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Hracán ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8B419E06-D4E4-4092-8C90-A7548B3E58DB','0FD32189-1390-4CB3-9047-92F3EF391E1C','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5FBEF8C0-9E66-4814-90B5-A7C020071F0B','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','H','Block de barro hueco vitrifica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0E3DAA86-89DF-40FC-BC4D-A85CE7C2621E','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Nivel de Aguas Mínimo de Opera', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8033F45A-6EB0-499A-8AA0-A870EAD872D7','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('83288640-F669-435C-9EBA-A88C5AA2F02C','1E1DA417-5EC6-4A03-A13E-08EB93D15E77','','Tunel y compuertas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7EF67AFD-3906-4180-B438-A897DE81829D','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros medianos rigidizada', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1E829FEB-B88F-4FC9-B3F9-A92CD6273D75','EA766519-648F-41FB-B429-A21F94758AE8','','Canales', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1664D9AD-45CE-4799-A578-AA896ECE572C','443523EE-F532-4577-956B-64E55A7B89BB','','Mejillón', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C4D145E3-6699-436F-B8AD-AAABE34DD678','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Ninguno', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0E39AF62-2CBD-4879-9105-AAEF8C455442','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red electromecánica (saneamien', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CC956656-46F5-4ED4-A044-AB05F0E6F1F9','2043ACDA-56F7-40CE-A853-233F470FCB76','','Oficinas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('92CFA66C-695A-4220-B584-AB0DC55EA9B9','DB407EA2-A1BB-4254-87C7-6AF894A75AF4','','Depósitos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('675D314E-5322-4499-853A-AB8BBADB6200','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Caminos, puentes y túneles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8F05D1C7-2144-48D7-B31E-AC0E46D897B7','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen al NAME', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8EABC000-3397-4875-93A4-AC535B367ED1','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0D989CDA-F74E-4DB3-967D-AC674C2C550D','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Instrumental médico', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A00A3B20-3F2E-4DB1-9D64-AD251FB3C5FD','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Bordos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4842F83B-FDAD-4764-B290-AD9A1CF723B7','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Canales de drenaje', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BD400EA1-BE2E-4DD6-88B2-ADBF7334E5C3','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Elevación de la corona', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CAE59183-13D5-45CF-9825-AE2695E1731A','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Monto presupuestado destinado ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('39CFCBBB-B016-4685-BC19-AE7E0BB52624','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor promedio de reposición d', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8736B811-A686-493C-B39F-AEBF678C11E2','94E45704-8045-4CF8-B747-763D600789C4','','Capacidad mayor a 100 tonelada', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('56E685AE-B2AE-44F6-A5BD-AF56A0CBBC2F','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor promedio estimado de rec', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D144CAAB-10BB-4EC9-A88E-B00C25E0096F','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Vertederos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0A725F1C-F530-4B18-A2BE-B05BB70F9E18','DE4815D5-E7B5-4C48-A694-180F351E1B41','A','A base de muros de carga y ref', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C3BE71BB-B881-4A2F-A57E-B0F1DF479E64','A232C4C1-5524-4302-9329-F69D050F9698','','Clínica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('30C47124-4A97-4408-A9D0-B101E77006B1','1E1DA417-5EC6-4A03-A13E-08EB93D15E77','','Tunel y válvulas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BC42295A-EB87-4F76-A713-B107362494AD','443523EE-F532-4577-956B-64E55A7B89BB','','Carpa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('205EF8B1-11E3-4674-81BB-B108B08806E1','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n3','prueba 3', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','D2261320-26BD-4560-AD96-0135366285A3','TAM','Tamaulipas ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DA90B6B8-4A96-406D-8D62-B2C0FD3EF7EE','6CB19E01-D41B-4052-9C10-BC5360DD22FE','lvs','Luvisoles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2D09E941-9416-4A8C-9AA9-B2C8581FBCAF','D2261320-26BD-4560-AD96-0135366285A3','TLA','Tlaxcala ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E4B5FDC4-0F64-41F5-9928-B468F23C9C9C','F751B0D0-97F8-402C-9065-077B5136B6D6','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('19CA51CA-EEAE-41E2-8066-B4935E35B1C4','443523EE-F532-4577-956B-64E55A7B89BB','','Camarón', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4CCC8F7D-9119-4C1A-BFFB-B4AD80808A79','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Paneles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E82265D7-CAEB-4C37-8EE0-B5889F3A685F','F751B0D0-97F8-402C-9065-077B5136B6D6','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F4606983-47BB-49C5-96F7-B58AF90DB8F2','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Detalle Otros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('00CE65C2-5197-4B19-B0E3-B5A05B8F96F4','234FB73E-843A-4976-BF1F-48318D352CDA','','Si, severos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4D8486AB-1F7B-4400-8DDD-B6068E2471AE','22A44093-7E34-46D9-92DF-57FE3A335E13','','Estaciones depuradoras', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4DF63070-33F3-4074-AB72-B75010E268AC','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Derrumbe', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5760E0C7-7612-49DA-86CE-B852E04477BD','75B17471-ED30-467C-BA2A-8F8D1D988282','','Distancia', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4B00463B-CD5F-4591-9BE4-B87ECDFA5135','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Nivel de Aguas Máximas Extraor', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D0EDA2D6-E19D-4444-AB5E-B94EE5C85309','A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','','Decantadores o sedimentadores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D4C94600-E425-4712-BA34-BA2C5C21A905','B81B7AC0-310C-4645-9B25-7C4CFE5D1734','','Con claros grandes rigidizada', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BFEADDF6-7B6F-4D1A-9980-BAAE1339E42C','E43CE3ED-6F48-4668-ABF3-222A8533005B','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('645C5B0C-8427-4750-A76A-BACF8B44B6CC','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Región Hidrológica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AD5FE7F0-2A07-4F24-82ED-BB164668E432','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Cuerpo de presa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D43BD6F5-7F8D-488E-A73E-BB19EE57C76E','443523EE-F532-4577-956B-64E55A7B89BB','','Salmón', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('73BD7584-2C2D-40C4-A7A1-BCC1DC65C6A8','DB407EA2-A1BB-4254-87C7-6AF894A75AF4','','Embalses', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('450511D0-7DC6-4157-8420-BCEA1F8E412A','6CB19E01-D41B-4052-9C10-BC5360DD22FE','Gls','Gleysoloes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('812CE4EF-783F-4E2B-9218-BD3D3E30D3EA','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor unitario estimado de rep', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5C626B8E-B782-4582-9A44-BD9332C3150A','E43CE3ED-6F48-4668-ABF3-222A8533005B','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D5AAE3FE-5779-4D9C-8856-BDB9201A463E','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Valor obra civil', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('67BA88F5-D327-460C-A96D-BDDC0665A3C6','B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','','Enajenación por asignación dir', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DCEE544D-049F-48F3-B205-BE2460E5F476','F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','','Abastecimiento.', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A278335A-5C15-4087-8D5F-BF9DED3C7ACB','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Total kilómetros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EC27C80D-D935-4231-BDEE-BFA35B62C4E6','B1137091-9300-4989-BE0F-4D38B9C543C6','','Escuelas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F5F784DD-2BC6-4522-ADA9-BFD708880277','782F77AC-0455-45F8-8B8F-F29C9DC058B9','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('11F98018-15F8-482D-AC33-C000B7B716BF','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AF0D4D6B-C9C8-48B4-9D32-C00BFA13336D','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Secundaria', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D50DAED1-18C1-49B3-BD2D-C0105E3A58E2','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red de alimentación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3AC58692-7E6B-4F77-99C9-C0F402F3BF36','5E0E74BD-7EAE-4257-B19B-89692156AC08','','Medianos > 7 y < 12 mts', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F7BD1F33-BEEE-429F-8549-C17D2458E51E','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Altura sobre el calce', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0228C44B-185A-45E4-BE8D-C1D984F67670','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Longitud', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('46FF5ECD-3C14-47DF-953C-C1F52CA65D77','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Depósitos de combustible', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('79C547A6-9529-418B-B0BD-C214A8C33B14','27BC594A-4F9B-44D2-924D-D0AE52D54A8E','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C8E0BE4A-1918-47CE-A381-C25CD0C16B83','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','A','Adobe', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1E171CDE-E651-4F48-BA34-C2AB9F5E7AD8','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor de reposición a nuevo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('75DF1869-DE9A-4670-8DF6-C2D6563A87A4','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','I','Costaneros de asbesto sobre pe', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('66F2BFB7-D569-4ED0-8AEB-C30DE3D3E7CA','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Monto presupuestado destinado ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('08839679-93F8-43A0-B0C8-C3AA75147EB6','D2261320-26BD-4560-AD96-0135366285A3','VER','Veracruz', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('84B02DB6-3D76-47F4-9D85-C41DC13FC94D','54DA8427-56C1-4EC7-A258-FBA21E9303D2','','En suelo y roca', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('893CA3F9-A621-40B7-92BF-C43A368B1694','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D3F0A270-9FF5-499B-9F55-C46ACE58A5C9','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Altura del parapeto', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('65225D21-2C9F-4D9A-8A8B-C5604C12D5CA','E8E42A49-5874-4AE2-9070-8C15EE163201','','Mamposteria', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0189AC40-DB42-4466-818B-C66DA492C068','F3244557-C16D-472E-BC31-CA403BE81DA0','','Techo curvo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A9A8D618-EDA0-47DB-9F19-C6D67A5E36D4','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n4','Prueba 4', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E40EB25A-3F3E-41AF-9118-C79FA36D1991','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Cimbras', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2CBD0817-D3E2-49F7-B2F3-C82C2CCDDD50','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','','Mucha', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','75B17471-ED30-467C-BA2A-8F8D1D988282','X','Longitude', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DA7A7E78-AE66-42DD-B587-C8781061C046','C52AF614-2865-4708-B489-1BE720793BE6','','G.    presa de arco', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C4B66D66-D0BC-48EF-B3AB-C8BFAEAAF28F','3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','','Residual', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B350B8B1-4B3C-49CD-8609-C922E9918A1D','B56CE9B9-A793-4C6E-995D-EB7D6490F71E','','Nula', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('38750791-01FB-413F-BB6A-C9AB7CEC36C5','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Geometría regular', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('79516D64-A265-4CE6-9348-CA10D9652EE6','4F217F72-98B8-445F-AF8C-396B2B06671B','D','En puertas y ventanas perfiles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BC688C66-B0FE-4FF7-A006-CA1EF89D2321','76A8D090-EB15-4600-ADB5-9F68043E46A5','','Puente', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','D2261320-26BD-4560-AD96-0135366285A3','YUC','Yucatán', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E8A3186E-D0F1-4C18-9BCE-CA4E4870F1C7','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Num Puentes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A38D04BF-44E1-4EBD-9FC0-CA7E809862DF','1B44E444-42A5-463B-AC94-0FA2DAFCB2D8','','Ligera (lamina)', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BAE65756-D9B9-4373-B505-CB0E1632E377','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Equipos de diagnósitoco y labo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('12852E10-512B-4C15-9B65-CB45B77E4D7B','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Desvio temporal', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2CA2B76D-138B-493D-80C9-CB510071E89B','5E0E74BD-7EAE-4257-B19B-89692156AC08','','Cortos < 7 mts', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8A95F972-8730-4C42-9210-CB78E5FF3836','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','ExistenObjetos en azotea', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DAE92CF4-36BB-4186-946F-CC2C67FC1E95','0D4FEBCD-D28A-4293-9065-A5A906BF6E69','','De 10 a 15 m', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E80C6CC0-C386-4655-80A0-CC2F1D4D3AF0','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Cancelería', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('57E87AC8-BACE-431B-8409-CC8E3DA7F368','C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','','Bocatomas de derivación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2A6946F8-6103-4793-90B5-CD00B11F5226','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','K','Costaneros de lamina pintro so', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('ACD6980D-0C78-4EFE-B8F8-CD41415DD286','1E1DA417-5EC6-4A03-A13E-08EB93D15E77','','Torre-galería', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5DE56387-077D-48F4-8638-CD4B4C7CF639','6DAC2E76-3A32-401C-BBEB-1CC3CDA7E428','','Canal de riego con dren --> pu', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F5758A40-90CC-4012-93B7-CD828FF961C9','7D3B0E9B-7336-4E89-874B-DD0073B2B75A','','Depresiones', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FFB983D3-BD84-45CA-B23A-CDCAB4FB6FC4','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Tipo de túnel', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C260A1F1-21CC-4654-9422-CE265565928E','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E8776B49-033B-45AF-BDF8-CE3DA9D76470','083726F8-4AEB-45E8-85E8-47B69130FFE5','','Tuneles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('030908EE-7446-4290-93F1-CE775B99CE2E','539D1830-90A8-484C-B183-F79A448CEC78','','B.3. canal lateral', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Elevación de desplante de la p', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FCDE3AC9-F416-4EB0-B9F4-CFE0B51C6500','7EB43883-335D-4184-86F3-D899EB2F6784','','Homogenea de tierra', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E93721DC-206C-4918-9638-CFEDFA047031','C5F22BCB-5B7E-4C20-B284-0B71B311BE78','','Oficinas administrativas de si', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('86267EF7-9149-4E12-97D8-D0013249417B','F4DBA965-B5D9-4A89-93A2-C55850E82FFB','','Distante a la cortina (indicar', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('23806607-AF02-4E82-B28D-D011783F0EB3','12634A22-8918-4A1C-B2DC-BE4C827735CA','','Madera', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('08D5E6FA-D3BE-4E4A-8B0F-D022FCD1BC3C','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Red electromecánica', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8F29339F-3AC7-446E-9142-D0637466054A','F4DBA965-B5D9-4A89-93A2-C55850E82FFB','','Subterránea', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('85B9E0DC-7F12-49B1-849A-D09F2404AF7C','07C3B108-6581-4DA7-85BC-FF6263924E99','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('729063FB-EA8D-4346-A5DF-D0BC1242ECB4','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Techumbres', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7296391B-93F8-45D0-986B-D1819E43CF0D','0FD32189-1390-4CB3-9047-92F3EF391E1C','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1507EDE4-43A0-446B-B301-D2101DD49DD2','0E28A695-8644-4E13-92D1-F32EAEEEA29A','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('45DB1D54-05AC-4920-8313-D27183F52AAF','F2C11D82-03E7-4771-9231-31283AD1D519','','De menor y mator altura', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('39298EC3-EA9B-4D99-931A-D2A58DCFFD86','8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','','Brecha', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D9D28390-F7A7-4794-8909-D39281E89C03','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Pintura', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('65191D5A-49AF-41EA-8A03-D3999F9A7FD7','F2C11D82-03E7-4771-9231-31283AD1D519','','De igual altura', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0E815275-F782-4EB0-A254-D3CC7E16A6BE','B504418E-8F8D-4086-8E59-CD615707E5FB','','Escalera de peces', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B59B7EFB-42BB-4640-B301-D3CF1DDCC3F5','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Túneles o ductos de conducción', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7EBF93C4-F1BB-481C-B82F-D48A91794D12','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('92E3D299-AC40-4B52-867E-D537E1E77B57','F2C11D82-03E7-4771-9231-31283AD1D519','','De menor altura', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7FF87226-25E6-47DB-8BE8-D58081F851FD','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen al NAMO', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('733E4658-52B3-4E64-B526-D5C9EA0ECA9B','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Bocatoma', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CB3F18B5-6FB1-46FC-807E-D5E29DFFDDF0','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','Colgante', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FAB78DE3-AB97-4BB7-91B7-D5E4BF7FA7B1','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Cortina', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('204266D3-3109-432D-AE4F-D679CC4EE903','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2C1D1D7D-1407-42AB-A22C-D73159051F4A','4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','','Acuícola', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('97D7A91E-3544-4C1E-B3B0-D785EE8FF350','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor unitario estimado de rep', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9F81001A-AD8E-4CCB-8F7D-D7C2035F2CBC','7EB43883-335D-4184-86F3-D899EB2F6784','','Bloques labrados', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8EA8FFD6-5722-4823-B9CB-D90AA6DEC587','F2FB2D80-6B35-40CF-961C-18D6A63C2518','','Altura máxima', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5318380B-145B-442E-8F30-DA2868331399','4F1CFD2A-E24E-4AD5-B2C6-FC20367D0FFC','','Río', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1B034109-50B9-44D6-B6EA-DB0AB08496F2','3585818D-7660-4569-A24F-F5F0999D4705','E','Muertos de concreto simple', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A48C6EA3-C71E-4CB4-A161-DC3C8A7DA95D','C52AF614-2865-4708-B489-1BE720793BE6','','B.    escollera', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DCE35523-84B1-41D0-B8BA-DC558F562C9E','F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','','Riegos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C08E5B7F-0114-41FD-9A90-DCADE741C8E8','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Geometría irregular', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2891F3E2-2970-4EBB-B00C-DD4B379BF6EF','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('24431C2E-E8D0-4EFD-812C-DD7C02D37392','54DA8427-56C1-4EC7-A258-FBA21E9303D2','','En suelo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('03FCF704-BCFF-460B-B8B0-DD7C4537376F','5C5A425D-AC7E-4103-A64D-D69ACD97DD50','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2E8CE8FC-DEBD-40BA-9F5A-DD834CCBCEF0','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Techumbres', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F8254142-FC4C-48AF-B4EC-DD8D0DAD7D15','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones saturadas', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('417E779A-78C5-45A6-A238-DDCBD482FEA9','07C3B108-6581-4DA7-85BC-FF6263924E99','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('29751529-FCEF-4FBF-B399-DE0225703908','5E0E74BD-7EAE-4257-B19B-89692156AC08','','Largos > 12 mts', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('78C3C3F1-575E-4927-8D49-DE686946E3D6','3585818D-7660-4569-A24F-F5F0999D4705','C','Losa de cimentación de concret', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1FAB1802-29ED-4B65-A1A9-DE98F62CAFAA','4F217F72-98B8-445F-AF8C-396B2B06671B','A','En puertas y ventanas perfiles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F4B048C4-0A44-4E54-9E01-DE9E2C2F1DC9','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor promedio de reposición d', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C71BB043-E793-4689-BBC8-DFB58291C010','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Dispositivos móviles de riego ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A26A5A87-7149-435C-B7B7-E008663D3C9A','A3D049D7-DFC9-47A1-B88A-B9288834068F','','Madera', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('81BE09A2-7E94-4C0B-8611-E03F1CA38794','C52AF614-2865-4708-B489-1BE720793BE6','','E.     presa de bóvedas múltip', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F7F9E1A6-8CC0-4391-9B50-E089DABECB34','C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','E','Ruinosa', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DCF27175-EE31-45C5-B91E-E0BF7C10AEEA','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Pintura', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('79BFB7AF-1B31-4088-BF61-E0DAEF07FB83','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Red de abastecimiento de energ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6419CE4B-D535-4BCE-AF9F-E0DBB3AC3F3B','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Número de cuerpos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('87EB66D2-EA84-4358-AFD4-E14962854AB7','E43CE3ED-6F48-4668-ABF3-222A8533005B','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8C3626DA-562E-4835-98CF-E1A937767C5A','A232C4C1-5524-4302-9329-F69D050F9698','','Bodega', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B442A62B-DFFD-4254-AE88-E1CF8ADBC2A5','539D1830-90A8-484C-B183-F79A448CEC78','','A. cimacio tipo creager', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('57552A4E-9AB7-4AB7-AA2F-E21712653957','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Otros', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('04F58B3F-745A-49CD-AA8F-E28840386E00','CA38F79C-B87A-4B9B-BC1D-2002B2658C9B','','Poca', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D0EEDDA4-5B01-4E64-A244-E3BD489C5EE8','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Red electromecánica (obras de ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2D9DE949-A018-4E35-B2EE-E425AEE8417C','8831E933-E4BB-47DF-A0FA-139EC6495C73','','Vertedor de pozo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('58D09D38-3A21-4512-AD31-E442718788D0','443523EE-F532-4577-956B-64E55A7B89BB','','Robalo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DF9D370B-55C4-4CC5-89BA-E4E315F5665A','0FD32189-1390-4CB3-9047-92F3EF391E1C','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A102999C-E84E-4A90-B910-E52A499BBDD6','4B5B4221-B295-4B08-9C51-2B3BB231081C','','Cimentaciones en limo-arcilla', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AE30C03C-005C-4F7C-85EF-E5439B932982','4F1CFD2A-E24E-4AD5-B2C6-FC20367D0FFC','','Manantial', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4FBB6137-9322-4C6E-8AAB-E5471DF59F20','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','NumPasos superiores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('549D9B9D-CEC5-43F4-AB00-E5FE4B8B4EFC','4F217F72-98B8-445F-AF8C-396B2B06671B','C','En puertas y ventanas perfiles', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E3A00057-7CAA-4DC4-8C1F-E687F10AE76B','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Pozos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D99AB80F-6E10-488E-8F1E-E6B09BBFC9C9','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Instrucciones especiales de oe', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3771EE8D-1F6F-428F-BA91-E6F4B12BB8A0','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Cds', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('379A69FE-806F-43A7-A4C2-E72DBEA27B20','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Almacenes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C37B98C5-CCC6-4B8D-858F-E7D8E1513CC6','B56CE9B9-A793-4C6E-995D-EB7D6490F71E','','Poca', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('226D3BEC-B08D-473C-8C24-E80FE3A0B771','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Sismo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1378BDB0-474D-4CA5-B894-E89C2FB01A1B','782F77AC-0455-45F8-8B8F-F29C9DC058B9','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D066282F-5BB9-485D-AEB7-E89D3568DCAD','F51D5AC0-DFBE-499E-AA84-4906E77E6E41','I','Cristal de 6mm. biselado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4421CCC8-0AC6-4D35-A69B-E8C4815B626E','3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','','Obras de toma', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DE39610E-E351-4AB4-9420-E92A70FDE789','1ACD8B10-3A71-4481-ABEA-70E91B70B53A','','Suelos de grano fino (e.g. lim', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5BB50BB8-CEE1-49D8-83A6-E9D8E2EAA35B','E8E42A49-5874-4AE2-9070-8C15EE163201','','Plastico', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6D01807F-124C-481D-AF36-E9ED617DB05D','0FD32189-1390-4CB3-9047-92F3EF391E1C','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DE7386AB-91E7-415E-B58B-EAE09CCCDCEF','0615CF81-8E65-4D64-A529-768B1D6C79FD','','Deslave', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('D94C5681-51CE-4467-A4C4-EB791072742A','9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B0C6DD01-FF6A-49D8-92D6-EBC6FB6EF0CA','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen de avenida máxima regi', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EA0C637F-F7E2-4F47-BB03-EC719BA7475D','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Nivel de Aguas Máximas Ordinar', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('98DA7E9F-8377-46A0-92F7-EC7535A5667F','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','Atirantado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4083D9B1-C947-4971-9584-EC8B371295CE','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor obra civil', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('5D96A958-63F4-497C-9C5B-EC973DEBE3C6','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','','Almacenamiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4FACD655-FCB4-4734-9987-ECF17A367D21','81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','','Gravedad', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('76F3AC4C-0A22-45BF-9456-ED0D317FA369','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1524E843-BEBE-4D0E-92E5-ED1AC5CA0EFF','4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','','Hipocloradores', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('7FD12A6E-A23B-4E13-9618-ED5BAB4807DD','3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','','Sedimentario', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('017C56AF-4B38-43D7-96E9-ED64BB58786C','F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','','Uso mixto.', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F47629FB-4D92-40AC-9861-ED989610FDAC','3585818D-7660-4569-A24F-F5F0999D4705','B','Zapatas aisladas, dados, contr', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('EF279DAE-7F58-425B-B517-EDAC96F5B338','94E45704-8045-4CF8-B747-763D600789C4','','Capacidad de menor de 10 tonel', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F83A9BA0-6D0C-4C1B-AAE7-EDB04056ABF3','D2261320-26BD-4560-AD96-0135366285A3','ZAC','Zacatecas ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1C5D6DBF-3D7A-4109-B209-EE743EEC9ACA','8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','','De arco en acero', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C28872F2-BAEB-41AE-85C7-EE7918A618CD','98068CA2-66D7-4B3C-9E34-B39372B9D59C','','Reja para el desbaste y la ret', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('04CBA926-49D4-4ACD-A1CE-EE8962505356','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Pintura', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('FCBB3903-A20C-498C-B7F8-EE978F6FF769','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Organismo responsable', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('1BA1A59F-CB38-41D1-83C1-EEB4D105EF1E','2043ACDA-56F7-40CE-A853-233F470FCB76','','Báscula', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2FF330C5-F6ED-443E-9310-EF398E763FCF','539D1830-90A8-484C-B183-F79A448CEC78','','B.1. económico o lavadero', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('CE2343ED-DD2F-4390-BFC5-EFD56A08F6E7','68A049E3-4B55-4F48-AE03-A6CB920028B8','','Río', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F891E369-8570-42AE-883C-F105D840F9C9','B504418E-8F8D-4086-8E59-CD615707E5FB','','Túnel de derivación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C4665A95-5979-4C2F-A83B-F196A05068EE','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Anuncios espectaculares', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('132AB919-C75C-4878-8F05-F1EF8A436263','54DA8427-56C1-4EC7-A258-FBA21E9303D2','','En roca', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('524C7CFF-DFCD-4976-901A-F272F7616254','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Equipo de telecomunicaciones', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C30E3F8D-CBD3-42B0-966B-F2DF8BC04C3B','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('07B1CC81-92D5-4F55-8A56-F2F5E0F7B791','B5A4C0E7-FF66-4988-A957-F8FC519E2895','','Otro tipo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('40F69BB2-AAC5-4197-9808-F3EF069E1053','8831E933-E4BB-47DF-A0FA-139EC6495C73','','Cortado en la roca adyacente a', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DBE30BC0-C58B-41CB-B343-F40D18AC6355','EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','','Arbolados, lomerios, barrio re', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4089A68B-73B1-4C3A-96D2-F4D671F7E16D','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','','Control de afluentes', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E0825274-57D0-43C4-9209-F52C7F2D7287','1758A063-FF75-4AAB-9D43-6FDD89946A54','','Monotubo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('23A11090-4CD0-4256-A285-F56441CD5E75','443523EE-F532-4577-956B-64E55A7B89BB','','Corvina', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E960A7BF-A572-4D63-9405-F5920E785210','F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','','Volúmen de avenida Diseño', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('A00BC745-DA49-4D7F-9068-F60AADF9B397','089475EF-D242-4F7B-9C0C-CBFE98BB480D','','Medianos >.25 y <1 m2', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('632E5F23-427B-4AC3-85B9-F612B4064EBD','2043ACDA-56F7-40CE-A853-233F470FCB76','','Caminos de acceso', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('115D7CEF-07C0-4427-BCFB-F62A351D9A3B','75B17471-ED30-467C-BA2A-8F8D1D988282','H','Altitude', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('2B813A7C-8D89-4E08-8D86-F63AE2442A60','C52AF614-2865-4708-B489-1BE720793BE6','','A.     terraplén', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('95566830-EFF4-4048-9175-F6ACF22C3ABA','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Valor maquinara y equipo', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0F94528A-4CB2-4378-A2CB-F6B491BD488B','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','M','Spancret o similar', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0F3D9596-69B3-4572-8605-F7474FF558D3','75B17471-ED30-467C-BA2A-8F8D1D988282','S','StateID', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('3D57AD96-3FCC-4D91-9540-F79F46C8D33F','DE6350AF-17E3-40F6-9D76-7DCA124E3D38','','Domos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('63DFF92C-7420-4AAC-85BC-F7A358FCDA79','E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','A','Nueva', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('B0BDE85B-5FE6-42EB-85D8-F82F8815D5A9','782F77AC-0455-45F8-8B8F-F29C9DC058B9','C','Buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('87029EB0-680B-4057-8ED2-F886E74C5947','FD0490B9-581D-4970-80AD-76A3AC8A8DDB','','Pozos de inspección', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('4B88AB8E-70BD-4E91-9EE4-F8988F919DE4','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Vertederos', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('88EC99E7-FB7C-44E5-A91A-F8E4CC3EF71B','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Mnto destinado anualmente a ma', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DAEA5CE9-FFC2-4F92-A94F-F92CB92EF07E','782F77AC-0455-45F8-8B8F-F29C9DC058B9','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('0D2A6CDF-C437-4910-881E-F996D6EB7676','84987039-D458-480B-8B34-0C7AEE808206','','Concreto armado', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('BD6B4922-321D-4A57-9ED2-FA9A74B41CE1','D2261320-26BD-4560-AD96-0135366285A3','DIF','Distrito Federal ', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9E4EFA91-A546-4DA4-B760-FAA5C74D1401','E8E42A49-5874-4AE2-9070-8C15EE163201','','Vidrio', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Edificio', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('84D2BAA4-800E-480A-BEC7-FAF59FDFBCC7','443523EE-F532-4577-956B-64E55A7B89BB','','Langosta', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8B6B976C-13DD-462F-9359-FAFE07940FC5','BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','','Túneles o ductos de conducción', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('AC855C6E-6189-41CB-BDB2-FB22A6B5DD85','1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','D','Mala', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('268F8011-963B-4FE6-B8AD-FB7E17770FB0','7EB43883-335D-4184-86F3-D899EB2F6784','','Enrocamiento con hormigón', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E6C1B3A9-0FE0-44B9-99FF-FB87A00F4AD3','83D0B22E-2CB4-443F-A540-5692DBDBBBE9','C','Block hueco (cemento-arena)', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('DDC52FD1-790E-4686-AD8C-FBC23E1F780A','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','','Derivadoras', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('F19AC24F-F1D8-4349-BCF1-FCF3C79D1047','DB407EA2-A1BB-4254-87C7-6AF894A75AF4','','Tratamiento', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6CDF9CEA-0317-43FE-9644-FD774CC779D3','15641A0F-51F0-4969-AD95-90EF456AFE13','B','Muy buena', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('58482DFF-E530-4C58-B04C-FD819B5DE8D0','B504418E-8F8D-4086-8E59-CD615707E5FB','','Cuencas de disipación', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('9703AFF7-BFCD-4246-A074-FE317F846787','2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','','Edificio', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('E50940EC-4701-4573-89A2-FE8DF7FE5FAF','3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','','Obras de toma', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('6C27DCF0-6A78-44FC-B8FB-FED854952651','1C15926A-28A6-4D67-B1E6-0F1C28670BEC','','Área de almacenamiento de insu', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('8418AEAF-CEA8-49A9-B90F-FF18DE8A3199','539D1830-90A8-484C-B183-F79A448CEC78','','A.3. canal lateral', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('C67C16CB-C192-4171-9085-FF85DCB3FF12','DD53EA87-1868-4DE9-A15C-0A6B1890CADC','','Lerma santiago pacífico', 0)");
        db.execSQL("INSERT INTO TBL_CatalogsContent(_id, CatalogID, ShortName, Description, Lock) VALUES('42E3FD97-66C9-48FE-BE4B-FFF08F878CD6','4BFE74A3-53E3-4779-9AD1-03B057E63BF3','n1','Prueba 2', 0)");
    }

    public void insertInto_TBL_CatalogsNames_VG(SQLiteDatabase db){
        db.execSQL("insert Into TBL_CatalogsNames Values ('00000000-0000-0000-0000-000000000000','Ninguno',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('D2261320-26BD-4560-AD96-0135366285A3','Estados de la república',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','Conceptos de enseres para reposición',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('4BFE74A3-53E3-4779-9AD1-03B057E63BF3','Propósito de construcción',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('F751B0D0-97F8-402C-9065-077B5136B6D6','Estado de conservación Carpintería',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('1E1DA417-5EC6-4A03-A13E-08EB93D15E77','Tipos obras de tomas',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('DD53EA87-1868-4DE9-A15C-0A6B1890CADC','Cuencas hidrologicas',4,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('C5F22BCB-5B7E-4C20-B284-0B71B311BE78','Tipo de infraestructura urbana municipal',8,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('B374ABA0-9C03-4916-ADE8-0BA48161CE05','Vías de acceso',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('84987039-D458-480B-8B34-0C7AEE808206','Tipo de recubrimiento',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('8EB882ED-6597-4111-ACD1-0DFF899B2BD8','Sistemas contra incendio',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('E398849C-BDF2-4ABB-AFB0-0E4E8C8EF326','Red alcantarillado',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('1C15926A-28A6-4D67-B1E6-0F1C28670BEC','Componentes no hidraulicos',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('E9D156CD-19DE-43F1-8B17-0F33227A90B2','Tipo de compuertas',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('1B44E444-42A5-463B-AC94-0FA2DAFCB2D8','Tpos de cubierta (edificios) ',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('0248C639-8F72-45D5-BF9F-1028C1B2CCE4','Esclusas',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('754E0F98-3A4A-4D1B-9B84-135B860A7791','Sismicidad',9,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('8831E933-E4BB-47DF-A0FA-139EC6495C73','Localización y tipo de vertedor',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('DE4815D5-E7B5-4C48-A694-180F351E1B41','Tipo de estructura',8,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('20F41507-5058-466C-AD1E-1819B8A6207F','Características de Galería',6,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('F2FB2D80-6B35-40CF-961C-18D6A63C2518','Características de cortina',6,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('C52AF614-2865-4708-B489-1BE720793BE6','Tipo de cortina',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('6DAC2E76-3A32-401C-BBEB-1CC3CDA7E428','Tipo de cruces',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','Topografía',2,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','Tipos de presas',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('CA38F79C-B87A-4B9B-BC1D-2002B2658C9B','Área expuesta al viento',9,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('E43CE3ED-6F48-4668-ABF3-222A8533005B','Estados de conservación',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('2043ACDA-56F7-40CE-A853-233F470FCB76','Obras complementarias',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('4B5B4221-B295-4B08-9C51-2B3BB231081C','Tipos de cimentación en presas',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('F2C11D82-03E7-4771-9231-31283AD1D519','Golpeteo con otro efidificio',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('5F7C9D4C-B206-4791-B14B-328E40FA25E2','Uso del agua',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','Estado de conservación Lambrines',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','Tipo de camino',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('4F217F72-98B8-445F-AF8C-396B2B06671B','Tipo de herrería',8,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','Tipos de puente',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('BCC6226E-FE2E-48E3-A401-459587F6A2A2','techo',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('083726F8-4AEB-45E8-85E8-47B69130FFE5','Tipo de inmuebles',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('234FB73E-843A-4976-BF1F-48318D352CDA','Daños estructurales',9,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('F51D5AC0-DFBE-499E-AA84-4906E77E6E41','Tipo de vidriería',8,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','Usos de presas',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('B1137091-9300-4989-BE0F-4D38B9C543C6','Tipo infraestructura educación',8,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','Tipo de almacenamiento de agua tratada',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('967615A4-74A4-40C3-B276-4EFCBDE63A48','Administrador por',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('83D0B22E-2CB4-443F-A540-5692DBDBBBE9','Tipo de muros',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('22A44093-7E34-46D9-92DF-57FE3A335E13','Redes de desagüe',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','Justificaciones',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('443523EE-F532-4577-956B-64E55A7B89BB','Tipo centro acuícola',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('DB407EA2-A1BB-4254-87C7-6AF894A75AF4','Redes de agua potable',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','Tipo de fachada',8,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('1758A063-FF75-4AAB-9D43-6FDD89946A54','Tipo de túneles',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('1ACD8B10-3A71-4481-ABEA-70E91B70B53A','Tipo de suelo presas',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('94E45704-8045-4CF8-B747-763D600789C4','Tipo de infraestructura para la disposición final de resuduos sólidos urbanos',8,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('0615CF81-8E65-4D64-A529-768B1D6C79FD','Evento Natural',9,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('FD0490B9-581D-4970-80AD-76A3AC8A8DDB','Generales',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('B4C32095-C18C-4448-BB13-7725B88EF351','Estructura externa mas cercana',9,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('B81B7AC0-310C-4645-9B25-7C4CFE5D1734','Formas de cubierta (naves industriales)',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('DE6350AF-17E3-40F6-9D76-7DCA124E3D38','Objetos en azotea',9,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('5E0E74BD-7EAE-4257-B19B-89692156AC08','Separación de columnas',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('37CE79B4-15EB-4BF3-892F-897223CFD12E','Características de Dique',6,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('E8E42A49-5874-4AE2-9070-8C15EE163201','Tipo de material en la fachada',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('75B17471-ED30-467C-BA2A-8F8D1D988282','GPS',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('15641A0F-51F0-4969-AD95-90EF456AFE13','Estado de conservación Cancelería',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('0FD32189-1390-4CB3-9047-92F3EF391E1C','Estado de conservación Instalación sanitaria',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','Estado de conservación Pisos',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('76A8D090-EB15-4600-ADB5-9F68043E46A5','Tipo de infraestructura urbana',8,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','Conceptos de enseres a valor real',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('EA766519-648F-41FB-B429-A21F94758AE8','Tipo de conductos',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('BA697C53-E720-4D8C-BB2F-A405ED51F43D','Tipo de acometidas',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('0D4FEBCD-D28A-4293-9065-A5A906BF6E69','Tamaño de la cortina',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('68A049E3-4B55-4F48-AE03-A6CB920028B8','Cuerpos de agua',4,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','Estado de conservación Acabados exteriores',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('98068CA2-66D7-4B3C-9E34-B39372B9D59C','Estaciones de bombeo',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','Estructuras complementarias',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('EF7170F5-F452-47A9-8E1E-B6ED39DE7E90','Tipo de protecciones',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('A3D049D7-DFC9-47A1-B88A-B9288834068F','Material de entrepisos',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('EA50619F-3012-4841-8939-BB83634FCF9C','Comportamiento de la cortina',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('6CB19E01-D41B-4052-9C10-BC5360DD22FE','Tipos de suelo',2,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','Tipo de subsuelo',2,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('12634A22-8918-4A1C-B2DC-BE4C827735CA','Estructura de entrepisos',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','Estado de conservación Pintura',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','Componentes hidraulicos',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('F4DBA965-B5D9-4A89-93A2-C55850E82FFB','Localización de las casas de máquinas',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('241FA096-9264-4150-AF9E-C9694D5E66C2','Tipo de tuberías',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('F3244557-C16D-472E-BC31-CA403BE81DA0','Formas de cubierta (edificios)',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('089475EF-D242-4F7B-9C0C-CBFE98BB480D','Tamaño de cristales expuestos',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','Tipo de infraestructura',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('B504418E-8F8D-4086-8E59-CD615707E5FB','Tipo de represa',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','Irregularidad en elevación y planta',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('27BC594A-4F9B-44D2-924D-D0AE52D54A8E','Estado de conservación Muebles sanitarios',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','Características de preasa',1,0)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('5C5A425D-AC7E-4103-A64D-D69ACD97DD50','Estado de conservación Zoclos',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('464A3F38-B8B5-4828-871B-D717885AE5CB','Tipo de  captación',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('7EB43883-335D-4184-86F3-D899EB2F6784','Material de la cortina',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('7D3B0E9B-7336-4E89-874B-DD0073B2B75A','Tipo de medios estructurales',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','Tipo planta tratamiento agua potable',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('B56CE9B9-A793-4C6E-995D-EB7D6490F71E','Exposición a ser golpeado por objetos arrastrados por el viento',9,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('782F77AC-0455-45F8-8B8F-F29C9DC058B9','Estado de conservación Acabados interiores',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('0E28A695-8644-4E13-92D1-F32EAEEEA29A','Estado de conservación Instalación eléctrica',7,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('3585818D-7660-4569-A24F-F5F0999D4705','Tipo de cimentación',8,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('A232C4C1-5524-4302-9329-F69D050F9698','Tipos uso de edificios',3,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('539D1830-90A8-484C-B183-F79A448CEC78','Tipos de vertedores',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('B5A4C0E7-FF66-4988-A957-F8FC519E2895','Tipos de válvulas',5,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('54DA8427-56C1-4EC7-A258-FBA21E9303D2','Cimentación (presas)',6,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('4F1CFD2A-E24E-4AD5-B2C6-FC20367D0FFC','Tipo de corrientes',4,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','Tipo de canales',4,1)");
        db.execSQL("insert Into TBL_CatalogsNames Values ('07C3B108-6581-4DA7-85BC-FF6263924E99','Estado de conservación Plafones',7,1)");
    }

    public void insertInto_TBL_CatalogsNames(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('00000000-0000-0000-0000-000000000000','Ninguno','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('D2261320-26BD-4560-AD96-0135366285A3','Estados de la república','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('2D181AA7-A5CB-4B3D-B407-01F11AC9CEA7','Conceptos de enseres para reposición','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('4BFE74A3-53E3-4779-9AD1-03B057E63BF3','Propósito de construcción','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('F751B0D0-97F8-402C-9065-077B5136B6D6','Estado de conservación Carpintería','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('1E1DA417-5EC6-4A03-A13E-08EB93D15E77','Tipos obras de tomas','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('DD53EA87-1868-4DE9-A15C-0A6B1890CADC','Cuencas hidrologicas','4', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('C5F22BCB-5B7E-4C20-B284-0B71B311BE78','Tipo de infraestructura urbana municipal','8', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('B374ABA0-9C03-4916-ADE8-0BA48161CE05','Vías de acceso','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('84987039-D458-480B-8B34-0C7AEE808206','Tipo de recubrimiento','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('E398849C-BDF2-4ABB-AFB0-0E4E8C8EF326','Red alcantarillado','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('1C15926A-28A6-4D67-B1E6-0F1C28670BEC','Componentes no hidraulicos','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('E9D156CD-19DE-43F1-8B17-0F33227A90B2','Tipo de compuertas','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('1B44E444-42A5-463B-AC94-0FA2DAFCB2D8','Tpos de cubierta (edificios) ','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('0248C639-8F72-45D5-BF9F-1028C1B2CCE4','Esclusas','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('754E0F98-3A4A-4D1B-9B84-135B860A7791','Sismicidad','9', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('8831E933-E4BB-47DF-A0FA-139EC6495C73','Localización y tipo de vertedor','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('DE4815D5-E7B5-4C48-A694-180F351E1B41','Tipo de estructura','8', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('20F41507-5058-466C-AD1E-1819B8A6207F','Características de Galería','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('F2FB2D80-6B35-40CF-961C-18D6A63C2518','Características de cortina','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('C52AF614-2865-4708-B489-1BE720793BE6','Tipo de cortina','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('6DAC2E76-3A32-401C-BBEB-1CC3CDA7E428','Tipo de cruces','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('EDD5134C-4295-4610-A0C0-1DCB6D72E2E9','Topografía','2', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('81ECCB61-47B9-4B59-8EB7-1E2D8325B7B7','Tipos de presas','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('CA38F79C-B87A-4B9B-BC1D-2002B2658C9B','Área expuesta al viento','9', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('E43CE3ED-6F48-4668-ABF3-222A8533005B','Estados de conservación','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('2043ACDA-56F7-40CE-A853-233F470FCB76','Obras complementarias','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('4B5B4221-B295-4B08-9C51-2B3BB231081C','Tipos de cimentación en presas','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('F2C11D82-03E7-4771-9231-31283AD1D519','Golpeteo con otro efidificio','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('5F7C9D4C-B206-4791-B14B-328E40FA25E2','Uso del agua','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('1E8F41F0-A2B5-4572-BB90-372CF9A2CE0A','Estado de conservación Lambrines','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('8412ADA3-170E-4B64-B5B7-38C4E1BEAF5C','Tipo de camino','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('4F217F72-98B8-445F-AF8C-396B2B06671B','Tipo de herrería','8', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('8DE9D50F-A3C4-43F4-AEA9-3B8351014B6D','Tipos de puente','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('BCC6226E-FE2E-48E3-A401-459587F6A2A2','techo','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('083726F8-4AEB-45E8-85E8-47B69130FFE5','Tipo de inmuebles','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('234FB73E-843A-4976-BF1F-48318D352CDA','Daños estructurales','9', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('F51D5AC0-DFBE-499E-AA84-4906E77E6E41','Tipo de vidriería','8', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('F7D797CF-FC6B-4785-AB51-4BDEB1BB04F6','Usos de presas','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('B1137091-9300-4989-BE0F-4D38B9C543C6','Tipo infraestructura educación','8', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('4847DEDD-3FFF-4C7C-A980-4E0E6700EAAC','Tipo de almacenamiento de agua tratada','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('967615A4-74A4-40C3-B276-4EFCBDE63A48','Administrador por','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('83D0B22E-2CB4-443F-A540-5692DBDBBBE9','Tipo de muros','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('22A44093-7E34-46D9-92DF-57FE3A335E13','Redes de desagüe','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('B5E3D7F5-3861-4E4C-91A9-5C56D8825D4F','Justificaciones','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('443523EE-F532-4577-956B-64E55A7B89BB','Tipo centro acuícola','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('DB407EA2-A1BB-4254-87C7-6AF894A75AF4','Redes de agua potable','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('AC444BBE-ACCE-4D04-95D0-6D65DC6FD145','Tipo de fachada','8', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('1758A063-FF75-4AAB-9D43-6FDD89946A54','Tipo de túneles','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('1ACD8B10-3A71-4481-ABEA-70E91B70B53A','Tipo de suelo presas','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('94E45704-8045-4CF8-B747-763D600789C4','Tipo de infraestructura para la disposición final ','8', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('0615CF81-8E65-4D64-A529-768B1D6C79FD','Evento Natural','9', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('FD0490B9-581D-4970-80AD-76A3AC8A8DDB','Generales','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('B4C32095-C18C-4448-BB13-7725B88EF351','Estructura externa mas cercana','9', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('B81B7AC0-310C-4645-9B25-7C4CFE5D1734','Formas de cubierta (naves industriales)','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('DE6350AF-17E3-40F6-9D76-7DCA124E3D38','Objetos en azotea','9', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('5E0E74BD-7EAE-4257-B19B-89692156AC08','Separación de columnas','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('37CE79B4-15EB-4BF3-892F-897223CFD12E','Características de Dique','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('E8E42A49-5874-4AE2-9070-8C15EE163201','Tipo de material en la fachada','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('75B17471-ED30-467C-BA2A-8F8D1D988282','GPS','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('15641A0F-51F0-4969-AD95-90EF456AFE13','Estado de conservación Cancelería','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('0FD32189-1390-4CB3-9047-92F3EF391E1C','Estado de conservación Instalación sanitaria','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('C62591A7-FF81-4FD8-A9BD-9F0E1DBBF86C','Estado de conservación Pisos','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('76A8D090-EB15-4600-ADB5-9F68043E46A5','Tipo de infraestructura urbana','8', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('BE7C2A9B-9CCD-4968-93D7-A0284A0A16FC','Conceptos de enseres a valor real','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('EA766519-648F-41FB-B429-A21F94758AE8','Tipo de conductos','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('BA697C53-E720-4D8C-BB2F-A405ED51F43D','Tipo de acometidas','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('0D4FEBCD-D28A-4293-9065-A5A906BF6E69','Tamaño de la cortina','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('68A049E3-4B55-4F48-AE03-A6CB920028B8','Cuerpos de agua','4', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('E8BF7D35-6325-469E-9B5B-AC6DBB4D7985','Estado de conservación Acabados exteriores','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('98068CA2-66D7-4B3C-9E34-B39372B9D59C','Estaciones de bombeo','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('3448F6E8-D0F3-4AFE-BF3F-B6AD074C5D12','Estructuras complementarias','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('EF7170F5-F452-47A9-8E1E-B6ED39DE7E90','Tipo de protecciones','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('A3D049D7-DFC9-47A1-B88A-B9288834068F','Material de entrepisos','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('EA50619F-3012-4841-8939-BB83634FCF9C','Comportamiento de la cortina','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('6CB19E01-D41B-4052-9C10-BC5360DD22FE','Tipos de suelo','2', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('3871BF6A-FAF4-4DFF-8D02-BE2862BBC173','Tipo de subsuelo','2', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('12634A22-8918-4A1C-B2DC-BE4C827735CA','Estructura de entrepisos','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('9DFC9B94-6A1F-4DEF-A41A-BFA15A309802','Estado de conservación Pintura','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('3D4FAC4C-C391-4329-B69F-BFC7CE8A0EF8','Componentes hidraulicos','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('F4DBA965-B5D9-4A89-93A2-C55850E82FFB','Localización de las casas de máquinas','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('241FA096-9264-4150-AF9E-C9694D5E66C2','Tipo de tuberías','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('F3244557-C16D-472E-BC31-CA403BE81DA0','Formas de cubierta (edificios)','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('089475EF-D242-4F7B-9C0C-CBFE98BB480D','Tamaño de cristales expuestos','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('4986DFC6-EAD3-4DCD-8886-CCFB06417AE6','Tipo de infraestructura','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('B504418E-8F8D-4086-8E59-CD615707E5FB','Tipo de represa','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','Irregularidad en elevación y planta','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('27BC594A-4F9B-44D2-924D-D0AE52D54A8E','Estado de conservación Muebles sanitarios','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('F27F0409-1C5E-4BCE-A379-D4907F9ED5DE','Características de preasa','1', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('5C5A425D-AC7E-4103-A64D-D69ACD97DD50','Estado de conservación Zoclos','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('464A3F38-B8B5-4828-871B-D717885AE5CB','Tipo de  captación','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('7EB43883-335D-4184-86F3-D899EB2F6784','Material de la cortina','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('7D3B0E9B-7336-4E89-874B-DD0073B2B75A','Tipo de medios estructurales','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('A18DFF5C-BC1B-43FA-85C5-E2F6486FE46B','Tipo planta tratamiento agua potable','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('B56CE9B9-A793-4C6E-995D-EB7D6490F71E','Exposición a ser golpeado por objetos arrastrados ','9', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('782F77AC-0455-45F8-8B8F-F29C9DC058B9','Estado de conservación Acabados interiores','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('0E28A695-8644-4E13-92D1-F32EAEEEA29A','Estado de conservación Instalación eléctrica','7', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('3585818D-7660-4569-A24F-F5F0999D4705','Tipo de cimentación','8', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('A232C4C1-5524-4302-9329-F69D050F9698','Tipos uso de edificios','3', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('539D1830-90A8-484C-B183-F79A448CEC78','Tipos de vertedores','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('B5A4C0E7-FF66-4988-A957-F8FC519E2895','Tipos de válvulas','5', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('54DA8427-56C1-4EC7-A258-FBA21E9303D2','Cimentación (presas)','6', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('4F1CFD2A-E24E-4AD5-B2C6-FC20367D0FFC','Tipo de corrientes','4', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('C5B23B4C-D9DF-4673-BD6A-FE2F5F209AEC','Tipo de canales','4', 1)");
        db.execSQL("INSERT INTO TBL_CatalogsNames(_id, CatalogName, MenuCategoryID, Visible) VALUES('07C3B108-6581-4DA7-85BC-FF6263924E99','Estado de conservación Plafones','7', 1)");
    }

    public void insertInto_TBL_FormerDamageAndRisk(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D2193E0A-EFAE-4D27-BC02-DEE69452615D','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D2193E0A-EFAE-4D27-BC02-DEE69452615D','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D2193E0A-EFAE-4D27-BC02-DEE69452615D','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D2193E0A-EFAE-4D27-BC02-DEE69452615D','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D2193E0A-EFAE-4D27-BC02-DEE69452615D','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D2193E0A-EFAE-4D27-BC02-DEE69452615D','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('573C3FF9-A125-44F1-8AE5-5E6A8E57E775','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('13E9DF4D-DDEA-43D4-BADD-22622231DD83','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('E966590E-3B42-4D95-83FC-E7CFEE6689D0','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0DCC020-89FB-4E69-8A29-E40618F6650E','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D3275386-1332-4D90-BC66-49708EEACBB2','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('B36D2BAB-DC6D-43A5-877F-13733281E790','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
    }

    public void insertInto_TBL_Properties(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','666-hist-002-vgr','','666-abc-hist-001','calle falsa no 13 BCS','0','0','0','0','Oct 20 2013  1:22PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','8C9A7255-9F0E-4B5D-9C52-0D71BFDE1E90','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','abcd-hist-001-xyz','','Historico VGR 1','Calle falsa No 13 Hist','0','0','0','0','Oct 19 2013  3:16PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','3BF8B8B7-72F3-40FE-90A4-0913CFBA68AC','88888', '77777')");

        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('580125D9-506A-4C6B-A0AD-0267C9F0CC6B','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','hist-009-vgr','','Historico VGR 5','calle falsa 44','0','0','0','0','Oct 13 2013 11:21PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','840EB65B-3075-48A1-9B20-02BCB1F35AE7','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('FC531EF3-B83E-4181-95CD-0304D50190AE','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','Centro acuicola 9','calle falsa 42','0','0','0','0','Oct 12 2013  4:17AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','38D1A287-02BF-47FD-B1A9-2F74D7044A49','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('4D477C11-6DE0-487E-9205-0F60846CB0D2','6E26183E-6799-4839-B293-77E9B3C863AA','CEB-1','Capacitacion','CEBETI 13','Iman 300','0','0','0','2','Sep 18 2013  1:19PM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','E10F50C3-DA96-446C-8344-0023B619B0CE','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('653331A8-1651-4C56-820C-18C5637709C9','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','hist-004-vgr','','Historico VGR','calle falsa 36','0','0','0','0','Oct 11 2013  2:14AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','D1815344-DF6A-4511-83F3-0339D0625E04','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('B976223B-6B89-4A62-BA84-2327E5585817','3D1555E2-058D-4409-96DB-5D4816E9CB06','','','Colector pluvial 2','calle falsa 33','0','0','0','0','Oct 10 2013  1:18AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','5CAB0628-1AA8-4E85-8B0A-03B3E8D73CB0','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('40C9DE91-04BE-49D3-89D0-24DBF6526EDD','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Hospital 1','Av. Principal 100','0','1','0','2','Sep 18 2013  1:08PM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','64F59280-5C05-4AF3-B7BD-5DAD2F8DA305','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('FB4B752A-E276-4E98-B130-2883834D4712','675D314E-5322-4499-853A-AB8BBADB6200','','','Camino 1','','0','0','0','0','Oct 10 2013  2:32AM','ANAKIN SKYWALKER, Admin','1', '99999','00000000-0000-0000-0000-000000000000','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('B181075F-4F7D-4719-828E-2E47A32AB012','44A78916-1C19-476E-BF88-57F2D0B19B68','','','hospital zona 3','calle falsa No. 24','1','0','0','0','Oct  8 2013  3:24AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','8ED1D041-5233-43A6-B41F-55E339D9AF2F','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('F8C30136-D8DB-41DB-9B07-33E8C692833E','6E26183E-6799-4839-B293-77E9B3C863AA','edu vgr 1','educativo','educacion 1','calle falsa 1 Pue','0','0','0','0','Oct 14 2013 11:05AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','1F2D6D52-46F7-408A-8DA9-008B48AD8BF7','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('AC74C70F-4069-4686-B148-341CFB410A65','44A78916-1C19-476E-BF88-57F2D0B19B68','','','hospital zona 1','calle falsa No. 22','0','0','0','0','Oct  8 2013  3:18AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','8ED1D041-5233-43A6-B41F-55E339D9AF2F','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('35551199-D0F7-4A31-B7FB-342BC4560DFC','6E26183E-6799-4839-B293-77E9B3C863AA','abcd-1234-xyz','escuela','Instituto Prueba 7','calle falsa No. 20','1','0','0','0','Oct  8 2013  1:54AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','A9B01510-AC59-4495-9244-0543F2169403','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('A8BFBAE2-BBD3-40FF-B9A3-34BC72838F17','6E26183E-6799-4839-B293-77E9B3C863AA','','educativo','Educación 2','calle falsa 13 edu','0','0','0','0','Oct 14 2013 11:17AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','C7B97610-F309-4C27-B71E-27F1DFE36BEF','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('AE535B1F-B5BE-4C4A-B2B6-35B432949F9C','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Clinica 1','25 pte 982','0','1','0','1','Sep 18 2013 10:56AM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','CC8BCACE-58FE-4A97-AD63-068E0DE7D2B4','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('3B52907F-80DC-4AE9-9AB5-3CA98C23FF91','6E26183E-6799-4839-B293-77E9B3C863AA','vgr-edu-666','educación','Instituto 777','calle falsa no 13 BCS','0','0','0','0','Oct 14 2013  2:55PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','1482DCEE-D0CE-430E-8054-AA15D7BECCAC','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('D03A84A3-0795-4FBC-9108-461D9558427D','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','hist-008-vgr','','Historico VGR 4','Calle falsa 41','0','0','0','0','Oct 12 2013  3:39AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','0F2DEC3E-4C15-4CC2-BFBB-0DDD260E4E0D','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('E6BC0263-85AE-47FA-8445-4DCDFCA55B34','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Clinica 2','Av. Principal 100','0','1','0','1','Sep 18 2013  1:04PM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','64F59280-5C05-4AF3-B7BD-5DAD2F8DA305','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('C8C1DE67-D1CE-4F14-9E53-51FCB442F144','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Clinica 3','San Fernando S/N','0','1','0','1','Sep 18 2013 11:56AM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','1B43B5F0-DABB-4A1F-903B-158C522F9FF3','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('FE6E3AA3-5C3D-468D-B321-56AE419B7AF2','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','Centro acuicola 10','calle falsa 43','0','0','0','0','Oct 12 2013  8:57PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','DF1CD991-3AD0-447C-898D-572E055B0DF1','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('89B77586-4810-4281-85BE-57BB5CF1A836','9638A1E6-9D30-4885-AC60-705CEB53BE82','','','urbana 10','','0','0','0','0','Oct 14 2013  1:55AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('3608C9BA-A3E0-4C13-8313-5D138A5CB24E','3D1555E2-058D-4409-96DB-5D4816E9CB06','','','hidraulica 2','calle falsa 37 NL','0','0','0','0','Oct 14 2013  4:13AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','16B00200-6A97-43BD-8F44-CDF38EA02E22','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('883F7A7A-E78F-4BCD-8EA5-5F1B05AA0AB4','6E26183E-6799-4839-B293-77E9B3C863AA','','','Instituto Prueba 5','calle falsa No. 15','0','0','0','0','Oct  7 2013  4:47PM','ANAKIN SKYWALKER, Admin','1', '99999','00000000-0000-0000-0000-000000000000','7FE960D0-2B3D-4348-979A-037E56FC76C5','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('1D6C7E37-E335-4EC0-92AD-633C267360AA','44A78916-1C19-476E-BF88-57F2D0B19B68','','','hospital zona 2','calle falsa No. 23','0','0','0','0','Oct  8 2013  3:22AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','0FE4D189-95FA-47B4-9E27-44D012850E8A','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('4E2A92A0-0314-439E-A825-684D31D25C2A','6E26183E-6799-4839-B293-77E9B3C863AA','','','Instituto Prueba 3','calle falsa No. 13','0','0','0','0','Oct  7 2013  3:57PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','878A0429-979B-4E1D-9FAD-25E095EB78F0','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('F13BD1B0-9659-43B5-80C2-7678ED57BD34','6E26183E-6799-4839-B293-77E9B3C863AA','abcd-1234-xyz','escuela','Instituto Prueba 7','calle falsa No. 20','0','0','0','0','Oct  8 2013  1:55AM','ANAKIN SKYWALKER, Admin','1', '99999','00000000-0000-0000-0000-000000000000','A9B01510-AC59-4495-9244-0543F2169403','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('878E40BA-7B9A-42F6-A466-777FF31D9A4C','6E26183E-6799-4839-B293-77E9B3C863AA','bt-1','Capacitacion','CEBETA 13','moras 800','0','1','0','2','Sep 18 2013 10:55AM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','E1F73F36-5F8D-4DB3-A298-987D14601486','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('C8C31B5A-AD83-4DCE-8E31-790F42CD0D66','9638A1E6-9D30-4885-AC60-705CEB53BE82','','','urbana 2','','0','0','0','0','Oct 10 2013  1:43AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','CE37F9C0-C152-435A-B09E-0177BD51225B','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('1919FDA1-1143-4F06-BBB7-7B5226AB359A','6E26183E-6799-4839-B293-77E9B3C863AA','','','Centro cultural 1','San Fernando S/N','0','1','0','1','Sep 18 2013 12:01PM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','1B43B5F0-DABB-4A1F-903B-158C522F9FF3','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('731FAAF3-24C2-4DB6-B307-7E0E8A0A844C','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','hist-007-vgr','','Historico VGR 3','calle falsa 39','0','0','0','0','Oct 11 2013  4:21PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','BABB6E77-EA6B-484D-B559-6568F23F493E','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('A918CCC5-472D-4466-BCED-822E4229C861','6E26183E-6799-4839-B293-77E9B3C863AA','ESP-D-1','Escuela','Escuela secundaria tenica no, 1','Eje 10 sur 1234','0','0','0','2','Sep 18 2013 10:39AM','SKYWALKER SITH, ANAKIN','1', '99999','00000000-0000-0000-0000-000000000000','B1EA408A-1944-4CFD-954A-4893DEAC35A3','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('4DC8B443-A6AA-496C-8FE1-89BEA4451973','6E26183E-6799-4839-B293-77E9B3C863AA','abcd-1234-xyz','escuela','Instituto Prueba 7','calle falsa No. 20','0','0','0','0','Oct  8 2013  1:56AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','A9B01510-AC59-4495-9244-0543F2169403','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('61270414-B42F-4ACF-BD3E-8CE335808108','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','hist-005-vgr','','Historico VGR 2','calle falsa 37','0','0','0','0','Oct 11 2013  2:22AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','2EF84E5E-38BE-4ED4-B086-0517255E26C7','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('0C8316A0-9C49-4045-9D26-8EA080EC331A','6E26183E-6799-4839-B293-77E9B3C863AA','','','Instituto Prueba 2','calle falsa No. 13','0','1','0','1','Oct  7 2013 12:34PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','9DE6E486-367D-4E54-9EC0-060D34B8D45B','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('9CB6D512-CA2A-44A5-99E4-939F786A312B','6E26183E-6799-4839-B293-77E9B3C863AA','mnop-7865-abcd','escuela','Instituto Prueba 8','calle falsa No. 21','0','0','0','0','Oct  8 2013  2:13AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','8CC76160-5D19-428C-BFB5-0508BE0B3E5B','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('1DDEF547-3ACE-4B8E-85FB-9456674A77F4','08A9201C-F6CC-44F0-B136-61370BC2EFDB','','','Disposición 1','','0','0','0','0','Oct 14 2013  5:38AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('446D2038-A8AC-4A49-9DCB-94EB9DA12291','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','Centro acuicola 1','calle falsa 29','0','0','0','0','Oct  8 2013  2:44PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','E551395D-8957-4457-9404-131A3E7C4DDC','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('6E299CED-EF09-4B41-B33B-99EC5C60ADC4','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','hist-003-vgr','','Monumento 3','calle falsa 35','0','0','0','0','Oct 11 2013 12:54AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','F4E8B602-B522-4C93-A15B-069CA1D3697E','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('734E7592-1E67-4186-8602-9CE6CCA90020','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Hispital 2','San Fernando S/N','0','0','0','2','Sep 18 2013 11:59AM','SKYWALKER SITH, ANAKIN','1', '99999','00000000-0000-0000-0000-000000000000','1B43B5F0-DABB-4A1F-903B-158C522F9FF3','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('6D351C28-2956-44AC-B992-A19B356130F5','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Laboratorio 1','Gustavo A. Madero 142','0','0','0','2','Sep 18 2013  1:18PM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','B1EA408A-1944-4CFD-954A-4893DEAC35A3','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('70AEC50F-DFEC-4F6D-9680-A47D85A4B7EA','9638A1E6-9D30-4885-AC60-705CEB53BE82','','','urbana 3','','0','0','0','0','Oct 10 2013  1:45AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','CE37F9C0-C152-435A-B09E-0177BD51225B','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('DBF3AAAF-B1B0-43A7-9291-A9EB91730ABE','9638A1E6-9D30-4885-AC60-705CEB53BE82','','','urbana 1','','0','0','0','0','Oct  8 2013  6:24AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','8C0E6EE2-8952-40BE-9207-089C77AED498','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('BC6DA516-1A00-46A1-877A-AC059DB0753E','6E26183E-6799-4839-B293-77E9B3C863AA','abcd-1234','escuela','Instituto Prueba 6','calle falsa No. 19','0','0','0','0','Oct  8 2013  1:49AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','957556E6-16A8-4409-8BBF-05C8B76659C6','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('617195F5-0812-4BD6-915A-C06A17FEE657','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','El Farolito','calle falsa 40','0','0','0','0','Oct 12 2013  2:55AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','15960677-F2CF-4EE3-8703-012E51029723','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('9170071C-8A2F-4F54-B288-C1580DC67547','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','Centro acuicola 5','Calle Falsta 666','0','0','0','0','Oct 12 2013  2:33AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','7D3CCB65-1FF2-4617-877C-02A719AB2037','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('70C261C1-EBC2-47E8-8EBB-C5D4AA0F0AC2','6E26183E-6799-4839-B293-77E9B3C863AA','','','Instituto Prueba 4','calle falsa No. 14','0','1','0','1','Oct  7 2013  4:14PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','4C58A83A-713B-46C1-9D16-03162EA26FFA','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('97FBF17D-8F6D-493F-B096-C93640BF415D','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','hist-006-vgr','','Historico VGR2','calle falsa 38','0','0','0','0','Oct 11 2013  3:53PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','6D727DE6-99C6-4CF7-AF02-00FDAA6F4935','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('342D5C64-675F-469D-8ED6-CE02F45B9D83','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','hist-001-vgr','','Historico 1','calle falsa 26','0','0','0','0','Oct  8 2013  4:57AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','B6649897-03FB-4DBE-86AC-01EDA2459E42','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('6ABFA944-3798-40C1-9606-D4538155B1D5','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','hist-002-vgr','','Monumento 2','calle falsa 34','0','0','0','0','Oct 10 2013  5:24AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','6B761F88-CC73-49AE-9D5E-016188323F9B','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('21FC90EE-EF5D-4FB9-A319-D8B1F9A70CE4','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','Centro acuicola 2','Calle Falsa 30','0','0','0','0','Oct  8 2013  2:41PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','E551395D-8957-4457-9404-131A3E7C4DDC','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('EB9AD67E-940D-4363-806E-DB5972ED119E','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Clinica 4','San Fernando S/N','0','1','0','1','Sep 18 2013 11:59AM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','1B43B5F0-DABB-4A1F-903B-158C522F9FF3','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('D9CDA030-3730-4179-9EF2-E2A8879F6C11','6E26183E-6799-4839-B293-77E9B3C863AA','edu-vgr-tst-1','Educacion','Instituto 666','calle falsa 31','0','0','0','0','Oct  9 2013  3:18AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','FA5EC3D6-AAE2-4792-B8BB-0448ADD99271','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('0AAE5484-87C7-4BE2-ADBE-E322F18AEB1A','3D1555E2-058D-4409-96DB-5D4816E9CB06','','','hidraulica 666','calle falsa 36 NL','0','0','0','0','Oct 14 2013  3:13AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','FBB0CC4E-078F-48AA-99D5-000D549A5726','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('E8E20A3E-D2B1-4DFD-86B9-E43D653C817A','6E26183E-6799-4839-B293-77E9B3C863AA','edu-vgr-tst-1','Educacion','Instituto 666','calle falsa 31','0','0','0','0','Oct  9 2013  3:17AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','FA5EC3D6-AAE2-4792-B8BB-0448ADD99271','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('AC41E446-1048-4831-9F57-E5354DFDF063','9638A1E6-9D30-4885-AC60-705CEB53BE82','','','urbana 9','','0','0','0','0','Oct 14 2013  1:15AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('F43F4765-37F0-4528-AB1F-EA388007EA95','08A9201C-F6CC-44F0-B136-61370BC2EFDB','','','Planta de tratamiento 1','calle falsa 27','0','0','0','0','Oct  8 2013 12:38PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','2B713615-B6EF-4BEC-A1D7-71FE247A46CE','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('AD674424-04C6-4530-800B-EB44B495D724','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','Centro acuicola 3','Calle Falsa 31','0','0','0','0','Oct  8 2013  2:42PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','E551395D-8957-4457-9404-131A3E7C4DDC','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('0449EB99-7346-46D5-B925-EED5BFE113DF','6E26183E-6799-4839-B293-77E9B3C863AA','abcd-1234-xyz','escuela','Instituto Prueba 7','calle falsa No. 20','0','0','0','0','Oct  8 2013  1:55AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','A9B01510-AC59-4495-9244-0543F2169403','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('BAEA146D-39C2-4274-AFD5-EF973F9606BB','08A9201C-F6CC-44F0-B136-61370BC2EFDB','','','Planta de tratamiento 2','calle falsa 28','0','0','0','0','Oct  8 2013 12:55PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','0F96AF11-2965-42CB-B49C-D0189801C186','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('9663A8D0-D39A-45FF-A761-F5010C2D4FC0','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','Centro acuicola 11','calle falsa 45','0','0','0','0','Oct 13 2013 11:29PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','D468DCEB-4A57-4816-9967-F20B83A1E854','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('CD17AA79-E239-45F9-9EF0-FEBFE4C7C1D5','3D1555E2-058D-4409-96DB-5D4816E9CB06','','','Colector pluvial 1','calle falsa 32','0','0','0','0','Oct  9 2013 12:12PM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','69255D04-38C5-4915-8D00-3A9E70E5DA80','88888', '77777')");

//	    db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('4D477C11-6DE0-487E-9205-0F60846CB0D2','6E26183E-6799-4839-B293-77E9B3C863AA','CEB-1','Capacitacion','CEBETI 13','Iman 300','0','0','0','2','Sep 18 2013  1:19PM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','E10F50C3-DA96-446C-8344-0023B619B0CE','88888', '77777')");
//	    db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('B976223B-6B89-4A62-BA84-2327E5585817','3D1555E2-058D-4409-96DB-5D4816E9CB06','','','Colector pluvial 2','calle falsa 33','0','0','0','0','Oct 10 2013  1:18AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','5CAB0628-1AA8-4E85-8B0A-03B3E8D73CB0','88888', '77777')");
//	    db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('40C9DE91-04BE-49D3-89D0-24DBF6526EDD','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Hospital 1','Av. Principal 100','0','1','0','2','Sep 18 2013  1:08PM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','64F59280-5C05-4AF3-B7BD-5DAD2F8DA305','88888', '77777')");
//	    db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('FB4B752A-E276-4E98-B130-2883834D4712','675D314E-5322-4499-853A-AB8BBADB6200','','','Camino 1','','0','0','0','0','Oct 10 2013  2:32AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','00000000-0000-0000-0000-000000000000','88888', '77777')");
//
//	    db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('4D477C11-6DE0-487E-9205-0F60846CB0D1','6E26183E-6799-4839-B293-77E9B3C863AA','CEB-1','Capacitacion','CEBETI 13','Iman 300','0','0','0','2','Sep 18 2013  1:19PM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','E10F50C3-DA96-446C-8344-0023B619B0CE','88888', '77777')");
//	    db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('B976223B-6B89-4A62-BA84-2327E5585816','3D1555E2-058D-4409-96DB-5D4816E9CB06','','','Colector pluvial 2','calle falsa 33','0','0','0','0','Oct 10 2013  1:18AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','5CAB0628-1AA8-4E85-8B0A-03B3E8D73CB0','88888', '77777')");
//	    db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('40C9DE91-04BE-49D3-89D0-24DBF6526EDA','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Hospital 1','Av. Principal 100','0','1','0','2','Sep 18 2013  1:08PM','SKYWALKER SITH, ANAKIN','0', '99999','00000000-0000-0000-0000-000000000000','64F59280-5C05-4AF3-B7BD-5DAD2F8DA305','88888', '77777')");
//	    db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('FB4B752A-E276-4E98-B130-2883834D4711','675D314E-5322-4499-853A-AB8BBADB6200','','','Camino 1','','0','0','0','0','Oct 10 2013  2:32AM','ANAKIN SKYWALKER, Admin','0', '99999','00000000-0000-0000-0000-000000000000','00000000-0000-0000-0000-000000000000','88888', '77777')");
    }

    public void insertInto_TBL_PropertyFeatures(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','F9A21F90-E696-47D0-9FB0-37AA39B7F24B','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','39301E28-AFF7-452E-8B0B-9F38489AB220','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','17900000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','9703AFF7-BFCD-4246-A074-FE317F846787','21000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','CBC48695-91B6-4959-83A1-A1C015784FFC','1500000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','171E7A87-8DD4-44F4-9091-17552ACB1389','1700000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','7D7A9721-2B8F-4DF5-8C09-37DC1833C6E8','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','181E092C-025C-42DE-AF91-440262209F60','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','D0A962B1-C77A-4777-A409-3D7B2095DFCE','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','6D8A20A4-11C9-4FA9-9421-78E5499B4EE3','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','576ECD79-210B-44CE-9797-51AC63884E6A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','3771EE8D-1F6F-428F-BA91-E6F4B12BB8A0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','4261EE86-3A7A-43A6-BE02-116A12C78499','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','492010A2-1935-4B0D-924D-847D19B67110','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','95B91081-AF09-4D5E-9541-2483D9AFC90D','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','66B1B7A6-DF11-489A-8DD7-74C000D78A26','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','71823EDB-3947-4024-BE13-0B8352A2B713','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','FFF576F7-3CD3-4355-ADBF-1CD7C2B3B3E0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','919A4798-DA01-4BC9-9396-583AC4598968','10.1234')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','20.8765')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0DCC020-89FB-4E69-8A29-E40618F6650E','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','300.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','A5F06F88-B3A8-4D55-AB0F-9CEDA99A68D4','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','A556770F-1EA6-4CF2-B876-2302B7DC543E','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','250000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','9703AFF7-BFCD-4246-A074-FE317F846787','310000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','CBC48695-91B6-4959-83A1-A1C015784FFC','185000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','171E7A87-8DD4-44F4-9091-17552ACB1389','210000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','7D7A9721-2B8F-4DF5-8C09-37DC1833C6E8','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','181E092C-025C-42DE-AF91-440262209F60','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','D0A962B1-C77A-4777-A409-3D7B2095DFCE','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','6D8A20A4-11C9-4FA9-9421-78E5499B4EE3','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','576ECD79-210B-44CE-9797-51AC63884E6A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','3771EE8D-1F6F-428F-BA91-E6F4B12BB8A0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','4261EE86-3A7A-43A6-BE02-116A12C78499','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','492010A2-1935-4B0D-924D-847D19B67110','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','95B91081-AF09-4D5E-9541-2483D9AFC90D','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','66B1B7A6-DF11-489A-8DD7-74C000D78A26','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','71823EDB-3947-4024-BE13-0B8352A2B713','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','FFF576F7-3CD3-4355-ADBF-1CD7C2B3B3E0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','919A4798-DA01-4BC9-9396-583AC4598968','70.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','80.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B36D2BAB-DC6D-43A5-877F-13733281E790','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','90.0')");
    }

    public void ioInsertaPostalCodes(SQLiteDatabase db){
        InputStream is = null;
        try {
            is = contexto.getAssets().open("importTBL_Propierties.sql");
            if (is != null) {
                db.beginTransaction();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "Cp1252"));
                String line = reader.readLine();
                while (!TextUtils.isEmpty(line)) {
                    db.execSQL(line);
                    line = reader.readLine();
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            // Muestra log
        } finally {
            db.endTransaction();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // Muestra log
                }
            }
        }

    }

    public void insertInto_TBL_States(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('00000000-0000-0000-0000-000000000000','Generico','0', '0','GEN')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('E4886740-2AA3-4DF7-8AD5-016E0A02E110','Aguascalientes ','0', '0','AGU')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('24DDF93E-A50A-4296-B248-0F9910098A33','Baja California ','0', '0','BCN')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('DAB02A9A-8A91-463F-BA49-129A0E6E1389','Baja California Sur ','0', '0','BCS')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('18B65E0C-7C41-480A-8D19-130624114EF7','Campeche ','0', '0','CAM')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('ECB19424-C020-4381-B7F7-1A892CAD84F0','Chiapas ','0', '0','COA')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('C2CA920B-6C45-41D2-9FB9-1B9A44B46411','Chihuahua ','0', '0','COL')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('BA97FAB2-D490-45B3-A92D-2300541BC757','Coahuila','0', '0','CHP')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('93FF0681-8D45-470A-8BBC-2C46250CB550','Colima ','0', '0','CHH')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('DC09E0A9-1040-4080-BB58-33061D1C1699','Durango ','0', '0','DUR')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('069942C1-2FCC-4ABA-956C-3A2EE69C6C68','Guanajuato ','0', '0','GUA')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','Guerrero ','0', '0','GRO')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('BE7DCBE2-5B33-4ADF-9521-434B378E72AC','Hidalgo ','0', '0','HID')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('EE4282A4-33DB-426B-AFA7-4EB32074384A','Jalisco ','0', '0','JAL')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('C76D4B02-BB8E-4479-A2E0-50473E4B0775','México ','0', '0','MEX')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('44AD696B-CAE1-4251-A975-52EE8ACF6399','Michoacán','0', '0','MIC')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('6D431485-6C7F-417A-80C8-5678B9AF5ACF','Morelos ','0', '0','MOR')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('63BAFD4F-6234-4A6F-9FAF-5B959069353D','Nayarit ','0', '0','NAY')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('27FC32A7-DC28-455C-AD54-6BB56DA86E1F','Nuevo León ','0', '0','NLE')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('3A94A057-9BAF-4633-9AD4-72F60065C46A','Oaxaca ','0', '0','OAX')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('E58E0C92-3AEB-4378-B962-786BD484459C','Puebla ','0', '0','PUE')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('245341C6-5DBE-4227-BF11-9172906C1E24','Querétaro ','0', '0','QUE')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','Quintana Roo ','0', '0','ROO')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('494C0A72-F88B-4E66-AEC5-94A63D228153','San Luis Potosí ','0', '0','SLP')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('DE9C700C-0B99-4683-8E02-977A142120C8','Sinaloa ','0', '0','SIN')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('808920BA-B0C3-4B8F-ABEC-9883688BAD36','Sonora ','0', '0','SON')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('C8E5BBC2-8142-4A50-9520-996F0942A383','Tabasco ','0', '0','TAB')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','Tamaulipas ','0', '0','TAM')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('2D09E941-9416-4A8C-9AA9-B2C8581FBCAF','Tlaxcala ','0', '0','TLA')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('08839679-93F8-43A0-B0C8-C3AA75147EB6','Veracruz','0', '0','VER')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','Yucatán','0', '0','YUC')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('F83A9BA0-6D0C-4C1B-AAE7-EDB04056ABF3','Zacatecas ','0', '0','ZAC')");
        db.execSQL("INSERT INTO TBL_States(_id, StateName, DELETED, LOCKED, StateCode) Values ('BD6B4922-321D-4A57-9ED2-FA9A74B41CE1','Distrito Federal ','0', '0','DIF')");
    }

    public void insertInto_TBLS_Aqu1(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','Pba móvil Acuicola','Calle No. ext. No. Int.','0','0','0','0','2014-01-15','JUAN CARLOS GARCÍA, jgarcia','0', '99999','00000000-0000-0000-0000-000000000000','93CE1BD4-36D1-46C4-92B7-08C4E27F28A6','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','9CE95B9F-CAAA-49BE-9019-16C624CF2053','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','0A725F1C-F530-4B18-A2BE-B05BB70F9E18','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','2E084FD5-681C-4A14-915B-8227CED05C7A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','C8E0BE4A-1918-47CE-A381-C25CD0C16B83','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','9B674B1C-3E69-4249-AEC0-9EDD05C2628E','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','5C0B7B63-E2DE-48C6-9086-680BAF833781','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','1137AE2A-8473-4BF1-98D0-1D1500A0212C','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','3A6F52AB-B1C2-43AB-98AC-2CD6914830B0','Instalaciones especiales')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','045DE8F3-FB18-46F0-B783-1771D662E85C','Tipo edificio')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','1000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','9703AFF7-BFCD-4246-A074-FE317F846787','1000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','E264210A-287A-47CC-B40F-416324BC02ED','Rehabilitación de estanques')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','97635CAF-308A-43FF-BB56-34B54B168927','2000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','BEF0166B-EE4B-442E-B23F-37109D13DC63','2000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','E80C6CC0-C386-4655-80A0-CC2F1D4D3AF0','Cancelería')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','AD89F22C-AA79-4C94-A263-956CF9CEA51F','3000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','C42FE244-8F70-4EF0-959C-9034C14E4A93','3000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','F1C41B62-4F08-4050-A6BD-04EACC827E15','Malla ciclónica')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','4E90F1ED-9FEF-4973-8604-39425C6C4049','4000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','E07B24E8-33AF-4C60-8326-1F7B3B419FB6','4000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','D9D28390-F7A7-4794-8909-D39281E89C03','Pintura')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','DCF27175-EE31-45C5-B91E-E0BF7C10AEEA','5000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','04CBA926-49D4-4ACD-A1CE-EE8962505356','5000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','729063FB-EA8D-4346-A5DF-D0BC1242ECB4','Techumbres')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','2E8CE8FC-DEBD-40BA-9F5A-DD834CCBCEF0','6000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','96F4E9E5-E5E4-44E1-A75D-9913175F052E','6000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','D50DAED1-18C1-49B3-BD2D-C0105E3A58E2','Red alimentación')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','04BFB10E-8005-475B-9564-92B9B90E2C87','7000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','99B256E2-2A6A-4177-93D1-41649F379220','7000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','046A83CE-043B-41AD-A7D5-148BDD788E1D','Red conducción de agua')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','AB2A8871-7430-4499-9D56-62BBEABACA32','8000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','6287ECE5-8BEA-493C-A860-3DB770460C1B','8000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','3E81DF69-5B7D-47A2-803B-2EDC1F3C7E6F','Red electromecánica')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','D0EEDDA4-5B01-4E64-A244-E3BD489C5EE8','9000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','26C4E75F-821A-446F-B3F6-58A32B5B884A','9000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','DF2A44B6-93E4-484C-B3A2-3E731EBB0366','Red sanitaria')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','6D7E8620-C6E1-4184-B426-9E4BD0916AAA','10000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','98B72092-0088-4AC3-AABC-4C18B811B315','10000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','DDE06835-B4C8-4883-B484-A4F83A42370C','Instalación sanitaria')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','B001F51B-0787-4A2B-9BF5-A60FF751DAF7','11000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','E3E9B5D8-66AF-49A5-8576-0B9F97A118B2','11000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','08D5E6FA-D3BE-4E4A-8B0F-D022FCD1BC3C','Instalación sanitaria')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','0E39AF62-2CBD-4879-9105-AAEF8C455442','12000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','E80F4100-A293-420B-B8E0-87CCFE8B30A6','12000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','919A4798-DA01-4BC9-9396-583AC4598968','19.316989')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.305702')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('43D70461-D9D7-47A2-9874-957E25A155D1','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2014', '700000000', 'Desc 2014')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2013', '700000000', 'Desc 2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2012', '700000000', 'Desc 2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2011', '700000000', 'Desc 2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2010', '700000000', 'Desc 2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2009', '700000000', 'Desc 2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2008', '700000000', 'Desc 2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2007', '700000000', 'Desc 2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2006', '700000000', 'Desc 2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('43D70461-D9D7-47A2-9874-957E25A155D1','2005', '700000000', 'Desc 2005')");
    }

    public void insertInto_TBLS_Aqu(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2C1D1D7D-1407-42AB-A22C-D73159051F4A','','','Acuicola pba JC','Calle No. ext. No. int.','0','0','0','0','2013-12-12  3:21PM','Admin Admin, Admin','0', '99999','00000000-0000-0000-0000-000000000000','93CE1BD4-36D1-46C4-92B7-08C4E27F28A6','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','9CE95B9F-CAAA-49BE-9019-16C624CF2053','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','0A725F1C-F530-4B18-A2BE-B05BB70F9E18','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2E084FD5-681C-4A14-915B-8227CED05C7A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','C8E0BE4A-1918-47CE-A381-C25CD0C16B83','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','9B674B1C-3E69-4249-AEC0-9EDD05C2628E','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','5C0B7B63-E2DE-48C6-9086-680BAF833781','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','1137AE2A-8473-4BF1-98D0-1D1500A0212C','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','3A6F52AB-B1C2-43AB-98AC-2CD6914830B0','Inst. especiales')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','045DE8F3-FB18-46F0-B783-1771D662E85C','Edificio tipo')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','100000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','9703AFF7-BFCD-4246-A074-FE317F846787','200000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','E264210A-287A-47CC-B40F-416324BC02ED','Rehab. estanques')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','97635CAF-308A-43FF-BB56-34B54B168927','100000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','BEF0166B-EE4B-442E-B23F-37109D13DC63','200000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','E80C6CC0-C386-4655-80A0-CC2F1D4D3AF0','Canceleria')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','AD89F22C-AA79-4C94-A263-956CF9CEA51F','100000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','C42FE244-8F70-4EF0-959C-9034C14E4A93','200000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','F1C41B62-4F08-4050-A6BD-04EACC827E15','Malla ciclon')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','4E90F1ED-9FEF-4973-8604-39425C6C4049','100000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','E07B24E8-33AF-4C60-8326-1F7B3B419FB6','200000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','D9D28390-F7A7-4794-8909-D39281E89C03','Pintura')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','DCF27175-EE31-45C5-B91E-E0BF7C10AEEA','100000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','04CBA926-49D4-4ACD-A1CE-EE8962505356','200000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','729063FB-EA8D-4346-A5DF-D0BC1242ECB4','Techumbres')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2E8CE8FC-DEBD-40BA-9F5A-DD834CCBCEF0','100000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','96F4E9E5-E5E4-44E1-A75D-9913175F052E','200000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','D50DAED1-18C1-49B3-BD2D-C0105E3A58E2','Alimentación')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','04BFB10E-8005-475B-9564-92B9B90E2C87','300000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','99B256E2-2A6A-4177-93D1-41649F379220','400000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','046A83CE-043B-41AD-A7D5-148BDD788E1D','Conducción de agua')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','AB2A8871-7430-4499-9D56-62BBEABACA32','300000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','6287ECE5-8BEA-493C-A860-3DB770460C1B','400000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','3E81DF69-5B7D-47A2-803B-2EDC1F3C7E6F','Electromecánica')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','D0EEDDA4-5B01-4E64-A244-E3BD489C5EE8','300000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','26C4E75F-821A-446F-B3F6-58A32B5B884A','400000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','DF2A44B6-93E4-484C-B3A2-3E731EBB0366','Sanitaria')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','6D7E8620-C6E1-4184-B426-9E4BD0916AAA','500000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','98B72092-0088-4AC3-AABC-4C18B811B315','600000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','DDE06835-B4C8-4883-B484-A4F83A42370C','Inst. sanitaria')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','B001F51B-0787-4A2B-9BF5-A60FF751DAF7','500000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','E3E9B5D8-66AF-49A5-8576-0B9F97A118B2','600000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','08D5E6FA-D3BE-4E4A-8B0F-D022FCD1BC3C','Inst. sanitaria')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','0E39AF62-2CBD-4879-9105-AAEF8C455442','500000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','E80F4100-A293-420B-B8E0-87CCFE8B30A6','600000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','919A4798-DA01-4BC9-9396-583AC4598968','19.545743')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.235066')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','30')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2013', '700000000', '2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2012', '700000000', '2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2011', '700000000', '2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2010', '700000000', '2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2009', '700000000', '2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2008', '700000000', '2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2007', '700000000', '2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2006', '700000000', '2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2005', '700000000', '2005')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('9DA4B39A-83D8-4952-A802-60F5CA6D8D47','2004', '700000000', '2004')");
    }

    public void insertInto_TBLS_Dam1(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','115B140C-2596-4F69-95F7-8EFAC742051F','IDpresa150114','','Prueba móvil Presa','Calle No. ext. No. Int.','0','0','0','0','2014-01-17  3:35PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','00000000-0000-0000-0000-000000000000','15902FC3-7688-4E2E-926E-7D01A4533451','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','812CE4EF-783F-4E2B-9218-BD3D3E30D3EA','1000000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','686A7908-26BF-45BA-81D4-9270D0ED43F4','321351')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','1E5D6FE7-1516-47F5-BC97-A5BA4D31C163','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','28C54E78-15B8-48F4-B1F7-9261A75F2325','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','5D96A958-63F4-497C-9C5B-EC973DEBE3C6','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','CABF2D63-ADEF-4226-BF66-6D7AD8B47DF1','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','12F2C6E5-6026-48D3-B45C-024A396BB119','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','DE39610E-E351-4AB4-9420-E92A70FDE789','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','C06F5911-6A89-41EA-8FD2-7762DEECD539','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','A102999C-E84E-4A90-B910-E52A499BBDD6','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','AE30C03C-005C-4F7C-85EF-E5439B932982','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','C4B66D66-D0BC-48EF-B3AB-C8BFAEAAF28F','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','C4B66D66-D0BC-48EF-B3AB-C8BFAEAAF28F','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','C08E5B7F-0114-41FD-9A90-DCADE741C8E8','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','29458E01-80B8-4982-AE9F-409DE1FC5DD7','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','B9FFADBA-530F-4EED-8811-867B7B7DD4C2','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','8CACBFC2-60BA-4542-B5BD-63B2A9E1FCFF','2')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','87EB66D2-EA84-4358-AFD4-E14962854AB7','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','6811BAF0-2EF0-407E-946F-895C4BB258C9','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','E6F46501-32F1-469A-A5D1-A456BCB6C9BD','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','FA3307DE-ECE8-455B-B436-098DE5CCBACA','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','0CF4F5E6-33D7-4A16-8F38-8BEB69FF8C13','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','42E4A3AD-6EAF-400D-91AD-845B218336CF','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2281FC61-C336-4C63-82FE-DB11E8ED752F','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','98CFD274-71A3-4EDA-B5B1-8202A13D44E4','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','897BC614-403F-47EB-8EF2-854351F431BB','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','F3AF4BE3-1C0F-4DAE-AC10-0B92D827624D','Presota')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','3F7D1163-E3C0-4C20-9B45-38BCDFD99F26','CNA')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','645C5B0C-8427-4750-A76A-BACF8B44B6CC','HIDROLÓGICA')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','62E62F2E-20C9-4955-8F08-56C2335AC131','Cuenca')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','EB31C186-1719-4CE7-B66C-640CFCC5E593','Corriente')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','5652F097-CA3C-4349-8283-7A88309AFE93','Afluente')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','7FF87226-25E6-47DB-8BE8-D58081F851FD','654.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','4AA6D428-CE01-4F88-B779-71017EAE5196','Diseñador')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','D4BABE2A-F30A-4307-8430-70A122FACE70','Constructor')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','FCBB3903-A20C-498C-B7F8-EE978F6FF769','Org. Responsable')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','47D8EDA0-D54F-4ADA-AC95-7701B7A7DE75','987.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','226E9B9F-4023-4576-B060-48A52BE444D7','987.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','6291860C-380D-40C8-ADC5-61F1EFDCC1DD','987.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','919A4798-DA01-4BC9-9396-583AC4598968','19.403611')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.178908')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','34069595-7CA2-4341-B1D1-632507BD06ED','10.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','30F63AA1-7E28-4991-9050-07A2462C5F85','20.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','A319CE2C-0EFE-49C9-BF9A-814922C7CC12','30.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','6549873216549876546514')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','4B00463B-CD5F-4591-9BE4-B87ECDFA5135','789.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','EA0C637F-F7E2-4F47-BB03-EC719BA7475D','456.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','0E3DAA86-89DF-40FC-BC4D-A85CE7C2621E','1423.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','8F05D1C7-2144-48D7-B31E-AC0E46D897B7','123.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','7FF87226-25E6-47DB-8BE8-D58081F851FD','654.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','830E087E-07FA-48FD-A96D-38FD71642A48','984.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','56BCFEBE-1C66-4F21-993F-5E7B411C0252','65.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','6750A007-0F9A-47C2-B744-3E2B8BB44DBE','651.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','425DB9F7-93CD-4310-9C31-99D7B6FDCD5D','651.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','FF4AC697-B9D0-48DA-95D2-5B4A3E80892B','351.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','5FC905BE-52E9-4360-A403-59EAC5DD1750','351.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','5026E187-04A6-4F0F-A268-52991956BCDA','351.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','5C03CF75-7DF8-407B-81CC-973D4E8A4968','351.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','E960A7BF-A572-4D63-9405-F5920E785210','351.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','B0C6DD01-FF6A-49D8-92D6-EBC6FB6EF0CA','351.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','C0939295-E05F-43EE-A96B-2A94755A20ED','351.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','BE2FD0F8-68CD-4586-9055-0B66C33A1D2C','546841.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','D99AB80F-6E10-488E-8F1E-E6B09BBFC9C9','INSTRUCCIONES DE OPERACIÓN')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','8B541FEF-B43B-48C0-99A5-2297F6121470','COMENTARIOS')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','8EC64169-4982-4D59-AB22-078E1D868C9F','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','379A69FE-806F-43A7-A4C2-E72DBEA27B20','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','8B6B976C-13DD-462F-9359-FAFE07940FC5','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','AD5FE7F0-2A07-4F24-82ED-BB164668E432','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','3F5770ED-26FF-40B0-85CF-0956BF285147','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','20A5A886-B52B-456F-8199-6C9F3D63C092','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','8087E024-AC00-4B31-A678-0AD0E73061F3','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','51C8A233-0790-4DBE-9540-0BB3AA511FF3','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','56BA60CA-CD09-40B0-BDC0-36C6C02DBC4D','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','3AA6E5A2-A138-4BD6-9132-D4793FACE572','Especificar')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','3788CF80-8F9C-406F-BFE2-013857CC6AD6','987.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','21CAACCE-0DAF-4132-A283-26E51FBFDCAF','654.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','44790418-99C3-4B66-B456-B91F622E9E48','321.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','18434229-1950-431B-BA13-4EA38F5D96BD','25651.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','0B59181F-C16B-473B-92B6-5600D7ED3172','321.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','E32672F5-5B56-43E7-AE23-9256F054C5DD','654.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','037702C4-72AB-49D8-B124-433CE827A512','INEGI')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','6E48CA3E-D61E-4E9B-B9A5-45A7B178364C','654.0 ')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','FFE859EF-E837-44FE-98C0-5DEDA615A8D2','321.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','CFBDEE20-FEA6-4AF5-8DED-874A9F1B3A92',',presa 1,presa 2,4,3')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2014', '700000000', 'd')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2013', '700000000', 'd')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2012', '700000000', 'd')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2011', '700000000', 'd')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2010', '700000000', 'd')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2009', '700000000', 'd')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2008', '700000000', 'd')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2007', '700000000', 'd')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2006', '700000000', 'd')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','2005', '700000000', 'd')");

    }

    public void insertInto_TBLS_Dam(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','115B140C-2596-4F69-95F7-8EFAC742051F','IDPRESA','','Presa pba JC','','0','0','0','0','2013-12-12  7:20PM','Admin Admin, Admin','0', '99999','00000000-0000-0000-0000-000000000000','187358E2-AF7D-43C5-8750-035A23AF162A','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','DDEB7463-2B76-4B49-9630-76B8EF104F56','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','06E3CBD9-A517-4E51-B019-65621E025CEC','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','4089A68B-73B1-4C3A-96D2-F4D671F7E16D','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','4332572D-8FD6-4C25-934C-74A3165B3C26','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','12F2C6E5-6026-48D3-B45C-024A396BB119','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','DE39610E-E351-4AB4-9420-E92A70FDE789','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','C06F5911-6A89-41EA-8FD2-7762DEECD539','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','A102999C-E84E-4A90-B910-E52A499BBDD6','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','AE30C03C-005C-4F7C-85EF-E5439B932982','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','C4B66D66-D0BC-48EF-B3AB-C8BFAEAAF28F','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','C4B66D66-D0BC-48EF-B3AB-C8BFAEAAF28F','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','F3AF4BE3-1C0F-4DAE-AC10-0B92D827624D','Nombre común')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','3F7D1163-E3C0-4C20-9B45-38BCDFD99F26','CNA')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','645C5B0C-8427-4750-A76A-BACF8B44B6CC','R. Hidrologica')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','62E62F2E-20C9-4955-8F08-56C2335AC131','Cuenca')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','EB31C186-1719-4CE7-B66C-640CFCC5E593','Corriente')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','5652F097-CA3C-4349-8283-7A88309AFE93','Afluente')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','7FF87226-25E6-47DB-8BE8-D58081F851FD','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','4AA6D428-CE01-4F88-B779-71017EAE5196','Diseñador')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','D4BABE2A-F30A-4307-8430-70A122FACE70','Constructor')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','FCBB3903-A20C-498C-B7F8-EE978F6FF769','Org. Responsable')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','47D8EDA0-D54F-4ADA-AC95-7701B7A7DE75','10')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','226E9B9F-4023-4576-B060-48A52BE444D7','20')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','6291860C-380D-40C8-ADC5-61F1EFDCC1DD','30')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','919A4798-DA01-4BC9-9396-583AC4598968','19.442659')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.297296')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','30')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','34069595-7CA2-4341-B1D1-632507BD06ED','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','30F63AA1-7E28-4991-9050-07A2462C5F85','200')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','A319CE2C-0EFE-49C9-BF9A-814922C7CC12','300')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','1000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','4B00463B-CD5F-4591-9BE4-B87ECDFA5135','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','EA0C637F-F7E2-4F47-BB03-EC719BA7475D','200')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','0E3DAA86-89DF-40FC-BC4D-A85CE7C2621E','300')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','8F05D1C7-2144-48D7-B31E-AC0E46D897B7','400')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','7FF87226-25E6-47DB-8BE8-D58081F851FD','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','830E087E-07FA-48FD-A96D-38FD71642A48','600')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','56BCFEBE-1C66-4F21-993F-5E7B411C0252','700')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','6750A007-0F9A-47C2-B744-3E2B8BB44DBE','800')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','425DB9F7-93CD-4310-9C31-99D7B6FDCD5D','900')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','FF4AC697-B9D0-48DA-95D2-5B4A3E80892B','1000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','5FC905BE-52E9-4360-A403-59EAC5DD1750','1100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','5026E187-04A6-4F0F-A268-52991956BCDA','1200')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','5C03CF75-7DF8-407B-81CC-973D4E8A4968','1300')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','E960A7BF-A572-4D63-9405-F5920E785210','1400')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','B0C6DD01-FF6A-49D8-92D6-EBC6FB6EF0CA','1500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','C0939295-E05F-43EE-A96B-2A94755A20ED','1600')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','BE2FD0F8-68CD-4586-9055-0B66C33A1D2C','1700')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','D99AB80F-6E10-488E-8F1E-E6B09BBFC9C9','Intrucciones especiales de ope')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','8B541FEF-B43B-48C0-99A5-2297F6121470','Comentarios')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','8EC64169-4982-4D59-AB22-078E1D868C9F','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','379A69FE-806F-43A7-A4C2-E72DBEA27B20','200')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','8B6B976C-13DD-462F-9359-FAFE07940FC5','300')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','AD5FE7F0-2A07-4F24-82ED-BB164668E432','400')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','3F5770ED-26FF-40B0-85CF-0956BF285147','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','20A5A886-B52B-456F-8199-6C9F3D63C092','600')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','8087E024-AC00-4B31-A678-0AD0E73061F3','700')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','51C8A233-0790-4DBE-9540-0BB3AA511FF3','800')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('848F5A89-EA10-4CF6-9B01-F107C120CA2C','56BA60CA-CD09-40B0-BDC0-36C6C02DBC4D','900')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2013', '700000000', '2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2012', '700000000', '2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2011', '700000000', '2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2010', '700000000', '2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2009', '700000000', '2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2008', '700000000', '2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2007', '700000000', '2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2006', '700000000', '2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2005', '700000000', '2005')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('848F5A89-EA10-4CF6-9B01-F107C120CA2C','2004', '700000000', '2004')");
    }

    public void insertInto_TBLS_Dam_Vertederos(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','E74C8ECF-3DFE-4CFA-A837-2B01DC233FB9','','','Vertedero 1','','0','0','0','0','Ene 15 2014  7:38PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','C0AC1E55-DB48-48F9-ABA7-BFBBF2620676','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','DC92108D-C042-4FC6-8CE0-8C507B30E320','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','504BAE09-9F93-46A5-83CA-790CB72996F2','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','80BF9015-3EEF-49DC-93C8-916B34E4F867','53513')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','D87BA7E4-A70C-403A-A7D2-3A724B92FE6E','351351')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','B7B3B16D-D32D-46F9-8DF6-7BDDB521A539','5135')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','1558192C-0BA7-4FAC-889B-A81FB92C2B05','6513')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','690DC6C3-C31C-437C-98A1-A6A07C2BA7A2','135')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','A8D155C4-C73E-480D-AD9D-111275D2AFF1','35135')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','DAB45D5C-EB5A-4738-8B3F-56225F539085','351')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','DD5964D9-510F-486B-8B3F-58B173C0D094','3513')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','71FE3A18-2FE8-4200-A9A2-6B0540CF5E60','51')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','05646369-9E42-4E07-B64F-E3E80B303858','351')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','73AC510F-7DE9-415A-A490-11610B624406','351')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','A7B4259A-7448-4F78-B4BA-414D7A0639C3','351416')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','81A874BC-7EB4-4ED0-985C-2B35865C1C8A','8168')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('B40B4E4C-8F5F-452D-BA5D-15043626BCF3','47296174-C9EB-49F7-8D6E-2BA751D1ABF9','16')");
    }

    public void insertInto_TBLS_Edu1(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','6E26183E-6799-4839-B293-77E9B3C863AA','123456789','Uso general','Prueba móvil Educación','Calle No. ext. No. Int.','0','0','0','0','2014-01-17  3:33PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','00000000-0000-0000-0000-000000000000','93CE1BD4-36D1-46C4-92B7-08C4E27F28A6','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','C06F5911-6A89-41EA-8FD2-7762DEECD539','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','38750791-01FB-413F-BB6A-C9AB7CEC36C5','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','78C3C3F1-575E-4927-8D49-DE686946E3D6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','97375940-E1E4-45DE-A269-301EAED38029','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','A26A5A87-7149-435C-B7B7-E008663D3C9A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','23806607-AF02-4E82-B28D-D011783F0EB3','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','F6DD4CC8-80B0-4995-B60E-9E55ED8A640B','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','3E113983-2942-435A-B471-83D67866C650','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','0C1403CF-5E97-4EFA-AB4A-687276B83A54','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','79516D64-A265-4CE6-9348-CA10D9652EE6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','B546FF4C-8DC1-47B0-B648-5F53B0998BEF','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','D4C94600-E425-4712-BA34-BA2C5C21A905','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','C30E3F8D-CBD3-42B0-966B-F2DF8BC04C3B','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','DAEA5CE9-FFC2-4F92-A94F-F92CB92EF07E','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','417E779A-78C5-45A6-A238-DDCBD482FEA9','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','AC855C6E-6189-41CB-BDB2-FB22A6B5DD85','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','4C729D1F-0CFB-4483-93E4-59D563B1DE13','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','03FCF704-BCFF-460B-B8B0-DD7C4537376F','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','E82265D7-CAEB-4C37-8EE0-B5889F3A685F','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','BDF64B6B-482B-4011-9A68-A4E647632281','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','51C2B7D8-9AC4-4CF4-AC5E-5D2D0EEAD57F','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','97D1E7C0-38BE-4BBD-80D2-2D211DFD6216','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','3479F77A-1A10-4BE2-BFA3-4CEF9CFD7E42','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','1507EDE4-43A0-446B-B301-D2101DD49DD2','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','48E751A1-3FA8-44E3-B59B-7D673C1E7192','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','975D1E82-3F39-419D-8116-94A8EACA4A95','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','B4D8440A-39B9-4A44-BB27-8BC129EA7FDA','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','8F684117-6E6E-4C78-83AF-9147C0AFFE55','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','0C76AD41-D3BE-481C-A82A-8E947F5134B0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','8A95F972-8730-4C42-9210-CB78E5FF3836','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','37E088BE-8C24-4F43-BACC-4E3C9A3C918E','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','6811BAF0-2EF0-407E-946F-895C4BB258C9','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','E6F46501-32F1-469A-A5D1-A456BCB6C9BD','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','B9FFADBA-530F-4EED-8811-867B7B7DD4C2','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','8CACBFC2-60BA-4542-B5BD-63B2A9E1FCFF','2')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','87EB66D2-EA84-4358-AFD4-E14962854AB7','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','919A4798-DA01-4BC9-9396-583AC4598968','19.321456')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.174427')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','3A6F52AB-B1C2-43AB-98AC-2CD6914830B0','Instalaciones especiales')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','9703AFF7-BFCD-4246-A074-FE317F846787','200')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','CBC48695-91B6-4959-83A1-A1C015784FFC','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','171E7A87-8DD4-44F4-9091-17552ACB1389','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','7D7A9721-2B8F-4DF5-8C09-37DC1833C6E8','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','181E092C-025C-42DE-AF91-440262209F60','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','D0A962B1-C77A-4777-A409-3D7B2095DFCE','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','6D8A20A4-11C9-4FA9-9421-78E5499B4EE3','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','576ECD79-210B-44CE-9797-51AC63884E6A','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','3771EE8D-1F6F-428F-BA91-E6F4B12BB8A0','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','4261EE86-3A7A-43A6-BE02-116A12C78499','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','492010A2-1935-4B0D-924D-847D19B67110','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','95B91081-AF09-4D5E-9541-2483D9AFC90D','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','66B1B7A6-DF11-489A-8DD7-74C000D78A26','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','71823EDB-3947-4024-BE13-0B8352A2B713','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','FFF576F7-3CD3-4355-ADBF-1CD7C2B3B3E0','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','2')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','F28B4182-39C5-40D0-96FE-554D61C654B5','Aluminio colado')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('07229B21-4C6D-4CE5-9728-6AB01751674D','B5C155EB-5B5B-4509-AD46-A0F797CA327F','Rejas de herrería')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2014', '700000000', '2014')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2013', '700000000', '2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2012', '700000000', '2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2011', '700000000', '2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2010', '700000000', '2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2009', '700000000', '2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2008', '700000000', '2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2007', '700000000', '2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2006', '700000000', '2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('07229B21-4C6D-4CE5-9728-6AB01751674D','2005', '700000000', '2005')");

    }

    public void insertInto_TBLS_Edu(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','6E26183E-6799-4839-B293-77E9B3C863AA','12345657890','Uso general','Educación pba JC','Calle No. ext. No. Int.','0','0','0','0','2013-12-12  7:06PM','Admin Admin, Admin','0', '99999','00000000-0000-0000-0000-000000000000','6625A2E9-2407-430E-A62A-1293896294BF','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','C06F5911-6A89-41EA-8FD2-7762DEECD539','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','38750791-01FB-413F-BB6A-C9AB7CEC36C5','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','78C3C3F1-575E-4927-8D49-DE686946E3D6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','97375940-E1E4-45DE-A269-301EAED38029','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','A26A5A87-7149-435C-B7B7-E008663D3C9A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','23806607-AF02-4E82-B28D-D011783F0EB3','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','F6DD4CC8-80B0-4995-B60E-9E55ED8A640B','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','3E113983-2942-435A-B471-83D67866C650','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','0C1403CF-5E97-4EFA-AB4A-687276B83A54','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','79516D64-A265-4CE6-9348-CA10D9652EE6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','B546FF4C-8DC1-47B0-B648-5F53B0998BEF','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','D4C94600-E425-4712-BA34-BA2C5C21A905','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','D6AD4FE9-55C1-4C54-A0D1-5D311E3D8D93','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','DAEA5CE9-FFC2-4F92-A94F-F92CB92EF07E','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','417E779A-78C5-45A6-A238-DDCBD482FEA9','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','AC855C6E-6189-41CB-BDB2-FB22A6B5DD85','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','204266D3-3109-432D-AE4F-D679CC4EE903','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','7EBF93C4-F1BB-481C-B82F-D48A91794D12','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','224BD51D-2007-4452-A26F-112EAC580DC5','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','41C333F3-1A40-471D-B997-121D0B472E37','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','8EABC000-3397-4875-93A4-AC535B367ED1','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','79C547A6-9529-418B-B0BD-C214A8C33B14','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','6D01807F-124C-481D-AF36-E9ED617DB05D','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','60752DDF-C488-4757-9A6B-65F2903B06DA','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','48E751A1-3FA8-44E3-B59B-7D673C1E7192','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','975D1E82-3F39-419D-8116-94A8EACA4A95','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','B4D8440A-39B9-4A44-BB27-8BC129EA7FDA','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','8F684117-6E6E-4C78-83AF-9147C0AFFE55','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','0C76AD41-D3BE-481C-A82A-8E947F5134B0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','8A95F972-8730-4C42-9210-CB78E5FF3836','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','37E088BE-8C24-4F43-BACC-4E3C9A3C918E','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','6811BAF0-2EF0-407E-946F-895C4BB258C9','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','E6F46501-32F1-469A-A5D1-A456BCB6C9BD','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','30')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','919A4798-DA01-4BC9-9396-583AC4598968','19.582025')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.226693')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','3A6F52AB-B1C2-43AB-98AC-2CD6914830B0','Instalaciones especiales')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','100000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','9703AFF7-BFCD-4246-A074-FE317F846787','100000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','CBC48695-91B6-4959-83A1-A1C015784FFC','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','171E7A87-8DD4-44F4-9091-17552ACB1389','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','7D7A9721-2B8F-4DF5-8C09-37DC1833C6E8','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','181E092C-025C-42DE-AF91-440262209F60','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','D0A962B1-C77A-4777-A409-3D7B2095DFCE','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','6D8A20A4-11C9-4FA9-9421-78E5499B4EE3','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','576ECD79-210B-44CE-9797-51AC63884E6A','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','3771EE8D-1F6F-428F-BA91-E6F4B12BB8A0','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','4261EE86-3A7A-43A6-BE02-116A12C78499','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','492010A2-1935-4B0D-924D-847D19B67110','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','95B91081-AF09-4D5E-9541-2483D9AFC90D','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','66B1B7A6-DF11-489A-8DD7-74C000D78A26','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','71823EDB-3947-4024-BE13-0B8352A2B713','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','FFF576F7-3CD3-4355-ADBF-1CD7C2B3B3E0','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','2')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','F28B4182-39C5-40D0-96FE-554D61C654B5','Ventanas protec')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('A30D96F8-0356-4E78-82BA-419262F5A02C','B5C155EB-5B5B-4509-AD46-A0F797CA327F','Domos protec')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2013','100','2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2012','100','2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2011','100','2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2010','100','2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2009','100','2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2008','100','2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2007','100','2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2006','100','2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2005','100','2005')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('A30D96F8-0356-4E78-82BA-419262F5A02C','2004','100','2004')");
    }

    public void insertInto_TBLS_His1(SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','BHYA 150114','','Prueba móvil BHYA','Calle No. ext. No. Int.','0','0','0','0','2014-01-15 12:57PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','00000000-0000-0000-0000-000000000000','D276F82A-60BB-48BB-BB23-29418E7E8584','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','A5F06F88-B3A8-4D55-AB0F-9CEDA99A68D4','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','39301E28-AFF7-452E-8B0B-9F38489AB220','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','1000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','9703AFF7-BFCD-4246-A074-FE317F846787','1000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','CBC48695-91B6-4959-83A1-A1C015784FFC','2000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','171E7A87-8DD4-44F4-9091-17552ACB1389','2000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','7D7A9721-2B8F-4DF5-8C09-37DC1833C6E8','3000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','181E092C-025C-42DE-AF91-440262209F60','3000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','D0A962B1-C77A-4777-A409-3D7B2095DFCE','4000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','6D8A20A4-11C9-4FA9-9421-78E5499B4EE3','4000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','576ECD79-210B-44CE-9797-51AC63884E6A','5000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','3771EE8D-1F6F-428F-BA91-E6F4B12BB8A0','5000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','4261EE86-3A7A-43A6-BE02-116A12C78499','6000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','492010A2-1935-4B0D-924D-847D19B67110','6000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','95B91081-AF09-4D5E-9541-2483D9AFC90D','7000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','66B1B7A6-DF11-489A-8DD7-74C000D78A26','7000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','71823EDB-3947-4024-BE13-0B8352A2B713','8000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','FFF576F7-3CD3-4355-ADBF-1CD7C2B3B3E0','8000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','919A4798-DA01-4BC9-9396-583AC4598968','19.791448')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.098135')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('703DD2AF-8289-4269-A192-DB0C669A7849','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2014', '700000000', 'Desc 2014')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2013', '700000000', 'Desc 2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2012', '700000000', 'Desc 2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2011', '700000000', 'Desc 2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2010', '700000000', 'Desc 2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2009', '700000000', 'Desc 2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2008', '700000000', 'Desc 2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2007', '700000000', 'Desc 2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2006', '700000000', 'Desc 2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('703DD2AF-8289-4269-A192-DB0C669A7849','2005', '700000000', 'Desc 2005')");

    }

    public void insertInto_TBLS_His(SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','C0F7CDCB-1D0A-40F0-955D-5C347B81F550','12345678abc','','BHYA pba JC','Calle No. ext. No. int.','0','0','0','0','2013-12-21  3:27AM','Admin Admin, Admin','0', '99999','00000000-0000-0000-0000-000000000000','15902FC3-7688-4E2E-926E-7D01A4533451','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','A5F06F88-B3A8-4D55-AB0F-9CEDA99A68D4','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','39301E28-AFF7-452E-8B0B-9F38489AB220','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','2000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','9703AFF7-BFCD-4246-A074-FE317F846787','3000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','CBC48695-91B6-4959-83A1-A1C015784FFC','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','171E7A87-8DD4-44F4-9091-17552ACB1389','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','7D7A9721-2B8F-4DF5-8C09-37DC1833C6E8','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','181E092C-025C-42DE-AF91-440262209F60','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','D0A962B1-C77A-4777-A409-3D7B2095DFCE','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','6D8A20A4-11C9-4FA9-9421-78E5499B4EE3','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','576ECD79-210B-44CE-9797-51AC63884E6A','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','3771EE8D-1F6F-428F-BA91-E6F4B12BB8A0','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','4261EE86-3A7A-43A6-BE02-116A12C78499','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','492010A2-1935-4B0D-924D-847D19B67110','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','95B91081-AF09-4D5E-9541-2483D9AFC90D','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','66B1B7A6-DF11-489A-8DD7-74C000D78A26','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','71823EDB-3947-4024-BE13-0B8352A2B713','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','FFF576F7-3CD3-4355-ADBF-1CD7C2B3B3E0','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','919A4798-DA01-4BC9-9396-583AC4598968','19.401395')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.162687')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('79DFFACD-3CCA-4889-A502-F7951E1CF88C','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','30.0')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2013', '700000000', '2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2012', '700000000', '2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2011', '700000000', '2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2010', '700000000', '2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2009', '700000000', '2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2008', '700000000', '2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2007', '700000000', '2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2006', '700000000', '2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2005', '700000000', '2005')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('79DFFACD-3CCA-4889-A502-F7951E1CF88C','2004', '700000000', '2004')");
    }

    public void insertInto_TBLS_Hea1(SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Prueba móvil Salud','Calle No. ext. No. Int.','0','0','0','0','2014-01-15  7:58PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','00000000-0000-0000-0000-000000000000','6625A2E9-2407-430E-A62A-1293896294BF','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','C06F5911-6A89-41EA-8FD2-7762DEECD539','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','78C3C3F1-575E-4927-8D49-DE686946E3D6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','97375940-E1E4-45DE-A269-301EAED38029','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','F6DD4CC8-80B0-4995-B60E-9E55ED8A640B','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','3E113983-2942-435A-B471-83D67866C650','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','87EB66D2-EA84-4358-AFD4-E14962854AB7','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','0C1403CF-5E97-4EFA-AB4A-687276B83A54','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','975D1E82-3F39-419D-8116-94A8EACA4A95','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','B546FF4C-8DC1-47B0-B648-5F53B0998BEF','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','D4C94600-E425-4712-BA34-BA2C5C21A905','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','478BFA53-F1FC-4843-8E9E-66C52A882F63','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','C08E5B7F-0114-41FD-9A90-DCADE741C8E8','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','8A95F972-8730-4C42-9210-CB78E5FF3836','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','37E088BE-8C24-4F43-BACC-4E3C9A3C918E','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','6811BAF0-2EF0-407E-946F-895C4BB258C9','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','E6F46501-32F1-469A-A5D1-A456BCB6C9BD','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','B4D8440A-39B9-4A44-BB27-8BC129EA7FDA','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','8F684117-6E6E-4C78-83AF-9147C0AFFE55','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','0C76AD41-D3BE-481C-A82A-8E947F5134B0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','B9FFADBA-530F-4EED-8811-867B7B7DD4C2','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','8CACBFC2-60BA-4542-B5BD-63B2A9E1FCFF','2')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','F28B4182-39C5-40D0-96FE-554D61C654B5','6549')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','B5C155EB-5B5B-4509-AD46-A0F797CA327F','654')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','2')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','919A4798-DA01-4BC9-9396-583AC4598968','19.791448')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.098135')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','919A4798-DA01-4BC9-9396-583AC4598968','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','1000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','427D821A-1386-4C68-8DB6-4046DDC7A239','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','BAE65756-D9B9-4373-B505-CB0E1632E377','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','CBC48695-91B6-4959-83A1-A1C015784FFC','987')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','71823EDB-3947-4024-BE13-0B8352A2B713','987')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2014', '700000000', 'e')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2013', '700000000', 'e')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2012', '700000000', 'e')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2011', '700000000', 'e')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2010', '700000000', 'e')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2009', '700000000', 'e')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2008', '700000000', 'e')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2007', '700000000', 'e')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2006', '700000000', 'e')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('AF7A656D-97D2-4EA8-AED5-BD06C0E4DC35','2005', '700000000', 'e')");

    }

    public void insertInto_TBLS_Hea(SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','44A78916-1C19-476E-BF88-57F2D0B19B68','','','Salud pba JC','Calle No. ext. No. Int.','0','0','0','0','2013-12-21  3:36AM','Admin Admin, Admin','0', '99999','00000000-0000-0000-0000-000000000000','15902FC3-7688-4E2E-926E-7D01A4533451','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','C06F5911-6A89-41EA-8FD2-7762DEECD539','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','78C3C3F1-575E-4927-8D49-DE686946E3D6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','97375940-E1E4-45DE-A269-301EAED38029','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','F6DD4CC8-80B0-4995-B60E-9E55ED8A640B','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','3E113983-2942-435A-B471-83D67866C650','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','0C1403CF-5E97-4EFA-AB4A-687276B83A54','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','975D1E82-3F39-419D-8116-94A8EACA4A95','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','B546FF4C-8DC1-47B0-B648-5F53B0998BEF','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','D4C94600-E425-4712-BA34-BA2C5C21A905','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','478BFA53-F1FC-4843-8E9E-66C52A882F63','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','C08E5B7F-0114-41FD-9A90-DCADE741C8E8','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','8A95F972-8730-4C42-9210-CB78E5FF3836','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','37E088BE-8C24-4F43-BACC-4E3C9A3C918E','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','6811BAF0-2EF0-407E-946F-895C4BB258C9','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','E6F46501-32F1-469A-A5D1-A456BCB6C9BD','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','B4D8440A-39B9-4A44-BB27-8BC129EA7FDA','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','8F684117-6E6E-4C78-83AF-9147C0AFFE55','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','0C76AD41-D3BE-481C-A82A-8E947F5134B0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','F28B4182-39C5-40D0-96FE-554D61C654B5','Ventanas protec')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','B5C155EB-5B5B-4509-AD46-A0F797CA327F','Domos protec')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','5F9C3FF6-8A60-47BC-8FB2-CF58738B08BB','2')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','919A4798-DA01-4BC9-9396-583AC4598968','19.378847')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.151983')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','30.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','919A4798-DA01-4BC9-9396-583AC4598968','19.378847')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','E0905F8B-A77C-4F36-9E5E-FAE05E254CD9','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','427D821A-1386-4C68-8DB6-4046DDC7A239','200')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','BAE65756-D9B9-4373-B505-CB0E1632E377','300')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','CBC48695-91B6-4959-83A1-A1C015784FFC','400')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('D4E7D503-00B7-4425-8267-93F669A0B86C','71823EDB-3947-4024-BE13-0B8352A2B713','500')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2013', '700000000', '2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2012', '700000000', '2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2011', '700000000', '2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2010', '700000000', '2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2009', '700000000', '2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2008', '700000000', '2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2007', '700000000', '2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2006', '700000000', '2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2005', '700000000', '2005')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('D4E7D503-00B7-4425-8267-93F669A0B86C','2004', '700000000', '2004')");
    }

    public void insertInto_TBLS_Hyd1(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','3D1555E2-058D-4409-96DB-5D4816E9CB06','','','Prueba móvil Inf. Hidraúlica','Calle No. ext. No. Int.','0','0','0','0','2014-01-15  7:15PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','00000000-0000-0000-0000-000000000000','7964FA26-62AF-4009-88C3-32C85A98B76F','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','39301E28-AFF7-452E-8B0B-9F38489AB220','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','C06F5911-6A89-41EA-8FD2-7762DEECD539','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','78C3C3F1-575E-4927-8D49-DE686946E3D6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','97375940-E1E4-45DE-A269-301EAED38029','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','478BFA53-F1FC-4843-8E9E-66C52A882F63','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','1CB48385-7879-4E03-B0AE-0B52F8DC7F1F','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','4D8486AB-1F7B-4400-8DDD-B6068E2471AE','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','4D8486AB-1F7B-4400-8DDD-B6068E2471AE','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','8A8EA3AC-5D57-425E-A121-628A1EFBE552','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','D9C3F321-A3CE-4784-8E63-9AD53B3EF6F5','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','15B81355-B8D8-4AA5-8141-030F824F9AA3','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','D9C05E00-C1C3-4C22-94C7-522EBD9EB08A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','57E87AC8-BACE-431B-8409-CC8E3DA7F368','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','80C32CBD-155F-45D3-9994-32EF4B2BE2DC','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','F0AAC454-6FE2-453F-8CE1-5B4B8DA82E93','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','B0D85803-E7BA-44F0-BDC5-211458B63132','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','504BAE09-9F93-46A5-83CA-790CB72996F2','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','B475F358-782B-49DF-BCBE-62F4646FDB1C','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','593BAEAB-940C-48DD-96AF-852A4C5F8454','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','733E4658-52B3-4E64-B526-D5C9EA0ECA9B','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','79383E89-BC48-4454-9655-56F1F251B066','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','C19EC910-1500-47AC-921F-3DF631DBB667','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','280EB7FD-72B4-4C87-AAED-547DD5B70EBF','1000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','919A4798-DA01-4BC9-9396-583AC4598968','19.210860')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-100.138399')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','F092724D-DA1E-44AA-AAA0-55E562E05221','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','2794E46F-4FC7-4291-9A98-292AD27DEF16','200')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','CE4D2C2C-B3A9-475D-B0D7-A265027AB968','300')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','3B6A4AE4-8E8B-4600-A881-752C394BFC2C','400')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','D144CAAB-10BB-4EC9-A88E-B00C25E0096F','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','1905CF7B-C8D3-4F79-B58F-1BEBE7597B4D','600')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','ED76BA38-EAA1-4DED-9C65-27EC963AD10F','700')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','87029EB0-680B-4057-8ED2-F886E74C5947','800')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','666FA81A-D0B0-4C10-939D-88449A0E5481','900')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','B4E5D36A-1D9B-443E-8D1D-013167C66E0E','1100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','88EC99E7-FB7C-44E5-A91A-F8E4CC3EF71B','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','9703AFF7-BFCD-4246-A074-FE317F846787','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','4083D9B1-C947-4971-9584-EC8B371295CE','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','95566830-EFF4-4048-9175-F6ACF22C3ABA','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','57552A4E-9AB7-4AB7-AA2F-E21712653957','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('960A003D-84A4-4304-94BE-1079ABF4F1CB','F4606983-47BB-49C5-96F7-B58AF90DB8F2','100')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2014', '700000000', 'D')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2013', '700000000', 'E')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2012', '700000000', 'F')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2011', '700000000', 'F')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2010', '700000000', 'W')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2009', '700000000', 'E')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2008', '700000000', 'Q')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2007', '700000000', 'Ñ')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2006', '700000000', 'R')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('960A003D-84A4-4304-94BE-1079ABF4F1CB','2005', '700000000', 'V')");

    }

    public void insertInto_TBLS_Hyd(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','3D1555E2-058D-4409-96DB-5D4816E9CB06','','','Hidraúlica pba JC','Calle No. ext. No. Int.','0','0','0','0','2013-12-12  7:11PM','Admin Admin, Admin','0', '99999','00000000-0000-0000-0000-000000000000','EB4BF3FC-E7E8-4FED-A94C-C12D8C231280','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','39301E28-AFF7-452E-8B0B-9F38489AB220','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','C06F5911-6A89-41EA-8FD2-7762DEECD539','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','78C3C3F1-575E-4927-8D49-DE686946E3D6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','97375940-E1E4-45DE-A269-301EAED38029','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','478BFA53-F1FC-4843-8E9E-66C52A882F63','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','1CB48385-7879-4E03-B0AE-0B52F8DC7F1F','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','4D8486AB-1F7B-4400-8DDD-B6068E2471AE','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','4D8486AB-1F7B-4400-8DDD-B6068E2471AE','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','8A8EA3AC-5D57-425E-A121-628A1EFBE552','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','D9C3F321-A3CE-4784-8E63-9AD53B3EF6F5','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','15B81355-B8D8-4AA5-8141-030F824F9AA3','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','D9C05E00-C1C3-4C22-94C7-522EBD9EB08A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','57E87AC8-BACE-431B-8409-CC8E3DA7F368','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','80C32CBD-155F-45D3-9994-32EF4B2BE2DC','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','F0AAC454-6FE2-453F-8CE1-5B4B8DA82E93','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','B0D85803-E7BA-44F0-BDC5-211458B63132','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','504BAE09-9F93-46A5-83CA-790CB72996F2','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','B475F358-782B-49DF-BCBE-62F4646FDB1C','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','593BAEAB-940C-48DD-96AF-852A4C5F8454','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','733E4658-52B3-4E64-B526-D5C9EA0ECA9B','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','79383E89-BC48-4454-9655-56F1F251B066','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','C19EC910-1500-47AC-921F-3DF631DBB667','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','280EB7FD-72B4-4C87-AAED-547DD5B70EBF','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','919A4798-DA01-4BC9-9396-583AC4598968','18.148014')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-94.428292')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','30')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','F092724D-DA1E-44AA-AAA0-55E562E05221','10')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','2794E46F-4FC7-4291-9A98-292AD27DEF16','20')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','CE4D2C2C-B3A9-475D-B0D7-A265027AB968','30')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','3B6A4AE4-8E8B-4600-A881-752C394BFC2C','40')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','D144CAAB-10BB-4EC9-A88E-B00C25E0096F','50')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','1905CF7B-C8D3-4F79-B58F-1BEBE7597B4D','60')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','ED76BA38-EAA1-4DED-9C65-27EC963AD10F','70')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','87029EB0-680B-4057-8ED2-F886E74C5947','80')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','666FA81A-D0B0-4C10-939D-88449A0E5481','90')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','B4E5D36A-1D9B-443E-8D1D-013167C66E0E','110')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','88EC99E7-FB7C-44E5-A91A-F8E4CC3EF71B','5000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','9703AFF7-BFCD-4246-A074-FE317F846787','5000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','4083D9B1-C947-4971-9584-EC8B371295CE','6000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','95566830-EFF4-4048-9175-F6ACF22C3ABA','6000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','57552A4E-9AB7-4AB7-AA2F-E21712653957','7000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('18F3E271-4C7F-4231-A9AC-900379844BAF','F4606983-47BB-49C5-96F7-B58AF90DB8F2','7000000')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2013', '700000000', '2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2012', '700000000', '2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2011', '700000000', '2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2010', '700000000', '2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2009', '700000000', '2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2008', '700000000', '2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2007', '700000000', '2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2006', '700000000', '2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2005', '700000000', '2005')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('18F3E271-4C7F-4231-A9AC-900379844BAF','2004', '700000000', '2004')");
    }

    public void insertInto_TBLS_Urb1(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','9638A1E6-9D30-4885-AC60-705CEB53BE82','','','Prueba móvil Urbana','Calle No. ext. No. Int.','0','0','0','0','2014-01-15  8:00PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','00000000-0000-0000-0000-000000000000','4FE046E6-2DFA-4A43-BED1-7CE36CE142DB','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','C06F5911-6A89-41EA-8FD2-7762DEECD539','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','78C3C3F1-575E-4927-8D49-DE686946E3D6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','97375940-E1E4-45DE-A269-301EAED38029','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','6419CE4B-D535-4BCE-AF9F-E0DBB3AC3F3B','3')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','AD0D2809-30C8-4E53-9595-8F7894062CC0','null')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','919A4798-DA01-4BC9-9396-583AC4598968','20.217107')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','919A4798-DA01-4BC9-9396-583AC4598968','29.523923')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-87.451351')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-98.393998')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','56E685AE-B2AE-44F6-A5BD-AF56A0CBBC2F','10')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','97D7A91E-3544-4C1E-B3B0-D785EE8FF350','20')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','F4B048C4-0A44-4E54-9E01-DE9E2C2F1DC9','30')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','CAE59183-13D5-45CF-9825-AE2695E1731A','40')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','66F2BFB7-D569-4ED0-8AEB-C30DE3D3E7CA','50')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2014', '700000000', '2')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2013', '700000000', '4')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2012', '700000000', '6')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2011', '700000000', '8')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2010', '700000000', '10')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2009', '700000000', '12')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2008', '700000000', '14')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2007', '700000000', '16')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2006', '700000000', '18')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('13C8F68B-4374-4B5D-A99F-5AEA5BF65539','2005', '700000000', '20')");

    }

    public void insertInto_TBLS_Urb(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','9638A1E6-9D30-4885-AC60-705CEB53BE82','','','Urbana pba JC','camino alguno','0','0','0','0','2013-12-21  3:38AM','Admin Admin, Admin','0', '99999','00000000-0000-0000-0000-000000000000','FF7218DF-2799-4664-BB48-00752BEE27D3','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','C06F5911-6A89-41EA-8FD2-7762DEECD539','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','78C3C3F1-575E-4927-8D49-DE686946E3D6','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','97375940-E1E4-45DE-A269-301EAED38029','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','6419CE4B-D535-4BCE-AF9F-E0DBB3AC3F3B','4')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','AD0D2809-30C8-4E53-9595-8F7894062CC0','null')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','919A4798-DA01-4BC9-9396-583AC4598968','25.721212')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','919A4798-DA01-4BC9-9396-583AC4598968','19.434058')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-100.341531')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.086651')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','50.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','60.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','56E685AE-B2AE-44F6-A5BD-AF56A0CBBC2F','100')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','97D7A91E-3544-4C1E-B3B0-D785EE8FF350','200')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','F4B048C4-0A44-4E54-9E01-DE9E2C2F1DC9','300')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','CAE59183-13D5-45CF-9825-AE2695E1731A','400')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','66F2BFB7-D569-4ED0-8AEB-C30DE3D3E7CA','500')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2013', '700000000', '2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2012', '700000000', '2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2011', '700000000', '2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2010', '700000000', '2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2009', '700000000', '2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2008', '700000000', '2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2007', '700000000', '2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2006', '700000000', '2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2005', '700000000', '2005')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('40FF648B-2A32-4FB3-AB0B-CD0E754A50A1','2004', '700000000', '2004')");
    }

    public void insertInto_TBLS_Was1(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','08A9201C-F6CC-44F0-B136-61370BC2EFDB','','','Prueba móvil DR','Calle No. ext. No. Int.','0','0','0','0','2014-01-15  6:55PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','00000000-0000-0000-0000-000000000000','15902FC3-7688-4E2E-926E-7D01A4533451','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','1558CE49-AE55-4505-9D9D-59D70FAA0653','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','0A725F1C-F530-4B18-A2BE-B05BB70F9E18','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','2E084FD5-681C-4A14-915B-8227CED05C7A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','C8E0BE4A-1918-47CE-A381-C25CD0C16B83','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','1E171CDE-E651-4F48-BA34-C2AB9F5E7AD8','5000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','919A4798-DA01-4BC9-9396-583AC4598968','19.355323')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','919A4798-DA01-4BC9-9396-583AC4598968','19.353703')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.162237')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.176184')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','D5AAE3FE-5779-4D9C-8856-BDB9201A463E','5000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','C084285A-B1D3-4190-8D7A-44996075C029','5000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','74A5FF1A-2F4C-44B4-AB61-7880F462CA92','5000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','947DD835-305C-40F1-A93F-34B294F743E1','500000.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','632E5F23-427B-4AC3-85B9-F612B4064EBD','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','BC589497-44DC-48BD-8FBF-92E30B3EC8AD','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','8EBEB4B7-4BC9-491A-B387-20B31F4E6672','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','1CAB8099-D748-4215-B70B-84031036B944','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','1BA1A59F-CB38-41D1-83C1-EEB4D105EF1E','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','7567E762-5B28-4833-A61A-0418AB8B7CAE','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','04C4A44E-B1C9-4FAD-AC58-26B97004BCD1','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','BA1A74D2-F64C-4F5D-92A4-2463A43B096F','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','CC956656-46F5-4ED4-A044-AB05F0E6F1F9','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','8BA335C7-6BBF-46A1-8DB5-8BDCDB001FD1','1')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('42D7D84E-E3AA-45F0-B5BC-6EAD207DB186','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");

    }

    public void insertInto_TBLS_Was(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','08A9201C-F6CC-44F0-B136-61370BC2EFDB','','','Disp. Residuos pba JC','calle falsa 190','0','0','0','0','2013-12-21  3:29AM','Admin Admin, Admin','0', '99999','00000000-0000-0000-0000-000000000000','32587D93-72A4-4C08-B5D3-02E99E114AF0','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','1558CE49-AE55-4505-9D9D-59D70FAA0653','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','0A725F1C-F530-4B18-A2BE-B05BB70F9E18','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','2E084FD5-681C-4A14-915B-8227CED05C7A','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','C8E0BE4A-1918-47CE-A381-C25CD0C16B83','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','1E171CDE-E651-4F48-BA34-C2AB9F5E7AD8','12000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','919A4798-DA01-4BC9-9396-583AC4598968','19.296166')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','919A4798-DA01-4BC9-9396-583AC4598968','20.538795')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.097802')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.895325')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','10.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','115D7CEF-07C0-4427-BCFB-F62A351D9A3B','100.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','D5AAE3FE-5779-4D9C-8856-BDB9201A463E','2000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','C084285A-B1D3-4190-8D7A-44996075C029','3000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','74A5FF1A-2F4C-44B4-AB61-7880F462CA92','3000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','947DD835-305C-40F1-A93F-34B294F743E1','1.2E8')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','632E5F23-427B-4AC3-85B9-F612B4064EBD','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','BC589497-44DC-48BD-8FBF-92E30B3EC8AD','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','8EBEB4B7-4BC9-491A-B387-20B31F4E6672','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','1CAB8099-D748-4215-B70B-84031036B944','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','1BA1A59F-CB38-41D1-83C1-EEB4D105EF1E','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','7567E762-5B28-4833-A61A-0418AB8B7CAE','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','04C4A44E-B1C9-4FAD-AC58-26B97004BCD1','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','BA1A74D2-F64C-4F5D-92A4-2463A43B096F','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','CC956656-46F5-4ED4-A044-AB05F0E6F1F9','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('16CBABBB-CAA0-49E9-8A09-383C20053042','8BA335C7-6BBF-46A1-8DB5-8BDCDB001FD1','1')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('16CBABBB-CAA0-49E9-8A09-383C20053042','R','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('16CBABBB-CAA0-49E9-8A09-383C20053042','R','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('16CBABBB-CAA0-49E9-8A09-383C20053042','R','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('16CBABBB-CAA0-49E9-8A09-383C20053042','R','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('16CBABBB-CAA0-49E9-8A09-383C20053042','R','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
    }

    public void insertInto_TBLS_Way1_Tunnel(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('41CF04B9-A069-472A-A66E-3A87C70A7BF8','BC7003E5-34F6-40A9-965A-27A4B4AB1B51','','','Túnel 1','','0','0','0','0','Ene 15 2014  6:43PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','45664E3C-70A0-476F-AACC-9C730FFFFC58','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('94BD989B-09D3-4C52-B874-C68D2CC9B545','BC7003E5-34F6-40A9-965A-27A4B4AB1B51','','','Túnel 2','','0','0','0','0','Ene 15 2014  6:44PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','45664E3C-70A0-476F-AACC-9C730FFFFC58','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('41CF04B9-A069-472A-A66E-3A87C70A7BF8','E0825274-57D0-43C4-9209-F52C7F2D7287','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('41CF04B9-A069-472A-A66E-3A87C70A7BF8','5760E0C7-7612-49DA-86CE-B852E04477BD','300')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('41CF04B9-A069-472A-A66E-3A87C70A7BF8','3210C802-6BED-4DE0-B6F5-66064949EA22','1000000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('94BD989B-09D3-4C52-B874-C68D2CC9B545','E88B42EE-81B7-4986-8A9F-7E8CBFE720EC','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('94BD989B-09D3-4C52-B874-C68D2CC9B545','5760E0C7-7612-49DA-86CE-B852E04477BD','2121')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('94BD989B-09D3-4C52-B874-C68D2CC9B545','3210C802-6BED-4DE0-B6F5-66064949EA22','5000000000')");
    }

    public void insertInto_TBLS_Way1_Puente(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('4E45AF8E-ABD7-4D3C-81C9-0FCCF03EE70F','4FFF7B5D-90C2-4B92-848D-1BEF72520E1E','','','Puente 3','','0','0','0','0','Ene 15 2014  6:45PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','45664E3C-70A0-476F-AACC-9C730FFFFC58','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('5AE7714F-C369-4590-8294-534E1C2FFE70','4FFF7B5D-90C2-4B92-848D-1BEF72520E1E','','','Puente 1','','0','0','0','0','Ene 15 2014  6:44PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','45664E3C-70A0-476F-AACC-9C730FFFFC58','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('4E45AF8E-ABD7-4D3C-81C9-0FCCF03EE70F','D26C6275-8F48-4F07-8CCF-488B90570ACE','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('4E45AF8E-ABD7-4D3C-81C9-0FCCF03EE70F','0D0F0F70-88D5-42EB-BFC7-02A067C5B4FD','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('4E45AF8E-ABD7-4D3C-81C9-0FCCF03EE70F','5760E0C7-7612-49DA-86CE-B852E04477BD','10')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('4E45AF8E-ABD7-4D3C-81C9-0FCCF03EE70F','3210C802-6BED-4DE0-B6F5-66064949EA22','10000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('5AE7714F-C369-4590-8294-534E1C2FFE70','CB3F18B5-6FB1-46FC-807E-D5E29DFFDDF0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('5AE7714F-C369-4590-8294-534E1C2FFE70','0D0F0F70-88D5-42EB-BFC7-02A067C5B4FD','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('5AE7714F-C369-4590-8294-534E1C2FFE70','5760E0C7-7612-49DA-86CE-B852E04477BD','5154')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('5AE7714F-C369-4590-8294-534E1C2FFE70','3210C802-6BED-4DE0-B6F5-66064949EA22','51540000000')");
    }

    public void insertInto_TBLS_Way1(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','675D314E-5322-4499-853A-AB8BBADB6200','','','Prueba móvil CPYT','','0','0','0','0','2014-01-15  8:04PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','00000000-0000-0000-0000-000000000000','70F74E47-6CE1-4D9F-9497-00103161B9CB','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','39298EC3-EA9B-4D99-931A-D2A58DCFFD86','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','E4886740-2AA3-4DF7-8AD5-016E0A02E110','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','39301E28-AFF7-452E-8B0B-9F38489AB220','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','2EE66436-7EB6-463D-A64F-7EFA95791E72','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','2EAF0764-13E0-47E1-9A2F-2FCEDCAEBE10','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','919A4798-DA01-4BC9-9396-583AC4598968','19.527729')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','919A4798-DA01-4BC9-9396-583AC4598968','19.395849')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.259882')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.153546')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','5760E0C7-7612-49DA-86CE-B852E04477BD','100.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','EDD00D9C-4FAD-4847-BE66-6B35252F704B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','EDD00D9C-4FAD-4847-BE66-6B35252F704B','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','84CFF176-BB1E-4B18-9053-718888F9DB4A','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','84CFF176-BB1E-4B18-9053-718888F9DB4A','0.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','664B3CC3-031D-4CB6-8C66-0E9AF34465F0','10.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','4FBB6137-9322-4C6E-8AAB-E5471DF59F20','20.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','6419CE4B-D535-4BCE-AF9F-E0DBB3AC3F3B','9')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','A278335A-5C15-4087-8D5F-BF9DED3C7ACB','5000.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','B1C48FCD-BC79-47C3-951F-04689E2C63D3','9.0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','F092724D-DA1E-44AA-AAA0-55E562E05221','2000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','3210C802-6BED-4DE0-B6F5-66064949EA22','10000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('45664E3C-70A0-476F-AACC-9C730FFFFC58','BFE031DC-88BF-422B-94EE-8A26A4729F45','1')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','D','FA74EBFF-9B9E-4F3B-A976-89EA7D8B8385')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','D','3DCBCA5D-346F-44CC-8BB8-A6E9C7B7B13B')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','D','4DF63070-33F3-4074-AB72-B75010E268AC')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','D','226D3BEC-B08D-473C-8C24-E80FE3A0B771')");
        db.execSQL("INSERT INTO TBL_FormerDamageAndRisk(_id, DataType, ConceptID) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','D','DE7386AB-91E7-415E-B58B-EAE09CCCDCEF')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2014', '700000000', '2014')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2013', '700000000', '2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2012', '700000000', '2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2011', '700000000', '2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2010', '700000000', '2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2009', '700000000', '2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2008', '700000000', '2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2007', '700000000', '2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2006', '700000000', '2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('45664E3C-70A0-476F-AACC-9C730FFFFC58','2005', '700000000', '2005')");

    }

    public void insertInto_TBLS_Way_Tunnel(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('38D7DABF-0261-4BBB-B8EC-43CC072B4469','BC7003E5-34F6-40A9-965A-27A4B4AB1B51','','','Túnel 1','','0','0','0','0','Dic 12 2013  6:23PM','Admin Admin, Admin','0', '99999','290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('3022CC3C-D3FB-4320-8497-52E58897C410','BC7003E5-34F6-40A9-965A-27A4B4AB1B51','','','Túnel 2','','0','0','0','0','Dic 12 2013  6:39PM','Admin Admin, Admin','0', '99999','290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('36C83714-BA59-46DE-B3BD-C869385AE013','BC7003E5-34F6-40A9-965A-27A4B4AB1B51','','','Tunel 3','','0','0','0','0','Ene  9 2014  3:12PM','JUAN CARLOS GARCÍA, jgarcia','0', '99999','290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('3022CC3C-D3FB-4320-8497-52E58897C410','E0825274-57D0-43C4-9209-F52C7F2D7287','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('3022CC3C-D3FB-4320-8497-52E58897C410','5760E0C7-7612-49DA-86CE-B852E04477BD','24')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('3022CC3C-D3FB-4320-8497-52E58897C410','3210C802-6BED-4DE0-B6F5-66064949EA22','3000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('36C83714-BA59-46DE-B3BD-C869385AE013','5760E0C7-7612-49DA-86CE-B852E04477BD','10')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('36C83714-BA59-46DE-B3BD-C869385AE013','3210C802-6BED-4DE0-B6F5-66064949EA22','1000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('38D7DABF-0261-4BBB-B8EC-43CC072B4469','E88B42EE-81B7-4986-8A9F-7E8CBFE720EC','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('38D7DABF-0261-4BBB-B8EC-43CC072B4469','5760E0C7-7612-49DA-86CE-B852E04477BD','12')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('38D7DABF-0261-4BBB-B8EC-43CC072B4469','3210C802-6BED-4DE0-B6F5-66064949EA22','2000')");
    }

    public void insertInto_TBLS_Way_Puente(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('EBF6F46B-CE5C-443F-8A10-45DE3E8146A8','4FFF7B5D-90C2-4B92-848D-1BEF72520E1E','','','Puente 1','','0','0','0','0','Dic 12 2013  6:18PM','Admin Admin, Admin','0', '99999','290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('EAEE603E-6BBD-401A-9AB9-9348652B52F6','4FFF7B5D-90C2-4B92-848D-1BEF72520E1E','','','Puente 3','','0','0','0','0','Dic 12 2013  6:19PM','Admin Admin, Admin','0', '99999','290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('190BFCCB-F2E2-41E7-964E-F15C0C5087A5','4FFF7B5D-90C2-4B92-848D-1BEF72520E1E','','','Puente 2','','0','0','0','0','Dic 12 2013  6:18PM','Admin Admin, Admin','0', '99999','290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','00000000-0000-0000-0000-000000000000','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('EAEE603E-6BBD-401A-9AB9-9348652B52F6','1C5D6DBF-3D7A-4109-B209-EE743EEC9ACA','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('EAEE603E-6BBD-401A-9AB9-9348652B52F6','0D0F0F70-88D5-42EB-BFC7-02A067C5B4FD','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('EAEE603E-6BBD-401A-9AB9-9348652B52F6','5760E0C7-7612-49DA-86CE-B852E04477BD','123456789')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('EAEE603E-6BBD-401A-9AB9-9348652B52F6','3210C802-6BED-4DE0-B6F5-66064949EA22','10')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('190BFCCB-F2E2-41E7-964E-F15C0C5087A5','CB3F18B5-6FB1-46FC-807E-D5E29DFFDDF0','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('190BFCCB-F2E2-41E7-964E-F15C0C5087A5','0D0F0F70-88D5-42EB-BFC7-02A067C5B4FD','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('190BFCCB-F2E2-41E7-964E-F15C0C5087A5','5760E0C7-7612-49DA-86CE-B852E04477BD','123456678910')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('190BFCCB-F2E2-41E7-964E-F15C0C5087A5','3210C802-6BED-4DE0-B6F5-66064949EA22','200000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('EBF6F46B-CE5C-443F-8A10-45DE3E8146A8','98DA7E9F-8377-46A0-92F7-EC7535A5667F','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('EBF6F46B-CE5C-443F-8A10-45DE3E8146A8','0D0F0F70-88D5-42EB-BFC7-02A067C5B4FD','1')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('EBF6F46B-CE5C-443F-8A10-45DE3E8146A8','5760E0C7-7612-49DA-86CE-B852E04477BD','123456789')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('EBF6F46B-CE5C-443F-8A10-45DE3E8146A8','3210C802-6BED-4DE0-B6F5-66064949EA22','1000000000')");
    }

    public void insertInto_TBLS_Way(SQLiteDatabase db) {
        db.execSQL("INSERT INTO TBL_Properties(_id, Propertytype, PropertyNumber, PropertyUse, Name, Address, Lock, Assigned, Finding, Status, RegisterDate, RegisterUser, Deleted, Justification, PropertyParentID, PostalCodeID, BuildingDate, Levels) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','675D314E-5322-4499-853A-AB8BBADB6200','','','CPyT pba JC','','0','0','0','0','2013-12-12  6:17PM','Admin Admin, Admin','0', '99999','00000000-0000-0000-0000-000000000000','5A81EED4-B9A5-4A98-BA9F-00D69F8FDE60','88888', '77777')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','39298EC3-EA9B-4D99-931A-D2A58DCFFD86','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','BD6B4922-321D-4A57-9ED2-FA9A74B41CE1','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','39301E28-AFF7-452E-8B0B-9F38489AB220','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2EE66436-7EB6-463D-A64F-7EFA95791E72','0')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','919A4798-DA01-4BC9-9396-583AC4598968','19.390870')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','919A4798-DA01-4BC9-9396-583AC4598968','19.386093')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.146808')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','F68DAFA0-4401-426E-9FDB-C82F9D84A9A1','-99.131015')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','5760E0C7-7612-49DA-86CE-B852E04477BD','500')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','EDD00D9C-4FAD-4847-BE66-6B35252F704B','600')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','EDD00D9C-4FAD-4847-BE66-6B35252F704B','700')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','84CFF176-BB1E-4B18-9053-718888F9DB4A','800')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','84CFF176-BB1E-4B18-9053-718888F9DB4A','900')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','664B3CC3-031D-4CB6-8C66-0E9AF34465F0','5')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','4FBB6137-9322-4C6E-8AAB-E5471DF59F20','10')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','6419CE4B-D535-4BCE-AF9F-E0DBB3AC3F3B','6')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','A278335A-5C15-4087-8D5F-BF9DED3C7ACB','45600')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','B1C48FCD-BC79-47C3-951F-04689E2C63D3','18')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','F092724D-DA1E-44AA-AAA0-55E562E05221','300')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','3210C802-6BED-4DE0-B6F5-66064949EA22','5000000')");
        db.execSQL("INSERT INTO TBL_PropertyFeatures(_id, FeatureID, Value) VALUES('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','BFE031DC-88BF-422B-94EE-8A26A4729F45','1')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2013', '700000000', '2013')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2012', '700000000', '2012')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2011', '700000000', '2011')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2010', '700000000', '2010')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2009', '700000000', '2009')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2008', '700000000', '2008')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2007', '700000000', '2007')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2006', '700000000', '2006')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2005', '700000000', '2005')");
        db.execSQL("INSERT INTO TBL_AnnualAmount(_id, Year, Amount, Description) Values ('290DF55D-7C66-4BEA-A7AE-D9D435AEB0C9','2004', '700000000', '2004')");
    }
    public void insertInto_TBL_Users(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse) VALUES('73C7FA35-602F-45F0-B9A5-06358A241A6C','73C7FA35-602F-45F0-B9A5-06358A241A6C2','USER_TEST','1','0','0','20','0','')");
        db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse) VALUES('50B514C6-ED4B-45E2-9318-09EDA15323A5','50B514C6-ED4B-45E2-9318-09EDA15323A52','USERTEST','1','0','0','22','0','')	");
        db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse) VALUES('27CC1819-B890-407F-8638-459D6848CD0A','27CC1819-B890-407F-8638-459D6848CD0A2','USERTEST1','1','0','0','23','0','')");
        db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse) VALUES('641EB7A6-5D31-4F16-AC43-7241992DFD5C','641EB7A6-5D31-4F16-AC43-7241992DFD5C2','USERTEST.','1','0','0','24','0','')");
        db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse) VALUES('76BD5F1A-4A22-4E7B-93EE-74CD1C1C6925','76BD5F1A-4A22-4E7B-93EE-74CD1C1C6925','jgarcia','1','0','0','18','0','')");
        db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse) VALUES('269E6823-F600-4B8C-866C-80CF47FCE591','269E6823-F600-4B8C-866C-80CF47FCE591','Admin','1','0','0','1','0','')");
        db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse) VALUES('9401D82B-3A8D-4B73-9702-8A22588D3746','9401D82B-3A8D-4B73-9702-8A22588D37462','USER-TEST','1','0','0','21','0','')");
        db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse) VALUES('EB271A9B-B8AC-458B-A1DC-C8368F707215','EB271A9B-B8AC-458B-A1DC-C8368F707215','fbuendia','1','0','0','19','0','')");
        db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse) VALUES('144BC6E0-CC1D-4F57-A399-EE3315FFE4EF','144BC6E0-CC1D-4F57-A399-EE3315FFE4EF','vhernandez','1','0','0','17','0','')");

    }

    public void insertInto_TBL_WeatherStations(SQLiteDatabase db){
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D98A0CF2-A4F5-40B1-B60C-02AC96ADFA7E','494C0A72-F88B-4E66-AEC5-94A63D228153','MATLAPA','19.379089','-99.134877','133','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('DF70DA87-C53C-45A0-8B65-043DA4F58EAC','8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','PROGRESO','19.413795','-99.064756','2','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('8164A5C0-988D-4E5E-85FA-060100681A3E','DC09E0A9-1040-4080-BB58-33061D1C1699','TEPEHUANES','19.416385','-99.056345','1874','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('F6C3F8AF-448D-4629-9325-07E088668AA6','ECB19424-C020-4381-B7F7-1A892CAD84F0','PALENQUE','19.545743','-99.235066','52','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('506B5232-6F10-4922-BAFD-08C27C78A52D','8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','TANTAQUIN','20.0303','89.0472','30','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('873C6035-5AC6-47F1-99DE-0B52391545E8','3A94A057-9BAF-4633-9AD4-72F60065C46A','SALINA CRUZ','16.1619','95.2267','2','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('75BE6314-E454-4470-B04F-0B9BCBA6DF73','C8E5BBC2-8142-4A50-9520-996F0942A383','PARAISO','18.4231','93.1556','4','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('4B571C4A-910A-4149-9F06-0D17E5F0DE10','44AD696B-CAE1-4251-A975-52EE8ACF6399','URUAPAN','19.3808','102.029','1606','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('23C659BE-7AE9-4157-87F5-0D66B0EACE8F','24DDF93E-A50A-4296-B248-0F9910098A33','LA RUMOROSA','32.2722','116.206','1262','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('69B4DF42-FD1D-45A2-8566-0E9A0D64FC4F','3A94A057-9BAF-4633-9AD4-72F60065C46A','PINOTEPA NACIONAL','16.3497','98.0525','195','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7C075B1D-6171-44ED-9DF9-1195C73187B8','E58E0C92-3AEB-4378-B962-786BD484459C','HUAUCHINANGO','20.0989','98.1528','2193','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('2A0E741A-5C78-4766-B0E4-12B27B3AFF08','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','NUEVO CASA GRANDES','30.39','107.917','1467','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('66A3B035-0ABF-4FA9-BB2B-132CE5A38CC1','C76D4B02-BB8E-4479-A2E0-50473E4B0775','CERRO CATEDRAL','19.5419','99.5192','3754','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('9CD43ADD-5999-4D81-BEF2-14E63F2E85DE','08839679-93F8-43A0-B0C8-C3AA75147EB6','XALAPA','19.5119','96.9039','1360','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('6D1231E9-7DA3-4947-86B8-15146CCB6832','3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','CHETUMAL','18.5006','88.3275','9','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D0483646-DBBF-4DA8-8644-1705F6A96AA1','EE4282A4-33DB-426B-AFA7-4EB32074384A','LOS COLOMOS','20.7067','103.393','1571','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('61F4C1B0-11AC-4FEE-92C9-17E6F697537C','24DDF93E-A50A-4296-B248-0F9910098A33','BAHÍA DE LOS ANGELES','28.97','113.561','10','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('C16432CA-6A55-4C94-9391-1934E6E46E26','DE9C700C-0B99-4683-8E02-977A142120C8','SAN JUAN','25.4856','107.843','112','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('1CB76073-AE79-4D9B-93B4-1942812CB629','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','EL VERGEL','26.4733','106.39','2800','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D7718C43-4CDD-4804-BD90-194D2992F39E','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','VILLA AHUMADA','30.6156','106.505','1931','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('CBA5F7B0-380D-49AE-8D7E-19C171240EF2','ECB19424-C020-4381-B7F7-1A892CAD84F0','ESCUINTLA','15.2975','92.6761','42','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('FCD01947-9C1F-4215-9573-1B0F407B79C1','BD6B4922-321D-4A57-9ED2-FA9A74B41CE1','ESCUELA NACIONAL DE CIENCIAS BIOLÓGICAS II, IPN.','19.4986','99.1453','2240','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('ACFC41E0-C36E-4840-866F-1BBF2F9464BB','44AD696B-CAE1-4251-A975-52EE8ACF6399','ANGAMACUTIRO','20.1253','101.723','1730','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('F50F0595-220A-468C-80DB-1BC7F4376F9B','DC09E0A9-1040-4080-BB58-33061D1C1699','LA FLOR','26.5497','103.986','1181','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('65BA6786-CC52-4A8A-B787-1C177E3682F2','18B65E0C-7C41-480A-8D19-130624114EF7','CAMPECHE','19.8361','90.5072','11','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('A5363D71-D80B-45F4-B509-1EEE5D6DD295','DE9C700C-0B99-4683-8E02-977A142120C8','CULIACAN','24.8','107.4','38','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('AA11BB0F-691C-41C8-BA01-1F74635121BD','24DDF93E-A50A-4296-B248-0F9910098A33','SAN QUINTÍN','30.5317','115.937','32','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('883F0828-F2AE-4D37-8672-1F8E9BB37795','494C0A72-F88B-4E66-AEC5-94A63D228153','TAMUÍN','22.0167','98.7833','23','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('EB43C753-3D3F-476D-B998-1FBB68C23FFF','08839679-93F8-43A0-B0C8-C3AA75147EB6','TUXPAN','20.96','97.4172','28','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('95212045-CD42-4B5B-BF7A-23A7E96DD2DF','ECB19424-C020-4381-B7F7-1A892CAD84F0','TAPACHULA','14.8869','92.2964','118.1','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('489A0D51-8888-4F2A-8358-23BB39CBCCCA','120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','ACAPULCO','16.7628','99.7492','3','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('74327F03-4011-480A-A664-26C4640C1D25','8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','CELESTÚN','20.8581','90.3831','10','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('5E8BFDED-C931-47ED-82AC-2B26A662172B','BE7DCBE2-5B33-4ADF-9521-434B378E72AC','HUICHAPAN','20.3886','99.6639','2080','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('F8F938B6-387B-4DF3-880F-2E5D8E9C639F','808920BA-B0C3-4B8F-ABEC-9883688BAD36','NOGALES','31.2978','110.914','1275','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('084B90D1-CA32-49BB-976C-35DF2025922F','DE9C700C-0B99-4683-8E02-977A142120C8','OBISPO','24.2511','107.188','4','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D1A22604-18C7-4B8E-8C78-365AC4FC7F55','F83A9BA0-6D0C-4C1B-AAE7-EDB04056ABF3','ZACATECAS','22.7467','102.506','2270','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('B40414DC-3E1D-4AB1-A7A6-3A381108150E','E58E0C92-3AEB-4378-B962-786BD484459C','IZUCAR DE MATAMOROS','18.6167','98.4519','1353','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7028B778-0870-474B-8873-3EF8BFD26422','C76D4B02-BB8E-4479-A2E0-50473E4B0775','CEMCAS','19.4797','98.9736','2176','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('99B3F6B7-848D-4F1F-993D-40D9453B38FA','808920BA-B0C3-4B8F-ABEC-9883688BAD36','SAN LUIS RIO COLORADO','32.4239','114.798','39','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('DC047E6F-3C5C-44B2-BEC6-44EC6BEA840D','DC09E0A9-1040-4080-BB58-33061D1C1699','LAS VEGAS','24.1858','105.466','2398','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('44B787C3-95B8-41BD-8A45-46A6A4A16370','494C0A72-F88B-4E66-AEC5-94A63D228153','MATEHUALA','23.64','100.658','1627','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('74FA5FCF-4581-441E-8954-470B41B6CB3B','245341C6-5DBE-4227-BF11-9172906C1E24','HUIMILPAN','20.39','100.283','2280','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('90B44480-ECBC-45EF-BA05-4761ECC8A081','BD6B4922-321D-4A57-9ED2-FA9A74B41CE1','ECOGUARDAS','19.2714','99.2039','2200','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('C57A8CC5-91E6-416E-9121-48C2153A6CF0','BA97FAB2-D490-45B3-A92D-2300541BC757','CUATRO CIENEGAS','26.9903','102.038','725','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('677426E3-2E27-4251-ABFB-49710FD1989D','2D09E941-9416-4A8C-9AA9-B2C8581FBCAF','TLAXCALA','19.3167','98.2333','2247','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('75467693-4ECC-4BD1-83B2-4BF8DCE3819A','BE7DCBE2-5B33-4ADF-9521-434B378E72AC','ZIMAPAN','20.74','99.3906','1788','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('749DFA17-CC91-4F77-8FFA-4C338E0B835A','08839679-93F8-43A0-B0C8-C3AA75147EB6','ORIZABA','18.85','97.1','1259','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('32CC83F7-0293-44EF-99DD-4D2CBE774CCF','808920BA-B0C3-4B8F-ABEC-9883688BAD36','HERMOSILLO - BAHIA DE KINO','29.0133','111.137','160','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('59F04237-E738-401D-8C25-4D659F2895AD','C76D4B02-BB8E-4479-A2E0-50473E4B0775','PRESA MADÍN','19.5244','99.2681','2364','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('604518FF-5210-4573-AD5F-4E4CBE62BB96','BA97FAB2-D490-45B3-A92D-2300541BC757','PIEDRAS NEGRAS','28.6833','100.567','250','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('C8F9438A-4F90-4E6D-B7C3-4F519EB623DA','DAB02A9A-8A91-463F-BA49-129A0E6E1389','SANTA ROSALÍA','27.3381','112.274','53','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('06E595D7-1BC9-436A-8DB1-50BC51A2D9C4','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','URIQUE','27.2156','107.917','577','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('A93FAC0F-76E2-49F0-9FDA-519E2AFDA268','EE4282A4-33DB-426B-AFA7-4EB32074384A','RÍO TOMATLAN','19.9983','105.133','141','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('35E88450-AF07-466D-8E37-51BF4E4B343D','EE4282A4-33DB-426B-AFA7-4EB32074384A','JOCOTEPEC','20.2831','103.416','1506','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('BAF410DD-60D0-4566-B0A7-5258CF0CC05F','6D431485-6C7F-417A-80C8-5678B9AF5ACF','INSTITUTO MEXICANO DE TECNOLOGÍA DEL AGUA','18.8822','99.1569','1355','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('ADADA9E0-6A70-47CB-94A0-528E24FBD20B','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','MAGUARICHI','27.8583','107.994','1663','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('543C86C1-F271-43D8-AA4A-54CEEF6C9D86','3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','NICOLAS BRAVO','18.4553','88.9239','104','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('1EA22302-8AC9-4B3C-898E-554D19B0727E','08839679-93F8-43A0-B0C8-C3AA75147EB6','TUXPAN','20.96','97.4169','5','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D1BB6D16-2397-40B1-8952-55E6D6B08D3F','3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','CHETUMAL','18.5006','88.3278','14','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('9BCB8E98-DE2D-4EC2-8702-5742F6EA7410','6D431485-6C7F-417A-80C8-5678B9AF5ACF','TEPOZTLAN','18.9508','99.0789','1384','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('0C53DFF7-BB9D-4909-91C2-57527F98F35B','63BAFD4F-6234-4A6F-9FAF-5B959069353D','IXTLAN DEL RIO','21.0386','104.285','1163','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('B70A973E-778D-465E-902E-5F870E7AF170','8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','OXKUTZCAB','20.2911','89.3944','28','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('F460D18F-726F-47FA-8E69-5FC0F2FCE8E0','808920BA-B0C3-4B8F-ABEC-9883688BAD36','ALAMOS','27.0217','108.938','409','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7B5E5789-C5E1-46E4-8FE6-60C6F443E40B','3A94A057-9BAF-4633-9AD4-72F60065C46A','MIAHUATLAN','16.3442','96.5797','0','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('B026965E-3F5C-42F4-8F3B-60E4733C8E51','2D09E941-9416-4A8C-9AA9-B2C8581FBCAF','HUAMANTLA','19.3833','97.95','2222','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('752991C0-9361-4D29-BEDE-619887BD68F7','08839679-93F8-43A0-B0C8-C3AA75147EB6','CITLALTEPEC','21.3344','97.8786','211','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D7DF30CC-53EB-42A3-9A3E-636A3CEC0132','DAB02A9A-8A91-463F-BA49-129A0E6E1389','CABO SAN LUCAS','22.8811','109.926','224','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('EACFBE3C-9126-4483-94AF-64C4E109D25C','3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','FELIPE CARRILLO PUERTO','19.5833','88.05','20','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('2427CE1D-CC70-41B7-9132-658B2E6D910D','120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','CD. ALTAMIRANO','18.3506','100.658','251','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('A9E4CD2E-F07D-45D2-8676-65EB58A5F661','DC09E0A9-1040-4080-BB58-33061D1C1699','SAN JUAN DE GUADALUPE','24.6314','102.774','1531','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('9CDC968E-C7E3-4FE8-8AA5-66B8A07A92B5','3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','SIAN KAAN','20.1278','87.4656','8','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('474C663B-AFFE-4C6D-85CE-6739CC581C8E','ECB19424-C020-4381-B7F7-1A892CAD84F0','ARRIAGA','16.2322','93.9039','49','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7E0DBCBD-4C56-46AC-842C-68337B1E8A67','3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','LA UNION','17.8969','88.8786','12','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('2A68CECE-72AE-4547-94BB-6ABB3CB72056','120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','TLAPA DE COMONFORT','17.5494','98.5631','1060','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('3D5B7F50-2CB4-43D0-870C-6BD068AAAE3C','18B65E0C-7C41-480A-8D19-130624114EF7','CALAKMUL','18.365','89.8925','28','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('C8C60252-C410-434F-9302-6C3F16DDD8AB','E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','VILLAGRAN','24.4706','99.4886','390','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('A39DC392-F5C4-4834-85A8-6D1F1EB4A753','EE4282A4-33DB-426B-AFA7-4EB32074384A','TIZAPAN','20.1694','103.044','1503','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('DA550504-1C17-4DB6-BF2D-6DDF4BD93C7B','3A94A057-9BAF-4633-9AD4-72F60065C46A','PUERTO ÁNGEL','15.6711','96.4972','91','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D38DFC9E-9204-41E4-A3DD-6EEF57BEAC32','BD6B4922-321D-4A57-9ED2-FA9A74B41CE1','ESCUELA NACIONAL DE CIENCIAS BIOLÓGICAS, IPN.','19.4536','99.1711','2389','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('5F4D4B8A-9354-4B3F-926E-6F7D35D46022','BE7DCBE2-5B33-4ADF-9521-434B378E72AC','ZACUALTIPAN','20.6644','98.6817','2056','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('59FFEEBC-55EF-4D00-9ED9-6F7E412565BF','BA97FAB2-D490-45B3-A92D-2300541BC757','SANTA CECILIA','28.3994','101.213','595','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('BD849119-6253-4689-BC42-70A7F6796D8B','08839679-93F8-43A0-B0C8-C3AA75147EB6','COATZACOALCO','18.1403','94.5108','27','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('0257C20E-612C-4FA5-A894-72ED880E60FF','BA97FAB2-D490-45B3-A92D-2300541BC757','NUEVA ROSITA','27.9214','101.207','366','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('16712562-7CFE-427B-AE28-73EDCC4FFA18','24DDF93E-A50A-4296-B248-0F9910098A33','PRESA EMILIO LÓPEZ ZAMORA (ENSENADA)','31.8914','116.603','32','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('F44E56E4-15C7-45D6-BDBC-76DB72C49F10','24DDF93E-A50A-4296-B248-0F9910098A33','PRESA ABELARDO L.RODRÍGUEZ (TIJUANA)','32.4472','116.908','156','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('1075AD51-39AF-4080-8B33-76E582C45BAD','8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','RÍO LAGARTOS','21.5714','88.1603','5','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('0F62231B-00DD-4850-9DB4-78A6CD37D960','18B65E0C-7C41-480A-8D19-130624114EF7','YOHALTUM','19.0136','90.3108','80','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('E848A862-3B67-4E16-BE36-7DFCE76C1AF2','069942C1-2FCC-4ABA-956C-3A2EE69C6C68','PRESA ALLENDE','20.8483','100.826','1915','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('50FA200A-86A9-44AB-867D-7E151492C12F','63BAFD4F-6234-4A6F-9FAF-5B959069353D','ACAPONETA','22.4667','105.385','29','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D5669E82-F937-4FCA-BC03-7F19AA0B29E1','E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','MATAMOROS','25.8858','97.5186','4','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('CDF368DB-578C-4AAB-93EF-80ABFA462E55','C76D4B02-BB8E-4479-A2E0-50473E4B0775','PARQUE IZTA-POPO','19.0956','98.6403','3682','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('9E2850D8-43F0-40B3-934F-821CBAF48B52','BD6B4922-321D-4A57-9ED2-FA9A74B41CE1','TEZONTLE','19.3847','99.0994','2358','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('F790143E-D345-4819-972D-85FBD7521A1B','BA97FAB2-D490-45B3-A92D-2300541BC757','TORREÓN','25.5203','100.416','1123','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('896077AD-48D5-4B40-8CD3-87D158092D7E','08839679-93F8-43A0-B0C8-C3AA75147EB6','PEROTE','19.545','97.2683','0','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('959281EA-177F-4E89-8DBF-8870FCCEA3EF','E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','CIUDAD VICTORIA','23.7333','99.1333','336','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('015A7355-3A97-422C-92A0-8ABA234DBD33','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','CD. CUAUHTEMOC','28.3967','106.839','2100','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('9BE5AA78-45FA-48CD-88E2-8BAA49CB53EC','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','JIMENEZ','27.1108','104.907','1360','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('4572734C-EAC7-49BC-A799-8C5B17CC9CF9','E58E0C92-3AEB-4378-B962-786BD484459C','UNIVERSIDAD TECNOLÓGICA DE TECAMACHALCO','18.8664','97.7217','2047','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('68F00220-B5F2-456C-90FF-8E6D44F16C7C','BA97FAB2-D490-45B3-A92D-2300541BC757','VENUSTIANO CARRANZA','27.5194','100.617','264','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('37905ED1-3A17-4064-BF38-8ECFF5F52D5C','DE9C700C-0B99-4683-8E02-977A142120C8','CHOIX','26.7167','108.333','237','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('AAA8F5BE-0170-4A8C-90C7-8FE939D6F974','3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','COZUMEL','20.4769','86.9069','5','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('A244EEA8-51B4-48EC-9BCC-8FF18384D3F2','BA97FAB2-D490-45B3-A92D-2300541BC757','6D431485-6C7F-417A-80C8-5678B9AF5ACF - MUZQUIZ','28.0131','101.711','492','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('ABD955CA-E77C-4CC7-A086-90A4B752F6D2','E4886740-2AA3-4DF7-8AD5-016E0A02E110','CALVILLO','21.8494','102.712','1618','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('37583D0D-DBD3-4ADC-9075-92A2DD41F883','DAB02A9A-8A91-463F-BA49-129A0E6E1389','CD. CONSTITUCIÓN','25.0097','111.663','28','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('ECA4FB69-8504-4A83-A260-92D60765CE08','24DDF93E-A50A-4296-B248-0F9910098A33','SAN FELIPE','31.0281','114.847','20','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('CEEFB5DA-A449-4279-A0D2-9337AA312EFA','DC09E0A9-1040-4080-BB58-33061D1C1699','VILLA OCAMPO','26.4406','105.502','1657','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('776352A6-0748-4604-B04F-944204CEAA15','08839679-93F8-43A0-B0C8-C3AA75147EB6','CORDOBA','18.89','96.9231','852','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('27BBB3ED-195D-447B-9FFF-94814D1A73A5','6D431485-6C7F-417A-80C8-5678B9AF5ACF','TRES MARIAS','19.0506','99.2486','0','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('686325B6-4FBA-4327-84CB-94A6E78D2177','3A94A057-9BAF-4633-9AD4-72F60065C46A','OAXACA','17','96.7167','1518','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('82A4D341-206A-43D6-BAF3-9746B17C45F0','494C0A72-F88B-4E66-AEC5-94A63D228153','SAN LUIS POTOSÍ','22.1758','100.987','1870','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('066D4DC6-DBD3-4F7A-97AD-997CFA018609','DAB02A9A-8A91-463F-BA49-129A0E6E1389','GUSTAVO DÍAZ ORDAZ','27.6428','113.458','37','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('1E3164B9-ECBF-441C-BE6B-9B04C37C422E','808920BA-B0C3-4B8F-ABEC-9883688BAD36','YECORA','28.3667','108.917','1531','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('70486C60-07F5-4D0B-82CE-9D6172346469','44AD696B-CAE1-4251-A975-52EE8ACF6399','MORELIA','19.7','101.183','1912','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('9B3E956E-A4E2-4266-BE09-9E825AB66E6C','18B65E0C-7C41-480A-8D19-130624114EF7','MONCLOVA','18.0569','90.8208','100','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('5528A230-32D6-41EB-B7E3-A004EAEBD91D','08839679-93F8-43A0-B0C8-C3AA75147EB6','08839679-93F8-43A0-B0C8-C3AA75147EB6','19.1425','96.1131','16.45','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('20946F93-4A08-4AA8-9AEC-A066768FC96B','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','OJINAGA','29.5342','104.476','790','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('67BD9CD7-7F02-4763-9F0E-A418B229F493','BD6B4922-321D-4A57-9ED2-FA9A74B41CE1','TACUBAYA','19.4036','99.1967','2308','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('E60FE298-B4E4-47BF-8477-A782AADB8F84','494C0A72-F88B-4E66-AEC5-94A63D228153','CIUDAD VALLES','21.9797','99.0308','58','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('52D42F5F-4BED-4021-9A50-A85FE44FB3A8','24DDF93E-A50A-4296-B248-0F9910098A33','CATAVIÑA','29.7272','114.719','514','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('8BF997BF-A7F9-43BB-A19E-A97EB945D9CF','3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','JOSE MARIA MORELOS','19.7522','88.7036','56','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('FB9CFDDE-2474-4FD9-8E36-AFDEE3B4EB2A','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','BASASEACHI','28.1992','108.209','1973','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('E3D01A00-DF49-4254-A65A-B024CDE4C4DF','808920BA-B0C3-4B8F-ABEC-9883688BAD36','ALTAR','30.7167','111.883','397','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('591E29E9-E06D-495A-8A1E-B13AC9473D9D','E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','CIUDAD MANTE','22.7444','98.9831','85','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('2F460F59-5BE0-490E-852D-B2FBB678FDB4','808920BA-B0C3-4B8F-ABEC-9883688BAD36','CABORCA','30.7719','112.435','210','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('AA518AB2-FF77-4CA1-B792-B31DA28B8C08','494C0A72-F88B-4E66-AEC5-94A63D228153','CIUDAD FERNANDEZ','21.9361','100.022','1009','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D74BF590-F4DE-42F9-9468-B4B9CE41672C','BA97FAB2-D490-45B3-A92D-2300541BC757','MONCLOVA','26.9333','101.433','615','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('B651C75A-DF76-487F-AF51-B4CD23B5F46A','6D431485-6C7F-417A-80C8-5678B9AF5ACF','CUERNAVACA','18.9333','99.2333','1618','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('9E1BC551-59C4-4C8B-96FB-B8F9CC37FF3C','3A94A057-9BAF-4633-9AD4-72F60065C46A','HUAJUAPAN DE LEON','17.7889','97.7717','1650','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('AEF8432C-73DE-4DF7-8B97-B9347AAE7D72','E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','BARRA DEL TORDO','23.0519','97.7722','5','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('1AB6DAE9-1445-473F-84B2-BDA8A3FEE8DB','E4886740-2AA3-4DF7-8AD5-016E0A02E110','AGUASCALIENTES','21.8511','102.291','1874','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('9E86F2F3-C748-4F7D-8630-BE0CCA44F46F','8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','DZILAM','21.3906','88.9042','2','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('D28A4AA1-CAE0-48A8-8DF9-BECEE3ED04F6','120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','PETACALCO','17.9844','102.123','53','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('52431AEC-6F91-468E-B6CE-BED679C35686','BE7DCBE2-5B33-4ADF-9521-434B378E72AC','TULANCINGO','20.0842','98.3572','2213','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('A6900930-A1F2-4EB2-B988-BF35C059B5A0','E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','ALTAMIRA','22.3875','97.9256','9','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('5D4C48D7-06A2-4755-BAD4-C01809E45B68','E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','SOTO LA MARÍNA','23.7667','98.2167','21','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('67D2209D-1C79-4B89-8543-C08A4489F1F1','18B65E0C-7C41-480A-8D19-130624114EF7','ESCARCEGA','18.6078','90.7586','60','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7C9BB997-8901-447D-8344-C0A57119924B','C76D4B02-BB8E-4479-A2E0-50473E4B0775','NEVADO DE TOLUCA','19.1258','99.7708','4139','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('4E21C011-C80F-40FE-91F9-C27632327778','8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','VALLADOLID','20.6833','88.2167','86','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('5418E6B4-3209-4850-A0E2-C3D6D1F8FA5D','08839679-93F8-43A0-B0C8-C3AA75147EB6','CD. ALEMAN','18.1892','96.0975','107','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('1B527D66-499E-440A-B049-C5CD270AC352','8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','TIZIMIN','21.1614','87.9889','5','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('074D5088-8F86-4BCC-BDD1-C7F7DC130E3E','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','CHINATÚ','26.2294','106.771','1982','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('931DB1EA-FC99-4ABC-AB74-C85DC8FEDED9','08839679-93F8-43A0-B0C8-C3AA75147EB6','ACAYUCAN','17.9767','94.9008','106','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('5D3EA4C3-2C3A-4853-AB1C-CAC69FA3DCDB','BE7DCBE2-5B33-4ADF-9521-434B378E72AC','HUEJUTLA','21.1547','98.3686','115','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('6DF1ECF5-85C9-4DC2-A702-CC3905D0548E','DE9C700C-0B99-4683-8E02-977A142120C8','EL FUERTE','26.4114','108.618','82','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('8B54FDBE-0F47-4354-BDE0-CFFAE54A79B1','44AD696B-CAE1-4251-A975-52EE8ACF6399','APATZINGAN','19.0828','102.372','282','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('A18B086C-DA2E-452A-BDB6-D3B14F6A4808','24DDF93E-A50A-4296-B248-0F9910098A33','MEXICALI','32.6661','115.453','50','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7E02637F-C562-4309-AE7E-D40B13D88438','808920BA-B0C3-4B8F-ABEC-9883688BAD36','SONOYTA','31.8653','112.996','393','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('C1D8C7D6-86B2-4055-8A86-D5CA7A314563','3A94A057-9BAF-4633-9AD4-72F60065C46A','MATIAS ROMERO','16.8828','95.0364','186','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('0E729366-033F-49E3-926E-D61DDC7175EA','120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','ACAPULCO','16.7633','99.7489','7.5','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('00F090D7-3B88-4F3B-B6B8-D74A435C7F42','08839679-93F8-43A0-B0C8-C3AA75147EB6','PRESA LA CANGREJERA','18.1058','94.3314','34','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('55121C20-56DA-4BE2-B9AB-D82DB83E4524','18B65E0C-7C41-480A-8D19-130624114EF7','CD. DEL CARMEN','18.6481','91.8225','8','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('25751349-00F8-4589-8470-D8904F1862D3','3BA7F72B-91EE-4C09-8A4B-937E7C7F2DF3','CANCÚN','21.0294','86.8522','4','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('B7384690-CF47-41E3-9D31-DA932A546120','120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','ATOYAC','17.2094','100.44','120','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('14361011-0AAA-4BDF-AC74-DCF6DCB7F5EA','F83A9BA0-6D0C-4C1B-AAE7-EDB04056ABF3','SOMBRERETE','23.6333','103.65','1351','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('87EE5473-67A5-46A8-ACC1-DF637C646931','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','CHINIPAS','27.3897','108.536','431','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('853C8352-BBA8-448F-A444-E3CFC1A70AAE','F83A9BA0-6D0C-4C1B-AAE7-EDB04056ABF3','LA FLORIDA','22.6861','103.603','1870','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('C535A2F0-5FB5-4E24-A52B-E73D72A3C4CA','27FC32A7-DC28-455C-AD54-6BB56DA86E1F','PRESA EL CUCHILLO','25.7331','99.3208','134','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('A2EE003F-36A1-4C27-9EFD-E8EF2D31134E','C76D4B02-BB8E-4479-A2E0-50473E4B0775','ATLACOMULCO','19.7917','99.8697','2600','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('3C5649E1-8249-4F18-A927-EAD2A302C8AA','EE4282A4-33DB-426B-AFA7-4EB32074384A','CHAPALA','20.2903','103.202','1493','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('4B09C30F-14A6-445C-8545-EB341C9B5E67','BE7DCBE2-5B33-4ADF-9521-434B378E72AC','PACHUCA','20.1086','98.7069','2423','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('BC8620D8-DFC6-4901-A53E-EBD0C921C31B','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','CHIHUAHUA','28.6706','106.03','1433','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('39B7E3E7-1861-4711-9262-EBF99E15D653','DAB02A9A-8A91-463F-BA49-129A0E6E1389','SAN JUANICO','26.2575','112.479','14','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('DD3A7163-AA10-483F-AEE5-F004A8D3E055','DC09E0A9-1040-4080-BB58-33061D1C1699','AGUSTIN MELGAR','25.2633','104.066','1226','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('30F42AAA-2C71-4DE9-A7BF-F10FC8E3DC2B','DAB02A9A-8A91-463F-BA49-129A0E6E1389','LORETO','26.0167','111.35','15','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('542B05BC-181C-48A0-AD0F-F12391373C6A','8B8C63D0-97EF-468F-A2AD-CA24CB6C664B','MERIDA','20.9469','89.6522','11','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('BB0EDF42-4F1A-48DA-BA37-F18DADA6409B','08839679-93F8-43A0-B0C8-C3AA75147EB6','ALVARADO','18.715','95.6325','113','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('76915AF2-C575-4C25-AA3A-F3D14B1927B5','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','CIUDAD DELICIAS','28.1975','105.467','1188','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('C41382F0-A1CE-4CE6-93C0-F43F06CD7E3E','3A94A057-9BAF-4633-9AD4-72F60065C46A','NOCHISTLAN','17.4367','97.2492','2040','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7BCCEB53-94B3-4488-9B79-F4E1C6B50726','069942C1-2FCC-4ABA-956C-3A2EE69C6C68','GUANAJUATO','21.0167','101.25','1999','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7E305C90-4090-4F47-B7ED-F5296B1973D3','120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','IGUALA','18.3603','99.5242','780','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7BBA7A16-F3B8-482C-BACC-F60A111F08CF','E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','JAUMAVE','23.4075','99.3753','750','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('9B646189-C3F8-4B3D-9FCC-F775AF96CBB4','93FF0681-8D45-470A-8BBC-2C46250CB550','COLIMA','19.2342','103.72','444','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('09A15759-9762-46D6-8600-F85EA46B2BA6','E58E0C92-3AEB-4378-B962-786BD484459C','TEZUITLAN','19.8878','97.3906','1578','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('B1E09D58-F8CE-487F-B176-F8CB9B82BCC2','3A94A057-9BAF-4633-9AD4-72F60065C46A','PUERTO ÁNGEL','15.6583','96.5003','46','20140131','ESIME')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('7F33A1D1-6617-4DD6-9DD7-F95246E4E1E9','C2CA920B-6C45-41D2-9FB9-1B9A44B46411','GUACHOCHI','26.8133','107.073','2390','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('A2089328-4328-4DA5-ADD9-FAE522629765','E3F18C25-C5C3-4894-8C19-B1449A6E7B3B','SAN FERNANDO','24.8428','98.1575','45','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('65EA5235-2AB3-4297-8C7B-FB6750F2F3D4','120210B7-BFAD-48DE-AFB0-3EBB43E0ADF0','ZIHUATANEJO','17.645','101.555','5','20140131','EMA')");
        db.execSQL("INSERT INTO TBL_WeatherStations(_id, StateID, StationName, Latitude, Longitude, Altitude, StartDate, StationType) VALUES('81098907-3113-44DC-8F0C-FB7C9CF8466F','808920BA-B0C3-4B8F-ABEC-9883688BAD36','CIUDAD OBREGÓN','27.4833','109.933','38','20140131','ESIME')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Se elimina la versión anterior de la tabla Personas.
        db.execSQL("DROP TABLE IF EXISTS TBL_Properties");

        // Se crea la nueva versión de la tabla personas.
        db.execSQL(SQL_CREATE_TABLE_TBL_Properties);
    }

    public void insertarProperty(Property property){
        ContentValues valores = new ContentValues();
        valores.put("_id", property.getPropertyID());
        valores.put("PropertyType", property.getPropertyType());
        valores.put("PropertyNumber", property.getPropertyNumber());
        valores.put("PropertyUse", property.getPropertyUse());
        valores.put("Name", property.getName());
        valores.put("Address", property.getAddress());
        valores.put("Lock", property.getLock());
        valores.put("Assigned", property.getAssigned());
        valores.put("Finding", property.getFinding());
        valores.put("Status", property.getStatus());
        valores.put("RegisterDate", property.getRegisterDate());
        valores.put("RegisterUser", property.getRegisterUser());
        valores.put("Deleted", property.getDeleted());
        valores.put("Justification", property.getJustification());
        valores.put("PropertyParentID", property.getPropertyParentID());
        valores.put("PostalCodeID", property.getPostalCodeID());
        valores.put("BuildingDate", property.getBuildingDate());
        valores.put("Levels", property.getLevels());
        try{
            this.getReadableDatabase().insert("TBL_Properties", null, valores);
        }
        catch (Exception e){
            String error =  e.getMessage().toString();
        }
    }

    public void insertarProperty(
            String PropertyID,
            String PropertyType,
            String PropertyNumber,
            String PropertyUse,
            String Name,
            String Address,
            int Lock,
            int Assigned,
            int Finding,
            int Status,
            String RegisterDate,
            String RegisterUser,
            String Deleted,
            String Justification,
            String PropertyParentID,
            String PostalCodeID,
            String BuildingDate,
            int Levels
    ){
        ContentValues valores = new ContentValues();
        valores.put("_id", PropertyID);
        valores.put("PropertyType", PropertyType);
        valores.put("PropertyNumber", PropertyNumber);
        valores.put("PropertyUse", PropertyUse);
        valores.put("Name", Name);
        valores.put("Address", Address);
        valores.put("Lock", Lock);
        valores.put("Assigned", Assigned);
        valores.put("Finding", Finding);
        valores.put("Status", Status);
        valores.put("RegisterDate", RegisterDate);
        valores.put("RegisterUser", RegisterUser);
        valores.put("Deleted", Deleted);
        valores.put("Justification", Justification);
        valores.put("PropertyParentID", PropertyParentID);
        valores.put("PostalCodeID", PostalCodeID);
        valores.put("BuildingDate", BuildingDate);
        valores.put("Levels", Levels);
        try{
            this.getReadableDatabase().insert("TBL_Properties", null, valores);
        }
        catch (Exception e){
            String error =  e.getMessage().toString();
        }
    }

    public void insertPropertyFeatures(String PropertyID, String FeatureID, String Value){
        ContentValues valores = new ContentValues();
        valores.put("_id", PropertyID);
        valores.put("FeatureID", FeatureID);
        valores.put("Value", Value);
        try{
            this.getReadableDatabase().insert("TBL_PropertyFeatures", null, valores);
        }
        catch (Exception e){
            String error =  e.getMessage().toString();
        }
    }

    public void insertFormerDamageAndRisk(String PropertyID, String DataType, String ConceptID){
        ContentValues valores = new ContentValues();
        valores.put("_id", PropertyID);
        valores.put("DataType", DataType);
        valores.put("ConceptID", ConceptID);
        try{
            this.getReadableDatabase().insert("TBL_FormerDamageAndRisk", null, valores);
        }
        catch (Exception e){
            String error =  e.getMessage().toString();
        }
    }

    public void insertAnnualAmount(String PropertyID, String Year, String Amount, String Description){
        ContentValues valores = new ContentValues();
        valores.put("_id", PropertyID);
        valores.put("Year", Year);
        valores.put("Amount", Amount);
        valores.put("Description", Description);
        try{
            this.getReadableDatabase().insert("TBL_AnnualAmount", null, valores);
        }
        catch (Exception e){
            String error =  e.getMessage().toString();
        }
    }

    public void actualizarProperty(String p_propertyID,
                                   String p_propertyNumber,
                                   String p_propertyUse,
                                   String p_name,
                                   String p_address,
                                   int p_lock,
                                   int p_assigned,
                                   int p_finding,
                                   int p_status,
                                   String p_registerDate,
                                   String p_registerUser,
                                   String p_deleted,
                                   String p_justification,
                                   String p_propertyParentID,
                                   String p_postalCodeID,
                                   String p_buildingDate,
                                   int p_levels){
        ContentValues actualizaProperty = new ContentValues();
        actualizaProperty.put("PropertyNumber", p_propertyNumber);
        actualizaProperty.put("PropertyUse", p_propertyUse);
        actualizaProperty.put("Name", p_name);
        actualizaProperty.put("Address", p_address);
        actualizaProperty.put("Lock", p_lock);
        actualizaProperty.put("Assigned", p_assigned);
        actualizaProperty.put("Finding", p_finding);
        actualizaProperty.put("Status", p_status);
        actualizaProperty.put("RegisterDate", p_registerDate);
        actualizaProperty.put("RegisterUser", p_registerUser);
        actualizaProperty.put("Deleted", p_deleted);
        actualizaProperty.put("Justification", p_justification);
        actualizaProperty.put("PropertyParentID", p_propertyParentID);
        actualizaProperty.put("PostalCodeID", p_postalCodeID);
        actualizaProperty.put("BuildingDate", p_buildingDate);
        actualizaProperty.put("Levels", p_levels);

        String where = "_id=?";
        String[] whereArgs = new String[] {String.valueOf(p_propertyID)};
        try{
            this.getReadableDatabase().update("TBL_Properties", actualizaProperty, where, whereArgs);
        }
        catch (Exception e){
            String error =  e.getMessage().toString();
        }
    }

    public void actualizarOpenClose(String p_propertyID, int iStatus)
    {
        ContentValues actualizaProperty = new ContentValues();
        actualizaProperty.put("Status", iStatus);
        String where = "_id=?";

        String[] whereArgs = new String[] {String.valueOf(p_propertyID)};
        try{
            this.getReadableDatabase().update("TBL_Properties", actualizaProperty, where, whereArgs);
        }
        catch (Exception e){
            String error =  e.getMessage().toString();
        }
    }

    public List<CatalogItem> getCatalogList(String p_id) {
        Cursor cursor;
        String[] columnas = new String[]{"_id", "CatalogID", "ShortName", "Description"};
        try
        {
            cursor = this.getReadableDatabase().query("TBL_CatalogsContent", columnas, "CatalogID" + "= '" + p_id + "'", null, null, null, "Description");
            //(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit, cancellationSignal)
            // public Cursor query (String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        } catch (Exception e){
            cursor = null;
            Log.d("Error", "El mensaje de error es: " + e.getMessage());
        }

        List<CatalogItem> listaDeCatalogItems = new ArrayList<CatalogItem>();
        CatalogItem catalogItem;

        listaDeCatalogItems.add(new CatalogItem("-1", "Ninguno", "Seleccione opción", 0));
        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                try
                {
                    String id = cursor.getString(0);
                    String parent = cursor.getString(1);
                    String codigo= cursor.getString(2);
                    String nombre = cursor.getString(3);
                    catalogItem = new CatalogItem(id, codigo, nombre, 0);
                    listaDeCatalogItems.add(catalogItem);
                } catch (Exception e){
                    Log.d("Error", "El mensaje de error es: " + e.getMessage());
                }
            } while(cursor.moveToNext());
        }
        return listaDeCatalogItems;
    }

    public List<CatalogItem> getPostalCodesList() {
        int i=0;
        Cursor cursor;
        String[] columnas = new String[]{"_id", "PostalCode", "Township", "TownshipKind", "CityHall"};
        try
        {
            cursor = this.getReadableDatabase().query("TBL_PostalCodes", columnas, null, null, null, null, null);
        } catch (Exception e){
            cursor = null;
            Log.d("Error", "El mensaje de error es: " + e.getMessage());
        }

        List<CatalogItem> listaDeCatalogItems = new ArrayList<CatalogItem>();
        CatalogItem catalogItem;

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                try
                {
                    String id = cursor.getString(0);
                    String parent = cursor.getString(1);
                    String codigo= cursor.getString(3);
                    String nombre = cursor.getString(2);
                    catalogItem = new CatalogItem(id, codigo, nombre, 0);
                    listaDeCatalogItems.add(catalogItem);
                    ++i;
                } catch (Exception e){
                    Log.d("Error", "El mensaje de error es: " + e.getMessage());
                }
            } while(cursor.moveToNext() && i<1000);
        }
        return listaDeCatalogItems;
    }

    public Property getProperty(String p_id) {
        String[] columnas = new String[]{"_id", "PropertyType", "PropertyNumber", "PropertyUse", "Name", "Address", "Lock", "Assigned", "Finding", "Status", "RegisterDate", "RegisterUser", "Deleted", "Justification", "PropertyParentID", "PostalCodeID", "BuildingDate", "Levels"};
        Cursor cursor = this.getReadableDatabase().query("TBL_Properties", columnas, "_id" + "= '" + p_id + "'", null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Property property = new Property(cursor.getString(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                Integer.parseInt(cursor.getString(6)),
                Integer.parseInt(cursor.getString(7)),
                Integer.parseInt(cursor.getString(8)),
                Integer.parseInt(cursor.getString(9)),
                cursor.getString(10),
                cursor.getString(11),
                Integer.parseInt(cursor.getString(12)),
                cursor.getString(13),
                cursor.getString(14),
                cursor.getString(15),
                Integer.parseInt(cursor.getString(16)),
                Integer.parseInt(cursor.getString(17))
        );

        // Retorna la persona especifica.
        return property;
    }

    public void cerrar(){
        this.close();
    }

    public List<CatalogItem> getWeatherList() {
        Cursor cursor;
        String[] columnas = new String[]{"_id", "StationName"};
        try
        {
            cursor = this.getReadableDatabase().query("TBL_WeatherStations", columnas, "_id <> '00000000-0000-0000-0000-000000000000'", null, null, null, "StationName");
        } catch (Exception e){
            cursor = null;
            Log.d("Error", "El mensaje de error es: " + e.getMessage());
        }

        List<CatalogItem> listaDeCatalogItems = new ArrayList<CatalogItem>();
        CatalogItem catalogItem;

        listaDeCatalogItems.add(new CatalogItem("-1", "Ninguno", "Seleccione opción", 0));
        if(cursor != null) {
            if (cursor.getCount() > 0){
                cursor.moveToFirst();
                do {
                    try
                    {
                        String id = cursor.getString(0);
                        String parent = "";
                        String codigo = "";
                        String nombre = cursor.getString(1);
                        catalogItem = new CatalogItem(id, codigo, nombre, 0);
                        listaDeCatalogItems.add(catalogItem);
                    } catch (Exception e){
                        Log.d("Error", "El mensaje de error es: " + e.getMessage());
                    }
                } while(cursor.moveToNext());
            }
        }
        return listaDeCatalogItems;
    }

    public Cursor obtenerPruebas(){
        String[] columnas = new String[]{ "PruebaID", "PruebaType", "Name" };
        Cursor cursor = this.getReadableDatabase().query("Prueba", columnas, null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor obtenerPropiedades(String type){
        String[] columnas = new String[]{"_id", "PropertyType", "PropertyNumber", "PropertyUse", "Name", "Address", "Lock", "Assigned", "Finding", "Status", "RegisterDate", "RegisterUser", "Deleted", "Justification", "PropertyParentID", "PostalCodeID", "BuildingDate", "Levels"};
        String condition;

        if (type.equals("0"))
            condition = "";
        else
            condition = "PropertyType" + "= '" + type + "'";

        if (condition.length()>0)
            condition += "And PropertyParentID" + "='" + Constants.GENERICO + "'";
        else
            condition = "PropertyParentID" + "='" + Constants.GENERICO + "'";

        Cursor cursor = this.getReadableDatabase().query("TBL_Properties", columnas, condition, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor selectPostalCodes(String filter_CityHall, String filter_Township, String filter_PostalCode){

        String[] columnas = new String[]{"_id", "StateID", "PostalCode", "Township", "TownshipKind", "CityHall", "City"};
        String condition = "";

        if (filter_CityHall.equals(""))
            condition = "";
        else
            condition = condition + " And (CityHall" + " like '%" + filter_CityHall + "%')";

        if (filter_Township.equals(""))
            condition = "";
        else
            condition = condition + " And (Township" + " like '%" + filter_Township + "%')";

        if (filter_PostalCode.equals(""))
            condition = "";
        else
            condition = condition + " And (PostalCode" + " like '%" + filter_PostalCode + "%')";

        if (condition.length() > 0)
            if (condition.substring(0, 4).compareTo(" And") == 0)
                condition = condition.substring(5);

        Cursor cursor = this.getReadableDatabase().query("TBL_PostalCodes", columnas, condition, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean eliminaPropierty(String id){
        return this.getReadableDatabase().delete("TBL_Properties", "_id" + "='" + id + "'", null) > 0;
    }

    public boolean eliminaPropierties(String ids)
    {
        if (ids.endsWith(", "))
            ids = ids.substring(0, ids.length() - ", ".length());
        return this.getReadableDatabase().delete("TBL_Properties", "_id" + " IN (" + ids + ")", null) > 0;
    }

    public boolean eliminaPropiertyFeatures(String id){
        return this.getReadableDatabase().delete("TBL_PropertyFeatures", "_id" + "='" + id + "'", null) > 0;
    }

    public boolean eliminaPropiertiesFeatures(String ids){
        if (ids.endsWith(", "))
            ids = ids.substring(0, ids.length() - ", ".length());
        return this.getReadableDatabase().delete("TBL_PropertyFeatures", "_id" + " IN (" + ids + ")", null) > 0;
    }

    public boolean eliminaFormerDamageAndRisk(String id){
        return this.getReadableDatabase().delete("TBL_FormerDamageAndRisk", "_id" + "='" + id + "'", null) > 0;
    }

    public boolean eliminaAnnualAmount(String id){
        return this.getReadableDatabase().delete("TBL_AnnualAmount", "_id" + "='" + id + "'", null) > 0;
    }

    public void ExecuteInsertaPostalCodes(String sQuery){
        try
        {
            this.getReadableDatabase().execSQL(sQuery);
        }
        catch (Exception e)
        {
            String error =  e.getMessage().toString();
        }
    }

    public void insertaUsuario(){
        //db.execSQL("INSERT INTO TBL_Users(_id, QrCode, UserName, Password, Active, Lock, Deleted, UserID, inUse)
        // VALUES('269E6823-F600-4B8C-866C-80CF47FCE591','269E6823-F600-4B8C-866C-80CF47FCE591','Admin','1','0','0','1','0','')");
        ContentValues valores = new ContentValues();
        valores.put("_id", "269E6823-F600-4B8C-866C-80CF47FCE555");
        valores.put("QrCode", "269E6823-F600-4B8C-866C-80CF47FCE555");
        valores.put("UserName", "Admin");
        valores.put("Password", "1");
        valores.put("Active", "0");
        valores.put("Lock", "0");
        valores.put("Deleted", "1");
        valores.put("UserID", "0");
        valores.put("inUse", "");
        try{
            this.getWritableDatabase().insert("TBL_Users", null, valores);
        }
        catch (Exception e){
            String error =  e.getMessage().toString();
        }
    }
}
