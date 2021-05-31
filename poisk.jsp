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
<link href="poisk.css" type="text/css" rel="stylesheet" >
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
			 <h4 class="article-sub-subtitle">Поиск в Базе Данных</h4>
				<form method="post" action="ServletPoisk" >
				<!-- forma starts here -->
					<div class="forma">
					<!-- labels -->
						<label for="name" class="name-label">Введите имя :<input type="text" id="name" class="name" placeholder="Имя" name="std_name"></label>	
						<label for="surname" class="surname-label">Введите фамилию :<input type="text" id="surname" class="surname" placeholder="Фамилия" name="std_surname"></label>
						<label for="library-card" class="library-card-label">Введите номер читательского билета :<input type="text" id="library-card" class="library-card" placeholder="Номер билета" name="lib_card"></label>
					<!-- labels // -->
					</div>
					<!-- forma finishes here -->
					<div class="forma-button">
					<label for="button"><input type="submit" id="button" value="Отправить"></label>
					<h2 class ="servlet"><%if(request.getAttribute("message") != null){
							out.println("<p>"+request.getAttribute("message").toString()+"</p>");
						}%>
					</h2>
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

	


</script>
</body>
</html>