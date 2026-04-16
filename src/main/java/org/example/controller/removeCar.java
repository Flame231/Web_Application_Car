package org.example.controller;

import org.example.service.CarService;
import org.example.service.CarServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class removeCar extends HttpServlet {
    CarService carService = new CarServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        carService.removeCar(id);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/showAllCars");
        dispatcher.forward(request, response);

    }
}
