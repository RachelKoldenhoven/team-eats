import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

import ItemAdd from './ItemAdd';
import ItemList from './ItemList';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: []
        };
    }

    async componentDidMount() {
        await this.getItems();
    }

    async getItems() {
        const response = await fetch('/api/items');
        const data = await response.json();
        const newState = JSON.parse(JSON.stringify(this.state));
        newState.items = data;
        this.setState(newState);
    }

    onSave = async (item) => {
        const newItem = {text: item};
        await fetch('/api/items', {
            method: 'POST',
            body: JSON.stringify(newItem),
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        });
        this.getItems();
    };

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h2>Team Eats</h2>
                </header>
                <ItemList items={this.state.items}/>
                <ItemAdd onSave={this.onSave}/>
                <p>The Pull Request for this story will create a review app.</p>
                <p>Logo by: <a href="http://www.onlinewebfonts.com">oNline Web Fonts</a></p>
            </div>
        );
    }
}

export default App;
