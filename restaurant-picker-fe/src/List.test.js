import React from 'react';
import { render, fireEvent, screen } from '@testing-library/react';
import { setupServer } from 'msw/node';
import { rest } from 'msw'; 
import List from './List';

const server = setupServer(
  rest.get('http://localhost:8080/restaurantpicker/pick?restaurants=', (req, res, ctx) => {
    return res(
      ctx.status(200),
      ctx.json({ result: 'Mocked API response' })
    );
  })
);

beforeAll(() => {
  server.listen();
});

afterEach(() => {
  server.resetHandlers();
});

afterAll(() => {
  server.close();
});

test('renders List component', async () => {
  render(<List />);

  const headerElement = screen.getByText('List the Restaurants where you want to eat');
  expect(headerElement).toBeInTheDocument();

  const inputElement = screen.getByPlaceholderText('Add an item');
  const addButton = screen.getByText('Add');
  fireEvent.change(inputElement, { target: { value: 'Test Item' } });
  fireEvent.click(addButton);

  const listItem = screen.getByText('Test Item');
  expect(listItem).toBeInTheDocument();


  server.use(
    rest.get('http://localhost:8080/restaurantpicker/pick?restaurants=', (req, res, ctx) => {
      return res(
        ctx.status(200),
        ctx.json({ result: 'Mocked API response' })
      );
    })
  );

  const submitButton = screen.getByText('Submit');
  fireEvent.click(submitButton);

  // Check if the API result is displayed
  const apiResult = await screen.findByText('You should eat at');
  expect(apiResult).toBeInTheDocument();
  expect(apiResult).toHaveTextContent('Mocked API response');
});
