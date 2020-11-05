/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dalesko.hw10;

/**
 *
 * @author spiegel
 */
public class HikeInfo {
    private String hike = null;
    private String startDate = null;
    private boolean success = false;
    
    public String getHike() {
        return hike;
    }

    public void setHike(String hike) {
        this.hike = hike;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public boolean getSuccess() {
        return success;
    }
}
