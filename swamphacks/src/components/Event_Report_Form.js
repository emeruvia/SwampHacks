import React from "react";
import '../App.css';
import {FormGroup, ControlLabel, FormControl, Form, Button} from 'react-bootstrap';

class Event_Report_Form extends React.Component {
    constructor(props,context) {
    super(props, context);

    this.handleChange = this.handleChange.bind(this);

    this.state = {
      value: ''
    };
}
getValidationState() {
    const length = this.state.value.length;
    if (length > 10) return 'success';
    else if (length > 5) return 'warning';
    else if (length > 0) return 'error';
    return null;
  }

  handleChange(e) {
    this.setState({ value: e.target.value });
  }
    render (){
    return(
        <Form inline>
        <FormGroup controlId="formInlineName">
            <ControlLabel>Name</ControlLabel>{' '}
            <FormControl type="text" placeholder="Jane Doe" />
        </FormGroup>{' '}
        <FormGroup controlId="formInlineEmail">
            <ControlLabel>Email</ControlLabel>{' '}
            <FormControl type="email" placeholder="jane.doe@example.com" />
        </FormGroup>{' '}
        <Button type="submit">Send invitation</Button>
        </Form>
    );
    }
}

export default Event_Report_Form;