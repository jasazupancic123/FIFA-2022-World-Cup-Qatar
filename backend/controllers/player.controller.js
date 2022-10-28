const PlayerModel = require('../models/Player')
const JsonUtil = require('../utils/json')

const { default: mongoose } = require('mongoose')

module.exports = class PlayerController {
  static async find(req, res, next) {
    try {
      const players = await PlayerModel.find()
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }
  static async findById(req, res, next) {
    try {
      const player = await PlayerModel.findById(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully found player', player))
    } catch (e) {
      next(e)
    }
  }
  static async findByName(req, res, next) {
    try {
      if (!req.params.firstName || !req.params.lastName) res.json(JsonUtil.response(res, true, 'Please provide first name and last name', null))
      const player = await PlayerModel.findOne({ firstName: req.params.firstName, lastName: req.params.lastName })
      res.json(JsonUtil.response(res, false, 'Successfully found player', player))
    } catch (e) {
      next(e)
    }
  }
  static async findByTeam(req, res, next) {
    try {
      if (!req.params.team) res.json(JsonUtil.response(res, true, 'Please provide team', null))
      const players = await PlayerModel.find({ nationality: req.params.team })
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }
  static async findByClub(req, res, next) {
    try {
      if (!req.params.club) res.json(JsonUtil.response(res, true, 'Please provide club', null))
      const players = await PlayerModel.find({ club: req.params.club })
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }
  static async findByPosition(req, res, next) {
    try {
      if (!req.params.position) res.json(JsonUtil.response(res, true, 'Please provide position', null))
      const players = await PlayerModel.find({ position: req.params.position })
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }
  static async findByTeamAndPosition(req, res, next) {
    try {
      if (!req.params.team) res.json(JsonUtil.response(res, true, 'Please provide team', null))
      if (!req.params.position) res.json(JsonUtil.response(res, true, 'Please provide position', null))
      const players = await PlayerModel.find({ position: req.params.position, nationality: req.params.team })
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }
  static async findByMostGoals(req, res, next) {
    try {
      const players = await PlayerModel.find().sort({ goals: -1 })
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }
  static async findByMostAssists(req, res, next) {
    try {
      const players = await PlayerModel.find().sort({ assists: -1 })
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }
  static async findByMostYellowCards(req, res, next) {
    try {
      const players = await PlayerModel.find().sort({ yellowCards: -1 })
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }
  static async findByMostRedCards(req, res, next) {
    try {
      const players = await PlayerModel.find().sort({ redCards: -1 })
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }
  static async findYoungestByTeam(req, res, next) {
    try {
      if (!req.params.team) res.json(JsonUtil.response(res, true, 'Please provide team', null))
      const players = await PlayerModel.find({ team: req.params.team }).sort({ age: 1 })
      res.json(JsonUtil.response(res, false, 'Successfully found players', players))
    } catch (e) {
      next(e)
    }
  }

  //CUSTOM GET
  static async findByIdFunction(id) {
    try {
      const player = await PlayerModel.findById(id)
      return player
    } catch (e) {
      return e
    }
  }

  //POST
  static async create(req, res, next) {
    try {
      const player = await PlayerModel.create(req.body)
      res.json(JsonUtil.response(res, false, 'Successfully created player', player))
    } catch (e) {
      next(e)
    }
  }

  //PUT
  static async update(req, res, next) {
    try {
      const player = await PlayerModel.findByIdAndUpdate(req.params.id, req.body, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated player', player))
    } catch (e) {
      next(e)
    }
  }
  static async updateGoals(req, res, next) {
    try {
      if (!req.params.id) res.json(JsonUtil.response(res, true, 'Please provide player id', null))
      if (!req.params.goals) res.json(JsonUtil.response(res, true, 'Please provide scored goals', null))
      const player = await PlayerModel.findById(req.params.id)
      player.goals = req.params.goals
      await player.save()
      res.json(JsonUtil.response(res, false, 'Successfully updated player', player))
    } catch (e) {
      next(e)
    }
  }
  static async updateAssists(req, res, next) {
    try {
      if (!req.params.id) res.json(JsonUtil.response(res, true, 'Please provide player id', null))
      if (!req.params.assists) res.json(JsonUtil.response(res, true, 'Please provide scored assists', null))
      const player = await PlayerModel.findById(req.params.id)
      player.assists = req.params.assists
      await player.save()
      res.json(JsonUtil.response(res, false, 'Successfully updated player', player))
    } catch (e) {
      next(e)
    }
  }
  static async updateYellowCards(req, res, next) {
    try {
      if (!req.params.id) res.json(JsonUtil.response(res, true, 'Please provide player id', null))
      if (!req.params.yellowCards) res.json(JsonUtil.response(res, true, 'Please provide yellow cards', null))
      const player = await PlayerModel.findById(req.params.id)
      player.yellowCards = req.params.yellowCards
      await player.save()
      res.json(JsonUtil.response(res, false, 'Successfully updated player', player))
    } catch (e) {
      next(e)
    }
  }
  static async updateRedCards(req, res, next) {
    try {
      if (!req.params.id) res.json(JsonUtil.response(res, true, 'Please provide player id', null))
      if (!req.params.redCards) res.json(JsonUtil.response(res, true, 'Please provide red cards', null))
      const player = await PlayerModel.findById(req.params.id)
      player.redCards = req.params.redCards
      await player.save()
      res.json(JsonUtil.response(res, false, 'Successfully updated player', player))
    } catch (e) {
      next(e)
    }
  }

  static async updateAppearances(req, res, next) {
    try {
      if (!req.params.id) res.json(JsonUtil.response(res, true, 'Please provide player id', null))
      if (!req.params.appearances) res.json(JsonUtil.response(res, true, 'Please provide appearances', null))
      const player = await PlayerModel.findById(req.params.id)
      player.appearances = req.params.appearances
      await PlayerModel.findByIdAndUpdate(req.params.id, player, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated player', player))
    } catch (e) {
      next(e)
    }
  }

  static async updatePicture(req, res, next) {
    try {
      if (!req.params.id) res.json(JsonUtil.response(res, true, 'Please provide player id', null))
      if (!req.params.picture) res.json(JsonUtil.response(res, true, 'Please provide picture', null))
      const player = await PlayerModel.findById(req.params.id)
      player.picture = req.params.path
      await player.save()
      res.json(JsonUtil.response(res, false, 'Successfully updated player', player))
    } catch (e) {
      next(e)
    }
  }

  //DELETE
  static async delete(req, res, next) {
    try {
      const player = await PlayerModel.findByIdAndDelete(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully deleted player', player))
    } catch (e) {
      next(e)
    }
  }
}
