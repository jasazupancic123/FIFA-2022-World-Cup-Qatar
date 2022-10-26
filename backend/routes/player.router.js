const { Router } = require('express')
const PlayerController = require('../controllers/player.controller')

const router = Router()

router.get('/', PlayerController.find)
router.get('/:id', PlayerController.findById)
router.get('/name/:firstName/:lastName', PlayerController.findByName)
router.get('/country/:country', PlayerController.findByCountry)
router.get('/club/:club', PlayerController.findByClub)
router.get('/position/:position', PlayerController.findByPosition)
router.get('/stats/mostGoals', PlayerController.findByMostGoals)
router.get('/stats/mostAssists', PlayerController.findByMostAssists)
router.get('/stats/mostYellowCards', PlayerController.findByMostYellowCards)
router.get('/stats/mostRedCards', PlayerController.findByMostRedCards)
router.get('/youngestByCountry/:country', PlayerController.findYoungestByCountry)

router.post('/', PlayerController.create)

router.put('/:id', PlayerController.update)
router.put('/goals/:id', PlayerController.updateGoals)
router.put('/assists/:id', PlayerController.updateAssists)
router.put('/yellowCards/:id', PlayerController.updateYellowCards)
router.put('/redCards/:id', PlayerController.updateRedCards)
router.put('/appearances/:id', PlayerController.updateAppearances)
router.put('/picture/:picture/:id', PlayerController.updatePicture)

router.delete('/:id', PlayerController.delete)

module.exports = router
