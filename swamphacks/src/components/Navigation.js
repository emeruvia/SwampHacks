import React from "react";
import {Navbar,Nav,NavItem} from 'react-bootstrap';

const Navigation = () => {
    return(
        <div>
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
        </div>
    );
}

export default Navigation;