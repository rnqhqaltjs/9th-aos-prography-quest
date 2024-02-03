# 프로그라피 9기 모바일 과제
### Unsplash API를 이용한 Photo App

## 기본적인 구조

<img src="https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/d26b0ba4-344d-4e7c-a98b-2bda1096108f" width="500" height="400"/>


### Navigation  
각 화면의 전환과 바텀 네비게이션은 Jetpack Navigation을 통해 구성하였으며, 다른 화면으로 데이터 전달이 필요할경우 SafeArgs를 사용하여 전달합니다.  

### Observer Pattern  
UIState와 StateFlow를 이용해 Loading, Success, Failure에 따라 UI를 적절하게 업데이트 하도록 구현하였습니다.

### Dependency Injection  
Hilt 라이브러리로 각 모듈별로 의존성을 주입하였습니다.  

### 주요 엔티티  
PhotoResponseItem  
<img src="https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/fdd9b2f6-3f74-460f-9e1b-f52322055d00" width="300" height="400"/>  
Usplash API의 통신을 받아 이미지와 그외의 정보들을 보여주기 위해 사용합니다.

BookmarkEntity  
![image](https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/28dd8992-17c6-4a66-9b63-eaa0ee8bf0a3)  
Room DB에서 사용되는 엔티티로 북마크 할때 값을 저장하기 위해 사용합니다.

## Home
![device-2024-02-03-025111-ezgif com-resize](https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/81cdce87-6e77-49e7-8932-0bcdd946d021)  
* 페이지당 10개의 아이템을 최신순으로 보여주는 Photo 리사이클러뷰입니다.
* Shimmer 라이브러리를 이용하여 로딩화면을 스켈레톤 UI으로 구성하였습니다.
* Paging3 라이브러리를 이용해 무한 스크롤을 구현하였고 LoadStateHandle을 이용해서 로딩중일때는 로딩 상태를 다룹니다.
* 로딩이 완료되면 리사이클러뷰가 보이도록 구현하였습니다.

![device-2024-02-03-040303-ezgif com-resize](https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/e505362f-35a2-4211-91c5-f0cad51ef6cf)  
* 북마크 했던 사진들의 목록을 보여주는 Bookmark 리사이클러뷰입니다.  

## RandomPhoto
![device-2024-02-03-021606-ezgif com-resize](https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/57fec832-6ebc-4515-9cae-ff9b9bf1e6e7)  
* 무작위 사진을 요청받아 5개의 랜덤한 사진을 표시합니다.  
* 북마크 버튼을 터치하면 다음 사진으로 넘어가고 북마크가 완료됩니다.  
* 북마크가 완료되면 스낵바로 북마크가 완료되었다는 문구가 표시됩니다.  
* 스낵바의 취소를 누르면 방금 북마크한 사진을 취소할 수 있습니다.  

![device-2024-02-03-022447-ezgif com-resize](https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/c0e97393-5f1b-44f6-b4c5-d17c6cc43edd)  
* "X" 버튼을 터치하면 현재 사진을 없애고 다음 사진으로 넘어갑니다.  
* "i" 버튼을 터치하면 Detail 화면으로 넘어갑니다.

## Detail
![device-2024-02-03-124312-ezgif com-resize](https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/4aa47603-1df5-4226-8188-b30512b3c2d9)  
* Home과 RandomPhoto화면에서 터치한 이미지의 내용을 세부적으로 볼 수 있습니다.  
* "X" 이미지를 터치하면 현재 창을 나가 뒤로 돌아갑니다.  
* 북마크 버튼을 터치하면 이미지 내용을 북마크 하거나 해제할 수 있습니다.  
* Room DB에 북마크한 데이터가 저장되어있는지 여부에 따라 북마크 버튼 색이 달라집니다.  

## 해결하지 못한 문제 (해결)

![device-2024-02-03-020344-ezgif com-resize](https://github.com/rnqhqaltjs/9th-aos-prography-quest/assets/86480696/6ad2e9c9-1150-41f2-831c-b4151f894fdc)  
~~*Photo 리사이클러뷰를 스크롤하면 전체 페이지가 스크롤 되지 않고 Photo 리사이클러뷰만 스크롤 되는 문제*~~
*~~이 문제를 해결하기 위해 리사이클러뷰의 스크롤을 비활성화 하면 페이징이 로드 되지않고, NestedScrollView를 쓰면 모든 데이터를 한꺼번에 불러오는 문제가 발생합니다.~~*  

Constraintlayout -> Coordinatorlayout으로 수정하여 문제를 해결하였습니다.

## 성장한 점  
Detail화면 구성을 위해 생각하다가 Dialog를 떠올렸지만, Dialog는 적절하게 생명주기를 활용할 수 없어서 찾아보다가 DialogFragment을 알게되었습니다.  
생명주기 메서드를 활용하여 화면이 생성되고 소멸될 때 필요한 작업들을 매끄럽게 처리할 수 있어서 좋았습니다.
