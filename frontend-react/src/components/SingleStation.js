import React, { useState, useEffect } from 'react';
import './SingleStation.css';
import { useParams } from 'react-router-dom';


function SingleStation() {
    const [station, setStation] = useState(null);
    const {id} = useParams();
    const [averageDistanceDeparture, setAverageDistanceDeparture] = useState(null);
    const [averageDistanceReturn, setAverageDistanceReturn] = useState(null);

    const fetchSingleStation = async () => {
        try {
            const url = `http://localhost:8080/stations/${id}`;
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log(data);
            setStation(data);
        } catch (error) {
            console.error(`Error fetching station: ${error}`);
        }
    };
    const fetchAverageDistanceDeparture = async () => {
        try {
            const url = `http://localhost:8080/stations/${id}/averageDistanceDeparture`;
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log(data);
            setAverageDistanceDeparture(data);
        } catch (error) {
            console.error(`Error fetching average distance for departure: ${error}`);
        }
    };

    const fetchAverageDistanceReturn = async () => {
        try {
            const url = `http://localhost:8080/stations/${id}/averageDistanceReturn`;
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log(data);
            setAverageDistanceReturn(data);
        } catch (error) {
            console.error(`Error fetching average distance for return: ${error}`);
        }
    };

    useEffect(() => {
        fetchSingleStation();
        fetchAverageDistanceDeparture();
        fetchAverageDistanceReturn();
    }, []);

    return (
        <div>
            <h1>The station</h1>
            {station && (
                <table className = "station-details">
                    <thead>
                    <tr>
                        <th>Name of the station in Finnish</th>
                        <th>Address in Finnish</th>
                        <th>Number of Departure Journeys</th>
                        <th>Number of Return Journeys</th>
                        <th>Average Distance (km) of Departure Journeys</th>
                        <th>Average Distance (km) of Return Journeys</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>{station.nameFin}</td>
                        <td>{station.addressFin}</td>
                        <td>{station.departureCount}</td>
                        <td>{station.returnCount}</td>
                        <td>{averageDistanceDeparture.toFixed(2)}</td>
                        <td>{averageDistanceReturn.toFixed(2)}</td>
                    </tr>
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default SingleStation;
