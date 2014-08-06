<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
	<div class="margin-left-right">
		<ul class="breadcrumb">
			<li><i class="glyphicon glyphicon-home"></i> 
				<a href="<spring:message code='app.home.link' />"><spring:message	code="" /></a>
				<span class="divider">»</span> 
			</li>
			<li>
				<a href="signup.html"><spring:message code="app.breadcrumb.signup" /></a>
			</li>
		</ul>
	</div>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$("#role").change(function() {
					if ($("#role").val() == 2) {
						$("#student").hide();
					}
					if ($("#role").val() == 3) {
						$("#student").show();
					}
				});
			});
		</script>
		
		
		<div class="col-sm-2"></div>
		<div class="col-sm-8">	
			<div class="panel panel-primary">
			<div class="panel-heading">
			<h3 class="panel-title"><spring:message code="app.panel.title.signup" /></h3>
			</div>
			<div class="panel-body">
				<form:form commandName="user">
					<div class="form-group">${errorMsg}</div>
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="email"><spring:message code="app.form.email" /></label>
								<form:input path="email" cssClass="form-control" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}"  required="true" />
								<form:errors path="email" cssClass="danger" />
							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group">
								<label for="role"><spring:message code="app.form.role" /></label><br>
								<form:select path="user_role.id" cssClass="form-control"  id="role" required="true">
									<option value="0">- Select -</option>
									<option value="3"><spring:message code="app.student" /></option>
									<option value="2"><spring:message code="app.teacher" /></option>
								</form:select>
								<form:errors path="user_role.id" cssClass="danger" />
							</div>
						</div>
						
						<div class="col-sm-4" id="student">
							<div class="form-group">
								<label for="role"><spring:message code="app.form.choosecourse" /></label><br>
								<form:select path="course_id" cssClass="form-control">
									<option value="0">- Select -</option>
									<c:forEach items="${courseList}" var="course">
										<option value="${course.id}">${course.name}</option>
									</c:forEach>
								</form:select>
								<form:errors path="course_id" cssClass="danger" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
								<label for="password"><spring:message code="app.form.password" /></label>
								<form:password path="password" cssClass="form-control" required="true" />
								<form:errors path="password" cssClass="danger" />
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
							<label for="varify_password"><spring:message code="app.form.confirmpassword" /></label>
							<form:password path="confirm_password" class="form-control" required="true"/>
							<form:errors path="confirm_password" cssClass="danger" />
							</div>
						</div>
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
							<label for="name"><spring:message code="app.form.name" /></label>
							<form:input path="name" class="form-control" required="true"/>
							<form:errors path="name" cssClass="danger" />
							</div>
						</div> 
					</div>
					
					<div class="row">
						<div class="col-sm-6">
							<div class="form-group">
							<label for="security_question"><spring:message code="app.form.securityquestion" /></label>
							<form:input path="security_question" cssClass="form-control" required="true" />
							<form:errors path="security_question" cssClass="danger" />
							</div>
						</div>
						<div class="col-sm-6">
							<div class="form-group">
							<label for="security_answer"><spring:message code="app.form.securityanswer" /></label>
							<form:password path="security_answer" cssClass="form-control" required="true" />
							<form:errors path="security_answer" cssClass="danger" />
							</div>
						</div>
					</div>
														
					<button type="submit" id="submit" class="btn btn-primary"><spring:message code="app.form.register" /></button>
				</form:form>
			</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
