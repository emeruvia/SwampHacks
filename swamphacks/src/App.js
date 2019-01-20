import React, { Component } from 'react';
import logo from './logo.svg';
import {Navbar,Nav,NavItem} from 'react-bootstrap';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="App">
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
    );
  }
}

export default App;
