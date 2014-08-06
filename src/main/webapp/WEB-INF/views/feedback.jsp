<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<%@ page trimDirectiveWhitespaces="true" %>

<tiles:insertDefinition name="studentTemplate">
	<tiles:putAttribute name="body">

		<div class="col-sm-9">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						<spring:message code="app.panel.title.feedbackform" />
					</h3>
				</div>
				<div class="panel-body">
					<spring:message code="app.feedback.form.msg" />
					<hr/>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
