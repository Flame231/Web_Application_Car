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
public class removeCar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        CarDAO carDAO = new CarDAOImpl(HibernateUtil.getEntityManager());
        String id = request.getParameter("id");
        carDAO.delete(Integer.parseInt(id));
        response.sendRedirect("index.jsp?deleted=true");
    }
}
