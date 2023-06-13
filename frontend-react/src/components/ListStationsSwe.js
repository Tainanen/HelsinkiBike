import React, { useState, useEffect } from 'react';
import './ListStationsSwe.css';
import {Link} from "react-router-dom";
// import API_URL from './config.js';

function ListStationsSwe() {
    const [stations, setStations] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [searchTerm, setSearchTerm] = useState('');
    const [noStationsFound, setNoStationsFound] = useState(false);
    const [totalPages, setTotalPages] = useState(0);

    const fetchStations = async () => {
        try {
            const url = `http://localhost:8080/stations/searchSwe?word=${searchTerm}&page=${currentPage}&size=20`;
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
                id: station.id,
                nameSwe: station.nameSwe,
                addressSwe: station.addressSwe,
                citySwe: station.citySwe,
                capacity: station.capacity,

            }));
            console.log('Stations:', stationsArray);
            setStations(stationsArray);
            setTotalPages(data.totalPages);
            window.scrollTo({ top: 0, behavior: 'smooth' });
        } catch (error) {
            console.error(`Error fetching stations: ${error}`);
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
                            <th>Name of the station in Swedish</th>
                            <th>Address in Swedish</th>
                            <th>City in Swedish</th>
                            <th>Capacity</th>
                        </tr>

                        </thead>
                        <tbody>
                        {stations.map((station) => (
                            <tr key={station.id}>
                                <Link to={`/singlestation/${station.id}`}>
                                    {station.nameSwe}
                                </Link>
                                <td>{station.addressSwe}</td>
                                <td>{station.citySwe}</td>
                                <td>{station.capacity}</td>
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

export default ListStationsSwe;
