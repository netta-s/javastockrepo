package com.t2k.javastockapp;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		resp.setContentType("text/html");
				
		//Create 3 Stock instances
		String pihSymbol = "PIH";
		float pihAsk = 13.1f;
		float pihBid = 12.4f;
		Date pihDate = new Date(1416009600000L);
		Stock pihStock = new Stock(pihSymbol, pihAsk, pihBid, pihDate);
		
		String aalSymbol = "AAL";
		float aalAsk = 5.78f;
		float aalBid = 5.5f;
		Date aalDate = new Date(1416009600000L);
		Stock aalStock = new Stock(aalSymbol, aalAsk, aalBid, aalDate);
		
		String caasSymbol = "CAAS";
		float caasAsk = 32.2f;
		float caasBid = 31.5f;
		Date caasDate = new Date(1416009600000L);
		Stock caasStock = new Stock(caasSymbol, caasAsk, caasBid, caasDate);		
		
		//Print details to page
		resp.getWriter().println(pihStock.getHtmlDescription());
		resp.getWriter().println("<br>");
		resp.getWriter().println(aalStock.getHtmlDescription());
		resp.getWriter().println("<br>");
		resp.getWriter().println(caasStock.getHtmlDescription());
	}
}
