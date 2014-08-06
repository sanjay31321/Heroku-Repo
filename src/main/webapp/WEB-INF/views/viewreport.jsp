<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
					<a href="feedbackreport.html"><spring:message code="app.feedback.report" /></a>
					<span class="divider">»</span>
				</li>
				<li>
					<a href="#"><spring:message code="app.view.feedback.report" /></a>
				</li>
			</ul>
		</div>
		<div class="col-sm-12">
			<div class="row">
				<div class="col-sm-6">
					<div class="well">
						<div class="row">
							<div class="col-sm-4">
								<label><spring:message code="app.panel.feedback"/></label><span class="pull-right">:</span> 
							</div>
							<div class="col-sm-8">
								${feedback.name}
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<label><spring:message code="app.teacher"/></label><span class="pull-right">:</span>
							</div>
							<div class="col-sm-8">
								${feedback.user.name}
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<label><spring:message code="app.subject"/></label><span class="pull-right">:</span>
							</div>
							<div class="col-sm-8">
								${feedback.subject.name}
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<label><spring:message code="app.panel.created"/></label><span class="pull-right">:</span>
							</div>
							<div class="col-sm-8">
								<fmt:formatDate value="${feedback.created}" pattern="dd-MMMM-yyyy" />
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<label><spring:message code="app.feedback.attempted"/></label><span class="pull-right">:</span>
							</div>
							<div class="col-sm-8">
								${feedback.attempted }
							</div>
						</div>
						<div class="row">
							<div class="col-sm-4">
								<label><spring:message code="app.panel.questionset"/></label><span class="pull-right">:</span>
							</div>
							<div class="col-sm-8">
								${feedback.question_set.name}
							</div>
						</div>
						
					</div>
				</div>
				<div class="col-sm-6">
					<div class="panel panel-default">
						<div class="panel-heading"><strong><spring:message code="app.result"/></strong></div>
						<div class="panel-body">
						<div class="row">
							<c:set var="total_star" value="${0}"/>
							<c:set var="temp" value="${0}"/>
							<c:forEach items="${questionList}" var="question">
								<c:set var="option1" value="${0}"/>
								<c:set var="option2" value="${0}"/>
								<c:set var="option3" value="${0}"/>
								<c:set var="option4" value="${0}"/>
								<c:set var="option5" value="${0}"/>								
								<c:forEach items="${answerList}" var="answer">
									<c:if test="${answer.question.id == question.id}">
										<c:if test="${answer.answer == 1}">
											<c:set var="option1" value="${option1+1}"/>
										</c:if>
										<c:if test="${answer.answer == 2}">
											<c:set var="option2" value="${option2+1}"/>
										</c:if>
										<c:if test="${answer.answer == 3}">
											<c:set var="option3" value="${option3+1}"/>
										</c:if>
										<c:if test="${answer.answer == 4}">
											<c:set var="option4" value="${option4+1}"/>
										</c:if>
										<c:if test="${answer.answer == 5}">
											<c:set var="option5" value="${option5+1}"/>
										</c:if>
									</c:if>
								</c:forEach>
								<c:if test="${option1>0}">
									<c:set var="temp" value="${option1*1}"/>
									<c:set var="total_star" value="${total_star+temp}"/>
								</c:if>
								<c:if test="${option2>0}">
									<c:set var="temp" value="${option2*2}"/>
									<c:set var="total_star" value="${total_star+temp}"/>
								</c:if>
								<c:if test="${option3>0}">
									<c:set var="temp" value="${option3*3}"/>
									<c:set var="total_star" value="${total_star+temp}"/>
								</c:if>
								<c:if test="${option4>0}">
									<c:set var="temp" value="${option4*4}"/>
									<c:set var="total_star" value="${total_star+temp}"/>
								</c:if>
								<c:if test="${option5>0}">
									<c:set var="temp" value="${option5*5}"/>
									<c:set var="total_star" value="${total_star+temp}"/>
								</c:if>
							</c:forEach>
							<div class="col-sm-12">
								<c:set var="total_user" value="${questionList.size()}"/>
								<c:set var="score" value="${(total_star/ (feedback.attempted * 5 * total_user)) * 100}"/></div>
								<div class="row">
									<div class="col-sm-1"></div>
									<div class="col-sm-4"><strong><spring:message code="app.score"/></strong><span class="pull-right">:</span></div>
									<div class="col-sm-3"><c:if test="${feedback.attempted<=0}">
										<spring:message code="app.score.not.available"/></c:if>
									<c:if test="${feedback.attempted>0}">
											${fn:substringBefore(score, ".")} %
									</c:if>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-sm-1"></div>
									<div class="col-sm-10">
										<div class="progress">
       										<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="60" 
       										aria-valuemin="0" aria-valuemax="100" style="width: ${score}%"><span class="sr-only">${score}% Complete</span></div>
     									</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
       </div>
       <br>
       <div class="col-sm-12">
       		<div class="well">
       			<div class="table-responsive">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th><spring:message code="app.panel.question"/></th>
								<th colspan="5">
									<spring:message code="app.result"/><span class="pull-right">
									<spring:message code="app.total.attempted.user"/> : ${feedback.attempted}</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${questionList}" var="question">
								<c:set var="option1" value="${0}"/>
       							<c:set var="option2" value="${0}"/>
       							<c:set var="option3" value="${0}"/>
       							<c:set var="option4" value="${0}"/>
       							<c:set var="option5" value="${0}"/>
								<c:set var="counter" value="${counter+1}"/>
								<c:forEach items="${answerList}" var="answer">
	       							<c:if test="${answer.question.id == question.id}">
	       								<c:if test="${answer.answer == 1}">
	       									<c:set var="option1" value="${option1+1}"/>
	       								</c:if>
	       								<c:if test="${answer.answer == 2}">
	       									<c:set var="option2" value="${option2+1}"/>
	       								</c:if>
	       								<c:if test="${answer.answer == 3}">
	       									<c:set var="option3" value="${option3+1}"/>
	       								</c:if>
	       								<c:if test="${answer.answer == 4}">
	       									<c:set var="option4" value="${option4+1}"/>
	       								</c:if>
	       								<c:if test="${answer.answer == 5}">
	       									<c:set var="option5" value="${option5+1}"/>
	       								</c:if>
	       							</c:if>
	       						</c:forEach>
								<tr>
									<td>${counter}</td>
									<td>${question.question}</td>
									<td><spring:message code="app.disagree.stronglydisagree"/> <span class="badge">${option1}</span>
     								<td><spring:message code="app.disagree"/> <span class="badge">${option2}</span>
     								<td><spring:message code="app.not.agree.no.disagree"/> <span class="badge">${option3}</span>
     								<td><spring:message code="app.agree"/> <span class="badge">${option4}</span>
     								<td><spring:message code="app.panel.stronglyagree"/> <span class="badge">${option5}</span>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
       		</div>
       </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
