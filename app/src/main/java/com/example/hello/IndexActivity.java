package com.example.hello;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class IndexActivity extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(5);

        MoviePagerAdapter adapter = new MoviePagerAdapter(getSupportFragmentManager());

        FragmentMovie1 f1  = new FragmentMovie1();
        adapter.addItem(f1);
        FragmentMovie2 f2  = new FragmentMovie2();
        adapter.addItem(f2);
        FragmentMovie3 f3  = new FragmentMovie3();
        adapter.addItem(f3);
        FragmentMovie4 f4  = new FragmentMovie4();
        adapter.addItem(f4);
        FragmentMovie5 f5  = new FragmentMovie5();
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
}