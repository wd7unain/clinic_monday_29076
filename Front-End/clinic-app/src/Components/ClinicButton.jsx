import React from "react";
export default function clinicButton({buttonLable, action}){
    return(
        <div>
            <button onClick={action}>{buttonLable}</button>
        </div>
    )
}