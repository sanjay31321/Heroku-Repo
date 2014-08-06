<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<tiles:insertDefinition name="studentTemplate">
	<tiles:putAttribute name="body">
		<c:set var="role" value="${loggedInUserROLE}"/>
		<c:if test="${role!='ROLE_STUDENT'}">
			<script type="text/javascript">	window.location.href="login.html"; </script>
		</c:if>
				
		<div class="margin-left-right">
			<ul class="breadcrumb">
				<li>
					<i class="glyphicon glyphicon-home"></i> 
					<a href="${loggedInUserHOMELINK}"><spring:message	code="${loggedInUserHOME}" /></a>
					<span class="divider">»</span> 
				</li>
				<li>
					<a href="studentprofile.html"><spring:message code="app.breadcrumb.student.profile" /></a>
				</li>
			</ul>
		</div>
		
		
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="app.panel.title.profile" /> - ${loggedInUserNAME}
					<span class="pull-right">
					<a href="editstudentprofile.html?email=${loggedInUserEMAIL}"><b><spring:message code="app.btn.edit" /></b></a>
					</span>
				</div>
				<div class="panel-body">
				<div class="alert alert-error"> ${Msg} ${errorMsg} </div>
					<div class="row">
						<div class="col-sm-8">
							<div class="row">
								<div class="col-sm-4">
									<label for="role"><spring:message code="app.form.role" /></label>
								</div>
								<div class="col-sm-4">
									<label for="role"><strong>${loggedInUserROLE}</strong></label>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-sm-4">
									<label for="email"><spring:message code="app.form.email" /></label>
								</div>
								<div class="col-sm-7">
									<label for="email"> ${loggedInUserEMAIL}</label>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-sm-4">
									<label for="name"><spring:message code="app.form.name" /></label>
								</div>
								<div class="col-sm-7">
									<label for="name">${loggedInUserNAME}</label>
								</div>
							</div>
							<br />
							<div class="row">
								<div class="col-sm-4">
									<label for="name"><spring:message code="app.panel.course" /></label>
								</div>
								<div class="col-sm-7">
									<label for="name">${loggedInUserCOURSE}</label>
								</div>
							</div>
						</div>
						<div class="col-sm-4">

							<img data-src="holder.js/150x150/auto/vine"
								class="img-responsive" height="180px" width="180px"
								alt="200x200"
								src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIyMDAiIGhlaWdodD0iMjAwIj48cmVjdCB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgZmlsbD0iIzM5REJBQyIvPjx0ZXh0IHRleHQtYW5jaG9yPSJtaWRkbGUiIHg9IjEwMCIgeT0iMTAwIiBzdHlsZT0iZmlsbDojMUUyOTJDO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1zaXplOjEzcHg7Zm9udC1mYW1pbHk6QXJpYWwsSGVsdmV0aWNhLHNhbnMtc2VyaWY7ZG9taW5hbnQtYmFzZWxpbmU6Y2VudHJhbCI+MjAweDIwMDwvdGV4dD48L3N2Zz4=">
							<br />
						</div>

					</div>

				</div>
			</div>

		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
