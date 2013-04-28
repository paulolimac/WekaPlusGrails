<%@ page import="wekaPlusGrails.Classifier" %>



<div class="fieldcontain ${hasErrors(bean: classifierInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="classifier.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${classifierInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: classifierInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="classifier.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="description" cols="40" rows="5" maxlength="500" required="" value="${classifierInstance?.description}"/>
</div>

<%--
<div class="fieldcontain ${hasErrors(bean: classifierInstance, field: 'filename', 'error')} ">
	<label for="filename">
		<g:message code="classifier.filename.label" default="Filename" />
		
	</label>
	<g:textField name="filename" value="${classifierInstance?.filename}"/>
</div>
--%>

<div class="fieldcontain ${hasErrors(bean: classifierInstance, field: 'filename', 'error')} ">
	<g:hiddenField name="filename" value="${classifierInstance?.filename}" />
</div>

<%--
<div class="fieldcontain ${hasErrors(bean: classifierInstance, field: 'modelTxt', 'error')} ">
	<label for="modelTxt">
		<g:message code="classifier.modelTxt.label" default="Model Txt" />
		
	</label>
	<g:textField name="modelTxt" value="${classifierInstance?.modelTxt}"/>
</div>
--%>

<div class="fieldcontain ${hasErrors(bean: classifierInstance, field: 'modelTxt', 'error')} ">
	<g:hiddenField name="modelTxt" value="${classifierInstance?.modelTxt}" />
</div>


<div class="fieldcontain ${hasErrors(bean: classifierInstance, field: 'typeClassifier', 'error')} required">
	<label for="typeClassifier">
		<g:message code="classifier.typeClassifier.label" default="Type Classifier" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="typeClassifier" from="${classifierInstance.constraints.typeClassifier.inList}" required="" value="${classifierInstance?.typeClassifier}" valueMessagePrefix="classifier.typeClassifier"/>
</div>

<div class="fieldcontain ${hasErrors(bean: classifierInstance, field: 'trainSample', 'error')} required">
	<label for="trainSample">
		<g:message code="classifier.trainSample.label" default="Train Sample" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="trainSample" name="trainSample.id" from="${wekaPlusGrails.TrainSample.list()}" optionKey="id" required="" value="${classifierInstance?.trainSample?.id}" class="many-to-one"/>
</div>

