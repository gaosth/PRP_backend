import React from 'react';
import './UserInformation.css';

const UserInformation = ({tcId, email, password, phoneNumber, address, editClicked, deleteClicked}) => (
        <tr className="UserInformation">
                <td>{tcId}</td>
                <td>{email}</td>
                <td>{password}</td>
                <td>{phoneNumber}</td>
                <td>{address}</td>
                <button onClick={editClicked} className="buttonStyle">Edit</button>
                <button onClick={deleteClicked} className="buttonStyle">Delete</button>
        </tr>
    );


export default UserInformation;