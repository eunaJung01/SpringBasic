<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/v1/headerV1.jspf" %>
<body>
<%@ include file="/include/v1/navigationV1.jspf" %>

<div class="container" id="main">
    <table class="table table-striped">
        <thead class="col-md-12">
        <tr>
            <th class="col-md-3">아이디</th>
            <th class="col-md-3">이름</th>
            <th class="col-md-3">이메일</th>
            <th class="col-md-3">#</th>

        </tr>
        </thead>
        <tbody>

        <c:forEach items="${users}" var="user">
            <tr>
                <th class="col-md-3">${user.userId}
                </th>
                <th class="col-md-3">${user.name}
                </th>
                <th class="col-md-3">${user.email}
                </th>
                <th class="col-md-3"><a href="/v1/user/updateForm?userId=${user.userId}" class="btn btn-success"
                                        role="button">수정</a></th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="../../js/v1/scriptsV1.js"></script>
</body>
</html>
