<%@include file="/WEB-INF/fragments/headTag.jsp"%>

<section>
    <h3><fmt:message key="users.title"/></h3>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th><fmt:message key="users.name"/></th>
            <th><fmt:message key="users.email"/></th>
            <th><fmt:message key="users.roles"/></th>
            <th><fmt:message key="users.active"/></th>
       </tr>
        </thead>
        <c:forEach items="${userList}" var="user">
            <jsp:useBean id="user" scope="page" type="org.andsav.family_budget_manager.model.User"/>
            <tr>
                <td>${user.name}</td>
                <td><a href="mailto:${user.email}">${user.email}</a></td>
                <td>${user.roles}</td>
                <td>${user.enabled}</td>
            </tr>
        </c:forEach>
    </table>
</section>





<%@include file="/WEB-INF/fragments/footer.jsp"%>



