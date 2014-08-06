<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					<span class="divider">»</span>
				</li>
				<li>
					<a href="#"><spring:message code="app.course.change" /></a>
				</li>
			</ul>
		</div>
		<div class="col-sm-12">

			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="app.panel.title.profile" />
				</div>
				<div class="panel-body">
					<div class="alert alert-error"> ${Msg} ${errorMsg} </div>
					<form:form commandName="course">
						<c:set var="flag_role" value="false"/>
						<c:set var="flag_course" value="false"/>
						<div class="row">
							<div class="col-sm-9">
								<div class="row">
									<div class="col-sm-4">
										<label for="role"><spring:message code="app.form.role" /></label>
									</div>
									<div class="col-sm-7">
										<div class="row">
											<div class="col-sm-4"><label>${profile.user_role.name}</label></div>
										</div>
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-4">
										<label for="email"><spring:message	code="app.form.email" /></label>
									</div>
									<div class="col-sm-7">
										<input type="text" class="form-control" name="email" disabled value="${profile.email}"  />
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-4">
										<label for="first_name"><spring:message
												code="app.form.name" /></label>
									</div>
									<div class="col-sm-7">
										<input type="text" class="form-control" name="name" disabled value="${profile.name}" />
									</div>
								</div>
								<c:if test="${profile.user_role.name == 'ROLE_STUDENT' }">
									<br>
									<c:forEach items="${studentCourseList}" var="student_course">
										<c:if test="${profile.id == student_course.user.id}">
											<c:forEach items="${courseList}" var="course">
												<c:if test="${student_course.course.id == course.id}">
													<div class="row">
														<div class="col-sm-4">
															<label for="course"><spring:message code="app.course" /></label>
														</div>
														<div class="col-sm-7">
															<label for="course">${course.name}</label>
														</div>
													</div>
												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach>
									<br>
									<div class="row">
										<div class="col-sm-4"></div>
										<div class="col-sm-7">
											<select name="course" id="course" class="form-control" required>
												<option value="0">- Select -</option>
												<c:forEach items="${courseList}" var="course">
													<option value="${course.id}">${course.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</c:if>
								<br><br>
								<input type="hidden" value="${profile.user_role.id}" name="role" id="role"/>
								<div class="row">
									<div class="col-sm-4"></div>
									<div class="col-sm-2">
										<button type="submit" class="btn btn-sm btn-success form-control">
											<spring:message code="app.form.save" />
										</button>
									</div>
									<div class="col-sm-2">
										<a href="studentmanager.html" class="btn btn-sm btn-default form-control">
											<spring:message code="app.form.cancel" />
										</a>
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<img data-src="holder.js/150x150/auto/vine"
									class="img-responsive" height="180px" width="180px"
									alt="200x200"
									src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iIzM5REJBQyIvPjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjEwMCIgeT0iMTAwIiBzdHlsZT0iZmlsbDojMUUyOTJDO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zaXplOjEzcHg7Zm9udC1mYW1pbHk6QXJpYWwsSGVsdmV0aWNhLHNhbnMtc2VyaWY7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+MjAweDIwMDwvdGV4dD48L3N2Zz4=">
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
