const express = require('express');
const mongoose = require('mongoose');
const app = express();
const ejs = require('ejs');

app.set('view engine', 'ejs');

mongoose.connect('mongodb+srv://madalinoprea:K4a2E!4yfwDaz-F@cluster0.3qscp.mongodb.net/devDB?retryWrites=true&w=majority');

const fleetSchema = {
    brand: String,
    model: String
}

const Car = mongoose.model("Car", fleetSchema);


app.get('/cars1', (req, res) => {
    Car.find({}, function (err, cars) {
        res.render('cars', {
            carsList: cars
        })
    })
})

app.listen(8080, function () {
    console.log('server is running');
})