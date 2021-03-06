<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="5">
    <title>${title} | Web Checkers</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">
  
    <h1>Web Checkers</h1>
    
    <div class="navigation">
          <a href="/">my home</a>
          <#if currentPlayer??>
                      <a href="/signout">sign out [${currentPlayer.name}]</a>
                <#else>
                      <a href="/signin">sign in</a>
                </#if>
          <a href="/stats">my stats</a>
    </div>
    <#if message??>
        <i><font color=blue>${message}</font></i>
    </#if>
    <div class="body">
      <p>Welcome to the world of online Checkers.</p>
      <i><font color=red>${error}</font></i>
      <br/><br/>

      <#if currentPlayer??>
                  <u>List of Players:</u>
                  </br>

                  <#list playerLobby.iterator() as player>
                     <#if !player.equals(currentPlayer)>
                     <h4>${player.getName()}:
                     <#if player.isInGame()>
                        <form action="/spectate" method="POST">
                            <button type="submit" name="opponent" value="${player.getName()}" style=border-radius:4px>
                                Spectate
                            </button>
                            </br>
                        </form>

                     <#else>
                        <form action="/start" method="POST">
                            <button type="submit" name="opponent" value="${player.getName()}" style=border-radius:4px>
                                Challenge
                            </button>
                            </br>
                        </form>
                     </#if>
                     </h4>
                     </#if>
                  </#list>
            <#else>
               <u>Players Online: ${playerLobby.getPlayerCount()}<u/>
            </#if>
    </div>
    
  </div>
</body>
</html>
