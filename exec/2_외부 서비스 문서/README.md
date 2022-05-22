## 외부 서비스 문서

### 🎭 Frontend

- Vue & Vuex

  ```vue
  npm install -g @vue/cli 
  ```
  
- Axios

  ``` vue
  npm install axios
  ```

- TailwindCSS

  ``` vue
  npm install -D tailwindcss@latest postcss@latest autoprefixer@latest
  ```

  

### 🩺 Backend

- JPA

  - build.gradle에 의존성 등록

    - "org.springframework.boot:spring-boot-stater-data-jpa", "com.h2database:h2"

      ```java
      dependencies {
      	implementation 'org.springframework.boot:spring-boot-starter-web'
      	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
      	compileOnly 'org.projectlombok:lombok'
      	runtimeOnly 'com.h2database:h2'
      	annotationProcessor 'org.projectlombok:lombok'
      	testImplementation('org.springframework.boot:spring-boot-starter-test') {
      		exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
      	}
      }
      ```

- S3

  - 버킷 생성

  - 정책 생성

  - IAM 관리용 사용자 만들기

  - 정책 연결

  - Spring Boot 연결

    - build.gradle 추가

      ```java
      implementation 'org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE'
      ```

    - AmazonS3config 등록

    - application-aws.yml 등록

- jwt, security, oauth



### 🧨Unity

- Unity
- Photon Cloud
  - 포톤 클라우드 회원가입 진행
    https://id.photonengine.com/
  - 서비스에 맞는 제품 선정
  - 관리화면 - 새 어플리케이션으로 무료 ccu 생성
    - 유료 ccu 업데이트 원할 시 한국 대리점에 문의 후 진행
      https://www.photonengine.com/ko-KR/contact![한국대리점](../img/pht_kr.PNG)
      - Ncloud24 추천 - 문의 및 응대가 빠름.
  
  - connect




### 🎁 Nginx setting & SSL 인증서

- Nginx 세팅

  ``` ubuntu
  sudo apt update
  sudo apt install nginx
  ```

  

- SSL 인증서 설치

  - Certbot 활용

    - ubuntu 20.04에 snapd 설치 유무 체크

    - snapd 버전 업데이트

      ```java
      sudo snap install core; sudo snap refresh core
      ```

    - apt, dnf, yum으로 certbot 설치 시 삭제 진행

      ```ubuntu
      sudo apt-get remove certbot
      ```

    - certbot install

      ``` ubuntu
      sudo snap install --classic certbot
      ```

    - certbot 명령 실행 가능 여부 확인

      ``` ubuntu
      sudo ln -s /snap/bin/certbot /usr/bin/certbot
      ```



### 🎑 PORTS 정리

```java
```

