import React, { useState, useEffect } from 'react';
import './SingleStation.css';

function SingleStation() {
    const [station, setStation] = useState(null);

    const fetchSingleStation = async () => {
        try {
            const url = `http://localhost:8080/api/stations/5`;
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

    useEffect(() => {
        fetchSingleStation();
    }, []);

    return (
        <div>
            <h1>The station</h1>
            {station && (
                <table>
                    <thead>
                    <tr>
                        <th>Name of the station in Finnish</th>
                        <th>Address in Finnish</th>
                        <th>Number of Departure Journeys</th>
                        <th>Number of Return Journeys</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>{station.nameFin}</td>
                        <td>{station.addressFin}</td>
                        <td>{station.departureCount}</td>
                        <td>{station.returnCount}</td>
                    </tr>
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default SingleStation;
