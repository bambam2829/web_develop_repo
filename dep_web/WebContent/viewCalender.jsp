<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script>
      function displaySomething(e) {
        var date = e.target.value
        var d = "2018-02-08"
        if(d == date){
        	alert(正解)
        }else{
        	alert(違うよ)
        }
      }
    </script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>testpage</title>
</head>
<body>
<h1>誕生日選択</h1>
<form action="DateServlet" method="post">
<input type="date" value="2018-01-01" name="date">
<br>
<input type="submit" value="送信"> 
</form>
</body>
</html>