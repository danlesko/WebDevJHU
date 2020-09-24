package com.dalesko.hw4;

public final class Destroyer extends Ship {
	
	private int numberMissles;
	
	public Destroyer() {
		super(20, "Destroyer1", "Destroyer");
		this.numberMissles = 2;
	}

	public Destroyer(int len, String name, String type, int numMissles) {
		super(len, name, type);
		this.numberMissles = numMissles;
	}

	public int getNumberMissles() {
		return numberMissles;
	}

	public void setNumberMissles(int numberMissles) {
		this.numberMissles = numberMissles;
	}
	
	public void setNumberMissles(String numberMissles) {
		try {
			this.numberMissles = Integer.parseInt(numberMissles);
		}
		catch (NumberFormatException e) {
			System.out.println("Why would you even attempt to enter anything other than a number?... Setting the number of missles to 2.");
			this.numberMissles = 2;
		}
	}
	
	@Override
    public String toString() { 
        String returnStr = String.format("Type: " + this.getType() + "\n" +
        					"Name: " + this.getName() + "\n" +
        					"Length: " + this.getLength() + "\n" +
        					"Missles: " + this.getNumberMissles() + "\n"); 
        
        if (this.getSpeed() != 0) {
			return returnStr.concat("Speed: " + this.getSpeed() + "\n");
		} else {
			return returnStr.concat("Speed: Stationary\n");
		}
    } 

}
