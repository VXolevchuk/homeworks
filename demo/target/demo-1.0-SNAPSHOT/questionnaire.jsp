<%--
  Created by IntelliJ IDEA.
  User: Woldemar
  Date: 01.02.2021
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<H1>Questionnaire</H1>

<form action="questionnaire-servlet" method="POST">
    <p><b>What is your mood today?</b></p>
    <p><input type="radio" name="mood" value="good" required>Good<Br>
        <input type="radio" name="mood" value="bad" >Bad<Br>
    <p><b>Which type of tea would you like to drink?</b></p>
    <p><input type="radio" name="tea" value="dark" required>Dark<Br>
        <input type="radio" name="tea" value="green" >Green<Br>
    <p><input type="submit" value="Statistic"></p>
</form>

</body>
</html>