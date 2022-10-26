const CountryModel = require('../models/Country')
const JsonUtil = require('../utils/json')

module.exports = class CountryController {
  static async find(req, res, next) {
    try {
      const countries = await CountryModel.find()
      res.json(JsonUtil.response(res, false, 'Successfully found countries', countries))
    } catch (e) {
      next(e)
    }
  }
  static async findById(req, res, next) {
    try {
      const country = await CountryModel.findById(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully found country', country))
    } catch (e) {
      next(e)
    }
  }
  static async findByName(req, res, next) {
    try {
      const country = await CountryModel.findOne({ name: req.params.name })
      res.json(JsonUtil.response(res, false, 'Successfully found country', country))
    } catch (e) {
      next(e)
    }
  }
  static async findByRegion(req, res, next) {
    try {
      const countries = await CountryModel.find({ region: req.params.region })
      res.json(JsonUtil.response(res, false, 'Successfully found countries', countries))
    } catch (e) {
      next(e)
    }
  }
  static async findByFifaCode(req, res, next) {
    try {
      const country = await CountryModel.findOne({ fifaCode: req.params.fifaCode })
      res.json(JsonUtil.response(res, false, 'Successfully found country', country))
    } catch (e) {
      next(e)
    }
  }
  static async findByIso2(req, res, next) {
    try {
      const country = await CountryModel.findOne({ iso2: req.params.iso2 })
      res.json(JsonUtil.response(res, false, 'Successfully found country', country))
    } catch (e) {
      next(e)
    }
  }
  static async findByGroup(req, res, next) {
    try {
      const countries = await CountryModel.find({ group: req.params.group })
      res.json(JsonUtil.response(res, false, 'Successfully found countries', countries))
    } catch (e) {
      next(e)
    }
  }
  static async findByAtLeastOneTitle(req, res, next) {
    try {
      const countries = await CountryModel.find({ noOfTitles: { $gt: 0 } })
      res.json(JsonUtil.response(res, false, 'Successfully found countries', countries))
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
      const country = await CountryModel.create({ name, region, fifaCode, iso2, flag, group, noOfTitles })
      res.json(JsonUtil.response(res, false, 'Successfully created country', country))
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
      const country = await CountryModel.updateOne({ _id: req.params.id }, { name, region, fifaCode, iso2, flag, group, noOfTitles })
      res.json(JsonUtil.response(res, false, 'Successfully updated country', country))
    } catch (e) {
      next(e)
    }
  }

  static async updateGoalsScored(req, res, next) {
    try {
      const country = await CountryModel.updateOne({ _id: req.params.id }, { $inc: { goalsScored: 1 } })
      res.json(JsonUtil.response(res, false, 'Successfully updated country', country))
    } catch (e) {
      next(e)
    }
  }

  //DELETE
  static async delete(req, res, next) {
    try {
      const country = await CountryModel.deleteOne({ _id: req.params.id })
      res.json(JsonUtil.response(res, false, 'Successfully deleted country', country))
    } catch (e) {
      next(e)
    }
  }
}
