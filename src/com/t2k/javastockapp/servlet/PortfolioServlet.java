package com.t2k.javastockapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.t2k.javastockapp.service.*;
import com.t2k.javastockapp.model.*;

public class PortfolioServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			resp.setContentType("text/html");
			
			PortfolioManager portfolioManager = new PortfolioManager();
			Portfolio portfolio = portfolioManager.getPortfolio();
			Portfolio portfolioCopy = portfolioManager.duplicatePortfolio(portfolio);
			
			portfolioCopy.setTitle("Portfolio #2");

			resp.getWriter().println(portfolio.getHtmlString());
			resp.getWriter().println(portfolioCopy.getHtmlString());
			
			portfolio.removeStock(0);
			
			resp.getWriter().println(portfolio.getHtmlString());
			resp.getWriter().println(portfolioCopy.getHtmlString());
			
			portfolioCopy.getStocks()[portfolioCopy.getPortfolioSize() - 1].setBid(55.55f);
			
			resp.getWriter().println(portfolio.getHtmlString());
			resp.getWriter().println(portfolioCopy.getHtmlString());
	}
}
