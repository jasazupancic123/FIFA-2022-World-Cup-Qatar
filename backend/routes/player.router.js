const { Router } = require('express')
const PlayerController = require('../controllers/player.controller')

const router = Router()

router.get('/', PlayerController.find)
router.get('/:id', PlayerController.findById)
router.get('/name/:firstName/:lastName', PlayerController.findByName)
router.get('/team/:team', PlayerController.findByTeam)
router.get('/club/:club', PlayerController.findByClub)
router.get('/position/:position', PlayerController.findByPosition)
router.get('/team/:team/position/:position', PlayerController.findByTeamAndPosition)
router.get('/stats/mostGoals', PlayerController.findByMostGoals)
router.get('/stats/mostAssists', PlayerController.findByMostAssists)
router.get('/stats/mostYellowCards', PlayerController.findByMostYellowCards)
router.get('/stats/mostRedCards', PlayerController.findByMostRedCards)
router.get('/youngestByCountry/:team', PlayerController.findYoungestByTeam)

router.post('/', PlayerController.create)

router.put('/:id', PlayerController.update)
router.put('/goals/:id/:goals', PlayerController.updateGoals)
router.put('/assists/:id/:assists', PlayerController.updateAssists)
router.put('/yellowCards/:id/:yellowCards', PlayerController.updateYellowCards)
router.put('/redCards/:id/:redCards', PlayerController.updateRedCards)
router.put('/appearances/:id/:appearances', PlayerController.updateAppearances)
router.put('/picture/:id/:picture', PlayerController.updatePicture)

router.delete('/:id', PlayerController.delete)

module.exports = router
