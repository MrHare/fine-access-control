package com.hare.demo;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class AddPolicyActivity extends AppCompatActivity {
    private String[] sensorList;
    private String[][] detailList;
    private List<String> modeTypeList;

    private ExpandableListView expandableListView;
    private EditText scene;
    private EditText startTime;
    private EditText endTime;

    private DatePicker datePicker;
    private TimePicker timePicker;

    private Button submitTime;
    private Spinner modeTypeSpinner;
    private ArrayAdapter<String> modeTypeAdapter;
    private Dialog dialog;

    private String time;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpolicy);
        initData();
        scene = (EditText)findViewById(R.id.scene_name);
        modeTypeSpinner = (Spinner)findViewById(R.id.modeType_spinner);
        startTime = (EditText)findViewById(R.id.start_time);
        endTime = (EditText)findViewById(R.id.end_time);

        modeTypeSpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, modeTypeList));
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTime(0);
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickTime(1);

            }
        });

        expandableListView = (ExpandableListView)findViewById(R.id.expand_list);
        expandableListView.setAdapter(new MyExpandableListAdapter());

    }

    public void pickTime(final int flag){
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddPolicyActivity.this);
        View view = this.getLayoutInflater().inflate(R.layout.dialog_timepicker,null);
        builder.setView(view);


        datePicker = (DatePicker)view.findViewById(R.id.datePicker);
        timePicker = (TimePicker)view.findViewById(R.id.timePicker);
        submitTime = (Button)view.findViewById(R.id.submit_time);
        timePicker.setIs24HourView(true);
        submitTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time = ""+datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth()+
                        "    "+timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute();
                if(flag == 0){
                    startTime.setText(time);
                }else{
                    endTime.setText(time);
                }
                dialog.dismiss();
            }
        });
        dialog = builder.show();


    }

    public void initData(){
        sensorList = new String[]{"      Wifi", "      Audio", "      Location", "      Camera", "      Sensor", "      BlueTooth"};
        detailList = new String[][]{{"state", "attribute", "control"},
                                    {"audio", "mic", "volume"},
                                    {"fine-grained", "coarse-grained"},
                                    {"photograph", "recording","preview"},
                                    {"ACCELEROMETER", "MAGNETIC_FIELD","ORIENTATION","GYROSCOPE","LIGHT","PRESSURE","TEMPERATURE","PROXIMITY","GRAVITY","LINEAR_ACCELERATION","ROTATION_VECTOR","RELATIVE_HUMIDITY","AMBIENT_TEMPERATURE"},
                                    {"state", "attribute", "control"}
                                    };
        modeTypeList = new ArrayList<String>();
        modeTypeList.add("Conference Mode");
        modeTypeList.add("Call Mode");
        modeTypeList.add("Payment Mode");
        modeTypeList.add("Work Mode");
        modeTypeList.add("Privacy Mode");
        modeTypeList.add("Others");
    }

    private class MyExpandableListAdapter extends BaseExpandableListAdapter{


        @Override
        public int getGroupCount() {
            return sensorList.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return detailList[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return sensorList[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return detailList[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(AddPolicyActivity.this).inflate(R.layout.item_sensorlist, parent, false);
                groupViewHolder = new GroupViewHolder();
                groupViewHolder.sensor = (TextView) convertView.findViewById(R.id.sensor_name);
                groupViewHolder.sensorCheck = (CheckBox) convertView.findViewById(R.id.sensor_check);
                groupViewHolder.sensorCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i = groupPosition;
                    }
                });
                convertView.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) convertView.getTag();
            }
            groupViewHolder.sensor.setText(sensorList[groupPosition]);
            return convertView;
        }
        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder;
            if(convertView == null){
                convertView = LayoutInflater.from(AddPolicyActivity.this).inflate(R.layout.item_detaillist,parent,false);
                childViewHolder = new ChildViewHolder();
                childViewHolder.detail = (TextView)convertView.findViewById(R.id.detail_name);
                convertView.setTag(childViewHolder);
            }else {
                childViewHolder = (ChildViewHolder)convertView.getTag();
            }
            childViewHolder.detail.setText(detailList[groupPosition][childPosition]);
            return convertView;
        }
        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        class GroupViewHolder{
            TextView sensor;
            CheckBox sensorCheck;
        }

        class ChildViewHolder{
            TextView detail;
        }
    }
}

