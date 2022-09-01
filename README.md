# 🚚 Eggplant-Delivery
> Eggplant Delivery 서비스는 애플리케이션 이용에 필요한 API 를 제공함으로써   
> 클라이언트의 요청을 받아 운송장 번호 발급, 배송 정보 조회 등을 할 수 있습니다.

# Getting Started

- 해당 프로젝트를 다운로드하거나 `git clone` 을 통해 실행 환경을 구성한 뒤, 다음과 같은 명령어를 실행합니다.


```
./mvnw spring-boot:run
```

# Project Architecture

![배송](https://user-images.githubusercontent.com/53285909/184058391-1a8d0582-a190-456c-b1a7-40b7dbf6159a.svg)


![image](https://user-images.githubusercontent.com/53285909/184069587-cee635ea-0f6c-454a-a004-6cbaa69abd1f.png)

# Features

- 운송장 번호 관리 (Co-authored-by: [@김훈민](http://github.com/bunsung92), [@조재철](https://github.com/JoisFe))
  - 운송장 번호 생성
  - 운송장 번호 전송
  - 운송장 번호 실패 처리 (구현중...)

- 배송 정보 관리 (Co-authored-by: [@김훈민](http://github.com/bunsung92), [@조재철](https://github.com/JoisFe))
  - 배송 정보 추가 
  - 배송 정보 수정 (배송 상태 변경)
  - 배송 정보 삭제 (Hard Delete 시점 구현중...)
  - 배송 정보 조회 (현 위치등을 조회)

## Tech Stack
### Build Tools

![ApacheMaven](https://img.shields.io/badge/Maven-C71A36?style=flat&logo=ApacheMaven&logoColor=white)

### Datebases

![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white)
![H2](https://img.shields.io/badge/H2-4479A1?style=flat&logo=SteamDB&logoColor=white)

### DevOps

![GitHubActions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=flat&logo=GitHubActions&logoColor=white)
![SonarQube](https://img.shields.io/badge/SonarQube-4E98CD?style=flat&logo=SonarQube&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=white)
![RabbitMQ](http://img.shields.io/badge/RabbitMQ-FF81F9?style=flat&logo=RabbitMQ&logoColor=#FF6600)

### Frameworks

![SpringBoot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=SpringBoot&logoColor=white)
![Spring Batch](https://img.shields.io/badge/Spring%20Batch-6DB33F?style=flat&logo=Spring&logoColor=white)

### Languages

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=Java&logoColor=white&style=flat)

### Testing Tools

![Junit5](https://img.shields.io/badge/Junit5-25A162?style=flat&logo=Junit5&logoColor=white)

### 형상 관리 전략

![Git](https://img.shields.io/badge/Git-F05032?style=flat&logo=Git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white)
![Sourcetree](https://img.shields.io/badge/Sourcetree-0052CC?style=flat&logo=Sourcetree&logoColor=white)

- Git Flow 를 사용하여 관리
  모든 브랜치는 Pull Request에 코드 리뷰 진행 후 merge 합니다.
- ![gitflow-workflow](https://user-images.githubusercontent.com/54662174/183854876-aa8c7e55-ce19-4cbf-ba7c-8921ab7a8ab8.png)
- `main`: 배포시 사용
- `develop`: 개발 단계가 끝난 부분에 대해서 merge 내용 포함
- `feature`: 기능 개발 단계
- `hotfix`: merge 후 발생한 버그 및 수정사항 반영 시 사용
- `chore` : 설정 파일 등등 기타사항 변경시 사용

## ERD
![image](https://user-images.githubusercontent.com/53285909/184798692-d7b28b95-f744-4946-9eb1-2859c8802b88.png)

## Contributors

<a href="https://github.com/nhn-academy/eggplant-delivery/graphs/contributors">
<img src="https://contrib.rocks/image?repo=nhn-academy/eggplant-delivery" />
</a>


## License

`Eggplant-Delivery` is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
