<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>

	<div id="boardsContent">
		<h2>List of Boards</h2>
		<br>
		
		<c:if test="${empty boards}">
			<p>No boards to display</p>			
		</c:if>
		<c:if test="${not empty boards}">
		    <table class="table table-hover">
		      <tbody>
	            <tr>
	                <th>Name</th>
	                <th>Link</th>   
	            </tr>
				<c:forEach var="board" items="${boards}">
		            <tr>
		                <td>${board.name}</td>
		                <td>
		                	<c:url value="/board/${board.id}" var="url"/>
 							<a href="<c:out value='${url}'/>">Show</a>
 						</td>	   
		            </tr>
				</c:forEach>
			  </tbody>
		    </table>
		</c:if>

	</div>