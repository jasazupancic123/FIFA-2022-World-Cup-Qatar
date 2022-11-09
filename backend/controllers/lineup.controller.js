const LineupModel = require('../models/Lineup')
const JsonUtil = require('../utils/json')

const PlayerModel = require('../models/Player')
const TeamModel = require('../models/Team')
const MatchModel = require('../models/Match')
const ManagerModel = require('../models/Manager')
const ClubModel = require('../models/Club')

const mongoose = require('mongoose')

module.exports = class LineupController {
  static async find(req, res, next) {
    try {
      const lineups = await LineupModel.find()
      for (let i = 0; i < lineups.length; i++) {
        lineups[i].team = await TeamModel.findById(lineups[i].team)
        lineups[i].match = await MatchModel.findById(lineups[i].match)
        lineups[i].match.homeTeam = await TeamModel.findById(lineups[i].match.homeTeam)
        lineups[i].match.homeTeam.manager = await ManagerModel.findById(lineups[i].match.homeTeam.manager)
        lineups[i].match.awayTeam = await TeamModel.findById(lineups[i].match.awayTeam)
        lineups[i].match.awayTeam.manager = await ManagerModel.findById(lineups[i].match.awayTeam.manager)
        lineups[i].match.winner = await TeamModel.findById(lineups[i].match.winner)
        const manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].team.manager = manager

        lineups[i].goalkeeper = await PlayerModel.findById(lineups[i].goalkeeper)
        lineups[i].goalkeeper.nationality = await TeamModel.findById(lineups[i].goalkeeper.nationality)
        lineups[i].goalkeeper.nationality.manager = await ManagerModel.findById(lineups[i].goalkeeper.nationality.manager)
        lineups[i].goalkeeper.club = await ClubModel(lineups[i].goalkeeper.club)

        for (let j = 0; j < lineups[i].defenders.length; j++) {
          lineups[i].defenders[j] = await PlayerModel.findById(lineups[i].defenders[j])
          lineups[i].defenders[j].nationality = lineups[i].team
          lineups[i].defenders[j].nationality.manager = lineups[i].team.manager
          lineups[i].defenders[j].club = await ClubModel.findById(lineups[i].defenders[j].club)
        }
        //convert lineup[i].midfielders to PlayerModel
        const midfielders = []
        for (let j = 0; j < lineups[i].midfielders.length; j++) {
          const midfielder = await PlayerModel.findById(lineups[i].midfielders[j])
          midfielder.nationality = lineups[i].team
          midfielder.nationality.manager = lineups[i].team.manager
          midfielder.club = await ClubModel.findById(midfielder.club)
          lineups[i].midfielders[j] = midfielder
        }

        for (let j = 0; j < lineups[i].attackers.length; j++) {
          lineups[i].attackers[j] = await PlayerModel.findById(lineups[i].attackers[j])
          lineups[i].attackers[j].nationality = lineups[i].team
          lineups[i].attackers[j].nationality.manager = lineups[i].team.manager
          lineups[i].attackers[j].club = await ClubModel.findById(lineups[i].attackers[j].club)
        }
        for (let j = 0; j < lineups[i].substitutes.length; j++) {
          lineups[i].substitutes[j] = await PlayerModel.findById(lineups[i].substitutes[j])
          lineups[i].substitutes[j].nationality = lineups[i].team
          lineups[i].substitutes[j].nationality.manager = lineups[i].team.manager
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
      lineup.match.homeTeam = await TeamModel.findById(lineup.match.homeTeam)
      lineup.match.homeTeam.manager = await ManagerModel.findById(lineup.match.homeTeam.manager)
      lineup.match.awayTeam = await TeamModel.findById(lineup.match.awayTeam)
      lineup.match.awayTeam.manager = await ManagerModel.findById(lineup.match.awayTeam.manager)
      lineup.match.winner = await TeamModel.findById(lineup.match.winner)
      lineup.match.winner.manager = await ManagerModel.findById(lineup.match.winner.manager)
      const manager = await ManagerModel.findById(lineup.team.manager)
      lineup.team.manager = manager

      lineup.goalkeeper = await PlayerModel.findById(lineup.goalkeeper)
      lineup.goalkeeper.nationality = await TeamModel.findById(lineup.goalkeeper.nationality)
      lineup.goalkeeper.nationality.manager = await ManagerModel.findById(lineup.goalkeeper.nationality.manager)
      lineup.goalkeeper.club = await ClubModel(lineup.goalkeeper.club)

      const defenders = []
      for (let j = 0; j < lineup.defenders.length; j++) {
        lineup.defenders[j] = await PlayerModel.findById(lineup.defenders[j])
        lineup.defenders[j].nationality = lineup.team
        lineup.defenders[j].nationality.manager = lineup.team.manager
        lineup.defenders[j].club = await ClubModel.findById(lineup.defenders[j].club)
      }
      for (let j = 0; j < lineup.midfielders.length; j++) {
        lineup.midfielders[j] = await PlayerModel.findById(lineup.midfielders[j])
        lineup.midfielders[j].nationality = lineup.team
        lineup.midfielders[j].nationality.manager = lineup.team.manager
        lineup.midfielders[j].club = await ClubModel.findById(lineup.midfielders[j].club)
      }
      for (let j = 0; j < lineup.attackers.length; j++) {
        lineup.attackers[j] = await PlayerModel.findById(lineup.attackers[j])
        lineup.attackers[j].nationality = lineup.team
        lineup.attackers[j].nationality.manager = lineup.team.manager
        lineup.attackers[j].club = await ClubModel.findById(lineup.attackers[j].club)
      }
      for (let j = 0; j < lineup.substitutes.length; j++) {
        lineup.substitutes[j] = await PlayerModel.findById(lineup.substitutes[j])
        lineup.substitutes[j].nationality = lineup.team
        lineup.substitutes[j].nationality.manager = lineup.team.manager
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
        lineups[i].match.homeTeam = await TeamModel.findById(lineups[i].match.homeTeam)
        lineups[i].match.homeTeam.manager = await ManagerModel.findById(lineups[i].match.homeTeam.manager)
        lineups[i].match.awayTeam = await TeamModel.findById(lineups[i].match.awayTeam)
        lineups[i].match.awayTeam.manager = await ManagerModel.findById(lineups[i].match.awayTeam.manager)
        lineups[i].match.winner = await TeamModel.findById(lineups[i].match.winner)
        const manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].team.manager = manager

        lineups[i].goalkeeper = await PlayerModel.findById(lineups[i].goalkeeper)
        lineups[i].goalkeeper.nationality = await TeamModel.findById(lineups[i].goalkeeper.nationality)
        lineups[i].goalkeeper.nationality.manager = await ManagerModel.findById(lineups[i].goalkeeper.nationality.manager)
        lineups[i].goalkeeper.club = await ClubModel(lineups[i].goalkeeper.club)

        for (let j = 0; j < lineups[i].defenders.length; j++) {
          lineups[i].defenders[j] = await PlayerModel.findById(lineups[i].defenders[j])
          lineups[i].defenders[j].nationality = lineups[i].team
          lineups[i].defenders[j].nationality.manager = lineups[i].team.manager
          lineups[i].defenders[j].club = await ClubModel.findById(lineups[i].defenders[j].club)
        }
        for (let j = 0; j < lineups[i].midfielders.length; j++) {
          lineups[i].midfielders[j] = await PlayerModel.findById(lineups[i].midfielders[j])
          lineups[i].midfielders[j].nationality = lineups[i].team
          lineups[i].midfielders[j].nationality.manager = lineups[i].team.manager
          lineups[i].midfielders[j].club = await ClubModel.findById(lineups[i].midfielders[j].club)
        }
        for (let j = 0; j < lineups[i].attackers.length; j++) {
          lineups[i].attackers[j] = await PlayerModel.findById(lineups[i].attackers[j])
          lineups[i].attackers[j].nationality = lineups[i].team
          lineups[i].attackers[j].nationality.manager = lineups[i].team.manager
          lineups[i].attackers[j].club = await ClubModel.findById(lineups[i].attackers[j].club)
        }
        for (let j = 0; j < lineups[i].substitutes.length; j++) {
          lineups[i].substitutes[j] = await PlayerModel.findById(lineups[i].substitutes[j])
          lineups[i].substitutes[j].nationality = lineups[i].team
          lineups[i].substitutes[j].nationality.manager = lineups[i].team.manager
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
        lineups[i].match.homeTeam = await TeamModel.findById(lineups[i].match.homeTeam)
        lineups[i].match.homeTeam.manager = await ManagerModel.findById(lineups[i].match.homeTeam.manager)
        lineups[i].match.awayTeam = await TeamModel.findById(lineups[i].match.awayTeam)
        lineups[i].match.awayTeam.manager = await ManagerModel.findById(lineups[i].match.awayTeam.manager)
        lineups[i].match.winner = await TeamModel.findById(lineups[i].match.winner)
        const manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].team.manager = manager

        lineups[i].goalkeeper = await PlayerModel.findById(lineups[i].goalkeeper)
        lineups[i].goalkeeper.nationality = await TeamModel.findById(lineups[i].goalkeeper.nationality)
        lineups[i].goalkeeper.nationality.manager = await ManagerModel.findById(lineups[i].goalkeeper.nationality.manager)
        lineups[i].goalkeeper.club = await ClubModel(lineups[i].goalkeeper.club)

        for (let j = 0; j < lineups[i].defenders.length; j++) {
          lineups[i].defenders[j] = await PlayerModel.findById(lineups[i].defenders[j])
          lineups[i].defenders[j].nationality = lineups[i].team
          lineups[i].defenders[j].nationality.manager = lineups[i].team.manager
          lineups[i].defenders[j].club = await ClubModel.findById(lineups[i].defenders[j].club)
        }
        for (let j = 0; j < lineups[i].midfielders.length; j++) {
          lineups[i].midfielders[j] = await PlayerModel.findById(lineups[i].midfielders[j])
          lineups[i].midfielders[j].nationality = lineups[i].team
          lineups[i].midfielders[j].nationality.manager = lineups[i].team.manager
          lineups[i].midfielders[j].club = await ClubModel.findById(lineups[i].midfielders[j].club)
        }
        for (let j = 0; j < lineups[i].attackers.length; j++) {
          lineups[i].attackers[j] = await PlayerModel.findById(lineups[i].attackers[j])
          lineups[i].attackers[j].nationality = lineups[i].team
          lineups[i].attackers[j].nationality.manager = lineups[i].team.manager
          lineups[i].attackers[j].club = await ClubModel.findById(lineups[i].attackers[j].club)
        }
        for (let j = 0; j < lineups[i].substitutes.length; j++) {
          lineups[i].substitutes[j] = await PlayerModel.findById(lineups[i].substitutes[j])
          lineups[i].substitutes[j].nationality = lineups[i].team
          lineups[i].substitutes[j].nationality.manager = lineups[i].team.manager
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
        lineups[i].match.homeTeam = await TeamModel.findById(lineups[i].match.homeTeam)
        lineups[i].match.homeTeam.manager = await ManagerModel.findById(lineups[i].match.homeTeam.manager)
        lineups[i].match.awayTeam = await TeamModel.findById(lineups[i].match.awayTeam)
        lineups[i].match.awayTeam.manager = await ManagerModel.findById(lineups[i].match.awayTeam.manager)
        lineups[i].match.winner = await TeamModel.findById(lineups[i].match.winner)
        const manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].team.manager = manager

        lineups[i].goalkeeper = await PlayerModel.findById(lineups[i].goalkeeper)
        lineups[i].goalkeeper.nationality = await TeamModel.findById(lineups[i].goalkeeper.nationality)
        lineups[i].goalkeeper.nationality.manager = await ManagerModel.findById(lineups[i].goalkeeper.nationality.manager)
        lineups[i].goalkeeper.club = await ClubModel(lineups[i].goalkeeper.club)

        for (let j = 0; j < lineups[i].defenders.length; j++) {
          lineups[i].defenders[j] = await PlayerModel.findById(lineups[i].defenders[j])
          lineups[i].defenders[j].nationality = lineups[i].team
          lineups[i].defenders[j].nationality.manager = lineups[i].team.manager
          lineups[i].defenders[j].club = await ClubModel.findById(lineups[i].defenders[j].club)
        }
        for (let j = 0; j < lineups[i].midfielders.length; j++) {
          lineups[i].midfielders[j] = await PlayerModel.findById(lineups[i].midfielders[j])
          lineups[i].midfielders[j].nationality = lineups[i].team
          lineups[i].midfielders[j].nationality.manager = lineups[i].team.manager
          lineups[i].midfielders[j].club = await ClubModel.findById(lineups[i].midfielders[j].club)
        }
        for (let j = 0; j < lineups[i].attackers.length; j++) {
          lineups[i].attackers[j] = await PlayerModel.findById(lineups[i].attackers[j])
          lineups[i].attackers[j].nationality = lineups[i].team
          lineups[i].attackers[j].nationality.manager = lineups[i].team.manager
          lineups[i].attackers[j].club = await ClubModel.findById(lineups[i].attackers[j].club)
        }
        for (let j = 0; j < lineups[i].substitutes.length; j++) {
          lineups[i].substitutes[j] = await PlayerModel.findById(lineups[i].substitutes[j])
          lineups[i].substitutes[j].nationality = lineups[i].team
          lineups[i].substitutes[j].nationality.manager = lineups[i].team.manager
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
      lineup.match.homeTeam = await TeamModel.findById(lineup.match.homeTeam)
      lineup.match.homeTeam.manager = await ManagerModel.findById(lineup.match.homeTeam.manager)
      lineup.match.awayTeam = await TeamModel.findById(lineup.match.awayTeam)
      lineup.match.awayTeam.manager = await ManagerModel.findById(lineup.match.awayTeam.manager)
      lineup.match.winner = await TeamModel.findById(lineup.match.winner)
      lineup.match.winner.manager = await ManagerModel.findById(lineup.match.winner.manager)
      const manager = await ManagerModel.findById(lineup.team.manager)
      lineup.team.manager = manager

      lineup.goalkeeper = await PlayerModel.findById(lineup.goalkeeper)
      lineup.goalkeeper.nationality = await TeamModel.findById(lineup.goalkeeper.nationality)
      lineup.goalkeeper.nationality.manager = await ManagerModel.findById(lineup.goalkeeper.nationality.manager)
      lineup.goalkeeper.club = await ClubModel(lineup.goalkeeper.club)

      const defenders = []
      for (let j = 0; j < lineup.defenders.length; j++) {
        lineup.defenders[j] = await PlayerModel.findById(lineup.defenders[j])
        lineup.defenders[j].nationality = lineup.team
        lineup.defenders[j].nationality.manager = lineup.team.manager
        lineup.defenders[j].club = await ClubModel.findById(lineup.defenders[j].club)
      }
      for (let j = 0; j < lineup.midfielders.length; j++) {
        lineup.midfielders[j] = await PlayerModel.findById(lineup.midfielders[j])
        lineup.midfielders[j].nationality = lineup.team
        lineup.midfielders[j].nationality.manager = lineup.team.manager
        lineup.midfielders[j].club = await ClubModel.findById(lineup.midfielders[j].club)
      }
      for (let j = 0; j < lineup.attackers.length; j++) {
        lineup.attackers[j] = await PlayerModel.findById(lineup.attackers[j])
        lineup.attackers[j].nationality = lineup.team
        lineup.attackers[j].nationality.manager = lineup.team.manager
        lineup.attackers[j].club = await ClubModel.findById(lineup.attackers[j].club)
      }
      for (let j = 0; j < lineup.substitutes.length; j++) {
        lineup.substitutes[j] = await PlayerModel.findById(lineup.substitutes[j])
        lineup.substitutes[j].nationality = lineup.team
        lineup.substitutes[j].nationality.manager = lineup.team.manager
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
        defender.nationality = await TeamModel.findById(defender.nationality)
        defender.nationality.manager = await ManagerModel.findById(defender.nationality.manager)
        defender.club = await ClubModel.findById(defender.club)
        defender.appearances += 1
        await defender.save()
        defenders.push(defender)
      }

      const midfielders = []
      for (let i = 0; i < req.body.midfielders.length; i++) {
        const midfielder = await PlayerModel.findById(req.body.midfielders[i])
        midfielder.nationality = await TeamModel.findById(midfielder.nationality)
        midfielder.nationality.manager = await ManagerModel.findById(midfielder.nationality.manager)
        midfielder.club = await ClubModel.findById(midfielder.club)
        midfielder.appearances += 1
        await midfielder.save()
        midfielders.push(midfielder)
      }

      const attackers = []
      for (let i = 0; i < req.body.attackers.length; i++) {
        const attacker = await PlayerModel.findById(req.body.attackers[i])
        attacker.nationality = await TeamModel.findById(attacker.nationality)
        attacker.nationality.manager = await ManagerModel.findById(attacker.nationality.manager)
        attacker.club = await ClubModel.findById(attacker.club)
        attacker.appearances += 1
        await attacker.save()
        attackers.push(attacker)
      }

      const substitutes = []
      for (let i = 0; i < req.body.substitutes.length; i++) {
        const substitute = await PlayerModel.findById(req.body.substitutes[i])
        substitute.nationality = await TeamModel.findById(substitute.nationality)
        substitute.nationality.manager = await ManagerModel.findById(substitute.nationality.manager)
        substitute.club = await ClubModel.findById(substitute.club)
        await substitute.save()
        substitutes.push(substitute)
      }

      const lineup = await LineupModel.create({
        type: req.body.type,
        team: team,
        match: match,
        goalkeeper: goalkeeper,
        defenders: defenders,
        midfielders: midfielders,
        attackers: attackers,
        substitutes: substitutes,
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
