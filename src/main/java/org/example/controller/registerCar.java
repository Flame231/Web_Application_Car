package org.example.controller;

import org.example.connector.HibernateUtil;
import org.example.dao.CarDAOImpl;
import org.example.model.Car;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class registerCar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        CarService carService = new CarServiceImpl(new CarDAOImpl(HibernateUtil.getEntityManager()));
        carService.registerCar(new Car(name, type));
        response.sendRedirect("index.jsp?saved=true");
    }
}
