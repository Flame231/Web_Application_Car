package org.example.controller;

import org.example.model.Car;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;

import javax.naming.Context;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class showAllCars extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        CarService carService = new CarServiceImpl();
        List<Car> carSet = carService.showAllCars();
        request.setAttribute("carList",carSet);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CarsDatabase.jsp");
        dispatcher.forward(request,response);
    }
}
