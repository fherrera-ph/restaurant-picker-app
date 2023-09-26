// src/List.js
import React, { useState } from 'react';

function List() {
  const [items, setItems] = useState([]);
  const [newItem, setNewItem] = useState('');
  const [apiResult, setApiResult] = useState(null);

  const addItem = () => {
    if (newItem.trim() !== '') {
      setItems([...items, newItem]);
      setNewItem('');
    }
  };

  const removeItem = (index) => {
    const updatedItems = [...items];
    updatedItems.splice(index, 1);
    setItems(updatedItems);
  };

  const handleSubmit = () => {
    const requestParams = items.join(',');
    const apiUrl = process.env.PERSTAURANT_BE_API_URL ||'http://localhost:8080/restaurantpicker/pick?restaurants=' + requestParams;

    fetch(apiUrl)
      .then((response) => response.json())
      .then((data) => {
        setApiResult(data.result)
      })
      .catch((error) => {
        console.error('Error:', error);
      });
  };

  return (
    <div>
      <h1>List the Restaurants where you want to eat</h1>
      <div>
        <input
          type="text"
          placeholder="Add an item"
          value={newItem}
          onChange={(e) => setNewItem(e.target.value)}
        />
        <button onClick={addItem}>Add</button>
      </div>
      <ul>
        {items.map((item, index) => (
          <li key={index}>
            {item}{' '}
            <button onClick={() => removeItem(index)}>Remove</button>
          </li>
        ))}
      </ul>
      <button onClick={handleSubmit}>Submit</button>

      {apiResult !== null && (
        <div>
          <h2>You should eat at</h2>
          <p>{apiResult}</p>
        </div>
      )}

    </div>
  );
}

export default List;