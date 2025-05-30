<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Gênero</title>
</head>
<body>
<h1>Editar Gênero</h1>
<form action="genre" method="post">
    <input type="hidden" name="id" value="${genero.id}" />
    Nome: <input type="text" name="nome" value="${genero.name}" required /><br/><br/>
    <button type="submit">Salvar</button>
</form>
<a href="genre?action=listar">Voltar para lista</a>
</body>
</html>
