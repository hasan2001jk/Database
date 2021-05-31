package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String DB_URL="jdbc:postgresql://localhost:5432/library";
		String User="user";
		String Pass="user";
		System.out.println("Testing connection to Postgresql JDBC");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
	
		
		String login= request.getParameter("login");
		String password=request.getParameter("password");
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println("Postgresql not found");
			e.printStackTrace();
			return;
		}
		
		System.out.println("PostgreSQL JDBC Driver successfully connected");
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(DB_URL,User,Pass);
			connection.close();
			System.out.println("connection closed!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		if (connection != null) {
				
				if(login.equals(User) && password.equals(Pass)) {
					
					PrintWriter text = response.getWriter();
					text.print("<br>Welcome to DB");
					String path=request.getContextPath()+"/rabota.html";
					response.sendRedirect(path);
//					ServletContext servletContext =getServletContext();
//					RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
//					requestDispatcher.forward(request, response);
					
				}else {
					request.setAttribute("info", "Вы ввели неправильный логин или пароль!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
					}
	}else {
			System.out.println("Failed to make connection to database\n");
		}
	}


	
	
	}

