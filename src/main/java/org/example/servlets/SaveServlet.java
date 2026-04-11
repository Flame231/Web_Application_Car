package org.example.servlets;

import jakarta.persistence.EntityManager;
import org.example.Entity.Car;
import org.example.dao.CarDAO;
import org.example.dao.CarDAOImpl;
import org.example.utils.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class SaveServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        EntityManager entityManager = HibernateUtil.getEntityManager();
        CarDAO carDAO = new CarDAOImpl(entityManager);
        carDAO.save(new Car(name, type));
        response.sendRedirect("index.jsp");
    }
}
