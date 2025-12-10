
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> <!-- the inlcude stament for JSTL -->

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Bookshop</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true"/>
        <%@ page import="model.*" %>
        <%@ page import="java.util.*" %>
        <%@ page import="java.text.*" %>

        <h1>The following items are in your shopping cart</h1>
        <form name="form1" method="post" action="./books">
            <input type="hidden" name="action" value="update_cart">
            <table>
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Price/unit</th>
                        <th>Quantity</th>
                        <th>Subtotal</th>
                        <th>Remove</th>
                    </tr>
                </thead>
                <tbody>
                <c:set var="items" value="${sessionScope.cart}" />
                 <c:set var="totalCostOfOrder" value="0.00" />
                <c:forEach var="item" items="${items}">
                    <c:set var="entry" value="${item.value}"/>
                    <c:set var="isbn" value="${item.getKey()}"/>
                    <c:set var="item" value="${item.getValue()}"/>
                    <c:set var="title" value="${entry.getBook().getTitle()}"/>
                    <c:set var="price" value="${entry.getBook().getDollarPrice()}"/>
                    <c:set var="quantity" value="${item.getQuantity()}"/>
                    <c:set var="cost" value="${item.getOrderCost()}"/>
                    <c:set var="dollarCost" value="${item.getDollarOrderCost()}"/>
                    <c:set var="totalCostOfOrder" value="${totalCostOfOrder + cost}" />
                
                    <tr>
                        <td>${isbn}</td>
                        <td>${title}</td>
                        <td>${price}</td>
                        <td>
                            <input type="text" name="${isbn}" size="2" value="${quantity}" maxlength="4">
                        </td>
                        <td>${dollarCost}</td>
                        <td>
                            <div align="center">
                                <input type="checkbox" name="remove" value="${isbn}">
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4">
                            <input type="submit" name="Submit" value="Update Cart">
                        </td>
                        <td colspan="2">
                            <div align="right"><b>$<fmt:formatNumber value="${totalCostOfOrder}" pattern="0.00" /></b></div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <div class="link-container">
            <p><a href="./books?action=continue">Continue Shopping</a></p>
            <p><a href="./books?action=checkout">Check Out</a></p>
        </div>
        <jsp:include page="footer.jsp" flush="true"/>
    </body>
</html>