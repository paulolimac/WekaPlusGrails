
<%@ page import="wekaPlusGrails.TestSample" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'testSample.label', default: 'TestSample')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-testSample" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-testSample" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list testSample">
			
				<g:if test="${testSampleInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="testSample.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${testSampleInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${testSampleInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="testSample.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${testSampleInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${testSampleInstance?.filename}">
				<li class="fieldcontain">
					<span id="filename-label" class="property-label"><g:message code="testSample.filename.label" default="Filename" /></span>
										
						<span class="property-value" aria-labelledby="filename-label"><g:link action="downloadFilename" id="${testSampleInstance.id}"> <g:fieldValue bean="${testSampleInstance}" field="filename"/> </g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${testSampleInstance?.evaluationTxt}">
				<li class="fieldcontain">
					<span id="evaluationTxt-label" class="property-label"><g:message code="testSample.evaluationTxt.label" default="Evaluation Txt" /></span>
					
						<%--<span class="property-value" aria-labelledby="evaluationTxt-label"><g:fieldValue bean="${testSampleInstance}" field="evaluationTxt"/></span>--%>
						
						<span class="property-value" aria-labelledby="evaluationTxt-label"><g:link action="downloadEvaluationTxt" id="${testSampleInstance.id}"> <g:fieldValue bean="${testSampleInstance}" field="evaluationTxt"/> </g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${testSampleInstance?.classifier}">
				<li class="fieldcontain">
					<span id="classifier-label" class="property-label"><g:message code="testSample.classifier.label" default="Classifier" /></span>
					
						<span class="property-value" aria-labelledby="classifier-label"><g:link controller="classifier" action="show" id="${testSampleInstance?.classifier?.id}">${testSampleInstance?.classifier?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${testSampleInstance?.id}" />
					<g:link class="edit" action="edit" id="${testSampleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
