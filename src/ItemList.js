import React, {Component} from 'react';

import styles from './ItemList.module.css';

export default class ItemList extends Component {
    get items() {
        return this.props.items.map((item, idx) => {
            return <li key={idx}>
                <p>{item.text}</p>
            </li>
        })
    }

    render() {
        return (
            <div>
                <ul className={styles["Item"]}>
                    {this.items}
                </ul>
            </div>
        )
    }
}