# [SOOP GITHUB PROJECT]



## *****Description*****


GitHub 레포지토리 검색 및 상세 정보 페이지를 제공하는 애플리케이션입니다. 

사용자는 GitHub에서 레포지토리 및 유저 정보를 검색하고, 상세 정보를 확인할 수 있습니다. 

클린 아키텍처와 MVVM 패턴을 기반으로 구축되어 유지보수와 확장성을 고려했습니다. 

또한, 성능 최적화와 메모리 관리를 위해 다양한 최신 기술을 적용했습니다.



# 📱 *****Preview*****
|       뷰       |                                                              1                                                              |                                                              2                                                              |                                                              3                                                                                        |
|:-------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------:|
| 깃허브 <br> 검색 및 상세페이지 | <img width="200px" src="https://github.com/user-attachments/assets/cf9cf6e5-324b-4990-9240-68e758814692"/> | <img width="200px" src="https://github.com/user-attachments/assets/5755640e-4381-4f3a-a073-5cdcf9f3659d"/> | <img width="200px" src="https://github.com/user-attachments/assets/d945a14d-982c-4b56-9c1f-101b8e7fa8c3"/> |                           


# 👤 *****Contributor*****
<div align="center">
<table style="font-weight : bold">
      <tr>
         <td align="center">
          <a href="https://github.com/arinming">                 
                  <img alt="arinming" src="https://avatars.githubusercontent.com/arinming" width="100" />            
              </a>
          </td>
      </tr>
      <tr>
          <td align="center">김아린</td>
       <br> 전체 기능 구현 
        <br/>
      </tr>
  </table>
</div>


</br>

# 🔎 ***Architecture***


### Clean Architecture

<img width="500" src="https://blog.kakaocdn.net/dn/UGyIK/btsnZH40l6y/WBiqZPQRxw1hqvFAMc77xK/img.png"/>

<br>


### 클린 아키텍처 및 MVVM 패턴 적용

 **클린 아키텍처**와 **MVVM 패턴**을 적용하여 **각 계층을 분리**하고 **유지보수성**을 크게 향상시켰습니다. 각 계층은 **Hilt**를 통해 의존성 주입을 처리하며, **단일 책임 원칙**을 지키는 방식으로 설계되었습니다.

#### **UI 계층**
- **ViewModel**을 사용하여 **UI와 비즈니스 로직**을 분리하였습니다.  
- **MutableStateFlow**를 활용하여 UI 상태를 관리하고, 데이터 변경 사항을 효율적으로 반영할 수 있게 했습니다. 이로 인해 UI의 상태를 직관적이고 반응적으로 처리할 수 있습니다.

#### **도메인 계층**
- **UseCase**를 통해 **비즈니스 로직**을 처리합니다.  
- 복잡한 로직을 **ViewModel**에서 분리하여 **UseCase**로 이동시켜, 각 계층의 역할을 명확히 하였습니다.

#### **데이터 계층**
- **Repository**와 **DataSource**를 활용하여 **API 통신**과 **데이터 캐싱**을 처리하였습니다.
- **Service**를 통해 외부 API와의 통신을 관리하며, 데이터 제공의 효율성을 높였습니다.

#### **MVVM 패턴 적용**
- **MVVM(Model-View-ViewModel)** 패턴을 적용하여 UI와 데이터 로직을 독립적으로 테스트할 수 있도록 설계하였습니다.
- **MutableStateFlow**를 사용하여 **UI 업데이트** 시 **데이터 변경 사항**을 간편하게 반영하고, 비즈니스 로직과 UI의 분리된 구조를 유지하여 테스트와 확장성을 개선했습니다.



<br>


# ⚒️ ***Tech***


Target SDK Level 34

Min SDK Level 26




| Title | Content                               |
| ------------ |---------------------------------------|
| Architecture | Clean Architecture, MVVM, Multi-Module |
| UI Framework  | Jetpack Compose                       |
| Build Tools  | Custom Build Logic, Gradle Version Catalog                  |
| Dependency Injection | Hilt                                  |
| Network | Retrofit2, OkHttp                     |
| Asynchronous Processing | Coroutine, Flow                       |
| Third Party Library | Coil, Timber, Paging       |\



### 코루틴을 통한 비동기 처리 및 최적화
- **코루틴**을 사용하여 비동기 처리를 효율적으로 관리합니다.
- **네트워크 통신** 및 **데이터베이스 작업**을 백그라운드 스레드에서 처리하여 UI의 응답성을 향상시켰습니다.
- **Retrofit**과 **OkHttp**를 사용하여 **네트워크 API 호출**을 처리하고, **suspend function**을 이용해 비동기 작업을 안전하게 처리합니다.

### 페이징 처리로 성능 최적화
- **Paging 3** 라이브러리를 도입하여 대용량 데이터를 효율적으로 처리합니다.
- 검색 결과를 **페이지 단위로 가져오며**, 스크롤을 내릴 때마다 새로운 데이터를 비동기적으로 로드하여 **메모리 효율성**과 **성능 최적화**를 달성했습니다.
- **PagingData**를 사용해 서버에서 데이터를 비동기적으로 가져오고, **PagingSource**를 통해 데이터를 페이징 방식으로 처리하여, 한 번에 모든 데이터를 로드하지 않고 필요한 데이터만 로드하도록 하여 메모리 사용을 최적화합니다.

### 멀티모듈 구조
- 프로젝트는 **멀티모듈 구조**로 설계되어 각 모듈은 독립적으로 관리되고, 의존성이 구분됩니다.
- 새로운 기능을 추가하거나 수정할 때, 다른 모듈에 미치는 영향을 최소화하며, **빌드 속도**와 **유지보수성**을 개선합니다.
- 중복 코드를 줄이고, 필요 시 모듈을 추가하거나 수정하는 것이 용이합니다.

### 빌드 로직 및 의존성 관리
- **Gradle**을 최적화하여 의존성 관리와 빌드 속도를 개선했습니다.
- 모든 라이브러리 버전은 **libs.versions.toml**을 사용하여 일관되게 관리하고, 불필요한 의존성 충돌을 방지합니다.
- 의존성 버전을 중앙화하고, 필요하지 않은 라이브러리를 제거하여 **빌드 시간을 단축**시켰습니다.

### 공통 테마 및 컴포넌트 사용
- **Jetpack Compose**를 사용하여 UI를 **선언형**으로 구현했습니다.
- 공통 **테마**와 **컴포넌트**를 작성하여 UI의 일관성을 유지하고, 여러 화면에서 재사용할 수 있도록 했습니다.
- 이를 통해 UI의 **유지보수성**을 크게 향상시켰습니다.




<br>



## 📁 *****Foldering*****


```

🗃️app

🗃️build-logic
 ┣ 📂️convention
 ┗ 📂extension

🗃️core
 ┣ 🗃️common
 ┣ 🗃️data
 ┃ ┣ 📂di
 ┃ ┣ 📂repository
 ┣ 🗃️designsystem
 ┃ ┣ 📂theme
 ┣ 🗃️domain
 ┣ 🗃️model
 ┣ 🗃️network
 ┗ 🗃️testing

🗃️feature
 ┣ 🗃️repository
 ┣ 🗃️search

```


<br>  

## BRANCH STRATEGY
```
작업 유형/이슈 번호-작업 내용
```

## COMMIT CONVENTION
```
[작업 유형/#이슈 번호] 작업 내용
```

## PR CONVENTION
```
[작업 유형/#이슈 번호] 작업 내용
```
<br>

