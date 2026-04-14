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
import java.nio.file.PathMatcher;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class findCar extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        EntityManager em = HibernateUtil.getEntityManager();
        CarService carService = new CarServiceImpl(em);

        String searchWord = request.getParameter("id");

        Pattern pattern = Pattern.compile(searchWord);
        Matcher matcher = pattern.matcher("\\d+");
        if (matcher.find()) {
            Car car = null;
            try {
                car = carService.findCar(searchWord);
                request.setAttribute("car", car);
                request.setAttribute("found", "true");
                request.setAttribute("id", searchWord);
            } catch (EntityNotFoundException e) {
                request.setAttribute("found", "false"); // Кладем флаг во внутренний атрибут
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } else {
            List<Car> carSet = carService.showCarsByBrand(searchWord);
            request.setAttribute("carList", carSet);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CarsDatabase.jsp");
            dispatcher.forward(request, response);
        }


        em.close();
    }
}
