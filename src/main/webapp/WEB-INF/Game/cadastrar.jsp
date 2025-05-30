<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Cadastrar Jogo</title>
</head>
<body>
<h1>Novo Jogo</h1>
<form action="game" method="post">
    Título: <input type="text" name="titulo" required /><br/><br/>
    
    Modo de Jogo:
    <select name="modoJogo" required>
        <option value="">--Selecione--</option>
        <option value="SINGLEPLAYER">Singleplayer</option>
        <option value="MULTIPLAYER">Multiplayer</option>
        <option value="COOP">Co-op</option>
    </select><br/><br/>
    
    Gêneros:<br/>
    <c:forEach var="genre" items="${generos}">
        <input type="checkbox" name="generos" value="${genre.id}"/> ${genre.name}<br/>
    </c:forEach>
    <br/>
    
    Plataformas:<br/>
    <c:forEach var="platform" items="${plataformas}">
        <input type="checkbox" name="plataformas" value="${platform.id}"/> ${platform.name}<br/>
    </c:forEach>
    <br/>
    
    <button type="submit">Salvar</button>
</form>
<a href="game?action=listar">Voltar para lista</a>
</body>
</html>
