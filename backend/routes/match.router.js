const { Router } = require('express')
const MatchController = require('../controllers/match.controller')

const router = Router()

router.get('/', MatchController.find)
router.get('/:id', MatchController.findById)
router.get('/date/:date', MatchController.findByDate)
router.get('/team/:team', MatchController.findByTeam)
router.get('/round/:round', MatchController.findByRoundOrGroup)
router.get('/status/finished', MatchController.findFinishedMatches)
router.get('/status/unfinished', MatchController.findUnfinishedMatches)

router.post('/', MatchController.create)

router.put('/:id', MatchController.update)
router.put('/homeScore/:id', MatchController.updateHomeScore)
router.put('/awayScore/:id', MatchController.updateAwayScore)
router.put('/minute/:id', MatchController.updateMinute)
router.put('/finished/:id', MatchController.updateIsFinished)
router.put('/halftime/:id', MatchController.updateIsHalfTime)
router.put('/winner/:id', MatchController.updateWinner)
router.put('/hasStarted/:id', MatchController.updateHasStarted)

router.delete('/:id', MatchController.delete)

module.exports = router
