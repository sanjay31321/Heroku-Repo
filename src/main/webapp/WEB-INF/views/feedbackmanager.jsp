<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
		<c:set var="role" value="${role}"/>
		<c:if test="${role!='authenticated'}">
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
					<a href="feedbackmanager.html"><spring:message code="app.panel.title.feedbackmanager" /></a>
				</li>
			</ul>
		</div>
		<div class="col-sm-12">
		<div class="panel panel-default">
 		<div class="panel-heading"><spring:message code="app.panel.title.feedbackmanager" /></div>
 			<div class="panel-body">
				<div class="row">
					<div class="col-sm-8"></div>
    			</div>
    			<a href="createfeedback.html" class="btn btn-sm btn-primary">Create New Feedback</a>
    			<div class="alert alert-error"> ${Msg} ${errorMsg} </div>
    			<hr/>
				<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th><spring:message code="app.panel.feedback" /></th>
									<th><spring:message code="app.panel.createdby" /></th>
									<th><spring:message code="app.panel.subject" /></th>
									<th><spring:message code="app.panel.created" /></th>
									<th><spring:message code="app.panel.from" /></th>
									<th><spring:message code="app.panel.to" /></th>
									<th><spring:message code="app.panel.action" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${feedbackList}" var="feedback">
									<c:if test="${loggedInUserROLE=='ROLE_TEACHER' }">
										<c:forEach items="${teacherSubjectList}" var="teacher_subject">
										 	<c:if test="${teacher_subject.user.id == loggedInUserID && teacher_subject.subject.id == feedback.subject.id }">
												<c:set var="counter" value="${counter + 1}"/>
												<tr>
													<td>${counter}</td>
													<td>${feedback.name}</td>
													<td>${feedback.user.name}</td>
													<td>${feedback.subject.name}</td>
													<td><fmt:formatDate value="${feedback.created}" pattern="dd.MM.yyyy" /></td>
													<td><fmt:formatDate value="${feedback.date_from}" pattern="dd.MM.yyyy" /></td>
													<td><fmt:formatDate value="${feedback.date_to}" pattern="dd.MM.yyyy" /></td>
													<td>
														<a href="editfeedback.html?id=${feedback.id}" class="btn btn-sm btn-primary">EDIT</a>
														<a href="deletefeedback.html?id=${feedback.id}" class="btn btn-sm btn-danger">DELETE</a>
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${loggedInUserROLE == 'ROLE_ADMIN' }" >
										<c:set var="counter" value="${counter + 1}"/>
										<tr>
											<td>${counter}</td>
											<td>${feedback.name}</td>
											<td>${feedback.user.name}</td>
											<td>${feedback.subject.name}</td>
											<td><fmt:formatDate value="${feedback.created}" pattern="dd.MM.yyyy" /></td>
											<td><fmt:formatDate value="${feedback.date_from}" pattern="dd.MM.yyyy" /></td>
											<td><fmt:formatDate value="${feedback.date_to}" pattern="dd.MM.yyyy" /></td>
											<td>
												<a href="editfeedback.html?id=${feedback.id}" class="btn btn-sm btn-primary">EDIT</a>
												<a href="deletefeedback.html?id=${feedback.id}" class="btn btn-sm btn-danger">DELETE</a>
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
