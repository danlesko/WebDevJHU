/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dalesko.hw11;

import java.util.List;

/**
 *
 * @author dlesko1
 */
public class ReservationInfo {
    private String startDate = null;
    private List<Reservation> reservations;
    private boolean success = false;
    

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

	public List<Reservation> getHikes() {
		return reservations;
	}

	public void setHikes(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
