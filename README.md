# Приложение для получения информации о пользователе VK

## Обзор

Сервис является RESTful API приложением для получения ФИО пользователя VK по его ID, а также состояния членства в группе по ID группы. Согласно ТЗ реализованы минимальные требования и часть дополнительных заданий

### Сервис реализует (минимальные требования):
- Получение ФИО пользователя;
- Получение состояния членства пользователя в группе;
- OpenAPI - документацию;
- Unit & Integration tests;
- Обработку ошибок от VK API и сервиса с выдачей ответа в формате JSON с полем message;
### Сервис реализует дополнительные требования:
- Данные, получаемые из сервиса, кэшируются на стороне приложения с помощью Spring Cache. Кэш хранится 1 минуту для актуализации данных о пользователе. Ключами являются `user_id, group_id`;

## Стек технологий:
- Java 17
- Spring Boot
- VK API
- Maven
- Lombok
- Log4j
- JUnit 5
- OpenAPI Swagger

## Запуск
### Внимание! Для получения ответа от VK нужно иметь рабочее приложение VK (ID указывается в `application.properties`), а также сервисный ключ доступа (https://dev.vk.com/mini-apps/management/settings)
1. Склонировать репозиторий
2. Запустить приложение UserInfoProcessingApplication

## Endpoints
`GET gpn/get_info`
### Параметры:
Заголовок `vk_service_token` - получить в своем приложении в настройках (см. документацию VK API)
JSON-объект с параметрами `user_id, groupd_id` - ID пользователя и группы (числовые типы)
### Ответ:
JSON-объект с параметрами `last_name", first_name, middle_name` (числовые типы); `member` (логический тип)
### Пример запроса:
<img width="543" alt="image" src="https://user-images.githubusercontent.com/90566014/201539410-62a1e0c0-c08c-4850-b978-ae5f994627fe.png">

### Пример ответа:
<img width="539" alt="image" src="https://user-images.githubusercontent.com/90566014/201539421-fd35a813-e850-48ac-8c69-dc3aa9028c33.png">

## Примечание
В тестах проекта указан vkServiceToken автора, а в `application.properties` - ID работающего приложения автора. Эти данные можно использовать для работы с проектом.

## Заключение
Сервис реализует мнимальные требования проекта, а так же архитектура позволяет резместить приложение в облаке. Сам проект может быть фукнционально дополнен и расширен.

