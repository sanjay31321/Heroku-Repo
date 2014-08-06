<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<c:set var="template" value="defaultTemplate"/>
<c:if test="${loggedInUserROLE=='ROLE_ADMIN' }">
	<c:set var="template" value="adminTemplate"/>
</c:if>
<c:if test="${loggedInUserROLE=='ROLE_TEACHER' }">
	<c:set var="template" value="teacherTemplate"/>
</c:if>
<tiles:insertDefinition name="${template}">
	<tiles:putAttribute name="body">
	<div class="col-sm-9">
	
		<div class="panel panel-default">
 		<div class="panel-heading">Create Question Set</div>
 			<div class="panel-body">
 				<div class="row">
 					<div class="col-sm-8">
						<div class="row">
 							<div class="col-sm-4">
								<label for="email">Name</label>
							</div>
							<div class="col-sm-7">
								<input Class="form-control" name="email" />
							</div>
						</div>
						<br/>
						<div class="row">
 							<div class="col-sm-4">
								<label for="first_name">Number of Questions</label>
							</div>
							<div class="col-sm-7">
								<input Class="form-control" name="first_name" />
							</div>
						</div>
						<br/><br/>
						<div class="row">
							
 							<div class="col-sm-4">
 								<button  type="submit" class="btn btn-sm btn-success form-control">Save</button>
 							</div>
 							
 						</div>
 					</div>
 					
 				</div>
 				
 			</div>	
 				
        </div>
     </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
