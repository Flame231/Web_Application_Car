<%--
  Created by IntelliJ IDEA.
  User: System Administrator
  Date: 10.04.2026
  Time: 14:18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    /* Стилизуем форму как сетку */
    .my-form {
        display: grid;
        grid-template-columns: 120px 300px; /* 1-я колонка для текста, 2-я для полей */
        gap: 15px; /* Расстояние между строками и столбцами */
        align-items: center; /* Центрируем текст по вертикали относительно инпута */
    }

    .my-form input {
        padding: 5px;
        width: 100%; /* Заполнит свои 300px из сетки */
    }

    .my-form button {
        grid-column: 1 / span 2; /* Кнопка растянется на обе колонки */
        width: fit-content;
    }
</style>
<html>
<head>
    <title>Title</title>
    <h1>База данных автомобилей</h1>
    <form class="my-form" action="save" method="get">
        Название авто:<input name="name" type="text"/></br>
        Тип:<input name="type" type="text"/>
        <input type="submit">
    </form>
</head>

<body>

</body>
</html>
