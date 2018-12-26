<%@ page isELIgnored="false" %>
<c:if test="${not empty og.url}">
<meta property="og:url"           content="//www.findmylingo.local/${og.url}" />
</c:if>
<c:if test="${not empty og.type}">
<meta property="og:type"          content="${og.type}" />
</c:if>
<c:if test="${not empty og.title}">
<meta property="og:title"         content="${og.title}" />
</c:if>
<c:if test="${not empty og.description}">
<meta property="og:description"   content="${og.description}" />
</c:if>
<c:if test="${not empty og.image}">
<meta property="og:image"         content="//www.findmylingo.local/${og.image}" />
</c:if>
