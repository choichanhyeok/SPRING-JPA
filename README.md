# SPRING-JPA

<details>
  <summary><h3>1. 스프링 핵심 원리 학습 기록 完</h3></summary>
<div markdown="2">
  
  > 1. [[SPRING-CORE] 스프링 컨테이너와 스프링 빈 시리즈 (1) - 순수한 자바 코드를 이용한 DI에서 스프링을 이용한 DI로](https://blog.naver.com/cksgurwkd12/222844316041)
  > 2. [[SPRING-CORE] 스프링 컨테이너와 스프링 빈 시리즈 (2) - 스프링 컨테이너 생성 과정](https://blog.naver.com/cksgurwkd12/222844357507)
  > 3. [[SPRING-CORE] 스프링 컨테이너와 스프링 빈 시리즈 (3) - 스프링 빈 조회 방법과 문제 정리](https://blog.naver.com/cksgurwkd12/222844377600)
  > 4. [[SPRING-CORE] 스프링 컨테이너와 스프링 빈 시리즈 (4) - BeanFactory와 ApplicationContext](https://blog.naver.com/cksgurwkd12/222844390001)
  > 5. [[SPRING-CORE] 스프링 컨테이너와 스프링 빈 시리즈 (5) - ApplicationContext의 다양한 설정 형식 지원 (java code, XML)](https://blog.naver.com/cksgurwkd12/222844406617)
  > 6. [[SPRING-CORE] 스프링 컨테이너와 스프링 빈 시리즈 (6) - 스프링 빈 설정 메타 정보 (BeanDefinition) ](https://blog.naver.com/cksgurwkd12/222844413913)
  > 7. [[SPRING-CORE] 싱글톤 컨테이너 시리즈 (1) - 웹 어플리케이션과 싱글톤](https://blog.naver.com/cksgurwkd12/222848936082)
  > 8. [[SPRING-CORE] 싱글톤 컨테이너 시리즈 (2) - 싱글톤 컨테이너가 기존 싱글톤 방식의 어떤 문제를 해결하는가](https://blog.naver.com/cksgurwkd12/222849107572)
  > 9. [[SPRING-CORE] 싱글톤 컨테이너 시리즈 (3) - 싱글톤 방식의 주의점 ** -> 형철 튜터님 질문했던 거](https://blog.naver.com/cksgurwkd12/222849244770)
  > 10. [[SPRING-CORE] 싱글톤 컨테이너 시리즈 (4) - @Configuration의 비밀 (Configuration과 싱글톤) 完](https://blog.naver.com/cksgurwkd12/222849388801)
  > 11. [[SPRING-CORE] 컴포넌트 스캔 시리즈(1) - 컴포넌트 스캔과 의존관계 자동 주입 기초](https://blog.naver.com/cksgurwkd12/222856380548)
  > 12. [[SPRING-CORE] 컴포넌트 스캔 시리즈(2) - 탐색 위치와 기본 스캔 대상](https://blog.naver.com/cksgurwkd12/222856510401)
  > 13. [[SPRING-CORE] 컴포넌트 스캔 시리즈(3) - 컴포넌트 스캔을 위한 필터들](https://blog.naver.com/cksgurwkd12/222856530202)
  > 14. [[SPRING-CORE] 컴포넌트 스캔 시리즈(4) - 같은 이름의 스프링 빈 중복 등록과 충돌 完](https://blog.naver.com/cksgurwkd12/222857965362)
  > 15. [[SPRING-CORE] 의존관계 주입 시리즈 (1) - 다양한 의존관계 주입 방법](https://blog.naver.com/cksgurwkd12/222858300483)
  > 16. [[SPRING-CORE] 의존관계 주입 시리즈 (2) - 스프링 의존관계 자동주입(@Autowired)의 옵션처리](https://blog.naver.com/cksgurwkd12/222858382470)
  > 17. [[SPRING-CORE] 의존관계 주입 시리즈 (3) - 생성자 주입을 선택해야 하는 이유](https://blog.naver.com/cksgurwkd12/222859219074)
  > 18. [[SPRING-CORE] 의존관계 주입 시리즈 (4) - 롬복을 이용한 의존관계 주입 최신 트렌드](https://blog.naver.com/cksgurwkd12/222859240222)
  > 19. [[SPRING-CORE] 의존관계 주입 시리즈 (5) -조회 빈이 2개 이상일 때의 문제  (*****)](https://blog.naver.com/cksgurwkd12/222859269679)
  > 20. [[SPRING-CORE] 의존관계 주입 시리즈 (6) - @Qualifier을 어노테이션을 적용해 더 깔끔하게](https://blog.naver.com/cksgurwkd12/222859279424)
  > 21. [[SPRING-CORE] 의존관계 주입 시리즈 (7) - 조회한 빈이 모두 필요할 때, List, Map](https://blog.naver.com/cksgurwkd12/222859856310)
  > 22. [[SPRING-CORE] 의존관계 주입 시리즈 (8) - 자동, 수동 의존성 주입의 올바른 실무 운영 기준 完](https://blog.naver.com/cksgurwkd12/222859933031)
  > 23. [[SPRING-CORE] 빈 초기화 콜백과 소멸전 콜백 운용 시리즈(0) - 빈 생명주기 콜백 시작](https://blog.naver.com/cksgurwkd12/222860773608)
  > 24. [[SPRING-CORE] 빈 초기화 콜백과 소멸전 콜백 운용 시리즈(1) - 인터페이스 (initializingBean, DisposableBean) + 거의 사용하지 않는 +](https://blog.naver.com/cksgurwkd12/222861711969)
  >25. [[SPRING-CORE] 빈 초기화 콜백과 소멸전 콜백 운용 시리즈(2) - 메서드와 @Bean](https://blog.naver.com/cksgurwkd12/222861729276)
  >26. [[SPRING-CORE] 빈 초기화 콜백과 소멸전 콜백 운용 시리즈(3) - 애노테이션 ** (@PostConstruct, @PreDestroy) 完](https://blog.naver.com/cksgurwkd12/222861734979)
  >27. [[SPRING-CORE] 빈 스코프 시리즈(0) - 빈 스코프란?](https://blog.naver.com/cksgurwkd12/222861742091)
  >28. [[SPRING-CORE] 빈 스코프 시리즈(1) - 프로토타입 스코프](https://blog.naver.com/cksgurwkd12/222862579107)
  >29. [[SPRING-CORE] 빈 스코프 시리즈(2) - 프로토 타입 스코프: 싱글톤 빈과 함께 사용시 문제점](https://blog.naver.com/cksgurwkd12/222863571536)
  >30. [[SPRING-CORE] 빈 스코프 시리즈(3) - 싱글톤 빈과 함께 사용시 Provider로 문제 해결](https://blog.naver.com/cksgurwkd12/222863605326)
  >31. [[SPRING-CORE] 빈 스코프 시리즈(4) - 웹 스코프: 개요](https://blog.naver.com/cksgurwkd12/222863610596)
  >32. [[SPRING-CORE] 빈 스코프 시리즈(5) - 웹 스코프: Request 스코프 이해하기 (Provider을 이용한)](https://blog.naver.com/cksgurwkd12/222864557375)
  >33. [[SPRING-CORE] 빈 스코프 시리즈(6) - 스코프와 프록시: 개발자들은 ObjectProvider 마저도 줄이고 싶다.完](https://blog.naver.com/cksgurwkd12/222864565509)
  
</details>
  
<br>
<br>
오늘 하루도 공부할 수 있어 크게 감사합니다.

