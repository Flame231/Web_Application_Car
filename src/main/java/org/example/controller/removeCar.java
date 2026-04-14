package org.example.controller;

import jakarta.persistence.EntityManager;
import org.example.connector.HibernateUtil;
import org.example.model.Car;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;
import org.w3c.dom.ls.LSOutput;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class removeCar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EntityManager em = HibernateUtil.getEntityManager();
        CarService carService = new CarServiceImpl(em);
        String id = request.getParameter("id");
        Car car = carService.findCar(Integer.parseInt(id));
        carService.removeCar(car);
        response.sendRedirect("index.jsp?deleted=true");
        em.close();
    }
}
