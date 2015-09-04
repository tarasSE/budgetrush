<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<jsp:directive.page contentType="text/html;charset=UTF-8" />

<t:template>

<c:if test="${!empty categories}">
  <table class="category-list">
    <tr>

      <th>Id</th>
      <th>Name</th>
      <th>Parent</th>
      <th>&nbsp;</th>


    </tr>
    <c:forEach items="${categories}" var="category">
      <tr>
        <td>${category.id}</td>
        <td>${category.name}</td>
        <td>${category.parent.id}</td>

      </tr>
    </c:forEach>
  </table>

</c:if>
</t:template>
