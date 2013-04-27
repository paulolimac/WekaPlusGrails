
<%@ page import="wekaPlusGrails.TrainSample" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'trainSample.label', default: 'TrainSample')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
	
		<a href="#show-trainSample" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-trainSample" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list trainSample">
			
				<g:if test="${trainSampleInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="trainSample.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${trainSampleInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${trainSampleInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="trainSample.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${trainSampleInstance}" field="description"/></span>
					
				</li>
				</g:if>
				
				<%-- 			
				<g:if test="${trainSampleInstance?.arffFile}">
				<li class="fieldcontain">
					<span id="arffFile-label" class="property-label"><g:message code="trainSample.arffFile.label" default="Arff File" /></span>
					
				</li>
				</g:if>
				--%>
				
				<g:if test="${trainSampleInstance?.filename}">
				<li class="fieldcontain">
					<span id="filename-label" class="property-label"><g:message code="trainSample.filename.label" default="Arff File" /></span>
						
						<span class="property-value" aria-labelledby="name-label"><g:link action="download" id="${trainSampleInstance.id}"><g:fieldValue bean="${trainSampleInstance}" field="filename"/></g:link></span>

					
				</li>
				</g:if>
				
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${trainSampleInstance?.id}" />
					<g:link class="edit" action="edit" id="${trainSampleInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
