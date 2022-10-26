const { model, Schema } = require('mongoose')

const schema = new Schema({
  scorer: {
    type: Schema.Types.ObjectId,
    ref: 'Player',
    required: true,
  },
  assister: {
    type: Schema.Types.ObjectId,
    ref: 'Player',
    default: null,
  },
  minute: {
    type: Schema.Types.Number,
    required: true,
  },
  match: {
    type: Schema.Types.ObjectId,
    ref: 'Match',
    required: true,
  },
  isHomeTeamGoal: {
    type: Schema.Types.Boolean,
    required: true,
  },
  isOwnGoal: {
    type: Schema.Types.Boolean,
    default: false,
  },
})

module.exports = model('Goal', schema)
