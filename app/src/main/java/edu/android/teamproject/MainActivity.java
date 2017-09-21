package edu.android.teamproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements VehicleFragment.VehicleFragmentListener {


    private static final String KEY_EXTRA_AIR = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // VehicleFragment 를 자바코드 동적으로 끼워넣음
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.vehicleFragment);

        if(fragment == null){
            fragment = new VehicleFragment();

            FragmentTransaction transaction = fm.beginTransaction();

            transaction.add(R.id.vehicleFragment, fragment);

            transaction.commit();
        }


    }

    @Override
    public void onVehicleClick(int vehicle) {
        switch (vehicle) {
            case 1:

                Toast.makeText(this, "비행기", Toast.LENGTH_SHORT).show();

                Intent intent1 = new Intent(this, AirPlaneActivity.class);

                startActivity(intent1);



                break;

            case 2:
                Toast.makeText(this, "배", Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(this, AirPlaneActivity.class);

                startActivity(intent2);

                break;
        }


    }
}
