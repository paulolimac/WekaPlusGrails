
<%@ page import="wekaPlusGrails.Classifier" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'classifier.label', default: 'Classifier')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>

	</head>
	<body>
		<a href="#show-classifier" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-classifier" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list classifier">
			
				<g:if test="${classifierInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="classifier.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${classifierInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${classifierInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="classifier.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${classifierInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${classifierInstance?.filename}">
				<li class="fieldcontain">
					<span id="filename-label" class="property-label"><g:message code="classifier.filename.label" default="Filename" /></span>
					
						<%--<span class="property-value" aria-labelledby="filename-label"><g:fieldValue bean="${classifierInstance}" field="filename"/></span>--%>
						
						<span class="property-value" aria-labelledby="filename-label"><g:link action="downloadClassifierModel" id="${classifierInstance.id}"> ${fieldValue(bean: classifierInstance, field: "filename")} </g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${classifierInstance?.modelTxt}">
				<li class="fieldcontain">
					<span id="modelTxt-label" class="property-label"><g:message code="classifier.modelTxt.label" default="Model Txt" /></span>
					
						<%--<span class="property-value" aria-labelledby="modelTxt-label"><g:fieldValue bean="${classifierInstance}" field="modelTxt"/></span>--%>
						
						<span class="property-value" aria-labelledby="modelTxt-label"><g:link action="downloadModelTxt" id="${classifierInstance.id}"> ${fieldValue(bean: classifierInstance, field: "modelTxt")} </g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${classifierInstance?.typeClassifier}">
				<li class="fieldcontain">
					<span id="typeClassifier-label" class="property-label"><g:message code="classifier.typeClassifier.label" default="Type Classifier" /></span>
					
						<span class="property-value" aria-labelledby="typeClassifier-label"><g:fieldValue bean="${classifierInstance}" field="typeClassifier"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${classifierInstance?.trainSample}">
				<li class="fieldcontain">
					<span id="trainSample-label" class="property-label"><g:message code="classifier.trainSample.label" default="Train Sample" /></span>
					
						<span class="property-value" aria-labelledby="trainSample-label"><g:link controller="trainSample" action="show" id="${classifierInstance?.trainSample?.id}">${classifierInstance?.trainSample?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${classifierInstance?.id}" />
					<g:link class="edit" action="edit" id="${classifierInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
