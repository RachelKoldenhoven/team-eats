import React from 'react';
import {shallow} from 'enzyme';

import App from './App';
import ItemAdd from "./ItemAdd";

describe('App', () => {
    it('renders without crashing', () => {
        const appWrapper = shallow(<App/>);
        const itemAdd = appWrapper.find(ItemAdd);
        expect(itemAdd).toHaveLength(1);
    });
});


