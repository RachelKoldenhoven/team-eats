import React from 'react';
import {shallow} from 'enzyme';
import sinon from 'sinon';
import fetchMock from 'fetch-mock';

import App from './App';
import ItemAdd from './ItemAdd';
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

    it('should add new item to list on save', async () => {
        // Setup
        const appWrapper = shallow(<App/>);
        appWrapper.instance().getItems = sinon.spy();
        const expected = {text: 'Bobotie'};
        const url = '/api/items';
        fetchMock
            .post('/api/items', 200);

        // Exercise
        await appWrapper.instance().onSave('Bobotie');

        // Assert
        expect(fetchMock.called()).toEqual(true);
        expect(fetchMock.called(url, expected)).toEqual(true);
        expect(appWrapper.instance().getItems.calledOnce).toBe(true);

        // Teardown
        fetchMock.reset();
    });

    it('should call fetch in getItems', async () => {
        // Setup
        const appWrapper = shallow(<App/>);
        const url = '/api/items';
        const items = {
            body: [{text: 'Aloo Ghobi'}, {text: 'Saag'}],
            headers: {'content-type': 'application/json'}
        };
        const expected = [{text: 'Aloo Ghobi'}, {text: 'Saag'}];
        fetchMock.get(url, items);

        // Exercise
        await appWrapper.instance().getItems();

        // Assert
        expect(fetchMock.called()).toEqual(true);
        expect(fetchMock.called(url)).toEqual(true);
        expect(appWrapper.state().items).toEqual(expected);

        // Teardown
        fetchMock.reset();
    });
});


