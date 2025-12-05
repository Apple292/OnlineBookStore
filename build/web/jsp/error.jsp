<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- the inlcude stament for JSTL -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Error Page</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <c:set var="msg" value="${sessionScope.result}"/> <!-- accessing the books session object -->
        
        <h3>
            "${msg}"
        </h3>

<c:if test="${not empty sessionScope}">
            <c:remove var="sessionScope" scope="session"/>
        </c:if>
    </body>
</html>