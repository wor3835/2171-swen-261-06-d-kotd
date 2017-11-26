<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">

    <h1>Game Over</h1>

    <div class="navigation">
          <a href="/">my home</a>
          <#if currentPlayer??>
                      <a href="/signout">sign out [${currentPlayer.name}]</a>
                <#else>
                      <a href="/signin">sign in</a>
                </#if>
          <a href="/stats">my stats</a>
    </div>

    <div class="body">
      <h2>
        ${message}
      </h2>

      <form action="/saveGame" method="POST">
        <#if error??>
            <i><font color=red>${error}</font></i>
        </#if>
        <p>Would you like to save the game you just played</p>
        <br/>
        Name: <input name = "gamename"/>
        <button type="submit" style=border-radius:4px>Yes</button>
      </form>

      <br/>
      <a href="/">Return to main page</a>

    </div>

  </div>
</body>
</html>