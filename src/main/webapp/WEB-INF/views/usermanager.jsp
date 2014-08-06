<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="true"%>

<tiles:insertDefinition name="adminTemplate">
	<tiles:putAttribute name="body">
		<c:set var="role" value="${loggedInUserROLE}"/>
		<c:if test="${role!='ROLE_ADMIN'}">
			<script type="text/javascript">	window.location.href="login.html"; </script>
		</c:if>
		<div class="margin-left-right">
			<ul class="breadcrumb">
				<li>
					<i class="glyphicon glyphicon-home"></i> 
					<a href="${loggedInUserHOMELINK}"><spring:message code="${loggedInUserHOME}" /></a> 
					<span class="divider">»</span>
				</li>
				<li>
					<a href="usermanager.html"><spring:message code="app.panel.title.usermanager" /></a>
				</li>
			</ul>
		</div>
	
	<div class="col-sm-12">
	<div class="panel panel-default">
 		<div class="panel-heading"><spring:message code="app.panel.title.usermanager" /></div>
 			<div class="panel-body">
 				<div class="row">
 					<div class="col-sm-2">
 						<a href="adduser.html" class="btn btn-sm btn-primary form-control">
 							<spring:message code="app.user.add"/>
 						</a>
 					</div>	
 					<div class="col-sm-6"><div class="alert alert-error"> ${Msg} ${errorMsg} </div></div>
 				</div>
				<div class="table-responsive">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th>#</th>
									<th><spring:message code="app.form.email" /></th>
									<th><spring:message code="app.form.name" /></th>
									<th><spring:message code="app.form.role" /></th>
									<th><spring:message code="app.panel.action" /></th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList}" var="user">
									<c:set var="counter" value="${counter+1 }"/>
									<tr>
										<td>${counter}</td>
										<td><a href="edituser.html?id=${user.id}">${user.email}</a></td>
										<td><a href="edituser.html?id=${user.id}">${user.name}</a></td>
										<td>${user.user_role.name}</td>
										<td>
											<a href="edituser.html?id=${user.id}" class="btn btn-sm btn-primary"><spring:message code="app.btn.edit" /></a>
											<a href="setrole.html?id=${user.id}" class="btn btn-sm btn-primary"><spring:message code="app.user.role.set" /></a>
											<a href="deleteuser.html?id=${user.id}" class="btn btn-sm btn-danger"><spring:message code="app.btn.delete" /></a>
											<c:if test="${!user.active}" >
												<a href="activate.html?id=${user.id}&role=${user.user_role.id}&page=user" class="btn btn-sm btn-success"><spring:message code="app.user.activate" /></a>
											</c:if>
											<c:if test="${user.active}" >
												<a href="deactivate.html?id=${user.id}&role=${user.user_role.id}&page=user" class="btn btn-sm btn-warning"><spring:message code="app.user.deactivate" /></a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
          		</div>
          	</div>
          </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
