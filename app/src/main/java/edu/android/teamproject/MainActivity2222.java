package edu.android.teamproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity2222 extends AppCompatActivity {

    private static final String URL_AIRPLANE_INFO =
            "http://openapi.airport.co.kr/service/rest/FlightScheduleList/getIflightScheduleList?"
                    + "ServiceKey="
                    + "8hIeG6ga31T9L%2FYytDvxB49ASzbFxRAF47Jze%2B08Op8cRJBQPHTY8heGRhea%2B6A%2BfKt7NGg2440qf3qCv23d9w%3D%3D"
                    + "&schDate=20170920"
                    + "&schDeptCityCode=GMP"
                    + "&schArrvCityCode=KIX";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("logTag", "onCreate");
        //textView = (TextView) findViewById(R.id.textView);

    }

    @Override
    protected void onStart() {
        super.onStart();
        startNetworkTask();
    }

    public void startNetworkTask() {
        // 사용가능한 네트워크가 있는 지 검사
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isAvailable()) {
            // TODO: AsyncTask를 사용해서 URL 주소(Open API)로 요청을 보내고
            // 응답(지하철 역 주소/전화번호 데이터)을 받음
            Log.i("logTag", "netInfo isAvailable()");

            GetAirplaneInfoTask task = new GetAirplaneInfoTask();
            task.execute(URL_AIRPLANE_INFO);
        }

    }

    public class GetAirplaneInfoTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Log.i("logTag", "doInBackground()");

            URL url = null;
            HttpURLConnection conn = null;
            InputStream in = null;
            InputStreamReader reader = null;
            BufferedReader br = null;
            StringBuffer buffer = new StringBuffer();
            try {
                url = new URL(params[0]);
                conn = (HttpURLConnection) url.openConnection();

                conn.setConnectTimeout(5 * 1000);
                conn.setReadTimeout(5 * 1000);
                conn.setRequestMethod("GET"); // 생략 불가!

                int resCode = conn.getResponseCode();
                if (resCode == HttpURLConnection.HTTP_OK) {
                    Log.i("logTag", "HttpURLConnction OK");


                    in = conn.getInputStream();
                    reader = new InputStreamReader(in);
                    br = new BufferedReader(reader);

                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }

                        buffer.append(line);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("logTag", "onPostExecute()");
            Log.i("logTag", s);

            try {
                AirplaneInfoXmlParser parser = new AirplaneInfoXmlParser();
                List<AirplaneInfo> list = parser.parse(s);
                Log.i("logTag", "list size : " + list.size());
                StringBuffer buffer = new StringBuffer();
                for (AirplaneInfo airplaneInfo : list) {
                    Log.i("logTag", "airplaneInfo : \n" + airplaneInfo.toString());

                    buffer.append(airplaneInfo.toString())
                            .append("\n\n");
                }
                textView.setText(buffer);
            } catch (Exception e) {
                Log.e("logTag", e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
