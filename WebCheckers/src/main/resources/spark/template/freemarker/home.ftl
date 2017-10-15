<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <meta http-equiv="refresh" content="10">
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
    </div>
    
    <div class="body">
      <p>Welcome to the world of online Checkers.</p>
      <br/><br/>
      <u>Players Online: <u/>
      <#if currentPlayer??>
                  <br/>
                  List of Players:
                  <p id = "player" onclick ="" >${playerLobby.getPlayersExcept(currentPlayer)}</p>
                  <#list playerLobby.iterator() as player>
                     <#if !player.equals(currentPlayer)>
                        player: ${player.getName()}
                     </#if>
                  </#list>
            <#else>
            ${playerLobby.getPlayerCount()}
            </#if>
    </div>
    
  </div>
</body>
</html>
