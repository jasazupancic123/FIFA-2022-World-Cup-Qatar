const { Router } = require('express')
const GoalController = require('../controllers/goal.controller')

const router = Router()

router.get('/', GoalController.find)
router.get('/:id', GoalController.findById)
router.get('/scorer/:scorer', GoalController.findByScorer)
router.get('/assister/:assister', GoalController.findByAssister)
router.get('/minute/:minute', GoalController.findByMinute)
router.get('/match/:match', GoalController.findByMatch)
router.get('/match/:match/homeTeam', GoalController.findByMatchAndHomeTeam)
router.get('/match/:match/awayTeam', GoalController.findByMatchAndAwayTeam)

router.post('/', GoalController.create)
router.post('/updateEverything', GoalController.createAndUpdateEverything)

router.put('/:id', GoalController.update)

router.delete('/:id', GoalController.delete)

module.exports = router
