package pawel.szafraniec.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pawel.szafraniec.model.InvalidInputException;
import pawel.szafraniec.model.TextToBrailleModel;
import pawel.szafraniec.view.View;

/**
 * Servlet handling conversion from text to Braille and displaying both input
 * and output strings.
 *
 * @author Pawel Szafraniec
 * @version 4.0
 */
@WebServlet(name = "ConvertTextToBrailleServlet", urlPatterns = {"/ConvertTextToBrailleServlet"})
public class ConvertTextToBrailleServlet extends HttpServlet {

    /**
     * TextToBrailleModel class object
     */
    private TextToBrailleModel model;
    /**
     * View class object
     */
    private View view;

    /**
     * Class constructor
     */
    public ConvertTextToBrailleServlet() {
        this.model = new TextToBrailleModel();
        this.view = new View();
    }

    /**
     * List of Strings storing input strings - used in displaying history of operations
     */
    List<String> historyInput = new ArrayList<>();
    /**
     * List of Strings storing output strings - used in displaying history of operations
     */
    List<String> historyOutput = new ArrayList<>();

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

            String inputString = request.getParameter("inputString");

            if (inputString.isBlank()) {
                response.sendError(response.SC_BAD_REQUEST, "Input string is empty!");
            }

            try {
                String outputText = view.returnOutputStringBraille(model.convertToBraille(inputString));
                out.println("Input text:" + inputString + "<br>"
                        + "Converted: " + outputText);

                historyInput.add(inputString);
                historyOutput.add(outputText);

            } catch (InvalidInputException ex) {
                Logger.getLogger(ConvertTextToBrailleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //session setup
            session.setAttribute("inputTextToBraille", historyInput);
            session.setAttribute("outputTextToBraille", historyOutput);

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
