package com.example.mytranslator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.LinearLayout;

import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.Gravity;

import com.example.mytranslator.ui.favourite.FavouriteFragment;
import com.example.mytranslator.ui.history.HistoryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//library for listview
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.guna.ocrlibrary.OCRCapture;

import static com.guna.ocrlibrary.OcrCaptureActivity.TextBlockObject;
import com.google.android.gms.common.api.CommonStatusCodes;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    ListView lvw;
    String val;
    String[] lstitle;
    ArrayAdapter<String> adapter;
    Button top10;
    ImageButton take_photo,type_text;
    private TextView textView;
    private final int CAMERA_SCAN_TEXT = 0;
    private final int LOAD_IMAGE_RESULTS = 1;

    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //display the logo during 5 seconds,
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                //set the new Content of your activity
//                MainActivity.this.setContentView(R.layout.home);
                home();
            }
        }.start();
    }

    protected void home(){
        MainActivity.this.setContentView(R.layout.home);
        BottomNavigationView nav = findViewById(R.id.nav_view);
        nav.setOnNavigationItemSelectedListener(this);

        take_photo = (ImageButton) findViewById(R.id.take_photo);

        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog alertDialog=new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Are you sure?");
//                alertDialog.setMessage("This is a three-button dialog!");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,"CANCLE", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"SURE", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent camera_gallery=new Intent(MainActivity.this,OtherActivity.class);
                        startActivity(camera_gallery);
                    }
                });
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,"     CAMERA", new DialogInterface.OnClickListener(){
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent camera=new Intent(MainActivity.this,OtherActivity.class);
//                        startActivity(camera);
//                    }
//                });

                alertDialog.show();

            }
        });

        type_text = (ImageButton) findViewById(R.id.type_text);
        type_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent type=new Intent(MainActivity.this,TypeTextActivity.class);
                startActivity(type);
            }
        });
    }

////    private boolean loadFragment(Fragment fragment){
////        if(fragment!=null){
////            getSupportFragmentManager()
////                    .beginTransaction()
////                    .replace(R.id.fragment_container,fragment)
////                    .commit();
////            return true;
////        }
////        return false;
////    }
//


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//        Fragment fragment=null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_favourite:
//                fragment = new FavouriteFragment();
                MainActivity.this.setContentView(R.layout.fragment_favourite);
                showfav();
                break;

            case R.id.navigation_history:
//                fragment = new HistoryFragment();
//                top10 = findViewById(R.id.top10button);
                MainActivity.this.setContentView(R.layout.fragment_history);
//                top10.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        MainActivity.this.setContentView(R.layout.history_top10);
//                    }
//                });

                break;
            case R.id.navigation_home:
//                fragment = new HistoryFragment();
                MainActivity.this.setContentView(R.layout.home);
                break;

        }
        return true;
    }

    private void showfav(){
        lvw=(ListView)findViewById(R.id.fav_list);
        lstitle=new String[]{
                "Gie","Ploy","Kitty","Hwain","Pin"
        };
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,android.R.id.text1,lstitle);
        lvw.setAdapter(adapter);

        lvw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                val = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(),"Item value is: "+val,Toast.LENGTH_LONG).show();
            }
        });
    }



}

