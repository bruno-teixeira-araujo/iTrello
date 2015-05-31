<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

	<div id="formsContent">
		<h2>Login</h2>
		<ol>
          	<li>Login on <a href="https://trello.com" target="_blank">Trello</a></li>
			<li>Go to <a href="https://trello.com/app-key" target="_blank">Trello API</a> to get your Trello key and secret.</li>
			<li>Fill the next fields on Login Info:</li>
		</ol>
        <br>
        
		<form:form id="form" method="post" modelAttribute="login" class="form-horizontal" role="form">                 
		  	<fieldset>
		  		<legend>Login Info</legend>
		  		
		  		<div class="form-group error">
					<form:label class="col-lg-2 control-label" path="key"> 
						Trello key
					</form:label>
					<div class="col-lg-6">
						<form:input path="key" class="form-control" placeholder="Trello API key"/>
					</div>
				</div>	
				<div class="form-group warning">
					<form:label class="col-lg-2 control-label" path="secret">
		  				Trello secret
					</form:label>
					<div class="col-lg-6">
						<form:input path="secret" class="form-control" placeholder="Trello API secret"/>
					</div>
				</div>
		  	</fieldset>
	
            <hr/>
            
			<p><button type="submit" class="btn btn-primary">Submit</button></p>				
		</form:form>
	</div>