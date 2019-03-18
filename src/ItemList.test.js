import React from 'react';
import {shallow} from 'enzyme';

describe('ItemList', () => {
    it('should display the list of items', () => {
        // setup & exercise
        const expected = [
            {text: 'Aloo Ghobi'},
            {text: 'Saag'}
        ];
        const itemListWrap = shallow(<ItemList items={expected}/>);

        // assert
        expect(itemListWrap.find('li')).toHaveLength(2);
    });
});