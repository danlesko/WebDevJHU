/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dalesko.hw10;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author spiegel
 */
@WebServlet("/Controller")	
public class Controller extends HttpServlet {
    public static final String HIKE= "hike";
    public static final String PASSWORD = "password";
    /** 
    * Processes requests for  HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        HikeInfo hikeInfo = (HikeInfo) session.getAttribute(HIKE);
        if (hikeInfo == null) {
            hikeInfo = new HikeInfo();
            session.setAttribute(HIKE, hikeInfo);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            String name = request.getParameter(HIKE);
            String password = request.getParameter(PASSWORD);
            hikeInfo.setName(name);
            hikeInfo.setPassword(password);
            if (hikeInfo.getSuccess()) {
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/welcome.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
        }
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
    }
}
