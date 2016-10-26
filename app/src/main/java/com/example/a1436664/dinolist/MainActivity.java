package com.example.a1436664.dinolist;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private String[] dinoNames;
    private int[] dinoIconIds;

    private static DinoDAO dao;
    private Cursor cursor;
    private SimpleCursorAdapter sca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] columns = {DinoDAO.COLUMN_NAME, DinoDAO.COLUMN_INFO, DinoDAO.COLUMN_ICONID};
        int[] ids = {R.id.listTextView, R.id.descriptionText, R.id.listImageView};

        setContentView(R.layout.activity_main);

        dao = DinoDAO.getDinoDao(this);

//        dinoNames = getResources().getStringArray(R.array.dino_names);
//        dinoIconIds = new int[] {R.drawable.brachiosaurus_icon,
//                        R.drawable.carnotaurus_icon,
//                        R.drawable.giganotosaurus_icon,
//                        R.drawable.pentaceratops_icon,
//                        R.drawable.parasaurolophus_icon,
//                        R.drawable.rajasaurus_icon,
//                        R.drawable.styracosaurus_icon,
//                        R.drawable.spinosaurus_icon,
//                        R.drawable.minmi_icon,
//                        R.drawable.deinonychus_icon};

        lv = (ListView) findViewById(R.id.listView);
        cursor = dao.getDinos();
        sca = new SimpleCursorAdapter(this, R.layout.activity_main, cursor,
                                                            columns, ids, 0);
        lv.setAdapter(sca);
        //lv.setOnItemClickListener(deleteThis);

        //lv.setAdapter(new CustomAdapter(this, dinoIconIds, dinoNames));
    }

    public void refreshView() {
        Log.d("DBCURSOR", "refreshView()");
        // renew the cursor
        cursor = dao.getDinos();
        // have the adapter use the new cursor, changeCursor closes old cursor too
        sca.changeCursor(cursor);
        // have the adapter tell the observers
        sca.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();
        Log.d("DBCURSOR", "onResume()");
        refreshView();
    }

    public void onPause() {
        super.onPause();
        Log.d("DBCURSOR", "onPause()");
        //release resources
        dao.close();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//
//        Intent addDino = new Intent(this, AddDino.class);
//        Intent deleteDino = new Intent(this, DeleteDino.class);
//
//        menu.add("Add Dino").setIntent(addDino).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//        menu.add("Delete Dino").setIntent(deleteDino).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//
//        return true;
//    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
    }*/
}
