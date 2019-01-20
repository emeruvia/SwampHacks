import React from "react";
import logo from '../Logo.PNG';
import {ButtonToolbar, Button} from 'react-bootstrap';
import '../App.css';

const Home = () => {
    return(
        <div> 
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <h1>
                FGCU QUIDDITCH OVERVIEW
                </h1>
                <h3>
                2018-2019
                </h3>
            </header>
            <ButtonToolbar>
                <Button className="Chapter-Button" bsStyle="success" bsSize="large">VIEW CHAPTER REPORT</Button>
            </ButtonToolbar>       
        </div>
    );
};

export default Home;