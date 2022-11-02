const SubstitionModel = require('../models/Substitution')
const PlayerModel = require('../models/Player')
const MatchModel = require('../models/Match')

const JsonUtil = require('../utils/json')

module.exports = class SubstitutionController {
  static async find(req, res, next) {
    try {
      const substitutions = await SubstitionModel.find()
      res.json(JsonUtil.response(res, false, 'Successfully found substitutions', substitutions))
    } catch (e) {
      next(e)
    }
  }
  static async findById(req, res, next) {
    try {
      const substitution = await SubstitionModel.findById(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully found substitution', substitution))
    } catch (e) {
      next(e)
    }
  }
  static async findByMatch(req, res, next) {
    try {
      if (!req.params.match) res.json(JsonUtil.response(res, true, 'Please provide match', null))
      const substitutions = await SubstitionModel.find({ match: req.params.match })
      res.json(JsonUtil.response(res, false, 'Successfully found substitutions', substitutions))
    } catch (e) {
      next(e)
    }
  }
  static async findByTeam(req, res, next) {
    try {
      if (!req.params.team) res.json(JsonUtil.response(res, true, 'Please provide team', null))
      const substitutions = await SubstitionModel.find({ team: req.params.team })
      res.json(JsonUtil.response(res, false, 'Successfully found substitutions', substitutions))
    } catch (e) {
      next(e)
    }
  }
  static async findByMatchAndTeam(req, res, next) {
    try {
      if (!req.params.match || !req.params.team) res.json(JsonUtil.response(res, true, 'Please provide match and team', null))
      const substitutions = await SubstitionModel.find({ match: req.params.match, team: req.params.team })
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
