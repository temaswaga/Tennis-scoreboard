<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Match Score</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:wght@300&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/tenis.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="/">Home</a>
                <a class="nav-link" href="matches?page=1&filter_by_player_name=">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>${matchTitle}</h1>
        <div class="current-match-image"></div>
        <section class="score">
            <table class="table">
                <thead class="result">
                <tr>
                    <th class="table-text">Player</th>
                    <th class="table-text">Sets</th>
                    <th class="table-text">Games</th>
                    <th class="table-text">Points</th>
                    <th class="table-text">Action</th>
                </tr>
                </thead>
                <tbody>
                <tr class="player1">
                    <span class="bbh-sans-hegarty-regular">
                    <td class="table-text">${player1Name}</td>
                    <td class="table-text">${player1Sets}</td>
                    <td class="table-text">${player1Games}</td>
                    <td class="table-text">${player1Points}</td>
                    <td class="table-text">
                        <form action="match-score" method="post">
                            <input type="hidden" name="uuid" value="${matchUuid}">
                            <input type="hidden" name="player" value="player1">
                            <button type="submit" class="score-btn">+ Point</button>
                        </form>
                    </td>
                </span></tr>
                <tr class="player2">
                    <td class="table-text">${player2Name}</td>
                    <td class="table-text">${player2Sets}</td>
                    <td class="table-text">${player2Games}</td>
                    <td class="table-text">${player2Points}</td>
                    <td class="table-text">
                        <form action="match-score" method="post">
                            <input type="hidden" name="uuid" value="${matchUuid}">
                            <input type="hidden" name="player" value="player2">
                            <button type="submit" class="score-btn">+ Point</button>
                        </form>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>

        <!-- Простая кнопка для обновления страницы -->
        <div style="text-align: center; margin-top: 20px;">
            <a href="match-score?uuid=${matchUuid}" class="score-btn">Refresh Score</a>
        </div>
    </div>
</main>
</body>
</html>