import React, { useState } from 'react';
import Photo from '../images/Yellow_city_bike_in_Helsinki,_Finland,_2021_May.jpg';

function Home() {
        return (
            <div className="container">
                <h1>Helsinki City Bikes in summer 2021!</h1>
                <img src={Photo} alt="photo by Henry Söderlund, licensed under CC BY 2.0" className="photo" />
            </div>
        );
    }

    export default Home;