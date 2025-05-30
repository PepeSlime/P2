<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Editar Jogo</title>
</head>
<body>
<h1>Editar Jogo</h1>
<form action="game" method="post">
    <input type="hidden" name="id" value="${jogo.id}" />
    
    Título: <input type="text" name="titulo" value="${jogo.name}" required /><br/><br/>
    
    Modo de Jogo:
    <select name="modoJogo" required>
        <option value="">--Selecione--</option>
        <option value="SINGLEPLAYER" ${jogo.mode == 'SINGLEPLAYER' ? 'selected' : ''}>Singleplayer</option>
        <option value="MULTIPLAYER" ${jogo.mode == 'MULTIPLAYER' ? 'selected' : ''}>Multiplayer</option>
        <option value="COOP" ${jogo.mode == 'COOP' ? 'selected' : ''}>Co-op</option>
    </select><br/><br/>
    
    Gêneros:<br/>
    <c:forEach var="genre" items="${generos}">
        <input type="checkbox" name="generos" value="${genre.id}"
            <c:if test="${jogo.genre != null && jogo.genre.contains(genre)}">checked</c:if> />
        ${genre.name}<br/>
    </c:forEach>
    <br/>
    
    Plataformas:<br/>
    <c:forEach var="platform" items="${plataformas}">
        <input type="checkbox" name="plataformas" value="${platform.id}"
            <c:if test="${jogo.platform != null && jogo.platform.contains(platform)}">checked</c:if> />
        ${platform.name}<br/>
    </c:forEach>
    <br/>
    
    <button type="submit">Salvar</button>
</form>
<a href="game?action=listar">Voltar para lista</a>
</body>
</html>
