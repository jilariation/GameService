# GameService

GameService - мой первый полноценный пет-проект, сервис для обслуживания игрового или банковского приложения.

## Идея
Идея заключается в том, чтобы написать сервис с простым, но удобным и полезным функцианалом, который "примерно" повторяет
работу игрового или банковского приложения, которое совершает транзакции.

## Объем MVC
Я, как новый игрок (пользователь), могу зарегистрироваться, авторизоваться, посмотреть свой профиль, совершить транзакцию дебита
или кредита

## Как это должно работать
Основываясь на паттерне MVC, ожидается следующее поведение:
- При входе в систему игрок должен зарегистрироваться или авторизоваться
- Игрок может посмотреть свой профиль, где указана информация об его балансе
- Игрок может совершить транзакцию дебита, если дебит не превышает значение его баланса
- Игрок может совершить транзакцию кредита, если кредит не является отрицателным числом

## Используемые технологии
- **Spring Boot Framework** как каркас приложения
- **Postgresql** как основная база данных, в которой хранятся данные о пользователях, их истории и транзакций
- **Liquibase** как основной инструмент миграции
- **Model Mapper** как упроещение конвертации в DTO и наоборот
- **Testсontainers** как библиотека для тестирования приложения в специальном контейнере

# Принцип работы

## Docker
Запуск приложение сопровождается запуском контейнера Docker. Приложение разворачивается на локальном сервере на 8080 порту.
```yaml
version: "3.1"

services:
  gs-db:
    container_name: dev-gs-db
    image: postgres:16-alpine3.19
    environment:
      POSTGRES_DB: "dev_gs_db"
      POSTGES_USER: "postgres"
      POSTGRES_PASSWORD: "postgres"
    volumes:
      - "./postgres_data:/var/lib/postgresql/date/"
    ports:
      - "5432:5432"
```

## Регистрация пользователя
Для регистриции пользователя создается следющий запрос (http://localhost:8080/player/reg). Запрос является POST.

Далее посылается JSON с такими данными:
```json
{
    "mail": "alex@mail.com",
    "password": "123456"
}
```
После приходит ответ с кодом 200, а в базе данных появляется соответсвующая запись:

![Reg SQL ](https://github.com/jilariation/GameService/assets/117482776/d147d4de-4e90-4a50-b2fb-db35e1177bbd)

## Авторизация пользователя
Для авторизации пользователя создается следующий запрос (http://localhost:8080/player/log). Запрос является POST.

Далее посылается JSON с такими данными:
```json
{
    "mail": "alex@mail.com",
    "password": "123456"
}
```

После приходит ответ с кодом 200, что означает успешную авторизацию

## Информация о профиле
Для вывода информации о пользователе формируется такой запрос (http://localhost:8080/player/2). Запрос является GET.

В ответ на этот запрос присылается JSON с такими данными:
```json
{
    "mail": "alex@mail.com",
    "password": "123456",
    "balance": 100
}
```

## Транзакция дебита
Для совершения транзакции дебита формируется такой запрос (http://localhost:8080/player/2/transaction/debit). 
Запрос является POST

Далее посылается JSON с такими данными:
```json
{
    "id": 3,
    "value": 10,
    "transactionType": "DEBIT"
}
```

После приходит ответ с кодом 200 и соответствующая запись в базе данных:

![Debit SQL](https://github.com/jilariation/GameService/assets/117482776/fb06307f-e3ad-4ab4-8bcf-9e70c6133e91)

## Транзакция кредита
Для совершения транзакции кредита формируется такой запрос (http://localhost:8080/player/2/transaction/credit).
Запрос является POST

Далее посылается JSON с такими данными:
```json
{
    "id": 4,
    "value": 10,
    "transactionType": "CREDIT"
}
```

После приходит ответ с кодом 200 и соответствующая запись в базе данных:

![Credit SQL](https://github.com/jilariation/GameService/assets/117482776/261cefbd-3a5a-447d-b524-0a8870dd3881)

## История пользователя
Для вывода истории пользователя формируется запрос (http://localhost:8080/player_history/2). Запрос является GET.

В этот на этот запрос присылается JSON с такими данными:
```json
[
    {
        "id": 2,
        "whatPlayerDoing": "REG"
    },
    {
        "id": 2,
        "whatPlayerDoing": "LOG"
    },
    {
        "id": 2,
        "whatPlayerDoing": "INFO"
    },
    {
        "id": 2,
        "whatPlayerDoing": "DEBIT"
    },
    {
        "id": 2,
        "whatPlayerDoing": "CREDIT"
    }
]
```

# Как развернуть приложение на своем устройстве
* Скачиваете репозиторий проекта(https://github.com/jilariation/GameService)
* В файле docker-compose.yml прописаны все параметры контейнера, можете установить свои логин, пароль и название БД
* Не забудьте создать и запустить контейнер с помощью команды: **docker-compose up -d**
* Соберите приложение с помощью Maven
* Вауля, все работает! :D

# Дополнительная информация
* В случае неясноти или неувереннности в работе приложения перейдите на страницу (http://localhost:8080/swagger-ui/index.html). Там описана вся документация с использованием Swagger UI
* Связаться со мной по поводу рекомендации и оценки проекта можно через почту: **aleksandrmarygin18@gmail.com** 