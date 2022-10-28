const { model, Schema } = require('mongoose')

const schema = new Schema({
  name: {
    type: Schema.Types.String,
    required: true,
  },
  shortName: {
    type: Schema.Types.String,
    required: true,
  },
  picture: {
    type: Schema.Types.String,
    required: true,
  },
  league: {
    type: Schema.Types.String,
    required: true,
  },
})

module.exports = model('Club', schema)
