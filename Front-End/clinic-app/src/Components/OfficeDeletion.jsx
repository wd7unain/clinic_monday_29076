import React, {useEffect, useState} from "react";
import ClinicButton from "./ClinicButton.jsx"
import axios from "axios";
export default function officeDeletion(){
    const[offices,setOffices]=useState([])
    const[message,setMessage]=useState("")

    async function loadOffices() {
        try {
            const response = await axios.get("http://localhost:8080/api/offices");
            setOffices(response.data);
        } catch (error) {
            console.error(error);
            setMessage("Failed to load offices");
        }
    }

    useEffect(() => {
        loadOffices();
    }, []);

    async function deleteOffice(id) {
        if (!window.confirm("Delete this office?")) return;
        try {
            const response = await axios.delete(`http://localhost:8080/api/offices/${id}`);
            setMessage(response.data);
            loadOffices();
        } catch (error) {
            console.error(error);
            setMessage("Delete failed (the office may be linked to other records)");
        }
    }
    return(
        <div>
            <h3>All Offices</h3>

            {message && <p>{message}</p>}

            <table border="1" cellPadding="8">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Office Number</th>
                    <th>Floor</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                {offices.length === 0 ? (
                    <tr>
                        <td colSpan="4">No offices found</td>
                    </tr>
                ) : (
                    offices.map(office => (
                        <tr key={office.id}>
                            <td>{office.id}</td>
                            <td>{office.officeNumber}</td>
                            <td>{office.floor}</td>
                            <td>
                                <ClinicButton
                                    buttonLable="delete"
                                    action={() => deleteOffice(office.id)}
                                />
                            </td>
                        </tr>
                    ))
                )}
                </tbody>
            </table>
        </div>
)

}