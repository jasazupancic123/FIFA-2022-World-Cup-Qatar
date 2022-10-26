const MatchModel = require('../models/Match')

module.exports = class PlayerController {
  static async find(req, res) {
    try {
      const matches = await MatchModel.find().sort({ date: 1 }) //ce nebo vredu daj -1 namesto 1
      res.json(JsonUtil.response(res, false, 'Successfully found mathces', matches))
    } catch (error) {
      next(e)
    }
  }
  static async findById(req, res) {
    try {
      const match = await MatchModel.findById(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully found match', match))
    } catch (error) {
      next(e)
    }
  }
  static async findByDate(req, res) {
    try {
      const match = await MatchModel.find({ date: req.params.date })
      res.json(JsonUtil.response(res, false, 'Successfully found matches', match))
    } catch (error) {
      next(e)
    }
  }
  static async findByTeam(req, res) {
    try {
      const homeMatches = await MatchModel.find({ homeTeam: req.params.team })
      const awayMatches = await MatchModel.find({ awayTeam: req.params.team })
      const matches = homeMatches.concat(awayMatches)
      res.json(JsonUtil.response(res, false, 'Successfully found matches', matches))
    } catch (error) {
      next(e)
    }
  }
  static async findByRoundOrGroup(req, res) {
    try {
      const matches = await MatchModel.find({ roundOrGroup: req.params.round })
      res.json(JsonUtil.response(res, false, 'Successfully found matches', matches))
    } catch (error) {
      next(e)
    }
  }

  static async findFinishedMatches(req, res) {
    try {
      const matches = await MatchModel.find({ isFinished: true })
      res.json(JsonUtil.response(res, false, 'Successfully found matches', matches))
    } catch (error) {
      next(e)
    }
  }

  static async findUnfinishedMatches(req, res) {
    try {
      const matches = await MatchModel.find({ isFinished: false })
      res.json(JsonUtil.response(res, false, 'Successfully found matches', matches))
    } catch (error) {
      next(e)
    }
  }

  //POST
  static async create(req, res) {
    try {
      const match = await MatchModel.create(req.body)
      res.json(JsonUtil.response(res, false, 'Successfully created match', match))
    } catch (error) {
      next(e)
    }
  }

  //PUT
  static async update(req, res) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, req.body, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async updateHomeScore(req, res) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { homeTeamScore: req.body.homeTeamScore }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async updateAwayScore(req, res) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { awayTeamScore: req.body.awayTeamScore }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async updateMinute(req, res) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { minute: req.body.minute }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async addHomeTeamGoal(req, res) {
    try {
      const match = await MatchModel.findById(req.params.id)
      match.homeTeamGoals.push(req.body.goal)
      await match.save()
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async addAwayTeamGoal(req, res) {
    try {
      const match = await MatchModel.findById(req.params.id)
      match.awayTeamGoals.push(req.body.goal)
      await match.save()
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async addHomeTeamLineup(req, res) {
    try {
      const match = await MatchModel.findById(req.params.id)
      match.homeTeamLineUp = req.body.lineup
      await match.save()
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async addAwayTeamLineup(req, res) {
    try {
      const match = await MatchModel.findById(req.params.id)
      match.awayTeamLineUp = req.body.lineup
      await match.save()
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async updateIsFinished(req, res) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { isFinished: req.body.isFinished }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async updateIsHalfTime(req, res) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { isHalfTime: req.body.isHalfTime }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  static async updateWinner(req, res) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { winner: req.body.winner }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (error) {
      next(e)
    }
  }

  //DELETE
  static async delete(req, res) {
    try {
      const match = await MatchModel.findByIdAndDelete(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully deleted match', match))
    } catch (error) {
      next(e)
    }
  }
}
