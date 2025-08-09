# WalletServiceApp

Приложение Wallet Service - RESTful сервис для управления кошельками с возможностью выполнения операций депозита и вывода средств.

## Стек

- Язык: Java
- Версия JDK: 17
- Система сборки: Gradle (Kotlin DSL)
- Spring Framework (Boot, MVC, Data)
- Hibernate
- PostgreSQL
- Liquibase для миграций базы данных
- Docker и Docker Compose для контейнеризации и запуска всей системы
- JUnit 5, Mockito


## Инструкция для запуска

Перейдите в корень проекта и запустите скрипт **run.sh**.
  >Скрипт собирает приложение в ***.jar-библиотеку** по пути `WalletService/build/libs/*.jar`, поднимает контейнеры с базой данных PostgreSQL и приложением через Docker compose. Необходимые переменные окружения находятся в корне проекта в файле **secrets.env**.


## Описание

Сервис предоставляет два основных эндпоинта:

  * `POST /api/v1/wallet` - создание транзакции по кошельку (депозит или вывод средств).

    Формат входящего json-запроса:

    ```
    {
      "walletId": "UUID",
      "operationType": "DEPOSIT" | "WITHDRAW",
      "amount": 1000
    }
    ```
  * `GET /api/v1/wallets/{WALLET_UUID}` - получение текущего баланса указанного кошелька.
