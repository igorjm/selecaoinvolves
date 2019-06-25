import React from 'react';
import { Link } from 'react-router-dom'

import './header.css'

import logo from '../../assets/involves.png'

export default function Header() {
  return (
      <header id="header">
          <div className="headerOpt">
              <Link to="/" className="logo">
                <img src={logo} alt="Logo" />
              </Link>
          </div>
      </header>
  );
}
