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

@WebServlet("/ServletDelete")
public class ServletDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletDelete() {
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String DB_URL = "jdbc:postgresql://localhost:5432/library";
		String User = "user";
		String Pass = "user";
		System.out.println("Testing connection to Postgresql JDBC");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(DB_URL, User, Pass);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		String choice = request.getParameter("menu");
		try {
			//start////
			if(choice.equals("reader")) {
				System.out.println("--reader--");
				String id_str=request.getParameter("std_id");
				boolean id_is_here=false;
				Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
				if(id_str.isEmpty()) {
					System.out.println("You should fill in the ID!!!");
					request.setAttribute("del", "ID не может быть пустым");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else {
					PreparedStatement smt=connection.prepareStatement("SELECT * FROM reader");
					ResultSet rst=smt.executeQuery();
					while(rst.next()) {
						int var=rst.getInt("id");
						if(i==var) {
							System.out.println("ID is here!");
							id_is_here=true;
							break;
						}
					}
					smt.close();
					rst.close();
					if(id_is_here==true) {
						System.out.println("ID is here!");
						PreparedStatement statement =connection.prepareStatement("DELETE FROM reader WHERE id=(?)");
						statement.setInt(1, i);
						statement.executeUpdate();
						statement.close();
						request.setAttribute("del", "Данные успешно удалены!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else {
						System.out.println("ID is not here!");
						request.setAttribute("del", "Такого ID нет в Базе Данных!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
				}
			}else if(choice.equals("books")) {
				System.out.println("--books--");
				String id_str=request.getParameter("std_id");
				boolean id_is_here=false;
				Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
				if(id_str.isEmpty()) {
					System.out.println("You should fill in the ID!!!");
					request.setAttribute("del", "ID не может быть пустым");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else {
					PreparedStatement smt=connection.prepareStatement("SELECT * FROM book");
					ResultSet rst=smt.executeQuery();
					while(rst.next()) {
						int var=rst.getInt("book_code");
						if(i==var) {
							System.out.println("ID is here!");
							id_is_here=true;
							break;
						}
					}
					smt.close();
					rst.close();
					if(id_is_here==true) {
						System.out.println("ID is here!");
						PreparedStatement statement =connection.prepareStatement("DELETE FROM book WHERE book_code=(?)");
						statement.setInt(1, i);
						statement.executeUpdate();
						statement.close();
						request.setAttribute("del", "Данные успешно удалены!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else {
						System.out.println("ID is not here!");
						request.setAttribute("del", "Такого ID нет в Базе Данных!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
				}
				
				
				
				
				
				
			}else if(choice.equals("lending_books")) {
				System.out.println("--lending_books--");
				String id_str=request.getParameter("std_id");
				boolean id_is_here=false;
				Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
				if(id_str.isEmpty()) {
					System.out.println("You should fill in the ID!!!");
					request.setAttribute("del", "ID не может быть пустым");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else {
					PreparedStatement smt=connection.prepareStatement("SELECT * FROM lending_books");
					ResultSet rst=smt.executeQuery();
					while(rst.next()) {
						int var=rst.getInt("issue_code");
						if(i==var) {
							System.out.println("ID is here!");
							id_is_here=true;
							break;
						}
					}
					smt.close();
					rst.close();
					if(id_is_here==true) {
						System.out.println("ID is here!");
						PreparedStatement statement =connection.prepareStatement("DELETE FROM lending_books WHERE issue_code=(?)");
						statement.setInt(1, i);
						statement.executeUpdate();
						statement.close();
						request.setAttribute("del", "Данные успешно удалены!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else {
						System.out.println("ID is not here!");
						request.setAttribute("del", "Такого ID нет в Базе Данных!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
				}
				
				
				
				
				
			}else if(choice.equals("publishers")) {
				System.out.println("--publishers--");
				String id_str=request.getParameter("std_id");
				boolean id_is_here=false;
				Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
				if(id_str.isEmpty()) {
					System.out.println("You should fill in the ID!!!");
					request.setAttribute("del", "ID не может быть пустым");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else {
					PreparedStatement smt=connection.prepareStatement("SELECT * FROM publishers");
					ResultSet rst=smt.executeQuery();
					while(rst.next()) {
						int var=rst.getInt("publisher_code");
						if(i==var) {
							System.out.println("ID is here!");
							id_is_here=true;
							break;
						}
					}
					smt.close();
					rst.close();
					if(id_is_here==true) {
						System.out.println("ID is here!");
						PreparedStatement statement =connection.prepareStatement("DELETE FROM publishers WHERE publisher_code=(?)");
						statement.setInt(1, i);
						statement.executeUpdate();
						statement.close();
						request.setAttribute("del", "Данные успешно удалены!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else {
						System.out.println("ID is not here!");
						request.setAttribute("del", "Такого ID нет в Базе Данных!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
				}
				
				
				
				
				
			}else if(choice.equals("cities")) {
				System.out.println("--cities--");
				String id_str=request.getParameter("std_id");
				boolean id_is_here=false;
				Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
				if(id_str.isEmpty()) {
					System.out.println("You should fill in the ID!!!");
					request.setAttribute("del", "ID не может быть пустым");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else {
					PreparedStatement smt=connection.prepareStatement("SELECT * FROM cities");
					ResultSet rst=smt.executeQuery();
					while(rst.next()) {
						int var=rst.getInt("city_code");
						if(i==var) {
							System.out.println("ID is here!");
							id_is_here=true;
							break;
						}
					}
					smt.close();
					rst.close();
					if(id_is_here==true) {
						System.out.println("ID is here!");
						PreparedStatement statement =connection.prepareStatement("DELETE FROM cities WHERE city_code=(?)");
						statement.setInt(1, i);
						statement.executeUpdate();
						statement.close();
						request.setAttribute("del", "Данные успешно удалены!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else {
						System.out.println("ID is not here!");
						request.setAttribute("del", "Такого ID нет в Базе Данных!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
				}
				
				
				
				
				
			}else if(choice.equals("authors")) {
				System.out.println("--authors--");
				String id_str=request.getParameter("std_id");
				boolean id_is_here=false;
				Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
				if(id_str.isEmpty()) {
					System.out.println("You should fill in the ID!!!");
					request.setAttribute("del", "ID не может быть пустым");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else {
					PreparedStatement smt=connection.prepareStatement("SELECT * FROM authors");
					ResultSet rst=smt.executeQuery();
					while(rst.next()) {
						int var=rst.getInt("author_code");
						if(i==var) {
							System.out.println("ID is here!");
							id_is_here=true;
							break;
						}
					}
					smt.close();
					rst.close();
					if(id_is_here==true) {
						System.out.println("ID is here!");
						PreparedStatement statement =connection.prepareStatement("DELETE FROM authors WHERE author_code=(?)");
						statement.setInt(1, i);
						statement.executeUpdate();
						statement.close();
						request.setAttribute("del", "Данные успешно удалены!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else {
						System.out.println("ID is not here!");
						request.setAttribute("del", "Такого ID нет в Базе Данных!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/delete.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
					
					
				}
				
				
				
				
				
				
			}
			///end///
		}catch(SQLException se) {
			se.printStackTrace();
		}
	
		
		

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Postgresql not found");
			e.printStackTrace();
			return;
		}
		
		try {
			connection.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
