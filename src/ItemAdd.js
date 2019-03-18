import React, {Component} from 'react';

export default class ItemAdd extends Component {
    constructor(props) {
        super(props);
        this.state = {
            item: ''
        }
    }

    editName = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        this.setState({...this.state, [name]: value});
    };

    handleClick = () => {
        this.props.onSave(this.state.item);
        const newState = JSON.parse(JSON.stringify(this.state));
        newState.item = '';
        this.setState(newState);
    };

    render() {
        return (
            <div>
                <input name="item"
                       onChange={this.editName}
                       value={this.state.item}/>
                <button name="save"
                        onClick={this.handleClick}>
                    Save
                </button>
            </div>
        )
    }
}