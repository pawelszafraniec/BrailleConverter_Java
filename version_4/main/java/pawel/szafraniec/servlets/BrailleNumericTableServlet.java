/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pawel.szafraniec.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pawel.szafraniec.model.BrailleToTextModel;

/**
 * Servlet displaying Braille alphabet in numeric representation
 * 
 * @author Pawel Szafraniec
 * @version 4.0
 */
@WebServlet(name = "BrailleNumericTableServlet", urlPatterns = {"/BrailleNumericTableServlet"})
public class BrailleNumericTableServlet extends HttpServlet {

    /**
     * BrailleToTextModel class object
     */
    private BrailleToTextModel brailleToTextModel;

    /**
     * Class constructor
     */
    public BrailleNumericTableServlet() {
        brailleToTextModel = new BrailleToTextModel();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     * 
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            TreeMap<String, String> sortedMap = new TreeMap<>(brailleToTextModel.getMap());
            Set<Map.Entry<String, String>> mapEntry = sortedMap.entrySet();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SessionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<table >");
            out.println("<tr>");
            out.println("<th> Number representation </th>");
            out.println("<th> Braille symbol </th>");
            out.println("</tr>");
            mapEntry.forEach(entry -> {
                out.println("<tr>");
                out.println("<td>" + entry.getKey() + "</td>");
                out.println("<td>" + entry.getValue() + "</td>");
                out.println("</tr>");
            });
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

            request.setAttribute("map", sortedMap);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
