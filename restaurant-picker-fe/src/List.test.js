import React from 'react';
import { render, fireEvent } from '@testing-library/react';
import List from './List';

test('renders List component', () => {
  const { getByText, getByPlaceholderText } = render(<List />);

  // Check if the component renders properly
  const headerElement = getByText('List the Restaurants where you want to eat');
  expect(headerElement).toBeInTheDocument();

  const inputElement = getByPlaceholderText('Add an item');
  const addButton = getByText('Add');
  fireEvent.change(inputElement, { target: { value: 'Test Item' } });
  fireEvent.click(addButton);

  const listItem = getByText('Test Item');
  expect(listItem).toBeInTheDocument();
});
