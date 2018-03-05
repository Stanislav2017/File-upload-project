<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
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
    <link href="${contextPath}/resources/css/bootstrap-theme.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Storage system</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value='/document'/>">Add new document <span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="<c:url value='/logout'/>">Logout <span class="glyphicon glyphicon-search" aria-hidden="true"></span></a></li>
                    </ul>
                </li>
            </ul>
            <form class="navbar-form navbar-right">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-sm-6 col-sm-offset-3">
            <div class="jumbotron">
                <h2 class="text-center">Add new file</h2>
                <hr class="featurette-divider">
                <form:form modelAttribute="fileBucket" enctype="multipart/form-data" method="POST">
                    <div class="form-group">
                        <label for="file">File input</label>
                        <form:input type="file" path="file" class="form-control-file" id="file"/>
                        <div class="has-error"><form:errors path="file" class="text-danger"/></div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label">File description</label>
                        <form:textarea type="text" path="description" id="description" class="form-control" placeholder="File description" />
                        <div class="has-error"><form:errors path="description" class="text-danger"/></div>
                    </div>
                    <hr class="featurette-divider">
                    <input type="submit" value="Submit" class="btn btn-primary btn-sm btn-block" />
                    <a href="<c:url value='/documents'/>" class="btn btn-danger btn-sm btn-block">Cancel</a>
                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
</body>
</html>
