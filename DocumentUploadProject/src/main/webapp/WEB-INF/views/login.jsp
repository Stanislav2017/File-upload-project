<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div
				class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
				<div class="jumbotron">
					<h2 class="text-center">Login page</h2>
					<hr class="featurette-divider">
					<c:url var="loginUrl" value="/login" />
					<form action="${loginUrl}" method="post">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger"><p>Invalid username and password.</p></div>
						</c:if>
						<c:if test="${param.logout != null}">
							<div class="alert alert-success"><p>You have been logged out successfully.</p></div>
						</c:if>
						<div class="form-group">
							<label for="username" class="control-label">Username</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
								<input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
							</div>
						</div>
						<div class="form-group">
							<label for="password" class="control-label">Password</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
								<input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
						<hr class="featurette-divider">
						<input type="submit" value="Log In" class="btn btn-primary btn-sm btn-block" />
						<a href="<c:url value='/registration'/>" class="btn btn-success btn-sm btn-block">Sign In</a>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>