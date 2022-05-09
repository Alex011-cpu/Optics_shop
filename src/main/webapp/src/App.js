import React, { useEffect, useState } from 'react';
import {
    BrowserRouter as Router,
} from "react-router-dom";

import AppRoutes from "./routes/AppRoutes";




export default function App() {


  return (
      <div>
          <Router>
              <AppRoutes/>
          </Router>
      </div>
  );
}
