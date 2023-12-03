import React from "react";
import { useNavigate } from 'react-router-dom';


import {
    Button,
    Container,
    Header,
    Icon,
    Menu,
    Segment,
} from 'semantic-ui-react'

function Home() {
    const navigate = useNavigate();

    const HomepageHeading = () => (
        <Container text inverted>
          <Header as='h1' content='Air React' inverted style={{fontSize: '4em', fontWeight: 'normal', marginBottom: 0, marginTop: '2em',}} />
          <Header as='h2' content='Fly to your dreams' inverted style={{fontSize: '1.7em', fontWeight: 'normal', marginTop: '1.5em',}}/>
          <Button primary size='huge' onClick={() => navigate('/account/create')}>
            Create an Account
            <Icon name='right arrow' />
          </Button>
        </Container>
    )

    return (
        <div>
            <Segment inverted textAlign='center' style={{ minHeight: 700,  }} vertical>
                <Menu size='massive' borderless style={{ margin: 35 }}>
                    <Menu.Item as='a' active style={{marginLeft: 35}}>Home</Menu.Item>
                    <Menu.Item position='right' >
                    <Button as='a' onClick={() => navigate("/login")}>
                        Login
                    </Button>
                    <Button as='a' onClick={() => navigate("crew/login")} style={{ marginLeft: '0.5em', marginRight: 35}}>
                        Staff
                    </Button>
                    </Menu.Item>
                </Menu>
                <HomepageHeading />
            </Segment>
        </div>
    );
}

export default Home;