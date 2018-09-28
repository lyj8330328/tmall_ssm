<%--
  Created by IntelliJ IDEA.
  User: 
  Date: 2018/9/20
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<script>
</script>

<title>用户管理</title>
<div class="workingArea">
    <h1 class="label-info label">用户管理</h1>

    <br>
    <br>

    <div class="listDataTableDiv">
        <table class="table table-striped table-bordered table-hover  table-condensed">
            <c:if test="${users.size() != 0}">
                <thead>
                    <tr class="success">
                        <th>ID</th>
                        <th>用户名称</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${users}" var="u">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </c:if>
            <c:if test="${users.size() == 0}">
                <thead>
                    <tr class="success">
                        <th colspan="2" class="text-center">无用户数据</th>
                    </tr>
                </thead>
            </c:if>
        </table>
    </div>
    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>
</div>
<%@include file="../include/admin/adminFooter.jsp"%>