package pawel.szafraniec.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pawel.szafraniec.model.BrailleToTextModel;
import pawel.szafraniec.model.InvalidInputException;
import pawel.szafraniec.model.TextToBrailleModel;
import pawel.szafraniec.view.View;

/**
 * Servlet handling conversion from Braille to text and displaying both input
 * and output strings.
 *
 * @author Pawel Szafraniec
 * @version 4.0
 */
@WebServlet(name = "ConvertBrailleToTextServlet", urlPatterns = {"/ConvertBrailleToTextServlet"})
public class ConvertBrailleToTextServlet extends HttpServlet {

    /**
     * TextToBrailleModel class object
     */
    private TextToBrailleModel textToBrailleModel;

    /**
     * BrailleToTextModel class object
     */
    private BrailleToTextModel brailleToTextModel;

    /**
     * View class object
     */
    private View view;

    /**
     * Class constructor
     */
    public ConvertBrailleToTextServlet() {
        this.textToBrailleModel = new TextToBrailleModel();
        this.brailleToTextModel = new BrailleToTextModel();
        this.view = new View();
    }

    /**
     * List of Strings storing input strings - used in displaying history of operations
     */
    List<String> inputHistory = new ArrayList<>();
    /**
     * List of Strings storing output strings - used in displaying history of operations
     */
    List<String> outputHistory = new ArrayList<>();

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
                //error message
                response.sendError(response.SC_BAD_REQUEST, "Input string is empty!");
            } else {
                for (int i = 0; i < inputString.length(); i++) {
                    if ((Character.isDigit(inputString.charAt(i)) == false) && (inputString.charAt(i) != ' ')) {
                        //redirect to ErrorServlet
                        getServletContext().getRequestDispatcher("/Error").forward(request, response);
                    }
                }
            }

            try {
                String outputText = view.returnOutputCharacterList(brailleToTextModel.convertToText(inputString, textToBrailleModel.getMap()));
                out.println("Input text:" + inputString + "<br>"
                        + "Converted: "
                        + outputText);

                inputHistory.add(inputString);
                outputHistory.add(outputText);
            } catch (InvalidInputException ex) {
                Logger.getLogger(ConvertTextToBrailleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            //session setup
            session.setAttribute("inputBrailleToText", inputHistory);
            session.setAttribute("outputBrailleToText", outputHistory);

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
