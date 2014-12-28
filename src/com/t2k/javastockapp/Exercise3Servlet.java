package com.t2k.javastockapp;

import java.io.IOException;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise3Servlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		resp.setContentType("text/html");
		
		String pattern = new String("###.##");
		DecimalFormat df = new DecimalFormat(pattern);
		
		//Calculate area of circle
		int radius = 50;
		double area = Math.PI * (double) radius * (double) radius;
		
		//Calculate length of triangle's opposite
		int angleInDegrees = 30;
		int hypotenuse = 50;
		double angleInRadians = Math.toRadians(angleInDegrees);
		double opposite = Math.sin(angleInRadians) * hypotenuse;
		
		//Calculate power
		int base = 20;
		int exp = 13;
		double result = Math.pow(base, exp);
		
		String line1 = new String("Calculation 1: Area of circle with radius " + radius + " is: " + df.format(area) + " square-cm.");		
		String line2 = new String("Calculation 2: Length of opposite where angle B is " + angleInDegrees + " degrees and hypotenuse length is " + hypotenuse + "cm is: " + df.format(opposite) + "cm.");
		String line3 = new String("Calculation 3: Power of " + base + " with exp of " + exp + " is: " + df.format(result) + ".");
		String resultStr = line1 + "<br>" + line2 + "<br>" + line3;
		
		resp.getWriter().println(resultStr);
	}
}
