import './App.css';
import ListTrips from './components/ListTrips.js';
import ListStations from './components/ListStations.js';
import Home from './components/Home.js'
import { BrowserRouter as Router, Route, Link, Routes } from 'react-router-dom';
import SingleStation from "./components/SingleStation.js";

function App() {
    return (
        <Router>
            <div className="App">
                <nav>
                        <li>
                            <Link to="/">Main page</Link>
                        </li>
                        <li>
                            <Link to="/trips">List of trips</Link>
                        </li>
                        <li>
                            <Link to="/stations">List of stations</Link>
                        </li>
                </nav>

                    <Routes>
                        <Route path="/trips" element={<ListTrips />} />
                        <Route path="/stations" element={<ListStations />} />
                        <Route path="/" element={<Home />} />
                        <Route path="/singlestation/:id" element={<SingleStation />} />
                    </Routes>
                </div>
        </Router>
);
}

export default App;







