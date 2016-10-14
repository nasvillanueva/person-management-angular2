import React from "react";
import ReactDOM from "react-dom";
import { hashHistory } from 'react-router';
import initRoutes from 'modules/routes';
import { initStore } from 'modules/store';

const store = initStore();

ReactDOM.render(initRoutes(store, hashHistory), document.getElementById('app'));
