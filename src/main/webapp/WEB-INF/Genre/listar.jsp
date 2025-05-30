<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Gêneros</title>
</head>
<body>
<h1>Gêneros</h1>
<a href="genre?action=novo">Novo Gênero</a>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="genre" items="${generos}">
        <tr>
            <td>${genre.id}</td>
            <td>${genre.name}</td>
            <td>
                <a href="genre?action=editar&id=${genre.id}">Editar</a> |
                <a href="genre?action=excluir&id=${genre.id}" onclick="return confirm('Excluir este gênero?')">Excluir</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="game?action=listar">Voltar para Jogos</a>
</body>
</html>
