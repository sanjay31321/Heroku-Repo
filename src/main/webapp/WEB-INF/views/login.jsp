<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
	<div class="margin-left-right">
		<ul class="breadcrumb">
			<li><i class="glyphicon glyphicon-home"></i> 
				<a href="<spring:message code='app.home.link' />"><spring:message	code="" /></a>
				<span class="divider">»</span> 
			</li>
			<li>
				<a href="login.html"><spring:message code="app.breadcrumb.login" /></a>
			</li>
		</ul>
	</div>
	
		<div class="col-sm-4"></div>
		<div class="col-sm-4">	
			<div class="panel panel-primary">
			<div class="panel-heading">
			<h3 class="panel-title"><spring:message code="app.panel.title.login" /></h3>
			</div>
			<div class="panel-body">
				
				<form:form commandName="user">
						<div class="form-group">${errorMsg}</div>
						<div class="form-group">
							<label for="inputEmail"><spring:message code="app.form.email" /></label>
							<form:input path="email" cssClass="form-control" required="true" />
							<form:errors path="email" cssClass="danger" />
						</div>
						
						<div class="form-group">
							<label for="inputPassword"><spring:message code="app.form.password" /></label>
							<form:input path="password" type="password" cssClass="form-control"  required="true"/>
							<form:errors path="password" cssClass="danger" ></form:errors>
						</div>
						
						<div class="checkbox">
							<label><input type="checkbox"><spring:message code="app.form.rememberme" /></label>
						</div>
						<button type="submit" class="btn btn-primary pull left"> <spring:message code="app.form.login" /> </button>
						<span class="pull-right">
							<label><a href="forgetpassword.html"><spring:message code="app.panel.title.forgetpassword" /> </a></label> 
						</span> 
				</form:form>		
						
			</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
