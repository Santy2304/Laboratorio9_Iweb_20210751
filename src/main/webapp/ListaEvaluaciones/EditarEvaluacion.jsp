<%--
  Created by IntelliJ IDEA.
  User: Santiago
  Date: 24/11/2023
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<jsp:useBean id="evaluacion" type="com.example.laboratorio9_20210751.Beans.Evaluaciones" scope="request"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Evaluación</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

</head>
<body>
<div class='container'>

    <div class="row mb-4">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <h1 class='mb-3'>Editar Evaluación</h1>
            <hr>
            <form method="POST" action="ListaEvaluacionesServlet">
                <input type="hidden" name="idevaluacion" value="<%= evaluacion.getIdEvaluaciones()%>"/>
                <div class="mb-3">
                    <label class="form-label" for="first_name">First Name</label>
                    <input type="text" class="form-control form-control-sm" id="first_name" name="first_name"
                           value="<%= empleado.getFirstName() == null ? "" : empleado.getFirstName()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="last_name">Last Name</label>
                    <input type="text" class="form-control form-control-sm" id="last_name" name="last_name"
                           value="<%= empleado.getLastName() == null ? "" : empleado.getLastName()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="email">Email</label>
                    <input type="text" class="form-control form-control-sm" id="email" name="email"
                           value="<%= empleado.getEmail() == null ? "" : empleado.getEmail()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="phone">Phone number</label>
                    <input type="text" class="form-control form-control-sm" id="phone" name="phone"
                           value="<%= empleado.getPhoneNumber() == null ? "" : empleado.getPhoneNumber()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="hire_date">Hire date</label>
                    <input type="text" class="form-control form-control-sm" id="hire_date" name="hire_date"
                           value="<%= empleado.getHireDate() == null ? "" : empleado.getHireDate()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="job_id">Job ID</label>
                    <select name="job_id" id="job_id" class="form-select">
                        <% for (Job job : listaTrabajos) {%>
                        <option value="<%=job.getJobId()%>" <%= empleado.getJob().getJobId().equals(job.getJobId()) ? "selected" : "" %>>
                            <%=job.getJobTitle()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="salary">Salary</label>
                    <input type="text" class="form-control form-control-sm" id="salary" name="salary"
                           value="<%= empleado.getSalary().equals("0.0") ? "" : empleado.getSalary()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="commission">Commision PCT</label>
                    <input type="text" class="form-control form-control-sm" id="commission" name="commission"
                           value="<%= empleado.getCommissionPct() == null ? "" : empleado.getCommissionPct()%>">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="manager_id">Manager</label>
                    <select name="manager_id" id="manager_id" class="form-select">
                        <option value="sin-jefe">--Sin jefe--</option>
                        <% for (Employee employee : listaJefes) {%>
                        <option value="<%=employee.getEmployeeId()%>" <%= empleado.getManager().getEmployeeId() == employee.getEmployeeId() ? "selected" : "" %>>
                            <%=employee.getFirstName()%> <%=employee.getLastName()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                <div class="mb-3">
                    <label class="form-label" for="department_id">Department</label>
                    <select name="department_id" id="department_id" class="form-select">
                        <% for (Department department : listaDepartamentos) {%>
                        <option value="<%=department.getDepartmentId()%>" <%= empleado.getDepartment().getDepartmentId() == department.getDepartmentId() ? "selected" : "" %>>
                            <%=department.getDepartmentName()%>
                        </option>
                        <% }%>
                    </select>
                </div>
                <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
                <input type="submit" value="Guardar" class="btn btn-primary"/>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>

</body>
</html>
