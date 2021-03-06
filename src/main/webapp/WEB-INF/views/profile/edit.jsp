<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/fragment/taglib-includes.jspf"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<jsp:include page="../common/common.jsp" />
</head>

<body>

  <jsp:include page="../common/header.jsp" />
  <form:form modelAttribute="form" action="${pageContext.request.contextPath}/profile/update" method="post" class="form-horizontal">

    <div class="container">

      <div class="row">
        <div class="col-md-offset-1 col-md-10">
          <h3>
            <c:out value="${displayTitle}" />
          </h3>
        </div>
      </div>

      <div class="row">
        <div class="col-md-offset-1 col-md-10">
          <%@include file="/WEB-INF/fragment/messages.jspf"%>
        </div>
      </div>

      <!-- テーブル -->
      <div class="row">
        <div class="col-md-offset-1 col-md-10">
          <div class="panel panel-default main-border-color">
            <div class="panel-body sub-background-color">
              <div class="form-group">
                <label class="col-md-4 control-label">LINE 設定</label>
                <c:choose>
                  <c:when test="${form.lineFlg == 1}">
                    <div class="col-md-6">
                      <label class="control-label"><span class="label label-success" >設定済</span></label>
                    </div>
                  </c:when>
                  <c:otherwise>
                    <div class="col-md-6 text-left">
                      <label class="control-label"><span class="label label-danger" >未設定</span></label>
                    </div>
                  </c:otherwise>
                </c:choose>
              </div>
              <div class="form-group">
                <label class="col-md-4 control-label">LINE ユーザ名</label>
                <div class="col-md-6">
                  <form:input path="lineUserName" class="form-control" disabled="true"/>
                </div>
              </div>

              <div class="form-group">
                <label class="col-md-4 control-label">氏名
                  <span class="label label-danger" style="margin-left: 10px">必須</span>
                </label>
                <div class="col-md-3 <ext:isErrors path='lastName' value='has-error'/>">
                  <form:input path="lastName" class="form-control" maxlength="${ext:getMaxLen('CNT0001') }" placeholder="氏"/>
                </div>
                <div class="col-md-3 <ext:isErrors path='firstName' value='has-error'/>">
                  <form:input path="firstName" class="form-control" maxlength="${ext:getMaxLen('CNT0001') }" placeholder="名"/>
                </div>
                <div style="clear: both;">
                  <span class="col-md-4"></span>
                  <div class="col-md-3">
                    <form:errors path="lastName" element="div" cssClass="text-danger" />
                  </div>
                  <div class="col-md-3">
                    <form:errors path="firstName" element="div" cssClass="text-danger" />
                  </div>
               </div>
             </div>

              <%--
              <div class="form-group <ext:isErrors path='userName' value='has-error'/>">
                <label class="col-md-4 control-label">ニックネーム</label>
                <div class="col-md-6">
                  <form:input path="userName" class="form-control" maxlength="${ext:getMaxLen('CNT0001') }" />
                </div>
                <div style="clear: both;">
                  <span class="col-md-4"></span>
                  <div class="col-md-6">
                    <form:errors path="userName" element="div" cssClass="text-danger" />
                  </div>
                </div>
              </div>
              <div class="form-group <ext:isErrors path='email' value='has-error'/>">
                <label class="col-md-4 control-label">Eメール<span class="label label-danger" style="margin-left: 10px">必須</span></label>
                <div class="col-md-6">
                  <form:input path="email" class="form-control" maxlength="${ext:getMaxLen('CNT0001') }" />
                </div>
                <div style="clear: both;">
                  <span class="col-md-4"></span>
                  <div class="col-md-6">
                    <form:errors path="email" element="div" cssClass="text-danger" />
                  </div>
                </div>
              </div>

               <div class="form-group <ext:isErrors path='loginId' value='has-error'/>">
                <label class="col-md-4 control-label">ログインID</label>
                <div class="col-md-6">
                  <form:input path="loginId" class="form-control" maxlength="${ext:getMaxLen('CNT0001') }" />
                </div>
                <div style="clear: both;">
                  <span class="col-md-4"></span>
                  <div class="col-md-6">
                    <form:errors path="loginId" element="div" cssClass="text-danger" />
                  </div>
                </div>
              </div>
              <div class="form-group <ext:isErrors path='password' value='has-error'/>">
                <label class="col-md-4 control-label">パスワード<span class="label label-danger" style="margin-left: 10px"></span></label>
                <div class="col-md-6">
                  <form:password path="password" class="form-control" maxlength="${ext:getMaxLen('CNT0001') }" />
                </div>
                <div style="clear: both;">
                  <span class="col-md-4"></span>
                  <div class="col-md-6">
                    <form:errors path="password" element="div" cssClass="text-danger" />
                  </div>
                </div>
              </div>
              <div class="form-group <ext:isErrors path='newPassword' value='has-error'/>">
                <label class="col-md-4 control-label">新しいパスワード<span class="label label-danger" style="margin-left: 10px"></span></label>
                <div class="col-md-6">
                  <form:password path="newPassword" class="form-control" maxlength="${ext:getMaxLen('CNT0001') }" />
                </div>
                <div style="clear: both;">
                  <span class="col-md-4"></span>
                  <div class="col-md-6">
                    <form:errors path="newPassword" element="div" cssClass="text-danger" />
                  </div>
                </div>
              </div>
              <div class="form-group <ext:isErrors path='confirmPassword' value='has-error'/>">
                <label class="col-md-4 control-label">確認用パスワード<span class="label label-danger" style="margin-left: 10px"></span></label>
                <div class="col-md-6">
                  <form:password path="confirmPassword" class="form-control" maxlength="${ext:getMaxLen('CNT0001') }" />
                </div>
                <div style="clear: both;">
                  <span class="col-md-4"></span>
                  <div class="col-md-6">
                    <form:errors path="confirmPassword" element="div" cssClass="text-danger" />
                  </div>
                </div>
              </div>
              --%>
            </div>
          </div>
        </div>
      </div>

      <!-- ボタン -->
      <div class="row">
        <div class="col-md-offset-1col-md-10" align="center">
          <table style="margin-bottom: 20px">
            <tr>
              <td style="vertical-align: middle;">
                <button type="submit" class="btn btn-warning" id="modify">更新</button>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div></div>
  </form:form>

  <jsp:include page="../common/footer.jsp" />
</body>
</html>
