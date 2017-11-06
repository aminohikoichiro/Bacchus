<%@ page contentType="text/html;charset=Windows-31J" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
  <!-- ナビゲーションバー -->
  <nav class="navbar navbar-default">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">TOP</a>
      </div>
      <!-- メニュー -->
      <c:if test="${userInfo.logined}">
        <ul class="nav navbar-nav">
          <li><a href="${pageContext.request.contextPath}/">xxxx</a></li>
          <c:if test="${userInfo.adminFlg}">
            <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button">アカウント管理<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="${pageContext.request.contextPath}/">一覧表示</a></li>
                <li><a href="${pageContext.request.contextPath}/">新規登録</a></li>
              </ul></li>
          </c:if>
        </ul>
        <!-- ログアウト -->
        <div class="navbar-right">
          <c:if test="${userInfo.logined}">
            <ul class="nav navbar-nav">
              <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"><span class="glyphicon glyphicon-user"> <c:out value="${userInfo.userName}" />
                </span><span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="${pageContext.request.contextPath}/profile/edit"><span class="glyphicon glyphicon-edit">&nbsp;</span>プロフィール編集</a></li>
                  <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out">&nbsp;</span>Log out</a></li>
                </ul></li>
            </ul>
          </c:if>
        </div>
      </c:if>
    </div>
  </nav>
</div>