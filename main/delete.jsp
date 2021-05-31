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
<link href="delete.css" type="text/css" rel="stylesheet" >
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
						<li><a class="search" href="poisk.jsp" >Поиск</a></li>
						<li><a class="add" href="dobav.jsp" >Добавление</a></li>
						<li><a class="edit" href="edit.jsp" >Редактирование</a></li>
						<li><a class="delete" href="delete.jsp" >Удаление</a></li>
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
			 <h4 class="article-sub-subtitle" id="article_subtitle">Редактирование базы данных</h4>
				<form method="post" action="ServletDelete">
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
						</label>
						<!-- label -->
						<label for="name" id="id"><span id="id-text">Введите ID строки  :</span><input type="text" id="id" class="id" placeholder="ID" name="std_id"></label>
						<!-- label -->
					</div>
					<!-- forma finishes here -->
					<div class="forma-button">
					<label for="button"><input type="submit" id="button" value="Отправить"></label>
					</div>
						<span >
						 <%if(request.getAttribute("del") != null){
							out.println("<h6 class='servlet' >"+request.getAttribute("del").toString()+"</h6>");
							}%>
					</span>	
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
		
		
				
</script>
</body>
</html>
