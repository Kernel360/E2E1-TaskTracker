# 프로젝트 계층형 구조

### Domain

- 데이터베이스와 직접적으로 상호작용하는 객체
- DB 테이블 == 스프링 도메인
- Repository랑 상호작용

### Dto

- 변동사항이 있을 때 마다 db에 접속하는 것을 방지하기 위한 object
- 모든 변동사항이 있을 때 마다 dto를 이용함

### Repository

- domain과 직접적으로 연결함
- 해당 프로젝트에서 대부분 JPA Repository를 상속받아 사용함
- 반환형은 domain 대신 dto를 사용

### Exception

- custom exception을 발생시키기 위한 목적
- 예외 사항 관련된 부분을 저장

### Service

- 비즈니스 로직 처리
- 컨트롤러에서 요청 사항을 받아 해결하는 역할

### util

- 공용적으로 사용할 목적의 코드를 저장 == global

### Controller

- 유저와 상호작용하는 역할
- 반환형 String인 경우 templates를 루트 경로 삼아 만든 뷰를 띄워준다.
- dfdf
