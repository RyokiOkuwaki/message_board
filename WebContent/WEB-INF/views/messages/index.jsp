<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- import を使うことで、url属性に指定したファイル内容をその位置で読み込むことができる。 -->
<c:import url="../layout/app.jsp">
    <!-- param name = 'content' と書いたタグの中の記述内容が、　app.jsp　の＄｛param.content｝ のところに入る。-->
    <c:param name="content">
        <h2>メッセージ一覧</h2>
        <ul>
            <c:forEach var="message" items="${messages}">
                <li>
                    <a href="${pageContext.request.contextPath}/show?id=${message.id}">
                        <c:out value="${message.id}" />
                    </a>
                    ：<c:out value="${message.title}"></c:out> &gt; <c:out value="${message.content}" />
                </li>
            </c:forEach>
        </ul>

        <p><a href="${pageContext.request.contextPath}/new">新規メッセージの投稿</a></p>

    </c:param>
</c:import>