const ClubModel = require('../models/Club')

module.exports = class ClubController {
  static async find(req, res, next) {
    try {
      const clubs = await ClubModel.find()
      res.json(JsonUtil.response(res, false, 'Successfully found clubs', clubs))
    } catch (e) {
      next(e)
    }
  }
  static async findById(req, res, next) {
    try {
      const club = await ClubModel.findById(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully found club', club))
    } catch (e) {
      next(e)
    }
  }
  static async findByName(req, res, next) {
    try {
      if (!req.params.name) res.json(JsonUtil.response(res, true, 'Please provide name', null))
      const club = await ClubModel.findOne({ name: req.params.name })
      res.json(JsonUtil.response(res, false, 'Successfully found club', club))
    } catch (e) {
      next(e)
    }
  }
  static async findByLeague(req, res, next) {
    try {
      if (!req.params.league) res.json(JsonUtil.response(res, true, 'Please provide league', null))
      const clubs = await ClubModel.find({ league: req.params.league })
      res.json(JsonUtil.response(res, false, 'Successfully found clubs', clubs))
    } catch (e) {
      next(e)
    }
  }

  //POST
  static async create(req, res, next) {
    try {
      const club = await ClubModel.create(req.body)
      res.json(JsonUtil.response(res, false, 'Successfully created club', club))
    } catch (e) {
      next(e)
    }
  }

  //PUT
  static async update(req, res, next) {
    try {
      const club = await ClubModel.findByIdAndUpdate(req.params.id, req.body, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated club', club))
    } catch (e) {
      next(e)
    }
  }

  //DELETE
  static async delete(req, res, next) {
    try {
      const club = await ClubModel.findByIdAndDelete(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully deleted club', club))
    } catch (e) {
      next(e)
    }
  }
}
