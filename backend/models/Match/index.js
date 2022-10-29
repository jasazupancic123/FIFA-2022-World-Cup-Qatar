const { model, Schema } = require('mongoose')

//MOGOCE NAREDIS POSEBEN CLASS STATICTICS PA SE NAVEZUJE NA MATCH PA POL OD TAM ČRPAŠ GOLE PA MINUTE PA TO
const schema = new Schema({
  homeTeam: {
    type: Schema.Types.ObjectId,
    ref: 'Club',
    required: true,
  },
  awayTeam: {
    type: Schema.Types.ObjectId,
    ref: 'Club',
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
  homeTeamGoals: {
    //array ki vsebuje class goals
    type: [Schema.Types.ObjectId],
    ref: 'Goal',
    default: [],
  },
  awayTeamGoals: {
    //array ki vsebuje class goals
    type: [Schema.Types.ObjectId],
    ref: 'Goal',
    default: [],
  },
  homeTeamSubstitutions: {
    type: [Schema.Types.ObjectId],
    ref: 'Substitution',
    default: [],
  },
  awayTeamSubstitutions: {
    type: [Schema.Types.ObjectId],
    ref: 'Substitution',
    default: [],
  },
  homeTeamLineUp: {
    type: Schema.Types.ObjectId,
    ref: 'LineUp',
    default: null,
  },
  awayTeamLineUp: {
    type: Schema.Types.ObjectId,
    ref: 'LineUp',
    default: null,
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
    ref: 'Country',
    default: null, //ce ostane null pol je draw
  },
})

module.exports = model('Match', schema)
