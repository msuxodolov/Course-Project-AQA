# Курсовой проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»
## Бизнес-часть    
![/](service.png)    
Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

1. Обычная оплата по дебетовой карте.
2. Уникальная технология: выдача кредита по данным банковской карты.

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:
- сервису платежей, далее Payment Gate;
- кредитному сервису, далее Credit Gate.  
Приложение в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом.   
Данные карт сохранять не допускается.

## Процедура запуска:
- Клонировать проект командой git clone;
- Открыть приложение IntelliJ IDEA;
- Запустить контейнер командой docker-compose up;
- Ввести в терминале команду "java -jar artifacts/aqa-shop.jar"
- Запустить в терминале команду ./gradlew clean test;
- После прогона тестов запустить команду ./gradlew allureServe.

## Отчет по запуску Allure   
![Allure](fkk.png)