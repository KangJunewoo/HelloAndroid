package com.example.hello;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hello.data.MovieInfo;
import com.example.hello.data.MovieList;
import com.example.hello.data.ResponseInfo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class IndexActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    int i=0;
    ViewPager pager;
    // 아싸리 전역으로 선언해버리기..?
    public static ArrayList movies = new ArrayList<ArrayList<String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        // 네트워크
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMovieList();
            }
        });


        requestMovieList2();
        Toast.makeText(getApplicationContext(), movies.get(0).toString(), Toast.LENGTH_LONG).show();

        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        // 네트워크 끝
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(5);

        MoviePagerAdapter adapter = new MoviePagerAdapter(getSupportFragmentManager());

        FragmentMovie1 f1 = new FragmentMovie1();
        Bundle bundle = new Bundle();
        //bundle.putString("title", movies.get(0).toString());
        f1.setArguments(bundle);
        adapter.addItem(f1);
        FragmentMovie2 f2 = new FragmentMovie2();
        adapter.addItem(f2);
        FragmentMovie3 f3 = new FragmentMovie3();
        adapter.addItem(f3);
        FragmentMovie4 f4 = new FragmentMovie4();
        adapter.addItem(f4);
        FragmentMovie5 f5 = new FragmentMovie5();
        adapter.addItem(f5);


        pager.setAdapter(adapter);
    }

    class MoviePagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MoviePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            items.add(item);
        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int curId = item.getItemId();
        if(curId == R.id.menu_hamburger){
            // TODO : 슈루룩 보이는 코드 필요
        }

        return super.onOptionsItemSelected(item);
    }

    public void requestMovieList(){
        // url 정의하고
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";

        // 쿼리스트링 달고
        url += "?" + "type=1";

        // 리퀘스트 만듦. 오.. 어렵지 않네.
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        println("응답 받음!");

                        processResponse(response);
                    }
                },

                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        println("에러 발생 -> " + error.getMessage());
                    }
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        println("영화목록 요청 보냄.");
    }

    public void requestMovieList2(){
        // url 정의하고
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";

        // 쿼리스트링 달고
        url += "?" + "type=1";

        // 리퀘스트 만듦. 오.. 어렵지 않네.
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        processResponse2(response);
                    }
                },

                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        println("에러 발생 -> " + error.getMessage());
                    }
                }
        );
        // 이 부분은 무엇일까
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
    }

    // 이거 원래 콘솔에 띄우는거 아니었나..? @Override 안달아도 되나?
    public void println(String data){
        textView.append(data + "\n");
    }

    public void processResponse(String response){
        // response는 json으로 받아 gson으로 변환.
        Gson gson = new Gson();

        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);
        if(info.code == 200){
            MovieList movieList = gson.fromJson(response, MovieList.class);
            println("영화 개수 : " + movieList.result.size());

            for(int i=0;i<movieList.result.size();i++){
                MovieInfo movieInfo = movieList.result.get(i);
                println("영화 #" + i + " -> " + movieInfo.id + ", " + movieInfo.title + "; " + movieInfo.grade);
            }
        }
    }

    public void processResponse2(String response){
        // response는 json으로 받아 gson으로 변환.
        Gson gson = new Gson();

        // info엔 code, message, resultType이 저장됨.
        ResponseInfo info = gson.fromJson(response, ResponseInfo.class);

        // 정상코드면 ㄱㄱ
        if(info.code == 200){
            // movieList.result로 영화정보 접근 가능.
            // 여기서 title, image, reservation_rate, grade, date를 빼야함.
            MovieList movieList = gson.fromJson(response, MovieList.class);
            for(int i=0;i<movieList.result.size();i++){
                ArrayList movie = new ArrayList<String>();
                MovieInfo movieInfo = movieList.result.get(i);
                movie.add(movieInfo.title);
                movie.add(movieInfo.image);
                movie.add(movieInfo.reservation_rate);
                movie.add(movieInfo.grade);
                movie.add(movieInfo.date);
                movies.add(movie);
                // println("영화 #" + i + " -> " + movieInfo.id + ", " + movieInfo.title + "; " + movieInfo.grade);
            }
        }
    }

    public void applyInfo(MovieInfo movieInfo){

    }
}