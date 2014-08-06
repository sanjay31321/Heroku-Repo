<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<span class="divider">»</span></li>
				<li>
					<a href="feedbackmanager.html"><spring:message code="app.panel.title.feedbackmanager" /></a>
				</li>
				<li>
					<a href="createfeedback.html"><spring:message code="app.panel.title.createfeedback" /></a>
				</li>
			</ul>
		</div>
		
		<link rel="stylesheet" href="http://tarruda.github.io/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css" type="text/css"/>
		<script src="http://tarruda.github.io/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
	
		<div class="panel panel-default">
 		<div class="panel-heading"><spring:message code="app.panel.title.createfeedback" /></div>
				<div class="panel-body">
					<form:form commandName="feedback">
						<div class="row">
							<div class="col-sm-1"></div>
							<div class="col-sm-10">
								<div class="alert alert-error"> ${Msg} ${errorMsg} </div>
								<div class="row">
									<div class="col-sm-4">
										<label for="name"><spring:message code="app.form.name" /></label>
									</div>
									<div class="col-sm-8">
										<form:input cssClass="form-control" path="name" required="true"/>
										<form:errors path="name" cssClass="danger" />
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-4">
										<label for="question_set"><spring:message
												code="app.panel.questionset" /></label>
									</div>
									<div class="col-sm-8">
										<div class="row">
											<div class="col-sm-8">
												<form:select class="form-control" path="question_set.id" required="true">
													<option value="0">- Select -</option>
													<c:forEach items="${questionsetList}" var="questionset">
														<option value="${questionset.id}">${questionset.name}</option>
													</c:forEach>
												</form:select>
											</div>
											<div class="col-sm-4">
												<a href="questionsetmanager.html"
													class="btn btn-sm btn-primary col-sm-12"> <spring:message
														code="app.panel.questionset" /></a>
											</div>
										</div>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-sm-4">
										<label for="date_from"><spring:message code="app.panel.from" /></label>
									</div>
									<div class="col-sm-8">
										<div id="datetimepicker1" class="input-group date">
											<input name="date_from" data-format="dd.MM.yyyy" type="text" class="form-control" required="true"/>
											<span class="add-on input-group-addon"> 
												<i data-time-icon="icon-time" data-date-icon="glyphicon glyphicon-calendar"> </i>
											</span>
										</div>
									</div>
									<script type="text/javascript">
										$(function() {
											$('#datetimepicker1')
													.datetimepicker({
														language : 'en',
														pick12HourFormat : true
													});
										});
									</script>
								</div>
								<br>
								<div class="row">
									<div class="col-sm-4">
										<label for="date_to"><spring:message code="app.panel.to" /></label>
									</div>
									<div class="col-sm-8">
										<div id="datetimepicker2" class="input-group date">
											<input name="date_to" data-format="dd.MM.yyyy" type="text" class="form-control" required="true"/>
											<span class="add-on input-group-addon"> 
												<i data-time-icon="icon-time" data-date-icon="glyphicon glyphicon-calendar"> </i>
											</span>
										</div>
									</div>
									<script type="text/javascript">
										$(function() {
											$('#datetimepicker2')
													.datetimepicker({
														language : 'en',
														pick12HourFormat : true
													});
										});
									</script>
								</div>
								<br>
								
								<div class="row">
									<div class="col-sm-4">
										<label for="subject"><spring:message
												code="app.panel.subject" /></label>
									</div>
									<div class="col-sm-8">
										<div class="row">
											<div class="col-sm-8">
												<form:select class="form-control" path="subject.id" required="true">
													<option value="0">- Select -</option>
													<c:forEach items="${subjectList}" var="subject">
														<option value="${subject.id}">${subject.name}</option>
													</c:forEach>
												</form:select>
											</div>
											<div class="col-sm-4">
												<a href="subjectmanager.html"
													class="btn btn-sm btn-primary col-sm-12"><spring:message
														code="app.panel.subject" /></a>
											</div>
										</div>
									</div>
								</div>
								<br> <br>
								<div class="row">
									<div class="col-sm-4">
										<input type="hidden" name="email" value="${loggedInUserEMAIL}"/>
										<button type="submit"
											class="btn btn-sm btn-success form-control">
											<spring:message code="app.btn.save" />
										</button>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
     </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
