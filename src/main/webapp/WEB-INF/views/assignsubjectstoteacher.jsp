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
				<li>
					<i class="glyphicon glyphicon-home"></i> 
					<a href="${loggedInUserHOMELINK}"><spring:message code="${loggedInUserHOME}" /></a> 
					<span class="divider">»</span>
				</li>
				<li>
					<a href="teachermanager.html"><spring:message code="app.manager.teacher" /></a>
					<span class="divider">»</span>
				</li>
				<li>
					<a href="#"><spring:message code="app.subject.assign.to.teacher" /></a>
				</li>
			</ul>
		</div>
	
	<div class="col-sm-12">
		<div class="row">
			<div class="col-sm-8">
				<div class="panel panel-default">
	 				<div class="panel-heading"><spring:message code="app.panel.subject" /></div>
					<div class="panel-body">				
						<div class="table-responsive">
							<form:form commandName="subject">
								<table class="table">
									<tr>
										<td>
											<table class="table">
												<thead>
													<tr>
														<th><spring:message code="app.panel.subject" /></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${subjectList}" var="subject">
														<c:set var="counter" value="${counter+1}" />
														<tr>
															<c:if test="${counter%2==1}">
																<td><input type="checkbox" name="subject1" value="${subject.id}" />	${subject.name }</td>
															</c:if>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</td>
										<td>
											<table class="table">
												<thead>
													<tr>
														<th><spring:message code="app.panel.subject" /></th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${subjectList}" var="subject">
														<c:set var="counter" value="${counter+1}" />
														<tr>
															<c:if test="${counter%2==0}">
																<td><input type="checkbox" name="subject1" value="${subject.id}" /> ${subject.name }</td>
															</c:if>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</td>
									</tr>
								</table>
								<div class="row">
									<div class="col-sm-4">
										<input type="hidden" name="teacher_id" value="${teacher.id}">
	        							<input type="hidden" name="action" value="add">
										<button type="submit" class="btn btn-primary form-control"><spring:message code="app.assign" /></button>
									</div>
								</div>
							</form:form>
						</div>
	        		</div>
	        	</div>
	        </div>
	        
	        <div class="col-sm-4">
				<div class="panel panel-default">
	 				<div class="panel-heading"><spring:message code="app.assigned.subjects" /></div>
					<div class="panel-body">
						<form:form commandName="course">				
							<div class="table-responsive">
								<h5 class="sub-header"><spring:message code="app.teacher"/> : <b>${teacher.name}</b></h5><hr/>
								<table class="table table-striped">
		            				<thead>
										<tr>
											<th><spring:message code="app.panel.subject" /></th>
										</tr>
									</thead>
									<tbody>
			            				<c:forEach items="${subjectList}" var="subject">
											<c:forEach items="${teacherSubjectList}" var="teacher_subject">
												<c:if test="${subject.id == teacher_subject.subject.id}">
													<tr>
														<td><input type="checkbox" name="subject2" value="${subject.id}" /> ${subject.name }</td>
													</tr>
												</c:if>
											</c:forEach>
										</c:forEach>
									</tbody>
								</table>
	        				</div>
	        				<input type="hidden" name="teacher_id" value="${teacher.id}">
	        				<input type="hidden" name="action" value="remove">
	        				<button class="btn btn-primary"><spring:message code="app.remove" /></button>
	        			</form:form>
	        		</div>
	        	</div>
	        </div>
	    </div>
    </div>
	</tiles:putAttribute>
</tiles:insertDefinition>
