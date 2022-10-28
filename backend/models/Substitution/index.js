const { model, Schema } = require('mongoose')

const schema = new Schema({
  minute: {
    type: Schema.Types.Number,
    required: true,
  },
  playerIn: {
    type: Schema.Types.ObjectId,
    ref: 'Player',
    required: true,
  },
  playerOut: {
    type: Schema.Types.ObjectId,
    ref: 'Player',
    required: true,
  },
  match: {
    type: Schema.Types.ObjectId,
    ref: 'Match',
    required: true,
  },
  team: {
    type: Schema.Types.ObjectId,
    ref: 'Team',
    required: true,
  },
  isHomeTeamSubstitution: {
    type: Schema.Types.Boolean,
    required: true,
  },
})

module.exports = model('Substitution', schema)
