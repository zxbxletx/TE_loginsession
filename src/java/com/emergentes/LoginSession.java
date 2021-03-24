package com.emergentes;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zxbxl
 */
@WebServlet(name = "LoginSession", urlPatterns = {"/LoginSession"})
public class LoginSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        String login = (String) ses.getAttribute("login");
        if (login.equals("OK")) {
            //Terminarla session
            ses.invalidate();
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        if ((usuario.equals("admin")) && (password.equals("1234"))) {
            ses.setAttribute("login", "OK");
            ses.setAttribute("usuario", usuario);
            // Desamas ingresar al panel
            response.sendRedirect("panel.jsp");
        } else {
            ses.setAttribute("error", "Usuario sin autorización");
            response.sendRedirect("login.jsp");

        }
    }
}
