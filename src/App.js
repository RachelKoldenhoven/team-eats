import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

import ItemAdd from './ItemAdd';

class App extends Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h2>Team Eats</h2>
                </header>
                <ItemAdd/>
                <p>Logo by: <a href="http://www.onlinewebfonts.com">oNline Web Fonts</a></p>
            </div>
        );
    }
}

export default App;
