<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<h5>Rate Calculator</h5>
    <form action="BHCController" method=POST>
		<label for="hike">Choose a hike:</label>
		  <select name="hike" id="hike">
		    <option value="1">Gardiner Lake</option>
		    <option value="2">Hellroaring Plateau</option>
		    <option value="3">The Beaten Path</option>
		  </select>
		  <br/>
		<label for="start">Start date:</label>
		<input type="date" id="startDate" name="startDate"
		       min="2020-01-01" max="2025-12-31" required>
		    <br/>   
		 <label for="duration">Choose a duration:</label>
		  <select name="duration" id="duration">
		    <option value="1">1</option>
		    <option value="2">2</option>
		    <option selected value="3">3</option>
		    <option value="4">4</option>
		    <option value="5">5</option>
		    <option value="6">6</option>
		    <option value="7">7</option>
		    <option value="8">8</option>
		    <option value="9">9</option>
		  </select>
		<br /> 
		
		<label for="people">Number of hikers:</label>
		  <select name="people" id="people">
		    <option value="1">1</option>
		    <option value="2">2</option>
		    <option value="3">3</option>
		    <option value="4">4</option>
		    <option value="5">5</option>
		    <option value="6">6</option>
		    <option value="7">7</option>
		    <option value="8">8</option>
		    <option value="9">9</option>
		    <option value="10">10</option>
		  </select>
		<br /> 
		
		<input type="SUBMIT">
		<input type="reset">
	</form>