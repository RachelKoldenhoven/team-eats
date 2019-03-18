import React from 'react';
import {shallow} from 'enzyme';

import App from './App';
import ItemAdd from "./ItemAdd";
import ItemList from './ItemList';

describe('App', () => {
    it('renders without crashing', () => {
        // setup
        const appWrapper = shallow(<App/>);
        const itemAdd = appWrapper.find(ItemAdd);
        const itemList = appWrapper.find(ItemList);

        // assert
        expect(itemAdd).toHaveLength(1);
        expect(itemList).toHaveLength(1);
    });

    it('passes props to itemList', () => {
        // setup
        const appWrapper = shallow(<App/>);
        appWrapper.setState({
            items: [
                {text: 'Aloo Ghobi'},
                {text: 'Saag'}
            ]
        });
        const itemList = appWrapper.find(ItemList);
        const itemAdd = appWrapper.find(ItemAdd);

        // assert
        expect(itemList.props().items).toHaveLength(2);
        expect(typeof itemAdd.props().onSave).toEqual('function');
    });

    it('should add new item to list on save', () => {
        // Setup
        const appWrapper = shallow(<App/>);
        const expected = {text: 'Bobotie'};

        // Exercise
        appWrapper.instance().onSave('Bobotie');
        appWrapper.update();
        const itemList = appWrapper.find(ItemList);

        // Assert
        expect(itemList.props().items).toContainEqual(expected);
    });
});


