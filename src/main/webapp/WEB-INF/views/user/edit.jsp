<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="/WEB-INF/fragment/taglib-includes.jspf"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<jsp:include page="../common/common.jsp" />

<script type="text/javascript">
	function deleteConfirmation() {


		if (window.confirm('削除しますか？')) {

			document.form.action = "${pageContext.request.contextPath}/user/delete"

			document.form.submit();

		}
	}
	</script>
</head>

<body>

	<jsp:include page="../common/header.jsp" />
	<form:form modelAttribute="form" method="post" name="form"
		action="${pageContext.request.contextPath}/user/update"
		class="form-horizontal">


		<div class="container">


			<%-- 画面タイトル --%>
			<div class="row">
				<div class="col-md-offset-1 col-md-10">
					<h3>
						<c:out value="${displayTitle}" />
					</h3>
				</div>
			</div>

			<%-- メッセージ領域 --%>
			<div class="row">
				<div class="col-md-offset-1 col-md-10">
					<%@include file="/WEB-INF/fragment/messages.jspf"%>
				</div>
			</div>
			<div class="row">
				<div class="col-md-offset-1 col-md-10">
					<div class="panel panel-default main-border-color">
						<div class="panel-body sub-background-color">
							<div
								class="form-group <ext:isErrors path='userName' value='has-error'/>">
								<label class="col-md-4 control-label">ユーザー名<span
									class="label label-danger" style="margin-left: 10px">必須</span></label>
								<div class="col-md-6">
									<form:input path="userName" value="${userTDto.userName}"
										class="form-control" />
									<form:hidden path="userId" />
								</div>
								<div style="clear: both;">
									<span class="col-md-4"></span>
									<div class="col-md-6">
										<form:errors path="userName" element="div"
											cssClass="text-danger" />
									</div>
								</div>
							</div>
							<div
								class="form-group <ext:isErrors path='lastName' value='has-error'/>">
								<label class="col-md-4 control-label">苗字(氏)<span
									class="label label-danger" style="margin-left: 10px">必須</span></label>
								<div class="col-md-6">
									<form:input path="lastName" value="${userTDto.lastName}"
										class="form-control" />
								</div>
								<div style="clear: both;">
									<span class="col-md-4"></span>
									<div class="col-md-6">
										<form:errors path="lastName" element="div"
											cssClass="text-danger" />
									</div>
								</div>
							</div>
							<div
								class="form-group <ext:isErrors path='firstName' value='has-error'/>">
								<label class="col-md-4 control-label">名前(名)<span
									class="label label-danger" style="margin-left: 10px">必須</span></label>
								<div class="col-md-6">
									<form:input path="firstName" value="${userTDto.firstName}"
										class="form-control" />
								</div>
								<div style="clear: both;">
									<span class="col-md-4"></span>
									<div class="col-md-6">
										<form:errors path="firstName" element="div"
											cssClass="text-danger" />
									</div>
								</div>
							</div>
							<div
								class="form-group <ext:isErrors path='email' value='has-error'/>">
								<label class="col-md-4 control-label">メールアドレス<span
									class="label label-danger" style="margin-left: 10px">必須</span></label>
								<div class="col-md-6">
									<form:input path="email" value="${userTDto.email}"
										class="form-control" />
								</div>
								<div style="clear: both;">
									<span class="col-md-4"></span>
									<div class="col-md-6">
										<form:errors path="email" element="div" cssClass="text-danger" />
									</div>
								</div>
							</div>
							<div
								class="form-group <ext:isErrors path='password' value='has-error'/>">
								<label class="col-md-4 control-label">パスワード<span
									class="label label-danger" style="margin-left: 10px">必須</span></label>
								<div class="col-md-6">
									<form:password path="password" class="form-control" />
								</div>
								<div style="clear: both;">
									<span class="col-md-4"></span>
									<div class="col-md-6">
										<form:errors path="password" element="div"
											cssClass="text-danger" />
									</div>
								</div>
							</div>
							<div
								class="form-group <ext:isErrors path='confirmPassword' value='has-error'/>">
								<label class="col-md-4 control-label">パスワード確認<span
									class="label label-danger" style="margin-left: 10px">必須</span></label>
								<div class="col-md-6">
									<form:password path="confirmPassword" class="form-control" />
								</div>
								<div style="clear: both;">
									<span class="col-md-4"></span>
									<div class="col-md-6">
										<form:errors path="confirmPassword" element="div"
											cssClass="text-danger" />
									</div>
								</div>
							</div>
							<div
								class="form-group <ext:isErrors path='userTypeId' value='has-error'/>">
								<label class="col-md-4 control-label">ユーザー区分<span
									class="label label-danger" style="margin-left: 10px">必須</span></label>
								<div class="col-md-6">
									<form:select path="userTypeId" class="form-control"
										items="${userTypeSelectList}" itemLabel="label"
										itemValue="value" />
								</div>
								<div style="clear: both;">
									<span class="col-md-4"></span>
									<div class="col-md-6">
										<form:errors path="userTypeId" element="div"
											cssClass="text-danger" />
									</div>
								</div>
							</div>
							<div
								class="form-group <ext:isErrors path='authLevel' value='has-error' />">
								<label class="col-md-4 control-label">権限レベル<span
									class="label label-danger" style="margin-left: 10px">必須</span></label>
								<div class="col-md-6">
									<form:select path="authLevel" class="form-control"
										items="${authList}" itemLabel="label" itemValue="value" />
								</div>
								<div style="clear: both;">
									<span class="col-md-4"></span>
									<div class="col-md-6">
										<form:errors path="authLevel" element="div"
											cssClass="text-danger" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-offset-1 col-md-10" align="center">
					<table style="margin-bottom: 20px">
						<tr>
							<td style="vertical-align: middle;">
								<button type="submit" class="btn btn-info">更新</button>
							</td>
							<td><label class="space" style="width: 30px;"></label></td>

							<td style="vertical-align: middle;">
								<button type="button" class="btn btn-danger"
									onClick="deleteConfirmation()">削除</button>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form:form>

	<jsp:include page="../common/footer.jsp" />
</body>
</html>