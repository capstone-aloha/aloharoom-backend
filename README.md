<h1 align="center">🏘<strong>aloha <strong>room(주거공간 모집)</strong>🏘</h1>


<div align="center">
  <strong>HSU 2023 Capstone Project</strong>
</div>

<div align="center">
  룸메이트를 모집하는 1인 가구 플랫폼
</div>

<div align="center">
  <h3>
    <a href="https://github.com/capstone-aloha/aloharoom-frontend">
      🌏 Frontend
    </a>
    <span> | </span>
    <a href="https://cut-prune-d8b.notion.site/API-9e0aa8a740524b0ebf45c52894c15d1c?pvs=4">
      📜 REST API Document
    </a>
  </h3>
</div>
<br>

## 🔖 목차

- [개요](https://github.com/capstone-aloha/aloharoom-backend#-%EA%B0%9C%EC%9A%94)
- [배포 URL](https://github.com/capstone-aloha/aloharoom-backend#%EB%B0%B0%ED%8F%AC-url)
- [실행 및 설치 방법](https://github.com/capstone-aloha/aloharoom-backend#-%EC%8B%A4%ED%96%89-%EB%B0%8F-%EC%84%A4%EC%B9%98-%EB%B0%A9%EB%B2%95)
- [핵심 기능](https://github.com/capstone-aloha/aloharoom-backend#-%EC%8B%A4%ED%96%89-%EB%B0%8F-%EC%84%A4%EC%B9%98-%EB%B0%A9%EB%B2%95)
  * [해시태그 등록](https://github.com/capstone-aloha/aloharoom-backend#%ED%95%B4%EC%8B%9C%ED%83%9C%EA%B7%B8-%EB%93%B1%EB%A1%9D)
  * [방 구하기](https://github.com/capstone-aloha/aloharoom-backend#%EB%B0%A9-%EA%B5%AC%ED%95%98%EA%B8%B0-%ED%95%84%ED%84%B0%EB%A7%81)
  * [방 데이터 그래프](https://github.com/capstone-aloha/aloharoom-backend#%EB%B0%A9-%EA%B5%AC%ED%95%98%EA%B8%B0-%ED%95%84%ED%84%B0%EB%A7%81)
- [기술 스택](https://github.com/capstone-aloha/aloharoom-backend#%EB%B0%A9-%EA%B5%AC%ED%95%98%EA%B8%B0-%ED%95%84%ED%84%B0%EB%A7%81)
- [시스템 구조도](https://github.com/capstone-aloha/aloharoom-backend#%EB%B0%A9-%EA%B5%AC%ED%95%98%EA%B8%B0-%ED%95%84%ED%84%B0%EB%A7%81)
- [주요 화면](https://github.com/capstone-aloha/aloharoom-backend#-%EC%A3%BC%EC%9A%94-%ED%99%94%EB%A9%B4)
- [팀 정보](https://github.com/capstone-aloha/aloharoom-backend#-%ED%8C%80-%EC%A0%95%EB%B3%B4)


## 📍 개요
이 플랫폼은 주거공간 공유에 초점을 맞춰 룸메이트를 모집하는 1인 가구 플랫폼이다.<br>
사용자의 위치 정보와 사용자 성향이 반영된 해시태그들을 가지고 자신과 성향이 비슷한 룸메이트를 모집할 수 있다.<br>
또한 룸메이트를 모집하는 것뿐만 아니라 이 플랫폼의 사용자를 유지시키기 위해 커뮤니티를 두어 다른 사용자들이 서로 소통할 수 있다.
  
## 배포 URL
http://3.34.33.83:3000/

## 🏃 실행 및 설치 방법
1. aloharoom 원격 저장소를 클론합니다.
   ```shell
   git clone https://github.com/capstone-aloha/aloharoom-backend.git
   ```
2. 생성된 로컬 저장소로 이동 후 빌드
    ```shell
    /* windows */
    $ gradlew build

    /* linux */
    $ ./gradlew build
    ```
3. ./build/libs 에서 .jar파일 실행
   ```shell
   java -jar aloharoom-backend-0.0.1-SNAPSHOT.jar
   ```

## ✨ 핵심 기능

### 해시태그 등록
- 해시태그는 내 집에 대한 해시태그, 내가 선호하는 해시태그로 나뉩니다.
- 회원가입 시 추가할 수 있고 내 정보화면에서 변경 가능합니다.
- 해시태그로 사용자의 성향을 한 눈에 파악할 수 있습니다.


### 방 구하기 (필터링)
  
- 캠페인에서 배너 생성 버튼을 누르거나, 배너제작 페이지로 들어가 배너를 제작할 수 있습니다.
- 블로그, 기사에 관련 기부를 첨부해 보세요!
- 자유롭게 커스터마이징이 가능해 기부 관련 컨텐츠가 아니라도 배너를 만들 수 있습니다.
- 로그인 상태에서 만든 배너는 저장되며, 수정도 가능합니다.


### 방 데이터 그래프
  - 텍스트를 삽입하면 주요 단어가 추출됩니다.
  - 그 중요도에 따라 가중치를 부여해 Elasticsearch에서 기부를 검색하고, 반환합니다.
  - 본인의 컨텐츠와 밀접한 기부캠페인을 추천 받을 수 있습니다.

## 📌 기술 스택
<div>
<table>
   <tr>
      <td colspan="1" align="center">
        Language
      </td>
      <td colspan="3">
        <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
        <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
      </td>
   </tr>
   <tr>
      <td colspan="1" align="center">
        Library & Framework
      </td>
      <td colspan="3">
        <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=white"> 
        <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
        <img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
        <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white"> 
        <img src="https://img.shields.io/badge/QueryDSL-0088D7?style=for-the-badge&logoColor=white"> 
        <img src="https://img.shields.io/badge/amazon ec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> 
        <img src="https://img.shields.io/badge/amazon s3-569A31?style=for-the-badge&logo=amazons3&logoColor=white"> 
        <img src="https://img.shields.io/badge/Kakao Map API-FF9900?style=for-the-badge&logoColor=white">
      </td>
   </tr>
   <tr>
      <td colspan="1" align="center">
        Database
      </td>
      <td colspan="3">
        <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
        <img src="https://img.shields.io/badge/amazon rds-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
      </td>
   </tr>
   <tr>
      <td colspan="1" align="center">
        Tool
      </td>
      <td colspan="3">
          <img src="https://img.shields.io/badge/intellijidea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
          <img src="https://img.shields.io/badge/visualstudiocode-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white">
      </td>
   </tr>
   <tr>
      <td colspan="1" align="center">
        etc
      </td>
      <td colspan="3">
          <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">
          <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">
      </td>
   </tr>
</table>
</div>

## 시스템 구조도
<img width="895" alt="image" src="https://github.com/capstone-aloha/aloharoom-backend/assets/92067099/59025dee-d6e8-4d95-add1-8679f5d7db27">


## 📸 주요 화면

<details>
  <summary>주요 화면 보기</summary>
  
  - 내 정보<br>
  ![내 정보 보기](https://github.com/capstone-aloha/aloharoom-backend/assets/92067099/4539d3d3-3d09-4610-ab8f-da43ac6a9b97)

  - 방 구하기<br>
  ![지도움직이기](https://github.com/capstone-aloha/aloharoom-backend/assets/92067099/bba4bac1-914e-42d4-b5f8-fe80bbc76b20)

  - 방 필터링<br>
  ![방 필터링](https://github.com/capstone-aloha/aloharoom-backend/assets/92067099/0791aacd-9676-4f14-ac78-94690730688b)

  - 지명 검색<br>
  ![지명검색](https://github.com/capstone-aloha/aloharoom-backend/assets/92067099/1f541207-1c8b-49dc-851c-4278d5c2990e)

  - 방 상세보기<br>
  ![방 상세보기](https://github.com/capstone-aloha/aloharoom-backend/assets/92067099/24125b26-c2ea-4b9a-833e-58a72ae1a80b)

  - 커뮤니티<br>
  ![커뮤니티 페이지](https://github.com/capstone-aloha/aloharoom-backend/assets/92067099/3f8fd145-679c-47e3-92df-a1c60761398e)
  
</details>


## 👩‍👩‍👧‍👦 팀 정보

<div sytle="overflow:hidden;">
<table>
   <tr>
      <td colspan="2" align="center"><strong>Front-End Developer</strong></td>
      <td colspan="2" align="center"><strong>Back-End Developer</strong></td>
   </tr>
  <tr>
    <td align="center">
    <a href="https://github.com/gretea5"><img src="https://avatars.githubusercontent.com/u/120379834?v=4" width="150px;" alt="박장훈"/><br /><sub><b>박장훈</b></sub></a><br />
    </td>
     <td align="center">
        <a href="https://github.com/JiYun1101"><img src="https://avatars.githubusercontent.com/u/91119322?v=4" width="150px" alt="김지윤"/><br /><sub><b>김지윤</b></sub></a>
     </td>
     <td align="center">
        <a href="https://github.com/fkgnssla"><img src="https://avatars.githubusercontent.com/u/92067099?v=4" width="150px" alt="김형민"/><br /><sub><b>김형민</b></sub></a>
     </td>
     <td align="center">
        <a href="https://github.com/hwldus"><img src="https://avatars.githubusercontent.com/u/89963270?v=4" width="150px" alt="황지연"/><br /><sub><b>황지연</b></sub></a>
     </td>
  <tr>

</table>
</div>
