<%@ include file="common/header.jsp"%>
<%@ include file="common/navigation.jsp"%>
<div class="container">
    <H1>List of Numeriai:</H1>

    <table border="1">
        <caption>Numeriai</caption>
        <thead>
            <tr>
                <th>Id</th><th>TelNr</th><th>UserId</th><th>Update</th><th>Delete</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${numeriai}" var="numeris">
            <tr>
                <td>${numeris.id}</td>
                <td>${numeris.telNr}</td>
                <td>${numeris.userId}</td>
                <td><a type="button" href="/update-numeris/${numeris.id}">UPDATE</a></td>
                <td><a type="button" href="/delete-numeris/${numeris.id}">DELETE</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div>
        <p>
            <a class="button" href="add-numeris">ADD NUMERIS</a>
        </p>
    </div>

</div>