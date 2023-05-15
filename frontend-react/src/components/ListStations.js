import React, { useState, useEffect } from 'react';
import './ListStations.css';

function ListStations() {
    const [stations, setStations] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [sortOrder, setSortOrder] = useState('asc');
    const [sortColumn, setSortColumn] = useState('departureStationName');

    const fetchStations = async () => {
        try {
            const url = `http://localhost:8080/api/stations?page=${currentPage}&size=20`;
            const response = await fetch(url);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            console.log(data);
            const stationsArray = data.content.map((station) => ({
                id: station[0],
                nameFin: station[1],
                nameSwe: station[2],
                nameEn: station[3],
                addressFin: station[4],
                addressSwe: station[5],
                cityFin: station[6],
                citySwe: station[7],
                'operator': station[8],
                capacity: station[9],
                x: station[10],
                y: station[11]
            }));
            console.log('Stations:', stationsArray);
            setStations(stationsArray);
        } catch (error) {
            console.error(`Error fetching trips: ${error}`);
        }
    };

    useEffect(() => {
        fetchStations();
    }, [currentPage]);

 /*   const handleSort = (columnName) => {
        if (columnName === sortColumn) {
            setSortOrder((order) => (order === 'asc' ? 'desc' : 'asc'));
        } else {
            setSortColumn(columnName);
            setSortOrder('asc');
        }
        setCurrentPage(0);
    };
*/
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
            <h1>List of Bike Stations</h1>
            <table>
                <thead>
                <tr>
                    <th>Name of the station in Finnish</th>
                    <th>Name of the station in Swedish</th>
                    <th>Name of the station in English</th>
                    <th>Address in Finnish</th>
                    <th>Address in Swedish</th>
                    <th>City in Finnish</th>
                    <th>City in Swedish</th>
                    <th>Operator</th>
                    <th>Capacity</th>
                    <th>Longitude</th>
                    <th>Latitude</th>
                </tr>

                </thead>
                <tbody>
                {stations.map((station) => (
                    <tr key={station.id}>
                        <td>{station.nameFin}</td>
                        <td>{station.nameSwe}</td>
                        <td>{station.nameEn}</td>
                        <td>{station.addressFin}</td>
                        <td>{station.addressSwe}</td>
                        <td>{station.cityFin}</td>
                        <td>{station.citySwe}</td>
                        <td>{station.operator}</td>
                        <td>{station.capacity}</td>
                        <td>{(station.x).toFixed(5)}</td>
                        <td>{(station.y).toFixed(5)}</td>
                    </tr>
                ))}
                </tbody>
            </table>
            <button onClick={handlePreviousPage}>Previous page</button>
            <button onClick={handleNextPage}>Next page</button>
        </div>
    );
}

export default ListStations;
