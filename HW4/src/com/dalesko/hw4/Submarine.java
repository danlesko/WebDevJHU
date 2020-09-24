package com.dalesko.hw4;

public final class Submarine extends Ship {
	
	private int numberTorpedos;

	public Submarine() {
		super(45, "Submarine1", "Submarine");
		this.numberTorpedos = 4;
	}

	public Submarine(int length, String name, String type, int numTorpedos) {
		super(length, name, type);
		this.numberTorpedos = numTorpedos;
	}

	public int getNumberTorpedos() {
		return numberTorpedos;
	}

	public void setNumberTorpedos(int numberTorpedos) {
		this.numberTorpedos = numberTorpedos;
	}
	
	public void setNumberTorpedos(String numberTorpedos) {
		try {
			this.numberTorpedos = Integer.parseInt(numberTorpedos);
		}
		catch (NumberFormatException e) {
			System.out.println("Why would you even attempt to enter anything other than a number?... Setting the number of torpedos to 2.");
			this.numberTorpedos = 2;
		}
	}
	
	@Override
    public String toString() { 
		String returnStr = String.format("Type: " + this.getType() + "\n" +
				"Name: " + this.getName() + "\n" +
				"Length: " + this.getLength() + "\n" +
				"Torpedos: " + this.getNumberTorpedos() + "\n"); 
		if (this.getSpeed() != 0) {
			return returnStr.concat("Speed: " + this.getSpeed() + "\n");
		} else {
			return returnStr.concat("Speed: Stationary\n");
		}
        
    } 

}
