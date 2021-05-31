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




@WebServlet("/ServletEdit")
public class ServletEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletEdit() {
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
		String checkbox =request.getParameter("choose");
		try {
			//-----------
			if(choice.equals("reader")) {
				System.out.println("--reader--");
					if(checkbox.equals("with_string")) {
						System.out.println("--with_string--");
						String id_str=request.getParameter("std_id");
						String name =request.getParameter("std_name");
						String surname=request.getParameter("std_surname");
						String lib_card=request.getParameter("lib_card");
						String std_email = request.getParameter("email");
						String telephone=request.getParameter("telephone");
						String address=request.getParameter("address");
						boolean id_alert=true;
						boolean lib_alert=false;
						boolean fio_alert=false;
						Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
						Integer j =((lib_card=="") || (lib_card=="0"))? 0 : Integer.parseInt(lib_card.trim());	
						if(id_str.isEmpty() || name.isEmpty() || surname.isEmpty() || lib_card.isEmpty() || std_email.isEmpty() || telephone.isEmpty() || address.isEmpty()) {
							System.out.println("You should choose with_cells");
						}else {
							System.out.println("Everything is good now!");
						}
						
						
						PreparedStatement stmt=connection.prepareStatement("SELECT * FROM reader",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs = stmt.executeQuery();
						
						while(rs.next()) {
							int n_id=rs.getInt("id");
							
							
							if(n_id==i) {
								System.out.println("ID is good for work!");
								id_alert=false;	
								break;
							}else {
								System.out.println("ID alert! there is not this kind of id!");
								
							}
							
						}
						rs.first();
						while(rs.next()) {
							int n_lib=rs.getInt("library_card");
							if(n_lib==j) {
								System.out.println("This lib_card is already here!");
								lib_alert=true;
								break;
							}else {
								System.out.println("Everything is already ok!");
							}
						}
						rs.first();
						while(rs.next()) {
							String n_name=rs.getString("first_name");
							String n_surname=rs.getString("last_name");
							if(name.equals(n_name)&& surname.equals(n_surname)) {
								System.out.println("Reader maybe is already here!");
								fio_alert=true;
								break;
							}else {
								System.out.println("Everything is ok!");
							}
						}
						
						
						if(id_alert==false) {
							System.out.println("ID is good!");
							if(lib_alert==false) {
								System.out.println("Lib-card is also good!");
								if(fio_alert==false){
									System.out.println("Fio is also good!");
									PreparedStatement statement = connection.prepareStatement("UPDATE reader SET first_name=(?), last_name=(?), email=(?), phone=(?), library_card=(?), address=(?) WHERE id=(?)");
									statement.setString(1, name);
									statement.setString(2, surname);
									statement.setString(3, std_email);
									statement.setString(4, telephone);
									statement.setInt(5, j);
									statement.setString(6, address);
									statement.setInt(7, i);
									statement.executeUpdate();
									statement.close();
									request.setAttribute("sms", "Изменения успешно внесены в Базу Данных!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}else {
									System.out.println("Fio is not good!");
									request.setAttribute("sms", "Читатель с таким именем и фамилией уже существует!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}
							}else {
								System.out.println("Lib-card is not so good!");
								request.setAttribute("sms", "Читательский билет не заполнен либо же уже существует в Базе данных!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}
						}else {
							System.out.println("ID alert!");
							request.setAttribute("sms", "ID не заполнен либо не существует в Базе Данных!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}
						
						
						stmt.close();
						rs.close();
					}else if(checkbox.equals("with_cells")) {
						System.out.println("--with_cells--");
						String id_str=request.getParameter("std_id");
						String name =request.getParameter("std_name");
						String surname=request.getParameter("std_surname");
						String lib_card=request.getParameter("lib_card");
						String std_email = request.getParameter("email");
						String telephone=request.getParameter("telephone");
						String address=request.getParameter("address");
						boolean id_is_empty=false;
						boolean id_is_not_found=false;
						boolean lib_card_is_empty=false;
						boolean lib_card_is_already_here=false;
						Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
						Integer j =((lib_card=="") || (lib_card=="0"))? 0 : Integer.parseInt(lib_card.trim());
						boolean cont=false;
						if(name.isEmpty() || surname.isEmpty() || lib_card.isEmpty() || std_email.isEmpty() || telephone.isEmpty() || address.isEmpty()) {
							System.out.println("Everything not so bad!");
							cont=true;
						}else {
							cont=false;
							System.out.println("Everything so bad! You shoude choose with_string!");
							request.setAttribute("sms", "Для данного типа реадкиторавния следует выбрать 'По строкам'!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}
						
						if(cont==true) {
							System.out.println("Everything is good we are continue!");
							if(id_str.isEmpty()) {
								System.out.println("Everything is bad you should fill in id!");
								id_is_empty=true;
							}else {
								System.out.println("Everything is good we are continue!");
								PreparedStatement statement =connection.prepareStatement("SELECT * FROM reader");
								ResultSet rs=statement.executeQuery();
								while(rs.next()) {
									int var=rs.getInt("id");
									
									if(i==var) {
										System.out.println("Everything is good we are continue!");
										break;
									}else {
										System.out.println("Everything is bad there isn't such an id!");
										id_is_not_found=true;
										
									}
								}
							}
						
						if(lib_card.isEmpty()) {
							System.out.println("The lib_card is empty");
							lib_card_is_empty=true;
						}else {
							if(id_is_not_found==false && id_is_empty==false) {
							PreparedStatement smt=connection.prepareStatement("SELECT library_card FROM reader");
							ResultSet rs=smt.executeQuery();
							while(rs.next()) {
								int var=rs.getInt("library_card");
								if(j==var) {
									System.out.println("The lib_card is already here!");
									lib_card_is_already_here=true;
									break;
								}else {
									System.out.println("The lib_card is good!");
								}
							}
							if(lib_card_is_already_here==false) {
								PreparedStatement statement=connection.prepareStatement("UPDATE reader SET library_card=(?) WHERE ID=(?)");
								statement.setInt(1, j);
								statement.setInt(2, i);
								statement.executeUpdate();
								statement.close();
							}
							smt.close();
							rs.close();
							}
						}
							
						
						
						if(name.isEmpty()) {
							System.out.println("Everything is bad name is empty!");
						}else {
							if(id_is_not_found==false && id_is_empty==false && lib_card_is_empty==false && lib_card_is_already_here==false) {
							PreparedStatement statement = connection.prepareStatement("UPDATE reader SET first_name=(?) WHERE id=(?)");
							statement.setString(1, name);
							statement.setInt(2, i);
							statement.executeUpdate();
							statement.close();
							}
						}
						
						if(surname.isEmpty()) {
							System.out.println("Everything is bad surname is empty!");
							
						}else {
							if(id_is_not_found==false && id_is_empty==false && lib_card_is_empty==false && lib_card_is_already_here==false) {
							PreparedStatement statement = connection.prepareStatement("UPDATE reader SET last_name=(?) WHERE id=(?)");
							statement.setString(1, surname);
							statement.setInt(2, i);
							statement.executeUpdate();
							statement.close();
							}
						}
						
						
						
						if(std_email.isEmpty()) {
							System.out.println("The std_email is empty");
						}else {
							if(id_is_not_found==false && id_is_empty==false && lib_card_is_empty==false && lib_card_is_already_here==false) {
							PreparedStatement statement = connection.prepareStatement("UPDATE reader SET email=(?) WHERE id=(?)");
							statement.setString(1, std_email);
							statement.setInt(2, i);
							statement.executeUpdate();
							statement.close();
						}
					}
						
						
						if(telephone.isEmpty()) {
							System.out.println("The phone is empty");
						}else {
							if(id_is_not_found==false && id_is_empty==false && lib_card_is_empty==false && lib_card_is_already_here==false) {
							PreparedStatement statement = connection.prepareStatement("UPDATE reader SET phone=(?) WHERE id=(?)");
							statement.setString(1, telephone);
							statement.setInt(2, i);
							statement.executeUpdate();
							statement.close();
							}
						}
						
						
						
						if(address.isEmpty()) {
							System.out.println("The address is empty");
						}else {
							if(id_is_not_found==false && id_is_empty==false && lib_card_is_empty==false && lib_card_is_already_here==false) {
							PreparedStatement statement = connection.prepareStatement("UPDATE reader SET address=(?) WHERE id=(?)");
							statement.setString(1, address);
							statement.setInt(2, i);
							statement.executeUpdate();
							statement.close();
							}
						}
					
						
					if(id_is_empty==true) {
						request.setAttribute("sms", "ID не может быть пустым!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else if(id_is_not_found==true) {
						request.setAttribute("sms", "ID не найдено в Базе Данных!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else if(lib_card_is_empty==true) {
						request.setAttribute("sms", "Читательский билет не может быть пустым!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else if(lib_card_is_already_here==true) {
						request.setAttribute("sms", "Данный читательский билет уже существует!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else {
						request.setAttribute("sms", "Изменения успешно внесены в Базу Данных!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
						
				  }	
				}
				
			}else if(choice.equals("books")) {
				System.out.println("--books--");
				if(checkbox.equals("with_string")) {
					System.out.println("--with_string--");
					String id_str=request.getParameter("std_id");
					String book_title=request.getParameter("book-title");
					String publish_date=request.getParameter("publish-date");
					String book_size=request.getParameter("book-size");
					String price=request.getParameter("price");
					String amount=request.getParameter("amount");
					String publish_code=request.getParameter("publish-code");
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					Integer p =((publish_code=="") || (publish_code=="0"))? 0 : Integer.parseInt(publish_code.trim());
					Integer bk =((book_size=="") || (book_size=="0"))? 0 : Integer.parseInt(book_size.trim());
					Integer a =((amount=="") || (amount=="0"))? 0 : Integer.parseInt(amount.trim());
					
					boolean rec =false;
					boolean id_is_here = false;
					boolean pub_code_is_here=false;
					boolean book_is_already_here=false;
					if(id_str.isEmpty() || book_title.isEmpty() || publish_date.isEmpty() || book_size.isEmpty() || price.isEmpty() || amount.isEmpty() || publish_code.isEmpty()) {
						System.out.println("You should choose with_cells");
						
					}else {
						System.out.println("Everything is good!");
						rec=true;
					}
						
					if(rec==true) {
						PreparedStatement stmt = connection.prepareStatement("SELECT * FROM book");
						ResultSet rs =stmt.executeQuery();
						while(rs.next()) {
							int var =rs.getInt("book_code");
							if(i==var) {
								System.out.println("Everything is good we can continue working with this id");
								id_is_here=true;
								break;
							}
						}
						stmt.close();
						rs.close();
						if(id_is_here==true) {
							PreparedStatement smt = connection.prepareStatement("SELECT * FROM publishers");
							ResultSet rst =smt.executeQuery();
							while(rst.next()) {
								int pub_code=rst.getInt("publisher_code");
								if(p==pub_code) {
									System.out.println("Publisher_code is here");
									pub_code_is_here=true;
									break;
								}
							}
							smt.close();
							rst.close();
						}
						
						if(id_is_here==true && pub_code_is_here==true) {
							PreparedStatement smnt=connection.prepareStatement("SELECT * FROM book");
							ResultSet rsn=smnt.executeQuery();
							while(rsn.next()) {
								String title=rsn.getString("book_title");
								if(book_title.equals(title)) {
									System.out.println("Book is already here");
									book_is_already_here=true;
									break;
								}
								
							}
							smnt.close();
							rsn.close();
						}
						
						if(id_is_here==true) {
							System.out.println("Id is good here!");
							if(pub_code_is_here==true) {
								System.out.println("pub_code is good here!");
								if(book_is_already_here==false) {
									System.out.println("book is good here!");
									PreparedStatement statement=connection.prepareStatement("UPDATE book SET book_title=(?), publisher_code=(?), publish_date=(?), book_size=(?), price=(?), amount=(?) WHERE book_code=(?)");
									statement.setString(1, book_title);
									statement.setInt(2, p);
									statement.setDate(3, java.sql.Date.valueOf(publish_date));
									statement.setInt(4, bk);
									statement.setString(5, price);
									statement.setInt(6, a);
									statement.setInt(7, i);
									statement.executeUpdate();
									statement.close();
								}else {
									System.out.println("book isn't good here!");
									request.setAttribute("sms", "Данная книга уже существует в Базе Данных!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}
							}else {
								System.out.println("pub_code isn't good here!");
								request.setAttribute("sms", "Такого издательства не существует!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}
						}else {
							System.out.println("Id is not good here!");
							request.setAttribute("sms", "Такого ID строки не существует!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}
						
					}else {
						System.out.println("You should choose with_cells");
						request.setAttribute("sms", "Для данного типа редактирования вам нужно выбрать 'По Ячейкам'!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
						
						
						
						
				//with_string
				}else if(checkbox.equals("with_cells")) {
					System.out.println("--with_cells--");
					String id_str=request.getParameter("std_id");
					String book_title=request.getParameter("book-title");
					String publish_date=request.getParameter("publish-date");
					String book_size=request.getParameter("book-size");
					String price=request.getParameter("price");
					String amount=request.getParameter("amount");
					String publish_code=request.getParameter("publish-code");
					System.out.println(id_str);
					System.out.println(book_title);
					System.out.println(publish_date);
					System.out.println(book_size);
					System.out.println(price);
					System.out.println(amount);
					System.out.println(publish_code);
					
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					
					Integer p =((publish_code=="") || (publish_code=="0"))? 0 : Integer.parseInt(publish_code.trim());
					Integer bk =((book_size=="") || (book_size=="0"))? 0 : Integer.parseInt(book_size.trim());
					Integer a =((amount=="") || (amount=="0"))? 0 : Integer.parseInt(amount.trim());
					
				
					
					boolean rec=false;
					boolean id_is_here=false;
					boolean id_is_empty=false;
					boolean pub_code_is_empty=false;
					boolean pub_code_is_here=false;
					boolean title_is_already_here=false;
					boolean title_is_empty=false;
					boolean date_is_empty=false;
					boolean size_is_empty=false;
					boolean price_is_empty=false;
					boolean amount_is_empty=false;
					if(id_str.isEmpty() || book_title.isEmpty() || publish_date.isEmpty() || book_size.isEmpty() || price.isEmpty() || amount.isEmpty() || publish_code.isEmpty()) {
						System.out.println("Everything is good!");
						rec=true;
					}else {
						System.out.println("You should choose with_string");
					}
					
					
					
					if(rec==true) {
						PreparedStatement smtm=connection.prepareStatement("SELECT * FROM book",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs=smtm.executeQuery();

						if(id_str.isEmpty()) {
							System.out.println("This id is empty!");
							id_is_empty=true;
						}else {
							while(rs.next()) {
								int var=rs.getInt("book_code");
								if(i==var) {
									System.out.println("Evrything is good we continue working with this id!");
									id_is_here=true;
									break;
								}
							}
						}
						
						if(id_is_empty == false && id_is_here==true ) {
							if(publish_code.isEmpty()) {
								System.out.println("Publish code is empty!");
								pub_code_is_empty=true;
							}else {
								PreparedStatement smn=connection.prepareStatement("SELECT * FROM publishers");
								ResultSet rst=smn.executeQuery();
								while(rst.next()) {
									int pub_code=rst.getInt("publisher_code");
									if(p==pub_code) {
										System.out.println("Everything is good pub_code is there!");
										pub_code_is_here=true;
										break;
									}
								}
								smn.close();
								rst.close();
							}
						}
						
						rs.first();
						if(book_title.isEmpty()) {
							System.out.println("book_title is empty!");
							title_is_empty=true;
						}else {
							while(rs.next()) {
								String title=rs.getString("book_title");
								if(book_title.equals(title)) {
									System.out.println("Everything is bad book is already is here!");
									title_is_already_here=true;
									break;
								}
							}
						}
						
						if(publish_date.isEmpty()) {
							System.out.println("Publish_date is empty!");
							date_is_empty=true;
						}
						
						if(book_size.isEmpty()) {
							System.out.println("book_size is empty!");
							size_is_empty=true;
						}
						
						
						if(price.isEmpty()) {
							System.out.println("Price is empty!");
							price_is_empty=true;
						}
						
						if(amount.isEmpty()) {
							System.out.println("amount is empty!");
							amount_is_empty=true;
						}
						
						PreparedStatement statement=connection.prepareStatement("SELECT * FROM book");
					if(title_is_empty==false) {
						System.out.println("book title is not empty!");
						if(title_is_already_here==false) {
							System.out.println("book title is good!");
							statement =connection.prepareStatement("UPDATE book SET book_title=(?) WHERE book_code=(?)");
							statement.setString(1, book_title);
							statement.setInt(2, i);
						}else {
							System.out.println("book title is already here!");
						}
					}else {
						System.out.println("book title is empty!");
					}
						
					if(date_is_empty==false) {
						System.out.println("date is not empty!");
						statement =connection.prepareStatement("UPDATE book SET publish_date=(?) WHERE book_code=(?)");
						statement.setDate(1, java.sql.Date.valueOf(publish_date));
						statement.setInt(2, i);
					}else {
						System.out.println("date is empty!");
					}
					
					if(size_is_empty==false) {
						System.out.println("book_size is not empty!");
						statement =connection.prepareStatement("UPDATE book SET book_size=(?) WHERE book_code=(?)");
						statement.setInt(1, bk);
						statement.setInt(2, i);
					}else {
						System.out.println("book_size is empty!");
					}
					
					if(price_is_empty==false) {
						System.out.println("price is not empty!");
						statement =connection.prepareStatement("UPDATE book SET price=(?) WHERE book_code=(?)");
						statement.setString(1, price);
						statement.setInt(2, i);
					}else {
						System.out.println("price is empty!");
					}
					
					if(amount_is_empty==false) {
						System.out.println("amount is not empty!");
						statement =connection.prepareStatement("UPDATE book SET amount=(?) WHERE book_code=(?)");
						statement.setInt(1, a);
						statement.setInt(2, i);
					}else {
						System.out.println("amount is  empty!");
					}
					
					if(pub_code_is_empty==false) {
						System.out.println("pub_code is not empty!");
						if(pub_code_is_here==true) {
							System.out.println("pub_code is here!");
							statement =connection.prepareStatement("UPDATE book SET publisher_code=(?) WHERE book_code=(?)");
							statement.setInt(1, p);
							statement.setInt(2, i);
						}else {
							System.out.println("pub_code is not here!");
							
						}
					}else {
						System.out.println("pub_code is empty!");
					}
						
					if(id_is_empty==false) {	
						System.out.println("Id is not empty!");
						if(id_is_here ==true) {
							System.out.println("Id is good for work!");
							statement.execute();
							statement.close();
							request.setAttribute("sms", "Изменения успешнов внесены!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}else {
							System.out.println("Id is not here!");
							request.setAttribute("sms", "Такого ID нет!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}
					}else {
						System.out.println("Id is  empty!");
						request.setAttribute("sms", "ID не может быть пустым!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
						
				
					
						////else-if
					}else {
						System.out.println("You should choose with_string");
						request.setAttribute("sms", "Для данного типа редактирования вам нужно выбрать 'По Строкам'!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
				
				}
				
			}else if(choice.equals("lending_books")) {
				System.out.println("--lending_books--");
				if(checkbox.equals("with_string")) {
					System.out.println("--with_string--");
					String id_str=request.getParameter("std_id");
					String issue_date=request.getParameter("date-issue");
					String return_date=request.getParameter("return-date");
					String book_code=request.getParameter("book-code");
					String lib_card =request.getParameter("lib-card-code");
					boolean rec= false;
					boolean bk_code_is_here=false;
					boolean lib_code_is_here=false;
					boolean lib_code_is_already_here=false;
					boolean id_is_here=false;
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					Integer bk_code =((book_code=="") || (book_code=="0"))? 0 : Integer.parseInt(book_code.trim());
					Integer lib_ =((lib_card=="") || (lib_card=="0"))? 0 : Integer.parseInt(lib_card.trim());
					
					if(issue_date.isEmpty() || return_date.isEmpty() || book_code.isEmpty() || lib_card.isEmpty()) {
						System.out.println("--You should choose with_cells--");
					}else {
						System.out.println("--Everything is good we are continue--");
						rec=true;
					}
					
					if(rec==true) {
						PreparedStatement smt = connection.prepareStatement("SELECT * FROM book");
						ResultSet rst=smt.executeQuery();
						while(rst.next()) {
							int var =rst.getInt("book_code");
							if(bk_code==var) {
								System.out.println("book_code is here!");
								bk_code_is_here=true;
								break;
							}
						}
						smt.close();
						rst.close();
						
						PreparedStatement snt=connection.prepareStatement("SELECT * FROM reader");
						ResultSet rnt=snt.executeQuery();
						while(rnt.next()) {
							int lib=rnt.getInt("library_card");
							if(lib_==lib) {
								System.out.println("lib_code is here!");
								lib_code_is_here=true;
								break;
							}
						}
						snt.close();
						rnt.close();
						
						PreparedStatement smtm=connection.prepareStatement("SELECT * FROM lending_books",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet resultSet=smtm.executeQuery();
						while(resultSet.next()) {
							int var=resultSet.getInt("library_card_code");
							if(lib_==var) {
								System.out.println("lib_code is already here!");
								lib_code_is_already_here=true;
								break;
							}
						}
						resultSet.first();
						while(resultSet.next()) {
							int variable =resultSet.getInt("issue_code");
							if(i==variable) {
								System.out.println("ID is here!");
								id_is_here=true;
								break;
							}
						}
						smtm.close();
						resultSet.close();
						
						
						
						if(id_is_here==true) {
							System.out.println("ID is here!");
							if(bk_code_is_here==true) {
								System.out.println("book_code is here!");
								if(lib_code_is_here==true) {
									System.out.println("lib_code is here!");
									if(lib_code_is_already_here==false) {
										System.out.println("lib_code is ok!");
										PreparedStatement statement=connection.prepareStatement("UPDATE lending_books SET book_code=(?), library_card_code=(?), date_of_issue=(?), return_date=(?) WHERE issue_code=(?)");
										statement.setInt(1, bk_code);
										statement.setInt(2, lib_);
										statement.setDate(3, java.sql.Date.valueOf(issue_date));
										statement.setDate(4, java.sql.Date.valueOf(return_date));
										statement.setInt(5, i);
										statement.executeUpdate();
										statement.close();
										request.setAttribute("sms", "Данные успешно изменены!");
										RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
										dispatcher.forward(request, response);
										response.setIntHeader("Refresh", 5);
									}else {
										System.out.println("lib code is already here!");
										request.setAttribute("sms", "Такой читательский билет уже существует!");
										RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
										dispatcher.forward(request, response);
										response.setIntHeader("Refresh", 5);
									}
								}else {
									System.out.println("lib_code is not here!");
									request.setAttribute("sms", "Такого читательского билета не существует!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}
								
								
								
							}else {
								System.out.println("book_code is not here!");
								request.setAttribute("sms", "Такой книги нет в Базе Данных!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}
							
							
							
						}else {
							System.out.println("ID is not here!");
							request.setAttribute("sms", "Такого ID не существует!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}
						
						
					}else {
						System.out.println("--You should choose with_cells--");
						request.setAttribute("sms", "Для данного типа редактирования вам нужно выбрать 'По Ячейкам'!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
					
				}else if (checkbox.equals("with_cells")) {
					System.out.println("--with_cells--");
					String id_str=request.getParameter("std_id");
					String issue_date=request.getParameter("date-issue");
					String return_date=request.getParameter("return-date");
					String book_code=request.getParameter("book-code");
					String lib_card =request.getParameter("lib-card-code");
					boolean rec= false;
					boolean bk_code_is_here=false;
					boolean lib_code_is_here=false;
					boolean lib_code_is_already_here=false;
					boolean lib_card_is_empty=false;
					boolean id_is_here=false;
					boolean book_code_is_empty=false;
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					Integer bk_code =((book_code=="") || (book_code=="0"))? 0 : Integer.parseInt(book_code.trim());
					Integer lib_ =((lib_card=="") || (lib_card=="0"))? 0 : Integer.parseInt(lib_card.trim());
					
					if(issue_date.isEmpty()||return_date.isEmpty()||book_code.isEmpty()||lib_card.isEmpty() ) {
						System.out.println("Everything is good we are continue!");
						rec=true;
					}else {
						System.out.println("You should choose with_string!");
					}
					
					if(rec==true) {
							
							if(book_code.isEmpty()) {
								System.out.println("book_code is empty!");
								book_code_is_empty=true;
							}else {
								System.out.println("book_code is  not empty!");
								PreparedStatement smt = connection.prepareStatement("SELECT * FROM book");
								ResultSet rst=smt.executeQuery();
								while(rst.next()) {
									int var =rst.getInt("book_code");
									if(bk_code==var) {
										System.out.println("book_code is here!");
										bk_code_is_here=true;
										break;
									}
								}
								smt.close();
								rst.close();

							}
							
							if(lib_card.isEmpty()) {
								System.out.println("lib_card is empty!");
								lib_card_is_empty=true;
							}else {
								System.out.println("lib_card is not empty!");
								PreparedStatement snt=connection.prepareStatement("SELECT * FROM reader");
								ResultSet rnt=snt.executeQuery();
								while(rnt.next()) {
									int lib=rnt.getInt("library_card");
									if(lib_==lib) {
										System.out.println("lib_code is here!");
										lib_code_is_here=true;
										break;
									}
								}
								snt.close();
								rnt.close();
								
							}
							
							if(lib_card_is_empty==false) {
								System.out.println("Proverka!");
							PreparedStatement smtm=connection.prepareStatement("SELECT * FROM lending_books");
							ResultSet resultSet=smtm.executeQuery();
								while(resultSet.next()) {
									int var=resultSet.getInt("library_card_code");
									if(lib_==var) {
										System.out.println("lib_code is already here!");
										lib_code_is_already_here=true;
										break;
									}
								}
								smtm.close();
								resultSet.close();
							}else {
								System.out.println("Proverka failed!");
							}
							
							PreparedStatement kl=connection.prepareStatement("SELECT * FROM lending_books");
							ResultSet k_l=kl.executeQuery();
							while(k_l.next()) {
								int variable =k_l.getInt("issue_code");
								if(i==variable) {
									System.out.println("ID is here!");
									id_is_here=true;
									break;
								}
							}
							
							
							if(lib_code_is_already_here==true) {
								PreparedStatement prov=connection.prepareStatement("SELECT * FROM lending_books WHERE library_card_code=(?)");
								prov.setInt(1, lib_);
								ResultSet pr=prov.executeQuery();
								while(pr.next()) {
									int vr=pr.getInt("book_code");
									System.out.println(vr);
									if(bk_code==vr) {
										System.out.println("Record is identic!");
										break;
									}else {
										lib_code_is_already_here=false;
									}
								}
								prov.close();
								pr.close();
							}
						
						
						if(id_str.isEmpty()) {
							
							System.out.println("Id is empty!");
						
						}else {
							System.out.println("Id is not empty!");
							if(id_is_here==true) {
							System.out.println("id is here!");
							PreparedStatement statement = null;
							boolean cont=true;
								if(book_code_is_empty==false) {
									System.out.println("book_code is not empty!");
									if(bk_code_is_here==true) {
									
									System.out.println("book_code is here!");
									System.out.println(bk_code);
									statement=connection.prepareStatement("UPDATE lending_books SET book_code=(?) WHERE issue_code=(?)");
									statement.setInt(1, bk_code);
									statement.setInt(2, i);
									
									}else {
										System.out.println("book_code is not here!");
										cont=false;
										request.setAttribute("sms", "Такой книги нет в Базе Данных!");
										RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
										dispatcher.forward(request, response);
										response.setIntHeader("Refresh", 5);
									}
								}else {
									System.out.println("book_code is  empty!");
								}
								
								if(issue_date.isEmpty()) {
									System.out.println("issue_date is empty!");
									
								}else {
									System.out.println("issue_date is not empty!");
									System.out.println(issue_date);
									statement=connection.prepareStatement("UPDATE lending_books SET date_of_issue=(?) WHERE issue_code=(?)");
									statement.setDate(1, java.sql.Date.valueOf(issue_date));
									statement.setInt(2, i);
								
								}
								
								if(return_date.isEmpty()) {
									System.out.println("return_date is empty!");
								}else {
									System.out.println("return_date is not empty!");
									System.out.println(return_date);
									statement=connection.prepareStatement("UPDATE lending_books SET return_date=(?) WHERE issue_code=(?)");
									statement.setDate(1, java.sql.Date.valueOf(return_date));
									statement.setInt(2, i);
									
								}
								
								if(lib_card_is_empty==false) {
									System.out.println("lib_card is not empty!");
									if(lib_code_is_here==true) {
										System.out.println("lib_card is here!");
										if(lib_code_is_already_here==false) {
											System.out.println("Everything is good for changing!");
											System.out.println(lib_card);
											statement=connection.prepareStatement("UPDATE lending_books SET library_card_code=(?) WHERE issue_code=(?)");
											statement.setInt(1, lib_);
											statement.setInt(2, i);
											
										}else {
											System.out.println("This record is already here!!!");
											cont=false;
											request.setAttribute("sms", "Данная запись уже в Базе Данных!");
											RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
											dispatcher.forward(request, response);
											response.setIntHeader("Refresh", 5);
										}
									}else {
										System.out.println("lib_card is not here!");
										cont=false;
										request.setAttribute("sms", "Такого читательского билета нет в Базе Данных!");
										RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
										dispatcher.forward(request, response);
										response.setIntHeader("Refresh", 5);
									}
								}else {
									System.out.println("lib_card is empty-empty!");
								}
								
								if(cont==true) {
									statement.executeUpdate();
									statement.close();
									request.setAttribute("sms", "Изменения успешно внесены в Базу Данных!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}else {
									System.out.println("Sorry !");
								}
							
							}else {
								System.out.println("id is not here!");
								request.setAttribute("sms", "Такого ID не существует!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}
						}
						
						
						
						
						
						
					
					
					
					
					}else {
						System.out.println("You should choose with_string");
						request.setAttribute("sms", "Для данного типа редактирования вам нужно выбрать 'По Строкам'!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
					
					
					
				}
				
			}else if(choice.equals("publishers")) {
				System.out.println("--publishers--");
				if(checkbox.equals("with_string")) {
					System.out.println("--with_string--");
					String id_str=request.getParameter("std_id");
					String pub_title=request.getParameter("publisher-title");
					String city_code=request.getParameter("city-code");
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					Integer ct_code =((city_code=="") || (city_code=="0"))? 0 : Integer.parseInt(city_code.trim());
					boolean rec =false;
					boolean id_is_here=false;
					boolean city_code_is_here=false;
					boolean pub_title_is_already_here=false;
					if(pub_title.isEmpty() || city_code.isEmpty()) {
						System.out.println("You should choose with_cells");
					}else {
						System.out.println("Everything is good we are continue!");
						rec=true;
					}
					
					if(rec==true) {
						PreparedStatement smt =connection.prepareStatement("SELECT * FROM publishers");
						ResultSet rst=smt.executeQuery();
						while(rst.next()) {
							int var=rst.getInt("publisher_code");
							if(i==var) {
								System.out.println("Id is good for work");
								id_is_here=true;
								break;
							}
						}
						smt.close();
						rst.close();
						
						if(id_is_here==true) {
							PreparedStatement snt=connection.prepareStatement("SELECT * FROM cities");
							ResultSet rnt=snt.executeQuery();
							while(rnt.next()) {
								int ck=rnt.getInt("city_code");
								if(ct_code==ck) {
									System.out.println("City_Code is good for work");
									city_code_is_here=true;
									break;
								}
							}
							snt.close();
							rnt.close();
						}
						
						if(city_code_is_here==true) {
							PreparedStatement slt=connection.prepareStatement("SELECT * FROM publishers");
							ResultSet rnl=slt.executeQuery();
							while(rnl.next()) {
								String name=rnl.getString("name_");
								if(pub_title.equals(name)) {
									System.out.println("pub_title is alredy here !");
									pub_title_is_already_here=true;
								}
							}
							slt.close();
							rnl.close();
						}
						
						if(id_is_here==true) {
							System.out.println("Id is here!");
							if(city_code_is_here==true) {
								System.out.println("city_code is here!");
								if(pub_title_is_already_here==false) {
									System.out.println("Pub_title is good!");
									PreparedStatement statement=connection.prepareStatement("UPDATE publishers SET name_=(?), city_code=(?) WHERE publisher_code=(?)");
									statement.setString(1, pub_title);
									statement.setInt(2, ct_code);
									statement.setInt(3, i);
									statement.executeUpdate();
									statement.close();
									request.setAttribute("sms", "Изменения успешно внесены!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}else {
									System.out.println("pub_title_is_alredy here!!");
									request.setAttribute("sms", "Такое издательство уже существует!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}
							}else {
								System.out.println("city_code is not here!");
								request.setAttribute("sms", "Такого города нет в Базе Данных!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}
						}else {
							System.out.println("Id is not here!");
							request.setAttribute("sms", "Такого ID нет в Базе Данных!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}
						
					}else {
						System.out.println("You should choose with_cells");
						request.setAttribute("sms", "Для данного типа редактирования вам нужно выбрать 'По Ячейкам'!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
					
					
					
					
					
					
					
				}else if(checkbox.equals("with_cells")) {
					System.out.println("--with_cells--");
					String id_str=request.getParameter("std_id");
					String pub_title=request.getParameter("publisher-title");
					String city_code=request.getParameter("city-code");
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					Integer ct_code =((city_code=="") || (city_code=="0"))? 0 : Integer.parseInt(city_code.trim());
					boolean rec =false;
					boolean id_is_here=false;
					boolean city_code_is_here=false;
					boolean pub_title_is_already_here=false;
					boolean city_code_is_empty=false;
					boolean pub_title_is_empty=false;
					if(pub_title.isEmpty() || city_code.isEmpty()) {
						System.out.println("Everything is good we are continue!");
						rec=true;
					}else {
						System.out.println("You should choose with_string");
					}
					
					if(rec==true) {
						PreparedStatement smt =connection.prepareStatement("SELECT * FROM publishers");
						ResultSet rst=smt.executeQuery();
						while(rst.next()) {
							int var=rst.getInt("publisher_code");
							if(i==var) {
								System.out.println("Id is good for work");
								id_is_here=true;
								break;
							}
						}
						smt.close();
						rst.close();
						
						if(id_is_here==true) {
							if(city_code.isEmpty()) {
								System.out.println("city_code is empty");
								city_code_is_empty=true;
							}else {
								System.out.println("city_code is not  empty");
								PreparedStatement snt=connection.prepareStatement("SELECT * FROM cities");
								ResultSet rnt=snt.executeQuery();
								while(rnt.next()) {
									int ck=rnt.getInt("city_code");
									if(ct_code==ck) {
										System.out.println("City_Code is good for work");
										city_code_is_here=true;
										break;
									}
								}
								snt.close();
								rnt.close();
							}
						}
						
					
							if(pub_title.isEmpty()) {
								System.out.println("pub_title is empty");
								pub_title_is_empty=true;
							}else {
								System.out.println("pub_title is not empty");
								PreparedStatement slt=connection.prepareStatement("SELECT * FROM publishers");
								ResultSet rnl=slt.executeQuery();
								while(rnl.next()) {
									String name=rnl.getString("name_");
									if(pub_title.equals(name)) {
										System.out.println("pub_title is alredy here !");
										pub_title_is_already_here=true;
									}
								}
								slt.close();
								rnl.close();
							}
					
						
						if(id_is_here==true) {
							System.out.println("ID is here!");
							if(city_code_is_empty==false) {
								System.out.println("city_code is not empty!");
								if(city_code_is_here==true) {
									System.out.println("city_code is here");
									PreparedStatement statement=connection.prepareStatement("UPDATE publishers SET city_code=(?) WHERE publisher_code=(?)");
									statement.setInt(1, ct_code);
									statement.setInt(2, i);
									statement.executeUpdate();
									statement.close();
									request.setAttribute("sms", "Изменение успешно внесены!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}else {
									System.out.println("city_code is not here!");
									request.setAttribute("sms", "Такого города нет в Базе Данных!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}
							}else {
								System.out.println("city_code is empty!");
							}
							
							if(pub_title_is_empty==false) {
								System.out.println("pub_title is not empty!");
								if(pub_title_is_already_here==false) {
									System.out.println("pub_title is ok!");
									PreparedStatement statement=connection.prepareStatement("UPDATE publishers SET name_=(?) WHERE publisher_code=(?)");
									statement.setString(1, pub_title);
									statement.setInt(2, i);
									statement.executeUpdate();
									statement.close();
									request.setAttribute("sms", "Изменение успешно внесены!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}else {
									System.out.println("pub_title is already here!");
									request.setAttribute("sms", "Такое издательство уже есть в Базе Данных!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}
							}else {
								System.out.println("pub_title is empty!");
							}
							
						}else {
							System.out.println("ID is not here!");
							request.setAttribute("sms", "Такого ID нет в Базе Данных!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}
						
						
					}else {
						request.setAttribute("sms", "Для данного типа редактирования вам нужно выбрать 'По Строкам'!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
						
					
				}
			}else if(choice.equals("cities")) {
				System.out.println("--cities--");
				if(checkbox.equals("with_string")) {
					System.out.println("--with_string--");
					String id_str=request.getParameter("std_id");
					String city_name=request.getParameter("city-name");
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					boolean city_is_already_here=false;
					boolean id_is_here=false;
					PreparedStatement smt =connection.prepareStatement("SELECT * FROM cities",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rst=smt.executeQuery();
					while(rst.next()) {
						String name=rst.getString("name");
						if(city_name.equalsIgnoreCase(name)) {
							System.out.println("The city is already there!");
							city_is_already_here=true;
							break;
						}
					}
					
					rst.first();
					while(rst.next()) {
						int var =rst.getInt("city_code");
						if(i==var) {
							System.out.println("the id is here!");
							id_is_here=true;
							break;
						}
					}
					smt.close();
					rst.close();
					
					
					if(id_str.isEmpty()) {
						System.out.println("The id is empty!");
						request.setAttribute("sms", "ID строки не может быть пустым!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else {
					if(id_is_here==true) {
						System.out.println("The id is here!");
						if(city_name.isEmpty()) {
							System.out.println("city name is empty!");
							request.setAttribute("sms", "Для данного типа редактирования вам нужно заполнить название города!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}else {
							System.out.println("city name is not empty!");
							if(city_is_already_here==false) {
								System.out.println("city name is good!");
								PreparedStatement statement =connection.prepareStatement("UPDATE cities SET name=(?) WHERE city_code=(?)");
								statement.setString(1, city_name);
								statement.setInt(2, i);
								statement.executeUpdate();
								statement.close();
								request.setAttribute("sms", "Изменение успешно внесены!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}else {
								System.out.println("city name is already there!");
								request.setAttribute("sms", "Данный город уже есть в Базе Данных!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}
						}
						
					  }else {
							System.out.println("The id is not here!");
							request.setAttribute("sms", "Такого ID нет в Базе Данных!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
					  }
					
					}
					
					
					
					
					
					
					
				}else if (checkbox.equals("with_cells")) {
					System.out.println("--with_cells--");
					String id_str=request.getParameter("std_id");
					String city_name=request.getParameter("city-name");
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					boolean city_is_already_here=false;
					boolean id_is_here=false;
					PreparedStatement smt =connection.prepareStatement("SELECT * FROM cities",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					ResultSet rst=smt.executeQuery();
					while(rst.next()) {
						String name=rst.getString("name");
						if(city_name.equalsIgnoreCase(name)) {
							System.out.println("The city is already there!");
							city_is_already_here=true;
							break;
						}
					}
					
					rst.first();
					
					while(rst.next()) {
						int var =rst.getInt("city_code");
						if(i==var) {
							System.out.println("the id is here!");
							id_is_here=true;
							break;
						}
					}
					smt.close();
					rst.close();
					
					
					if(id_str.isEmpty()) {
						System.out.println("The id is empty!");
						request.setAttribute("sms", "ID строки не может быть пустым!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}else {
					if(id_is_here==true) {
						System.out.println("The id is here!");
						if(city_name.isEmpty()) {
							System.out.println("city name is empty!");
							request.setAttribute("sms", "Для данного типа редактирования вам нужно заполнить название города!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}else {
							System.out.println("city name is not empty!");
							if(city_is_already_here==false) {
								System.out.println("city name is good!");
								PreparedStatement statement =connection.prepareStatement("UPDATE cities SET name=(?) WHERE city_code=(?)");
								statement.setString(1, city_name);
								statement.setInt(2, i);
								statement.executeUpdate();
								statement.close();
								request.setAttribute("sms", "Изменение успешно внесены!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}else {
								System.out.println("city name is already there!");
								request.setAttribute("sms", "Данный город уже есть в Базе Данных!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}
						}
						
					  }else {
							System.out.println("The id is not here!");
							request.setAttribute("sms", "Такого ID нет в Базе Данных!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
					  }
					
					}
					
					
					
				}
				
				
			}else if(choice.equals("authors")) {
				System.out.println("--authors--");
				if(checkbox.equals("with_string")) {
					System.out.println("--with_string--");
					String id_str=request.getParameter("std_id");
					String name=request.getParameter("author-name");
					String surname=request.getParameter("author-surname");
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					boolean rec=false;
					boolean id_is_here=false;
					boolean surname_is_already_here=false;
					if(name.isEmpty() || surname.isEmpty()) {
						System.out.println("You should choose with_cells");
					}else {
						System.out.println("Everything is good");
						rec=true;
					}
					
					
					if(rec==true) {
						PreparedStatement smt =connection.prepareStatement("SELECT * FROM authors",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						ResultSet rs =smt.executeQuery();
						while(rs.next()) {
							int var =rs.getInt("author_code");
							if(i==var) {
								System.out.println("ID is here");
								id_is_here=true;
								break;
							}
						}
						rs.first();
						while(rs.next()) {
							String n_surname=rs.getString("surname");

							if(surname.equalsIgnoreCase(n_surname)) {
								System.out.println("Surname is already here");
								surname_is_already_here=true;
								System.out.println(n_surname);
								break;
							}
						}
						smt.close();
						rs.close();
						
						if(surname_is_already_here==true) {
							PreparedStatement snt=connection.prepareStatement("SELECT name FROM authors WHERE surname=(?)");
							snt.setString(1, surname);
							ResultSet rnt=snt.executeQuery();
							while(rnt.next()) {
								String n=rnt.getString("name");
								if(name.equalsIgnoreCase(n)) {
									System.out.println("+++++++++++++++!");
									System.out.println("author is already here!");
									System.out.println(n);
									surname_is_already_here=true;
									break;
								}else {
									surname_is_already_here=false;
								}
							}
							snt.close();
							rnt.close();
						}
						
						if(id_str.isEmpty()) {
							System.out.println("You should fiil in the id");
							request.setAttribute("sms", "ID Строки не может быть пустым!");
							RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
							dispatcher.forward(request, response);
							response.setIntHeader("Refresh", 5);
						}else {
							System.out.println("Id is not empty!");
							if(id_is_here==true) {
								System.out.println("ID is here!");
								if(surname_is_already_here==false) {
									System.out.println("Everything is good!");
									PreparedStatement statement =connection.prepareStatement("UPDATE authors SET name=(?),surname=(?) WHERE author_code=(?)");
									statement.setString(1, name);
									statement.setString(2, surname);
									statement.setInt(3, i);
									statement.executeUpdate();
									statement.close();
									request.setAttribute("sms", "Изменения успешно добавлен!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}else {
									System.out.println("author is alredy here!");
									request.setAttribute("sms", "Данный автор уже есть в Базе Данных!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}
							}else {
								System.out.println("Id is not here !");
								request.setAttribute("sms", "Такого ID нет в Базе Данных!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}
						}
						
					}else {
						System.out.println("You shoud choose with_cells !");
						request.setAttribute("sms", "Для данного типа редактирования вам нужно выбрать 'По Ячейкам'!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
					
					
				}else if(checkbox.equals("with_cells")) {
					System.out.println("--with_cells--");
					String id_str=request.getParameter("std_id");
					String name=request.getParameter("author-name");
					String surname=request.getParameter("author-surname");
					String znach=null;
					String znach_2=null;
					Integer i =((id_str=="") || (id_str=="0"))? 0 : Integer.parseInt(id_str.trim());
					boolean rec=false;
					boolean id_is_here=false;
					boolean id_is_empty=false;
					boolean surname_is_already_here=false;
					boolean surname_is_empty=false;
					boolean name_is_empty=false;
					boolean name_is_already_here=false;
					if(name.isEmpty() || surname.isEmpty()) {
						System.out.println("Everything is good");
						rec=true;
					}else {
						System.out.println("You should choose with_cells");
					}
					
					
					if(rec==true) {
						if(id_str.isEmpty()) {
							System.out.println("ID is empty");
							id_is_empty=true;
						}else {
							System.out.println("ID is not empty");
							PreparedStatement smt =connection.prepareStatement("SELECT * FROM authors");
							ResultSet rs =smt.executeQuery();
							while(rs.next()) {
								int var =rs.getInt("author_code");
								if(i==var) {
									System.out.println("ID is here");
									id_is_here=true;
									break;
								}
							}
							smt.close();
							rs.close();

						}
						
						if(surname.isEmpty()) {
							System.out.println("surname is empty");
							surname_is_empty=true;
						}else {
							System.out.println("surname is not empty");
							PreparedStatement hk =connection.prepareStatement("SELECT * FROM authors");
							ResultSet hkr =hk.executeQuery();
							
							while(hkr.next()) {
								String n_surname=hkr.getString("surname");
	
								if(surname.equalsIgnoreCase(n_surname)) {
									System.out.println("Surname is already here");
									surname_is_already_here=true;
									System.out.println(n_surname);
									break;
								}
							}
						}
						
						if(surname.isEmpty()) {
							System.out.println("Proverka for surname failed");
						}else {
							System.out.println("Proverka for surname");
							PreparedStatement slt=connection.prepareStatement("SELECT name FROM authors WHERE author_code=(?)");
							slt.setInt(1, i);
							ResultSet rf=slt.executeQuery();
							while(rf.next()) {
								String username=rf.getString("name");
								znach=username;
							}
							slt.close();
							rf.close();
							PreparedStatement prov=connection.prepareStatement("SELECT * FROM authors");
							ResultSet rov=prov.executeQuery();
							while(rov.next()) {
								String var_1=rov.getString("surname");
								String var_2=rov.getString("name");
								if(znach.equals(var_2) && surname.equals(var_1)) {
									System.out.println("This author is already here!");
									surname_is_already_here=true;
									break;
								}else {
									surname_is_already_here=false;
								}
							}
							prov.close();
							rov.close();
						}
					
						
						if(name.isEmpty()) {
							System.out.println("name is empty");
							name_is_empty=true;
						}else {
							System.out.println("name is not empty");
							PreparedStatement micr=connection.prepareStatement("SELECT name FROM authors");
							ResultSet mic=micr.executeQuery();
							while(mic.next()) {
								String tes=mic.getString("name");
								if(name.equals(tes)) {
									System.out.println("name is already here!");
									name_is_already_here=true;
									break;
								}
							}
						}
						
						
						if(name.isEmpty()) {
							System.out.println("Proverka for name failed");
						}else {
							if(name_is_already_here=true) {
								System.out.println("Proverka for name!!!");
								PreparedStatement criro=connection.prepareStatement("SELECT surname FROM authors WHERE author_code=(?)");
								criro.setInt(1, i);
								ResultSet cri=criro.executeQuery();
								while(cri.next()) {
									String mes=cri.getString("surname");
									znach_2=mes;
								}
								criro.close();
								cri.close();
								System.out.println(znach_2);
								PreparedStatement ment =connection.prepareStatement("SELECT * FROM authors");
								ResultSet ent =ment.executeQuery();
								while(ent.next()) {
									String var_1=ent.getString("name");
									String var_2=ent.getString("surname");
									if(name.equals(var_1) && znach_2.equals(var_2)) {
										System.out.println("This author is already here!");
										name_is_already_here=true;
										break;
									}else {
										name_is_already_here=false;
									}
								}
								ment.close();
								ent.close();
							}
						}
							
							
							if(id_is_empty==false) {
								System.out.println("Id is not empty!");
								if(id_is_here==true) {
									System.out.println("Id is here!");
									if(surname_is_empty==false) {
										System.out.println("surname is not empty!");
										if(surname_is_already_here==false) {
											System.out.println("surname is good!");
											PreparedStatement statement=connection.prepareStatement("UPDATE authors SET surname=(?) WHERE author_code=(?)");
											statement.setString(1, surname);
											statement.setInt(2, i);
											statement.executeUpdate();
											statement.close();
											request.setAttribute("sms", "Изменения успешно внесены!");
											RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
											dispatcher.forward(request, response);
											response.setIntHeader("Refresh", 5);
										}else {
											System.out.println("author is already here!");
											request.setAttribute("sms", "Автор уже есть в Базе Данных!");
											RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
											dispatcher.forward(request, response);
											response.setIntHeader("Refresh", 5);
										}
									}else {
										System.out.println("surname is empty!");
									}
									
									if(name_is_empty==false) {
										System.out.println("name is not empty!");
										if(name_is_already_here==false) {
											System.out.println("Name is good!");
											PreparedStatement statement=connection.prepareStatement("UPDATE authors SET name=(?) WHERE author_code=(?)");
											statement.setString(1, name);
											statement.setInt(2, i);
											statement.executeUpdate();
											statement.close();
											request.setAttribute("sms", "Изменения успешно внесены!");
											RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
											dispatcher.forward(request, response);
											response.setIntHeader("Refresh", 5);
										}else {
											System.out.println("author is already here!");
											request.setAttribute("sms", "Автор уже есть в Базе Данных!");
											RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
											dispatcher.forward(request, response);
											response.setIntHeader("Refresh", 5);
										}
									}else {
										System.out.println("name is empty!");
									}
									
									
									
									
								}else {
									System.out.println("Id is not here!");
									request.setAttribute("sms", "Такого ID нет в Базе Данных!");
									RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
									dispatcher.forward(request, response);
									response.setIntHeader("Refresh", 5);
								}
							}else {
								System.out.println("Id is  empty!");
								request.setAttribute("sms", "ID не может быть пустым!");
								RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
								dispatcher.forward(request, response);
								response.setIntHeader("Refresh", 5);
							}
							
							
							
						
						
						
						
					}else {
						System.out.println("You should choose with_string!");
						request.setAttribute("sms", "Для данного типа редактирования вам нужно выбрать 'По Строкам'!");
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/edit.jsp");
						dispatcher.forward(request, response);
						response.setIntHeader("Refresh", 5);
					}
					
				}
				
				
				
				
				
				
				
				
				
				
			}
		//----------------------
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
