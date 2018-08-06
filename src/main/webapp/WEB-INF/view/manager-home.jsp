<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Payroll</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

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
          <a class="navbar-brand" href="#">Payroll Application</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="home.jsp">Main</a></li>
          </ul>
          <ul class="nav navbar-nav navbar-right">
            <li> Welcome ${user.firstName} </li>
            <form:form action="${pageContext.request.contextPath}/logout" 
						  method="POST" class="form-horizontal">
				<input type="submit" class="btn btn-primary" value="Logout"/>
			</form:form>

          </ul>
        </div>
      </div>
    </nav>

    <header id="header">
      <div class="container">
        <div class="row">
          <div class="col-md-10">
            <h1><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Payroll Web App <small>Manage Your Site</small></h1>
          </div>
        </div>
      </div>
    </header>

    <section id="main">
      <div class="container">
        <div class="row">
          <div class="col-md-3">
            <div class="list-group">
              <a href="index.html" class="list-group-item active main-color-bg">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Dashboard
              </a>
               <spring:url value="/user/showUserProfile" var="userProfileUrl"/>
               <spring:url value="/user/list" var="userListUrl"/>
               <spring:url value="/benefit/list" var="benefitListUrl"/>
               <spring:url value="/department/list" var="departmentListUrl"/>
               <spring:url value="/job/list" var="jobListUrl"/>
               <spring:url value="/payroll/list" var="payrollListUrl"/>
               <spring:url value="/performance/list" var="performanceListUrl"/>
              <a href="${userProfileUrl}" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Your Profile </a>
              <a href="${userListUrl}" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Users </a>
              <a href="${benefitListUrl}" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Benefits</a>
              <a href="${departmentListUrl}" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Departments </a>
              <a href="${jobListUrl}" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Jobs</a>
              <a href="${payrollListUrl}" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Payroll</a>
              <a href="${performanceListUrl}" class="list-group-item"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> Performance</a>
            </div>
          </div>
          <div class="col-md-9">
            <!-- Website Overview -->
            <div class="panel panel-default">
              <div class="panel-heading main-color-bg">
                <h3 class="panel-title">Website Overview</h3>
              </div>
              <div class="panel-body">
                <div class="col-md-3">
                  <div class="well dash-box">
                    <h2><span class="glyphicon glyphicon-user" aria-hidden="true"></span></h2>
                    <h4>Users</h4>
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="well dash-box">
                    <h2><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> </h2>
                    <h4>Benefits</h4>
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="well dash-box">
                    <h2><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> </h2>
                    <h4>Departments</h4>
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="well dash-box">
                    <h2><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> </h2>
                    <h4>Jobs</h4>
                  </div>
                </div>
                 <div class="col-md-3">
                  <div class="well dash-box">
                    <h2><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> </h2>
                    <h4>Payrolls</h4>
                  </div>
                </div>
                <div class="col-md-3">
                  <div class="well dash-box">
                    <h2><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> </h2>
                    <h4>Perfomances</h4>
                  </div>
                </div>
              </div>
              </div>
          </div>
        </div>
      </div>
    </section>

    <footer id="footer">
    </footer>

  <script>
     CKEDITOR.replace( 'editor1' );
 </script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>