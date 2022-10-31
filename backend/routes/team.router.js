const { Router } = require('express')
const TeamController = require('../controllers/team.controller')

const router = Router()

router.get('/', TeamController.find)
router.get('/:id', TeamController.findById)
router.get('/name/:name', TeamController.findByName)
router.get('/region/:region', TeamController.findByRegion)
router.get('/fifaCode/:fifaCode', TeamController.findByFifaCode)
router.get('/iso2/:iso2', TeamController.findByIso2)
router.get('/group/:group', TeamController.findByGroup)
router.get('/findBy/atLeastOneTitle', TeamController.findByAtLeastOneTitle)

router.post('/', TeamController.create)

router.put('/:id', TeamController.update)
router.put('/addGoalsFor/:id/:goalsFor', TeamController.addGoalsFor)
router.put('/addGoalsAgainst/:id/:goalsAgainst', TeamController.addGoalsAgainst)
router.put('/addPoints/:id/:points', TeamController.addPoints)
router.put('/addMatchesPlayed/:id/:matchesPlayed', TeamController.addMatchesPlayed)
router.put('/addMatchesWon/:id/:matchesWon', TeamController.addMatchesWon)
router.put('/addMatchesDrawn/:id/:matchesDrawn', TeamController.addMatchesDrawn)
router.put('/addMatchesLost/:id/:matchesLost', TeamController.addMatchesLost)
router.put('/updateNoOfTitles/:id/:noOfTitles', TeamController.updateNoOfTitles)
router.put('/updateIsEliminated/:id/:isEliminated', TeamController.updateIsEliminated)

router.delete('/:id', TeamController.delete)

module.exports = router
