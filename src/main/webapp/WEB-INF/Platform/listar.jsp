<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Plataformas</title>
</head>
<body>
<h1>Plataformas</h1>
<a href="platform?action=novo">Nova Plataforma</a>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Ações</th>
    </tr>
    <c:forEach var="platform" items="${plataformas}">
        <tr>
            <td>${platform.id}</td>
            <td>${platform.name}</td>
            <td>
                <a href="platform?action=editar&id=${platform.id}">Editar</a> |
                <a href="platform?action=excluir&id=${platform.id}" onclick="return confirm('Excluir esta plataforma?')">Excluir</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="game?action=listar">Voltar para Jogos</a>
</body>
</html>
