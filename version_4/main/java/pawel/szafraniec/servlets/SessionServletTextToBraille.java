package pawel.szafraniec.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet handling session for text to Braille conversion. Displays session
 * attributes in html table. Used in displaying history of performed operations
 *
 * @author Pawel Szafraniec
 * @version 4.0
 */
@WebServlet(name = "SessionServletTextToBraille", urlPatterns = {"/SessionServletTextToBraille"})
public class SessionServletTextToBraille extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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

            HttpSession session = request.getSession();

            List<String> inputHistory = (List<String>) session.getAttribute("inputTextToBraille");
            List<String> outputHistory = (List<String>) session.getAttribute("outputTextToBraille");
            int counter = inputHistory.size();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Session</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CookiesServlet</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<p> Session ID = " + session.getId() + "</p>");
            out.println("<h1>Text to Braille conversion statistics:</h1>\n");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th> Input </th>");
            out.println("<th> Output </th>");
            out.println("</tr>");

            for (int i = 0; i < counter; i++) {
                out.println("<tr>");
                out.println("<td>" + inputHistory.get(i) + "</td>");
                out.println("<td>" + outputHistory.get(i) + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

            //include method
            getServletContext().getRequestDispatcher("/SessionBTT").include(request, response);

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
