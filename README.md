#Задача:

###Написать набор сервисов для SOA WEB приложения. 
###Приложение должно реализовывать такие сервисы:
- создание пользователя (регистрация). 
  Должен создаваться пользователь с именем и паролем.
  Имя должно быть уникальным.
  Сразу после создания текущий пользователь должен авторизоваться в том-же запросе.

- Незарегистрированный пользователь должен иметь возможность проверить доступность имени через сервис (валидации).

- Созданный в системе пользователь должен иметь возможность авторизоваться, передав в сервис имя и пароль.
  Количество неудачных попыток авторизации - не должно превышать 10 за 1 час и сбрасываться при успешной авторизации.

- авторизованный пользователь должен иметь возможность
  - создавать/редактировать/удалять животных __[Вид(из списка-справочника), дата рождения, пол, Кличка(уникальна)]__.
  - получить список своих животных.
  - получить детали любого животного по `id`.

Все взаимодействие должно происходить с использованием `JSON` формата данных.

Все ошибки должны иметь номера и текстовые расшифровки.
Ошибки, в случае возникновения, так-же должны передаваться в виде `JSON` объекта.

В качестве базы данных можно взять `PostgreSQL`, `Mongo` или любую InMemory базу (но, тогда jar-ник надо добавить в архив).

Рекомендуется использовать `Spring` и `Hibernate` (можно с `JPA`).




- __Фронт приложения не нужно__
---
 Приложение деплоил в Tomcat, тестировал в SoapUI.
 С логином через JSON до этого момента не сталкивался, 
 мне показался интересным вариант с JWT токеном,
 поэтому решил сделать через него, 
 при тестировании добавлял header "Authorization". 
 Но как мне кажется, можно сделать лучше, 
 на момент сдачи смотрю https://youtu.be/VVn9OG9nfH0 этот вариант.
 Тоже самое с ограничением попыток логина.
 Хотел добавить passwordEncoder, 
 но в задании такого требования не было, поэтому решил не заморачиваться, 
 на мой взгляд, и так затянул с выполнением.
 