# 프로그라피 9기 모바일 과제


## 기본적인 구조

<img src="https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/d26b0ba4-344d-4e7c-a98b-2bda1096108f" width="500" height="400"/>

Hilt 라이브러리로 각 모듈별로 의존성을 주입하였습니다.  
각 화면의 전환과 바텀 네비게이션은 제트팩 네비게이션을 통해 구성하였으며 다른 화면으로 데이터 전달이 필요할경우 SafeArgs를 사용하여 전달합니다.  


### 주요 엔티티  
PhotoResponseItem  
<img src="https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/fdd9b2f6-3f74-460f-9e1b-f52322055d00" width="300" height="400"/>  
Usplash API의 통신을 받아 이미지와 그외의 정보들을 보여주기 위해 사용합니다.

BookmarkEntity  
![image](https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/28dd8992-17c6-4a66-9b63-eaa0ee8bf0a3)  
Room DB에서 사용되는 엔티티로 이미지를 북마크 할때 값을 저장하기 위해 사용합니다.

## Home
리사이클러뷰는 
Paging3 라이브러리와 LoadStateHandle을 이용해서 페이징이 로딩중일 때는 스켈레톤 UI가 보이고 로딩이 완료되면 리사이클러뷰가 보이도록 구현하였습니다.  


## RandomPhoto
무작위 사진을 요청받아 5개의 랜덤한 사진을 표시합니다.  


북마크 버튼을 터치하면 다음 사진으로 넘어가고 북마크가 완료됩니다.  
북마크가 완료되면 스낵바로 북마크가 완료되었다는 문구가 표시됩니다. 스낵바의 취소를 누르면 방금 북마크한 사진을 취소할 수 있습니다.  
X 버튼을 터치하면 현재 사진을 없애고 다음 사진으로 넘어갑니다.  

i 버튼을 터치하면 Detail 화면으로 넘어갑니다.


## Detail
Home과 RandomPhoto화면에서 터치한 이미지의 내용을 세부적으로 볼 수 있습니다.  

X 이미지를 터치하면 현재 창을 나가 뒤로 돌아갑니다.  

북마크 버튼을 터치하면 이미지 내용을 북마크 하거나 해제할 수 있습니다.  
Room DB에 북마크한 데이터가 저장되어있는지 여부에 따라 북마크 버튼 색이 달라집니다.  
(StateFlow로 북마크 버튼을 누를때마다 데이터 변화를 감지하여 북마크 버튼의 색을 바꿉니다.)

### 해결하지 못한 문제

![device-2024-02-03-020344-ezgif com-resize](https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/6ad2e9c9-1150-41f2-831c-b4151f894fdc)

전체 페이지가 스크롤 되지 않고 Photo를 보여주는 리사이클러뷰만 스크롤 되는 문제
이 문제를 해결하기 위해 리사이클러뷰의 스크롤을 비활성화 하면 페이징이 로드 되지않고 NestedScrollView를 쓰면 모든 데이터를 한꺼번에 불러오는 문제가 발생합니다.

### 배운점
