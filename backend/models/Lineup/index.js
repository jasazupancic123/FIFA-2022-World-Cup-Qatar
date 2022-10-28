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
  goalkeeper: {
    type: Schema.Types.ObjectId,
    ref: 'Player',
    required: true,
  },
  defenders: {
    type: [Schema.Types.ObjectId],
    ref: 'Player',
    required: true,
  },
  midfielders: {
    type: [Schema.Types.ObjectId],
    ref: 'Player',
    required: true,
  },
  attackers: {
    type: [Schema.Types.ObjectId],
    ref: 'Player',
    required: true,
  },
  substitutes: {
    type: [Schema.Types.ObjectId],
    ref: 'Player',
    required: true,
  },
})

module.exports = model('Lineup', schema)
