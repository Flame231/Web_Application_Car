package org.example.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.example.connector.HibernateUtil;
import org.example.dto.CarDTO;
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
    CarService carService = new CarServiceImpl();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if("editPage".equals(request.getParameter("action"))){
            String searchWord = request.getParameter("id");
            request.setAttribute("car", carService.findCar(searchWord));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editPage.jsp");
            dispatcher.forward(request,response);
        }
/*
        CarService carService = new CarServiceImpl();
        String searchWord = request.getParameter("id");
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(searchWord);
        boolean b = matcher.find();
        if (b) {

            try {

                request.setAttribute("car", carService.findCar(searchWord));
                request.setAttribute("found", "true");
                request.setAttribute("id", searchWord);
            } catch (EntityNotFoundException e) {
                request.setAttribute("found", "false"); // Кладем флаг во внутренний атрибут
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } else {
            List<CarDTO> carSet = carService.showCarsByBrand(searchWord);
            request.setAttribute("carList", carSet);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CarsDatabase.jsp");
            dispatcher.forward(request, response);
        }*/

    }
}
