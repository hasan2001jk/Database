package ru.unlimit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/ServletPoisk")
public class ServletPoisk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ServletPoisk(){
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String DB_URL="jdbc:postgresql://localhost:5432/library";
		String User="user";
		String Pass="user";
		System.out.println("Testing connection to Postgresql JDBC");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		boolean yes_no = false;
		String first_name=request.getParameter("std_name");
		String last_name=request.getParameter("std_surname");
		String library_card=request.getParameter("lib_card");
		
		
		
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(DB_URL, User, Pass);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
			try {
			
			PreparedStatement statement =connection.prepareStatement("SELECT * FROM reader");
			ResultSet resultSet =statement.executeQuery();
			while(resultSet.next()) {
				String new_name = resultSet.getString("first_name");
				String new_surname=resultSet.getString("last_name");
				String new_lib_card =resultSet.getString("library_card");
				int id_ser=resultSet.getInt("id");
					if(new_name.equals(first_name) && new_surname.equals(last_name)) {
						System.out.println(id_ser);
							if(library_card.equals(new_lib_card)) {
								yes_no=true;
								System.out.println(yes_no);
								System.out.println(id_ser);
								request.setAttribute("message", "Читатель найден!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/poisk.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 20);
							}
					}else {
						continue;
					}
				}
			}catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		try {
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e) {
			System.out.println("Postgresql not found");
			e.printStackTrace();
			return;
		}
		
		if(yes_no == false) {
			request.setAttribute("message", "Читатель не найден!");
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/poisk.jsp");
			dispatcher.forward(request, response);
		}
	}
	}
	

