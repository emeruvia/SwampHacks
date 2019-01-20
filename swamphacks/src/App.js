import React, { Component } from 'react';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import './App.css';

import Home from "./components/Home";
import Travel_Form from "./components/Travel_Form";
import Event_Report_Form from "./components/Event_Report_Form";
import Budget_Application from "./components/Budget_Application";
import Chapter_Report from "./components/Chapter_Report";
import Expense_Report from "./components/Expense_Report";
import Error from "./components/Error";

import Navigation from "./components/Navigation";

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div className="App">
          <Navigation />
          <Switch>
            <Route exact path="/" render={Home}/>
            <Route path="/Travel_Form" render={Travel_Form}/>
            <Route path="/Event_Report" render={Event_Report_Form}/>
            <Route path="/Budget_Application" render={Budget_Application}/>
            <Route path="/Chapter_Report" render={Chapter_Report}/>
            <Route path="/Expense_Report" render={Expense_Report}/>
            <Route Component={Error}/>
          </Switch>
        </div>
      </BrowserRouter>
    );
  }
};

export default App;
