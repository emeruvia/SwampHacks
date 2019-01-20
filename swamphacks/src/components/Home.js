import React from "react";
import logo from '../logo.svg';
import '../App.css';

const Home = () => {
    console.log("test");
    return(
        <div> 
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <h1>
                Header for things
                </h1>
                <p>
                stuff here
                </p>
            </header>
        </div>
    );
};

export default Home;