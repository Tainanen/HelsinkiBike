import React, { useState, useEffect } from 'react';
import './ListTrips.css';

function ListTrips() {
    const [trips, setTrips] = useState([]);

    useEffect(() => {
        async function fetchTrips() {
            try{
                const response = await fetch('http://localhost:8080/api/trips');
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                const data = await response.json();
                console.log('Data:', data);
                setTrips(data.content);
            } catch (error) {
                console.error('Error fetching trips:', error);
            }
        }
        fetchTrips();
    }, []);

    return (
        <div>
            <h1>List of Bike Trips</h1>
            <table>
                <thead>
                <tr>
                    <th>Departure</th>
                    <th>Return</th>
                    <th>Departure Station Name</th>
                    <th>Return Station Name</th>
                    <th>Distance</th>
                    <th>Duration</th>
                </tr>
                </thead>
                <tbody>
            {trips.map((trip) => (
                <tr key={trip.id}>
                    <td>{trip.departureTime}</td>
                    <td>{trip.returnTime}</td>
                    <td>{trip.departureStationName}</td>
                    <td>{trip.returnStationName}</td>
                    <td>{trip.distanceInMetres}</td>
                    <td>{trip.durationInSeconds}</td>
                </tr>
            ))}
                </tbody>
            </table>
        </div>
    );
}

export default ListTrips;









