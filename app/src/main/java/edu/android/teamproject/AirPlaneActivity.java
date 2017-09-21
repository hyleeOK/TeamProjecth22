package edu.android.teamproject;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AirPlaneActivity extends AppCompatActivity implements
        DataFragment.DataSelectListener , DataEndFragment.DataSelectListenerTwo{

    public static final String TAG =  "Main_index";
    private int viewHolderCount; // 카운터 수
    private TextView textview_DataStart;
    private TextView textview_DataEnd;
    private RecyclerView recycler;
    private AirPlaneAdapter adapter;
    private Button btn_next;


    private AirplaneScheduleLab lab = AirplaneScheduleLab.getInstance();
    private List<AirplaneSchedule> airplaneSchedules;

    // 출발날짜를 불러오는 맴버변수
    // 도착날짜를 불러오는 맴버변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airplane);

        textview_DataStart = (TextView) findViewById(R.id.textView_DataStart); // 출발날짜 텍스트 뷰를 찾음
        textview_DataEnd = (TextView) findViewById(R.id.textView_DataEnd);  //  도착날짜 텍스트뷰를 찾음
        btn_next = (Button) findViewById(R.id.btn_next);



        textview_DataStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataFragment dlg= new DataFragment();
                dlg.show(getSupportFragmentManager(), "data_dlg");

            }
        });

        textview_DataEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataEndFragment dlg = new DataEndFragment();
                dlg.show(getSupportFragmentManager(), "data_dlg2");

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





            }
        });




        // 1. RecyLerView를 찾음
        recycler = (RecyclerView) findViewById(R.id.recyclerView);

        // 2. RecyclerView에 LayoutManger 를 설정
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // 3. RecyclerView에 Adapter 를 설정
        airplaneSchedules = new ArrayList<>();
        adapter = new AirPlaneAdapter();
        recycler.setAdapter(adapter);




    }


    public class AirPlaneAdapter extends RecyclerView.Adapter<AirPlaneViewHolder>{

        @Override
        public AirPlaneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            viewHolderCount++;

            // 레이아웃을 만들수 있는 LayoutInflater 객체를 찾음
            Log.i(TAG, "viewHolderCount:" + viewHolderCount);
            LayoutInflater inflater = LayoutInflater.from(AirPlaneActivity.this);
            // Adapter 안에
            // AirPlaneActivity 주소가 와야함

            // 아이템 하나를 보여주는 View 객체를 생성
            View view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            // 아이템 하나의 View 를 재활용 하는 ViewHolder 객체를 생성
            AirPlaneViewHolder viewHoler = new AirPlaneViewHolder(view);

            // 생성된 ViewHolder 객체를 리턴
            return viewHoler;

        }

        @Override
        public void onBindViewHolder(AirPlaneViewHolder holder, int position) {

                // 불리는 메소드 (onBindViewHolder)

                // 재활용 틀 ViewHolder에 아이템의 내용을 채워줄 때 호출
            holder.textView.setText(airplaneSchedules.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return airplaneSchedules.size();
        }


    }

    public class AirPlaneViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private int index;

        public void setIndex(int index){
            this.index = index;
        }

        public AirPlaneViewHolder(View itemView) {
            super(itemView);

            textView =(TextView) itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(AirPlaneActivity.this, textView.toString(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }




    @Override
    public void dataSelected(int year, int month, int dayOfMonth) {
        String  text = year + "." + (month+1) + "." + dayOfMonth;
        textview_DataStart.setText(text);

        Calendar cal = new GregorianCalendar(year, month, dayOfMonth);
        Date searchDepartTime = cal.getTime();
        airplaneSchedules = lab.getListByDepartDate(searchDepartTime);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void dateSelectedTwo(int yearTwo, int monthTwo, int dayOfMonthTwo) {
        String text =  yearTwo + "." + (monthTwo+1) + "." + dayOfMonthTwo;
        textview_DataEnd.setText(text);


        Calendar cal = new GregorianCalendar(yearTwo, monthTwo, dayOfMonthTwo);
        Date searchDepartTime = cal.getTime();
        airplaneSchedules = lab.getListByDepartDate(searchDepartTime);

        adapter.notifyDataSetChanged();
    }



}
