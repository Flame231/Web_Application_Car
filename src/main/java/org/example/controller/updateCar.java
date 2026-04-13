package org.example.controller;

import org.example.connector.HibernateUtil;
import org.example.dao.CarDAO;
import org.example.dao.CarDAOImpl;
import org.example.model.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class updateCar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        CarDAO carDAO = new CarDAOImpl(HibernateUtil.getEntityManager());
       String id = request.getParameter("id");
        Car car = carDAO.get(Integer.parseInt(id));
        car.setBrand(request.getParameter("brand"));
        car.setModel(request.getParameter("model"));
        carDAO.update(car);
        response.sendRedirect("index.jsp?updated=true");
        }
    }
