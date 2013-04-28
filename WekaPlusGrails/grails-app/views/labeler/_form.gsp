<%@ page import="wekaPlusGrails.Labeler" %>



<div class="fieldcontain ${hasErrors(bean: labelerInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="labeler.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${labelerInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: labelerInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="labeler.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="500" required="" value="${labelerInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: labelerInstance, field: 'typeInput', 'error')} required">
	<label for="typeInput">
		<g:message code="labeler.typeInput.label" default="Type Input" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="typeInput" from="${labelerInstance.constraints.typeInput.inList}" required="" value="${labelerInstance?.typeInput}" valueMessagePrefix="labeler.typeInput"/>
</div>

<%--
<div class="fieldcontain ${hasErrors(bean: labelerInstance, field: 'unlabeledArffFilename', 'error')} ">
	<label for="unlabeledArffFilename">
		<g:message code="labeler.unlabeledArffFilename.label" default="Unlabeled Arff Filename" />
		
	</label>
	<g:textField name="unlabeledArffFilename" value="${labelerInstance?.unlabeledArffFilename}"/>
</div>
--%>

<div class="fieldcontain ${hasErrors(bean: labelerInstance, field: 'unlabeledArffFilename', 'error')}">
	<label for="unlabeledArffFilename">
		<g:message code="labeler.unlabeledArffFilename.label" default="Unlabeled Arff Filename" />
	</label>
	<input type="file" id="unlabeledArffFilename" name="unlabeledArffFilename" />
</div>


<div class="fieldcontain ${hasErrors(bean: labelerInstance, field: 'unlabeledTextFieldFilename', 'error')} ">
	<label for="unlabeledTextFieldFilename">
		<g:message code="labeler.unlabeledTextFieldFilename.label" default="Unlabeled Text Field Filename" />
		
	</label>
	<g:textArea name="unlabeledTextFieldFilename" cols="40" rows="5" maxlength="10000" value="${labelerInstance?.unlabeledTextFieldFilename}"/>
</div>

<%--
<div class="fieldcontain ${hasErrors(bean: labelerInstance, field: 'labeledArffFilename', 'error')} ">
	<label for="labeledArffFilename">
		<g:message code="labeler.labeledArffFilename.label" default="Labeled Arff Filename" />
		
	</label>
	<g:textField name="labeledArffFilename" value="${labelerInstance?.labeledArffFilename}"/>
</div>
--%>

<div class="fieldcontain ${hasErrors(bean: labelerInstance, field: 'labeledArffFilename', 'error')} ">
	<g:hiddenField name="labeledArffFilename" value="${labelerInstance?.labeledArffFilename}" />
</div>

<div class="fieldcontain ${hasErrors(bean: labelerInstance, field: 'classifier', 'error')} required">
	<label for="classifier">
		<g:message code="labeler.classifier.label" default="Classifier" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="classifier" name="classifier.id" from="${wekaPlusGrails.Classifier.list()}" optionKey="id" required="" value="${labelerInstance?.classifier?.id}" class="many-to-one"/>
</div>

