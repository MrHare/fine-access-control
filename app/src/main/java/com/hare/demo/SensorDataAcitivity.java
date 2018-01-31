package com.hare.demo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhouanyuan on 26/12/2017.
 */

public class SensorDataAcitivity extends AppCompatActivity {
    SensorManager sensorManager;
    List<Sensor> sensorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_data);
        TextView editText = (TextView) findViewById(R.id.sensorData_editText);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        /**String result = "";
        for(int i = 1; i < 18; i ++){
            sensorList = sensorManager.getSensorList(i);
            result += "\n"+sensorList.toString();
        }
        editText.setText(sensorManager.getSensorList(Sensor.TYPE_ALL).toString());
        //editText.setText(result);
         **/
        Sensor sensor = sensorManager.getDefaultSensor(6);

        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        }, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
}
