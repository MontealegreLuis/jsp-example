<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String name = request.getParameter("name"); %>
<% String language = request.getParameter("language"); %>
<% boolean newsLetter = request.getParameter("newsletter") != null; %>
<% String age = request.getParameter("age"); %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Codeup coding dojo</title>
    <link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
        crossorigin="anonymous"
    >

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <h1>Welcome to the coding dojo!</h1>
    <% if (name != null) { %>
        Name <%= name %> <br>
    <% } %>
    <% if (language !=null) { %>
        <% if (language.equalsIgnoreCase("java")) { %>
            You are now registered for <strong>Java Bootcamp</strong><br>
        <% } else if (language.equalsIgnoreCase("php")) { %>
            You are now registered for <strong>PHP</strong><br>
        <% } else { %>
            Invalid language code.  You are not registered.
        <% } %>
    <% } %>
    <% if (age != null) { %>
        <% if(age.equals("0-10")) { %>
            You're way too young to be in the DOJO! Come back when you're older...<br>
        <% } else { %>
            You are <%= age %> years old!<br>
        <% } %>
    <% } %>
    <% if (newsLetter) { %>
        You'll receive our newsletter...
    <% } else { %>
        You won't receive our newsletter...
    <% } %>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"
></script>
</body>
</html>
