<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page session="false"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
	
	<div class="margin-left-right">
		<ul class="breadcrumb">
			<li><i class="glyphicon glyphicon-home"></i> 
				<a href="<spring:message code='app.home.link' />">
					<spring:message	code="" />
				</a> 
			</li>
			
		</ul>
	</div>
		
	</tiles:putAttribute>
</tiles:insertDefinition>
