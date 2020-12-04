/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dalesko.bhc.jspImpl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dalesko.bhc.HikeInfo;

/**
 *
 * @author dlesko1
 */
@WebServlet("/Controller")	
public class Controller extends HttpServlet {
    public static final String HIKE= "hike";
    public static final String STARTDATE = "startDate";
    public static final String DURATION = "duration";
    public static final String PEOPLE = "people";
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
        
        // If we have not previously been to the calculator, show the calculator page 
        // This page has the same HTML essentially but no JSP
        if (hikeInfo == null) {
            hikeInfo = new HikeInfo();
            session.setAttribute(HIKE, hikeInfo);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/calculator.jsp");
            dispatcher.forward(request, response);
        } else {
        	// Saturate HikeInfo Bean
            String hike = request.getParameter(HIKE);
            String startDate = request.getParameter(STARTDATE);
            String duration = request.getParameter(DURATION);
            String people = request.getParameter(PEOPLE);
            
            if (hike == null && startDate == null && duration == null && people == null) {
	        	hikeInfo.setErrMsg("Please enter information for your hike.");
	        } else {
	        	hikeInfo.setErrMsg(null);
	        }
            
            // Ask user to enter information for their hike if any of these parameters are empty
            // Need to do this in order to call the set calls below
	        if (hike == null) {
	        	hikeInfo.setErrMsg("Please enter information for your hike.");
	        }
	        if (startDate == null) {
	        	hikeInfo.setErrMsg("Please enter information for your hike.");
	        }
	        if (duration == null) {
	        	hikeInfo.setErrMsg("Please enter information for your hike.");
	        }
	        if (people == null) {
	        	hikeInfo.setErrMsg("Please enter information for your hike.");
	        }
            
	        // Hydrate the bean
            if(hikeInfo.getErrMsg() == null) {
	            hikeInfo.setHike(hike);
	            hikeInfo.setStartDate(startDate);
	            hikeInfo.setDuration(duration);
	            hikeInfo.setPeople(people);
	            
	            hikeInfo.processRequest();
            }
            
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/calculator_results.jsp");
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
