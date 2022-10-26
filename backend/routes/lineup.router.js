const { Router } = require('express')
const LineupController = require('../controllers/lineup.controller')

const router = Router()

router.get('/', LineupController.find)
router.get('/:id', LineupController.findById)
router.get('/type/:type', LineupController.findByType)
router.get('/match/:match', LineupController.findByMatch)
router.get('/team/:team', LineupController.findByTeam)
router.get('/match/:match/team/:team', LineupController.findByMatchAndTeam)

router.post('/', LineupController.create)
router.post('/updateEverything', LineupController.createAndUpdateEverything)

router.put('/:id', LineupController.update)

router.delete('/:id', LineupController.delete)

module.exports = router
