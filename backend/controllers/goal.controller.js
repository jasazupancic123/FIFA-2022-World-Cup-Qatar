const GoalModel = require('../models/Goal')
const JsonUtil = require('../utils/json')

const CountryController = require('./country.controller')
const PlayerController = require('./player.controller')
const MatchController = require('./match.controller')

module.exports = class GoalController {
  static async find(req, res, next) {
    try {
      const goals = await GoalModel.find()
      res.json(JsonUtil.response(res, false, 'Successfully found goals', goals))
    } catch (e) {
      next(e)
    }
  }
  static async findById(req, res, next) {
    try {
      const goal = await GoalModel.findById(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully found goal', goal))
    } catch (e) {
      next(e)
    }
  }
  static async findByScorer(req, res, next) {
    try {
      const goals = await GoalModel.find({ scorer: req.params.scorer })
      res.json(JsonUtil.response(res, false, 'Successfully found goals', goals))
    } catch (e) {
      next(e)
    }
  }
  static async findByAssister(req, res, next) {
    try {
      const goals = await GoalModel.find({ assister: req.params.assister })
      res.json(JsonUtil.response(res, false, 'Successfully found goals', goals))
    } catch (e) {
      next(e)
    }
  }

  static async findByMinute(req, res, next) {
    try {
      const goals = await GoalModel.find({ minute: req.params.minute })
      res.json(JsonUtil.response(res, false, 'Successfully found goals', goals))
    } catch (e) {
      next(e)
    }
  }

  static async findByMatch(req, res, next) {
    try {
      const goals = await GoalModel.find({ match: req.params.match })
      res.json(JsonUtil.response(res, false, 'Successfully found goals', goals))
    } catch (e) {
      next(e)
    }
  }

  static async findByMatchAndHomeTeam(req, res, next) {
    try {
      const goals = await GoalModel.find({ match: req.params.match, homeTeam: true })
      res.json(JsonUtil.response(res, false, 'Successfully found goals', goals))
    } catch (e) {
      next(e)
    }
  }

  static async findByMatchAndAwayTeam(req, res, next) {
    try {
      const goals = await GoalModel.find({ match: req.params.match, homeTeam: false })
      res.json(JsonUtil.response(res, false, 'Successfully found goals', goals))
    } catch (e) {
      next(e)
    }
  }

  //POST
  static async createAndUpdateEverything(req, res, next) {
    try {
      const goal = await GoalModel.create(req.body)
      if (!goal.isOwnGoal) {
        const scorer = await PlayerController.findById(goal.scorer)
        PlayerController.updateGoals(scorer, scorer.goals + 1)
      }
      if (assister != null) {
        const assister = await PlayerController.findById(goal.assister)
        PlayerController.updateAssists(assister, assister.assists + 1)
      }
      const match = await MatchController.findById(goal.match)
      const homeTeam = await CountryController.findById(match.homeTeam)
      const awayTeam = await CountryController.findById(match.awayTeam)
      if (goal.isHomeTeamGoal) {
        MatchController.updateHomeScore(match, match.homeTeamGoals + 1)
        MatchController.addHomeTeamGoal(match, goal)
        CountryController.updateGoalsScored(homeTeam, homeTeam.goals + 1)
      } else {
        MatchController.updateAwayScore(match, match.awayTeamGoals + 1)
        MatchController.addAwayTeamGoal(match, goal)
        CountryController.updateGoalsScored(awayTeam, awayTeam.goals + 1)
      }
      res.json(JsonUtil.response(res, false, 'Successfully created goal', goal))
    } catch (e) {
      next(e)
    }
  }

  static async create(req, res, next) {
    try {
      const goal = await GoalModel.create(req.body)
      res.json(JsonUtil.response(res, false, 'Successfully created goal', goal))
    } catch (e) {
      next(e)
    }
  }

  //PUT
  static async update(req, res, next) {
    try {
      const goal = await GoalModel.findByIdAndUpdate(req.params.id, req.body, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated goal', goal))
    } catch (e) {
      next(e)
    }
  }

  //DELETE
  static async delete(req, res, next) {
    try {
      const goal = await GoalModel.findByIdAndDelete(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully deleted goal', goal))
    } catch (e) {
      next(e)
    }
  }
}
