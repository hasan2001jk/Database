package ru.unlimit;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/ServletTables")
public class ServletTables extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletTables() {
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
		   PrintWriter out = response.getWriter();
		try {
			connection = DriverManager.getConnection(DB_URL, User, Pass);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		String choice = request.getParameter("menu");
		try {
			if(choice.equals("reader")) {
		 
		    out.println("<html>");
		    out.println("<head><title>Читатели</title></head>");
		    out.println("<body>");
		    out.println("<center><h1>Читатели</h1>");
		    out.println("<table border='1'><tr><th>Id</th><th>Имя</th><th>Фамилия</th><th>Читательский билет</th><th>Телефон</th><th>Email</th><th>Адрес</th></tr>");
		    
		    System.out.println("connection");
		    Statement stmt=connection.createStatement();
		     ResultSet rs = stmt.executeQuery("SELECT * FROM reader");
		    
		      while (rs.next()) {
		    	  out.print("<tr><td>");
		    	  out.println(rs.getInt("id"));
		    	  out.print("</td>");
		    	  out.print("<td>");
		    	  out.println(rs.getString("first_name"));
		    	  out.print("</td>");
		    	  out.print("<td>");
		    	  out.println(rs.getString("last_name"));
		    	  out.print("</td>");
		    	  out.print("<td>");
		    	  out.println(rs.getInt("library_card"));
		    	  out.print("</td>");
		    	  out.print("<td>");
		    	  out.println(rs.getString("phone"));
		    	  out.print("</td>");
		    	  out.print("<td>");
		    	  out.println(rs.getString("email"));
		    	  out.print("</td>");
		    	  out.print("<td>");
		    	  out.println(rs.getString("address"));
		    	  out.print("</td>");
		    	  out.print("</tr>");
		      }
		    
		    out.print("</table>");
		    out.println("</center>");
		    out.println("</body>");
		    out.println("</html>");
		    out.close();
		    stmt.close();
		    rs.close();
			}else if(choice.equals("books")) {
				 out.println("<html>");
				    out.println("<head><title>Книги</title></head>");
				    out.println("<body>");
				    out.println("<center><h1>Книги</h1>");
				    out.println("<table border='1'><tr><th>Id</th><th>Название книги</th><th>Дата издания</th><th>Количество страниц</th><th>Цена</th><th>Код издательства</th><th>Количество</th></tr>");
				    
				    System.out.println("connection");
				    Statement stmt=connection.createStatement();
				     ResultSet rs = stmt.executeQuery("SELECT * FROM book");
				    
				      while (rs.next()) {
				    	  out.print("<tr><td>");
				    	  out.println(rs.getInt("book_code"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getString("book_title"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getDate("publish_date"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getInt("book_size"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getString("price")+" руб");
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getInt("publisher_code"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getInt("amount")+" шт");
				    	  out.print("</td>");
				    	  out.print("</tr>");
				      }
				    
				    out.print("</table>");
				    out.println("</center>");
				    out.println("</body>");
				    out.println("</html>");
				    out.close();
				    stmt.close();
				    rs.close();	
				
				
			}else if(choice.equals("lending_books")) {
				 out.println("<html>");
				    out.println("<head><title>Выдача</title></head>");
				    out.println("<body>");
				    out.println("<center><h1>Выдача</h1>");
				    out.println("<table border='1'><tr><th>Id</th><th>Дата выдачи</th><th>Дата возврата</th><th>Код издательства</th><th>Читательский билет</th></tr>");
				    
				    System.out.println("connection");
				    Statement stmt=connection.createStatement();
				     ResultSet rs = stmt.executeQuery("SELECT * FROM lending_books");
				    
				      while (rs.next()) {
				    	  out.print("<tr><td>");
				    	  out.println(rs.getInt("issue_code"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getDate("date_of_issue"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getDate("return_date"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getInt("publisher_code"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getInt("publisher_code"));
				    	  out.print("</td>");
				    	  out.print("</tr>");
				      }
				    
				    out.print("</table>");
				    out.println("</center>");
				    out.println("</body>");
				    out.println("</html>");
				    out.close();
				    stmt.close();
				    rs.close();	
				
				
				
			}else if(choice.equals("publishers")) {
				 out.println("<html>");
				    out.println("<head><title>Издательство</title></head>");
				    out.println("<body>");
				    out.println("<center><h1>Издательство</h1>");
				    out.println("<table border='1'><tr><th>Id</th><th>Название издательства</th><th>Код города</th></tr>");
				    
				    System.out.println("connection");
				    Statement stmt=connection.createStatement();
				     ResultSet rs = stmt.executeQuery("SELECT * FROM publishers");
				    
				      while (rs.next()) {
				    	  out.print("<tr><td>");
				    	  out.println(rs.getInt("publisher_code"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getString("name_"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getInt("city_code"));
				    	  out.print("</td>");
				    	  out.print("</tr>");
				      }
				    
				    out.print("</table>");
				    out.println("</center>");
				    out.println("</body>");
				    out.println("</html>");
				    out.close();
				    stmt.close();
				    rs.close();	
				
				
				
				
			}else if(choice.equals("cities")) {
				 out.println("<html>");
				    out.println("<head><title>Город</title></head>");
				    out.println("<body>");
				    out.println("<center><h1>Город</h1>");
				    out.println("<table border='1'><tr><th>Id</th><th>Название города</th></tr>");
				    
				    System.out.println("connection");
				    Statement stmt=connection.createStatement();
				     ResultSet rs = stmt.executeQuery("SELECT * FROM cities");
				    
				      while (rs.next()) {
				    	  out.print("<tr><td>");
				    	  out.println(rs.getInt("city_code"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getString("name"));
				    	  out.print("</td>");
				    	  out.print("</tr>");
				      }
				    
				    out.print("</table>");
				    out.println("</center>");
				    out.println("</body>");
				    out.println("</html>");
				    out.close();
				    stmt.close();
				    rs.close();	
				
				
				
			}else if(choice.equals("authors")) {
				 out.println("<html>");
				    out.println("<head><title>Авторы</title></head>");
				    out.println("<body>");
				    out.println("<center><h1>Авторы</h1>");
				    out.println("<table border='1'><tr><th>Id</th><th>Имя</th><th>Фамилия</th></tr>");
				    
				    System.out.println("connection");
				    Statement stmt=connection.createStatement();
				     ResultSet rs = stmt.executeQuery("SELECT * FROM authors");
				    
				      while (rs.next()) {
				    	  out.print("<tr><td>");
				    	  out.println(rs.getInt("author_code"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getString("name"));
				    	  out.print("</td>");
				    	  out.print("<td>");
				    	  out.println(rs.getString("surname"));
				    	  out.print("</td>");
				    	  out.print("</tr>");
				      }
				    
				    out.print("</table>");
				    out.println("</center>");
				    out.println("</body>");
				    out.println("</html>");
				    out.close();
				    stmt.close();
				    rs.close();	
				
				
				
				
			}
		}catch(SQLException es) {
			es.printStackTrace();
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
