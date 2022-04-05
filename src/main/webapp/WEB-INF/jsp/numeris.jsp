<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
    <p>Add new Preke:</p>
    <%--@elvariable id="numeris" type="com.pst.PST1.model.TelNr"--%>
    <form:form method="post" modelAttribute="numeris">
        <form:label path="id">Id</form:label>
        <form:input path="id" type="text" required="required" />
        <form:errors path="id" />

        <form:label path="telNr">Telefono numeris</form:label>
        <form:input path="telNr" type="text" required="required" />
        <form:errors path="telNr" />

        <form:label path="userId">Naudotojo ID</form:label>
        <form:input path="userId" type="text" required="required" />
        <form:errors path="userId" />

        <button type="submit">OK</button>
    </form:form>
</div>