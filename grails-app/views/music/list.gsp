
<%@ page import="org.example.Music" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'music.label', default: 'Music')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-music" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-music" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'music.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="jacket" title="${message(code: 'music.jacket.label', default: 'Jacket')}" />
					
						<g:sortableColumn property="bpm" title="${message(code: 'music.bpm.label', default: 'Bpm')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${musicInstanceList}" status="i" var="musicInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${musicInstance.id}">${fieldValue(bean: musicInstance, field: "title")}</g:link></td>
				<td><g:if test="${musicInstance.jacket}">
<img src="<g:createLink controller='music' action='image' id='${musicInstance.id}' />" />
</g:if></td>	
						<td>${fieldValue(bean: musicInstance, field: "bpm")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${musicInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
