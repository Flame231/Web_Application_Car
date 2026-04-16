package org.example.controller;

import org.example.dto.CarDTO;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class registerCar extends HttpServlet {
    CarService carService = new CarServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");

        CarDTO carDTO = new CarDTO(brand, model);
        carService.registerCar(carDTO);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);

    }
}
