package ru.akolupaev.listviewfile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST = 100;
    private List<State> states = new ArrayList();
    ListView countriesList;
    StateAdapter stateAdapter ;
    Context context = MainActivity.this;
    String path = "";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(this, SecondActivity.class);
//        intent = new Intent(this, FullscreenActivity.class);

        // получаем элемент ListView
        countriesList = (ListView) findViewById(R.id.countriesList);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                State selectedState = (State)parent.getItemAtPosition(position);
                if (selectedState.getCapital().equals("Папка")){
                    path = selectedState.getFullPath();
                    states.clear();
                    setInitialData2();
                }else {
                    intent.putExtra("File", selectedState.getFullPath());
                    startActivity(intent);
                    //Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedState.getFullPath(),Toast.LENGTH_SHORT).show();
                }
            }
        };
        countriesList.setOnItemClickListener(itemListener);

        path = Environment.getExternalStorageDirectory().toString()+"/DCIM";

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // начальная инициализация списка
                        setInitialData2();
                    }
                },
        300);
    }
    /*
    private void setInitialData(){

        states.add(new State ("Бразилия", "Бразилиа", R.drawable.brazilia, nameFile));
        states.add(new State ("Аргентина", "Буэнос-Айрес", R.drawable.argentina, nameFile));
        states.add(new State ("Колумбия", "Богота", R.drawable.colombia, nameFile));
        states.add(new State ("Уругвай", "Монтевидео", R.drawable.uruguai, nameFile));
        states.add(new State ("Чили", "Сантьяго", R.drawable.chile, nameFile));
    }
    */
    private void setInitialData2(){
        requestStoregePermission();


        Log.d("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files == null){
            Toast.makeText(this, "Объект files=null: Наверное прав не дали", Toast.LENGTH_LONG).show();
            return;
        }
        Log.d("Files", "Size: "+ files.length);
        String pathUp = path.substring(0,path.lastIndexOf("/"));
        states.add(new State (pathUp, "Папка", R.drawable.chile, null, pathUp));
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory()) {
                String name = files[i].getName();
                String country = "Папка";
                    states.add(new State (name, country, R.drawable.papka, null, files[i].getAbsolutePath()));
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isFile()) {
                String name = files[i].getName();
                String country = sdf.format(files[i].lastModified());
                String fullPath = files[i].getAbsolutePath();
                if (name!=null && country!=null && name.endsWith(".jpg")){
                    states.add(new State (name, country, R.drawable.brazilia, files[i], fullPath));
                }else{
                    states.add(new State (name, country, R.drawable.brazilia, null, fullPath));
                }
            }
        }
        // создаем адаптер
        stateAdapter = new StateAdapter(context, R.layout.list_item, states);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);
        //Toast.makeText(this, "getFirstVisiblePosition: " + countriesList.getLastVisiblePosition(), Toast.LENGTH_LONG).show();
   }
    private void requestStoregePermission(){
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST);
        }
    }

}
