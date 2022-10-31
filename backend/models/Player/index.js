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
  image: {
    type: Schema.Types.String,
    required: true,
  },
  nationality: {
    type: Schema.Types.ObjectId,
    ref: 'Team',
    required: true,
  },
  position: {
    type: Schema.Types.String,
    required: true,
  },
  shirtNumber: {
    type: Schema.Types.Number,
    required: true,
  },
  birthDate: {
    type: Schema.Types.Date,
    required: true,
  },
  height: {
    type: Schema.Types.Number,
    required: true,
  },
  prefferedFoot: {
    type: Schema.Types.String,
    required: true,
  },
  club: {
    type: Schema.Types.ObjectId,
    ref: 'Club',
    required: true,
  },
  appearances: {
    type: Schema.Types.Number,
    default: 0,
  },
  goals: {
    type: Schema.Types.Number,
    default: 0,
  },
  assists: {
    type: Schema.Types.Number,
    default: 0,
  },
  yellowCards: {
    type: Schema.Types.Number,
    default: 0,
  },
  redCards: {
    type: Schema.Types.Number,
    default: 0,
  },
})

module.exports = model('Player', schema)
