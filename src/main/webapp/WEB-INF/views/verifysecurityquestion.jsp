<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>
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
					<a href="forgetpassword.html"><spring:message code="app.panel.title.forgetpassword" /></a>
					<span class="divider">»</span>
				</li>
				<li>
					<a href="verifysecurityquestion.html?email=${user.email}"><spring:message code="app.panel.title.verifysecurityquestion" /></a>
				</li>
			</ul>
		</div>
		
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			
			<div class="panel panel-primary">
			<div class="panel-heading">
			<h3 class="panel-title"><spring:message code="app.panel.title.verifysecurityquestion" /></h3>
			</div>
			<div class="panel-body">			
				<form:form commandName="user">
						<div class="alert alert-error"> ${Msg} ${errorMsg} </div>
						<div class="form-group">
							<label for="inputEmail"><spring:message code="app.form.email" /></label>
							<form:input path="email" cssClass="form-control" disabled="true" value="${user.email}" />
							<form:errors path="email" cssClass="error" />
						</div>
						<div>
							<div class="form-group">
								<label for="securityquestion"><spring:message code="app.form.securityquestion" /></label>
								<form:input path="security_question" cssClass="form-control" value="${user.security_question}" disabled="true" />
							</div>
							<div class="form-group">
								<label for="security_answer"><spring:message code="app.form.securityanswer" /></label>
								<form:password path="security_answer" class="form-control" autocomplete="off" required="true"/>
								<form:errors path="security_answer" cssClass="error" />
							</div>
						</div>
						<button type="submit" class="btn btn-primary"> <spring:message code="app.form.submit" /> </button>
				</form:form>
				
			</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
