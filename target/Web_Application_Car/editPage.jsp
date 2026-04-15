<%@ page import="org.example.dto.CarDTO" %><%--
  Created by IntelliJ IDEA.
  User: System Administrator
  Date: 15.04.2026
  Time: 14:59:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String carId = "";
    String carBrand = "";
    String carModel = "";
    String action = "registerCar";
    String action2 = "updateCar";
    if (request.getAttribute("car") != null) {
        action = action2; %>
<fieldset style="border: 2px solid #ccc; padding: 20px; border-radius: 8px; max-width: 400px;">
    <h2>Добавление автомобиля в базу</h2>
    <form class="my-form" action="<%=action%>" method="post">


        <% CarDTO carDTO = (CarDTO) request.getAttribute("car");
            carId = String.valueOf(carDTO.getId());
            carBrand = carDTO.getBrand();
            carModel = carDTO.getModel();
        }
        %>

        <p>Номер записи:</p>
        <div></div>
        <p><%=carId%>
        </p>
        <input type="hidden" name="id" value="<%=carId%>"/>
        <input type="hidden" name="action" value="editPage">
        Марка:<br/>
        <div>
            <input name="brand" type="text" value="<%=carBrand%>" required
                   placeholder="Напр: BMW" style="width: 100%; margin-bottom: 10px;"/>
        </div>
        Модель:<br/>
        <div>
            <input name="model" type="text" value="<%=carModel%>" required placeholder="Напр: Седан"
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
</body>
</html>
