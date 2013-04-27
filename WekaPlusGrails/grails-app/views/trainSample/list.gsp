
<%@ page import="wekaPlusGrails.TrainSample" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'trainSample.label', default: 'TrainSample')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-trainSample" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-trainSample" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'trainSample.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'trainSample.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="arffFile" title="${message(code: 'trainSample.arffFile.label', default: 'Arff File')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${trainSampleInstanceList}" status="i" var="trainSampleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${trainSampleInstance.id}">${fieldValue(bean: trainSampleInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: trainSampleInstance, field: "description")}</td>
						
						<td><g:link action="download" id="${trainSampleInstance.id}"> ${trainSampleInstance?.filename} </g:link><br></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${trainSampleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
