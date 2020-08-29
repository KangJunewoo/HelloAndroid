package com.example.hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FragmentCallback {
    RatingBar ratingBar;
    TextView outputView;
    TextView likeCountView;
    TextView dislikeCountView;
    Button likeButton;
    Button dislikeButton;
    Button yemaeButton;
    Button facebookButton;
    Button kakaoButton;
    Button moduButton;
    Button jakseongButton;
    CommentAdapter adapter;
    ListView listView;
    Fragment1 fragment1;
    Button button;

    int likeCount = 34;
    int dislikeCount = 12;
    boolean likeState = false;
    boolean dislikeState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // 인플레이션 과정, 이후 findViewById 사용 가능.


        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(fragment1 != null){
                    fragment1.onCommandFromActivity("show", "액티비티로부터 전달됨");
                }
            }
        });
        fragment1 = new Fragment1();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment1).commit();

        jakseongButton = (Button) findViewById(R.id.jakseongButton);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        outputView = (TextView) findViewById(R.id.outputView);
        jakseongButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                float rating = ratingBar.getRating();

                // CommentWriteActivity를 띄워주는 인텐트인가보다. putExtra()로 부가데이터 넣어주고
                // startActivityForResult로 액티비티 시작!
                Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
                intent.putExtra("rating", rating);
                startActivityForResult(intent, 101);
            }
        });

        // Project B
        likeButton = (Button) findViewById(R.id.likeButton);
        dislikeButton = (Button) findViewById(R.id.dislikeButton);
        yemaeButton = (Button) findViewById(R.id.yemaeButton);
        moduButton = (Button) findViewById(R.id.moduButton);
        facebookButton = (Button) findViewById(R.id.facebookButton);
        kakaoButton = (Button) findViewById(R.id.kakaoButton);


        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onclick시 4가지 경우
                // 좋o싫x : 좋x
                // 좋x싫o : 좋o싫x
                // 좋x싫x : 좋o
                if(likeState){
                    decrLikeCount();
                } else{
                    incrLikeCount();
                    if(dislikeState){
                        decrDislikeCount();
                        dislikeState = false;
                    }
                }

                likeState = !likeState;
            }
        });

        dislikeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(dislikeState){
                    decrDislikeCount();
                } else{
                    incrDislikeCount();
                    if(likeState){
                        decrLikeCount();
                        likeState = false;
                    }
                }

                dislikeState = !dislikeState;
            }
        });

        yemaeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onYemaeButtonClicked(v);
            }
        });

        moduButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onModuButtonClicked(v);
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onFacebookButtonClicked(v);
            }
        });

        kakaoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onKakaoButtonClicked(v);
            }
        });

        likeCountView = (TextView) findViewById(R.id.likeCountView);
        dislikeCountView = (TextView) findViewById(R.id.dislikeCountView);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new CommentAdapter();

        adapter.addItem(new CommentItem("brianjune", 20, (float) 8.9, "넘나재밌어요", 4, R.drawable.user1));
        adapter.addItem(new CommentItem("junewoo98", 10, (float) 2.3, "형편없어요 ㅜㅜ", 5, R.drawable.user1));

        listView.setAdapter(adapter);

    }


    public void onCommand(String command, String data){
        button.setText(data);
    }


    // CommentWriteActivity의 result, 즉 그 액티비티가 끝날때 하는것들 말하는가보다.
    // 여기선 contents 입력한값대로 설정해주기.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101){
            if(intent != null){
                String contents = intent.getStringExtra("contents");
                float rating = intent.getFloatExtra("rating", 0.0f);
                outputView.setText(String.valueOf(rating) + " & " + contents);
                adapter.addItem(new CommentItem("test", 1, rating, contents, 0, R.drawable.user1));
            }
        }

    }

    public void onModuButtonClicked(View v){
        // CommentWriteActivity를 띄워주는 인텐트인가보다. putExtra()로 부가데이터 넣어주고
        // startActivityForResult로 액티비티 시작!
        Intent intent = new Intent(getApplicationContext(), ModuBogiActivity.class);
        startActivity(intent);
    }

    // Project B
    class CommentAdapter extends BaseAdapter {
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount(){
            return items.size();
        }

        public void addItem(CommentItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position){
            return items.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup){
            CommentItemView view = new CommentItemView(getApplicationContext());
            CommentItem item = items.get(position);
            view.setId(item.getId());
            view.setImage(item.getResId());
            view.setRatingBar(item.getRating());
            view.setContents(item.getContents());
            view.setLikes(item.getLikes());
            view.setMinutes(item.getMinutes());

            return view;
        }
    }

    public void incrLikeCount(){
        likeCount += 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up_selected);

    }

    public void decrLikeCount(){
        likeCount -= 1;
        likeCountView.setText(String.valueOf(likeCount));

        likeButton.setBackgroundResource(R.drawable.ic_thumb_up);
    }

    public void incrDislikeCount(){
        dislikeCount += 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down_selected);
    }

    public void decrDislikeCount(){
        dislikeCount -= 1;
        dislikeCountView.setText(String.valueOf(dislikeCount));

        dislikeButton.setBackgroundResource(R.drawable.ic_thumb_down);
    }

    public void onYemaeButtonClicked(View v){
        Toast.makeText(getApplicationContext(), "예매버튼이 눌렸어요.", Toast.LENGTH_LONG).show();
    }

    public void onFacebookButtonClicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.facebook.com"));
        startActivity(intent);
    }

    public void onKakaoButtonClicked(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.kakao.com"));
        startActivity(intent);
    }
}

