<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
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
        <h1>Matches</h1>
        <div class="input-container">
            <form action="matches" method="get" class="input-container">
                <input type="hidden" name="page" value="1" />
            <input class="input-filter" name="filter_by_player_name" placeholder="Filter by name" type="text" value="${player_name_filter}"/>
                </form>
            <div class="matches-container">
                <a href="matches?page=1&filter_by_player_name=">
                    <button class="btn-filter">Reset filter</button>
                </a>
            </div>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <tr>
                <span class="bbh-sans-hegarty-regular">
                <td class="table-text">${player1_1Name}</td>
                <td class="table-text">${player1_2Name}</td>
                <td><span class="winner-name-td">${winner1_name}</span></td>
                    </span>
            </tr>
            <tr>
                <span class="bbh-sans-hegarty-regular">
                <td class="table-text">${player2_1Name}</td>
                <td class="table-text">${player2_2Name}</td>
                <td><span class="winner-name-td">${winner2_name}</span></td>
                    </span>
            </tr>
            <tr>
                <span class="bbh-sans-hegarty-regular">
                <td class="table-text">${player3_1Name}</td>
                <td class="table-text">${player3_2Name}</td>
                <td><span class="winner-name-td">${winner3_name}</span></td>
                    </span>
            </tr>
            <tr>
                <span class="bbh-sans-hegarty-regular">
                <td class="table-text">${player4_1Name}</td>
                <td class="table-text">${player4_2Name}</td>
                <td><span class="winner-name-td">${winner4_name}</span></td>
                    </span>
            </tr>
            <tr>
                <span class="bbh-sans-hegarty-regular">
                <td class="table-text">${player5_1Name}</td>
                <td class="table-text">${player5_2Name}</td>
                <td><span class="winner-name-td">${winner5_name}</span></td>
                    </span>
            </tr>
            <tr>
                <span class="bbh-sans-hegarty-regular">
                <td class="table-text">${player6_1Name}</td>
                <td class="table-text">${player6_2Name}</td>
                <td><span class="winner-name-td">${winner6_name}</span></td>
                    </span>
            </tr>
        </table>

        <div class="pagination">
            <a class="prev" href="matches?page=${page_number_prev}&filter_by_player_name=${player_name_filter}"> < </a>
            <a class="num-page" href="matches?page=${currentPage}&filter_by_player_name=${player_name_filter}">${currentPage}</a>
            <a class="num-page" >/</a>
            <a class="num-page" href="matches?page=${totalNumberOfPages}&filter_by_player_name=${player_name_filter}">${totalNumberOfPages}</a>
            <a class="next" href="matches?page=${page_number_next}&filter_by_player_name=${player_name_filter}"> > </a>
        </div>
    </div>
</main>
</body>
</html>
