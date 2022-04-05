<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp"%>
<div class="container">
    <H1>List of Saskaitos:</H1>

    <table border="1">
        <caption>Saskaitos</caption>
        <thead>
        <tr>
            <th>Id</th><th>TelNrId</th><th>Menuo</th><th>Suma</th><th>TelNr</th></th></th><th>Update</th><th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${saskaitos}" var="saskaita">
            <tr>
                <td>${saskaita.id}</td>
                <td>${saskaita.telNrId}</td>
                <td>${saskaita.menuo}</td>
                <td>${saskaita.suma}</td>
                <td>${saskaita.telNr}</td>
                <td><a type="button" href="/update-saskaita/${saskaita.id}">UPDATE</a></td>
                <td><a type="button" href="/delete-saskaita/${saskaita.id}">DELETE</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div>
        <p>
            <a class="button" href="add-saskaita">ADD SASKAITA</a>
        </p>
    </div>

</div>