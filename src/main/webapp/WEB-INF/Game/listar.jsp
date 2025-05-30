<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Jogos</title>
</head>
<body>
<h1>Jogos</h1>
<a href="game?action=novo">Novo Jogo</a>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Modo de Jogo</th>
        <th>Gêneros</th>
        <th>Plataformas</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="jogo" items="${jogos}">
        <tr>
            <td>${jogo.id}</td>
            <td>${jogo.name}</td>
            <td>${jogo.mode}</td>
            <td>
                <c:forEach var="genre" items="${jogo.genre}">
                    ${genre.name}<br/>
                </c:forEach>
            </td>
            <td>
                <c:forEach var="platform" items="${jogo.platform}">
                    ${platform.name}<br/>
                </c:forEach>
            </td>
            <td>
                <a href="game?action=editar&id=${jogo.id}">Editar</a> |
                <a href="game?action=excluir&id=${jogo.id}" onclick="return confirm('Excluir este jogo?')">Excluir</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
