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
					<a href="teachermanager.html"><spring:message code="app.manager.teacher" /></a>
				</li>
			</ul>
		</div>
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
	<div class="panel panel-default">
 		<div class="panel-heading"><spring:message code="app.manager.teacher" /></div>
 			<div class="panel-body">	
 			<div class="alert alert-error"> ${Msg} ${errorMsg} </div>			
				<div class="table-responsive">
		            <table class="table table-striped">
		              <thead>
		                <tr>
		                  <th>#</th>
		                  <th><spring:message code="app.form.name" /></th>
		                  <th><spring:message code="app.form.email" /></th>
		                  <th><spring:message code="app.panel.created" /></th>
		                  <th><spring:message code="app.panel.action" /></th>
		                </tr>
		              </thead>
		              <tbody>
						  	<c:forEach items="${teacherList}" var="teacher">
								<c:if test="${teacher.user_role.name=='ROLE_TEACHER'}">
									<c:set var="counter" value="${counter+1}"/>
									<tr>
										<td>${counter}</td>
										<td><a href="edituser.html?id=${teacher.id}">${teacher.name}</a></td>
										<td><a href="edituser.html?id=${teacher.id}">${teacher.email}</a></td>
										<td>date</td>
										<td>
											<a href="edituser.html?id=${teacher.id}" class="btn btn-sm btn-primary"><spring:message code="app.btn.edit" /></a>
											<a href="deleteuser.html&id=${teacher.id}" class="btn btn-sm btn-danger"><spring:message code="app.btn.delete" /></a>
											<a href="assignsubjectstoteacher.html?id=${teacher.id}" class="btn btn-sm btn-success">ASSIGN SUBJECTS</a>
											<c:if test="${!teacher.active}" >
												<a href="activate.html?id=${teacher.id}&role=${teacher.user_role.id}&page=teacher" class="btn btn-sm btn-success"><spring:message code="app.user.activate" /></a>
											</c:if>
											<c:if test="${teacher.active}" >
												<a href="deactivate.html?id=${teacher.id}&role=${teacher.user_role.id}&page=teacher" class="btn btn-sm btn-warning"><spring:message code="app.user.deactivate" /></a>
											</c:if>
										</td>
									</tr>
								</c:if>
							</c:forEach>
		              </tbody>
		            </table>
	          	</div>
          		</div>
          	</div>
          </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
