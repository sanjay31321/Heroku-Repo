<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="true"%>

<c:set var="template" value="defaultTemplate"/>
<c:if test="${loggedInUserROLE=='ROLE_ADMIN' }">
	<c:set var="template" value="adminTemplate"/>
</c:if>
<c:if test="${loggedInUserROLE=='ROLE_TEACHER' }">
	<c:set var="template" value="teacherTemplate"/>
</c:if>
<tiles:insertDefinition name="${template}">
	<tiles:putAttribute name="body">
	<c:set var="role" value="${role}" />
		<c:if test="${role!='authenticated'}">
			<script type="text/javascript">
				window.location.href = "login.html";
			</script>
		</c:if>
		<div class="margin-left-right">
			<ul class="breadcrumb">
				<li>
					<i class="glyphicon glyphicon-home"></i> 
					<a href="${loggedInUserHOMELINK}"><spring:message code="${loggedInUserHOME}" /></a> 
					<span class="divider">�</span>
				</li>
				<li>
					<a href="profile.html"><spring:message code="app.panel.title.profile" /></a>
					<span class="divider">�</span>
				</li>
				<li>
					<a href="#"><spring:message code="app.profile.edit" /></a>
				</li>
			</ul>
		</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-10">

			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="app.panel.title.profile" />
					- ${loggedInUserNAME}
				</div>
				<div class="panel-body">
					<div>${errorMsg} ${Msg}</div>
					<form:form commandName="user">
						<div class="row">
							<div class="col-sm-8">
								<div class="row">
									<div class="col-sm-4">
										<label for="role"><spring:message code="app.form.role" /></label>
									</div>
									<div class="col-sm-4">
										<label for="role"><strong>${profile.user_role.name}</strong></label>
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-4">
										<label for="email"><spring:message
												code="app.form.email" /></label>
									</div>
									<div class="col-sm-7">
										<form:input cssClass="form-control" path="email" value="${profile.email}" disabled="true" />
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-4"> 
										<label for="first_name"><spring:message code="app.form.name" /></label>
									</div>
									<div class="col-sm-7">
										<form:input cssClass="form-control" path="name" value="${profile.name}" required="true"/>
										<form:errors path="name" cssClass="danger" />
									</div>
								</div>

							</div>
							<div class="col-sm-4">

								<img data-src="holder.js/150x150/auto/vine"
									class="img-responsive" height="180px" width="180px"
									alt="200x200"
									src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iIzM5REJBQyIvPjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjEwMCIgeT0iMTAwIiBzdHlsZT0iZmlsbDojMUUyOTJDO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zaXplOjEzcHg7Zm9udC1mYW1pbHk6QXJpYWwsSGVsdmV0aWNhLHNhbnMtc2VyaWY7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+MjAweDIwMDwvdGV4dD48L3N2Zz4=">
								<br />
								<p>
									<input type="file" name="pic" class="btn btn-sm btn-default"></input>
								</p>
							</div>

						</div>


						<hr />

						<div class="row">
							<div class="col-sm-8">
								<div class="row">
									<div class="col-sm-4">
										<label for="role"><spring:message
												code="app.form.securityquestion" /></label>
									</div>
									<div class="col-sm-7">
										<form:input cssClass="form-control" path="security_question" value="${profile.security_question}"  required="true"/>
										<form:errors path="security_question" cssClass="danger" />
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-sm-4">
										<label for="role"><spring:message
												code="app.form.securityanswer" /></label>
									</div>
									<div class="col-sm-7">
										<form:input cssClass="form-control" path="security_answer" value="${profile.security_answer}" required="true"/>
											<form:errors path="security_answer" cssClass="danger" />
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-4">
										<label for="role"><spring:message
												code="app.form.password" /></label>
									</div>
									<div class="col-sm-7">
										<form:password cssClass="form-control" path="password" value="${profile.password}" required="true" />
										<form:errors path="password" cssClass="danger" />
									</div>
								</div>

							</div>
							<div class="col-sm-2">
								<button type="submit"
									class="btn btn-sm btn-success form-control">
									<spring:message code="app.form.save" />
								</button>
								<br />
								<br />
								<button class="btn btn-sm btn-default form-control">
									<spring:message code="app.form.cancel" />
								</button>
							</div>
						</div>
						<br>

					</form:form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
