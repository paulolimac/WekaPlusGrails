<%@ page import="wekaPlusGrails.TestSample" %>




<div class="fieldcontain ${hasErrors(bean: testSampleInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="testSample.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${testSampleInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: testSampleInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="testSample.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="500" required="" value="${testSampleInstance?.description}"/>
</div>

<%--
<div class="fieldcontain ${hasErrors(bean: testSampleInstance, field: 'filename', 'error')} ">
	<label for="filename">
		<g:message code="testSample.filename.label" default="Filename" />
		
	</label>
	<g:textField name="filename" value="${testSampleInstance?.filename}"/>
</div>
--%>

<div class="fieldcontain ${hasErrors(bean: testSampleInstance, field: 'filename', 'error')} required">
	<label for="filename">
		<g:message code="testSample.filename.label" default="Filename" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="filename" name="filename" value="${testSampleInstance?.filename}"/>
</div>

<%--
<div class="fieldcontain ${hasErrors(bean: testSampleInstance, field: 'evaluationTxt', 'error')} ">
	<label for="evaluationTxt">
		<g:message code="testSample.evaluationTxt.label" default="Evaluation Txt" />
		
	</label>
	<g:textField name="evaluationTxt" value="${testSampleInstance?.evaluationTxt}"/>
</div>
--%>

<div class="fieldcontain ${hasErrors(bean: testSampleInstance, field: 'evaluationTxt', 'error')} ">
	<g:hiddenField name="evaluationTxt" value="${testSampleInstance?.evaluationTxt}" />
</div>

<div class="fieldcontain ${hasErrors(bean: testSampleInstance, field: 'classifier', 'error')} required">
	<label for="classifier">
		<g:message code="testSample.classifier.label" default="Classifier" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="classifier" name="classifier.id" from="${ekzemploDeWekaEnGrails.Classifier.list()}" optionKey="id" required="" value="${testSampleInstance?.classifier?.id}" class="many-to-one"/>
</div>

