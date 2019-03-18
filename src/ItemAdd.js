import React, {Component} from 'react';

export default class ItemAdd extends Component {
    render() {
        return (
            <div>
                <input name="item"/>
                <button name="save">Save</button>
            </div>
        )
    }
}