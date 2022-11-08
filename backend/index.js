var createError = require('http-errors')
var express = require('express')
var path = require('path')
var cookieParser = require('cookie-parser')
var logger = require('morgan')

const cors = require('cors')
const bp = require('body-parser')
const request = require('request')
const axios = require('axios')
require('dotenv').config()

var app = express()

app.use(bp.json())
app.use(bp.urlencoded({ extended: true }))

app.listen(process.env.PORT || 3000, () => {
  console.log('Listening on http://localhost:3000')
})

const DatabaseUtil = require('./utils/db')
//const db = new DatabaseUtil('mongodb+srv://jasazupancic:zupancic123@cluster0.g8dt0zk.mongodb.net/?retryWrites=true&w=majority')
const db = new DatabaseUtil('mongodb://localhost:27017/wcqatar2022')
db.connect()

const router = require('./routes/index')
app.use(router)

app.use(logger('dev'))
app.use(express.json())
app.use(express.urlencoded({ extended: false }))
app.use(cookieParser())
app.use(express.static(path.join(__dirname, 'public')))

app.use(function (req, res, next) {
  next(createError(404))
})

//getTeams()

module.exports = app

async function getTeams() {
  // request('http://localhost:3000/team', { json: true }, (err, res, body) => {
  //   if (err) {
  //     return console.log(err)
  //   }
  //   console.log(body)
  // })
}
