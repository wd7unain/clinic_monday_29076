import React from "react";

import OfficePage from "./Components/OfficePage.jsx";
import SaveOffice from "./Components/SaveOffice.jsx"
import Doctors from "./Page/Doctors.jsx";
import Patients from "./Page/Patients.jsx";
import Appointments from "./Page/Appointments.jsx";
import OfficeDeletion from "./Components/OfficeDeletion.jsx"
import './App.css'
import {BrowserRouter, Route, Routes} from "react-router";

function App() {

  return (
   <div>
     <SaveOffice/>
     <OfficePage/>
     <OfficeDeletion/>
    {/*<BrowserRouter>*/}
    {/*    <Routes>*/}
    {/*        <Route path="/docts" element={<Doctors/>}/>*/}
    {/*        <Route path="/patients" element={<Patients/>} />*/}
    {/*         <Route path="/" element={<Appointments/>}/>*/}
    {/*    </Routes>*/}
    {/*</BrowserRouter>*/}
   </div>
  )
}

export default App
