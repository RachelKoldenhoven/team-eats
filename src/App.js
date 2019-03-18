import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h4>Team Eats</h4>
                </header>
                <p><a href="http://www.onlinewebfonts.com">oNline Web Fonts</a></p>
            </div>
        );
    }
}

export default App;
