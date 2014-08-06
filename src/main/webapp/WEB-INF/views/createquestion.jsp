<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<tiles:insertDefinition name="adminTemplate">
	<tiles:putAttribute name="body">
	<div class="col-sm-9">
	
		<div class="panel panel-default">
 		<div class="panel-heading">Create Question</div>
 			<div class="panel-body">
 				<div class="row">
 					<div class="col-sm-2"><br>
						<label for="email">Question</label>
					</div>
					<div class="col-sm-9">
						<textarea Class="form-control" name="email" rows="4"></textarea>
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
	</tiles:putAttribute>
</tiles:insertDefinition>
