const { connect: _connect } = require('mongoose')

module.exports = class DatabaseUtil {
  constructor(url) {
    this.url = url
  }

  connect() {
    const options = {
      useNewUrlParser: true,
      useUnifiedTopology: true,
      serverSelectionTimeoutMS: 5000,
      autoIndex: false,
      maxPoolSize: 10,
      serverSelectionTimeoutMS: 5000,
      socketTimeoutMS: 45000,
      family: 4,
    }

    var mongoose = require('mongoose')
    mongoose.connect(this.url, options).then(() => console.log('DB is connected'))
    mongoose.Promise = global.Promise
    var db = mongoose.connection
    db.on('error', console.error.bind(console, 'MongoDB connection error:'))
  }
}
