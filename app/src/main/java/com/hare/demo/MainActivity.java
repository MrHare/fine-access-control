package com.hare.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ListView sceneList;
    List<String> sceneNameList;
    Button addSceneButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sceneList = (ListView)findViewById(R.id.scene_listView);
        addSceneButton = (Button)findViewById(R.id.addScene_button);

        initData();
        sceneList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,sceneNameList));
        sceneList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,AddPolicyActivity.class);
                startActivity(intent);
            }
        });
        sceneList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                return false;
            }
        });
        addSceneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = MainActivity.this.getLayoutInflater().inflate(R.layout.dialog_scene_choose,null);
                builder.setView(view);
                builder.show();

                Button button = (Button)view.findViewById(R.id.scene_choose_ok_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this,AddPolicyActivity.class);
                        startActivity(intent);

                    }
                });
            }
        });
    }
    public void initData(){
        sceneNameList = new ArrayList<String>();
        sceneNameList.add("Scene1");
        sceneNameList.add("Scene2");
        sceneNameList.add("Scene3");
    }
}
