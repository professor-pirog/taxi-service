<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h2>Login</h2>
<div>
    <form action="login" method="post">
        <div>
            <label>
                login:
                <input type="text" name="login">
            </label>
        </div>
        <div>
            <label>
                password:
                <input type="password" name="password">
            </label>
        </div>
        <button>Login</button><a href="${pageContext.request.contextPath}/registration">registration</a>
        <%--@elvariable id="errors" type="java.util.List<String>"--%>
        <c:if test="${errors != null && not empty errors}">
            <div style="color: red">
                <c:forEach items="${errors}" var="error" >
                    <div><c:out value="${error}"/></div>
                </c:forEach>
            </div>
        </c:if>
    </form>
</div>
</body>
</html>
