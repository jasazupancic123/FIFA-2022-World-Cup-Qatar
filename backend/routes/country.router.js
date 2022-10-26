const { Router } = require('express')
const CountryController = require('../controllers/country.controller')

const router = Router()

router.get('/', CountryController.find)
router.get('/:id', CountryController.findById)
router.get('/name/:name', CountryController.findByName)
router.get('/region/:region', CountryController.findByRegion)
router.get('/fifaCode/:fifaCode', CountryController.findByFifaCode)
router.get('/iso2/:iso2', CountryController.findByIso2)
router.get('/group/:group', CountryController.findByGroup)
router.get('/atLeastOneTitle', CountryController.findByAtLeastOneTitle)

router.post('/', CountryController.create)

router.put('/:id', CountryController.update)

router.delete('/:id', CountryController.delete)

module.exports = router
