import './App.css';
import ListTrips from './components/ListTrips.js';
import ListStations from './components/ListStations.js';
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import SingleStation from "./components/SingleStation.js";

function App() {
    return (
        <Router>
            <div className="App">
                <nav>
                    <ul>
                        <li>
                            <Link to="/">Home</Link>
                        </li>
                        <li>
                            <Link to="/trips">Trips</Link>
                        </li>
                        <li>
                            <Link to="/stations">Stations</Link>
                        </li>
                    </ul>
                </nav>

                    <Routes>
                        <Route path="/trips" element={<ListTrips />} />
                        <Route path="/stations" element={<ListStations />} />
                        <Route path="" element={<h1>Welcome to my app!</h1>} />
                        <Route path="/singlestation" element={<SingleStation />} />
                    </Routes>
                </div>
        </Router>
);
}

export default App;







