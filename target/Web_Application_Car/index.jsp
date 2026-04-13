<%@ page import="org.w3c.dom.stylesheets.LinkStyle" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Car" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    fieldset {
        width: fit-content; /* Рамка подстроится под ширину формы */
    }

    form button,
    form input[type="submit"] {
        width: 300px; /* Укажите нужную вам ширину в пикселях */
        height: 40px; /* Одинаковая высота для всех */
        padding: 5px;
        margin-top: 10px;
        cursor: pointer;
        box-sizing: border-box; /* Чтобы padding не раздувал кнопку */
    }

    table {
        width: 50%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid black;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }

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
    <title>База данных автомобилей</title>
    <h1>База данных автомобилей</h1>
    <fieldset style="border: 2px solid #ccc; padding: 20px; border-radius: 8px;">
        <h2>Добавление автомобиля в базу</h2>
        <form class="my-form" action="register" method="post">
            Название авто:<input name="name" type="text" required placeholder="Напр: BMW"/></br>
            Тип:<input name="type" type="text" required placeholder="Напр: Седан"/>
            <input type="submit" value="Сохранить автомобиль">
        </form>
    </fieldset>

    <%Car car = (Car) request.getAttribute("car");%>
    <fieldset style="border: 2px solid #ccc; padding: 20px; border-radius: 8px;">
        <form method="post">
            <h2>Поиск записи</h2>
            Введите номер записи:<input type="search" name="id" required placeholder="Напр: 2"
                <% if (request.getAttribute("id") != null) {%>    value="<%=request.getAttribute("id")%>"<%}%>
        />
            <% if ("false".equals(request.getAttribute("found"))) { %>
            <div style="color: red;">Запись не найдена!</div>
            <% } %>


            <input type="submit" value="Найти" formaction="findCar">
            <input type="submit" value="Удалить запись" formaction="removeCar">
            <% if ("true".equals(request.getParameter("deleted"))) { %>
            <div style="color: green; font-weight: bold;">
                Запись успешно удалена!
            </div>
            <% } %>
        </form>


        <% if (car != null) {%>
        <h3>Результаты поиска</h3>
        <p>Номер записи:<%= car.getId()%>
        </p>
        <p>Название:<%= car.getBrand()%>
        </p>
        <p>Тип:<%= car.getModel()%>
        </p>
        <p>Дата создания: <%= car.getCreateDateTime()%>
        </p>
        <p>Дата обновления: <%= car.getUpdateDateTime()%>
        </p>
        <h3>Обновить</h3>

        <%}%>

    </fieldset>


    <fieldset style="border: 2px solid #ccc; padding: 20px; border-radius: 8px;">
        <form action="showAll" method="post">
            <button type="submit">Посмотреть базу автомобилей</button>
        </form>
    </fieldset>

</head>
<body>
</body>
</html>
