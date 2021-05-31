<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Библиотека</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Itim&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Raleway+Dots&display=swap" rel="stylesheet">
<link href="dobav.css" type="text/css" rel="stylesheet" >
</head>
<body>

<!-- container starts here -->
<div class="container">
	<!-- Header starts -->
	<div class="header">
	<h1 class="header_title">Самарский Государственный Университет Путей Сообщения</h1>
	</div>
	<!-- Header finishes -->
	<div class="body-wrapper">
	<!-- Body-wrapper wraps this div -->
		<div class="center-wrapper">
		<!--center-wrapper wraps this cards-> left-side -->
			<div class="left-side">
			<!-- left-side starts here -->
				<div class="text">
				<!-- Text starts  here-->
				<h2 class="left-side-header">Работа с базой данных</h2>
					<ul style = "list-style-type:none">
						<li><a class="search" href="poisk.jsp">Поиск</a></li>
						<li><a class="add" href="dobav.jsp">Добавление</a></li>
						<li><a class="edit" href="edit.jsp">Редактирование</a></li>
						<li><a class="delete" href="delete.jsp">Удаление</a></li>
					</ul>
				</div>	
				<!-- Text finishes  here-->
			</div>
			<!-- left-side finishes here -->
		</div>
		<!--center-wrapper finishes here-->
		<div class="center-wrapper">
		<!--center-wrapper wraps this card-> article -->	
			<div class = "article">
		     <h2 class="article-title">База Данных</h2>
			 <h3 class="article-subtitle">Библиотека</h3>
			 <h4 class="article-sub-subtitle">Добавление информации в Базу Данных</h4>
			 	<span class="servlet">
			 		 <%if(request.getAttribute("text") != null){
							out.println("<h6>"+request.getAttribute("text").toString()+"</h6>");
						}%>
			 	</span>
				<form method="post" action="ServletDobav" >
				<!-- forma starts here -->
					<div class="forma">
						<label for="list">Выберите таблицу :
							<select size="1" name="menu" id="list"  onchange="fun1()">
								<option id="reader" selected="selected" value="reader">Читатель</option>
								<option id="books" value="books">Книги</option>
								<option id="lending_books" value="lending_books">Выдача</option>
								<option id="publishers" value="publishers">Издательство</option>
								<option id="cities" value="cities">Город</option>
								<option id="authors" value="authors">Авторы</option>
							</select>
							<!-- labels -->
						</label>
						<label for="name" id="name-label"><span id="name-text">Введите имя  :</span><input type="text" id="name" class="name" placeholder="Имя" name="std_name" ></label>	
						<label for="surname" id="surname-label"><span id="surname-text">Введите фамилию :</span><input type="text" id="surname" class="surname" placeholder="Фамилия" name="std_surname" ></label>
						<label for="library-card" id="library-card-label"><span id="library-text">Введите номер читательского билета :</span><input type="text" id="library-card" class="library-card" placeholder="Номер билета" name="lib_card" required></label>
						<label for="mail" id="email-label"><span id="mail-text">Введите email :</span><input type="text" id="mail" class="mail" placeholder="Email" name="email" ></label>
						<label for="telephone" id="telephone-label"><span id="telephone-text">Введите номер телефона :</span><input type="text" id="telephone" class="telephone" placeholder="Телефон" name="telephone" ></label>
						<label for="address" id="address-label"><span id="address-text">Введите адрес :</span><input type="text" id="address" class="address" placeholder="Адрес" name="address" ></label>
						<!-- labels // -->
					</div>
					<!-- forma finishes here -->
					<div class="forma-button">
					<label for="button"><input type="submit" id="button" value="Отправить"></label>
					</div>
				</form>								
			</div>	
		</div>	
		<!--center-wrapper finishes here-->
		<div class="center-wrapper">
		<!--center-wrapper wraps this cards-> right-side -->
			<div class="right-side">
				<div class="text">
				<!-- Text starts here -->
				<h2 class="right-side-title">Таблицы</h2>
					<ul style = "list-style-type:none">
						<li><a class="reader" href="tables.jsp" target="_blank">Читатель</a></li>
						<li><a class="book" href="tables.jsp" target="_blank">Книги</a></li>
						<li><a class="lending-books" href="tables.jsp" target="_blank">Выдача</a></li>
						<li><a class="publishers" href="tables.jsp" target="_blank">Издательство</a></li>
						<li><a class="cities" href="tables.jsp" target="_blank">Город</a></li>
						<li><a class="authors" href="tables.jsp" target="_blank">Авторы</a></li>
					</ul>
				</div>
				<!-- Text finishes here -->
			</div> 	
		</div>
		<!--center-wrapper finishes here-->
	</div>
	<!-- body-wrapper finishes  here-->
	<div class="footer">
	<!-- footer starts here -->
		<!-- Footer-contacts starts here -->
		<div class="footer-contacts">
				<!-- footer-contacts-img starts here -->
				<div class="footer-contacts-img">
					<img src="./img/email.png" alt="email">
				</div>
				<!-- footer-contacts-img finishes here -->
				<!-- email starts here -->
			<a href="mailto:ya.hasan2001@yandex.ru" target="_blank" class="email">ya.hasan2001@yandex.ru</a>
				<!-- email finishes here -->
				<!-- footer-contacts-img starts here -->
				<div class="footer-contacts-img">
					<img src="./img/phone.png" alt="phone">
				</div>
				<!-- footer-contacts-img finishes here -->
				<!-- phone starts here -->
			<a href="tel:+79613815648" target="_blank" class="phone">+79613815648</a>
				<!-- email finishes here -->
		</div>
		<!-- Footer-contacts finishes here -->
		
		<!-- Footer-year starts here -->
		<div class="footer-year">
			<marquee>2021</marquee>
		</div>
		<!-- Footer-year finishes here -->
		
		<!-- Footer-theme starts here -->
		<div class="footer-theme">
			<!-- footer-theme-img starts here -->
			<div class="footer-theme-img">
			
				<img src="./img/bulb.png" id="theme" onclick="change();">
			
			</div>
			<!-- footer-theme-img finishes here -->
		</div>
		<!-- Footer-theme finishes here -->
		
	</div>
	<!-- footer finishes here -->
