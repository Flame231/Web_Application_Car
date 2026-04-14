package org.example.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.example.connector.HibernateUtil;
import org.example.model.Car;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class findCar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        EntityManager em = HibernateUtil.getEntityManager();
        CarService carService = new CarServiceImpl(em);

        String id = request.getParameter("id");

        Car car = null;
        try {
            car = carService.findCar(id);
            request.setAttribute("car", car);
            request.setAttribute("found", "true");
            request.setAttribute("id", id);
        } catch (EntityNotFoundException e) {
            request.setAttribute("found", "false"); // Кладем флаг во внутренний атрибут
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
        em.close();
    }
}
