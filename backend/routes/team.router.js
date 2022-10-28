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
router.get('/atLeastOneTitle', TeamController.findByAtLeastOneTitle)

router.post('/', TeamController.create)

router.put('/:id', TeamController.update)

router.delete('/:id', TeamController.delete)

module.exports = router
