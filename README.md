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
    ```
    view.setOnTouchListener(new View.OnTouchListener() { 
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int action = motionEvent.getAction();

            if (action == MotionEvent.ACTION_DOWN) {
                println("손가락 눌림.");
            } else if (action == MotionEvent.ACTION_MOVE) {
                println("손가락 움직임.");
            } else if (action == MotionEvent.ACTION_UP) {
                println("손가락 뗌.");
            }
            return true;
        }
    });
    ```
  - 키 이벤트 : 시스템 back 키 많이 사용함. 위 onTouch 메소드와 비슷한 원리.
- 토스트와 대화상자
  - js에서 console.log처럼 디버깅시 확인이 필요할때 안드로이드는 toast 많이 활용.
  - 비슷한 걸로 SnackBar 존재.
  - AlertDialog : 웹에서 confirm창같은건가보다.
  
- 비트맵 버튼 만들기
  - 나인패치 이미지 : 버튼 키울때 자연스럽게 보정. merong.png를 merong.9.png로 저장하면 끝.
  - 비트맵 버튼 : 버튼을 눌러 배경 이미지 바꾸는것 등에 활용.
- 인플레이션 이해하기 : xml - java 매칭
- 리스트뷰 만들기
  - 리스트뷰 : 
  - 스피너 : 
  - 그리드뷰 : 
