# 우리은행 온택트 해커톤(백엔드)

편리한 착오송금 반환 서비스(2021.09.01 ~ 진행중)

### ✍Skill

- Java (openJDK16 Local 설정)

- Spring Boot **2.1.7.RELEASE**

- Gradle **7.1.1**([gradle](https://github.com/brightest-ko/springboot-webservice/tree/master/gradle)/[wrapper](https://github.com/brightest-ko/springboot-webservice/tree/master/gradle/wrapper)/**gradle-wrapper.properties**)

  - settings.grade

    ```
    rootProject.name = 'backend'
    include 'WER-core'
    include 'WER-api'
    ```

- Mysql 8.0.26 (Local 설정)

  - ```
    $cd /usr/local/mysql/bin
    $./mysql -u root -p
    $패스워드 입력
    mysql>
    use mysql;
    
    CREATE USER Woori identified by '비밀번호'
    select host, user from user;
    
    grant all privileges on *.* to Woori;
    show grants for Woori@localhost;
    
    flush privileges;
    --drop user userid@localhost;
    
    Create Database wooridb;
    ```

- Tool

  - DB(Mysql)

    - cmd창
    - mysql workbench(Oracle사 제공)

  - API 테스트

    - Insomnia (https://insomnia.rest/ 무료버전 제공)
      - 2021.09.02  [Insomnia_2021-09-02.json](../Desktop/Insomnia_2021-09-02.json) 
    - Postman

  - Git https://github.com/Wooribank-Error-Remittance/Back-End

    - Fork(https://git-fork.com/ 무료버전 제공)
    - SourceTree

    

### ✍Entity (Domain)

- Account
  
  - **Long account_id** id
    
  - String number
    
  - String name
    
  - double balance
    
  - **WooriUser** wooriUser
    
    ```
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "woori_user_id")
    private WooriUser wooriUser;
    ```
  
- WooriUser
  - **Long woori_user_id** id

  - String user_id userId

  - String password password

  - String name name

  - String phone_number phoneNumber

  - List<Account> countList

    ```
    @OneToMany(mappedBy = "wooriUser")
    private List<Account> accountList = new ArrayList<>();
    ```

  

### ✍How To Develop

- 비밀번호 암호화 사용

  ```
  package com.wooribank.backend.configuration;
  
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
  import org.springframework.security.crypto.password.PasswordEncoder;
  
  @Configuration
  public class SecurityConfiguration {
  
      @Bean
      public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
      }
  }
  ```

- 계좌인증 API 사용(@PostMapping("/account/update/all") accountService.updateAllAccounts)

  ```
  package com.wooribank.backend.configuration;
  
  import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.http.converter.HttpMessageConverter;
  import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
  import org.springframework.web.client.RestTemplate;
  
  @Configuration
  public class RestTemplateConfiguration {
  
      @Bean
      public RestTemplate restTemplate() {
          RestTemplate restTemplate = new RestTemplate();
          MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
          restTemplate.getMessageConverters().add(converter);
          return restTemplate;
      }
  
  
  }
  ```

- API 문서 자동화 규격 제공 (http://localhost:8080/swagger-ui/index.html)

  ```
  package com.wooribank.backend.configuration;
  
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import springfox.documentation.builders.PathSelectors;
  import springfox.documentation.builders.RequestHandlerSelectors;
  import springfox.documentation.spi.DocumentationType;
  import springfox.documentation.spring.web.plugins.Docket;
  import springfox.documentation.swagger2.annotations.EnableSwagger2;
  
  @Configuration
  public class SwaggerConfiguration {
      @Bean
      public Docket api() {
          return new Docket(DocumentationType.SWAGGER_2)
                  .select()
                  .apis(RequestHandlerSelectors.basePackage("com.wooribank.backend.controller"))
                  .paths(PathSelectors.any())
                  .build();
      }
  }
  ```
