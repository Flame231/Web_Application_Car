package org.example.controller;

import jakarta.persistence.EntityManager;
import org.example.connector.HibernateUtil;
import org.example.model.Car;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class updateCar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        EntityManager em = HibernateUtil.getEntityManager();
        CarService carService = new CarServiceImpl(em);
        String id = request.getParameter("id");
        Car car = carService.findCar(Integer.parseInt(id));
        car.setBrand(request.getParameter("brand"));
        car.setModel(request.getParameter("model"));
        carService.updateCar(car);
        response.sendRedirect("index.jsp?updated=true");
        em.close();
    }
}
