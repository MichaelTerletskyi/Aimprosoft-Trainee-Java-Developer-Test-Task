# Trainee Full Stack-Developer test task for Aimprosoft company




# Table of Contents
[Задача](#task_description)<br>
[Структура проекта](#structure)<br>
[Доступные функции](#avaiable_functions)<br>
[Валидация](#validation)<br>
[Для девелоперов](#developers_guide)<br>
[Конфигурация соеденения с базой данных](#configuration)<br>
[Разработчик](#developer)



# <a name="task_description"></a>Задача
![Header Image](src/main/resources/screens/TestTaskDescription.jpg)



# <a name="structure"></a>Структура проекта

* Java 12
* JDBC
* Maven
* javax.servlet 4.0.1
* JSP
* JSTL 1.2
* maven-checkstyle-plugin 3.1.1
* mysql-connector-java 8.0.25
* org.junit.jupiter 5.8.0-M1
* org.hibernate.validator 6.0.10.Final
* javax.validation 2.0.1.Final

<hr>



# <a name="avvailable_functions"></a>Доступные функции
* Просмотр списка Департаментов

![Header Image](src/main/resources/screens/Main%20Page.jpg)

* Создание нового Департамента

![Header Image](src/main/resources/screens/Create%20Department%20Page.jpg)

* Редактирование существующего Департамента

![Header Image](src/main/resources/screens/Edit%20Department%20Page.jpg)

* Просмотр всех сутрудников Департамента

![Header Image](src/main/resources/screens/Employees%20of%20Department%20Page.jpg)

* Просмотр статистики по Департаменту (данная функция не была указана в тестовом задании)

![Header Image](src/main/resources/screens/Details%20of%20Department%20Page.jpg)

* Удаление департамента

* Создание нового Сотрудника

![Header Image](src/main/resources/screens/Create%20Employee%20Page.jpg)

* Редактирование существующего Сотрудника

![Header Image](src/main/resources/screens/Update%20Employee%20Page.jpg)

* Просмотр статистики по Сотруднику (данная функция не была указана в тестовом задании)

![Header Image](src/main/resources/screens/Employee%20DetailsPage.jpg)

* Повышение сотрудника до Главы Департмента (данная функция не была указана в тестовом задании)

* Удаление сотрудника

<hr>



# <a name="validation"></a>Валидация

Данный проект поддерживает валидацию входных данных в соответствии с описанием к заданию.
Валидация происходит на нескольких уровнях. 
Обратите внимание, невалидные данные в любом случае сохраняются на клиенте

(смотреть) [Database Scheme](src/main/resources/sql/database_scheme.sql)


* Для Департамента

Входные данные для Департамента валидируется на уровне сервиса по средствам javax.servlet Filter
(смотреть) [DepartmentFilter](src/main/java/filters/DepartmentFilter.java)

Так же имеется валидация на уровне базы данных

![Header Image](src/main/resources/screens/Department%20Scheme.jpg)

Правила валидации:
1. Title не может быть null
2. Title не может быть пустым
3. Минимальная длинна Title не должна быть короче 1 буквы
4. Максимальная длинна Title не должна быть больше 64 букв

![Header Image](src/main/resources/screens/Department%20title%20empty%20or%20length%20error%20message.jpg)

5. Title может состоять только из латиници или кириллицы

![Header Image](src/main/resources/screens/Department%20title%20regexp%20error%20message.jpg)

6. Title - по умолчанию уникальный, это значит, что вы не сможете создать Департамент, title, которого уже существует в базе данных, то же правило действительно и для редактировния

![Header Image](src/main/resources/screens/Department%20unique%20check%20error%20message.jpg)

7. Description может быть пустым или иметь до 255 любых символов

![Header Image](src/main/resources/screens/Department%20description%20length%20error%20message.jpg)



* Для Сотрудника

Входные данные для Сотрудника валидируется на уровне сервиса по средствам javax.servlet Filter

(смотреть) [EmployeeFilter](src/main/java/filters/EmployeeFilter.java)

Так же имеется валидация на уровне базы данных

![Header Image](src/main/resources/screens/Employee%20Scheme.jpg)


Правила валидации:
1. First Name не может быть null
2. First Name не может быть пустым
3. Минимальная длинна First Name не должна быть короче 1 буквы
4. Максимальная длинна First Name не должна быть больше 32 букв

![Header Image](src/main/resources/screens/Employee%20firstName%20length%20error%20message.jpg)

5. firstName может состоять только из латиници или кириллицы

![Header Image](src/main/resources/screens/Employee%20firstName%20regexp%20error%20message.jpg)

6. Last Name не может быть null
7. Last Name не может быть пустым
8. Минимальная длинна Last Name не должна быть короче 1 буквы
9. Максимальная длинна Last Name не должна быть больше 32 букв

![Header Image](src/main/resources/screens/Employee%20lastName%20length%20error%20message.jpg)

10. Last Name может состоять только из латиници или кириллицы

![Header Image](src/main/resources/screens/Employee%20lastName%20regexp%20error%20message.jpg)

11. Email не может быть null
12. Email не может быть пустым

![Header Image](src/main/resources/screens/Employee%20email%20empty%20error%20message.jpg)

13. Email должен быть валидным

![Header Image](src/main/resources/screens/Employee%20email%20incorrect%20error%20message.jpg)

14. Email - по умолчанию уникальный, это значит, что вы не сможете создать Сотрудника, email, которого уже существует в базе данных, то же правило действительно и для редактировния

![Header Image](src/main/resources/screens/Employee%20email%20unique%20check%20error%20message.jpg)

15. Salary Per Hour не может null

![Header Image](src/main/resources/screens/Employee%20salaryPerHour%20cannot%20be%20null.jpg)

16. Salary Per Hour не может быть меньше 1 $

![Header Image](src/main/resources/screens/Employee%20salaryPerHour%20min%20error%20message.jpg)

17. Salary Per Hour не может превышать число в 99999 $

![Header Image](src/main/resources/screens/Employee%20salaryPerHour%20max%20error%20message.jpg)

18. Date Of Birth не может null

![Header Image](src/main/resources/screens/Employee%20dateOfBirth%20null%20error%20mesage.jpg)

19. Date Of Birth не может быть в будущем

![Header Image](src/main/resources/screens/Employee%20dateOfBirth%20future%20error%20message.jpg)
<hr>




# <a name="developers_guide"></a>Для девелоперов
Для успешного запуска проекта вам понадобится установить :

* Java 8+ (Рекомендую скачать JDK по этой ссылке https://openjdk.java.net/)
* Tomcat (Ссылка https://tomcat.apache.org/download-90.cgi)
* MySQL (Ссылка https://dev.mysql.com/downloads/installer/)

<hr>




# <a name="configuration"></a>Конфигурация соеденения с базой данных
(смотреть) [DatabaseConnectionConfig](src/main/resources/databaseConfig.properties)


# <a name="developer"></a>Разработчик

GitHub :octocat: [Michael Terletskyi](https://github.com/MichaelTerletskyi)