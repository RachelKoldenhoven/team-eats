import React from 'react';
import {shallow} from 'enzyme';

describe('ItemAdd', () => {
    it('should render a text box and btn for adding items', () => {
        // Setup
        const itemAddWrapper = shallow(<ItemAdd/>);

        // Assert
        expect(itemAddWrapper.find({name: 'item'})).toHaveLength(1);
        expect(itemAddWrapper.find({name: 'save'})).toHaveLength(1);
    });
});