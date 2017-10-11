<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
    <title>${title} | Sign-In</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">

    <h1>Sign-In</h1>

    <div class="navigation">
          <a href="/">my home</a>
    </div>

    <div class="body">
    <form action="/signin" method="POST">
      <p>Please sign-in if you already have an existing account</p>
      <br/>
      User Name: <input name = "username" />
      <br/>
      <button type="submit">Enter</button>
    </form>
    </div>

  </div>
</body>
</html>
