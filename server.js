
// db.js - MySQL Connection with password 1234

const mysql = require("mysql2");

const db = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "1234",
    database: "dream_journal"
});

db.connect(err => {
    if (err) throw err;
    console.log("MySQL Connected (password 1234)");
});

module.exports = db;
const express = require("express");
const mysql = require("mysql2");
const app = express();


app.use(express.json());

// Connect MySQL
const db = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "your_password",
    database: "dream_journal"
});

// Test connection
db.connect(err => {
    if (err) throw err;
    console.log("MySQL Connected!");
});

// Get all entries
app.get("/entries", (req, res) => {
    db.query("SELECT * FROM entries", (err, results) => {
        if (err) throw err;
        res.json(results);
    });
});

// Add entry
app.post("/entries", (req, res) => {
    const { date, theme, description } = req.body;

    const sql = "INSERT INTO entries (date, theme, description) VALUES (?, ?, ?)";
    db.query(sql, [date, theme, description], err => {
        if (err) throw err;
        res.json({ message: "Entry added!" });
    });
});

// Search by theme
app.get("/entries/search/:theme", (req, res) => {
    const theme = req.params.theme;
    const sql = "SELECT * FROM entries WHERE theme LIKE ? OR description LIKE ?";
    
    db.query(sql, [`%${theme}%`, `%${theme}%`], (err, results) => {
        if (err) throw err;
        res.json(results);
    });
});

app.listen(3000, () => console.log("Server running on port 3000"));
