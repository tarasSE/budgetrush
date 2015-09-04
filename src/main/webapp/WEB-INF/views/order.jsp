<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:directive.page contentType="text/html;charset=UTF-8" />

<t:template>
<c:if test="${!empty orders}">
    <table class="order-list">
        <tr>

            <th>Id</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Contractor</th>
            <th>Account</th>
            <th>Category</th>

            <th>&nbsp;</th>


        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.amount}</td>
                <td>${order.date}</td>
                <td>${order.contractor.id}</td>
                <td>${order.account.id}</td>
                <td>${order.category.id}</td>

            </tr>
        </c:forEach>
    </table>
</c:if>
</t:template>
