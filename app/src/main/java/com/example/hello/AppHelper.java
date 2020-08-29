package com.example.hello;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.android.volley.RequestQueue;

public class AppHelper {
    private static final String TAG = "AppHelper";

    private static SQLiteDatabase database;

    // 각각의 경우에 맞게 sql문 정의하자.
    private static String createTableOutlineSql = "create table if not exists outline" +
            "(" +
            "    _id integer PRIMARY KEY autoincrement, " +
            "    id integer, " +
            "    title text, " +
            "    title_eng text, " +
            "    dateValue text, " +
            "    user_rating float, " +
            "    audience_rating float, " +
            "    reviewer_rating float, " +
            "    reservation_rate float, " +
            "    reservation_grade integer, " +
            "    grade integer, " +
            "    thumb text, " +
            "    image text" +
            ")";


    public static void openDatabase(Context context, String databaseName){
        println("openDatabase 호출됨.");

        try{
            database = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
            if(database != null){
                println("데이터베이스 " + databaseName + " 오픈됨.");
            }
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void createTable(String tableName){
        println("createTable 호출됨" + tableName);

        if(database != null){
            if(tableName.equals("outline")){
                database.execSQL(createTableOutlineSql);
                println("outline 테이블 생성 요청됨.");
            }
        } else{
            println("데이터베이스를 먼저 오픈하세요.");
        }
    }

    public static void println(String data){
        Log.d(TAG, data);
    }
    // 네트워크 시작
    // 어디서든 접근 가능하게! public static 선언
    // 어댑터도 비슷하게 하면 되지 않을까?
    public static RequestQueue requestQueue;

    // 호스트랑 포트
    public static String host = "boostcourse-appapi.connect.or.kr";
    public static int port = 10000;
    // 네트워크 끝
}
