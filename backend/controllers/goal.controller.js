const GoalModel = require('../models/Goal')
const JsonUtil = require('../utils/json')
const PlayerModel = require('../models/Player')
const MatchModel = require('../models/Match')
const TeamModel = require('../models/Team')

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
      const goalBody = req.body
      if (!goalBody.isOwnGoal) {
        const scorer = await PlayerModel.findById(goalBody.scorer)
        scorer.goals += 1
        await scorer.save()
      }
      if (goalBody.assister != null) {
        const assister = await PlayerModel.findById(goalBody.assister)
        assister.assists += 1
        await assister.save()
      }
      const goal = await GoalModel.create(goalBody)
      const match = await MatchModel.findById(goal.match)
      if (goal.isHomeTeamGoal) {
        match.homeTeamScore += 1
        await match.save()
        const homeTeam = await TeamModel.findById(match.homeTeam)
        homeTeam.goalsScored += 1
        await homeTeam.save()

        const awayTeam = await TeamModel.findById(match.awayTeam)
        awayTeam.goalsAgainst += 1
        await awayTeam.save()
      } else {
        match.awayTeamScore += 1
        await match.save()
        const awayTeam = await TeamModel.findById(match.awayTeam)
        awayTeam.goalsScored += 1
        await awayTeam.save()

        const homeTeam = await TeamModel.findById(match.homeTeam)
        homeTeam.goalsAgainst += 1
        await homeTeam.save()
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
