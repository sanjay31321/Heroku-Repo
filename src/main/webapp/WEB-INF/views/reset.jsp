<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="true"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<c:if test="${resetSession!='resetThisUser'}">
			<script type="text/javascript">	window.location.href="forgetpassword.html"; </script>
		</c:if>
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
					<a href="reset.html?email=${user.email}"><spring:message code="app.panel.title.resetpassword" /></a>
				</li>
			</ul>
		</div>
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			
			<div class="panel panel-primary">
			<div class="panel-heading">
			<h3 class="panel-title"><spring:message code="app.panel.title.resetpassword" /></h3>
			</div>
			<div class="panel-body">			
				<form:form commandName="user">
						<div class="form-group">${errorMsg}</div>
						<div class="form-group">
							<label for="inputEmail"><spring:message code="app.form.email" /></label>
							<form:input path="email" cssClass="form-control" disabled="true" value="${email}" />
							<form:errors path="email" cssClass="error" />
						</div>
							<div class="form-group">
								<label for="password"><spring:message code="app.form.newpassword" /></label>
								<form:password path="password" cssClass="form-control"  required="true"/>
								<form:errors path="password" cssClass="error" />
							</div>
							<div class="form-group">
								<label for="confirm_password"><spring:message code="app.form.confirmpassword" /></label>
								<form:password path="confirm_password" cssClass="form-control" required="true"/>
								<form:errors path="confirm_password" cssClass="error" />
						</div>
						<button type="submit" id="submit" class="btn btn-primary"> <spring:message code="app.form.submit" /> </button>
				</form:form>
				
			</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
