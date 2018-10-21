package ru.akolupaev.listviewfile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);
        getSupportActionBar().hide();
        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("File").toString();
        ImageView imgView = findViewById(R.id.fullscreen_content);
        Glide.with(this).load(name).into(imgView);
    }
}
