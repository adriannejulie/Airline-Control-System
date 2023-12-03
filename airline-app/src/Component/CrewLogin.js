import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Form, Grid, Header, Segment} from 'semantic-ui-react'
import axios from 'axios';


function CrewLogin({setStafflg, setStaffLgInfo}) {
    const navigate = useNavigate();
    const [user, setUser] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);
    const [errorUsr, setErrorUsr] = useState(null);
    const [errorPass, setErrorPass] = useState(null);

    const url = process.env.REACT_APP_API_URL;


    const handleCrew = async (e) => {
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
        const staffLogin = await axios.get(`${url}/api/login/crew`, {
            params: {
                USERNAME: user,
            }
        });


        if(staffLogin.status === 200){
            setStafflg(true);
            setLoading(false);
            setStaffLgInfo(staffLogin.data);
            navigate('/staff');
        } else {
            setLoading(false);
            setErrorUsr({ content: 'Invalid User', pointing: 'below' });
            setErrorPass("Invalid Password");
            return;
        }

    }

    return (
        <div>
            <Grid textAlign='center' style={{ height: '100vh' }} verticalAlign='middle'>
                <Grid.Column style={{ maxWidth: 500 }}>
                    <Header as='h2' color='black' textAlign='center'>
                        Crew Login
                    </Header>
                    <Form size='large' onSubmit={handleCrew}>
                        <Segment stacked >
                            <Form.Input error={errorUsr} fluid icon='user' iconPosition='left' placeholder='Employee ID' onChange={e =>{ setUser(e.target.value); setErrorUsr(null)}} />
                            <Form.Input error={errorPass} fluid icon='lock' iconPosition='left' placeholder='Password' type='password' onChange={e =>{ setPassword(e.target.value); setErrorPass(null)}} />
                            <Form.Button loading={loading} color='black' fluid size='large' type='submit'>Login</Form.Button>
                        </Segment>
                    </Form>
                </Grid.Column>
            </Grid>
        </div>
    )
}

export default CrewLogin;
