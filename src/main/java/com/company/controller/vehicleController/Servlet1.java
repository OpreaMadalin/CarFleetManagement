package com.company.controller.vehicleController;

import com.company.controller.databaseController.MongoController;
import org.bson.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class Servlet1 extends HttpServlet {


    @Override
    @GetMapping("/getVehicles1")
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MongoController mc = new MongoController();
        ArrayList<Document> cars = mc.getVehicles();
        request.setAttribute("messageList", cars);

        request.getRequestDispatcher("/inde.jsp").forward(request, response);
    }


}
