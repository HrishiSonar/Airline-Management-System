console.log("Inside flightDtls.js");
const exp = require('express');
const mysql = require('mysql2');
const config = require('config');
var connectionDetails = {
    host:config.get("host"),
    user:config.get("user"),
    password: config.get("password"),
    database: config.get("db")
}
const app = exp.Router();
app.get("/", (req, res)=>{
    var connection = mysql.createConnection(connectionDetails);
    var stmt = `select name, class, destination, source, DATE_FORMAT(departure, '%Y-%m-%d %H:%i:%s') departure, DATE_FORMAT(arrival, '%Y-%m-%d %H:%i:%s') arrival from flightDtls`;
    connection.query(stmt, (err, result)=>{
        if(err == null){
            res.send(result);
            console.log(result);
        }
        else{
            res.send(err);
            console.log(err);
        }
    });
    connection.end();
})

module.exports = app;