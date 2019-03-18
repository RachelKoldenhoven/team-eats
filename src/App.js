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
        }
    }

    onSave = (item) => {
        const newState = JSON.parse(JSON.stringify(this.state));
        newState.items.push({text: item});
        this.setState(newState);
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
                <p>Logo by: <a href="http://www.onlinewebfonts.com">oNline Web Fonts</a></p>
            </div>
        );
    }
}

export default App;
