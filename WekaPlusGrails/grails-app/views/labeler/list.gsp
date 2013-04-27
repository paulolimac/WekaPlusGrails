
<%@ page import="wekaPlusGrails.Labeler" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'labeler.label', default: 'Labeler')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-labeler" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-labeler" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'labeler.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'labeler.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="typeInput" title="${message(code: 'labeler.typeInput.label', default: 'Type Input')}" />
					
						<g:sortableColumn property="unlabeledArffFilename" title="${message(code: 'labeler.unlabeledArffFilename.label', default: 'Unlabeled Arff Filename')}" />
					
						<g:sortableColumn property="unlabeledTextFieldFilename" title="${message(code: 'labeler.unlabeledTextFieldFilename.label', default: 'Unlabeled Text Field Filename')}" />
					
						<g:sortableColumn property="labeledArffFilename" title="${message(code: 'labeler.labeledArffFilename.label', default: 'Labeled Arff Filename')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${labelerInstanceList}" status="i" var="labelerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${labelerInstance.id}">${fieldValue(bean: labelerInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: labelerInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: labelerInstance, field: "typeInput")}</td>
						
						<td><g:link action="downloadFileUnlabeledArff" id="${labelerInstance.id}"> ${labelerInstance?.unlabeledArffFilename} </g:link></td>
						
						<td><g:link action="downloadFileUnlabeledTextField" id="${labelerInstance.id}"> ${labelerInstance?.unlabeledTextFieldFilename} </g:link></td>
					
						<%--<td>${fieldValue(bean: labelerInstance, field: "labeledArffFilename")}</td>--%>
						
						<td><g:link action="downloadFileLabeledArff" id="${labelerInstance.id}">${fieldValue(bean: labelerInstance, field: "labeledArffFilename")}</g:link></td>
						
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${labelerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
