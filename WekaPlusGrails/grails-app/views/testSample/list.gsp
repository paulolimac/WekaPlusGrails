
<%@ page import="wekaPlusGrails.TestSample" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'testSample.label', default: 'TestSample')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-testSample" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-testSample" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'testSample.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'testSample.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="filename" title="${message(code: 'testSample.filename.label', default: 'Filename')}" />
					
						<g:sortableColumn property="evaluationTxt" title="${message(code: 'testSample.evaluationTxt.label', default: 'Evaluation Txt')}" />
					
						<th><g:message code="testSample.classifier.label" default="Classifier" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${testSampleInstanceList}" status="i" var="testSampleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${testSampleInstance.id}">${fieldValue(bean: testSampleInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: testSampleInstance, field: "description")}</td>
					
						<%--<td>${fieldValue(bean: testSampleInstance, field: "filename")}</td>--%>
						
						<td><g:link action="downloadFilename" id="${testSampleInstance.id}"> ${testSampleInstance?.filename} </g:link><br></td>
					
						<%--<td>${fieldValue(bean: testSampleInstance, field: "evaluationTxt")}</td>--%>
						
						<td><g:link action="downloadEvaluationTxt" id="${testSampleInstance.id}"> ${testSampleInstance?.evaluationTxt} </g:link><br></td>
					
						<td>${fieldValue(bean: testSampleInstance, field: "classifier")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${testSampleInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
