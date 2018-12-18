<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ABC</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- the following to include all needed things 
	font
	font awesome icon
	bootstraps custom-mize if you have using button , grid-system
	custom css
-->
<link
	href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese"
	rel="stylesheet">
<link rel="stylesheet"
	href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<div class="account-manage">
		<%@ include file="/jsps/components/_account-manage.header.jsp"%>
		<div class="account-manage__content">
			<div class="row u-height--100">
				<%@ include file="/jsps/components/_account-manage.admin-nav.jsp"%>
				<div class="col-md-10">
					<div>
						<p class="u-3x u-align-center">Cấp quyền ghim</p>
					</div>
					<div class="search">
						<%@ include file="/jsps/components/_search-bar.account.jsp"%>

						<div class="u-width--80 u-centered u-2x">
							<table class="table table-hover table-light">
								<!-- c:for de do du lieu ra -->
								<c:if test="${not empty searchResultAccount}">
									<tr class="row">
										<td class="col-md-3">Username</td>
										<td class="col-md-3">Nickname</td>
										<td class="col-md-3">Email</td>
										<td class="col-md-3">Action</td>
									</tr>
									<tr class="row">
										<td class="col-md-3">${searchResultAccount.userName}</td>
										<td class="col-md-3">${searchResultAccount.displayedName}</td>
										<td class="col-md-3">${searchResultAccount.gmail}</td>
										<td><c:if test="${searchResultAccount.pin}">
												<form action="grant-pin" method="post">
													<input type="hidden" name="accountID"
														value="${searchResultAccount.id}">
													<button class="btn btn-danger" name="action" type="submit"
														value="unpin">Bỏ quyền ghim</button>
												</form>
											</c:if> <c:if test="${!searchResultAccount.pin}">
												<form action="grant-pin" method="post">
													<input type="hidden" name="accountID"
														value="${searchResultAccount.id}">
													<button class="btn btn-primary" name="action" type="submit"
														value="pin">Cấp quyền ghim</button>
												</form>
											</c:if></td>
									</tr>
								</c:if>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>