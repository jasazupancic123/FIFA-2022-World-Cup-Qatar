const { model, Schema } = require('mongoose')

const schema = new Schema({
  name: {
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
  iso2: {
    type: Schema.Types.String,
    required: true,
  },
  manager: {
    type: Schema.Types.ObjectId,
    ref: 'Manager',
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
  points: {
    type: Schema.Types.Number,
    default: 0,
  },
  goalsScored: {
    type: Schema.Types.Number,
    default: 0,
  },
  matchesPlayed: {
    type: Schema.Types.Number,
    default: 0,
  },
  matchesWon: {
    type: Schema.Types.Number,
    default: 0,
  },
  matchesDrawn: {
    type: Schema.Types.Number,
    default: 0,
  },
  matchesLost: {
    type: Schema.Types.Number,
    default: 0,
  },
  goalsAgainst: {
    type: Schema.Types.Number,
    default: 0,
  },
})

module.exports = model('Team', schema)
