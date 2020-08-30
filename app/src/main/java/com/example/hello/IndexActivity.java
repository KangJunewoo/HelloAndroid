package com.example.hello;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    public MovieList movies = new MovieList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        // AppHelper에서 정의해놓은 sql실행함수 호출
        AppHelper.openDatabase(getApplicationContext(), "movie");
        AppHelper.createTable("outline");

        // 네트워크
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestMovieList();
            }
        });

// 왜 안될까.. 그 이유는 추후에 찾자.
// requestMovieList2();

        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        // 네트워크 끝
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(5);

        MoviePagerAdapter adapter = new MoviePagerAdapter(getSupportFragmentManager());
//        ArrayList<Bundle> bundles = new ArrayList<Bundle>();
        Bundle bundle1 = new Bundle();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = new Bundle();
        Bundle bundle4 = new Bundle();
        Bundle bundle5 = new Bundle();
//        bundles.add(bundle1);
//        bundles.add(bundle2);
//        bundles.add(bundle3);
//        bundles.add(bundle4);
//        bundles.add(bundle5);


        FragmentMovie1 f1 = new FragmentMovie1();
        bundle1.putString("title", "꾼");
        bundle1.putString("image", "http://movie.phinf.naver.net/20171107_251/1510033896133nWqxG_JPEG/movie_image.jpg");
        bundle1.putFloat("reservation_rate", 61.69f);
        bundle1.putInt("grade", 15);
        bundle1.putString("date", "2017-11-22");
//        bundle1.putString("title", movies.result.get(0).title);
//        bundle1.putString("image", movies.result.get(0).image);
//        bundle1.putFloat("reservation_rate", movies.result.get(0).reservation_rate);
//        bundle1.putInt("grade", movies.result.get(0).grade);
//        bundle1.putString("date", movies.result.get(0).date);
        // glide 사용해 url 이미지 보여주기 가능. 하지만! 지금은 생략~
        f1.setArguments(bundle1);
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
                        System.out.println("메롱메롱");
                        System.out.println(error.getMessage());
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
            // 여기서 title, image, reservation_rate, grade, date를 추출해야함.
            MovieList movieList = gson.fromJson(response, MovieList.class);
            movies = movieList;
        }
    }
}