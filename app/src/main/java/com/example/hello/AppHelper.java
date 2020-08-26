package com.example.hello;

        import com.android.volley.RequestQueue;

public class AppHelper {
    // 어디서든 접근 가능하게! public static 선언
    // 어댑터도 비슷하게 하면 되지 않을까?
    public static RequestQueue requestQueue;

    // 호스트랑 포트
    public static String host = "boostcourse-appapi.connect.or.kr";
    public static int port = 10000;
}
