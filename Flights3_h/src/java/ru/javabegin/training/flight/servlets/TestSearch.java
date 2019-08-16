/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.javabegin.training.flight.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.javabegin.training.flight.database.CityDB;
import ru.javabegin.training.flight.database.FlightDB;
import ru.javabegin.training.flight.objects.Flight;
import ru.javabegin.training.flight.spr.objects.City;

/**
 *
 * @author Tim
 */
@WebServlet(name = "TestSearch", urlPatterns = {"/TestSearch"})
public class TestSearch extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet TestSearch</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet TestSearch at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            
            City c1 = CityDB.getInstance().getCity(1);
            City c2 = CityDB.getInstance().getCity(2);
            City c3 = CityDB.getInstance().getCity(3);
            
            long date = 1328418000000L;
            
            ArrayList<Flight> list = FlightDB.getInstance().getFlights(date, c3, c2);
            
            for (Flight flight : list) {
                System.out.println(flight.getAircraft().getName());
            }
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
