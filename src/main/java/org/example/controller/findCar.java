package org.example.controller;

import jakarta.persistence.EntityNotFoundException;
import org.example.dto.CarDTO;
import org.example.service.CarService;
import org.example.service.CarServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class findCar extends HttpServlet {
    CarService carService = new CarServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if ("editPage".equals(request.getParameter("action"))) {
            String searchWord = request.getParameter("id");
            request.setAttribute("car", carService.findCar(searchWord));
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/editPage.jsp");
            dispatcher.forward(request, response);
        } else if ("indexPage".equals(request.getParameter("action"))) {
            CarService carService = new CarServiceImpl();
            String searchWord = request.getParameter("id");
            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(searchWord);
            boolean isNumber = matcher.find();
            if (isNumber) {
                request.setAttribute("car", carService.findCar(searchWord));
                request.setAttribute("id", searchWord);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            } else {
                List<CarDTO> carSet = carService.showCarsByBrand(searchWord);
                request.setAttribute("carList", carSet);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/CarsDatabase.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
