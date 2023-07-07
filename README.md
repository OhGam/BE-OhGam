# 😇 OhGam - 오늘의 감사

오늘의 감사한 점을 기록하는 앱  

--- 

## 📕 BE API 사용법

모든 url 앞에는 http://35.175.196.60:8080를 붙여야 합니다.

### 🙍‍ Member 관련

### 🗒️ Note 관련

#### 1. 조회 &nbsp; 🔍

- 조회하는 회원 로그인 아이디, 노트 작성한 회원 로그인 아이디, 작성 날짜를 받아 특정 노트 조회
- 조회 회원과 노트 작성 회원이 다르다면 조회수 증가
  - HTTP method
  ```
  GET
  ```
  - url
  ```
  /findNote?searchUserId=test1&noteUserId=test2&year=2023&month=7&day=8
  ```
  - HTTP Header: x
  - HTTP body: x  


- 모든 회원들이 작성한 모든 감사 노트 조회
  - HTTP method
  ```
  GET
  ```
  - url 
  ```
  /api/notes/findall
  ```
  - HTTP Header: x
  - HTTP body: x


- 시작 날짜, 마지막 날짜를 쿼리문으로 받아 그 기간에 적힌 노트 조회
  - HTTP method
  ```
  GET
  ```
  - url
  ```
  /api/notes/findByDate?startYear=2023&startMonth=7&startDay=5&endYear=2023&endMonth=7&endDay=7 (숫자는 예시임)
  ```
  - HTTP Header: x
  - HTTP body: x
  

- 회원 로그인 아이디로 그 회원이 적은 모든 노트를 조회
  - HTTP method
  ```
  GET
  ```
  - url
  ```
  /api/notes/findByUserId?userId=test (test 대신 로그인 아이디, ""로 감싸줄 필요 없음)
  ```
  - HTTP Header: x
  - HTTP body: x


- 회원 로그인 아이디 및 시작 날짜, 마지막 날짜를 쿼리문으로 받아 그 기간에 그 회원이 적은 노트 조회
  - HTTP method
  ```
  GET
  ```
  - url
  ```
  /api/notes/findByDate?userId=test&startYear=2023&startMonth=7&startDay=5&endYear=2023&endMonth=7&endDay=7 (숫자는 예시임)
  ```
  - HTTP Header: x
  - HTTP body: x

#### 2. 작성 &nbsp; ✏️

- 회원 로그인 아이디를 받아 감사노트 작성
  - HTTP method
  ```
  POST
  ```
  - url
  ```
  /api/notes/write?userId=test
  ```
  - HTTP Header
  ```
  Content-Type: application/json
  ```
  - HTTP body:
  ```
  {  
  "isPublic": false,  
  "text": [  
  {"content": "testtest1"},  
  {"content": "testtest2"},  
  {"content": "testtest3"}  
  ]  
  } (예시)
  ```


#### 3. 수정 &nbsp; 🔨

- 회원 로그인 아이디와 날짜를 받아 감사노트 수정
  - HTTP method
  ```
  POST
  ```
  - url
  ```
  /api/notes/edit?userId=test&year=2023&month=7&day=7
  ```
  - HTTP Header
  ```
  Content-Type: application/json
  ```
  - HTTP body
  ```
  {  
  "isPublic": false,  
  "text": [  
  {"content": "testtest1"},  
  {"content": "testtest2"},  
  {"content": "testtest3"}  
  ]  
  }
  ```