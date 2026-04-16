<%@ page import="org.w3c.dom.stylesheets.LinkStyle" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.model.Car" %>
<%@ page import="org.example.dto.CarDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String carId = "";
    String carBrand = "";
    String carModel = "";
    String action = "registerCar";
    String action2 = "updateCar";
    CarDTO carDTO = (CarDTO) request.getAttribute("car");
    if (carDTO != null) {
        action = action2;
        carId = String.valueOf(carDTO.getId());
        carBrand = carDTO.getBrand();
        carModel = carDTO.getModel();
    }
%>
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
    <fieldset style="border: 2px solid #ccc; padding: 20px; border-radius: 8px; max-width: 400px;">
        <h2>Добавление автомобиля в базу</h2>
        <form class="my-form" action="registerCar" method="post">
            Марка:<br/>
            <div>
                <input name="brand" type="text" required placeholder="Напр: BMW"
                       style="width: 100%; margin-bottom: 10px;"/>
            </div>
            Модель:<br/>
            <div>
                <input name="model" type="text" required placeholder="Напр: Седан"
                       style="width: 100%; margin-bottom: 10px;"/>
            </div>

            <!-- Кнопку в отдельный блок -->
            <div style="margin-top: 10px; display: block;">
                <input type="submit" value="Сохранить автомобиль" )/>
            </div>

            <% if ("true".equals(request.getParameter("saved"))) { %>
            <!-- Сообщению даем верхний отступ, чтобы оно не липло к кнопке -->
            <div></div>
            <div style="color: green; font-weight: bold;">
                Запись сохранена!
            </div>
            <% } %>
        </form>
    </fieldset>


    <fieldset style="border: 2px solid #ccc; padding: 20px; border-radius: 8px;  display: flex;
    flex-direction: column;
    gap: 10px;">
        <form method="post" style="display: flex; flex-direction: column; gap: 10px;">
            <h2>Поиск записи</h2>

            <!-- Используем flex для выравнивания текста и инпута внутри строки -->
            <div style="display: flex; justify-content: space-between; align-items: center; gap: 10px;">
                <span>Введите номер записи или марку автомобиля:</span>
                <input type="search" name="id" <% if (request.getAttribute("id") != null)
                {%> value="<%= request.getAttribute("id")%>"<%}%> required style="flex-grow: 1;">
            </div>

            <div><input type="submit" value="Найти" formaction="findCar" style="width: 100%;"></div>


            <% if (carDTO != null) { %>
            <div><input type="submit" value="Удалить запись" formaction="removeCar" style="width: 100%;"></div>
            <h3>Обновление записи</h3>
            <div><input type="submit" value="Обновить" formaction="updateCar" style="width: 100%;"></div>


            <!-- Одинаковая ширина для подписей (label) выровняет поля -->
            <div style="display: flex; align-items: center; gap: 10px;">
                <p>Номер записи:</p>
               <p><%=request.getAttribute("id")%></p>


            </div>


            <div style="display: flex; align-items: center; gap: 10px;">
                <span style="min-width: 60px;">Марка:</span>
                <input type="text" name="brand" value="<%=carBrand%>" style="flex-grow: 1;">
                <span style="min-width: 60px;">Модель:</span>
                <input type="text" name="model" value="<%=carModel%>" style="flex-grow: 1;">
            </div>
            <%}%>
            <% if ("true".equals(request.getParameter("updated"))) { %>
            <div style="color: green; font-weight: bold;">
                Запись обновлена!
            </div>
            <% } %>
            <% if (carDTO == null && request.getParameter("id") != null) { %>
            <div style="color: red;">Запись не найдена!</div>
            <% } %>


            <% if ("true".equals(request.getParameter("deleted"))) { %>
            <div style="color: green; font-weight: bold;">
                Запись удалена!
            </div>
            <% } %>
        </form>


        <% if (carDTO != null) {%>
        <h3>Результаты поиска</h3>
        <p>Номер записи:<%= carDTO.getId()%>
        </p>
        <p>Марка:<%= carDTO.getBrand()%>
        </p>
        <p>Модель:<%= carDTO.getModel()%>
        </p>
        <p>Дата создания: <%= carDTO.getCreateDateTime()%>
        </p>
        <p>Дата обновления:
            <%if (carDTO.getUpdateDateTime() != null) {%>
            <%= carDTO.getUpdateDateTime()%>
            <%}%>
        </p>
        <h3>Обновить</h3>

        <%}%>

    </fieldset>

    <fieldset style="border: 2px solid #ccc; padding: 20px; border-radius: 8px;">
        <form action="showAllCars" method="post">
            <button type="submit">Посмотреть базу автомобилей</button>
        </form>
    </fieldset>

</head>
<body>
</body>
</html>
