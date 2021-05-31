package ru.unlimit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletDobav")
public class ServletDobav extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletDobav() {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		System.out.println(choice);
		try {

			if (choice.equals("reader")) {
				System.out.println("--reader--");
				String name = request.getParameter("std_name");
				String surname = request.getParameter("std_surname");
				String library_card = request.getParameter("lib_card");
				String email = request.getParameter("email");
				String telephone = request.getParameter("telephone");
				String address = request.getParameter("address");
				Integer i =((library_card=="") || (library_card=="0"))? 0 : Integer.parseInt(library_card.trim());
				boolean rec=true;
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO reader (first_name,last_name,email,phone,library_card,address) VALUES((?),(?),(?),(?),(?),(?))");
				PreparedStatement snt=connection.prepareStatement("SELECT * FROM reader");
				ResultSet rs=snt.executeQuery();
				while(rs.next()) {
					String n_name=rs.getString("first_name");
					String n_surname=rs.getString("last_name");
					int n_lib_card=rs.getInt("library_card");
					String n_phone=rs.getString("phone");
					if(i==n_lib_card) {
						System.out.println("This reader is already here");
						rec=false;
					}else if(n_name.equals(name) && n_surname.equals(surname)) {
						if(n_phone.equals(n_phone)) {
							System.out.println("This reader is already here!");
							rec=false;
						}else {
							System.out.println("The FIO is simialar but it doesn't necessarily mean that he is here"+":)");
						}
					}
				}
				statement.setString(1, name);
				statement.setString(2, surname);
				statement.setString(3, email);
				statement.setString(4, telephone);
				statement.setInt(5, i);
				statement.setString(6, address);
				if(rec==true) {
				statement.executeUpdate();
				System.out.println("Everything chik-and-piks");
				request.setAttribute("text", "Читатель добавлен!");
				RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
				dispatcher.forward(request, response);
				response.setIntHeader("Refresh", 10);
				}else {
					System.out.println("Sad- and - sad There is repeat");
					request.setAttribute("text", "Данный читатель уже добавлен!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 10);
					
				}
				statement.close();
				snt.close();
				rs.close();
			} else if (choice.equals("books")) {
				System.out.println("--books--");
				String book_title = request.getParameter("book-title");
				String publish_date = request.getParameter("publish-date");
				String book_size = request.getParameter("book-size");
				String price = request.getParameter("price");
				String amount = request.getParameter("amount");
				String publisher_code = request.getParameter("publish-code");
				boolean proverka=false;
				int f = Integer.parseInt(publisher_code.trim());
				int i = Integer.parseInt(book_size.trim());
				int j = Integer.parseInt(amount.trim());
				PreparedStatement stmnt=connection.prepareStatement("SELECT publisher_code FROM publishers");
				ResultSet rs=stmnt.executeQuery();
				while(rs.next()) {
					int var =rs.getInt("publisher_code");
					if(f==var) {
						proverka=true;
					}
				}
				if(proverka==true) {
				System.out.println(proverka);
				System.out.println("Everything is ok!");
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO book(book_title,publisher_code,publish_date,book_size,price,amount) VALUES((?),(?),(?),(?),(?),(?))");
				
				statement.setString(1, book_title);
				statement.setInt(2, f);
				statement.setDate(3, java.sql.Date.valueOf(publish_date));
				statement.setInt(4, i);
				statement.setString(5, price);
				statement.setInt(6, j);
				boolean rec=true;
				PreparedStatement snt=connection.prepareStatement("SELECT * FROM book");
				ResultSet rst=snt.executeQuery();
				while(rst.next()) {
					String n_title=rst.getString("book_title");
					int n_pubcode=rst.getInt("publisher_code");
					
					int n_bksize=rst.getInt("book_size");
					String n_price=rst.getString("price");
					int n_amount=rst.getInt("amount");
					
						if(book_title.equals(n_title) && f==n_pubcode ) {
							if(i==n_bksize) {
								if(price.equals(n_price) && j==n_amount) {
									System.out.println("This book is already exist");
									rec=false;
								}else if(price.equals(n_price) || j==n_amount) {
									if(book_title.equals(n_title) && f==n_pubcode) {
									System.out.println("This book is already exist ! ");
										rec=false;
									}
								}
							}
							
						}else {
							System.out.println("Everything ok!");
						}
					}
				
					if(rec==true) {
					statement.executeUpdate();
					request.setAttribute("text", "Книга добавлена!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
					statement.close();
					}else {
						System.out.println("The record is already or maybe in database !");
						request.setAttribute("text", "Книга уже добавлена!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
						
						statement.close();
					}
					
					snt.close();
					rst.close();
					
				}else {
					System.out.println(proverka);
					System.out.println("Everything is bad!");
					request.setAttribute("text", "Такого издательства не существует!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
					
				}
				stmnt.close();
				rs.close();
			
			} else if (choice.equals("lending_books")) {
				System.out.println("--lending_books--");
				String date_issue = request.getParameter("date-issue");
				String return_date = request.getParameter("return-date");
				String book_code = request.getParameter("book-code");
				String lib_card = request.getParameter("lib-card-code");
				boolean bk=false;
				boolean lib=false;
				boolean rec=true;
				int i = Integer.parseInt(lib_card.trim());
				int k = Integer.parseInt(book_code.trim());
				PreparedStatement stmnt=connection.prepareStatement("SELECT book_code FROM book");
				ResultSet rs=stmnt.executeQuery();
				while(rs.next()) {
					int var_1=rs.getInt("book_code");
					if(k==var_1) {
						System.out.println("book_code is similar to var_1 ");
						
						PreparedStatement smt=connection.prepareStatement("SELECT library_card FROM reader");
						ResultSet resultSet=smt.executeQuery();
						while(resultSet.next()) {
							int val =resultSet.getInt("library_card");
								if(i==val) {
									System.out.println("Everything is ok!");
									PreparedStatement statement = connection.prepareStatement(
											"INSERT INTO lending_books(date_of_issue,return_date,library_card_code,book_code) VALUES((?),(?),(?),(?))");
									statement.setDate(1, java.sql.Date.valueOf(date_issue));
									statement.setDate(2, java.sql.Date.valueOf(return_date));
									statement.setInt(3, i);
									statement.setInt(4, k);
									PreparedStatement snt=connection.prepareStatement("SELECT * FROM lending_books");
									ResultSet rst=snt.executeQuery();
								
									while(rst.next()) {
										int book =rst.getInt("book_code");
										int lib_code=rst.getInt("library_card_code");
										if(k==book && i==lib_code) {
											rec=false;
											
										}
									}
									if(rec == true) {
										
									statement.executeUpdate();
									break;
									}else {
										System.out.println("This record is alredy in database!");
										
									}
									lib=true;
									statement.close();
									snt.close();
									rst.close();
									break;
								}else {
									
									System.out.println("First is good but second not!");
									
								}
							}
						resultSet.close();
						smt.close();
						bk=true;
						break;
						
						
					}else {
						System.out.println("book_code is not exist");
						
					}
				}
				if(bk==false) {
					request.setAttribute("text", "Такой книги нет в Базе Данных!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else if(lib==false) {
					request.setAttribute("text", "Такого читательского билета нет!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else if(rec==true) {
					request.setAttribute("text", "Запись добавлена!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else{
					request.setAttribute("text", "Запись уже добавлена!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}
				
				stmnt.close();
				rs.close();
				
			}else if(choice.equals("publishers")) {
				System.out.println("--publishers--");
				String publish_name=request.getParameter("publisher-title");
				String city_code=request.getParameter("city-code");
				int i = Integer.parseInt(city_code.trim());
				boolean rec=true;
				boolean alert=false;
				PreparedStatement stmn=connection.prepareStatement("SELECT city_code FROM cities");
				ResultSet rs=stmn.executeQuery();
				
				while(rs.next()) {
					int var=rs.getInt("city_code");
					if(i==var) {
						alert=true;
						System.out.println("Foreign key is ok!");
						PreparedStatement snt=connection.prepareStatement("SELECT * FROM publishers");
						ResultSet rst=snt.executeQuery();
						while(rst.next()) {
							String n_name=rst.getString("name_");
							if(publish_name.equals(n_name)) {
								System.out.println("This record is alredy here!");
								rec=false;
								break;
							}else {
								System.out.println("Everything is ok!");
								
							}
						}
						snt.close();
						rst.close();
						break;
					}else {
						System.out.println("Foreign key is not ok!");
						
					}
				}
				if(rec!=false && alert==true) {
					PreparedStatement statement=connection.prepareStatement("INSERT INTO publishers(name_,city_code) VALUES((?),(?))");
					statement.setString(1, publish_name);
					statement.setInt(2, i);
					System.out.println("Everything so good!");
					statement.executeUpdate();
					statement.close();
				}else {
					System.out.println("Everything so-so bad!");
					
				}
				stmn.close();
				rs.close();
				
				if(alert==false) {
					request.setAttribute("text", "Такого города нет!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else if(rec==true) {
					request.setAttribute("text", "Запись добавлена!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else {
					request.setAttribute("text", "Запись уже добавлена!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}
			}else if(choice.equals("cities")) {
				System.out.println("--cities--");
				String name=request.getParameter("city-name");
				boolean rec=true;
				PreparedStatement statement =connection.prepareStatement("INSERT INTO cities(name) VALUES((?))");
				statement.setString(1, name);
				PreparedStatement smt=connection.prepareStatement("SELECT * FROM cities");
				ResultSet rs=smt.executeQuery();
				while(rs.next()) {
					String var = rs.getString("name");
					if(name.equals(var)) {
						System.out.println("record is alredy here!");
						rec=false;
						break;
					}
				}
				if(rec==true) {
					System.out.println("Everything is ok!");
					statement.executeUpdate();
					statement.close();
					request.setAttribute("text", "Запись добавлена!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}else {
					System.out.println("Everything is bad!");
					request.setAttribute("text", "Запись уже добавлена!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
				}
				smt.close();
				rs.close();
			}else if(choice.equals("authors")) {
				System.out.println("--authors--");
				String auth_name =request.getParameter("author-name");
				String auth_surname=request.getParameter("author-surname");
				boolean rec=true;
				PreparedStatement statement = connection.prepareStatement("INSERT INTO authors(name,surname) VALUES((?),(?))");
				PreparedStatement smt= connection.prepareStatement("SELECT * FROM authors");
				ResultSet rs=smt.executeQuery();
				while(rs.next()) {
					String n_name=rs.getString("name");
					String n_surname=rs.getString("surname");
					if(auth_name.equals(n_name) && auth_surname.equals(n_surname)) {
						System.out.println("The record is alredy here!");
						rec=false;
						break;
					}
				}
				if(rec==true) {
					System.out.println("Everything is ok!");
					statement.setString(1, auth_name);
					statement.setString(2, auth_surname);
					statement.executeUpdate();
					request.setAttribute("text", "Запись добавлена!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
					statement.close();
				}else {
					System.out.println("Everything is bad!");
					request.setAttribute("text", "Запись уже добавлена!");
					RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/dobav.jsp");
					dispatcher.forward(request, response);
					response.setIntHeader("Refresh", 5);
					statement.close();
				}
				smt.close();
				rs.close();
			}
		} catch (SQLException se) {
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
