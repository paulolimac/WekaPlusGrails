<%@ page import="wekaPlusGrails.TrainSample" %>



<div class="fieldcontain ${hasErrors(bean: trainSampleInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="trainSample.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${trainSampleInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: trainSampleInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="trainSample.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="500" required="" value="${trainSampleInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: trainSampleInstance, field: 'arffFile', 'error')} required">
	<label for="arffFile">
		<g:message code="trainSample.arffFile.label" default="Arff File" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="arffFile" name="arffFile" />
</div>


