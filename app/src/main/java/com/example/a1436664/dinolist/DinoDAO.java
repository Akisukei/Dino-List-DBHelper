package com.example.a1436664.dinolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 1436664 on 10/26/2016.
 */
public class DinoDAO extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "dinos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Dinos";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_INFO = "Info";
    public static final String COLUMN_IMGID = "ImageId";
    public static final String COLUMN_ICONID = "IconId";

    private static final String DATABASE_CREATE =
            "CREATE TABLE "+TABLE_NAME+" ("
            + COLUMN_ID     +" INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME   +" TEXT, "
            + COLUMN_INFO   +" TEXT, "
            + COLUMN_IMGID  +" INTEGER, "
            + COLUMN_ICONID +" INTEGER "
            + ");";

    private static DinoDAO dao = null;
    private Context context;

    private DinoDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DinoDAO getDinoDao(Context context) {

        if(dao == null) {
            dao = new DinoDAO(context.getApplicationContext());
        }
        return dao;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createPopulateDB(db);
        populateDB(db);
    }

    public void createPopulateDB(SQLiteDatabase db) {

        try {
            db.execSQL(DATABASE_CREATE);
        }
        catch(SQLException e) {
            Log.e(DinoDAO.class.getName(), "Create Exception"+ Log.getStackTraceString(e));
            throw e;
        }
    }

    public void populateDB(SQLiteDatabase db) {

        String[] dinoNames = context.getResources().getStringArray(R.array.dino_names);
        int[] dinoIconIds = new int[] {R.drawable.brachiosaurus_icon,
                R.drawable.carnotaurus_icon,
                R.drawable.giganotosaurus_icon,
                R.drawable.pentaceratops_icon,
                R.drawable.parasaurolophus_icon,
                R.drawable.rajasaurus_icon,
                R.drawable.styracosaurus_icon,
                R.drawable.spinosaurus_icon,
                R.drawable.minmi_icon,
                R.drawable.deinonychus_icon};
        String[] descriptions = context.getResources().getStringArray(R.array.dino_info);
        int[] dinoImageIds = new int[] {R.drawable.brachiosaurus,
                R.drawable.carnotaurus,
                R.drawable.giganotosaurus,
                R.drawable.pentaceratops,
                R.drawable.parasaurolophus,
                R.drawable.rajasaurus,
                R.drawable.styracosaurus,
                R.drawable.spinosaurus,
                R.drawable.minmi,
                R.drawable.deinonychus};

        for(int i=0; i < 10; i++)
            insertNewDino(dinoNames[i], descriptions[i], dinoImageIds[i], dinoIconIds[i]);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(DinoDAO.class.getName(), "Upgrading database: from " + oldVersion +
                                                            "to " + newVersion +
                                            " (All old data will be destroyed)");
        try {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }
        catch (SQLException e) {
            Log.e(DinoDAO.class.getName(), "Drop Exception"+Log.getStackTraceString(e));
            throw e;
        }
        createPopulateDB(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.i(DinoDAO.class.getName(), "onOpen()");
    }

    public long insertNewDino(String name, String info, int imgId, int iconId) {

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_INFO, info);
        cv.put(COLUMN_IMGID, imgId);
        cv.put(COLUMN_ICONID, iconId);

        long code = getWritableDatabase().insert(TABLE_NAME, null, cv);
        return code;
    }

    public int deleteDino(int id) {

        int rowsAffected = 0;
        rowsAffected = getWritableDatabase().delete(TABLE_NAME, COLUMN_ID + "=?",
                                                    new String[] {String.valueOf(id)} );
        return rowsAffected;
    }

    public Cursor getDinos() {

        return getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
    }
}
