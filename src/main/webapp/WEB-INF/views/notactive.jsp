<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="col-sm-2"></div>
		<div class="col-sm-8">	
			<div class="panel panel-danger">
			<div class="panel-heading">
			<h3 class="panel-title"><spring:message code="app.panel.title.needactivation" /></h3>
			</div> 
			<div class="panel-body">
				<div class="alert">
        			<spring:message code="app.account.inactive" />
      			</div>											
			</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
