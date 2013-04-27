
<%@ page import="wekaPlusGrails.Labeler" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'labeler.label', default: 'Labeler')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-labeler" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-labeler" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list labeler">
			
				<g:if test="${labelerInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="labeler.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${labelerInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${labelerInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="labeler.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${labelerInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${labelerInstance?.typeInput}">
				<li class="fieldcontain">
					<span id="typeInput-label" class="property-label"><g:message code="labeler.typeInput.label" default="Type Input" /></span>
					
						<span class="property-value" aria-labelledby="typeInput-label"><g:fieldValue bean="${labelerInstance}" field="typeInput"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${labelerInstance?.unlabeledArffFilename}">
				<li class="fieldcontain">
					<span id="unlabeledArffFilename-label" class="property-label"><g:message code="labeler.unlabeledArffFilename.label" default="Unlabeled Arff Filename" /></span>
					
						<span class="property-value" aria-labelledby="unlabeledArffFilename-label"><g:link action="downloadFileUnlabeledArff" id="${labelerInstance.id}"><g:fieldValue bean="${labelerInstance}" field="unlabeledArffFilename"/></g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${labelerInstance?.unlabeledTextFieldFilename}">
				<li class="fieldcontain">
					<span id="unlabeledTextFieldFilename-label" class="property-label"><g:message code="labeler.unlabeledTextFieldFilename.label" default="Unlabeled Text Field Filename" /></span>
					
						<span class="property-value" aria-labelledby="unlabeledTextFieldFilename-label"><g:link action="downloadFileUnlabeledTextField" id="${labelerInstance.id}"><g:fieldValue bean="${labelerInstance}" field="unlabeledTextFieldFilename"/></g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${labelerInstance?.labeledArffFilename}">
				<li class="fieldcontain">
					<span id="labeledArffFilename-label" class="property-label"><g:message code="labeler.labeledArffFilename.label" default="Labeled Arff Filename" /></span>
					
						<%--<span class="property-value" aria-labelledby="labeledArffFilename-label"><g:fieldValue bean="${labelerInstance}" field="labeledArffFilename"/></span>--%>
						
						<span class="property-value" aria-labelledby="labeledArffFilename-label"><g:link action="downloadFileLabeledArff" id="${labelerInstance.id}"><g:fieldValue bean="${labelerInstance}" field="labeledArffFilename"/></g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${labelerInstance?.classifier}">
				<li class="fieldcontain">
					<span id="classifier-label" class="property-label"><g:message code="labeler.classifier.label" default="Classifier" /></span>
					
						<span class="property-value" aria-labelledby="classifier-label"><g:link controller="classifier" action="show" id="${labelerInstance?.classifier?.id}">${labelerInstance?.classifier?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<%--<g:if test="${labelerInstance?.tt}">
				<li class="fieldcontain">
					<span id="tt-label" class="property-label"><g:message code="labeler.tt.label" default="Tt" /></span>
					
				</li>
				</g:if>--%>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${labelerInstance?.id}" />
					<g:link class="edit" action="edit" id="${labelerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
