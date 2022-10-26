var createError = require('http-errors')
var express = require('express')
var path = require('path')
var cookieParser = require('cookie-parser')
var logger = require('morgan')
const cors = require('cors')

var app = express()

app.listen(3000, () => {
  console.log('Listening on http://localhost:3000')
})

const DatabaseUtil = require('./utils/db')
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

module.exports = app
