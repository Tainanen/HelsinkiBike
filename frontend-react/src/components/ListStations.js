import React, { useState, useEffect } from 'react';
import './ListStations.css';
import {Link} from "react-router-dom";
import API_URL from './config.js';

function ListStations() {
    const [stations, setStations] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [searchTerm, setSearchTerm] = useState('');
    const [noStationsFound, setNoStationsFound] = useState(false);
    const [totalPages, setTotalPages] = useState(0);

    const fetchStations = async () => {
        try {
            const url = `http://localhost:8080/stations/search?word=${searchTerm}&page=${currentPage}&size=20`;
            //This is the URL for the cloud configuration:
            //const url = `${API_URL}/stations/search?word=${searchTerm}&page=${currentPage}&size=20\`;`
            const response = await fetch(url);
            if (!response.ok) {
                setNoStationsFound(true);
                throw new Error('Network response was not ok');
            }
            const data = await response.json();
            setNoStationsFound(false);
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
            setTotalPages(data.totalPages);
            window.scrollTo({ top: 0, behavior: 'smooth' });
        } catch (error) {
            console.error(`Error fetching trips: ${error}`);
        }
    };

    useEffect(() => {
        fetchStations(searchTerm);
    }, [currentPage, searchTerm]);

    const handleSearch = (event) => {
        setSearchTerm(event.target.value);
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
            <h1>List of Bike Stations</h1>
            <div>
                <label htmlFor="search">Search by station name:</label>
                <input type="text" id="search" value={searchTerm} onChange={handleSearch} />
            </div>
            {noStationsFound ? (
                <p>There are no stations with that name.</p>
            ) : (
                <>
            <table>
                <thead>
                <tr>
                    <th>Id of the station</th>
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
                        <td>{station.id}</td>
                        <Link to={`/singlestation/${station.id}`}>
                            {station.nameFin}
                        </Link>
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
                    <span>Page {currentPage + 1} of {totalPages}</span>
            <button onClick={handleNextPage}>Next page</button>
                </>
            )}
        </div>
    );
}

export default ListStations;
