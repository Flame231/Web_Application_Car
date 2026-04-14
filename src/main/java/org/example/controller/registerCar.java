package org.example.controller;

import jakarta.persistence.EntityManager;
import org.example.connector.HibernateUtil;
import org.example.model.Car;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class registerCar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        EntityManager em = HibernateUtil.getEntityManager();
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        CarService carService = new CarServiceImpl(em);
        try {
            carService.registerCar(new Car(name, type));
            response.sendRedirect("index.jsp?saved=true");
        } catch (HibernateException e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?saved=false");
        } finally {
            em.close();
        }
    }
}
