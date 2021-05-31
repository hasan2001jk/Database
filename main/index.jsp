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
<link href="style.css" type="text/css" rel="stylesheet" >
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
						<li><a class="search" href="poisk.jsp" id="a"  onclick="alert('Введите логин и пароль !')">Поиск</a></li>
						<li><a class="add" href="dobav.jsp" id="a"  onclick="alert('Введите логин и пароль !')">Добавление</a></li>
						<li><a class="edit" href="edit.jsp" id="a"  onclick="alert('Введите логин и пароль !')">Редактирование</a></li>
						<li><a class="delete" href="delete.jsp" id="a"  onclick="alert('Введите логин и пароль !')">Удаление</a></li>
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
				<form method="post" action="MyServlet" name="Form">
					<div class="forma">
						<label for="login" class="login">Введите логин : <input type="text" id="login" name="login"></label>
						<label for="password" class="password">Введите пароль : <input type="password" id="password" name="password"></label>
					</div>
					<div class="forma-button">
					<label for="button"><input type="submit" id="button" value="Войти"></label>
						<span >
						 <%if(request.getAttribute("info") != null){
							out.println("<h6 class='servlet' >"+request.getAttribute("info").toString()+"</h6>");
							}%>
					</span>	
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
						<li><a class="reader" href="tables.jsp"  id="a"  onclick="alert('Введите логин и пароль !')">Читатель</a></li>
						<li><a class="book" href="tables.jsp" id="a"   onclick="alert('Введите логин и пароль !')">Книги</a></li>
						<li><a class="lending-books" href="tables.jsp" target="_blank" id="a"  onclick="alert('Введите логин и пароль !')">Выдача</a></li>
						<li><a class="publishers"  href="tables.jsp" target="_blank" id="a"  onclick="alert('Введите логин и пароль !')">Издательство</a></li>
						<li><a class="cities" href="tables.jsp" target="_blank" id="a"  onclick="alert('Введите логин и пароль !')">Город</a></li>
						<li><a class="authors" href="tables.jsp" target="_blank" id="a" onclick="alert('Введите логин и пароль !')">Авторы</a></li>
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
		var lg=document.getElementById('login');
		var pas=document.getElementById('password');
		document.body.style.transition = "all linear 1s";
		document.body.style.backgroundColor = "gold";
		if(bg1=="gold"){
			document.body.style.backgroundColor = "white";
		}
		};

		if(lg != "user"){
			if(pas !="user"){
				alert("Введён неправельный пароль или логин");
			}
			
		}else if(pas !="user"){
			if(lg != "user"){
				alert("Введён неправельный пароль или логин");
			}
		}
		
		
			
	</script>
			}
</body>
</html>
