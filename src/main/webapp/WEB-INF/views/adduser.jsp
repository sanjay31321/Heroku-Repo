<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					<a href="usermanager.html"><spring:message code="app.panel.title.usermanager" /></a>
					<span class="divider">»</span>
				</li>
				<li>
					<a href="#"><spring:message code="app.user.add" /></a>
				</li>
			</ul>
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-8">
			<div class="panel panel-default">
				<div class="panel-heading">
					<spring:message code="app.user.add" />
				</div>
				<div class="panel-body">
					<div>${errorMsg}${Msg}</div>
					<form:form commandName="user">
						<c:set var="flag_role" value="false"/>
						<c:set var="flag_course" value="false"/>
						<div class="row">
							<div class="col-sm-9">
								<div class="row">
									<div class="col-sm-4">
										<label for="role"><spring:message code="app.form.role" /></label>
									</div>
									<div class="col-sm-7">
										<form:select path="user_role.id" cssClass="form-control" required="true">
											<option value="0">- Select -</option>
											<c:forEach items="${userRoleList}" var="user_role">
												<option value="${user_role.id}">${user_role.name}</option>
											</c:forEach>
										</form:select>
										<form:errors path="user_role.id" cssClass="errorblock"  />
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-4">
										<label for="email"><spring:message code="app.form.email" /></label>
									</div>
									<div class="col-sm-7">
										<form:input path="email" cssClass="form-control" pattern="[^@]+@[^@]+\.[a-zA-Z]{2,6}"  required="true" />
										<form:errors path="email" cssClass="errorblock"  />
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-sm-4">
										<label for="password"><spring:message code="app.form.password" /></label>
									</div>
									<div class="col-sm-7">
										<form:password cssClass="form-control" path="password" required="true"/>
										<form:errors path="password" cssClass="errorblock"  />
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-4">
										<label for="name"><spring:message code="app.form.name" /></label>
									</div>
									<div class="col-sm-7">
										<form:input cssClass="form-control" path="name" required="true"/>
										<form:errors path="name" cssClass="errorblock"  />
									</div>
								</div>
								<br><br>
								<div class="row">
									<div class="col-sm-4"></div>
									<div class="col-sm-3">
										<button type="submit" class="btn btn-sm btn-success form-control">
											<spring:message code="app.form.save" />
										</button>
									</div>
									<div class="col-sm-3">
										<a href="usermanager.html" class="btn btn-sm btn-default form-control">
											<spring:message code="app.form.cancel" />
										</a>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
