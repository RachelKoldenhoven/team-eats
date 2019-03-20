import React from 'react';
import {shallow} from 'enzyme';
import sinon from 'sinon';

import ItemAdd from './ItemAdd';

describe('ItemAdd', () => {
    it('should render a text box and btn for adding items', () => {
        // Setup & Exercise
        const itemAddWrapper = shallow(<ItemAdd/>);

        // Assert
        expect(itemAddWrapper.find({name: 'item'})).toHaveLength(1);
        expect(itemAddWrapper.find({name: 'save'})).toHaveLength(1);
    });

    it('should handle changes to the input', () => {
        // Setup
        const itemAddWrapper = shallow(<ItemAdd/>);
        const event = {target: {name: 'item', value: 'Bobotie'}};

        // Exercise
        itemAddWrapper.find({name: 'item'}).simulate('change', event);

        // Assert
        expect(itemAddWrapper.find({value: 'Bobotie'})).toHaveLength(1);
    });

    it('should send a new person to parent to save', () => {
        // Setup
        const onSave = sinon.spy();
        const itemAddWrapper = shallow(
            <ItemAdd onSave={onSave}/>
        );
        itemAddWrapper.setState({item: 'Quiche'});

        //Exercise
        itemAddWrapper.find({name: 'save'}).simulate('click');

        // Assert
        expect(onSave.calledOnce).toBe(true);
        expect(onSave.calledWith('Quiche')).toBe(true);
        expect(itemAddWrapper.state().item).toEqual('');
    });
});