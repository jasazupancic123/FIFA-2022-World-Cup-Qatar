const { model, Schema } = require('mongoose')

const schema = new Schema({
  type: {
    type: Schema.Types.String,
    required: true,
  },
  team: {
    type: Schema.Types.ObjectId,
    ref: 'Team',
    required: true,
  },
  match: {
    type: Schema.Types.ObjectId,
    ref: 'Match',
    required: true,
  },
  goalkeeper: {
    type: Schema.Types.ObjectId,
    ref: 'Player',
    required: true,
  },
  defenders: {
    type: Schema.Types.Array,
    ref: 'Player',
    required: true,
  },
  midfielders: {
    type: Schema.Types.Array,
    ref: 'Player',
    required: true,
  },
  attackers: {
    type: Schema.Types.Array,
    ref: 'Player',
    required: true,
  },
  substitutes: {
    type: Schema.Types.Array,
    ref: 'Player',
    required: true,
  },
})

module.exports = model('Lineup', schema)
