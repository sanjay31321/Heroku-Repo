<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<a href="forgetpassword.html"><spring:message code="app.panel.title.forgetpassword" /></a>
					<span class="divider">»</span>
				</li>
				<li>
					<a href="resetsuccess.html"><spring:message code="app.panel.resetpasswordsucess" /></a>
				</li>
			</ul>
		</div>
		<div class="col-sm-1"></div>
		<div class="col-sm-10">	
			<div class="panel panel-success">
			<div class="panel-heading">
			<h3 class="panel-title">
				<spring:message code="app.panel.title.congratulations" /> - 
				<spring:message code="app.panel.resetpasswordsucessfully" />
			</h3>
			</div> 
			<div class="panel-body">
				<div class="alert">
					<spring:message code="app.resetpassword.success" />
      			</div>											
			</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
