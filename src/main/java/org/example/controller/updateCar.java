package org.example.controller;

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

public class updateCar extends HttpServlet {
    CarService carService = new CarServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        if("editPage".equals(request.getParameter("action"))){





            Integer id = Integer.parseInt(request.getParameter("id"));
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");

            CarDTO carDTO = new CarDTO(id, brand, model);

           CarDTO carDTO1 = (CarDTO)request.getAttribute("car");
           carDTO1.setBrand(brand);
           carDTO1.setModel(model);
           request.setAttribute("car",carDTO1);
            carService.updateCar(carDTO);



            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/showAllCars");
            dispatcher.forward(request,response);
        }

/*        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Integer id = Integer.parseInt(request.getParameter("id"));

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        CarDTO carDTO = new CarDTO(id, brand, model);

        carService.updateCar(carDTO);
        response.sendRedirect("index.jsp?updated=true");*/
    }
}
