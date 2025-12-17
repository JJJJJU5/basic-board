# 🎮 취미 공유 커뮤니티 (Project Name)

> **게임/취미 정보를 공유하고, 활동 포인트로 나만의 프로필을 꾸미는 커뮤니티 서비스입니다.**

![Generic badge](https://img.shields.io/badge/Spring_Boot-3.x-green.svg) ![Generic badge](https://img.shields.io/badge/Java-17-blue.svg) ![Generic badge](https://img.shields.io/badge/JPA-Hibernate-red.svg) ![Generic badge](https://img.shields.io/badge/MySQL-8.0-orange.svg)

## 📅 프로젝트 개발 일지 (Dev Log)
이 프로젝트의 기획, 설계, 구현 과정은 블로그에 상세히 기록되어 있습니다.

| 순서 | 주제 | 링크 | 내용 요약 |
|:---:|:---|:---:|:---|
| **Ep.1** | **기획과 의도** | [Velog 보기](https://velog.io/@ohjeonguk_/Project-%EC%B7%A8%EB%AF%B8%ED%99%9C%EB%8F%99%EC%9D%84-%EC%9C%84%ED%95%9C-%EC%A0%95%EB%B3%B4-%EA%B3%B5%EC%9C%A0-%EC%BB%A4%EB%AE%A4%EB%8B%88%ED%8B%B0-Ep.1-%EA%B8%B0%ED%9A%8D%EA%B3%BC%EC%9D%98%EB%8F%84) | 프로젝트 주제 선정 및 핵심 기능 정의 |
| **Ep.2** | **DB 설계 (ERD)** | [Velog 보기](https://velog.io/@ohjeonguk_/Project-%EC%B7%A8%EB%AF%B8%ED%99%9C%EB%8F%99%EC%9D%84-%EC%9C%84%ED%95%9C-%EC%A0%95%EB%B3%B4-%EA%B3%B5%EC%9C%A0-%EC%BB%A4%EB%AE%A4%EB%8B%88%ED%8B%B0-Ep.2-DB-%EC%84%A4%EA%B3%84) | 비식별 관계 전략 및 정규화 과정 |
| **Ep.3** | **Entity 설계** | [Velog 보기](https://velog.io/@ohjeonguk_/Project-%EC%B7%A8%EB%AF%B8%ED%99%9C%EB%8F%99%EC%9D%84-%EC%9C%84%ED%95%9C-%EC%A0%95%EB%B3%B4-%EA%B3%B5%EC%9C%A0-%EC%BB%A4%EB%AE%A4%EB%8B%88%ED%8B%B0-Ep.3-Entity-%EC%84%A4%EA%B3%84%EC%99%80-Repository-%EA%B5%AC%ED%98%84) | JPA Entity 매핑 및 연관관계 설정 |
<br>

## 🛠 Tech Stack
- **Backend:** Java 17, Spring Boot 3.x, Spring Data JPA, QueryDSL
- **Database:** MariaDB (or MySQL), H2 (Test)
- **Tool:** IntelliJ IDEA, Git, Gradle
<br>

## 💾 ERD 설계
![ERD 이미지](https://velog.velcdn.com/images/ohjeonguk_/post/32fbd8e7-40f3-4124-bb8a-b59a4ac0dd16/image.png)
<br>

## ✨ Key Features
* **회원가입/로그인:** (Spring Security 적용 예정)
* **게시판:** 카테고리별 게시글 작성, 댓글/대댓글, 파일 첨부
* **검색:** 태그(Tag) 기반 검색 및 필터링
* **활성화:**
    * 활동(글작성, 댓글) 시 포인트 적립
    * 포인트로 상점 아이템 구매 및 인벤토리 관리

<br>

## 🚀 How to Run
```bash
# Clone this repository
$ git clone [https://github.com/JJJJJU5/basic-board.git](https://github.com/JJJJJU5/basic-board.git)

# Build
$ ./gradlew build

# Run
$ java -jar build/libs/demo-0.0.1-SNAPSHOT.jar
