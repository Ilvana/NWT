<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<!DOCTYPE html>
<html lang="en" class="no-js" ng-app="Application">

<jsp:include page="headers.jsp"/>

<body>

<jsp:include page="menu.jsp"/>

<div class="container-fluid">
    <div ng-view></div>
</div>

<jsp:include page="footer.jsp"/>
<jsp:include page="js-scripts.jsp"/>
<script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.7/angular-material.min.js"></script>
<script src="resources/js/jquery.datetimepicker.full.min.js"></script>
</body>
</html>