const { model, Schema } = require('mongoose')

const schema = new Schema({
  name: {
    type: Schema.Types.String,
    required: true,
  },
  picture: {
    type: Schema.Types.String,
    required: true,
  },
  region: {
    type: Schema.Types.String,
    required: true,
  },
  fifaCode: {
    type: Schema.Types.String,
    required: true,
  },
  manager: {
    type: Schema.Types.String,
    required: true,
  },
  managerPicture: {
    type: Schema.Types.String,
    required: true,
  },
  iso2: {
    type: Schema.Types.String,
    required: true,
  },
  flag: {
    type: Schema.Types.String,
    required: true,
  },
  group: {
    type: Schema.Types.String,
    required: true,
  },
  noOfTitles: {
    type: Schema.Types.Number,
    default: 0,
  },
  isEliminated: {
    type: Schema.Types.Boolean,
    default: false,
  },
  goalsScored: {
    type: Schema.Types.Number,
    default: 0,
  },
})

module.exports = model('Country', schema)
