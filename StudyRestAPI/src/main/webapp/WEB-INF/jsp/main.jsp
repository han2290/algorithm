<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Hello, world!</title>
    <style type="text/css">
    	html, 
		body {
		    height: 100%;
		}
    </style>
</head>
<body class="container">
	<header class="row d-flex justify-content-center align-items-start  h-25 w-100">
		<h1 class="align-self-center">JIRA REST API TEST</h1>
	</header>
	
	<section class="row align-self-center  h-50">
		<div class="col-6 align-self-center ">
			<form action="/request" method="GET" class="w-100">
				<select class="form-control w-100" name="methodtype">
			      <option value="POST">POST</option>
			      <option value="GET">GET</option>
			    </select>
			    <input type="text" class="form-control w-100" name="resturl" placeholder="URL" value="${preUrl}">
			    <textarea class="form-control w-100" rows="5" name="jsonstring" placeholder="JSON or NULL">${preString}</textarea>
			    <button type="submit" class="btn btn-secondary w-100">
			    	전송
			    </button>			    
			</form>
		</div>
		<div class="col-6 align-self-center ">
			<textarea class="form-control w-100" rows="10" readonly="readonly">${json}${error}</textarea>
		</div>
	</section>
	
	
	<footer class="row align-items-end h-25"></footer>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>