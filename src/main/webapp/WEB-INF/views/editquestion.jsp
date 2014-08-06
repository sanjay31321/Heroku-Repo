<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
					<a href="questionsetmanager.html"><spring:message code="app.panel.title.questionsetmanager" /></a>
					<span class="divider">»</span>
				</li>
				<li>
					<a href="#"><spring:message code="app.manager.question" /></a>
				</li>
			</ul>
		</div>
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
	
	<div class="panel panel-default">
 		<div class="panel-heading">
 			<spring:message code="app.manager.question" />
 			<span class="pull-right"><a href="questionmanager.html"><spring:message code="app.btn.back" /></a></span>
 		</div>
 			<div class="panel-body">
				<div class="row">
					<div class="col-sm-8">
							<form:form commandName="question">
								<div class="input-group">${errorMsg} ${Msg}</div>
								<div class="input-group">
									<form:input path="question" class="form-control" required="true"/>
									<form:errors path="question" cssClass="danger" />
									<input type="hidden" id="question_set_id" name="question_set_id" value="${question.question_set.id}"> 
										<span class="input-group-btn">
											<button type="submit" class="btn btn-default" type="button">
											<spring:message code="app.question.add" />
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
									<th><spring:message code="app.question" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${counter}</td>
									<td>${question.question}</td>
								</tr>
							</tbody>
						</table>
					</div>
          		</div>
          	</div>
          </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