</div>
<!-- container finishes here -->
<script>

function change(){
		var bg1=document.body.style.backgroundColor;
		document.body.style.transition = "all linear 1s";
		document.body.style.backgroundColor = "gold";
		
		if(bg1=="gold"){
			
			document.body.style.backgroundColor = "white";
			
		}
		};
		
		function fun1() {
			  var sel=document.getElementById('list').selectedIndex;
			  var options=document.getElementById('list').options;
			  if(options[sel].value=="books"){
				  var book_title =document.getElementById('name-text');
				  book_title.innerHTML="Введите название книги : ";
				  var book_placeholder =document.getElementById('name');
				  book_placeholder.placeholder="Название книги";
				  book_placeholder.name="book-title";
				  /*-------------------------------------------------*/
				  var publish_date = document.getElementById('surname-text');
				  var publish_placeholder=document.getElementById('surname');
				  if(publish_date.style.display =='none'){
				  publish_date.style.display="inline-block";
				  publish_placeholder.style.display="inline-block";
				  publish_date.innerHTML = "Введите дату издания : ";
				  publish_placeholder.placeholder="Дата издания";
				  publish_placeholder.name="publish-date";
				  }else{
					  publish_date.innerHTML = "Введите дату издания : ";
					  publish_placeholder.placeholder="Дата издания";
					  publish_placeholder.name="publish-date";  
				  }
				  /*-------------------------------------------------*/
				  var book_size=document.getElementById('library-text');
				  var bk_size_placeholder=document.getElementById('library-card');
				  if(book_size.style.display=='none'){
				  book_size.style.display="inline-block";
				  bk_size_placeholder.style.display="inline-block";
				  book_size.innerHTML ="Введите количество страниц : ";
				  bk_size_placeholder.placeholder="Количество страниц";
				  bk_size_placeholder.name="book-size";
				  }else{
					  book_size.innerHTML ="Введите количество страниц : ";
					  bk_size_placeholder.placeholder="Количество страниц";
					  bk_size_placeholder.name="book-size";   
				  }
				  /*-------------------------------------------------*/
				  var price=document.getElementById('mail-text');
				  var price_placeholder=document.getElementById('mail');
				  if(price.style.display=='none'){
				  price.style.display="inline-block";
				  price_placeholder.style.display="inline-block";
				  price.innerHTML="Введите цену : ";				  
				  price_placeholder.placeholder="Цена";
				  price_placeholder.name="price";
				  }else{
					  price.innerHTML="Введите цену : ";				  
					  price_placeholder.placeholder="Цена";
					  price_placeholder.name="price";
				  }
				  /*-------------------------------------------------*/
				  var amount =document.getElementById('telephone-text');
				  var amount_placeholder=document.getElementById('telephone');
				  if(amount.style.display=='none'){
				  amount.style.display="inline-block";
				  amount_placeholder.style.display="inline-block";
				  amount.innerHTML="Введите количество книг : ";
				  amount_placeholder.placeholder="Количество книг";
				  amount_placeholder.name="amount";
				  }else{
					  amount.innerHTML="Введите количество книг : ";
					  amount_placeholder.placeholder="Количество книг";
					  amount_placeholder.name="amount";  
				  }
				  /*-------------------------------------------------*/
				  var publish_code=document.getElementById('address-text');
				  var publish_place=document.getElementById('address');
				  if(publish_code.style.display=='none'){
					  publish_code.style.display="inline-block";
					  publish_place.style.display="inline-block";
					  publish_code.innerHTML="Введите код издательства: ";
					  publish_place.placeholder="Код издательства";
					  publish_place.name="publish-code";
				  }else{
					  publish_code.innerHTML="Введите код издательства: ";
					  publish_place.placeholder="Код издательства";
					  publish_place.name="publish-code";
				  }
				  
			  }else if(options[sel].value=="lending_books"){
				  var date_of_issue =document.getElementById('name-text');
				  date_of_issue.innerHTML="Введите дату выдачи : ";
				  var date_placeholder =document.getElementById('name');
				  date_placeholder.placeholder="Дата выдачи";
				  date_placeholder.name="date-issue";
				  /*-------------------------------------------------*/
				  var return_date = document.getElementById('surname-text');
				  var return_placeholder=document.getElementById('surname');
				  if(return_date.style.display=='none'){
					  return_date.style.display="inline-block";
					  return_placeholder.style.display='inline-block';
					  return_date.innerHTML = "Введите дату возврата : ";
					  return_placeholder.placeholder="Дата возврата";
					  return_placeholder.name="return-date";  
				  }else{
				  return_date.innerHTML = "Введите дату возврата : ";
				  return_placeholder.placeholder="Дата возврата";
				  return_placeholder.name="return-date";
				  }
				  /*-------------------------------------------------*/
				  var book_id =document.getElementById('library-text');
				  var book_id_placeholder=document.getElementById('library-card');
				  if(book_id.style.display=='none'){
				  book_id.style.display="inline-block";
				  book_id_placeholder.style.display="inline-block";	
				  book_id.innerHTML="Введите код книги: ";
				  book_id_placeholder.placeholder="Код книги";
				  book_id_placeholder.name="book-code";
				  }else{
					  book_id.innerHTML="Введите код книги: ";
					  book_id_placeholder.placeholder="Код книги";
					  book_id_placeholder.name="book-code";
				  }
				  /*-------------------------------------------------*/
				  var lib_card_code=document.getElementById('mail-text');
				  var lib_card_code_placeholder =document.getElementById('mail');
				  if(lib_card_code.style.display=='none'){
				  lib_card_code.style.display="inline-block";
				  lib_card_code_placeholder.style.display="inline-block";
				  lib_card_code.innerHTML="Введите код читательского билета: ";
				  lib_card_code_placeholder.placeholder="Номер билета";
				  lib_card_code_placeholder.name="lib-card-code";
				  }else{
					  lib_card_code.innerHTML="Введите код читательского билета: ";
					  lib_card_code_placeholder.placeholder="Номер билета";
					  lib_card_code_placeholder.name="lib-card-code";
				  }
				  /*-------------------------------------------------*/
				  var remove_three =document.getElementById('telephone-text');
				  remove_three.style.display="none";
				  var remove_three_one =document.getElementById('telephone');
				  remove_three_one.style.display="none";
				  /*-------------------------------------------------*/
				  var re=document.getElementById('address-text');
				  re.style.display="none";
				  var move=document.getElementById('address');
				  move.style.display="none";
			  }else if(options[sel].value=="publishers"){
				  var publisher_title =document.getElementById('name-text');
				  publisher_title.innerHTML="Введите названия издательства : ";
				  var publisher_placeholder =document.getElementById('name');
				  publisher_placeholder.placeholder="Названия издательства";
				  publisher_placeholder.name="publisher-title";
				  /*-------------------------------------------------*/
				  var city_code = document.getElementById('surname-text');
				  var city_code_placeholder=document.getElementById('surname');
				  if(city_code.style.display=='none'){
				  city_code.style.display="inline-block";
				  city_code_placeholder.style.display="inline-block";
				  city_code.innerHTML="Введите код города: ";
				  city_code_placeholder.placeholder="Код города";
				  city_code_placeholder.name="city-code";
				  }else{
					  city_code.innerHTML="Введите код города: ";
					  city_code_placeholder.placeholder="Код города";
					  city_code_placeholder.name="city-code";
				  }
				  /*-------------------------------------------------*/
				  var remove_one =document.getElementById('library-text');
				  remove_one.style.display="none";
				  var remove_one_two=document.getElementById('library-card');
				  remove_one_two.style.display="none";
				  /*-------------------------------------------------*/
				  var remove_two=document.getElementById('mail-text');
				  remove_two.style.display="none";
				  var remove_two_one =document.getElementById('mail');
				  remove_two_one.style.display="none";
				  /*-------------------------------------------------*/
				  var remove_three =document.getElementById('telephone-text');
				  remove_three.style.display="none";
				  var remove_three_one =document.getElementById('telephone');
				  remove_three_one.style.display="none";
				  /*-------------------------------------------------*/
				  var re=document.getElementById('address-text');
				  re.style.display="none";
				  var move=document.getElementById('address');
				  move.style.display="none";
				 
			  }else if(options[sel].value=="reader"){
				  var std_name =document.getElementById('name-text');
				  std_name.innerHTML="Введите имя : ";
				  var std_name_placeholder= document.getElementById('name');
				  std_name_placeholder.placeholder="Имя";
				  std_name_placeholder.name="std-name";
				  /*-------------------------------------------------*/
				  var std_surname = document.getElementById('surname-text');
				  var std_surname_placeholder=document.getElementById('surname');
				  if(std_surname.style.display=='none'){
				  std_surname.style.display="inline-block";
				  std_surname_placeholder.style.display="inline-block";
				  std_surname.innerHTML = "Введите фамилию : ";
				  std_surname_placeholder.placeholder="Фамилия";
				  std_surname_placeholder.name="std_surname";
				  }else{
					  std_surname.innerHTML = "Введите фамилию : ";
					  std_surname_placeholder.placeholder="Фамилия";
					  std_surname_placeholder.name="std_surname";  
				  }
				  /*-------------------------------------------------*/
				  var lib_card=document.getElementById('library-text');
				  var lib_card_placeholder=document.getElementById('library-card');
				  if(lib_card.style.display=='none'){
				  lib_card.style.display="inline-block";
				  lib_card_placeholder.style.display="inline-block";
				  lib_card.innerHTML ="Введите номер читательского билета : ";
				  lib_card_placeholder.placeholder="Номер билета";
				  lib_card_placeholder.name="lib_card";
				  }else{
					  lib_card.innerHTML ="Введите номер читательского билета : ";
					  lib_card_placeholder.placeholder="Номер билета";
					  lib_card_placeholder.name="lib_card";  
				  }
				  /*-------------------------------------------------*/
				  var mail=document.getElementById('mail-text');
				  var mail_placeholder=document.getElementById('mail');
				  if(mail.style.display=='none'){
				  mail.style.display="inline-block";
				  mail_placeholder.style.display="inline-block";
				  mail.innerHTML="Введите email : ";
				  mail_placeholder.placeholder="Email";
				  mail_placeholder.name="email";
				  }else{
					  mail.innerHTML="Введите email : ";
					  mail_placeholder.placeholder="Email";
					  mail_placeholder.name="email";  
				  }
				  /*-------------------------------------------------*/
				  var telephone =document.getElementById('telephone-text');
				  var telephone_placeholder=document.getElementById('telephone');
				  if(telephone.style.display=='none'){
				  telephone.style.display="inline-block";
				  telephone_placeholder.style.display="inline-block";
				  telephone.innerHTML="Введите номер телефона : ";
				  telephone_placeholder.placeholder="Телефон";
				  telephone_placeholder.name="telephone";
				  }else{
					  telephone.innerHTML="Введите номер телефона : ";
					  telephone_placeholder.placeholder="Телефон";
					  telephone_placeholder.name="telephone";
				  }
				  /*-------------------------------------------------*/
				  var address=document.getElementById('address-text');
				  var address_placeholder =document.getElementById('address');
				  if(address.style.display=='none'){
				  address.style.display="inline-block";
				  address_placeholder.style.display="inline-block";
				  address.innerHTML="Введите адрес : ";
				  address_placeholder.placeholder="Адрес";
				  address_placeholder.name="address";
				  }else{
					  address.innerHTML="Введите адрес : ";
					  address_placeholder.placeholder="Адрес";
					  address_placeholder.name="address";   
				  }
				  /*-------------------------------------------------*/
			  }else if(options[sel].value=="cities"){
				  var city = document.getElementById('name-text');
				  city.innerHTML="Введите названия города : ";
				  var city_placeholder =document.getElementById('name');
				  city_placeholder.placeholder="Названия города";
				  city_placeholder.name="city-name";
				  /*-------------------------------------------------*/
				  var re_zero = document.getElementById('surname-text');
				  re_zero.style.display="none";
				  var re_zero_one =document.getElementById('surname');
				  re_zero_one.style.display="none";
				  /*-------------------------------------------------*/
				  var remove_one =document.getElementById('library-text');
				  remove_one.style.display="none";
				  var remove_one_two=document.getElementById('library-card');
				  remove_one_two.style.display="none";
				  /*-------------------------------------------------*/
				  var remove_two=document.getElementById('mail-text');
				  remove_two.style.display="none";
				  var remove_two_one =document.getElementById('mail');
				  remove_two_one.style.display="none";
				  /*-------------------------------------------------*/
				  var remove_three =document.getElementById('telephone-text');
				  remove_three.style.display="none";
				  var remove_three_one =document.getElementById('telephone');
				  remove_three_one.style.display="none";
				  /*-------------------------------------------------*/
				  var re=document.getElementById('address-text');
				  re.style.display="none";
				  var move=document.getElementById('address');
				  move.style.display="none";
			  }else if (options[sel].value =="authors"){
				  var author_name = document.getElementById('name-text');
				  author_name.innerHTML="Введите имя автора : ";
				  var author_name_placeholder =document.getElementById('name');
				  author_name_placeholder.placeholder="Имя";
				  author_name_placeholder.name="author-name";
				  /*-------------------------------------------------*/
				  var author_surname = document.getElementById('surname-text');
				  var author_surname_placeholder =document.getElementById('surname');
				  if(author_surname.style.display=='none'){
				  author_surname.style.display="inline-block";
				  author_surname_placeholder.style.display="inline-block";
				  author_surname.innerHTML="Введите фамилию автора : ";
				  author_surname_placeholder.placeholder="Фамилия";
				  author_surname_placeholder.name="author-surname";
				  }else{
					  author_surname.innerHTML="Введите фамилию автора : ";
					  author_surname_placeholder.placeholder="Фамилия";
					  author_surname_placeholder.name="author-surname";  
				  }
				  /*-------------------------------------------------*/
				  var remove_one =document.getElementById('library-text');
				  remove_one.style.display="none";
				  var remove_one_two=document.getElementById('library-card');
				  remove_one_two.style.display="none";
				  /*-------------------------------------------------*/
				  var remove_two=document.getElementById('mail-text');
				  remove_two.style.display="none";
				  var remove_two_one =document.getElementById('mail');
				  remove_two_one.style.display="none";
				  /*-------------------------------------------------*/
				  var remove_three =document.getElementById('telephone-text');
				  remove_three.style.display="none";
				  var remove_three_one =document.getElementById('telephone');
				  remove_three_one.style.display="none";
				  /*-------------------------------------------------*/
				  var re=document.getElementById('address-text');
				  re.style.display="none";
				  var move=document.getElementById('address');
				  move.style.display="none";
			  }
		}
		
</script>
</body>
</html>