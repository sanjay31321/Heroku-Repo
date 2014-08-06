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
				</li>
			</ul>
		</div>
	<div class="col-sm-1"></div>
	<div class="col-sm-10">
	
	<div class="panel panel-default">
 		<div class="panel-heading"><spring:message code="app.panel.title.questionsetmanager" /></div>
 			<div class="panel-body">
				<div class="row">
					<div class="col-sm-8">
							<form:form commandName="question_set">
								<div class="input-group">${errorMsg} ${Msg}</div>
								<div class="input-group">
									<form:input path="name" class="form-control" required="true" /> 
									<form:errors path="name" cssClass="danger" />
										<span class="input-group-btn">
											<button type="submit" class="btn btn-default" type="button">
											<spring:message code="app.btn.addquestionset" />
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
									<th><spring:message code="app.panel.questionset" /></th>
									<th><spring:message code="app.panel.date" /></th>
									<th><spring:message code="app.panel.action" /></th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${questionsetList}" var="questionset">
									<tr>
										<td>${questionset.id}</td>
										<td>${questionset.name}</td>
										<td>date</td>
										<td>
											<a href="editquestionset.html?id=${questionset.id}" class="btn btn-sm btn-primary">
												<spring:message code="app.btn.edit" /></a>
											<a href="deletequestionset.html?id=${questionset.id}" class="btn btn-sm btn-danger">
												<spring:message code="app.btn.delete" /></a>
											<a href="questionmanager.html?id=${questionset.id}" class="btn btn-sm btn-success">
												<spring:message code="app.btn.addquestion" /></a>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
          		</div>
          	</div>
          </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
