<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="true"%>

<tiles:insertDefinition name="adminTemplate">
	<tiles:putAttribute name="body">
		<c:set var="role" value="${loggedInUserROLE}"/>
		<c:if test="${role!='ROLE_ADMIN'}">
			<script type="text/javascript">	window.location.href="login.html"; </script>
		</c:if>
		<div class="margin-left-right">
			<ul class="breadcrumb">
				<li><i class="glyphicon glyphicon-home"></i> 
					<a href="${loggedInUserHOMELINK}">
						<spring:message	code="${loggedInUserHOME}" />
					</a> 
				</li>
			</ul>
		</div>
		
		<div class="col-sm-8">	
			<div class="panel panel-primary">
			<div class="panel-heading">
			<h3 class="panel-title">Admin</h3>
			</div>
			<div class="panel-body">
					
			</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
