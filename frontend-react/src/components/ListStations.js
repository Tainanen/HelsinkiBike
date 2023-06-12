import React, { useState, useEffect } from 'react';
import './ListStations.css';
import {Link} from "react-router-dom";
// import API_URL from './config.js';

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
                id: station.id,
                nameFin: station.nameFin,
                addressFin: station.addressFin,
                cityFin: station.cityFin,
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
            <Link to="/stationsSwe">
                <button>Click here for the information in Swedish</button>
            </Link>
            {noStationsFound ? (
                <p>There are no stations with that name.</p>
            ) : (
                <>
            <table>
                <thead>
                <tr>
                    <th>Id of the station</th>
                    <th>Name of the station in Finnish</th>
                    <th>Address in Finnish</th>
                    <th>City in Finnish</th>
                    <th>Capacity</th>
                </tr>

                </thead>
                <tbody>
                {stations.map((station) => (
                    <tr key={station.id}>
                        <td>{station.id}</td>
                        <Link to={`/singlestation/${station.id}`}>
                            {station.nameFin}
                        </Link>
                        <td>{station.addressFin}</td>
                        <td>{station.cityFin}</td>
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

export default ListStations;
