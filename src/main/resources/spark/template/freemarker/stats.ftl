<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
    <!-- <meta http-equiv="refresh" content="10"> -->
    <!-- No need for refresh on signin page -->
    <title>${title} | Sign-In</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
  <div class="page">

    <h1>My Stats</h1>

    <div class="navigation">
          <a href="/">my home</a>
          <#if currentPlayer??>
              <a href="/signout">sign out [${currentPlayer.name}]</a>
          <#else>
              <a href="/signin">sign in</a>
          </#if>
    </div>

    <div class="body">
        <#if currentPlayer??>
            <#if currentPlayer.gamesSaved()=0>
                <h2>No stats to display</h2>
            <#else>
                <#list currentPlayer.gameIterator() as game>
                   <h3>${game} : <#if currentPlayer.won(game)>
                        (win)
                        <#else>
                        (loss)
                        </#if> in ${currentPlayer.getGame(game).movesListSize()} moves

                      <form action="/replay" method="POST">
                          <button type="submit" name="savegame" value="${game}" style=border-radius:4px>
                                Replay
                          </button>
                      </form>
                      <form action="/delete" method="POST">
                          <button type="submit" name="savegame" value="${game}" style=border-radius:4px>
                             Delete
                          </button>
                      </form>
                   </h3>

                </#list>
            </#if>
        <#else>
            <i><font color=red>Must be signed in to view stats</font></i>
        </#if>

    </div>

  </div>
</body>
</html>