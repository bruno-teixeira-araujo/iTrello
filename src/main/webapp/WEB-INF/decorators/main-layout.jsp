<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>iTrello</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
        rel="stylesheet"  type="text/css" />
  <link href="<c:url value="/resources/css/main.css" />"
        rel="stylesheet"  type="text/css" />

  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

</head>

<body>
    <div id="wrap">

        <c:import url="/WEB-INF/views/tags/navbar.jsp"/>

        <div class="container">
            <div class="row">
				<div class="col-md-1">
				</div>

                <div class="col-md-10">
                    <div class="jumbotron">
                      <c:import url="/WEB-INF/views/tags/banner.jsp"/>
                    </div>

                    <div class="row">
                    	<div class="col-md-1">
						</div>
	                    <div class="col-md-10">
	                    	<hr class="soften">
	                        <decorator:body />
	                    </div>
                    	<div class="col-md-1">
						</div>
                    </div>
                </div>
                
                <div class="col-md-1">
				</div>
            </div>
          </div>

    </div>

    <c:import url="/WEB-INF/views/tags/footer.jsp"/>

	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</body>
</html>
