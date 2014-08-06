<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
	
		<div class="col-sm-2"></div>
		<div class="col-sm-4">	
			<div class="panel panel-primary">
			<div class="panel-heading">
			<h3 class="panel-title"><spring:message code="app.panel.title.login" /></h3>
			</div>
			<div class="panel-body">
				
				<form:form commandName="user">
						<form:errors path="*" cssClass="errorblock" element="div" />
						
						<div class="form-group">
							<label for="inputEmail"><spring:message code="app.form.email" /></label>
							<form:input path="email" cssClass="form-control" />
							<form:errors path="email" cssClass="error" />
						</div>
						
						<div class="form-group">
							<label for="inputPassword"><spring:message code="app.form.password" /></label>
							<form:input path="password" type="password" cssClass="form-control"  />
							<form:errors path="password" cssClass="error" ></form:errors>
						</div>
						
						<div class="checkbox">
							<label><input type="checkbox"><spring:message code="app.form.rememberme" /></label>
						</div>
						<button type="submit" class="btn btn-primary pull left"> <spring:message code="app.form.login" /> </button>
						<span class="pull-right">
								<label><a href="forgetpassword.html"><spring:message code="app.forgetpassword" /> </a></label> 
							</span> 
				</form:form>		
						
			</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
