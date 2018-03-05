<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
<link href="${contextPath}/resources/css/bootstrap-theme.css">
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
				<div class="jumbotron">
					<h2 class="text-center">Registration</h2>
					<hr class="featurette-divider">
					<form:form modelAttribute="user" method="POST">
					<form:input type="hidden" path="id" id="id" />
					<form:input type="hidden" path="registrationAt" id="registrationAt" />
						<div class="form-group">
							<label for="username" class="control-label">Username</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
								<form:input type="text" path="username" id="username" class="form-control" placeholder="Username"/>
							</div>
							<div class="has-error"><form:errors path="username" class="text-danger"/></div>
						</div>
						<div class="form-group">
							<label for="password" class="control-label">Password</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
								<form:input type="password" path="password" id="password" class="form-control" placeholder="Password" />
							</div>
							<div class="has-error"><form:errors path="password" class="text-danger"/></div>
						</div>
						<div class="form-group">
							<label for="password" class="control-label">Confirm Password</label>
							<div class="input-group">
								<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
								<form:input type="password" path="confirmPassword" id="confirmPassword" class="form-control" placeholder="Confirm Password" />
							</div>
							<div class="has-error"><form:errors path="confirmPassword" class="text-danger"/></div>
						</div>
						<hr class="featurette-divider">
						<input type="submit" value="Sign In" class="btn btn-primary btn-sm btn-block" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>