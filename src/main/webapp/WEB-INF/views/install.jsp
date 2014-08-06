<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			
	
			<div class="panel panel-success">
				<div class="panel-heading">
					<h3 class="panel-title"><spring:message code="app.panel.title.install" /></h3>
				</div> 
				<div class="panel-body">
					<div class="alert">
						<form:form commandName="install">
							<spring:message code="app.install.msg" />  
							<button class="btn btn-lg btn-success"><spring:message code="app.btn.install" /></button>
						</form:form>
					</div>
					<div class="alert alert-error">
   						 ${Msg} ${errorMsg}
					</div>											
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
