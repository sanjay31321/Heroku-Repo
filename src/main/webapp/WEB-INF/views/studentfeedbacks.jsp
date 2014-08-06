<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<%@ page trimDirectiveWhitespaces="true" %>

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
					<a href="studentfeedbacks.html?email=${loggedInUserEMAIL}"><spring:message code="app.panel.title.availablefeedbacks" /></a>
				</li>
			</ul>
		</div>
		
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<spring:message code="app.panel.title.availablefeedbacks" />
					</h3>
				</div>
				<div class="panel-body">
					<div> ${errorMsg} ${Msg}</div>
					<div class="table-responsive">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th><spring:message code="app.panel.course" /></th>
									<th><spring:message code="app.panel.createdby" /></th>
									<th><spring:message code="app.panel.subject" /></th>
									<th><spring:message code="app.panel.date" /></th>
									<th><spring:message code="app.panel.action" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${feedbackList}" var="feedback">
								<c:set var="counter" value="${counter + 1}"/>
									<c:forEach items="${coursesubjectList}" var="subject">
									<c:if test="${feedback.subject.id==subject.subject.id}">
									<tr>
										<td>${counter}</td>
										<td>${feedback.name}</td>
										<td>${feedback.user.name}</td>
										<td>${feedback.subject.name}</td>
										<td>date</td>
										<td>
											<c:set var="flag" value="true" />
											<c:set var="flag1" value="true" />
											<c:set var="cssClass" value="btn btn-sm btn-success form-control" />
											<c:set var="submit" value="app.panel.submitfeedback"/>
											<c:set var="enabled" value=""/>
											<c:forEach items="${feedbackdoneList}" var="feedback_done">
												<c:if test="${feedback.id==feedback_done.feedback.id && flag==flag1}">
													<c:set var="enabled" value="disabled"/>
													<c:set var="cssClass" value="btn btn-sm btn-default form-control" />
													<c:set var="submit" value="app.panel.submitted"/>
													<c:set var="flag" value="false"/>
												</c:if>
											</c:forEach>
											<a href="submitfeedback.html?email=${loggedInUserEMAIL}&feedback_id=${feedback.id}" class="${cssClass}" ${enabled}><spring:message code="${submit}" /></a>
										</td>
									</tr>
									</c:if>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
