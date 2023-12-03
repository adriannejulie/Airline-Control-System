import React, { useState, useEffect } from 'react';
import { Divider, Dropdown, Header } from 'semantic-ui-react';
import './SeatMap.scss'; 
import axios from 'axios';

function SeatMap({setPassangersInfo, passangersInfo, selectedFlight}) {
    const [selectedSeat, setSelectedSeat] = useState('');
    const [reservedSeats, setReservedSeats] = useState([]);
    const [updatedPassengers, setUpdatedPassengers] = useState(passangersInfo);
    const [selectedPassengerIndex, setSelectedPassengerIndex] = useState(0);

    const url = process.env.REACT_APP_API_URL;

    
    console.log("passangersInfo", passangersInfo);

    useEffect(() => {
        setUpdatedPassengers(passangersInfo);
    }, [passangersInfo]);

    useEffect(() => {
        const getReservedSeats = async () => {
            try {
                const response = await axios.get(`${url}/api/seats/reservation`, {
                    params: {
                        FLIGHT_NUMBER: selectedFlight.FLIGHT_NUMBER
                    }
                });
                if (response.status === 201) {
                    console.log("reserved", response.data);
                    setReservedSeats(response.data);
                }
            } catch (error) {
                console.error("Error fetching reserved seats:", error);
            }
        };
        getReservedSeats();
    }, [selectedFlight]);

    const passengerOptions = passangersInfo.map((passenger, index) => ({
        key: index,
        text: passenger.NAME,
        value: index
    }));

    const isSeatReserved = (seatId) => {
        // Check if the seat is reserved in the server data
        if (reservedSeats.includes(seatId)) {
            return true;
        }

        return updatedPassengers.some((passenger, index) => 
        index !== selectedPassengerIndex && passenger.SEAT_NUMBER === seatId);
    };

    // Adjust the onChange handler for Semantic UI Dropdown
    const handleDropdownChange = (e, { value }) => {
        setSelectedPassengerIndex(value);
    
        // Fetch the seat number of the newly selected passenger
        const passengerSeat = updatedPassengers[value].SEAT_NUMBER || '';
        setSelectedSeat(passengerSeat);
    };

    const assignSeatToPassenger = (seatId, seatType) => {
        let updated = [...updatedPassengers];
        updated[selectedPassengerIndex] = {
            ...updated[selectedPassengerIndex],
            SEAT_NUMBER: seatId,
            SEAT_TYPE: seatType
        };
        setUpdatedPassengers(updated);
        setPassangersInfo(updated);
    };

    const handleSeatSelection = (seatId) => {
        setSelectedSeat(seatId);

        const rowNumber = parseInt(seatId.slice(0, -1), 10);
        let seatType = 'Ordinary';
        if (rowNumber >= 1 && rowNumber <= 3) {
            seatType = 'Business';
        } else if (rowNumber >= 4 && rowNumber <= 5) {
            seatType = 'Comfort';
        }

        assignSeatToPassenger(seatId, seatType);
    };

    console.log("updatedPassengers", updatedPassengers);

    return (
        <>
            <div>
                <Header as="h5">Choose Passangers</Header>
                <Dropdown 
                    placeholder="Select Passenger"
                    fluid
                    selection
                    options={passengerOptions}
                    value={selectedPassengerIndex}
                    onChange={handleDropdownChange}
                />
                <Divider />
                <div className="plane">
                    <ol className="cabin fuselage">
                        {[...Array(12)].map((_, index) => {
                            const rowNumber = index + 1;
                            return (
                                <li className={`row row--${rowNumber}`} key={rowNumber}>
                                    <ol className="seats" type="A">
                                        {['A', 'B', 'C', 'D'].map(seat => {
                                            const seatId = `${rowNumber}${seat}`;
                                            const isReserved = isSeatReserved(seatId);
                                            return (
                                                <li className="seat" key={seatId}>
                                                    <input
                                                        type="checkbox"
                                                        id={seatId}
                                                        checked={selectedSeat === seatId}
                                                        disabled={isReserved}
                                                        onChange={() => handleSeatSelection(seatId)}
                                                    />
                                                    <label htmlFor={seatId}>{seatId}</label>
                                                </li>
                                            );
                                        })}
                                    </ol>
                                </li>
                            );
                        })}
                    </ol> 
                </div>
            </div>
        </>
    );
}

export default SeatMap;
