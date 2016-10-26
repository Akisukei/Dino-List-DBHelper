package com.example.a1436664.dinolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 1436664 on 10/19/2016.
 */
public class DescriptionActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView iv;
    private String[] descriptions;
    private int[] dinoImageIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.description_activity);

        descriptions = getResources().getStringArray(R.array.dino_info);
        dinoImageIds = new int[] {R.drawable.brachiosaurus,
                R.drawable.carnotaurus,
                R.drawable.giganotosaurus,
                R.drawable.pentaceratops,
                R.drawable.parasaurolophus,
                R.drawable.rajasaurus,
                R.drawable.styracosaurus,
                R.drawable.spinosaurus,
                R.drawable.minmi,
                R.drawable.deinonychus};
        Intent i = getIntent();

        tv = (TextView) findViewById(R.id.descriptionText);
        tv.setText(descriptions[i.getIntExtra("position", 0)]);

        iv = (ImageView) findViewById(R.id.largeImg);
        iv.setImageResource(dinoImageIds[i.getIntExtra("position", 0)]);
    }
}
