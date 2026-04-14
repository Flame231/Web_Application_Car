package org.example.controller;

import jakarta.persistence.EntityManager;
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
import java.util.List;

public class showAllCars extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        EntityManager em = HibernateUtil.getEntityManager();
        CarService carService = new CarServiceImpl(em);
        List<Car> carSet = carService.showAllCars();
        request.setAttribute("carList", carSet);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CarsDatabase.jsp");
        dispatcher.forward(request, response);
        em.close();
    }
}
