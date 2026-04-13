package org.example.controller;

import org.example.connector.HibernateUtil;
import org.example.dao.CarDAO;
import org.example.dao.CarDAOImpl;
import org.example.model.Car;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Hello world!
 *
 */
public class findCar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CarDAO carDAO = new CarDAOImpl(HibernateUtil.getEntityManager());
       String id = request.getParameter("id");
        Car car = carDAO.get(Integer.parseInt(id));
        if (car == null) {
            request.setAttribute("found", "false"); // Кладем флаг во внутренний атрибут
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else{
            request.setAttribute("car",car);
            request.setAttribute("id",id);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request,response);
        }
    }
}
