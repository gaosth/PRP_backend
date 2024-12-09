import axios from 'axios';
import React, { Component } from 'react';
import EditUser from '../../components/EditUser/EditUser';
import NewUser from '../../components/NewUser/NewUser';
import UserInformation from '../../components/UserInformation/UserInformation';
import './User.css';

class User extends Component {
    state = {
        userInformations: [],
        editable: false,
        userId: null,
    }

    componentDidMount() {
        axios.get('/users/')
            .then(response => {
                this.setState({ userInformations: response.data });
            })
            .catch(error => {
                console.log(error);
            });
    }

    handleUserAdded = (newUser) => {
        this.setState(prevState => ({
            userInformations: [...prevState.userInformations, newUser]
        }));
    }

    handleUserUpdated = (updatedUser) => {
        this.setState(prevState => ({
            userInformations: prevState.userInformations.map(user =>
                user.id === updatedUser.id ? updatedUser : user
            )
        }));
    }

    editClickedHandler = (id) => {
        this.setState({ editable: true, userId: id });
    }

    deleteClickedHandler = (id) => {
        axios.delete('/users/' + id)
            .then(response => {
                this.setState(prevState => ({
                    userInformations: prevState.userInformations.filter(user => user.id !== id)
                }));
            })
            .catch(error => {
                console.log(error);
            });
    }

    render() {
        const users = this.state.userInformations.map(({ id, tckn, password, gsm, email, address }) => (
            <UserInformation
                key={id}
                tcId={tckn}
                password={password}
                phoneNumber={gsm}
                email={email}
                address={address}
                editClicked={() => this.editClickedHandler(id)}
                deleteClicked={() => this.deleteClickedHandler(id)}
            />
        ));

        return (
            <div>
                <NewUser
                    text="Add New User"
                    buttonText="Add"
                    onUserAdded={this.handleUserAdded}
                />
                <div className="User">
                    <table>
                        <thead>
                            <tr className="header">
                                <th>Tc Kimlik</th>
                                <th>E-mail</th>
                                <th>Password</th>
                                <th>Phone Number</th>
                                <th>Address</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users}
                        </tbody>
                    </table>
                </div>
                {this.state.editable ? (
                    <EditUser
                        text="Edit Selected User"
                        buttonText="Save"
                        userId={this.state.userId}
                        onUserUpdated={this.handleUserUpdated}
                    />
                ) : null}
            </div>
        );
    }
}

export default User;