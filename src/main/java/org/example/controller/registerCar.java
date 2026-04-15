package org.example.controller;

import org.example.dto.CarDTO;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class registerCar extends HttpServlet {
    CarService carService = new CarServiceImpl();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");

        try {
            CarDTO carDTO = new CarDTO(brand, model);
            carService.registerCar(carDTO);
            response.sendRedirect("index.jsp?saved=true");
        } catch (HibernateException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?saved=false");
        }
    }
}
