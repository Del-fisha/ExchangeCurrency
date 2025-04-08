# ExchangeCurrency - Пет-проект

## Описание

Этот pet-проект представляет собой REST API для управления валютами и обменными курсами. Он позволяет создавать, читать, обновлять и удалять (CRUD) валюты и обменные курсы, а также предоставляет эндпоинт для конвертации валют.

## Технологии

•   **Язык программирования:** Java 17

•   **Фреймворк:** Spring Boot 3.4.3

•   **Управление зависимостями:** Maven 3.9.9

•   **База данных:** SQLite (используется для простоты развертывания и демонстрации, но может быть легко заменена на другую реляционную СУБД)

•   **ORM:** Spring Data JPA, Hibernate

•   **Миграции базы данных:** Liquibase

•   **Логгирование:** SLF4J

•   **AOP:** AspectJ (для логирования)

•   **Тестирование:** JUnit 5

•   **Lombok:** Используется для сокращения boilerplate кода (геттеры, сеттеры, конструкторы)

## Архитектура

Проект использует классическую трехуровневую архитектуру:

•   **Controller:**  Обрабатывает HTTP запросы и делегирует логику сервисам.

•   **Service:** Содержит бизнес-логику приложения, взаимодействует с репозиториями для доступа к данным.

•   **Repository:** Абстракция для доступа к базе данных, использует Spring Data JPA.

•   **DTO (Data Transfer Object):** Используется для передачи данных между слоями приложения.

•   **Model:** Представление сущностей базы данных.

## Структура проекта

ExchangeCurrency/
├── src/main/java/pet/exchangecurrency/

│  ├── aop/

│  │  ├── controller/

│  │  │  └── crud/

│  │  ├── service/

│  │  │  ├── currency/

│  │  │  │  └── crud/

│  │  │  └── exchangerate/

│  │  │    └── crud/

│  ├── controller/      # REST контроллеры

│  ├── dto/         # Data Transfer Objects

│  ├── exceptions/      # Пользовательские исключения

│  ├── model/        # Модели сущностей базы данных

│  ├── repository/      # Репозитории для доступа к данным

│  ├── service/        # Бизнес-логика приложения

│  ├── utilits/       # Вспомогательные утилиты

│  └── ExchangeCurrencyApplication.java # Точка входа в приложение


Приложение будет доступно по адресу: http://localhost:8080

## API Endpoints

### Валюты (Currencies)

•   **POST /currencies:**  Создание новой валюты.

    *   Параметры запроса: code (String, обязательный), fullName (String, обязательный), sign (String, обязательный)
    *   Возвращает: CurrencyDto (созданная валюта) или ошибку.
•   **GET /currencies:** Получение списка всех валют.

    *   Возвращает: Collection<CurrencyDto> (список валют).
•   **GET /currency/id/{id}:** Получение валюты по ID.

    *   {id}: ID валюты (Long, обязательный).
    *   Возвращает: CurrencyDto (найденная валюта) или ошибку.
•   **GET /currency/{code}:** Получение валюты по коду.

    *   {code}: Код валюты (String, обязательный).
    *   Возвращает: CurrencyDto (найденная валюта) или ошибку.
•   **DELETE /currency/id/{id}:** Удаление валюты по ID.

    *   {id}: ID валюты (Long, обязательный).
    *   Возвращает: HTTP 200 OK или ошибку.
•   **DELETE /currency/{code}:** Удаление валюты по коду.

    *   {code}: Код валюты (String, обязательный).
    *   Возвращает: HTTP 200 OK или ошибку.
•   **PUT /currencies:** Обновление информации о валюте.

    *   Запрос в теле: CurrencyDto (обновленная валюта). id обязателен.
    *   Возвращает: CurrencyDto (обновленная валюта) или ошибку.

### Обменные курсы (Exchange Rates)

*   **POST /exchangerates:** Создание нового обменного курса.
    *   **Параметры запроса:**
        *   `baseCurrencyCode` (String, обязательный) - Код базовой валюты.
        *   `targetCurrencyCode` (String, обязательный) - Код целевой валюты.
        *   `rate` (Double, обязательный) - Курс обмена.
    *   **Возвращает:** `ExchangeRateDto` (созданный обменный курс) или сообщение об ошибке.

*   **DELETE /exchangerates/{id}:** Удаление обменного курса по ID.
    *   **{id}:** ID обменного курса (Long, обязательный).
    *   **Возвращает:** HTTP 200 OK или сообщение об ошибке.

*   **GET /exchangerates:** Получение списка всех обменных курсов.
    *   **Возвращает:** `Collection<ExchangeRateDto>` (список обменных курсов).

*   **GET /exchangerates/{codecode}:** Получение обменного курса по коду пары валют (например, USDGBP).
    *   **{codecode}:** Код пары валют (String, обязательный, 6 символов: код базовой валюты + код целевой валюты).
    *   **Возвращает:** `ExchangeRateDto` (найденный обменный курс) или сообщение об ошибке.

*   **PUT /exchangerates:** Обновление информации об обменном курсе.
    *   **Запрос в теле:** `ExchangeRateDto` (обновленный обменный курс). `id` обязателен.
    *   **Возвращает:** `ExchangeRateDto` (обновленный обменный курс) или сообщение об ошибке.

*   **PATCH /exchangerates/{codecode}:** Обновление только курса обмена для существующей пары валют.
    *   **{codecode}:** Код пары валют (String, обязательный, 6 символов).
    *   **Параметры запроса:**
        *   `rate` (Double, обязательный) - Новый курс обмена.
    *   **Возвращает:** `ExchangeRateDto` (обновленный обменный курс) или сообщение об ошибке.

### Конвертация валют (Exchange)

*   **GET /exchange:** Конвертация валюты.
    *   **Параметры запроса:**
        *   `from` (String, обязательный) - Код базовой валюты.
        *   `to` (String, обязательный) - Код целевой валюты.
        *   `amount` (Double, обязательный) - Сумма для конвертации.
    *   **Возвращает:** `ExchangeConvertedDto` (результат конвертации) или сообщение об ошибке.


# AOP в проекте

В проекте реализовано логирование с использованием AspectJ. Аспекты применяются к контроллерам и сервисам для:

•   Логгирования входа и выхода из методов.
•   Логгирования параметров методов.
•   Логгирования времени выполнения методов.
•   Логгирования исключений.

Логирование разделено на две группы:

•   **Контроллеры:** Общее логирование контроллерных методов.
•   **Сервисы CRUD операций над валютами и обменными курсами:**
    •   Создание
    •   Получение
    •   Обновление
    •   Удаление
    •   Логирование времени выполнения методов.

## Планы на будущее

•   **Аутентификация и авторизация:** 
Реализация полноценной аутентификации и авторизации с использованием Spring Security.

•   **Тестирование:** 
Добавление Unit и Integration тестов для повышения стабильности и надежности кода.

•   **Spring Data JPA:** 
Использование более продвинутых возможностей Spring Data JPA (например, спецификации) для более гибкой работы с данными.

•   **Кэширование:** 
Реализация кэширования (например, используя Spring Cache) для повышения производительности приложения.

•   **Миграция СУБД:** 
Переход на более надежную СУБД, например, PostgreSQL.

•   **Валидация данных:** 
Добавление валидации входящих данных с использованием JSR-303 (Bean Validation) для предотвращения некорректных данных.

•   **Асинхронные операции:** 
Реализация асинхронных операций (например, обновление курсов валют из внешних источников) для повышения отзывчивости приложения.

•   **Документация API:** 
Более подробное документирование API с использованием Swagger/OpenAPI для упрощения взаимодействия с сервисом.

