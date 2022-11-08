const TeamModel = require('../models/Team')
const ManagerModel = require('../models/Manager')
const JsonUtil = require('../utils/json')

module.exports = class TeamController {
  static async find(req, res, next) {
    try {
      const teams = await TeamModel.find()
      for (let i = 0; i < teams.length; i++) {
        const manager = await ManagerModel.findById(teams[i].manager)
        teams[i].manager = manager
      }
    } catch (e) {
      next(e)
    }
  }
  static async findById(req, res, next) {
    try {
      const team = await TeamModel.findById(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully found team', team))
    } catch (e) {
      next(e)
    }
  }
  static async findByName(req, res, next) {
    try {
      const team = await TeamModel.findOne({ name: req.params.name })
      res.json(JsonUtil.response(res, false, 'Successfully found team', team))
    } catch (e) {
      next(e)
    }
  }
  static async findByRegion(req, res, next) {
    try {
      const teams = await TeamModel.find({ region: req.params.region })
      res.json(JsonUtil.response(res, false, 'Successfully found teams', teams))
    } catch (e) {
      next(e)
    }
  }
  static async findByFifaCode(req, res, next) {
    try {
      const team = await TeamModel.findOne({ fifaCode: req.params.fifaCode })
      res.json(JsonUtil.response(res, false, 'Successfully found team', team))
    } catch (e) {
      next(e)
    }
  }
  static async findByIso2(req, res, next) {
    try {
      const team = await TeamModel.findOne({ iso2: req.params.iso2 })
      res.json(JsonUtil.response(res, false, 'Successfully found team', team))
    } catch (e) {
      next(e)
    }
  }
  static async findByGroup(req, res, next) {
    try {
      const teams = await TeamModel.find({ group: req.params.group })
      res.json(JsonUtil.response(res, false, 'Successfully found teams', teams))
    } catch (e) {
      next(e)
    }
  }
  static async findByAtLeastOneTitle(req, res, next) {
    try {
      const teams = await TeamModel.find({ noOfTitles: { $gt: 0 } })
      res.json(JsonUtil.response(res, false, 'Successfully found teams', teams))
    } catch (e) {
      next(e)
    }
  }

  //POST
  static async create(req, res, next) {
    try {
      const {
        name,
        region,
        fifaCode,
        iso2,
        manager,
        flag,
        group,
        noOfTitles,
        isEliminated,
        points,
        goalsScored,
        matchesPlayed,
        matchesWon,
        matchesDrawn,
        matchesLost,
        goalsAgainst,
      } = req.body
      if (!name) throw new Error('Name is required')
      if (!region) throw new Error('Region is required')
      if (!fifaCode) throw new Error('Fifa Code is required')
      if (!iso2) throw new Error('Iso2 is required')
      if (!manager) throw new Error('Manager is required')
      if (!flag) throw new Error('Flag is required')
      if (!group) throw new Error('Group is required')
      const team = await TeamModel.create({
        name: name,
        region: region,
        fifaCode: fifaCode,
        iso2: iso2,
        flag: flag,
        manager: manager,
        group: group,
        noOfTitles: noOfTitles,
        isEliminated: isEliminated,
        points: points,
        goalsScored: goalsScored,
        matchesPlayed: matchesPlayed,
        matchesWon: matchesWon,
        matchesLost: matchesLost,
        matchesDrawn: matchesDrawn,
        goalsAgainst: goalsAgainst,
      })
      res.json(JsonUtil.response(res, false, 'Successfully created team', team))
    } catch (e) {
      next(e)
    }
  }

  //PUT
  static async update(req, res, next) {
    try {
      const {
        name,
        region,
        fifaCode,
        iso2,
        manager,
        flag,
        group,
        noOfTitles,
        isEliminated,
        points,
        goalsScored,
        matchesPlayed,
        matchesWon,
        matchesDrawn,
        matchesLost,
        goalsAgainst,
      } = req.body
      if (!name) throw new Error('Name is required')
      if (!region) throw new Error('Region is required')
      if (!fifaCode) throw new Error('Fifa Code is required')
      if (!manager) throw new Error('Manager is required')
      if (!iso2) throw new Error('Iso2 is required')
      if (!flag) throw new Error('Flag is required')
      if (!group) throw new Error('Group is required')
      const team = await TeamModel.create({
        name: name,
        region: region,
        fifaCode: fifaCode,
        iso2: iso2,
        flag: flag,
        manager: manager,
        group: group,
        noOfTitles: noOfTitles,
        isEliminated: isEliminated,
        points: points,
        goalsScored: goalsScored,
        matchesPlayed: matchesPlayed,
        matchesWon: matchesWon,
        matchesLost: matchesLost,
        matchesDrawn: matchesDrawn,
        goalsAgainst: goalsAgainst,
      })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async updateGoalsScored(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { goalsScored: req.params.goalsScored })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async addGoalsFor(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { $inc: { goalsScored: req.params.goalsScored } })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async addGoalsAgainst(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { $inc: { goalsAgainst: req.params.goalsAgainst } })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async addPoints(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { $inc: { points: req.params.points } })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async addMatchesPlayed(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { $inc: { matchesPlayed: req.params.matchesPlayed } })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async addMatchesWon(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { $inc: { matchesWon: req.params.matchesWon } })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async addMatchesLost(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { $inc: { matchesLost: req.params.matchesLost } })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async addMatchesDrawn(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { $inc: { matchesDrawn: req.params.matchesDrawn } })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async updateNoOfTitles(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { $inc: { noOfTitles: req.params.noOfTitles } })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async updateIsEliminated(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { isEliminated: req.params.isEliminated })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }
  //DELETE
  static async delete(req, res, next) {
    try {
      const team = await TeamModel.deleteOne({ _id: req.params.id })
      res.json(JsonUtil.response(res, false, 'Successfully deleted team', team))
    } catch (e) {
      next(e)
    }
  }
}
