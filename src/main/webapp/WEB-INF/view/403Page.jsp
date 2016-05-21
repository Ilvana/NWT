<%@page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <jsp:include page="headers.jsp"/>

<body>
<div class="container text-center">
    <div class="logo-404">
        <a href="/#/"><img src="resources/images/home/logo.png" alt=""/></a>
    </div>
    <div class="content-404">
        <img src="resources/images/403.png" class="img-responsive" alt=""/>

        <h1><b>OPPS!</b> You don't have permission to access this page.</h1>

        <h2><a href="/#/">Bring me back Home</a></h2>
    </div>
</div>

<jsp:include page="js-scripts.jsp"/>
</body>
</html>