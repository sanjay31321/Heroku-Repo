<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<a href="#"><spring:message code="app.panel.title.feedbackform" /></a>
				</li>
			</ul>
		</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-10">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<spring:message code="app.panel.title.feedbackform" />
					</h3>
				</div>
				<div class="panel-body">
					<b><spring:message code="app.feedback.form.msg" /></b>
					<br/>
				<hr/>
				<div class="alert alert-error"> ${Msg} ${errorMsg} </div>
				<form:form commandName="answer">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th><spring:message code="app.panel.question" /></th>
								<th colspan="5">
									<span class="pull-left">
										<spring:message code="app.disagree.stronglydisagree" />
									</span> 
									<span class="pull-right"> 
										<spring:message code="app.panel.stronglyagree" />
									</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${questionList}" var="question" varStatus="status">
								<c:set var="counter" value="${counter + 1}"/>
								<tr>
									<td>${counter}</td>
									<td>${question.question}<input type="hidden" name="que[${counter}]" value="${question.id}" id="${question.id}" /> </td>
									<td><input type="radio" name="ans[${counter}]" id="ans[${counter}]"  value="1" /> 1</td>
									<td><input type="radio" name="ans[${counter}]" id="ans[${counter}]"  value="2" /> 2</td>
									<td><input type="radio" name="ans[${counter}]" id="ans[${counter}]"  value="3" checked/> 3</td>
									<td><input type="radio" name="ans[${counter}]" id="ans[${counter}]"  value="4" /> 4</td>
									<td><input type="radio" name="ans[${counter}]" id="ans[${counter}]"  value="5" /> 5</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input type="hidden" value="${questionList.size()}" name="n" id="n"/>
					<button class="btn btn-lg btn-success" type="submit"><spring:message code="app.panel.submitfeedback" /></button>
					</form:form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
