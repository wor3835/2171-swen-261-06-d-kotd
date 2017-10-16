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

      <#if currentPlayer??>
                  <u>List of Players:<u/>
                  <br/>
                  <form>
                  <#list playerLobby.iterator() as player>
                     <#if !player.equals(currentPlayer)>
                        <a href='/game' name='opponent' >${player.getName()}</a>
                        </br>
                        </form>
                     </#if>
                  </#list>
            <#else>
               <u>Players Online: ${playerLobby.getPlayerCount()}<u/>
            </#if>
    </div>
    
  </div>
</body>
</html>
