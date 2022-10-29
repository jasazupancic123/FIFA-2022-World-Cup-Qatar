const ManagerModel = require('../models/Manager')
const JsonUtil = require('../utils/json')

module.exports = class ManagerController {
  static async find(req, res, next) {
    try {
      const managers = await ManagerModel.find()
      res.json(JsonUtil.response(res, false, 'Successfully found managers', managers))
    } catch (e) {
      next(e)
    }
  }

  static async findById(req, res, next) {
    try {
      const manager = await ManagerModel.findById(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully found manager', manager))
    } catch (e) {
      next(e)
    }
  }

  //POST
  static async create(req, res, next) {
    try {
      const manager = await ManagerModel.create(req.body)
      res.json(JsonUtil.response(res, false, 'Successfully created manager', manager))
    } catch (e) {
      next(e)
    }
  }

  //PUT
  static async update(req, res, next) {
    try {
      const manager = await ManagerModel.findByIdAndUpdate(req.params.id, req.body, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated manager', manager))
    } catch (e) {
      next(e)
    }
  }

  //DELETE
  static async delete(req, res, next) {
    try {
      const manager = await ManagerModel.findByIdAndDelete(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully deleted manager', manager))
    } catch (e) {
      next(e)
    }
  }
}
