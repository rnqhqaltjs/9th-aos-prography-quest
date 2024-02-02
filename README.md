# 프로그라피 9기 모바일 과제


## 기본적인 구조

<img src="https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/d26b0ba4-344d-4e7c-a98b-2bda1096108f width="200" height="400"/>

Hilt 라이브러리로 각 모듈별로 의존성을 주입하였습니다. 
화면 구성은 하나의 액티비티와 두개의 프래그먼트 하나의 다이얼로그 프래그먼트로 나뉘어집니다.
각 화면의 전환과 바텀 네비게이션은 제트팩 네비게이션을 통해 구성하였으며 다른 화면으로 데이터 전달이 필요할경우 SafeArgs를 사용하여 전달합니다.



## Home

Paging3 라이브러리와 LoadStateHandle을 이용해서 페이징이 로딩중일 때는 스켈레톤 UI가 보이고 로딩이 완료되면 리사이클러뷰가 보이도록 구현하였습니다.
## RandomPhoto



## Detail
Home과 RandomPhoto화면에서 터치한 이미지의 내용을 세부적으로 볼 수 있습니다.

x 이미지를 터치하면 
