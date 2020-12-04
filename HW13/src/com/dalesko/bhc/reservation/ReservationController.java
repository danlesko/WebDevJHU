/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dalesko.bhc.reservation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
 * @author dlesko1
 */
@WebServlet("/ReservationController")	
public class ReservationController extends HttpServlet {
	// This was super confusing in the notes, you need to name the session bean essentially
	public static final String RESERVATIONINFO = "reservationInfo";
    public static final String STARTDATE = "startDate";
    
    private ReservationDAO reservationDAO;
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
        ReservationInfo reservationInfo = (ReservationInfo) session.getAttribute(RESERVATIONINFO);
        
        // If we have not previously been to the calculator, show the calculator page 
        // This page has the same HTML essentially but no JSP
        if (reservationInfo == null) {
        	reservationInfo = new ReservationInfo();
            session.setAttribute(RESERVATIONINFO, reservationInfo);
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/reservation.jsp");
            dispatcher.forward(request, response);
        } else {
        	// Saturate HikeInfo Bean
            String startDate = request.getParameter(STARTDATE);
            
            // Get objects from database by initializing DAO
            reservationDAO = new ReservationDAO(startDate);
            
            // Get reservations from the DAO
            // Afterwards determine if the success yielded results
            // Return set success and assign reservations to reservations attribute
            try {
				List<Reservation> reservations = reservationDAO.list();
				int size = reservations.size();
				if (size > 0) {
					reservationInfo.setSuccess(true);
				}else {
					reservationInfo.setSuccess(false);
				}
				request.setAttribute("reservations", reservations);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            // set startDate
            reservationInfo.setStartDate(startDate);
            
            // Send user to reservations.jsp
            RequestDispatcher dispatcher = servletContext.getRequestDispatcher("/reservation_results.jsp");
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
