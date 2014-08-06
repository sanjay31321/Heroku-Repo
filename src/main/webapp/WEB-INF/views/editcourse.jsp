<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<a href="coursemanager.html"><spring:message code="app.panel.title.coursemanager" /></a>
					<span class="divider">»</span>
				</li>
				<li>
					<a href="editcourse.html?id=${course.id}"><spring:message code="app.btn.editcourse" /></a>
				</li>
			</ul>
		</div>
		
	<div class="col-sm-1"></div>	
	<div class="col-sm-10">
	<div class="panel panel-default">
 		<div class="panel-heading">
 			<spring:message code="app.btn.editcourse" />
 			<span class="pull-right"><a href="coursemanager.html"><spring:message code="app.btn.back"/></a></span>
 		</div>
 			<div class="panel-body">
				<div class="row">
					<div class="col-sm-8">
						<form:form commandName="course">
							<div class="input-group">${errorMsg}</div>
							<div class="input-group">
								<form:input path="name" class="form-control" required="true"/> 
								<form:errors path="name" cssClass="danger" />
								<span class="input-group-btn">
									<button type="submit" class="btn btn-default" type="button">
										<spring:message code="app.btn.editcourse" />
									</button>
								</span>
							</div>
						</form:form>
					</div>
    			</div>
    			<hr/>
				<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th><spring:message code="app.panel.course" /></th>
								</tr>
							</thead>
							<tbody>
								<%-- <c:set value="${editcourse}" var="editcourse" /> --%>
								<tr>
									<td>${course.id}</td>
									<td>${course.name}</td>
								</tr>
							</tbody>
						</table>
					</div>
          		</div>
          	</div>
          </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
