import React, {useState} from "react";
import axios from "axios";
import ClinicButton from "./ClinicButton.jsx"
export default function save(){
    const [officeNumber,setOfficeNumber]= useState("")
    const [floor,setFloor]= useState("")
    async function save(){
        const office ={
            officeNumber,
            floor
        }
        try {
            const response= await axios.post(
                "/api/offices/save",
                office
            );

                alert(response.data)
        }catch (error){

                alert(error)
        }
    }
    return(
        <div>
            <label>office Number : </label><input type="number" onChange={e =>setOfficeNumber(e.target.value)} placeholder="enter the office number"/><br/>
            <label>Search floor : </label><input type="number" onChange={e =>setFloor(e.target.value)} placeholder="enter the office floor"/><br/>
            <ClinicButton buttonLable="save" action={save}/>
        </div>
    )
}