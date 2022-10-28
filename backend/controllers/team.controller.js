const TeamModel = require('../models/Team')
const JsonUtil = require('../utils/json')

module.exports = class TeamController {
  static async find(req, res, next) {
    try {
      const teams = await TeamModel.find()
      res.json(JsonUtil.response(res, false, 'Successfully found teams', teams))
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
      console.log('req body:', req.body)
      const { name, region, fifaCode, iso2, flag, group, noOfTitles } = req.body
      if (!name) throw new Error('Name is required')
      if (!region) throw new Error('Region is required')
      if (!fifaCode) throw new Error('Fifa Code is required')
      if (!iso2) throw new Error('Iso2 is required')
      if (!flag) throw new Error('Flag is required')
      if (!group) throw new Error('Group is required')
      const team = await TeamModel.create({ name, region, fifaCode, iso2, flag, group, noOfTitles })
      res.json(JsonUtil.response(res, false, 'Successfully created team', team))
    } catch (e) {
      next(e)
    }
  }

  //PUT
  static async update(req, res, next) {
    try {
      const { name, region, fifaCode, iso2, flag, group, noOfTitles } = req.body
      if (!name) throw new Error('Name is required')
      if (!region) throw new Error('Region is required')
      if (!fifaCode) throw new Error('Fifa Code is required')
      if (!iso2) throw new Error('Iso2 is required')
      if (!flag) throw new Error('Flag is required')
      if (!group) throw new Error('Group is required')
      const team = await TeamModel.updateOne({ _id: req.params.id }, { name, region, fifaCode, iso2, flag, group, noOfTitles })
      res.json(JsonUtil.response(res, false, 'Successfully updated team', team))
    } catch (e) {
      next(e)
    }
  }

  static async updateGoalsScored(req, res, next) {
    try {
      const team = await TeamModel.updateOne({ _id: req.params.id }, { $inc: { goalsScored: 1 } })
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
