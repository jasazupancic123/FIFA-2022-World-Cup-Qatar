const SubstitionModel = require('../models/Substitution')
const PlayerModel = require('../models/Player')
const ManagerModel = require('../models/Match')
const TeamModel = require('../models/Team')
const MatchModel = require('../models/Match')

const JsonUtil = require('../utils/json')

module.exports = class SubstitutionController {
  static async find(req, res, next) {
    try {
      const substitutions = await SubstitionModel.find()
      for (let i = 0; i < substitutions.length; i++) {
        substitutions[i].team = await TeamModel.findById(substitutions[i].team)
        const manager = await ManagerModel.findById(substitutions[i].playerIn.team.manager)
        substitutions[i].team.manager = manager

        substitutions[i].playerIn = await PlayerModel.findById(substitutions[i].playerIn)
        substitutions[i].playerIn.team = substitutions[i].team
        substitutions[i].playerIn.team.manager = manager

        substitutions[i].playerOut = await PlayerModel.findById(substitutions[i].playerOut)
        substitutions[i].playerOut.team = substitutions[i].team
        substitutions[i].playerOut.team.manager = manager

        substitutions[i].match = await MatchModel.findById(substitutions[i].match)
        substitutions[i].match.homeTeam = await TeamModel.findById(substitutions[i].match.homeTeam)
        substitutions[i].match.homeTeam.manager = await ManagerModel.findById(substitutions[i].match.homeTeam.manager)
        substitutions[i].match.awayTeam = await TeamModel.findById(substitutions[i].match.awayTeam)
        substitutions[i].match.awayTeam.manager = await ManagerModel.findById(substitutions[i].match.awayTeam.manager)
      }
      res.json(JsonUtil.response(res, false, 'Successfully found substitutions', substitutions))
    } catch (e) {
      next(e)
    }
  }
  static async findById(req, res, next) {
    try {
      const substitution = await SubstitionModel.findById(req.params.id)
      substitution.team = await TeamModel.findById(substitution.team)
      const manager = await ManagerModel.findById(substitution.playerIn.team.manager)
      substitution.team.manager = manager

      substitution.playerIn = await PlayerModel.findById(substitution.playerIn)
      substitution.playerIn.team = substitution.team
      substitution.playerIn.team.manager = manager

      substitution.playerOut = await PlayerModel.findById(substitution.playerOut)
      substitution.playerOut.team = substitution.team
      substitution.playerOut.team.manager = manager

      substitution.match = await MatchModel.findById(substitution.match)
      substitution.match.homeTeam = await TeamModel.findById(substitution.match.homeTeam)
      substitution.match.homeTeam.manager = await ManagerModel.findById(substitution.match.homeTeam.manager)
      substitution.match.awayTeam = await TeamModel.findById(substitution.match.awayTeam)
      substitution.match.awayTeam.manager = await ManagerModel.findById(substitution.match.awayTeam.manager)
      res.json(JsonUtil.response(res, false, 'Successfully found substitution', substitution))
    } catch (e) {
      next(e)
    }
  }
  static async findByMatch(req, res, next) {
    try {
      if (!req.params.match) res.json(JsonUtil.response(res, true, 'Please provide match', null))
      const substitutions = await SubstitionModel.find({ match: req.params.match })
      for (let i = 0; i < substitutions.length; i++) {
        substitutions[i].team = await TeamModel.findById(substitutions[i].team)
        const manager = await ManagerModel.findById(substitutions[i].playerIn.team.manager)
        substitutions[i].team.manager = manager

        substitutions[i].playerIn = await PlayerModel.findById(substitutions[i].playerIn)
        substitutions[i].playerIn.team = substitutions[i].team
        substitutions[i].playerIn.team.manager = manager

        substitutions[i].playerOut = await PlayerModel.findById(substitutions[i].playerOut)
        substitutions[i].playerOut.team = substitutions[i].team
        substitutions[i].playerOut.team.manager = manager

        substitutions[i].match = await MatchModel.findById(substitutions[i].match)
        substitutions[i].match.homeTeam = await TeamModel.findById(substitutions[i].match.homeTeam)
        substitutions[i].match.homeTeam.manager = await ManagerModel.findById(substitutions[i].match.homeTeam.manager)
        substitutions[i].match.awayTeam = await TeamModel.findById(substitutions[i].match.awayTeam)
        substitutions[i].match.awayTeam.manager = await ManagerModel.findById(substitutions[i].match.awayTeam.manager)
      }
      res.json(JsonUtil.response(res, false, 'Successfully found substitutions', substitutions))
    } catch (e) {
      next(e)
    }
  }
  static async findByTeam(req, res, next) {
    try {
      if (!req.params.team) res.json(JsonUtil.response(res, true, 'Please provide team', null))
      const substitutions = await SubstitionModel.find({ team: req.params.team })
      for (let i = 0; i < substitutions.length; i++) {
        substitutions[i].team = await TeamModel.findById(substitutions[i].team)
        const manager = await ManagerModel.findById(substitutions[i].playerIn.team.manager)
        substitutions[i].team.manager = manager

        substitutions[i].playerIn = await PlayerModel.findById(substitutions[i].playerIn)
        substitutions[i].playerIn.team = substitutions[i].team
        substitutions[i].playerIn.team.manager = manager

        substitutions[i].playerOut = await PlayerModel.findById(substitutions[i].playerOut)
        substitutions[i].playerOut.team = substitutions[i].team
        substitutions[i].playerOut.team.manager = manager

        substitutions[i].match = await MatchModel.findById(substitutions[i].match)
        substitutions[i].match.homeTeam = await TeamModel.findById(substitutions[i].match.homeTeam)
        substitutions[i].match.homeTeam.manager = await ManagerModel.findById(substitutions[i].match.homeTeam.manager)
        substitutions[i].match.awayTeam = await TeamModel.findById(substitutions[i].match.awayTeam)
        substitutions[i].match.awayTeam.manager = await ManagerModel.findById(substitutions[i].match.awayTeam.manager)
      }
      res.json(JsonUtil.response(res, false, 'Successfully found substitutions', substitutions))
    } catch (e) {
      next(e)
    }
  }
  static async findByMatchAndTeam(req, res, next) {
    try {
      if (!req.params.match || !req.params.team) res.json(JsonUtil.response(res, true, 'Please provide match and team', null))
      const substitutions = await SubstitionModel.find({ match: req.params.match, team: req.params.team })
      for (let i = 0; i < substitutions.length; i++) {
        substitutions[i].team = await TeamModel.findById(substitutions[i].team)
        const manager = await ManagerModel.findById(substitutions[i].playerIn.team.manager)
        substitutions[i].team.manager = manager

        substitutions[i].playerIn = await PlayerModel.findById(substitutions[i].playerIn)
        substitutions[i].playerIn.team = substitutions[i].team
        substitutions[i].playerIn.team.manager = manager

        substitutions[i].playerOut = await PlayerModel.findById(substitutions[i].playerOut)
        substitutions[i].playerOut.team = substitutions[i].team
        substitutions[i].playerOut.team.manager = manager

        substitutions[i].match = await MatchModel.findById(substitutions[i].match)
        substitutions[i].match.homeTeam = await TeamModel.findById(substitutions[i].match.homeTeam)
        substitutions[i].match.homeTeam.manager = await ManagerModel.findById(substitutions[i].match.homeTeam.manager)
        substitutions[i].match.awayTeam = await TeamModel.findById(substitutions[i].match.awayTeam)
        substitutions[i].match.awayTeam.manager = await ManagerModel.findById(substitutions[i].match.awayTeam.manager)
      }
      res.json(JsonUtil.response(res, false, 'Successfully found substitutions', substitutions))
    } catch (e) {
      next(e)
    }
  }

  static async createAndUpdateEverything(req, res, next) {
    try {
      if (!req.body.playerIn || !req.body.playerOut || !req.body.match || !req.body.team || !req.body.minute)
        res.json(JsonUtil.response(res, true, 'Please provide playerIn, playerOut, match and team', null))
      const playerIn = await PlayerModel.findById(req.body.playerIn)
      playerIn.appearances += 1
      await playerIn.save()
      const playerOut = await PlayerModel.findById(req.body.playerOut)
      const substitution = await SubstitionModel.create({
        minute: req.body.minute,
        playerIn,
        playerOut,
        match: req.body.match,
        team: req.body.team,
        isHomeTeamSubstitution: req.body.isHomeTeamSubstitution,
      })
      res.json(JsonUtil.response(res, false, 'Successfully created substitution', substitution))
    } catch (e) {
      next(e)
    }
  }

  //POST
  static async create(req, res, next) {
    try {
      if (!req.body.playerIn || !req.body.playerOut || !req.body.match || !req.body.team || !req.body.minute)
        res.json(JsonUtil.response(res, true, 'Please provide playerIn, playerOut, match and team', null))
      const playerIn = await PlayerModel.findById(req.body.playerIn)
      const playerOut = await PlayerModel.findById(req.body.playerOut)
      const substitution = await SubstitionModel.create({
        minute: req.body.minute,
        playerIn,
        playerOut,
        match: req.body.match,
        team: req.body.team,
        isHomeTeamSubstitution: req.body.isHomeTeamSubstitution,
      })
      res.json(JsonUtil.response(res, false, 'Successfully created substitution', substitution))
    } catch (e) {
      next(e)
    }
  }

  //PUT
  static async update(req, res, next) {
    try {
      if (!req.body.playerIn || !req.body.playerOut || !req.body.match || !req.body.team || !req.body.minute)
        res.json(JsonUtil.response(res, true, 'Please provide playerIn, playerOut, match and team', null))
      const playerIn = await PlayerModel.findById(req.body.playerIn)
      const playerOut = await PlayerModel.findById(req.body.playerOut)
      const substitution = await SubstitionModel.findByIdAndUpdate(req.params.id, {
        minute: req.body.minute,
        playerIn,
        playerOut,
        match: req.body.match,
        team: req.body.team,
        isHomeTeamSubstitution: req.body.isHomeTeamSubstitution,
      })
      res.json(JsonUtil.response(res, false, 'Successfully updated substitution', substitution))
    } catch (e) {
      next(e)
    }
  }

  //DELETE
  static async delete(req, res, next) {
    try {
      const substitution = await SubstitionModel.findByIdAndDelete(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully deleted substitution', substitution))
    } catch (e) {
      next(e)
    }
  }
}
