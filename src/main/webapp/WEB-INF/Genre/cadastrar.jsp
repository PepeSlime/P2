<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastrar Gênero</title>
</head>
<body>
<h1>Novo Gênero</h1>
<form action="genre" method="post">
    Nome: <input type="text" name="nome" required /><br/><br/>
    <button type="submit">Salvar</button>
</form>
<a href="genre?action=listar">Voltar para lista</a>
</body>
</html>
