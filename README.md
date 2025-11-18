# Mutual Fund Calculator

A full-stack web application for calculating mutual fund investment returns using the Capital Asset Pricing Model (CAPM).

## Live Demo

**[View Live Application](https://invigorating-enthusiasm-production.up.railway.app)**

<!-- Add demo GIF or video here - see instructions below -->

## Overview

This application helps users estimate potential returns on mutual fund investments by:
- Selecting from 12 pre-configured mutual funds
- Inputting an initial investment amount
- Choosing an investment time horizon
- Calculating projected future value using CAPM

The calculator fetches real-time beta values from the Newton Analytics API and displays results with an interactive chart showing year-by-year growth.

## Tech Stack

**Backend**
- Java 17
- Spring Boot 3.3.7
- Spring Data JPA
- PostgreSQL
- RESTful API

**Frontend**
- Angular 19
- TypeScript
- Tailwind CSS
- Angular Material
- Chart.js

**Deployment**
- Railway (PostgreSQL, Backend, Frontend)
- Docker containers

## Features

- Real-time beta value fetching from external API
- Interactive Chart.js visualization of investment growth
- 12 mutual funds from major providers (Vanguard, JPMorgan, T. Rowe Price, etc.)
- Responsive UI with Goldman Sachs-inspired design
- RESTful API with CORS support

## CAPM Formula

The future value is calculated using:

```
FV = P × e^(r × t)
```

Where:
- **P** = Principal (initial investment)
- **r** = Risk-free rate + Beta × (Market return rate - Risk-free rate)
- **t** = Time (years)

## Local Development

### Prerequisites
- Java 17+
- Node.js 20+
- PostgreSQL
- Maven

### Backend Setup

```bash
cd backend
./mvnw spring-boot:run
```

Backend runs on http://localhost:8095

### Frontend Setup

```bash
cd frontend
npm install
npm start
```

Frontend runs on http://localhost:4200

### Database Setup

Create PostgreSQL database and import seed data:

```bash
psql -U postgres -c "CREATE DATABASE mutualfunds;"
psql -U postgres -d mutualfunds -f backend/init.sql
```

## API Endpoints

- `GET /mutualfunds/requests/allFunds` - Retrieve all mutual funds
- `POST /mutualfunds/requests/calculate/futureValue` - Calculate investment returns
- `GET /mutualfunds/requests/byId?id={id}` - Get fund by ID

## Contributors

- Ephraim Akai-Nettey
- Kofi Osei
- June Mwenda
- Wiam Skakri
- Hiruy Worku

## License

MIT License - feel free to use and modify as needed.
