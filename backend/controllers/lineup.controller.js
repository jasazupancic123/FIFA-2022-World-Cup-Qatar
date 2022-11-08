const LineupModel = require('../models/Lineup')
const JsonUtil = require('../utils/json')

const PlayerModel = require('../models/Player')
const TeamModel = require('../models/Team')
const MatchModel = require('../models/Match')
const ManagerModel = require('../models/Manager')

const mongoose = require('mongoose')

module.exports = class LineupController {
  static async find(req, res, next) {
    try {
      const lineups = await LineupModel.find()
      for (let i = 0; i < lineups.length; i++) {
        lineups[i].team = await TeamModel.findById(lineups[i].team)
        lineups[i].match = await MatchModel.findById(lineups[i].match)
        const manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].team.manager = manager

        const goalkeeper = await PlayerModel.findById(lineups[i].goalkeeper)
        goalkeeper.team = lineups[i].team
        goalkeeper.team.manager = lineups[i].team.manager
        goalkeeper.club = await ClubModel.findById(lineups[i].goalkeeper.club)

        lineups[i].defenders = await PlayerModel.findByIds(lineups[i].defenders)
        for (let j = 0; j < lineups[i].defenders.length; j++) {
          lineups[i].defenders[j].team = lineups[i].team
          lineups[i].defenders[j].team.manager = lineups[i].team.manager
          lineups[i].defenders[j].club = await ClubModel.findById(lineups[i].defenders[j].club)
        }
        lineups[i].midfielders = await PlayerModel.findByIds(lineups[i].midfielders)
        for (let j = 0; j < lineups[i].midfielders.length; j++) {
          lineups[i].midfielders[j].team = lineups[i].team
          lineups[i].midfielders[j].team.manager = lineups[i].team.manager
          lineups[i].midfielders[j].club = await ClubModel.findById(lineups[i].midfielders[j].club)
        }
        lineups[i].attackers = await PlayerModel.findByIds(lineups[i].attackers)
        for (let j = 0; j < lineups[i].attackers.length; j++) {
          lineups[i].attackers[j].team = lineups[i].team
          lineups[i].attackers[j].team.manager = lineups[i].team.manager
          lineups[i].attackers[j].club = await ClubModel.findById(lineups[i].attackers[j].club)
        }
        lineups[i].substitutes = await PlayerModel.findByIds(lineups[i].substitutes)
        for (let j = 0; j < lineups[i].substitutes.length; j++) {
          lineups[i].substitutes[j].team = lineups[i].team
          lineups[i].substitutes[j].team.manager = lineups[i].team.manager
          lineups[i].substitutes[j].club = await ClubModel.findById(lineups[i].substitutes[j].club)
        }
      }
      res.json(JsonUtil.response(res, false, 'Successfully found lineups', lineups))
    } catch (e) {
      next(e)
    }
  }

  static async findById(req, res, next) {
    try {
      const lineup = await LineupModel.findById(req.params.id)
      lineup.team = await TeamModel.findById(lineup.team)
      lineup.match = await MatchModel.findById(lineup.match)
      const manager = await ManagerModel.findById(lineup.team.manager)
      lineup.team.manager = manager

      const goalkeeper = await PlayerModel.findById(lineup.goalkeeper)
      goalkeeper.team = lineup.team
      goalkeeper.team.manager = lineup.team.manager
      goalkeeper.club = await ClubModel.findById(lineup.goalkeeper.club)

      lineup.defenders = await PlayerModel.findByIds(lineup.defenders)
      for (let j = 0; j < lineup.defenders.length; j++) {
        lineup.defenders[j].team = lineup.team
        lineup.defenders[j].team.manager = lineup.team.manager
        lineup.defenders[j].club = await ClubModel.findById(lineup.defenders[j].club)
      }
      lineup.midfielders = await PlayerModel.findByIds(lineup.midfielders)
      for (let j = 0; j < lineup.midfielders.length; j++) {
        lineup.midfielders[j].team = lineup.team
        lineup.midfielders[j].team.manager = lineup.team.manager
        lineup.midfielders[j].club = await ClubModel.findById(lineup.midfielders[j].club)
      }
      lineup.attackers = await PlayerModel.findByIds(lineup.attackers)
      for (let j = 0; j < lineup.attackers.length; j++) {
        lineup.attackers[j].team = lineup.team
        lineup.attackers[j].team.manager = lineup.team.manager
        lineup.attackers[j].club = await ClubModel.findById(lineup.attackers[j].club)
      }
      lineup.substitutes = await PlayerModel.findByIds(lineup.substitutes)
      for (let j = 0; j < lineup.substitutes.length; j++) {
        lineup.substitutes[j].team = lineup.team
        lineup.substitutes[j].team.manager = lineup.team.manager
        lineup.substitutes[j].club = await ClubModel.findById(lineup.substitutes[j].club)
      }

      res.json(JsonUtil.response(res, false, 'Successfully found lineup', lineup))
    } catch (e) {
      next(e)
    }
  }

  static async findByType(req, res, next) {
    try {
      const lineups = await LineupModel.find({ type: req.params.type })
      for (let i = 0; i < lineups.length; i++) {
        lineups[i].team = await TeamModel.findById(lineups[i].team)
        lineups[i].match = await MatchModel.findById(lineups[i].match)
        const manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].team.manager = manager

        const goalkeeper = await PlayerModel.findById(lineups[i].goalkeeper)
        goalkeeper.team = lineups[i].team
        goalkeeper.team.manager = lineups[i].team.manager
        goalkeeper.club = await ClubModel.findById(lineups[i].goalkeeper.club)

        lineups[i].defenders = await PlayerModel.findByIds(lineups[i].defenders)
        for (let j = 0; j < lineups[i].defenders.length; j++) {
          lineups[i].defenders[j].team = lineups[i].team
          lineups[i].defenders[j].team.manager = lineups[i].team.manager
          lineups[i].defenders[j].club = await ClubModel.findById(lineups[i].defenders[j].club)
        }
        lineups[i].midfielders = await PlayerModel.findByIds(lineups[i].midfielders)
        for (let j = 0; j < lineups[i].midfielders.length; j++) {
          lineups[i].midfielders[j].team = lineups[i].team
          lineups[i].midfielders[j].team.manager = lineups[i].team.manager
          lineups[i].midfielders[j].club = await ClubModel.findById(lineups[i].midfielders[j].club)
        }
        lineups[i].attackers = await PlayerModel.findByIds(lineups[i].attackers)
        for (let j = 0; j < lineups[i].attackers.length; j++) {
          lineups[i].attackers[j].team = lineups[i].team
          lineups[i].attackers[j].team.manager = lineups[i].team.manager
          lineups[i].attackers[j].club = await ClubModel.findById(lineups[i].attackers[j].club)
        }
        lineups[i].substitutes = await PlayerModel.findByIds(lineups[i].substitutes)
        for (let j = 0; j < lineups[i].substitutes.length; j++) {
          lineups[i].substitutes[j].team = lineups[i].team
          lineups[i].substitutes[j].team.manager = lineups[i].team.manager
          lineups[i].substitutes[j].club = await ClubModel.findById(lineups[i].substitutes[j].club)
        }
      }
      res.json(JsonUtil.response(res, false, 'Successfully found lineups', lineups))
    } catch (e) {
      next(e)
    }
  }

  static async findByMatch(req, res, next) {
    try {
      const lineups = await LineupModel.find({ match: req.params.match })
      for (let i = 0; i < lineups.length; i++) {
        lineups[i].team = await TeamModel.findById(lineups[i].team)
        lineups[i].match = await MatchModel.findById(lineups[i].match)
        const manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].team.manager = manager

        const goalkeeper = await PlayerModel.findById(lineups[i].goalkeeper)
        goalkeeper.team = lineups[i].team
        goalkeeper.team.manager = lineups[i].team.manager
        goalkeeper.club = await ClubModel.findById(lineups[i].goalkeeper.club)

        lineups[i].defenders = await PlayerModel.findByIds(lineups[i].defenders)
        for (let j = 0; j < lineups[i].defenders.length; j++) {
          lineups[i].defenders[j].team = lineups[i].team
          lineups[i].defenders[j].team.manager = lineups[i].team.manager
          lineups[i].defenders[j].club = await ClubModel.findById(lineups[i].defenders[j].club)
        }
        lineups[i].midfielders = await PlayerModel.findByIds(lineups[i].midfielders)
        for (let j = 0; j < lineups[i].midfielders.length; j++) {
          lineups[i].midfielders[j].team = lineups[i].team
          lineups[i].midfielders[j].team.manager = lineups[i].team.manager
          lineups[i].midfielders[j].club = await ClubModel.findById(lineups[i].midfielders[j].club)
        }
        lineups[i].attackers = await PlayerModel.findByIds(lineups[i].attackers)
        for (let j = 0; j < lineups[i].attackers.length; j++) {
          lineups[i].attackers[j].team = lineups[i].team
          lineups[i].attackers[j].team.manager = lineups[i].team.manager
          lineups[i].attackers[j].club = await ClubModel.findById(lineups[i].attackers[j].club)
        }
        lineups[i].substitutes = await PlayerModel.findByIds(lineups[i].substitutes)
        for (let j = 0; j < lineups[i].substitutes.length; j++) {
          lineups[i].substitutes[j].team = lineups[i].team
          lineups[i].substitutes[j].team.manager = lineups[i].team.manager
          lineups[i].substitutes[j].club = await ClubModel.findById(lineups[i].substitutes[j].club)
        }
      }
      res.json(JsonUtil.response(res, false, 'Successfully found lineups', lineups))
    } catch (e) {
      next(e)
    }
  }

  static async findByTeam(req, res, next) {
    try {
      const lineups = await LineupModel.find({ team: req.params.team })
      for (let i = 0; i < lineups.length; i++) {
        lineups[i].team = await TeamModel.findById(lineups[i].team)
        lineups[i].match = await MatchModel.findById(lineups[i].match)
        const manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].team.manager = manager

        const goalkeeper = await PlayerModel.findById(lineups[i].goalkeeper)
        goalkeeper.team = lineups[i].team
        goalkeeper.team.manager = lineups[i].team.manager
        goalkeeper.club = await ClubModel.findById(lineups[i].goalkeeper.club)

        lineups[i].defenders = await PlayerModel.findByIds(lineups[i].defenders)
        for (let j = 0; j < lineups[i].defenders.length; j++) {
          lineups[i].defenders[j].team = lineups[i].team
          lineups[i].defenders[j].team.manager = lineups[i].team.manager
          lineups[i].defenders[j].club = await ClubModel.findById(lineups[i].defenders[j].club)
        }
        lineups[i].midfielders = await PlayerModel.findByIds(lineups[i].midfielders)
        for (let j = 0; j < lineups[i].midfielders.length; j++) {
          lineups[i].midfielders[j].team = lineups[i].team
          lineups[i].midfielders[j].team.manager = lineups[i].team.manager
          lineups[i].midfielders[j].club = await ClubModel.findById(lineups[i].midfielders[j].club)
        }
        lineups[i].attackers = await PlayerModel.findByIds(lineups[i].attackers)
        for (let j = 0; j < lineups[i].attackers.length; j++) {
          lineups[i].attackers[j].team = lineups[i].team
          lineups[i].attackers[j].team.manager = lineups[i].team.manager
          lineups[i].attackers[j].club = await ClubModel.findById(lineups[i].attackers[j].club)
        }
        lineups[i].substitutes = await PlayerModel.findByIds(lineups[i].substitutes)
        for (let j = 0; j < lineups[i].substitutes.length; j++) {
          lineups[i].substitutes[j].team = lineups[i].team
          lineups[i].substitutes[j].team.manager = lineups[i].team.manager
          lineups[i].substitutes[j].club = await ClubModel.findById(lineups[i].substitutes[j].club)
        }
      }
      res.json(JsonUtil.response(res, false, 'Successfully found lineups', lineups))
    } catch (e) {
      next(e)
    }
  }

  static async findByMatchAndTeam(req, res, next) {
    try {
      const lineup = await LineupModel.findOne({ match: req.params.match, team: req.params.team })
      lineup.team = await TeamModel.findById(lineup.team)
      lineup.match = await MatchModel.findById(lineup.match)
      const manager = await ManagerModel.findById(lineup.team.manager)
      lineup.team.manager = manager

      const goalkeeper = await PlayerModel.findById(lineup.goalkeeper)
      goalkeeper.team = lineup.team
      goalkeeper.team.manager = lineup.team.manager
      goalkeeper.club = await ClubModel.findById(lineup.goalkeeper.club)

      lineup.defenders = await PlayerModel.findByIds(lineup.defenders)
      for (let j = 0; j < lineup.defenders.length; j++) {
        lineup.defenders[j].team = lineup.team
        lineup.defenders[j].team.manager = lineup.team.manager
        lineup.defenders[j].club = await ClubModel.findById(lineup.defenders[j].club)
      }
      lineup.midfielders = await PlayerModel.findByIds(lineup.midfielders)
      for (let j = 0; j < lineup.midfielders.length; j++) {
        lineup.midfielders[j].team = lineup.team
        lineup.midfielders[j].team.manager = lineup.team.manager
        lineup.midfielders[j].club = await ClubModel.findById(lineup.midfielders[j].club)
      }
      lineup.attackers = await PlayerModel.findByIds(lineup.attackers)
      for (let j = 0; j < lineup.attackers.length; j++) {
        lineup.attackers[j].team = lineup.team
        lineup.attackers[j].team.manager = lineup.team.manager
        lineup.attackers[j].club = await ClubModel.findById(lineup.attackers[j].club)
      }
      lineup.substitutes = await PlayerModel.findByIds(lineup.substitutes)
      for (let j = 0; j < lineup.substitutes.length; j++) {
        lineup.substitutes[j].team = lineup.team
        lineup.substitutes[j].team.manager = lineup.team.manager
        lineup.substitutes[j].club = await ClubModel.findById(lineup.substitutes[j].club)
      }
      res.json(JsonUtil.response(res, false, 'Successfully found lineups', lineup))
    } catch (e) {
      next(e)
    }
  }

  //POST
  static async createAndUpdateEverything(req, res, next) {
    try {
      const team = await TeamModel.findById(req.body.team)
      const match = await MatchModel.findById(req.body.match)

      const goalkeeper = await PlayerModel.findById(req.body.goalkeeper)
      goalkeeper.appearances += 1
      await goalkeeper.save()

      const defenders = []
      for (let i = 0; i < req.body.defenders.length; i++) {
        const defender = await PlayerModel.findById(req.body.defenders[i])
        defender.appearances += 1
        await defender.save()
        defenders.push(defender)
      }

      const midfielders = []
      for (let i = 0; i < req.body.midfielders.length; i++) {
        const midfielder = await PlayerModel.findById(req.body.midfielders[i])
        midfielder.appearances += 1
        await midfielder.save()
        midfielders.push(midfielder)
      }

      const attackers = []
      for (let i = 0; i < req.body.attackers.length; i++) {
        const attacker = await PlayerModel.findById(req.body.attackers[i])
        attacker.appearances += 1
        await attacker.save()
        attackers.push(attacker)
      }

      const lineup = await LineupModel.create({
        type: req.body.type,
        team: team,
        match: match,
        goalkeeper: goalkeeper,
        defenders: defenders,
        midfielders: midfielders,
        attackers: attackers,
      })
      res.json(JsonUtil.response(res, false, 'Successfully created lineup', lineup))
    } catch (e) {
      next(e)
    }
  }

  static async create(req, res, next) {
    try {
      const team = await TeamModel.findById(req.body.team)
      const match = await MatchModel.findById(req.body.match)

      const goalkeeper = await PlayerModel.findById(req.body.goalkeeper)

      const defenders = []
      for (let i = 0; i < req.body.defenders.length; i++) {
        const defender = await PlayerModel.findById(req.body.defenders[i])
        defenders.push(defender)
      }

      const midfielders = []
      for (let i = 0; i < req.body.midfielders.length; i++) {
        const midfielder = await PlayerModel.findById(req.body.midfielders[i])
        midfielders.push(midfielder)
      }

      const attackers = []
      for (let i = 0; i < req.body.attackers.length; i++) {
        const attacker = await PlayerModel.findById(req.body.attackers[i])
        attackers.push(attacker)
      }

      const lineup = await LineupModel.create({
        type: req.body.type,
        team: team,
        match: match,
        goalkeeper: goalkeeper,
        defenders: defenders,
        midfielders: midfielders,
        attackers: attackers,
      })
      res.json(JsonUtil.response(res, false, 'Successfully created lineup', lineup))
    } catch (e) {
      next(e)
    }
  }

  //PUT
  static async update(req, res, next) {
    try {
      const lineup = await LineupModel.findByIdAndUpdate(req.params.id, req.body, { new: true })
      res.json(JsonUtil.response(res, false, 'Successfully updated lineup', lineup))
    } catch (e) {
      next(e)
    }
  }

  //DELETE
  static async delete(req, res, next) {
    try {
      const lineup = await LineupModel.findByIdAndDelete(req.params.id)
      res.json(JsonUtil.response(res, false, 'Successfully deleted lineup', lineup))
    } catch (e) {
      next(e)
    }
  }
}
