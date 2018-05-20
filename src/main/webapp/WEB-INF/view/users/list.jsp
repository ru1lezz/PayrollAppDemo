
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Users</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

</head>
<body>
	<nav class="navbar navbar-default">
	      <div class="container">
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="#">Payroll System</a>
	        </div>
	        <div id="navbar" class="collapse navbar-collapse">
	          <ul class="nav navbar-nav">
	          <li><a href="#">Benefits</a></li>
	            <li><a href="#">Departments</a></li>
	            <li><a href="#">Payroll</a></li>
	            <li><a href="#">Performance</a></li>
	            <li class="active"><a href="users.html">Users</a></li>
	          </ul>
	          <ul class="nav navbar-nav navbar-right">
	            <li><a href="#">Welcome, user</a></li>
	            <li><a href="login.html">Logout</a></li>
	          </ul>
	        </div><!--/.nav-collapse -->
	      </div>
	    </nav>
	
	    <header id="header">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-10">
	            <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Users</h1>
	          </div>
	        </div>
	      </div>
	    </header>
	
	    <section id="breadcrumb">
	      <div class="container">
	        <ol class="breadcrumb">
	          <li><a href="index.html">Dashboard</a></li>
	          <li class="active">Users</li>
	        </ol>
	      </div>
	    </section>
	
	    <section id="main">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-3">
	            <div class="list-group">
	              <a href="index.html" class="list-group-item active main-color-bg">
	                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Dashboard
	              </a>
	              <a href="pages.html" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Departments </a>
	              <a href="posts.html" class="list-group-item"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Payroll </a>
	              <a href="users.html" class="list-group-item"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Users </a>
	            </div>

	          </div>
	          <div class="col-md-9">
	            <!-- Website Overview -->
	            <div class="panel panel-default">
	              <div class="panel-heading main-color-bg">
	                <h3 class="panel-title">Users</h3>
	              </div>
	              <div class="panel-body">
	                <div class="row">
	                      <div class="col-md-12">
	                          <input class="form-control" type="text" placeholder="Filter Users...">
	                      </div>
	                </div>
	                <br>
	                <input type="button" value="Add User" onclick="window.location.href='showAddForm'; return false;">
	                <table class="table table-striped table-hover">
	                      <tr>
	                        <th>First Name</th>
	                        <th>Last Name</th>
	                        <th>Action</th>
	                      </tr>
	                      <c:forEach var="tempUser" items="${users}">
	                      <c:url var="updateLink" value="/user/showUpdateForm">
	                      	<c:param name="id" value="${tempUser.id}"/>
	                      </c:url>
							<tr>
								<td> ${tempUser.firstName} </td>
								<td> ${tempUser.lastName} </td>
								
								<td>
									<a href="${updateLink}">Update</td></a>
								</td>
							</tr>
						</c:forEach>
	                    </table>
	              </div>
	              </div>
	
	          </div>
	        </div>
	      </div>
	    </section>
	
	    <footer id="footer">
	    </footer>
	
	    <!-- Modals -->
	
	
	  <script>
	     CKEDITOR.replace( 'editor1' );
	 </script>
	
	    <!-- Bootstrap core JavaScript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  </body>
</html>