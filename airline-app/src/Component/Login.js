import { useNavigate } from 'react-router-dom';
import { Form, Grid, Header, Segment } from 'semantic-ui-react'
import { useState } from 'react';
import axios from 'axios';

function Login({setCustomerlg, setCustomerInfo}) {
    const navigate = useNavigate();
    const [user, setUser] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [errorUsr, setErrorUsr] = useState(null);
    const [errorPass, setErrorPass] = useState(null);

    const url = process.env.REACT_APP_API_URL;

    const handleCustomer = async (e) => {
        e.preventDefault();


        if(user === "" && password === ""){
            setErrorUsr({ content: 'Please enter a user', pointing: 'below' });
            setErrorPass("Please enter a password");
            return;
        } else if (user !== "" && password === ""){
            setErrorPass("Please enter a password");
            return;
        } else if (user === "" && password !== ""){
            setErrorUsr({ content: 'Please enter a user', pointing: 'below' });
            return;
        }

        setLoading(true);
        const customerLogin = await axios.get(`${url}/api/login/customer`, {
            params: {
                USERNAME: user,
            }
        });


        if(customerLogin.status === 200){
            setCustomerlg(true);
            setLoading(false);
            setCustomerInfo(customerLogin.data);
            navigate('/flights/customer');
        } else if (customerLogin.status === 204){
            setLoading(false);
            setErrorUsr({ content: 'User not found, create account', pointing: 'below' });
            setTimeout(() => {
                navigate('/account/create');
            }, 500);
            return;
        } else {
            setLoading(false);
            setErrorUsr({ content: 'Invalid User', pointing: 'below' });
            return;
        }

        
        
    }

    

    return (
        <div>
            <Grid textAlign='center' style={{ height: '100vh' }} verticalAlign='middle'>
                <Grid.Column style={{ maxWidth: 450 }}>
                <Header as='h2' color='black' textAlign='center'>
                    Log-in to your account
                </Header>
                <Form size='large' onSubmit={handleCustomer}>
                    <Segment stacked>
                        <Form.Input error={errorUsr} fluid icon='user' iconPosition='left' placeholder='User name' onChange={e =>{ setUser(e.target.value); setErrorUsr(null)}} />
                        <Form.Input error={errorPass} fluid icon='lock' iconPosition='left' placeholder='Password' type='password' onChange={e =>{ setPassword(e.target.value); setErrorPass(null)}} />
                        <Form.Button loading={loading} color='black' fluid size='large' type='submit'>Login</Form.Button>
                    </Segment>
                </Form>
                </Grid.Column>
            </Grid>
        </div>
    )

}

export default Login;