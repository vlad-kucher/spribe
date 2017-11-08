[![Codacy Badge](https://api.codacy.com/project/badge/Grade/44295576ab5e44c1b9b3524d78b69b67)](https://www.codacy.com/app/vlad-kucher/spribe?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=vlad-kucher/spribe&amp;utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/vlad-kucher/spribe.svg?branch=master)](https://travis-ci.org/vlad-kucher/spribe)
[![Dependency Status](https://www.versioneye.com/user/projects/5a0236cd15f0d72ed30311b0/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5a0236cd15f0d72ed30311b0)

*Задание:*

1.	**Word Counter** - написать сервис, которому можно передавать слова. По запросу он должен возвращать число, которое показывает, сколько раз было передано определенное слово без учета регистра (ignoring case). Сервис должен корректно работать в многопоточной среде, т.е. быть thread-safe.
2.	**Find Opponent** - написать сервис, которому можно передавать игроков, которые готовы к игре. У игрока есть имя и рейтинг (double). По запросу сервис должен возвращать оппонента для определенного игрока, по принципу самый ближайший по рейтингу (как в большую, так и в меньшую сторону); при этом найденный оппонент должен быть удален из списка игроков, которые готовы к игре.

-----------------------------
**Build:**

`mvn jetty:run`

-----------------------------
**REST API:**

REST uri     |    HTTP метод | Принимает | Возвращает
------------ | -------------- | ---------------  | ---------------
`<domain>/rest/words/{word}` | GET | path variable `{word}` - слово | сколько раз было передано `{word}`
`<domain>/rest/words/{word}`| PUT | path variable `{word}` - слово | - 
`<domain>/rest/players`| PUT | request body - игрок в формате JSON | - 
`<domain>/rest/players`| POST | request body - игрок в формате JSON | возвращает ближайшего по рейтингу оппонента для переданного игрока, если противников нет - 404

-----------------------------
**Примеры CURL:**

* Получаем count для слова "test":

`curl http://localhost:8080/rest/words/test`
* Передаем слово "test":

`curl -X PUT http://localhost:8080/rest/words/test`
* Добавляем игрока готового к игре:

`curl -X PUT -d '{"name":"player1", "rating":1.1}' -H 'Content-Type: application/json' http://localhost:8080/rest/players`
* Получаем оппонента для переданного игрока:

`curl -X POST -d '{"name":"player2", "rating":2}' -H 'Content-Type: application/json' http://localhost:8080/rest/players`