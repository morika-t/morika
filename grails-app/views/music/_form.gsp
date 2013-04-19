<%@ page import="org.example.Music" %>



<div class="fieldcontain ${hasErrors(bean: musicInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="music.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${musicInstance?.title}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: musicInstance, field: 'jacket', 'error')} required">
	<label for="jacket">
		<g:message code="music.jacket.label" default="Jacket" />
		<span class="required-indicator">*</span>
	</label>
	<input type="file" id="jacket" name="jacket" />
</div>

<div class="fieldcontain ${hasErrors(bean: musicInstance, field: 'bpm', 'error')} required">
	<label for="bpm">
		<g:message code="music.bpm.label" default="Bpm" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="bpm" type="number" value="${musicInstance.bpm}" required=""/>
</div>

