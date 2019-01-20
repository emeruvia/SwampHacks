import React, { Component } from 'react';
import logo from './logo.svg';
import {Navbar,Nav,NavItem} from 'react-bootstrap';
import {BrowserRouter, Route} from 'react-router-dom';
import './App.css';

import Home from "./components/Home";
import Travel_Form from "./components/Travel_Form";
import Event_Report_Form from "./components/Event_Report_Form";
import Budget_Application from "./components/Budget_Application";
import Chapter_Report from "./components/Chapter_Report";
import Expense_Report from "./components/Expense_Report";

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="CardBoardCastle">
          <Route path="/" Component={Home} exact/>
          <Route path="/Travel_Form" Component={Travel_Form}/>
          <Route path="/Event_Report" Component={Event_Report_Form}/>
          <Route path="/Budget_Application" Component={Budget_Application}/>
          <Route path="/Chapter_Report" Component={Chapter_Report}/>
          <Route path="/Expense_Report" Component={Expense_Report}/>
        


          <Navbar inverse collapseOnSelect>
          <Navbar.Header>
            <Navbar.Brand>
              <a href="#brand">E-NEST</a>
            </Navbar.Brand>
            <Navbar.Toggle />
          </Navbar.Header>
          <Navbar.Collapse>
            <Nav pullRight>
              <NavItem eventKey={1} href="#">
                Events
              </NavItem>
              <NavItem eventKey={2} href="#">
                Travel
              </NavItem>
              <NavItem eventKey={1} href="#">
                Budget
              </NavItem>
              <NavItem eventKey={1} href="#">
                Fundraisers
              </NavItem>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
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
      </BrowserRouter>
    );
  }
}

export default App;
