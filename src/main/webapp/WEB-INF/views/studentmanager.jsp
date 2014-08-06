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
					<a href="studentmanager.html"><spring:message code="app.manager.student" /></a>
				</li>
			</ul>
		</div>
	<div class="col-sm-12">
	<div class="panel panel-default">
 		<div class="panel-heading"><spring:message code="app.manager.student" /></div>
 			<div class="panel-body">				
				<div class="table-responsive">
		            <table class="table table-striped">
		              <thead>
		                <tr>
		                  <th>#</th>
		                  <th><spring:message code="app.form.name" /></th>
		                  <th><spring:message code="app.form.email" /></th>
		                  <th><spring:message code="app.panel.course" /></th>
		                  <th><spring:message code="app.panel.created" /></th>
		                  <th><spring:message code="app.panel.action" /></th>
		                </tr>
		              </thead>
		              <tbody>
						  	<c:forEach items="${studentList}" var="student">
								<c:if test="${student.user_role.name=='ROLE_STUDENT'}">
									<c:set var="counter" value="${counter+1}"/>
									<c:set var="flag" value="false"/>
									<tr>
										<td>${counter}</td>
										<td><a href="edituser.html?id=${student.id}">${student.name}</a></td>
										<td><a href="edituser.html?id=${student.email}">${student.email}</a></td>
										<c:forEach items="${studentCourseList}" var="student_course">
											<c:if test="${student.id == student_course.user.id}">
												<c:forEach items="${courseList}" var="course">
													<c:if test="${student_course.course.id == course.id}">
														<td>${course.name}</td>
														<c:set var="flag" value="true"/>
													</c:if>
												</c:forEach>
											</c:if>
										</c:forEach>
										<c:if test="${flag=='false'}" >
											<td>date</td>
										</c:if>
										<td>date</td>
										<td>
											<a href="edituser.html?id=${student.id}" class="btn btn-sm btn-primary"><spring:message code="app.btn.edit" /></a>
											<a href="deleteuser.html&id=${student.id}" class="btn btn-sm btn-danger"><spring:message code="app.btn.delete" /></a>
											<a href="changecourse.html?id=${student.id}" class="btn btn-sm btn-success">ASSIGN COURSE</a>
											<c:if test="${!student.active}" >
												<a href="activate.html?id=${student.id}&role=${student.user_role.id}&page=student" class="btn btn-sm btn-success"><spring:message code="app.user.activate" /></a>
											</c:if>
											<c:if test="${student.active}" >
												<a href="deactivate.html?id=${student.id}&role=${student.user_role.id}&page=student" class="btn btn-sm btn-warning"><spring:message code="app.user.deactivate" /></a>
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
