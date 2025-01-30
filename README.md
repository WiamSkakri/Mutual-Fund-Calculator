# Mutual Fund Calculator

## Introduction
Mutual Fund Calculator is a **full-stack web application** designed to help users estimate potential returns on their **mutual fund investments**. Users can:
- Select a **mutual fund ticker**
- Input an **initial investment amount**
- Choose an **investment duration**  
The system then **calculates** the projected **future value** using financial models.

This project serves as an **educational tool** for college students at a **sophomore level** in computer science or finance. By completing this project, students will gain:
- **Full-stack development experience**
- **Financial literacy** in mutual fund trading
- **Hands-on exposure** to API integrations and data modeling

---

## Features
- 📊 **Calculate future value** of mutual fund investments
- 📈 **Fetch mutual fund data** from external APIs
- 🔗 **RESTful API endpoints** for data retrieval
- 🎨 **Interactive UI** for input and result visualization

---

## Technologies Used
### **Backend (Spring Boot)**
- **Java 8**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **RESTful APIs**

### **Frontend (Angular)**
- **Angular CLI**
- **TypeScript**
- **HTML, CSS**
- **Tailwind CSS**

### **APIs Integrated**
- **FRED API** (for market return rates)
- **Newton Analytics API** (for mutual fund beta values)

---

## Key Terminology
- **Mutual Fund** – An investment pool that purchases a diversified portfolio of assets.
  - 📌 *Example: ClearBridge Large Cap Growth Fund*
- **Beta** – Measures volatility compared to the market. 
  - *S&P 500 has a Beta of 1; higher values indicate greater volatility.*
- **Return Rate** – The percentage of gain or loss over an investment period.

📖 **[More Definitions](https://www.investopedia.com/)**

---

## 📈 Mutual Fund Prediction Formula
The **future value (FV)** is calculated using the **Capital Asset Pricing Model (CAPM)**:

\[
FV = P \times e^{r \times t}
\]

Where:
- **P** = Principal (initial investment)
- **r** = Risk-free rate + Beta × (Market return rate - Risk-free rate)
- **t** = Time (years)

### **Market Data Sources**
- **Risk-free rate** → [US Treasury Interest Rate](https://fred.stlouisfed.org/series/DGS10)
- **Market return rate** → Average historical S&P 500 return
- **Beta values** → [Newton Analytics API](https://api.newtonanalytics.com/stock-beta/)

---

## Project Structure

### **Backend (Spring Boot)**
- **Service Layer**: Exposes **RESTful APIs** for frontend requests.
- **Endpoints**:
  - `GET /mutual-funds` → Fetch a list of mutual funds
  - `GET /future-value?ticker={ticker}&amount={amount}&time={time}` → Calculate investment return
- **Hardcoded Mutual Funds**:
  - Sourced from **[MarketWatch Top 25 Funds](https://www.marketwatch.com/tools/top-25-mutual-funds)**

### **Frontend (Angular)**
- **Dropdown Component** → Select mutual fund ticker
- **Input Fields** → Enter investment amount & duration
- **Result Display** → Show predicted future value

---

## 📅 Timeline (4-Week Plan)
| Week  | Task |
|-------|------|
| **Week 1** | Setup project structure & environments |
| **Week 2** | Develop backend API (Spring Boot) |
| **Week 3** | Implement frontend UI (Angular) |
| **Week 4** | Testing, UI enhancements & final presentation |

---

## 📌 Prerequisites
Before running the project, ensure the following tools are installed:

- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows) (or any Java IDE)
- [Git](https://www.jetbrains.com/help/idea/set-up-a-git-repository.html)
- [Angular CLI & Node.js](https://angular.dev/tutorials/first-app)
- [Java 8](https://www.java.com/en/download)

---

## 🚀 Running the Project
### **Backend (Spring Boot)**

Navigate to the `backend` directory:
   ```sh
   cd backend
```markdown
### Build and Run the Application

```sh
./mvnw spring-boot:run
```

API will be accessible at [http://localhost:8080](http://localhost:8080)

### Frontend (Angular)

Navigate to the frontend directory:

```sh
cd frontend
```

Install dependencies:

```sh
npm install
```

Run the Angular app:

```sh
ng serve
```

Open [http://localhost:4200](http://localhost:4200) in a browser.

### ✨ Possible Bonus Features

✅ **Unit Testing**  
- **Backend:** JUnit tests  
- **Frontend:** Jasmine tests  

✅ **Advanced Investment Comparisons**  
- Allow users to compare multiple mutual funds  
- Include ETFs and index funds  

✅ **Enhanced UI**  
- Historical price graphs 📊  
- Investment comparison charts 📈  
- Smooth animations 🎨  

✅ **Database Integration**  
- Store user investments using **SQL**  
- Display investment history in an **AG Grid** or **Graph UI**  
  
🔗 **GitHub Repo:** *Mutual Fund Calculator*  

👥 **Contributors:** 
- **Ephraim Akai-Nettey**
- **Kofi Osei**
- **June Mwenda**
- **Wiam Skakri**  
- **Hiruy Worku**  

### 📜 License  
This project is licensed under the **MIT License** – feel free to use and modify it as needed.  
```
