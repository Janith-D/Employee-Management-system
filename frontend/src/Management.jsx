import React, { useState, useEffect } from "react";
import axios from "axios";

function EmployeeManagement() {
    const [employees, setEmployees] = useState([]);
    const [employeeData, setEmployeeData] = useState({
        empId: "",
        empName: "",
        empAddress: "",
        empNumber: "",
        departmentId: "",
        teamId: ""
    });
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        getAllEmployees();
    }, []);

    const getAllEmployees = async () => {
        setIsLoading(true);
        try {
            const response = await axios.get("/api/v1/empcontrol/getAllEmployees");
            console.log({response})
            if (response.data.code === "00") {
                console.log('hi')
                setEmployees(response.data.content || []);
            } else {
                alert("Failed to fetch Employees");
            }
        } catch (error) {
            console.error("Error fetching employees:", error);
            setError("Error fetching employees");
        } finally {
            setIsLoading(false);
        }
    };

    // Define the clearForm function to reset form fields
    const clearForm = () => {
        setEmployeeData({
            empId: "",
            empName: "",
            empAddress: "",
            empNumber: "",
            departmentId: "",
            teamId: ""
        });
    };

    const saveEmployee = async () => {
        try {
            await axios.post("/api/v1/empcontrol/saveemployee", employeeData);
            alert("Employee saved successfully!");
            getAllEmployees();
            clearForm();  // Call clearForm to reset the form fields
        } catch (error) {
            handleError(error, "Could not save employee.");
        }
    };

    const updateEmployee = async () => {
        try {
            await axios.put("/api/v1/empcontrol/updateemployee", employeeData);
            alert("Employee updated successfully");
            getAllEmployees();
            clearForm();  // Call clearForm to reset the form fields
        } catch (error) {
            handleError(error, "Could not update employee.");
        }
    };

    const handleError = (error, message) => {
        if (error.response) {
            console.error("Server error response:", error.response.data);
            alert(`Error: ${error.response.data.message || message}`);
        } else {
            console.error(message, error);
            alert(message);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEmployeeData((prevState) => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleEdit = (employee) => {
        setEmployeeData(employee);
    };

    return (
        <div>
            <h2>Employee Management System</h2>

            {isLoading && <p>Loading...</p>}
            {error && <p style={{ color: "red" }}>{error}</p>}

            <form onSubmit={(e) => e.preventDefault()} method="POST">
                <input
                    type="text"
                    name="empName"
                    placeholder="Name"
                    value={employeeData.empName || ""}
                    onChange={handleInputChange}
                    required
                />
                <input
                    type="text"
                    name="empAddress"
                    placeholder="Address"
                    value={employeeData.empAddress || ""}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="empNumber"
                    placeholder="Number"
                    value={employeeData.empNumber || ""}
                    onChange={handleInputChange}
                    required
                />
                <input
                    type="text"
                    name="departmentId"
                    placeholder="Department ID"
                    value={employeeData.departmentId || ""}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="teamId"
                    placeholder="Team ID"
                    value={employeeData.teamId || ""}
                    onChange={handleInputChange}
                />

                <button onClick={saveEmployee}>Save</button>
                <button onClick={updateEmployee}>Update</button>
            </form>

            <table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Number</th>
            <th>Department ID</th>
            <th>Team ID</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        {Array.isArray(employees) && employees.length > 0 ? (
            employees.map((emp) => (
                <tr key={emp.empId}>
                    <td>{emp.empId}</td>
                    <td>{emp.empName || "Not Available"}</td>
                    <td>{emp.empAddress || "Not Available"}</td>
                    <td>{emp.empNumber || "Not Available"}</td>
                    <td>{emp.departmentId !== null ? emp.departmentId : "Not Available"}</td>
                    <td>{emp.teamId !== null ? emp.teamId : "Not Available"}</td>
                    <td>
                        <button onClick={() => handleEdit(emp)}>Edit</button>
                        <button onClick={() => deleteEmployee(emp.empId)}>Delete</button>
                    </td>
                </tr>
            ))
        ) : (
            <tr>
                <td colSpan="7">No Employees found</td>
            </tr>
        )}
    </tbody>
</table>

        </div>
    );
    

}



export default EmployeeManagement;
