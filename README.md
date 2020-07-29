# Hello, Android!
### 에드윗 강좌와 함께하는 안드로이드 입문

## 1. 레이아웃 만들기
- 개발환경 세팅
- 다양한 레이아웃(LinearLayout & RelativeLayout 많이 쓰는듯)
- 드로어블 만들기 : 이미지 삽입
- 간단해서 내용은 딱히 정리 안했음.

## 2. 이벤트와 리스트뷰
- TableLayout & ScrollView
- 이벤트 이해하기
  - 버튼 클릭 이벤트
    - 정석 : OnClickListener라는 인터페이스를 구현하는 객체를 만들어 버튼에 등록
    - 워낙 많이 발생하는 이벤트라 버튼 자체 onClick 속성에 메소드를 넣으면 됨.
  - 터치 이벤트 : 누른상태, 누르고 움직이는 상태, 뗀 상태를 각각 이벤트로 전달해줌.
  - 키 이벤트 : 시스템 back 키 많이 사용함. 위 onTouch 메소드와 비슷한 원리.
- 토스트와 대화상자
  - js에서 console.log처럼 디버깅시 확인이 필요할때 안드로이드는 toast 많이 활용.
  - 비슷한 걸로 SnackBar 존재.
  - AlertDialog : 웹에서 confirm창같은건가보다.
  
- 비트맵 버튼 만들기
  - 나인패치 이미지 : 버튼 키울때 자연스럽게 보정. merong.png를 merong.9.png로 저장하면 끝.
  - 비트맵 버튼 : 버튼을 눌러 배경 이미지 바꾸는것 등에 활용.

- 인플레이션 이해하기 : xml - java 매칭
  - 인플레이션 : xml에 정의된 내용이 메모리에 객체화되는 과정
    - setContentView(R.layout.activity_main)이 인플레이션 담당함.
- 리스트뷰 만들기
  - 리사이클러뷰가 리스트뷰보다 많이 쓰인다고 하지만, 기초는 리스트뷰라고 한다.
  - 리스트뷰
    - 원본 데이터 - 어댑터 - 선택위젯
    - 선택위젯으로는 리스트뷰, 스피너, 그리드뷰 등이 있음.
    - 리스트뷰를 통해서 어댑터를 쓰는 패턴을 익히자!
    - 리스트뷰 만들기 steps
      - xml상에서 리스트뷰 정의하고 id 설정.
      - java상에서 id찾아 불러옴.
      - onCreate 바깥에다음과 같이 어댑터 정의
      ```
        class SingerAdapter extends BaseAdapter{
          ArrayList<SingerItem> items = new ArrayList<SingerItem>();

          자동생성 메서드들..
        }
      ```
        - SingerItem.java 새로 만든 뒤
        ```
          public class SingerItem{
            String name;
            String mobile;

            생성자 & get함수 & set함수..
          }
        ```
        식으로 정의.
      - 리스트뷰 정의하기
        - SingerItem에 해당하는 새 xml 만들고 연동해주면 됨.
        ```
        public SingerItemView(Context context, @Nullable AttributeSet attrs){
          super(context, attrs);
          init(context);
        }

        private void init(Context context){
          LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
          inflater.inflate(R.layout.singer_item, this, true);
        }
        ```
        - 이 부분은 책 보면서 다시 한 번 따라해보자.
  - 스피너 & 그리드뷰 : 나중에 ㄱㄱ.