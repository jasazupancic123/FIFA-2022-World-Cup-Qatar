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
        lineups[i].team.manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].match = await MatchModel.findById(lineups[i].match)
        lineups[i].match.homeTeam = await TeamModel.findById(lineups[i].match.homeTeam)
        lineups[i].match.homeTeam.manager = await ManagerModel.findById(lineups[i].match.homeTeam.manager)
        lineups[i].match.awayTeam = await TeamModel.findById(lineups[i].match.awayTeam)
        lineups[i].match.awayTeam.manager = await ManagerModel.findById(lineups[i].match.awayTeam.manager)

        lineups[i].goalkeeper = await PlayerModel.findById(lineups[i].goalkeeper)
        lineups[i].goalkeeper.nationality = await TeamModel.findById(lineups[i].goalkeeper.nationality)
        lineups[i].goalkeeper.nationality.manager = await ManagerModel.findById(lineups[i].goalkeeper.nationality.manager)
        console.log(lineups[i].goalkeeper.nationality.manager)
        lineups[i].goalkeeper.club = await ClubModel.findById(lineups[i].goalkeeper.club)

        lineups[i].match.winner = await TeamModel.findById(lineups[i].match.winner)
        if (lineups[i].match.winner != null) {
          lineups[i].match.winner.manager = await ManagerModel.findById(lineups[i].match.winner.manager)
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
      lineup.team.manager = await ManagerModel.findById(lineup.team.manager)
      lineup.match = await MatchModel.findById(lineup.match)
      lineup.match.homeTeam = await TeamModel.findById(lineup.match.homeTeam)
      lineup.match.homeTeam.manager = await ManagerModel.findById(lineup.match.homeTeam.manager)
      lineup.match.awayTeam = await TeamModel.findById(lineup.match.awayTeam)
      lineup.match.awayTeam.manager = await ManagerModel.findById(lineup.match.awayTeam.manager)
      lineup.match.winner = await TeamModel.findById(lineup.match.winner)
      if (lineup.match.winner != null) {
        lineup.match.winner.manager = await ManagerModel.findById(lineup.match.winner.manager)
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
        lineups[i].team.manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].match = await MatchModel.findById(lineups[i].match)
        lineups[i].match.homeTeam = await TeamModel.findById(lineups[i].match.homeTeam)
        lineups[i].match.homeTeam.manager = await ManagerModel.findById(lineups[i].match.homeTeam.manager)
        lineups[i].match.awayTeam = await TeamModel.findById(lineups[i].match.awayTeam)
        lineups[i].match.awayTeam.manager = await ManagerModel.findById(lineups[i].match.awayTeam.manager)
        lineups[i].match.winner = await TeamModel.findById(lineups[i].match.winner)
        if (lineups[i].match.winner != null) {
          lineups[i].match.winner.manager = await ManagerModel.findById(lineups[i].match.winner.manager)
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
        lineups[i].team.manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].match = await MatchModel.findById(lineups[i].match)
        lineups[i].match.homeTeam = await TeamModel.findById(lineups[i].match.homeTeam)
        lineups[i].match.homeTeam.manager = await ManagerModel.findById(lineups[i].match.homeTeam.manager)
        lineups[i].match.awayTeam = await TeamModel.findById(lineups[i].match.awayTeam)
        lineups[i].match.awayTeam.manager = await ManagerModel.findById(lineups[i].match.awayTeam.manager)
        lineups[i].match.winner = await TeamModel.findById(lineups[i].match.winner)
        if (lineups[i].match.winner != null) {
          lineups[i].match.winner.manager = await ManagerModel.findById(lineups[i].match.winner.manager)
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
        lineups[i].team.manager = await ManagerModel.findById(lineups[i].team.manager)
        lineups[i].match = await MatchModel.findById(lineups[i].match)
        lineups[i].match.homeTeam = await TeamModel.findById(lineups[i].match.homeTeam)
        lineups[i].match.homeTeam.manager = await ManagerModel.findById(lineups[i].match.homeTeam.manager)
        lineups[i].match.awayTeam = await TeamModel.findById(lineups[i].match.awayTeam)
        lineups[i].match.awayTeam.manager = await ManagerModel.findById(lineups[i].match.awayTeam.manager)
        lineups[i].match.winner = await TeamModel.findById(lineups[i].match.winner)
        if (lineups[i].match.winner != null) {
          lineups[i].match.winner.manager = await ManagerModel.findById(lineups[i].match.winner.manager)
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
      lineup.team.manager = await ManagerModel.findById(lineup.team.manager)
      lineup.match = await MatchModel.findById(lineup.match)
      lineup.match.homeTeam = await TeamModel.findById(lineup.match.homeTeam)
      lineup.match.homeTeam.manager = await ManagerModel.findById(lineup.match.homeTeam.manager)
      lineup.match.awayTeam = await TeamModel.findById(lineup.match.awayTeam)
      lineup.match.awayTeam.manager = await ManagerModel.findById(lineup.match.awayTeam.manager)
      lineup.match.winner = await TeamModel.findById(lineup.match.winner)
      if (lineup.match.winner != null) {
        lineup.match.winner.manager = await ManagerModel.findById(lineup.match.winner.manager)
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
      goalkeeper.nationality = await TeamModel.findById(goalkeeper.nationality)
      goalkeeper.nationality.manager = await ManagerModel.findById(goalkeeper.nationality.manager)
      goalkeeper.club = await ClubModel.findById(goalkeeper.club)
      console.log(goalkeeper)
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
