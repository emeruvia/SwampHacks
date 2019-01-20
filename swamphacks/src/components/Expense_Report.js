import React from "react";
import logo from './../Logo.PNG';
import '../App.css';

const Expense_Report = () => {
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
}

export default Expense_Report;