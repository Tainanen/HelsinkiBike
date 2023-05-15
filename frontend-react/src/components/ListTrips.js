import React, { useState, useEffect } from 'react';
import './ListTrips.css';

function ListTrips() {
    const [trips, setTrips] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);

    const fetchTrips = async () => {
        try {
            const url = `http://localhost:8080/api/trips?page=${currentPage}&size=20`;
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            const tripsArray = data.content.map(trip => {
                return {
                    id: trip[0],
                    departureStationName: trip[1],
                    returnStationName: trip[2],
                    distanceInMetres: trip[3],
                    durationInSeconds: trip[4],
                }
            });
            console.log('Trips:', tripsArray);
            setTrips(tripsArray);
        } catch (error) {
            console.error(`Error fetching trips: ${error}`);
        }
    };

    useEffect(() => {
        fetchTrips();
    }, [currentPage]);

    const handleNextPage = () => {
        setCurrentPage(currentPage + 1);
    };

    const handlePreviousPage = () => {
        if (currentPage > 0) {
            setCurrentPage(currentPage - 1);
        }
    };

    return (
        <div>
            <h1>List of Bike Trips</h1>
            <table>
                <thead>
                <tr>
                    <th>Departure Station Name</th>
                    <th>Return Station Name</th>
                    <th>Distance (kilometres)</th>
                    <th>Duration (minutes)</th>
                </tr>
                </thead>
                <tbody>
            {trips.map((trip) => (
                <tr key={trip.id}>
                    <td>{trip.departureStationName}</td>
                    <td>{trip.returnStationName}</td>
                    <td>{Number(trip.distanceInMetres / 1000.0).toFixed(2)}</td>
                    <td>{Number(trip.durationInSeconds / 60.0).toFixed(2)}</td>
                </tr>
            ))}
                </tbody>
            </table>
            <button onClick={handlePreviousPage}>Previous page</button>
            <button onClick={handleNextPage}>Next page</button>
        </div>
    );
}

export default ListTrips;









