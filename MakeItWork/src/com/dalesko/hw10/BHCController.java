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
@WebServlet("/BHCController")	
public class BHCController extends HttpServlet {
    public static final String HIKE      = "";
    public static final String STARTDATE = "";
    public static final String DURATION  = "";
    public static final String PEOPLE    = "";
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
        HikeInfo login = (HikeInfo) session.getAttribute(HIKE);
        if (login == null) {
            login = new HikeInfo();
            session.setAttribute(HIKE, login);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        } else {
            String hike = request.getParameter(HIKE);
            String startDate = request.getParameter(STARTDATE);
            login.setHike(hike);
            login.setStartDate(startDate);
            
            //
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/welcome.jsp");
            dispatcher.forward(request, response);
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
