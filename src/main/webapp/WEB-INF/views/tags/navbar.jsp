<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">                                   
       <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
         <span class="icon-bar"></span>
       </button>
       <a class="navbar-brand" href="<c:url value="/" />">iTrello</a>
       </div>
       <div class="navbar-collapse collapse">  
         <ul class="nav navbar-nav">
           <li class="active"><a href="<c:url value="/" />">Home</a></li>
           <li><a href="<c:url value="/login" />">Login</a></li>
			<c:if test="${not empty session}">
	            <li><a href="<c:url value="/boards" />">Boards</a></li>
	            <li><a href="<c:url value="/logout" />">Logout</a></li>
			</c:if>
         </ul>
       </div>   			      		 
  </div>
</div>