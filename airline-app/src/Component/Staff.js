import { Container, Segment, Table, Button, Modal, Header, List, Grid, Icon } from "semantic-ui-react"
import { useState, useEffect } from "react"
import { useNavigate } from "react-router-dom"
import axios from "axios"

function Staff({setStafflg , staffLgInfo}) {
    const [open, setOpen] = useState(false);
    const [selectedFlight, setSelectedFlight] = useState(null);
    const [flightsInfo, setFlightsInfo] = useState([]);
    const [loading, setLoading] = useState(false);

    const url = process.env.REACT_APP_API_URL;

    const airCodes = {
        CYYZ: "Toronto",
        CYVR: "Vancouver",
        CYUL: "Montreal",
        CYYC: "Calgary",
        CYOW: "Ottawa",
        CYTZ: "Toronto City Centre",
        CYEG: "Edmonton",
        CYWG: "Winnipeg",
    }

    useEffect(() => {
        const getCrewSchedule = async () => {
            setLoading(true);
            const flightsResponse = await axios.get(`${url}/api/crew/scheduledFlights`, {
                params: {
                    USERNAME: staffLgInfo.USERNAME
                }
            });

            const flightsWithDetails = await Promise.all(flightsResponse.data.map(async flight => {
                const passengerInfo = await getPasangersInfo(flight.FLIGHT_NUMBER);
                const originCity = airCodes[flight.ORIGIN] || flight.ORIGIN;
                const destinationCity = airCodes[flight.DESTINATION] || flight.DESTINATION;
                return { 
                    ...flight, 
                    ORIGIN: `${flight.ORIGIN} ${originCity}`,
                    DESTINATION: `${flight.DESTINATION} ${destinationCity}`,
                    passangersInfo: passengerInfo 
                };
            }));
            setLoading(false);

            setFlightsInfo(flightsWithDetails);
        };

        const getPasangersInfo = async (flightNumber) => {
            const response = await axios.get(`${url}/api/flightData/passengers`, {
                params: {
                    FLIGHT_NUMBER: flightNumber
                }
            });
            return response.data;
        }

        getCrewSchedule();
    }, [staffLgInfo]);

    const navigate = useNavigate();

    const handleButtonClick = (flight) => {
        console.log(flight);
        setSelectedFlight(flight);
        setOpen(true);
    };

    const logout = () => {
        setStafflg(false);
        navigate('/');
    }

    
                
                

    return (
        <>
            <Segment style={{ padding: '2em' }} vertical>
            <Grid columns={2}>
                    <Grid.Column>
                        <Header as='h2'>Welcome {staffLgInfo.NAME}</Header>
                        <Header as='h3'>Employee Number: {staffLgInfo.USERNAME}</Header>
                    </Grid.Column>
                    <Grid.Column textAlign="right">
                        <Button animated compact size="large" onClick={() => logout()}>
                        <Button.Content visible>Logout</Button.Content>
                        <Button.Content hidden>
                            <Icon name='arrow right' />
                        </Button.Content>
                    </Button>
                    </Grid.Column>
                </Grid>
            </Segment>
            <Container loading={loading}>
                <Segment style={{ padding: '0.5em' }} vertical>
                    <Segment textAlign="left" style={{ padding: '1em' }} vertical>
                        <Header as='h2'>Schedule Information</Header>
                        <Modal size="large" closeIcon open={open} onClose={() => setOpen(false)}>
                            <Header icon='plane' content='Crew Flight Information' />
                            <Modal.Content>
                                {selectedFlight && 
                                    <Grid columns={2} divided>
                                        <Grid.Column>
                                            <Container textAlign="left">
                                                <Grid columns={3}>
                                                    <Grid.Column>
                                                        <Header as='h3'>Destination</Header>
                                                        <p>{selectedFlight.DESTINATION.split(" ").slice(1).join(" ")}</p>
                                                    </Grid.Column>
                                                    <Grid.Column>
                                                        <Header as='h3'>Origin</Header>
                                                        <p>{selectedFlight.ORIGIN.split(" ").slice(1).join(" ")}</p>
                                                    </Grid.Column>
                                                    <Grid.Column>
                                                        <Header as='h3'>Date</Header>
                                                        <p>{selectedFlight.TIME_DEPARTURE.split('T')[0]}</p>
                                                    </Grid.Column>
                                                </Grid>
                                                <Grid columns={3}>
                                                    <Grid.Row>
                                                        <Grid.Column>
                                                            <Header as='h3'>Flight</Header>
                                                            <p>{selectedFlight.FLIGHT_NUMBER}</p>
                                                        </Grid.Column>
                                                        <Grid.Column>
                                                            <Header as='h3'>Airport</Header>
                                                            <p>{selectedFlight.ORIGIN.split(" ")[0]}</p>
                                                        </Grid.Column>
                                                        <Grid.Column>
                                                            <Header as='h3'>Time</Header>
                                                            <p>{selectedFlight.TIME_DEPARTURE.split('T')[1].split(':').slice(0, 2).join(':')}</p>
                                                        </Grid.Column>
                                                    </Grid.Row>
                                                </Grid>
                                            </Container>
                                        </Grid.Column>
                                        <Grid.Column>
                                            <Container textAlign="left">
                                                <Header as='h3'>Passangers Information</Header>
                                                <List horizontal style={{padding: "0.1em"}}>
                                                    {selectedFlight.passangersInfo.map((passenger, index) => (
                                                        <List.Item key={index}>
                                                            <List.Content>
                                                                <List.Header>{passenger.USERNAME}</List.Header>
                                                                <List.Description>{passenger.SEAT_TYPE}</List.Description>
                                                                <List.Description>{passenger.SEAT_NUMBER}</List.Description>
                                                                <List.Description>{passenger.BIRTH}</List.Description>
                                                            </List.Content>
                                                        </List.Item>
                                                    ))}
                                                </List>
                                            </Container>
                                        </Grid.Column>
                                    </Grid>
                                }
                            </Modal.Content>
                        </Modal>
                    </Segment>
                    <Table singleLine>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell>Flight Number</Table.HeaderCell>
                                <Table.HeaderCell>Origin</Table.HeaderCell>
                                <Table.HeaderCell>Destination</Table.HeaderCell>
                                <Table.HeaderCell>Date</Table.HeaderCell>
                                <Table.HeaderCell>Time</Table.HeaderCell>
                                <Table.HeaderCell></Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>

                        <Table.Body>
                            {
                                flightsInfo.map((flight, index) => 
                                    (
                                        <Table.Row key={index} >
                                            <Table.Cell>{flight.FLIGHT_NUMBER}</Table.Cell>
                                            <Table.Cell>{flight.ORIGIN.split(" ").slice(1).join(" ")}</Table.Cell>
                                            <Table.Cell>{flight.DESTINATION.split(" ").slice(1).join(" ")}</Table.Cell>
                                            <Table.Cell>{flight.TIME_DEPARTURE.split('T')[0]}</Table.Cell>
                                            <Table.Cell>{flight.TIME_DEPARTURE.split('T')[1].split(':').slice(0, 2).join(':')}</Table.Cell>
                                            <Table.Cell collapsing>
                                                <Button animated="vertical" floated="left" size='midium' onClick={()=>handleButtonClick(flight)}>
                                                    <Button.Content visible>See details</Button.Content>
                                                    <Button.Content hidden>
                                                        {flight.FLIGHT_NUMBER}
                                                    </Button.Content>
                                                </Button>
                                            </Table.Cell>
                                        </Table.Row>
                                    )
                                )
                            }
                        </Table.Body>
                    </Table>
                </Segment>
            </Container>
        
            
        </>
        
    )
}


export default Staff