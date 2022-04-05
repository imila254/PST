<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
    <p>Add new Preke:</p>
    <%--@elvariable id="saskaita" type="com.pst.PST1.model.Saskaita"--%>
    <form:form method="post" modelAttribute="saskaita">
        <form:label path="id">Id</form:label>
        <form:input path="id" type="text" required="required" />
        <form:errors path="id" />

        <form:label path="telNrId">Tel.Nr. ID</form:label>
        <form:input path="telNrId" type="text" required="required" />
        <form:errors path="telNrId" />

        <form:label path="menuo">Menuo</form:label>
        <form:input path="menuo" type="text" required="required" />
        <form:errors path="menuo" />

        <form:label path="suma">Suma</form:label>
        <form:input path="suma" type="text" required="required" />
        <form:errors path="suma" />

        <button type="submit">OK</button>
    </form:form>
</div>