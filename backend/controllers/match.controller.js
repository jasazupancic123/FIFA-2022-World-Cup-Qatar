const MatchModel = require('../models/Match')
const TeamMode = require('../models/Team')
const JsonUtil = require('../utils/json')
const ManagerModel = require('../models/Manager')

module.exports = class MatchController {
  static async find(req, res, next) {
    try {
      const matches = await MatchModel.find().sort({ date: 1 }) //ce nebo vredu daj -1 namesto 1
      for (let i = 0; i < matches.length; i++) {
        const homeTeam = await TeamMode.findById(matches[i].homeTeam)
        const awayTeam = await TeamMode.findById(matches[i].awayTeam)
        matches[i].homeTeam = homeTeam
        matches[i].awayTeam = awayTeam
        const homeTeamManager = await ManagerModel.findById(homeTeam.manager)
        const awayTeamManager = await ManagerModel.findById(awayTeam.manager)
        matches[i].homeTeam.manager = homeTeamManager
        matches[i].awayTeam.manager = awayTeamManager
      }
      res.json(JsonUtil.response(res, false, 'Successfully found mathces', matches))
    } catch (e) {
      next(e)
    }
  }
  static async findById(req, res, next) {
    try {
      const match = await MatchModel.findById(req.params.id)
      const homeTeam = await TeamMode.findById(match.homeTeam)
      const awayTeam = await TeamMode.findById(match.awayTeam)
      match.homeTeam = homeTeam
      match.awayTeam = awayTeam
      const homeTeamManager = await ManagerModel.findById(homeTeam.manager)
      const awayTeamManager = await ManagerModel.findById(awayTeam.manager)
      match.homeTeam.manager = homeTeamManager
      match.awayTeam.manager = awayTeamManager
      res.json(JsonUtil.response(res, false, 'Successfully found match', match))
    } catch (e) {
      next(e)
    }
  }
  static async findByDate(req, res, next) {
    try {
      const match = await MatchModel.find({ date: req.params.date })
      for (let i = 0; i < matches.length; i++) {
        const homeTeam = await TeamMode.findById(matches[i].homeTeam)
        const awayTeam = await TeamMode.findById(matches[i].awayTeam)
        matches[i].homeTeam = homeTeam
        matches[i].awayTeam = awayTeam
        const homeTeamManager = await ManagerModel.findById(homeTeam.manager)
        const awayTeamManager = await ManagerModel.findById(awayTeam.manager)
        matches[i].homeTeam.manager = homeTeamManager
        matches[i].awayTeam.manager = awayTeamManager
      }
      res.json(JsonUtil.response(res, false, 'Successfully found matches', match))
    } catch (e) {
      next(e)
    }
  }
  static async findByTeam(req, res, next) {
    try {
      const homeMatches = await MatchModel.find({ homeTeam: req.params.team })
      const awayMatches = await MatchModel.find({ awayTeam: req.params.team })
      const matches = homeMatches.concat(awayMatches)
      for (let i = 0; i < matches.length; i++) {
        const homeTeam = await TeamMode.findById(matches[i].homeTeam)
        const awayTeam = await TeamMode.findById(matches[i].awayTeam)
        matches[i].homeTeam = homeTeam
        matches[i].awayTeam = awayTeam
        const homeTeamManager = await ManagerModel.findById(homeTeam.manager)
        const awayTeamManager = await ManagerModel.findById(awayTeam.manager)
        matches[i].homeTeam.manager = homeTeamManager
        matches[i].awayTeam.manager = awayTeamManager
      }
      res.json(JsonUtil.response(res, false, 'Successfully found matches', matches))
    } catch (e) {
      next(e)
    }
  }
  static async findByRoundOrGroup(req, res, next) {
    try {
      const matches = await MatchModel.find({ roundOrGroup: req.params.round })
      for (let i = 0; i < matches.length; i++) {
        const homeTeam = await TeamMode.findById(matches[i].homeTeam)
        const awayTeam = await TeamMode.findById(matches[i].awayTeam)
        matches[i].homeTeam = homeTeam
        matches[i].awayTeam = awayTeam
        const homeTeamManager = await ManagerModel.findById(homeTeam.manager)
        const awayTeamManager = await ManagerModel.findById(awayTeam.manager)
        matches[i].homeTeam.manager = homeTeamManager
        matches[i].awayTeam.manager = awayTeamManager
      }
      res.json(JsonUtil.response(res, false, 'Successfully found matches', matches))
    } catch (e) {
      next(e)
    }
  }

  static async findFinishedMatches(req, res, next) {
    try {
      const matches = await MatchModel.find({ isFinished: true })
      for (let i = 0; i < matches.length; i++) {
        const homeTeam = await TeamMode.findById(matches[i].homeTeam)
        const awayTeam = await TeamMode.findById(matches[i].awayTeam)
        matches[i].homeTeam = homeTeam
        matches[i].awayTeam = awayTeam
        const homeTeamManager = await ManagerModel.findById(homeTeam.manager)
        const awayTeamManager = await ManagerModel.findById(awayTeam.manager)
        matches[i].homeTeam.manager = homeTeamManager
        matches[i].awayTeam.manager = awayTeamManager
      }
      res.json(JsonUtil.response(res, false, 'Successfully found matches', matches))
    } catch (e) {
      next(e)
    }
  }

  static async findUnfinishedMatches(req, res, next) {
    try {
      const matches = await MatchModel.find({ isFinished: false })
      for (let i = 0; i < matches.length; i++) {
        const homeTeam = await TeamMode.findById(matches[i].homeTeam)
        const awayTeam = await TeamMode.findById(matches[i].awayTeam)
        matches[i].homeTeam = homeTeam
        matches[i].awayTeam = awayTeam
        const homeTeamManager = await ManagerModel.findById(homeTeam.manager)
        const awayTeamManager = await ManagerModel.findById(awayTeam.manager)
        matches[i].homeTeam.manager = homeTeamManager
        matches[i].awayTeam.manager = awayTeamManager
      }
      res.json(JsonUtil.response(res, false, 'Successfully found matches', matches))
    } catch (e) {
      next(e)
    }
  }

  static async findFiveUpcomingMatches(req, res, next) {
    try {
      const matches = await MatchModel.find({ isFinished: false }).sort({ date: 1 }).limit(5)
      for (let i = 0; i < matches.length; i++) {
        const homeTeam = await TeamMode.findById(matches[i].homeTeam)
        const awayTeam = await TeamMode.findById(matches[i].awayTeam)
        matches[i].homeTeam = homeTeam
        matches[i].awayTeam = awayTeam
        const homeTeamManager = await ManagerModel.findById(homeTeam.manager)
        const awayTeamManager = await ManagerModel.findById(awayTeam.manager)
        matches[i].homeTeam.manager = homeTeamManager
        matches[i].awayTeam.manager = awayTeamManager
      }
      res.json(JsonUtil.response(res, false, 'Successfully found matches', matches))
    } catch (e) {
      next(e)
    }
  }

  //POST
  static async create(req, res, next) {
    try {
      const match = await MatchModel.create(req.body)
      res.json(JsonUtil.response(res, false, 'Successfully created match', match))
    } catch (e) {
      next(e)
    }
  }

  //PUT
  static async update(req, res, next) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, req.body, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (e) {
      next(e)
    }
  }

  static async updateHomeScore(req, res, next) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { homeTeamScore: req.body.homeTeamScore }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (e) {
      next(e)
    }
  }

  static async updateAwayScore(req, res, next) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { awayTeamScore: req.body.awayTeamScore }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (e) {
      next(e)
    }
  }

  static async updateMinute(req, res, next) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { minute: req.body.minute }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (e) {
      next(e)
    }
  }

  static async updateIsFinished(req, res, next) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { isFinished: req.body.isFinished }, { new: true })
      if (match.homeTeamScore > match.awayTeamScore) {
        const homeTeam = await TeamMode.findById(match.homeTeam)
        const awayTeam = await TeamMode.findById(match.awayTeam)
        homeTeam.points += 3
        homeTeam.matchesWon += 1
        awayTeam.matchesLost += 1
        await homeTeam.save()
        await awayTeam.save()
        match.winner = homeTeam
        await match.save()
      } else if (match.homeTeamScore < match.awayTeamScore) {
        const homeTeam = await TeamMode.findById(match.homeTeam)
        const awayTeam = await TeamMode.findById(match.awayTeam)
        awayTeam.points += 3
        awayTeam.matchesWon += 1
        homeTeam.matchesLost += 1
        await homeTeam.save()
        await awayTeam.save()
        match.winner = awayTeam
        await match.save()
      } else {
        const homeTeam = await TeamMode.findById(match.homeTeam)
        const awayTeam = await TeamMode.findById(match.awayTeam)
        homeTeam.points += 1
        awayTeam.points += 1
        homeTeam.matchesDrawn += 1
        awayTeam.matchesDrawn += 1
        await homeTeam.save()
        await awayTeam.save()
      }
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (e) {
      next(e)
    }
  }

  static async updateIsHalfTime(req, res, next) {
    try {
      console.log('In updateIsHalfTime')
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { isHalfTime: true }, { new: true })
      match.half
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (e) {
      next(e)
    }
  }

  static async updateHalfTimeResumedAt(req, res, next) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { halfTimeResumedAt: new Date(), isHalfTime: false }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (e) {
      next(e)
    }
  }

  static async updateWinner(req, res, next) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { winner: req.body.winner }, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (e) {
      next(e)
    }
  }

  static async updateHasStarted(req, res, next) {
    try {
      const match = await MatchModel.findByIdAndUpdate(req.params.id, { hasStarted: req.body.hasStarted }, { new: true })
      const homeTeam = await TeamMode.findById(match.homeTeam)
      const awayTeam = await TeamMode.findById(match.awayTeam)
      if (match.hasStarted) {
        homeTeam.matchesPlayed++
        awayTeam.matchesPlayed++
      }
      res.json(JsonUtil.response(res, false, 'Successfully updated match', match))
    } catch (e) {
      next(e)
    }
  }

  //DELETE
  static async delete(req, res, next) {
    try {
      const match = await MatchModel.findByIdAndDelete(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully deleted match', match))
    } catch (e) {
      next(e)
    }
  }
}
