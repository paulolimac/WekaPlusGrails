
<%@ page import="wekaPlusGrails.Classifier" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'classifier.label', default: 'Classifier')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>

	</head>
	<body>
		<a href="#list-classifier" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-classifier" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'classifier.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'classifier.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="filename" title="${message(code: 'classifier.filename.label', default: 'Filename')}" />
					
						<g:sortableColumn property="modelTxt" title="${message(code: 'classifier.modelTxt.label', default: 'Model Txt')}" />
					
						<g:sortableColumn property="typeClassifier" title="${message(code: 'classifier.typeClassifier.label', default: 'Type Classifier')}" />
					
						<th><g:message code="classifier.trainSample.label" default="Train Sample" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${classifierInstanceList}" status="i" var="classifierInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${classifierInstance.id}">${fieldValue(bean: classifierInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: classifierInstance, field: "description")}</td>
					
						<%--<td>${fieldValue(bean: classifierInstance, field: "filename")}</td>--%>
						
						<td><g:link action="downloadClassifierModel" id="${classifierInstance.id}"> ${fieldValue(bean: classifierInstance, field: "filename")} </g:link><br></td>
					
						<%--<td>${fieldValue(bean: classifierInstance, field: "modelTxt")}</td>--%>

						<td><g:link action="downloadModelTxt" id="${classifierInstance.id}"> ${fieldValue(bean: classifierInstance, field: "modelTxt")} </g:link><br></td>
					
						<td>${fieldValue(bean: classifierInstance, field: "typeClassifier")}</td>
					
						<td>${fieldValue(bean: classifierInstance, field: "trainSample")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${classifierInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
