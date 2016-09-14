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
    <form method="post" action="register.jsp">
        <div class="form-group">
            <label for="name">Name</label>
            <input
                type="text"
                class="form-control"
                name="name"
                id="name"
            >
        </div>
        <div class="radio">
          <label>
            <input type="radio" name="age" value="0-10">
            0-10
          </label>
        </div>
        <div class="radio">
          <label>
            <input type="radio" name="age" value="11-20">
            11-20
          </label>
        </div>
        <div class="radio">
          <label>
            <input type="radio" name="age" value="21-30">
            21-30
          </label>
        </div>
        <div class="radio">
          <label>
            <input type="radio" name="age" value="31-40">
            31-40
          </label>
        </div>
        <div class="radio">
          <label>
            <input type="radio" name="age" value="41-50">
            41-50
          </label>
        </div>
        <div class="radio">
          <label>
            <input type="radio" name="age" value="50+">
            50+
          </label>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="newsletter" name="newsletter">
                Subscribe to newsletter
            </label>
        </div>
        <div class="form-group">
            <label for="language">Language</label>
            <select name="language" id="language" class="form-control">
                <option value="PHP">PHP</option>
                <option value="Java">Java</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit!</button>
    </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
    src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
    crossorigin="anonymous"
></script>
</body>
</html>
