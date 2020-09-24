package com.dalesko.hw4;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
	
	/** Creates a new instance of Main */
    public Test() {
    }

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {
		
		ArrayList<Destroyer> destroyers = new ArrayList<Destroyer>();
		ArrayList<Submarine> submarines = new ArrayList<Submarine>();
		ArrayList<P3> p3s = new ArrayList<P3>();
		
		LinkedList<Ship> ships = new LinkedList<Ship>();
		LinkedList<Contact> contacts = new LinkedList<Contact>();
		
		Destroyer d1 = new Destroyer();
		Destroyer d2 = new Destroyer(25, "The Cool Destroyer", "Destroyer", 22);
		destroyers.add(d1);
		destroyers.add(d2);
		d2.setSpeed(22);
		
		Submarine s1 = new Submarine();
		Submarine s2 = new Submarine(45, "The Cool Submarine", "Submarine", 42);
		submarines.add(s1);
		submarines.add(s2);
		s1.setNumberTorpedos("Foo");
		
		P3 p1 = new P3();
		P3 p2 = new P3(45, "The Cool P3", "P3", 4);
		p3s.add(p1);
		p3s.add(p2);
		p2.setAltitude(1000);
		p2.setSpeed(500);
		
		
		ships.add(s1);
		ships.add(s2);
		ships.add(d1);
		ships.add(d2);
		
		contacts.add(s1);
		contacts.add(s2);
		contacts.add(d1);
		contacts.add(d2);
		contacts.add(p1);
		contacts.add(p2);
		
		contacts.forEach(System.out::println);
	}

}
