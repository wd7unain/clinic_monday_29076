import React, {useState} from "react";
import axios from "axios";
import ClinicButton from "./ClinicButton.jsx"
export default function (){
    const [officeNumber,setOfficeNumber]= useState("")
    const [office,setOffice]= useState(null)
    async function searchOffice(){

        try {
            const response = await axios.get(
                "/api/offices/byNumber",
                {
                    params:{
                        "officeNumber":officeNumber
                    }
                }
            );
                setOffice(response.data)
        }catch (error){
            alert(error)
            setOffice(null)
        }
    }
    return(
        <div>
            <label>Search an office : </label><input type="number" onChange={e =>setOfficeNumber(e.target.value)} placeholder="enter the office number"/><br/>
            <ClinicButton buttonLable="search" action={searchOffice}/>
            <hr />

            <h3>Search Result:</h3>

            {office ? (
                <div>
                    <p>ID: {office.id}</p>
                    <p>Office Number: {office.officeNumber}</p>
                    <p>Floor: {office.floor}</p>
                </div>
            ) : (
                <p>null</p>
            )}
        </div>
    )
}