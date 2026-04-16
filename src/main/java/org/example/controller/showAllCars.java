package org.example.controller;

import org.example.service.CarService;
import org.example.service.CarServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class showAllCars extends HttpServlet {
    CarService carService = new CarServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("carList", carService.showAllCars());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CarsDatabase.jsp");
        dispatcher.forward(request, response);
    }
}
