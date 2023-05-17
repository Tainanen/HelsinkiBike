import React, { useState, useEffect } from 'react';
import './ListTrips.css';

function ListTrips() {
    const [trips, setTrips] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [sortOrder, setSortOrder] = useState('asc');
    const [sortColumn, setSortColumn] = useState('departureStationName');
    const [totalPages, setTotalPages] = useState(0);

    const fetchTrips = async () => {
        try {
            const url = `http://localhost:8080/trips/sort?sortBy=${sortColumn}&sortOrder=${sortOrder}&page=${currentPage}&size=20`;
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            const tripsArray = data.content.map((trip) => ({
                id: trip[0],
                departureStationName: trip[1],
                returnStationName: trip[2],
                distanceInMetres: trip[3],
                durationInSeconds: trip[4]
            }));
            console.log('Trips:', tripsArray);
            setTrips(tripsArray);
            setTotalPages(data.totalPages);
        } catch (error) {
            console.error(`Error fetching trips: ${error}`);
        }
    };

    useEffect(() => {
        fetchTrips();
    }, [currentPage, sortOrder, sortColumn]);

    const handleSort = (columnName) => {
        if (columnName === sortColumn) {
            setSortOrder((order) => (order === 'asc' ? 'desc' : 'asc'));
        } else {
            setSortColumn(columnName);
            setSortOrder('asc');
        }
        setCurrentPage(0);
    };

    const handleNextPage = () => {
        setCurrentPage((page) => page + 1);
    };

    const handlePreviousPage = () => {
        if (currentPage > 0) {
            setCurrentPage((page) => page - 1);
        }
    };

    return (
        <div>
            <h1>List of Bike Trips</h1>
            <h2>Click the column names to sort the results!</h2>
            <table>
                <thead>
                <tr>
                    <th onClick={() => handleSort('departureStationName')}>
                        Departure Station Name
                        {sortColumn === 'departureStationName' && (
                            <span>{sortOrder === 'asc' ? ' ▲' : ' ▼'}</span>
                        )}
                    </th>
                    <th onClick={() => handleSort('returnStationName')}>
                        Return Station Name
                        {sortColumn === 'returnStationName' && (
                            <span>{sortOrder === 'asc' ? ' ▲' : ' ▼'}</span>
                        )}
                    </th>
                    <th onClick={() => handleSort('distanceInMetres')}>
                        Distance (kilometres)
                        {sortColumn === 'distanceInMetres' && (
                            <span>{sortOrder === 'asc' ? ' ▲' : ' ▼'}</span>
                        )}
                    </th>
                    <th onClick={() => handleSort('durationInSeconds')}>
                        Duration (minutes)
                        {sortColumn === 'durationInSeconds' && (
                            <span>{sortOrder === 'asc' ? ' ▲' : ' ▼'}</span>
                        )}
                    </th>
                </tr>

                </thead>
                <tbody>
            {trips.map((trip) => (
                <tr key={trip.id}>
                    <td>{trip.departureStationName}</td>
                    <td>{trip.returnStationName}</td>
                    <td>{trip.distanceInMetres.toFixed(2)}</td>
                    <td>{trip.durationInSeconds.toFixed(2)}</td>
                </tr>
            ))}
                </tbody>
            </table>
            <button onClick={handlePreviousPage}>Previous page</button>
            <span>Page {currentPage + 1} of {totalPages}</span> {/* Display current page and total pages */}
            <button onClick={handleNextPage}>Next page</button>
        </div>
    );
}

export default ListTrips;









