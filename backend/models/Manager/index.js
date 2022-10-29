const { model, Schema } = require('mongoose')

const schema = new Schema({
  firstName: {
    type: Schema.Types.String,
    required: true,
  },
  lastName: {
    type: Schema.Types.String,
    required: true,
  },
  birthDate: {
    type: Schema.Types.Date,
    required: true,
  },
  image: {
    type: Schema.Types.String,
    required: true,
  },
})

module.exports = model('Manager', schema)
