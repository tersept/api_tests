# Дипломный проект по API тестированию для [reqres.in](https://reqres.in/)

## Содержание:

* [Технологии и инструменты](#rocket-технологии-и-инструменты)
* [Реализованные проверки](#scroll-реализованные-проверки)
* [Сборка в Jenkins](#-jenkins-job)
* [Allure отчет](#earth_africa-Allure-отчет)
* [Отчет в Telegram](#-уведомление-в-telegram-при-помощи-бота)

## :rocket: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/Github.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="images/Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="images/Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/Allure_Report.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://rest-assured.io/"><img alt="Rest-assured" height="50" src="images/rest-assured.svg" width="70"/></a>
<a href="https://www.jenkins.io/"><img src="images/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
</p>

## :scroll: Реализованные-проверки

✓ Тест на успешное создание пользовател

✓ Тест на успешную регистрацию

✓ Тест на безуспешную регистрацию

✓ Тест на успешную авторизацию

✓ Тест на безуспешную авторизацию

✓ Тест на присутствие пользователя в выдаче

✓ Тест на проверку количества элементов на выдаче

## Сборка в <a  target="_blank" href ="https://jenkins.autotests.cloud/job/apiTests-diplom/">Jenkins:</a>

![](images/jenkins.jpg)

## <img src="images/Allure_Report.svg" width="25" height="25"  alt="Allure"/> Отчет в <a target="_blank" href="https://jenkins.autotests.cloud/job/apiTests-diplom/allure/">Allure report</a>

### Главное окно

![](images/allureMain.jpg)

### Окно с тестовыми кейсами

![](images/allureTest.jpg)

### Окно с графиками

![](images/allureGraphics.jpg)

## <img src="images/Telegram.svg" width="25" height="25"  alt="Allure"/> Уведомление в Telegram при помощи бота

После завершения тестов отчет о прохождении приходит в Telegram с помощью заранее созданного бота

![]()