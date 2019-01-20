import React from "react";
import {Navbar,Nav,NavItem,MenuItem,NavDropdown} from 'react-bootstrap';

const Navigation = () => {
    return(
        <div>
          <Navbar inverse collapseOnSelect>
          <Navbar.Header>
            <Navbar.Brand>
              <a href="/">Cardboard Castle</a>
            </Navbar.Brand>
            <Navbar.Toggle />
          </Navbar.Header>
          <Navbar.Collapse>
            <Nav pullRight>
              <NavItem eventKey={1} href="/Event_Report_Form">
                Events
              </NavItem>
              <NavDropdown eventKey={3} title="Budget" id="budget-dropdown">
                <MenuItem eventKey={3.1} href="/Receipts">Expense Receipts</MenuItem>
                <MenuItem eventKey={3.2} href="/Budget_Application">Submit Budget Application</MenuItem>
              </NavDropdown>
            </Nav>
          </Navbar.Collapse>
        </Navbar>
        </div>
    );
}

export default Navigation;