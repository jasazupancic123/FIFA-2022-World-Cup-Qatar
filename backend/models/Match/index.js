const { model, Schema } = require('mongoose')

//MOGOCE NAREDIS POSEBEN CLASS STATICTICS PA SE NAVEZUJE NA MATCH PA POL OD TAM ČRPAŠ GOLE PA MINUTE PA TO
const schema = new Schema({
  homeTeam: {
    type: Schema.Types.ObjectId,
    ref: 'Team',
    required: true,
  },
  awayTeam: {
    type: Schema.Types.ObjectId,
    ref: 'Team',
    required: true,
  },
  date: {
    type: Schema.Types.Date,
    required: true,
  },
  stadium: {
    type: Schema.Types.String,
    required: true,
  },
  referee: {
    type: Schema.Types.String,
    default: 'Unknown',
  },
  homeTeamScore: {
    type: Schema.Types.Number,
    default: 0,
  },
  awayTeamScore: {
    type: Schema.Types.Number,
    default: 0,
  },
  roundOrGroup: {
    type: Schema.Types.String,
    required: true,
  },
  minute: {
    type: Schema.Types.Number,
    default: 0,
  },
  isFinished: {
    type: Schema.Types.Boolean,
    default: false,
  },
  hasStarted: {
    type: Schema.Types.Boolean,
    default: false,
  },
  isHalfTime: {
    type: Schema.Types.Boolean,
    default: false,
  },
  winner: {
    type: Schema.Types.ObjectId,
    ref: 'Team',
    default: null, //ce ostane null pol je draw
  },
})

module.exports = model('Match', schema)
