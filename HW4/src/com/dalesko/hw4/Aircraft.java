package com.dalesko.hw4;

public abstract class Aircraft implements Contact {

	private int length;
	private int speed = 0;
	private int altitude;
	private String name;
	private String type;
	
	public Aircraft() {
	}
	
	public Aircraft(int length, String name, String type) {
		this.length = length;
		this.name = name;
		this.type = type;
	}
	

	@Override
	public int getLength() {
		return this.length;
	}

	@Override
	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public void setSpeed(String speed) {
		this.speed = Integer.parseInt(speed);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setType(String type) {
		this.type = type;

	}

	public int getAltitude() {
		return this.altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

}
