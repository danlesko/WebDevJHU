package com.dalesko.hw4;

public final class P3 extends Aircraft {
	
	private int numberEngines;

	public P3() {
		super(20, "P31", "P3");
		this.numberEngines = 2;
	}

	public P3(int length, String name, String type, int numEngines) {
		super(length, name, type);
		this.numberEngines = numEngines;
	}

	public int getNumberEngines() {
		return numberEngines;
	}

	public void setNumberEngines(int numberEngines) {
		this.numberEngines = numberEngines;
	}
	
	@Override
    public String toString() { 
        String returnStr = String.format("Type: " + this.getType() + "\n" +
        					"Name: " + this.getName() + "\n" +
        					"Length: " + this.getLength() + "\n" +
        					"Engines: " + this.getNumberEngines() + "\n"); 
        if (this.getSpeed() != 0) {
			return returnStr.concat("Speed: " + this.getSpeed() + "\n" +
									"Altitude: " + this.getAltitude() + "\n");
		} else {
			return returnStr.concat("Speed: Stationary\n");
		}
    } 

}
