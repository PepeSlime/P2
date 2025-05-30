<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastrar Plataforma</title>
</head>
<body>
<h1>Nova Plataforma</h1>
<form action="platform" method="post">
    Nome: <input type="text" name="nome" required /><br/><br/>
    <button type="submit">Salvar</button>
</form>
<a href="platform?action=listar">Voltar para lista</a>
</body>
</html>
