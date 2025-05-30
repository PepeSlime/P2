<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Plataforma</title>
</head>
<body>
<h1>Editar Plataforma</h1>
<form action="platform" method="post">
    <input type="hidden" name="id" value="${plataforma.id}" />
    Nome: <input type="text" name="nome" value="${plataforma.name}" required /><br/><br/>
    <button type="submit">Salvar</button>
</form>
<a href="platform?action=listar">Voltar para lista</a>
</body>
</html>
