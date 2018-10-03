<%--
  Created by IntelliJ IDEA.
  User: 
  Date: 2018/10/2
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div class="aliPayPageDiv">
    <div class="aliPayPageLogo">
        <img class="pull-left" src="${pageContext.request.contextPath}/img/site/simpleLogo.png">
        <div style="clear:both"></div>
    </div>

    <div>
        <span class="confirmMoneyText">扫一扫付款（元）</span>
        <span class="confirmMoney">
        ￥<fmt:formatNumber type="number" value="${param.total}" minFractionDigits="2"/></span>

    </div>
    <div>
        <img class="aliPayImg" src="${pageContext.request.contextPath}/img/site/alipay2wei.png">
    </div>

    <div>
        <a href="forepayed?oid=${param.oid}&total=${param.total}"><button class="confirmPay">确认支付</button></a>
    </div>

</div>