import { useState } from 'react';
import { Button, Form, Container, Message } from 'semantic-ui-react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Account() {
    const [checked, setChecked] = useState(false);
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [phone, setPhone] = useState("");
    const [user, setUser] = useState("");
    const [password, setPassword] = useState("");
    const [membershipNumber, setMembershipNumber] = useState(0);

    const [successMessage, setSuccessMessage] = useState("");
    const [errorMessage, setErrorMessage] = useState("");

    const navigate = useNavigate();

    const url = process.env.REACT_APP_API_URL;

    const handleCheckboxChange = (e, { checked }) => {
        setChecked(checked);
        if (checked) {
            setMembershipNumber(Math.floor(Math.random() * 1000000000)); // Generate membership number
        } else {
            setMembershipNumber(0); // Reset to 0 if unchecked
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault(); // Prevent the default form submit action
        const formData = {
            NAME: firstName + " " + lastName,
            EMAIL: email,
            PHONE_NUMBER: phone,
            USERNAME: user,
            PASSWORD: password,
            MEMBERSHIP_ID: membershipNumber
        };

        console.log(formData); // Log the form data to the console for debugging purposes
        try {
            const response = await axios.post(`${url}/api/customer/signup`, formData);
            console.log(response);
            if (response.status === 201) {
                setSuccessMessage("User created successfully!");
                setErrorMessage("");
                setTimeout(() => {
                    navigate('/login');
                }, 1000);
            } else if (response.status === 208) {
                setErrorMessage("User already exists. Please login instead.");
                setSuccessMessage("");
                setTimeout(() => {
                    navigate('/login');
                }, 1000);
            } else {
                throw new Error('Non-200 response');
            }
        } catch (error) {
            setErrorMessage("Error creating user. Please try again.");
            setSuccessMessage(""); // Clear any previous success message
        }
        
    };

    return (
        <Container style={{ padding: "5em" }}>
            <h1>Please Create an Account</h1>
            {successMessage && <Message success>{successMessage}</Message>}
            {errorMessage && <Message error>{errorMessage}</Message>}
            <Form size="big" onSubmit={handleSubmit}>
                <Form.Group unstackable widths={2}>
                    <Form.Input label='First name' placeholder='First name' value={firstName} onChange={(e) => setFirstName(e.target.value)} />
                    <Form.Input label='Last name' placeholder='Last name' value={lastName} onChange={(e) => setLastName(e.target.value)} />
                </Form.Group>
                <Form.Group widths={2}>
                    <Form.Input label='Email' placeholder='Email' value={email} onChange={(e) => setEmail(e.target.value)} />
                    <Form.Input label='Phone' placeholder='Phone' value={phone} onChange={(e) => setPhone(e.target.value)} />
                </Form.Group>
                <Form.Group widths={2}>
                    <Form.Input label='User' placeholder='User name' value={user} onChange={(e) => setUser(e.target.value)} />
                    <Form.Input label='Password' placeholder='Password' type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
                </Form.Group>
                <Form.Checkbox onChange={handleCheckboxChange} label='Add membership' />
                {checked && <p>Membership number: {membershipNumber}</p>}
                <Button type='submit'>Submit</Button>
            </Form>
        </Container>
    );
}

export default Account;
