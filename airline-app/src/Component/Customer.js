import { Container, Segment, Table, Button, Icon, Modal, Header, Form, Dropdown, List, Grid, Divider, Radio, Message} from "semantic-ui-react"
import React, { useState, useEffect } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom"
import SeatMap from "../utils/SeatMap"

function Customer({customerInfo, setCustomerlg}) {
    const [open, setOpen] = useState(false);
    const [secondOpen, setSecondOpen] = useState(false);
    const [thirdOpen, setThirdOpen] = useState(false);
    const [updateTrigger, setUpdateTrigger] = useState(0);
    const [loading, setLoading] = useState(false);
    const [bookingLoading, setBookingLoading] = useState(false);
    const [message, setMessage] = useState(null);


    const [selectedFlight, setSelectedFlight] = useState(null);

    const [insurance, setInsurance] = useState(null);
    const [voucher, setVoucher] = useState(null);


    const [availableFlights, setAvailableFlights] = useState([]);
    const [bookedFlights, setBookedFlights] = useState([]);

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
        const fetchBookedFlights = async () => {
            try {
                setLoading(true);
                const response = await axios.get(`${url}/api/customer/viewFlights`, {
                    params: {
                        USERNAME: customerInfo.USERNAME
                    }
                });
                if (response.status === 200) {
                    const updatedFlights = response.data.map(flight => ({
                        ...flight,
                        ORIGIN: `${flight.ORIGIN} ${airCodes[flight.ORIGIN] || ''}`,
                        DESTINATION: `${flight.DESTINATION} ${airCodes[flight.DESTINATION] || ''}`
                    }));
                    setBookedFlights(updatedFlights);
                    setLoading(false);
                }
            } catch (error) {
                console.error('Error fetching flight data:', error);
            }
        };

        const fetchAvailableFlights = async () => {
            try {
                setLoading(true);
                const response = await axios.get(`${url}/api/flightData/allData`);
                if (response.status === 200) {
                    const updatedFlights = response.data.map(flight => ({
                        ...flight,
                        ORIGIN: `${flight.ORIGIN} ${airCodes[flight.ORIGIN] || ''}`,
                        DESTINATION: `${flight.DESTINATION} ${airCodes[flight.DESTINATION] || ''}`
                    }));
                    setAvailableFlights(updatedFlights);
                    setLoading(false);
                }
            } catch (error) {
                console.error('Error fetching flight data:', error);
            }
        };

        fetchBookedFlights();
        fetchAvailableFlights();
    }, [updateTrigger]);

    let navigate = useNavigate();    

    const flightOptions = availableFlights.map((flight, index) => ({
        key: index,
        text: `${flight.FLIGHT_NUMBER} - ${flight.ORIGIN.split(" ").slice(1).join(" ")} to ${flight.DESTINATION.split(" ").slice(1).join(" ")} at ${flight.TIME_DEPARTURE.split('T')[1].split(':').slice(0, 2).join(':')}`,
        value: flight.FLIGHT_NUMBER
    }));

    const handleFlightSelect = (e, { value }) => {
        setSelectedFlight(availableFlights.find(flight => flight.FLIGHT_NUMBER === value));
    };

    // Prices for seat types
    const ordinaryPrice = 200;
    const comfortPrice = ordinaryPrice * 1.40; // 40% more than ordinary
    const businessPrice = ordinaryPrice * 1.70; // 70% more than ordinary
    const insurancePrice = 10; // Fixed price for insurance

    // Function to calculate price based on seat type and insurance
    const calculatePrice = () => {
        let totalPrice = 0;
        let cheapestSeatPrice = Number.MAX_VALUE;
    
        passangersInfo.forEach(passenger => {
            let basePrice = 0;
            switch (passenger.SEAT_TYPE) {
                case 'Ordinary':
                    basePrice = ordinaryPrice;
                    break;
                case 'Comfort':
                    basePrice = comfortPrice;
                    break;
                case 'Business':
                    basePrice = businessPrice;
                    break;
                default:
                    basePrice = 0;
            }
    
            if (insurance === '1') {
                basePrice += insurancePrice;
            }
    
            totalPrice += basePrice;
    
            if (basePrice < cheapestSeatPrice) {
                cheapestSeatPrice = basePrice;
            }
        });
    
        // Apply voucher discount only if valid, and there are more than one passengers
        if (voucher === "free" && passangersInfo.length > 1 && cheapestSeatPrice !== Number.MAX_VALUE) {
            totalPrice -= cheapestSeatPrice;
        }
    
        return totalPrice;
    };

    const calculateIndividualPrice = (passenger) => {
        let basePrice = 0;
        switch (passenger.SEAT_TYPE) {
            case 'Ordinary':
                basePrice = ordinaryPrice;
                break;
            case 'Comfort':
                basePrice = comfortPrice;
                break;
            case 'Business':
                basePrice = businessPrice;
                break;
            default:
                basePrice = 0;
        }
    
        // Add insurance price if insurance is selected
        if (insurance === '1') {
            basePrice += insurancePrice;
        }
    
        return basePrice;
    };
    

    const handleBookingConfirmation = async () => {
        if (!selectedFlight) {
            console.error('Please select a flight and seat type.');
            return;
        }

        setBookingLoading(true);
        let bookingSuccessful = true;
    
        for (let passenger of passangersInfo) {
            const bookingData = {
                USERNAME: passenger.USERNAME,
                SEAT_NUMBER: passenger.SEAT_NUMBER,
                SEAT_TYPE: passenger.SEAT_TYPE,
                FLIGHT_NUMBER: selectedFlight.FLIGHT_NUMBER,
                INSURANCE_STATUS: insurance,
                ORIGIN: selectedFlight.ORIGIN.split(" ")[0],
                DESTINATION: selectedFlight.DESTINATION.split(" ")[0],
                TIME_DEPARTURE: selectedFlight.TIME_DEPARTURE,
            };
    
            console.log('Booking data:', bookingData);
    
            try {
                const response = await axios.post(`${url}/api/customer/booking`, bookingData);
                console.log(`Booking confirmed for ${passenger.NAME}:`, response.data);
                if (response.status !== 201) {
                    throw new Error('Non-201 response');
                    bookingSuccessful = false;
                }
            } catch (error) {
                console.error(`Error during booking for ${passenger.NAME}:`, error);
                bookingSuccessful = false;
            }
        }
    
        // Actions to perform after all bookings are processed
        setBookingLoading(false);
        setOpen(false);
        setSelectedFlight(null);
        setUpdateTrigger(oldValue => oldValue + 1);

        // Display success message for 30 seconds
        if (bookingSuccessful) {
            setMessage(true);
            setTimeout(() => {
                setMessage(null);
            }, 5000); // 30 seconds
        }
    };


    const logout = () => {
        setCustomerlg(false);
        navigate('/');
    }

    const [name, setName] = useState("");
    const [pas, setPas] = useState("");
    const [passangersInfo, setPassangersInfo] = useState([customerInfo]);
    const [extra, setExtra] = useState(false);


    const addPassenger = async () => {
        const getPassenger = async () => {
            try {
                const response = await axios.get(`${url}/api/login/customer`, {
                    params: {
                        USERNAME: name,
                    }
                });
                if(response.status === 200) {
                    return response.data;
                } else {
                    throw new Error('Non-200 response');
                }

            } catch (error) {
                console.error('Error fetching passenger data:', error);
            }      
        }

        let passenger = await getPassenger();
        if(!passenger) {
            return;
        } else {
            setPassangersInfo([...passangersInfo, passenger]);
            setName("");
            setPas("");
            setSecondOpen(false);
        }

        //add info to the setCustomerInfo array
    
    }

    const handleDelete = async (flight) => {
        try {
            const response = await axios.delete(`${url}/api/customer/cancel`, {
                params: {
                    USERNAME: customerInfo.USERNAME,
                    FLIGHT_NUMBER: flight.FLIGHT_NUMBER,
                },
            });

            if(response.status === 200) {
                setUpdateTrigger(oldValue => oldValue + 1);
            } else {
                throw new Error('Non-200 response');
            }
            
        } catch (error) {
            console.error('Error deleting flight:', error);
        }
        
    }

    const resetModalState = () => {
        setOpen(false); // Close the modal
        // Reset states to their default values
        setSelectedFlight(null);
        setInsurance(null);
        setVoucher(null);
        setPassangersInfo([customerInfo]); // Reset to initial customer info
        // Add any other state resets here as needed
    };

    console.log("passangersInfo", passangersInfo);

    const modalBooking = () => {
        return (
            <Modal size="fullscreen" closeIcon open={open} trigger={<Button icon labelPosition='left' primary size="large"><Icon name='plane' />Book Flight</Button>} onClose={resetModalState} onOpen={() => setOpen(true)}>
                <Header content='Book a Flight' />
                <Segment loading={bookingLoading} style={{padding: "0.5em"}} vertical>
                    <Modal.Content>
                        <Grid columns={3} relaxed='very' divided style={{padding:"1em"}}>
                            <Grid.Column>
                                <Form>
                                    <Form.Field required>
                                        <Header as='h3' content='Flight Information' />
                                        <Dropdown
                                            placeholder='Select Flight'
                                            fluid
                                            selection
                                            options={flightOptions}
                                            onChange={handleFlightSelect}
                                        />
                                    </Form.Field>
                                    <Grid columns={2}>
                                        <Grid.Column>
                                        <Header as='h3' content='Passenger Information' />
                                        </Grid.Column>
                                        <Grid.Column textAlign="right">
                                            <Button animated compact size="medium" onClick={() => setSecondOpen(true)}>
                                                <Button.Content visible>Add</Button.Content>
                                                <Button.Content hidden>
                                                    <Icon name='plus' />
                                                </Button.Content>
                                            </Button>
                                        </Grid.Column>
                                    </Grid>
                                    <Modal size="small" closeIcon open={secondOpen} onClose={() => setSecondOpen(false)} onOpen={() => setSecondOpen(true)}>
                                        <Header content='Add Passenger' />
                                        <Modal.Content>
                                            <Header as='h4' content='Continue with account' />
                                            <Form>
                                                <Form.Field required>
                                                    <Form.Input placeholder='Username' value={name} onChange={e => setName(e.target.value)} />
                                                    <Form.Input placeholder='Password' value={pas} onChange={e => setPas(e.target.value)} />
                                                </Form.Field>
                                            </Form>
                                        </Modal.Content>
                                        <Modal.Actions>
                                            <Button color='green' onClick={addPassenger}>
                                                <Icon name='checkmark' /> Add
                                            </Button>
                                        </Modal.Actions>
                                    </Modal>
                                    <Segment>
                                        <List style={{padding:"0.1em"}}>
                                            <List.Item>
                                                <List.Content>
                                                    {passangersInfo.map((passenger, index) => (
                                                        <List.Item style={{marginTop: "0.3em"}} key={index}>
                                                            <List.Content>
                                                                <List.Header>{passenger.NAME}</List.Header>
                                                                <List.Description>
                                                                    <b>Date of Birth:</b> {passenger.BIRTH} <br/>
                                                                    <b>Phone Number:</b> {passenger.PHONE_NUMBER}
                                                                </List.Description>
                                                            </List.Content>
                                                        </List.Item>
                                                    ))}
                                                </List.Content>
                                            </List.Item>
                                        </List>
                                    </Segment>
                                    <Divider/>
                                    <Header as='h3' content='Payment Info' />
                                    <Form.Field>
                                        <Form.Input placeholder='Name on Card' />
                                        <Form.Input placeholder='Credit Card Number' />
                                    </Form.Field>
                                    <Form.Group widths='equal'>
                                        <Form.Input fluid placeholder='MM/YY' />
                                        <Form.Input fluid placeholder='CVV' />
                                    </Form.Group>
                                    <Divider/>
                                    <Form.Field>
                                        <Form.Input placeholder='Add Voucher for free passanger' value={voucher} onChange={e => setVoucher(e.target.value)} />
                                    </Form.Field>
                                </Form>
                            </Grid.Column>
                            <Grid.Column>
                                <Header as='h3' content='Seat Selection' />
                                <SeatMap setPassangersInfo={setPassangersInfo} passangersInfo={passangersInfo} selectedFlight={selectedFlight} />
                            </Grid.Column>
                            <Grid.Column>
                                <Header as='h3' content='Insurance' />
                                <Radio toggle
                                    label='Standard Insurance'
                                    name='radioGroup'
                                    checked={insurance === '1'}
                                    onChange={() => setInsurance(insurance === '1' ? '0' : '1')}
                                />
                                <Divider/>
                                <Header as='h3' content='Price Summary' />
                                <List>
                                    {passangersInfo.map((passenger, index) => {
                                        const individualPrice = calculateIndividualPrice(passenger); // Function to calculate the price for each passenger
                                        return (
                                            <List.Item key={index}>
                                                <List.Content floated='right'>
                                                    <b>${individualPrice.toFixed(2)}</b>
                                                </List.Content>
                                                <List.Content>
                                                    {passenger.NAME}'s Seat Price
                                                </List.Content>
                                            </List.Item>
                                        );
                                    })}
                                    <Divider/>
                                    <List.Item>
                                        <List.Content floated='right'>
                                            <b>${calculatePrice().toFixed(2)}</b>
                                        </List.Content>
                                        <List.Content>
                                            Total Price
                                        </List.Content>
                                    </List.Item>
                                </List>
                            </Grid.Column>
                        </Grid>

                    </Modal.Content>
                </Segment>
                <Modal.Actions>
                    <Button color='green' onClick={handleBookingConfirmation}>
                        <Icon name='checkmark' /> Confirm
                    </Button>
                </Modal.Actions>
            </Modal>
        )
    }

    const modalBooked = () => {
        return (
            <Modal size="large" closeIcon open={thirdOpen} trigger={<Button icon labelPosition='left' size="large"><Icon name='list' />Booked Flights</Button>} onClose={() => setThirdOpen(false)} onOpen={() => setThirdOpen(true)}>
                <Modal.Content>
                    <Header content='Booked Flights' />
                    <Table singleLine>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell>Flight Number</Table.HeaderCell>
                                <Table.HeaderCell>Origin</Table.HeaderCell>
                                <Table.HeaderCell>Destination</Table.HeaderCell>
                                <Table.HeaderCell>Date</Table.HeaderCell>
                                <Table.HeaderCell>Time</Table.HeaderCell>
                                <Table.HeaderCell>Seat Type</Table.HeaderCell>
                                <Table.HeaderCell>Seat Number</Table.HeaderCell>
                                <Table.HeaderCell>Insurance</Table.HeaderCell>
                                <Table.HeaderCell></Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>
                        <Table.Body>
                        {
                            bookedFlights.map((flight, index) =>
                                (
                                    <Table.Row key={index}>
                                        <Table.Cell>{flight.FLIGHT_NUMBER}</Table.Cell>
                                        <Table.Cell>{flight.ORIGIN.split(" ").slice(1).join(" ")}</Table.Cell>
                                        <Table.Cell>{flight.DESTINATION.split(" ").slice(1).join(" ")}</Table.Cell>
                                        <Table.Cell>{flight.TIME_DEPARTURE.split('T')[0]}</Table.Cell>
                                        <Table.Cell>{flight.TIME_DEPARTURE.split('T')[1].split(':').slice(0, 2).join(':')}</Table.Cell>
                                        <Table.Cell>{flight.SEAT_TYPE}</Table.Cell>
                                        <Table.Cell>{flight.SEAT_NUMBER}</Table.Cell>
                                        <Table.Cell>{flight.INSURANCE_STATUS === 1 ? 'Yes' : 'No'}</Table.Cell>
                                        <Table.Cell collapsing>
                                                <Button animated="vertical" floated="left" size='midium' onClick={()=>handleDelete(flight)}>
                                                    <Button.Content visible>Cancel</Button.Content>
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
                </Modal.Content>
            </Modal>
        )
    }

    return (
        <>
            <Segment style={{ padding: '2em' }} vertical>
                <Grid columns={2}>
                    <Grid.Column>
                        <Header as='h2'>Welcome {customerInfo.NAME}</Header>
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
            {
                message && (
                    //add a success message for booking
                    <Message style={{marginLeft: "10em", marginRight: "10em"}}
                        success
                        header='Your booking was successful'
                        content='You can view your bookings below'
                    />
                )
            }
            <Container>
                <Segment loading={loading} style={{ padding: '0.5em' }} vertical>
                    <Segment textAlign="right" style={{ padding: '1em' }} vertical>
                        {modalBooked()}
                        {modalBooking()}
                    </Segment>
                    <Table singleLine>
                        <Table.Header>
                            <Table.Row>
                                <Table.HeaderCell>Flight Number</Table.HeaderCell>
                                <Table.HeaderCell>Origin</Table.HeaderCell>
                                <Table.HeaderCell>Destination</Table.HeaderCell>
                                <Table.HeaderCell>Date</Table.HeaderCell>
                                <Table.HeaderCell>Time</Table.HeaderCell>
                            </Table.Row>
                        </Table.Header>

                        <Table.Body>
                            {
                                availableFlights.map((flight, index) => 
                                    (
                                        <Table.Row key={index}>
                                            <Table.Cell>{flight.FLIGHT_NUMBER}</Table.Cell>
                                            <Table.Cell>{flight.ORIGIN.split(" ").slice(1).join(" ")}</Table.Cell>
                                            <Table.Cell>{flight.DESTINATION.split(" ").slice(1).join(" ")}</Table.Cell>
                                            <Table.Cell>{flight.TIME_DEPARTURE.split('T')[0]}</Table.Cell>
                                            <Table.Cell>{flight.TIME_DEPARTURE.split('T')[1].split(':').slice(0, 2).join(':')}</Table.Cell>
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


export default Customer